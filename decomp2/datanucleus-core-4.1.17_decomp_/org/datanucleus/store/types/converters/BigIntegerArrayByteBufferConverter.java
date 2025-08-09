package org.datanucleus.store.types.converters;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import org.datanucleus.util.TypeConversionHelper;

public class BigIntegerArrayByteBufferConverter implements TypeConverter {
   private static final long serialVersionUID = 1225964406998563456L;

   public ByteBuffer toDatastoreType(BigInteger[] memberValue) {
      if (memberValue == null) {
         return null;
      } else {
         byte[] bytes = TypeConversionHelper.getByteArrayFromBigIntegerArray(memberValue);
         ByteBuffer byteBuffer = ByteBuffer.allocate(memberValue.length);
         byteBuffer.put(bytes);
         return byteBuffer;
      }
   }

   public BigInteger[] toMemberType(ByteBuffer datastoreValue) {
      return datastoreValue == null ? null : TypeConversionHelper.getBigIntegerArrayFromByteArray(datastoreValue.array());
   }
}
