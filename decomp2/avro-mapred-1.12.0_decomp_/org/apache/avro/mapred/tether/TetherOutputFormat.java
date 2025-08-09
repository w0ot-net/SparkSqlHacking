package org.apache.avro.mapred.tether;

import java.io.IOException;
import org.apache.avro.Schema;
import org.apache.avro.file.CodecFactory;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.mapred.AvroJob;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordWriter;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.util.Progressable;

class TetherOutputFormat extends FileOutputFormat {
   public static void setDeflateLevel(JobConf job, int level) {
      FileOutputFormat.setCompressOutput(job, true);
      job.setInt("avro.mapred.deflate.level", level);
   }

   public RecordWriter getRecordWriter(FileSystem ignore, JobConf job, String name, Progressable prog) throws IOException {
      Schema schema = AvroJob.getOutputSchema(job);
      final DataFileWriter writer = new DataFileWriter(new GenericDatumWriter());
      if (FileOutputFormat.getCompressOutput(job)) {
         int level = job.getInt("avro.mapred.deflate.level", -1);
         writer.setCodec(CodecFactory.deflateCodec(level));
      }

      Path path = FileOutputFormat.getTaskOutputPath(job, name + ".avro");
      writer.create(schema, path.getFileSystem(job).create(path));
      return new RecordWriter() {
         public void write(TetherData datum, NullWritable ignore) throws IOException {
            writer.appendEncoded(datum.buffer());
         }

         public void close(Reporter reporter) throws IOException {
            writer.close();
         }
      };
   }
}
