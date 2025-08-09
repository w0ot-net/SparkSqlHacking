package com.univocity.parsers.common.record;

import com.univocity.parsers.common.fields.FieldSet;
import com.univocity.parsers.conversions.Conversion;

public interface RecordMetaData {
   int indexOf(Enum var1);

   int indexOf(String var1);

   Class typeOf(Enum var1);

   Class typeOf(String var1);

   Class typeOf(int var1);

   void setTypeOfColumns(Class var1, Enum... var2);

   void setTypeOfColumns(Class var1, String... var2);

   void setTypeOfColumns(Class var1, int... var2);

   void setDefaultValueOfColumns(Object var1, Enum... var2);

   void setDefaultValueOfColumns(Object var1, String... var2);

   void setDefaultValueOfColumns(Object var1, int... var2);

   Object defaultValueOf(Enum var1);

   Object defaultValueOf(String var1);

   Object defaultValueOf(int var1);

   FieldSet convertFields(Class var1, Conversion... var2);

   FieldSet convertFields(Conversion... var1);

   FieldSet convertIndexes(Conversion... var1);

   String[] headers();

   String[] selectedHeaders();

   boolean containsColumn(String var1);
}
