package org.apache.hadoop.hive.serde2;

import java.util.ArrayList;

public class ColumnSet {
   public ArrayList col;

   public ColumnSet() {
   }

   public ColumnSet(ArrayList col) {
      this();
      this.col = col;
   }

   public String toString() {
      return this.col.toString();
   }
}
