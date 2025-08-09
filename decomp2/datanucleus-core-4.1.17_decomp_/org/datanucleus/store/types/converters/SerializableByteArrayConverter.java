package org.datanucleus.store.types.converters;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.datanucleus.exceptions.NucleusException;

public class SerializableByteArrayConverter implements TypeConverter {
   private static final long serialVersionUID = 6620126332595722119L;

   public byte[] toDatastoreType(Serializable memberValue) {
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

         return bytes;
      } catch (IOException ioe) {
         throw new NucleusException("Error serialising object of type " + memberValue.getClass().getName() + " to byte[]", ioe);
      }
   }

   public Serializable toMemberType(byte[] datastoreValue) {
      Serializable obj = null;
      ByteArrayInputStream bais = new ByteArrayInputStream(datastoreValue);
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
   }
}
