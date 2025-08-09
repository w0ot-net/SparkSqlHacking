package org.apache.commons.compress.archivers.tar;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.apache.commons.compress.utils.ArchiveUtils;
import org.apache.commons.compress.utils.BoundedInputStream;
import org.apache.commons.compress.utils.IOUtils;

public class TarArchiveInputStream extends ArchiveInputStream {
   private static final int SMALL_BUFFER_SIZE = 256;
   private final byte[] smallBuf;
   private final byte[] recordBuffer;
   private final int blockSize;
   private boolean atEof;
   private long entrySize;
   private long entryOffset;
   private List sparseInputStreams;
   private int currentSparseInputStreamIndex;
   private TarArchiveEntry currEntry;
   private final ZipEncoding zipEncoding;
   private Map globalPaxHeaders;
   private final List globalSparseHeaders;
   private final boolean lenient;

   public static boolean matches(byte[] signature, int length) {
      int versionOffset = 263;
      int versionLen = 2;
      if (length < 265) {
         return false;
      } else {
         int magicOffset = 257;
         int magicLen = 6;
         if (ArchiveUtils.matchAsciiBuffer("ustar\u0000", signature, 257, 6) && ArchiveUtils.matchAsciiBuffer("00", signature, 263, 2)) {
            return true;
         } else if (!ArchiveUtils.matchAsciiBuffer("ustar ", signature, 257, 6) || !ArchiveUtils.matchAsciiBuffer(" \u0000", signature, 263, 2) && !ArchiveUtils.matchAsciiBuffer("0\u0000", signature, 263, 2)) {
            return ArchiveUtils.matchAsciiBuffer("ustar\u0000", signature, 257, 6) && ArchiveUtils.matchAsciiBuffer("\u0000\u0000", signature, 263, 2);
         } else {
            return true;
         }
      }
   }

   public TarArchiveInputStream(InputStream inputStream) {
      this(inputStream, 10240, 512);
   }

   public TarArchiveInputStream(InputStream inputStream, boolean lenient) {
      this(inputStream, 10240, 512, (String)null, lenient);
   }

   public TarArchiveInputStream(InputStream inputStream, int blockSize) {
      this(inputStream, blockSize, 512);
   }

   public TarArchiveInputStream(InputStream inputStream, int blockSize, int recordSize) {
      this(inputStream, blockSize, recordSize, (String)null);
   }

   public TarArchiveInputStream(InputStream inputStream, int blockSize, int recordSize, String encoding) {
      this(inputStream, blockSize, recordSize, encoding, false);
   }

   public TarArchiveInputStream(InputStream inputStream, int blockSize, int recordSize, String encoding, boolean lenient) {
      super(inputStream, encoding);
      this.smallBuf = new byte[256];
      this.globalPaxHeaders = new HashMap();
      this.globalSparseHeaders = new ArrayList();
      this.zipEncoding = ZipEncodingHelper.getZipEncoding(encoding);
      this.recordBuffer = new byte[recordSize];
      this.blockSize = blockSize;
      this.lenient = lenient;
   }

   public TarArchiveInputStream(InputStream inputStream, int blockSize, String encoding) {
      this(inputStream, blockSize, 512, encoding);
   }

   public TarArchiveInputStream(InputStream inputStream, String encoding) {
      this(inputStream, 10240, 512, encoding);
   }

   private void applyPaxHeadersToCurrentEntry(Map headers, List sparseHeaders) throws IOException {
      this.currEntry.updateEntryFromPaxHeaders(headers);
      this.currEntry.setSparseHeaders(sparseHeaders);
   }

   public int available() throws IOException {
      if (this.isDirectory()) {
         return 0;
      } else {
         long available = this.currEntry.getRealSize() - this.entryOffset;
         return available > 2147483647L ? Integer.MAX_VALUE : (int)available;
      }
   }

