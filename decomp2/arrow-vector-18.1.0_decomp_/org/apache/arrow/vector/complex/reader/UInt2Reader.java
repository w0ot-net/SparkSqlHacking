package org.apache.arrow.vector.complex.reader;

import org.apache.arrow.vector.complex.writer.UInt2Writer;
import org.apache.arrow.vector.holders.NullableUInt2Holder;
import org.apache.arrow.vector.holders.UInt2Holder;

public interface UInt2Reader extends BaseReader {
   void read(UInt2Holder var1);

   void read(NullableUInt2Holder var1);

   Object readObject();

   Character readCharacter();

   boolean isSet();

   void copyAsValue(UInt2Writer var1);

   void copyAsField(String var1, UInt2Writer var2);
}
