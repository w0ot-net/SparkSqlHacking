package org.apache.commons.compress.archivers.zip;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.zip.Inflater;
import java.util.zip.ZipException;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.deflate64.Deflate64CompressorInputStream;
import org.apache.commons.compress.utils.BoundedArchiveInputStream;
import org.apache.commons.compress.utils.BoundedSeekableByteChannelInputStream;
import org.apache.commons.compress.utils.InputStreamStatistics;
import org.apache.commons.compress.utils.SeekableInMemoryByteChannel;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.build.AbstractOrigin;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.apache.commons.io.input.BoundedInputStream;

public class ZipFile implements Closeable {
   private static final String DEFAULT_CHARSET_NAME;
   private static final EnumSet READ;
   private static final int HASH_SIZE = 509;
   static final int NIBLET_MASK = 15;
   static final int BYTE_SHIFT = 8;
   private static final int POS_0 = 0;
   private static final int POS_1 = 1;
   private static final int POS_2 = 2;
   private static final int POS_3 = 3;
   private static final byte[] ONE_ZERO_BYTE;
   private static final int CFH_LEN = 42;
   private static final long CFH_SIG;
   static final int MIN_EOCD_SIZE = 22;
   private static final int MAX_EOCD_SIZE = 65557;
   private static final int CFD_LENGTH_OFFSET = 12;
   private static final int CFD_DISK_OFFSET = 6;
   private static final int CFD_LOCATOR_RELATIVE_OFFSET = 8;
   private static final int ZIP64_EOCDL_LENGTH = 20;
   private static final int ZIP64_EOCDL_LOCATOR_OFFSET = 8;
   private static final int ZIP64_EOCD_CFD_LOCATOR_OFFSET = 48;
   private static final int ZIP64_EOCD_CFD_DISK_OFFSET = 20;
   private static final int ZIP64_EOCD_CFD_LOCATOR_RELATIVE_OFFSET = 24;
   private static final long LFH_OFFSET_FOR_FILENAME_LENGTH = 26L;
   private static final Comparator offsetComparator;
   private final List entries;
   private final Map nameMap;
   private final Charset encoding;
   private final ZipEncoding zipEncoding;
   private final SeekableByteChannel archive;
   private final boolean useUnicodeExtraFields;
   private volatile boolean closed;
   private final boolean isSplitZipArchive;
   private final byte[] dwordBuf;
   private final byte[] wordBuf;
   private final byte[] cfhBuf;
   private final byte[] shortBuf;
   private final ByteBuffer dwordBbuf;
   private final ByteBuffer wordBbuf;
   private final ByteBuffer cfhBbuf;
   private final ByteBuffer shortBbuf;
   private long centralDirectoryStartDiskNumber;
   private long centralDirectoryStartRelativeOffset;
   private long centralDirectoryStartOffset;
   private long firstLocalFileHeaderOffset;

   public static Builder builder() {
      return new Builder();
   }

   public static void closeQuietly(ZipFile zipFile) {
      IOUtils.closeQuietly(zipFile);
   }

   private static SeekableByteChannel newReadByteChannel(Path path) throws IOException {
      return Files.newByteChannel(path, READ);
   }

   private static SeekableByteChannel openZipChannel(Path path, long maxNumberOfDisks, OpenOption[] openOptions) throws IOException {
      FileChannel channel = FileChannel.open(path, StandardOpenOption.READ);
      List<FileChannel> channels = new ArrayList();

      try {
         boolean is64 = positionAtEndOfCentralDirectoryRecord(channel);
         long numberOfDisks;
         if (is64) {
            channel.position(channel.position() + 4L + 4L + 8L);
            ByteBuffer buf = ByteBuffer.allocate(4);
            buf.order(ByteOrder.LITTLE_ENDIAN);
            org.apache.commons.compress.utils.IOUtils.readFully((ReadableByteChannel)channel, (ByteBuffer)buf);
            buf.flip();
            numberOfDisks = (long)buf.getInt() & 4294967295L;
         } else {
            channel.position(channel.position() + 4L);
            ByteBuffer buf = ByteBuffer.allocate(2);
            buf.order(ByteOrder.LITTLE_ENDIAN);
            org.apache.commons.compress.utils.IOUtils.readFully((ReadableByteChannel)channel, (ByteBuffer)buf);
            buf.flip();
            numberOfDisks = (long)((buf.getShort() & '\uffff') + 1);
         }

         if (numberOfDisks > Math.min(maxNumberOfDisks, 2147483647L)) {
            throw new IOException("Too many disks for zip archive, max=" + Math.min(maxNumberOfDisks, 2147483647L) + " actual=" + numberOfDisks);
         } else if (numberOfDisks <= 1L) {
            return channel;
         } else {
            channel.close();
            Path parent = path.getParent();
            String basename = FilenameUtils.removeExtension(Objects.toString(path.getFileName(), (String)null));
            return ZipSplitReadOnlySeekableByteChannel.forPaths((List)IntStream.range(0, (int)numberOfDisks).mapToObj((i) -> {
               if ((long)i == numberOfDisks - 1L) {
                  return path;
               } else {
                  Path lowercase = parent.resolve(String.format("%s.z%02d", basename, i + 1));
                  if (Files.exists(lowercase, new LinkOption[0])) {
                     return lowercase;
                  } else {
                     Path uppercase = parent.resolve(String.format("%s.Z%02d", basename, i + 1));
                     return Files.exists(uppercase, new LinkOption[0]) ? uppercase : lowercase;
                  }
               }
            }).collect(Collectors.toList()), openOptions);
         }
      } catch (Throwable ex) {
         IOUtils.closeQuietly(channel);
         channels.forEach(IOUtils::closeQuietly);
         throw ex;
      }
   }

