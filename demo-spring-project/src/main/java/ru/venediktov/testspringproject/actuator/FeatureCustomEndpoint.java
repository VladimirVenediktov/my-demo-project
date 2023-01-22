package ru.venediktov.testspringproject.actuator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Компонент для создания и настройки собственного endpoint'а для актуатора.
 * Идея: как инструмент получения инфы, вкл/выкл фичей в приложении (фича-тогглы).
 * GET /features - список всех фичей
 * GET /features/1 - получение информации о фиче по ее id
 * POST /features/1/switch-off - изменить статус фичи на false
 * POST /features/1/switch-off - изменить статус фичи на true
 * DELETE /features/1 - удалить инфу о фиче
 */
@Component
@RestControllerEndpoint(id = "features")
public class FeatureCustomEndpoint {

    private Map<Integer, Feature> features = new HashMap<>();

    @PostConstruct
    public void init() {
        features.put(1, new Feature(1, "Фича 1", true));
        features.put(2, new Feature(2, "Фича 2", false));
    }

    @GetMapping()
    public Map<Integer, Feature> features() {
        return features;
    }

    @GetMapping("/{id}")
    public Feature getFeature(@PathVariable int id) {
        return features.get(id);
    }

    @PostMapping("/{id}/switch-off")
    public void switchOffFeature(@PathVariable int id) {
        Optional.ofNullable(features.get(id))
                .ifPresent(feature -> feature.setEnabled(false));
    }

    @PostMapping("/{id}/switch-on")
    public void switchOnFeature(@PathVariable int id) {
        Optional.ofNullable(features.get(id))
                .ifPresent(feature -> feature.setEnabled(true));
    }

    @DeleteMapping("/{id}")
    public void deleteFeature(@PathVariable int id) {
        features.remove(id);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Feature {

        private int id;
        private String description;
        private Boolean enabled;
    }

}
