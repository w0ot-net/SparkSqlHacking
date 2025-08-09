package io.vertx.core.shareddata;

public interface Shareable {
   default Shareable copy() {
      return this;
   }
}
