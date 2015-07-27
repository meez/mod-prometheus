mod-prometheus
==============

Vert.x 2 module that wraps the [Prometheus](http://prometheus.io) Simple Client library so it can be shared across all Verticles.

## Usage

Due to the class-loader isolation design in Vert.x 2 the default `CollectorRegistry` in `simpleclient` is not shared across Verticles / Modules. By packaging the library as a non-runnable module you can `include` this in your app module(s) and every Verticle can share the same default `CollectorRegistry`. 

## MetricsHandler

The module also inclueds a basic Metrics Handler that can be used to scape a Vert.x instance from Prometheus. The class `com.meez.vertx.prometheus.MetricsHandler` implements `Handler<HttpServerRequest>` so it can be run as a standalone `HttpServer` or server as part of an existing `RouteMatcher`.

## License

Copyright 2015 Donnerwood Media Inc

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
