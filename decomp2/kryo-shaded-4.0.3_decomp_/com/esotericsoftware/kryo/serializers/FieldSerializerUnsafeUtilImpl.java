package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.util.IntArray;
import com.esotericsoftware.kryo.util.UnsafeUtil;
import com.esotericsoftware.minlog.Log;
import com.esotericsoftware.reflectasm.FieldAccess;
import java.lang.reflect.Field;
import java.util.List;

final class FieldSerializerUnsafeUtilImpl implements FieldSerializerUnsafeUtil {
   private FieldSerializer serializer;

   public FieldSerializerUnsafeUtilImpl(FieldSerializer serializer) {
      this.serializer = serializer;
   }

   public void createUnsafeCacheFieldsAndRegions(List validFields, List cachedFields, int baseIndex, IntArray useAsm) {
      long startPrimitives = 0L;
      long endPrimitives = 0L;
      boolean lastWasPrimitive = false;
      int primitiveLength = 0;
      int lastAccessIndex = -1;
      Field lastField = null;
      long fieldOffset = -1L;
      long fieldEndOffset = -1L;
      long lastFieldEndOffset = -1L;
      int i = 0;

      for(int n = validFields.size(); i < n; ++i) {
         Field field = (Field)validFields.get(i);
         int accessIndex = -1;
         if (this.serializer.access != null && useAsm.get(baseIndex + i) == 1) {
            accessIndex = ((FieldAccess)this.serializer.access).getIndex(field.getName());
         }

         fieldOffset = UnsafeUtil.unsafe().objectFieldOffset(field);
         fieldEndOffset = fieldOffset + (long)this.fieldSizeOf(field.getType());
         if (!field.getType().isPrimitive() && lastWasPrimitive) {
            lastWasPrimitive = false;
            if (primitiveLength > 1) {
               if (Log.TRACE) {
                  Log.trace("kryo", "Class " + this.serializer.getType().getName() + ". Found a set of consecutive primitive fields. Number of fields = " + primitiveLength + ". Byte length = " + (lastFieldEndOffset - startPrimitives) + " Start offset = " + startPrimitives + " endOffset=" + lastFieldEndOffset);
               }

               FieldSerializer.CachedField cf = new UnsafeCacheFields.UnsafeRegionField(startPrimitives, lastFieldEndOffset - startPrimitives);
               cf.field = lastField;
               cachedFields.add(cf);
            } else if (lastField != null) {
               cachedFields.add(this.serializer.newCachedField(lastField, cachedFields.size(), lastAccessIndex));
            }

            cachedFields.add(this.serializer.newCachedField(field, cachedFields.size(), accessIndex));
         } else if (!field.getType().isPrimitive()) {
            cachedFields.add(this.serializer.newCachedField(field, cachedFields.size(), accessIndex));
         } else if (!lastWasPrimitive) {
            startPrimitives = fieldOffset;
            lastWasPrimitive = true;
            primitiveLength = 1;
         } else {
            ++primitiveLength;
         }

         lastAccessIndex = accessIndex;
         lastField = field;
         lastFieldEndOffset = fieldEndOffset;
      }

      if (!this.serializer.getUseAsmEnabled() && this.serializer.getUseMemRegions() && lastWasPrimitive) {
         if (primitiveLength > 1) {
            if (Log.TRACE) {
               Log.trace("kryo", "Class " + this.serializer.getType().getName() + ". Found a set of consecutive primitive fields. Number of fields = " + primitiveLength + ". Byte length = " + (lastFieldEndOffset - startPrimitives) + " Start offset = " + startPrimitives + " endOffset=" + lastFieldEndOffset);
            }

            FieldSerializer.CachedField cf = new UnsafeCacheFields.UnsafeRegionField(startPrimitives, lastFieldEndOffset - startPrimitives);
            cf.field = lastField;
            cachedFields.add(cf);
         } else if (lastField != null) {
            cachedFields.add(this.serializer.newCachedField(lastField, cachedFields.size(), lastAccessIndex));
         }
      }

   }

   private int fieldSizeOf(Class clazz) {
      if (clazz != Integer.TYPE && clazz != Float.TYPE) {
         if (clazz != Long.TYPE && clazz != Double.TYPE) {
            if (clazz != Byte.TYPE && clazz != Boolean.TYPE) {
               return clazz != Short.TYPE && clazz != Character.TYPE ? UnsafeUtil.unsafe().addressSize() : 2;
            } else {
               return 1;
            }
         } else {
            return 8;
         }
      } else {
         return 4;
      }
   }

   public long getObjectFieldOffset(Field field) {
      return UnsafeUtil.unsafe().objectFieldOffset(field);
   }
}
