mod-prometheus
==============

Vert.x 2 module that wraps the [Prometheus](http://prometheus.io) Simple Client library so it can be shared across all Verticles.

## Usage

Due to the class-loader isolation design in Vert.x 2 the default `CollectorRegistry` in `simpleclient` is not shared across Verticles / Modules. By packaging the library as a non-runnable module you can `include` this in your app module(s) and every Verticle can share the same default `CollectorRegistry`. 

## MetricsHandler

The module also inclueds a basic Metrics Handler that can be used to scape a Vert.x instance from Prometheus. The class `com.meez.vertx.prometheus.MetricsHandler` implements `Handler<HttpServerRequest>` so it can be run as a standalone `HttpServer` or server as part of an existing `RouteMatcher`.

## License

This module is licensed under the 'Apache License 2.0' - see the [LICENSE](LICENSE) for more details
