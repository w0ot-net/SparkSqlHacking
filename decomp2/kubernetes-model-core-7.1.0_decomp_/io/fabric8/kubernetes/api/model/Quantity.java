package io.fabric8.kubernetes.api.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import lombok.Generated;

@JsonDeserialize(
   using = Deserializer.class
)
@JsonSerialize(
   using = Serializer.class
)
public class Quantity implements Serializable, Comparable {
   private String amount;
   private String format = "";
   @JsonIgnore
   private Map additionalProperties = new HashMap();

   public Quantity() {
   }

   public Quantity(String amount) {
      Quantity parsedQuantity = parse(amount);
      this.amount = parsedQuantity.getAmount();
      this.format = parsedQuantity.getFormat();
   }

   public Quantity(String amount, String format) {
      this.amount = amount;
      if (format != null) {
         this.format = format;
      }

   }

   public String getAmount() {
      return this.amount;
   }

   public void setAmount(String amount) {
      this.amount = amount;
   }

   public String getFormat() {
      return this.format;
   }

   public void setFormat(String format) {
      this.format = format;
   }

   @JsonIgnore
   public BigDecimal getNumericalAmount() throws ArithmeticException {
      return getAmountInBytes(this);
   }

   public static BigDecimal getAmountInBytes(Quantity quantity) throws ArithmeticException {
      String value = "";
      if (quantity.getAmount() != null && quantity.getFormat() != null) {
         String var10000 = quantity.getAmount();
         value = var10000 + quantity.getFormat();
      } else if (quantity.getAmount() != null) {
         value = quantity.getAmount();
      }

      if (value != null && !value.isEmpty()) {
         if (!Character.isDigit(value.indexOf(0)) && value.startsWith(".")) {
            value = "0" + value;
         }

         Quantity amountFormatPair = parse(value);
         String formatStr = amountFormatPair.getFormat();
         BigDecimal digit = new BigDecimal(amountFormatPair.getAmount());
         BigDecimal multiple = getMultiple(formatStr);
         return digit.multiply(multiple);
      } else {
         throw new IllegalArgumentException("Invalid quantity value passed to parse");
      }
   }

   public static Quantity fromNumericalAmount(BigDecimal amountInBytes, String desiredFormat) {
      if (desiredFormat != null && !desiredFormat.isEmpty()) {
         BigDecimal scaledToDesiredFormat = amountInBytes.divide(getMultiple(desiredFormat), MathContext.DECIMAL64);
         return new Quantity(scaledToDesiredFormat.stripTrailingZeros().toPlainString(), desiredFormat);
      } else {
         return new Quantity(amountInBytes.stripTrailingZeros().toPlainString());
      }
   }

   private static BigDecimal getMultiple(String formatStr) {
      if (containsAtLeastOneDigit(formatStr) && formatStr.length() > 1) {
         int exponent = Integer.parseInt(formatStr.substring(1));
         return (new BigDecimal("10")).pow(exponent, MathContext.DECIMAL64);
      } else {
         BigDecimal multiple = new BigDecimal("1");
         BigDecimal binaryFactor = new BigDecimal("2");
         BigDecimal decimalFactor = new BigDecimal("10");
         switch (formatStr) {
            case "Ki":
               multiple = binaryFactor.pow(10, MathContext.DECIMAL64);
               break;
            case "Mi":
               multiple = binaryFactor.pow(20, MathContext.DECIMAL64);
               break;
            case "Gi":
               multiple = binaryFactor.pow(30, MathContext.DECIMAL64);
               break;
            case "Ti":
               multiple = binaryFactor.pow(40, MathContext.DECIMAL64);
               break;
            case "Pi":
               multiple = binaryFactor.pow(50, MathContext.DECIMAL64);
               break;
            case "Ei":
               multiple = binaryFactor.pow(60, MathContext.DECIMAL64);
               break;
            case "n":
               multiple = decimalFactor.pow(-9, MathContext.DECIMAL64);
               break;
            case "u":
               multiple = decimalFactor.pow(-6, MathContext.DECIMAL64);
               break;
            case "m":
               multiple = decimalFactor.pow(-3, MathContext.DECIMAL64);
               break;
            case "k":
               multiple = decimalFactor.pow(3, MathContext.DECIMAL64);
               break;
            case "M":
               multiple = decimalFactor.pow(6, MathContext.DECIMAL64);
               break;
            case "G":
               multiple = decimalFactor.pow(9, MathContext.DECIMAL64);
               break;
            case "T":
               multiple = decimalFactor.pow(12, MathContext.DECIMAL64);
               break;
            case "P":
               multiple = decimalFactor.pow(15, MathContext.DECIMAL64);
               break;
            case "E":
               multiple = decimalFactor.pow(18, MathContext.DECIMAL64);
            case "":
               break;
            default:
               throw new IllegalArgumentException("Invalid quantity format passed to parse");
         }

         return multiple;
      }
   }

