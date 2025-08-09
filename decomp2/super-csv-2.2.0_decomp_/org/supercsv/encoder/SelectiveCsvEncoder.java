package org.supercsv.encoder;

import java.util.HashSet;
import java.util.Set;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.util.CsvContext;

public class SelectiveCsvEncoder extends DefaultCsvEncoder {
   private final Set columnNumbers = new HashSet();

   public SelectiveCsvEncoder(int... columnsToEncode) {
      if (columnsToEncode == null) {
         throw new NullPointerException("columnsToEncode should not be null");
      } else {
         int[] var2 = columnsToEncode;
         int var3 = columnsToEncode.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            Integer columnToEncode = var2[var4];
            this.columnNumbers.add(columnToEncode);
         }

      }
   }

   public SelectiveCsvEncoder(boolean[] columnsToEncode) {
      if (columnsToEncode == null) {
         throw new NullPointerException("columnsToEncode should not be null");
      } else {
         for(int i = 0; i < columnsToEncode.length; ++i) {
            if (columnsToEncode[i]) {
               this.columnNumbers.add(i + 1);
            }
         }

      }
   }

   public String encode(String input, CsvContext context, CsvPreference preference) {
      return this.columnNumbers.contains(context.getColumnNumber()) ? super.encode(input, context, preference) : input;
   }
}
