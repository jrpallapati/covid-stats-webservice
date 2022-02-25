package com.wipro.covid.stats.webservice.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class Covid19Stat {
  private String keyId;
  private String city;
  private String province;
  private String country;
  private Integer confirmed;
  private Integer deaths;
  private Integer recovered;
  private ZonedDateTime lastUpdate;
}
