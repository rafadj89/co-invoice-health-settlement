package com.reven.rips.application.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reven.rips.application.assembler.XmlDataAssembler;
import com.reven.rips.application.dto.AttachmentDto;
import com.reven.rips.application.dto.FacturaDto;
import com.reven.rips.application.dto.FileInsideZipDTO;
import com.reven.rips.application.dto.XmlDataDTO;
import com.reven.rips.infrastructure.domain.FacturaService;
import com.reven.rips.infrastructure.entity.factura.XmlData;
import com.reven.rips.shared.InvoiceDocuments;
import com.reven.rips.shared.Utility;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.util.Objects.nonNull;

@Slf4j
@Component
@AllArgsConstructor
public class FacturaApplicationService {


    public XmlData saveInvoices(MultipartFile files) throws IOException {
        byte[] fileBytes = files.getBytes();
        AttachmentDto attachmentDTO = new AttachmentDto();
        attachmentDTO.setContent(fileBytes);
        attachmentDTO.setSize((int)files.getSize());
        attachmentDTO.setName(files.getOriginalFilename());

        attachmentDTO.setFileId(UUID.randomUUID().toString());
        attachmentDTO.setZipString(Base64.getEncoder().encodeToString(fileBytes));
        return null;
    }




}
