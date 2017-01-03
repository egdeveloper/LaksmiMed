package org.egdeveloper.config;

import org.egdeveloper.generators.impl.*;
import org.egdeveloper.service.generators.ReportGeneratorService;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by Roman Baygildin (rvyarnykh@edu.hse.ru) on 30.12.16.
 */
@Configuration
public class AppConfig {

    @Bean
    public PropertyPlaceholderConfigurer getPropertyPlaceholderConfigurer()
    {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setLocation(new ClassPathResource("application.properties"));
        ppc.setIgnoreUnresolvablePlaceholders(true);
        return ppc;
    }

    @Bean
    public ReportGeneratorService reportGeneratorService(){
        ApplicationContext context = AppContextProvider.getApplicationContext();
        ReportGeneratorService reportGeneratorService = new ReportGeneratorService();
        reportGeneratorService.registerGenerator("application/pdf", context.getBean(PDFReportGenerator.class));
        reportGeneratorService.registerGenerator("text/xml", context.getBean(XMLReportGenerator.class));
        reportGeneratorService.registerGenerator("application/json", context.getBean(JSONReportGenerator.class));
        reportGeneratorService.registerGenerator("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", context.getBean(ExcelReportGenerator.class));
        reportGeneratorService.registerGenerator("application/vnd.openxmlformats-officedocument.wordprocessingml.document", context.getBean(WordReportGenerator.class));
        return reportGeneratorService;
    }
}
