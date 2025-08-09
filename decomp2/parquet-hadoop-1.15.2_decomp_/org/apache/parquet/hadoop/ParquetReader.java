package org.apache.parquet.hadoop;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.HadoopReadOptions;
import org.apache.parquet.ParquetReadOptions;
import org.apache.parquet.Preconditions;
import org.apache.parquet.bytes.ByteBufferAllocator;
import org.apache.parquet.bytes.HeapByteBufferAllocator;
import org.apache.parquet.compression.CompressionCodecFactory;
import org.apache.parquet.conf.HadoopParquetConfiguration;
import org.apache.parquet.conf.ParquetConfiguration;
import org.apache.parquet.crypto.FileDecryptionProperties;
import org.apache.parquet.filter.UnboundRecordFilter;
import org.apache.parquet.filter2.compat.FilterCompat;
import org.apache.parquet.hadoop.api.ReadSupport;
import org.apache.parquet.hadoop.util.ConfigurationUtil;
import org.apache.parquet.hadoop.util.HadoopInputFile;
import org.apache.parquet.hadoop.util.HiddenFileFilter;
import org.apache.parquet.io.InputFile;

public class ParquetReader implements Closeable {
   private final ReadSupport readSupport;
   private final Iterator filesIterator;
   private final ParquetReadOptions options;
   private InternalParquetRecordReader reader;

   /** @deprecated */
   @Deprecated
   public ParquetReader(Path file, ReadSupport readSupport) throws IOException {
      this(new Configuration(), file, readSupport, FilterCompat.NOOP);
   }

   /** @deprecated */
   @Deprecated
   public ParquetReader(Configuration conf, Path file, ReadSupport readSupport) throws IOException {
      this(conf, file, readSupport, FilterCompat.NOOP);
   }

   /** @deprecated */
   @Deprecated
   public ParquetReader(Path file, ReadSupport readSupport, UnboundRecordFilter unboundRecordFilter) throws IOException {
      this(new Configuration(), file, readSupport, FilterCompat.get(unboundRecordFilter));
   }

   /** @deprecated */
   @Deprecated
   public ParquetReader(Configuration conf, Path file, ReadSupport readSupport, UnboundRecordFilter unboundRecordFilter) throws IOException {
      this(conf, file, readSupport, FilterCompat.get(unboundRecordFilter));
   }

   private ParquetReader(Configuration conf, Path file, ReadSupport readSupport, FilterCompat.Filter filter) throws IOException {
      this(Collections.singletonList(HadoopInputFile.fromPath(file, conf)), HadoopReadOptions.builder(conf, file).withRecordFilter((FilterCompat.Filter)Objects.requireNonNull(filter, "filter cannot be null")).build(), readSupport);
   }

   private ParquetReader(List files, ParquetReadOptions options, ReadSupport readSupport) throws IOException {
      this.readSupport = readSupport;
      this.options = options;
      this.filesIterator = files.iterator();
   }

   public Object read() throws IOException {
      try {
         if (this.reader != null && this.reader.nextKeyValue()) {
            return this.reader.getCurrentValue();
         } else {
            this.initReader();
            return this.reader == null ? null : this.read();
         }
      } catch (InterruptedException e) {
         throw new IOException(e);
      }
   }

   public long getCurrentRowIndex() {
      return this.reader == null ? -1L : this.reader.getCurrentRowIndex();
   }

   private void initReader() throws IOException {
      if (this.reader != null) {
         this.reader.close();
         this.reader = null;
      }

      if (this.filesIterator.hasNext()) {
         InputFile file = (InputFile)this.filesIterator.next();
         ParquetFileReader fileReader = ParquetFileReader.open(file, this.options);
         this.reader = new InternalParquetRecordReader(this.readSupport, this.options.getRecordFilter());
         this.reader.initialize(fileReader, this.options);
      }

   }

   public void close() throws IOException {
      if (this.reader != null) {
         this.reader.close();
      }

   }

