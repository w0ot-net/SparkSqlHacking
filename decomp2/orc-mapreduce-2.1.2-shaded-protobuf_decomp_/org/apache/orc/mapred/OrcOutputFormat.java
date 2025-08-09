package org.apache.orc.mapred;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordWriter;
import org.apache.hadoop.util.Progressable;
import org.apache.orc.CompressionKind;
import org.apache.orc.OrcConf;
import org.apache.orc.OrcFile;
import org.apache.orc.TypeDescription;
import org.apache.orc.Writer;
import org.apache.orc.OrcFile.EncodingStrategy;
import org.apache.orc.OrcFile.Version;

public class OrcOutputFormat extends FileOutputFormat {
   public static OrcFile.WriterOptions buildOptions(Configuration conf) {
      return OrcFile.writerOptions(conf).version(Version.byName(OrcConf.WRITE_FORMAT.getString(conf))).setSchema(TypeDescription.fromString(OrcConf.MAPRED_OUTPUT_SCHEMA.getString(conf))).compress(CompressionKind.valueOf(OrcConf.COMPRESS.getString(conf))).encodingStrategy(EncodingStrategy.valueOf(OrcConf.ENCODING_STRATEGY.getString(conf))).bloomFilterColumns(OrcConf.BLOOM_FILTER_COLUMNS.getString(conf)).bloomFilterFpp(OrcConf.BLOOM_FILTER_FPP.getDouble(conf)).blockSize(OrcConf.BLOCK_SIZE.getLong(conf)).blockPadding(OrcConf.BLOCK_PADDING.getBoolean(conf)).stripeSize(OrcConf.STRIPE_SIZE.getLong(conf)).rowIndexStride((int)OrcConf.ROW_INDEX_STRIDE.getLong(conf)).bufferSize((int)OrcConf.BUFFER_SIZE.getLong(conf)).paddingTolerance(OrcConf.BLOCK_PADDING_TOLERANCE.getDouble(conf)).encrypt(OrcConf.ENCRYPTION.getString(conf)).masks(OrcConf.DATA_MASK.getString(conf));
   }

   public RecordWriter getRecordWriter(FileSystem fileSystem, JobConf conf, String name, Progressable progressable) throws IOException {
      Path path = getTaskOutputPath(conf, name);
      Writer writer = OrcFile.createWriter(path, buildOptions(conf).fileSystem(fileSystem));
      return new OrcMapredRecordWriter(writer, OrcConf.ROW_BATCH_SIZE.getInt(conf), OrcConf.ROW_BATCH_CHILD_LIMIT.getInt(conf));
   }
}
