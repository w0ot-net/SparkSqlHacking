package org.apache.avro.mapreduce;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.avro.AvroRuntimeException;
import org.apache.avro.file.CodecFactory;
import org.apache.avro.hadoop.file.HadoopCodecFactory;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.OutputCommitter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public abstract class AvroOutputFormatBase extends FileOutputFormat {
   protected static CodecFactory getCompressionCodec(TaskAttemptContext context) {
      if (FileOutputFormat.getCompressOutput(context)) {
         int deflateLevel = context.getConfiguration().getInt("avro.mapred.deflate.level", -1);
         int xzLevel = context.getConfiguration().getInt("avro.mapred.xz.level", 6);
         int zstdLevel = context.getConfiguration().getInt("avro.mapred.zstd.level", 3);
         boolean zstdBufferPool = context.getConfiguration().getBoolean("avro.mapred.zstd.bufferpool", false);
         String outputCodec = context.getConfiguration().get("avro.output.codec");
         if (outputCodec == null) {
            String compressionCodec = context.getConfiguration().get("mapred.output.compression.codec");
            String avroCodecName = HadoopCodecFactory.getAvroCodecName(compressionCodec);
            if (avroCodecName != null) {
               context.getConfiguration().set("avro.output.codec", avroCodecName);
               return HadoopCodecFactory.fromHadoopString(compressionCodec);
            } else {
               return CodecFactory.deflateCodec(deflateLevel);
            }
         } else if ("deflate".equals(outputCodec)) {
            return CodecFactory.deflateCodec(deflateLevel);
         } else if ("xz".equals(outputCodec)) {
            return CodecFactory.xzCodec(xzLevel);
         } else {
            return "zstandard".equals(outputCodec) ? CodecFactory.zstandardCodec(zstdLevel, false, zstdBufferPool) : CodecFactory.fromString(outputCodec);
         }
      } else {
         return CodecFactory.nullCodec();
      }
   }

   private Path getWorkPathFromCommitter(TaskAttemptContext context) throws IOException {
      OutputCommitter committer = this.getOutputCommitter(context);

      try {
         return (Path)committer.getClass().getMethod("getWorkPath").invoke(committer);
      } catch (ReflectiveOperationException e) {
         throw new AvroRuntimeException("Committer: " + committer.getClass().getName() + " does not have method getWorkPath", e);
      }
   }

   protected OutputStream getAvroFileOutputStream(TaskAttemptContext context) throws IOException {
      Path path = new Path(this.getWorkPathFromCommitter(context), getUniqueFile(context, context.getConfiguration().get("avro.mo.config.namedOutput", "part"), ".avro"));
      return path.getFileSystem(context.getConfiguration()).create(path);
   }

   protected static int getSyncInterval(TaskAttemptContext context) {
      return context.getConfiguration().getInt("avro.mapred.sync.interval", 64000);
   }
}
