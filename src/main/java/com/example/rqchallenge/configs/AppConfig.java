package com.example.rqchallenge.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class AppConfig {

   private final String restApiExampleUrl;

   public AppConfig(@Value("restapiexample.base.url") String restApiExampleUrl) {
      this.restApiExampleUrl = restApiExampleUrl;
   }

   @Bean
   public RestTemplate restTemplate() {
      var restTemplate = new RestTemplate();
      restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(restApiExampleUrl));

      List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
      var converter = new MappingJackson2HttpMessageConverter();
      converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
      messageConverters.add(converter);
      restTemplate.setMessageConverters(messageConverters);

      return restTemplate;
   }
}
