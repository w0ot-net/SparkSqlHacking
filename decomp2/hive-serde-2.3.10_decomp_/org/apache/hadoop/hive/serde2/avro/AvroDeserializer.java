package org.apache.hadoop.hive.serde2.avro;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.rmi.server.UID;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.avro.Schema;
import org.apache.avro.UnresolvedUnionException;
import org.apache.avro.Schema.Type;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.hadoop.hive.common.type.HiveChar;
import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.hive.common.type.HiveVarchar;
import org.apache.hadoop.hive.serde2.io.DateWritable;
import org.apache.hadoop.hive.serde2.objectinspector.StandardUnionObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.JavaHiveDecimalObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.typeinfo.DecimalTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.ListTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.MapTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.StructTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.UnionTypeInfo;
import org.apache.hadoop.io.Writable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class AvroDeserializer {
   private static final Logger LOG = LoggerFactory.getLogger(AvroDeserializer.class);
   private final HashSet noEncodingNeeded = new HashSet();
   private final HashMap reEncoderCache = new HashMap();
   private boolean warnedOnce = false;
   private List row;

   public Object deserialize(List columnNames, List columnTypes, Writable writable, Schema readerSchema) throws AvroSerdeException {
      if (!(writable instanceof AvroGenericRecordWritable)) {
         throw new AvroSerdeException("Expecting a AvroGenericRecordWritable");
      } else {
         if (this.row != null && this.row.size() == columnNames.size()) {
            this.row.clear();
         } else {
            this.row = new ArrayList(columnNames.size());
         }

         AvroGenericRecordWritable recordWritable = (AvroGenericRecordWritable)writable;
         GenericRecord r = recordWritable.getRecord();
         Schema fileSchema = recordWritable.getFileSchema();
         UID recordReaderId = recordWritable.getRecordReaderID();
         if (!this.noEncodingNeeded.contains(recordReaderId)) {
            SchemaReEncoder reEncoder = null;
            if (this.reEncoderCache.containsKey(recordReaderId)) {
               reEncoder = (SchemaReEncoder)this.reEncoderCache.get(recordReaderId);
            } else if (!r.getSchema().equals(readerSchema)) {
               reEncoder = new SchemaReEncoder(r.getSchema(), readerSchema);
               this.reEncoderCache.put(recordReaderId, reEncoder);
            } else {
               LOG.debug("Adding new valid RRID :" + recordReaderId);
               this.noEncodingNeeded.add(recordReaderId);
            }

            if (reEncoder != null) {
               if (!this.warnedOnce) {
                  LOG.warn("Received different schemas.  Have to re-encode: " + r.getSchema().toString(false) + "\nSIZE" + this.reEncoderCache + " ID " + recordReaderId);
                  this.warnedOnce = true;
               }

               r = reEncoder.reencode(r);
            }
         }

         this.workerBase(this.row, fileSchema, columnNames, columnTypes, r);
         return this.row;
      }
   }

   private List workerBase(List objectRow, Schema fileSchema, List columnNames, List columnTypes, GenericRecord record) throws AvroSerdeException {
      for(int i = 0; i < columnNames.size(); ++i) {
         TypeInfo columnType = (TypeInfo)columnTypes.get(i);
         String columnName = (String)columnNames.get(i);
         Object datum = record.get(columnName);
         Schema datumSchema = record.getSchema().getField(columnName).schema();
         Schema.Field field = AvroSerdeUtils.isNullableType(fileSchema) ? AvroSerdeUtils.getOtherTypeFromNullableType(fileSchema).getField(columnName) : fileSchema.getField(columnName);
         objectRow.add(this.worker(datum, field == null ? null : field.schema(), datumSchema, columnType));
      }

      return objectRow;
   }

   private Object worker(Object datum, Schema fileSchema, Schema recordSchema, TypeInfo columnType) throws AvroSerdeException {
      if (AvroSerdeUtils.isNullableType(recordSchema)) {
         return this.deserializeNullableUnion(datum, fileSchema, recordSchema);
      } else {
         switch (columnType.getCategory()) {
            case STRUCT:
               return this.deserializeStruct((GenericData.Record)datum, fileSchema, (StructTypeInfo)columnType);
            case UNION:
               return this.deserializeUnion(datum, fileSchema, recordSchema, (UnionTypeInfo)columnType);
            case LIST:
               return this.deserializeList(datum, fileSchema, recordSchema, (ListTypeInfo)columnType);
            case MAP:
               return this.deserializeMap(datum, fileSchema, recordSchema, (MapTypeInfo)columnType);
            case PRIMITIVE:
               return this.deserializePrimitive(datum, fileSchema, recordSchema, (PrimitiveTypeInfo)columnType);
            default:
               throw new AvroSerdeException("Unknown TypeInfo: " + columnType.getCategory());
         }
      }
   }

   private Object deserializePrimitive(Object datum, Schema fileSchema, Schema recordSchema, PrimitiveTypeInfo columnType) throws AvroSerdeException {
      switch (columnType.getPrimitiveCategory()) {
         case STRING:
            return datum.toString();
         case BINARY:
            if (recordSchema.getType() == Type.FIXED) {
               GenericData.Fixed fixed = (GenericData.Fixed)datum;
               return fixed.bytes();
            } else {
               if (recordSchema.getType() == Type.BYTES) {
                  return AvroSerdeUtils.getBytesFromByteBuffer((ByteBuffer)datum);
               }

               throw new AvroSerdeException("Unexpected Avro schema for Binary TypeInfo: " + recordSchema.getType());
            }
         case DECIMAL:
            if (fileSchema == null) {
               throw new AvroSerdeException("File schema is missing for decimal field. Reader schema is " + columnType);
            } else {
               int scale = 0;

               try {
                  scale = AvroSerdeUtils.getIntFromSchema(fileSchema, "scale");
               } catch (Exception ex) {
                  throw new AvroSerdeException("Failed to obtain scale value from file schema: " + fileSchema, ex);
               }

               HiveDecimal dec = AvroSerdeUtils.getHiveDecimalFromByteBuffer((ByteBuffer)datum, scale);
               JavaHiveDecimalObjectInspector oi = (JavaHiveDecimalObjectInspector)PrimitiveObjectInspectorFactory.getPrimitiveJavaObjectInspector((PrimitiveTypeInfo)((DecimalTypeInfo)columnType));
               return oi.set((Object)null, (HiveDecimal)dec);
            }
         case CHAR:
            if (fileSchema == null) {
               throw new AvroSerdeException("File schema is missing for char field. Reader schema is " + columnType);
            } else {
               int maxLength = 0;

               try {
                  maxLength = AvroSerdeUtils.getIntFromSchema(fileSchema, "maxLength");
               } catch (Exception ex) {
                  throw new AvroSerdeException("Failed to obtain maxLength value for char field from file schema: " + fileSchema, ex);
               }

               String str = datum.toString();
               HiveChar hc = new HiveChar(str, maxLength);
               return hc;
            }
         case VARCHAR:
            if (fileSchema == null) {
               throw new AvroSerdeException("File schema is missing for varchar field. Reader schema is " + columnType);
            } else {
               int maxLength = 0;

               try {
                  maxLength = AvroSerdeUtils.getIntFromSchema(fileSchema, "maxLength");
               } catch (Exception ex) {
                  throw new AvroSerdeException("Failed to obtain maxLength value for varchar field from file schema: " + fileSchema, ex);
               }

               String str = datum.toString();
               HiveVarchar hvc = new HiveVarchar(str, maxLength);
               return hvc;
            }
         case DATE:
            if (recordSchema.getType() != Type.INT) {
               throw new AvroSerdeException("Unexpected Avro schema for Date TypeInfo: " + recordSchema.getType());
            }

            return new Date(DateWritable.daysToMillis((Integer)datum));
         case TIMESTAMP:
            if (recordSchema.getType() != Type.LONG) {
               throw new AvroSerdeException("Unexpected Avro schema for Date TypeInfo: " + recordSchema.getType());
            }

            return new Timestamp((Long)datum);
         default:
            return datum;
      }
   }

   private Object deserializeNullableUnion(Object datum, Schema fileSchema, Schema recordSchema) throws AvroSerdeException {
      if (recordSchema.getTypes().size() == 2) {
         return this.deserializeSingleItemNullableUnion(datum, fileSchema, recordSchema);
      } else if (datum == null) {
         return null;
      } else {
         Schema newRecordSchema = AvroSerdeUtils.getOtherTypeFromNullableType(recordSchema);
         return this.worker(datum, fileSchema, newRecordSchema, SchemaToTypeInfo.generateTypeInfo(newRecordSchema, (Set)null));
      }
   }

   private Object deserializeSingleItemNullableUnion(Object datum, Schema fileSchema, Schema recordSchema) throws AvroSerdeException {
      int tag = GenericData.get().resolveUnion(recordSchema, datum);
      Schema schema = (Schema)recordSchema.getTypes().get(tag);
      if (schema.getType().equals(Type.NULL)) {
         return null;
      } else {
         Schema currentFileSchema = null;
         if (fileSchema != null) {
            if (fileSchema.getType() == Type.UNION) {
               try {
                  tag = GenericData.get().resolveUnion(fileSchema, datum);
                  currentFileSchema = (Schema)fileSchema.getTypes().get(tag);
               } catch (UnresolvedUnionException e) {
                  if (LOG.isDebugEnabled()) {
                     String datumClazz = null;
                     if (datum != null) {
                        datumClazz = datum.getClass().getName();
                     }

                     String msg = "File schema union could not resolve union. fileSchema = " + fileSchema + ", recordSchema = " + recordSchema + ", datum class = " + datumClazz + ": " + e;
                     LOG.debug(msg, e);
                  }

                  currentFileSchema = schema;
               }
            } else {
               currentFileSchema = fileSchema;
            }
         }

         return this.worker(datum, currentFileSchema, schema, SchemaToTypeInfo.generateTypeInfo(schema, (Set)null));
      }
   }

   private Object deserializeStruct(GenericData.Record datum, Schema fileSchema, StructTypeInfo columnType) throws AvroSerdeException {
      ArrayList<TypeInfo> innerFieldTypes = columnType.getAllStructFieldTypeInfos();
      ArrayList<String> innerFieldNames = columnType.getAllStructFieldNames();
      List<Object> innerObjectRow = new ArrayList(innerFieldTypes.size());
      return this.workerBase(innerObjectRow, fileSchema, innerFieldNames, innerFieldTypes, datum);
   }

   private Object deserializeUnion(Object datum, Schema fileSchema, Schema recordSchema, UnionTypeInfo columnType) throws AvroSerdeException {
      int fsTag = GenericData.get().resolveUnion(fileSchema, datum);
      int rsTag = GenericData.get().resolveUnion(recordSchema, datum);
      Object desered = this.worker(datum, fileSchema == null ? null : (Schema)fileSchema.getTypes().get(fsTag), (Schema)recordSchema.getTypes().get(rsTag), (TypeInfo)columnType.getAllUnionObjectTypeInfos().get(rsTag));
      return new StandardUnionObjectInspector.StandardUnion((byte)rsTag, desered);
   }

   private Object deserializeList(Object datum, Schema fileSchema, Schema recordSchema, ListTypeInfo columnType) throws AvroSerdeException {
      if (recordSchema.getType().equals(Type.FIXED)) {
         GenericData.Fixed fixed = (GenericData.Fixed)datum;
         List<Byte> asList = new ArrayList(fixed.bytes().length);

         for(int j = 0; j < fixed.bytes().length; ++j) {
            asList.add(fixed.bytes()[j]);
         }

         return asList;
      } else if (!recordSchema.getType().equals(Type.BYTES)) {
         List listData = (List)datum;
         Schema listSchema = recordSchema.getElementType();
         List<Object> listContents = new ArrayList(listData.size());

         for(Object obj : listData) {
            listContents.add(this.worker(obj, fileSchema == null ? null : fileSchema.getElementType(), listSchema, columnType.getListElementTypeInfo()));
         }

         return listContents;
      } else {
         ByteBuffer bb = (ByteBuffer)datum;
         List<Byte> asList = new ArrayList(bb.capacity());
         byte[] array = bb.array();

         for(int j = 0; j < array.length; ++j) {
            asList.add(array[j]);
         }

         return asList;
      }
   }

   private Object deserializeMap(Object datum, Schema fileSchema, Schema mapSchema, MapTypeInfo columnType) throws AvroSerdeException {
      Map<String, Object> map = new HashMap();
      Map<CharSequence, Object> mapDatum = (Map)datum;
      Schema valueSchema = mapSchema.getValueType();
      TypeInfo valueTypeInfo = columnType.getMapValueTypeInfo();

      for(CharSequence key : mapDatum.keySet()) {
         Object value = mapDatum.get(key);
         map.put(key.toString(), this.worker(value, fileSchema == null ? null : fileSchema.getValueType(), valueSchema, valueTypeInfo));
      }

      return map;
   }

   public HashSet getNoEncodingNeeded() {
      return this.noEncodingNeeded;
   }

   public HashMap getReEncoderCache() {
      return this.reEncoderCache;
   }

   static class SchemaReEncoder {
      private final ByteArrayOutputStream baos = new ByteArrayOutputStream();
      private final GenericDatumWriter gdw = new GenericDatumWriter();
      private BinaryDecoder binaryDecoder = null;
      GenericDatumReader gdr = null;

      public SchemaReEncoder(Schema writer, Schema reader) {
         this.gdr = new GenericDatumReader(writer, reader);
      }

      public GenericRecord reencode(GenericRecord r) throws AvroSerdeException {
         this.baos.reset();
         BinaryEncoder be = EncoderFactory.get().directBinaryEncoder(this.baos, (BinaryEncoder)null);
         this.gdw.setSchema(r.getSchema());

         try {
            this.gdw.write(r, be);
            ByteArrayInputStream bais = new ByteArrayInputStream(this.baos.toByteArray());
            this.binaryDecoder = DecoderFactory.defaultFactory().createBinaryDecoder(bais, this.binaryDecoder);
            return (GenericRecord)this.gdr.read(r, this.binaryDecoder);
         } catch (IOException e) {
            throw new AvroSerdeException("Exception trying to re-encode record to new schema", e);
         }
      }
   }
}
