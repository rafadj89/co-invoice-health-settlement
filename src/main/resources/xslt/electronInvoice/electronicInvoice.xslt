<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2"
  xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2"
  xmlns:ext="urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2"
  xmlns:sts="dian:gov:co:facturaelectronica:Structures-2-1"
  xmlns:ei="urn:oasis:names:specification:ubl:schema:xsd:Invoice-2"
  exclude-result-prefixes="cac cbc ext sts ei">
  <xsl:template match="/">
    <electronicInvoice>
      <xsl:for-each select="ei:Invoice">
        <!--        This taxes not appear into XML attached-->
        <!--        <xsl:for-each select="cac:WithholdingTaxTotal">-->
        <!--          <taxes>-->
        <!--            <xsl:for-each select="cac:TaxSubtotal/cac:TaxCategory">-->
        <!--              <tax>-->
        <!--                <taxId>-->
        <!--                  <xsl:value-of select="cac:TaxScheme/cbc:ID"/>-->
        <!--                </taxId>-->
        <!--                <name>-->
        <!--                  <xsl:value-of select="cac:TaxScheme/cbc:Name"/>-->
        <!--                </name>-->
        <!--                <percent>-->
        <!--                  <xsl:value-of select="cbc:Percent"/>-->
        <!--                </percent>-->
        <!--              </tax>-->
        <!--            </xsl:for-each>-->
        <!--          </taxes>-->
        <!--        </xsl:for-each>-->
        <xsl:for-each
          select="ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sts:DianExtensions/sts:InvoiceControl/sts:AuthorizationPeriod">
          <startDate>
            <xsl:value-of select="cbc:StartDate"/>
          </startDate>
          <finalDate>
            <xsl:value-of select="cbc:EndDate"/>
          </finalDate>
        </xsl:for-each>
        <emissionDate>
          <xsl:value-of select="cbc:IssueDate"/>
        </emissionDate>
        <resolutionId>
          <xsl:value-of
            select="ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sts:DianExtensions/sts:InvoiceControl/sts:InvoiceAuthorization"/>
        </resolutionId>
        <startRange>
          <xsl:value-of
            select="ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sts:DianExtensions/sts:InvoiceControl/sts:AuthorizedInvoices/sts:From"/>
        </startRange>
        <finalRange>
          <xsl:value-of
            select="ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/sts:DianExtensions/sts:InvoiceControl/sts:AuthorizedInvoices/sts:To"/>
        </finalRange>
        <expirationDate>
          <xsl:value-of
            select="cac:PaymentMeans/cbc:PaymentDueDate"/>
        </expirationDate>
        <uniqueElectronicDocumentCode>
          <xsl:value-of
            select="cbc:UUID"/>
        </uniqueElectronicDocumentCode>
        <paymentMeansType>
          <xsl:value-of
            select="cac:PaymentMeans/cbc:ID"/>
        </paymentMeansType>
        <description>
          <xsl:value-of
            select="cac:InvoiceLine/cac:Item/cbc:Description"/>
        </description>

        <totalValue>
          <xsl:value-of
            select="cac:LegalMonetaryTotal/cbc:PayableAmount"/>
        </totalValue>

        <amount>
          <xsl:value-of
            select="cac:InvoiceLine/cbc:InvoicedQuantity"/>
        </amount>
        <unitValue>
          <xsl:value-of
            select="cac:InvoiceLine/cac:Price/cbc:PriceAmount"/>
        </unitValue>
        <subTotal>
          <xsl:value-of
            select="cac:LegalMonetaryTotal/cbc:LineExtensionAmount"/>
        </subTotal>
        <iva>
          <xsl:value-of
            select="cac:TaxTotal/cac:TaxSubtotal/cbc:TaxAmount"/>
        </iva>
        <withholding>
          <xsl:value-of
            select="cac:WithholdingTaxTotal/cbc:TaxAmount"/>
        </withholding>
        <expeditionDate>
          <xsl:value-of
            select="cbc:IssueDate"/>
        </expeditionDate>
        <taxPercent>
          <xsl:value-of
            select="cac:TaxTotal/cac:TaxSubtotal/cac:TaxCategory/cbc:Percent"/>
        </taxPercent>
        <tax>
          <xsl:value-of
            select="cac:TaxTotal/cac:TaxSubtotal/cac:TaxCategory/cac:TaxScheme/cbc:Name"/>
        </tax>
        <taxValue>
          <xsl:value-of
            select="cac:TaxTotal/cbc:TaxAmount"/>
        </taxValue>
        <additionalInformation>
          <xsl:for-each
            select="ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/ei:CustomTagGeneral/ei:Interoperabilidad/ei:Group[@schemeName='Sector Salud']">
            <xsl:for-each select="ei:Collection[@schemeName='Usuario']">
              <users>
                <xsl:if test="ei:AdditionalInformation[1]/ei:Value">
                  <lenderCode>
                    <xsl:value-of
                      select="ei:AdditionalInformation[1]/ei:Value"/>
                  </lenderCode>
                </xsl:if>
                <xsl:if test="ei:AdditionalInformation[2]/ei:Value">
                  <documentTypeUser>
                    <xsl:value-of
                      select="ei:AdditionalInformation[2]/ei:Value"/>
                  </documentTypeUser>
                </xsl:if>
                <xsl:if test="ei:AdditionalInformation[3]/ei:Value">
                  <documentNumberUser>
                    <xsl:value-of
                      select="ei:AdditionalInformation[3]/ei:Value"/>
                  </documentNumberUser>
                </xsl:if>
                <xsl:if test="ei:AdditionalInformation[4]/ei:Value">
                  <surname>
                    <xsl:value-of
                      select="ei:AdditionalInformation[4]/ei:Value"/>
                  </surname>
                </xsl:if>
                <xsl:if test="ei:AdditionalInformation[5]/ei:Value">
                  <secondSurname>
                    <xsl:value-of
                      select="ei:AdditionalInformation[5]/ei:Value"/>
                  </secondSurname>
                </xsl:if>
                <xsl:if test="ei:AdditionalInformation[6]/ei:Value">
                  <firstName>
                    <xsl:value-of
                      select="ei:AdditionalInformation[6]/ei:Value"/>
                  </firstName>
                </xsl:if>
                <xsl:if test="ei:AdditionalInformation[7]/ei:Value">
                  <secondName>
                    <xsl:value-of
                      select="ei:AdditionalInformation[7]/ei:Value"/>
                  </secondName>
                </xsl:if>
                <xsl:if test="ei:AdditionalInformation[8]/ei:Value">
                  <userType>
                    <xsl:value-of
                      select="ei:AdditionalInformation[8]/ei:Value"/>
                  </userType>
                </xsl:if>
                <xsl:if test="ei:AdditionalInformation[9]/ei:Value">
                  <contractingModality>
                    <xsl:value-of
                      select="ei:AdditionalInformation[9]/ei:Value"/>
                  </contractingModality>
                </xsl:if>
                <xsl:if test="ei:AdditionalInformation[10]/ei:Value">
                  <coverage>
                    <xsl:value-of
                      select="ei:AdditionalInformation[10]/ei:Value"/>
                  </coverage>
                </xsl:if>
                <xsl:if test="ei:AdditionalInformation[11]/ei:Value">
                  <authorizationNumber>
                    <xsl:value-of
                      select="ei:AdditionalInformation[11]/ei:Value"/>
                  </authorizationNumber>
                </xsl:if>
                <xsl:if test="ei:AdditionalInformation[12]/ei:Value">
                  <prescriptionNumber>
                    <xsl:value-of
                      select="ei:AdditionalInformation[12]/ei:Value"/>
                  </prescriptionNumber>
                </xsl:if>
                <xsl:if test="ei:AdditionalInformation[13]/ei:Value">
                  <supplyNumber>
                    <xsl:value-of
                      select="ei:AdditionalInformation[13]/ei:Value"/>
                  </supplyNumber>
                </xsl:if>
                <xsl:if test="ei:AdditionalInformation[14]/ei:Value">
                  <contractNumber>
                    <xsl:value-of
                      select="ei:AdditionalInformation[14]/ei:Value"/>
                  </contractNumber>
                </xsl:if>
                <xsl:if test="ei:AdditionalInformation[15]/ei:Value">
                  <policyNumber>
                    <xsl:value-of
                      select="ei:AdditionalInformation[15]/ei:Value"/>
                  </policyNumber>
                </xsl:if>
                <xsl:if test="ei:AdditionalInformation[16]/ei:Value">
                  <invoicingStartDate>
                    <xsl:value-of
                      select="ei:AdditionalInformation[16]/ei:Value"/>
                  </invoicingStartDate>
                </xsl:if>
                <xsl:if test="ei:AdditionalInformation[17]/ei:Value">
                  <invoicingEndDate>
                    <xsl:value-of
                      select="ei:AdditionalInformation[17]/ei:Value"/>
                  </invoicingEndDate>
                </xsl:if>
                <xsl:if test="ei:AdditionalInformation[18]/ei:Value">
                  <coPayment>
                    <xsl:value-of
                      select="ei:AdditionalInformation[18]/ei:Value"/>
                  </coPayment>
                </xsl:if>
                <xsl:if test="ei:AdditionalInformation[19]/ei:Value">
                  <moderatingFee>
                    <xsl:value-of
                      select="ei:AdditionalInformation[19]/ei:Value"/>
                  </moderatingFee>
                </xsl:if>
                <xsl:if test="ei:AdditionalInformation[20]/ei:Value">
                  <recuperationFee>
                    <xsl:value-of
                      select="ei:AdditionalInformation[20]/ei:Value"/>
                  </recuperationFee>
                </xsl:if>
                <xsl:if test="ei:AdditionalInformation[21]/ei:Value">
                  <sharedPayment>
                    <xsl:value-of
                      select="ei:AdditionalInformation[21]/ei:Value"/>
                  </sharedPayment>
                </xsl:if>
              </users>
            </xsl:for-each>
          </xsl:for-each>
        </additionalInformation>
      </xsl:for-each>
    </electronicInvoice>
  </xsl:template>
</xsl:stylesheet>