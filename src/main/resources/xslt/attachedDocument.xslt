<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2"
  xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2"
  xmlns:ad="urn:oasis:names:specification:ubl:schema:xsd:AttachedDocument-2"
  exclude-result-prefixes="cbc cac xsl ad">
  <xsl:template match="/">
    <attachedDocument>
      <xsl:for-each select="ad:AttachedDocument">
        <billId>
          <xsl:value-of select="cbc:ParentDocumentID"/>
        </billId>
        <thirdPartyDocumentType>
          <xsl:value-of select="cac:SenderParty/cac:PartyTaxScheme/cbc:CompanyID/@schemeName"/>
        </thirdPartyDocumentType>
        <thirdPartyDocumentNumber>
          <xsl:value-of
            select="cac:SenderParty/cac:PartyTaxScheme/cbc:CompanyID"/>
        </thirdPartyDocumentNumber>
        <thirdPartyName>
          <xsl:value-of
            select="cac:SenderParty/cac:PartyTaxScheme/cbc:RegistrationName"/>
        </thirdPartyName>
        <receiverPartyDocumentNumber>
          <xsl:value-of
            select="cac:ReceiverParty/cac:PartyTaxScheme/cbc:CompanyID"/>
        </receiverPartyDocumentNumber>
        <receiverPartyDocumentType>
					<xsl:value-of
						select="cac:ReceiverParty/cac:PartyTaxScheme/cbc:CompanyID/@schemeName"/>
		</receiverPartyDocumentType>
        <receiverPartyName>
          <xsl:value-of
            select="cac:ReceiverParty/cac:PartyTaxScheme/cbc:RegistrationName"/>
        </receiverPartyName>
        <validationDate>
          <xsl:value-of
            select="cac:ParentDocumentLineReference/cac:DocumentReference/cac:ResultOfVerification/cbc:ValidationDate"/>
        </validationDate>
        <documentDescription>
          <xsl:text disable-output-escaping="yes">&lt;![CDATA[</xsl:text>
          <xsl:value-of disable-output-escaping="yes"
            select="cac:Attachment/cac:ExternalReference/cbc:Description"/>
          <xsl:text disable-output-escaping="yes">]]&gt;</xsl:text>
        </documentDescription>
        <dianValidationDescription>
          <xsl:text disable-output-escaping="yes">&lt;![CDATA[</xsl:text>
          <xsl:value-of disable-output-escaping="yes"
            select="cac:ParentDocumentLineReference/cac:DocumentReference/cac:Attachment/cac:ExternalReference/cbc:Description"/>
          <xsl:text disable-output-escaping="yes">]]&gt;</xsl:text>
        </dianValidationDescription>
      </xsl:for-each>
    </attachedDocument>
  </xsl:template>
</xsl:stylesheet>