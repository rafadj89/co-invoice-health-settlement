<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2"
  xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2"
  xmlns:apr="urn:oasis:names:specification:ubl:schema:xsd:ApplicationResponse-2"
  exclude-result-prefixes="cbc cac xsl apr">
  <xsl:template match="/" name="dian">
    <dianValidation>
      <xsl:for-each select="apr:ApplicationResponse">
        <description>
          <xsl:value-of select="cac:DocumentResponse/cac:Response/cbc:Description"/>
        </description>
        <providerNit>
          <xsl:value-of select="cac:ReceiverParty/cac:PartyTaxScheme/cbc:CompanyID"/>
        </providerNit>
      </xsl:for-each>
    </dianValidation>
  </xsl:template>
</xsl:stylesheet>