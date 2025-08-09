package org.apache.commons.compress.archivers.zip;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import org.apache.commons.compress.utils.FileNameUtils;

final class ZipSplitOutputStream extends RandomAccessOutputStream {
   private static final long ZIP_SEGMENT_MIN_SIZE = 65536L;
   private static final long ZIP_SEGMENT_MAX_SIZE = 4294967295L;
   private FileChannel currentChannel;
   private FileRandomAccessOutputStream outputStream;
   private Path zipFile;
   private final long splitSize;
   private long totalPosition;
   private int currentSplitSegmentIndex;
   private long currentSplitSegmentBytesWritten;
   private boolean finished;
   private final byte[] singleByte;
   private final List diskToPosition;
   private final TreeMap positionToFiles;

   ZipSplitOutputStream(File zipFile, long splitSize) throws IllegalArgumentException, IOException {
      this(zipFile.toPath(), splitSize);
   }

   ZipSplitOutputStream(Path zipFile, long splitSize) throws IllegalArgumentException, IOException {
      this.singleByte = new byte[1];
      this.diskToPosition = new ArrayList();
      this.positionToFiles = new TreeMap();
      if (splitSize >= 65536L && splitSize <= 4294967295L) {
         this.zipFile = zipFile;
         this.splitSize = splitSize;
         this.outputStream = new FileRandomAccessOutputStream(zipFile);
         this.currentChannel = this.outputStream.channel();
         this.positionToFiles.put(0L, this.zipFile);
         this.diskToPosition.add(0L);
         this.writeZipSplitSignature();
      } else {
         throw new IllegalArgumentException("Zip split segment size should between 64K and 4,294,967,295");
      }
   }

   public long calculateDiskPosition(long disk, long localOffset) throws IOException {
      if (disk >= 2147483647L) {
         throw new IOException("Disk number exceeded internal limits: limit=2147483647 requested=" + disk);
      } else {
         return (Long)this.diskToPosition.get((int)disk) + localOffset;
      }
   }

   public void close() throws IOException {
      if (!this.finished) {
         this.finish();
      }

   }

   private Path createNewSplitSegmentFile(Integer zipSplitSegmentSuffixIndex) throws IOException {
      Path newFile = this.getSplitSegmentFileName(zipSplitSegmentSuffixIndex);
      if (Files.exists(newFile, new LinkOption[0])) {
         throw new IOException("split ZIP segment " + newFile + " already exists");
      } else {
         return newFile;
      }
   }

   private void finish() throws IOException {
      if (this.finished) {
         throw new IOException("This archive has already been finished");
      } else {
         String zipFileBaseName = FileNameUtils.getBaseName(this.zipFile);
         this.outputStream.close();
         Files.move(this.zipFile, this.zipFile.resolveSibling(zipFileBaseName + ".zip"), StandardCopyOption.ATOMIC_MOVE);
         this.finished = true;
      }
   }

   public long getCurrentSplitSegmentBytesWritten() {
      return this.currentSplitSegmentBytesWritten;
   }

   public int getCurrentSplitSegmentIndex() {
      return this.currentSplitSegmentIndex;
   }

   private Path getSplitSegmentFileName(Integer zipSplitSegmentSuffixIndex) {
      int newZipSplitSegmentSuffixIndex = zipSplitSegmentSuffixIndex == null ? this.currentSplitSegmentIndex + 2 : zipSplitSegmentSuffixIndex;
      String baseName = FileNameUtils.getBaseName(this.zipFile);
      StringBuilder extension = new StringBuilder(".z");
      if (newZipSplitSegmentSuffixIndex <= 9) {
         extension.append("0").append(newZipSplitSegmentSuffixIndex);
      } else {
         extension.append(newZipSplitSegmentSuffixIndex);
      }

      Path parent = this.zipFile.getParent();
      String dir = Objects.nonNull(parent) ? parent.toAbsolutePath().toString() : ".";
      return this.zipFile.getFileSystem().getPath(dir, baseName + extension.toString());
   }

