package io.vertx.core.metrics.impl;

import io.vertx.core.spi.metrics.ClientMetrics;
import io.vertx.core.spi.metrics.DatagramSocketMetrics;
import io.vertx.core.spi.metrics.EventBusMetrics;
import io.vertx.core.spi.metrics.HttpClientMetrics;
import io.vertx.core.spi.metrics.HttpServerMetrics;
import io.vertx.core.spi.metrics.PoolMetrics;
import io.vertx.core.spi.metrics.TCPMetrics;
import io.vertx.core.spi.metrics.VertxMetrics;

public class DummyVertxMetrics implements VertxMetrics {
   public static final DummyVertxMetrics INSTANCE = new DummyVertxMetrics();

   public static class DummyEventBusMetrics implements EventBusMetrics {
      public static final DummyEventBusMetrics INSTANCE = new DummyEventBusMetrics();
   }

   public static class DummyHttpServerMetrics implements HttpServerMetrics {
      public static final DummyHttpServerMetrics INSTANCE = new DummyHttpServerMetrics();
   }

   public static class DummyHttpClientMetrics implements HttpClientMetrics {
      public static final DummyHttpClientMetrics INSTANCE = new DummyHttpClientMetrics();
   }

   public static class DummyClientMetrics implements ClientMetrics {
      public static final DummyClientMetrics INSTANCE = new DummyClientMetrics();
   }

   public static class DummyTCPMetrics implements TCPMetrics {
      public static final DummyTCPMetrics INSTANCE = new DummyTCPMetrics();
   }

   public static class DummyDatagramMetrics implements DatagramSocketMetrics {
      public static final DummyDatagramMetrics INSTANCE = new DummyDatagramMetrics();
   }

   public static class DummyWorkerPoolMetrics implements PoolMetrics {
      public static final DummyWorkerPoolMetrics INSTANCE = new DummyWorkerPoolMetrics();
   }
}
