package org.apache.commons.compress.archivers.sevenz;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import org.apache.commons.compress.MemoryLimitException;
import org.apache.commons.compress.utils.ByteUtils;
import org.apache.commons.compress.utils.InputStreamStatistics;
import org.apache.commons.compress.utils.SeekableInMemoryByteChannel;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.build.AbstractOrigin;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.apache.commons.io.input.BoundedInputStream;
import org.apache.commons.io.input.ChecksumInputStream;

public class SevenZFile implements Closeable {
   static final int SIGNATURE_HEADER_SIZE = 32;
   private static final String DEFAULT_FILE_NAME = "unknown archive";
   static final byte[] sevenZSignature = new byte[]{55, 122, -68, -81, 39, 28};
   private final String fileName;
   private SeekableByteChannel channel;
   private final Archive archive;
   private int currentEntryIndex;
   private int currentFolderIndex;
   private InputStream currentFolderInputStream;
   private byte[] password;
   private long compressedBytesReadFromCurrentEntry;
   private long uncompressedBytesReadFromCurrentEntry;
   private final ArrayList deferredBlockStreams;
   private final int maxMemoryLimitKb;
   private final boolean useDefaultNameForUnnamedEntries;
   private final boolean tryToRecoverBrokenArchives;

   private static int assertFitsIntoNonNegativeInt(String what, long value) throws IOException {
      if (value <= 2147483647L && value >= 0L) {
         return (int)value;
      } else {
         throw new IOException(String.format("Cannot handle % %,d", what, value));
      }
   }

   public static Builder builder() {
      return new Builder();
   }

   private static ByteBuffer checkEndOfFile(ByteBuffer buf, int expectRemaining) throws EOFException {
      int remaining = buf.remaining();
      if (remaining < expectRemaining) {
         throw new EOFException(String.format("remaining %,d < expectRemaining %,d", remaining, expectRemaining));
      } else {
         return buf;
      }
   }

   private static void get(ByteBuffer buf, byte[] to) throws EOFException {
      checkEndOfFile(buf, to.length).get(to);
   }

   private static char getChar(ByteBuffer buf) throws EOFException {
      return checkEndOfFile(buf, 2).getChar();
   }

   private static int getInt(ByteBuffer buf) throws EOFException {
      return checkEndOfFile(buf, 4).getInt();
   }

   private static long getLong(ByteBuffer buf) throws EOFException {
      return checkEndOfFile(buf, 8).getLong();
   }

   private static int getUnsignedByte(ByteBuffer buf) throws EOFException {
      if (!buf.hasRemaining()) {
         throw new EOFException();
      } else {
         return buf.get() & 255;
      }
   }

   public static boolean matches(byte[] signature, int length) {
      if (length < sevenZSignature.length) {
         return false;
      } else {
         for(int i = 0; i < sevenZSignature.length; ++i) {
            if (signature[i] != sevenZSignature[i]) {
               return false;
            }
         }

         return true;
      }
   }

   private static SeekableByteChannel newByteChannel(File file) throws IOException {
      return Files.newByteChannel(file.toPath(), EnumSet.of(StandardOpenOption.READ));
   }

   private static long readUint64(ByteBuffer in) throws IOException {
      long firstByte = (long)getUnsignedByte(in);
      int mask = 128;
      long value = 0L;

      for(int i = 0; i < 8; ++i) {
         if ((firstByte & (long)mask) == 0L) {
            return value | (firstByte & (long)(mask - 1)) << 8 * i;
         }

         long nextByte = (long)getUnsignedByte(in);
         value |= nextByte << 8 * i;
         mask >>>= 1;
      }

      return value;
   }

   private static long skipBytesFully(ByteBuffer input, long bytesToSkip) {
      if (bytesToSkip < 1L) {
         return 0L;
      } else {
         int current = input.position();
         int maxSkip = input.remaining();
         if ((long)maxSkip < bytesToSkip) {
            bytesToSkip = (long)maxSkip;
         }

         input.position(current + (int)bytesToSkip);
         return bytesToSkip;
      }
   }

   /** @deprecated */
   @Deprecated
   public SevenZFile(File fileName) throws IOException {
      this(fileName, SevenZFileOptions.DEFAULT);
   }

   /** @deprecated */
   @Deprecated
   public SevenZFile(File file, byte[] password) throws IOException {
      this(newByteChannel(file), file.getAbsolutePath(), password, true, SevenZFileOptions.DEFAULT);
   }

   /** @deprecated */
   @Deprecated
   public SevenZFile(File file, char[] password) throws IOException {
      this(file, password, SevenZFileOptions.DEFAULT);
   }

   /** @deprecated */
   @Deprecated
   public SevenZFile(File file, char[] password, SevenZFileOptions options) throws IOException {
      this(newByteChannel(file), file.getAbsolutePath(), AES256SHA256Decoder.utf16Decode(password), true, options);
   }

   /** @deprecated */
   @Deprecated
   public SevenZFile(File file, SevenZFileOptions options) throws IOException {
      this((File)file, (char[])null, (SevenZFileOptions)options);
   }

   /** @deprecated */
   @Deprecated
   public SevenZFile(SeekableByteChannel channel) throws IOException {
      this(channel, SevenZFileOptions.DEFAULT);
   }

   /** @deprecated */
   @Deprecated
   public SevenZFile(SeekableByteChannel channel, byte[] password) throws IOException {
      this(channel, "unknown archive", password);
   }

   /** @deprecated */
   @Deprecated
   public SevenZFile(SeekableByteChannel channel, char[] password) throws IOException {
      this(channel, password, SevenZFileOptions.DEFAULT);
   }

   /** @deprecated */
   @Deprecated
   public SevenZFile(SeekableByteChannel channel, char[] password, SevenZFileOptions options) throws IOException {
      this(channel, "unknown archive", password, options);
   }

   /** @deprecated */
   @Deprecated
   public SevenZFile(SeekableByteChannel channel, SevenZFileOptions options) throws IOException {
      this(channel, "unknown archive", (char[])null, options);
   }

   /** @deprecated */
   @Deprecated
   public SevenZFile(SeekableByteChannel channel, String fileName) throws IOException {
      this(channel, fileName, SevenZFileOptions.DEFAULT);
   }

   /** @deprecated */
   @Deprecated
   public SevenZFile(SeekableByteChannel channel, String fileName, byte[] password) throws IOException {
      this(channel, fileName, password, false, SevenZFileOptions.DEFAULT);
   }

   private SevenZFile(SeekableByteChannel channel, String fileName, byte[] password, boolean closeOnError, int maxMemoryLimitKb, boolean useDefaultNameForUnnamedEntries, boolean tryToRecoverBrokenArchives) throws IOException {
      this.currentEntryIndex = -1;
      this.currentFolderIndex = -1;
      this.deferredBlockStreams = new ArrayList();
      boolean succeeded = false;
      this.channel = channel;
      this.fileName = fileName;
      this.maxMemoryLimitKb = maxMemoryLimitKb;
      this.useDefaultNameForUnnamedEntries = useDefaultNameForUnnamedEntries;
      this.tryToRecoverBrokenArchives = tryToRecoverBrokenArchives;

      try {
         this.archive = this.readHeaders(password);
         if (password != null) {
            this.password = Arrays.copyOf(password, password.length);
         } else {
            this.password = null;
         }

         succeeded = true;
      } finally {
         if (!succeeded && closeOnError) {
            this.channel.close();
         }

      }

   }

   /** @deprecated */
   @Deprecated
   private SevenZFile(SeekableByteChannel channel, String fileName, byte[] password, boolean closeOnError, SevenZFileOptions options) throws IOException {
      this(channel, fileName, password, closeOnError, options.getMaxMemoryLimitInKb(), options.getUseDefaultNameForUnnamedEntries(), options.getTryToRecoverBrokenArchives());
   }

   /** @deprecated */
   @Deprecated
   public SevenZFile(SeekableByteChannel channel, String fileName, char[] password) throws IOException {
      this(channel, fileName, password, SevenZFileOptions.DEFAULT);
   }

   /** @deprecated */
   @Deprecated
   public SevenZFile(SeekableByteChannel channel, String fileName, char[] password, SevenZFileOptions options) throws IOException {
      this(channel, fileName, AES256SHA256Decoder.utf16Decode(password), false, options);
   }