   private static boolean positionAtEndOfCentralDirectoryRecord(SeekableByteChannel channel) throws IOException {
      boolean found = tryToLocateSignature(channel, 22L, 65557L, ZipArchiveOutputStream.EOCD_SIG);
      if (!found) {
         throw new ZipException("Archive is not a ZIP archive");
      } else {
         boolean found64 = false;
         long position = channel.position();
         if (position > 20L) {
            ByteBuffer wordBuf = ByteBuffer.allocate(4);
            channel.position(channel.position() - 20L);
            wordBuf.rewind();
            org.apache.commons.compress.utils.IOUtils.readFully((ReadableByteChannel)channel, (ByteBuffer)wordBuf);
            wordBuf.flip();
            found64 = wordBuf.equals(ByteBuffer.wrap(ZipArchiveOutputStream.ZIP64_EOCD_LOC_SIG));
            if (!found64) {
               channel.position(position);
            } else {
               channel.position(channel.position() - 4L);
            }
         }

         return found64;
      }
   }

   private static boolean tryToLocateSignature(SeekableByteChannel channel, long minDistanceFromEnd, long maxDistanceFromEnd, byte[] sig) throws IOException {
      ByteBuffer wordBuf = ByteBuffer.allocate(4);
      boolean found = false;
      long off = channel.size() - minDistanceFromEnd;
      long stopSearching = Math.max(0L, channel.size() - maxDistanceFromEnd);
      if (off >= 0L) {
         for(; off >= stopSearching; --off) {
            channel.position(off);

            try {
               wordBuf.rewind();
               org.apache.commons.compress.utils.IOUtils.readFully((ReadableByteChannel)channel, (ByteBuffer)wordBuf);
               wordBuf.flip();
            } catch (EOFException var13) {
               break;
            }

            int curr = wordBuf.get();
            if (curr == sig[0]) {
               curr = wordBuf.get();
               if (curr == sig[1]) {
                  curr = wordBuf.get();
                  if (curr == sig[2]) {
                     curr = wordBuf.get();
                     if (curr == sig[3]) {
                        found = true;
                        break;
                     }
                  }
               }
            }
         }
      }

      if (found) {
         channel.position(off);
      }

      return found;
   }

   /** @deprecated */
   @Deprecated
   public ZipFile(File file) throws IOException {
      this(file, DEFAULT_CHARSET_NAME);
   }

   /** @deprecated */
   @Deprecated
   public ZipFile(File file, String encoding) throws IOException {
      this(file.toPath(), encoding, true);
   }

   /** @deprecated */
   @Deprecated
   public ZipFile(File file, String encoding, boolean useUnicodeExtraFields) throws IOException {
      this(file.toPath(), encoding, useUnicodeExtraFields, false);
   }

   /** @deprecated */
   @Deprecated
   public ZipFile(File file, String encoding, boolean useUnicodeExtraFields, boolean ignoreLocalFileHeader) throws IOException {
      this(newReadByteChannel(file.toPath()), file.getAbsolutePath(), encoding, useUnicodeExtraFields, true, ignoreLocalFileHeader);
   }

   /** @deprecated */
   @Deprecated
   public ZipFile(Path path) throws IOException {
      this(path, DEFAULT_CHARSET_NAME);
   }

   /** @deprecated */
   @Deprecated
   public ZipFile(Path path, String encoding) throws IOException {
      this(path, encoding, true);
   }

   /** @deprecated */
   @Deprecated
   public ZipFile(Path path, String encoding, boolean useUnicodeExtraFields) throws IOException {
      this(path, encoding, useUnicodeExtraFields, false);
   }

   /** @deprecated */
   @Deprecated
   public ZipFile(Path path, String encoding, boolean useUnicodeExtraFields, boolean ignoreLocalFileHeader) throws IOException {
      this(newReadByteChannel(path), path.toAbsolutePath().toString(), encoding, useUnicodeExtraFields, true, ignoreLocalFileHeader);
   }

   /** @deprecated */
   @Deprecated
   public ZipFile(SeekableByteChannel channel) throws IOException {
      this(channel, "a SeekableByteChannel", DEFAULT_CHARSET_NAME, true);
   }

   /** @deprecated */
   @Deprecated
   public ZipFile(SeekableByteChannel channel, String encoding) throws IOException {
      this(channel, "a SeekableByteChannel", encoding, true);
   }

