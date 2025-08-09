package org.apache.thrift.partial;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import org.apache.thrift.TBase;
import org.apache.thrift.TEnum;
import org.apache.thrift.TFieldIdEnum;

public class ThriftStructProcessor implements ThriftFieldValueProcessor {
   private static final EnumCache enums = new EnumCache();

   public Object createNewStruct(ThriftMetadata.ThriftStruct metadata) {
      return metadata.createNewStruct();
   }

   public TBase prepareStruct(Object instance) {
      return (TBase)instance;
   }

   public Object createNewList(int expectedSize) {
      return new Object[expectedSize];
   }

   public void setListElement(Object instance, int index, Object value) {
      ((Object[])instance)[index] = value;
   }

   public Object prepareList(Object instance) {
      return Arrays.asList(instance);
   }

   public Object createNewMap(int expectedSize) {
      return new HashMap(expectedSize);
   }

   public void setMapElement(Object instance, int index, Object key, Object value) {
      ((HashMap)instance).put(key, value);
   }

   public Object prepareMap(Object instance) {
      return instance;
   }

   public Object createNewSet(int expectedSize) {
      return new HashSet(expectedSize);
   }

   public void setSetElement(Object instance, int index, Object value) {
      ((HashSet)instance).add(value);
   }

   public Object prepareSet(Object instance) {
      return instance;
   }

   public Object prepareEnum(Class enumClass, int ordinal) {
      return enums.get(enumClass, ordinal);
   }

   public Object prepareString(ByteBuffer buffer) {
      return byteBufferToString(buffer);
   }

   public Object prepareBinary(ByteBuffer buffer) {
      return buffer;
   }

   public void setBool(TBase valueCollection, TFieldIdEnum fieldId, boolean value) {
      valueCollection.setFieldValue(fieldId, value);
   }

   public void setByte(TBase valueCollection, TFieldIdEnum fieldId, byte value) {
      valueCollection.setFieldValue(fieldId, value);
   }

   public void setInt16(TBase valueCollection, TFieldIdEnum fieldId, short value) {
      valueCollection.setFieldValue(fieldId, value);
   }

   public void setInt32(TBase valueCollection, TFieldIdEnum fieldId, int value) {
      valueCollection.setFieldValue(fieldId, value);
   }

   public void setInt64(TBase valueCollection, TFieldIdEnum fieldId, long value) {
      valueCollection.setFieldValue(fieldId, value);
   }

   public void setDouble(TBase valueCollection, TFieldIdEnum fieldId, double value) {
      valueCollection.setFieldValue(fieldId, value);
   }

   public void setBinary(TBase valueCollection, TFieldIdEnum fieldId, ByteBuffer value) {
      valueCollection.setFieldValue(fieldId, value);
   }

   public void setString(TBase valueCollection, TFieldIdEnum fieldId, ByteBuffer buffer) {
      String value = byteBufferToString(buffer);
      valueCollection.setFieldValue(fieldId, value);
   }

   public void setEnumField(TBase valueCollection, TFieldIdEnum fieldId, Object value) {
      valueCollection.setFieldValue(fieldId, value);
   }

   public void setListField(TBase valueCollection, TFieldIdEnum fieldId, Object value) {
      valueCollection.setFieldValue(fieldId, value);
   }

   public void setMapField(TBase valueCollection, TFieldIdEnum fieldId, Object value) {
      valueCollection.setFieldValue(fieldId, value);
   }

   public void setSetField(TBase valueCollection, TFieldIdEnum fieldId, Object value) {
      valueCollection.setFieldValue(fieldId, value);
   }

   public void setStructField(TBase valueCollection, TFieldIdEnum fieldId, Object value) {
      valueCollection.setFieldValue(fieldId, value);
   }

   private static String byteBufferToString(ByteBuffer buffer) {
      byte[] bytes = buffer.array();
      int pos = buffer.position();
      return new String(bytes, pos, buffer.limit() - pos, StandardCharsets.UTF_8);
   }
}
