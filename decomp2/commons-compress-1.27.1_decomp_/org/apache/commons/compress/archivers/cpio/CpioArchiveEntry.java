package org.apache.commons.compress.archivers.cpio;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.Date;
import java.util.Objects;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.utils.ExactMath;
import org.apache.commons.compress.utils.TimeUtils;

public class CpioArchiveEntry implements CpioConstants, ArchiveEntry {
   private final short fileFormat;
   private final int headerSize;
   private final int alignmentBoundary;
   private long chksum;
   private long filesize;
   private long gid;
   private long inode;
   private long maj;
   private long min;
   private long mode;
   private long mtime;
   private String name;
   private long nlink;
   private long rmaj;
   private long rmin;
   private long uid;

   public CpioArchiveEntry(File inputFile, String entryName) {
      this((short)1, inputFile, entryName);
   }

   public CpioArchiveEntry(Path inputPath, String entryName, LinkOption... options) throws IOException {
      this((short)1, inputPath, entryName, options);
   }

   public CpioArchiveEntry(short format) {
      switch (format) {
         case 1:
            this.headerSize = 110;
            this.alignmentBoundary = 4;
            break;
         case 2:
            this.headerSize = 110;
            this.alignmentBoundary = 4;
            break;
         case 3:
         case 5:
         case 6:
         case 7:
         default:
            throw new IllegalArgumentException("Unknown header type " + format);
         case 4:
            this.headerSize = 76;
            this.alignmentBoundary = 0;
            break;
         case 8:
            this.headerSize = 26;
            this.alignmentBoundary = 2;
      }

      this.fileFormat = format;
   }

   public CpioArchiveEntry(short format, File inputFile, String entryName) {
      this(format, entryName, inputFile.isFile() ? inputFile.length() : 0L);
      if (inputFile.isDirectory()) {
         this.setMode(16384L);
      } else {
         if (!inputFile.isFile()) {
            throw new IllegalArgumentException("Cannot determine type of file " + inputFile.getName());
         }

         this.setMode(32768L);
      }

      this.setTime(inputFile.lastModified() / 1000L);
   }

   public CpioArchiveEntry(short format, Path inputPath, String entryName, LinkOption... options) throws IOException {
      this(format, entryName, Files.isRegularFile(inputPath, options) ? Files.size(inputPath) : 0L);
      if (Files.isDirectory(inputPath, options)) {
         this.setMode(16384L);
      } else {
         if (!Files.isRegularFile(inputPath, options)) {
            throw new IllegalArgumentException("Cannot determine type of file " + inputPath);
         }

         this.setMode(32768L);
      }

      this.setTime(Files.getLastModifiedTime(inputPath, options));
   }

   public CpioArchiveEntry(short format, String name) {
      this(format);
      this.name = name;
   }

   public CpioArchiveEntry(short format, String name, long size) {
      this(format, name);
      this.setSize(size);
   }

   public CpioArchiveEntry(String name) {
      this((short)1, name);
   }

   public CpioArchiveEntry(String name, long size) {
      this(name);
      this.setSize(size);
   }

   private void checkNewFormat() {
      if ((this.fileFormat & 3) == 0) {
         throw new UnsupportedOperationException();
      }
   }

