package ru.venediktov.testspringproject.metrics;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MetricsTest implements MetricsTestMXBean {

  private final CalendarEventsMetric calendarEventsMetric;

  @Override
  public void incrementCounter(int octoberIncrement, int novemberIncrement) {
    calendarEventsMetric.countEvents(octoberIncrement, novemberIncrement);
  }
}
