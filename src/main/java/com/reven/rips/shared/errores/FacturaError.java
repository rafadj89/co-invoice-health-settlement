// Archivo: FacturaError.java
package com.reven.rips.shared.errores;

/**
 * Enumeración centralizada para los errores de validación de facturas.
 * Cada miembro de la enumeración contiene el código de error y el mensaje
 * asociado.
 */
public enum FacturaError {

    // --- Errores de ZIP y Parseo ---
    
    /** * Error cuando no se encuentra exactamente un archivo ZIP.
     * El mensaje es una plantilla; se debe concatenar el número de archivos encontrados.
     */
    INVALID_ZIP_COUNT("001", "Cantidad de zip de factura inválida. Se esperaba 1 y se encontraron: "),
    
    /** * Error genérico al intentar leer/descomprimir el ZIP.
     * El mensaje es una plantilla; se debe concatenar el mensaje de la excepción.
     */
    ZIP_PROCESSING_ERROR("002-A", "Error al leer el zip de factura: "), // Nota: Código cambiado de "002"
    
    /** * Error cuando el ZIP no contiene el archivo XML esperado.
     */
    XML_NOT_FOUND_IN_ZIP("002-B", "No se encontró un archivo XML dentro del ZIP."), // Nota: Código cambiado de "002"

    // --- Errores de Validación de Negocio ---
    
    /** * Error cuando los NITs del XML no coinciden con el NIT del prestador en sesión.
     */
    NIT_MISMATCH("003", "Factura no pertenece al prestador en sesión"),
    
    /** * Error cuando el XML no contiene el nodo de servicios de salud.
     */
    NO_HEALTH_NODE("004", "Factura no contiene nodo de sector salud"),
    
    /** * Error cuando el nodo de salud no especifica un número de contrato.
     */
    NO_CONTRACT("005", "Factura no contiene un contrato"),

    // --- Errores Inesperados ---
    
    /** * Error genérico para excepciones no controladas.
     * El mensaje es una plantilla; se debe concatenar el mensaje de la excepción.
     */
    UNEXPECTED_ERROR("999", "Error inesperado: ");

    
    // --- Campos y Constructor ---
    
    private final String code;
    private final String message;

    /**
     * Constructor para la enumeración de errores.
     * @param code El código de error (ej. "001", "003").
     * @param message El mensaje de error descriptivo (puede ser una plantilla).
     */
    FacturaError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    // --- Getters ---
    
    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}