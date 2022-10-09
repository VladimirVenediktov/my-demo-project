package ru.venediktov.testspringproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/logs")
@Slf4j
public class LoggerRestController {

  @GetMapping
  public void getLogs() {
    log.error("ERROR LOG");
    log.warn("WARN LOG");
    log.info("INFO LOG");
    log.debug("DEBUG log");
    log.trace("TRACE log");
  }

}