   private ZipFile(SeekableByteChannel channel, String channelDescription, Charset encoding, boolean useUnicodeExtraFields, boolean closeOnError, boolean ignoreLocalFileHeader) throws IOException {
      this.entries = new LinkedList();
      this.nameMap = new HashMap(509);
      this.closed = true;
      this.dwordBuf = new byte[8];
      this.wordBuf = new byte[4];
      this.cfhBuf = new byte[42];
      this.shortBuf = new byte[2];
      this.dwordBbuf = ByteBuffer.wrap(this.dwordBuf);
      this.wordBbuf = ByteBuffer.wrap(this.wordBuf);
      this.cfhBbuf = ByteBuffer.wrap(this.cfhBuf);
      this.shortBbuf = ByteBuffer.wrap(this.shortBuf);
      this.isSplitZipArchive = channel instanceof ZipSplitReadOnlySeekableByteChannel;
      this.encoding = Charsets.toCharset(encoding, ZipFile.Builder.DEFAULT_CHARSET);
      this.zipEncoding = ZipEncodingHelper.getZipEncoding(encoding);
      this.useUnicodeExtraFields = useUnicodeExtraFields;
      this.archive = channel;
      boolean success = false;

      try {
         Map<ZipArchiveEntry, NameAndComment> entriesWithoutUTF8Flag = this.populateFromCentralDirectory();
         if (!ignoreLocalFileHeader) {
            this.resolveLocalFileHeaderData(entriesWithoutUTF8Flag);
         }

         this.fillNameMap();
         success = true;
      } catch (IOException e) {
         throw new IOException("Error reading Zip content from " + channelDescription, e);
      } finally {
         this.closed = !success;
         if (!success && closeOnError) {
            IOUtils.closeQuietly(this.archive);
         }

      }

   }

   /** @deprecated */
   @Deprecated
   public ZipFile(SeekableByteChannel channel, String channelDescription, String encoding, boolean useUnicodeExtraFields) throws IOException {
      this(channel, channelDescription, encoding, useUnicodeExtraFields, false, false);
   }

   /** @deprecated */
   @Deprecated
   public ZipFile(SeekableByteChannel channel, String channelDescription, String encoding, boolean useUnicodeExtraFields, boolean ignoreLocalFileHeader) throws IOException {
      this(channel, channelDescription, encoding, useUnicodeExtraFields, false, ignoreLocalFileHeader);
   }

   private ZipFile(SeekableByteChannel channel, String channelDescription, String encoding, boolean useUnicodeExtraFields, boolean closeOnError, boolean ignoreLocalFileHeader) throws IOException {
      this(channel, channelDescription, Charsets.toCharset(encoding), useUnicodeExtraFields, closeOnError, ignoreLocalFileHeader);
   }

   /** @deprecated */
   @Deprecated
   public ZipFile(String name) throws IOException {
      this((new File(name)).toPath(), DEFAULT_CHARSET_NAME);
   }

   /** @deprecated */
   @Deprecated
   public ZipFile(String name, String encoding) throws IOException {
      this((new File(name)).toPath(), encoding, true);
   }

   public boolean canReadEntryData(ZipArchiveEntry entry) {
      return ZipUtil.canHandleEntryData(entry);
   }

   public void close() throws IOException {
      this.closed = true;
      this.archive.close();
   }

   public void copyRawEntries(ZipArchiveOutputStream target, ZipArchiveEntryPredicate predicate) throws IOException {
      Enumeration<ZipArchiveEntry> src = this.getEntriesInPhysicalOrder();

      while(src.hasMoreElements()) {
         ZipArchiveEntry entry = (ZipArchiveEntry)src.nextElement();
         if (predicate.test(entry)) {
            target.addRawArchiveEntry(entry, this.getRawInputStream(entry));
         }
      }

   }

   private BoundedArchiveInputStream createBoundedInputStream(long start, long remaining) {
      if (start >= 0L && remaining >= 0L && start + remaining >= start) {
         return (BoundedArchiveInputStream)(this.archive instanceof FileChannel ? new BoundedFileChannelInputStream(start, remaining, (FileChannel)this.archive) : new BoundedSeekableByteChannelInputStream(start, remaining, this.archive));
      } else {
         throw new IllegalArgumentException("Corrupted archive, stream boundaries are out of range");
      }
   }

   private void fillNameMap() {
      this.entries.forEach((ze) -> {
         String name = ze.getName();
         LinkedList<ZipArchiveEntry> entriesOfThatName = (LinkedList)this.nameMap.computeIfAbsent(name, (k) -> new LinkedList());
         entriesOfThatName.addLast(ze);
      });
   }

   protected void finalize() throws Throwable {
      try {
         if (!this.closed) {
            this.close();
         }
      } finally {
         super.finalize();
      }

   }

   public InputStream getContentBeforeFirstLocalFileHeader() {
      return this.firstLocalFileHeaderOffset == 0L ? null : this.createBoundedInputStream(0L, this.firstLocalFileHeaderOffset);
   }

   private long getDataOffset(ZipArchiveEntry ze) throws IOException {
      long s = ze.getDataOffset();
      if (s == -1L) {
         this.setDataOffset(ze);
         return ze.getDataOffset();
      } else {
         return s;
      }
   }

   public String getEncoding() {
      return this.encoding.name();
   }

   public Enumeration getEntries() {
      return Collections.enumeration(this.entries);
   }

