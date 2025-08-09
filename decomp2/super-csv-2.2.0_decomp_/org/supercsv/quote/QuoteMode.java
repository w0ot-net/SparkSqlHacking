package org.supercsv.quote;

import org.supercsv.prefs.CsvPreference;
import org.supercsv.util.CsvContext;

public interface QuoteMode {
   boolean quotesRequired(String var1, CsvContext var2, CsvPreference var3);
}
