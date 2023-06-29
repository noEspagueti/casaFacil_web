/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.casafacil.project.services;

import org.springframework.http.*;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Miguel
 */
@Service
public class webServiceImpl<T> implements webService<T> {

    @Override
    public ResponseEntity consumirApi(String url, T object) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<T> entidad = new HttpEntity(object, header);
        ResponseEntity<String> response = restTemplate.exchange(url.trim(), HttpMethod.POST, entidad, String.class);
        return response;
    }

    @Override
    public T methoGet(String url, T entidad) {
        RestTemplate restTemplate = new RestTemplate();
        Class<T> objectClass = (Class<T>) entidad.getClass();
        ResponseEntity<T> entity = restTemplate.getForEntity(url.trim(), objectClass);
        return entity.getBody();
    }

}