   private void buildSparseInputStreams() throws IOException {
      this.currentSparseInputStreamIndex = -1;
      this.sparseInputStreams = new ArrayList();
      List<TarArchiveStructSparse> sparseHeaders = this.currEntry.getOrderedSparseHeaders();
      InputStream zeroInputStream = new TarArchiveSparseZeroInputStream();
      long offset = 0L;

      for(TarArchiveStructSparse sparseHeader : sparseHeaders) {
         long zeroBlockSize = sparseHeader.getOffset() - offset;
         if (zeroBlockSize < 0L) {
            throw new IOException("Corrupted struct sparse detected");
         }

         if (zeroBlockSize > 0L) {
            this.sparseInputStreams.add(new BoundedInputStream(zeroInputStream, sparseHeader.getOffset() - offset));
         }

         if (sparseHeader.getNumbytes() > 0L) {
            this.sparseInputStreams.add(new BoundedInputStream(this.in, sparseHeader.getNumbytes()));
         }

         offset = sparseHeader.getOffset() + sparseHeader.getNumbytes();
      }

      if (!this.sparseInputStreams.isEmpty()) {
         this.currentSparseInputStreamIndex = 0;
      }

   }

   public boolean canReadEntryData(ArchiveEntry archiveEntry) {
      return archiveEntry instanceof TarArchiveEntry;
   }

   public void close() throws IOException {
      if (this.sparseInputStreams != null) {
         for(InputStream inputStream : this.sparseInputStreams) {
            inputStream.close();
         }
      }

      this.in.close();
   }

   private void consumeRemainderOfLastBlock() throws IOException {
      long bytesReadOfLastBlock = this.getBytesRead() % (long)this.blockSize;
      if (bytesReadOfLastBlock > 0L) {
         this.count(IOUtils.skip(this.in, (long)this.blockSize - bytesReadOfLastBlock));
      }

   }

   private long getActuallySkipped(long available, long skipped, long expected) throws IOException {
      long actuallySkipped = skipped;
      if (this.in instanceof FileInputStream) {
         actuallySkipped = Math.min(skipped, available);
      }

      if (actuallySkipped != expected) {
         throw new IOException("Truncated TAR archive");
      } else {
         return actuallySkipped;
      }
   }

   public TarArchiveEntry getCurrentEntry() {
      return this.currEntry;
   }

   protected byte[] getLongNameData() throws IOException {
      ByteArrayOutputStream longName = new ByteArrayOutputStream();
      int length = 0;

      while((length = this.read(this.smallBuf)) >= 0) {
         longName.write(this.smallBuf, 0, length);
      }

      this.getNextEntry();
      if (this.currEntry == null) {
         return null;
      } else {
         byte[] longNameData = longName.toByteArray();

         for(length = longNameData.length; length > 0 && longNameData[length - 1] == 0; --length) {
         }

         if (length != longNameData.length) {
            longNameData = Arrays.copyOf(longNameData, length);
         }

         return longNameData;
      }
   }

   public TarArchiveEntry getNextEntry() throws IOException {
      return this.getNextTarEntry();
   }

   /** @deprecated */
   @Deprecated
   public TarArchiveEntry getNextTarEntry() throws IOException {
      if (this.isAtEOF()) {
         return null;
      } else {
         if (this.currEntry != null) {
            IOUtils.skip(this, Long.MAX_VALUE);
            this.skipRecordPadding();
         }

         byte[] headerBuf = this.getRecord();
         if (headerBuf == null) {
            this.currEntry = null;
            return null;
         } else {
            try {
               this.currEntry = new TarArchiveEntry(this.globalPaxHeaders, headerBuf, this.zipEncoding, this.lenient);
            } catch (IllegalArgumentException e) {
               throw new IOException("Error detected parsing the header", e);
            }

            this.entryOffset = 0L;
            this.entrySize = this.currEntry.getSize();
            if (this.currEntry.isGNULongLinkEntry()) {
               byte[] longLinkData = this.getLongNameData();
               if (longLinkData == null) {
                  return null;
               }

               this.currEntry.setLinkName(this.zipEncoding.decode(longLinkData));
            }

            if (this.currEntry.isGNULongNameEntry()) {
               byte[] longNameData = this.getLongNameData();
               if (longNameData == null) {
                  return null;
               }

               String name = this.zipEncoding.decode(longNameData);
               this.currEntry.setName(name);
               if (this.currEntry.isDirectory() && !name.endsWith("/")) {
                  this.currEntry.setName(name + "/");
               }
            }

            if (this.currEntry.isGlobalPaxHeader()) {
               this.readGlobalPaxHeaders();
            }

            try {
               if (this.currEntry.isPaxHeader()) {
                  this.paxHeaders();
               } else if (!this.globalPaxHeaders.isEmpty()) {
                  this.applyPaxHeadersToCurrentEntry(this.globalPaxHeaders, this.globalSparseHeaders);
               }
            } catch (NumberFormatException e) {
               throw new IOException("Error detected parsing the pax header", e);
            }

            if (this.currEntry.isOldGNUSparse()) {
               this.readOldGNUSparse();
            }

            this.entrySize = this.currEntry.getSize();
            return this.currEntry;
         }
      }
   }

