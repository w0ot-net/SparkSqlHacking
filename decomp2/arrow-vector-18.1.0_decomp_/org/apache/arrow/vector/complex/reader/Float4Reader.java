package org.apache.arrow.vector.complex.reader;

import org.apache.arrow.vector.complex.writer.Float4Writer;
import org.apache.arrow.vector.holders.Float4Holder;
import org.apache.arrow.vector.holders.NullableFloat4Holder;

public interface Float4Reader extends BaseReader {
   void read(Float4Holder var1);

   void read(NullableFloat4Holder var1);

   Object readObject();

   Float readFloat();

   boolean isSet();

   void copyAsValue(Float4Writer var1);

   void copyAsField(String var1, Float4Writer var2);
}
