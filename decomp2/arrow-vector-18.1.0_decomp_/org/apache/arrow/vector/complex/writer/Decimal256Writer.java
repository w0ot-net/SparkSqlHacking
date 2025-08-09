package org.apache.arrow.vector.complex.writer;

import java.math.BigDecimal;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.vector.holders.Decimal256Holder;
import org.apache.arrow.vector.types.pojo.ArrowType;

public interface Decimal256Writer extends BaseWriter {
   void write(Decimal256Holder var1);

   /** @deprecated */
   @Deprecated
   void writeDecimal256(long var1, ArrowBuf var3);

   void writeDecimal256(long var1, ArrowBuf var3, ArrowType var4);

   void writeDecimal256(BigDecimal var1);

   void writeBigEndianBytesToDecimal256(byte[] var1, ArrowType var2);

   /** @deprecated */
   @Deprecated
   void writeBigEndianBytesToDecimal256(byte[] var1);
}
