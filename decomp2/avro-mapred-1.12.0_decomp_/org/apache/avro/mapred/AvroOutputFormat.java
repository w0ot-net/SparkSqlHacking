package org.apache.avro.mapred;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.apache.avro.Schema;
import org.apache.avro.file.CodecFactory;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.hadoop.file.HadoopCodecFactory;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordWriter;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.util.Progressable;

public class AvroOutputFormat extends FileOutputFormat {
   public static final String EXT = ".avro";
   public static final String DEFLATE_LEVEL_KEY = "avro.mapred.deflate.level";
   public static final String XZ_LEVEL_KEY = "avro.mapred.xz.level";
   public static final String ZSTD_LEVEL_KEY = "avro.mapred.zstd.level";
   public static final String ZSTD_BUFFERPOOL_KEY = "avro.mapred.zstd.bufferpool";
   public static final String SYNC_INTERVAL_KEY = "avro.mapred.sync.interval";

   public static void setDeflateLevel(JobConf job, int level) {
      FileOutputFormat.setCompressOutput(job, true);
      job.setInt("avro.mapred.deflate.level", level);
   }

   public static void setSyncInterval(JobConf job, int syncIntervalInBytes) {
      job.setInt("avro.mapred.sync.interval", syncIntervalInBytes);
   }

   static void configureDataFileWriter(DataFileWriter writer, JobConf job) throws UnsupportedEncodingException {
      CodecFactory factory = getCodecFactory(job);
      if (factory != null) {
         writer.setCodec(factory);
      }

      writer.setSyncInterval(job.getInt("avro.mapred.sync.interval", 64000));

      for(Map.Entry e : job) {
         if (((String)e.getKey()).startsWith("avro.meta.text.")) {
            writer.setMeta(((String)e.getKey()).substring("avro.meta.text.".length()), (String)e.getValue());
         }

         if (((String)e.getKey()).startsWith("avro.meta.binary.")) {
            writer.setMeta(((String)e.getKey()).substring("avro.meta.binary.".length()), URLDecoder.decode((String)e.getValue(), StandardCharsets.ISO_8859_1.name()).getBytes(StandardCharsets.ISO_8859_1));
         }
      }

   }

   static CodecFactory getCodecFactory(JobConf job) {
      CodecFactory factory = null;
      if (FileOutputFormat.getCompressOutput(job)) {
         int deflateLevel = job.getInt("avro.mapred.deflate.level", -1);
         int xzLevel = job.getInt("avro.mapred.xz.level", 6);
         int zstdLevel = job.getInt("avro.mapred.zstd.level", 3);
         boolean zstdBufferPool = job.getBoolean("avro.mapred.zstd.bufferpool", false);
         String codecName = job.get("avro.output.codec");
         if (codecName == null) {
            String codecClassName = job.get("mapred.output.compression.codec", (String)null);
            String avroCodecName = HadoopCodecFactory.getAvroCodecName(codecClassName);
            if (codecClassName != null && avroCodecName != null) {
               factory = HadoopCodecFactory.fromHadoopString(codecClassName);
               job.set("avro.output.codec", avroCodecName);
               return factory;
            }

            return CodecFactory.deflateCodec(deflateLevel);
         }

         if (codecName.equals("deflate")) {
            factory = CodecFactory.deflateCodec(deflateLevel);
         } else if (codecName.equals("xz")) {
            factory = CodecFactory.xzCodec(xzLevel);
         } else if (codecName.equals("zstandard")) {
            factory = CodecFactory.zstandardCodec(zstdLevel, false, zstdBufferPool);
         } else {
            factory = CodecFactory.fromString(codecName);
         }
      }

      return factory;
   }

   public RecordWriter getRecordWriter(FileSystem ignore, JobConf job, String name, Progressable prog) throws IOException {
      boolean isMapOnly = job.getNumReduceTasks() == 0;
      Schema schema = isMapOnly ? AvroJob.getMapOutputSchema(job) : AvroJob.getOutputSchema(job);
      GenericData dataModel = AvroJob.createDataModel(job);
      final DataFileWriter<T> writer = new DataFileWriter(dataModel.createDatumWriter((Schema)null));
      configureDataFileWriter(writer, job);
      Path path = FileOutputFormat.getTaskOutputPath(job, name + ".avro");
      writer.create(schema, path.getFileSystem(job).create(path));
      return new RecordWriter() {
         public void write(AvroWrapper wrapper, NullWritable ignore) throws IOException {
            writer.append(wrapper.datum());
         }

         public void close(Reporter reporter) throws IOException {
            writer.close();
         }
      };
   }
}
