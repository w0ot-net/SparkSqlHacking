package com.univocity.parsers.common.processor.core;

import java.util.List;
import java.util.Map;

interface ColumnReader {
   String[] getHeaders();

   List getColumnValuesAsList();

   void putColumnValuesInMapOfNames(Map var1);

   void putColumnValuesInMapOfIndexes(Map var1);

   Map getColumnValuesAsMapOfNames();

   Map getColumnValuesAsMapOfIndexes();

   List getColumn(String var1);

   List getColumn(int var1);
}
