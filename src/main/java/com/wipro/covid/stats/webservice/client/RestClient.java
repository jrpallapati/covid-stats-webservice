package com.wipro.covid.stats.webservice.client;

import com.wipro.covid.stats.webservice.dto.Covid19Stat;
import com.wipro.covid.stats.webservice.dto.Response;
import com.wipro.covid.stats.webservice.dto.Stats;
import com.wipro.covid.stats.webservice.dto.Total;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Component
public class RestClient {
  public static final String X_RAPIDAPI_HOST = "x-rapidapi-host";
  public static final String X_RAPIDAPI_KEY = "x-rapidapi-key";
  private final RestTemplate restTemplate;

  @Value("${covid_stats_host_base_url}")
  private String baseUrl;

  @Value(("${covid_stats_application_key}"))
  private String applicationKey;

  @Value(("${covid_stats_application_host}"))
  private String applicationHost;

  public Response<Stats> getStats(String location, Integer index, Integer count) {
    log.info("Getting stats for location: " + location);
    HttpHeaders headers = getHttpHeaders();

    HttpEntity request = new HttpEntity(headers);
    ResponseEntity<? extends Response> response =
        restTemplate.exchange(
            baseUrl + "/v1/stats?country={country}",
            HttpMethod.GET,
            request,
            new ParameterizedTypeReference<Response<Stats>>() {},
            location);
    Response<Stats> responseBody = response.getBody();

    // send back only the subset of data requested
    Stats data = responseBody.getData();
    List<Covid19Stat> covid19Stats = data.getCovid19Stats();
    List<Covid19Stat> covid19StatsSublist = covid19Stats.subList(index - 1, index + count - 1);
    data.setCovid19Stats(covid19StatsSublist);
    data.setIndex(index);
    data.setCount(count);
    data.setTotal(covid19Stats.size());

    return responseBody;
  }

  public Response<Total> getTotal(String location) {
    log.info("Getting total for location: " + location);
    log.info("Base URL: " + baseUrl);
    HttpHeaders headers = getHttpHeaders();

    HttpEntity request = new HttpEntity(headers);
    ResponseEntity<? extends Response> response =
        restTemplate.exchange(
            baseUrl + "/v1/total?country={country}",
            HttpMethod.GET,
            request,
            new ParameterizedTypeReference<Response<Total>>() {},
            location);
    return response.getBody();
  }

  private HttpHeaders getHttpHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.add(X_RAPIDAPI_HOST, applicationHost);
    headers.add(X_RAPIDAPI_KEY, applicationKey);
    return headers;
  }
}
