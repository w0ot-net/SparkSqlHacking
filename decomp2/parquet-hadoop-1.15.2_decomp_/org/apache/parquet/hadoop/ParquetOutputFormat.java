package org.apache.parquet.hadoop;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Function;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.OutputCommitter;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.parquet.column.ParquetProperties;
import org.apache.parquet.column.ParquetProperties.WriterVersion;
import org.apache.parquet.crypto.FileEncryptionProperties;
import org.apache.parquet.hadoop.api.WriteSupport;
import org.apache.parquet.hadoop.codec.CodecConfig;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.apache.parquet.hadoop.util.ConfigurationUtil;
import org.apache.parquet.hadoop.util.ContextUtil;
import org.apache.parquet.hadoop.util.HadoopOutputFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParquetOutputFormat extends FileOutputFormat {
   private static final Logger LOG = LoggerFactory.getLogger(ParquetOutputFormat.class);
   /** @deprecated */
   @Deprecated
   public static final String ENABLE_JOB_SUMMARY = "parquet.enable.summary-metadata";
   public static final String JOB_SUMMARY_LEVEL = "parquet.summary.metadata.level";
   public static final String BLOCK_SIZE = "parquet.block.size";
   public static final String PAGE_SIZE = "parquet.page.size";
   public static final String COMPRESSION = "parquet.compression";
   public static final String WRITE_SUPPORT_CLASS = "parquet.write.support.class";
   public static final String DICTIONARY_PAGE_SIZE = "parquet.dictionary.page.size";
   public static final String ENABLE_DICTIONARY = "parquet.enable.dictionary";
   public static final String VALIDATION = "parquet.validation";
   public static final String WRITER_VERSION = "parquet.writer.version";
   public static final String MEMORY_POOL_RATIO = "parquet.memory.pool.ratio";
   public static final String MIN_MEMORY_ALLOCATION = "parquet.memory.min.chunk.size";
   public static final String MAX_PADDING_BYTES = "parquet.writer.max-padding";
   public static final String MIN_ROW_COUNT_FOR_PAGE_SIZE_CHECK = "parquet.page.size.row.check.min";
   public static final String MAX_ROW_COUNT_FOR_PAGE_SIZE_CHECK = "parquet.page.size.row.check.max";
   public static final String PAGE_VALUE_COUNT_THRESHOLD = "parquet.page.value.count.threshold";
   public static final String ESTIMATE_PAGE_SIZE_CHECK = "parquet.page.size.check.estimate";
   public static final String COLUMN_INDEX_TRUNCATE_LENGTH = "parquet.columnindex.truncate.length";
   public static final String STATISTICS_TRUNCATE_LENGTH = "parquet.statistics.truncate.length";
   public static final String BLOOM_FILTER_ENABLED = "parquet.bloom.filter.enabled";
   public static final String BLOOM_FILTER_EXPECTED_NDV = "parquet.bloom.filter.expected.ndv";
   public static final String BLOOM_FILTER_MAX_BYTES = "parquet.bloom.filter.max.bytes";
   public static final String BLOOM_FILTER_FPP = "parquet.bloom.filter.fpp";
   public static final String ADAPTIVE_BLOOM_FILTER_ENABLED = "parquet.bloom.filter.adaptive.enabled";
   public static final String BLOOM_FILTER_CANDIDATES_NUMBER = "parquet.bloom.filter.candidates.number";
   public static final String PAGE_ROW_COUNT_LIMIT = "parquet.page.row.count.limit";
   public static final String PAGE_WRITE_CHECKSUM_ENABLED = "parquet.page.write-checksum.enabled";
   public static final String STATISTICS_ENABLED = "parquet.column.statistics.enabled";
   public static final String SIZE_STATISTICS_ENABLED = "parquet.size.statistics.enabled";
   private WriteSupport writeSupport;
   private ParquetOutputCommitter committer;
   private static MemoryManager memoryManager;

   public static JobSummaryLevel getJobSummaryLevel(Configuration conf) {
      String level = conf.get("parquet.summary.metadata.level");
      String deprecatedFlag = conf.get("parquet.enable.summary-metadata");
      if (deprecatedFlag != null) {
         LOG.warn("Setting parquet.enable.summary-metadata is deprecated, please use parquet.summary.metadata.level");
      }

      if (level != null && deprecatedFlag != null) {
         LOG.warn("Both parquet.summary.metadata.level and parquet.enable.summary-metadata are set! parquet.enable.summary-metadata will be ignored.");
      }

      if (level != null) {
         return ParquetOutputFormat.JobSummaryLevel.valueOf(level.toUpperCase());
      } else if (deprecatedFlag != null) {
         return Boolean.parseBoolean(deprecatedFlag) ? ParquetOutputFormat.JobSummaryLevel.ALL : ParquetOutputFormat.JobSummaryLevel.NONE;
      } else {
         return ParquetOutputFormat.JobSummaryLevel.ALL;
      }
   }

   public static void setWriteSupportClass(Job job, Class writeSupportClass) {
      ContextUtil.getConfiguration(job).set("parquet.write.support.class", writeSupportClass.getName());
   }

   public static void setWriteSupportClass(JobConf job, Class writeSupportClass) {
      job.set("parquet.write.support.class", writeSupportClass.getName());
   }

   public static Class getWriteSupportClass(Configuration configuration) {
      String className = configuration.get("parquet.write.support.class");
      if (className == null) {
         return null;
      } else {
         Class<?> writeSupportClass = ConfigurationUtil.getClassFromConfig(configuration, "parquet.write.support.class", WriteSupport.class);
         return writeSupportClass;
      }
   }

   public static void setBlockSize(Job job, int blockSize) {
      ContextUtil.getConfiguration(job).setInt("parquet.block.size", blockSize);
   }

   public static void setPageSize(Job job, int pageSize) {
      ContextUtil.getConfiguration(job).setInt("parquet.page.size", pageSize);
   }

   public static void setDictionaryPageSize(Job job, int pageSize) {
      ContextUtil.getConfiguration(job).setInt("parquet.dictionary.page.size", pageSize);
   }

   public static void setCompression(Job job, CompressionCodecName compression) {
      ContextUtil.getConfiguration(job).set("parquet.compression", compression.name());
   }

   public static void setEnableDictionary(Job job, boolean enableDictionary) {
      ContextUtil.getConfiguration(job).setBoolean("parquet.enable.dictionary", enableDictionary);
   }

   public static boolean getEnableDictionary(JobContext jobContext) {
      return getEnableDictionary(ContextUtil.getConfiguration(jobContext));
   }

   public static int getBloomFilterMaxBytes(Configuration conf) {
      return conf.getInt("parquet.bloom.filter.max.bytes", 1048576);
   }

   public static boolean getBloomFilterEnabled(Configuration conf) {
      return conf.getBoolean("parquet.bloom.filter.enabled", false);
   }

   public static boolean getAdaptiveBloomFilterEnabled(Configuration conf) {
      return conf.getBoolean("parquet.bloom.filter.adaptive.enabled", false);
   }

   public static int getBlockSize(JobContext jobContext) {
      return getBlockSize(ContextUtil.getConfiguration(jobContext));
   }

   public static int getPageSize(JobContext jobContext) {
      return getPageSize(ContextUtil.getConfiguration(jobContext));
   }

   public static int getDictionaryPageSize(JobContext jobContext) {
      return getDictionaryPageSize(ContextUtil.getConfiguration(jobContext));
   }

   public static CompressionCodecName getCompression(JobContext jobContext) {
      return getCompression(ContextUtil.getConfiguration(jobContext));
   }

   public static boolean isCompressionSet(JobContext jobContext) {
      return isCompressionSet(ContextUtil.getConfiguration(jobContext));
   }

   public static void setValidation(JobContext jobContext, boolean validating) {
      setValidation(ContextUtil.getConfiguration(jobContext), validating);
   }

   public static boolean getValidation(JobContext jobContext) {
      return getValidation(ContextUtil.getConfiguration(jobContext));
   }

   public static boolean getEnableDictionary(Configuration configuration) {
      return configuration.getBoolean("parquet.enable.dictionary", true);
   }

   public static int getMinRowCountForPageSizeCheck(Configuration configuration) {
      return configuration.getInt("parquet.page.size.row.check.min", 100);
   }

   public static int getMaxRowCountForPageSizeCheck(Configuration configuration) {
      return configuration.getInt("parquet.page.size.row.check.max", 10000);
   }

   public static int getValueCountThreshold(Configuration configuration) {
      return configuration.getInt("parquet.page.value.count.threshold", 1073741823);
   }

   public static boolean getEstimatePageSizeCheck(Configuration configuration) {
      return configuration.getBoolean("parquet.page.size.check.estimate", true);
   }

   /** @deprecated */
   @Deprecated
   public static int getBlockSize(Configuration configuration) {
      return configuration.getInt("parquet.block.size", 134217728);
   }

   public static long getLongBlockSize(Configuration configuration) {
      return configuration.getLong("parquet.block.size", 134217728L);
   }

   public static int getPageSize(Configuration configuration) {
      return configuration.getInt("parquet.page.size", 1048576);
   }

   public static int getDictionaryPageSize(Configuration configuration) {
      return configuration.getInt("parquet.dictionary.page.size", 1048576);
   }

   public static ParquetProperties.WriterVersion getWriterVersion(Configuration configuration) {
      String writerVersion = configuration.get("parquet.writer.version", ParquetProperties.DEFAULT_WRITER_VERSION.toString());
      return WriterVersion.fromString(writerVersion);
   }

   public static CompressionCodecName getCompression(Configuration configuration) {
      return CodecConfig.getParquetCompressionCodec(configuration);
   }

   public static boolean isCompressionSet(Configuration configuration) {
      return CodecConfig.isParquetCompressionSet(configuration);
   }

   public static void setValidation(Configuration configuration, boolean validating) {
      configuration.setBoolean("parquet.validation", validating);
   }

   public static boolean getValidation(Configuration configuration) {
      return configuration.getBoolean("parquet.validation", false);
   }

   private CompressionCodecName getCodec(TaskAttemptContext taskAttemptContext) {
      return CodecConfig.from(taskAttemptContext).getCodec();
   }

   public static void setMaxPaddingSize(JobContext jobContext, int maxPaddingSize) {
      setMaxPaddingSize(ContextUtil.getConfiguration(jobContext), maxPaddingSize);
   }

   public static void setMaxPaddingSize(Configuration conf, int maxPaddingSize) {
      conf.setInt("parquet.writer.max-padding", maxPaddingSize);
   }

   private static int getMaxPaddingSize(Configuration conf) {
      return conf.getInt("parquet.writer.max-padding", 8388608);
   }

   public static void setColumnIndexTruncateLength(JobContext jobContext, int length) {
      setColumnIndexTruncateLength(ContextUtil.getConfiguration(jobContext), length);
   }

   public static void setColumnIndexTruncateLength(Configuration conf, int length) {
      conf.setInt("parquet.columnindex.truncate.length", length);
   }

   private static int getColumnIndexTruncateLength(Configuration conf) {
      return conf.getInt("parquet.columnindex.truncate.length", 64);
   }

   public static void setStatisticsTruncateLength(JobContext jobContext, int length) {
      setStatisticsTruncateLength(ContextUtil.getConfiguration(jobContext), length);
   }

   private static void setStatisticsTruncateLength(Configuration conf, int length) {
      conf.setInt("parquet.statistics.truncate.length", length);
   }

   private static int getStatisticsTruncateLength(Configuration conf) {
      return conf.getInt("parquet.statistics.truncate.length", Integer.MAX_VALUE);
   }

   public static void setPageRowCountLimit(JobContext jobContext, int rowCount) {
      setPageRowCountLimit(ContextUtil.getConfiguration(jobContext), rowCount);
   }

   public static void setPageRowCountLimit(Configuration conf, int rowCount) {
      conf.setInt("parquet.page.row.count.limit", rowCount);
   }

   private static int getPageRowCountLimit(Configuration conf) {
      return conf.getInt("parquet.page.row.count.limit", 20000);
   }

   public static void setPageWriteChecksumEnabled(JobContext jobContext, boolean val) {
      setPageWriteChecksumEnabled(ContextUtil.getConfiguration(jobContext), val);
   }

   public static void setPageWriteChecksumEnabled(Configuration conf, boolean val) {
      conf.setBoolean("parquet.page.write-checksum.enabled", val);
   }

   public static boolean getPageWriteChecksumEnabled(Configuration conf) {
      return conf.getBoolean("parquet.page.write-checksum.enabled", true);
   }

   public static void setStatisticsEnabled(JobContext jobContext, boolean enabled) {
      ContextUtil.getConfiguration(jobContext).setBoolean("parquet.column.statistics.enabled", enabled);
   }

   public static boolean getStatisticsEnabled(Configuration conf) {
      return conf.getBoolean("parquet.column.statistics.enabled", true);
   }

   public static void setStatisticsEnabled(JobContext jobContext, String columnPath, boolean enabled) {
      ContextUtil.getConfiguration(jobContext).set("parquet.column.statistics.enabled#" + columnPath, String.valueOf(enabled));
   }

   public static boolean getStatisticsEnabled(Configuration conf, String columnPath) {
      String columnSpecific = conf.get("parquet.column.statistics.enabled#" + columnPath);
      return columnSpecific != null ? Boolean.parseBoolean(columnSpecific) : conf.getBoolean("parquet.column.statistics.enabled", true);
   }

   public static void setSizeStatisticsEnabled(Configuration conf, boolean enabled) {
      conf.setBoolean("parquet.size.statistics.enabled", enabled);
   }

   public static void setSizeStatisticsEnabled(Configuration conf, String path, boolean enabled) {
      conf.setBoolean("parquet.size.statistics.enabled#" + path, enabled);
   }

   public static boolean getSizeStatisticsEnabled(Configuration conf) {
      return conf.getBoolean("parquet.size.statistics.enabled", true);
   }

   public static boolean getSizeStatisticsEnabled(Configuration conf, String path) {
      return conf.getBoolean("parquet.size.statistics.enabled#" + path, getSizeStatisticsEnabled(conf));
   }

   public ParquetOutputFormat(WriteSupport writeSupport) {
      this.writeSupport = writeSupport;
   }

   public ParquetOutputFormat() {
   }

   public RecordWriter getRecordWriter(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
      return this.getRecordWriter(taskAttemptContext, ParquetFileWriter.Mode.CREATE);
   }

   public RecordWriter getRecordWriter(TaskAttemptContext taskAttemptContext, ParquetFileWriter.Mode mode) throws IOException, InterruptedException {
      Configuration conf = ContextUtil.getConfiguration(taskAttemptContext);
      CompressionCodecName codec = this.getCodec(taskAttemptContext);
      String extension = codec.getExtension() + ".parquet";
      Path file = this.getDefaultWorkFile(taskAttemptContext, extension);
      return this.getRecordWriter(conf, file, codec, mode);
   }

   public RecordWriter getRecordWriter(TaskAttemptContext taskAttemptContext, Path file) throws IOException, InterruptedException {
      return this.getRecordWriter(taskAttemptContext, file, ParquetFileWriter.Mode.CREATE);
   }

   public RecordWriter getRecordWriter(TaskAttemptContext taskAttemptContext, Path file, ParquetFileWriter.Mode mode) throws IOException, InterruptedException {
      return this.getRecordWriter(ContextUtil.getConfiguration(taskAttemptContext), file, this.getCodec(taskAttemptContext), mode);
   }

   public RecordWriter getRecordWriter(Configuration conf, Path file, CompressionCodecName codec) throws IOException, InterruptedException {
      return this.getRecordWriter(conf, file, codec, ParquetFileWriter.Mode.CREATE);
   }

   public RecordWriter getRecordWriter(Configuration conf, Path file, CompressionCodecName codec, ParquetFileWriter.Mode mode) throws IOException, InterruptedException {
      WriteSupport<T> writeSupport = this.getWriteSupport(conf);
      ParquetProperties.Builder propsBuilder = ParquetProperties.builder().withPageSize(getPageSize(conf)).withDictionaryPageSize(getDictionaryPageSize(conf)).withDictionaryEncoding(getEnableDictionary(conf)).withWriterVersion(getWriterVersion(conf)).estimateRowCountForPageSizeCheck(getEstimatePageSizeCheck(conf)).withMinRowCountForPageSizeCheck(getMinRowCountForPageSizeCheck(conf)).withMaxRowCountForPageSizeCheck(getMaxRowCountForPageSizeCheck(conf)).withPageValueCountThreshold(getValueCountThreshold(conf)).withColumnIndexTruncateLength(getColumnIndexTruncateLength(conf)).withStatisticsTruncateLength(getStatisticsTruncateLength(conf)).withMaxBloomFilterBytes(getBloomFilterMaxBytes(conf)).withBloomFilterEnabled(getBloomFilterEnabled(conf)).withAdaptiveBloomFilterEnabled(getAdaptiveBloomFilterEnabled(conf)).withPageRowCountLimit(getPageRowCountLimit(conf)).withPageWriteChecksumEnabled(getPageWriteChecksumEnabled(conf)).withStatisticsEnabled(getStatisticsEnabled(conf));
      ColumnConfigParser var10000 = new ColumnConfigParser();
      Function var10002 = (key) -> conf.getBoolean(key, false);
      propsBuilder.getClass();
      var10000 = var10000.withColumnConfig("parquet.enable.dictionary", var10002, propsBuilder::withDictionaryEncoding);
      var10002 = (key) -> conf.getBoolean(key, false);
      propsBuilder.getClass();
      var10000 = var10000.withColumnConfig("parquet.bloom.filter.enabled", var10002, propsBuilder::withBloomFilterEnabled);
      var10002 = (key) -> conf.getLong(key, -1L);
      propsBuilder.getClass();
      var10000 = var10000.withColumnConfig("parquet.bloom.filter.expected.ndv", var10002, propsBuilder::withBloomFilterNDV);
      var10002 = (key) -> conf.getDouble(key, 0.01);
      propsBuilder.getClass();
      var10000 = var10000.withColumnConfig("parquet.bloom.filter.fpp", var10002, propsBuilder::withBloomFilterFPP);
      var10002 = (key) -> conf.getInt(key, 5);
      propsBuilder.getClass();
      var10000 = var10000.withColumnConfig("parquet.bloom.filter.candidates.number", var10002, propsBuilder::withBloomFilterCandidatesNumber);
      var10002 = (key) -> conf.getBoolean(key, true);
      propsBuilder.getClass();
      var10000.withColumnConfig("parquet.column.statistics.enabled", var10002, propsBuilder::withStatisticsEnabled).parseConfig(conf);
      ParquetProperties props = propsBuilder.build();
      long blockSize = getLongBlockSize(conf);
      int maxPaddingSize = getMaxPaddingSize(conf);
      boolean validating = getValidation(conf);
      LOG.info("ParquetRecordWriter [block size: {}b, row group padding size: {}b, validating: {}]", new Object[]{blockSize, maxPaddingSize, validating});
      LOG.debug("Parquet properties are:\n{}", props);
      WriteSupport.WriteContext fileWriteContext = writeSupport.init(conf);
      FileEncryptionProperties encryptionProperties = createEncryptionProperties(conf, file, fileWriteContext);
      ParquetFileWriter w = new ParquetFileWriter(HadoopOutputFile.fromPath(file, conf), fileWriteContext.getSchema(), mode, blockSize, maxPaddingSize, encryptionProperties, props);
      w.start();
      float maxLoad = conf.getFloat("parquet.memory.pool.ratio", 0.95F);
      long minAllocation = conf.getLong("parquet.memory.min.chunk.size", 1048576L);
      synchronized(ParquetOutputFormat.class) {
         if (memoryManager == null) {
            memoryManager = new MemoryManager(maxLoad, minAllocation);
         }
      }

      if (memoryManager.getMemoryPoolRatio() != maxLoad) {
         LOG.warn("The configuration parquet.memory.pool.ratio has been set. It should not be reset by the new value: " + maxLoad);
      }

      return new ParquetRecordWriter(w, writeSupport, fileWriteContext.getSchema(), fileWriteContext.getExtraMetaData(), blockSize, codec, validating, props, memoryManager, conf);
   }

   public WriteSupport getWriteSupport(Configuration configuration) {
      if (this.writeSupport != null) {
         return this.writeSupport;
      } else {
         Class<?> writeSupportClass = getWriteSupportClass(configuration);

         try {
            return (WriteSupport)((Class)Objects.requireNonNull(writeSupportClass, "writeSupportClass cannot be null")).newInstance();
         } catch (IllegalAccessException | InstantiationException e) {
            throw new BadConfigurationException("could not instantiate write support class: " + writeSupportClass, e);
         }
      }
   }

   public OutputCommitter getOutputCommitter(TaskAttemptContext context) throws IOException {
      if (this.committer == null) {
         Path output = getOutputPath(context);
         this.committer = new ParquetOutputCommitter(output, context);
      }

      return this.committer;
   }

   public static synchronized MemoryManager getMemoryManager() {
      return memoryManager;
   }

   public static FileEncryptionProperties createEncryptionProperties(Configuration fileHadoopConfig, Path tempFilePath, WriteSupport.WriteContext fileWriteContext) {
      return EncryptionPropertiesHelper.createEncryptionProperties(fileHadoopConfig, tempFilePath, fileWriteContext);
   }

   public static enum JobSummaryLevel {
      NONE,
      ALL,
      COMMON_ONLY;
   }
}
