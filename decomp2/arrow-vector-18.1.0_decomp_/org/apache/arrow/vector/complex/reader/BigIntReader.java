package org.apache.arrow.vector.complex.reader;

import org.apache.arrow.vector.complex.writer.BigIntWriter;
import org.apache.arrow.vector.holders.BigIntHolder;
import org.apache.arrow.vector.holders.NullableBigIntHolder;

public interface BigIntReader extends BaseReader {
   void read(BigIntHolder var1);

   void read(NullableBigIntHolder var1);

   Object readObject();

   Long readLong();

   boolean isSet();

   void copyAsValue(BigIntWriter var1);

   void copyAsField(String var1, BigIntWriter var2);
}
