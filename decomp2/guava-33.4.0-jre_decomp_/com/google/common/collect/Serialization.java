package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
@J2ktIncompatible
final class Serialization {
   private Serialization() {
   }

   static int readCount(ObjectInputStream stream) throws IOException {
      return stream.readInt();
   }

   static void writeMap(Map map, ObjectOutputStream stream) throws IOException {
      stream.writeInt(map.size());

      for(Map.Entry entry : map.entrySet()) {
         stream.writeObject(entry.getKey());
         stream.writeObject(entry.getValue());
      }

   }

   static void populateMap(Map map, ObjectInputStream stream) throws IOException, ClassNotFoundException {
      int size = stream.readInt();
      populateMap(map, stream, size);
   }

   static void populateMap(Map map, ObjectInputStream stream, int size) throws IOException, ClassNotFoundException {
      for(int i = 0; i < size; ++i) {
         K key = (K)stream.readObject();
         V value = (V)stream.readObject();
         map.put(key, value);
      }

   }

   static void writeMultiset(Multiset multiset, ObjectOutputStream stream) throws IOException {
      int entryCount = multiset.entrySet().size();
      stream.writeInt(entryCount);

      for(Multiset.Entry entry : multiset.entrySet()) {
         stream.writeObject(entry.getElement());
         stream.writeInt(entry.getCount());
      }

   }

   static void populateMultiset(Multiset multiset, ObjectInputStream stream) throws IOException, ClassNotFoundException {
      int distinctElements = stream.readInt();
      populateMultiset(multiset, stream, distinctElements);
   }

   static void populateMultiset(Multiset multiset, ObjectInputStream stream, int distinctElements) throws IOException, ClassNotFoundException {
      for(int i = 0; i < distinctElements; ++i) {
         E element = (E)stream.readObject();
         int count = stream.readInt();
         multiset.add(element, count);
      }

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

   static FieldSetter getFieldSetter(Class clazz, String fieldName) {
      try {
         Field field = clazz.getDeclaredField(fieldName);
         return new FieldSetter(field);
      } catch (NoSuchFieldException e) {
         throw new AssertionError(e);
      }
   }

   static final class FieldSetter {
      private final Field field;

      private FieldSetter(Field field) {
         this.field = field;
         field.setAccessible(true);
      }

      void set(Object instance, Object value) {
         try {
            this.field.set(instance, value);
         } catch (IllegalAccessException impossible) {
            throw new AssertionError(impossible);
         }
      }

      void set(Object instance, int value) {
         try {
            this.field.set(instance, value);
         } catch (IllegalAccessException impossible) {
            throw new AssertionError(impossible);
         }
      }
   }
}
