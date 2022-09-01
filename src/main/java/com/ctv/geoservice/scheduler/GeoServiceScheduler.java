package com.ctv.geoservice.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ctv.geoservice.geolocalize.GeolocalizationService;

@Component
public class GeoServiceScheduler {

  @Autowired
  GeolocalizationService geolocalizationService;

  private static final Logger log = LoggerFactory.getLogger(GeoServiceScheduler.class);

  @Scheduled(cron = "0/10 * 10 * * ?") // 3 am in the morning
  public void refreshGeolocalizationRules() {
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss zzz");
    log.info("Running geolocalize refresh on {}", sdf1.format(new Date()));
    try {
      geolocalizationService.refreshGeolocalizationRules();
//      log.info(geolocalizationService.getGeolocalizationRules().toString());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }
}
