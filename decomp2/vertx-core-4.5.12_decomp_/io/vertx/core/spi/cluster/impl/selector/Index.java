package io.vertx.core.spi.cluster.impl.selector;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

class Index implements IntUnaryOperator {
   private final int max;
   private final AtomicInteger idx = new AtomicInteger(0);

   Index(int max) {
      this.max = max;
   }

   int nextVal() {
      return this.idx.getAndUpdate(this);
   }

   public int applyAsInt(int i) {
      return i == this.max - 1 ? 0 : i + 1;
   }
}