   public Iterable getEntries(String name) {
      return (Iterable)this.nameMap.getOrDefault(name, ZipArchiveEntry.EMPTY_LINKED_LIST);
   }

   public Enumeration getEntriesInPhysicalOrder() {
      ZipArchiveEntry[] allEntries = (ZipArchiveEntry[])this.entries.toArray(ZipArchiveEntry.EMPTY_ARRAY);
      return Collections.enumeration(Arrays.asList(this.sortByOffset(allEntries)));
   }

   public Iterable getEntriesInPhysicalOrder(String name) {
      LinkedList<ZipArchiveEntry> linkedList = (LinkedList)this.nameMap.getOrDefault(name, ZipArchiveEntry.EMPTY_LINKED_LIST);
      return Arrays.asList(this.sortByOffset((ZipArchiveEntry[])linkedList.toArray(ZipArchiveEntry.EMPTY_ARRAY)));
   }

   public ZipArchiveEntry getEntry(String name) {
      LinkedList<ZipArchiveEntry> entries = (LinkedList)this.nameMap.get(name);
      return entries != null ? (ZipArchiveEntry)entries.getFirst() : null;
   }

   public long getFirstLocalFileHeaderOffset() {
      return this.firstLocalFileHeaderOffset;
   }

   public InputStream getInputStream(ZipArchiveEntry entry) throws IOException {
      if (!(entry instanceof Entry)) {
         return null;
      } else {
         ZipUtil.checkRequestedFeatures(entry);
         InputStream is = new BufferedInputStream(this.getRawInputStream(entry));
         switch (ZipMethod.getMethodByCode(entry.getMethod())) {
            case STORED:
               return new StoredStatisticsStream(is);
            case UNSHRINKING:
               return new UnshrinkingInputStream(is);
            case IMPLODING:
               try {
                  return new ExplodingInputStream(entry.getGeneralPurposeBit().getSlidingDictionarySize(), entry.getGeneralPurposeBit().getNumberOfShannonFanoTrees(), is);
               } catch (IllegalArgumentException ex) {
                  throw new IOException("bad IMPLODE data", ex);
               }
            case DEFLATED:
               final Inflater inflater = new Inflater(true);
               return new InflaterInputStreamWithStatistics(new SequenceInputStream(is, new ByteArrayInputStream(ONE_ZERO_BYTE)), inflater) {
                  public void close() throws IOException {
                     try {
                        super.close();
                     } finally {
                        inflater.end();
                     }

                  }
               };
            case BZIP2:
               return new BZip2CompressorInputStream(is);
            case ENHANCED_DEFLATED:
               return new Deflate64CompressorInputStream(is);
            case AES_ENCRYPTED:
            case EXPANDING_LEVEL_1:
            case EXPANDING_LEVEL_2:
            case EXPANDING_LEVEL_3:
            case EXPANDING_LEVEL_4:
            case JPEG:
            case LZMA:
            case PKWARE_IMPLODING:
            case PPMD:
            case TOKENIZATION:
            case UNKNOWN:
            case WAVPACK:
            case XZ:
            default:
               throw new UnsupportedZipFeatureException(ZipMethod.getMethodByCode(entry.getMethod()), entry);
         }
      }
   }

   public InputStream getRawInputStream(ZipArchiveEntry entry) throws IOException {
      if (!(entry instanceof Entry)) {
         return null;
      } else {
         long start = this.getDataOffset(entry);
         return start == -1L ? null : this.createBoundedInputStream(start, entry.getCompressedSize());
      }
   }

   public String getUnixSymlink(ZipArchiveEntry entry) throws IOException {
      if (entry != null && entry.isUnixSymlink()) {
         InputStream in = this.getInputStream(entry);

         String var3;
         try {
            var3 = this.zipEncoding.decode(IOUtils.toByteArray(in));
         } catch (Throwable var6) {
            if (in != null) {
               try {
                  in.close();
               } catch (Throwable var5) {
                  var6.addSuppressed(var5);
               }
            }

            throw var6;
         }

         if (in != null) {
            in.close();
         }

         return var3;
      } else {
         return null;
      }
   }

   private Map populateFromCentralDirectory() throws IOException {
      HashMap<ZipArchiveEntry, NameAndComment> noUTF8Flag = new HashMap();
      this.positionAtCentralDirectory();
      this.centralDirectoryStartOffset = this.archive.position();
      this.wordBbuf.rewind();
      org.apache.commons.compress.utils.IOUtils.readFully((ReadableByteChannel)this.archive, (ByteBuffer)this.wordBbuf);
      long sig = ZipLong.getValue(this.wordBuf);
      if (sig != CFH_SIG && this.startsWithLocalFileHeader()) {
         throw new IOException("Central directory is empty, can't expand corrupt archive.");
      } else {
         while(sig == CFH_SIG) {
            this.readCentralDirectoryEntry(noUTF8Flag);
            this.wordBbuf.rewind();
            org.apache.commons.compress.utils.IOUtils.readFully((ReadableByteChannel)this.archive, (ByteBuffer)this.wordBbuf);
            sig = ZipLong.getValue(this.wordBuf);
         }

         return noUTF8Flag;
      }
   }

