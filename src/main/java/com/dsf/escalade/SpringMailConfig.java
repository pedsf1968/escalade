package com.dsf.escalade;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import java.io.IOException;
import java.util.Collections;


public class SpringMailConfig {

   public static final String EMAIL_TEMPLATE_ENCODING = "UTF-8";
   private static final String JAVA_MAIL_FILE = "classpath:mail/javamail.properties";

   private static final String HOST = "spring.mail.host";
   private static final String PORT = "spring.mail.port";
   private static final String PROTOCOL = "spring.mail.protocol";
   private static final String USERNAME = "spring.mail.username";
   private static final String PASSWORD = "spring.mail.password";


   @Bean
   public JavaMailSender mailSender() throws IOException {

      final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

      // Basic mail sender configuration, based on emailconfig.properties
      mailSender.setHost(HOST);
      mailSender.setPort(Integer.parseInt(PORT));
      mailSender.setProtocol(PROTOCOL);
      mailSender.setUsername(USERNAME);
      mailSender.setPassword(PASSWORD);

      return mailSender;
   }

   @Bean
   public ResourceBundleMessageSource emailMessageSource() {
      final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
      messageSource.setBasename("mail/MailMessages");
      return messageSource;
   }



   @Bean
   public TemplateEngine emailTemplateEngine() {
      final SpringTemplateEngine templateEngine = new SpringTemplateEngine();

      // Resolver for TEXT emails
      templateEngine.addTemplateResolver(textTemplateResolver());
      // Resolver for HTML emails (except the editable one)
      templateEngine.addTemplateResolver(htmlTemplateResolver());
      // Resolver for HTML editable emails (which will be treated as a String)
      templateEngine.addTemplateResolver(stringTemplateResolver());
      // Message source, internationalization specific to emails
      templateEngine.setTemplateEngineMessageSource(emailMessageSource());

      return templateEngine;
   }

   private ITemplateResolver textTemplateResolver() {
      final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
      templateResolver.setOrder(Integer.valueOf(1));
      templateResolver.setResolvablePatterns(Collections.singleton("text/*"));
      templateResolver.setPrefix("/mail/");
      templateResolver.setSuffix(".txt");
      templateResolver.setTemplateMode(TemplateMode.TEXT);
      templateResolver.setCharacterEncoding(EMAIL_TEMPLATE_ENCODING);
      templateResolver.setCacheable(false);
      return templateResolver;
   }

   private ITemplateResolver htmlTemplateResolver() {
      final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
      templateResolver.setOrder(Integer.valueOf(2));
      templateResolver.setResolvablePatterns(Collections.singleton("html/*"));
      templateResolver.setPrefix("/mail/");
      templateResolver.setSuffix(".html");
      templateResolver.setTemplateMode(TemplateMode.HTML);
      templateResolver.setCharacterEncoding(EMAIL_TEMPLATE_ENCODING);
      templateResolver.setCacheable(false);
      return templateResolver;
   }

   private ITemplateResolver stringTemplateResolver() {
      final StringTemplateResolver templateResolver = new StringTemplateResolver();
      templateResolver.setOrder(Integer.valueOf(3));
      // No resolvable pattern, will simply process as a String template everything not previously matched
      templateResolver.setTemplateMode("HTML5");
      templateResolver.setCacheable(false);
      return templateResolver;
   }


}
