package com.example.rqchallenge.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class AppConfig {

   @Bean
   public RestTemplate restTemplate() {
      var restTemplate = new RestTemplate();

      List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
      var converter = new MappingJackson2HttpMessageConverter();
      converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
      messageConverters.add(converter);
      restTemplate.setMessageConverters(messageConverters);

      return restTemplate;
   }
}