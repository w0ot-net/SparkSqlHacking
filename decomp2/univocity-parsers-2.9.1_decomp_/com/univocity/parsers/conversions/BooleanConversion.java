package com.univocity.parsers.conversions;

import com.univocity.parsers.common.ArgumentUtils;
import com.univocity.parsers.common.DataProcessingException;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class BooleanConversion extends ObjectConversion {
   private String defaultForTrue;
   private String defaultForFalse;
   private final Set falseValues;
   private final Set trueValues;

   public BooleanConversion(String[] valuesForTrue, String[] valuesForFalse) {
      this((Boolean)null, (String)null, valuesForTrue, valuesForFalse);
   }

   public BooleanConversion(Boolean valueIfStringIsNull, String valueIfObjectIsNull, String[] valuesForTrue, String[] valuesForFalse) {
      super(valueIfStringIsNull, valueIfObjectIsNull);
      this.falseValues = new LinkedHashSet();
      this.trueValues = new LinkedHashSet();
      ArgumentUtils.notEmpty("Values for true", valuesForTrue);
      ArgumentUtils.notEmpty("Values for false", valuesForFalse);
      Collections.addAll(this.falseValues, valuesForFalse);
      Collections.addAll(this.trueValues, valuesForTrue);
      normalize((Collection)this.falseValues);
      normalize((Collection)this.trueValues);

      for(String falseValue : this.falseValues) {
         if (this.trueValues.contains(falseValue)) {
            throw new DataProcessingException("Ambiguous string representation for both false and true values: '" + falseValue + '\'');
         }
      }

      this.defaultForTrue = valuesForTrue[0];
      this.defaultForFalse = valuesForFalse[0];
   }

   public String revert(Boolean input) {
      if (input != null) {
         if (Boolean.FALSE.equals(input)) {
            return this.defaultForFalse;
         }

         if (Boolean.TRUE.equals(input)) {
            return this.defaultForTrue;
         }
      }

      return this.getValueIfObjectIsNull();
   }

   protected Boolean fromString(String input) {
      return input != null ? getBoolean(input, this.trueValues, this.falseValues) : (Boolean)super.getValueIfStringIsNull();
   }

   public static Boolean getBoolean(String booleanString, String[] trueValues, String[] falseValues) {
      trueValues = trueValues != null && trueValues.length != 0 ? trueValues : new String[]{"true"};
      falseValues = falseValues != null && falseValues.length != 0 ? falseValues : new String[]{"false"};
      BooleanConversion tmp = new BooleanConversion(trueValues, falseValues);
      return getBoolean(booleanString, tmp.trueValues, tmp.falseValues);
   }

   private static Boolean getBoolean(String defaultString, Set trueValues, Set falseValues) {
      String normalized = normalize(defaultString);
      if (falseValues.contains(normalized)) {
         return Boolean.FALSE;
      } else if (trueValues.contains(normalized)) {
         return Boolean.TRUE;
      } else {
         DataProcessingException exception = new DataProcessingException("Unable to convert '{value}' to Boolean. Allowed Strings are: " + trueValues + " for true; and " + falseValues + " for false.");
         exception.setValue(defaultString);
         throw exception;
      }
   }

   private static String normalize(String string) {
      return string == null ? null : string.trim().toLowerCase();
   }

   private static void normalize(Collection strings) {
      LinkedHashSet<String> normalized = new LinkedHashSet(strings.size());

      for(String string : strings) {
         if (string == null) {
            normalized.add((Object)null);
         } else {
            normalized.add(string.trim().toLowerCase());
         }
      }

      strings.clear();
      strings.addAll(normalized);
   }
}
