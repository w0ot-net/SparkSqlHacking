package org.datanucleus.store.types.converters;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.util.Base64;

public class SerializableStringConverter implements TypeConverter {
   private static final long serialVersionUID = 5269636067035783545L;

   public String toDatastoreType(Serializable memberValue) {
      String str = null;
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ObjectOutputStream oos = null;

      try {
         try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(memberValue);
            str = new String(Base64.encode(baos.toByteArray()));
         } finally {
            try {
               baos.close();
            } finally {
               if (oos != null) {
                  oos.close();
               }

            }
         }

         return str;
      } catch (IOException ioe) {
         throw new NucleusException("Error serialising object of type " + memberValue.getClass().getName() + " to String", ioe);
      }
   }

   public Serializable toMemberType(String datastoreValue) {
      byte[] bytes = Base64.decode(datastoreValue);
      Serializable obj = null;
      ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
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