   static boolean containsAtLeastOneDigit(String value) {
      for(int i = 0; i < value.length(); ++i) {
         if (Character.isDigit(value.charAt(i))) {
            return true;
         }
      }

      return false;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         Quantity quantity = (Quantity)o;
         return this.compareTo(quantity) == 0;
      } else {
         return false;
      }
   }

   public int compareTo(Quantity o) {
      return this.getNumericalAmount().compareTo(o.getNumericalAmount());
   }

   public int hashCode() {
      return getAmountInBytes(this).toBigInteger().hashCode();
   }

   public String toString() {
      StringBuilder b = new StringBuilder();
      if (this.getAmount() != null) {
         b.append(this.getAmount());
      }

      if (this.getFormat() != null) {
         b.append(this.getFormat());
      }

      return b.toString();
   }

   public static Quantity parse(String quantityAsString) {
      if (quantityAsString != null && !quantityAsString.isEmpty()) {
         int unitIndex = indexOfUnit(quantityAsString);
         String amountStr = quantityAsString.substring(0, unitIndex);
         String formatStr = quantityAsString.substring(unitIndex);
         if (containsAtLeastOneDigit(formatStr) && Character.isAlphabetic(formatStr.charAt(formatStr.length() - 1))) {
            throw new IllegalArgumentException("Invalid quantity string format passed");
         } else {
            return new Quantity(amountStr, formatStr);
         }
      } else {
         throw new IllegalArgumentException("Invalid quantity string format passed.");
      }
   }

   static int indexOfUnit(String param0) {
      // $FF: Couldn't be decompiled
   }

   @JsonAnyGetter
   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   @JsonAnySetter
   public void setAdditionalProperty(String name, Object value) {
      this.additionalProperties.put(name, value);
   }

   public Quantity add(Quantity y) {
      return this.op(y, BigDecimal::add);
   }

   public Quantity subtract(Quantity y) {
      return this.op(y, BigDecimal::subtract);
   }

   public Quantity multiply(int multiplicand) {
      BigDecimal numericalAmount = this.getNumericalAmount();
      numericalAmount = numericalAmount.multiply(BigDecimal.valueOf((long)multiplicand));
      return fromNumericalAmount(numericalAmount, this.format);
   }

   Quantity op(Quantity y, BiFunction func) {
      BigDecimal numericalAmount = this.getNumericalAmount();
      numericalAmount = (BigDecimal)func.apply(numericalAmount, y.getNumericalAmount());
      String format = this.format;
      if (numericalAmount.signum() == 0) {
         format = y.format;
      }

      return fromNumericalAmount(numericalAmount, format);
   }

   @JsonIgnore
   @Generated
   public void setAdditionalProperties(Map additionalProperties) {
      this.additionalProperties = additionalProperties;
   }

   public static class Serializer extends JsonSerializer {
      public void serialize(Quantity value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
         if (value != null) {
            StringBuilder objAsStringBuilder = new StringBuilder();
            if (value.getAmount() != null) {
               objAsStringBuilder.append(value.getAmount());
            }

            if (value.getFormat() != null) {
               objAsStringBuilder.append(value.getFormat());
            }

            jgen.writeString(objAsStringBuilder.toString());
         } else {
            jgen.writeNull();
         }

      }
   }

   public static class Deserializer extends JsonDeserializer {
      public Quantity deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
         ObjectCodec oc = jsonParser.getCodec();
         JsonNode node = (JsonNode)oc.readTree(jsonParser);
         Quantity quantity = null;
         if (node.get("amount") != null && node.get("format") != null) {
            quantity = new Quantity(node.get("amount").asText(), node.get("format").asText());
         } else if (node.get("amount") != null) {
            quantity = new Quantity(node.get("amount").asText());
         } else {
            quantity = new Quantity(node.asText());
         }

         return quantity;
      }
   }
}
