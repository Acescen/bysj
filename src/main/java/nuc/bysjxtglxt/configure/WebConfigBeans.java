package nuc.bysjxtglxt.configure;

/**
 * @Author: jurui
 * @Email: acescen@foxmail.com
 * @ProjectName: bysjxtglxt
 * @Package: nuc.bysjxtglxt.configure
 * @Description:
 * @Date: Created in 9:53 2019-12-22
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;

@Configuration
public class WebConfigBeans {
    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;


    @PostConstruct
    public void initEditableValidation() {
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) handlerAdapter.getWebBindingInitializer();

        if (initializer.getConversionService() != null) {

            GenericConversionService genericConversionService = (GenericConversionService) initializer.getConversionService();

            genericConversionService.addConverter(new StringToDateConverter());
        }


    }

}
