package midianet.mykudos.infra.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageHelper {
    private final MessageSource messageSource;

    public String getMessage(@NonNull final String key,
                             @Nullable final Object... params) {
        return messageSource.getMessage(key, params, LocaleContextHolder.getLocale());
    }

}