   /** @deprecated */
   @Deprecated
   public SevenZFile(SeekableByteChannel channel, String fileName, SevenZFileOptions options) throws IOException {
      this(channel, fileName, (byte[])null, false, options);
   }

   private InputStream buildDecoderStack(Folder folder, long folderOffset, int firstPackStreamIndex, SevenZArchiveEntry entry) throws IOException {
      this.channel.position(folderOffset);
      InputStream inputStreamStack = new FilterInputStream(new BufferedInputStream(new BoundedSeekableByteChannelInputStream(this.channel, this.archive.packSizes[firstPackStreamIndex]))) {
         private void count(int c) {
            SevenZFile.this.compressedBytesReadFromCurrentEntry = (long)c;
         }

         public int read() throws IOException {
            int r = this.in.read();
            if (r >= 0) {
               this.count(1);
            }

            return r;
         }

         public int read(byte[] b) throws IOException {
            return this.read(b, 0, b.length);
         }

         public int read(byte[] b, int off, int len) throws IOException {
            if (len == 0) {
               return 0;
            } else {
               int r = this.in.read(b, off, len);
               if (r >= 0) {
                  this.count(r);
               }

               return r;
            }
         }
      };
      LinkedList<SevenZMethodConfiguration> methods = new LinkedList();

      for(Coder coder : folder.getOrderedCoders()) {
         if (coder.numInStreams != 1L || coder.numOutStreams != 1L) {
            throw new IOException("Multi input/output stream coders are not yet supported");
         }

         SevenZMethod method = SevenZMethod.byId(coder.decompressionMethodId);
         inputStreamStack = Coders.addDecoder(this.fileName, inputStreamStack, folder.getUnpackSizeForCoder(coder), coder, this.password, this.maxMemoryLimitKb);
         methods.addFirst(new SevenZMethodConfiguration(method, Coders.findByMethod(method).getOptionsFromCoder(coder, inputStreamStack)));
      }

      entry.setContentMethods((Iterable)methods);
      if (folder.hasCrc) {
         return ((ChecksumInputStream.Builder)ChecksumInputStream.builder().setChecksum(new CRC32()).setInputStream(inputStreamStack)).setCountThreshold(folder.getUnpackSize()).setExpectedChecksumValue(folder.crc).get();
      } else {
         return inputStreamStack;
      }
   }

   private void buildDecodingStream(int entryIndex, boolean isRandomAccess) throws IOException {
      if (this.archive.streamMap == null) {
         throw new IOException("Archive doesn't contain stream information to read entries");
      } else {
         int folderIndex = this.archive.streamMap.fileFolderIndex[entryIndex];
         if (folderIndex < 0) {
            this.deferredBlockStreams.clear();
         } else {
            SevenZArchiveEntry file = this.archive.files[entryIndex];
            boolean isInSameFolder = false;
            if (this.currentFolderIndex == folderIndex) {
               if (entryIndex > 0) {
                  file.setContentMethods(this.archive.files[entryIndex - 1].getContentMethods());
               }

               if (isRandomAccess && file.getContentMethods() == null) {
                  int folderFirstFileIndex = this.archive.streamMap.folderFirstFileIndex[folderIndex];
                  SevenZArchiveEntry folderFirstFile = this.archive.files[folderFirstFileIndex];
                  file.setContentMethods(folderFirstFile.getContentMethods());
               }

               isInSameFolder = true;
            } else {
               this.currentFolderIndex = folderIndex;
               this.reopenFolderInputStream(folderIndex, file);
            }

            boolean haveSkippedEntries = false;
            if (isRandomAccess) {
               haveSkippedEntries = this.skipEntriesWhenNeeded(entryIndex, isInSameFolder, folderIndex);
            }

            if (!isRandomAccess || this.currentEntryIndex != entryIndex || haveSkippedEntries) {
               InputStream fileStream = ((BoundedInputStream.Builder)((BoundedInputStream.Builder)((BoundedInputStream.Builder)BoundedInputStream.builder().setInputStream(this.currentFolderInputStream)).setMaxCount(file.getSize())).setPropagateClose(false)).get();
               if (file.getHasCrc()) {
                  fileStream = ((ChecksumInputStream.Builder)ChecksumInputStream.builder().setChecksum(new CRC32()).setInputStream(fileStream)).setExpectedChecksumValue(file.getCrcValue()).get();
               }

               this.deferredBlockStreams.add(fileStream);
            }
         }
      }
   }

   private void calculateStreamMap(Archive archive) throws IOException {
      int nextFolderPackStreamIndex = 0;
      int numFolders = archive.folders != null ? archive.folders.length : 0;
      int[] folderFirstPackStreamIndex = new int[numFolders];

      for(int i = 0; i < numFolders; ++i) {
         folderFirstPackStreamIndex[i] = nextFolderPackStreamIndex;
         nextFolderPackStreamIndex += archive.folders[i].packedStreams.length;
      }

      long nextPackStreamOffset = 0L;
      int numPackSizes = archive.packSizes.length;
      long[] packStreamOffsets = new long[numPackSizes];

      for(int i = 0; i < numPackSizes; ++i) {
         packStreamOffsets[i] = nextPackStreamOffset;
         nextPackStreamOffset += archive.packSizes[i];
      }

      int[] folderFirstFileIndex = new int[numFolders];
      int[] fileFolderIndex = new int[archive.files.length];
      int nextFolderIndex = 0;
      int nextFolderUnpackStreamIndex = 0;

      for(int i = 0; i < archive.files.length; ++i) {
         if (!archive.files[i].hasStream() && nextFolderUnpackStreamIndex == 0) {
            fileFolderIndex[i] = -1;
         } else {
            if (nextFolderUnpackStreamIndex == 0) {
               while(nextFolderIndex < archive.folders.length) {
                  folderFirstFileIndex[nextFolderIndex] = i;
                  if (archive.folders[nextFolderIndex].numUnpackSubStreams > 0) {
                     break;
                  }

                  ++nextFolderIndex;
               }

               if (nextFolderIndex >= archive.folders.length) {
                  throw new IOException("Too few folders in archive");
               }
            }

            fileFolderIndex[i] = nextFolderIndex;
            if (archive.files[i].hasStream()) {
               ++nextFolderUnpackStreamIndex;
               if (nextFolderUnpackStreamIndex >= archive.folders[nextFolderIndex].numUnpackSubStreams) {
                  ++nextFolderIndex;
                  nextFolderUnpackStreamIndex = 0;
               }
            }
         }
      }

      archive.streamMap = new StreamMap(folderFirstPackStreamIndex, packStreamOffsets, folderFirstFileIndex, fileFolderIndex);
   }

   private void checkEntryIsInitialized(Map archiveEntries, int index) {
      archiveEntries.computeIfAbsent(index, (i) -> new SevenZArchiveEntry());
   }

   public void close() throws IOException {
      if (this.channel != null) {
         try {
            this.channel.close();
         } finally {
            this.channel = null;
            if (this.password != null) {
               Arrays.fill(this.password, (byte)0);
            }

            this.password = null;
         }
      }

   }

   private InputStream getCurrentStream() throws IOException {
      if (this.archive.files[this.currentEntryIndex].getSize() == 0L) {
         return new ByteArrayInputStream(ByteUtils.EMPTY_BYTE_ARRAY);
      } else if (this.deferredBlockStreams.isEmpty()) {
         throw new IllegalStateException("No current 7z entry (call getNextEntry() first).");
      } else {
         for(; this.deferredBlockStreams.size() > 1; this.compressedBytesReadFromCurrentEntry = 0L) {
            InputStream stream = (InputStream)this.deferredBlockStreams.remove(0);

            try {
               IOUtils.skip(stream, Long.MAX_VALUE, IOUtils::byteArray);
            } catch (Throwable var5) {
               if (stream != null) {
                  try {
                     stream.close();
                  } catch (Throwable var4) {
                     var5.addSuppressed(var4);
                  }
               }

               throw var5;
            }

            if (stream != null) {
               stream.close();
            }
         }

         return (InputStream)this.deferredBlockStreams.get(0);
      }
   }

   public String getDefaultName() {
      if (!"unknown archive".equals(this.fileName) && this.fileName != null) {
         String lastSegment = (new File(this.fileName)).getName();
         int dotPos = lastSegment.lastIndexOf(".");
         return dotPos > 0 ? lastSegment.substring(0, dotPos) : lastSegment + "~";
      } else {
         return null;
      }
   }