   public static Builder read(InputFile file) throws IOException {
      return new Builder(file);
   }

   public static Builder read(InputFile file, ParquetConfiguration conf) throws IOException {
      return new Builder(file, conf);
   }

   public static Builder builder(ReadSupport readSupport, Path path) {
      return new Builder(readSupport, path);
   }

   public static class Builder {
      private final ReadSupport readSupport;
      private final InputFile file;
      private final Path path;
      private FilterCompat.Filter filter;
      private ByteBufferAllocator allocator;
      protected ParquetConfiguration configuration;
      private ParquetReadOptions.Builder optionsBuilder;
      /** @deprecated */
      @Deprecated
      protected Configuration conf;

      /** @deprecated */
      @Deprecated
      private Builder(ReadSupport readSupport, Path path) {
         this.filter = null;
         this.allocator = new HeapByteBufferAllocator();
         this.readSupport = (ReadSupport)Objects.requireNonNull(readSupport, "readSupport cannot be null");
         this.file = null;
         this.path = (Path)Objects.requireNonNull(path, "path cannot be null");
         this.conf = new Configuration();
         this.configuration = new HadoopParquetConfiguration(this.conf);
         this.optionsBuilder = HadoopReadOptions.builder(this.conf, path);
      }

      /** @deprecated */
      @Deprecated
      protected Builder(Path path) {
         this.filter = null;
         this.allocator = new HeapByteBufferAllocator();
         this.readSupport = null;
         this.file = null;
         this.path = (Path)Objects.requireNonNull(path, "path cannot be null");
         this.conf = new Configuration();
         this.configuration = new HadoopParquetConfiguration(this.conf);
         this.optionsBuilder = HadoopReadOptions.builder(this.conf, path);
      }

      protected Builder(InputFile file) {
         this.filter = null;
         this.allocator = new HeapByteBufferAllocator();
         this.readSupport = null;
         this.file = (InputFile)Objects.requireNonNull(file, "file cannot be null");
         this.path = null;
         if (file instanceof HadoopInputFile) {
            HadoopInputFile hadoopFile = (HadoopInputFile)file;
            this.configuration = new HadoopParquetConfiguration(hadoopFile.getConfiguration());
         } else {
            this.configuration = new HadoopParquetConfiguration();
         }

         this.optionsBuilder = HadoopReadOptions.builder(this.configuration);
      }

      protected Builder(InputFile file, ParquetConfiguration conf) {
         this.filter = null;
         this.allocator = new HeapByteBufferAllocator();
         this.readSupport = null;
         this.file = (InputFile)Objects.requireNonNull(file, "file cannot be null");
         this.path = null;
         this.configuration = conf;
         if (file instanceof HadoopInputFile) {
            this.conf = ConfigurationUtil.createHadoopConfiguration(conf);
            HadoopInputFile hadoopFile = (HadoopInputFile)file;
            this.optionsBuilder = HadoopReadOptions.builder(this.conf, hadoopFile.getPath());
         } else {
            this.optionsBuilder = ParquetReadOptions.builder(conf);
         }

      }

      public Builder withConf(Configuration conf) {
         this.conf = (Configuration)Objects.requireNonNull(conf, "conf cannot be null");
         this.configuration = new HadoopParquetConfiguration(this.conf);
         this.optionsBuilder = HadoopReadOptions.builder(conf, this.path);
         if (this.filter != null) {
            this.optionsBuilder.withRecordFilter(this.filter);
         }

         return this;
      }

      public Builder withConf(ParquetConfiguration conf) {
         this.configuration = conf;
         this.optionsBuilder = ParquetReadOptions.builder(conf);
         if (this.filter != null) {
            this.optionsBuilder.withRecordFilter(this.filter);
         }

         return this;
      }

      public Builder withFilter(FilterCompat.Filter filter) {
         this.filter = filter;
         this.optionsBuilder.withRecordFilter(filter);
         return this;
      }

