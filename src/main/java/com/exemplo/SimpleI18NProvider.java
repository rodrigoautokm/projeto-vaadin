package com.exemplo;

import com.vaadin.flow.i18n.I18NProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class SimpleI18NProvider implements I18NProvider {

    private static final Logger logger = LoggerFactory.getLogger(SimpleI18NProvider.class);

    @Override
    public List<Locale> getProvidedLocales() {
        return Collections.unmodifiableList(List.of(
            new Locale("pt", "BR"), // Português do Brasil
            new Locale("en", "US")  // Inglês dos EUA
        ));
    }

    @Override
    public String getTranslation(String key, Locale locale, Object... params) {
        Locale effectiveLocale = getProvidedLocales().contains(locale) ? locale : new Locale("en", "US");
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("messages", effectiveLocale);
            String translation = bundle.getString(key);
            return params.length > 0 ? String.format(translation, params) : translation;
        } catch (Exception e) {
            logger.warn("Translation not found for key '{}' in locale '{}'", key, effectiveLocale);
            return key;
        }
    }
}