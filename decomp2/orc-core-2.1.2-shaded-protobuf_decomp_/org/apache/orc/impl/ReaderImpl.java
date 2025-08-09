package org.apache.orc.impl;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.util.JavaDataModel;
import org.apache.hadoop.io.Text;
import org.apache.orc.ColumnStatistics;
import org.apache.orc.CompressionCodec;
import org.apache.orc.CompressionKind;
import org.apache.orc.DataMaskDescription;
import org.apache.orc.EncryptionAlgorithm;
import org.apache.orc.EncryptionKey;
import org.apache.orc.EncryptionVariant;
import org.apache.orc.FileFormatException;
import org.apache.orc.FileMetadata;
import org.apache.orc.OrcConf;
import org.apache.orc.OrcFile;
import org.apache.orc.OrcProto;
import org.apache.orc.OrcUtils;
import org.apache.orc.Reader;
import org.apache.orc.RecordReader;
import org.apache.orc.StripeInformation;
import org.apache.orc.StripeStatistics;
import org.apache.orc.TypeDescription;
import org.apache.orc.OrcProto.CalendarKind;
import org.apache.orc.OrcProto.FileStatistics;
import org.apache.orc.OrcProto.FileTail;
import org.apache.orc.OrcProto.Footer;
import org.apache.orc.OrcProto.Metadata;
import org.apache.orc.OrcProto.PostScript;
import org.apache.orc.OrcProto.Type;
import org.apache.orc.OrcProto.Stream.Kind;
import org.apache.orc.impl.reader.ReaderEncryption;
import org.apache.orc.impl.reader.ReaderEncryptionVariant;
import org.apache.orc.protobuf.CodedInputStream;
import org.apache.orc.protobuf.InvalidProtocolBufferException;
import org.apache.orc.protobuf.MessageOrBuilder;
import org.apache.orc.protobuf.TextFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReaderImpl implements Reader {
   private static final Logger LOG = LoggerFactory.getLogger(ReaderImpl.class);
   private static final OrcFile.Version[] ORC_FILE_VERSION_VALUES = OrcFile.Version.values();
   private static final OrcFile.WriterVersion[] ORC_FILE_WRITER_VERSION_VALUES = OrcFile.WriterVersion.values();
   private static final int DIRECTORY_SIZE_GUESS = 16384;
   public static final int DEFAULT_COMPRESSION_BLOCK_SIZE = 262144;
   private final long maxLength;
   protected final Path path;
   protected final OrcFile.ReaderOptions options;
   protected final CompressionKind compressionKind;
   protected FSDataInputStream file;
   protected int bufferSize;
   protected List stripeStatistics;
   private final int metadataSize;
   protected final List types;
   private final TypeDescription schema;
   private final List userMetadata;
   private final List fileStats;
   private final List stripes;
   protected final int rowIndexStride;
   private final long contentLength;
   private final long numberOfRows;
   private final ReaderEncryption encryption;
   private long deserializedSize = -1L;
   protected final Configuration conf;
   protected final boolean useUTCTimestamp;
   private final List versionList;
   private final OrcFile.WriterVersion writerVersion;
   private final String softwareVersion;
   protected final OrcTail tail;

   public long getNumberOfRows() {
      return this.numberOfRows;
   }

   public List getMetadataKeys() {
      List<String> result = new ArrayList();

      for(OrcProto.UserMetadataItem item : this.userMetadata) {
         result.add(item.getName());
      }

      return result;
   }

   public ByteBuffer getMetadataValue(String key) {
      for(OrcProto.UserMetadataItem item : this.userMetadata) {
         if (item.hasName() && item.getName().equals(key)) {
            return item.getValue().asReadOnlyByteBuffer();
         }
      }

      throw new IllegalArgumentException("Can't find user metadata " + key);
   }

   public boolean hasMetadataValue(String key) {
      for(OrcProto.UserMetadataItem item : this.userMetadata) {
         if (item.hasName() && item.getName().equals(key)) {
            return true;
         }
      }

      return false;
   }

   public CompressionKind getCompressionKind() {
      return this.compressionKind;
   }

   public int getCompressionSize() {
      return this.bufferSize;
   }

   public List getStripes() {
      return this.stripes;
   }

   public long getContentLength() {
      return this.contentLength;
   }

   public List getTypes() {
      return OrcUtils.getOrcTypes(this.schema);
   }

   public static OrcFile.Version getFileVersion(List versionList) {
      if (versionList != null && !versionList.isEmpty()) {
         for(OrcFile.Version version : ORC_FILE_VERSION_VALUES) {
            if (version.getMajor() == (Integer)versionList.get(0) && version.getMinor() == (Integer)versionList.get(1)) {
               return version;
            }
         }

         return OrcFile.Version.FUTURE;
      } else {
         return OrcFile.Version.V_0_11;
      }
   }

   public OrcFile.Version getFileVersion() {
      return getFileVersion(this.versionList);
   }

   public OrcFile.WriterVersion getWriterVersion() {
      return this.writerVersion;
   }

   public String getSoftwareVersion() {
      return this.softwareVersion;
   }

   public OrcProto.FileTail getFileTail() {
      return this.tail.getFileTail();
   }

   public EncryptionKey[] getColumnEncryptionKeys() {
      return this.encryption.getKeys();
   }

   public DataMaskDescription[] getDataMasks() {
      return this.encryption.getMasks();
   }

   public ReaderEncryptionVariant[] getEncryptionVariants() {
      return this.encryption.getVariants();
   }

   public List getVariantStripeStatistics(EncryptionVariant variant) throws IOException {
      if (variant == null) {
         if (this.stripeStatistics == null) {
            CompressionCodec codec = OrcCodecPool.getCodec(this.compressionKind);

            try {
               InStream.StreamOptions options = new InStream.StreamOptions();
               if (codec != null) {
                  options.withCodec(codec).withBufferSize(this.bufferSize);
               }

               this.stripeStatistics = deserializeStripeStats(this.tail.getTailBuffer(), this.tail.getMetadataOffset(), this.tail.getMetadataSize(), options);
            } catch (Throwable var7) {
               if (codec != null) {
                  try {
                     codec.close();
                  } catch (Throwable var6) {
                     var7.addSuppressed(var6);
                  }
               }

               throw var7;
            }

            if (codec != null) {
               codec.close();
            }
         }

         return this.convertFromProto(this.stripeStatistics);
      } else {
         CompressionCodec codec = OrcCodecPool.getCodec(this.compressionKind);

         List var4;
         try {
            InStream.StreamOptions compression = new InStream.StreamOptions();
            if (codec != null) {
               compression.withCodec(codec).withBufferSize(this.bufferSize);
            }

            var4 = ((ReaderEncryptionVariant)variant).getStripeStatistics((boolean[])null, compression, this);
         } catch (Throwable var8) {
            if (codec != null) {
               try {
                  codec.close();
               } catch (Throwable var5) {
                  var8.addSuppressed(var5);
               }
            }

            throw var8;
         }

         if (codec != null) {
            codec.close();
         }

         return var4;
      }
   }

   public ReaderEncryption getEncryption() {
      return this.encryption;
   }

   public int getRowIndexStride() {
      return this.rowIndexStride;
   }

   public ColumnStatistics[] getStatistics() {
      ColumnStatistics[] result = this.deserializeStats(this.schema, this.fileStats);
      if (this.encryption.getKeys().length > 0) {
         CompressionCodec codec = OrcCodecPool.getCodec(this.compressionKind);

         try {
            InStream.StreamOptions compression = InStream.options();
            if (codec != null) {
               compression.withCodec(codec).withBufferSize(this.bufferSize);
            }

            for(int c = this.schema.getId(); c <= this.schema.getMaximumId(); ++c) {
               ReaderEncryptionVariant variant = this.encryption.getVariant(c);
               if (variant != null) {
                  try {
                     int base = variant.getRoot().getId();
                     ColumnStatistics[] overrides = this.decryptFileStats(variant, compression, this.tail.getFooter());

                     for(int sub = 0; sub < overrides.length; ++sub) {
                        result[base + sub] = overrides[sub];
                     }
                  } catch (IOException var10) {
                     String var10002 = String.valueOf(this.path);
                     throw new RuntimeException("Can't decrypt file stats for " + var10002 + " with " + String.valueOf(variant.getKeyDescription()));
                  }
               }
            }
         } catch (Throwable var11) {
            if (codec != null) {
               try {
                  codec.close();
               } catch (Throwable var9) {
                  var11.addSuppressed(var9);
               }
            }

            throw var11;
         }

         if (codec != null) {
            codec.close();
         }
      }

      return result;
   }

   private ColumnStatistics[] decryptFileStats(ReaderEncryptionVariant encryption, InStream.StreamOptions compression, OrcProto.Footer footer) throws IOException {
      Key key = encryption.getFileFooterKey();
      if (key == null) {
         return null;
      } else {
         OrcProto.EncryptionVariant protoVariant = footer.getEncryption().getVariants(encryption.getVariantId());
         byte[] bytes = protoVariant.getFileStatistics().toByteArray();
         BufferChunk buffer = new BufferChunk(ByteBuffer.wrap(bytes), 0L);
         EncryptionAlgorithm algorithm = encryption.getKeyDescription().getAlgorithm();
         byte[] iv = new byte[algorithm.getIvLength()];
         CryptoUtils.modifyIvForStream(encryption.getRoot().getId(), Kind.FILE_STATISTICS, (long)(footer.getStripesCount() + 1)).accept(iv);
         InStream.StreamOptions options = (new InStream.StreamOptions(compression)).withEncryption(algorithm, key, iv);
         InStream in = InStream.create("encrypted file stats", buffer, 0L, (long)bytes.length, options);
         OrcProto.FileStatistics decrypted = FileStatistics.parseFrom(in);
         ColumnStatistics[] result = new ColumnStatistics[decrypted.getColumnCount()];
         TypeDescription root = encryption.getRoot();

         for(int i = 0; i < result.length; ++i) {
            result[i] = ColumnStatisticsImpl.deserialize(root.findSubtype(root.getId() + i), decrypted.getColumn(i), this.writerUsedProlepticGregorian(), this.getConvertToProlepticGregorian());
         }

         return result;
      }
   }

   public ColumnStatistics[] deserializeStats(TypeDescription schema, List fileStats) {
      ColumnStatistics[] result = new ColumnStatistics[fileStats.size()];

      for(int i = 0; i < result.length; ++i) {
         TypeDescription subschema = schema == null ? null : schema.findSubtype(i);
         result[i] = ColumnStatisticsImpl.deserialize(subschema, (OrcProto.ColumnStatistics)fileStats.get(i), this.writerUsedProlepticGregorian(), this.getConvertToProlepticGregorian());
      }

      return result;
   }

   public TypeDescription getSchema() {
      return this.schema;
   }

   protected static void ensureOrcFooter(FSDataInputStream in, Path path, int psLen, ByteBuffer buffer) throws IOException {
      int magicLength = "ORC".length();
      int fullLength = magicLength + 1;
      if (psLen >= fullLength && buffer.remaining() >= fullLength) {
         int offset = buffer.arrayOffset() + buffer.position() + buffer.limit() - fullLength;
         byte[] array = buffer.array();
         if (!Text.decode(array, offset, magicLength).equals("ORC")) {
            byte[] header = new byte[magicLength];
            in.readFully(0L, header, 0, magicLength);
            if (!Text.decode(header, 0, magicLength).equals("ORC")) {
               throw new FileFormatException("Malformed ORC file " + String.valueOf(path) + ". Invalid postscript.");
            }
         }

      } else {
         String var10002 = String.valueOf(path);
         throw new FileFormatException("Malformed ORC file " + var10002 + ". Invalid postscript length " + psLen);
      }
   }

   /** @deprecated */
   protected static void ensureOrcFooter(ByteBuffer buffer, int psLen) throws IOException {
      int magicLength = "ORC".length();
      int fullLength = magicLength + 1;
      if (psLen >= fullLength && buffer.remaining() >= fullLength) {
         int offset = buffer.arrayOffset() + buffer.position() + buffer.limit() - fullLength;
         byte[] array = buffer.array();
         if (!Text.decode(array, offset, magicLength).equals("ORC") && !Text.decode(buffer.array(), 0, magicLength).equals("ORC")) {
            throw new FileFormatException("Malformed ORC file. Invalid postscript length " + psLen);
         }
      } else {
         throw new FileFormatException("Malformed ORC file. Invalid postscript length " + psLen);
      }
   }

   private static String versionString(List version) {
      StringBuilder buffer = new StringBuilder();

      for(int i = 0; i < version.size(); ++i) {
         if (i != 0) {
            buffer.append('.');
         }

         buffer.append(version.get(i));
      }

      return buffer.toString();
   }

   protected static void checkOrcVersion(Path path, OrcProto.PostScript postscript) throws IOException {
      List<Integer> version = postscript.getVersionList();
      if (getFileVersion(version) == OrcFile.Version.FUTURE) {
         String var10002 = String.valueOf(path);
         throw new IOException(var10002 + " was written by a future ORC version " + versionString(version) + ". This file is not readable by this version of ORC.\nPostscript: " + TextFormat.shortDebugString((MessageOrBuilder)postscript));
      }
   }

   public ReaderImpl(Path path, OrcFile.ReaderOptions options) throws IOException {
      this.path = path;
      this.options = options;
      this.conf = options.getConfiguration();
      this.maxLength = options.getMaxLength();
      this.useUTCTimestamp = options.getUseUTCTimestamp();
      FileMetadata fileMetadata = options.getFileMetadata();
      if (fileMetadata != null) {
         this.compressionKind = fileMetadata.getCompressionKind();
         this.bufferSize = fileMetadata.getCompressionBufferSize();
         this.metadataSize = fileMetadata.getMetadataSize();
         this.stripeStatistics = fileMetadata.getStripeStats();
         this.versionList = fileMetadata.getVersionList();
         OrcFile.WriterImplementation writer = OrcFile.WriterImplementation.from(fileMetadata.getWriterImplementation());
         this.writerVersion = OrcFile.WriterVersion.from(writer, fileMetadata.getWriterVersionNum());
         List<OrcProto.Type> types = fileMetadata.getTypes();
         OrcUtils.isValidTypeTree(types, 0);
         this.schema = OrcUtils.convertTypeFromProtobuf(types, 0);
         this.rowIndexStride = fileMetadata.getRowIndexStride();
         this.contentLength = fileMetadata.getContentLength();
         this.numberOfRows = fileMetadata.getNumberOfRows();
         this.fileStats = fileMetadata.getFileStats();
         this.stripes = fileMetadata.getStripes();
         this.tail = null;
         this.userMetadata = null;
         this.encryption = new ReaderEncryption();
         this.softwareVersion = null;
      } else {
         OrcTail orcTail = options.getOrcTail();
         if (orcTail == null) {
            this.tail = this.extractFileTail(this.getFileSystem(), path, options.getMaxLength());
            options.orcTail(this.tail);
         } else {
            checkOrcVersion(path, orcTail.getPostScript());
            this.tail = orcTail;
         }

         this.compressionKind = this.tail.getCompressionKind();
         this.bufferSize = this.tail.getCompressionBufferSize();
         this.metadataSize = this.tail.getMetadataSize();
         this.versionList = this.tail.getPostScript().getVersionList();
         this.schema = this.tail.getSchema();
         this.rowIndexStride = this.tail.getFooter().getRowIndexStride();
         this.contentLength = this.tail.getFooter().getContentLength();
         this.numberOfRows = this.tail.getFooter().getNumberOfRows();
         this.userMetadata = this.tail.getFooter().getMetadataList();
         this.fileStats = this.tail.getFooter().getStatisticsList();
         this.writerVersion = this.tail.getWriterVersion();
         this.stripes = this.tail.getStripes();
         this.stripeStatistics = null;
         OrcProto.Footer footer = this.tail.getFooter();
         this.encryption = new ReaderEncryption(footer, this.schema, this.tail.getStripeStatisticsOffset(), this.tail.getTailBuffer(), this.stripes, options.getKeyProvider(), this.conf);
         this.softwareVersion = OrcUtils.getSoftwareVersion(footer.getWriter(), footer.getSoftwareVersion());
      }

      this.types = OrcUtils.getOrcTypes(this.schema);
   }

   protected FileSystem getFileSystem() throws IOException {
      FileSystem fileSystem = this.options.getFilesystem();
      if (fileSystem == null) {
         fileSystem = this.path.getFileSystem(this.options.getConfiguration());
         this.options.filesystem(fileSystem);
      }

      return fileSystem;
   }

   protected Supplier getFileSystemSupplier() {
      return () -> {
         try {
            return this.getFileSystem();
         } catch (IOException e) {
            throw new RuntimeException("Can't create filesystem", e);
         }
      };
   }

   public static OrcFile.WriterVersion getWriterVersion(int writerVersion) {
      for(OrcFile.WriterVersion version : ORC_FILE_WRITER_VERSION_VALUES) {
         if (version.getId() == writerVersion) {
            return version;
         }
      }

      return OrcFile.WriterVersion.FUTURE;
   }

   public static OrcProto.Metadata extractMetadata(ByteBuffer bb, int metadataAbsPos, int metadataSize, InStream.StreamOptions options) throws IOException {
      bb.position(metadataAbsPos);
      bb.limit(metadataAbsPos + metadataSize);
      return Metadata.parseFrom(InStream.createCodedInputStream(InStream.create("metadata", new BufferChunk(bb, 0L), 0L, (long)metadataSize, options)));
   }

   private static OrcProto.PostScript extractPostScript(BufferChunk buffer, Path path, int psLen, long psOffset) throws IOException {
      CodedInputStream in = InStream.createCodedInputStream(InStream.create("ps", buffer, psOffset, (long)psLen));
      OrcProto.PostScript ps = PostScript.parseFrom(in);
      checkOrcVersion(path, ps);
      switch (ps.getCompression()) {
         case NONE:
         case ZLIB:
         case SNAPPY:
         case LZO:
         case LZ4:
         case ZSTD:
         case BROTLI:
            return ps;
         default:
            throw new IllegalArgumentException("Unknown compression");
      }
   }

   OrcTail buildEmptyTail() throws IOException {
      OrcProto.PostScript.Builder postscript = PostScript.newBuilder();
      OrcFile.Version version = OrcFile.Version.CURRENT;
      postscript.setMagic("ORC").setCompression(org.apache.orc.OrcProto.CompressionKind.NONE).setFooterLength(0L).addVersion(version.getMajor()).addVersion(version.getMinor()).setMetadataLength(0L).setWriterVersion(OrcFile.CURRENT_WRITER.getId());
      OrcProto.Type.Builder struct = Type.newBuilder();
      struct.setKind(org.apache.orc.OrcProto.Type.Kind.STRUCT);
      OrcProto.Footer.Builder footer = Footer.newBuilder();
      footer.setHeaderLength(0L).setContentLength(0L).addTypes(struct).setNumberOfRows(0L).setRowIndexStride(0);
      OrcProto.FileTail.Builder result = FileTail.newBuilder();
      result.setFooter(footer);
      result.setPostscript(postscript);
      result.setFileLength(0L);
      result.setPostscriptLength(0L);
      return new OrcTail(result.build(), new BufferChunk(0L, 0), -1L, this);
   }

   private static void read(FSDataInputStream file, BufferChunk chunks) throws IOException {
      for(; chunks != null; chunks = (BufferChunk)chunks.next) {
         if (!chunks.hasData()) {
            int len = chunks.getLength();
            ByteBuffer bb = ByteBuffer.allocate(len);
            file.readFully(chunks.getOffset(), bb.array(), bb.arrayOffset(), len);
            chunks.setChunk(bb);
         }
      }

   }

   /** @deprecated */
   public static OrcTail extractFileTail(ByteBuffer buffer) throws IOException {
      return extractFileTail(buffer, -1L, -1L);
   }

   public static int getCompressionBlockSize(OrcProto.PostScript postScript) {
      return postScript.hasCompressionBlockSize() ? (int)postScript.getCompressionBlockSize() : 262144;
   }

   /** @deprecated */
   public static OrcTail extractFileTail(ByteBuffer buffer, long fileLen, long modificationTime) throws IOException {
      long readSize = (long)buffer.limit();
      OrcProto.FileTail.Builder fileTailBuilder = FileTail.newBuilder();
      fileTailBuilder.setFileLength(fileLen != -1L ? fileLen : readSize);
      int psLen = buffer.get((int)(readSize - 1L)) & 255;
      int psOffset = (int)(readSize - 1L - (long)psLen);
      ensureOrcFooter(buffer, psLen);
      byte[] psBuffer = new byte[psLen];
      System.arraycopy(buffer.array(), psOffset, psBuffer, 0, psLen);
      OrcProto.PostScript ps = PostScript.parseFrom(psBuffer);
      int footerSize = (int)ps.getFooterLength();
      CompressionKind compressionKind = CompressionKind.valueOf(ps.getCompression().name());
      fileTailBuilder.setPostscriptLength((long)psLen).setPostscript(ps);
      InStream.StreamOptions compression = new InStream.StreamOptions();
      CompressionCodec codec = OrcCodecPool.getCodec(compressionKind);

      try {
         if (codec != null) {
            compression.withCodec(codec).withBufferSize(getCompressionBlockSize(ps));
         }

         OrcProto.Footer footer = Footer.parseFrom(InStream.createCodedInputStream(InStream.create("footer", new BufferChunk(buffer, 0L), (long)(psOffset - footerSize), (long)footerSize, compression)));
         fileTailBuilder.setPostscriptLength((long)psLen).setFooter(footer);
      } catch (Throwable var19) {
         if (codec != null) {
            try {
               codec.close();
            } catch (Throwable var18) {
               var19.addSuppressed(var18);
            }
         }

         throw var19;
      }

      if (codec != null) {
         codec.close();
      }

      buffer.clear();
      return new OrcTail(fileTailBuilder.build(), new BufferChunk(buffer.slice(), 0L), modificationTime);
   }

   protected OrcTail extractFileTail(FileSystem fs, Path path, long maxFileLength) throws IOException {
      OrcProto.FileTail.Builder fileTailBuilder = FileTail.newBuilder();
      this.file = fs.open(path);

      BufferChunk buffer;
      long modificationTime;
      try {
         long size;
         if (maxFileLength == Long.MAX_VALUE) {
            FileStatus fileStatus = fs.getFileStatus(path);
            size = fileStatus.getLen();
            modificationTime = fileStatus.getModificationTime();
         } else {
            size = maxFileLength;
            modificationTime = -1L;
         }

         if (size == 0L) {
            return this.buildEmptyTail();
         }

         if (size <= (long)"ORC".length()) {
            String var10002 = String.valueOf(path);
            throw new FileFormatException("Not a valid ORC file " + var10002 + " (maxFileLength= " + maxFileLength + ")");
         }

         fileTailBuilder.setFileLength(size);
         int readSize = (int)Math.min(size, 16384L);
         buffer = new BufferChunk(size - (long)readSize, readSize);
         read(this.file, buffer);
         ByteBuffer bb = buffer.getData();
         int psLen = bb.get(readSize - 1) & 255;
         ensureOrcFooter(this.file, path, psLen, bb);
         long psOffset = size - 1L - (long)psLen;
         OrcProto.PostScript ps = extractPostScript(buffer, path, psLen, psOffset);
         CompressionKind compressionKind = CompressionKind.valueOf(ps.getCompression().name());
         fileTailBuilder.setPostscriptLength((long)psLen).setPostscript(ps);
         int footerSize = (int)ps.getFooterLength();
         int metadataSize = (int)ps.getMetadataLength();
         int stripeStatSize = (int)ps.getStripeStatisticsLength();
         int tailSize = 1 + psLen + footerSize + metadataSize + stripeStatSize;
         int extra = Math.max(0, tailSize - readSize);
         if (extra > 0) {
            BufferChunk orig = buffer;
            buffer = new BufferChunk(size - (long)tailSize, extra);
            buffer.next = orig;
            orig.prev = buffer;
            read(this.file, buffer);
         }

         InStream.StreamOptions compression = new InStream.StreamOptions();
         CompressionCodec codec = OrcCodecPool.getCodec(compressionKind);

         try {
            if (codec != null) {
               compression.withCodec(codec).withBufferSize(getCompressionBlockSize(ps));
            }

            OrcProto.Footer footer = Footer.parseFrom(InStream.createCodedInputStream(InStream.create("footer", buffer, psOffset - (long)footerSize, (long)footerSize, compression)));
            fileTailBuilder.setFooter(footer);
         } catch (Throwable var29) {
            if (codec != null) {
               try {
                  codec.close();
               } catch (Throwable var28) {
                  var29.addSuppressed(var28);
               }
            }

            throw var29;
         }

         if (codec != null) {
            codec.close();
         }
      } catch (Throwable thr) {
         try {
            this.close();
         } catch (IOException except) {
            LOG.info("Ignoring secondary exception in close of " + String.valueOf(path), except);
         }

         throw thr instanceof IOException ? (IOException)thr : new IOException("Problem reading file footer " + String.valueOf(path), thr);
      }

      return new OrcTail(fileTailBuilder.build(), buffer, modificationTime, this);
   }

   public ByteBuffer getSerializedFileFooter() {
      return this.tail.getSerializedTail();
   }

   public boolean writerUsedProlepticGregorian() {
      OrcProto.Footer footer = this.tail.getFooter();
      return footer.hasCalendar() ? footer.getCalendar() == CalendarKind.PROLEPTIC_GREGORIAN : OrcConf.PROLEPTIC_GREGORIAN_DEFAULT.getBoolean(this.conf);
   }

   public boolean getConvertToProlepticGregorian() {
      return this.options.getConvertToProlepticGregorian();
   }

   public Reader.Options options() {
      return new Reader.Options(this.conf);
   }

   public RecordReader rows() throws IOException {
      return this.rows(this.options());
   }

   public RecordReader rows(Reader.Options options) throws IOException {
      Logger var10000 = LOG;
      String var10001 = String.valueOf(this.path);
      var10000.debug("Reading ORC rows from " + var10001 + " with " + String.valueOf(options));
      return new RecordReaderImpl(this, options);
   }

   public long getRawDataSize() {
      if (this.deserializedSize == -1L) {
         List<Integer> indices = new ArrayList();

         for(int i = 0; i < this.fileStats.size(); ++i) {
            indices.add(i);
         }

         this.deserializedSize = this.getRawDataSizeFromColIndices(indices);
      }

      return this.deserializedSize;
   }

   public long getRawDataSizeFromColIndices(List colIndices) {
      boolean[] include = new boolean[this.schema.getMaximumId() + 1];

      for(Integer rootId : colIndices) {
         TypeDescription root = this.schema.findSubtype(rootId);

         for(int c = root.getId(); c <= root.getMaximumId(); ++c) {
            include[c] = true;
         }
      }

      return getRawDataSizeFromColIndices(include, this.schema, this.fileStats);
   }

   public static long getRawDataSizeFromColIndices(List colIndices, List types, List stats) throws FileFormatException {
      TypeDescription schema = OrcUtils.convertTypeFromProtobuf(types, 0);
      boolean[] include = new boolean[schema.getMaximumId() + 1];

      for(Integer rootId : colIndices) {
         TypeDescription root = schema.findSubtype(rootId);

         for(int c = root.getId(); c <= root.getMaximumId(); ++c) {
            include[c] = true;
         }
      }

      return getRawDataSizeFromColIndices(include, schema, stats);
   }

   static long getRawDataSizeFromColIndices(boolean[] include, TypeDescription schema, List stats) {
      long result = 0L;

      for(int c = schema.getId(); c <= schema.getMaximumId(); ++c) {
         if (include[c]) {
            result += getRawDataSizeOfColumn(schema.findSubtype(c), stats);
         }
      }

      return result;
   }

   private static long getRawDataSizeOfColumn(TypeDescription column, List stats) {
      OrcProto.ColumnStatistics colStat = (OrcProto.ColumnStatistics)stats.get(column.getId());
      long numVals = colStat.getNumberOfValues();
      switch (column.getCategory()) {
         case BINARY:
            return colStat.getBinaryStatistics().getSum();
         case STRING:
         case CHAR:
         case VARCHAR:
            numVals = numVals == 0L ? 1L : numVals;
            int avgStrLen = (int)(colStat.getStringStatistics().getSum() / numVals);
            return numVals * (long)JavaDataModel.get().lengthForStringOfLength(avgStrLen);
         case TIMESTAMP:
         case TIMESTAMP_INSTANT:
            return numVals * (long)JavaDataModel.get().lengthOfTimestamp();
         case DATE:
            return numVals * (long)JavaDataModel.get().lengthOfDate();
         case DECIMAL:
            return numVals * (long)JavaDataModel.get().lengthOfDecimal();
         case DOUBLE:
         case LONG:
            return numVals * (long)JavaDataModel.get().primitive2();
         case FLOAT:
         case INT:
         case SHORT:
         case BOOLEAN:
         case BYTE:
         case STRUCT:
         case UNION:
         case MAP:
         case LIST:
            return numVals * (long)JavaDataModel.get().primitive1();
         default:
            LOG.debug("Unknown primitive category: {}", column.getCategory());
            return 0L;
      }
   }

   public long getRawDataSizeOfColumns(List colNames) {
      boolean[] include = new boolean[this.schema.getMaximumId() + 1];

      for(String name : colNames) {
         TypeDescription sub = this.schema.findSubtype(name);

         for(int c = sub.getId(); c <= sub.getMaximumId(); ++c) {
            include[c] = true;
         }
      }

      return getRawDataSizeFromColIndices(include, this.schema, this.fileStats);
   }

   public List getOrcProtoStripeStatistics() {
      if (this.stripeStatistics == null) {
         try {
            CompressionCodec codec = OrcCodecPool.getCodec(this.compressionKind);

            try {
               InStream.StreamOptions options = new InStream.StreamOptions();
               if (codec != null) {
                  options.withCodec(codec).withBufferSize(this.bufferSize);
               }

               this.stripeStatistics = deserializeStripeStats(this.tail.getTailBuffer(), this.tail.getMetadataOffset(), this.tail.getMetadataSize(), options);
            } catch (Throwable var5) {
               if (codec != null) {
                  try {
                     codec.close();
                  } catch (Throwable var4) {
                     var5.addSuppressed(var4);
                  }
               }

               throw var5;
            }

            if (codec != null) {
               codec.close();
            }
         } catch (IOException ioe) {
            throw new RuntimeException("Can't deserialize stripe stats", ioe);
         }
      }

      return this.stripeStatistics;
   }

   public List getOrcProtoFileStatistics() {
      return this.fileStats;
   }

   private static List deserializeStripeStats(BufferChunk tailBuffer, long offset, int length, InStream.StreamOptions options) throws IOException {
      try {
         InStream stream = InStream.create("stripe stats", tailBuffer, offset, (long)length, options);

         List var7;
         try {
            OrcProto.Metadata meta = Metadata.parseFrom(InStream.createCodedInputStream(stream));
            var7 = meta.getStripeStatsList();
         } catch (Throwable var9) {
            if (stream != null) {
               try {
                  stream.close();
               } catch (Throwable var8) {
                  var9.addSuppressed(var8);
               }
            }

            throw var9;
         }

         if (stream != null) {
            stream.close();
         }

         return var7;
      } catch (InvalidProtocolBufferException e) {
         LOG.warn("Failed to parse stripe statistics", e);
         return Collections.emptyList();
      }
   }

   private List convertFromProto(List list) {
      if (list == null) {
         return null;
      } else {
         List<StripeStatistics> result = new ArrayList(list.size());

         for(OrcProto.StripeStatistics ss : this.stripeStatistics) {
            result.add(new StripeStatisticsImpl(this.schema, new ArrayList(ss.getColStatsList()), this.writerUsedProlepticGregorian(), this.getConvertToProlepticGregorian()));
         }

         return result;
      }
   }

   public List getStripeStatistics() throws IOException {
      return this.getStripeStatistics((boolean[])null);
   }

   public List getStripeStatistics(boolean[] included) throws IOException {
      List<StripeStatistics> result = this.convertFromProto(this.stripeStatistics);
      if (result == null || this.encryption.getVariants().length > 0) {
         CompressionCodec codec = OrcCodecPool.getCodec(this.compressionKind);

         try {
            InStream.StreamOptions options = new InStream.StreamOptions();
            if (codec != null) {
               options.withCodec(codec).withBufferSize(this.bufferSize);
            }

            result = this.getVariantStripeStatistics((EncryptionVariant)null);
            if (this.encryption.getVariants().length > 0) {
               for(int c = this.schema.getId(); c <= this.schema.getMaximumId(); ++c) {
                  if (included == null || included[c]) {
                     ReaderEncryptionVariant variant = this.encryption.getVariant(c);
                     if (variant != null) {
                        TypeDescription variantType = variant.getRoot();
                        List<StripeStatistics> colStats = variant.getStripeStatistics(included, options, this);

                        for(int sub = c; sub <= variantType.getMaximumId(); ++sub) {
                           if (included == null || included[sub]) {
                              for(int s = 0; s < colStats.size(); ++s) {
                                 StripeStatisticsImpl resultElem = (StripeStatisticsImpl)result.get(s);
                                 resultElem.updateColumn(sub, ((StripeStatistics)colStats.get(s)).getColumn(sub - variantType.getId()));
                              }
                           }
                        }

                        c = variantType.getMaximumId();
                     }
                  }
               }
            }
         } catch (Throwable var13) {
            if (codec != null) {
               try {
                  codec.close();
               } catch (Throwable var12) {
                  var13.addSuppressed(var12);
               }
            }

            throw var13;
         }

         if (codec != null) {
            codec.close();
         }
      }

      return result;
   }

   public List getVersionList() {
      return this.versionList;
   }

   public int getMetadataSize() {
      return this.metadataSize;
   }

   public String toString() {
      StringBuilder buffer = new StringBuilder();
      buffer.append("ORC Reader(");
      buffer.append(this.path);
      if (this.maxLength != -1L) {
         buffer.append(", ");
         buffer.append(this.maxLength);
      }

      buffer.append(")");
      return buffer.toString();
   }

   public void close() throws IOException {
      if (this.file != null) {
         this.file.close();
      }

   }

   public FSDataInputStream takeFile() {
      FSDataInputStream result = this.file;
      this.file = null;
      return result;
   }

   public static class StripeInformationImpl implements StripeInformation {
      private final long stripeId;
      private final long originalStripeId;
      private final byte[][] encryptedKeys;
      private final OrcProto.StripeInformation stripe;

      public StripeInformationImpl(OrcProto.StripeInformation stripe, long stripeId, long previousOriginalStripeId, byte[][] previousKeys) {
         this.stripe = stripe;
         this.stripeId = stripeId;
         if (stripe.hasEncryptStripeId()) {
            this.originalStripeId = stripe.getEncryptStripeId();
         } else {
            this.originalStripeId = previousOriginalStripeId + 1L;
         }

         if (stripe.getEncryptedLocalKeysCount() != 0) {
            this.encryptedKeys = new byte[stripe.getEncryptedLocalKeysCount()][];

            for(int v = 0; v < this.encryptedKeys.length; ++v) {
               this.encryptedKeys[v] = stripe.getEncryptedLocalKeys(v).toByteArray();
            }
         } else {
            this.encryptedKeys = previousKeys;
         }

      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            StripeInformationImpl that = (StripeInformationImpl)o;
            return this.stripeId == that.stripeId && this.originalStripeId == that.originalStripeId && Arrays.deepEquals(this.encryptedKeys, that.encryptedKeys) && this.stripe.equals(that.stripe);
         } else {
            return false;
         }
      }

      public int hashCode() {
         int result = Objects.hash(new Object[]{this.stripeId, this.originalStripeId, this.stripe});
         result = 31 * result + Arrays.hashCode(this.encryptedKeys);
         return result;
      }

      public long getOffset() {
         return this.stripe.getOffset();
      }

      public long getLength() {
         return this.stripe.getDataLength() + this.getIndexLength() + this.getFooterLength();
      }

      public long getDataLength() {
         return this.stripe.getDataLength();
      }

      public long getFooterLength() {
         return this.stripe.getFooterLength();
      }

      public long getIndexLength() {
         return this.stripe.getIndexLength();
      }

      public long getNumberOfRows() {
         return this.stripe.getNumberOfRows();
      }

      public long getStripeId() {
         return this.stripeId;
      }

      public boolean hasEncryptionStripeId() {
         return this.stripe.hasEncryptStripeId();
      }

      public long getEncryptionStripeId() {
         return this.originalStripeId;
      }

      public byte[][] getEncryptedLocalKeys() {
         return this.encryptedKeys;
      }

      public String toString() {
         long var10000 = this.getOffset();
         return "offset: " + var10000 + " data: " + this.getDataLength() + " rows: " + this.getNumberOfRows() + " tail: " + this.getFooterLength() + " index: " + this.getIndexLength() + (this.hasEncryptionStripeId() && this.stripeId != this.originalStripeId - 1L ? " encryption id: " + this.originalStripeId : "");
      }
   }
}
