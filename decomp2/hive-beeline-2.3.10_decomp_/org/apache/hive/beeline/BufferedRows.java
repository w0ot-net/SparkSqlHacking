package org.apache.hive.beeline;

import com.google.common.base.Optional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;

class BufferedRows extends Rows {
   private final LinkedList list;
   private final Iterator iterator;
   private int maxColumnWidth;

   BufferedRows(BeeLine beeLine, ResultSet rs) throws SQLException {
      this(beeLine, rs, Optional.absent());
   }

   BufferedRows(BeeLine beeLine, ResultSet rs, Optional limit) throws SQLException {
      super(beeLine, rs);
      this.list = new LinkedList();
      int count = this.rsMeta.getColumnCount();
      this.list.add(new Rows.Row(count));
      int numRowsBuffered = 0;
      if (limit.isPresent()) {
         while((Integer)limit.get() > numRowsBuffered && rs.next()) {
            this.list.add(new Rows.Row(count, rs));
            ++numRowsBuffered;
         }
      } else {
         while(rs.next()) {
            this.list.add(new Rows.Row(count, rs));
         }
      }

      this.iterator = this.list.iterator();
      this.maxColumnWidth = beeLine.getOpts().getMaxColumnWidth();
   }

   public boolean hasNext() {
      return this.iterator.hasNext();
   }

   public Object next() {
      return this.iterator.next();
   }

   public String toString() {
      return this.list.toString();
   }

   void normalizeWidths() {
      int[] max = null;

      for(Rows.Row row : this.list) {
         if (max == null) {
            max = new int[row.values.length];
         }

         for(int j = 0; j < max.length; ++j) {
            max[j] = Math.min(Math.max(max[j], row.sizes[j] + 1), this.maxColumnWidth);
         }
      }

      for(Rows.Row row : this.list) {
         row.sizes = max;
      }

   }
}
