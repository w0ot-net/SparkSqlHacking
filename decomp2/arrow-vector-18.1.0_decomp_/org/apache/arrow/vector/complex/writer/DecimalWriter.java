package org.apache.arrow.vector.complex.writer;

import java.math.BigDecimal;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.vector.holders.DecimalHolder;
import org.apache.arrow.vector.types.pojo.ArrowType;

public interface DecimalWriter extends BaseWriter {
   void write(DecimalHolder var1);

   /** @deprecated */
   @Deprecated
   void writeDecimal(long var1, ArrowBuf var3);

   void writeDecimal(long var1, ArrowBuf var3, ArrowType var4);

   void writeDecimal(BigDecimal var1);

   void writeBigEndianBytesToDecimal(byte[] var1, ArrowType var2);

   /** @deprecated */
   @Deprecated
   void writeBigEndianBytesToDecimal(byte[] var1);
}
