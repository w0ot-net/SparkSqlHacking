package com.google.gson;

import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.internal.NumberLimits;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

public final class JsonPrimitive extends JsonElement {
   private final Object value;

   public JsonPrimitive(Boolean bool) {
      this.value = Objects.requireNonNull(bool);
   }

   public JsonPrimitive(Number number) {
      this.value = Objects.requireNonNull(number);
   }

   public JsonPrimitive(String string) {
      this.value = Objects.requireNonNull(string);
   }

   public JsonPrimitive(Character c) {
      this.value = ((Character)Objects.requireNonNull(c)).toString();
   }

   public JsonPrimitive deepCopy() {
      return this;
   }

   public boolean isBoolean() {
      return this.value instanceof Boolean;
   }

   public boolean getAsBoolean() {
      return this.isBoolean() ? (Boolean)this.value : Boolean.parseBoolean(this.getAsString());
   }

   public boolean isNumber() {
      return this.value instanceof Number;
   }

   public Number getAsNumber() {
      if (this.value instanceof Number) {
         return (Number)this.value;
      } else if (this.value instanceof String) {
         return new LazilyParsedNumber((String)this.value);
      } else {
         throw new UnsupportedOperationException("Primitive is neither a number nor a string");
      }
   }

   public boolean isString() {
      return this.value instanceof String;
   }

   public String getAsString() {
      if (this.value instanceof String) {
         return (String)this.value;
      } else if (this.isNumber()) {
         return this.getAsNumber().toString();
      } else if (this.isBoolean()) {
         return ((Boolean)this.value).toString();
      } else {
         throw new AssertionError("Unexpected value type: " + this.value.getClass());
      }
   }

   public double getAsDouble() {
      return this.isNumber() ? this.getAsNumber().doubleValue() : Double.parseDouble(this.getAsString());
   }

   public BigDecimal getAsBigDecimal() {
      return this.value instanceof BigDecimal ? (BigDecimal)this.value : NumberLimits.parseBigDecimal(this.getAsString());
   }

   public BigInteger getAsBigInteger() {
      return this.value instanceof BigInteger ? (BigInteger)this.value : (isIntegral(this) ? BigInteger.valueOf(this.getAsNumber().longValue()) : NumberLimits.parseBigInteger(this.getAsString()));
   }

   public float getAsFloat() {
      return this.isNumber() ? this.getAsNumber().floatValue() : Float.parseFloat(this.getAsString());
   }

   public long getAsLong() {
      return this.isNumber() ? this.getAsNumber().longValue() : Long.parseLong(this.getAsString());
   }

   public short getAsShort() {
      return this.isNumber() ? this.getAsNumber().shortValue() : Short.parseShort(this.getAsString());
   }

   public int getAsInt() {
      return this.isNumber() ? this.getAsNumber().intValue() : Integer.parseInt(this.getAsString());
   }

   public byte getAsByte() {
      return this.isNumber() ? this.getAsNumber().byteValue() : Byte.parseByte(this.getAsString());
   }

   /** @deprecated */
   @Deprecated
   public char getAsCharacter() {
      String s = this.getAsString();
      if (s.isEmpty()) {
         throw new UnsupportedOperationException("String value is empty");
      } else {
         return s.charAt(0);
      }
   }

   public int hashCode() {
      if (this.value == null) {
         return 31;
      } else if (isIntegral(this)) {
         long value = this.getAsNumber().longValue();
         return (int)(value ^ value >>> 32);
      } else if (this.value instanceof Number) {
         long value = Double.doubleToLongBits(this.getAsNumber().doubleValue());
         return (int)(value ^ value >>> 32);
      } else {
         return this.value.hashCode();
      }
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && this.getClass() == obj.getClass()) {
         JsonPrimitive other = (JsonPrimitive)obj;
         if (this.value == null) {
            return other.value == null;
         } else if (isIntegral(this) && isIntegral(other)) {
            return !(this.value instanceof BigInteger) && !(other.value instanceof BigInteger) ? this.getAsNumber().longValue() == other.getAsNumber().longValue() : this.getAsBigInteger().equals(other.getAsBigInteger());
         } else if (this.value instanceof Number && other.value instanceof Number) {
            if (this.value instanceof BigDecimal && other.value instanceof BigDecimal) {
               return this.getAsBigDecimal().compareTo(other.getAsBigDecimal()) == 0;
            } else {
               double thisAsDouble = this.getAsDouble();
               double otherAsDouble = other.getAsDouble();
               return thisAsDouble == otherAsDouble || Double.isNaN(thisAsDouble) && Double.isNaN(otherAsDouble);
            }
         } else {
            return this.value.equals(other.value);
         }
      } else {
         return false;
      }
   }

   private static boolean isIntegral(JsonPrimitive primitive) {
      if (!(primitive.value instanceof Number)) {
         return false;
      } else {
         Number number = (Number)primitive.value;
         return number instanceof BigInteger || number instanceof Long || number instanceof Integer || number instanceof Short || number instanceof Byte;
      }
   }
}
