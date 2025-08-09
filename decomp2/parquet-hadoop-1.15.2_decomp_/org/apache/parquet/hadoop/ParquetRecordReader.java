package org.apache.parquet.hadoop;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.parquet.CorruptDeltaByteArrays;
import org.apache.parquet.HadoopReadOptions;
import org.apache.parquet.ParquetReadOptions;
import org.apache.parquet.column.Encoding;
import org.apache.parquet.filter.UnboundRecordFilter;
import org.apache.parquet.filter2.compat.FilterCompat;
import org.apache.parquet.hadoop.api.ReadSupport;
import org.apache.parquet.hadoop.metadata.BlockMetaData;
import org.apache.parquet.hadoop.metadata.ColumnChunkMetaData;
import org.apache.parquet.hadoop.metadata.FileMetaData;
import org.apache.parquet.hadoop.util.ContextUtil;
import org.apache.parquet.hadoop.util.HadoopInputFile;
import org.apache.parquet.hadoop.util.counters.BenchmarkCounter;
import org.apache.parquet.io.InputFile;
import org.apache.parquet.io.ParquetDecodingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParquetRecordReader extends RecordReader {
   private static final Logger LOG = LoggerFactory.getLogger(ParquetRecordReader.class);
   private final InternalParquetRecordReader internalReader;

   public ParquetRecordReader(ReadSupport readSupport) {
      this(readSupport, FilterCompat.NOOP);
   }

   public ParquetRecordReader(ReadSupport readSupport, FilterCompat.Filter filter) {
      this.internalReader = new InternalParquetRecordReader(readSupport, filter);
   }

   /** @deprecated */
   @Deprecated
   public ParquetRecordReader(ReadSupport readSupport, UnboundRecordFilter filter) {
      this(readSupport, FilterCompat.get(filter));
   }

   public void close() throws IOException {
      this.internalReader.close();
   }

   public Void getCurrentKey() throws IOException, InterruptedException {
      return null;
   }

   public Object getCurrentValue() throws IOException, InterruptedException {
      return this.internalReader.getCurrentValue();
   }

   public float getProgress() throws IOException, InterruptedException {
      return this.internalReader.getProgress();
   }

   public void initialize(InputSplit inputSplit, TaskAttemptContext context) throws IOException, InterruptedException {
      if (ContextUtil.hasCounterMethod(context)) {
         BenchmarkCounter.initCounterFromContext(context);
      } else {
         LOG.error(String.format("Can not initialize counter because the class '%s' does not have a '.getCounterMethod'", context.getClass().getCanonicalName()));
      }

      this.initializeInternalReader(this.toParquetSplit(inputSplit), ContextUtil.getConfiguration(context));
   }

   public void initialize(InputSplit inputSplit, Configuration configuration, Reporter reporter) throws IOException, InterruptedException {
      BenchmarkCounter.initCounterFromReporter(reporter, configuration);
      this.initializeInternalReader(this.toParquetSplit(inputSplit), configuration);
   }

   private void initializeInternalReader(ParquetInputSplit split, Configuration configuration) throws IOException {
      Path path = split.getPath();
      long[] rowGroupOffsets = split.getRowGroupOffsets();
      ParquetReadOptions.Builder optionsBuilder = HadoopReadOptions.builder(configuration, path);
      if (rowGroupOffsets != null) {
         optionsBuilder.withOffsets(rowGroupOffsets);
      } else {
         optionsBuilder.withRange(split.getStart(), split.getEnd());
      }

      ParquetFileReader reader = ParquetFileReader.open((InputFile)HadoopInputFile.fromPath(path, configuration), (ParquetReadOptions)optionsBuilder.build());
      if (rowGroupOffsets != null) {
         List<BlockMetaData> blocks = reader.getFooter().getBlocks();
         if (blocks.size() != rowGroupOffsets.length) {
            throw new IllegalStateException("All of the offsets in the split should be found in the file. expected: " + Arrays.toString(rowGroupOffsets) + " found: " + blocks);
         }
      }

      if (!reader.getRowGroups().isEmpty() && reader.getFileMetaData().getEncryptionType() != FileMetaData.EncryptionType.ENCRYPTED_FOOTER && reader.getFileMetaData().getEncryptionType() != FileMetaData.EncryptionType.PLAINTEXT_FOOTER) {
         this.checkDeltaByteArrayProblem(reader.getFooter().getFileMetaData(), configuration, (BlockMetaData)reader.getRowGroups().get(0));
      }

      this.internalReader.initialize(reader, configuration);
   }

   private void checkDeltaByteArrayProblem(FileMetaData meta, Configuration conf, BlockMetaData block) {
      if (conf.getBoolean("parquet.split.files", true)) {
         Set<Encoding> encodings = new HashSet();

         for(ColumnChunkMetaData column : block.getColumns()) {
            encodings.addAll(column.getEncodings());
         }

         for(Encoding encoding : encodings) {
            if (CorruptDeltaByteArrays.requiresSequentialReads(meta.getCreatedBy(), encoding)) {
               throw new ParquetDecodingException("Cannot read data due to PARQUET-246: to read safely, set parquet.split.files to false");
            }
         }
      }

   }

   public boolean nextKeyValue() throws IOException, InterruptedException {
      return this.internalReader.nextKeyValue();
   }

   public long getCurrentRowIndex() throws IOException {
      return this.internalReader.getCurrentRowIndex();
   }

   private ParquetInputSplit toParquetSplit(InputSplit split) throws IOException {
      if (split instanceof ParquetInputSplit) {
         return (ParquetInputSplit)split;
      } else if (split instanceof FileSplit) {
         return ParquetInputSplit.from((FileSplit)split);
      } else if (split instanceof org.apache.hadoop.mapred.FileSplit) {
         return ParquetInputSplit.from((org.apache.hadoop.mapred.FileSplit)split);
      } else {
         throw new IllegalArgumentException("Invalid split (not a FileSplit or ParquetInputSplit): " + split);
      }
   }
}
