package org.rocksdb;

public abstract class MutableOptionValue {
   abstract double asDouble() throws NumberFormatException;

   abstract long asLong() throws NumberFormatException;

   abstract int asInt() throws NumberFormatException;

   abstract boolean asBoolean() throws IllegalStateException;

   abstract int[] asIntArray() throws IllegalStateException;

   abstract String asString();

   abstract Object asObject();

   static MutableOptionValue fromString(String var0) {
      return new MutableOptionStringValue(var0);
   }

   static MutableOptionValue fromDouble(double var0) {
      return new MutableOptionDoubleValue(var0);
   }

   static MutableOptionValue fromLong(long var0) {
      return new MutableOptionLongValue(var0);
   }

   static MutableOptionValue fromInt(int var0) {
      return new MutableOptionIntValue(var0);
   }

   static MutableOptionValue fromBoolean(boolean var0) {
      return new MutableOptionBooleanValue(var0);
   }

   static MutableOptionValue fromIntArray(int[] var0) {
      return new MutableOptionIntArrayValue(var0);
   }

   static MutableOptionValue fromEnum(Enum var0) {
      return new MutableOptionEnumValue(var0);
   }

   private abstract static class MutableOptionValueObject extends MutableOptionValue {
      protected final Object value;

      protected MutableOptionValueObject(Object var1) {
         this.value = var1;
      }

      Object asObject() {
         return this.value;
      }
   }

   static class MutableOptionStringValue extends MutableOptionValueObject {
      MutableOptionStringValue(String var1) {
         super(var1);
      }

      double asDouble() throws NumberFormatException {
         return Double.parseDouble((String)this.value);
      }

      long asLong() throws NumberFormatException {
         return Long.parseLong((String)this.value);
      }

      int asInt() throws NumberFormatException {
         return Integer.parseInt((String)this.value);
      }

      boolean asBoolean() throws IllegalStateException {
         return Boolean.parseBoolean((String)this.value);
      }

      int[] asIntArray() throws IllegalStateException {
         throw new IllegalStateException("String is not applicable as int[]");
      }

      String asString() {
         return (String)this.value;
      }
   }

   static class MutableOptionDoubleValue extends MutableOptionValue {
      private final double value;

      MutableOptionDoubleValue(double var1) {
         this.value = var1;
      }

      double asDouble() {
         return this.value;
      }

      long asLong() throws NumberFormatException {
         return Double.valueOf(this.value).longValue();
      }

      int asInt() throws NumberFormatException {
         if (!(this.value > (double)Integer.MAX_VALUE) && !(this.value < (double)Integer.MIN_VALUE)) {
            return Double.valueOf(this.value).intValue();
         } else {
            throw new NumberFormatException("double value lies outside the bounds of int");
         }
      }

      boolean asBoolean() throws IllegalStateException {
         throw new IllegalStateException("double is not applicable as boolean");
      }

      int[] asIntArray() throws IllegalStateException {
         if (!(this.value > (double)Integer.MAX_VALUE) && !(this.value < (double)Integer.MIN_VALUE)) {
            return new int[]{Double.valueOf(this.value).intValue()};
         } else {
            throw new NumberFormatException("double value lies outside the bounds of int");
         }
      }

      String asString() {
         return String.valueOf(this.value);
      }

      Double asObject() {
         return this.value;
      }
   }

   static class MutableOptionLongValue extends MutableOptionValue {
      private final long value;

      MutableOptionLongValue(long var1) {
         this.value = var1;
      }

      double asDouble() {
         return Long.valueOf(this.value).doubleValue();
      }

      long asLong() throws NumberFormatException {
         return this.value;
      }

      int asInt() throws NumberFormatException {
         if (this.value <= 2147483647L && this.value >= -2147483648L) {
            return Long.valueOf(this.value).intValue();
         } else {
            throw new NumberFormatException("long value lies outside the bounds of int");
         }
      }

      boolean asBoolean() throws IllegalStateException {
         throw new IllegalStateException("long is not applicable as boolean");
      }

      int[] asIntArray() throws IllegalStateException {
         if (this.value <= 2147483647L && this.value >= -2147483648L) {
            return new int[]{Long.valueOf(this.value).intValue()};
         } else {
            throw new NumberFormatException("long value lies outside the bounds of int");
         }
      }

      String asString() {
         return String.valueOf(this.value);
      }

      Long asObject() {
         return this.value;
      }
   }

   static class MutableOptionIntValue extends MutableOptionValue {
      private final int value;

      MutableOptionIntValue(int var1) {
         this.value = var1;
      }

      double asDouble() {
         return Integer.valueOf(this.value).doubleValue();
      }

      long asLong() throws NumberFormatException {
         return (long)this.value;
      }

      int asInt() throws NumberFormatException {
         return this.value;
      }

      boolean asBoolean() throws IllegalStateException {
         throw new IllegalStateException("int is not applicable as boolean");
      }

      int[] asIntArray() throws IllegalStateException {
         return new int[]{this.value};
      }

      String asString() {
         return String.valueOf(this.value);
      }

      Integer asObject() {
         return this.value;
      }
   }

   static class MutableOptionBooleanValue extends MutableOptionValue {
      private final boolean value;

      MutableOptionBooleanValue(boolean var1) {
         this.value = var1;
      }

      double asDouble() {
         throw new NumberFormatException("boolean is not applicable as double");
      }

      long asLong() throws NumberFormatException {
         throw new NumberFormatException("boolean is not applicable as Long");
      }

      int asInt() throws NumberFormatException {
         throw new NumberFormatException("boolean is not applicable as int");
      }

      boolean asBoolean() {
         return this.value;
      }

      int[] asIntArray() throws IllegalStateException {
         throw new IllegalStateException("boolean is not applicable as int[]");
      }

      String asString() {
         return String.valueOf(this.value);
      }

      Boolean asObject() {
         return this.value;
      }
   }

   static class MutableOptionIntArrayValue extends MutableOptionValueObject {
      MutableOptionIntArrayValue(int[] var1) {
         super(var1);
      }

      double asDouble() {
         throw new NumberFormatException("int[] is not applicable as double");
      }

      long asLong() throws NumberFormatException {
         throw new NumberFormatException("int[] is not applicable as Long");
      }

      int asInt() throws NumberFormatException {
         throw new NumberFormatException("int[] is not applicable as int");
      }

      boolean asBoolean() {
         throw new NumberFormatException("int[] is not applicable as boolean");
      }

      int[] asIntArray() throws IllegalStateException {
         return (int[])this.value;
      }

      String asString() {
         StringBuilder var1 = new StringBuilder();

         for(int var2 = 0; var2 < ((int[])this.value).length; ++var2) {
            var1.append(((int[])this.value)[var2]);
            if (var2 + 1 < ((int[])this.value).length) {
               var1.append(":");
            }
         }

         return var1.toString();
      }
   }

   static class MutableOptionEnumValue extends MutableOptionValueObject {
      MutableOptionEnumValue(Enum var1) {
         super(var1);
      }

      double asDouble() throws NumberFormatException {
         throw new NumberFormatException("Enum is not applicable as double");
      }

      long asLong() throws NumberFormatException {
         throw new NumberFormatException("Enum is not applicable as long");
      }

      int asInt() throws NumberFormatException {
         throw new NumberFormatException("Enum is not applicable as int");
      }

      boolean asBoolean() throws IllegalStateException {
         throw new NumberFormatException("Enum is not applicable as boolean");
      }

      int[] asIntArray() throws IllegalStateException {
         throw new NumberFormatException("Enum is not applicable as int[]");
      }

      String asString() {
         return ((Enum)this.value).name();
      }
   }
}