   public Iterable getEntries() {
      return new ArrayList(Arrays.asList(this.archive.files));
   }

   public InputStream getInputStream(SevenZArchiveEntry entry) throws IOException {
      int entryIndex = -1;

      for(int i = 0; i < this.archive.files.length; ++i) {
         if (entry == this.archive.files[i]) {
            entryIndex = i;
            break;
         }
      }

      if (entryIndex < 0) {
         throw new IllegalArgumentException("Can not find " + entry.getName() + " in " + this.fileName);
      } else {
         this.buildDecodingStream(entryIndex, true);
         this.currentEntryIndex = entryIndex;
         this.currentFolderIndex = this.archive.streamMap.fileFolderIndex[entryIndex];
         return this.getCurrentStream();
      }
   }

   public SevenZArchiveEntry getNextEntry() throws IOException {
      if (this.currentEntryIndex >= this.archive.files.length - 1) {
         return null;
      } else {
         ++this.currentEntryIndex;
         SevenZArchiveEntry entry = this.archive.files[this.currentEntryIndex];
         if (entry.getName() == null && this.useDefaultNameForUnnamedEntries) {
            entry.setName(this.getDefaultName());
         }

         this.buildDecodingStream(this.currentEntryIndex, false);
         this.uncompressedBytesReadFromCurrentEntry = this.compressedBytesReadFromCurrentEntry = 0L;
         return entry;
      }
   }

   public InputStreamStatistics getStatisticsForCurrentEntry() {
      return new InputStreamStatistics() {
         public long getCompressedCount() {
            return SevenZFile.this.compressedBytesReadFromCurrentEntry;
         }

         public long getUncompressedCount() {
            return SevenZFile.this.uncompressedBytesReadFromCurrentEntry;
         }
      };
   }

   private boolean hasCurrentEntryBeenRead() {
      boolean hasCurrentEntryBeenRead = false;
      if (!this.deferredBlockStreams.isEmpty()) {
         InputStream currentEntryInputStream = (InputStream)this.deferredBlockStreams.get(this.deferredBlockStreams.size() - 1);
         if (currentEntryInputStream instanceof ChecksumInputStream) {
            hasCurrentEntryBeenRead = ((ChecksumInputStream)currentEntryInputStream).getRemaining() != this.archive.files[this.currentEntryIndex].getSize();
         } else if (currentEntryInputStream instanceof BoundedInputStream) {
            hasCurrentEntryBeenRead = ((BoundedInputStream)currentEntryInputStream).getRemaining() != this.archive.files[this.currentEntryIndex].getSize();
         }
      }

      return hasCurrentEntryBeenRead;
   }

   private Archive initializeArchive(StartHeader startHeader, byte[] password, boolean verifyCrc) throws IOException {
      assertFitsIntoNonNegativeInt("nextHeaderSize", startHeader.nextHeaderSize);
      int nextHeaderSizeInt = (int)startHeader.nextHeaderSize;
      this.channel.position(32L + startHeader.nextHeaderOffset);
      if (verifyCrc) {
         long position = this.channel.position();
         CheckedInputStream cis = new CheckedInputStream(Channels.newInputStream(this.channel), new CRC32());
         if (cis.skip((long)nextHeaderSizeInt) != (long)nextHeaderSizeInt) {
            throw new IOException("Problem computing NextHeader CRC-32");
         }

         if (startHeader.nextHeaderCrc != cis.getChecksum().getValue()) {
            throw new IOException("NextHeader CRC-32 mismatch");
         }

         this.channel.position(position);
      }

      Archive archive = new Archive();
      ByteBuffer buf = ByteBuffer.allocate(nextHeaderSizeInt).order(ByteOrder.LITTLE_ENDIAN);
      this.readFully(buf);
      int nid = getUnsignedByte(buf);
      if (nid == 23) {
         buf = this.readEncodedHeader(buf, archive, password);
         archive = new Archive();
         nid = getUnsignedByte(buf);
      }

      if (nid != 1) {
         throw new IOException("Broken or unsupported archive: no Header");
      } else {
         this.readHeader(buf, archive);
         archive.subStreamsInfo = null;
         return archive;
      }
   }

   public int read() throws IOException {
      int b = this.getCurrentStream().read();
      if (b >= 0) {
         ++this.uncompressedBytesReadFromCurrentEntry;
      }

      return b;
   }

   public int read(byte[] b) throws IOException {
      return this.read(b, 0, b.length);
   }

   public int read(byte[] b, int off, int len) throws IOException {
      if (len == 0) {
         return 0;
      } else {
         int cnt = this.getCurrentStream().read(b, off, len);
         if (cnt > 0) {
            this.uncompressedBytesReadFromCurrentEntry += (long)cnt;
         }

         return cnt;
      }
   }

   private BitSet readAllOrBits(ByteBuffer header, int size) throws IOException {
      int areAllDefined = getUnsignedByte(header);
      BitSet bits;
      if (areAllDefined != 0) {
         bits = new BitSet(size);

         for(int i = 0; i < size; ++i) {
            bits.set(i, true);
         }
      } else {
         bits = this.readBits(header, size);
      }

      return bits;
   }

   private void readArchiveProperties(ByteBuffer input) throws IOException {
      for(long nid = readUint64(input); nid != 0L; nid = readUint64(input)) {
         long propertySize = readUint64(input);
         byte[] property = new byte[(int)propertySize];
         get(input, property);
      }

   }

   private BitSet readBits(ByteBuffer header, int size) throws IOException {
      BitSet bits = new BitSet(size);
      int mask = 0;
      int cache = 0;

      for(int i = 0; i < size; ++i) {
         if (mask == 0) {
            mask = 128;
            cache = getUnsignedByte(header);
         }

         bits.set(i, (cache & mask) != 0);
         mask >>>= 1;
      }

      return bits;
   }

   private ByteBuffer readEncodedHeader(ByteBuffer header, Archive archive, byte[] password) throws IOException {
      int pos = header.position();
      ArchiveStatistics stats = new ArchiveStatistics();
      this.sanityCheckStreamsInfo(header, stats);
      stats.assertValidity(this.maxMemoryLimitKb);
      header.position(pos);
      this.readStreamsInfo(header, archive);
      if (archive.folders != null && archive.folders.length != 0) {
         if (archive.packSizes != null && archive.packSizes.length != 0) {
            Folder folder = archive.folders[0];
            int firstPackStreamIndex = 0;
            long folderOffset = 32L + archive.packPos + 0L;
            this.channel.position(folderOffset);
            InputStream inputStreamStack = new BoundedSeekableByteChannelInputStream(this.channel, archive.packSizes[0]);

            for(Coder coder : folder.getOrderedCoders()) {
               if (coder.numInStreams != 1L || coder.numOutStreams != 1L) {
                  throw new IOException("Multi input/output stream coders are not yet supported");
               }

               inputStreamStack = Coders.addDecoder(this.fileName, inputStreamStack, folder.getUnpackSizeForCoder(coder), coder, password, this.maxMemoryLimitKb);
            }

            if (folder.hasCrc) {
               inputStreamStack = ((ChecksumInputStream.Builder)ChecksumInputStream.builder().setChecksum(new CRC32()).setInputStream(inputStreamStack)).setCountThreshold(folder.getUnpackSize()).setExpectedChecksumValue(folder.crc).get();
            }

            int unpackSize = assertFitsIntoNonNegativeInt("unpackSize", folder.getUnpackSize());
            byte[] nextHeader = org.apache.commons.compress.utils.IOUtils.readRange(inputStreamStack, unpackSize);
            if (nextHeader.length < unpackSize) {
               throw new IOException("premature end of stream");
            } else {
               inputStreamStack.close();
               return ByteBuffer.wrap(nextHeader).order(ByteOrder.LITTLE_ENDIAN);
            }
         } else {
            throw new IOException("no packed streams, can't read encoded header");
         }
      } else {
         throw new IOException("no folders, can't read encoded header");
      }
   }