   private byte[] getRecord() throws IOException {
      byte[] headerBuf = this.readRecord();
      this.setAtEOF(this.isEOFRecord(headerBuf));
      if (this.isAtEOF() && headerBuf != null) {
         this.tryToConsumeSecondEOFRecord();
         this.consumeRemainderOfLastBlock();
         headerBuf = null;
      }

      return headerBuf;
   }

   public int getRecordSize() {
      return this.recordBuffer.length;
   }

   protected final boolean isAtEOF() {
      return this.atEof;
   }

   private boolean isDirectory() {
      return this.currEntry != null && this.currEntry.isDirectory();
   }

   protected boolean isEOFRecord(byte[] record) {
      return record == null || ArchiveUtils.isArrayZero(record, this.getRecordSize());
   }

   public synchronized void mark(int markLimit) {
   }

   public boolean markSupported() {
      return false;
   }

   private void paxHeaders() throws IOException {
      List<TarArchiveStructSparse> sparseHeaders = new ArrayList();
      Map<String, String> headers = TarUtils.parsePaxHeaders(this, sparseHeaders, this.globalPaxHeaders, this.entrySize);
      if (headers.containsKey("GNU.sparse.map")) {
         sparseHeaders = new ArrayList(TarUtils.parseFromPAX01SparseHeaders((String)headers.get("GNU.sparse.map")));
      }

      this.getNextEntry();
      if (this.currEntry == null) {
         throw new IOException("premature end of tar archive. Didn't find any entry after PAX header.");
      } else {
         this.applyPaxHeadersToCurrentEntry(headers, sparseHeaders);
         if (this.currEntry.isPaxGNU1XSparse()) {
            List var3 = TarUtils.parsePAX1XSparseHeaders(this.in, this.getRecordSize());
            this.currEntry.setSparseHeaders(var3);
         }

         this.buildSparseInputStreams();
      }
   }

   public int read(byte[] buf, int offset, int numToRead) throws IOException {
      if (numToRead == 0) {
         return 0;
      } else {
         int totalRead = 0;
         if (!this.isAtEOF() && !this.isDirectory()) {
            if (this.currEntry == null) {
               throw new IllegalStateException("No current tar entry");
            } else if (this.entryOffset >= this.currEntry.getRealSize()) {
               return -1;
            } else {
               numToRead = Math.min(numToRead, this.available());
               if (this.currEntry.isSparse()) {
                  totalRead = this.readSparse(buf, offset, numToRead);
               } else {
                  totalRead = this.in.read(buf, offset, numToRead);
               }

               if (totalRead == -1) {
                  if (numToRead > 0) {
                     throw new IOException("Truncated TAR archive");
                  }

                  this.setAtEOF(true);
               } else {
                  this.count(totalRead);
                  this.entryOffset += (long)totalRead;
               }

               return totalRead;
            }
         } else {
            return -1;
         }
      }
   }

   private void readGlobalPaxHeaders() throws IOException {
      this.globalPaxHeaders = TarUtils.parsePaxHeaders(this, this.globalSparseHeaders, this.globalPaxHeaders, this.entrySize);
      this.getNextEntry();
      if (this.currEntry == null) {
         throw new IOException("Error detected parsing the pax header");
      }
   }

   private void readOldGNUSparse() throws IOException {
      TarArchiveSparseEntry entry;
      if (this.currEntry.isExtended()) {
         do {
            byte[] headerBuf = this.getRecord();
            if (headerBuf == null) {
               throw new IOException("premature end of tar archive. Didn't find extended_header after header with extended flag.");
            }

            entry = new TarArchiveSparseEntry(headerBuf);
            this.currEntry.getSparseHeaders().addAll(entry.getSparseHeaders());
         } while(entry.isExtended());
      }

      this.buildSparseInputStreams();
   }

