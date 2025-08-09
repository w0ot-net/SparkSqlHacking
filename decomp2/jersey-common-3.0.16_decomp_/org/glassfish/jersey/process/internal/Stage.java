package org.glassfish.jersey.process.internal;

import java.util.function.Function;

public interface Stage {
   Continuation apply(Object var1);

   public static final class Continuation {
      private final Object result;
      private final Stage next;

      Continuation(Object result, Stage next) {
         this.result = result;
         this.next = next;
      }

      public static Continuation of(Object result, Stage next) {
         return new Continuation(result, next);
      }

      public static Continuation of(Object result) {
         return new Continuation(result, (Stage)null);
      }

      public Object result() {
         return this.result;
      }

      public Stage next() {
         return this.next;
      }

      public boolean hasNext() {
         return this.next != null;
      }
   }

   public interface Builder {
      Builder to(Function var1);

      Builder to(ChainableStage var1);

      Stage build();

      Stage build(Stage var1);
   }
}
