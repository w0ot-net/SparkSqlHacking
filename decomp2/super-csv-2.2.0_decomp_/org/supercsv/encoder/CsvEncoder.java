package org.supercsv.encoder;

import org.supercsv.prefs.CsvPreference;
import org.supercsv.util.CsvContext;

public interface CsvEncoder {
   String encode(String var1, CsvContext var2, CsvPreference var3);
}
