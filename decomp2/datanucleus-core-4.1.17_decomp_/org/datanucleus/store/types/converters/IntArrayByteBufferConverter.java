package org.datanucleus.store.types.converters;

import java.nio.ByteBuffer;
import org.datanucleus.util.TypeConversionHelper;

public class IntArrayByteBufferConverter implements TypeConverter {
   private static final long serialVersionUID = -426707949213710681L;

   public ByteBuffer toDatastoreType(int[] memberValue) {
      if (memberValue == null) {
         return null;
      } else {
         byte[] bytes = TypeConversionHelper.getByteArrayFromIntArray(memberValue);
         ByteBuffer byteBuffer = ByteBuffer.allocate(memberValue.length);
         byteBuffer.put(bytes);
         return byteBuffer;
      }
   }

   public int[] toMemberType(ByteBuffer datastoreValue) {
      return datastoreValue == null ? null : TypeConversionHelper.getIntArrayFromByteArray(datastoreValue.array());
   }
}
