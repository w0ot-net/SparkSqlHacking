package org.supercsv.cellprocessor;

import java.math.BigDecimal;
import java.text.DecimalFormatSymbols;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

public class ParseBigDecimal extends CellProcessorAdaptor implements StringCellProcessor {
   private static final char DEFAULT_DECIMAL_SEPARATOR = '.';
   private final DecimalFormatSymbols symbols;

   public ParseBigDecimal() {
      this.symbols = null;
   }

   public ParseBigDecimal(DecimalFormatSymbols symbols) {
      checkPreconditions(symbols);
      this.symbols = symbols;
   }

   public ParseBigDecimal(CellProcessor next) {
      super(next);
      this.symbols = null;
   }

   public ParseBigDecimal(DecimalFormatSymbols symbols, CellProcessor next) {
      super(next);
      checkPreconditions(symbols);
      this.symbols = symbols;
   }

   private static void checkPreconditions(DecimalFormatSymbols symbols) {
      if (symbols == null) {
         throw new NullPointerException("symbols should not be null");
      }
   }

   public Object execute(Object value, CsvContext context) {
      this.validateInputNotNull(value, context);
      if (value instanceof String) {
         String s = (String)value;

         BigDecimal result;
         try {
            if (this.symbols == null) {
               result = new BigDecimal(s);
            } else {
               result = new BigDecimal(fixSymbols(s, this.symbols));
            }
         } catch (NumberFormatException e) {
            throw new SuperCsvCellProcessorException(String.format("'%s' could not be parsed as a BigDecimal", value), context, this, e);
         }

         return this.next.execute(result, context);
      } else {
         throw new SuperCsvCellProcessorException(String.class, value, context, this);
      }
   }

   private static String fixSymbols(String s, DecimalFormatSymbols symbols) {
      char groupingSeparator = symbols.getGroupingSeparator();
      char decimalSeparator = symbols.getDecimalSeparator();
      return s.replace(String.valueOf(groupingSeparator), "").replace(decimalSeparator, '.');
   }
}
