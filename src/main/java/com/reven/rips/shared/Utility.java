package com.reven.rips.shared;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.reven.rips.application.dto.AttachmentDto;
import com.reven.rips.application.dto.FileInsideZipDTO;
import com.reven.rips.application.dto.InvoiceMessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import com.google.gson.Gson;

@Slf4j
public class Utility {

    public static final String MULTIPART_MIXED_TYPE = "multipart/mixed";
    public static final String ZIP = ".zip";
    private static final Gson gson = new Gson();
    public static final String DEFAULT_USER = "fe_system";
    public static final String BYTE_ORDER_MARK_CHARACTER = "\uFEFF";
    public static final String DOCUMENT_WAS_VALIDATED_BY_DIAN_RULE = "Documento validado por la DIAN";
    private static final List<String> APPLICATION_ZIP =
            Arrays.asList(
                    "application/zip",
                    "application/x-zip-compressed",
                    "application/x-compressed",
                    "multipart/x-zip",
                    "application/zip-compressed",
                    "application/octet-stream",
                    "multipart/mixed",
                    "multipart/alternative",
                    "application\\/zip",
                    "text/plain",
                    "application/octet-stream");

    public static String convertObjectToJSON(Object object) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("[convertObjectToJSON] ::: {} ::: [convertObjectToJSON]", e.getMessage());
        }
        return StringUtils.EMPTY;
    }


    public static String formatLocalDateToSpecificOne(final String format,
                                                      final LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern(format));
    }

    public static String getEmailIndexed(String emailToFix) {
        if (emailToFix.contains("<")) {
            return emailToFix.split("<")[1].split(">")[0];
        } else {
            return emailToFix;
        }
    }

    public static FileInsideZipDTO getXML(String messagePartBodyZipContent) {
        final byte[] bytes = Base64.getDecoder().decode(messagePartBodyZipContent);
        final String xmlString = readXmlFromZip(bytes);
        if (!Objects.isNull(xmlString) && StringUtils.isNotEmpty(xmlString)) {
            return FileInsideZipDTO.builder().fileName(".xml").data(bytes).build();
        }
        return null;
    }

    public static FileInsideZipDTO getXML(byte[] bytes) {
        final String xmlString = readStringFromXmlFromZip(bytes);
        if (!Objects.isNull(xmlString) && StringUtils.isNotEmpty(xmlString)) {
            return FileInsideZipDTO.builder().fileName(".xml").data(bytes).xmlString(xmlString).build();
        }
        return null;
    }

    public static List<AttachmentDto> giveMeAttachmentFromZip(InvoiceMessageDTO invoiceMessageDTO) {
        final String messagePartBodyZipContent = invoiceMessageDTO.getAttachments().get(0)
                .getZipString();
        return readFilesFromBase64(Base64.getDecoder().decode(messagePartBodyZipContent), invoiceMessageDTO);
    }

    public static String generateFileId(
            final InvoiceMessageDTO invoiceMessageDTO,
            final String attachmentName) {
        log.debug("method name [generateFileId]");
        return invoiceMessageDTO.getSubject().getNitBiller() +
                invoiceMessageDTO.getSubject().getNumber() +
                Utility.getExtensionFromFileName(attachmentName);
    }

    public static List<AttachmentDto> readFilesFromBase64(byte[] bytes,
                                                          InvoiceMessageDTO invoiceMessageDTO) {
        List<AttachmentDto> attachmentDtos = new ArrayList<>();
        try {
            ZipInputStream zipStream = new ZipInputStream(new ByteArrayInputStream(bytes));
            ZipEntry entry = null;
            while ((entry = zipStream.getNextEntry()) != null) {
                String entryName = entry.getName();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] bytesEntry = new byte[1024];
                int length;
                while ((length = zipStream.read(bytesEntry)) >= 0) {
                    bos.write(bytesEntry, 0, length);
                }
                bos.close();
                zipStream.closeEntry();
                attachmentDtos.add(
                        AttachmentDto.builder().name(entryName).content(bos.toByteArray())
                                .contentType(validateContentType(entryName))
                                .fileId(generateFileId(invoiceMessageDTO, entryName)).build());
            }
            zipStream.close();
        } catch (Exception ex) {
            log.error("Se presento un error an intentar tomar los archivos del zip error: {}:{}",
                    ex.getLocalizedMessage(), ex.getStackTrace());
        }
        return attachmentDtos;
    }

    public static List<AttachmentDto> readFilesFromBase64(byte[] bytes) {
        List<AttachmentDto> attachmentDtos = new ArrayList<>();
        try (ZipInputStream zipStream = new ZipInputStream(new ByteArrayInputStream(bytes))) {
            ZipEntry entry;

            while ((entry = zipStream.getNextEntry()) != null) {
                if (entry.isDirectory()) {
                    continue;
                }
                try {
                    String entryName = entry.getName();

                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[4096];
                    int length;

                    while ((length = zipStream.read(buffer)) >= 0) {
                        bos.write(buffer, 0, length);
                    }

                    attachmentDtos.add(
                            AttachmentDto.builder()
                                    .name(entryName)
                                    .content(bos.toByteArray())
                                    .contentType(validateContentTypeRadication(entryName))
                                    .fileId(UUID.randomUUID().toString())
                                    .build());

                } catch (Exception fileEx) {
                    log.error("Se presentó un error al leer la entrada '{}' del zip. Error: {}",
                            entry.getName(), fileEx.getMessage());
                }
                zipStream.closeEntry();
            }
        } catch (Exception ex) {
            log.error("Se presentó un error general al procesar el stream del zip. Error: {}",
                    ex.getMessage(), ex);
        }
        return attachmentDtos;
    }

    public static String validateContentType(final String name) {
        if (name.endsWith(".xml")) {
            return "text/xml";
        } else if (name.endsWith(".pdf")) {
            return "application/pdf";
        }
        return "";
    }

    public static String validateContentTypeRadication(final String name) {
        if (name.endsWith(".zip")) {
            return "application/zip";
        } else if (name.endsWith(".json")) {
            return "application/json";
        }else if (name.endsWith(".txt")) {
            return "text/plain";

        }
        return "";
    }

    public static String readXmlFromZip(byte[] bytes) {
        String xmlString = null;
        try {
            ZipInputStream zipStream = new ZipInputStream(new ByteArrayInputStream(bytes));
            ZipEntry entry = null;
            while ((entry = zipStream.getNextEntry()) != null) {

                String entryName = entry.getName();
                if (entryName.endsWith(".xml")) {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();

                    byte[] bytesEntry = new byte[1024];
                    int length;
                    while ((length = zipStream.read(bytesEntry)) >= 0) {
                        bos.write(bytesEntry, 0, length);
                    }
                    bos.close();
                    zipStream.closeEntry();
                    xmlString = new String(bos.toByteArray(), StandardCharsets.UTF_8);
                }
            }
            zipStream.close();
        } catch (Exception ex) {
            ex.getMessage();
        }
        return xmlString;
    }

    public static String readStringFromXmlFromZip(byte[] bytes) {
        String xmlString = null;
        try {
            ZipInputStream zipStream = new ZipInputStream(new ByteArrayInputStream(bytes));
            ZipEntry entry = null;
            while ((entry = zipStream.getNextEntry()) != null) {

                String entryName = entry.getName();
                if (entryName.endsWith(".xml")) {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();

                    byte[] bytesEntry = new byte[1024];
                    int length;
                    while ((length = zipStream.read(bytesEntry)) >= 0) {
                        bos.write(bytesEntry, 0, length);
                    }
                    bos.close();
                    zipStream.closeEntry();
                    return new String(bos.toByteArray(), StandardCharsets.UTF_8);
                }
            }
            zipStream.close();
        } catch (Exception ex) {
            ex.getMessage();
        }
        return xmlString;
    }

    public static String getExtensionFromFileName(final String fileName) {
        return fileName.split("\\.")[1];
    }

    public static boolean validateIfIsXml(final String stringFile) {
        return stringFile.contains("xml");
    }


    public static String convertToJSON(Object object) {
        return gson.toJson(object);
    }

    public static String parseXmlDocument(Resource resource, String xmlDocument) {
        try {
            Transformer transformer = TransformerFactory.newInstance()
                    .newTransformer(new StreamSource(resource.getInputStream()));
            StringWriter writer = new StringWriter();
            transformer
                    .transform(new StreamSource(new StringReader(cleanXmlDocument(xmlDocument))),
                            new StreamResult(writer));

            return writer.toString();
        } catch (IOException | TransformerException e) {
            log.error("An error occurred parsing the XML file: " + e.getMessage());
            return null;
        }
    }

    private static String cleanXmlDocument(String xmlDocument) {
        return xmlDocument.replace(BYTE_ORDER_MARK_CHARACTER, StringUtils.EMPTY).trim();
    }

}
