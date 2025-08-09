package org.apache.hive.beeline;

abstract class AbstractOutputFormat implements OutputFormat {
   public int print(Rows rows) {
      int count = 0;
      Rows.Row header = (Rows.Row)rows.next();
      this.printHeader(header);

      while(rows.hasNext()) {
         this.printRow(rows, header, (Rows.Row)rows.next());
         ++count;
      }

      this.printFooter(header);
      return count;
   }

   abstract void printHeader(Rows.Row var1);

   abstract void printFooter(Rows.Row var1);

   abstract void printRow(Rows var1, Rows.Row var2, Rows.Row var3);
}
