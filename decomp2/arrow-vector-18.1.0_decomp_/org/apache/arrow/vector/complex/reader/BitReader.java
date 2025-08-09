package org.apache.arrow.vector.complex.reader;

import org.apache.arrow.vector.complex.writer.BitWriter;
import org.apache.arrow.vector.holders.BitHolder;
import org.apache.arrow.vector.holders.NullableBitHolder;

public interface BitReader extends BaseReader {
   void read(BitHolder var1);

   void read(NullableBitHolder var1);

   Object readObject();

   Boolean readBoolean();

   boolean isSet();

   void copyAsValue(BitWriter var1);

   void copyAsField(String var1, BitWriter var2);
}
