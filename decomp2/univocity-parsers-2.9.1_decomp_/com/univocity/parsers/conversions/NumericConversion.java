package com.univocity.parsers.conversions;

import [Ljava.lang.String;;
import [Ljava.text.DecimalFormat;;
import com.univocity.parsers.annotations.helpers.AnnotationHelper;
import com.univocity.parsers.common.ArgumentUtils;
import com.univocity.parsers.common.DataProcessingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.Arrays;

public abstract class NumericConversion extends ObjectConversion implements FormattedConversion {
   private DecimalFormat[] formatters;
   private String[] formats;
   private final ParsePosition position;
   private Class numberType;

   public NumericConversion(Number valueIfStringIsNull, String valueIfObjectIsNull, String... numericFormats) {
      super(valueIfStringIsNull, valueIfObjectIsNull);
      this.formatters = new DecimalFormat[0];
      this.formats = new String[0];
      this.position = new ParsePosition(0);
      this.numberType = Number.class;
      ArgumentUtils.noNulls("Numeric formats", numericFormats);
      this.formats = (String[])((String;)numericFormats).clone();
      this.formatters = new DecimalFormat[numericFormats.length];

      for(int i = 0; i < numericFormats.length; ++i) {
         String numericFormat = numericFormats[i];
         this.formatters[i] = new DecimalFormat(numericFormat);
         this.configureFormatter(this.formatters[i]);
      }

   }

   public NumericConversion(Number valueIfStringIsNull, String valueIfObjectIsNull, DecimalFormat... numericFormatters) {
      super(valueIfStringIsNull, valueIfObjectIsNull);
      this.formatters = new DecimalFormat[0];
      this.formats = new String[0];
      this.position = new ParsePosition(0);
      this.numberType = Number.class;
      ArgumentUtils.noNulls("Numeric formatters", numericFormatters);
      this.formatters = (DecimalFormat[])((DecimalFormat;)numericFormatters).clone();
      this.formats = new String[numericFormatters.length];

      for(int i = 0; i < numericFormatters.length; ++i) {
         this.formats[i] = numericFormatters[i].toPattern();
      }

   }

   public NumericConversion(Number valueIfStringIsNull, String valueIfObjectIsNull) {
      super(valueIfStringIsNull, valueIfObjectIsNull);
      this.formatters = new DecimalFormat[0];
      this.formats = new String[0];
      this.position = new ParsePosition(0);
      this.numberType = Number.class;
   }

   public NumericConversion(String... numericFormats) {
      this((Number)null, (String)null, (String[])numericFormats);
   }

   public NumericConversion(DecimalFormat... numericFormatters) {
      this((Number)null, (String)null, (DecimalFormat[])numericFormatters);
   }

   public NumericConversion() {
      this.formatters = new DecimalFormat[0];
      this.formats = new String[0];
      this.position = new ParsePosition(0);
      this.numberType = Number.class;
   }

   public Class getNumberType() {
      return this.numberType;
   }

   public void setNumberType(Class numberType) {
      this.numberType = numberType;
   }

   public DecimalFormat[] getFormatterObjects() {
      return this.formatters;
   }

   protected abstract void configureFormatter(DecimalFormat var1);

   protected Number fromString(String input) {
      for(int i = 0; i < this.formatters.length; ++i) {
         this.position.setIndex(0);
         T out = (T)this.formatters[i].parse(input, this.position);
         if (this.formatters.length == 1 || this.position.getIndex() == input.length()) {
            if (out != null && this.numberType != Number.class) {
               if (this.numberType == Double.class) {
                  return out.doubleValue();
               } else if (this.numberType == Float.class) {
                  return out.floatValue();
               } else if (this.numberType == BigDecimal.class) {
                  return (Number)(out instanceof BigDecimal ? out : new BigDecimal(String.valueOf(out)));
               } else if (this.numberType == BigInteger.class) {
                  return (Number)(out instanceof BigInteger ? out : BigInteger.valueOf(out.longValue()));
               } else if (this.numberType == Long.class) {
                  return out.longValue();
               } else if (this.numberType == Integer.class) {
                  return out.intValue();
               } else if (this.numberType == Short.class) {
                  return out.shortValue();
               } else {
                  return (Number)(this.numberType == Byte.class ? out.byteValue() : out);
               }
            } else {
               return out;
            }
         }
      }

      DataProcessingException exception = new DataProcessingException("Cannot parse '{value}' as a valid number. Supported formats are: " + Arrays.toString(this.formats));
      exception.setValue(input);
      throw exception;
   }

   public String revert(Number input) {
      if (input == null) {
         return super.revert((Object)null);
      } else {
         for(DecimalFormat formatter : this.formatters) {
            try {
               return formatter.format(input);
            } catch (Throwable var7) {
            }
         }

         DataProcessingException exception = new DataProcessingException("Cannot format '{value}'. No valid formatters were defined.");
         exception.setValue(input);
         throw exception;
      }
   }

   public void addFormat(String format, String... formatOptions) {
      DecimalFormat formatter = new DecimalFormat(format);
      this.configureFormatter(formatter);
      AnnotationHelper.applyFormatSettings(formatter, formatOptions);
      this.formats = (String[])Arrays.copyOf(this.formats, this.formats.length + 1);
      this.formatters = (DecimalFormat[])Arrays.copyOf(this.formatters, this.formatters.length + 1);
      this.formats[this.formats.length - 1] = format;
      this.formatters[this.formatters.length - 1] = formatter;
   }
}
