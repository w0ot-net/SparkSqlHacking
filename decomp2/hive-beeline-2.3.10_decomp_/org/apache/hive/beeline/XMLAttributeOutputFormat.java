package org.apache.hive.beeline;

class XMLAttributeOutputFormat extends AbstractOutputFormat {
   private final BeeLine beeLine;
   private final StringBuilder buf = new StringBuilder();

   XMLAttributeOutputFormat(BeeLine beeLine) {
      this.beeLine = beeLine;
   }

   public void printHeader(Rows.Row header) {
      this.beeLine.output("<resultset>");
   }

   public void printFooter(Rows.Row header) {
      this.beeLine.output("</resultset>");
   }

   public void printRow(Rows rows, Rows.Row header, Rows.Row row) {
      String[] head = header.values;
      String[] vals = row.values;
      this.buf.setLength(0);
      this.buf.append("  <result");

      for(int i = 0; i < head.length && i < vals.length; ++i) {
         this.buf.append(' ').append(head[i]).append("=\"").append(BeeLine.xmlattrencode(vals[i])).append('"');
      }

      this.buf.append("/>");
      this.beeLine.output(this.buf.toString());
   }
}
