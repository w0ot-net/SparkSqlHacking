package org.apache.avro.mapred;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.reflect.ReflectData;
import org.apache.avro.specific.SpecificData;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.lib.IdentityMapper;
import org.apache.hadoop.mapred.lib.IdentityReducer;
import org.apache.hadoop.util.ReflectionUtils;

public class AvroJob {
   static final String MAPPER = "avro.mapper";
   static final String COMBINER = "avro.combiner";
   static final String REDUCER = "avro.reducer";
   public static final String INPUT_SCHEMA = "avro.input.schema";
   public static final String MAP_OUTPUT_SCHEMA = "avro.map.output.schema";
   public static final String OUTPUT_SCHEMA = "avro.output.schema";
   public static final String OUTPUT_CODEC = "avro.output.codec";
   public static final String TEXT_PREFIX = "avro.meta.text.";
   public static final String BINARY_PREFIX = "avro.meta.binary.";
   public static final String INPUT_IS_REFLECT = "avro.input.is.reflect";
   public static final String MAP_OUTPUT_IS_REFLECT = "avro.map.output.is.reflect";
   private static final String CONF_DATA_MODEL = "avro.serialization.data.model";

   private AvroJob() {
   }

   public static void setInputSchema(JobConf job, Schema s) {
      job.set("avro.input.schema", s.toString());
      configureAvroInput(job);
   }

   public static Schema getInputSchema(Configuration job) {
      String schemaString = job.get("avro.input.schema");
      return schemaString != null ? (new Schema.Parser()).parse(schemaString) : null;
   }

   public static void setMapOutputSchema(JobConf job, Schema s) {
      job.set("avro.map.output.schema", s.toString());
      configureAvroShuffle(job);
   }

   public static Schema getMapOutputSchema(Configuration job) {
      return (new Schema.Parser()).parse(job.get("avro.map.output.schema", job.get("avro.output.schema")));
   }

   public static void setOutputSchema(JobConf job, Schema s) {
      job.set("avro.output.schema", s.toString());
      configureAvroOutput(job);
   }

   public static void setOutputCodec(JobConf job, String codec) {
      job.set("avro.output.codec", codec);
   }

   public static void setOutputMeta(JobConf job, String key, String value) {
      job.set("avro.meta.text." + key, value);
   }

   public static void setOutputMeta(JobConf job, String key, long value) {
      job.set("avro.meta.text." + key, Long.toString(value));
   }

   public static void setOutputMeta(JobConf job, String key, byte[] value) {
      try {
         job.set("avro.meta.binary." + key, URLEncoder.encode(new String(value, StandardCharsets.ISO_8859_1), StandardCharsets.ISO_8859_1.name()));
      } catch (UnsupportedEncodingException var4) {
      }

   }

   public static void setInputSequenceFile(JobConf job) {
      job.setInputFormat(SequenceFileInputFormat.class);
   }

   public static void setReflect(JobConf job) {
      setInputReflect(job);
      setMapOutputReflect(job);
   }

   public static void setInputReflect(JobConf job) {
      job.setBoolean("avro.input.is.reflect", true);
   }

   public static void setMapOutputReflect(JobConf job) {
      job.setBoolean("avro.map.output.is.reflect", true);
   }

   public static Schema getOutputSchema(Configuration job) {
      return (new Schema.Parser()).parse(job.get("avro.output.schema"));
   }

   private static void configureAvroInput(JobConf job) {
      if (job.get("mapred.input.format.class") == null) {
         job.setInputFormat(AvroInputFormat.class);
      }

      if (job.getMapperClass() == IdentityMapper.class) {
         job.setMapperClass(HadoopMapper.class);
      }

      configureAvroShuffle(job);
   }

   private static void configureAvroOutput(JobConf job) {
      if (job.get("mapred.output.format.class") == null) {
         job.setOutputFormat(AvroOutputFormat.class);
      }

      if (job.getReducerClass() == IdentityReducer.class) {
         job.setReducerClass(HadoopReducer.class);
      }

      job.setOutputKeyClass(AvroWrapper.class);
      configureAvroShuffle(job);
   }

   private static void configureAvroShuffle(JobConf job) {
      job.setOutputKeyComparatorClass(AvroKeyComparator.class);
      job.setMapOutputKeyClass(AvroKey.class);
      job.setMapOutputValueClass(AvroValue.class);
      Collection<String> serializations = job.getStringCollection("io.serializations");
      if (!serializations.contains(AvroSerialization.class.getName())) {
         serializations.add(AvroSerialization.class.getName());
         job.setStrings("io.serializations", (String[])serializations.toArray(new String[0]));
      }

   }

   public static void setMapperClass(JobConf job, Class c) {
      job.set("avro.mapper", c.getName());
   }

   public static void setCombinerClass(JobConf job, Class c) {
      job.set("avro.combiner", c.getName());
      job.setCombinerClass(HadoopCombiner.class);
   }

   public static void setReducerClass(JobConf job, Class c) {
      job.set("avro.reducer", c.getName());
   }

   public static void setDataModelClass(JobConf job, Class modelClass) {
      job.setClass("avro.serialization.data.model", modelClass, GenericData.class);
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
      return newDataModelInstance(getDataModelClass(conf), conf);
   }

   public static GenericData createInputDataModel(Configuration conf) {
      String className = conf.get("avro.serialization.data.model", (String)null);
      Class<? extends GenericData> modelClass;
      if (className != null) {
         modelClass = getDataModelClass(conf);
      } else if (conf.getBoolean("avro.input.is.reflect", false)) {
         modelClass = ReflectData.class;
      } else {
         modelClass = SpecificData.class;
      }

      return newDataModelInstance(modelClass, conf);
   }

   public static GenericData createMapOutputDataModel(Configuration conf) {
      String className = conf.get("avro.serialization.data.model", (String)null);
      Class<? extends GenericData> modelClass;
      if (className != null) {
         modelClass = getDataModelClass(conf);
      } else if (conf.getBoolean("avro.map.output.is.reflect", false)) {
         modelClass = ReflectData.class;
      } else {
         modelClass = SpecificData.class;
      }

      return newDataModelInstance(modelClass, conf);
   }
}
