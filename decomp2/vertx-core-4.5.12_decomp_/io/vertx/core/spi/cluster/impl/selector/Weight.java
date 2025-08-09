package io.vertx.core.spi.cluster.impl.selector;

class Weight implements Comparable {
   private int value;

   Weight(int value) {
      this.value = value;
   }

   public int value() {
      return this.value;
   }

   Weight increment() {
      ++this.value;
      return this;
   }

   public int compareTo(Weight other) {
      return Integer.compare(this.value, other.value);
   }
}
