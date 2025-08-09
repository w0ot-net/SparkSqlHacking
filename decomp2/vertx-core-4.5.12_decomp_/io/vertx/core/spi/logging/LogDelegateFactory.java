package io.vertx.core.spi.logging;

public interface LogDelegateFactory {
   boolean isAvailable();

   LogDelegate createDelegate(String var1);
}
