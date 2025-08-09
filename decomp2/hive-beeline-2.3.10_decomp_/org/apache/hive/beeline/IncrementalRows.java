package org.apache.hive.beeline;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

public class IncrementalRows extends Rows {
   protected final ResultSet rs;
   private final Rows.Row labelRow;
   private final Rows.Row maxRow;
   private Rows.Row nextRow;
   private boolean endOfResult;
   protected boolean normalizingWidths;

   IncrementalRows(BeeLine beeLine, ResultSet rs) throws SQLException {
      super(beeLine, rs);
      this.rs = rs;
      this.labelRow = new Rows.Row(this.rsMeta.getColumnCount());
      this.maxRow = new Rows.Row(this.rsMeta.getColumnCount());
      int maxWidth = beeLine.getOpts().getMaxColumnWidth();

      for(int i = 0; i < this.maxRow.sizes.length; ++i) {
         this.maxRow.sizes[i] = Math.max(this.maxRow.sizes[i], this.rsMeta.getColumnDisplaySize(i + 1));
         this.maxRow.sizes[i] = Math.min(maxWidth, this.maxRow.sizes[i]);
      }

      this.nextRow = this.labelRow;
      this.endOfResult = false;
   }

   public boolean hasNext() {
      if (this.endOfResult) {
         return false;
      } else {
         if (this.nextRow == null) {
            try {
               if (this.rs.next()) {
                  this.nextRow = new Rows.Row(this.labelRow.sizes.length, this.rs);
                  if (this.normalizingWidths) {
                     this.nextRow.sizes = this.labelRow.sizes;
                  }
               } else {
                  this.endOfResult = true;
               }
            } catch (SQLException ex) {
               throw new RuntimeException(ex.toString());
            }
         }

         return this.nextRow != null;
      }
   }

   public Object next() {
      if (!this.hasNext()) {
         throw new NoSuchElementException();
      } else {
         Object ret = this.nextRow;
         this.nextRow = null;
         return ret;
      }
   }

   void normalizeWidths() {
      this.labelRow.sizes = this.maxRow.sizes;
      this.normalizingWidths = true;
   }
}
