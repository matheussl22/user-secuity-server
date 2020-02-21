package br.com.pessoa.config.support;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class ApplicationContextProvider implements ApplicationContextAware {

    private static ApplicationContext context;

    public static ApplicationContext getContext() {
        if (Objects.nonNull(ApplicationContextProvider.context)) {
            return ApplicationContextProvider.context;
        } else {
            throw new IllegalStateException("Contexto da aplicação ainda não disponível!");
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        if (Objects.nonNull(ApplicationContextProvider.context)) {
            log.warn("Contexto da aplicação já inicializado");
        }
        ApplicationContextProvider.context = applicationContext;
    }

    public static <T> T getBean(final Class<T> beanClass){
        return ApplicationContextProvider.getContext().getBean(beanClass);
    }

    public static <T> T getBean(final String beanName){
        return (T) ApplicationContextProvider.getContext().getBean(beanName);
    }

    public static <T> T getBean(final Class<T> beanClass, final Object args){
        return ApplicationContextProvider.getContext().getBean(beanClass, args);
    }

    public static String getConfigurationProperty(final String key) {
        return ApplicationContextProvider.getContext().getEnvironment().getProperty(key);
    }
}