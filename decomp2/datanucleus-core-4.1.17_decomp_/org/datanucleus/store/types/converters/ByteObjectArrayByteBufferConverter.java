package org.datanucleus.store.types.converters;

import java.nio.ByteBuffer;
import org.datanucleus.util.TypeConversionHelper;

public class ByteObjectArrayByteBufferConverter implements TypeConverter {
   private static final long serialVersionUID = -6958778367541419692L;

   public ByteBuffer toDatastoreType(Byte[] memberValue) {
      if (memberValue == null) {
         return null;
      } else {
         byte[] bytes = TypeConversionHelper.getByteArrayFromByteObjectArray(memberValue);
         ByteBuffer byteBuffer = ByteBuffer.allocate(memberValue.length);
         byteBuffer.put(bytes);
         return byteBuffer;
      }
   }

   public Byte[] toMemberType(ByteBuffer datastoreValue) {
      return datastoreValue == null ? null : TypeConversionHelper.getByteObjectArrayFromByteArray(datastoreValue.array());
   }
}
