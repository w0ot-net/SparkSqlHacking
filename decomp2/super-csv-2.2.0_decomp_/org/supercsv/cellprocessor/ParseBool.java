package org.supercsv.cellprocessor;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.supercsv.cellprocessor.ift.BoolCellProcessor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

public class ParseBool extends CellProcessorAdaptor implements StringCellProcessor {
   private static final String[] DEFAULT_TRUE_VALUES = new String[]{"1", "true", "t", "y"};
   private static final String[] DEFAULT_FALSE_VALUES = new String[]{"0", "false", "f", "n"};
   private final Set trueValues;
   private final Set falseValues;

   public ParseBool() {
      this(DEFAULT_TRUE_VALUES, DEFAULT_FALSE_VALUES);
   }

   public ParseBool(BoolCellProcessor next) {
      this(DEFAULT_TRUE_VALUES, DEFAULT_FALSE_VALUES, next);
   }

   public ParseBool(String trueValue, String falseValue) {
      this.trueValues = new HashSet();
      this.falseValues = new HashSet();
      checkPreconditions(trueValue, falseValue);
      this.trueValues.add(trueValue);
      this.falseValues.add(falseValue);
   }

   public ParseBool(String[] trueValues, String[] falseValues) {
      this.trueValues = new HashSet();
      this.falseValues = new HashSet();
      checkPreconditions(trueValues, falseValues);
      Collections.addAll(this.trueValues, trueValues);
      Collections.addAll(this.falseValues, falseValues);
   }

   public ParseBool(String trueValue, String falseValue, BoolCellProcessor next) {
      super(next);
      this.trueValues = new HashSet();
      this.falseValues = new HashSet();
      checkPreconditions(trueValue, falseValue);
      this.trueValues.add(trueValue);
      this.falseValues.add(falseValue);
   }

   public ParseBool(String[] trueValues, String[] falseValues, BoolCellProcessor next) {
      super(next);
      this.trueValues = new HashSet();
      this.falseValues = new HashSet();
      checkPreconditions(trueValues, falseValues);
      Collections.addAll(this.trueValues, trueValues);
      Collections.addAll(this.falseValues, falseValues);
   }

   private static void checkPreconditions(String trueValue, String falseValue) {
      if (trueValue == null) {
         throw new NullPointerException("trueValue should not be null");
      } else if (falseValue == null) {
         throw new NullPointerException("falseValue should not be null");
      }
   }

   private static void checkPreconditions(String[] trueValues, String[] falseValues) {
      if (trueValues == null) {
         throw new NullPointerException("trueValues should not be null");
      } else if (trueValues.length == 0) {
         throw new IllegalArgumentException("trueValues should not be empty");
      } else if (falseValues == null) {
         throw new NullPointerException("falseValues should not be null");
      } else if (falseValues.length == 0) {
         throw new IllegalArgumentException("falseValues should not be empty");
      }
   }

   public Object execute(Object value, CsvContext context) {
      this.validateInputNotNull(value, context);
      if (!(value instanceof String)) {
         throw new SuperCsvCellProcessorException(String.class, value, context, this);
      } else {
         String stringValue = ((String)value).toLowerCase();
         Boolean result;
         if (this.trueValues.contains(stringValue)) {
            result = Boolean.TRUE;
         } else {
            if (!this.falseValues.contains(stringValue)) {
               throw new SuperCsvCellProcessorException(String.format("'%s' could not be parsed as a Boolean", value), context, this);
            }

            result = Boolean.FALSE;
         }

         return this.next.execute(result, context);
      }
   }
}
