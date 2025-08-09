package org.apache.commons.compress.archivers.tar;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.apache.commons.compress.utils.FixedLengthBlockOutputStream;
import org.apache.commons.compress.utils.TimeUtils;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.file.attribute.FileTimes;
import org.apache.commons.io.output.CountingOutputStream;
import org.apache.commons.lang3.ArrayFill;

public class TarArchiveOutputStream extends ArchiveOutputStream {
   public static final int LONGFILE_ERROR = 0;
   public static final int LONGFILE_TRUNCATE = 1;
   public static final int LONGFILE_GNU = 2;
   public static final int LONGFILE_POSIX = 3;
   public static final int BIGNUMBER_ERROR = 0;
   public static final int BIGNUMBER_STAR = 1;
   public static final int BIGNUMBER_POSIX = 2;
   private static final int RECORD_SIZE = 512;
   private static final ZipEncoding ASCII;
   private static final int BLOCK_SIZE_UNSPECIFIED = -511;
   private long currSize;
   private String currName;
   private long currBytes;
   private final byte[] recordBuf;
   private int longFileMode;
   private int bigNumberMode;
   private long recordsWritten;
   private final int recordsPerBlock;
   private boolean haveUnclosedEntry;
   private final CountingOutputStream countingOut;
   private final ZipEncoding zipEncoding;
   final String charsetName;
   private boolean addPaxHeadersForNonAsciiNames;

   public TarArchiveOutputStream(OutputStream os) {
      this(os, -511);
   }

   public TarArchiveOutputStream(OutputStream os, int blockSize) {
      this(os, blockSize, (String)null);
   }

   /** @deprecated */
   @Deprecated
   public TarArchiveOutputStream(OutputStream os, int blockSize, int recordSize) {
      this(os, blockSize, recordSize, (String)null);
   }

   /** @deprecated */
   @Deprecated
   public TarArchiveOutputStream(OutputStream os, int blockSize, int recordSize, String encoding) {
      this(os, blockSize, encoding);
      if (recordSize != 512) {
         throw new IllegalArgumentException("Tar record size must always be 512 bytes. Attempt to set size of " + recordSize);
      }
   }

   public TarArchiveOutputStream(OutputStream os, int blockSize, String encoding) {
      super(os);
      this.longFileMode = 0;
      this.bigNumberMode = 0;
      int realBlockSize;
      if (-511 == blockSize) {
         realBlockSize = 512;
      } else {
         realBlockSize = blockSize;
      }

      if (realBlockSize > 0 && realBlockSize % 512 == 0) {
         this.out = new FixedLengthBlockOutputStream(this.countingOut = new CountingOutputStream(os), 512);
         this.charsetName = Charsets.toCharset(encoding).name();
         this.zipEncoding = ZipEncodingHelper.getZipEncoding(encoding);
         this.recordBuf = new byte[512];
         this.recordsPerBlock = realBlockSize / 512;
      } else {
         throw new IllegalArgumentException("Block size must be a multiple of 512 bytes. Attempt to use set size of " + blockSize);
      }
   }

   public TarArchiveOutputStream(OutputStream os, String encoding) {
      this(os, -511, encoding);
   }

   private void addFileTimePaxHeader(Map paxHeaders, String header, FileTime value) {
      if (value != null) {
         Instant instant = value.toInstant();
         long seconds = instant.getEpochSecond();
         int nanos = instant.getNano();
         if (nanos == 0) {
            paxHeaders.put(header, String.valueOf(seconds));
         } else {
            this.addInstantPaxHeader(paxHeaders, header, seconds, nanos);
         }
      }

   }

   private void addFileTimePaxHeaderForBigNumber(Map paxHeaders, String header, FileTime value, long maxValue) {
      if (value != null) {
         Instant instant = value.toInstant();
         long seconds = instant.getEpochSecond();
         int nanos = instant.getNano();
         if (nanos == 0) {
            this.addPaxHeaderForBigNumber(paxHeaders, header, seconds, maxValue);
         } else {
            this.addInstantPaxHeader(paxHeaders, header, seconds, nanos);
         }
      }

   }

