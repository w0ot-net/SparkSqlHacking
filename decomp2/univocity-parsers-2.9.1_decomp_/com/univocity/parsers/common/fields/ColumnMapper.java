package com.univocity.parsers.common.fields;

import java.util.Map;

public interface ColumnMapper extends Cloneable {
   void attributeToColumnName(String var1, String var2);

   void attributeToColumn(String var1, Enum var2);

   void attributeToIndex(String var1, int var2);

   void attributesToColumnNames(Map var1);

   void attributesToColumns(Map var1);

   void attributesToIndexes(Map var1);

   void methodToColumnName(String var1, Class var2, String var3);

   void methodToColumn(String var1, Class var2, Enum var3);

   void methodToIndex(String var1, Class var2, int var3);

   void methodToColumnName(String var1, String var2);

   void methodToColumn(String var1, Enum var2);

   void methodToIndex(String var1, int var2);

   void methodsToColumnNames(Map var1);

   void methodsToColumns(Map var1);

   void methodsToIndexes(Map var1);

   ColumnMapper clone();

   void remove(String var1);
}
