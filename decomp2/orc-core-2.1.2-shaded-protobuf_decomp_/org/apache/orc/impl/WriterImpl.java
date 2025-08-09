package org.apache.orc.impl;

import com.github.luben.zstd.util.Native;
import io.airlift.compress.lz4.Lz4Compressor;
import io.airlift.compress.lz4.Lz4Decompressor;
import io.airlift.compress.lzo.LzoCompressor;
import io.airlift.compress.lzo.LzoDecompressor;
import io.airlift.compress.zstd.ZstdCompressor;
import io.airlift.compress.zstd.ZstdDecompressor;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TimeZone;
import java.util.TreeMap;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.apache.orc.ColumnStatistics;
import org.apache.orc.CompressionCodec;
import org.apache.orc.CompressionKind;
import org.apache.orc.OrcConf;
import org.apache.orc.OrcFile;
import org.apache.orc.OrcProto;
import org.apache.orc.OrcUtils;
import org.apache.orc.PhysicalWriter;
import org.apache.orc.StripeStatistics;
import org.apache.orc.TypeDescription;
import org.apache.orc.OrcProto.CalendarKind;
import org.apache.orc.OrcProto.DataMask;
import org.apache.orc.OrcProto.Encryption;
import org.apache.orc.OrcProto.EncryptionAlgorithm;
import org.apache.orc.OrcProto.EncryptionKey;
import org.apache.orc.OrcProto.EncryptionVariant;
import org.apache.orc.OrcProto.Footer;
import org.apache.orc.OrcProto.KeyProviderKind;
import org.apache.orc.OrcProto.Metadata;
import org.apache.orc.OrcProto.PostScript;
import org.apache.orc.OrcProto.StripeEncryptionVariant;
import org.apache.orc.OrcProto.StripeFooter;
import org.apache.orc.OrcProto.StripeInformation;
import org.apache.orc.OrcProto.UserMetadataItem;
import org.apache.orc.impl.writer.StreamOptions;
import org.apache.orc.impl.writer.TreeWriter;
import org.apache.orc.impl.writer.WriterContext;
import org.apache.orc.impl.writer.WriterEncryptionKey;
import org.apache.orc.impl.writer.WriterEncryptionVariant;
import org.apache.orc.protobuf.ByteString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WriterImpl implements WriterInternal, org.apache.orc.MemoryManager.Callback {
   private static final Logger LOG = LoggerFactory.getLogger(WriterImpl.class);
   private static final int MIN_ROW_INDEX_STRIDE = 1000;
   private final Path path;
   private final long stripeSize;
   private final long stripeRowCount;
   private final int rowIndexStride;
   private final TypeDescription schema;
   private final PhysicalWriter physicalWriter;
   private final OrcFile.WriterVersion writerVersion;
   private final StreamOptions unencryptedOptions;
   private long rowCount = 0L;
   private long rowsInStripe = 0L;
   private long rawDataSize = 0L;
   private int rowsInIndex = 0;
   private long lastFlushOffset = 0L;
   private int stripesAtLastFlush = -1;
   private final List stripes = new ArrayList();
   private final Map userMetadata = new TreeMap();
   private final TreeWriter treeWriter;
   private final boolean buildIndex;
   private final org.apache.orc.MemoryManager memoryManager;
   private long previousAllocation = -1L;
   private long memoryLimit;
   private final long ROWS_PER_CHECK;
   private long rowsSinceCheck = 0L;
   private final OrcFile.Version version;
   private final Configuration conf;
   private final OrcFile.WriterCallback callback;
   private final OrcFile.WriterContext callbackContext;
   private final OrcFile.EncodingStrategy encodingStrategy;
   private final OrcFile.CompressionStrategy compressionStrategy;
   private final boolean[] bloomFilterColumns;
   private final double bloomFilterFpp;
   private final OrcFile.BloomFilterVersion bloomFilterVersion;
   private final boolean writeTimeZone;
   private final boolean useUTCTimeZone;
   private final double dictionaryKeySizeThreshold;
   private final boolean[] directEncodingColumns;
   private final List unencryptedEncodings = new ArrayList();
   private SortedMap maskDescriptions = new TreeMap();
   private SortedMap keys = new TreeMap();
   private final WriterEncryptionVariant[] encryption;
   private final MaskDescriptionImpl[] columnMaskDescriptions;
   private final WriterEncryptionVariant[] columnEncryption;
   private KeyProvider keyProvider;
   private boolean needKeyFlush;
   private final boolean useProlepticGregorian;
   private boolean isClose = false;

   public WriterImpl(FileSystem fs, Path path, OrcFile.WriterOptions opts) throws IOException {
      this.path = path;
      this.conf = opts.getConfiguration();
      this.schema = opts.getSchema().clone();
      int numColumns = this.schema.getMaximumId() + 1;
      if (!opts.isEnforceBufferSize()) {
         opts.bufferSize(getEstimatedBufferSize(opts.getStripeSize(), numColumns, opts.getBufferSize()));
      }

      this.schema.annotateEncryption(opts.getEncryption(), opts.getMasks());
      this.columnEncryption = new WriterEncryptionVariant[numColumns];
      this.columnMaskDescriptions = new MaskDescriptionImpl[numColumns];
      this.encryption = this.setupEncryption(opts.getKeyProvider(), this.schema, opts.getKeyOverrides());
      this.needKeyFlush = this.encryption.length > 0;
      this.directEncodingColumns = OrcUtils.includeColumns(opts.getDirectEncodingColumns(), opts.getSchema());
      this.dictionaryKeySizeThreshold = OrcConf.DICTIONARY_KEY_SIZE_THRESHOLD.getDouble(this.conf);
      this.callback = opts.getCallback();
      if (this.callback != null) {
         this.callbackContext = () -> this;
      } else {
         this.callbackContext = null;
      }

      this.useProlepticGregorian = opts.getProlepticGregorian();
      this.writeTimeZone = hasTimestamp(this.schema);
      this.useUTCTimeZone = opts.getUseUTCTimestamp();
      this.encodingStrategy = opts.getEncodingStrategy();
      this.compressionStrategy = opts.getCompressionStrategy();
      if (opts.getRowIndexStride() >= 0 && opts.isBuildIndex()) {
         this.rowIndexStride = opts.getRowIndexStride();
      } else {
         this.rowIndexStride = 0;
      }

      this.buildIndex = this.rowIndexStride > 0;
      if (this.buildIndex && this.rowIndexStride < 1000) {
         throw new IllegalArgumentException("Row stride must be at least 1000");
      } else {
         this.writerVersion = opts.getWriterVersion();
         this.version = opts.getVersion();
         if (this.version == OrcFile.Version.FUTURE) {
            throw new IllegalArgumentException("Can not write in a unknown version.");
         } else {
            if (this.version == OrcFile.Version.UNSTABLE_PRE_2_0) {
               LOG.warn("ORC files written in " + this.version.getName() + " will not be readable by other versions of the software. It is only for developer testing.");
            }

            this.bloomFilterVersion = opts.getBloomFilterVersion();
            this.bloomFilterFpp = opts.getBloomFilterFpp();
            if (this.buildIndex && this.version != OrcFile.Version.V_0_11) {
               this.bloomFilterColumns = OrcUtils.includeColumns(opts.getBloomFilterColumns(), this.schema);
            } else {
               this.bloomFilterColumns = new boolean[this.schema.getMaximumId() + 1];
            }

            this.ROWS_PER_CHECK = Math.min(opts.getStripeRowCountValue(), OrcConf.ROWS_BETWEEN_CHECKS.getLong(this.conf));
            this.stripeRowCount = opts.getStripeRowCountValue();
            this.stripeSize = opts.getStripeSize();
            this.memoryLimit = this.stripeSize;
            this.memoryManager = opts.getMemoryManager();
            this.memoryManager.addWriter(path, this.stripeSize, this);
            this.physicalWriter = (PhysicalWriter)(opts.getPhysicalWriter() == null ? new PhysicalFsWriter(fs, path, opts, this.encryption) : opts.getPhysicalWriter());
            this.physicalWriter.writeHeader();
            this.unencryptedOptions = this.physicalWriter.getStreamOptions();
            OutStream.assertBufferSizeValid(this.unencryptedOptions.getBufferSize());
            this.treeWriter = TreeWriter.Factory.create(this.schema, (WriterEncryptionVariant)null, new StreamFactory());
            LOG.debug("ORC writer created for path: {} with stripeSize: {} options: {}", new Object[]{path, this.stripeSize, this.unencryptedOptions});
         }
      }
   }

   public static int getEstimatedBufferSize(long stripeSize, int numColumns, int bs) {
      int estBufferSize = (int)(stripeSize / (20L * (long)numColumns));
      estBufferSize = getClosestBufferSize(estBufferSize);
      return Math.min(estBufferSize, bs);
   }

   public void increaseCompressionSize(int newSize) {
      if (newSize > this.unencryptedOptions.getBufferSize()) {
         this.unencryptedOptions.bufferSize(newSize);
      }

   }

   private static int getClosestBufferSize(int size) {
      int kb4 = 4096;
      int kb256 = 262144;
      int pow2 = size == 1 ? 1 : Integer.highestOneBit(size - 1) * 2;
      return Math.min(262144, Math.max(4096, pow2));
   }

   public static CompressionCodec createCodec(CompressionKind kind) {
      switch (kind) {
         case NONE:
            return null;
         case ZLIB:
            return new ZlibCodec();
         case SNAPPY:
            return new SnappyCodec();
         case LZO:
            return new AircompressorCodec(kind, new LzoCompressor(), new LzoDecompressor());
         case LZ4:
            return new AircompressorCodec(kind, new Lz4Compressor(), new Lz4Decompressor());
         case ZSTD:
            if ("java".equalsIgnoreCase(System.getProperty("orc.compression.zstd.impl"))) {
               return new AircompressorCodec(kind, new ZstdCompressor(), new ZstdDecompressor());
            } else {
               if (Native.isLoaded()) {
                  return new ZstdCodec();
               }

               return new AircompressorCodec(kind, new ZstdCompressor(), new ZstdDecompressor());
            }
         case BROTLI:
            return new BrotliCodec();
         default:
            throw new IllegalArgumentException("Unknown compression codec: " + String.valueOf(kind));
      }
   }

   public boolean checkMemory(double newScale) throws IOException {
      this.memoryLimit = Math.round((double)this.stripeSize * newScale);
      return this.checkMemory();
   }

   private boolean checkMemory() throws IOException {
      if (this.rowsSinceCheck >= this.ROWS_PER_CHECK) {
         this.rowsSinceCheck = 0L;
         long size = this.treeWriter.estimateMemory();
         if (LOG.isDebugEnabled()) {
            LOG.debug("ORC writer " + String.valueOf(this.physicalWriter) + " size = " + size + " memoryLimit = " + this.memoryLimit + " rowsInStripe = " + this.rowsInStripe + " stripeRowCountLimit = " + this.stripeRowCount);
         }

         if (size > this.memoryLimit || this.rowsInStripe >= this.stripeRowCount) {
            this.flushStripe();
            return true;
         }
      }

      return false;
   }

   private static void writeTypes(OrcProto.Footer.Builder builder, TypeDescription schema) {
      builder.addAllTypes(OrcUtils.getOrcTypes(schema));
   }

   private void createRowIndexEntry() throws IOException {
      this.treeWriter.createRowIndexEntry();
      this.rowsInIndex = 0;
   }

   private void addEncryptedKeys(OrcProto.StripeInformation.Builder dirEntry) {
      for(WriterEncryptionVariant variant : this.encryption) {
         dirEntry.addEncryptedLocalKeys(ByteString.copyFrom(variant.getMaterial().getEncryptedKey()));
      }

      dirEntry.setEncryptStripeId((long)(1 + this.stripes.size()));
   }

   private void flushStripe() throws IOException {
      if (this.buildIndex && this.rowsInIndex != 0) {
         this.createRowIndexEntry();
      }

      if (this.rowsInStripe != 0L) {
         if (this.callback != null) {
            this.callback.preStripeWrite(this.callbackContext);
         }

         int requiredIndexEntries = this.rowIndexStride == 0 ? 0 : (int)((this.rowsInStripe + (long)this.rowIndexStride - 1L) / (long)this.rowIndexStride);
         OrcProto.StripeFooter.Builder builder = StripeFooter.newBuilder();
         if (this.writeTimeZone) {
            if (this.useUTCTimeZone) {
               builder.setWriterTimezone("UTC");
            } else {
               builder.setWriterTimezone(TimeZone.getDefault().getID());
            }
         }

         this.treeWriter.flushStreams();
         this.treeWriter.writeStripe(requiredIndexEntries);
         builder.addAllColumns(this.unencryptedEncodings);
         this.unencryptedEncodings.clear();

         for(WriterEncryptionVariant writerEncryptionVariant : this.encryption) {
            OrcProto.StripeEncryptionVariant.Builder encrypt = StripeEncryptionVariant.newBuilder();
            encrypt.addAllEncoding(writerEncryptionVariant.getEncodings());
            writerEncryptionVariant.clearEncodings();
            builder.addEncryption(encrypt);
         }

         OrcProto.StripeInformation.Builder dirEntry = StripeInformation.newBuilder().setNumberOfRows(this.rowsInStripe);
         if (this.encryption.length > 0 && this.needKeyFlush) {
            this.addEncryptedKeys(dirEntry);
            this.needKeyFlush = false;
         }

         this.physicalWriter.finalizeStripe(builder, dirEntry);
         this.stripes.add(dirEntry.build());
         this.rowCount += this.rowsInStripe;
         this.rowsInStripe = 0L;
      }

   }

   private long computeRawDataSize() {
      return this.treeWriter.getRawDataSize();
   }

   private OrcProto.CompressionKind writeCompressionKind(CompressionKind kind) {
      switch (kind) {
         case NONE -> {
            return org.apache.orc.OrcProto.CompressionKind.NONE;
         }
         case ZLIB -> {
            return org.apache.orc.OrcProto.CompressionKind.ZLIB;
         }
         case SNAPPY -> {
            return org.apache.orc.OrcProto.CompressionKind.SNAPPY;
         }
         case LZO -> {
            return org.apache.orc.OrcProto.CompressionKind.LZO;
         }
         case LZ4 -> {
            return org.apache.orc.OrcProto.CompressionKind.LZ4;
         }
         case ZSTD -> {
            return org.apache.orc.OrcProto.CompressionKind.ZSTD;
         }
         case BROTLI -> {
            return org.apache.orc.OrcProto.CompressionKind.BROTLI;
         }
         default -> throw new IllegalArgumentException("Unknown compression " + String.valueOf(kind));
      }
   }

   private void writeMetadata() throws IOException {
      this.physicalWriter.writeFileMetadata(Metadata.newBuilder());
   }

   private long writePostScript() throws IOException {
      OrcProto.PostScript.Builder builder = PostScript.newBuilder().setMagic("ORC").addVersion(this.version.getMajor()).addVersion(this.version.getMinor()).setWriterVersion(this.writerVersion.getId());
      CompressionCodec codec = this.unencryptedOptions.getCodec();
      if (codec == null) {
         builder.setCompression(org.apache.orc.OrcProto.CompressionKind.NONE);
      } else {
         builder.setCompression(this.writeCompressionKind(codec.getKind())).setCompressionBlockSize((long)this.unencryptedOptions.getBufferSize());
      }

      return this.physicalWriter.writePostScript(builder);
   }

   private OrcProto.EncryptionKey.Builder writeEncryptionKey(WriterEncryptionKey key) {
      OrcProto.EncryptionKey.Builder result = EncryptionKey.newBuilder();
      HadoopShims.KeyMetadata meta = key.getMetadata();
      result.setKeyName(meta.getKeyName());
      result.setKeyVersion(meta.getVersion());
      result.setAlgorithm(EncryptionAlgorithm.forNumber(meta.getAlgorithm().getSerialization()));
      return result;
   }

   private OrcProto.EncryptionVariant.Builder writeEncryptionVariant(WriterEncryptionVariant variant) {
      OrcProto.EncryptionVariant.Builder result = EncryptionVariant.newBuilder();
      result.setRoot(variant.getRoot().getId());
      result.setKey(variant.getKeyDescription().getId());
      result.setEncryptedKey(ByteString.copyFrom(variant.getMaterial().getEncryptedKey()));
      return result;
   }

   private OrcProto.Encryption.Builder writeEncryptionFooter() {
      OrcProto.Encryption.Builder encrypt = Encryption.newBuilder();

      for(MaskDescriptionImpl mask : this.maskDescriptions.values()) {
         OrcProto.DataMask.Builder maskBuilder = DataMask.newBuilder();
         maskBuilder.setName(mask.getName());

         for(String param : mask.getParameters()) {
            maskBuilder.addMaskParameters(param);
         }

         for(TypeDescription column : mask.getColumns()) {
            maskBuilder.addColumns(column.getId());
         }

         encrypt.addMask(maskBuilder);
      }

      for(WriterEncryptionKey key : this.keys.values()) {
         encrypt.addKey(this.writeEncryptionKey(key));
      }

      for(WriterEncryptionVariant variant : this.encryption) {
         encrypt.addVariants(this.writeEncryptionVariant(variant));
      }

      encrypt.setKeyProvider(KeyProviderKind.forNumber(this.keyProvider.getKind().getValue()));
      return encrypt;
   }

   private long writeFooter() throws IOException {
      this.writeMetadata();
      OrcProto.Footer.Builder builder = Footer.newBuilder();
      builder.setNumberOfRows(this.rowCount);
      builder.setRowIndexStride(this.rowIndexStride);
      this.rawDataSize = this.computeRawDataSize();
      writeTypes(builder, this.schema);
      builder.setCalendar(this.useProlepticGregorian ? CalendarKind.PROLEPTIC_GREGORIAN : CalendarKind.JULIAN_GREGORIAN);

      for(OrcProto.StripeInformation stripe : this.stripes) {
         builder.addStripes(stripe);
      }

      this.treeWriter.writeFileStatistics();

      for(Map.Entry entry : this.userMetadata.entrySet()) {
         builder.addMetadata(UserMetadataItem.newBuilder().setName((String)entry.getKey()).setValue((ByteString)entry.getValue()));
      }

      if (this.encryption.length > 0) {
         builder.setEncryption(this.writeEncryptionFooter());
      }

      builder.setWriter(OrcFile.WriterImplementation.ORC_JAVA.getId());
      builder.setSoftwareVersion(OrcUtils.getOrcVersion());
      this.physicalWriter.writeFileFooter(builder);
      return this.writePostScript();
   }

   public TypeDescription getSchema() {
      return this.schema;
   }

   public void addUserMetadata(String name, ByteBuffer value) {
      this.userMetadata.put(name, ByteString.copyFrom(value));
   }

   public void addRowBatch(VectorizedRowBatch batch) throws IOException {
      try {
         if (batch.size != 0 && this.rowsInStripe == 0L) {
            this.treeWriter.prepareStripe(this.stripes.size() + 1);
         }

         if (this.buildIndex) {
            int posn = 0;

            while(posn < batch.size) {
               int chunkSize = Math.min(batch.size - posn, this.rowIndexStride - this.rowsInIndex);
               if (!batch.isSelectedInUse()) {
                  this.treeWriter.writeRootBatch(batch, posn, chunkSize);
               } else {
                  for(int len = 1; len < chunkSize; ++len) {
                     if (batch.selected[posn + len] - batch.selected[posn] != len) {
                        chunkSize = len;
                        break;
                     }
                  }

                  this.treeWriter.writeRootBatch(batch, batch.selected[posn], chunkSize);
               }

               posn += chunkSize;
               this.rowsInIndex += chunkSize;
               this.rowsInStripe += (long)chunkSize;
               if (this.rowsInIndex >= this.rowIndexStride) {
                  this.createRowIndexEntry();
               }
            }
         } else {
            int chunkSize;
            if (!batch.isSelectedInUse()) {
               this.treeWriter.writeRootBatch(batch, 0, batch.size);
            } else {
               for(int posn = 0; posn < batch.size; posn += chunkSize) {
                  for(chunkSize = 1; posn + chunkSize < batch.size && batch.selected[posn + chunkSize] - batch.selected[posn] == chunkSize; ++chunkSize) {
                  }

                  this.treeWriter.writeRootBatch(batch, batch.selected[posn], chunkSize);
               }
            }

            this.rowsInStripe += (long)batch.size;
         }

         this.rowsSinceCheck += (long)batch.size;
         this.previousAllocation = this.memoryManager.checkMemory(this.previousAllocation, this);
         this.checkMemory();
      } catch (Throwable t) {
         try {
            this.close();
         } catch (Throwable var5) {
         }

         if (t instanceof IOException) {
            throw (IOException)t;
         } else {
            throw new IOException("Problem adding row to " + String.valueOf(this.path), t);
         }
      }
   }

   public void close() throws IOException {
      if (!this.isClose) {
         try {
            if (this.callback != null) {
               this.callback.preFooterWrite(this.callbackContext);
            }

            this.memoryManager.removeWriter(this.path);
            this.flushStripe();
            this.lastFlushOffset = this.writeFooter();
            this.physicalWriter.close();
         } finally {
            this.isClose = true;
         }
      }

   }

   public long getRawDataSize() {
      return this.rawDataSize;
   }

   public long getNumberOfRows() {
      return this.rowCount;
   }

   public long writeIntermediateFooter() throws IOException {
      this.flushStripe();
      if (this.stripesAtLastFlush != this.stripes.size()) {
         if (this.callback != null) {
            this.callback.preFooterWrite(this.callbackContext);
         }

         this.lastFlushOffset = this.writeFooter();
         this.stripesAtLastFlush = this.stripes.size();
         this.physicalWriter.flush();
      }

      return this.lastFlushOffset;
   }

   private static void checkArgument(boolean expression, String message) {
      if (!expression) {
         throw new IllegalArgumentException(message);
      }
   }

   public void appendStripe(byte[] stripe, int offset, int length, org.apache.orc.StripeInformation stripeInfo, OrcProto.StripeStatistics stripeStatistics) throws IOException {
      this.appendStripe(stripe, offset, length, stripeInfo, new StripeStatistics[]{new StripeStatisticsImpl(this.schema, stripeStatistics.getColStatsList(), false, false)});
   }

   public void appendStripe(byte[] stripe, int offset, int length, org.apache.orc.StripeInformation stripeInfo, StripeStatistics[] stripeStatistics) throws IOException {
      checkArgument(stripe != null, "Stripe must not be null");
      checkArgument(length <= stripe.length, "Specified length must not be greater specified array length");
      checkArgument(stripeInfo != null, "Stripe information must not be null");
      checkArgument(stripeStatistics != null, "Stripe statistics must not be null");
      if (this.rowsInStripe > 0L) {
         this.flushStripe();
      }

      this.rowsInStripe = stripeInfo.getNumberOfRows();
      OrcProto.StripeInformation.Builder dirEntry = StripeInformation.newBuilder().setNumberOfRows(this.rowsInStripe).setIndexLength(stripeInfo.getIndexLength()).setDataLength(stripeInfo.getDataLength()).setFooterLength(stripeInfo.getFooterLength());
      if (stripeInfo.hasEncryptionStripeId()) {
         dirEntry.setEncryptStripeId(stripeInfo.getEncryptionStripeId());

         for(byte[] key : stripeInfo.getEncryptedLocalKeys()) {
            dirEntry.addEncryptedLocalKeys(ByteString.copyFrom(key));
         }
      }

      this.physicalWriter.appendRawStripe(ByteBuffer.wrap(stripe, offset, length), dirEntry);
      this.treeWriter.addStripeStatistics(stripeStatistics);
      this.stripes.add(dirEntry.build());
      this.rowCount += this.rowsInStripe;
      this.rowsInStripe = 0L;
      this.needKeyFlush = this.encryption.length > 0;
   }

   public void appendUserMetadata(List userMetadata) {
      if (userMetadata != null) {
         for(OrcProto.UserMetadataItem item : userMetadata) {
            this.userMetadata.put(item.getName(), item.getValue());
         }
      }

   }

   public ColumnStatistics[] getStatistics() {
      ColumnStatistics[] result = new ColumnStatistics[this.schema.getMaximumId() + 1];
      this.treeWriter.getCurrentStatistics(result);
      return result;
   }

   public List getStripes() throws IOException {
      return Collections.unmodifiableList(OrcUtils.convertProtoStripesToStripes(this.stripes));
   }

   public CompressionCodec getCompressionCodec() {
      return this.unencryptedOptions.getCodec();
   }

   private static boolean hasTimestamp(TypeDescription schema) {
      if (schema.getCategory() == TypeDescription.Category.TIMESTAMP) {
         return true;
      } else {
         List<TypeDescription> children = schema.getChildren();
         if (children != null) {
            for(TypeDescription child : children) {
               if (hasTimestamp(child)) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   private WriterEncryptionKey getKey(String keyName, KeyProvider provider) throws IOException {
      WriterEncryptionKey result = (WriterEncryptionKey)this.keys.get(keyName);
      if (result == null) {
         result = new WriterEncryptionKey(provider.getCurrentKeyVersion(keyName));
         this.keys.put(keyName, result);
      }

      return result;
   }

   private MaskDescriptionImpl getMask(String maskString) {
      MaskDescriptionImpl result = (MaskDescriptionImpl)this.maskDescriptions.get(maskString);
      if (result == null) {
         result = ParserUtils.buildMaskDescription(maskString);
         this.maskDescriptions.put(maskString, result);
      }

      return result;
   }

   private int visitTypeTree(TypeDescription schema, boolean encrypted, KeyProvider provider) throws IOException {
      int result = 0;
      String keyName = schema.getAttributeValue("encrypt");
      String maskName = schema.getAttributeValue("mask");
      if (keyName != null) {
         if (provider == null) {
            throw new IllegalArgumentException("Encryption requires a KeyProvider.");
         }

         if (encrypted) {
            throw new IllegalArgumentException("Nested encryption type: " + String.valueOf(schema));
         }

         encrypted = true;
         ++result;
         WriterEncryptionKey key = this.getKey(keyName, provider);
         HadoopShims.KeyMetadata metadata = key.getMetadata();
         WriterEncryptionVariant variant = new WriterEncryptionVariant(key, schema, provider.createLocalKey(metadata));
         key.addRoot(variant);
      }

      if (encrypted && (keyName != null || maskName != null)) {
         MaskDescriptionImpl mask = this.getMask(maskName == null ? "nullify" : maskName);
         mask.addColumn(schema);
      }

      List<TypeDescription> children = schema.getChildren();
      if (children != null) {
         for(TypeDescription child : children) {
            result += this.visitTypeTree(child, encrypted, provider);
         }
      }

      return result;
   }

   private WriterEncryptionVariant[] setupEncryption(KeyProvider provider, TypeDescription schema, Map keyOverrides) throws IOException {
      this.keyProvider = provider != null ? provider : CryptoUtils.getKeyProvider(this.conf, new SecureRandom());

      for(HadoopShims.KeyMetadata key : keyOverrides.values()) {
         this.keys.put(key.getKeyName(), new WriterEncryptionKey(key));
      }

      int variantCount = this.visitTypeTree(schema, false, this.keyProvider);
      int nextId = 0;
      if (variantCount > 0) {
         for(MaskDescriptionImpl mask : this.maskDescriptions.values()) {
            mask.setId(nextId++);

            for(TypeDescription column : mask.getColumns()) {
               this.columnMaskDescriptions[column.getId()] = mask;
            }
         }
      }

      nextId = 0;
      int nextVariantId = 0;
      WriterEncryptionVariant[] result = new WriterEncryptionVariant[variantCount];

      for(WriterEncryptionKey key : this.keys.values()) {
         key.setId(nextId++);
         key.sortRoots();

         for(WriterEncryptionVariant variant : key.getEncryptionRoots()) {
            result[nextVariantId] = variant;
            this.columnEncryption[variant.getRoot().getId()] = variant;
            variant.setId(nextVariantId++);
         }
      }

      return result;
   }

   public long estimateMemory() {
      return this.treeWriter.estimateMemory();
   }

   static {
      try {
         if (!"java".equalsIgnoreCase(System.getProperty("orc.compression.zstd.impl"))) {
            Native.load();
         }
      } catch (ExceptionInInitializerError | UnsatisfiedLinkError var1) {
         LOG.warn("Unable to load zstd-jni library for your platform. Using builtin-java classes where applicable");
      }

   }

   private class StreamFactory implements WriterContext {
      public OutStream createStream(StreamName name) throws IOException {
         StreamOptions options = SerializationUtils.getCustomizedCodec(WriterImpl.this.unencryptedOptions, WriterImpl.this.compressionStrategy, name.getKind());
         WriterEncryptionVariant encryption = (WriterEncryptionVariant)name.getEncryption();
         if (encryption != null) {
            if (options == WriterImpl.this.unencryptedOptions) {
               options = new StreamOptions(options);
            }

            options.withEncryption(encryption.getKeyDescription().getAlgorithm(), encryption.getFileFooterKey()).modifyIv(CryptoUtils.modifyIvForStream(name, 1L));
         }

         return new OutStream(name, options, WriterImpl.this.physicalWriter.createDataStream(name));
      }

      public int getRowIndexStride() {
         return WriterImpl.this.rowIndexStride;
      }

      public boolean buildIndex() {
         return WriterImpl.this.buildIndex;
      }

      public boolean isCompressed() {
         return WriterImpl.this.unencryptedOptions.getCodec() != null;
      }

      public OrcFile.EncodingStrategy getEncodingStrategy() {
         return WriterImpl.this.encodingStrategy;
      }

      public boolean[] getBloomFilterColumns() {
         return WriterImpl.this.bloomFilterColumns;
      }

      public double getBloomFilterFPP() {
         return WriterImpl.this.bloomFilterFpp;
      }

      public Configuration getConfiguration() {
         return WriterImpl.this.conf;
      }

      public OrcFile.Version getVersion() {
         return WriterImpl.this.version;
      }

      public PhysicalWriter getPhysicalWriter() {
         return WriterImpl.this.physicalWriter;
      }

      /** @deprecated */
      @Deprecated
      public OrcFile.BloomFilterVersion getBloomFilterVersion() {
         return WriterImpl.this.bloomFilterVersion;
      }

      public void writeIndex(StreamName name, OrcProto.RowIndex.Builder index) throws IOException {
         WriterImpl.this.physicalWriter.writeIndex(name, index);
      }

      public void writeBloomFilter(StreamName name, OrcProto.BloomFilterIndex.Builder bloom) throws IOException {
         WriterImpl.this.physicalWriter.writeBloomFilter(name, bloom);
      }

      public WriterEncryptionVariant getEncryption(int columnId) {
         return columnId < WriterImpl.this.columnEncryption.length ? WriterImpl.this.columnEncryption[columnId] : null;
      }

      public org.apache.orc.DataMask getUnencryptedMask(int columnId) {
         if (WriterImpl.this.columnMaskDescriptions != null) {
            MaskDescriptionImpl descr = WriterImpl.this.columnMaskDescriptions[columnId];
            if (descr != null) {
               return org.apache.orc.DataMask.Factory.build(descr, WriterImpl.this.schema.findSubtype(columnId), (type) -> WriterImpl.this.columnMaskDescriptions[type.getId()]);
            }
         }

         return null;
      }

      public void setEncoding(int column, WriterEncryptionVariant encryption, OrcProto.ColumnEncoding encoding) {
         if (encryption == null) {
            WriterImpl.this.unencryptedEncodings.add(encoding);
         } else {
            encryption.addEncoding(encoding);
         }

      }

      public void writeStatistics(StreamName name, OrcProto.ColumnStatistics.Builder stats) throws IOException {
         WriterImpl.this.physicalWriter.writeStatistics(name, stats);
      }

      public boolean getUseUTCTimestamp() {
         return WriterImpl.this.useUTCTimeZone;
      }

      public double getDictionaryKeySizeThreshold(int columnId) {
         return WriterImpl.this.directEncodingColumns[columnId] ? (double)0.0F : WriterImpl.this.dictionaryKeySizeThreshold;
      }

      public boolean getProlepticGregorian() {
         return WriterImpl.this.useProlepticGregorian;
      }
   }
}
