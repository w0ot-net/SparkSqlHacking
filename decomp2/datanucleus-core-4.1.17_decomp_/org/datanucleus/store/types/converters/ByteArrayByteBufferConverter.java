package org.datanucleus.store.types.converters;

import java.nio.ByteBuffer;

public class ByteArrayByteBufferConverter implements TypeConverter {
   private static final long serialVersionUID = 6497939627582849065L;

   public ByteBuffer toDatastoreType(byte[] memberValue) {
      return memberValue == null ? null : ByteBuffer.wrap(memberValue);
   }

   public byte[] toMemberType(ByteBuffer datastoreValue) {
      if (datastoreValue == null) {
         return null;
      } else {
         byte[] dataStoreValueInBytes = new byte[datastoreValue.remaining()];
         datastoreValue.get(dataStoreValueInBytes);
         return dataStoreValueInBytes;
      }
   }
}
