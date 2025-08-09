package org.apache.arrow.vector.complex.reader;

import java.math.BigDecimal;
import org.apache.arrow.vector.complex.writer.Decimal256Writer;
import org.apache.arrow.vector.holders.Decimal256Holder;
import org.apache.arrow.vector.holders.NullableDecimal256Holder;

public interface Decimal256Reader extends BaseReader {
   void read(Decimal256Holder var1);

   void read(NullableDecimal256Holder var1);

   Object readObject();

   BigDecimal readBigDecimal();

   boolean isSet();

   void copyAsValue(Decimal256Writer var1);

   void copyAsField(String var1, Decimal256Writer var2);
}
