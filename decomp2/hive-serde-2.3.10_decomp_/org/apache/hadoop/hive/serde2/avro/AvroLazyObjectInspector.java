package org.apache.hadoop.hive.serde2.avro;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileStream;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.commons.lang3.ClassUtils;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.lazy.LazyArray;
import org.apache.hadoop.hive.serde2.lazy.LazyFactory;
import org.apache.hadoop.hive.serde2.lazy.LazyMap;
import org.apache.hadoop.hive.serde2.lazy.LazyObject;
import org.apache.hadoop.hive.serde2.lazy.LazyStruct;
import org.apache.hadoop.hive.serde2.lazy.LazyUnion;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.LazyListObjectInspector;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.LazyMapObjectInspector;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.LazySimpleStructObjectInspector;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.LazyUnionObjectInspector;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyObjectInspectorParameters;
import org.apache.hadoop.hive.serde2.objectinspector.ListObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.MapObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StandardUnionObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.io.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AvroLazyObjectInspector extends LazySimpleStructObjectInspector {
   private Schema readerSchema;
   private AvroSchemaRetriever schemaRetriever;
   public static final Logger LOG = LoggerFactory.getLogger(AvroLazyObjectInspector.class);

   /** @deprecated */
   @Deprecated
   public AvroLazyObjectInspector(List structFieldNames, List structFieldObjectInspectors, List structFieldComments, byte separator, Text nullSequence, boolean lastColumnTakesRest, boolean escaped, byte escapeChar) {
      super(structFieldNames, structFieldObjectInspectors, structFieldComments, separator, nullSequence, lastColumnTakesRest, escaped, escapeChar);
   }

   public AvroLazyObjectInspector(List structFieldNames, List structFieldObjectInspectors, List structFieldComments, byte separator, LazyObjectInspectorParameters lazyParams) {
      super(structFieldNames, structFieldObjectInspectors, structFieldComments, separator, lazyParams);
   }

   public void setReaderSchema(Schema readerSchema) {
      this.readerSchema = readerSchema;
   }

   public void setSchemaRetriever(AvroSchemaRetriever schemaRetriever) {
      this.schemaRetriever = schemaRetriever;
   }

   public Object getStructFieldData(Object data, StructField f) {
      if (data == null) {
         return null;
      } else {
         int fieldID = f.getFieldID();
         if (LOG.isDebugEnabled()) {
            LOG.debug("Getting struct field data for field: [" + f.getFieldName() + "] on data [" + data.getClass() + "]");
         }

         if (data instanceof LazyStruct) {
            LazyStruct row = (LazyStruct)data;
            Object rowField = row.getField(fieldID);
            if (rowField instanceof LazyStruct) {
               if (LOG.isDebugEnabled() && rowField != null) {
                  LOG.debug("Deserializing struct [" + rowField.getClass() + "]");
               }

               return this.deserializeStruct(rowField, f.getFieldName());
            } else if (rowField instanceof LazyMap) {
               LazyMap lazyMap = (LazyMap)rowField;

               for(Map.Entry entry : lazyMap.getMap().entrySet()) {
                  Object _key = entry.getKey();
                  Object _value = entry.getValue();
                  if (_value instanceof LazyStruct) {
                     lazyMap.getMap().put(_key, this.deserializeStruct(_value, f.getFieldName()));
                  }
               }

               if (LOG.isDebugEnabled()) {
                  LOG.debug("Returning a lazy map for field [" + f.getFieldName() + "]");
               }

               return lazyMap;
            } else {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Returning [" + rowField + "] for field [" + f.getFieldName() + "]");
               }

               return rowField;
            }
         } else if (!(data instanceof List)) {
            throw new IllegalArgumentException("data should be an instance of list");
         } else if (fieldID >= ((List)data).size()) {
            return null;
         } else {
            Object field = ((List)data).get(fieldID);
            return field == null ? null : this.toLazyObject(field, f.getFieldObjectInspector());
         }
      }
   }

   public List getStructFieldsDataAsList(Object data) {
      if (data == null) {
         return null;
      } else {
         List<Object> result = new ArrayList(this.fields.size());

         for(int i = 0; i < this.fields.size(); ++i) {
            result.add(this.getStructFieldData(data, (StructField)this.fields.get(i)));
         }

         return result;
      }
   }

   private Object deserializeStruct(Object struct, String fieldName) {
      byte[] data = ((LazyStruct)struct).getBytes();
      AvroDeserializer deserializer = new AvroDeserializer();
      if (data != null && data.length != 0) {
         if (this.readerSchema == null && this.schemaRetriever == null) {
            throw new IllegalArgumentException("reader schema or schemaRetriever must be set for field [" + fieldName + "]");
         } else {
            Schema ws = null;
            Schema rs = null;
            int offset = 0;
            AvroGenericRecordWritable avroWritable = new AvroGenericRecordWritable();
            if (this.readerSchema == null) {
               offset = this.schemaRetriever.getOffset();
               if (data.length < offset) {
                  throw new IllegalArgumentException("Data size cannot be less than [" + offset + "]. Found [" + data.length + "]");
               }

               rs = this.schemaRetriever.retrieveReaderSchema(data);
               if (rs == null) {
                  throw new IllegalStateException("A valid reader schema could not be retrieved either directly or from the schema retriever for field [" + fieldName + "]");
               }

               ws = this.schemaRetriever.retrieveWriterSchema(data);
               if (ws == null) {
                  throw new IllegalStateException("Null writer schema retrieved from schemaRetriever for field [" + fieldName + "]");
               }

               if (LOG.isDebugEnabled()) {
                  LOG.debug("Retrieved writer Schema: " + ws.toString());
                  LOG.debug("Retrieved reader Schema: " + rs.toString());
               }

               try {
                  avroWritable.readFields(data, offset, data.length, ws, rs);
               } catch (IOException ioe) {
                  throw new AvroObjectInspectorException("Error deserializing avro payload", ioe);
               }
            } else {
               if (this.schemaRetriever != null) {
                  ws = this.schemaRetriever.retrieveWriterSchema(data);
                  if (ws == null) {
                     throw new IllegalStateException("Null writer schema retrieved from schemaRetriever for field [" + fieldName + "]");
                  }
               } else {
                  ws = this.retrieveSchemaFromBytes(data);
               }

               rs = this.readerSchema;

               try {
                  avroWritable.readFields(data, ws, rs);
               } catch (IOException ioe) {
                  throw new AvroObjectInspectorException("Error deserializing avro payload", ioe);
               }
            }

            AvroObjectInspectorGenerator oiGenerator = null;
            Object deserializedObject = null;

            try {
               oiGenerator = new AvroObjectInspectorGenerator(rs);
               deserializedObject = deserializer.deserialize(oiGenerator.getColumnNames(), oiGenerator.getColumnTypes(), avroWritable, rs);
               return deserializedObject;
            } catch (SerDeException se) {
               throw new AvroObjectInspectorException("Error deserializing avro payload", se);
            }
         }
      } else {
         return null;
      }
   }

   private Schema retrieveSchemaFromBytes(byte[] data) {
      ByteArrayInputStream bais = new ByteArrayInputStream(data);
      DatumReader<GenericRecord> reader = new GenericDatumReader();
      Schema schema = null;

      try {
         DataFileStream<GenericRecord> dfs = new DataFileStream(bais, reader);
         schema = dfs.getSchema();
         return schema;
      } catch (IOException ioe) {
         throw new AvroObjectInspectorException("An error occurred retrieving schema from bytes", ioe);
      }
   }

   private Object toLazyObject(Object field, ObjectInspector fieldOI) {
      if (this.isPrimitive(field.getClass())) {
         return this.toLazyPrimitiveObject(field, fieldOI);
      } else if (fieldOI instanceof LazyListObjectInspector) {
         return this.toLazyListObject(field, fieldOI);
      } else if (field instanceof StandardUnionObjectInspector.StandardUnion) {
         return this.toLazyUnionObject(field, fieldOI);
      } else {
         return fieldOI instanceof LazyMapObjectInspector ? this.toLazyMapObject(field, fieldOI) : field;
      }
   }

   private LazyObject toLazyPrimitiveObject(Object obj, ObjectInspector oi) {
      if (obj == null) {
         return null;
      } else {
         LazyObject<? extends ObjectInspector> lazyObject = LazyFactory.createLazyObject(oi);
         ByteArrayRef ref = new ByteArrayRef();
         String objAsString = obj.toString().trim();
         ref.setData(objAsString.getBytes());
         lazyObject.init(ref, 0, ref.getData().length);
         return lazyObject;
      }
   }

   private Object toLazyListObject(Object obj, ObjectInspector objectInspector) {
      if (obj == null) {
         return null;
      } else {
         List<?> listObj = (List)obj;
         LazyArray retList = (LazyArray)LazyFactory.createLazyObject(objectInspector);
         List<Object> lazyList = retList.getList();
         ObjectInspector listElementOI = ((ListObjectInspector)objectInspector).getListElementObjectInspector();

         for(int i = 0; i < listObj.size(); ++i) {
            lazyList.add(this.toLazyObject(listObj.get(i), listElementOI));
         }

         return retList;
      }
   }

   private Object toLazyMapObject(Object obj, ObjectInspector objectInspector) {
      if (obj == null) {
         return null;
      } else {
         LazyMap lazyMap = (LazyMap)LazyFactory.createLazyObject(objectInspector);
         Map map = lazyMap.getMap();
         Map<Object, Object> origMap = (Map)obj;
         ObjectInspector keyObjectInspector = ((MapObjectInspector)objectInspector).getMapKeyObjectInspector();
         ObjectInspector valueObjectInspector = ((MapObjectInspector)objectInspector).getMapValueObjectInspector();

         for(Map.Entry entry : origMap.entrySet()) {
            Object value = entry.getValue();
            map.put(this.toLazyPrimitiveObject(entry.getKey(), keyObjectInspector), this.toLazyObject(value, valueObjectInspector));
         }

         return lazyMap;
      }
   }

   private Object toLazyUnionObject(Object obj, ObjectInspector objectInspector) {
      if (obj == null) {
         return null;
      } else if (!(objectInspector instanceof LazyUnionObjectInspector)) {
         throw new IllegalArgumentException("Invalid objectinspector found. Expected LazyUnionObjectInspector, Found " + objectInspector.getClass());
      } else {
         StandardUnionObjectInspector.StandardUnion standardUnion = (StandardUnionObjectInspector.StandardUnion)obj;
         LazyUnionObjectInspector lazyUnionOI = (LazyUnionObjectInspector)objectInspector;
         byte tag = standardUnion.getTag();
         Object field = standardUnion.getObject();
         ObjectInspector fieldOI = (ObjectInspector)lazyUnionOI.getObjectInspectors().get(tag);
         Object convertedObj = null;
         if (field != null) {
            convertedObj = this.toLazyObject(field, fieldOI);
         }

         return convertedObj == null ? null : new LazyUnion(lazyUnionOI, tag, convertedObj);
      }
   }

   private boolean isPrimitive(Class clazz) {
      return clazz.isPrimitive() || ClassUtils.wrapperToPrimitive(clazz) != null || clazz.getSimpleName().equals("String");
   }
}
