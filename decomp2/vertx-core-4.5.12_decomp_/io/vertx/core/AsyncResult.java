package io.vertx.core;

import java.util.function.Function;

public interface AsyncResult {
   Object result();

   Throwable cause();

   boolean succeeded();

   boolean failed();

   default AsyncResult map(final Function mapper) {
      if (mapper == null) {
         throw new NullPointerException();
      } else {
         return new AsyncResult() {
            public Object result() {
               return this.succeeded() ? mapper.apply(AsyncResult.this.result()) : null;
            }

            public Throwable cause() {
               return AsyncResult.this.cause();
            }

            public boolean succeeded() {
               return AsyncResult.this.succeeded();
            }

            public boolean failed() {
               return AsyncResult.this.failed();
            }
         };
      }
   }

   default AsyncResult map(Object value) {
      return this.map((Function)((t) -> value));
   }

   default AsyncResult mapEmpty() {
      return this.map((Object)null);
   }

   default AsyncResult otherwise(final Function mapper) {
      if (mapper == null) {
         throw new NullPointerException();
      } else {
         return new AsyncResult() {
            public Object result() {
               if (AsyncResult.this.succeeded()) {
                  return AsyncResult.this.result();
               } else {
                  return AsyncResult.this.failed() ? mapper.apply(AsyncResult.this.cause()) : null;
               }
            }

            public Throwable cause() {
               return null;
            }

            public boolean succeeded() {
               return AsyncResult.this.succeeded() || AsyncResult.this.failed();
            }

            public boolean failed() {
               return false;
            }
         };
      }
   }

   default AsyncResult otherwise(Object value) {
      return this.otherwise((Function)((err) -> value));
   }

   default AsyncResult otherwiseEmpty() {
      return this.otherwise((Function)((err) -> null));
   }
}
