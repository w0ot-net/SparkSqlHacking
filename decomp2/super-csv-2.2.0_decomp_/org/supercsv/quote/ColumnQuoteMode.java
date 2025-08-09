package org.supercsv.quote;

import java.util.HashSet;
import java.util.Set;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.util.CsvContext;

public class ColumnQuoteMode implements QuoteMode {
   private final Set columnNumbers = new HashSet();

   public ColumnQuoteMode(int... columnsToQuote) {
      if (columnsToQuote == null) {
         throw new NullPointerException("columnsToQuote should not be null");
      } else {
         int[] var2 = columnsToQuote;
         int var3 = columnsToQuote.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            Integer columnToQuote = var2[var4];
            this.columnNumbers.add(columnToQuote);
         }

      }
   }

   public ColumnQuoteMode(boolean[] columnsToQuote) {
      if (columnsToQuote == null) {
         throw new NullPointerException("columnsToQuote should not be null");
      } else {
         for(int i = 0; i < columnsToQuote.length; ++i) {
            if (columnsToQuote[i]) {
               this.columnNumbers.add(i + 1);
            }
         }

      }
   }

   public boolean quotesRequired(String csvColumn, CsvContext context, CsvPreference preference) {
      return this.columnNumbers.contains(context.getColumnNumber());
   }
}
