package com.esotericsoftware.kryo.serializers;

import java.lang.reflect.Field;

class ObjectCachedFieldFactory implements FieldSerializer.CachedFieldFactory {
   public FieldSerializer.CachedField createCachedField(Class fieldClass, Field field, FieldSerializer ser) {
      FieldSerializer.CachedField cachedField;
      if (fieldClass.isPrimitive()) {
         if (fieldClass == Boolean.TYPE) {
            cachedField = new ObjectField.ObjectBooleanField(ser);
         } else if (fieldClass == Byte.TYPE) {
            cachedField = new ObjectField.ObjectByteField(ser);
         } else if (fieldClass == Character.TYPE) {
            cachedField = new ObjectField.ObjectCharField(ser);
         } else if (fieldClass == Short.TYPE) {
            cachedField = new ObjectField.ObjectShortField(ser);
         } else if (fieldClass == Integer.TYPE) {
            cachedField = new ObjectField.ObjectIntField(ser);
         } else if (fieldClass == Long.TYPE) {
            cachedField = new ObjectField.ObjectLongField(ser);
         } else if (fieldClass == Float.TYPE) {
            cachedField = new ObjectField.ObjectFloatField(ser);
         } else if (fieldClass == Double.TYPE) {
            cachedField = new ObjectField.ObjectDoubleField(ser);
         } else {
            cachedField = new ObjectField(ser);
         }
      } else {
         cachedField = new ObjectField(ser);
      }

      return cachedField;
   }
}
