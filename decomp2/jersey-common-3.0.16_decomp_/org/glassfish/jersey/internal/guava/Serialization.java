package org.glassfish.jersey.internal.guava;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Map;

final class Serialization {
   private Serialization() {
   }

   static int readCount(ObjectInputStream stream) throws IOException {
      return stream.readInt();
   }

   static void writeMultimap(Multimap multimap, ObjectOutputStream stream) throws IOException {
      stream.writeInt(multimap.asMap().size());

      for(Map.Entry entry : multimap.asMap().entrySet()) {
         stream.writeObject(entry.getKey());
         stream.writeInt(((Collection)entry.getValue()).size());

         for(Object value : (Collection)entry.getValue()) {
            stream.writeObject(value);
         }
      }

   }

   static void populateMultimap(Multimap multimap, ObjectInputStream stream) throws IOException, ClassNotFoundException {
      int distinctKeys = stream.readInt();
      populateMultimap(multimap, stream, distinctKeys);
   }

   static void populateMultimap(Multimap multimap, ObjectInputStream stream, int distinctKeys) throws IOException, ClassNotFoundException {
      for(int i = 0; i < distinctKeys; ++i) {
         K key = (K)stream.readObject();
         Collection<V> values = multimap.get(key);
         int valueCount = stream.readInt();

         for(int j = 0; j < valueCount; ++j) {
            V value = (V)stream.readObject();
            values.add(value);
         }
      }

   }
}
