package org.apache.hive.beeline;

import java.io.IOException;
import java.io.StringWriter;
import org.apache.hadoop.io.IOUtils;
import org.supercsv.io.CsvListWriter;
import org.supercsv.prefs.CsvPreference;

class SeparatedValuesOutputFormat implements OutputFormat {
   public static final String DISABLE_QUOTING_FOR_SV = "disable.quoting.for.sv";
   private final BeeLine beeLine;
   private CsvPreference quotedCsvPreference;
   private CsvPreference unquotedCsvPreference;

   SeparatedValuesOutputFormat(BeeLine beeLine, char separator) {
      this.beeLine = beeLine;
      this.unquotedCsvPreference = (new CsvPreference.Builder('\u0000', separator, "")).build();
      this.quotedCsvPreference = (new CsvPreference.Builder('"', separator, "")).build();
   }

   private void updateCsvPreference() {
      if (this.beeLine.getOpts().getOutputFormat().equals("dsv")) {
         char curDel = (char)this.getCsvPreference().getDelimiterChar();
         char newDel = this.beeLine.getOpts().getDelimiterForDSV();
         if (newDel != curDel) {
            if (this.isQuotingDisabled()) {
               this.unquotedCsvPreference = (new CsvPreference.Builder('\u0000', newDel, "")).build();
            } else {
               this.quotedCsvPreference = (new CsvPreference.Builder('"', newDel, "")).build();
            }
         }
      }

   }

   public int print(Rows rows) {
      this.updateCsvPreference();
      int count = 0;

      while(rows.hasNext()) {
         if (count == 0 && !this.beeLine.getOpts().getShowHeader()) {
            rows.next();
            ++count;
         } else {
            this.printRow((Rows.Row)rows.next());
            ++count;
         }
      }

      return count - 1;
   }

   private String getFormattedStr(String[] vals) {
      StringWriter strWriter = new StringWriter();
      CsvListWriter writer = new CsvListWriter(strWriter, this.getCsvPreference());
      if (vals.length > 0) {
         try {
            writer.write(vals);
         } catch (IOException e) {
            this.beeLine.error((Throwable)e);
         } finally {
            IOUtils.closeStream(writer);
         }
      }

      return strWriter.toString();
   }

   private void printRow(Rows.Row row) {
      String[] vals = row.values;
      String formattedStr = this.getFormattedStr(vals);
      this.beeLine.output(formattedStr);
   }

   private boolean isQuotingDisabled() {
      String quotingDisabledStr = System.getProperty("disable.quoting.for.sv");
      if (quotingDisabledStr != null && !quotingDisabledStr.isEmpty()) {
         String parsedOptionStr = quotingDisabledStr.toLowerCase();
         if (!parsedOptionStr.equals("false") && !parsedOptionStr.equals("true")) {
            this.beeLine.error("System Property disable.quoting.for.sv is now " + parsedOptionStr + " which only accepts boolean value");
            return true;
         } else {
            return Boolean.parseBoolean(parsedOptionStr);
         }
      } else {
         return true;
      }
   }

   private CsvPreference getCsvPreference() {
      return this.isQuotingDisabled() ? this.unquotedCsvPreference : this.quotedCsvPreference;
   }
}
