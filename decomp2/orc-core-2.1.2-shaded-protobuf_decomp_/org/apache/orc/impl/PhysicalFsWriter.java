package org.apache.orc.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.orc.CompressionCodec;
import org.apache.orc.EncryptionVariant;
import org.apache.orc.OrcFile;
import org.apache.orc.OrcProto;
import org.apache.orc.PhysicalWriter;
import org.apache.orc.TypeDescription;
import org.apache.orc.OrcProto.ColumnarStripeStatistics;
import org.apache.orc.OrcProto.FileStatistics;
import org.apache.orc.OrcProto.Stream;
import org.apache.orc.OrcProto.StripeStatistics;
import org.apache.orc.OrcProto.Stream.Kind;
import org.apache.orc.impl.writer.StreamOptions;
import org.apache.orc.impl.writer.WriterEncryptionKey;
import org.apache.orc.impl.writer.WriterEncryptionVariant;
import org.apache.orc.protobuf.ByteString;
import org.apache.orc.protobuf.CodedOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PhysicalFsWriter implements PhysicalWriter {
   private static final Logger LOG = LoggerFactory.getLogger(PhysicalFsWriter.class);
   private static final int HDFS_BUFFER_SIZE = 262144;
   private FSDataOutputStream rawWriter;
   private final DirectStream rawStream;
   private OutStream compressStream;
   private CodedOutputStream codedCompressStream;
   private Path path;
   private final HadoopShims shims;
   private final long blockSize;
   private final int maxPadding;
   private final StreamOptions compress;
   private final OrcFile.CompressionStrategy compressionStrategy;
   private final boolean addBlockPadding;
   private final boolean writeVariableLengthBlocks;
   private final VariantTracker unencrypted;
   private long headerLength;
   private long stripeStart;
   private long blockOffset;
   private int metadataLength;
   private int stripeStatisticsLength;
   private int footerLength;
   private int stripeNumber;
   private final Map variants;
   private static final byte[] ZEROS = new byte[65536];

   public PhysicalFsWriter(FileSystem fs, Path path, OrcFile.WriterOptions opts) throws IOException {
      this(fs, path, opts, new WriterEncryptionVariant[0]);
   }

   public PhysicalFsWriter(FileSystem fs, Path path, OrcFile.WriterOptions opts, WriterEncryptionVariant[] encryption) throws IOException {
      this(fs.create(path, opts.getOverwrite(), 262144, fs.getDefaultReplication(path), opts.getBlockSize()), opts, encryption);
      this.path = path;
      LOG.debug("ORC writer created for path: {} with stripeSize: {} blockSize: {} compression: {}", new Object[]{path, opts.getStripeSize(), this.blockSize, this.compress});
   }

   public PhysicalFsWriter(FSDataOutputStream outputStream, OrcFile.WriterOptions opts, WriterEncryptionVariant[] encryption) throws IOException {
      this.stripeStatisticsLength = 0;
      this.stripeNumber = 0;
      this.variants = new TreeMap();
      this.rawWriter = outputStream;
      long defaultStripeSize = opts.getStripeSize();
      this.addBlockPadding = opts.getBlockPadding();
      if (opts.isEnforceBufferSize()) {
         this.compress = new StreamOptions(opts.getBufferSize());
      } else {
         this.compress = new StreamOptions(WriterImpl.getEstimatedBufferSize(defaultStripeSize, opts.getSchema().getMaximumId() + 1, opts.getBufferSize()));
      }

      CompressionCodec codec = OrcCodecPool.getCodec(opts.getCompress());
      if (codec != null) {
         CompressionCodec.Options tempOptions = codec.getDefaultOptions();
         if (codec instanceof ZstdCodec) {
            CompressionCodec.Options var9 = codec.getDefaultOptions();
            if (var9 instanceof ZstdCodec.ZstdOptions) {
               ZstdCodec.ZstdOptions options = (ZstdCodec.ZstdOptions)var9;
               OrcFile.ZstdCompressOptions zstdCompressOptions = opts.getZstdCompressOptions();
               if (zstdCompressOptions != null) {
                  options.setLevel(zstdCompressOptions.getCompressionZstdLevel());
                  options.setWindowLog(zstdCompressOptions.getCompressionZstdWindowLog());
               }
            }
         }

         this.compress.withCodec(codec, tempOptions);
      }

      this.compressionStrategy = opts.getCompressionStrategy();
      this.maxPadding = (int)(opts.getPaddingTolerance() * (double)defaultStripeSize);
      this.blockSize = opts.getBlockSize();
      this.blockOffset = 0L;
      this.unencrypted = new VariantTracker(opts.getSchema(), this.compress);
      this.writeVariableLengthBlocks = opts.getWriteVariableLengthBlocks();
      this.shims = opts.getHadoopShims();
      this.rawStream = new DirectStream(this.rawWriter);
      this.compressStream = new OutStream("stripe footer", this.compress, this.rawStream);
      this.codedCompressStream = CodedOutputStream.newInstance((OutputStream)this.compressStream);

      for(WriterEncryptionVariant variant : encryption) {
         WriterEncryptionKey key = variant.getKeyDescription();
         StreamOptions encryptOptions = (new StreamOptions(this.unencrypted.options)).withEncryption(key.getAlgorithm(), variant.getFileFooterKey());
         this.variants.put(variant, new VariantTracker(variant.getRoot(), encryptOptions));
      }

   }

   VariantTracker getVariant(EncryptionVariant column) {
      return column == null ? this.unencrypted : (VariantTracker)this.variants.get(column);
   }

   public long getFileBytes(int column, WriterEncryptionVariant variant) {
      return this.getVariant(variant).getFileBytes(column);
   }

   public StreamOptions getStreamOptions() {
      return this.unencrypted.options;
   }

   private static void writeZeros(OutputStream output, long remaining) throws IOException {
      while(remaining > 0L) {
         long size = Math.min((long)ZEROS.length, remaining);
         output.write(ZEROS, 0, (int)size);
         remaining -= size;
      }

   }

   private void padStripe(long stripeSize) throws IOException {
      this.stripeStart = this.rawWriter.getPos();
      long previousBytesInBlock = (this.stripeStart - this.blockOffset) % this.blockSize;
      if (previousBytesInBlock > 0L && previousBytesInBlock + stripeSize >= this.blockSize) {
         if (this.writeVariableLengthBlocks && this.shims.endVariableLengthBlock(this.rawWriter)) {
            this.blockOffset = this.stripeStart;
         } else if (this.addBlockPadding) {
            long padding = this.blockSize - previousBytesInBlock;
            if (padding <= (long)this.maxPadding) {
               writeZeros(this.rawWriter, padding);
               this.stripeStart += padding;
            }
         }
      }

   }

   private void writeStripeFooter(OrcProto.StripeFooter footer, SizeCounters sizes, OrcProto.StripeInformation.Builder dirEntry) throws IOException {
      footer.writeTo(this.codedCompressStream);
      this.codedCompressStream.flush();
      this.compressStream.flush();
      dirEntry.setOffset(this.stripeStart);
      dirEntry.setFooterLength(this.rawWriter.getPos() - this.stripeStart - sizes.total());
   }

   static void writeEncryptedStripeStatistics(DirectStream output, int stripeNumber, VariantTracker tracker) throws IOException {
      StreamOptions options = new StreamOptions(tracker.options);
      tracker.stripeStatsStreams.clear();

      for(int col = tracker.rootColumn; col < tracker.rootColumn + tracker.stripeStats.length; ++col) {
         options.modifyIv(CryptoUtils.modifyIvForStream(col, Kind.STRIPE_STATISTICS, (long)(stripeNumber + 1)));
         OutStream stream = new OutStream("stripe stats for " + col, options, output);
         OrcProto.ColumnarStripeStatistics stats = ColumnarStripeStatistics.newBuilder().addAllColStats(tracker.stripeStats[col - tracker.rootColumn]).build();
         long start = output.output.getPos();
         stats.writeTo(stream);
         stream.flush();
         OrcProto.Stream description = Stream.newBuilder().setColumn(col).setKind(Kind.STRIPE_STATISTICS).setLength(output.output.getPos() - start).build();
         tracker.stripeStatsStreams.add(description);
      }

   }

   static void setUnencryptedStripeStatistics(OrcProto.Metadata.Builder builder, int stripeCount, List[] stats) {
      builder.clearStripeStats();

      for(int s = 0; s < stripeCount; ++s) {
         OrcProto.StripeStatistics.Builder stripeStats = StripeStatistics.newBuilder();

         for(List col : stats) {
            stripeStats.addColStats((OrcProto.ColumnStatistics)col.get(s));
         }

         builder.addStripeStats(stripeStats.build());
      }

   }

   static void setEncryptionStatistics(OrcProto.Encryption.Builder encryption, int stripeNumber, Collection variants) throws IOException {
      int v = 0;

      for(VariantTracker variant : variants) {
         OrcProto.EncryptionVariant.Builder variantBuilder = encryption.getVariantsBuilder(v++);
         variantBuilder.clearStripeStatistics();
         variantBuilder.addAllStripeStatistics(variant.stripeStatsStreams);
         OrcProto.FileStatistics.Builder file = FileStatistics.newBuilder();

         for(OrcProto.ColumnStatistics col : variant.fileStats) {
            file.addColumn(col);
         }

         StreamOptions options = new StreamOptions(variant.options);
         options.modifyIv(CryptoUtils.modifyIvForStream(variant.rootColumn, Kind.FILE_STATISTICS, (long)(stripeNumber + 1)));
         BufferedStream buffer = new BufferedStream();
         OutStream stream = new OutStream("stats for " + String.valueOf(variant), options, buffer);
         file.build().writeTo(stream);
         stream.flush();
         variantBuilder.setFileStatistics(buffer.getBytes());
      }

   }

   public void writeFileMetadata(OrcProto.Metadata.Builder builder) throws IOException {
      long stripeStatisticsStart = this.rawWriter.getPos();

      for(VariantTracker variant : this.variants.values()) {
         writeEncryptedStripeStatistics(this.rawStream, this.stripeNumber, variant);
      }

      setUnencryptedStripeStatistics(builder, this.stripeNumber, this.unencrypted.stripeStats);
      long metadataStart = this.rawWriter.getPos();
      builder.build().writeTo(this.codedCompressStream);
      this.codedCompressStream.flush();
      this.compressStream.flush();
      this.stripeStatisticsLength = (int)(metadataStart - stripeStatisticsStart);
      this.metadataLength = (int)(this.rawWriter.getPos() - metadataStart);
   }

   static void addUnencryptedStatistics(OrcProto.Footer.Builder builder, OrcProto.ColumnStatistics[] stats) {
      for(OrcProto.ColumnStatistics stat : stats) {
         builder.addStatistics(stat);
      }

   }

   public void writeFileFooter(OrcProto.Footer.Builder builder) throws IOException {
      if (this.variants.size() > 0) {
         OrcProto.Encryption.Builder encryption = builder.getEncryptionBuilder();
         setEncryptionStatistics(encryption, this.stripeNumber, this.variants.values());
      }

      addUnencryptedStatistics(builder, this.unencrypted.fileStats);
      long bodyLength = this.rawWriter.getPos() - (long)this.metadataLength - (long)this.stripeStatisticsLength;
      builder.setContentLength(bodyLength);
      builder.setHeaderLength(this.headerLength);
      long startPosn = this.rawWriter.getPos();
      OrcProto.Footer footer = builder.build();
      footer.writeTo(this.codedCompressStream);
      this.codedCompressStream.flush();
      this.compressStream.flush();
      this.footerLength = (int)(this.rawWriter.getPos() - startPosn);
   }

   public long writePostScript(OrcProto.PostScript.Builder builder) throws IOException {
      builder.setFooterLength((long)this.footerLength);
      builder.setMetadataLength((long)this.metadataLength);
      if (this.variants.size() > 0) {
         builder.setStripeStatisticsLength((long)this.stripeStatisticsLength);
      }

      OrcProto.PostScript ps = builder.build();
      long startPosn = this.rawWriter.getPos();
      ps.writeTo(this.rawWriter);
      long length = this.rawWriter.getPos() - startPosn;
      if (length > 255L) {
         throw new IllegalArgumentException("PostScript too large at " + length);
      } else {
         this.rawWriter.writeByte((int)length);
         return this.rawWriter.getPos();
      }
   }

   public void close() throws IOException {
      CompressionCodec codec = this.compress.getCodec();
      if (codec != null) {
         OrcCodecPool.returnCodec(codec.getKind(), codec);
      }

      this.compress.withCodec((CompressionCodec)null, (CompressionCodec.Options)null);
      this.rawWriter.close();
      this.rawWriter = null;
   }

   public void flush() throws IOException {
      this.rawWriter.hflush();
   }

   public void appendRawStripe(ByteBuffer buffer, OrcProto.StripeInformation.Builder dirEntry) throws IOException {
      long start = this.rawWriter.getPos();
      int length = buffer.remaining();
      long availBlockSpace = this.blockSize - start % this.blockSize;
      if ((long)length < this.blockSize && (long)length > availBlockSpace && this.addBlockPadding) {
         byte[] pad = new byte[(int)Math.min(262144L, availBlockSpace)];
         LOG.debug("Padding ORC by {} bytes while merging", availBlockSpace);

         int writeLen;
         for(start += availBlockSpace; availBlockSpace > 0L; availBlockSpace -= (long)writeLen) {
            writeLen = (int)Math.min(availBlockSpace, (long)pad.length);
            this.rawWriter.write(pad, 0, writeLen);
         }
      }

      this.rawWriter.write(buffer.array(), buffer.arrayOffset() + buffer.position(), length);
      dirEntry.setOffset(start);
      ++this.stripeNumber;
   }

   void buildStreamList(OrcProto.StripeFooter.Builder footerBuilder, SizeCounters sizes) throws IOException {
      footerBuilder.addAllStreams(this.unencrypted.placeStreams(StreamName.Area.INDEX, sizes));
      long unencryptedIndexSize = sizes.index;
      int v = 0;

      for(VariantTracker variant : this.variants.values()) {
         OrcProto.StripeEncryptionVariant.Builder builder = footerBuilder.getEncryptionBuilder(v++);
         builder.addAllStreams(variant.placeStreams(StreamName.Area.INDEX, sizes));
      }

      if (sizes.index != unencryptedIndexSize) {
         footerBuilder.addStreams(Stream.newBuilder().setKind(Kind.ENCRYPTED_INDEX).setLength(sizes.index - unencryptedIndexSize));
      }

      footerBuilder.addAllStreams(this.unencrypted.placeStreams(StreamName.Area.DATA, sizes));
      long unencryptedDataSize = sizes.data;
      v = 0;

      for(VariantTracker variant : this.variants.values()) {
         OrcProto.StripeEncryptionVariant.Builder builder = footerBuilder.getEncryptionBuilder(v++);
         builder.addAllStreams(variant.placeStreams(StreamName.Area.DATA, sizes));
      }

      if (sizes.data != unencryptedDataSize) {
         footerBuilder.addStreams(Stream.newBuilder().setKind(Kind.ENCRYPTED_DATA).setLength(sizes.data - unencryptedDataSize));
      }

   }

   public void finalizeStripe(OrcProto.StripeFooter.Builder footerBuilder, OrcProto.StripeInformation.Builder dirEntry) throws IOException {
      SizeCounters sizes = new SizeCounters();
      this.buildStreamList(footerBuilder, sizes);
      OrcProto.StripeFooter footer = footerBuilder.build();
      this.padStripe(sizes.total() + (long)footer.getSerializedSize());
      this.unencrypted.writeStreams(StreamName.Area.INDEX, this.rawWriter);

      for(VariantTracker variant : this.variants.values()) {
         variant.writeStreams(StreamName.Area.INDEX, this.rawWriter);
      }

      this.unencrypted.writeStreams(StreamName.Area.DATA, this.rawWriter);

      for(VariantTracker variant : this.variants.values()) {
         variant.writeStreams(StreamName.Area.DATA, this.rawWriter);
      }

      this.writeStripeFooter(footer, sizes, dirEntry);
      dirEntry.setDataLength(sizes.data);
      dirEntry.setIndexLength(sizes.index);
      ++this.stripeNumber;
   }

   public void writeHeader() throws IOException {
      this.rawWriter.writeBytes("ORC");
      this.headerLength = this.rawWriter.getPos();
   }

   public BufferedStream createDataStream(StreamName name) {
      VariantTracker variant = this.getVariant(name.getEncryption());
      BufferedStream result = (BufferedStream)variant.streams.get(name);
      if (result == null) {
         result = new BufferedStream();
         variant.streams.put(name, result);
      }

      return result;
   }

   private StreamOptions getOptions(OrcProto.Stream.Kind kind) {
      return SerializationUtils.getCustomizedCodec(this.compress, this.compressionStrategy, kind);
   }

   protected OutputStream createIndexStream(StreamName name) {
      BufferedStream buffer = this.createDataStream(name);
      VariantTracker tracker = this.getVariant(name.getEncryption());
      StreamOptions options = SerializationUtils.getCustomizedCodec(tracker.options, this.compressionStrategy, name.getKind());
      if (options.isEncrypted()) {
         if (options == tracker.options) {
            options = new StreamOptions(options);
         }

         options.modifyIv(CryptoUtils.modifyIvForStream(name, (long)(this.stripeNumber + 1)));
      }

      return new OutStream(name.toString(), options, buffer);
   }

   public void writeIndex(StreamName name, OrcProto.RowIndex.Builder index) throws IOException {
      OutputStream stream = this.createIndexStream(name);
      index.build().writeTo(stream);
      stream.flush();
   }

   public void writeBloomFilter(StreamName name, OrcProto.BloomFilterIndex.Builder bloom) throws IOException {
      OutputStream stream = this.createIndexStream(name);
      bloom.build().writeTo(stream);
      stream.flush();
   }

   public void writeStatistics(StreamName name, OrcProto.ColumnStatistics.Builder statistics) {
      VariantTracker tracker = this.getVariant(name.getEncryption());
      if (name.getKind() == Kind.FILE_STATISTICS) {
         tracker.fileStats[name.getColumn() - tracker.rootColumn] = statistics.build();
      } else {
         tracker.stripeStats[name.getColumn() - tracker.rootColumn].add(statistics.build());
      }

   }

   public String toString() {
      return this.path != null ? this.path.toString() : ByteString.EMPTY.toString();
   }

   protected static class VariantTracker {
      protected final Map streams = new TreeMap();
      private final int rootColumn;
      private final int lastColumn;
      protected final StreamOptions options;
      protected final List[] stripeStats;
      protected final List stripeStatsStreams = new ArrayList();
      protected final OrcProto.ColumnStatistics[] fileStats;

      VariantTracker(TypeDescription schema, StreamOptions options) {
         this.rootColumn = schema.getId();
         this.lastColumn = schema.getMaximumId();
         this.options = options;
         this.stripeStats = new List[schema.getMaximumId() - schema.getId() + 1];

         for(int i = 0; i < this.stripeStats.length; ++i) {
            this.stripeStats[i] = new ArrayList();
         }

         this.fileStats = new OrcProto.ColumnStatistics[this.stripeStats.length];
      }

      public BufferedStream createStream(StreamName name) {
         BufferedStream result = new BufferedStream();
         this.streams.put(name, result);
         return result;
      }

      public List placeStreams(StreamName.Area area, SizeCounters sizes) {
         List<OrcProto.Stream> result = new ArrayList(this.streams.size());

         for(Map.Entry stream : this.streams.entrySet()) {
            StreamName name = (StreamName)stream.getKey();
            BufferedStream bytes = (BufferedStream)stream.getValue();
            if (name.getArea() == area && !bytes.isSuppressed) {
               OrcProto.Stream.Builder builder = Stream.newBuilder();
               long size = bytes.getOutputSize();
               if (area == StreamName.Area.INDEX) {
                  sizes.index += size;
               } else {
                  sizes.data += size;
               }

               builder.setColumn(name.getColumn()).setKind(name.getKind()).setLength(size);
               result.add(builder.build());
            }
         }

         return result;
      }

      public void writeStreams(StreamName.Area area, FSDataOutputStream raw) throws IOException {
         for(Map.Entry stream : this.streams.entrySet()) {
            if (((StreamName)stream.getKey()).getArea() == area) {
               ((BufferedStream)stream.getValue()).spillToDiskAndClear(raw);
            }
         }

      }

      public long getFileBytes(int column) {
         long result = 0L;
         if (column >= this.rootColumn && column <= this.lastColumn) {
            for(Map.Entry entry : this.streams.entrySet()) {
               StreamName name = (StreamName)entry.getKey();
               if (name.getColumn() == column && name.getArea() != StreamName.Area.INDEX) {
                  result += ((BufferedStream)entry.getValue()).getOutputSize();
               }
            }
         }

         return result;
      }
   }

   private static class DirectStream implements PhysicalWriter.OutputReceiver {
      private final FSDataOutputStream output;

      DirectStream(FSDataOutputStream output) {
         this.output = output;
      }

      public void output(ByteBuffer buffer) throws IOException {
         this.output.write(buffer.array(), buffer.arrayOffset() + buffer.position(), buffer.remaining());
      }

      public void suppress() {
         throw new UnsupportedOperationException("Can't suppress direct stream");
      }
   }

   static final class BufferedStream implements PhysicalWriter.OutputReceiver {
      private boolean isSuppressed = false;
      private final List output = new ArrayList();

      public void output(ByteBuffer buffer) {
         if (!this.isSuppressed) {
            this.output.add(buffer);
         }

      }

      public void suppress() {
         this.isSuppressed = true;
         this.output.clear();
      }

      boolean spillToDiskAndClear(FSDataOutputStream raw) throws IOException {
         if (this.isSuppressed) {
            this.isSuppressed = false;
            return false;
         } else {
            for(ByteBuffer buffer : this.output) {
               raw.write(buffer.array(), buffer.arrayOffset() + buffer.position(), buffer.remaining());
            }

            this.output.clear();
            return true;
         }
      }

      ByteString getBytes() {
         int len = this.output.size();
         if (len == 0) {
            return ByteString.EMPTY;
         } else {
            ByteString result = ByteString.copyFrom((ByteBuffer)this.output.get(0));

            for(int i = 1; i < this.output.size(); ++i) {
               result = result.concat(ByteString.copyFrom((ByteBuffer)this.output.get(i)));
            }

            this.output.clear();
            return result;
         }
      }

      ByteBuffer getByteBuffer() {
         ByteBuffer result;
         if (this.output.size() == 1) {
            result = (ByteBuffer)this.output.get(0);
         } else {
            result = ByteBuffer.allocate((int)this.getOutputSize());

            for(ByteBuffer buffer : this.output) {
               result.put(buffer);
            }

            this.output.clear();
            result.flip();
         }

         return result;
      }

      public long getOutputSize() {
         long result = 0L;

         for(ByteBuffer buffer : this.output) {
            result += (long)buffer.remaining();
         }

         return result;
      }
   }

   static class SizeCounters {
      long index = 0L;
      long data = 0L;

      long total() {
         return this.index + this.data;
      }
   }
}
