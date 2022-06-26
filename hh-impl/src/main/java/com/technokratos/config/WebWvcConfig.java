package com.technokratos.config;

import com.technokratos.mappers.custom_converters.impl.ResumeSortConverter;
import com.technokratos.mappers.custom_converters.impl.VacancySortConverter;
import net.kaczmarzyk.spring.data.jpa.web.SpecificationArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.List;
import java.util.Properties;

@EnableWebMvc
@Configuration
@ComponentScan({"com.technokratos", "org.springdoc"})
@PropertySource("classpath:application.properties")
public class WebWvcConfig implements WebMvcConfigurer {

    @Autowired
    private Environment environment;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter());
    }

    @Bean
    public ViewResolver viewResolver() {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setPrefix("");
        viewResolver.setSuffix(".ftlh");
        viewResolver.setContentType("text/html;charset=UTF-8");
        return viewResolver;
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        return configurer;
    }

    @Bean
    public freemarker.template.Configuration freeMarkerConfig(){
        freemarker.template.Configuration cfg = freeMarkerConfigurer().getConfiguration();
        cfg.setClassForTemplateLoading(this.getClass(), "/templates/");
        return cfg;
    }

    @Bean
    public JavaMailSender mailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(environment.getProperty("email.host"));
        mailSender.setPort(Integer.parseInt(environment.getProperty("email.port")));
        mailSender.setUsername(environment.getProperty("email.username"));
        mailSender.setPassword(environment.getProperty("email.password"));
        mailSender.setDefaultEncoding(environment.getProperty("email.default_encoding"));
        mailSender.getJavaMailProperties().put("mail.smtp.starttls.enable", environment.getProperty("email.starttls"));
        return mailSender;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new SpecificationArgumentResolver());
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new VacancySortConverter());
        registry.addConverter(new ResumeSortConverter());
    }
}
