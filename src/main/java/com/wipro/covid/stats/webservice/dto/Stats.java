package com.wipro.covid.stats.webservice.dto;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class Stats {
  private ZonedDateTime lastChecked;
  private Integer index;
  private Integer count;
  private Integer total;
  private List<Covid19Stat> covid19Stats;
}
