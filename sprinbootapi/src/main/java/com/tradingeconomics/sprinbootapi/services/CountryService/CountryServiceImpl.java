package com.tradingeconomics.sprinbootapi.services.CountryService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradingeconomics.sprinbootapi.models.countries.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService{

    public CountryServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    RestTemplate restTemplate;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    String url ="https://api.tradingeconomics.com/country?c=fcd064d5d09047b:jnf0d1gr3121vpb";


    String response;

    Country[] countriesResponse;





    @Override
    public List<Country> handleRequest() {

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>> Now in handleRequest Method in CountryServiceImpl class");

        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> Now in handleRequest Method in CountryServiceImpl class");

        try {

            response = restTemplate.getForObject(url, String.class);

            countriesResponse = new ObjectMapper().readValue(response, Country[].class);

        } catch (JsonProcessingException e) {

            logger.info("Error fetching Countries "+e.getMessage());

            throw new RuntimeException(e);

        }


        return Arrays.asList(countriesResponse);
    }
}
