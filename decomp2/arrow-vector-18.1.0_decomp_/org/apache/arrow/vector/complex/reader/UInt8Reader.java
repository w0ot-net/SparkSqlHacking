package org.apache.arrow.vector.complex.reader;

import org.apache.arrow.vector.complex.writer.UInt8Writer;
import org.apache.arrow.vector.holders.NullableUInt8Holder;
import org.apache.arrow.vector.holders.UInt8Holder;

public interface UInt8Reader extends BaseReader {
   void read(UInt8Holder var1);

   void read(NullableUInt8Holder var1);

   Object readObject();

   Long readLong();

   boolean isSet();

   void copyAsValue(UInt8Writer var1);

   void copyAsField(String var1, UInt8Writer var2);
}
