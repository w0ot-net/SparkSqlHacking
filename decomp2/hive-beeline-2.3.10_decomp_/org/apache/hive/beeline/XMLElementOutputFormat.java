package org.apache.hive.beeline;

class XMLElementOutputFormat extends AbstractOutputFormat {
   private final BeeLine beeLine;

   XMLElementOutputFormat(BeeLine beeLine) {
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
      this.beeLine.output("  <result>");

      for(int i = 0; i < head.length && i < vals.length; ++i) {
         this.beeLine.output("    <" + head[i] + ">" + BeeLine.xmlattrencode(vals[i]) + "</" + head[i] + ">");
      }

      this.beeLine.output("  </result>");
   }
}
