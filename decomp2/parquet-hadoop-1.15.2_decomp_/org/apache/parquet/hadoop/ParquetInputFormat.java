package org.apache.parquet.hadoop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.parquet.Preconditions;
import org.apache.parquet.conf.HadoopParquetConfiguration;
import org.apache.parquet.conf.ParquetConfiguration;
import org.apache.parquet.filter.UnboundRecordFilter;
import org.apache.parquet.filter2.compat.FilterCompat;
import org.apache.parquet.filter2.predicate.FilterPredicate;
import org.apache.parquet.hadoop.api.InitContext;
import org.apache.parquet.hadoop.api.ReadSupport;
import org.apache.parquet.hadoop.metadata.GlobalMetaData;
import org.apache.parquet.hadoop.util.ConfigurationUtil;
import org.apache.parquet.hadoop.util.ContextUtil;
import org.apache.parquet.hadoop.util.HiddenFileFilter;
import org.apache.parquet.hadoop.util.SerializationUtil;
import org.apache.parquet.io.ParquetDecodingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParquetInputFormat extends FileInputFormat {
   private static final Logger LOG = LoggerFactory.getLogger(ParquetInputFormat.class);
   public static final String READ_SUPPORT_CLASS = "parquet.read.support.class";
   public static final String UNBOUND_RECORD_FILTER = "parquet.read.filter";
   public static final String STRICT_TYPE_CHECKING = "parquet.strict.typing";
   public static final String FILTER_PREDICATE = "parquet.private.read.filter.predicate";
   public static final String RECORD_FILTERING_ENABLED = "parquet.filter.record-level.enabled";
   public static final String STATS_FILTERING_ENABLED = "parquet.filter.stats.enabled";
   public static final String DICTIONARY_FILTERING_ENABLED = "parquet.filter.dictionary.enabled";
   public static final String COLUMN_INDEX_FILTERING_ENABLED = "parquet.filter.columnindex.enabled";
   public static final String PAGE_VERIFY_CHECKSUM_ENABLED = "parquet.page.verify-checksum.enabled";
   public static final String BLOOM_FILTERING_ENABLED = "parquet.filter.bloom.enabled";
   public static final String OFF_HEAP_DECRYPT_BUFFER_ENABLED = "parquet.decrypt.off-heap.buffer.enabled";
   public static final String TASK_SIDE_METADATA = "parquet.task.side.metadata";
   public static final String SPLIT_FILES = "parquet.split.files";
   private static final int MIN_FOOTER_CACHE_SIZE = 100;
   public static final String HADOOP_VECTORED_IO_ENABLED = "parquet.hadoop.vectored.io.enabled";
   public static final boolean HADOOP_VECTORED_IO_DEFAULT = false;
   private LruCache footersCache;
   private final Class readSupportClass;

   public static void setTaskSideMetaData(Job job, boolean taskSideMetadata) {
      ContextUtil.getConfiguration(job).setBoolean("parquet.task.side.metadata", taskSideMetadata);
   }

   public static boolean isTaskSideMetaData(Configuration configuration) {
      return configuration.getBoolean("parquet.task.side.metadata", Boolean.TRUE);
   }

   public static void setReadSupportClass(Job job, Class readSupportClass) {
      ContextUtil.getConfiguration(job).set("parquet.read.support.class", readSupportClass.getName());
   }

   public static void setUnboundRecordFilter(Job job, Class filterClass) {
      Configuration conf = ContextUtil.getConfiguration(job);
      Preconditions.checkArgument(getFilterPredicate(conf) == null, "You cannot provide an UnboundRecordFilter after providing a FilterPredicate");
      conf.set("parquet.read.filter", filterClass.getName());
   }

   /** @deprecated */
   @Deprecated
   public static Class getUnboundRecordFilter(Configuration configuration) {
      return ConfigurationUtil.getClassFromConfig(configuration, "parquet.read.filter", UnboundRecordFilter.class);
   }

   private static UnboundRecordFilter getUnboundRecordFilterInstance(ParquetConfiguration configuration) {
      Class<?> clazz = ConfigurationUtil.getClassFromConfig(configuration, "parquet.read.filter", UnboundRecordFilter.class);
      if (clazz == null) {
         return null;
      } else {
         try {
            return (UnboundRecordFilter)clazz.newInstance();
         } catch (IllegalAccessException | InstantiationException e) {
            throw new BadConfigurationException("could not instantiate unbound record filter class", e);
         }
      }
   }

   public static void setReadSupportClass(JobConf conf, Class readSupportClass) {
      conf.set("parquet.read.support.class", readSupportClass.getName());
   }

   public static Class getReadSupportClass(Configuration configuration) {
      return ConfigurationUtil.getClassFromConfig(configuration, "parquet.read.support.class", ReadSupport.class);
   }

   public static void setFilterPredicate(Configuration configuration, FilterPredicate filterPredicate) {
      Preconditions.checkArgument(getUnboundRecordFilter(configuration) == null, "You cannot provide a FilterPredicate after providing an UnboundRecordFilter");
      configuration.set("parquet.private.read.filter.predicate.human.readable", filterPredicate.toString());

      try {
         SerializationUtil.writeObjectToConfAsBase64("parquet.private.read.filter.predicate", filterPredicate, configuration);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private static FilterPredicate getFilterPredicate(Configuration configuration) {
      return getFilterPredicate((ParquetConfiguration)(new HadoopParquetConfiguration(configuration)));
   }

   private static FilterPredicate getFilterPredicate(ParquetConfiguration configuration) {
      try {
         return (FilterPredicate)SerializationUtil.readObjectFromConfAsBase64("parquet.private.read.filter.predicate", configuration);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   public static FilterCompat.Filter getFilter(Configuration conf) {
      return getFilter((ParquetConfiguration)(new HadoopParquetConfiguration(conf)));
   }

   public static FilterCompat.Filter getFilter(ParquetConfiguration conf) {
      return FilterCompat.get(getFilterPredicate(conf), getUnboundRecordFilterInstance(conf));
   }

   public ParquetInputFormat() {
      this.readSupportClass = null;
   }

   public ParquetInputFormat(Class readSupportClass) {
      this.readSupportClass = readSupportClass;
   }

   public RecordReader createRecordReader(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
      Configuration conf = ContextUtil.getConfiguration(taskAttemptContext);
      ReadSupport<T> readSupport = this.getReadSupport(conf);
      return new ParquetRecordReader(readSupport, getFilter(conf));
   }

   /** @deprecated */
   @Deprecated
   ReadSupport getReadSupport(Configuration configuration) {
      return getReadSupportInstance(this.readSupportClass == null ? getReadSupportClass(configuration) : this.readSupportClass);
   }

   public static ReadSupport getReadSupportInstance(Configuration configuration) {
      return getReadSupportInstance(getReadSupportClass(configuration));
   }

   static ReadSupport getReadSupportInstance(Class readSupportClass) {
      try {
         return (ReadSupport)readSupportClass.newInstance();
      } catch (IllegalAccessException | InstantiationException e) {
         throw new BadConfigurationException("could not instantiate read support class", e);
      }
   }

   protected boolean isSplitable(JobContext context, Path filename) {
      return ContextUtil.getConfiguration(context).getBoolean("parquet.split.files", true);
   }

   public List getSplits(JobContext jobContext) throws IOException {
      Configuration configuration = ContextUtil.getConfiguration(jobContext);
      List<InputSplit> splits = new ArrayList();
      if (!isTaskSideMetaData(configuration)) {
         splits.addAll(this.getSplits(configuration, this.getFooters(jobContext)));
         return splits;
      } else {
         for(InputSplit split : super.getSplits(jobContext)) {
            Preconditions.checkArgument(split instanceof FileSplit, "Cannot wrap non-FileSplit: %s", split);
            splits.add(ParquetInputSplit.from((FileSplit)split));
         }

         return splits;
      }
   }

   /** @deprecated */
   @Deprecated
   public List getSplits(Configuration configuration, List footers) throws IOException {
      boolean strictTypeChecking = configuration.getBoolean("parquet.strict.typing", true);
      long maxSplitSize = configuration.getLong("mapred.max.split.size", Long.MAX_VALUE);
      long minSplitSize = Math.max(this.getFormatMinSplitSize(), configuration.getLong("mapred.min.split.size", 0L));
      if (maxSplitSize >= 0L && minSplitSize >= 0L) {
         GlobalMetaData globalMetaData = ParquetFileWriter.getGlobalMetaData(footers, strictTypeChecking);
         ReadSupport.ReadContext readContext = this.getReadSupport(configuration).init(new InitContext(configuration, globalMetaData.getKeyValueMetaData(), globalMetaData.getSchema()));
         return (new ClientSideMetadataSplitStrategy()).getSplits(configuration, footers, maxSplitSize, minSplitSize, readContext);
      } else {
         throw new ParquetDecodingException("maxSplitSize or minSplitSize should not be negative: maxSplitSize = " + maxSplitSize + "; minSplitSize = " + minSplitSize);
      }
   }

   protected List listStatus(JobContext jobContext) throws IOException {
      return getAllFileRecursively(super.listStatus(jobContext), ContextUtil.getConfiguration(jobContext));
   }

   private static List getAllFileRecursively(List files, Configuration conf) throws IOException {
      List<FileStatus> result = new ArrayList();

      for(FileStatus file : files) {
         if (file.isDir()) {
            Path p = file.getPath();
            FileSystem fs = p.getFileSystem(conf);
            staticAddInputPathRecursively(result, fs, p, HiddenFileFilter.INSTANCE);
         } else {
            result.add(file);
         }
      }

      LOG.info("Total input paths to process : {}", result.size());
      return result;
   }

   private static void staticAddInputPathRecursively(List result, FileSystem fs, Path path, PathFilter inputFilter) throws IOException {
      for(FileStatus stat : fs.listStatus(path, inputFilter)) {
         if (stat.isDir()) {
            staticAddInputPathRecursively(result, fs, stat.getPath(), inputFilter);
         } else {
            result.add(stat);
         }
      }

   }

   public List getFooters(JobContext jobContext) throws IOException {
      List<FileStatus> statuses = this.listStatus(jobContext);
      if (statuses.isEmpty()) {
         return Collections.emptyList();
      } else {
         Configuration config = ContextUtil.getConfiguration(jobContext);
         Map<FileStatusWrapper, Footer> footersMap = new LinkedHashMap();
         Set<FileStatus> missingStatuses = new HashSet();
         Map<Path, FileStatusWrapper> missingStatusesMap = new HashMap(missingStatuses.size());
         if (this.footersCache == null) {
            this.footersCache = new LruCache(Math.max(statuses.size(), 100));
         }

         for(FileStatus status : statuses) {
            FileStatusWrapper statusWrapper = new FileStatusWrapper(status);
            FootersCacheValue cacheEntry = (FootersCacheValue)this.footersCache.getCurrentValue(statusWrapper);
            if (LOG.isDebugEnabled()) {
               LOG.debug("Cache entry " + (cacheEntry == null ? "not " : "") + " found for '" + status.getPath() + "'");
            }

            if (cacheEntry != null) {
               footersMap.put(statusWrapper, cacheEntry.getFooter());
            } else {
               footersMap.put(statusWrapper, (Object)null);
               missingStatuses.add(status);
               missingStatusesMap.put(status.getPath(), statusWrapper);
            }
         }

         LOG.debug("found {} footers in cache and adding up to {} missing footers to the cache", footersMap.size(), missingStatuses.size());
         if (!missingStatuses.isEmpty()) {
            for(Footer newFooter : this.getFooters(config, (Collection)missingStatuses)) {
               FileStatusWrapper fileStatus = (FileStatusWrapper)missingStatusesMap.get(newFooter.getFile());
               this.footersCache.put(fileStatus, new FootersCacheValue(fileStatus, newFooter));
            }
         }

         List<Footer> footers = new ArrayList(statuses.size());

         for(Map.Entry footerEntry : footersMap.entrySet()) {
            Footer footer = (Footer)footerEntry.getValue();
            if (footer == null) {
               footers.add(((FootersCacheValue)this.footersCache.getCurrentValue(footerEntry.getKey())).getFooter());
            } else {
               footers.add(footer);
            }
         }

         return footers;
      }
   }

   public List getFooters(Configuration configuration, List statuses) throws IOException {
      return this.getFooters(configuration, (Collection)statuses);
   }

   public List getFooters(Configuration configuration, Collection statuses) throws IOException {
      LOG.debug("reading {} files", statuses.size());
      boolean taskSideMetaData = isTaskSideMetaData(configuration);
      return ParquetFileReader.readAllFootersInParallelUsingSummaryFiles(configuration, statuses, taskSideMetaData);
   }

   public GlobalMetaData getGlobalMetaData(JobContext jobContext) throws IOException {
      return ParquetFileWriter.getGlobalMetaData(this.getFooters(jobContext));
   }

   static final class FootersCacheValue implements LruCache.Value {
      private final long modificationTime;
      private final Footer footer;

      public FootersCacheValue(FileStatusWrapper status, Footer footer) {
         this.modificationTime = status.getModificationTime();
         this.footer = new Footer(footer.getFile(), footer.getParquetMetadata());
      }

      public boolean isCurrent(FileStatusWrapper key) {
         long currentModTime = key.getModificationTime();
         boolean isCurrent = this.modificationTime >= currentModTime;
         if (ParquetInputFormat.LOG.isDebugEnabled() && !isCurrent) {
            ParquetInputFormat.LOG.debug("The cache value for '{}' is not current: cached modification time={}, current modification time: {}", new Object[]{key, this.modificationTime, currentModTime});
         }

         return isCurrent;
      }

      public Footer getFooter() {
         return this.footer;
      }

      public boolean isNewerThan(FootersCacheValue otherValue) {
         return otherValue == null || this.modificationTime > otherValue.modificationTime;
      }

      public Path getPath() {
         return this.footer.getFile();
      }
   }

   static final class FileStatusWrapper {
      private final FileStatus status;

      public FileStatusWrapper(FileStatus fileStatus) {
         if (fileStatus == null) {
            throw new IllegalArgumentException("FileStatus object cannot be null");
         } else {
            this.status = fileStatus;
         }
      }

      public long getModificationTime() {
         return this.status.getModificationTime();
      }

      public int hashCode() {
         return this.status.hashCode();
      }

      public boolean equals(Object other) {
         return other instanceof FileStatusWrapper && this.status.equals(((FileStatusWrapper)other).status);
      }

      public String toString() {
         return this.status.getPath().toString();
      }
   }
}
