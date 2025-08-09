package org.apache.commons.compress.archivers.arj;

import java.io.File;
import java.util.Date;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipUtil;

public class ArjArchiveEntry implements ArchiveEntry {
   private final LocalFileHeader localFileHeader;

   public ArjArchiveEntry() {
      this.localFileHeader = new LocalFileHeader();
   }

   ArjArchiveEntry(LocalFileHeader localFileHeader) {
      this.localFileHeader = localFileHeader;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && this.getClass() == obj.getClass()) {
         ArjArchiveEntry other = (ArjArchiveEntry)obj;
         return this.localFileHeader.equals(other.localFileHeader);
      } else {
         return false;
      }
   }

   public int getHostOs() {
      return this.localFileHeader.hostOS;
   }

   public Date getLastModifiedDate() {
      long ts = this.isHostOsUnix() ? (long)this.localFileHeader.dateTimeModified * 1000L : ZipUtil.dosToJavaTime(4294967295L & (long)this.localFileHeader.dateTimeModified);
      return new Date(ts);
   }

   int getMethod() {
      return this.localFileHeader.method;
   }

   public int getMode() {
      return this.localFileHeader.fileAccessMode;
   }

   public String getName() {
      return (this.localFileHeader.arjFlags & 16) != 0 ? this.localFileHeader.name.replace("/", File.separator) : this.localFileHeader.name;
   }

   public long getSize() {
      return this.localFileHeader.originalSize;
   }

   public int getUnixMode() {
      return this.isHostOsUnix() ? this.getMode() : 0;
   }

   public int hashCode() {
      String name = this.getName();
      return name == null ? 0 : name.hashCode();
   }

   public boolean isDirectory() {
      return this.localFileHeader.fileType == 3;
   }

   public boolean isHostOsUnix() {
      return this.getHostOs() == 2 || this.getHostOs() == 8;
   }

   public static class HostOs {
      public static final int DOS = 0;
      public static final int PRIMOS = 1;
      public static final int UNIX = 2;
      public static final int AMIGA = 3;
      public static final int MAC_OS = 4;
      public static final int OS_2 = 5;
      public static final int APPLE_GS = 6;
      public static final int ATARI_ST = 7;
      public static final int NEXT = 8;
      public static final int VAX_VMS = 9;
      public static final int WIN95 = 10;
      public static final int WIN32 = 11;
   }
}