   private void addInstantPaxHeader(Map paxHeaders, String header, long seconds, int nanos) {
      BigDecimal bdSeconds = BigDecimal.valueOf(seconds);
      BigDecimal bdNanos = BigDecimal.valueOf((long)nanos).movePointLeft(9).setScale(7, RoundingMode.DOWN);
      BigDecimal timestamp = bdSeconds.add(bdNanos);
      paxHeaders.put(header, timestamp.toPlainString());
   }

   private void addPaxHeaderForBigNumber(Map paxHeaders, String header, long value, long maxValue) {
      if (value < 0L || value > maxValue) {
         paxHeaders.put(header, String.valueOf(value));
      }

   }

   private void addPaxHeadersForBigNumbers(Map paxHeaders, TarArchiveEntry entry) {
      this.addPaxHeaderForBigNumber(paxHeaders, "size", entry.getSize(), 8589934591L);
      this.addPaxHeaderForBigNumber(paxHeaders, "gid", entry.getLongGroupId(), 2097151L);
      this.addFileTimePaxHeaderForBigNumber(paxHeaders, "mtime", entry.getLastModifiedTime(), 8589934591L);
      this.addFileTimePaxHeader(paxHeaders, "atime", entry.getLastAccessTime());
      if (entry.getStatusChangeTime() != null) {
         this.addFileTimePaxHeader(paxHeaders, "ctime", entry.getStatusChangeTime());
      } else {
         this.addFileTimePaxHeader(paxHeaders, "ctime", entry.getCreationTime());
      }

      this.addPaxHeaderForBigNumber(paxHeaders, "uid", entry.getLongUserId(), 2097151L);
      this.addFileTimePaxHeader(paxHeaders, "LIBARCHIVE.creationtime", entry.getCreationTime());
      this.addPaxHeaderForBigNumber(paxHeaders, "SCHILY.devmajor", (long)entry.getDevMajor(), 2097151L);
      this.addPaxHeaderForBigNumber(paxHeaders, "SCHILY.devminor", (long)entry.getDevMinor(), 2097151L);
      this.failForBigNumber("mode", (long)entry.getMode(), 2097151L);
   }

   public void close() throws IOException {
      try {
         if (!this.isFinished()) {
            this.finish();
         }
      } finally {
         if (!this.isClosed()) {
            super.close();
         }

      }

   }

   public void closeArchiveEntry() throws IOException {
      this.checkFinished();
      if (!this.haveUnclosedEntry) {
         throw new IOException("No current entry to close");
      } else {
         ((FixedLengthBlockOutputStream)this.out).flushBlock();
         if (this.currBytes < this.currSize) {
            throw new IOException("Entry '" + this.currName + "' closed at '" + this.currBytes + "' before the '" + this.currSize + "' bytes specified in the header were written");
         } else {
            this.recordsWritten += this.currSize / 512L;
            if (0L != this.currSize % 512L) {
               ++this.recordsWritten;
            }

            this.haveUnclosedEntry = false;
         }
      }
   }

   public TarArchiveEntry createArchiveEntry(File inputFile, String entryName) throws IOException {
      this.checkFinished();
      return new TarArchiveEntry(inputFile, entryName);
   }

   public TarArchiveEntry createArchiveEntry(Path inputPath, String entryName, LinkOption... options) throws IOException {
      this.checkFinished();
      return new TarArchiveEntry(inputPath, entryName, options);
   }

   private byte[] encodeExtendedPaxHeadersContents(Map headers) {
      StringWriter w = new StringWriter();
      headers.forEach((k, v) -> {
         int len = k.length() + v.length() + 3 + 2;
         String line = len + " " + k + "=" + v + "\n";

         for(int actualLength = line.getBytes(StandardCharsets.UTF_8).length; len != actualLength; actualLength = line.getBytes(StandardCharsets.UTF_8).length) {
            len = actualLength;
            line = actualLength + " " + k + "=" + v + "\n";
         }

         w.write(line);
      });
      return w.toString().getBytes(StandardCharsets.UTF_8);
   }

   private void failForBigNumber(String field, long value, long maxValue) {
      this.failForBigNumber(field, value, maxValue, "");
   }

   private void failForBigNumber(String field, long value, long maxValue, String additionalMsg) {
      if (value < 0L || value > maxValue) {
         throw new IllegalArgumentException(field + " '" + value + "' is too big ( > " + maxValue + " )." + additionalMsg);
      }
   }

