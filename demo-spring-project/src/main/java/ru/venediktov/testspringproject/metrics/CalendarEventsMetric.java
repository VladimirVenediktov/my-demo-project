package ru.venediktov.testspringproject.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class CalendarEventsMetric {

  private Counter octoberCounter;
  private Counter novemberCounter;
  private final MeterRegistry registry;

  @PostConstruct
  public void init() {
    octoberCounter = Counter.builder("monthly_stats")
            .tag("month", "october")
            .register(registry);
    novemberCounter = Counter.builder("monthly_stats")
            .tag("month", "november")
            .register(registry);
    countEvents(1,1);

    // можно получить существующий counter (либо создастся новый)
    Meter.Id id = octoberCounter.getId();
    Counter counter = registry.counter("monthly_stats", "month", "october");
    Meter.Id foundId = counter.getId();
    log.info("Счетчик найден верно? - {}", foundId.equals(id));
  }

  public void countEvents(int octoberIncrement, int novemberIncrement) {
    IntStream.of(0, octoberIncrement)
        .forEach(octoberCounter::increment);
    IntStream.of(0, novemberIncrement)
        .forEach(novemberCounter::increment);
  }

  public void count(String operationType, String office) {
    Tag operationTypeTag = Tag.of("operation_type", operationType);
    Tag officeUuidTag = Tag.of("office", office);
    Counter simple_doc_usage = registry.counter("simple_doc_usage", Arrays.asList(operationTypeTag, officeUuidTag));
    simple_doc_usage.increment();
  }

  @Scheduled(fixedRate = 10000)
  public void testCount() {
    count("create", "Москва");
    count("delete", "Краснодар");
    count("update", "Новосибирск");
  }

  public void putInHistogram(int a) {
    DistributionSummary distributionSummary = DistributionSummary.builder("summary")
            .publishPercentiles(0.5, 0.8)
            .baseUnit("int")
            .register(registry);
    distributionSummary.record(a);
  }

}