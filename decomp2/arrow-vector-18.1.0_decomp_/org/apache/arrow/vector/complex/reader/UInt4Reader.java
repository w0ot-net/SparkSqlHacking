package org.apache.arrow.vector.complex.reader;

import org.apache.arrow.vector.complex.writer.UInt4Writer;
import org.apache.arrow.vector.holders.NullableUInt4Holder;
import org.apache.arrow.vector.holders.UInt4Holder;

public interface UInt4Reader extends BaseReader {
   void read(UInt4Holder var1);

   void read(NullableUInt4Holder var1);

   Object readObject();

   Integer readInteger();

   boolean isSet();

   void copyAsValue(UInt4Writer var1);

   void copyAsField(String var1, UInt4Writer var2);
}
