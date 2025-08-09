package org.apache.commons.lang3.function;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public class Consumers {
   private static final Consumer NOP;

   public static void accept(Consumer consumer, Object object) {
      if (consumer != null) {
         consumer.accept(object);
      }

   }

   public static Consumer nop() {
      return NOP;
   }

   private Consumers() {
   }

   static {
      Function var10000 = Function.identity();
      Objects.requireNonNull(var10000);
      NOP = var10000::apply;
   }
}
