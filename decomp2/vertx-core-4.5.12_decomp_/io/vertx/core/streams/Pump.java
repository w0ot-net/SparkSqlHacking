package io.vertx.core.streams;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.streams.impl.PumpImpl;

/** @deprecated */
@Deprecated
@VertxGen
public interface Pump {
   static Pump pump(ReadStream rs, WriteStream ws) {
      return new PumpImpl(rs, ws);
   }

   static Pump pump(ReadStream rs, WriteStream ws, int writeQueueMaxSize) {
      return new PumpImpl(rs, ws, writeQueueMaxSize);
   }

   @Fluent
   Pump setWriteQueueMaxSize(int var1);

   @Fluent
   Pump start();

   @Fluent
   Pump stop();

   int numberPumped();
}
