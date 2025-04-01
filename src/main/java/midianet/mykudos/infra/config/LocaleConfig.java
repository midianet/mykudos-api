package midianet.mykudos.infra.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Locale;

@Configuration
public class LocaleConfig {

    @Bean
    public AcceptHeaderLocaleResolver localeResolver() {
        final var defaultLocale = new Locale("pt", "BR");
        final var resolver = new AcceptHeaderLocaleResolver();
        resolver.setSupportedLocales(Arrays.asList(defaultLocale, Locale.US));
        resolver.setDefaultLocale(defaultLocale);
        return resolver;
    }

    @Bean
    public MessageSource messageSource() {
        final var messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        messageSource.setBasenames("classpath:ValidationMessages");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setCacheSeconds(5);
        return messageSource;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        final var lci = new LocaleChangeInterceptor();
        lci.setParamName("locale");
        return lci;
    }

}