   private void readFilesInfo(ByteBuffer header, Archive archive) throws IOException {
      int numFilesInt = (int)readUint64(header);
      Map<Integer, SevenZArchiveEntry> fileMap = new LinkedHashMap();
      BitSet isEmptyStream = null;
      BitSet isEmptyFile = null;
      BitSet isAnti = null;

      while(true) {
         int propertyType = getUnsignedByte(header);
         if (propertyType == 0) {
            propertyType = 0;
            int emptyFileCounter = 0;

            for(int i = 0; i < numFilesInt; ++i) {
               SevenZArchiveEntry entryAtIndex = (SevenZArchiveEntry)fileMap.get(i);
               if (entryAtIndex != null) {
                  entryAtIndex.setHasStream(isEmptyStream == null || !isEmptyStream.get(i));
                  if (entryAtIndex.hasStream()) {
                     if (archive.subStreamsInfo == null) {
                        throw new IOException("Archive contains file with streams but no subStreamsInfo");
                     }

                     entryAtIndex.setDirectory(false);
                     entryAtIndex.setAntiItem(false);
                     entryAtIndex.setHasCrc(archive.subStreamsInfo.hasCrc.get(propertyType));
                     entryAtIndex.setCrcValue(archive.subStreamsInfo.crcs[propertyType]);
                     entryAtIndex.setSize(archive.subStreamsInfo.unpackSizes[propertyType]);
                     if (entryAtIndex.getSize() < 0L) {
                        throw new IOException("broken archive, entry with negative size");
                     }

                     ++propertyType;
                  } else {
                     entryAtIndex.setDirectory(isEmptyFile == null || !isEmptyFile.get(emptyFileCounter));
                     entryAtIndex.setAntiItem(isAnti != null && isAnti.get(emptyFileCounter));
                     entryAtIndex.setHasCrc(false);
                     entryAtIndex.setSize(0L);
                     ++emptyFileCounter;
                  }
               }
            }

            archive.files = (SevenZArchiveEntry[])fileMap.values().stream().filter(Objects::nonNull).toArray((x$0) -> new SevenZArchiveEntry[x$0]);
            this.calculateStreamMap(archive);
            return;
         }

         long size = readUint64(header);
         switch (propertyType) {
            case 14:
               isEmptyStream = this.readBits(header, numFilesInt);
               break;
            case 15:
               isEmptyFile = this.readBits(header, isEmptyStream.cardinality());
               break;
            case 16:
               isAnti = this.readBits(header, isEmptyStream.cardinality());
               break;
            case 17:
               getUnsignedByte(header);
               byte[] names = new byte[(int)(size - 1L)];
               int namesLength = names.length;
               get(header, names);
               int nextFile = 0;
               int nextName = 0;

               for(int i = 0; i < namesLength; i += 2) {
                  if (names[i] == 0 && names[i + 1] == 0) {
                     this.checkEntryIsInitialized(fileMap, nextFile);
                     ((SevenZArchiveEntry)fileMap.get(nextFile)).setName(new String(names, nextName, i - nextName, StandardCharsets.UTF_16LE));
                     nextName = i + 2;
                     ++nextFile;
                  }
               }

               if (nextName != namesLength || nextFile != numFilesInt) {
                  throw new IOException("Error parsing file names");
               }
               break;
            case 18:
               BitSet timesDefined = this.readAllOrBits(header, numFilesInt);
               getUnsignedByte(header);

               for(int i = 0; i < numFilesInt; ++i) {
                  this.checkEntryIsInitialized(fileMap, i);
                  SevenZArchiveEntry entryAtIndex = (SevenZArchiveEntry)fileMap.get(i);
                  entryAtIndex.setHasCreationDate(timesDefined.get(i));
                  if (entryAtIndex.getHasCreationDate()) {
                     entryAtIndex.setCreationDate(getLong(header));
                  }
               }
               break;
            case 19:
               BitSet timesDefined = this.readAllOrBits(header, numFilesInt);
               getUnsignedByte(header);

               for(int i = 0; i < numFilesInt; ++i) {
                  this.checkEntryIsInitialized(fileMap, i);
                  SevenZArchiveEntry entryAtIndex = (SevenZArchiveEntry)fileMap.get(i);
                  entryAtIndex.setHasAccessDate(timesDefined.get(i));
                  if (entryAtIndex.getHasAccessDate()) {
                     entryAtIndex.setAccessDate(getLong(header));
                  }
               }
               break;
            case 20:
               BitSet timesDefined = this.readAllOrBits(header, numFilesInt);
               getUnsignedByte(header);

               for(int i = 0; i < numFilesInt; ++i) {
                  this.checkEntryIsInitialized(fileMap, i);
                  SevenZArchiveEntry entryAtIndex = (SevenZArchiveEntry)fileMap.get(i);
                  entryAtIndex.setHasLastModifiedDate(timesDefined.get(i));
                  if (entryAtIndex.getHasLastModifiedDate()) {
                     entryAtIndex.setLastModifiedDate(getLong(header));
                  }
               }
               break;
            case 21:
               BitSet attributesDefined = this.readAllOrBits(header, numFilesInt);
               getUnsignedByte(header);

               for(int i = 0; i < numFilesInt; ++i) {
                  this.checkEntryIsInitialized(fileMap, i);
                  SevenZArchiveEntry entryAtIndex = (SevenZArchiveEntry)fileMap.get(i);
                  entryAtIndex.setHasWindowsAttributes(attributesDefined.get(i));
                  if (entryAtIndex.getHasWindowsAttributes()) {
                     entryAtIndex.setWindowsAttributes(getInt(header));
                  }
               }
               break;
            case 22:
            case 23:
            case 24:
            default:
               skipBytesFully(header, size);
               break;
            case 25:
               skipBytesFully(header, size);
         }
      }
   }

   private Folder readFolder(ByteBuffer header) throws IOException {
      Folder folder = new Folder();
      long numCoders = readUint64(header);
      Coder[] coders = new Coder[(int)numCoders];
      long totalInStreams = 0L;
      long totalOutStreams = 0L;

      for(int i = 0; i < coders.length; ++i) {
         int bits = getUnsignedByte(header);
         int idSize = bits & 15;
         boolean isSimple = (bits & 16) == 0;
         boolean hasAttributes = (bits & 32) != 0;
         boolean moreAlternativeMethods = (bits & 128) != 0;
         byte[] decompressionMethodId = new byte[idSize];
         get(header, decompressionMethodId);
         long numInStreams;
         long numOutStreams;
         if (isSimple) {
            numInStreams = 1L;
            numOutStreams = 1L;
         } else {
            numInStreams = readUint64(header);
            numOutStreams = readUint64(header);
         }

         totalInStreams += numInStreams;
         totalOutStreams += numOutStreams;
         byte[] properties = null;
         if (hasAttributes) {
            long propertiesSize = readUint64(header);
            properties = new byte[(int)propertiesSize];
            get(header, properties);
         }

         if (moreAlternativeMethods) {
            throw new IOException("Alternative methods are unsupported, please report. The reference implementation doesn't support them either.");
         }

         coders[i] = new Coder(decompressionMethodId, numInStreams, numOutStreams, properties);
      }

      folder.coders = coders;
      folder.totalInputStreams = totalInStreams;
      folder.totalOutputStreams = totalOutStreams;
      long numBindPairs = totalOutStreams - 1L;
      BindPair[] bindPairs = new BindPair[(int)numBindPairs];

      for(int i = 0; i < bindPairs.length; ++i) {
         bindPairs[i] = new BindPair(readUint64(header), readUint64(header));
      }

      folder.bindPairs = bindPairs;
      long numPackedStreams = totalInStreams - numBindPairs;
      long[] packedStreams = new long[(int)numPackedStreams];
      if (numPackedStreams == 1L) {
         int i;
         for(i = 0; i < (int)totalInStreams && folder.findBindPairForInStream(i) >= 0; ++i) {
         }

         packedStreams[0] = (long)i;
      } else {
         for(int i = 0; i < (int)numPackedStreams; ++i) {
            packedStreams[i] = readUint64(header);
         }
      }

      folder.packedStreams = packedStreams;
      return folder;
   }

   private void readFully(ByteBuffer buf) throws IOException {
      buf.rewind();
      org.apache.commons.compress.utils.IOUtils.readFully((ReadableByteChannel)this.channel, (ByteBuffer)buf);
      buf.flip();
   }

