package org.sparkproject.jetty.util;

public class ConstantThrowable extends Throwable {
   public ConstantThrowable() {
      this((String)null);
   }

   public ConstantThrowable(String name) {
      super(name, (Throwable)null, false, false);
   }

   public String toString() {
      return String.valueOf(this.getMessage());
   }
}
