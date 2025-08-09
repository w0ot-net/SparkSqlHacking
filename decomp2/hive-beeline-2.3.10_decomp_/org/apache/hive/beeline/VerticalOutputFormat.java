package org.apache.hive.beeline;

class VerticalOutputFormat implements OutputFormat {
   private final BeeLine beeLine;

   VerticalOutputFormat(BeeLine beeLine) {
      this.beeLine = beeLine;
   }

   public int print(Rows rows) {
      int count = 0;

      for(Rows.Row header = (Rows.Row)rows.next(); rows.hasNext(); ++count) {
         this.printRow(rows, header, (Rows.Row)rows.next());
      }

      return count;
   }

   public void printRow(Rows rows, Rows.Row header, Rows.Row row) {
      String[] head = header.values;
      String[] vals = row.values;
      int headwidth = 0;

      for(int i = 0; i < head.length && i < vals.length; ++i) {
         headwidth = Math.max(headwidth, head[i].length());
      }

      headwidth += 2;

      for(int i = 0; i < head.length && i < vals.length; ++i) {
         this.beeLine.output(this.beeLine.getColorBuffer().bold(this.beeLine.getColorBuffer().pad(head[i], headwidth).getMono()).append(vals[i] == null ? "" : vals[i]));
      }

      this.beeLine.output("");
   }
}
