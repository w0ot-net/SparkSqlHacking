package org.datanucleus.store.types.converters;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import org.datanucleus.exceptions.NucleusException;

public class SerializableByteBufferConverter implements TypeConverter {
   private static final long serialVersionUID = 585211414298721468L;

   public ByteBuffer toDatastoreType(Serializable memberValue) {
      if (memberValue == null) {
         return null;
      } else {
         byte[] bytes = null;
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         ObjectOutputStream oos = null;

         try {
            try {
               oos = new ObjectOutputStream(baos);
               oos.writeObject(memberValue);
               bytes = baos.toByteArray();
            } finally {
               try {
                  baos.close();
               } finally {
                  if (oos != null) {
                     oos.close();
                  }

               }
            }
         } catch (IOException ioe) {
            throw new NucleusException("Error serialising object of type " + memberValue.getClass().getName() + " to ByteBuffer", ioe);
         }

         return ByteBuffer.wrap(bytes);
      }
   }

   public Serializable toMemberType(ByteBuffer datastoreValue) {
      if (datastoreValue != null && datastoreValue.limit() != 0) {
         Serializable obj = null;
         byte[] dataStoreValueInBytes = new byte[datastoreValue.remaining()];
         datastoreValue.get(dataStoreValueInBytes);
         ByteArrayInputStream bais = new ByteArrayInputStream(dataStoreValueInBytes);
         ObjectInputStream ois = null;

         try {
            try {
               ois = new ObjectInputStream(bais);
               obj = (Serializable)ois.readObject();
            } finally {
               try {
                  bais.close();
               } finally {
                  if (ois != null) {
                     ois.close();
                  }

               }
            }

            return obj;
         } catch (Exception e) {
            throw new NucleusException("Error deserialising " + datastoreValue, e);
         }
      } else {
         return null;
      }
   }
}
