package org.datanucleus.store.types.converters;

import java.nio.ByteBuffer;
import org.datanucleus.util.TypeConversionHelper;

public class BooleanArrayByteBufferConverter implements TypeConverter {
   private static final long serialVersionUID = -1034562477183576606L;

   public ByteBuffer toDatastoreType(boolean[] memberValue) {
      if (memberValue == null) {
         return null;
      } else {
         byte[] bytes = TypeConversionHelper.getByteArrayFromBooleanArray(memberValue);
         ByteBuffer byteBuffer = ByteBuffer.allocate(memberValue.length);
         byteBuffer.put(bytes);
         return byteBuffer;
      }
   }

   public boolean[] toMemberType(ByteBuffer datastoreValue) {
      return datastoreValue == null ? null : TypeConversionHelper.getBooleanArrayFromByteArray(datastoreValue.array());
   }
}