   private void failForBigNumbers(TarArchiveEntry entry) {
      this.failForBigNumber("entry size", entry.getSize(), 8589934591L);
      this.failForBigNumberWithPosixMessage("group id", entry.getLongGroupId(), 2097151L);
      this.failForBigNumber("last modification time", TimeUtils.toUnixTime(entry.getLastModifiedTime()), 8589934591L);
      this.failForBigNumber("user id", entry.getLongUserId(), 2097151L);
      this.failForBigNumber("mode", (long)entry.getMode(), 2097151L);
      this.failForBigNumber("major device number", (long)entry.getDevMajor(), 2097151L);
      this.failForBigNumber("minor device number", (long)entry.getDevMinor(), 2097151L);
   }

   private void failForBigNumberWithPosixMessage(String field, long value, long maxValue) {
      this.failForBigNumber(field, value, maxValue, " Use STAR or POSIX extensions to overcome this limit");
   }

   public void finish() throws IOException {
      this.checkFinished();
      if (this.haveUnclosedEntry) {
         throw new IOException("This archive contains unclosed entries.");
      } else {
         this.writeEOFRecord();
         this.writeEOFRecord();
         this.padAsNeeded();
         this.out.flush();
         super.finish();
      }
   }

   public long getBytesWritten() {
      return this.countingOut.getByteCount();
   }

   /** @deprecated */
   @Deprecated
   public int getCount() {
      return (int)this.getBytesWritten();
   }

   /** @deprecated */
   @Deprecated
   public int getRecordSize() {
      return 512;
   }

   private boolean handleLongName(TarArchiveEntry entry, String name, Map paxHeaders, String paxHeaderName, byte linkType, String fieldName) throws IOException {
      ByteBuffer encodedName = this.zipEncoding.encode(name);
      int len = encodedName.limit() - encodedName.position();
      if (len >= 100) {
         if (this.longFileMode == 3) {
            paxHeaders.put(paxHeaderName, name);
            return true;
         }

         if (this.longFileMode == 2) {
            TarArchiveEntry longLinkEntry = new TarArchiveEntry("././@LongLink", linkType);
            longLinkEntry.setSize((long)len + 1L);
            this.transferModTime(entry, longLinkEntry);
            this.putArchiveEntry(longLinkEntry);
            this.write(encodedName.array(), encodedName.arrayOffset(), len);
            this.write(0);
            this.closeArchiveEntry();
         } else if (this.longFileMode != 1) {
            throw new IllegalArgumentException(fieldName + " '" + name + "' is too long ( > " + 100 + " bytes)");
         }
      }

      return false;
   }

   private void padAsNeeded() throws IOException {
      int start = Math.toIntExact(this.recordsWritten % (long)this.recordsPerBlock);
      if (start != 0) {
         for(int i = start; i < this.recordsPerBlock; ++i) {
            this.writeEOFRecord();
         }
      }

   }

   public void putArchiveEntry(TarArchiveEntry archiveEntry) throws IOException {
      this.checkFinished();
      if (archiveEntry.isGlobalPaxHeader()) {
         byte[] data = this.encodeExtendedPaxHeadersContents(archiveEntry.getExtraPaxHeaders());
         archiveEntry.setSize((long)data.length);
         archiveEntry.writeEntryHeader(this.recordBuf, this.zipEncoding, this.bigNumberMode == 1);
         this.writeRecord(this.recordBuf);
         this.currSize = archiveEntry.getSize();
         this.currBytes = 0L;
         this.haveUnclosedEntry = true;
         this.write(data);
         this.closeArchiveEntry();
      } else {
         Map<String, String> paxHeaders = new HashMap();
         String entryName = archiveEntry.getName();
         boolean paxHeaderContainsPath = this.handleLongName(archiveEntry, entryName, paxHeaders, "path", (byte)76, "file name");
         String linkName = archiveEntry.getLinkName();
         boolean paxHeaderContainsLinkPath = linkName != null && !linkName.isEmpty() && this.handleLongName(archiveEntry, linkName, paxHeaders, "linkpath", (byte)75, "link name");
         if (this.bigNumberMode == 2) {
            this.addPaxHeadersForBigNumbers(paxHeaders, archiveEntry);
         } else if (this.bigNumberMode != 1) {
            this.failForBigNumbers(archiveEntry);
         }

         if (this.addPaxHeadersForNonAsciiNames && !paxHeaderContainsPath && !ASCII.canEncode(entryName)) {
            paxHeaders.put("path", entryName);
         }

         if (this.addPaxHeadersForNonAsciiNames && !paxHeaderContainsLinkPath && (archiveEntry.isLink() || archiveEntry.isSymbolicLink()) && !ASCII.canEncode(linkName)) {
            paxHeaders.put("linkpath", linkName);
         }

         paxHeaders.putAll(archiveEntry.getExtraPaxHeaders());
         if (!paxHeaders.isEmpty()) {
            this.writePaxHeaders(archiveEntry, entryName, paxHeaders);
         }

         archiveEntry.writeEntryHeader(this.recordBuf, this.zipEncoding, this.bigNumberMode == 1);
         this.writeRecord(this.recordBuf);
         this.currBytes = 0L;
         if (archiveEntry.isDirectory()) {
            this.currSize = 0L;
         } else {
            this.currSize = archiveEntry.getSize();
         }

         this.currName = entryName;
         this.haveUnclosedEntry = true;
      }

   }

