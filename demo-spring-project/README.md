# Мониторинг
Для мониторинга приложения можно использовать Prometheus + Graphana.
Необходимые настройки есть в папке resources/monitoring для их запуска в Docker:
1) выполнить в терминале команду docker compose up
2) проверить http://localhost:9090/targets#pool-test-spring-project что метрики отдаются в Prometheus
3) зайти в графану http://localhost:3000/ и настроить в качестве data source приложение test-spring-project
   (name = test-spring-project, url = http://host.docker.internal:9090),
а после можно использовать дашборд (https://grafana.com/grafana/dashboards/10280)