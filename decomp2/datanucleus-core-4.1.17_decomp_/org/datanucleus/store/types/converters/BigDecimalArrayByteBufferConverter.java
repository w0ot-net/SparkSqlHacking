package org.datanucleus.store.types.converters;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import org.datanucleus.util.TypeConversionHelper;

public class BigDecimalArrayByteBufferConverter implements TypeConverter {
   private static final long serialVersionUID = 5829673311829818607L;

   public ByteBuffer toDatastoreType(BigDecimal[] memberValue) {
      if (memberValue == null) {
         return null;
      } else {
         byte[] bytes = TypeConversionHelper.getByteArrayFromBigDecimalArray(memberValue);
         ByteBuffer byteBuffer = ByteBuffer.allocate(memberValue.length);
         byteBuffer.put(bytes);
         return byteBuffer;
      }
   }

   public BigDecimal[] toMemberType(ByteBuffer datastoreValue) {
      return datastoreValue == null ? null : TypeConversionHelper.getBigDecimalArrayFromByteArray(datastoreValue.array());
   }
}
