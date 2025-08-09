package org.supercsv.cellprocessor.constraint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.SuperCsvConstraintViolationException;
import org.supercsv.util.CsvContext;

public class ForbidSubStr extends CellProcessorAdaptor implements StringCellProcessor {
   private final List forbiddenSubStrings;

   public ForbidSubStr(List forbiddenSubStrings) {
      this.forbiddenSubStrings = new ArrayList();
      checkPreconditions(forbiddenSubStrings);
      this.checkAndAddForbiddenStrings(forbiddenSubStrings);
   }

   public ForbidSubStr(String... forbiddenSubStrings) {
      this.forbiddenSubStrings = new ArrayList();
      checkPreconditions(forbiddenSubStrings);
      this.checkAndAddForbiddenStrings(forbiddenSubStrings);
   }

   public ForbidSubStr(List forbiddenSubStrings, CellProcessor next) {
      super(next);
      this.forbiddenSubStrings = new ArrayList();
      checkPreconditions(forbiddenSubStrings);
      this.checkAndAddForbiddenStrings(forbiddenSubStrings);
   }

   public ForbidSubStr(String forbiddenSubString, CellProcessor next) {
      this(new String[]{forbiddenSubString}, next);
   }

   public ForbidSubStr(String[] forbiddenSubStrings, CellProcessor next) {
      super(next);
      this.forbiddenSubStrings = new ArrayList();
      checkPreconditions(forbiddenSubStrings);
      this.checkAndAddForbiddenStrings(forbiddenSubStrings);
   }

   private static void checkPreconditions(List forbiddenSubStrings) {
      if (forbiddenSubStrings == null) {
         throw new NullPointerException("forbiddenSubStrings list should not be null");
      } else if (forbiddenSubStrings.isEmpty()) {
         throw new IllegalArgumentException("forbiddenSubStrings list should not be empty");
      }
   }

   private static void checkPreconditions(String... forbiddenSubStrings) {
      if (forbiddenSubStrings == null) {
         throw new NullPointerException("forbiddenSubStrings array should not be null");
      } else if (forbiddenSubStrings.length == 0) {
         throw new IllegalArgumentException("forbiddenSubStrings array should not be empty");
      }
   }

   private void checkAndAddForbiddenStrings(String... forbiddenSubStrings) {
      this.checkAndAddForbiddenStrings(Arrays.asList(forbiddenSubStrings));
   }

   private void checkAndAddForbiddenStrings(List forbiddenSubStrings) {
      for(String forbidden : forbiddenSubStrings) {
         if (forbidden == null) {
            throw new NullPointerException("forbidden substring should not be null");
         }

         this.forbiddenSubStrings.add(forbidden);
      }

   }

   public Object execute(Object value, CsvContext context) {
      this.validateInputNotNull(value, context);
      String stringValue = value.toString();

      for(String forbidden : this.forbiddenSubStrings) {
         if (stringValue.contains(forbidden)) {
            throw new SuperCsvConstraintViolationException(String.format("'%s' contains the forbidden substring '%s'", value, forbidden), context, this);
         }
      }

      return this.next.execute(value, context);
   }
}
