<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2"
  xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2"
  xmlns:ad="urn:oasis:names:specification:ubl:schema:xsd:AttachedDocument-2"
  exclude-result-prefixes="cbc cac xsl ad">
  <xsl:template match="/">
    <invoiceMessage>
      <xsl:for-each select="ad:AttachedDocument">
        <lenderId>
          <xsl:value-of
            select="cac:ReceiverParty/cac:PartyTaxScheme/cbc:CompanyID"/>
        </lenderId>
        <lenderDocumentType>
          <xsl:value-of
            select="cac:ReceiverParty/cac:PartyTaxScheme/cbc:CompanyID/@schemeName"/>
        </lenderDocumentType>
        <lenderName>
          <xsl:value-of
            select="cac:SenderParty/cac:PartyTaxScheme/cbc:RegistrationName"/>
        </lenderName>
        <providerNit>
          <xsl:value-of
            select="cac:SenderParty/cac:PartyTaxScheme/cbc:CompanyID"/>
        </providerNit>
        <validationDate>
          <xsl:value-of
            select="cac:ParentDocumentLineReference/cac:DocumentReference/cac:ResultOfVerification/cbc:ValidationDate"/>
        </validationDate>
        <description>
          <xsl:text disable-output-escaping="yes">&lt;![CDATA[</xsl:text>
          <xsl:value-of disable-output-escaping="yes"
            select="cac:Attachment/cac:ExternalReference/cbc:Description"/>
          <xsl:text disable-output-escaping="yes">]]&gt;</xsl:text>
        </description>
        <dianValidationDescription>
          <xsl:text disable-output-escaping="yes">&lt;![CDATA[</xsl:text>
          <xsl:value-of disable-output-escaping="yes"
            select="cac:ParentDocumentLineReference/cac:DocumentReference/cac:Attachment/cac:ExternalReference/cbc:Description"/>
          <xsl:text disable-output-escaping="yes">]]&gt;</xsl:text>
        </dianValidationDescription>
        <documentType>
          <xsl:value-of select="cac:SenderParty/cac:PartyTaxScheme/cbc:CompanyID/@schemeName"/>
        </documentType>
      </xsl:for-each>
    </invoiceMessage>
  </xsl:template>
</xsl:stylesheet>