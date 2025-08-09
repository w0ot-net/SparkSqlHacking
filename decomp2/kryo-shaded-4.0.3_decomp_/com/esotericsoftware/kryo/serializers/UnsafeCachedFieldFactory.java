package com.esotericsoftware.kryo.serializers;

import java.lang.reflect.Field;

class UnsafeCachedFieldFactory implements FieldSerializer.CachedFieldFactory {
   public FieldSerializer.CachedField createCachedField(Class fieldClass, Field field, FieldSerializer ser) {
      FieldSerializer.CachedField cachedField;
      if (fieldClass.isPrimitive()) {
         if (fieldClass == Boolean.TYPE) {
            cachedField = new UnsafeCacheFields.UnsafeBooleanField(field);
         } else if (fieldClass == Byte.TYPE) {
            cachedField = new UnsafeCacheFields.UnsafeByteField(field);
         } else if (fieldClass == Character.TYPE) {
            cachedField = new UnsafeCacheFields.UnsafeCharField(field);
         } else if (fieldClass == Short.TYPE) {
            cachedField = new UnsafeCacheFields.UnsafeShortField(field);
         } else if (fieldClass == Integer.TYPE) {
            cachedField = new UnsafeCacheFields.UnsafeIntField(field);
         } else if (fieldClass == Long.TYPE) {
            cachedField = new UnsafeCacheFields.UnsafeLongField(field);
         } else if (fieldClass == Float.TYPE) {
            cachedField = new UnsafeCacheFields.UnsafeFloatField(field);
         } else if (fieldClass == Double.TYPE) {
            cachedField = new UnsafeCacheFields.UnsafeDoubleField(field);
         } else {
            cachedField = new UnsafeCacheFields.UnsafeObjectField(ser);
         }
      } else if (fieldClass != String.class || ser.kryo.getReferences() && ser.kryo.getReferenceResolver().useReferences(String.class)) {
         cachedField = new UnsafeCacheFields.UnsafeObjectField(ser);
      } else {
         cachedField = new UnsafeCacheFields.UnsafeStringField(field);
      }

      return cachedField;
   }
}
