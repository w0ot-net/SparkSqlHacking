package org.apache.thrift;

public abstract class Option {
   private static final Option NONE = new None();

   public abstract boolean isDefined();

   public abstract Object get();

   public Object or(Object other) {
      return this.isDefined() ? this.get() : other;
   }

   public static Option fromNullable(Object value) {
      return (Option)(value != null ? some(value) : none());
   }

   public static Some some(Object value) {
      return new Some(value);
   }

   public static None none() {
      return (None)NONE;
   }

   public static class None extends Option {
      public boolean isDefined() {
         return false;
      }

      public Object get() {
         throw new IllegalStateException("Cannot call get() on None");
      }

      public String toString() {
         return "None";
      }
   }

   public static class Some extends Option {
      private final Object value;

      public Some(Object value) {
         this.value = value;
      }

      public boolean isDefined() {
         return true;
      }

      public Object get() {
         return this.value;
      }

      public String toString() {
         return "Some(" + this.value + ")";
      }
   }
}
