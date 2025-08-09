package org.apache.arrow.vector.complex.reader;

import java.math.BigDecimal;
import org.apache.arrow.vector.complex.writer.DecimalWriter;
import org.apache.arrow.vector.holders.DecimalHolder;
import org.apache.arrow.vector.holders.NullableDecimalHolder;

public interface DecimalReader extends BaseReader {
   void read(DecimalHolder var1);

   void read(NullableDecimalHolder var1);

   Object readObject();

   BigDecimal readBigDecimal();

   boolean isSet();

   void copyAsValue(DecimalWriter var1);

   void copyAsField(String var1, DecimalWriter var2);
}
