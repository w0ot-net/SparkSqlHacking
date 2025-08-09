package io.jsonwebtoken.impl.lang;

public final class ConstantFunction implements Function {
   private final Object value;

   public ConstantFunction(Object value) {
      this.value = value;
   }

   public Object apply(Object t) {
      return this.value;
   }
}
