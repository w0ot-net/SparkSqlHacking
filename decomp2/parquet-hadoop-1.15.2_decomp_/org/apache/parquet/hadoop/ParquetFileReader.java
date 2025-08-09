package org.apache.parquet.hadoop;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.zip.CRC32;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.HadoopReadOptions;
import org.apache.parquet.ParquetReadOptions;
import org.apache.parquet.Preconditions;
import org.apache.parquet.bytes.ByteBufferInputStream;
import org.apache.parquet.bytes.ByteBufferReleaser;
import org.apache.parquet.bytes.BytesInput;
import org.apache.parquet.bytes.BytesUtils;
import org.apache.parquet.bytes.ReusingByteBufferAllocator;
import org.apache.parquet.column.ColumnDescriptor;
import org.apache.parquet.column.page.DataPage;
import org.apache.parquet.column.page.DataPageV1;
import org.apache.parquet.column.page.DataPageV2;
import org.apache.parquet.column.page.DictionaryPage;
import org.apache.parquet.column.page.DictionaryPageReadStore;
import org.apache.parquet.column.page.PageReadStore;
import org.apache.parquet.column.values.bloomfilter.BlockSplitBloomFilter;
import org.apache.parquet.column.values.bloomfilter.BloomFilter;
import org.apache.parquet.compression.CompressionCodecFactory;
import org.apache.parquet.crypto.AesCipher;
import org.apache.parquet.crypto.FileDecryptionProperties;
import org.apache.parquet.crypto.InternalColumnDecryptionSetup;
import org.apache.parquet.crypto.InternalFileDecryptor;
import org.apache.parquet.crypto.ModuleCipherFactory;
import org.apache.parquet.crypto.ParquetCryptoRuntimeException;
import org.apache.parquet.filter2.compat.FilterCompat;
import org.apache.parquet.filter2.compat.RowGroupFilter;
import org.apache.parquet.format.BlockCipher;
import org.apache.parquet.format.BloomFilterHeader;
import org.apache.parquet.format.DataPageHeader;
import org.apache.parquet.format.DataPageHeaderV2;
import org.apache.parquet.format.DictionaryPageHeader;
import org.apache.parquet.format.FileCryptoMetaData;
import org.apache.parquet.format.PageHeader;
import org.apache.parquet.format.Util;
import org.apache.parquet.format.converter.ParquetMetadataConverter;
import org.apache.parquet.hadoop.metadata.BlockMetaData;
import org.apache.parquet.hadoop.metadata.ColumnChunkMetaData;
import org.apache.parquet.hadoop.metadata.ColumnPath;
import org.apache.parquet.hadoop.metadata.FileMetaData;
import org.apache.parquet.hadoop.metadata.ParquetMetadata;
import org.apache.parquet.hadoop.util.HadoopInputFile;
import org.apache.parquet.hadoop.util.counters.BenchmarkCounter;
import org.apache.parquet.hadoop.util.wrapped.io.FutureIO;
import org.apache.parquet.internal.column.columnindex.ColumnIndex;
import org.apache.parquet.internal.column.columnindex.OffsetIndex;
import org.apache.parquet.internal.filter2.columnindex.ColumnIndexFilter;
import org.apache.parquet.internal.filter2.columnindex.ColumnIndexStore;
import org.apache.parquet.internal.filter2.columnindex.RowRanges;
import org.apache.parquet.internal.hadoop.metadata.IndexReference;
import org.apache.parquet.io.InputFile;
import org.apache.parquet.io.ParquetDecodingException;
import org.apache.parquet.io.ParquetFileRange;
import org.apache.parquet.io.SeekableInputStream;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.PrimitiveType;
import org.apache.parquet.util.AutoCloseables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParquetFileReader implements Closeable {
   private static final Logger LOG = LoggerFactory.getLogger(ParquetFileReader.class);
   public static final String PARQUET_READ_PARALLELISM = "parquet.metadata.read.parallelism";
   public static final long HADOOP_VECTORED_READ_TIMEOUT_SECONDS = 300L;
   private final ParquetMetadataConverter converter;
   private final CRC32 crc;
   private final ReusingByteBufferAllocator crcAllocator;
   protected final SeekableInputStream f;
   private final InputFile file;
   private final ParquetReadOptions options;
   private final Map paths;
   private final FileMetaData fileMetaData;
   private final List blocks;
   private final List blockIndexStores;
   private final List blockRowRanges;
   private ParquetMetadata footer;
   private int currentBlock;
   private ColumnChunkPageReadStore currentRowGroup;
   private DictionaryPageReader nextDictionaryReader;
   private InternalFileDecryptor fileDecryptor;

   /** @deprecated */
   @Deprecated
   public static List readAllFootersInParallelUsingSummaryFiles(Configuration configuration, List partFiles) throws IOException {
      return readAllFootersInParallelUsingSummaryFiles(configuration, partFiles, false);
   }

   private static ParquetMetadataConverter.MetadataFilter filter(boolean skipRowGroups) {
      return skipRowGroups ? ParquetMetadataConverter.SKIP_ROW_GROUPS : ParquetMetadataConverter.NO_FILTER;
   }

   /** @deprecated */
   @Deprecated
   public static List readAllFootersInParallelUsingSummaryFiles(Configuration configuration, Collection partFiles, boolean skipRowGroups) throws IOException {
      Set<Path> parents = new HashSet();

      for(FileStatus part : partFiles) {
         parents.add(part.getPath().getParent());
      }

      List<Callable<Map<Path, Footer>>> summaries = new ArrayList();

      for(Path path : parents) {
         summaries.add((Callable)() -> {
            ParquetMetadata mergedMetadata = readSummaryMetadata(configuration, path, skipRowGroups);
            if (mergedMetadata == null) {
               return Collections.emptyMap();
            } else {
               List<Footer> footers;
               if (skipRowGroups) {
                  footers = new ArrayList();

                  for(FileStatus f : partFiles) {
                     footers.add(new Footer(f.getPath(), mergedMetadata));
                  }
               } else {
                  footers = footersFromSummaryFile(path, mergedMetadata);
               }

               Map<Path, Footer> map = new HashMap();

               for(Footer footer : footers) {
                  footer = new Footer(new Path(path, footer.getFile().getName()), footer.getParquetMetadata());
                  map.put(footer.getFile(), footer);
               }

               return map;
            }
         });
      }

      Map<Path, Footer> cache = new HashMap();

      try {
         for(Map footers : runAllInParallel(configuration.getInt("parquet.metadata.read.parallelism", 5), summaries)) {
            cache.putAll(footers);
         }
      } catch (ExecutionException e) {
         throw new IOException("Error reading summaries", e);
      }

      List<Footer> result = new ArrayList(partFiles.size());
      List<FileStatus> toRead = new ArrayList();

      for(FileStatus part : partFiles) {
         Footer f = (Footer)cache.get(part.getPath());
         if (f != null) {
            result.add(f);
         } else {
            toRead.add(part);
         }
      }

      if (toRead.size() > 0) {
         LOG.info("reading another {} footers", toRead.size());
         result.addAll(readAllFootersInParallel(configuration, toRead, skipRowGroups));
      }

      return result;
   }

   private static List runAllInParallel(int parallelism, List toRun) throws ExecutionException {
      LOG.info("Initiating action with parallelism: {}", parallelism);
      ExecutorService threadPool = Executors.newFixedThreadPool(parallelism);

      Object var15;
      try {
         List<Future<T>> futures = new ArrayList();

         for(Callable callable : toRun) {
            futures.add(threadPool.submit(callable));
         }

         List<T> result = new ArrayList(toRun.size());

         for(Future future : futures) {
            try {
               result.add(future.get());
            } catch (InterruptedException e) {
               throw new RuntimeException("The thread was interrupted", e);
            }
         }

         var15 = result;
      } finally {
         threadPool.shutdownNow();
      }

      return (List)var15;
   }

   /** @deprecated */
   @Deprecated
   public static List readAllFootersInParallel(Configuration configuration, List partFiles) throws IOException {
      return readAllFootersInParallel(configuration, partFiles, false);
   }

   /** @deprecated */
   @Deprecated
   public static List readAllFootersInParallel(Configuration configuration, List partFiles, boolean skipRowGroups) throws IOException {
      List<Callable<Footer>> footers = new ArrayList();

      for(FileStatus currentFile : partFiles) {
         footers.add((Callable)() -> {
            try {
               return new Footer(currentFile.getPath(), readFooter(configuration, currentFile, filter(skipRowGroups)));
            } catch (IOException e) {
               throw new IOException("Could not read footer for file " + currentFile, e);
            }
         });
      }

      try {
         return runAllInParallel(configuration.getInt("parquet.metadata.read.parallelism", 5), footers);
      } catch (ExecutionException e) {
         throw new IOException("Could not read footer: " + e.getMessage(), e.getCause());
      }
   }

   /** @deprecated */
   @Deprecated
   public static List readAllFootersInParallel(Configuration configuration, FileStatus fileStatus, boolean skipRowGroups) throws IOException {
      List<FileStatus> statuses = listFiles(configuration, fileStatus);
      return readAllFootersInParallel(configuration, statuses, skipRowGroups);
   }

   /** @deprecated */
   @Deprecated
   public static List readAllFootersInParallel(Configuration configuration, FileStatus fileStatus) throws IOException {
      return readAllFootersInParallel(configuration, fileStatus, false);
   }

   /** @deprecated */
   @Deprecated
   public static List readFooters(Configuration configuration, Path path) throws IOException {
      return readFooters(configuration, status(configuration, path));
   }

   private static FileStatus status(Configuration configuration, Path path) throws IOException {
      return path.getFileSystem(configuration).getFileStatus(path);
   }

   /** @deprecated */
   @Deprecated
   public static List readFooters(Configuration configuration, FileStatus pathStatus) throws IOException {
      return readFooters(configuration, pathStatus, false);
   }

   /** @deprecated */
   @Deprecated
   public static List readFooters(Configuration configuration, FileStatus pathStatus, boolean skipRowGroups) throws IOException {
      List<FileStatus> files = listFiles(configuration, pathStatus);
      return readAllFootersInParallelUsingSummaryFiles(configuration, files, skipRowGroups);
   }

   static boolean filterHiddenFiles(FileStatus file) {
      char c = file.getPath().getName().charAt(0);
      return c != '.' && c != '_';
   }

   private static List listFiles(Configuration conf, FileStatus fileStatus) throws IOException {
      if (fileStatus.isDir()) {
         FileSystem fs = fileStatus.getPath().getFileSystem(conf);
         return (List)Arrays.stream(fs.listStatus(fileStatus.getPath())).filter(ParquetFileReader::filterHiddenFiles).flatMap((sub) -> {
            try {
               return listFiles(conf, sub).stream();
            } catch (IOException e) {
               throw new RuntimeException(e);
            }
         }).collect(Collectors.toList());
      } else {
         return Collections.singletonList(fileStatus);
      }
   }

   /** @deprecated */
   @Deprecated
   public static List readSummaryFile(Configuration configuration, FileStatus summaryStatus) throws IOException {
      Path parent = summaryStatus.getPath().getParent();
      ParquetMetadata mergedFooters = readFooter(configuration, summaryStatus, filter(false));
      return footersFromSummaryFile(parent, mergedFooters);
   }

   static ParquetMetadata readSummaryMetadata(Configuration configuration, Path basePath, boolean skipRowGroups) throws IOException {
      Path metadataFile = new Path(basePath, "_metadata");
      Path commonMetaDataFile = new Path(basePath, "_common_metadata");
      FileSystem fileSystem = basePath.getFileSystem(configuration);
      if (skipRowGroups && fileSystem.exists(commonMetaDataFile)) {
         LOG.info("reading summary file: {}", commonMetaDataFile);
         return readFooter(configuration, commonMetaDataFile, filter(skipRowGroups));
      } else if (fileSystem.exists(metadataFile)) {
         LOG.info("reading summary file: {}", metadataFile);
         return readFooter(configuration, metadataFile, filter(skipRowGroups));
      } else {
         return null;
      }
   }

   static List footersFromSummaryFile(Path parent, ParquetMetadata mergedFooters) {
      Map<Path, ParquetMetadata> footers = new HashMap();

      for(BlockMetaData block : mergedFooters.getBlocks()) {
         String path = block.getPath();
         Path fullPath = new Path(parent, path);
         ParquetMetadata current = (ParquetMetadata)footers.get(fullPath);
         if (current == null) {
            current = new ParquetMetadata(mergedFooters.getFileMetaData(), new ArrayList());
            footers.put(fullPath, current);
         }

         current.getBlocks().add(block);
      }

      List<Footer> result = new ArrayList();

      for(Map.Entry entry : footers.entrySet()) {
         result.add(new Footer((Path)entry.getKey(), (ParquetMetadata)entry.getValue()));
      }

      return result;
   }

   /** @deprecated */
   @Deprecated
   public static final ParquetMetadata readFooter(Configuration configuration, Path file) throws IOException {
      return readFooter(configuration, file, ParquetMetadataConverter.NO_FILTER);
   }

   /** @deprecated */
   @Deprecated
   public static ParquetMetadata readFooter(Configuration configuration, Path file, ParquetMetadataConverter.MetadataFilter filter) throws IOException {
      return readFooter((InputFile)HadoopInputFile.fromPath(file, configuration), (ParquetMetadataConverter.MetadataFilter)filter);
   }

   /** @deprecated */
   @Deprecated
   public static final ParquetMetadata readFooter(Configuration configuration, FileStatus file) throws IOException {
      return readFooter(configuration, file, ParquetMetadataConverter.NO_FILTER);
   }

   /** @deprecated */
   @Deprecated
   public static final ParquetMetadata readFooter(Configuration configuration, FileStatus file, ParquetMetadataConverter.MetadataFilter filter) throws IOException {
      return readFooter((InputFile)HadoopInputFile.fromStatus(file, configuration), (ParquetMetadataConverter.MetadataFilter)filter);
   }

   /** @deprecated */
   @Deprecated
   public static final ParquetMetadata readFooter(InputFile file, ParquetMetadataConverter.MetadataFilter filter) throws IOException {
      ParquetReadOptions options;
      if (file instanceof HadoopInputFile) {
         HadoopInputFile hadoopFile = (HadoopInputFile)file;
         options = HadoopReadOptions.builder(hadoopFile.getConfiguration(), hadoopFile.getPath()).withMetadataFilter(filter).build();
      } else {
         options = ParquetReadOptions.builder().withMetadataFilter(filter).build();
      }

      SeekableInputStream in = file.newStream();
      Throwable var4 = null;

      ParquetMetadata var5;
      try {
         var5 = readFooter(file, options, in);
      } catch (Throwable var14) {
         var4 = var14;
         throw var14;
      } finally {
         if (in != null) {
            if (var4 != null) {
               try {
                  in.close();
               } catch (Throwable var13) {
                  var4.addSuppressed(var13);
               }
            } else {
               in.close();
            }
         }

      }

      return var5;
   }

   public static final ParquetMetadata readFooter(InputFile file, ParquetReadOptions options, SeekableInputStream f) throws IOException {
      ParquetMetadataConverter converter = new ParquetMetadataConverter(options);
      return readFooter(file, options, f, converter);
   }

   private static final ParquetMetadata readFooter(InputFile file, ParquetReadOptions options, SeekableInputStream f, ParquetMetadataConverter converter) throws IOException {
      long fileLen = file.getLength();
      String filePath = file.toString();
      LOG.debug("File length {}", fileLen);
      int FOOTER_LENGTH_SIZE = 4;
      if (fileLen < (long)(ParquetFileWriter.MAGIC.length + FOOTER_LENGTH_SIZE + ParquetFileWriter.MAGIC.length)) {
         throw new RuntimeException(filePath + " is not a Parquet file (length is too low: " + fileLen + ")");
      } else {
         byte[] magic = new byte[ParquetFileWriter.MAGIC.length];
         long fileMetadataLengthIndex = fileLen - (long)magic.length - (long)FOOTER_LENGTH_SIZE;
         LOG.debug("reading footer index at {}", fileMetadataLengthIndex);
         f.seek(fileMetadataLengthIndex);
         int fileMetadataLength = BytesUtils.readIntLittleEndian(f);
         f.readFully(magic);
         boolean encryptedFooterMode;
         if (Arrays.equals(ParquetFileWriter.MAGIC, magic)) {
            encryptedFooterMode = false;
         } else {
            if (!Arrays.equals(ParquetFileWriter.EFMAGIC, magic)) {
               throw new RuntimeException(filePath + " is not a Parquet file. Expected magic number at tail, but found " + Arrays.toString(magic));
            }

            encryptedFooterMode = true;
         }

         long fileMetadataIndex = fileMetadataLengthIndex - (long)fileMetadataLength;
         LOG.debug("read footer length: {}, footer index: {}", fileMetadataLength, fileMetadataIndex);
         if (fileMetadataIndex >= (long)magic.length && fileMetadataIndex < fileMetadataLengthIndex) {
            f.seek(fileMetadataIndex);
            FileDecryptionProperties fileDecryptionProperties = options.getDecryptionProperties();
            InternalFileDecryptor fileDecryptor = null;
            if (null != fileDecryptionProperties) {
               fileDecryptor = new InternalFileDecryptor(fileDecryptionProperties);
            }

            ByteBuffer footerBytesBuffer = options.getAllocator().allocate(fileMetadataLength);

            ParquetMetadata var19;
            try {
               f.readFully(footerBytesBuffer);
               LOG.debug("Finished to read all footer bytes.");
               footerBytesBuffer.flip();
               InputStream footerBytesStream = ByteBufferInputStream.wrap(new ByteBuffer[]{footerBytesBuffer});
               if (encryptedFooterMode) {
                  if (null == fileDecryptor) {
                     throw new ParquetCryptoRuntimeException("Trying to read file with encrypted footer. No keys available");
                  }

                  FileCryptoMetaData fileCryptoMetaData = Util.readFileCryptoMetaData(footerBytesStream);
                  fileDecryptor.setFileCryptoMetaData(fileCryptoMetaData.getEncryption_algorithm(), true, fileCryptoMetaData.getKey_metadata());
                  ParquetMetadata var20 = converter.readParquetMetadata(footerBytesStream, options.getMetadataFilter(), fileDecryptor, true, 0);
                  return var20;
               }

               var19 = converter.readParquetMetadata(footerBytesStream, options.getMetadataFilter(), fileDecryptor, false, fileMetadataLength);
            } finally {
               options.getAllocator().release(footerBytesBuffer);
            }

            return var19;
         } else {
            throw new RuntimeException("corrupted file: the footer index is not within the file: " + fileMetadataIndex);
         }
      }
   }

   /** @deprecated */
   @Deprecated
   public static ParquetFileReader open(Configuration conf, Path file) throws IOException {
      return new ParquetFileReader(HadoopInputFile.fromPath(file, conf), HadoopReadOptions.builder(conf, file).build());
   }

   /** @deprecated */
   @Deprecated
   public static ParquetFileReader open(Configuration conf, Path file, ParquetMetadataConverter.MetadataFilter filter) throws IOException {
      return open((InputFile)HadoopInputFile.fromPath(file, conf), (ParquetReadOptions)HadoopReadOptions.builder(conf, file).withMetadataFilter(filter).build());
   }

   /** @deprecated */
   @Deprecated
   public static ParquetFileReader open(Configuration conf, Path file, ParquetMetadata footer) throws IOException {
      return new ParquetFileReader(conf, file, footer);
   }

   public static ParquetFileReader open(InputFile file) throws IOException {
      return new ParquetFileReader(file, ParquetReadOptions.builder().build());
   }

   public static ParquetFileReader open(InputFile file, ParquetReadOptions options) throws IOException {
      return new ParquetFileReader(file, options);
   }

   public static ParquetFileReader open(InputFile file, ParquetReadOptions options, SeekableInputStream f) throws IOException {
      return new ParquetFileReader(file, options, f);
   }

   /** @deprecated */
   @Deprecated
   public ParquetFileReader(Configuration configuration, Path filePath, List blocks, List columns) throws IOException {
      this(configuration, (FileMetaData)null, (Path)filePath, (List)blocks, (List)columns);
   }

   /** @deprecated */
   @Deprecated
   public ParquetFileReader(Configuration configuration, FileMetaData fileMetaData, Path filePath, List blocks, List columns) throws IOException {
      this.paths = new HashMap();
      this.currentBlock = 0;
      this.currentRowGroup = null;
      this.nextDictionaryReader = null;
      this.fileDecryptor = null;
      this.converter = new ParquetMetadataConverter(configuration);
      this.file = HadoopInputFile.fromPath(filePath, configuration);
      this.fileMetaData = fileMetaData;
      this.f = this.file.newStream();
      this.fileDecryptor = fileMetaData.getFileDecryptor();
      if (null == this.fileDecryptor) {
         this.options = HadoopReadOptions.builder(configuration).build();
      } else {
         this.options = HadoopReadOptions.builder(configuration).withDecryption(this.fileDecryptor.getDecryptionProperties()).build();
      }

      try {
         this.blocks = this.filterRowGroups(blocks);
      } catch (Exception e) {
         this.f.close();
         throw e;
      }

      this.blockIndexStores = listWithNulls(this.blocks.size());
      this.blockRowRanges = listWithNulls(this.blocks.size());

      for(ColumnDescriptor col : columns) {
         this.paths.put(ColumnPath.get(col.getPath()), col);
      }

      if (this.options.usePageChecksumVerification()) {
         this.crc = new CRC32();
         this.crcAllocator = ReusingByteBufferAllocator.strict(this.options.getAllocator());
      } else {
         this.crc = null;
         this.crcAllocator = null;
      }

   }

   /** @deprecated */
   @Deprecated
   public ParquetFileReader(Configuration conf, Path file, ParquetMetadataConverter.MetadataFilter filter) throws IOException {
      this(HadoopInputFile.fromPath(file, conf), HadoopReadOptions.builder(conf, file).withMetadataFilter(filter).build());
   }

   /** @deprecated */
   @Deprecated
   public ParquetFileReader(Configuration conf, Path file, ParquetMetadata footer) throws IOException {
      this.paths = new HashMap();
      this.currentBlock = 0;
      this.currentRowGroup = null;
      this.nextDictionaryReader = null;
      this.fileDecryptor = null;
      this.converter = new ParquetMetadataConverter(conf);
      this.file = HadoopInputFile.fromPath(file, conf);
      this.f = this.file.newStream();
      this.fileMetaData = footer.getFileMetaData();
      this.fileDecryptor = this.fileMetaData.getFileDecryptor();
      if (null == this.fileDecryptor) {
         this.options = HadoopReadOptions.builder(conf).build();
      } else {
         this.options = HadoopReadOptions.builder(conf).withDecryption(this.fileDecryptor.getDecryptionProperties()).build();
      }

      this.footer = footer;

      try {
         this.blocks = this.filterRowGroups(footer.getBlocks());
      } catch (Exception e) {
         this.f.close();
         throw e;
      }

      this.blockIndexStores = listWithNulls(this.blocks.size());
      this.blockRowRanges = listWithNulls(this.blocks.size());

      for(ColumnDescriptor col : footer.getFileMetaData().getSchema().getColumns()) {
         this.paths.put(ColumnPath.get(col.getPath()), col);
      }

      if (this.options.usePageChecksumVerification()) {
         this.crc = new CRC32();
         this.crcAllocator = ReusingByteBufferAllocator.strict(this.options.getAllocator());
      } else {
         this.crc = null;
         this.crcAllocator = null;
      }

   }

   public ParquetFileReader(Configuration conf, Path file, ParquetMetadata footer, ParquetReadOptions options) throws IOException {
      this(conf, file, footer, options, HadoopInputFile.fromPath(file, conf).newStream());
   }

   public ParquetFileReader(Configuration conf, Path file, ParquetMetadata footer, ParquetReadOptions options, SeekableInputStream f) throws IOException {
      this.paths = new HashMap();
      this.currentBlock = 0;
      this.currentRowGroup = null;
      this.nextDictionaryReader = null;
      this.fileDecryptor = null;
      this.converter = new ParquetMetadataConverter(conf);
      this.file = HadoopInputFile.fromPath(file, conf);
      this.f = f;
      this.fileMetaData = footer.getFileMetaData();
      this.fileDecryptor = this.fileMetaData.getFileDecryptor();
      this.options = options;
      this.footer = footer;

      try {
         this.blocks = this.filterRowGroups(footer.getBlocks());
      } catch (Exception e) {
         f.close();
         throw e;
      }

      this.blockIndexStores = listWithNulls(this.blocks.size());
      this.blockRowRanges = listWithNulls(this.blocks.size());

      for(ColumnDescriptor col : footer.getFileMetaData().getSchema().getColumns()) {
         this.paths.put(ColumnPath.get(col.getPath()), col);
      }

      if (options.usePageChecksumVerification()) {
         this.crc = new CRC32();
         this.crcAllocator = ReusingByteBufferAllocator.strict(options.getAllocator());
      } else {
         this.crc = null;
         this.crcAllocator = null;
      }

   }

   public ParquetFileReader(InputFile file, ParquetReadOptions options) throws IOException {
      this(file, options, file.newStream());
   }

   public ParquetFileReader(InputFile file, ParquetReadOptions options, SeekableInputStream f) throws IOException {
      this.paths = new HashMap();
      this.currentBlock = 0;
      this.currentRowGroup = null;
      this.nextDictionaryReader = null;
      this.fileDecryptor = null;
      this.converter = new ParquetMetadataConverter(options);
      this.file = file;
      this.f = f;
      this.options = options;

      try {
         this.footer = readFooter(file, options, f, this.converter);
      } catch (Exception e) {
         f.close();
         throw e;
      }

      this.fileMetaData = this.footer.getFileMetaData();
      this.fileDecryptor = this.fileMetaData.getFileDecryptor();
      if (null != this.fileDecryptor && this.fileDecryptor.plaintextFile()) {
         this.fileDecryptor = null;
      }

      try {
         this.blocks = this.filterRowGroups(this.footer.getBlocks());
      } catch (Exception e) {
         f.close();
         throw e;
      }

      this.blockIndexStores = listWithNulls(this.blocks.size());
      this.blockRowRanges = listWithNulls(this.blocks.size());

      for(ColumnDescriptor col : this.footer.getFileMetaData().getSchema().getColumns()) {
         this.paths.put(ColumnPath.get(col.getPath()), col);
      }

      if (options.usePageChecksumVerification()) {
         this.crc = new CRC32();
         this.crcAllocator = ReusingByteBufferAllocator.strict(options.getAllocator());
      } else {
         this.crc = null;
         this.crcAllocator = null;
      }

   }

   private static List listWithNulls(int size) {
      return new ArrayList(Collections.nCopies(size, (Object)null));
   }

   public ParquetMetadata getFooter() {
      if (this.footer == null) {
         try {
            this.footer = readFooter(this.file, this.options, this.f, this.converter);
         } catch (IOException e) {
            throw new ParquetDecodingException("Unable to read file footer", e);
         }
      }

      return this.footer;
   }

   public FileMetaData getFileMetaData() {
      return this.fileMetaData != null ? this.fileMetaData : this.getFooter().getFileMetaData();
   }

   public long getRecordCount() {
      long total = 0L;

      for(BlockMetaData block : this.blocks) {
         total += block.getRowCount();
      }

      return total;
   }

   public long getFilteredRecordCount() {
      if (this.options.useColumnIndexFilter() && FilterCompat.isFilteringRequired(this.options.getRecordFilter())) {
         long total = 0L;
         int i = 0;

         for(int n = this.blocks.size(); i < n; ++i) {
            total += this.getRowRanges(i).rowCount();
         }

         return total;
      } else {
         return this.getRecordCount();
      }
   }

   /** @deprecated */
   @Deprecated
   public Path getPath() {
      return new Path(this.file.toString());
   }

   public String getFile() {
      return this.file.toString();
   }

   private List filterRowGroups(List blocks) throws IOException {
      FilterCompat.Filter recordFilter = this.options.getRecordFilter();
      if (FilterCompat.isFilteringRequired(recordFilter)) {
         List<RowGroupFilter.FilterLevel> levels = new ArrayList();
         if (this.options.useStatsFilter()) {
            levels.add(RowGroupFilter.FilterLevel.STATISTICS);
         }

         if (this.options.useDictionaryFilter()) {
            levels.add(RowGroupFilter.FilterLevel.DICTIONARY);
         }

         if (this.options.useBloomFilter()) {
            levels.add(RowGroupFilter.FilterLevel.BLOOMFILTER);
         }

         return RowGroupFilter.filterRowGroups(levels, recordFilter, blocks, this);
      } else {
         return blocks;
      }
   }

   public List getRowGroups() {
      return this.blocks;
   }

   public void setRequestedSchema(MessageType projection) {
      this.paths.clear();

      for(ColumnDescriptor col : projection.getColumns()) {
         this.paths.put(ColumnPath.get(col.getPath()), col);
      }

   }

   public void appendTo(ParquetFileWriter writer) throws IOException {
      writer.appendRowGroups(this.f, this.blocks, true);
   }

   public PageReadStore readRowGroup(int blockIndex) throws IOException {
      return this.internalReadRowGroup(blockIndex);
   }

   public PageReadStore readNextRowGroup() throws IOException {
      ColumnChunkPageReadStore rowGroup = null;

      try {
         rowGroup = this.internalReadRowGroup(this.currentBlock);
      } catch (ParquetEmptyBlockException var3) {
         LOG.warn("Read empty block at index {} from {}", this.currentBlock, this.getFile());
         this.advanceToNextBlock();
         return this.readNextRowGroup();
      }

      if (rowGroup == null) {
         return null;
      } else {
         this.currentRowGroup = rowGroup;
         if (this.nextDictionaryReader != null) {
            this.nextDictionaryReader.setRowGroup(this.currentRowGroup);
         }

         this.advanceToNextBlock();
         return this.currentRowGroup;
      }
   }

   private ColumnChunkPageReadStore internalReadRowGroup(int blockIndex) throws IOException {
      if (blockIndex >= 0 && blockIndex < this.blocks.size()) {
         BlockMetaData block = (BlockMetaData)this.blocks.get(blockIndex);
         if (block.getRowCount() == 0L) {
            throw new ParquetEmptyBlockException("Illegal row group of 0 rows");
         } else {
            ColumnChunkPageReadStore rowGroup = new ColumnChunkPageReadStore(block.getRowCount(), block.getRowIndexOffset());
            List<ConsecutivePartList> allParts = new ArrayList();
            ConsecutivePartList currentParts = null;

            for(ColumnChunkMetaData mc : block.getColumns()) {
               ColumnPath pathKey = mc.getPath();
               ColumnDescriptor columnDescriptor = (ColumnDescriptor)this.paths.get(pathKey);
               if (columnDescriptor != null) {
                  BenchmarkCounter.incrementTotalBytes(mc.getTotalSize());
                  long startingPos = mc.getStartingPos();
                  if (currentParts == null || currentParts.endPos() != startingPos) {
                     currentParts = new ConsecutivePartList(startingPos);
                     allParts.add(currentParts);
                  }

                  currentParts.addChunk(new ChunkDescriptor(columnDescriptor, mc, startingPos, mc.getTotalSize()));
               }
            }

            ChunkListBuilder builder = new ChunkListBuilder(block.getRowCount());
            this.readAllPartsVectoredOrNormal(allParts, builder);
            rowGroup.setReleaser(builder.releaser);

            for(Chunk chunk : builder.build()) {
               this.readChunkPages(chunk, block, rowGroup);
            }

            return rowGroup;
         }
      } else {
         return null;
      }
   }

   public PageReadStore readFilteredRowGroup(int blockIndex) throws IOException {
      if (blockIndex >= 0 && blockIndex < this.blocks.size()) {
         if (this.options.useColumnIndexFilter() && FilterCompat.isFilteringRequired(this.options.getRecordFilter())) {
            BlockMetaData block = (BlockMetaData)this.blocks.get(blockIndex);
            if (block.getRowCount() == 0L) {
               throw new ParquetEmptyBlockException("Illegal row group of 0 rows");
            } else {
               RowRanges rowRanges = this.getRowRanges(blockIndex);
               return this.readFilteredRowGroup(blockIndex, rowRanges);
            }
         } else {
            return this.internalReadRowGroup(blockIndex);
         }
      } else {
         return null;
      }
   }

   public ColumnChunkPageReadStore readFilteredRowGroup(int blockIndex, RowRanges rowRanges) throws IOException {
      if (blockIndex >= 0 && blockIndex < this.blocks.size()) {
         if (Objects.isNull(rowRanges)) {
            throw new IllegalArgumentException("RowRanges must not be null");
         } else {
            BlockMetaData block = (BlockMetaData)this.blocks.get(blockIndex);
            if (block.getRowCount() == 0L) {
               return null;
            } else {
               long rowCount = rowRanges.rowCount();
               if (rowCount == 0L) {
                  return null;
               } else {
                  return rowCount == block.getRowCount() ? this.internalReadRowGroup(blockIndex) : this.internalReadFilteredRowGroup(block, rowRanges, this.getColumnIndexStore(blockIndex));
               }
            }
         }
      } else {
         throw new IllegalArgumentException(String.format("Invalid block index %s, the valid block index range are: [%s, %s]", blockIndex, 0, this.blocks.size() - 1));
      }
   }

   private void readAllPartsVectoredOrNormal(List allParts, ChunkListBuilder builder) throws IOException {
      if (this.shouldUseVectoredIo(allParts)) {
         try {
            this.readVectored(allParts, builder);
            return;
         } catch (UnsupportedOperationException | IllegalArgumentException e) {
            LOG.warn("readVectored() failed; falling back to normal IO against {}", this.f, e);
         }
      }

      for(ConsecutivePartList consecutiveChunks : allParts) {
         consecutiveChunks.readAll(this.f, builder);
      }

   }

   private boolean shouldUseVectoredIo(List allParts) {
      return this.options.useHadoopVectoredIo() && this.f.readVectoredAvailable(this.options.getAllocator()) && this.arePartsValidForVectoredIo(allParts);
   }

   private boolean arePartsValidForVectoredIo(List allParts) {
      for(ConsecutivePartList consecutivePart : allParts) {
         if (consecutivePart.length >= 2147483647L) {
            LOG.debug("Part length {} greater than Integer.MAX_VALUE thus disabling vectored IO", consecutivePart.length);
            return false;
         }
      }

      return true;
   }

   private void readVectored(List allParts, ChunkListBuilder builder) throws IOException {
      List<ParquetFileRange> ranges = new ArrayList(allParts.size());
      long totalSize = 0L;

      for(ConsecutivePartList consecutiveChunks : allParts) {
         long len = consecutiveChunks.length;
         Preconditions.checkArgument(len < 2147483647L, "Invalid length %s for vectored read operation. It must be less than max integer value.", len);
         ranges.add(new ParquetFileRange(consecutiveChunks.offset, (int)len));
         totalSize += len;
      }

      LOG.debug("Reading {} bytes of data with vectored IO in {} ranges", totalSize, ranges.size());
      this.f.readVectored(ranges, this.options.getAllocator());
      int k = 0;

      for(ConsecutivePartList consecutivePart : allParts) {
         ParquetFileRange currRange = (ParquetFileRange)ranges.get(k++);
         consecutivePart.readFromVectoredRange(currRange, builder);
      }

   }

   public PageReadStore readNextFilteredRowGroup() throws IOException {
      if (this.currentBlock == this.blocks.size()) {
         return null;
      } else if (this.options.useColumnIndexFilter() && FilterCompat.isFilteringRequired(this.options.getRecordFilter())) {
         BlockMetaData block = (BlockMetaData)this.blocks.get(this.currentBlock);
         if (block.getRowCount() == 0L) {
            LOG.warn("Read empty block at index {} from {}", this.currentBlock, this.getFile());
            this.advanceToNextBlock();
            return this.readNextFilteredRowGroup();
         } else {
            RowRanges rowRanges = this.getRowRanges(this.currentBlock);
            long rowCount = rowRanges.rowCount();
            if (rowCount == 0L) {
               this.advanceToNextBlock();
               return this.readNextFilteredRowGroup();
            } else if (rowCount == block.getRowCount()) {
               return this.readNextRowGroup();
            } else {
               this.currentRowGroup = this.internalReadFilteredRowGroup(block, rowRanges, this.getColumnIndexStore(this.currentBlock));
               if (this.nextDictionaryReader != null) {
                  this.nextDictionaryReader.setRowGroup(this.currentRowGroup);
               }

               this.advanceToNextBlock();
               return this.currentRowGroup;
            }
         }
      } else {
         return this.readNextRowGroup();
      }
   }

   private ColumnChunkPageReadStore internalReadFilteredRowGroup(BlockMetaData block, RowRanges rowRanges, ColumnIndexStore ciStore) throws IOException {
      ColumnChunkPageReadStore rowGroup = new ColumnChunkPageReadStore(rowRanges, block.getRowIndexOffset());
      ChunkListBuilder builder = new ChunkListBuilder(block.getRowCount());
      List<ConsecutivePartList> allParts = new ArrayList();
      ConsecutivePartList currentParts = null;

      for(ColumnChunkMetaData mc : block.getColumns()) {
         ColumnPath pathKey = mc.getPath();
         ColumnDescriptor columnDescriptor = (ColumnDescriptor)this.paths.get(pathKey);
         if (columnDescriptor != null) {
            OffsetIndex offsetIndex = ciStore.getOffsetIndex(mc.getPath());
            OffsetIndex filteredOffsetIndex = ColumnIndexFilterUtils.filterOffsetIndex(offsetIndex, rowRanges, block.getRowCount());

            for(ColumnIndexFilterUtils.OffsetRange range : ColumnIndexFilterUtils.calculateOffsetRanges(filteredOffsetIndex, mc, offsetIndex.getOffset(0))) {
               BenchmarkCounter.incrementTotalBytes(range.getLength());
               long startingPos = range.getOffset();
               if (currentParts == null || currentParts.endPos() != startingPos) {
                  currentParts = new ConsecutivePartList(startingPos);
                  allParts.add(currentParts);
               }

               ChunkDescriptor chunkDescriptor = new ChunkDescriptor(columnDescriptor, mc, startingPos, range.getLength());
               currentParts.addChunk(chunkDescriptor);
               builder.setOffsetIndex(chunkDescriptor, filteredOffsetIndex);
            }
         }
      }

      this.readAllPartsVectoredOrNormal(allParts, builder);
      rowGroup.setReleaser(builder.releaser);

      for(Chunk chunk : builder.build()) {
         this.readChunkPages(chunk, block, rowGroup);
      }

      return rowGroup;
   }

   private void readChunkPages(Chunk chunk, BlockMetaData block, ColumnChunkPageReadStore rowGroup) throws IOException {
      if (null != this.fileDecryptor && !this.fileDecryptor.plaintextFile()) {
         ColumnPath columnPath = ColumnPath.get(chunk.descriptor.col.getPath());
         InternalColumnDecryptionSetup columnDecryptionSetup = this.fileDecryptor.getColumnSetup(columnPath);
         if (!columnDecryptionSetup.isEncrypted()) {
            rowGroup.addColumn(chunk.descriptor.col, chunk.readAllPages());
         } else {
            rowGroup.addColumn(chunk.descriptor.col, chunk.readAllPages(columnDecryptionSetup.getMetaDataDecryptor(), columnDecryptionSetup.getDataDecryptor(), this.fileDecryptor.getFileAAD(), block.getOrdinal(), columnDecryptionSetup.getOrdinal()));
         }

      } else {
         rowGroup.addColumn(chunk.descriptor.col, chunk.readAllPages());
      }
   }

   public ColumnIndexStore getColumnIndexStore(int blockIndex) {
      ColumnIndexStore ciStore = (ColumnIndexStore)this.blockIndexStores.get(blockIndex);
      if (ciStore == null) {
         ciStore = ColumnIndexStoreImpl.create(this, (BlockMetaData)this.blocks.get(blockIndex), this.paths.keySet());
         this.blockIndexStores.set(blockIndex, ciStore);
      }

      return ciStore;
   }

   private RowRanges getRowRanges(int blockIndex) {
      assert FilterCompat.isFilteringRequired(this.options.getRecordFilter()) : "Should not be invoked if filter is null or NOOP";

      RowRanges rowRanges = (RowRanges)this.blockRowRanges.get(blockIndex);
      if (rowRanges == null) {
         rowRanges = ColumnIndexFilter.calculateRowRanges(this.options.getRecordFilter(), this.getColumnIndexStore(blockIndex), this.paths.keySet(), ((BlockMetaData)this.blocks.get(blockIndex)).getRowCount());
         this.blockRowRanges.set(blockIndex, rowRanges);
      }

      return rowRanges;
   }

   public boolean skipNextRowGroup() {
      return this.advanceToNextBlock();
   }

   private boolean advanceToNextBlock() {
      if (this.currentBlock == this.blocks.size()) {
         return false;
      } else {
         ++this.currentBlock;
         if (this.nextDictionaryReader != null) {
            this.nextDictionaryReader.close();
         }

         this.nextDictionaryReader = null;
         return true;
      }
   }

   public DictionaryPageReadStore getNextDictionaryReader() {
      if (this.nextDictionaryReader == null) {
         this.nextDictionaryReader = this.getDictionaryReader(this.currentBlock);
      }

      return this.nextDictionaryReader;
   }

   public DictionaryPageReader getDictionaryReader(int blockIndex) {
      return blockIndex >= 0 && blockIndex < this.blocks.size() ? new DictionaryPageReader(this, (BlockMetaData)this.blocks.get(blockIndex), this.options.getAllocator()) : null;
   }

   public DictionaryPageReader getDictionaryReader(BlockMetaData block) {
      return new DictionaryPageReader(this, block, this.options.getAllocator());
   }

   DictionaryPage readDictionary(ColumnChunkMetaData meta) throws IOException {
      if (!meta.hasDictionaryPage()) {
         return null;
      } else {
         if (this.f.getPos() != meta.getStartingPos()) {
            this.f.seek(meta.getStartingPos());
         }

         boolean encryptedColumn = false;
         InternalColumnDecryptionSetup columnDecryptionSetup = null;
         byte[] dictionaryPageAAD = null;
         BlockCipher.Decryptor pageDecryptor = null;
         if (null != this.fileDecryptor && !this.fileDecryptor.plaintextFile()) {
            columnDecryptionSetup = this.fileDecryptor.getColumnSetup(meta.getPath());
            if (columnDecryptionSetup.isEncrypted()) {
               encryptedColumn = true;
            }
         }

         PageHeader pageHeader;
         if (!encryptedColumn) {
            pageHeader = Util.readPageHeader(this.f);
         } else {
            byte[] dictionaryPageHeaderAAD = AesCipher.createModuleAAD(this.fileDecryptor.getFileAAD(), ModuleCipherFactory.ModuleType.DictionaryPageHeader, meta.getRowGroupOrdinal(), columnDecryptionSetup.getOrdinal(), -1);
            pageHeader = Util.readPageHeader(this.f, columnDecryptionSetup.getMetaDataDecryptor(), dictionaryPageHeaderAAD);
            dictionaryPageAAD = AesCipher.createModuleAAD(this.fileDecryptor.getFileAAD(), ModuleCipherFactory.ModuleType.DictionaryPage, meta.getRowGroupOrdinal(), columnDecryptionSetup.getOrdinal(), -1);
            pageDecryptor = columnDecryptionSetup.getDataDecryptor();
         }

         if (!pageHeader.isSetDictionary_page_header()) {
            return null;
         } else {
            DictionaryPage compressedPage = this.readCompressedDictionary(pageHeader, this.f, pageDecryptor, dictionaryPageAAD);
            CompressionCodecFactory.BytesInputDecompressor decompressor = this.options.getCodecFactory().getDecompressor(meta.getCodec());
            return new DictionaryPage(decompressor.decompress(compressedPage.getBytes(), compressedPage.getUncompressedSize()), compressedPage.getDictionarySize(), compressedPage.getEncoding());
         }
      }
   }

   private DictionaryPage readCompressedDictionary(PageHeader pageHeader, SeekableInputStream fin, BlockCipher.Decryptor pageDecryptor, byte[] dictionaryPageAAD) throws IOException {
      DictionaryPageHeader dictHeader = pageHeader.getDictionary_page_header();
      int uncompressedPageSize = pageHeader.getUncompressed_page_size();
      int compressedPageSize = pageHeader.getCompressed_page_size();
      BytesInput bin = BytesInput.from(fin, compressedPageSize);
      if (null != pageDecryptor) {
         bin = BytesInput.from(pageDecryptor.decrypt(bin.toByteArray(), dictionaryPageAAD));
      }

      return new DictionaryPage(bin, uncompressedPageSize, dictHeader.getNum_values(), this.converter.getEncoding(dictHeader.getEncoding()));
   }

   public BloomFilterReader getBloomFilterDataReader(int blockIndex) {
      return blockIndex >= 0 && blockIndex < this.blocks.size() ? new BloomFilterReader(this, (BlockMetaData)this.blocks.get(blockIndex)) : null;
   }

   public BloomFilterReader getBloomFilterDataReader(BlockMetaData block) {
      return new BloomFilterReader(this, block);
   }

   public BloomFilter readBloomFilter(ColumnChunkMetaData meta) throws IOException {
      long bloomFilterOffset = meta.getBloomFilterOffset();
      if (bloomFilterOffset < 0L) {
         return null;
      } else {
         BlockCipher.Decryptor bloomFilterDecryptor = null;
         byte[] bloomFilterHeaderAAD = null;
         byte[] bloomFilterBitsetAAD = null;
         if (null != this.fileDecryptor && !this.fileDecryptor.plaintextFile()) {
            InternalColumnDecryptionSetup columnDecryptionSetup = this.fileDecryptor.getColumnSetup(meta.getPath());
            if (columnDecryptionSetup.isEncrypted()) {
               bloomFilterDecryptor = columnDecryptionSetup.getMetaDataDecryptor();
               bloomFilterHeaderAAD = AesCipher.createModuleAAD(this.fileDecryptor.getFileAAD(), ModuleCipherFactory.ModuleType.BloomFilterHeader, meta.getRowGroupOrdinal(), columnDecryptionSetup.getOrdinal(), -1);
               bloomFilterBitsetAAD = AesCipher.createModuleAAD(this.fileDecryptor.getFileAAD(), ModuleCipherFactory.ModuleType.BloomFilterBitset, meta.getRowGroupOrdinal(), columnDecryptionSetup.getOrdinal(), -1);
            }
         }

         this.f.seek(bloomFilterOffset);
         int bloomFilterLength = meta.getBloomFilterLength();
         InputStream in = this.f;
         if (bloomFilterLength > 0) {
            byte[] headerAndBitSet = new byte[bloomFilterLength];
            this.f.readFully(headerAndBitSet);
            in = new ByteArrayInputStream(headerAndBitSet);
         }

         BloomFilterHeader bloomFilterHeader;
         try {
            bloomFilterHeader = Util.readBloomFilterHeader(in, bloomFilterDecryptor, bloomFilterHeaderAAD);
         } catch (IOException var12) {
            LOG.warn("read no bloom filter");
            return null;
         }

         int numBytes = bloomFilterHeader.getNumBytes();
         if (numBytes > 0 && numBytes <= 134217728) {
            if (bloomFilterHeader.getHash().isSetXXHASH() && bloomFilterHeader.getAlgorithm().isSetBLOCK() && bloomFilterHeader.getCompression().isSetUNCOMPRESSED()) {
               byte[] bitset;
               if (null == bloomFilterDecryptor) {
                  bitset = new byte[numBytes];
                  in.read(bitset);
               } else {
                  bitset = bloomFilterDecryptor.decrypt(in, bloomFilterBitsetAAD);
                  if (bitset.length != numBytes) {
                     throw new ParquetCryptoRuntimeException("Wrong length of decrypted bloom filter bitset");
                  }
               }

               return new BlockSplitBloomFilter(bitset);
            } else {
               LOG.warn("the read bloom filter is not supported yet,  algorithm = {}, hash = {}, compression = {}", new Object[]{bloomFilterHeader.getAlgorithm(), bloomFilterHeader.getHash(), bloomFilterHeader.getCompression()});
               return null;
            }
         } else {
            LOG.warn("the read bloom filter size is wrong, size is {}", bloomFilterHeader.getNumBytes());
            return null;
         }
      }
   }

   public ColumnIndex readColumnIndex(ColumnChunkMetaData column) throws IOException {
      IndexReference ref = column.getColumnIndexReference();
      if (ref == null) {
         return null;
      } else {
         this.f.seek(ref.getOffset());
         BlockCipher.Decryptor columnIndexDecryptor = null;
         byte[] columnIndexAAD = null;
         if (null != this.fileDecryptor && !this.fileDecryptor.plaintextFile()) {
            InternalColumnDecryptionSetup columnDecryptionSetup = this.fileDecryptor.getColumnSetup(column.getPath());
            if (columnDecryptionSetup.isEncrypted()) {
               columnIndexDecryptor = columnDecryptionSetup.getMetaDataDecryptor();
               columnIndexAAD = AesCipher.createModuleAAD(this.fileDecryptor.getFileAAD(), ModuleCipherFactory.ModuleType.ColumnIndex, column.getRowGroupOrdinal(), columnDecryptionSetup.getOrdinal(), -1);
            }
         }

         return ParquetMetadataConverter.fromParquetColumnIndex(column.getPrimitiveType(), Util.readColumnIndex(this.f, columnIndexDecryptor, columnIndexAAD));
      }
   }

   public OffsetIndex readOffsetIndex(ColumnChunkMetaData column) throws IOException {
      IndexReference ref = column.getOffsetIndexReference();
      if (ref == null) {
         return null;
      } else {
         this.f.seek(ref.getOffset());
         BlockCipher.Decryptor offsetIndexDecryptor = null;
         byte[] offsetIndexAAD = null;
         if (null != this.fileDecryptor && !this.fileDecryptor.plaintextFile()) {
            InternalColumnDecryptionSetup columnDecryptionSetup = this.fileDecryptor.getColumnSetup(column.getPath());
            if (columnDecryptionSetup.isEncrypted()) {
               offsetIndexDecryptor = columnDecryptionSetup.getMetaDataDecryptor();
               offsetIndexAAD = AesCipher.createModuleAAD(this.fileDecryptor.getFileAAD(), ModuleCipherFactory.ModuleType.OffsetIndex, column.getRowGroupOrdinal(), columnDecryptionSetup.getOrdinal(), -1);
            }
         }

         return ParquetMetadataConverter.fromParquetOffsetIndex(Util.readOffsetIndex(this.f, offsetIndexDecryptor, offsetIndexAAD));
      }
   }

   public void close() throws IOException {
      try {
         if (this.f != null) {
            this.f.close();
         }
      } finally {
         AutoCloseables.uncheckedClose(new AutoCloseable[]{this.nextDictionaryReader, this.crcAllocator});
         this.options.getCodecFactory().release();
      }

   }

   private class ChunkListBuilder {
      private final Map map = new HashMap();
      private ChunkDescriptor lastDescriptor;
      private final long rowCount;
      private SeekableInputStream f;
      private final ByteBufferReleaser releaser;

      public ChunkListBuilder(long rowCount) {
         this.releaser = new ByteBufferReleaser(ParquetFileReader.this.options.getAllocator());
         this.rowCount = rowCount;
      }

      void add(ChunkDescriptor descriptor, List buffers, SeekableInputStream f) {
         ((ChunkData)this.map.computeIfAbsent(descriptor, (d) -> new ChunkData())).buffers.addAll(buffers);
         this.lastDescriptor = descriptor;
         this.f = f;
      }

      void addBuffersToRelease(List toRelease) {
         ByteBufferReleaser var10001 = this.releaser;
         toRelease.forEach(var10001::releaseLater);
      }

      void setOffsetIndex(ChunkDescriptor descriptor, OffsetIndex offsetIndex) {
         ((ChunkData)this.map.computeIfAbsent(descriptor, (d) -> new ChunkData())).offsetIndex = offsetIndex;
      }

      List build() {
         Set<Map.Entry<ChunkDescriptor, ChunkData>> entries = this.map.entrySet();
         List<Chunk> chunks = new ArrayList(entries.size());

         for(Map.Entry entry : entries) {
            ChunkDescriptor descriptor = (ChunkDescriptor)entry.getKey();
            ChunkData data = (ChunkData)entry.getValue();
            if (descriptor.equals(this.lastDescriptor)) {
               chunks.add(ParquetFileReader.this.new WorkaroundChunk(this.lastDescriptor, data.buffers, this.f, data.offsetIndex, this.rowCount));
            } else {
               chunks.add(ParquetFileReader.this.new Chunk(descriptor, data.buffers, data.offsetIndex, this.rowCount));
            }
         }

         return chunks;
      }

      private class ChunkData {
         final List buffers;
         OffsetIndex offsetIndex;

         private ChunkData() {
            this.buffers = new ArrayList();
         }
      }
   }

   private class Chunk {
      protected final ChunkDescriptor descriptor;
      protected final ByteBufferInputStream stream;
      final OffsetIndex offsetIndex;
      final long rowCount;

      public Chunk(ChunkDescriptor descriptor, List buffers, OffsetIndex offsetIndex, long rowCount) {
         this.descriptor = descriptor;
         this.stream = ByteBufferInputStream.wrap(buffers);
         this.offsetIndex = offsetIndex;
         this.rowCount = rowCount;
      }

      protected PageHeader readPageHeader() throws IOException {
         return this.readPageHeader((BlockCipher.Decryptor)null, (byte[])null);
      }

      protected PageHeader readPageHeader(BlockCipher.Decryptor blockDecryptor, byte[] pageHeaderAAD) throws IOException {
         return Util.readPageHeader(this.stream, blockDecryptor, pageHeaderAAD);
      }

      private void verifyCrc(int referenceCrc, BytesInput bytes, String exceptionMsg) {
         ParquetFileReader.this.crc.reset();
         ByteBufferReleaser releaser = ParquetFileReader.this.crcAllocator.getReleaser();
         Throwable var5 = null;

         try {
            ParquetFileReader.this.crc.update(bytes.toByteBuffer(releaser));
         } catch (Throwable var14) {
            var5 = var14;
            throw var14;
         } finally {
            if (releaser != null) {
               if (var5 != null) {
                  try {
                     releaser.close();
                  } catch (Throwable var13) {
                     var5.addSuppressed(var13);
                  }
               } else {
                  releaser.close();
               }
            }

         }

         if (ParquetFileReader.this.crc.getValue() != ((long)referenceCrc & 4294967295L)) {
            throw new ParquetDecodingException(exceptionMsg);
         }
      }

      public ColumnChunkPageReadStore.ColumnChunkPageReader readAllPages() throws IOException {
         return this.readAllPages((BlockCipher.Decryptor)null, (BlockCipher.Decryptor)null, (byte[])null, -1, -1);
      }

      public ColumnChunkPageReadStore.ColumnChunkPageReader readAllPages(BlockCipher.Decryptor headerBlockDecryptor, BlockCipher.Decryptor pageBlockDecryptor, byte[] aadPrefix, int rowGroupOrdinal, int columnOrdinal) throws IOException {
         List<DataPage> pagesInChunk = new ArrayList();
         DictionaryPage dictionaryPage = null;
         PrimitiveType type = ParquetFileReader.this.getFileMetaData().getSchema().getType(this.descriptor.col.getPath()).asPrimitiveType();
         long valuesCountReadSoFar = 0L;
         int dataPageCountReadSoFar = 0;
         byte[] dataPageHeaderAAD = null;
         if (null != headerBlockDecryptor) {
            dataPageHeaderAAD = AesCipher.createModuleAAD(aadPrefix, ModuleCipherFactory.ModuleType.DataPageHeader, rowGroupOrdinal, columnOrdinal, this.getPageOrdinal(dataPageCountReadSoFar));
         }

         while(this.hasMorePages(valuesCountReadSoFar, dataPageCountReadSoFar)) {
            byte[] pageHeaderAAD = dataPageHeaderAAD;
            if (null != headerBlockDecryptor) {
               if (null == dictionaryPage && this.descriptor.metadata.hasDictionaryPage()) {
                  pageHeaderAAD = AesCipher.createModuleAAD(aadPrefix, ModuleCipherFactory.ModuleType.DictionaryPageHeader, rowGroupOrdinal, columnOrdinal, -1);
               } else {
                  int pageOrdinal = this.getPageOrdinal(dataPageCountReadSoFar);
                  AesCipher.quickUpdatePageAAD(dataPageHeaderAAD, pageOrdinal);
               }
            }

            PageHeader pageHeader = this.readPageHeader(headerBlockDecryptor, pageHeaderAAD);
            int uncompressedPageSize = pageHeader.getUncompressed_page_size();
            int compressedPageSize = pageHeader.getCompressed_page_size();
            switch (pageHeader.type) {
               case DICTIONARY_PAGE:
                  if (dictionaryPage != null) {
                     throw new ParquetDecodingException("more than one dictionary page in column " + this.descriptor.col);
                  }

                  BytesInput pageBytes = this.readAsBytesInput(compressedPageSize);
                  if (ParquetFileReader.this.options.usePageChecksumVerification() && pageHeader.isSetCrc()) {
                     this.verifyCrc(pageHeader.getCrc(), pageBytes, "could not verify dictionary page integrity, CRC checksum verification failed");
                  }

                  DictionaryPageHeader dicHeader = pageHeader.getDictionary_page_header();
                  dictionaryPage = new DictionaryPage(pageBytes, uncompressedPageSize, dicHeader.getNum_values(), ParquetFileReader.this.converter.getEncoding(dicHeader.getEncoding()));
                  if (pageHeader.isSetCrc()) {
                     dictionaryPage.setCrc(pageHeader.getCrc());
                  }
                  break;
               case DATA_PAGE:
                  DataPageHeader dataHeaderV1 = pageHeader.getData_page_header();
                  BytesInput pageBytes = this.readAsBytesInput(compressedPageSize);
                  if (ParquetFileReader.this.options.usePageChecksumVerification() && pageHeader.isSetCrc()) {
                     this.verifyCrc(pageHeader.getCrc(), pageBytes, "could not verify page integrity, CRC checksum verification failed");
                  }

                  DataPageV1 dataPageV1 = new DataPageV1(pageBytes, dataHeaderV1.getNum_values(), uncompressedPageSize, ParquetFileReader.this.converter.fromParquetStatistics(ParquetFileReader.this.getFileMetaData().getCreatedBy(), dataHeaderV1.getStatistics(), type), ParquetFileReader.this.converter.getEncoding(dataHeaderV1.getRepetition_level_encoding()), ParquetFileReader.this.converter.getEncoding(dataHeaderV1.getDefinition_level_encoding()), ParquetFileReader.this.converter.getEncoding(dataHeaderV1.getEncoding()));
                  if (pageHeader.isSetCrc()) {
                     dataPageV1.setCrc(pageHeader.getCrc());
                  }

                  pagesInChunk.add(dataPageV1);
                  valuesCountReadSoFar += (long)dataHeaderV1.getNum_values();
                  ++dataPageCountReadSoFar;
                  break;
               case DATA_PAGE_V2:
                  DataPageHeaderV2 dataHeaderV2 = pageHeader.getData_page_header_v2();
                  int dataSize = compressedPageSize - dataHeaderV2.getRepetition_levels_byte_length() - dataHeaderV2.getDefinition_levels_byte_length();
                  BytesInput repetitionLevels = this.readAsBytesInput(dataHeaderV2.getRepetition_levels_byte_length());
                  BytesInput definitionLevels = this.readAsBytesInput(dataHeaderV2.getDefinition_levels_byte_length());
                  BytesInput values = this.readAsBytesInput(dataSize);
                  if (ParquetFileReader.this.options.usePageChecksumVerification() && pageHeader.isSetCrc()) {
                     BytesInput pageBytes = BytesInput.concat(new BytesInput[]{repetitionLevels, definitionLevels, values});
                     this.verifyCrc(pageHeader.getCrc(), pageBytes, "could not verify page integrity, CRC checksum verification failed");
                  }

                  DataPageV2 dataPageV2 = new DataPageV2(dataHeaderV2.getNum_rows(), dataHeaderV2.getNum_nulls(), dataHeaderV2.getNum_values(), repetitionLevels, definitionLevels, ParquetFileReader.this.converter.getEncoding(dataHeaderV2.getEncoding()), values, uncompressedPageSize, ParquetFileReader.this.converter.fromParquetStatistics(ParquetFileReader.this.getFileMetaData().getCreatedBy(), dataHeaderV2.getStatistics(), type), dataHeaderV2.isIs_compressed());
                  if (pageHeader.isSetCrc()) {
                     dataPageV2.setCrc(pageHeader.getCrc());
                  }

                  pagesInChunk.add(dataPageV2);
                  valuesCountReadSoFar += (long)dataHeaderV2.getNum_values();
                  ++dataPageCountReadSoFar;
                  break;
               default:
                  ParquetFileReader.LOG.debug("skipping page of type {} of size {}", pageHeader.getType(), compressedPageSize);
                  this.stream.skipFully((long)compressedPageSize);
            }
         }

         if (this.offsetIndex == null && valuesCountReadSoFar != this.descriptor.metadata.getValueCount()) {
            throw new IOException("Expected " + this.descriptor.metadata.getValueCount() + " values in column chunk at " + ParquetFileReader.this.getPath() + " offset " + this.descriptor.metadata.getFirstDataPageOffset() + " but got " + valuesCountReadSoFar + " values instead over " + pagesInChunk.size() + " pages ending at file offset " + (this.descriptor.fileOffset + this.stream.position()));
         } else {
            CompressionCodecFactory.BytesInputDecompressor decompressor = ParquetFileReader.this.options.getCodecFactory().getDecompressor(this.descriptor.metadata.getCodec());
            return new ColumnChunkPageReadStore.ColumnChunkPageReader(decompressor, pagesInChunk, dictionaryPage, this.offsetIndex, this.rowCount, pageBlockDecryptor, aadPrefix, rowGroupOrdinal, columnOrdinal, ParquetFileReader.this.options);
         }
      }

      private boolean hasMorePages(long valuesCountReadSoFar, int dataPageCountReadSoFar) {
         return this.offsetIndex == null ? valuesCountReadSoFar < this.descriptor.metadata.getValueCount() : dataPageCountReadSoFar < this.offsetIndex.getPageCount();
      }

      private int getPageOrdinal(int dataPageCountReadSoFar) {
         return null == this.offsetIndex ? dataPageCountReadSoFar : this.offsetIndex.getPageOrdinal(dataPageCountReadSoFar);
      }

      public BytesInput readAsBytesInput(int size) throws IOException {
         return BytesInput.from(this.stream.sliceBuffers((long)size));
      }
   }

   private class WorkaroundChunk extends Chunk {
      private final SeekableInputStream f;

      private WorkaroundChunk(ChunkDescriptor descriptor, List buffers, SeekableInputStream f, OffsetIndex offsetIndex, long rowCount) {
         super(descriptor, buffers, offsetIndex, rowCount);
         this.f = f;
      }

      protected PageHeader readPageHeader() throws IOException {
         this.stream.mark(8192);

         PageHeader pageHeader;
         try {
            pageHeader = Util.readPageHeader(this.stream);
         } catch (IOException var3) {
            this.stream.reset();
            ParquetFileReader.LOG.info("completing the column chunk to read the page header");
            pageHeader = Util.readPageHeader(new SequenceInputStream(this.stream, this.f));
         }

         return pageHeader;
      }

      public BytesInput readAsBytesInput(int size) throws IOException {
         int available = this.stream.available();
         if (size > available) {
            int missingBytes = size - available;
            ParquetFileReader.LOG.info("completed the column chunk with {} bytes", missingBytes);
            List<ByteBuffer> streamBuffers = this.stream.sliceBuffers((long)available);
            ByteBuffer lastBuffer = ByteBuffer.allocate(missingBytes);
            this.f.readFully(lastBuffer);
            List<ByteBuffer> buffers = new ArrayList(streamBuffers.size() + 1);
            buffers.addAll(streamBuffers);
            buffers.add(lastBuffer);
            return BytesInput.from(buffers);
         } else {
            return super.readAsBytesInput(size);
         }
      }
   }

   private static class ChunkDescriptor {
      private final ColumnDescriptor col;
      private final ColumnChunkMetaData metadata;
      private final long fileOffset;
      private final long size;

      private ChunkDescriptor(ColumnDescriptor col, ColumnChunkMetaData metadata, long fileOffset, long size) {
         this.col = col;
         this.metadata = metadata;
         this.fileOffset = fileOffset;
         this.size = size;
      }

      public int hashCode() {
         return this.col.hashCode();
      }

      public boolean equals(Object obj) {
         if (this == obj) {
            return true;
         } else {
            return obj instanceof ChunkDescriptor ? this.col.equals(((ChunkDescriptor)obj).col) : false;
         }
      }
   }

   private class ConsecutivePartList {
      private final long offset;
      private long length;
      private final List chunks = new ArrayList();

      ConsecutivePartList(long offset) {
         this.offset = offset;
      }

      public void addChunk(ChunkDescriptor descriptor) {
         this.chunks.add(descriptor);
         this.length += descriptor.size;
      }

      public void readAll(SeekableInputStream f, ChunkListBuilder builder) throws IOException {
         f.seek(this.offset);
         int fullAllocations = Math.toIntExact(this.length / (long)ParquetFileReader.this.options.getMaxAllocationSize());
         int lastAllocationSize = Math.toIntExact(this.length % (long)ParquetFileReader.this.options.getMaxAllocationSize());
         int numAllocations = fullAllocations + (lastAllocationSize > 0 ? 1 : 0);
         List<ByteBuffer> buffers = new ArrayList(numAllocations);

         for(int i = 0; i < fullAllocations; ++i) {
            buffers.add(ParquetFileReader.this.options.getAllocator().allocate(ParquetFileReader.this.options.getMaxAllocationSize()));
         }

         if (lastAllocationSize > 0) {
            buffers.add(ParquetFileReader.this.options.getAllocator().allocate(lastAllocationSize));
         }

         builder.addBuffersToRelease(buffers);
         long readStart = System.nanoTime();

         for(ByteBuffer buffer : buffers) {
            f.readFully(buffer);
            buffer.flip();
         }

         this.setReadMetrics(readStart, this.length);
         BenchmarkCounter.incrementBytesRead(this.length);
         ByteBufferInputStream stream = ByteBufferInputStream.wrap(buffers);

         for(ChunkDescriptor descriptor : this.chunks) {
            builder.add(descriptor, stream.sliceBuffers(descriptor.size), f);
         }

      }

      private void setReadMetrics(long startNs, long len) {
         ParquetMetricsCallback metricsCallback = ParquetFileReader.this.options.getMetricsCallback();
         if (metricsCallback != null) {
            long totalFileReadTimeNs = Math.max(System.nanoTime() - startNs, 0L);
            double sizeInMb = (double)len / (double)1048576.0F;
            double timeInSec = (double)totalFileReadTimeNs / 1.0E11;
            double throughput = sizeInMb / timeInSec;
            ParquetFileReader.LOG.debug("Parquet: File Read stats:  Length: {} MB, Time: {} secs, throughput: {} MB/sec ", new Object[]{sizeInMb, timeInSec, throughput});
            metricsCallback.setDuration(ParquetFileReaderMetrics.ReadTime.name(), totalFileReadTimeNs);
            metricsCallback.setValueLong(ParquetFileReaderMetrics.ReadSize.name(), this.length);
            metricsCallback.setValueDouble(ParquetFileReaderMetrics.ReadThroughput.name(), throughput);
         }

      }

      public void readFromVectoredRange(ParquetFileRange currRange, ChunkListBuilder builder) throws IOException {
         long timeoutSeconds = 300L;
         long readStart = System.nanoTime();

         ByteBuffer buffer;
         try {
            ParquetFileReader.LOG.debug("Waiting for vectored read to finish for range {} with timeout {} seconds", currRange, 300L);
            buffer = (ByteBuffer)FutureIO.awaitFuture(currRange.getDataReadFuture(), 300L, TimeUnit.SECONDS);
            this.setReadMetrics(readStart, (long)currRange.getLength());
            BenchmarkCounter.incrementBytesRead((long)currRange.getLength());
         } catch (TimeoutException e) {
            String error = String.format("Timeout while fetching result for %s with time limit %d seconds", currRange, 300L);
            ParquetFileReader.LOG.error(error, e);
            throw new IOException(error, e);
         }

         ByteBufferInputStream stream = ByteBufferInputStream.wrap(new ByteBuffer[]{buffer});

         for(ChunkDescriptor descriptor : this.chunks) {
            builder.add(descriptor, stream.sliceBuffers(descriptor.size), ParquetFileReader.this.f);
         }

      }

      public long endPos() {
         return this.offset + this.length;
      }
   }
}
