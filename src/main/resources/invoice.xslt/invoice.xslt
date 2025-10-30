<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <invoice>
      <xsl:for-each select="Invoice">
        <xsl:for-each select="WithholdingTaxTotal">
          <xsl:for-each select="TaxSubtotal/TaxCategory">
            <taxes>
              <taxId>
                <xsl:value-of select="TaxScheme/ID"/>
              </taxId>
              <name>
                <xsl:value-of select="TaxScheme/Name"/>
              </name>
              <percent>
                <xsl:value-of select="Percent"/>
              </percent>
            </taxes>
          </xsl:for-each>
        </xsl:for-each>
        <xsl:for-each
          select="UBLExtensions/UBLExtension/ExtensionContent/DianExtensions/InvoiceControl/AuthorizationPeriod">
          <startDate>
            <xsl:value-of select="StartDate"/>
          </startDate>
          <finalDate>
            <xsl:value-of select="EndDate"/>
          </finalDate>
        </xsl:for-each>
        <emissionDate>
          <xsl:value-of select="IssueDate"/>
        </emissionDate>
        <resolutionId>
          <xsl:value-of
            select="UBLExtensions/UBLExtension/ExtensionContent/DianExtensions/InvoiceControl/InvoiceAuthorization"/>
        </resolutionId>
        <startRange>
          <xsl:value-of
            select="UBLExtensions/UBLExtension/ExtensionContent/DianExtensions/InvoiceControl/AuthorizedInvoices/From"/>
        </startRange>
        <finalRange>
          <xsl:value-of
            select="UBLExtensions/UBLExtension/ExtensionContent/DianExtensions/InvoiceControl/AuthorizedInvoices/To"/>
        </finalRange>
        <providerNitPartyTaxScheme>
          <xsl:value-of
                  select="AccountingSupplierParty/Party/PartyTaxScheme/CompanyID"/>
        </providerNitPartyTaxScheme>
        <providerNitPartyLegalEntity>
          <xsl:value-of
                  select="AccountingSupplierParty/Party/PartyLegalEntity/CompanyID"/>
        </providerNitPartyLegalEntity>
        <expirationDate>
          <xsl:value-of
            select="PaymentMeans/PaymentDueDate"/>
        </expirationDate>
        <cufe>
          <xsl:value-of
            select="UUID"/>
        </cufe>
        <billId>
          <xsl:value-of
            select="ID"/>
        </billId>
        <paymentMeansType>
          <xsl:value-of
            select="PaymentMeans/ID"/>
        </paymentMeansType>
        <description>
          <xsl:value-of
            select="InvoiceLine/Item/Description"/>
        </description>
        <totalValue>
          <xsl:value-of
            select="LegalMonetaryTotal/PayableAmount"/>
        </totalValue>
        <amount>
          <xsl:value-of
            select="InvoiceLine/InvoicedQuantity"/>
        </amount>
        <unitValue>
          <xsl:value-of
            select="InvoiceLine/Price/PriceAmount"/>
        </unitValue>
        <subTotal>
          <xsl:value-of
            select="LegalMonetaryTotal/LineExtensionAmount"/>
        </subTotal>
        <totalDiscount>
          <xsl:value-of
              select="LegalMonetaryTotal/PrepaidAmount"/>
        </totalDiscount>
        <baseIva>
          <xsl:value-of
                  select="TaxTotal/TaxSubtotal/TaxableAmount"/>
        </baseIva>
        <iva>
          <xsl:value-of
            select="TaxTotal/TaxSubtotal/TaxAmount"/>
        </iva>
        <billInitialDate>
          <xsl:value-of select="InvoicePeriod/StartDate"/>
        </billInitialDate>
        <billFinalDate>
          <xsl:value-of select="InvoicePeriod/EndDate"/>
        </billFinalDate>
        <fromLoader>
          <xsl:value-of select="string(true())"/>
        </fromLoader>
        <totalTaxes>
          <xsl:value-of
                  select="LegalMonetaryTotal/TaxInclusiveAmount"/>
        </totalTaxes>
        <rtefte>
          <xsl:value-of
            select="WithholdingTaxTotal/TaxAmount"/>
        </rtefte>
        <expeditionDate>
          <xsl:value-of
            select="IssueDate"/>
        </expeditionDate>
        <taxPercent>
          <xsl:value-of
            select="TaxTotal/TaxSubtotal/TaxCategory/Percent"/>
        </taxPercent>
        <tax>
          <xsl:value-of
            select="TaxTotal/TaxSubtotal/TaxCategory/TaxScheme/Name"/>
        </tax>
        <taxValue>
          <xsl:value-of
            select="TaxTotal/TaxAmount"/>
        </taxValue>
        <xsl:for-each select="WithholdingTaxTotal">
          <withholding>
            <total>
              <xsl:value-of select="TaxAmount"/>
            </total>
            <xsl:for-each select="TaxSubtotal">
              <taxableAmount>
                <xsl:value-of select="TaxableAmount"/>
              </taxableAmount>
              <amount>
                <xsl:value-of select="TaxAmount"/>
              </amount>
              <percent>
                <xsl:value-of select="TaxCategory/Percent"/>
              </percent>
              <taxId>
                <xsl:value-of select="TaxCategory/TaxScheme/ID"/>
              </taxId>
              <taxName>
                <xsl:value-of select="TaxCategory/TaxScheme/Name"/>
              </taxName>
            </xsl:for-each>
          </withholding>
        </xsl:for-each>

        <xsl:for-each select="TaxTotal">
          <taxTotal>
            <total>
              <xsl:value-of select="TaxAmount"/>
            </total>
            <xsl:for-each select="TaxSubtotal">
              <taxableAmount>
                <xsl:value-of select="TaxableAmount"/>
              </taxableAmount>
              <amount>
                <xsl:value-of select="TaxAmount"/>
              </amount>
              <percent>
                <xsl:value-of select="TaxCategory/Percent"/>
              </percent>
              <taxId>
                <xsl:value-of select="TaxCategory/TaxScheme/ID"/>
              </taxId>
              <taxName>
                <xsl:value-of select="TaxCategory/TaxScheme/Name"/>
              </taxName>
            </xsl:for-each>
          </taxTotal>
        </xsl:for-each>
        <additionalInformation>
          <xsl:for-each
            select="UBLExtensions/UBLExtension/ExtensionContent/CustomTagGeneral/Interoperabilidad/Group[@schemeName='Sector Salud']">
            <users>
              <xsl:for-each
                select="Collection[@schemeName='Usuario']/AdditionalInformation[Name='CODIGO_PRESTADOR']">
                <lenderCode>
                  <xsl:value-of select="Value"/>
                </lenderCode>
              </xsl:for-each>
              <xsl:for-each
                select="Collection[@schemeName='Usuario']/AdditionalInformation[Name='TIPO_DOCUMENTO_IDENTIFICACION']">
                <documentTypeUser>
                  <xsl:value-of select="Value"/>
                </documentTypeUser>
              </xsl:for-each>
              <xsl:for-each
                select="Collection[@schemeName='Usuario']/AdditionalInformation[Name='NUMERO_DOCUMENTO_IDENTIFICACION']">
                <documentNumberUser>
                  <xsl:value-of select="Value"/>
                </documentNumberUser>
              </xsl:for-each>
              <xsl:for-each
                select="Collection[@schemeName='Usuario']/AdditionalInformation[Name='PRIMER_APELLIDO']">
                <surname>
                  <xsl:value-of select="Value"/>
                </surname>
              </xsl:for-each>
              <xsl:for-each
                select="Collection[@schemeName='Usuario']/AdditionalInformation[Name='SEGUNDO_APELLIDO']">
                <secondSurname>
                  <xsl:value-of select="Value"/>
                </secondSurname>
              </xsl:for-each>
              <xsl:for-each
                select="Collection[@schemeName='Usuario']/AdditionalInformation[Name='PRIMER_NOMBRE']">
                <firstName>
                  <xsl:value-of select="Value"/>
                </firstName>
              </xsl:for-each>
              <xsl:for-each
                select="Collection[@schemeName='Usuario']/AdditionalInformation[Name='SEGUNDO_NOMBRE']">
                <secondName>
                  <xsl:value-of select="Value"/>
                </secondName>
              </xsl:for-each>
              <xsl:for-each
                select="Collection[@schemeName='Usuario']/AdditionalInformation[Name='TIPO_USUARIO']">
                <userType>
                  <xsl:value-of select="Value"/>
                </userType>
              </xsl:for-each>
              <xsl:for-each
                select="Collection[@schemeName='Usuario']/AdditionalInformation[Name='MODALIDAD_CONTRATACION']">
                <contractingModality>
                  <xsl:value-of select="Value"/>
                </contractingModality>
              </xsl:for-each>
              <xsl:for-each
                select="Collection[@schemeName='Usuario']/AdditionalInformation[Name='COBERTURA_PLAN_BENEFICIOS']">
                <coverage>
                  <xsl:value-of select="Value"/>
                </coverage>
              </xsl:for-each>
              <xsl:for-each
                select="Collection[@schemeName='Usuario']/AdditionalInformation[Name='NUMERO_AUTORIZACION']">
                <authorizationNumber>
                  <xsl:value-of select="Value"/>
                </authorizationNumber>
              </xsl:for-each>
              <xsl:for-each
                select="Collection[@schemeName='Usuario']/AdditionalInformation[Name='NUMERO_MIPRES']">
                <prescriptionNumber>
                  <xsl:value-of select="Value"/>
                </prescriptionNumber>
              </xsl:for-each>
              <xsl:for-each
                select="Collection[@schemeName='Usuario']/AdditionalInformation[Name='NUMERO_ENTREGA_MIPRES']">
                <supplyNumber>
                  <xsl:value-of select="Value"/>
                </supplyNumber>
              </xsl:for-each>
              <xsl:for-each
                select="Collection[@schemeName='Usuario']/AdditionalInformation[Name='NUMERO_CONTRATO']">
                <contractNumber>
                  <xsl:value-of select="Value"/>
                </contractNumber>
              </xsl:for-each>
              <xsl:for-each
                select="Collection[@schemeName='Usuario']/AdditionalInformation[Name='NUMERO_POLIZA']">
                <policyNumber>
                  <xsl:value-of select="Value"/>
                </policyNumber>
              </xsl:for-each>
              <xsl:for-each
                select="Collection[@schemeName='Usuario']/AdditionalInformation[Name='FECHA_INI_FAC']">
                <invoicingStartDate>
                  <xsl:value-of select="Value"/>
                </invoicingStartDate>
              </xsl:for-each>
              <xsl:for-each
                select="Collection[@schemeName='Usuario']/AdditionalInformation[Name='FECHA_FINAL']">
                <invoicingEndDate>
                  <xsl:value-of select="Value"/>
                </invoicingEndDate>
              </xsl:for-each>
              <xsl:for-each
                select="Collection[@schemeName='Usuario']/AdditionalInformation[Name='COPAGO']">
                <coPayment>
                  <xsl:value-of select="Value"/>
                </coPayment>
              </xsl:for-each>
              <xsl:for-each
                      select="Collection[@schemeName='Usuario']/AdditionalInformation[Name='ANTICIPO']">
                <advancePayment>
                  <xsl:value-of select="Value"/>
                </advancePayment>
              </xsl:for-each>
              <xsl:for-each
                select="Collection[@schemeName='Usuario']/AdditionalInformation[Name='CUOTA_MODERADORA']">
                <moderatingFee>
                  <xsl:value-of select="Value"/>
                </moderatingFee>
              </xsl:for-each>
              <xsl:for-each
                select="Collection[@schemeName='Usuario']/AdditionalInformation[Name='CUOTA_RECUPERACION']">
                <recuperationFee>
                  <xsl:value-of select="Value"/>
                </recuperationFee>
              </xsl:for-each>
              <xsl:for-each
                select="Collection[@schemeName='Usuario']/AdditionalInformation[Name='PAGOS_COMPARTIDOS']">
                <sharedPayment>
                  <xsl:value-of select="Value"/>
                </sharedPayment>
              </xsl:for-each>
              <xsl:for-each
                select="Collection[@schemeName='Usuario']/AdditionalInformation[Name='MODALIDAD_PAGO']">
                <paymentModality>
                  <xsl:value-of select="Value"/>
                </paymentModality>
              </xsl:for-each>
            </users>
          </xsl:for-each>
        </additionalInformation>
        <xsl:for-each select="InvoiceLine">
          <aiuElements>
            <amount>
              <xsl:value-of select="LineExtensionAmount"/>
            </amount>
            <description>
              <xsl:value-of select="Item/Description"/>
            </description>
          </aiuElements>
        </xsl:for-each>
      </xsl:for-each>
    </invoice>
  </xsl:template>
</xsl:stylesheet>
