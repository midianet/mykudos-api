package midianet.mykudos.infra.exception;

import org.springframework.lang.NonNull;

/**
 * Classe que respresenta uma Excessão de Negócio
 */
public class BusinessException extends RuntimeException {

    public BusinessException(@NonNull String message) {
        super(message);
    }

}
