package org.supercsv.cellprocessor.constraint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.SuperCsvConstraintViolationException;
import org.supercsv.util.CsvContext;

public class RequireSubStr extends CellProcessorAdaptor implements StringCellProcessor {
   private final List requiredSubStrings = new ArrayList();

   public RequireSubStr(String... requiredSubStrings) {
      checkPreconditions(requiredSubStrings);
      this.checkAndAddRequiredSubStrings(requiredSubStrings);
   }

   public RequireSubStr(List requiredSubStrings, CellProcessor next) {
      super(next);
      checkPreconditions(requiredSubStrings);
      this.checkAndAddRequiredSubStrings(requiredSubStrings);
   }

   public RequireSubStr(String requiredSubString, CellProcessor next) {
      super(next);
      checkPreconditions(requiredSubString);
      this.checkAndAddRequiredSubStrings(requiredSubString);
   }

   public RequireSubStr(String[] requiredSubStrings, CellProcessor next) {
      super(next);
      checkPreconditions(requiredSubStrings);
      this.checkAndAddRequiredSubStrings(requiredSubStrings);
   }

   private static void checkPreconditions(String... requiredSubStrings) {
      if (requiredSubStrings == null) {
         throw new NullPointerException("requiredSubStrings array should not be null");
      } else if (requiredSubStrings.length == 0) {
         throw new IllegalArgumentException("requiredSubStrings array should not be empty");
      }
   }

   private static void checkPreconditions(List requiredSubStrings) {
      if (requiredSubStrings == null) {
         throw new NullPointerException("requiredSubStrings List should not be null");
      } else if (requiredSubStrings.isEmpty()) {
         throw new IllegalArgumentException("requiredSubStrings List should not be empty");
      }
   }

   private void checkAndAddRequiredSubStrings(List requiredSubStrings) {
      for(String required : requiredSubStrings) {
         if (required == null) {
            throw new NullPointerException("required substring should not be null");
         }

         this.requiredSubStrings.add(required);
      }

   }

   private void checkAndAddRequiredSubStrings(String... requiredSubStrings) {
      this.checkAndAddRequiredSubStrings(Arrays.asList(requiredSubStrings));
   }

   public Object execute(Object value, CsvContext context) {
      this.validateInputNotNull(value, context);
      String stringValue = value.toString();

      for(String required : this.requiredSubStrings) {
         if (stringValue.contains(required)) {
            return this.next.execute(value, context);
         }
      }

      throw new SuperCsvConstraintViolationException(String.format("'%s' does not contain any of the required substrings", value), context, this);
   }
}
