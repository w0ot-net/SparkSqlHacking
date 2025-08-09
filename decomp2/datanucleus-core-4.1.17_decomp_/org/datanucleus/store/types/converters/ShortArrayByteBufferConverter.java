package org.datanucleus.store.types.converters;

import java.nio.ByteBuffer;
import org.datanucleus.util.TypeConversionHelper;

public class ShortArrayByteBufferConverter implements TypeConverter {
   private static final long serialVersionUID = -8773804855531292024L;

   public ByteBuffer toDatastoreType(short[] memberValue) {
      if (memberValue == null) {
         return null;
      } else {
         byte[] bytes = TypeConversionHelper.getByteArrayFromShortArray(memberValue);
         ByteBuffer byteBuffer = ByteBuffer.allocate(memberValue.length);
         byteBuffer.put(bytes);
         return byteBuffer;
      }
   }

   public short[] toMemberType(ByteBuffer datastoreValue) {
      return datastoreValue == null ? null : TypeConversionHelper.getShortArrayFromByteArray(datastoreValue.array());
   }
}