   private void checkOldFormat() {
      if ((this.fileFormat & 12) == 0) {
         throw new UnsupportedOperationException();
      }
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && this.getClass() == obj.getClass()) {
         CpioArchiveEntry other = (CpioArchiveEntry)obj;
         return Objects.equals(this.name, other.name);
      } else {
         return false;
      }
   }

   public int getAlignmentBoundary() {
      return this.alignmentBoundary;
   }

   public long getChksum() {
      this.checkNewFormat();
      return this.chksum & 4294967295L;
   }

   public int getDataPadCount() {
      if (this.alignmentBoundary == 0) {
         return 0;
      } else {
         long size = this.filesize;
         int remain = (int)(size % (long)this.alignmentBoundary);
         return remain > 0 ? this.alignmentBoundary - remain : 0;
      }
   }

   public long getDevice() {
      this.checkOldFormat();
      return this.min;
   }

   public long getDeviceMaj() {
      this.checkNewFormat();
      return this.maj;
   }

   public long getDeviceMin() {
      this.checkNewFormat();
      return this.min;
   }

   public short getFormat() {
      return this.fileFormat;
   }

   public long getGID() {
      return this.gid;
   }

   /** @deprecated */
   @Deprecated
   public int getHeaderPadCount() {
      return this.getHeaderPadCount((Charset)null);
   }

   public int getHeaderPadCount(Charset charset) {
      if (this.name == null) {
         return 0;
      } else {
         return charset == null ? this.getHeaderPadCount((long)this.name.length()) : this.getHeaderPadCount((long)this.name.getBytes(charset).length);
      }
   }

   public int getHeaderPadCount(long nameSize) {
      if (this.alignmentBoundary == 0) {
         return 0;
      } else {
         int size = this.headerSize + 1;
         if (this.name != null) {
            size = ExactMath.add(size, nameSize);
         }

         int remain = size % this.alignmentBoundary;
         return remain > 0 ? this.alignmentBoundary - remain : 0;
      }
   }

   public int getHeaderSize() {
      return this.headerSize;
   }

   public long getInode() {
      return this.inode;
   }

   public Date getLastModifiedDate() {
      return new Date(1000L * this.getTime());
   }

   public long getMode() {
      return this.mode == 0L && !"TRAILER!!!".equals(this.name) ? 32768L : this.mode;
   }

   public String getName() {
      return this.name;
   }

   public long getNumberOfLinks() {
      return this.nlink == 0L ? (this.isDirectory() ? 2L : 1L) : this.nlink;
   }

   public long getRemoteDevice() {
      this.checkOldFormat();
      return this.rmin;
   }

   public long getRemoteDeviceMaj() {
      this.checkNewFormat();
      return this.rmaj;
   }

   public long getRemoteDeviceMin() {
      this.checkNewFormat();
      return this.rmin;
   }

   public long getSize() {
      return this.filesize;
   }

   public long getTime() {
      return this.mtime;
   }

   public long getUID() {
      return this.uid;
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.name});
   }

   public boolean isBlockDevice() {
      return CpioUtil.fileType(this.mode) == 24576L;
   }

   public boolean isCharacterDevice() {
      return CpioUtil.fileType(this.mode) == 8192L;
   }

   public boolean isDirectory() {
      return CpioUtil.fileType(this.mode) == 16384L;
   }

   public boolean isNetwork() {
      return CpioUtil.fileType(this.mode) == 36864L;
   }

   public boolean isPipe() {
      return CpioUtil.fileType(this.mode) == 4096L;
   }

   public boolean isRegularFile() {
      return CpioUtil.fileType(this.mode) == 32768L;
   }

   public boolean isSocket() {
      return CpioUtil.fileType(this.mode) == 49152L;
   }

   public boolean isSymbolicLink() {
      return CpioUtil.fileType(this.mode) == 40960L;
   }

   public void setChksum(long chksum) {
      this.checkNewFormat();
      this.chksum = chksum & 4294967295L;
   }

   public void setDevice(long device) {
      this.checkOldFormat();
      this.min = device;
   }

   public void setDeviceMaj(long maj) {
      this.checkNewFormat();
      this.maj = maj;
   }

   public void setDeviceMin(long min) {
      this.checkNewFormat();
      this.min = min;
   }

   public void setGID(long gid) {
      this.gid = gid;
   }

   public void setInode(long inode) {
      this.inode = inode;
   }

   public void setMode(long mode) {
      long maskedMode = mode & 61440L;
      switch ((int)maskedMode) {
         case 4096:
         case 8192:
         case 16384:
         case 24576:
         case 32768:
         case 36864:
         case 40960:
         case 49152:
            this.mode = mode;
            return;
         default:
            throw new IllegalArgumentException("Unknown mode. Full: " + Long.toHexString(mode) + " Masked: " + Long.toHexString(maskedMode));
      }
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setNumberOfLinks(long nlink) {
      this.nlink = nlink;
   }

   public void setRemoteDevice(long device) {
      this.checkOldFormat();
      this.rmin = device;
   }

   public void setRemoteDeviceMaj(long rmaj) {
      this.checkNewFormat();
      this.rmaj = rmaj;
   }

   public void setRemoteDeviceMin(long rmin) {
      this.checkNewFormat();
      this.rmin = rmin;
   }

   public void setSize(long size) {
      if (size >= 0L && size <= 4294967295L) {
         this.filesize = size;
      } else {
         throw new IllegalArgumentException("Invalid entry size <" + size + ">");
      }
   }

   public void setTime(FileTime time) {
      this.mtime = TimeUtils.toUnixTime(time);
   }

   public void setTime(long time) {
      this.mtime = time;
   }

   public void setUID(long uid) {
      this.uid = uid;
   }
}