   private void positionAtCentralDirectory() throws IOException {
      boolean is64 = positionAtEndOfCentralDirectoryRecord(this.archive);
      if (!is64) {
         this.positionAtCentralDirectory32();
      } else {
         this.positionAtCentralDirectory64();
      }

   }

   private void positionAtCentralDirectory32() throws IOException {
      long endOfCentralDirectoryRecordOffset = this.archive.position();
      if (this.isSplitZipArchive) {
         this.skipBytes(6);
         this.shortBbuf.rewind();
         org.apache.commons.compress.utils.IOUtils.readFully((ReadableByteChannel)this.archive, (ByteBuffer)this.shortBbuf);
         this.centralDirectoryStartDiskNumber = (long)ZipShort.getValue(this.shortBuf);
         this.skipBytes(8);
         this.wordBbuf.rewind();
         org.apache.commons.compress.utils.IOUtils.readFully((ReadableByteChannel)this.archive, (ByteBuffer)this.wordBbuf);
         this.centralDirectoryStartRelativeOffset = ZipLong.getValue(this.wordBuf);
         ((ZipSplitReadOnlySeekableByteChannel)this.archive).position(this.centralDirectoryStartDiskNumber, this.centralDirectoryStartRelativeOffset);
      } else {
         this.skipBytes(12);
         this.wordBbuf.rewind();
         org.apache.commons.compress.utils.IOUtils.readFully((ReadableByteChannel)this.archive, (ByteBuffer)this.wordBbuf);
         long centralDirectoryLength = ZipLong.getValue(this.wordBuf);
         this.wordBbuf.rewind();
         org.apache.commons.compress.utils.IOUtils.readFully((ReadableByteChannel)this.archive, (ByteBuffer)this.wordBbuf);
         this.centralDirectoryStartDiskNumber = 0L;
         this.centralDirectoryStartRelativeOffset = ZipLong.getValue(this.wordBuf);
         this.firstLocalFileHeaderOffset = Long.max(endOfCentralDirectoryRecordOffset - centralDirectoryLength - this.centralDirectoryStartRelativeOffset, 0L);
         this.archive.position(this.centralDirectoryStartRelativeOffset + this.firstLocalFileHeaderOffset);
      }

   }

   private void positionAtCentralDirectory64() throws IOException {
      this.skipBytes(4);
      if (this.isSplitZipArchive) {
         this.wordBbuf.rewind();
         org.apache.commons.compress.utils.IOUtils.readFully((ReadableByteChannel)this.archive, (ByteBuffer)this.wordBbuf);
         long diskNumberOfEOCD = ZipLong.getValue(this.wordBuf);
         this.dwordBbuf.rewind();
         org.apache.commons.compress.utils.IOUtils.readFully((ReadableByteChannel)this.archive, (ByteBuffer)this.dwordBbuf);
         long relativeOffsetOfEOCD = ZipEightByteInteger.getLongValue(this.dwordBuf);
         ((ZipSplitReadOnlySeekableByteChannel)this.archive).position(diskNumberOfEOCD, relativeOffsetOfEOCD);
      } else {
         this.skipBytes(4);
         this.dwordBbuf.rewind();
         org.apache.commons.compress.utils.IOUtils.readFully((ReadableByteChannel)this.archive, (ByteBuffer)this.dwordBbuf);
         this.archive.position(ZipEightByteInteger.getLongValue(this.dwordBuf));
      }

      this.wordBbuf.rewind();
      org.apache.commons.compress.utils.IOUtils.readFully((ReadableByteChannel)this.archive, (ByteBuffer)this.wordBbuf);
      if (!Arrays.equals(this.wordBuf, ZipArchiveOutputStream.ZIP64_EOCD_SIG)) {
         throw new ZipException("Archive's ZIP64 end of central directory locator is corrupt.");
      } else {
         if (this.isSplitZipArchive) {
            this.skipBytes(16);
            this.wordBbuf.rewind();
            org.apache.commons.compress.utils.IOUtils.readFully((ReadableByteChannel)this.archive, (ByteBuffer)this.wordBbuf);
            this.centralDirectoryStartDiskNumber = ZipLong.getValue(this.wordBuf);
            this.skipBytes(24);
            this.dwordBbuf.rewind();
            org.apache.commons.compress.utils.IOUtils.readFully((ReadableByteChannel)this.archive, (ByteBuffer)this.dwordBbuf);
            this.centralDirectoryStartRelativeOffset = ZipEightByteInteger.getLongValue(this.dwordBuf);
            ((ZipSplitReadOnlySeekableByteChannel)this.archive).position(this.centralDirectoryStartDiskNumber, this.centralDirectoryStartRelativeOffset);
         } else {
            this.skipBytes(44);
            this.dwordBbuf.rewind();
            org.apache.commons.compress.utils.IOUtils.readFully((ReadableByteChannel)this.archive, (ByteBuffer)this.dwordBbuf);
            this.centralDirectoryStartDiskNumber = 0L;
            this.centralDirectoryStartRelativeOffset = ZipEightByteInteger.getLongValue(this.dwordBuf);
            this.archive.position(this.centralDirectoryStartRelativeOffset);
         }

      }
   }

