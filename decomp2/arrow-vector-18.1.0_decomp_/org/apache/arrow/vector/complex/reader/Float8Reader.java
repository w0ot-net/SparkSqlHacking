package org.apache.arrow.vector.complex.reader;

import org.apache.arrow.vector.complex.writer.Float8Writer;
import org.apache.arrow.vector.holders.Float8Holder;
import org.apache.arrow.vector.holders.NullableFloat8Holder;

public interface Float8Reader extends BaseReader {
   void read(Float8Holder var1);

   void read(NullableFloat8Holder var1);

   Object readObject();

   Double readDouble();

   boolean isSet();

   void copyAsValue(Float8Writer var1);

   void copyAsField(String var1, Float8Writer var2);
}
