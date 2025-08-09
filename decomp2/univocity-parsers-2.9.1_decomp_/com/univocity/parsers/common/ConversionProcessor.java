package com.univocity.parsers.common;

import com.univocity.parsers.common.fields.FieldSet;
import com.univocity.parsers.conversions.Conversion;

public interface ConversionProcessor {
   FieldSet convertIndexes(Conversion... var1);

   void convertAll(Conversion... var1);

   FieldSet convertFields(Conversion... var1);

   void convertType(Class var1, Conversion... var2);
}
