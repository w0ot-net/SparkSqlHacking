package org.datanucleus.store.types.converters;

import java.nio.ByteBuffer;
import org.datanucleus.util.TypeConversionHelper;

public class FloatArrayByteBufferConverter implements TypeConverter {
   private static final long serialVersionUID = -8328547369768394603L;

   public ByteBuffer toDatastoreType(float[] memberValue) {
      if (memberValue == null) {
         return null;
      } else {
         byte[] bytes = TypeConversionHelper.getByteArrayFromFloatArray(memberValue);
         ByteBuffer byteBuffer = ByteBuffer.allocate(memberValue.length);
         byteBuffer.put(bytes);
         return byteBuffer;
      }
   }

   public float[] toMemberType(ByteBuffer datastoreValue) {
      return datastoreValue == null ? null : TypeConversionHelper.getFloatArrayFromByteArray(datastoreValue.array());
   }
}
