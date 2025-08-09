package org.apache.arrow.vector.complex.reader;

import org.apache.arrow.vector.complex.writer.Float2Writer;
import org.apache.arrow.vector.holders.Float2Holder;
import org.apache.arrow.vector.holders.NullableFloat2Holder;

public interface Float2Reader extends BaseReader {
   void read(Float2Holder var1);

   void read(NullableFloat2Holder var1);

   Object readObject();

   Short readShort();

   boolean isSet();

   void copyAsValue(Float2Writer var1);

   void copyAsField(String var1, Float2Writer var2);
}
