package org.datanucleus.store.types.converters;

import java.nio.ByteBuffer;
import org.datanucleus.util.TypeConversionHelper;

public class DoubleArrayByteBufferConverter implements TypeConverter {
   private static final long serialVersionUID = 2169191689430539326L;

   public ByteBuffer toDatastoreType(double[] memberValue) {
      if (memberValue == null) {
         return null;
      } else {
         byte[] bytes = TypeConversionHelper.getByteArrayFromDoubleArray(memberValue);
         ByteBuffer byteBuffer = ByteBuffer.allocate(memberValue.length);
         byteBuffer.put(bytes);
         return byteBuffer;
      }
   }

   public double[] toMemberType(ByteBuffer datastoreValue) {
      return datastoreValue == null ? null : TypeConversionHelper.getDoubleArrayFromByteArray(datastoreValue.array());
   }
}
