package com.esotericsoftware.kryo.serializers;

import java.lang.reflect.Field;

class AsmCachedFieldFactory implements FieldSerializer.CachedFieldFactory {
   public FieldSerializer.CachedField createCachedField(Class fieldClass, Field field, FieldSerializer ser) {
      FieldSerializer.CachedField cachedField;
      if (fieldClass.isPrimitive()) {
         if (fieldClass == Boolean.TYPE) {
            cachedField = new AsmCacheFields.AsmBooleanField();
         } else if (fieldClass == Byte.TYPE) {
            cachedField = new AsmCacheFields.AsmByteField();
         } else if (fieldClass == Character.TYPE) {
            cachedField = new AsmCacheFields.AsmCharField();
         } else if (fieldClass == Short.TYPE) {
            cachedField = new AsmCacheFields.AsmShortField();
         } else if (fieldClass == Integer.TYPE) {
            cachedField = new AsmCacheFields.AsmIntField();
         } else if (fieldClass == Long.TYPE) {
            cachedField = new AsmCacheFields.AsmLongField();
         } else if (fieldClass == Float.TYPE) {
            cachedField = new AsmCacheFields.AsmFloatField();
         } else if (fieldClass == Double.TYPE) {
            cachedField = new AsmCacheFields.AsmDoubleField();
         } else {
            cachedField = new AsmCacheFields.AsmObjectField(ser);
         }
      } else if (fieldClass != String.class || ser.kryo.getReferences() && ser.kryo.getReferenceResolver().useReferences(String.class)) {
         cachedField = new AsmCacheFields.AsmObjectField(ser);
      } else {
         cachedField = new AsmCacheFields.AsmStringField();
      }

      return cachedField;
   }
}
