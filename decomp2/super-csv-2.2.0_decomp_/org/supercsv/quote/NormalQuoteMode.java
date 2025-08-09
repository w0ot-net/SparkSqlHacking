package org.supercsv.quote;

import org.supercsv.prefs.CsvPreference;
import org.supercsv.util.CsvContext;

public class NormalQuoteMode implements QuoteMode {
   public boolean quotesRequired(String csvColumn, CsvContext context, CsvPreference preference) {
      return false;
   }
}