   public void setAddPaxHeadersForNonAsciiNames(boolean b) {
      this.addPaxHeadersForNonAsciiNames = b;
   }

   public void setBigNumberMode(int bigNumberMode) {
      this.bigNumberMode = bigNumberMode;
   }

   public void setLongFileMode(int longFileMode) {
      this.longFileMode = longFileMode;
   }

   private boolean shouldBeReplaced(char c) {
      return c == 0 || c == '/' || c == '\\';
   }

   private String stripTo7Bits(String name) {
      int length = name.length();
      StringBuilder result = new StringBuilder(length);

      for(int i = 0; i < length; ++i) {
         char stripped = (char)(name.charAt(i) & 127);
         if (this.shouldBeReplaced(stripped)) {
            result.append("_");
         } else {
            result.append(stripped);
         }
      }

      return result.toString();
   }

   private void transferModTime(TarArchiveEntry from, TarArchiveEntry to) {
      long fromModTimeSeconds = TimeUtils.toUnixTime(from.getLastModifiedTime());
      if (fromModTimeSeconds < 0L || fromModTimeSeconds > 8589934591L) {
         fromModTimeSeconds = 0L;
      }

      to.setLastModifiedTime(FileTimes.fromUnixTime(fromModTimeSeconds));
   }

   public void write(byte[] wBuf, int wOffset, int numToWrite) throws IOException {
      if (!this.haveUnclosedEntry) {
         throw new IllegalStateException("No current tar entry");
      } else if (this.currBytes + (long)numToWrite > this.currSize) {
         throw new IOException("Request to write '" + numToWrite + "' bytes exceeds size in header of '" + this.currSize + "' bytes for entry '" + this.currName + "'");
      } else {
         this.out.write(wBuf, wOffset, numToWrite);
         this.currBytes += (long)numToWrite;
      }
   }

   private void writeEOFRecord() throws IOException {
      this.writeRecord(ArrayFill.fill(this.recordBuf, (byte)0));
   }

   void writePaxHeaders(TarArchiveEntry entry, String entryName, Map headers) throws IOException {
      String name = "./PaxHeaders.X/" + this.stripTo7Bits(entryName);
      if (name.length() >= 100) {
         name = name.substring(0, 99);
      }

      TarArchiveEntry pex = new TarArchiveEntry(name, (byte)120);
      this.transferModTime(entry, pex);
      byte[] data = this.encodeExtendedPaxHeadersContents(headers);
      pex.setSize((long)data.length);
      this.putArchiveEntry(pex);
      this.write(data);
      this.closeArchiveEntry();
   }

   private void writeRecord(byte[] record) throws IOException {
      if (record.length != 512) {
         throw new IOException("Record to write has length '" + record.length + "' which is not the record size of '" + 512 + "'");
      } else {
         this.out.write(record);
         ++this.recordsWritten;
      }
   }

   static {
      ASCII = ZipEncodingHelper.getZipEncoding(StandardCharsets.US_ASCII);
   }
}
