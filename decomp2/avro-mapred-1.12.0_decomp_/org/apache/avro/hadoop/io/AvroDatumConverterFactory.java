package org.apache.avro.hadoop.io;

import java.nio.ByteBuffer;
import org.apache.avro.Schema;
import org.apache.avro.Schema.Type;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericFixed;
import org.apache.avro.mapred.AvroKey;
import org.apache.avro.mapred.AvroValue;
import org.apache.avro.mapred.AvroWrapper;
import org.apache.avro.mapreduce.AvroJob;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.ByteWritable;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;

public class AvroDatumConverterFactory extends Configured {
   public AvroDatumConverterFactory(Configuration conf) {
      super(conf);
   }

   public AvroDatumConverter create(Class inputClass) {
      boolean isMapOnly = ((JobConf)this.getConf()).getNumReduceTasks() == 0;
      if (AvroKey.class.isAssignableFrom(inputClass)) {
         Schema schema;
         if (isMapOnly) {
            schema = AvroJob.getMapOutputKeySchema(this.getConf());
            if (null == schema) {
               schema = AvroJob.getOutputKeySchema(this.getConf());
            }
         } else {
            schema = AvroJob.getOutputKeySchema(this.getConf());
         }

         if (null == schema) {
            throw new IllegalStateException("Writer schema for output key was not set. Use AvroJob.setOutputKeySchema().");
         } else {
            return new AvroWrapperConverter(schema);
         }
      } else if (AvroValue.class.isAssignableFrom(inputClass)) {
         Schema schema;
         if (isMapOnly) {
            schema = AvroJob.getMapOutputValueSchema(this.getConf());
            if (null == schema) {
               schema = AvroJob.getOutputValueSchema(this.getConf());
            }
         } else {
            schema = AvroJob.getOutputValueSchema(this.getConf());
         }

         if (null == schema) {
            throw new IllegalStateException("Writer schema for output value was not set. Use AvroJob.setOutputValueSchema().");
         } else {
            return new AvroWrapperConverter(schema);
         }
      } else if (BooleanWritable.class.isAssignableFrom(inputClass)) {
         return new BooleanWritableConverter();
      } else if (BytesWritable.class.isAssignableFrom(inputClass)) {
         return new BytesWritableConverter();
      } else if (ByteWritable.class.isAssignableFrom(inputClass)) {
         return new ByteWritableConverter();
      } else if (DoubleWritable.class.isAssignableFrom(inputClass)) {
         return new DoubleWritableConverter();
      } else if (FloatWritable.class.isAssignableFrom(inputClass)) {
         return new FloatWritableConverter();
      } else if (IntWritable.class.isAssignableFrom(inputClass)) {
         return new IntWritableConverter();
      } else if (LongWritable.class.isAssignableFrom(inputClass)) {
         return new LongWritableConverter();
      } else if (NullWritable.class.isAssignableFrom(inputClass)) {
         return new NullWritableConverter();
      } else if (Text.class.isAssignableFrom(inputClass)) {
         return new TextConverter();
      } else {
         throw new UnsupportedOperationException("Unsupported input type: " + inputClass.getName());
      }
   }

   public static class AvroWrapperConverter extends AvroDatumConverter {
      private final Schema mSchema;

      public AvroWrapperConverter(Schema schema) {
         this.mSchema = schema;
      }

      public Object convert(AvroWrapper input) {
         return input.datum();
      }

      public Schema getWriterSchema() {
         return this.mSchema;
      }
   }

   public static class BooleanWritableConverter extends AvroDatumConverter {
      private final Schema mSchema;

      public BooleanWritableConverter() {
         this.mSchema = Schema.create(Type.BOOLEAN);
      }

      public Boolean convert(BooleanWritable input) {
         return input.get();
      }

      public Schema getWriterSchema() {
         return this.mSchema;
      }
   }

   public static class BytesWritableConverter extends AvroDatumConverter {
      private final Schema mSchema;

      public BytesWritableConverter() {
         this.mSchema = Schema.create(Type.BYTES);
      }

      public ByteBuffer convert(BytesWritable input) {
         return ByteBuffer.wrap(input.getBytes());
      }

      public Schema getWriterSchema() {
         return this.mSchema;
      }
   }

   public static class ByteWritableConverter extends AvroDatumConverter {
      private final Schema mSchema = Schema.createFixed("Byte", "A single byte", "org.apache.avro.mapreduce", 1);

      public GenericFixed convert(ByteWritable input) {
         return new GenericData.Fixed(this.mSchema, new byte[]{input.get()});
      }

      public Schema getWriterSchema() {
         return this.mSchema;
      }
   }

   public static class DoubleWritableConverter extends AvroDatumConverter {
      private final Schema mSchema;

      public DoubleWritableConverter() {
         this.mSchema = Schema.create(Type.DOUBLE);
      }

      public Double convert(DoubleWritable input) {
         return input.get();
      }

      public Schema getWriterSchema() {
         return this.mSchema;
      }
   }

   public static class FloatWritableConverter extends AvroDatumConverter {
      private final Schema mSchema;

      public FloatWritableConverter() {
         this.mSchema = Schema.create(Type.FLOAT);
      }

      public Float convert(FloatWritable input) {
         return input.get();
      }

      public Schema getWriterSchema() {
         return this.mSchema;
      }
   }

   public static class IntWritableConverter extends AvroDatumConverter {
      private final Schema mSchema;

      public IntWritableConverter() {
         this.mSchema = Schema.create(Type.INT);
      }

      public Integer convert(IntWritable input) {
         return input.get();
      }

      public Schema getWriterSchema() {
         return this.mSchema;
      }
   }

   public static class LongWritableConverter extends AvroDatumConverter {
      private final Schema mSchema;

      public LongWritableConverter() {
         this.mSchema = Schema.create(Type.LONG);
      }

      public Long convert(LongWritable input) {
         return input.get();
      }

      public Schema getWriterSchema() {
         return this.mSchema;
      }
   }

   public static class NullWritableConverter extends AvroDatumConverter {
      private final Schema mSchema;

      public NullWritableConverter() {
         this.mSchema = Schema.create(Type.NULL);
      }

      public Object convert(NullWritable input) {
         return null;
      }

      public Schema getWriterSchema() {
         return this.mSchema;
      }
   }

   public static class TextConverter extends AvroDatumConverter {
      private final Schema mSchema;

      public TextConverter() {
         this.mSchema = Schema.create(Type.STRING);
      }

      public CharSequence convert(Text input) {
         return input.toString();
      }

      public Schema getWriterSchema() {
         return this.mSchema;
      }
   }
}
