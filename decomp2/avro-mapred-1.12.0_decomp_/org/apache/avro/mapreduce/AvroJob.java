package org.apache.avro.mapreduce;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.hadoop.io.AvroKeyComparator;
import org.apache.avro.hadoop.io.AvroSerialization;
import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapred.AvroValue;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;

public final class AvroJob {
   private static final String CONF_INPUT_KEY_SCHEMA = "avro.schema.input.key";
   private static final String CONF_INPUT_VALUE_SCHEMA = "avro.schema.input.value";
   private static final String CONF_OUTPUT_KEY_SCHEMA = "avro.schema.output.key";
   private static final String CONF_OUTPUT_VALUE_SCHEMA = "avro.schema.output.value";
   public static final String CONF_OUTPUT_CODEC = "avro.output.codec";

   private AvroJob() {
   }

   public static void setInputKeySchema(Job job, Schema schema) {
      job.getConfiguration().set("avro.schema.input.key", schema.toString());
   }

   public static void setInputValueSchema(Job job, Schema schema) {
      job.getConfiguration().set("avro.schema.input.value", schema.toString());
   }

   public static void setMapOutputKeySchema(Job job, Schema schema) {
      job.setMapOutputKeyClass(AvroKey.class);
      job.setGroupingComparatorClass(AvroKeyComparator.class);
      job.setSortComparatorClass(AvroKeyComparator.class);
      AvroSerialization.setKeyWriterSchema(job.getConfiguration(), schema);
      AvroSerialization.setKeyReaderSchema(job.getConfiguration(), schema);
      AvroSerialization.addToConfiguration(job.getConfiguration());
   }

   public static void setMapOutputValueSchema(Job job, Schema schema) {
      job.setMapOutputValueClass(AvroValue.class);
      AvroSerialization.setValueWriterSchema(job.getConfiguration(), schema);
      AvroSerialization.setValueReaderSchema(job.getConfiguration(), schema);
      AvroSerialization.addToConfiguration(job.getConfiguration());
   }

   public static void setOutputKeySchema(Job job, Schema schema) {
      job.setOutputKeyClass(AvroKey.class);
      job.getConfiguration().set("avro.schema.output.key", schema.toString());
   }

   public static void setOutputValueSchema(Job job, Schema schema) {
      job.setOutputValueClass(AvroValue.class);
      job.getConfiguration().set("avro.schema.output.value", schema.toString());
   }

   public static void setDataModelClass(Job job, Class modelClass) {
      AvroSerialization.setDataModelClass(job.getConfiguration(), modelClass);
   }

   public static Schema getInputKeySchema(Configuration conf) {
      String schemaString = conf.get("avro.schema.input.key");
      return schemaString != null ? (new Schema.Parser()).parse(schemaString) : null;
   }

   public static Schema getInputValueSchema(Configuration conf) {
      String schemaString = conf.get("avro.schema.input.value");
      return schemaString != null ? (new Schema.Parser()).parse(schemaString) : null;
   }

   public static Schema getMapOutputKeySchema(Configuration conf) {
      return AvroSerialization.getKeyWriterSchema(conf);
   }

   public static Schema getMapOutputValueSchema(Configuration conf) {
      return AvroSerialization.getValueWriterSchema(conf);
   }

   public static Schema getOutputKeySchema(Configuration conf) {
      String schemaString = conf.get("avro.schema.output.key");
      return schemaString != null ? (new Schema.Parser()).parse(schemaString) : null;
   }

   public static Schema getOutputValueSchema(Configuration conf) {
      String schemaString = conf.get("avro.schema.output.value");
      return schemaString != null ? (new Schema.Parser()).parse(schemaString) : null;
   }
}
