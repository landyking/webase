package app.util.mvc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Component
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    private static final Log logger = LogFactory.getLog(CustomBeanFactoryPostProcessor.class);
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] names = beanFactory.getBeanNamesForType(RequestMappingHandlerMapping.class);
        //System.out.println(Arrays.toString(names));
        if (names.length > 1) {
            throw new NoUniqueBeanDefinitionException(RequestMappingHandlerMapping.class, names);
        }else if (names.length < 1) {
            throw new NoSuchBeanDefinitionException(RequestMappingHandlerMapping.class);
        } else {
            /*RootBeanDefinition beanDefinition = (RootBeanDefinition)beanFactory.getBeanDefinition(names[0]);
            beanDefinition.setBeanClass(CustomRequestMappingHandlerMapping.class);*/
            BeanDefinition definition = beanFactory.getBeanDefinition(names[0]);
            definition.setBeanClassName(CustomRequestMappingHandlerMapping.class.getName());
            logger.info("Replace [RequestMappingHandlerMapping] with [CustomRequestMappingHandlerMapping]");
        }
    }
}