   private void readHeader(ByteBuffer header, Archive archive) throws IOException {
      int pos = header.position();
      ArchiveStatistics stats = this.sanityCheckAndCollectStatistics(header);
      stats.assertValidity(this.maxMemoryLimitKb);
      header.position(pos);
      int nid = getUnsignedByte(header);
      if (nid == 2) {
         this.readArchiveProperties(header);
         nid = getUnsignedByte(header);
      }

      if (nid == 3) {
         throw new IOException("Additional streams unsupported");
      } else {
         if (nid == 4) {
            this.readStreamsInfo(header, archive);
            nid = getUnsignedByte(header);
         }

         if (nid == 5) {
            this.readFilesInfo(header, archive);
            nid = getUnsignedByte(header);
         }

      }
   }

   private Archive readHeaders(byte[] password) throws IOException {
      ByteBuffer buf = ByteBuffer.allocate(12).order(ByteOrder.LITTLE_ENDIAN);
      this.readFully(buf);
      byte[] signature = new byte[6];
      buf.get(signature);
      if (!Arrays.equals(signature, sevenZSignature)) {
         throw new IOException("Bad 7z signature");
      } else {
         byte archiveVersionMajor = buf.get();
         byte archiveVersionMinor = buf.get();
         if (archiveVersionMajor != 0) {
            throw new IOException(String.format("Unsupported 7z version (%d,%d)", archiveVersionMajor, archiveVersionMinor));
         } else {
            boolean headerLooksValid = false;
            long startHeaderCrc = 4294967295L & (long)buf.getInt();
            if (startHeaderCrc == 0L) {
               long currentPosition = this.channel.position();
               ByteBuffer peekBuf = ByteBuffer.allocate(20);
               this.readFully(peekBuf);
               this.channel.position(currentPosition);

               while(peekBuf.hasRemaining()) {
                  if (peekBuf.get() != 0) {
                     headerLooksValid = true;
                     break;
                  }
               }
            } else {
               headerLooksValid = true;
            }

            if (headerLooksValid) {
               return this.initializeArchive(this.readStartHeader(startHeaderCrc), password, true);
            } else if (this.tryToRecoverBrokenArchives) {
               return this.tryToLocateEndHeader(password);
            } else {
               throw new IOException("archive seems to be invalid.\nYou may want to retry and enable the tryToRecoverBrokenArchives if the archive could be a multi volume archive that has been closed prematurely.");
            }
         }
      }
   }

   private void readPackInfo(ByteBuffer header, Archive archive) throws IOException {
      archive.packPos = readUint64(header);
      int numPackStreamsInt = (int)readUint64(header);
      int nid = getUnsignedByte(header);
      if (nid == 9) {
         archive.packSizes = new long[numPackStreamsInt];

         for(int i = 0; i < archive.packSizes.length; ++i) {
            archive.packSizes[i] = readUint64(header);
         }

         nid = getUnsignedByte(header);
      }

      if (nid == 10) {
         archive.packCrcsDefined = this.readAllOrBits(header, numPackStreamsInt);
         archive.packCrcs = new long[numPackStreamsInt];

         for(int i = 0; i < numPackStreamsInt; ++i) {
            if (archive.packCrcsDefined.get(i)) {
               archive.packCrcs[i] = 4294967295L & (long)getInt(header);
            }
         }

         getUnsignedByte(header);
      }

   }

   private StartHeader readStartHeader(long startHeaderCrc) throws IOException {
      DataInputStream dataInputStream = new DataInputStream(((ChecksumInputStream.Builder)ChecksumInputStream.builder().setChecksum(new CRC32()).setInputStream(new BoundedSeekableByteChannelInputStream(this.channel, 20L))).setCountThreshold(20L).setExpectedChecksumValue(startHeaderCrc).get());

      StartHeader var12;
      try {
         long nextHeaderOffset = Long.reverseBytes(dataInputStream.readLong());
         if (nextHeaderOffset < 0L || nextHeaderOffset + 32L > this.channel.size()) {
            throw new IOException("nextHeaderOffset is out of bounds");
         }

         long nextHeaderSize = Long.reverseBytes(dataInputStream.readLong());
         long nextHeaderEnd = nextHeaderOffset + nextHeaderSize;
         if (nextHeaderEnd < nextHeaderOffset || nextHeaderEnd + 32L > this.channel.size()) {
            throw new IOException("nextHeaderSize is out of bounds");
         }

         long nextHeaderCrc = 4294967295L & (long)Integer.reverseBytes(dataInputStream.readInt());
         var12 = new StartHeader(nextHeaderOffset, nextHeaderSize, nextHeaderCrc);
      } catch (Throwable var14) {
         try {
            dataInputStream.close();
         } catch (Throwable var13) {
            var14.addSuppressed(var13);
         }

         throw var14;
      }

      dataInputStream.close();
      return var12;
   }

   private void readStreamsInfo(ByteBuffer header, Archive archive) throws IOException {
      int nid = getUnsignedByte(header);
      if (nid == 6) {
         this.readPackInfo(header, archive);
         nid = getUnsignedByte(header);
      }

      if (nid == 7) {
         this.readUnpackInfo(header, archive);
         nid = getUnsignedByte(header);
      } else {
         archive.folders = Folder.EMPTY_FOLDER_ARRAY;
      }

      if (nid == 8) {
         this.readSubStreamsInfo(header, archive);
         nid = getUnsignedByte(header);
      }

   }

   private void readSubStreamsInfo(ByteBuffer header, Archive archive) throws IOException {
      for(Folder folder : archive.folders) {
         folder.numUnpackSubStreams = 1;
      }

      long unpackStreamsCount = (long)archive.folders.length;
      int nid = getUnsignedByte(header);
      if (nid == 13) {
         unpackStreamsCount = 0L;

         for(Folder folder : archive.folders) {
            long numStreams = readUint64(header);
            folder.numUnpackSubStreams = (int)numStreams;
            unpackStreamsCount += numStreams;
         }

         nid = getUnsignedByte(header);
      }

      int totalUnpackStreams = (int)unpackStreamsCount;
      SubStreamsInfo subStreamsInfo = new SubStreamsInfo(totalUnpackStreams);
      int nextUnpackStream = 0;

      for(Folder folder : archive.folders) {
         if (folder.numUnpackSubStreams != 0) {
            long sum = 0L;
            if (nid == 9) {
               for(int i = 0; i < folder.numUnpackSubStreams - 1; ++i) {
                  long size = readUint64(header);
                  subStreamsInfo.unpackSizes[nextUnpackStream++] = size;
                  sum += size;
               }
            }

            if (sum > folder.getUnpackSize()) {
               throw new IOException("sum of unpack sizes of folder exceeds total unpack size");
            }

            subStreamsInfo.unpackSizes[nextUnpackStream++] = folder.getUnpackSize() - sum;
         }
      }

      if (nid == 9) {
         nid = getUnsignedByte(header);
      }

      int numDigests = 0;

      for(Folder folder : archive.folders) {
         if (folder.numUnpackSubStreams != 1 || !folder.hasCrc) {
            numDigests += folder.numUnpackSubStreams;
         }
      }

      if (nid == 10) {
         BitSet hasMissingCrc = this.readAllOrBits(header, numDigests);
         long[] missingCrcs = new long[numDigests];

         for(int i = 0; i < numDigests; ++i) {
            if (hasMissingCrc.get(i)) {
               missingCrcs[i] = 4294967295L & (long)getInt(header);
            }
         }

         int nextCrc = 0;
         int nextMissingCrc = 0;

         for(Folder folder : archive.folders) {
            if (folder.numUnpackSubStreams == 1 && folder.hasCrc) {
               subStreamsInfo.hasCrc.set(nextCrc, true);
               subStreamsInfo.crcs[nextCrc] = folder.crc;
               ++nextCrc;
            } else {
               for(int i = 0; i < folder.numUnpackSubStreams; ++i) {
                  subStreamsInfo.hasCrc.set(nextCrc, hasMissingCrc.get(nextMissingCrc));
                  subStreamsInfo.crcs[nextCrc] = missingCrcs[nextMissingCrc];
                  ++nextCrc;
                  ++nextMissingCrc;
               }
            }
         }

         nid = getUnsignedByte(header);
      }

      archive.subStreamsInfo = subStreamsInfo;
   }

