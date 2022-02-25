package com.wipro.covid.stats.webservice.controller;

import com.wipro.covid.stats.webservice.client.RestClient;
import com.wipro.covid.stats.webservice.dto.Response;
import com.wipro.covid.stats.webservice.dto.Stats;
import com.wipro.covid.stats.webservice.dto.Total;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Log4j2
public class CovidStatsController {
  private final RestClient restClient;

  @GetMapping("/v1/total")
  public Response<Total> getTotal(
      @RequestParam(value = "location", defaultValue = "Global") String location) {
    Response<Total> response = restClient.getTotal(location);
    return response;
  }

  @GetMapping("/v1/stats")
  public ResponseEntity<Response<Stats>> getStats(
      @RequestParam(value = "location", defaultValue = "Global") String location,
      @RequestParam(value = "index", defaultValue = "1") Integer index,
      @RequestParam(value = "count", defaultValue = "10") Integer count) {
    if (index < 1 || count < 1) {
      Response<Stats> statsResponseBadRequest =
          new Response<>(-1, "Error", "Index and count should be >=1", null);
      return new ResponseEntity<Response<Stats>>(statsResponseBadRequest, HttpStatus.BAD_REQUEST);
    }
    Response<Stats> response = restClient.getStats(location, index, count);

    return new ResponseEntity<Response<Stats>>(response, HttpStatus.OK);
  }
}
