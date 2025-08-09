package org.apache.parquet.example;

import java.util.List;
import org.apache.parquet.io.api.Binary;
import org.apache.parquet.io.api.Converter;
import org.apache.parquet.io.api.GroupConverter;
import org.apache.parquet.io.api.PrimitiveConverter;
import org.apache.parquet.io.api.RecordMaterializer;
import org.apache.parquet.schema.GroupType;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.PrimitiveType;
import org.apache.parquet.schema.TypeConverter;

public final class DummyRecordConverter extends RecordMaterializer {
   private Object a;
   private GroupConverter root;

   public DummyRecordConverter(MessageType schema) {
      this.root = (GroupConverter)schema.convertWith(new TypeConverter() {
         public Converter convertPrimitiveType(List path, PrimitiveType primitiveType) {
            return new PrimitiveConverter() {
               public void addBinary(Binary value) {
                  DummyRecordConverter.this.a = value;
               }

               public void addBoolean(boolean value) {
                  DummyRecordConverter.this.a = value;
               }

               public void addDouble(double value) {
                  DummyRecordConverter.this.a = value;
               }

               public void addFloat(float value) {
                  DummyRecordConverter.this.a = value;
               }

               public void addInt(int value) {
                  DummyRecordConverter.this.a = value;
               }

               public void addLong(long value) {
                  DummyRecordConverter.this.a = value;
               }
            };
         }

         public Converter convertGroupType(List path, GroupType groupType, final List converters) {
            return new GroupConverter() {
               public Converter getConverter(int fieldIndex) {
                  return (Converter)converters.get(fieldIndex);
               }

               public void start() {
                  DummyRecordConverter.this.a = "start()";
               }

               public void end() {
                  DummyRecordConverter.this.a = "end()";
               }
            };
         }

         public Converter convertMessageType(MessageType messageType, List children) {
            return this.convertGroupType((List)null, messageType, children);
         }
      });
   }

   public Object getCurrentRecord() {
      return this.a;
   }

   public GroupConverter getRootConverter() {
      return this.root;
   }
}