   private void readUnpackInfo(ByteBuffer header, Archive archive) throws IOException {
      int nid = getUnsignedByte(header);
      int numFoldersInt = (int)readUint64(header);
      Folder[] folders = new Folder[numFoldersInt];
      archive.folders = folders;
      getUnsignedByte(header);

      for(int i = 0; i < numFoldersInt; ++i) {
         folders[i] = this.readFolder(header);
      }

      nid = getUnsignedByte(header);

      for(Folder folder : folders) {
         assertFitsIntoNonNegativeInt("totalOutputStreams", folder.totalOutputStreams);
         folder.unpackSizes = new long[(int)folder.totalOutputStreams];

         for(int i = 0; (long)i < folder.totalOutputStreams; ++i) {
            folder.unpackSizes[i] = readUint64(header);
         }
      }

      nid = getUnsignedByte(header);
      if (nid == 10) {
         BitSet crcsDefined = this.readAllOrBits(header, numFoldersInt);

         for(int i = 0; i < numFoldersInt; ++i) {
            if (crcsDefined.get(i)) {
               folders[i].hasCrc = true;
               folders[i].crc = 4294967295L & (long)getInt(header);
            } else {
               folders[i].hasCrc = false;
            }
         }

         nid = getUnsignedByte(header);
      }

   }

   private void reopenFolderInputStream(int folderIndex, SevenZArchiveEntry file) throws IOException {
      this.deferredBlockStreams.clear();
      if (this.currentFolderInputStream != null) {
         this.currentFolderInputStream.close();
         this.currentFolderInputStream = null;
      }

      Folder folder = this.archive.folders[folderIndex];
      int firstPackStreamIndex = this.archive.streamMap.folderFirstPackStreamIndex[folderIndex];
      long folderOffset = 32L + this.archive.packPos + this.archive.streamMap.packStreamOffsets[firstPackStreamIndex];
      this.currentFolderInputStream = this.buildDecoderStack(folder, folderOffset, firstPackStreamIndex, file);
   }

   private ArchiveStatistics sanityCheckAndCollectStatistics(ByteBuffer header) throws IOException {
      ArchiveStatistics stats = new ArchiveStatistics();
      int nid = getUnsignedByte(header);
      if (nid == 2) {
         this.sanityCheckArchiveProperties(header);
         nid = getUnsignedByte(header);
      }

      if (nid == 3) {
         throw new IOException("Additional streams unsupported");
      } else {
         if (nid == 4) {
            this.sanityCheckStreamsInfo(header, stats);
            nid = getUnsignedByte(header);
         }

         if (nid == 5) {
            this.sanityCheckFilesInfo(header, stats);
            nid = getUnsignedByte(header);
         }

         if (nid != 0) {
            throw new IOException("Badly terminated header, found " + nid);
         } else {
            return stats;
         }
      }
   }

   private void sanityCheckArchiveProperties(ByteBuffer header) throws IOException {
      for(long nid = readUint64(header); nid != 0L; nid = readUint64(header)) {
         int propertySize = assertFitsIntoNonNegativeInt("propertySize", readUint64(header));
         if (skipBytesFully(header, (long)propertySize) < (long)propertySize) {
            throw new IOException("invalid property size");
         }
      }

   }

   private void sanityCheckFilesInfo(ByteBuffer header, ArchiveStatistics stats) throws IOException {
      stats.numberOfEntries = assertFitsIntoNonNegativeInt("numFiles", readUint64(header));
      int emptyStreams = -1;

      while(true) {
         int propertyType = getUnsignedByte(header);
         if (propertyType == 0) {
            stats.numberOfEntriesWithStream = stats.numberOfEntries - Math.max(emptyStreams, 0);
            return;
         }

         long size = readUint64(header);
         switch (propertyType) {
            case 14:
               emptyStreams = this.readBits(header, stats.numberOfEntries).cardinality();
               break;
            case 15:
               if (emptyStreams == -1) {
                  throw new IOException("Header format error: kEmptyStream must appear before kEmptyFile");
               }

               this.readBits(header, emptyStreams);
               break;
            case 16:
               if (emptyStreams == -1) {
                  throw new IOException("Header format error: kEmptyStream must appear before kAnti");
               }

               this.readBits(header, emptyStreams);
               break;
            case 17:
               int external = getUnsignedByte(header);
               if (external != 0) {
                  throw new IOException("Not implemented");
               }

               int namesLength = assertFitsIntoNonNegativeInt("file names length", size - 1L);
               if ((namesLength & 1) != 0) {
                  throw new IOException("File names length invalid");
               }

               int filesSeen = 0;
               int i = 0;

               for(; i < namesLength; i += 2) {
                  char c = getChar(header);
                  if (c == 0) {
                     ++filesSeen;
                  }
               }

               if (filesSeen != stats.numberOfEntries) {
                  throw new IOException("Invalid number of file names (" + filesSeen + " instead of " + stats.numberOfEntries + ")");
               }
               break;
            case 18:
               int timesDefined = this.readAllOrBits(header, stats.numberOfEntries).cardinality();
               int external = getUnsignedByte(header);
               if (external != 0) {
                  throw new IOException("Not implemented");
               }

               if (skipBytesFully(header, (long)(8 * timesDefined)) < (long)(8 * timesDefined)) {
                  throw new IOException("invalid creation dates size");
               }
               break;
            case 19:
               int timesDefined = this.readAllOrBits(header, stats.numberOfEntries).cardinality();
               int external = getUnsignedByte(header);
               if (external != 0) {
                  throw new IOException("Not implemented");
               }

               if (skipBytesFully(header, (long)(8 * timesDefined)) < (long)(8 * timesDefined)) {
                  throw new IOException("invalid access dates size");
               }
               break;
            case 20:
               int timesDefined = this.readAllOrBits(header, stats.numberOfEntries).cardinality();
               int external = getUnsignedByte(header);
               if (external != 0) {
                  throw new IOException("Not implemented");
               }

               if (skipBytesFully(header, (long)(8 * timesDefined)) < (long)(8 * timesDefined)) {
                  throw new IOException("invalid modification dates size");
               }
               break;
            case 21:
               int attributesDefined = this.readAllOrBits(header, stats.numberOfEntries).cardinality();
               int external = getUnsignedByte(header);
               if (external != 0) {
                  throw new IOException("Not implemented");
               }

               if (skipBytesFully(header, (long)(4 * attributesDefined)) < (long)(4 * attributesDefined)) {
                  throw new IOException("invalid windows attributes size");
               }
               break;
            case 22:
            case 23:
            default:
               if (skipBytesFully(header, size) < size) {
                  throw new IOException("Incomplete property of type " + propertyType);
               }
               break;
            case 24:
               throw new IOException("kStartPos is unsupported, please report");
            case 25:
               if (skipBytesFully(header, size) < size) {
                  throw new IOException("Incomplete kDummy property");
               }
         }
      }
   }

   private int sanityCheckFolder(ByteBuffer header, ArchiveStatistics stats) throws IOException {
      int numCoders = assertFitsIntoNonNegativeInt("numCoders", readUint64(header));
      if (numCoders == 0) {
         throw new IOException("Folder without coders");
      } else {
         stats.numberOfCoders = (long)numCoders;
         long totalOutStreams = 0L;
         long totalInStreams = 0L;

         for(int i = 0; i < numCoders; ++i) {
            int bits = getUnsignedByte(header);
            int idSize = bits & 15;
            get(header, new byte[idSize]);
            boolean isSimple = (bits & 16) == 0;
            boolean hasAttributes = (bits & 32) != 0;
            boolean moreAlternativeMethods = (bits & 128) != 0;
            if (moreAlternativeMethods) {
               throw new IOException("Alternative methods are unsupported, please report. The reference implementation doesn't support them either.");
            }

            if (isSimple) {
               ++totalInStreams;
               ++totalOutStreams;
            } else {
               totalInStreams += (long)assertFitsIntoNonNegativeInt("numInStreams", readUint64(header));
               totalOutStreams += (long)assertFitsIntoNonNegativeInt("numOutStreams", readUint64(header));
            }

            if (hasAttributes) {
               int propertiesSize = assertFitsIntoNonNegativeInt("propertiesSize", readUint64(header));
               if (skipBytesFully(header, (long)propertiesSize) < (long)propertiesSize) {
                  throw new IOException("invalid propertiesSize in folder");
               }
            }
         }

         assertFitsIntoNonNegativeInt("totalInStreams", totalInStreams);
         assertFitsIntoNonNegativeInt("totalOutStreams", totalOutStreams);
         stats.numberOfOutStreams = totalOutStreams;
         stats.numberOfInStreams = totalInStreams;
         if (totalOutStreams == 0L) {
            throw new IOException("Total output streams can't be 0");
         } else {
            int numBindPairs = assertFitsIntoNonNegativeInt("numBindPairs", totalOutStreams - 1L);
            if (totalInStreams < (long)numBindPairs) {
               throw new IOException("Total input streams can't be less than the number of bind pairs");
            } else {
               BitSet inStreamsBound = new BitSet((int)totalInStreams);

               for(int i = 0; i < numBindPairs; ++i) {
                  int inIndex = assertFitsIntoNonNegativeInt("inIndex", readUint64(header));
                  if (totalInStreams <= (long)inIndex) {
                     throw new IOException("inIndex is bigger than number of inStreams");
                  }

                  inStreamsBound.set(inIndex);
                  int outIndex = assertFitsIntoNonNegativeInt("outIndex", readUint64(header));
                  if (totalOutStreams <= (long)outIndex) {
                     throw new IOException("outIndex is bigger than number of outStreams");
                  }
               }

               int numPackedStreams = assertFitsIntoNonNegativeInt("numPackedStreams", totalInStreams - (long)numBindPairs);
               if (numPackedStreams == 1) {
                  if (inStreamsBound.nextClearBit(0) == -1) {
                     throw new IOException("Couldn't find stream's bind pair index");
                  }
               } else {
                  for(int i = 0; i < numPackedStreams; ++i) {
                     int packedStreamIndex = assertFitsIntoNonNegativeInt("packedStreamIndex", readUint64(header));
                     if ((long)packedStreamIndex >= totalInStreams) {
                        throw new IOException("packedStreamIndex is bigger than number of totalInStreams");
                     }
                  }
               }

               return (int)totalOutStreams;
            }
         }
      }
   }

