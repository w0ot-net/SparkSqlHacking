package org.apache.avro;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Map;
import org.apache.avro.generic.GenericEnumSymbol;
import org.apache.avro.generic.GenericFixed;
import org.apache.avro.generic.IndexedRecord;

public abstract class Conversion {
   public abstract Class getConvertedType();

   public abstract String getLogicalTypeName();

   public String adjustAndSetValue(String varName, String valParamName) {
      return varName + " = " + valParamName + ";";
   }

   public Schema getRecommendedSchema() {
      throw new UnsupportedOperationException("No recommended schema for " + this.getLogicalTypeName());
   }

   public Object fromBoolean(Boolean value, Schema schema, LogicalType type) {
      throw new UnsupportedOperationException("fromBoolean is not supported for " + type.getName());
   }

   public Object fromInt(Integer value, Schema schema, LogicalType type) {
      throw new UnsupportedOperationException("fromInt is not supported for " + type.getName());
   }

   public Object fromLong(Long value, Schema schema, LogicalType type) {
      throw new UnsupportedOperationException("fromLong is not supported for " + type.getName());
   }

   public Object fromFloat(Float value, Schema schema, LogicalType type) {
      throw new UnsupportedOperationException("fromFloat is not supported for " + type.getName());
   }

   public Object fromDouble(Double value, Schema schema, LogicalType type) {
      throw new UnsupportedOperationException("fromDouble is not supported for " + type.getName());
   }

   public Object fromCharSequence(CharSequence value, Schema schema, LogicalType type) {
      throw new UnsupportedOperationException("fromCharSequence is not supported for " + type.getName());
   }

   public Object fromEnumSymbol(GenericEnumSymbol value, Schema schema, LogicalType type) {
      throw new UnsupportedOperationException("fromEnumSymbol is not supported for " + type.getName());
   }

   public Object fromFixed(GenericFixed value, Schema schema, LogicalType type) {
      throw new UnsupportedOperationException("fromFixed is not supported for " + type.getName());
   }

   public Object fromBytes(ByteBuffer value, Schema schema, LogicalType type) {
      throw new UnsupportedOperationException("fromBytes is not supported for " + type.getName());
   }

   public Object fromArray(Collection value, Schema schema, LogicalType type) {
      throw new UnsupportedOperationException("fromArray is not supported for " + type.getName());
   }

   public Object fromMap(Map value, Schema schema, LogicalType type) {
      throw new UnsupportedOperationException("fromMap is not supported for " + type.getName());
   }

   public Object fromRecord(IndexedRecord value, Schema schema, LogicalType type) {
      throw new UnsupportedOperationException("fromRecord is not supported for " + type.getName());
   }

   public Boolean toBoolean(Object value, Schema schema, LogicalType type) {
      throw new UnsupportedOperationException("toBoolean is not supported for " + type.getName());
   }

   public Integer toInt(Object value, Schema schema, LogicalType type) {
      throw new UnsupportedOperationException("toInt is not supported for " + type.getName());
   }

   public Long toLong(Object value, Schema schema, LogicalType type) {
      throw new UnsupportedOperationException("toLong is not supported for " + type.getName());
   }

   public Float toFloat(Object value, Schema schema, LogicalType type) {
      throw new UnsupportedOperationException("toFloat is not supported for " + type.getName());
   }

   public Double toDouble(Object value, Schema schema, LogicalType type) {
      throw new UnsupportedOperationException("toDouble is not supported for " + type.getName());
   }

   public CharSequence toCharSequence(Object value, Schema schema, LogicalType type) {
      throw new UnsupportedOperationException("toCharSequence is not supported for " + type.getName());
   }

   public GenericEnumSymbol toEnumSymbol(Object value, Schema schema, LogicalType type) {
      throw new UnsupportedOperationException("toEnumSymbol is not supported for " + type.getName());
   }

   public GenericFixed toFixed(Object value, Schema schema, LogicalType type) {
      throw new UnsupportedOperationException("toFixed is not supported for " + type.getName());
   }

   public ByteBuffer toBytes(Object value, Schema schema, LogicalType type) {
      throw new UnsupportedOperationException("toBytes is not supported for " + type.getName());
   }

   public Collection toArray(Object value, Schema schema, LogicalType type) {
      throw new UnsupportedOperationException("toArray is not supported for " + type.getName());
   }

   public Map toMap(Object value, Schema schema, LogicalType type) {
      throw new UnsupportedOperationException("toMap is not supported for " + type.getName());
   }

   public IndexedRecord toRecord(Object value, Schema schema, LogicalType type) {
      throw new UnsupportedOperationException("toRecord is not supported for " + type.getName());
   }
}
