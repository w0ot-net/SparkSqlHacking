package org.apache.derby.catalog;

public interface IndexDescriptor {
   boolean isUnique();

   boolean isUniqueWithDuplicateNulls();

   boolean isUniqueDeferrable();

   boolean hasDeferrableChecking();

   int[] baseColumnPositions();

   int getKeyColumnPosition(int var1);

   int numberOfOrderedColumns();

   String indexType();

   boolean[] isAscending();

   boolean isAscending(Integer var1);

   boolean isDescending(Integer var1);

   void setBaseColumnPositions(int[] var1);

   void setIsAscending(boolean[] var1);

   void setNumberOfOrderedColumns(int var1);
}