      public Builder withAllocator(ByteBufferAllocator allocator) {
         this.allocator = allocator;
         this.optionsBuilder.withAllocator(allocator);
         return this;
      }

      public Builder useSignedStringMinMax(boolean useSignedStringMinMax) {
         this.optionsBuilder.useSignedStringMinMax(useSignedStringMinMax);
         return this;
      }

      public Builder useSignedStringMinMax() {
         this.optionsBuilder.useSignedStringMinMax();
         return this;
      }

      public Builder useStatsFilter(boolean useStatsFilter) {
         this.optionsBuilder.useStatsFilter(useStatsFilter);
         return this;
      }

      public Builder useStatsFilter() {
         this.optionsBuilder.useStatsFilter();
         return this;
      }

      public Builder useDictionaryFilter(boolean useDictionaryFilter) {
         this.optionsBuilder.useDictionaryFilter(useDictionaryFilter);
         return this;
      }

      public Builder useDictionaryFilter() {
         this.optionsBuilder.useDictionaryFilter();
         return this;
      }

      public Builder useRecordFilter(boolean useRecordFilter) {
         this.optionsBuilder.useRecordFilter(useRecordFilter);
         return this;
      }

      public Builder useRecordFilter() {
         this.optionsBuilder.useRecordFilter();
         return this;
      }

      public Builder useColumnIndexFilter(boolean useColumnIndexFilter) {
         this.optionsBuilder.useColumnIndexFilter(useColumnIndexFilter);
         return this;
      }

      public Builder useColumnIndexFilter() {
         this.optionsBuilder.useColumnIndexFilter();
         return this;
      }

      public Builder usePageChecksumVerification(boolean usePageChecksumVerification) {
         this.optionsBuilder.usePageChecksumVerification(usePageChecksumVerification);
         return this;
      }

      public Builder useBloomFilter(boolean useBloomFilter) {
         this.optionsBuilder.useBloomFilter(useBloomFilter);
         return this;
      }

      public Builder useBloomFilter() {
         this.optionsBuilder.useBloomFilter();
         return this;
      }

      public Builder usePageChecksumVerification() {
         this.optionsBuilder.usePageChecksumVerification();
         return this;
      }

      public Builder withFileRange(long start, long end) {
         this.optionsBuilder.withRange(start, end);
         return this;
      }

      public Builder withCodecFactory(CompressionCodecFactory codecFactory) {
         this.optionsBuilder.withCodecFactory(codecFactory);
         return this;
      }

      public Builder withDecryption(FileDecryptionProperties fileDecryptionProperties) {
         this.optionsBuilder.withDecryption(fileDecryptionProperties);
         return this;
      }

      public Builder set(String key, String value) {
         this.optionsBuilder.set(key, value);
         return this;
      }

      protected ReadSupport getReadSupport() {
         Preconditions.checkArgument(this.readSupport != null, "[BUG] Classes that extend Builder should override getReadSupport()");
         return this.readSupport;
      }

      public ParquetReader build() throws IOException {
         ParquetReadOptions options = this.optionsBuilder.withAllocator(this.allocator).build();
         if (this.path == null) {
            return new ParquetReader(Collections.singletonList(this.file), options, this.getReadSupport());
         } else {
            Configuration hadoopConf = ConfigurationUtil.createHadoopConfiguration(this.configuration);
            FileSystem fs = this.path.getFileSystem(hadoopConf);
            FileStatus stat = fs.getFileStatus(this.path);
            if (stat.isFile()) {
               return new ParquetReader(Collections.singletonList(HadoopInputFile.fromStatus(stat, hadoopConf)), options, this.getReadSupport());
            } else {
               List<InputFile> files = new ArrayList();

               for(FileStatus fileStatus : fs.listStatus(this.path, HiddenFileFilter.INSTANCE)) {
                  files.add(HadoopInputFile.fromStatus(fileStatus, hadoopConf));
               }

               return new ParquetReader(files, options, this.getReadSupport());
            }
         }
      }
   }
}
