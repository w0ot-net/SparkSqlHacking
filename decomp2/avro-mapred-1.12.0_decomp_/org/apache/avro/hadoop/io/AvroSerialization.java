package org.apache.avro.hadoop.io;

import java.lang.reflect.Constructor;
import java.util.Collection;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapred.AvroValue;
import org.apache.avro.mapred.AvroWrapper;
import org.apache.avro.reflect.ReflectData;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.serializer.Deserializer;
import org.apache.hadoop.io.serializer.Serialization;
import org.apache.hadoop.io.serializer.Serializer;
import org.apache.hadoop.util.ReflectionUtils;

public class AvroSerialization extends Configured implements Serialization {
   private static final String CONF_KEY_WRITER_SCHEMA = "avro.serialization.key.writer.schema";
   private static final String CONF_KEY_READER_SCHEMA = "avro.serialization.key.reader.schema";
   private static final String CONF_VALUE_WRITER_SCHEMA = "avro.serialization.value.writer.schema";
   private static final String CONF_VALUE_READER_SCHEMA = "avro.serialization.value.reader.schema";
   private static final String CONF_DATA_MODEL = "avro.serialization.data.model";

   public boolean accept(Class c) {
      return AvroKey.class.isAssignableFrom(c) || AvroValue.class.isAssignableFrom(c);
   }

   public Deserializer getDeserializer(Class c) {
      Configuration conf = this.getConf();
      GenericData dataModel = createDataModel(conf);
      if (AvroKey.class.isAssignableFrom(c)) {
         Schema writerSchema = getKeyWriterSchema(conf);
         Schema readerSchema = getKeyReaderSchema(conf);
         DatumReader<T> datumReader = readerSchema != null ? dataModel.createDatumReader(writerSchema, readerSchema) : dataModel.createDatumReader(writerSchema);
         return new AvroKeyDeserializer(writerSchema, readerSchema, datumReader);
      } else if (AvroValue.class.isAssignableFrom(c)) {
         Schema writerSchema = getValueWriterSchema(conf);
         Schema readerSchema = getValueReaderSchema(conf);
         DatumReader<T> datumReader = readerSchema != null ? dataModel.createDatumReader(writerSchema, readerSchema) : dataModel.createDatumReader(writerSchema);
         return new AvroValueDeserializer(writerSchema, readerSchema, datumReader);
      } else {
         throw new IllegalStateException("Only AvroKey and AvroValue are supported.");
      }
   }

   public Serializer getSerializer(Class c) {
      Configuration conf = this.getConf();
      Schema schema;
      if (AvroKey.class.isAssignableFrom(c)) {
         schema = getKeyWriterSchema(conf);
      } else {
         if (!AvroValue.class.isAssignableFrom(c)) {
            throw new IllegalStateException("Only AvroKey and AvroValue are supported.");
         }

         schema = getValueWriterSchema(conf);
      }

      GenericData dataModel = createDataModel(conf);
      DatumWriter<T> datumWriter = dataModel.createDatumWriter(schema);
      return new AvroSerializer(schema, datumWriter);
   }

   public static void addToConfiguration(Configuration conf) {
      Collection<String> serializations = conf.getStringCollection("io.serializations");
      if (!serializations.contains(AvroSerialization.class.getName())) {
         serializations.add(AvroSerialization.class.getName());
         conf.setStrings("io.serializations", (String[])serializations.toArray(new String[0]));
      }

   }

   public static void setKeyWriterSchema(Configuration conf, Schema schema) {
      if (null == schema) {
         throw new IllegalArgumentException("Writer schema may not be null");
      } else {
         conf.set("avro.serialization.key.writer.schema", schema.toString());
      }
   }

   public static void setKeyReaderSchema(Configuration conf, Schema schema) {
      conf.set("avro.serialization.key.reader.schema", schema.toString());
   }

   public static void setValueWriterSchema(Configuration conf, Schema schema) {
      if (null == schema) {
         throw new IllegalArgumentException("Writer schema may not be null");
      } else {
         conf.set("avro.serialization.value.writer.schema", schema.toString());
      }
   }

   public static void setValueReaderSchema(Configuration conf, Schema schema) {
      conf.set("avro.serialization.value.reader.schema", schema.toString());
   }

   public static void setDataModelClass(Configuration conf, Class modelClass) {
      conf.setClass("avro.serialization.data.model", modelClass, GenericData.class);
   }

   public static Schema getKeyWriterSchema(Configuration conf) {
      String json = conf.get("avro.serialization.key.writer.schema");
      return null == json ? null : (new Schema.Parser()).parse(json);
   }

   public static Schema getKeyReaderSchema(Configuration conf) {
      String json = conf.get("avro.serialization.key.reader.schema");
      return null == json ? null : (new Schema.Parser()).parse(json);
   }

   public static Schema getValueWriterSchema(Configuration conf) {
      String json = conf.get("avro.serialization.value.writer.schema");
      return null == json ? null : (new Schema.Parser()).parse(json);
   }

   public static Schema getValueReaderSchema(Configuration conf) {
      String json = conf.get("avro.serialization.value.reader.schema");
      return null == json ? null : (new Schema.Parser()).parse(json);
   }

   public static Class getDataModelClass(Configuration conf) {
      return conf.getClass("avro.serialization.data.model", ReflectData.class, GenericData.class);
   }

   private static GenericData newDataModelInstance(Class modelClass, Configuration conf) {
      GenericData dataModel;
      try {
         Constructor<? extends GenericData> ctor = modelClass.getDeclaredConstructor(ClassLoader.class);
         ctor.setAccessible(true);
         dataModel = (GenericData)ctor.newInstance(conf.getClassLoader());
      } catch (Exception e) {
         throw new RuntimeException(e);
      }

      ReflectionUtils.setConf(dataModel, conf);
      return dataModel;
   }

   public static GenericData createDataModel(Configuration conf) {
      Class<? extends GenericData> modelClass = getDataModelClass(conf);
      return newDataModelInstance(modelClass, conf);
   }
}
