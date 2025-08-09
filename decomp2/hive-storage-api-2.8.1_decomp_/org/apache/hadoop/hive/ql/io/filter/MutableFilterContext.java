package org.apache.hadoop.hive.ql.io.filter;

public interface MutableFilterContext extends FilterContext {
   void setFilterContext(boolean var1, int[] var2, int var3);

   boolean validateSelected();

   int[] updateSelected(int var1);

   default FilterContext immutable() {
      return this;
   }

   void setSelectedInUse(boolean var1);

   void setSelected(int[] var1);

   void setSelectedSize(int var1);
}
