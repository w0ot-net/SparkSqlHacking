package org.apache.orc.impl.reader;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.orc.DataReader;
import org.apache.orc.EncryptionAlgorithm;
import org.apache.orc.OrcFile;
import org.apache.orc.OrcProto;
import org.apache.orc.StripeInformation;
import org.apache.orc.TypeDescription;
import org.apache.orc.OrcProto.BloomFilterIndex;
import org.apache.orc.OrcProto.RowIndex;
import org.apache.orc.OrcProto.Stream.Kind;
import org.apache.orc.impl.BufferChunk;
import org.apache.orc.impl.BufferChunkList;
import org.apache.orc.impl.CryptoUtils;
import org.apache.orc.impl.InStream;
import org.apache.orc.impl.OrcIndex;
import org.apache.orc.impl.RecordReaderUtils;
import org.apache.orc.impl.StreamName;
import org.apache.orc.impl.reader.tree.TypeReader;
import org.apache.orc.protobuf.CodedInputStream;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StripePlanner {
   private static final Logger LOG = LoggerFactory.getLogger(StripePlanner.class);
   private final TypeDescription schema;
   private final OrcFile.WriterVersion version;
   private final OrcProto.ColumnEncoding[] encodings;
   private final ReaderEncryption encryption;
   private final DataReader dataReader;
   private final boolean ignoreNonUtf8BloomFilter;
   private final long maxBufferSize;
   private String writerTimezone;
   private long currentStripeId;
   private long originalStripeId;
   private final Map streams;
   private final List indexStreams;
   private final List dataStreams;
   private final OrcProto.Stream.Kind[] bloomFilterKinds;
   private final boolean[] hasNull;
   private final Set filterColIds;

   public StripePlanner(TypeDescription schema, ReaderEncryption encryption, DataReader dataReader, OrcFile.WriterVersion version, boolean ignoreNonUtf8BloomFilter, long maxBufferSize, Set filterColIds) {
      this.streams = new HashMap();
      this.indexStreams = new ArrayList();
      this.dataStreams = new ArrayList();
      this.schema = schema;
      this.version = version;
      this.encodings = new OrcProto.ColumnEncoding[schema.getMaximumId() + 1];
      this.encryption = encryption;
      this.dataReader = dataReader;
      this.ignoreNonUtf8BloomFilter = ignoreNonUtf8BloomFilter;
      this.bloomFilterKinds = new OrcProto.Stream.Kind[schema.getMaximumId() + 1];
      this.hasNull = new boolean[schema.getMaximumId() + 1];
      this.maxBufferSize = maxBufferSize;
      this.filterColIds = filterColIds;
   }

   public StripePlanner(TypeDescription schema, ReaderEncryption encryption, DataReader dataReader, OrcFile.WriterVersion version, boolean ignoreNonUtf8BloomFilter, long maxBufferSize) {
      this(schema, encryption, dataReader, version, ignoreNonUtf8BloomFilter, maxBufferSize, Collections.emptySet());
   }

   public StripePlanner(StripePlanner old) {
      this(old.schema, old.encryption, old.dataReader, old.version, old.ignoreNonUtf8BloomFilter, old.maxBufferSize, old.filterColIds);
   }

   public StripePlanner parseStripe(StripeInformation stripe, boolean[] columnInclude) throws IOException {
      OrcProto.StripeFooter footer = this.dataReader.readStripeFooter(stripe);
      this.currentStripeId = stripe.getStripeId();
      this.originalStripeId = stripe.getEncryptionStripeId();
      this.writerTimezone = footer.getWriterTimezone();
      this.streams.clear();
      this.dataStreams.clear();
      this.indexStreams.clear();
      this.buildEncodings(footer, columnInclude);
      this.findStreams(stripe.getOffset(), footer, columnInclude);
      Arrays.fill(this.hasNull, false);

      for(StreamInformation stream : this.dataStreams) {
         if (stream.kind == Kind.PRESENT) {
            this.hasNull[stream.column] = true;
         }
      }

      return this;
   }

   public BufferChunkList readData(OrcIndex index, boolean[] rowGroupInclude, boolean forceDirect, TypeReader.ReadPhase readPhase) throws IOException {
      BufferChunkList chunks = index != null && rowGroupInclude != null ? this.planPartialDataReading(index, rowGroupInclude, readPhase) : this.planDataReading(readPhase);
      this.dataReader.readFileData(chunks, forceDirect);
      return chunks;
   }

   public BufferChunkList readFollowData(OrcIndex index, boolean[] rowGroupInclude, int rgIdx, boolean forceDirect) throws IOException {
      BufferChunkList chunks = index != null && rowGroupInclude != null ? this.planPartialDataReading(index, rowGroupInclude, rgIdx, TypeReader.ReadPhase.FOLLOWERS) : this.planDataReading(TypeReader.ReadPhase.FOLLOWERS);
      this.dataReader.readFileData(chunks, forceDirect);
      return chunks;
   }

   public String getWriterTimezone() {
      return this.writerTimezone;
   }

   public InStream getStream(StreamName name) throws IOException {
      StreamInformation stream = (StreamInformation)this.streams.get(name);
      return stream == null ? null : InStream.create(name, stream.firstChunk, stream.offset, stream.length, this.getStreamOptions(stream.column, stream.kind));
   }

   public void clearStreams() {
      if (this.dataReader.isTrackingDiskRanges()) {
         this.dataReader.releaseAllBuffers();
      }

      this.indexStreams.clear();
      this.dataStreams.clear();
      this.streams.clear();
   }

   private InStream.StreamOptions getStreamOptions(int column, OrcProto.Stream.Kind kind) throws IOException {
      ReaderEncryptionVariant variant = this.encryption.getVariant(column);
      InStream.StreamOptions compression = this.dataReader.getCompressionOptions();
      if (variant == null) {
         return compression;
      } else {
         EncryptionAlgorithm algorithm = variant.getKeyDescription().getAlgorithm();
         byte[] iv = new byte[algorithm.getIvLength()];
         Key key = variant.getStripeKey(this.currentStripeId);
         CryptoUtils.modifyIvForStream(column, kind, this.originalStripeId).accept(iv);
         return (new InStream.StreamOptions(compression)).withEncryption(algorithm, key, iv);
      }
   }

   public OrcProto.ColumnEncoding getEncoding(int column) {
      return this.encodings[column];
   }

   private void buildEncodings(OrcProto.StripeFooter footer, boolean[] columnInclude) {
      for(int c = 0; c < this.encodings.length; ++c) {
         if (columnInclude == null || columnInclude[c]) {
            ReaderEncryptionVariant variant = this.encryption.getVariant(c);
            if (variant == null) {
               this.encodings[c] = footer.getColumns(c);
            } else {
               int subColumn = c - variant.getRoot().getId();
               this.encodings[c] = footer.getEncryption(variant.getVariantId()).getEncoding(subColumn);
            }
         }
      }

   }

   private long handleStream(long offset, boolean[] columnInclude, OrcProto.Stream stream, StreamName.Area area, ReaderEncryptionVariant variant) {
      int column = stream.getColumn();
      if (stream.hasKind()) {
         OrcProto.Stream.Kind kind = stream.getKind();
         if (StreamName.getArea(kind) != area || kind == Kind.ENCRYPTED_INDEX || kind == Kind.ENCRYPTED_DATA) {
            return 0L;
         }

         if (columnInclude[column] && this.encryption.getVariant(column) == variant && (kind != Kind.BLOOM_FILTER || !this.ignoreNonUtf8BloomFilter || !hadBadBloomFilters(this.schema.findSubtype(column).getCategory(), this.version))) {
            if (kind == Kind.BLOOM_FILTER_UTF8 || kind == Kind.BLOOM_FILTER) {
               this.bloomFilterKinds[column] = kind;
            }

            StreamInformation info = new StreamInformation(kind, column, offset, stream.getLength());
            switch (StreamName.getArea(kind)) {
               case DATA -> this.dataStreams.add(info);
               case INDEX -> this.indexStreams.add(info);
            }

            this.streams.put(new StreamName(column, kind), info);
         }
      }

      return stream.getLength();
   }

   private void findStreams(long streamStart, OrcProto.StripeFooter footer, boolean[] columnInclude) throws IOException {
      Arrays.fill(this.bloomFilterKinds, (Object)null);
      long currentOffset = this.findStreamsByArea(streamStart, footer, StreamName.Area.INDEX, columnInclude);
      this.findStreamsByArea(currentOffset, footer, StreamName.Area.DATA, columnInclude);
   }

   private long findStreamsByArea(long currentOffset, OrcProto.StripeFooter footer, StreamName.Area area, boolean[] columnInclude) {
      for(OrcProto.Stream stream : footer.getStreamsList()) {
         currentOffset += this.handleStream(currentOffset, columnInclude, stream, area, (ReaderEncryptionVariant)null);
      }

      for(ReaderEncryptionVariant variant : this.encryption.getVariants()) {
         int variantId = variant.getVariantId();
         OrcProto.StripeEncryptionVariant stripeVariant = footer.getEncryption(variantId);

         for(OrcProto.Stream stream : stripeVariant.getStreamsList()) {
            currentOffset += this.handleStream(currentOffset, columnInclude, stream, area, variant);
         }
      }

      return currentOffset;
   }

   public OrcIndex readRowIndex(boolean[] sargColumns, OrcIndex output) throws IOException {
      int typeCount = this.schema.getMaximumId() + 1;
      if (output == null) {
         output = new OrcIndex(new OrcProto.RowIndex[typeCount], new OrcProto.Stream.Kind[typeCount], new OrcProto.BloomFilterIndex[typeCount]);
      }

      System.arraycopy(this.bloomFilterKinds, 0, output.getBloomFilterKinds(), 0, this.bloomFilterKinds.length);
      BufferChunkList ranges = this.planIndexReading(sargColumns);
      this.dataReader.readFileData(ranges, false);
      OrcProto.RowIndex[] indexes = output.getRowGroupIndex();
      OrcProto.BloomFilterIndex[] blooms = output.getBloomFilterIndex();

      for(StreamInformation stream : this.indexStreams) {
         int column = stream.column;
         if (stream.firstChunk != null) {
            CodedInputStream data = InStream.createCodedInputStream(InStream.create("index", stream.firstChunk, stream.offset, stream.length, this.getStreamOptions(column, stream.kind)));
            switch (stream.kind) {
               case ROW_INDEX:
                  indexes[column] = RowIndex.parseFrom(data);
                  break;
               case BLOOM_FILTER:
               case BLOOM_FILTER_UTF8:
                  if (sargColumns != null && sargColumns[column]) {
                     blooms[column] = BloomFilterIndex.parseFrom(data);
                  }
            }
         }
      }

      return output;
   }

   private void addChunk(BufferChunkList list, StreamInformation stream, long offset, long length) {
      while(length > 0L) {
         long thisLen = Math.min(length, this.maxBufferSize);
         BufferChunk chunk = new BufferChunk(offset, (int)thisLen);
         if (stream.firstChunk == null) {
            stream.firstChunk = chunk;
         }

         list.add(chunk);
         offset += thisLen;
         length -= thisLen;
      }

   }

   private BufferChunkList planIndexReading(boolean[] bloomFilterColumns) {
      BufferChunkList result = new BufferChunkList();

      for(StreamInformation stream : this.indexStreams) {
         switch (stream.kind) {
            case ROW_INDEX:
               this.addChunk(result, stream, stream.offset, stream.length);
               break;
            case BLOOM_FILTER:
            case BLOOM_FILTER_UTF8:
               if (bloomFilterColumns[stream.column] && this.bloomFilterKinds[stream.column] == stream.kind) {
                  this.addChunk(result, stream, stream.offset, stream.length);
               }
         }
      }

      return result;
   }

   private BufferChunkList planDataReading(TypeReader.ReadPhase readPhase) {
      BufferChunkList result = new BufferChunkList();

      for(StreamInformation stream : this.dataStreams) {
         if (readPhase != TypeReader.ReadPhase.ALL && (readPhase != TypeReader.ReadPhase.LEADERS || !this.filterColIds.contains(stream.column)) && (readPhase != TypeReader.ReadPhase.FOLLOWERS || this.filterColIds.contains(stream.column))) {
            LOG.debug("Skipping planning for lazy column stream {}", stream);
         } else {
            this.addChunk(result, stream, stream.offset, stream.length);
         }
      }

      return result;
   }

   static boolean hadBadBloomFilters(TypeDescription.Category category, OrcFile.WriterVersion version) {
      switch (category) {
         case STRING:
         case CHAR:
         case VARCHAR:
            return !version.includes(OrcFile.WriterVersion.HIVE_12055);
         case DECIMAL:
            return true;
         case TIMESTAMP:
            return !version.includes(OrcFile.WriterVersion.ORC_135);
         default:
            return false;
      }
   }

   private static boolean hasSomeRowGroups(boolean[] includedRowGroups) {
      for(boolean include : includedRowGroups) {
         if (include) {
            return true;
         }
      }

      return false;
   }

   private BufferChunkList planPartialDataReading(OrcIndex index, @NotNull boolean[] includedRowGroups, TypeReader.ReadPhase readPhase) {
      return this.planPartialDataReading(index, includedRowGroups, 0, readPhase);
   }

   private BufferChunkList planPartialDataReading(OrcIndex index, @NotNull boolean[] includedRowGroups, int startGroup, TypeReader.ReadPhase readPhase) {
      BufferChunkList result = new BufferChunkList();
      if (hasSomeRowGroups(includedRowGroups)) {
         InStream.StreamOptions compression = this.dataReader.getCompressionOptions();
         boolean isCompressed = compression.getCodec() != null;
         int bufferSize = compression.getBufferSize();
         OrcProto.RowIndex[] rowIndex = index.getRowGroupIndex();

         for(StreamInformation stream : this.dataStreams) {
            if (readPhase != TypeReader.ReadPhase.ALL && (readPhase != TypeReader.ReadPhase.LEADERS || !this.filterColIds.contains(stream.column)) && (readPhase != TypeReader.ReadPhase.FOLLOWERS || this.filterColIds.contains(stream.column))) {
               LOG.debug("Skipping planning for column stream {} at level {}", stream, readPhase);
            } else {
               this.processStream(stream, result, rowIndex, startGroup, includedRowGroups, isCompressed, bufferSize);
            }
         }
      }

      return result;
   }

   private void processStream(StreamInformation stream, BufferChunkList result, OrcProto.RowIndex[] rowIndex, int startGroup, boolean[] includedRowGroups, boolean isCompressed, int bufferSize) {
      if (RecordReaderUtils.isDictionary(stream.kind, this.encodings[stream.column])) {
         this.addChunk(result, stream, stream.offset, stream.length);
      } else {
         int column = stream.column;
         OrcProto.RowIndex ri = rowIndex[column];
         TypeDescription.Category kind = this.schema.findSubtype(column).getCategory();
         long alreadyRead = 0L;

         for(int group = startGroup; group < includedRowGroups.length; ++group) {
            if (includedRowGroups[group]) {
               int endGroup;
               for(endGroup = group; endGroup < includedRowGroups.length - 1 && includedRowGroups[endGroup + 1]; ++endGroup) {
               }

               int posn = RecordReaderUtils.getIndexPosition(this.encodings[stream.column].getKind(), kind, stream.kind, isCompressed, this.hasNull[column]);
               long start = Math.max(alreadyRead, stream.offset + (group == 0 ? 0L : ri.getEntry(group).getPositions(posn)));
               long end = stream.offset;
               if (endGroup == includedRowGroups.length - 1) {
                  end += stream.length;
               } else {
                  long nextGroupOffset = ri.getEntry(endGroup + 1).getPositions(posn);
                  end += RecordReaderUtils.estimateRgEndOffset(isCompressed, bufferSize, false, nextGroupOffset, stream.length);
               }

               if (alreadyRead < end) {
                  this.addChunk(result, stream, start, end - start);
                  alreadyRead = end;
               }

               group = endGroup;
            }
         }
      }

   }

   public static class StreamInformation {
      public final OrcProto.Stream.Kind kind;
      public final int column;
      public final long offset;
      public final long length;
      public BufferChunk firstChunk;

      public StreamInformation(OrcProto.Stream.Kind kind, int column, long offset, long length) {
         this.kind = kind;
         this.column = column;
         this.offset = offset;
         this.length = length;
      }

      /** @deprecated */
      @Deprecated
      void releaseBuffers(DataReader reader) {
         long end = this.offset + this.length;

         for(BufferChunk ptr = this.firstChunk; ptr != null && ptr.getOffset() < end; ptr = (BufferChunk)ptr.next) {
            ByteBuffer buffer = ptr.getData();
            if (buffer != null) {
               reader.releaseBuffer(buffer);
               ptr.setChunk((ByteBuffer)null);
            }
         }

      }
   }
}
