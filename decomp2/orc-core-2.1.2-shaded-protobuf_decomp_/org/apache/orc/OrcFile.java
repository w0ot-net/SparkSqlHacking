package org.apache.orc;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.orc.impl.HadoopShims;
import org.apache.orc.impl.HadoopShimsFactory;
import org.apache.orc.impl.KeyProvider;
import org.apache.orc.impl.MemoryManagerImpl;
import org.apache.orc.impl.OrcTail;
import org.apache.orc.impl.ReaderImpl;
import org.apache.orc.impl.WriterImpl;
import org.apache.orc.impl.WriterInternal;
import org.apache.orc.impl.writer.WriterImplV2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrcFile {
   private static final Logger LOG = LoggerFactory.getLogger(OrcFile.class);
   public static final String MAGIC = "ORC";
   public static final WriterVersion CURRENT_WRITER;
   private static volatile MemoryManager memoryManager;

   protected OrcFile() {
   }

   public static ReaderOptions readerOptions(Configuration conf) {
      return new ReaderOptions(conf);
   }

   public static Reader createReader(Path path, ReaderOptions options) throws IOException {
      return new ReaderImpl(path, options);
   }

   public static WriterOptions writerOptions(Configuration conf) {
      return new WriterOptions((Properties)null, conf);
   }

   public static WriterOptions writerOptions(Properties tableProperties, Configuration conf) {
      return new WriterOptions(tableProperties, conf);
   }

   private static MemoryManager getStaticMemoryManager(Configuration conf) {
      if (memoryManager == null) {
         synchronized(OrcFile.class) {
            if (memoryManager == null) {
               memoryManager = new MemoryManagerImpl(conf);
            }
         }
      }

      return memoryManager;
   }

   public static Writer createWriter(Path path, WriterOptions opts) throws IOException {
      FileSystem fs = opts.getFileSystem() == null ? path.getFileSystem(opts.getConfiguration()) : opts.getFileSystem();
      switch (opts.getVersion()) {
         case V_0_11:
         case V_0_12:
            return new WriterImpl(fs, path, opts);
         case UNSTABLE_PRE_2_0:
            return new WriterImplV2(fs, path, opts);
         default:
            throw new IllegalArgumentException("Unknown version " + String.valueOf(opts.getVersion()));
      }
   }

   static boolean understandFormat(Path path, Reader reader) {
      if (reader.getFileVersion() == OrcFile.Version.FUTURE) {
         LOG.info("Can't merge {} because it has a future version.", path);
         return false;
      } else if (reader.getWriterVersion() == OrcFile.WriterVersion.FUTURE) {
         LOG.info("Can't merge {} because it has a future writerVersion.", path);
         return false;
      } else {
         return true;
      }
   }

   private static boolean sameKeys(EncryptionKey[] first, EncryptionKey[] next) {
      if (first.length != next.length) {
         return false;
      } else {
         for(int k = 0; k < first.length; ++k) {
            if (!first[k].getKeyName().equals(next[k].getKeyName()) || first[k].getKeyVersion() != next[k].getKeyVersion() || first[k].getAlgorithm() != next[k].getAlgorithm()) {
               return false;
            }
         }

         return true;
      }
   }

   private static boolean sameMasks(DataMaskDescription[] first, DataMaskDescription[] next) {
      if (first.length != next.length) {
         return false;
      } else {
         for(int k = 0; k < first.length; ++k) {
            if (!first[k].getName().equals(next[k].getName())) {
               return false;
            }

            String[] firstParam = first[k].getParameters();
            String[] nextParam = next[k].getParameters();
            if (firstParam.length != nextParam.length) {
               return false;
            }

            for(int p = 0; p < firstParam.length; ++p) {
               if (!firstParam[p].equals(nextParam[p])) {
                  return false;
               }
            }

            TypeDescription[] firstRoots = first[k].getColumns();
            TypeDescription[] nextRoots = next[k].getColumns();
            if (firstRoots.length != nextRoots.length) {
               return false;
            }

            for(int r = 0; r < firstRoots.length; ++r) {
               if (firstRoots[r].getId() != nextRoots[r].getId()) {
                  return false;
               }
            }
         }

         return true;
      }
   }

   private static boolean sameVariants(EncryptionVariant[] first, EncryptionVariant[] next) {
      if (first.length != next.length) {
         return false;
      } else {
         for(int k = 0; k < first.length; ++k) {
            if (first[k].getKeyDescription() == null != (next[k].getKeyDescription() == null) || !first[k].getKeyDescription().getKeyName().equals(next[k].getKeyDescription().getKeyName()) || first[k].getRoot().getId() != next[k].getRoot().getId()) {
               return false;
            }
         }

         return true;
      }
   }

   static boolean readerIsCompatible(Reader firstReader, Map userMetadata, Path path, Reader reader) {
      TypeDescription schema = firstReader.getSchema();
      if (!reader.getSchema().equals(schema)) {
         LOG.info("Can't merge {} because of different schemas {} vs {}", new Object[]{path, reader.getSchema(), schema});
         return false;
      } else {
         CompressionKind compression = firstReader.getCompressionKind();
         if (reader.getCompressionKind() != compression) {
            LOG.info("Can't merge {} because of different compression {} vs {}", new Object[]{path, reader.getCompressionKind(), compression});
            return false;
         } else {
            Version fileVersion = firstReader.getFileVersion();
            if (reader.getFileVersion() != fileVersion) {
               LOG.info("Can't merge {} because of different file versions {} vs {}", new Object[]{path, reader.getFileVersion(), fileVersion});
               return false;
            } else {
               WriterVersion writerVersion = firstReader.getWriterVersion();
               if (reader.getWriterVersion() != writerVersion) {
                  LOG.info("Can't merge {} because of different writer versions {} vs {}", new Object[]{path, reader.getFileVersion(), fileVersion});
                  return false;
               } else {
                  int rowIndexStride = firstReader.getRowIndexStride();
                  if (reader.getRowIndexStride() != rowIndexStride) {
                     LOG.info("Can't merge {} because of different row index strides {} vs {}", new Object[]{path, reader.getRowIndexStride(), rowIndexStride});
                     return false;
                  } else {
                     for(String key : reader.getMetadataKeys()) {
                        ByteBuffer currentValue = (ByteBuffer)userMetadata.get(key);
                        if (currentValue != null) {
                           ByteBuffer newValue = reader.getMetadataValue(key);
                           if (!newValue.equals(currentValue)) {
                              LOG.info("Can't merge {} because of different user metadata {}", path, key);
                              return false;
                           }
                        }
                     }

                     if (!sameKeys(firstReader.getColumnEncryptionKeys(), reader.getColumnEncryptionKeys())) {
                        LOG.info("Can't merge {} because it has different encryption keys", path);
                        return false;
                     } else if (!sameMasks(firstReader.getDataMasks(), reader.getDataMasks())) {
                        LOG.info("Can't merge {} because it has different encryption masks", path);
                        return false;
                     } else if (!sameVariants(firstReader.getEncryptionVariants(), reader.getEncryptionVariants())) {
                        LOG.info("Can't merge {} because it has different encryption variants", path);
                        return false;
                     } else if (firstReader.writerUsedProlepticGregorian() != reader.writerUsedProlepticGregorian()) {
                        LOG.info("Can't merge {} because it uses a different calendar", path);
                        return false;
                     } else {
                        return true;
                     }
                  }
               }
            }
         }
      }
   }

   static void mergeMetadata(Map metadata, Reader reader) {
      for(String key : reader.getMetadataKeys()) {
         metadata.put(key, reader.getMetadataValue(key));
      }

   }

   public static List mergeFiles(Path outputPath, WriterOptions options, List inputFiles) throws IOException {
      Writer output = null;
      Configuration conf = options.getConfiguration();
      KeyProvider keyProvider = options.getKeyProvider();

      try {
         byte[] buffer = new byte[0];
         Reader firstFile = null;
         List<Path> result = new ArrayList(inputFiles.size());
         Map<String, ByteBuffer> userMetadata = new HashMap();
         int bufferSize = 0;
         Iterator var11 = inputFiles.iterator();

         while(true) {
            Path input;
            Reader reader;
            while(true) {
               if (!var11.hasNext()) {
                  if (output != null) {
                     for(Map.Entry entry : userMetadata.entrySet()) {
                        output.addUserMetadata((String)entry.getKey(), (ByteBuffer)entry.getValue());
                     }

                     output.close();
                  }

                  return result;
               }

               input = (Path)var11.next();
               FileSystem fs = input.getFileSystem(conf);
               reader = createReader(input, readerOptions(options.getConfiguration()).filesystem(fs).setKeyProvider(keyProvider));
               if (understandFormat(input, reader)) {
                  if (firstFile != null) {
                     if (!readerIsCompatible(firstFile, userMetadata, input, reader)) {
                        continue;
                     }

                     mergeMetadata(userMetadata, reader);
                     if (bufferSize < reader.getCompressionSize()) {
                        bufferSize = reader.getCompressionSize();
                        ((WriterInternal)output).increaseCompressionSize(bufferSize);
                     }
                     break;
                  }

                  firstFile = reader;
                  bufferSize = reader.getCompressionSize();
                  CompressionKind compression = reader.getCompressionKind();
                  options.bufferSize(bufferSize).version(reader.getFileVersion()).writerVersion(reader.getWriterVersion()).compress(compression).rowIndexStride(reader.getRowIndexStride()).setSchema(reader.getSchema());
                  if (compression != CompressionKind.NONE) {
                     options.enforceBufferSize().bufferSize(bufferSize);
                  }

                  mergeMetadata(userMetadata, reader);

                  for(EncryptionKey key : reader.getColumnEncryptionKeys()) {
                     options.setKeyVersion(key.getKeyName(), key.getKeyVersion(), key.getAlgorithm());
                  }

                  output = createWriter(outputPath, options);
                  break;
               }
            }

            EncryptionVariant[] variants = reader.getEncryptionVariants();
            List<StripeStatistics>[] completeList = new List[variants.length + 1];

            for(int v = 0; v < variants.length; ++v) {
               completeList[v] = reader.getVariantStripeStatistics(variants[v]);
            }

            completeList[completeList.length - 1] = reader.getVariantStripeStatistics((EncryptionVariant)null);
            StripeStatistics[] stripeStats = new StripeStatistics[completeList.length];
            FSDataInputStream inputStream = ((ReaderImpl)reader).takeFile();

            try {
               result.add(input);

               for(StripeInformation stripe : reader.getStripes()) {
                  int length = (int)stripe.getLength();
                  if (buffer.length < length) {
                     buffer = new byte[length];
                  }

                  long offset = stripe.getOffset();
                  inputStream.readFully(offset, buffer, 0, length);
                  int stripeId = (int)stripe.getStripeId();

                  for(int v = 0; v < completeList.length; ++v) {
                     stripeStats[v] = (StripeStatistics)completeList[v].get(stripeId);
                  }

                  output.appendStripe(buffer, 0, length, stripe, (StripeStatistics[])stripeStats);
               }
            } catch (Throwable var29) {
               if (inputStream != null) {
                  try {
                     inputStream.close();
                  } catch (Throwable var28) {
                     var29.addSuppressed(var28);
                  }
               }

               throw var29;
            }

            if (inputStream != null) {
               inputStream.close();
            }
         }
      } catch (Throwable t) {
         if (output != null) {
            try {
               output.close();
            } catch (Throwable var27) {
            }

            try {
               FileSystem fs = options.getFileSystem() == null ? outputPath.getFileSystem(conf) : options.getFileSystem();
               fs.delete(outputPath, false);
            } catch (Throwable var26) {
            }
         }

         throw new IOException("Problem merging files into " + String.valueOf(outputPath), t);
      }
   }

   static {
      CURRENT_WRITER = OrcFile.WriterVersion.ORC_14;
      memoryManager = null;
   }

   public static enum Version {
      V_0_11("0.11", 0, 11),
      V_0_12("0.12", 0, 12),
      UNSTABLE_PRE_2_0("UNSTABLE-PRE-2.0", 1, 9999),
      FUTURE("future", Integer.MAX_VALUE, Integer.MAX_VALUE);

      public static final Version CURRENT = V_0_12;
      private final String name;
      private final int major;
      private final int minor;

      private Version(String name, int major, int minor) {
         this.name = name;
         this.major = major;
         this.minor = minor;
      }

      public static Version byName(String name) {
         for(Version version : values()) {
            if (version.name.equals(name)) {
               return version;
            }
         }

         throw new IllegalArgumentException("Unknown ORC version " + name);
      }

      public String getName() {
         return this.name;
      }

      public int getMajor() {
         return this.major;
      }

      public int getMinor() {
         return this.minor;
      }

      // $FF: synthetic method
      private static Version[] $values() {
         return new Version[]{V_0_11, V_0_12, UNSTABLE_PRE_2_0, FUTURE};
      }
   }

   public static enum WriterImplementation {
      ORC_JAVA(0),
      ORC_CPP(1),
      PRESTO(2),
      SCRITCHLEY_GO(3),
      TRINO(4),
      CUDF(5),
      UNKNOWN(Integer.MAX_VALUE);

      private final int id;

      private WriterImplementation(int id) {
         this.id = id;
      }

      public int getId() {
         return this.id;
      }

      public static WriterImplementation from(int id) {
         WriterImplementation[] values = values();
         return id >= 0 && id < values.length - 1 ? values[id] : UNKNOWN;
      }

      // $FF: synthetic method
      private static WriterImplementation[] $values() {
         return new WriterImplementation[]{ORC_JAVA, ORC_CPP, PRESTO, SCRITCHLEY_GO, TRINO, CUDF, UNKNOWN};
      }
   }

   public static enum WriterVersion {
      ORIGINAL(OrcFile.WriterImplementation.ORC_JAVA, 0),
      HIVE_8732(OrcFile.WriterImplementation.ORC_JAVA, 1),
      HIVE_4243(OrcFile.WriterImplementation.ORC_JAVA, 2),
      HIVE_12055(OrcFile.WriterImplementation.ORC_JAVA, 3),
      HIVE_13083(OrcFile.WriterImplementation.ORC_JAVA, 4),
      ORC_101(OrcFile.WriterImplementation.ORC_JAVA, 5),
      ORC_135(OrcFile.WriterImplementation.ORC_JAVA, 6),
      ORC_517(OrcFile.WriterImplementation.ORC_JAVA, 7),
      ORC_203(OrcFile.WriterImplementation.ORC_JAVA, 8),
      ORC_14(OrcFile.WriterImplementation.ORC_JAVA, 9),
      ORC_CPP_ORIGINAL(OrcFile.WriterImplementation.ORC_CPP, 6),
      PRESTO_ORIGINAL(OrcFile.WriterImplementation.PRESTO, 6),
      SCRITCHLEY_GO_ORIGINAL(OrcFile.WriterImplementation.SCRITCHLEY_GO, 6),
      TRINO_ORIGINAL(OrcFile.WriterImplementation.TRINO, 6),
      CUDF_ORIGINAL(OrcFile.WriterImplementation.CUDF, 6),
      FUTURE(OrcFile.WriterImplementation.UNKNOWN, Integer.MAX_VALUE);

      private final int id;
      private final WriterImplementation writer;
      private static final WriterVersion[][] values = new WriterVersion[OrcFile.WriterImplementation.values().length][];

      public WriterImplementation getWriterImplementation() {
         return this.writer;
      }

      public int getId() {
         return this.id;
      }

      private WriterVersion(WriterImplementation writer, int id) {
         this.writer = writer;
         this.id = id;
      }

      public static WriterVersion from(WriterImplementation writer, int val) {
         if (writer == OrcFile.WriterImplementation.UNKNOWN) {
            return FUTURE;
         } else if (writer != OrcFile.WriterImplementation.ORC_JAVA && val < 6) {
            throw new IllegalArgumentException("ORC File with illegal version " + val + " for writer " + String.valueOf(writer));
         } else {
            WriterVersion[] versions = values[writer.id];
            if (val >= 0 && versions.length > val) {
               WriterVersion result = versions[val];
               return result == null ? FUTURE : result;
            } else {
               return FUTURE;
            }
         }
      }

      public boolean includes(WriterVersion fix) {
         return this.writer != fix.writer || this.id >= fix.id;
      }

      // $FF: synthetic method
      private static WriterVersion[] $values() {
         return new WriterVersion[]{ORIGINAL, HIVE_8732, HIVE_4243, HIVE_12055, HIVE_13083, ORC_101, ORC_135, ORC_517, ORC_203, ORC_14, ORC_CPP_ORIGINAL, PRESTO_ORIGINAL, SCRITCHLEY_GO_ORIGINAL, TRINO_ORIGINAL, CUDF_ORIGINAL, FUTURE};
      }

      static {
         for(WriterVersion v : values()) {
            WriterImplementation writer = v.writer;
            if (writer != OrcFile.WriterImplementation.UNKNOWN) {
               if (values[writer.id] == null) {
                  values[writer.id] = new WriterVersion[values().length];
               }

               if (values[writer.id][v.id] != null) {
                  throw new IllegalArgumentException("Duplicate WriterVersion id " + String.valueOf(v));
               }

               values[writer.id][v.id] = v;
            }
         }

      }
   }

   public static enum EncodingStrategy {
      SPEED,
      COMPRESSION;

      // $FF: synthetic method
      private static EncodingStrategy[] $values() {
         return new EncodingStrategy[]{SPEED, COMPRESSION};
      }
   }

   public static enum CompressionStrategy {
      SPEED,
      COMPRESSION;

      // $FF: synthetic method
      private static CompressionStrategy[] $values() {
         return new CompressionStrategy[]{SPEED, COMPRESSION};
      }
   }

   public static class ReaderOptions {
      private final Configuration conf;
      private FileSystem filesystem;
      private long maxLength = Long.MAX_VALUE;
      private OrcTail orcTail;
      private KeyProvider keyProvider;
      private FileMetadata fileMetadata;
      private boolean useUTCTimestamp;
      private boolean useProlepticGregorian;

      public ReaderOptions(Configuration conf) {
         this.conf = conf;
         this.useProlepticGregorian = OrcConf.PROLEPTIC_GREGORIAN.getBoolean(conf);
      }

      public ReaderOptions filesystem(FileSystem fs) {
         this.filesystem = fs;
         return this;
      }

      public ReaderOptions maxLength(long val) {
         this.maxLength = val;
         return this;
      }

      public ReaderOptions orcTail(OrcTail tail) {
         this.orcTail = tail;
         return this;
      }

      public ReaderOptions setKeyProvider(KeyProvider provider) {
         this.keyProvider = provider;
         return this;
      }

      public ReaderOptions convertToProlepticGregorian(boolean newValue) {
         this.useProlepticGregorian = newValue;
         return this;
      }

      public Configuration getConfiguration() {
         return this.conf;
      }

      public FileSystem getFilesystem() {
         return this.filesystem;
      }

      public long getMaxLength() {
         return this.maxLength;
      }

      public OrcTail getOrcTail() {
         return this.orcTail;
      }

      public KeyProvider getKeyProvider() {
         return this.keyProvider;
      }

      /** @deprecated */
      public ReaderOptions fileMetadata(FileMetadata metadata) {
         this.fileMetadata = metadata;
         return this;
      }

      public FileMetadata getFileMetadata() {
         return this.fileMetadata;
      }

      public ReaderOptions useUTCTimestamp(boolean value) {
         this.useUTCTimestamp = value;
         return this;
      }

      public boolean getUseUTCTimestamp() {
         return this.useUTCTimestamp;
      }

      public boolean getConvertToProlepticGregorian() {
         return this.useProlepticGregorian;
      }
   }

   public static enum BloomFilterVersion {
      ORIGINAL("original"),
      UTF8("utf8");

      private final String id;

      private BloomFilterVersion(String id) {
         this.id = id;
      }

      public String toString() {
         return this.id;
      }

      public static BloomFilterVersion fromString(String s) {
         for(BloomFilterVersion version : values()) {
            if (version.id.equals(s)) {
               return version;
            }
         }

         throw new IllegalArgumentException("Unknown BloomFilterVersion " + s);
      }

      // $FF: synthetic method
      private static BloomFilterVersion[] $values() {
         return new BloomFilterVersion[]{ORIGINAL, UTF8};
      }
   }

   public static class ZstdCompressOptions {
      private int compressionZstdLevel;
      private int compressionZstdWindowLog;

      public int getCompressionZstdLevel() {
         return this.compressionZstdLevel;
      }

      public void setCompressionZstdLevel(int compressionZstdLevel) {
         this.compressionZstdLevel = compressionZstdLevel;
      }

      public int getCompressionZstdWindowLog() {
         return this.compressionZstdWindowLog;
      }

      public void setCompressionZstdWindowLog(int compressionZstdWindowLog) {
         this.compressionZstdWindowLog = compressionZstdWindowLog;
      }
   }

   public static class WriterOptions implements Cloneable {
      private final Configuration configuration;
      private FileSystem fileSystemValue = null;
      private TypeDescription schema = null;
      private long stripeSizeValue;
      private long stripeRowCountValue;
      private long blockSizeValue;
      private boolean buildIndex;
      private int rowIndexStrideValue;
      private int bufferSizeValue;
      private boolean enforceBufferSize = false;
      private boolean blockPaddingValue;
      private CompressionKind compressValue;
      private MemoryManager memoryManagerValue;
      private Version versionValue;
      private WriterCallback callback;
      private EncodingStrategy encodingStrategy;
      private CompressionStrategy compressionStrategy;
      private ZstdCompressOptions zstdCompressOptions;
      private double paddingTolerance;
      private String bloomFilterColumns;
      private double bloomFilterFpp;
      private BloomFilterVersion bloomFilterVersion;
      private PhysicalWriter physicalWriter;
      private WriterVersion writerVersion;
      private boolean useUTCTimestamp;
      private boolean overwrite;
      private boolean writeVariableLengthBlocks;
      private HadoopShims shims;
      private String directEncodingColumns;
      private String encryption;
      private String masks;
      private KeyProvider provider;
      private boolean useProlepticGregorian;
      private Map keyOverrides;

      protected WriterOptions(Properties tableProperties, Configuration conf) {
         this.writerVersion = OrcFile.CURRENT_WRITER;
         this.keyOverrides = new HashMap();
         this.configuration = conf;
         this.memoryManagerValue = OrcFile.getStaticMemoryManager(conf);
         this.overwrite = OrcConf.OVERWRITE_OUTPUT_FILE.getBoolean(tableProperties, conf);
         this.stripeSizeValue = OrcConf.STRIPE_SIZE.getLong(tableProperties, conf);
         this.stripeRowCountValue = OrcConf.STRIPE_ROW_COUNT.getLong(tableProperties, conf);
         this.blockSizeValue = OrcConf.BLOCK_SIZE.getLong(tableProperties, conf);
         this.buildIndex = OrcConf.ENABLE_INDEXES.getBoolean(tableProperties, conf);
         this.rowIndexStrideValue = (int)OrcConf.ROW_INDEX_STRIDE.getLong(tableProperties, conf);
         this.bufferSizeValue = (int)OrcConf.BUFFER_SIZE.getLong(tableProperties, conf);
         this.blockPaddingValue = OrcConf.BLOCK_PADDING.getBoolean(tableProperties, conf);
         this.compressValue = CompressionKind.valueOf(OrcConf.COMPRESS.getString(tableProperties, conf).toUpperCase());
         this.enforceBufferSize = OrcConf.ENFORCE_COMPRESSION_BUFFER_SIZE.getBoolean(tableProperties, conf);
         String versionName = OrcConf.WRITE_FORMAT.getString(tableProperties, conf);
         this.versionValue = OrcFile.Version.byName(versionName);
         String enString = OrcConf.ENCODING_STRATEGY.getString(tableProperties, conf);
         this.encodingStrategy = OrcFile.EncodingStrategy.valueOf(enString);
         String compString = OrcConf.COMPRESSION_STRATEGY.getString(tableProperties, conf);
         this.compressionStrategy = OrcFile.CompressionStrategy.valueOf(compString);
         this.zstdCompressOptions = new ZstdCompressOptions();
         this.zstdCompressOptions.setCompressionZstdLevel(OrcConf.COMPRESSION_ZSTD_LEVEL.getInt(tableProperties, conf));
         this.zstdCompressOptions.setCompressionZstdWindowLog(OrcConf.COMPRESSION_ZSTD_WINDOWLOG.getInt(tableProperties, conf));
         this.paddingTolerance = OrcConf.BLOCK_PADDING_TOLERANCE.getDouble(tableProperties, conf);
         this.bloomFilterColumns = OrcConf.BLOOM_FILTER_COLUMNS.getString(tableProperties, conf);
         this.bloomFilterFpp = OrcConf.BLOOM_FILTER_FPP.getDouble(tableProperties, conf);
         this.bloomFilterVersion = OrcFile.BloomFilterVersion.fromString(OrcConf.BLOOM_FILTER_WRITE_VERSION.getString(tableProperties, conf));
         this.shims = HadoopShimsFactory.get();
         this.writeVariableLengthBlocks = OrcConf.WRITE_VARIABLE_LENGTH_BLOCKS.getBoolean(tableProperties, conf);
         this.directEncodingColumns = OrcConf.DIRECT_ENCODING_COLUMNS.getString(tableProperties, conf);
         this.useProlepticGregorian = OrcConf.PROLEPTIC_GREGORIAN.getBoolean(conf);
      }

      public WriterOptions clone() {
         try {
            return (WriterOptions)super.clone();
         } catch (CloneNotSupportedException var2) {
            throw new AssertionError("Expected super.clone() to work");
         }
      }

      public WriterOptions fileSystem(FileSystem value) {
         this.fileSystemValue = value;
         return this;
      }

      public WriterOptions overwrite(boolean value) {
         this.overwrite = value;
         return this;
      }

      public WriterOptions stripeSize(long value) {
         this.stripeSizeValue = value;
         return this;
      }

      public WriterOptions blockSize(long value) {
         this.blockSizeValue = value;
         return this;
      }

      public WriterOptions rowIndexStride(int value) {
         this.rowIndexStrideValue = value;
         if (this.rowIndexStrideValue <= 0) {
            this.buildIndex = false;
         }

         return this;
      }

      public WriterOptions buildIndex(boolean value) {
         this.buildIndex = value;
         if (!this.buildIndex) {
            this.rowIndexStrideValue = 0;
         }

         return this;
      }

      public WriterOptions bufferSize(int value) {
         this.bufferSizeValue = value;
         return this;
      }

      public WriterOptions enforceBufferSize() {
         this.enforceBufferSize = true;
         return this;
      }

      public WriterOptions blockPadding(boolean value) {
         this.blockPaddingValue = value;
         return this;
      }

      public WriterOptions encodingStrategy(EncodingStrategy strategy) {
         this.encodingStrategy = strategy;
         return this;
      }

      public WriterOptions paddingTolerance(double value) {
         this.paddingTolerance = value;
         return this;
      }

      public WriterOptions bloomFilterColumns(String columns) {
         this.bloomFilterColumns = columns;
         return this;
      }

      public WriterOptions bloomFilterFpp(double fpp) {
         this.bloomFilterFpp = fpp;
         return this;
      }

      public WriterOptions compress(CompressionKind value) {
         this.compressValue = value;
         return this;
      }

      public WriterOptions setSchema(TypeDescription schema) {
         this.schema = schema;
         return this;
      }

      public WriterOptions version(Version value) {
         this.versionValue = value;
         return this;
      }

      public WriterOptions callback(WriterCallback callback) {
         this.callback = callback;
         return this;
      }

      /** @deprecated */
      @Deprecated
      public WriterOptions bloomFilterVersion(BloomFilterVersion version) {
         this.bloomFilterVersion = version;
         return this;
      }

      public WriterOptions physicalWriter(PhysicalWriter writer) {
         this.physicalWriter = writer;
         return this;
      }

      public WriterOptions memory(MemoryManager value) {
         this.memoryManagerValue = value;
         return this;
      }

      public WriterOptions writeVariableLengthBlocks(boolean value) {
         this.writeVariableLengthBlocks = value;
         return this;
      }

      public WriterOptions setShims(HadoopShims value) {
         this.shims = value;
         return this;
      }

      protected WriterOptions writerVersion(WriterVersion version) {
         if (version == OrcFile.WriterVersion.FUTURE) {
            throw new IllegalArgumentException("Can't write a future version.");
         } else {
            this.writerVersion = version;
            return this;
         }
      }

      public WriterOptions useUTCTimestamp(boolean value) {
         this.useUTCTimestamp = value;
         return this;
      }

      public WriterOptions directEncodingColumns(String value) {
         this.directEncodingColumns = value;
         return this;
      }

      public WriterOptions encrypt(String value) {
         this.encryption = value;
         return this;
      }

      public WriterOptions masks(String value) {
         this.masks = value;
         return this;
      }

      public WriterOptions setKeyVersion(String keyName, int version, EncryptionAlgorithm algorithm) {
         HadoopShims.KeyMetadata meta = new HadoopShims.KeyMetadata(keyName, version, algorithm);
         this.keyOverrides.put(keyName, meta);
         return this;
      }

      public WriterOptions setKeyProvider(KeyProvider provider) {
         this.provider = provider;
         return this;
      }

      public WriterOptions setProlepticGregorian(boolean newValue) {
         this.useProlepticGregorian = newValue;
         return this;
      }

      public KeyProvider getKeyProvider() {
         return this.provider;
      }

      public boolean getBlockPadding() {
         return this.blockPaddingValue;
      }

      public long getBlockSize() {
         return this.blockSizeValue;
      }

      public String getBloomFilterColumns() {
         return this.bloomFilterColumns;
      }

      public boolean getOverwrite() {
         return this.overwrite;
      }

      public FileSystem getFileSystem() {
         return this.fileSystemValue;
      }

      public Configuration getConfiguration() {
         return this.configuration;
      }

      public TypeDescription getSchema() {
         return this.schema;
      }

      public long getStripeSize() {
         return this.stripeSizeValue;
      }

      public long getStripeRowCountValue() {
         return this.stripeRowCountValue;
      }

      public CompressionKind getCompress() {
         return this.compressValue;
      }

      public WriterCallback getCallback() {
         return this.callback;
      }

      public Version getVersion() {
         return this.versionValue;
      }

      public MemoryManager getMemoryManager() {
         return this.memoryManagerValue;
      }

      public int getBufferSize() {
         return this.bufferSizeValue;
      }

      public boolean isEnforceBufferSize() {
         return this.enforceBufferSize;
      }

      public int getRowIndexStride() {
         return this.rowIndexStrideValue;
      }

      public boolean isBuildIndex() {
         return this.buildIndex;
      }

      public CompressionStrategy getCompressionStrategy() {
         return this.compressionStrategy;
      }

      public EncodingStrategy getEncodingStrategy() {
         return this.encodingStrategy;
      }

      public ZstdCompressOptions getZstdCompressOptions() {
         return this.zstdCompressOptions;
      }

      public double getPaddingTolerance() {
         return this.paddingTolerance;
      }

      public double getBloomFilterFpp() {
         return this.bloomFilterFpp;
      }

      /** @deprecated */
      @Deprecated
      public BloomFilterVersion getBloomFilterVersion() {
         return this.bloomFilterVersion;
      }

      public PhysicalWriter getPhysicalWriter() {
         return this.physicalWriter;
      }

      public WriterVersion getWriterVersion() {
         return this.writerVersion;
      }

      public boolean getWriteVariableLengthBlocks() {
         return this.writeVariableLengthBlocks;
      }

      public HadoopShims getHadoopShims() {
         return this.shims;
      }

      public boolean getUseUTCTimestamp() {
         return this.useUTCTimestamp;
      }

      public String getDirectEncodingColumns() {
         return this.directEncodingColumns;
      }

      public String getEncryption() {
         return this.encryption;
      }

      public String getMasks() {
         return this.masks;
      }

      public Map getKeyOverrides() {
         return this.keyOverrides;
      }

      public boolean getProlepticGregorian() {
         return this.useProlepticGregorian;
      }
   }

   public interface WriterCallback {
      void preStripeWrite(WriterContext var1) throws IOException;

      void preFooterWrite(WriterContext var1) throws IOException;
   }

   public interface WriterContext {
      Writer getWriter();
   }
}