   private void sanityCheckPackInfo(ByteBuffer header, ArchiveStatistics stats) throws IOException {
      long packPos = readUint64(header);
      if (packPos >= 0L && 32L + packPos <= this.channel.size() && 32L + packPos >= 0L) {
         long numPackStreams = readUint64(header);
         stats.numberOfPackedStreams = assertFitsIntoNonNegativeInt("numPackStreams", numPackStreams);
         int nid = getUnsignedByte(header);
         if (nid == 9) {
            long totalPackSizes = 0L;

            for(int i = 0; i < stats.numberOfPackedStreams; ++i) {
               long packSize = readUint64(header);
               totalPackSizes += packSize;
               long endOfPackStreams = 32L + packPos + totalPackSizes;
               if (packSize < 0L || endOfPackStreams > this.channel.size() || endOfPackStreams < packPos) {
                  throw new IOException("packSize (" + packSize + ") is out of range");
               }
            }

            nid = getUnsignedByte(header);
         }

         if (nid == 10) {
            int crcsDefined = this.readAllOrBits(header, stats.numberOfPackedStreams).cardinality();
            if (skipBytesFully(header, (long)(4 * crcsDefined)) < (long)(4 * crcsDefined)) {
               throw new IOException("invalid number of CRCs in PackInfo");
            }

            nid = getUnsignedByte(header);
         }

         if (nid != 0) {
            throw new IOException("Badly terminated PackInfo (" + nid + ")");
         }
      } else {
         throw new IOException("packPos (" + packPos + ") is out of range");
      }
   }

   private void sanityCheckStreamsInfo(ByteBuffer header, ArchiveStatistics stats) throws IOException {
      int nid = getUnsignedByte(header);
      if (nid == 6) {
         this.sanityCheckPackInfo(header, stats);
         nid = getUnsignedByte(header);
      }

      if (nid == 7) {
         this.sanityCheckUnpackInfo(header, stats);
         nid = getUnsignedByte(header);
      }

      if (nid == 8) {
         this.sanityCheckSubStreamsInfo(header, stats);
         nid = getUnsignedByte(header);
      }

      if (nid != 0) {
         throw new IOException("Badly terminated StreamsInfo");
      }
   }

   private void sanityCheckSubStreamsInfo(ByteBuffer header, ArchiveStatistics stats) throws IOException {
      int nid = getUnsignedByte(header);
      List<Integer> numUnpackSubStreamsPerFolder = new LinkedList();
      if (nid == 13) {
         for(int i = 0; i < stats.numberOfFolders; ++i) {
            numUnpackSubStreamsPerFolder.add(assertFitsIntoNonNegativeInt("numStreams", readUint64(header)));
         }

         stats.numberOfUnpackSubStreams = numUnpackSubStreamsPerFolder.stream().mapToLong(Integer::longValue).sum();
         nid = getUnsignedByte(header);
      } else {
         stats.numberOfUnpackSubStreams = (long)stats.numberOfFolders;
      }

      assertFitsIntoNonNegativeInt("totalUnpackStreams", stats.numberOfUnpackSubStreams);
      if (nid == 9) {
         for(int numUnpackSubStreams : numUnpackSubStreamsPerFolder) {
            if (numUnpackSubStreams != 0) {
               for(int i = 0; i < numUnpackSubStreams - 1; ++i) {
                  long size = readUint64(header);
                  if (size < 0L) {
                     throw new IOException("negative unpackSize");
                  }
               }
            }
         }

         nid = getUnsignedByte(header);
      }

      int numDigests = 0;
      if (numUnpackSubStreamsPerFolder.isEmpty()) {
         numDigests = stats.folderHasCrc == null ? stats.numberOfFolders : stats.numberOfFolders - stats.folderHasCrc.cardinality();
      } else {
         int folderIdx = 0;

         for(int numUnpackSubStreams : numUnpackSubStreamsPerFolder) {
            if (numUnpackSubStreams != 1 || stats.folderHasCrc == null || !stats.folderHasCrc.get(folderIdx++)) {
               numDigests += numUnpackSubStreams;
            }
         }
      }

      if (nid == 10) {
         assertFitsIntoNonNegativeInt("numDigests", (long)numDigests);
         int missingCrcs = this.readAllOrBits(header, numDigests).cardinality();
         if (skipBytesFully(header, (long)(4 * missingCrcs)) < (long)(4 * missingCrcs)) {
            throw new IOException("invalid number of missing CRCs in SubStreamInfo");
         }

         nid = getUnsignedByte(header);
      }

      if (nid != 0) {
         throw new IOException("Badly terminated SubStreamsInfo");
      }
   }

   private void sanityCheckUnpackInfo(ByteBuffer header, ArchiveStatistics stats) throws IOException {
      int nid = getUnsignedByte(header);
      if (nid != 11) {
         throw new IOException("Expected kFolder, got " + nid);
      } else {
         long numFolders = readUint64(header);
         stats.numberOfFolders = assertFitsIntoNonNegativeInt("numFolders", numFolders);
         int external = getUnsignedByte(header);
         if (external != 0) {
            throw new IOException("External unsupported");
         } else {
            List<Integer> numberOfOutputStreamsPerFolder = new LinkedList();

            for(int i = 0; i < stats.numberOfFolders; ++i) {
               numberOfOutputStreamsPerFolder.add(this.sanityCheckFolder(header, stats));
            }

            long totalNumberOfBindPairs = stats.numberOfOutStreams - (long)stats.numberOfFolders;
            long packedStreamsRequiredByFolders = stats.numberOfInStreams - totalNumberOfBindPairs;
            if (packedStreamsRequiredByFolders < (long)stats.numberOfPackedStreams) {
               throw new IOException("archive doesn't contain enough packed streams");
            } else {
               nid = getUnsignedByte(header);
               if (nid != 12) {
                  throw new IOException("Expected kCodersUnpackSize, got " + nid);
               } else {
                  for(int numberOfOutputStreams : numberOfOutputStreamsPerFolder) {
                     for(int i = 0; i < numberOfOutputStreams; ++i) {
                        long unpackSize = readUint64(header);
                        if (unpackSize < 0L) {
                           throw new IllegalArgumentException("negative unpackSize");
                        }
                     }
                  }

                  nid = getUnsignedByte(header);
                  if (nid == 10) {
                     stats.folderHasCrc = this.readAllOrBits(header, stats.numberOfFolders);
                     int crcsDefined = stats.folderHasCrc.cardinality();
                     if (skipBytesFully(header, (long)(4 * crcsDefined)) < (long)(4 * crcsDefined)) {
                        throw new IOException("invalid number of CRCs in UnpackInfo");
                     }

                     nid = getUnsignedByte(header);
                  }

                  if (nid != 0) {
                     throw new IOException("Badly terminated UnpackInfo");
                  }
               }
            }
         }
      }
   }

