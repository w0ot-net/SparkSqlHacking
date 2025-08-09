package org.apache.hive.beeline;

class TableOutputFormat implements OutputFormat {
   private final BeeLine beeLine;
   private final StringBuilder sb = new StringBuilder();

   TableOutputFormat(BeeLine beeLine) {
      this.beeLine = beeLine;
   }

   public int print(Rows rows) {
      int index = 0;
      ColorBuffer header = null;
      ColorBuffer headerCols = null;
      int width = this.beeLine.getOpts().getMaxWidth() - 4;
      rows.normalizeWidths();

      for(; rows.hasNext(); ++index) {
         Rows.Row row = (Rows.Row)rows.next();
         ColorBuffer cbuf = this.getOutputString(rows, row);
         if (this.beeLine.getOpts().getTruncateTable()) {
            cbuf = cbuf.truncate(width);
         }

         if (index == 0) {
            this.sb.setLength(0);

            for(int j = 0; j < row.sizes.length; ++j) {
               for(int k = 0; k < row.sizes[j]; ++k) {
                  this.sb.append('-');
               }

               if (j < row.sizes.length - 1) {
                  this.sb.append("-+-");
               }
            }

            headerCols = cbuf;
            header = this.beeLine.getColorBuffer().green(this.sb.toString());
            if (this.beeLine.getOpts().getTruncateTable()) {
               header = header.truncate(cbuf.getVisibleLength());
            }
         }

         if (this.beeLine.getOpts().getShowHeader()) {
            if (index == 0 || index - 1 > 0 && (index - 1) % this.beeLine.getOpts().getHeaderInterval() == 0) {
               this.printRow(header, true);
               this.printRow(headerCols, false);
               this.printRow(header, true);
            }
         } else if (index == 0) {
            this.printRow(header, true);
         }

         if (index != 0) {
            this.printRow(cbuf, false);
         }
      }

      if (header != null) {
         this.printRow(header, true);
      }

      return index - 1;
   }

   void printRow(ColorBuffer cbuff, boolean header) {
      if (header) {
         this.beeLine.output(this.beeLine.getColorBuffer().green("+-").append(cbuff).green("-+"));
      } else {
         this.beeLine.output(this.beeLine.getColorBuffer().green("| ").append(cbuff).green(" |"));
      }

   }

   public ColorBuffer getOutputString(Rows rows, Rows.Row row) {
      return this.getOutputString(rows, row, " | ");
   }

   ColorBuffer getOutputString(Rows rows, Rows.Row row, String delim) {
      ColorBuffer buf = this.beeLine.getColorBuffer();

      for(int i = 0; i < row.values.length; ++i) {
         if (buf.getVisibleLength() > 0) {
            buf.green(delim);
         }

         if (row.isMeta) {
            ColorBuffer v = this.beeLine.getColorBuffer().center(row.values[i], row.sizes[i]);
            if (rows.isPrimaryKey(i)) {
               buf.cyan(v.getMono());
            } else {
               buf.bold(v.getMono());
            }
         } else {
            ColorBuffer v = this.beeLine.getColorBuffer().pad(row.values[i], row.sizes[i]);
            if (rows.isPrimaryKey(i)) {
               buf.cyan(v.getMono());
            } else {
               buf.append(v.getMono());
            }
         }
      }

      if (row.deleted) {
         buf = this.beeLine.getColorBuffer().red(buf.getMono());
      } else if (row.updated) {
         buf = this.beeLine.getColorBuffer().blue(buf.getMono());
      } else if (row.inserted) {
         buf = this.beeLine.getColorBuffer().green(buf.getMono());
      }

      return buf;
   }
}
