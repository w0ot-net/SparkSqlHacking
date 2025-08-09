package com.univocity.parsers.common.fields;

import com.univocity.parsers.common.NormalizedString;

public interface FieldSelector extends Cloneable {
   int[] getFieldIndexes(String[] var1);

   int[] getFieldIndexes(NormalizedString[] var1);

   String describe();

   Object clone();
}