   private void readCentralDirectoryEntry(Map noUTF8Flag) throws IOException {
      this.cfhBbuf.rewind();
      org.apache.commons.compress.utils.IOUtils.readFully((ReadableByteChannel)this.archive, (ByteBuffer)this.cfhBbuf);
      int off = 0;
      Entry ze = new Entry();
      int versionMadeBy = ZipShort.getValue(this.cfhBuf, off);
      off += 2;
      ze.setVersionMadeBy(versionMadeBy);
      ze.setPlatform(versionMadeBy >> 8 & 15);
      ze.setVersionRequired(ZipShort.getValue(this.cfhBuf, off));
      off += 2;
      GeneralPurposeBit gpFlag = GeneralPurposeBit.parse(this.cfhBuf, off);
      boolean hasUTF8Flag = gpFlag.usesUTF8ForNames();
      ZipEncoding entryEncoding = hasUTF8Flag ? ZipEncodingHelper.ZIP_ENCODING_UTF_8 : this.zipEncoding;
      if (hasUTF8Flag) {
         ze.setNameSource(ZipArchiveEntry.NameSource.NAME_WITH_EFS_FLAG);
      }

      ze.setGeneralPurposeBit(gpFlag);
      ze.setRawFlag(ZipShort.getValue(this.cfhBuf, off));
      off += 2;
      ze.setMethod(ZipShort.getValue(this.cfhBuf, off));
      off += 2;
      long time = ZipUtil.dosToJavaTime(ZipLong.getValue(this.cfhBuf, off));
      ze.setTime(time);
      off += 4;
      ze.setCrc(ZipLong.getValue(this.cfhBuf, off));
      off += 4;
      long size = ZipLong.getValue(this.cfhBuf, off);
      if (size < 0L) {
         throw new IOException("broken archive, entry with negative compressed size");
      } else {
         ze.setCompressedSize(size);
         off += 4;
         size = ZipLong.getValue(this.cfhBuf, off);
         if (size < 0L) {
            throw new IOException("broken archive, entry with negative size");
         } else {
            ze.setSize(size);
            off += 4;
            int fileNameLen = ZipShort.getValue(this.cfhBuf, off);
            off += 2;
            if (fileNameLen < 0) {
               throw new IOException("broken archive, entry with negative fileNameLen");
            } else {
               int extraLen = ZipShort.getValue(this.cfhBuf, off);
               off += 2;
               if (extraLen < 0) {
                  throw new IOException("broken archive, entry with negative extraLen");
               } else {
                  int commentLen = ZipShort.getValue(this.cfhBuf, off);
                  off += 2;
                  if (commentLen < 0) {
                     throw new IOException("broken archive, entry with negative commentLen");
                  } else {
                     ze.setDiskNumberStart((long)ZipShort.getValue(this.cfhBuf, off));
                     off += 2;
                     ze.setInternalAttributes(ZipShort.getValue(this.cfhBuf, off));
                     off += 2;
                     ze.setExternalAttributes(ZipLong.getValue(this.cfhBuf, off));
                     off += 4;
                     byte[] fileName = org.apache.commons.compress.utils.IOUtils.readRange((ReadableByteChannel)this.archive, fileNameLen);
                     if (fileName.length < fileNameLen) {
                        throw new EOFException();
                     } else {
                        ze.setName(entryEncoding.decode(fileName), fileName);
                        ze.setLocalHeaderOffset(ZipLong.getValue(this.cfhBuf, off) + this.firstLocalFileHeaderOffset);
                        this.entries.add(ze);
                        byte[] cdExtraData = org.apache.commons.compress.utils.IOUtils.readRange((ReadableByteChannel)this.archive, extraLen);
                        if (cdExtraData.length < extraLen) {
                           throw new EOFException();
                        } else {
                           try {
                              ze.setCentralDirectoryExtra(cdExtraData);
                           } catch (RuntimeException e) {
                              ZipException z = new ZipException("Invalid extra data in entry " + ze.getName());
                              z.initCause(e);
                              throw z;
                           }

                           this.setSizesAndOffsetFromZip64Extra(ze);
                           this.sanityCheckLFHOffset(ze);
                           byte[] comment = org.apache.commons.compress.utils.IOUtils.readRange((ReadableByteChannel)this.archive, commentLen);
                           if (comment.length < commentLen) {
                              throw new EOFException();
                           } else {
                              ze.setComment(entryEncoding.decode(comment));
                              if (!hasUTF8Flag && this.useUnicodeExtraFields) {
                                 noUTF8Flag.put(ze, new NameAndComment(fileName, comment));
                              }

                              ze.setStreamContiguous(true);
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private void resolveLocalFileHeaderData(Map entriesWithoutUTF8Flag) throws IOException {
      for(ZipArchiveEntry zipArchiveEntry : this.entries) {
         Entry ze = (Entry)zipArchiveEntry;
         int[] lens = this.setDataOffset(ze);
         int fileNameLen = lens[0];
         int extraFieldLen = lens[1];
         this.skipBytes(fileNameLen);
         byte[] localExtraData = org.apache.commons.compress.utils.IOUtils.readRange((ReadableByteChannel)this.archive, extraFieldLen);
         if (localExtraData.length < extraFieldLen) {
            throw new EOFException();
         }

         try {
            ze.setExtra(localExtraData);
         } catch (RuntimeException e) {
            ZipException z = new ZipException("Invalid extra data in entry " + ze.getName());
            z.initCause(e);
            throw z;
         }

         if (entriesWithoutUTF8Flag.containsKey(ze)) {
            NameAndComment nc = (NameAndComment)entriesWithoutUTF8Flag.get(ze);
            ZipUtil.setNameAndCommentFromExtraFields(ze, nc.name, nc.comment);
         }
      }

   }

   private void sanityCheckLFHOffset(ZipArchiveEntry entry) throws IOException {
      if (entry.getDiskNumberStart() < 0L) {
         throw new IOException("broken archive, entry with negative disk number");
      } else if (entry.getLocalHeaderOffset() < 0L) {
         throw new IOException("broken archive, entry with negative local file header offset");
      } else {
         if (this.isSplitZipArchive) {
            if (entry.getDiskNumberStart() > this.centralDirectoryStartDiskNumber) {
               throw new IOException("local file header for " + entry.getName() + " starts on a later disk than central directory");
            }

            if (entry.getDiskNumberStart() == this.centralDirectoryStartDiskNumber && entry.getLocalHeaderOffset() > this.centralDirectoryStartRelativeOffset) {
               throw new IOException("local file header for " + entry.getName() + " starts after central directory");
            }
         } else if (entry.getLocalHeaderOffset() > this.centralDirectoryStartOffset) {
            throw new IOException("local file header for " + entry.getName() + " starts after central directory");
         }

      }
   }

   private int[] setDataOffset(ZipArchiveEntry entry) throws IOException {
      long offset = entry.getLocalHeaderOffset();
      if (this.isSplitZipArchive) {
         ((ZipSplitReadOnlySeekableByteChannel)this.archive).position(entry.getDiskNumberStart(), offset + 26L);
         offset = this.archive.position() - 26L;
      } else {
         this.archive.position(offset + 26L);
      }

      this.wordBbuf.rewind();
      org.apache.commons.compress.utils.IOUtils.readFully((ReadableByteChannel)this.archive, (ByteBuffer)this.wordBbuf);
      this.wordBbuf.flip();
      this.wordBbuf.get(this.shortBuf);
      int fileNameLen = ZipShort.getValue(this.shortBuf);
      this.wordBbuf.get(this.shortBuf);
      int extraFieldLen = ZipShort.getValue(this.shortBuf);
      entry.setDataOffset(offset + 26L + 2L + 2L + (long)fileNameLen + (long)extraFieldLen);
      if (entry.getDataOffset() + entry.getCompressedSize() > this.centralDirectoryStartOffset) {
         throw new IOException("data for " + entry.getName() + " overlaps with central directory.");
      } else {
         return new int[]{fileNameLen, extraFieldLen};
      }
   }

   private void setSizesAndOffsetFromZip64Extra(ZipArchiveEntry entry) throws IOException {
      ZipExtraField extra = entry.getExtraField(Zip64ExtendedInformationExtraField.HEADER_ID);
      if (extra != null && !(extra instanceof Zip64ExtendedInformationExtraField)) {
         throw new ZipException("archive contains unparseable zip64 extra field");
      } else {
         Zip64ExtendedInformationExtraField z64 = (Zip64ExtendedInformationExtraField)extra;
         if (z64 != null) {
            boolean hasUncompressedSize = entry.getSize() == 4294967295L;
            boolean hasCompressedSize = entry.getCompressedSize() == 4294967295L;
            boolean hasRelativeHeaderOffset = entry.getLocalHeaderOffset() == 4294967295L;
            boolean hasDiskStart = entry.getDiskNumberStart() == 65535L;
            z64.reparseCentralDirectoryData(hasUncompressedSize, hasCompressedSize, hasRelativeHeaderOffset, hasDiskStart);
            if (hasUncompressedSize) {
               long size = z64.getSize().getLongValue();
               if (size < 0L) {
                  throw new IOException("broken archive, entry with negative size");
               }

               entry.setSize(size);
            } else if (hasCompressedSize) {
               z64.setSize(new ZipEightByteInteger(entry.getSize()));
            }

            if (hasCompressedSize) {
               long size = z64.getCompressedSize().getLongValue();
               if (size < 0L) {
                  throw new IOException("broken archive, entry with negative compressed size");
               }

               entry.setCompressedSize(size);
            } else if (hasUncompressedSize) {
               z64.setCompressedSize(new ZipEightByteInteger(entry.getCompressedSize()));
            }

            if (hasRelativeHeaderOffset) {
               entry.setLocalHeaderOffset(z64.getRelativeHeaderOffset().getLongValue());
            }

            if (hasDiskStart) {
               entry.setDiskNumberStart(z64.getDiskStartNumber().getValue());
            }
         }

      }
   }

   private void skipBytes(int count) throws IOException {
      long currentPosition = this.archive.position();
      long newPosition = currentPosition + (long)count;
      if (newPosition > this.archive.size()) {
         throw new EOFException();
      } else {
         this.archive.position(newPosition);
      }
   }

   private ZipArchiveEntry[] sortByOffset(ZipArchiveEntry[] allEntries) {
      Arrays.sort(allEntries, offsetComparator);
      return allEntries;
   }

   private boolean startsWithLocalFileHeader() throws IOException {
      this.archive.position(this.firstLocalFileHeaderOffset);
      this.wordBbuf.rewind();
      org.apache.commons.compress.utils.IOUtils.readFully((ReadableByteChannel)this.archive, (ByteBuffer)this.wordBbuf);
      return Arrays.equals(this.wordBuf, ZipArchiveOutputStream.LFH_SIG);
   }

   static {
      DEFAULT_CHARSET_NAME = StandardCharsets.UTF_8.name();
      READ = EnumSet.of(StandardOpenOption.READ);
      ONE_ZERO_BYTE = new byte[1];
      CFH_SIG = ZipLong.getValue(ZipArchiveOutputStream.CFH_SIG);
      offsetComparator = Comparator.comparingLong(ZipArchiveEntry::getDiskNumberStart).thenComparingLong(ZipArchiveEntry::getLocalHeaderOffset);
   }

   private static class BoundedFileChannelInputStream extends BoundedArchiveInputStream {
      private final FileChannel archive;

      BoundedFileChannelInputStream(long start, long remaining, FileChannel archive) {
         super(start, remaining);
         this.archive = archive;
      }

      protected int read(long pos, ByteBuffer buf) throws IOException {
         int read = this.archive.read(buf, pos);
         buf.flip();
         return read;
      }
   }

   public static class Builder extends AbstractStreamBuilder {
      static final Charset DEFAULT_CHARSET;
      private SeekableByteChannel seekableByteChannel;
      private boolean useUnicodeExtraFields = true;
      private boolean ignoreLocalFileHeader;
      private long maxNumberOfDisks = 1L;

      public Builder() {
         this.setCharset(DEFAULT_CHARSET);
         this.setCharsetDefault(DEFAULT_CHARSET);
      }

      public ZipFile get() throws IOException {
         SeekableByteChannel actualChannel;
         String actualDescription;
         if (this.seekableByteChannel != null) {
            actualChannel = this.seekableByteChannel;
            actualDescription = actualChannel.getClass().getSimpleName();
         } else if (this.checkOrigin() instanceof AbstractOrigin.ByteArrayOrigin) {
            actualChannel = new SeekableInMemoryByteChannel(this.checkOrigin().getByteArray());
            actualDescription = actualChannel.getClass().getSimpleName();
         } else {
            OpenOption[] openOptions = this.getOpenOptions();
            if (openOptions.length == 0) {
               openOptions = new OpenOption[]{StandardOpenOption.READ};
            }

            Path path = this.getPath();
            actualChannel = ZipFile.openZipChannel(path, this.maxNumberOfDisks, openOptions);
            actualDescription = path.toString();
         }

         boolean closeOnError = this.seekableByteChannel != null;
         return new ZipFile(actualChannel, actualDescription, this.getCharset(), this.useUnicodeExtraFields, closeOnError, this.ignoreLocalFileHeader);
      }

      public Builder setIgnoreLocalFileHeader(boolean ignoreLocalFileHeader) {
         this.ignoreLocalFileHeader = ignoreLocalFileHeader;
         return this;
      }

      public Builder setMaxNumberOfDisks(long maxNumberOfDisks) {
         this.maxNumberOfDisks = maxNumberOfDisks;
         return this;
      }

      public Builder setSeekableByteChannel(SeekableByteChannel seekableByteChannel) {
         this.seekableByteChannel = seekableByteChannel;
         return this;
      }

      public Builder setUseUnicodeExtraFields(boolean useUnicodeExtraFields) {
         this.useUnicodeExtraFields = useUnicodeExtraFields;
         return this;
      }

      static {
         DEFAULT_CHARSET = StandardCharsets.UTF_8;
      }
   }

   private static final class Entry extends ZipArchiveEntry {
      private Entry() {
      }

      public boolean equals(Object other) {
         if (!super.equals(other)) {
            return false;
         } else {
            Entry otherEntry = (Entry)other;
            return this.getLocalHeaderOffset() == otherEntry.getLocalHeaderOffset() && super.getDataOffset() == otherEntry.getDataOffset() && super.getDiskNumberStart() == otherEntry.getDiskNumberStart();
         }
      }

      public int hashCode() {
         return 3 * super.hashCode() + (int)this.getLocalHeaderOffset() + (int)(this.getLocalHeaderOffset() >> 32);
      }
   }

   private static final class NameAndComment {
      private final byte[] name;
      private final byte[] comment;

      private NameAndComment(byte[] name, byte[] comment) {
         this.name = name;
         this.comment = comment;
      }
   }

   private static final class StoredStatisticsStream extends BoundedInputStream implements InputStreamStatistics {
      StoredStatisticsStream(InputStream in) {
         super(in);
      }

      public long getCompressedCount() {
         return super.getCount();
      }

      public long getUncompressedCount() {
         return this.getCompressedCount();
      }
   }
}