   private boolean skipEntriesWhenNeeded(int entryIndex, boolean isInSameFolder, int folderIndex) throws IOException {
      SevenZArchiveEntry file = this.archive.files[entryIndex];
      if (this.currentEntryIndex == entryIndex && !this.hasCurrentEntryBeenRead()) {
         return false;
      } else {
         int filesToSkipStartIndex = this.archive.streamMap.folderFirstFileIndex[this.currentFolderIndex];
         if (isInSameFolder) {
            if (this.currentEntryIndex < entryIndex) {
               filesToSkipStartIndex = this.currentEntryIndex + 1;
            } else {
               this.reopenFolderInputStream(folderIndex, file);
            }
         }

         for(int i = filesToSkipStartIndex; i < entryIndex; ++i) {
            SevenZArchiveEntry fileToSkip = this.archive.files[i];
            InputStream fileStreamToSkip = ((BoundedInputStream.Builder)((BoundedInputStream.Builder)((BoundedInputStream.Builder)BoundedInputStream.builder().setInputStream(this.currentFolderInputStream)).setMaxCount(fileToSkip.getSize())).setPropagateClose(false)).get();
            if (fileToSkip.getHasCrc()) {
               fileStreamToSkip = ((ChecksumInputStream.Builder)ChecksumInputStream.builder().setChecksum(new CRC32()).setInputStream(fileStreamToSkip)).setCountThreshold(fileToSkip.getSize()).setExpectedChecksumValue(fileToSkip.getCrcValue()).get();
            }

            this.deferredBlockStreams.add(fileStreamToSkip);
            fileToSkip.setContentMethods(file.getContentMethods());
         }

         return true;
      }
   }

   public String toString() {
      return this.archive.toString();
   }

   private Archive tryToLocateEndHeader(byte[] password) throws IOException {
      ByteBuffer nidBuf = ByteBuffer.allocate(1);
      long searchLimit = 1048576L;
      long previousDataSize = this.channel.position() + 20L;
      long minPos;
      if (this.channel.position() + 1048576L > this.channel.size()) {
         minPos = this.channel.position();
      } else {
         minPos = this.channel.size() - 1048576L;
      }

      long pos = this.channel.size() - 1L;

      while(pos > minPos) {
         --pos;
         this.channel.position(pos);
         nidBuf.rewind();
         if (this.channel.read(nidBuf) < 1) {
            throw new EOFException();
         }

         int nid = nidBuf.array()[0];
         if (nid == 23 || nid == 1) {
            try {
               long nextHeaderOffset = pos - previousDataSize;
               long nextHeaderSize = this.channel.size() - pos;
               StartHeader startHeader = new StartHeader(nextHeaderOffset, nextHeaderSize, 0L);
               Archive result = this.initializeArchive(startHeader, password, false);
               if (result.packSizes.length > 0 && result.files.length > 0) {
                  return result;
               }
            } catch (Exception var18) {
            }
         }
      }

      throw new IOException("Start header corrupt and unable to guess end header");
   }

   private static final class ArchiveStatistics {
      private int numberOfPackedStreams;
      private long numberOfCoders;
      private long numberOfOutStreams;
      private long numberOfInStreams;
      private long numberOfUnpackSubStreams;
      private int numberOfFolders;
      private BitSet folderHasCrc;
      private int numberOfEntries;
      private int numberOfEntriesWithStream;

      private ArchiveStatistics() {
      }

      void assertValidity(int maxMemoryLimitInKb) throws IOException {
         if (this.numberOfEntriesWithStream > 0 && this.numberOfFolders == 0) {
            throw new IOException("archive with entries but no folders");
         } else if ((long)this.numberOfEntriesWithStream > this.numberOfUnpackSubStreams) {
            throw new IOException("archive doesn't contain enough substreams for entries");
         } else {
            long memoryNeededInKb = this.estimateSize() / 1024L;
            if ((long)maxMemoryLimitInKb < memoryNeededInKb) {
               throw new MemoryLimitException(memoryNeededInKb, maxMemoryLimitInKb);
            }
         }
      }

      private long bindPairSize() {
         return 16L;
      }

      private long coderSize() {
         return 22L;
      }

      private long entrySize() {
         return 100L;
      }

      long estimateSize() {
         long lowerBound = 16L * (long)this.numberOfPackedStreams + (long)(this.numberOfPackedStreams / 8) + (long)this.numberOfFolders * this.folderSize() + this.numberOfCoders * this.coderSize() + (this.numberOfOutStreams - (long)this.numberOfFolders) * this.bindPairSize() + 8L * (this.numberOfInStreams - this.numberOfOutStreams + (long)this.numberOfFolders) + 8L * this.numberOfOutStreams + (long)this.numberOfEntries * this.entrySize() + this.streamMapSize();
         return 2L * lowerBound;
      }

      private long folderSize() {
         return 30L;
      }

      private long streamMapSize() {
         return (long)(8 * this.numberOfFolders + 8 * this.numberOfPackedStreams + 4 * this.numberOfEntries);
      }

      public String toString() {
         return "Archive with " + this.numberOfEntries + " entries in " + this.numberOfFolders + " folders. Estimated size " + this.estimateSize() / 1024L + " kB.";
      }
   }

   public static class Builder extends AbstractStreamBuilder {
      static final int MEMORY_LIMIT_IN_KB = Integer.MAX_VALUE;
      static final boolean USE_DEFAULTNAME_FOR_UNNAMED_ENTRIES = false;
      static final boolean TRY_TO_RECOVER_BROKEN_ARCHIVES = false;
      private SeekableByteChannel seekableByteChannel;
      private String defaultName = "unknown archive";
      private byte[] password;
      private int maxMemoryLimitKb = Integer.MAX_VALUE;
      private boolean useDefaultNameForUnnamedEntries = false;
      private boolean tryToRecoverBrokenArchives = false;

      public SevenZFile get() throws IOException {
         SeekableByteChannel actualChannel;
         String actualDescription;
         if (this.seekableByteChannel != null) {
            actualChannel = this.seekableByteChannel;
            actualDescription = this.defaultName;
         } else if (this.checkOrigin() instanceof AbstractOrigin.ByteArrayOrigin) {
            actualChannel = new SeekableInMemoryByteChannel(this.checkOrigin().getByteArray());
            actualDescription = this.defaultName;
         } else {
            OpenOption[] openOptions = this.getOpenOptions();
            if (openOptions.length == 0) {
               openOptions = new OpenOption[]{StandardOpenOption.READ};
            }

            Path path = this.getPath();
            actualChannel = Files.newByteChannel(path, openOptions);
            actualDescription = path.toAbsolutePath().toString();
         }

         boolean closeOnError = this.seekableByteChannel != null;
         return new SevenZFile(actualChannel, actualDescription, this.password, closeOnError, this.maxMemoryLimitKb, this.useDefaultNameForUnnamedEntries, this.tryToRecoverBrokenArchives);
      }

      public Builder setDefaultName(String defaultName) {
         this.defaultName = defaultName;
         return this;
      }

      public Builder setMaxMemoryLimitKb(int maxMemoryLimitKb) {
         this.maxMemoryLimitKb = maxMemoryLimitKb;
         return this;
      }

      public Builder setPassword(byte[] password) {
         this.password = password != null ? (byte[])(([B)password).clone() : null;
         return this;
      }

      public Builder setPassword(char[] password) {
         this.password = password != null ? AES256SHA256Decoder.utf16Decode((char[])(([C)password).clone()) : null;
         return this;
      }

      public Builder setPassword(String password) {
         this.password = password != null ? AES256SHA256Decoder.utf16Decode(password.toCharArray()) : null;
         return this;
      }

      public Builder setSeekableByteChannel(SeekableByteChannel seekableByteChannel) {
         this.seekableByteChannel = seekableByteChannel;
         return this;
      }

      public Builder setTryToRecoverBrokenArchives(boolean tryToRecoverBrokenArchives) {
         this.tryToRecoverBrokenArchives = tryToRecoverBrokenArchives;
         return this;
      }

      public Builder setUseDefaultNameForUnnamedEntries(boolean useDefaultNameForUnnamedEntries) {
         this.useDefaultNameForUnnamedEntries = useDefaultNameForUnnamedEntries;
         return this;
      }
   }
}
