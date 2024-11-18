package com.academia.equipamentos.exception;

/**
 * Exceção personalizada para recursos não encontrados.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Construtor para ResourceNotFoundException.
     *
     * @param message A mensagem de erro a ser exibida.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Construtor para ResourceNotFoundException com causa.
     *
     * @param message A mensagem de erro a ser exibida.
     * @param cause   A causa raiz da exceção.
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
