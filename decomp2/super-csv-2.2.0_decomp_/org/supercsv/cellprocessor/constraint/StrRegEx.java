package org.supercsv.cellprocessor.constraint;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.SuperCsvConstraintViolationException;
import org.supercsv.util.CsvContext;

public class StrRegEx extends CellProcessorAdaptor implements StringCellProcessor {
   private final String regex;
   private final Pattern regexPattern;
   private static final Map REGEX_MSGS = new HashMap();

   public StrRegEx(String regex) {
      checkPreconditions(regex);
      this.regexPattern = Pattern.compile(regex);
      this.regex = regex;
   }

   public StrRegEx(String regex, StringCellProcessor next) {
      super(next);
      checkPreconditions(regex);
      this.regexPattern = Pattern.compile(regex);
      this.regex = regex;
   }

   private static void checkPreconditions(String regex) {
      if (regex == null) {
         throw new NullPointerException("regex should not be null");
      } else if (regex.length() == 0) {
         throw new IllegalArgumentException("regex should not be empty");
      }
   }

   public Object execute(Object value, CsvContext context) {
      this.validateInputNotNull(value, context);
      boolean matches = this.regexPattern.matcher((String)value).matches();
      if (!matches) {
         String msg = (String)REGEX_MSGS.get(this.regex);
         if (msg == null) {
            throw new SuperCsvConstraintViolationException(String.format("'%s' does not match the regular expression '%s'", value, this.regex), context, this);
         } else {
            throw new SuperCsvConstraintViolationException(String.format("'%s' does not match the constraint '%s' defined by the regular expression '%s'", value, msg, this.regex), context, this);
         }
      } else {
         return this.next.execute(value, context);
      }
   }

   public static void registerMessage(String regex, String message) {
      REGEX_MSGS.put(regex, message);
   }
}
