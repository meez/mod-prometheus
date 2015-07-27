package com.meez.vertx.prometheus;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Handler;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.HttpServerResponse;

/** HttpServerRequets Handler for Prometheus metrics requests */
public class MetricsHandler implements Handler<HttpServerRequest> {

  // Instance variables

  /** Logging */
  protected final static Logger log= LoggerFactory.getLogger(MetricsHandler.class);

  /** Registry */
  protected final CollectorRegistry registry;

  // Public methods

  /** Create new MetricsHandler */
  public MetricsHandler() {
    this(CollectorRegistry.defaultRegistry);
  }

  /** Create new MetricsHandler for specified registry */
  public MetricsHandler(CollectorRegistry registry) {
    this.registry=registry;
  }

  // Handler implementation

  /** Handler request */
  public void handle(HttpServerRequest req) {

    log.debug("Fetching metrics (path={})",req.path());

    HttpServerResponse resp=req.response();

    ByteArrayOutputStream raw=new ByteArrayOutputStream();
    try
    {
      Writer out=new OutputStreamWriter(raw);
      TextFormat.write004(out, this.registry.metricFamilySamples());
      out.flush();

      byte[] rawResp=raw.toByteArray();

      resp.putHeader("Content-Type", TextFormat.CONTENT_TYPE_004);
      resp.putHeader("Content-Length", Integer.toString(rawResp.length));

      resp.end(new Buffer(rawResp));
    }
    catch (IOException e)
    {
      log.error("Unable to generate statistics", e);

      req.response().setStatusCode(500).setStatusMessage("Unable to render statistics");
    }
  }
}

