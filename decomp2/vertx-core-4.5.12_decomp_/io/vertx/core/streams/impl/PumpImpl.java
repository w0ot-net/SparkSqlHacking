package io.vertx.core.streams.impl;

import io.vertx.core.Handler;
import io.vertx.core.streams.Pump;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.WriteStream;
import java.util.Objects;

public class PumpImpl implements Pump {
   private final ReadStream readStream;
   private final WriteStream writeStream;
   private final Handler dataHandler;
   private final Handler drainHandler;
   private int pumped;

   public PumpImpl(ReadStream rs, WriteStream ws, int maxWriteQueueSize) {
      this(rs, ws);
      this.writeStream.setWriteQueueMaxSize(maxWriteQueueSize);
   }

   public PumpImpl(ReadStream rs, WriteStream ws) {
      Objects.requireNonNull(rs);
      Objects.requireNonNull(ws);
      this.readStream = rs;
      this.writeStream = ws;
      this.drainHandler = (v) -> this.readStream.resume();
      this.dataHandler = (data) -> {
         this.writeStream.write(data);
         this.incPumped();
         if (this.writeStream.writeQueueFull()) {
            this.readStream.pause();
            this.writeStream.drainHandler(this.drainHandler);
         }

      };
   }

   public PumpImpl setWriteQueueMaxSize(int maxSize) {
      this.writeStream.setWriteQueueMaxSize(maxSize);
      return this;
   }

   public PumpImpl start() {
      this.readStream.handler(this.dataHandler);
      return this;
   }

   public PumpImpl stop() {
      this.writeStream.drainHandler((Handler)null);
      this.readStream.handler((Handler)null);
      return this;
   }

   public synchronized int numberPumped() {
      return this.pumped;
   }

   private synchronized void incPumped() {
      ++this.pumped;
   }
}
