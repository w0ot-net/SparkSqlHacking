package org.apache.hive.beeline;

import com.google.common.base.Optional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

public class IncrementalRowsWithNormalization extends IncrementalRows {
   private final int incrementalBufferRows;
   private BufferedRows buffer;

   IncrementalRowsWithNormalization(BeeLine beeLine, ResultSet rs) throws SQLException {
      super(beeLine, rs);
      this.incrementalBufferRows = beeLine.getOpts().getIncrementalBufferRows();
      this.buffer = new BufferedRows(beeLine, rs, Optional.of(this.incrementalBufferRows));
      this.buffer.normalizeWidths();
   }

   public boolean hasNext() {
      try {
         if (this.buffer.hasNext()) {
            return true;
         } else {
            this.buffer = new BufferedRows(this.beeLine, this.rs, Optional.of(this.incrementalBufferRows));
            if (this.normalizingWidths) {
               this.buffer.normalizeWidths();
            }

            if (!this.buffer.hasNext()) {
               return false;
            } else {
               this.buffer.next();
               return this.buffer.hasNext();
            }
         }
      } catch (SQLException ex) {
         throw new RuntimeException(ex.toString());
      }
   }

   public Object next() {
      if (!this.hasNext()) {
         throw new NoSuchElementException();
      } else {
         return this.buffer.next();
      }
   }
}
