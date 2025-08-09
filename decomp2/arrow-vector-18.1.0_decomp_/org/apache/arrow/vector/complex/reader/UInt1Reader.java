package org.apache.arrow.vector.complex.reader;

import org.apache.arrow.vector.complex.writer.UInt1Writer;
import org.apache.arrow.vector.holders.NullableUInt1Holder;
import org.apache.arrow.vector.holders.UInt1Holder;

public interface UInt1Reader extends BaseReader {
   void read(UInt1Holder var1);

   void read(NullableUInt1Holder var1);

   Object readObject();

   Byte readByte();

   boolean isSet();

   void copyAsValue(UInt1Writer var1);

   void copyAsField(String var1, UInt1Writer var2);
}