   protected byte[] readRecord() throws IOException {
      int readCount = IOUtils.readFully(this.in, this.recordBuffer);
      this.count(readCount);
      return readCount != this.getRecordSize() ? null : this.recordBuffer;
   }

   private int readSparse(byte[] buf, int offset, int numToRead) throws IOException {
      if (this.sparseInputStreams != null && !this.sparseInputStreams.isEmpty()) {
         if (this.currentSparseInputStreamIndex >= this.sparseInputStreams.size()) {
            return -1;
         } else {
            InputStream currentInputStream = (InputStream)this.sparseInputStreams.get(this.currentSparseInputStreamIndex);
            int readLen = currentInputStream.read(buf, offset, numToRead);
            if (this.currentSparseInputStreamIndex == this.sparseInputStreams.size() - 1) {
               return readLen;
            } else if (readLen == -1) {
               ++this.currentSparseInputStreamIndex;
               return this.readSparse(buf, offset, numToRead);
            } else if (readLen < numToRead) {
               ++this.currentSparseInputStreamIndex;
               int readLenOfNext = this.readSparse(buf, offset + readLen, numToRead - readLen);
               return readLenOfNext == -1 ? readLen : readLen + readLenOfNext;
            } else {
               return readLen;
            }
         }
      } else {
         return this.in.read(buf, offset, numToRead);
      }
   }

   public synchronized void reset() {
   }

   protected final void setAtEOF(boolean atEof) {
      this.atEof = atEof;
   }

   protected final void setCurrentEntry(TarArchiveEntry currEntry) {
      this.currEntry = currEntry;
   }

   public long skip(long n) throws IOException {
      if (n > 0L && !this.isDirectory()) {
         long availableOfInputStream = (long)this.in.available();
         long available = this.currEntry.getRealSize() - this.entryOffset;
         long numToSkip = Math.min(n, available);
         long skipped;
         if (!this.currEntry.isSparse()) {
            skipped = IOUtils.skip(this.in, numToSkip);
            skipped = this.getActuallySkipped(availableOfInputStream, skipped, numToSkip);
         } else {
            skipped = this.skipSparse(numToSkip);
         }

         this.count(skipped);
         this.entryOffset += skipped;
         return skipped;
      } else {
         return 0L;
      }
   }

   private void skipRecordPadding() throws IOException {
      if (!this.isDirectory() && this.entrySize > 0L && this.entrySize % (long)this.getRecordSize() != 0L) {
         long available = (long)this.in.available();
         long numRecords = this.entrySize / (long)this.getRecordSize() + 1L;
         long padding = numRecords * (long)this.getRecordSize() - this.entrySize;
         long skipped = IOUtils.skip(this.in, padding);
         skipped = this.getActuallySkipped(available, skipped, padding);
         this.count(skipped);
      }

   }

   private long skipSparse(long n) throws IOException {
      if (this.sparseInputStreams != null && !this.sparseInputStreams.isEmpty()) {
         long bytesSkipped = 0L;

         while(bytesSkipped < n && this.currentSparseInputStreamIndex < this.sparseInputStreams.size()) {
            InputStream currentInputStream = (InputStream)this.sparseInputStreams.get(this.currentSparseInputStreamIndex);
            bytesSkipped += currentInputStream.skip(n - bytesSkipped);
            if (bytesSkipped < n) {
               ++this.currentSparseInputStreamIndex;
            }
         }

         return bytesSkipped;
      } else {
         return this.in.skip(n);
      }
   }

   private void tryToConsumeSecondEOFRecord() throws IOException {
      boolean shouldReset = true;
      boolean marked = this.in.markSupported();
      if (marked) {
         this.in.mark(this.getRecordSize());
      }

      try {
         shouldReset = !this.isEOFRecord(this.readRecord());
      } finally {
         if (shouldReset && marked) {
            this.pushedBackBytes((long)this.getRecordSize());
            this.in.reset();
         }

      }

   }
}
