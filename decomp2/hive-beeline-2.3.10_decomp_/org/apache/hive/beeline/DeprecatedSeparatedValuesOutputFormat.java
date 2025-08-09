package org.apache.hive.beeline;

class DeprecatedSeparatedValuesOutputFormat implements OutputFormat {
   private final BeeLine beeLine;
   private char separator;

   public DeprecatedSeparatedValuesOutputFormat(BeeLine beeLine, char separator) {
      this.beeLine = beeLine;
      this.setSeparator(separator);
   }

   public int print(Rows rows) {
      int count = 0;

      while(rows.hasNext()) {
         if (count == 0 && !this.beeLine.getOpts().getShowHeader()) {
            rows.next();
            ++count;
         } else {
            this.printRow(rows, (Rows.Row)rows.next());
            ++count;
         }
      }

      return count - 1;
   }

   public void printRow(Rows rows, Rows.Row row) {
      String[] vals = row.values;
      StringBuilder buf = new StringBuilder();

      for(int i = 0; i < vals.length; ++i) {
         buf.append(buf.length() == 0 ? "" : "" + this.getSeparator()).append('\'').append(vals[i] == null ? "" : vals[i]).append('\'');
      }

      this.beeLine.output(buf.toString());
   }

   public void setSeparator(char separator) {
      this.separator = separator;
   }

   public char getSeparator() {
      return this.separator;
   }
}