   private void openNewSplitSegment() throws IOException {
      if (this.currentSplitSegmentIndex == 0) {
         this.outputStream.close();
         Path newFile = this.createNewSplitSegmentFile(1);
         Files.move(this.zipFile, newFile, StandardCopyOption.ATOMIC_MOVE);
         this.positionToFiles.put(0L, newFile);
      }

      Path newFile = this.createNewSplitSegmentFile((Integer)null);
      this.outputStream.close();
      this.outputStream = new FileRandomAccessOutputStream(newFile);
      this.currentChannel = this.outputStream.channel();
      this.currentSplitSegmentBytesWritten = 0L;
      this.zipFile = newFile;
      ++this.currentSplitSegmentIndex;
      this.diskToPosition.add(this.totalPosition);
      this.positionToFiles.put(this.totalPosition, newFile);
   }

   public long position() {
      return this.totalPosition;
   }

   public void prepareToWriteUnsplittableContent(long unsplittableContentSize) throws IllegalArgumentException, IOException {
      if (unsplittableContentSize > this.splitSize) {
         throw new IllegalArgumentException("The unsplittable content size is bigger than the split segment size");
      } else {
         long bytesRemainingInThisSegment = this.splitSize - this.currentSplitSegmentBytesWritten;
         if (bytesRemainingInThisSegment < unsplittableContentSize) {
            this.openNewSplitSegment();
         }

      }
   }

   public void write(byte[] b) throws IOException {
      this.write(b, 0, b.length);
   }

   public void write(byte[] b, int off, int len) throws IOException {
      if (len > 0) {
         if (this.currentSplitSegmentBytesWritten >= this.splitSize) {
            this.openNewSplitSegment();
            this.write(b, off, len);
         } else if (this.currentSplitSegmentBytesWritten + (long)len > this.splitSize) {
            int bytesToWriteForThisSegment = (int)this.splitSize - (int)this.currentSplitSegmentBytesWritten;
            this.write(b, off, bytesToWriteForThisSegment);
            this.openNewSplitSegment();
            this.write(b, off + bytesToWriteForThisSegment, len - bytesToWriteForThisSegment);
         } else {
            this.outputStream.write(b, off, len);
            this.currentSplitSegmentBytesWritten += (long)len;
            this.totalPosition += (long)len;
         }

      }
   }

   public void write(int i) throws IOException {
      this.singleByte[0] = (byte)(i & 255);
      this.write(this.singleByte);
   }

   public void writeFully(byte[] b, int off, int len, long atPosition) throws IOException {
      long remainingPosition = atPosition;
      int remainingOff = off;
      int remainingLen = len;

      while(remainingLen > 0) {
         Map.Entry<Long, Path> segment = this.positionToFiles.floorEntry(remainingPosition);
         Long segmentEnd = (Long)this.positionToFiles.higherKey(remainingPosition);
         if (segmentEnd == null) {
            ZipIoUtil.writeFullyAt(this.currentChannel, ByteBuffer.wrap(b, remainingOff, remainingLen), remainingPosition - (Long)segment.getKey());
            remainingPosition += (long)remainingLen;
            remainingOff += remainingLen;
            remainingLen = 0;
         } else if (remainingPosition + (long)remainingLen <= segmentEnd) {
            this.writeToSegment((Path)segment.getValue(), remainingPosition - (Long)segment.getKey(), b, remainingOff, remainingLen);
            remainingPosition += (long)remainingLen;
            remainingOff += remainingLen;
            remainingLen = 0;
         } else {
            int toWrite = Math.toIntExact(segmentEnd - remainingPosition);
            this.writeToSegment((Path)segment.getValue(), remainingPosition - (Long)segment.getKey(), b, remainingOff, toWrite);
            remainingPosition += (long)toWrite;
            remainingOff += toWrite;
            remainingLen -= toWrite;
         }
      }

   }

   private void writeToSegment(Path segment, long position, byte[] b, int off, int len) throws IOException {
      FileChannel channel = FileChannel.open(segment, StandardOpenOption.WRITE);

      try {
         ZipIoUtil.writeFullyAt(channel, ByteBuffer.wrap(b, off, len), position);
      } catch (Throwable var11) {
         if (channel != null) {
            try {
               channel.close();
            } catch (Throwable var10) {
               var11.addSuppressed(var10);
            }
         }

         throw var11;
      }

      if (channel != null) {
         channel.close();
      }

   }

   private void writeZipSplitSignature() throws IOException {
      this.outputStream.write(ZipArchiveOutputStream.DD_SIG);
      this.currentSplitSegmentBytesWritten += (long)ZipArchiveOutputStream.DD_SIG.length;
      this.totalPosition += (long)ZipArchiveOutputStream.DD_SIG.length;
   }
}
