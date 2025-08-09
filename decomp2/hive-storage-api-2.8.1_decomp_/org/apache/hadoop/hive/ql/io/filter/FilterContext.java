package org.apache.hadoop.hive.ql.io.filter;

public interface FilterContext {
   void reset();

   boolean isSelectedInUse();

   int[] getSelected();

   int getSelectedSize();
}
