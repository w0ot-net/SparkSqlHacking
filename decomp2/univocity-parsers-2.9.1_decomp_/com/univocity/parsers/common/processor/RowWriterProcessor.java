package com.univocity.parsers.common.processor;

import com.univocity.parsers.common.NormalizedString;

public interface RowWriterProcessor {
   Object[] write(Object var1, NormalizedString[] var2, int[] var3);
}
