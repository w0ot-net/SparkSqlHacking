package org.apache.commons.compress.archivers.sevenz;

import java.nio.file.attribute.FileTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.io.file.attribute.FileTimes;

public class SevenZArchiveEntry implements ArchiveEntry {
   static final SevenZArchiveEntry[] EMPTY_SEVEN_Z_ARCHIVE_ENTRY_ARRAY = new SevenZArchiveEntry[0];
   private String name;
   private boolean hasStream;
   private boolean isDirectory;
   private boolean isAntiItem;
   private boolean hasCreationDate;
   private boolean hasLastModifiedDate;
   private boolean hasAccessDate;
   private FileTime creationDate;
   private FileTime lastModifiedDate;
   private FileTime accessDate;
   private boolean hasWindowsAttributes;
   private int windowsAttributes;
   private boolean hasCrc;
   private long crc;
   private long compressedCrc;
   private long size;
   private long compressedSize;
   private Iterable contentMethods;

   /** @deprecated */
   @Deprecated
   public static long javaTimeToNtfsTime(Date date) {
      return FileTimes.toNtfsTime(date);
   }

   /** @deprecated */
   @Deprecated
   public static Date ntfsTimeToJavaTime(long ntfsTime) {
      return FileTimes.ntfsTimeToDate(ntfsTime);
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && this.getClass() == obj.getClass()) {
         SevenZArchiveEntry other = (SevenZArchiveEntry)obj;
         return Objects.equals(this.name, other.name) && this.hasStream == other.hasStream && this.isDirectory == other.isDirectory && this.isAntiItem == other.isAntiItem && this.hasCreationDate == other.hasCreationDate && this.hasLastModifiedDate == other.hasLastModifiedDate && this.hasAccessDate == other.hasAccessDate && Objects.equals(this.creationDate, other.creationDate) && Objects.equals(this.lastModifiedDate, other.lastModifiedDate) && Objects.equals(this.accessDate, other.accessDate) && this.hasWindowsAttributes == other.hasWindowsAttributes && this.windowsAttributes == other.windowsAttributes && this.hasCrc == other.hasCrc && this.crc == other.crc && this.compressedCrc == other.compressedCrc && this.size == other.size && this.compressedSize == other.compressedSize && this.equalSevenZMethods(this.contentMethods, other.contentMethods);
      } else {
         return false;
      }
   }

   private boolean equalSevenZMethods(Iterable c1, Iterable c2) {
      if (c1 == null) {
         return c2 == null;
      } else if (c2 == null) {
         return false;
      } else {
         Iterator<? extends SevenZMethodConfiguration> i2 = c2.iterator();

         for(SevenZMethodConfiguration element : c1) {
            if (!i2.hasNext()) {
               return false;
            }

            if (!element.equals(i2.next())) {
               return false;
            }
         }

         return !i2.hasNext();
      }
   }

   public Date getAccessDate() {
      return FileTimes.toDate(this.getAccessTime());
   }

   public FileTime getAccessTime() {
      if (this.hasAccessDate) {
         return this.accessDate;
      } else {
         throw new UnsupportedOperationException("The entry doesn't have this timestamp");
      }
   }

   /** @deprecated */
   @Deprecated
   int getCompressedCrc() {
      return (int)this.compressedCrc;
   }

   long getCompressedCrcValue() {
      return this.compressedCrc;
   }

   long getCompressedSize() {
      return this.compressedSize;
   }

   public Iterable getContentMethods() {
      return this.contentMethods;
   }

   /** @deprecated */
   @Deprecated
   public int getCrc() {
      return (int)this.crc;
   }

   public long getCrcValue() {
      return this.crc;
   }

   public Date getCreationDate() {
      return FileTimes.toDate(this.getCreationTime());
   }

   public FileTime getCreationTime() {
      if (this.hasCreationDate) {
         return this.creationDate;
      } else {
         throw new UnsupportedOperationException("The entry doesn't have this timestamp");
      }
   }

   public boolean getHasAccessDate() {
      return this.hasAccessDate;
   }

   public boolean getHasCrc() {
      return this.hasCrc;
   }

   public boolean getHasCreationDate() {
      return this.hasCreationDate;
   }

   public boolean getHasLastModifiedDate() {
      return this.hasLastModifiedDate;
   }

   public boolean getHasWindowsAttributes() {
      return this.hasWindowsAttributes;
   }

   public Date getLastModifiedDate() {
      return FileTimes.toDate(this.getLastModifiedTime());
   }

   public FileTime getLastModifiedTime() {
      if (this.hasLastModifiedDate) {
         return this.lastModifiedDate;
      } else {
         throw new UnsupportedOperationException("The entry doesn't have this timestamp");
      }
   }

   public String getName() {
      return this.name;
   }

   public long getSize() {
      return this.size;
   }

   public int getWindowsAttributes() {
      return this.windowsAttributes;
   }

   public int hashCode() {
      String n = this.getName();
      return n == null ? 0 : n.hashCode();
   }

   public boolean hasStream() {
      return this.hasStream;
   }

   public boolean isAntiItem() {
      return this.isAntiItem;
   }

   public boolean isDirectory() {
      return this.isDirectory;
   }

   public void setAccessDate(Date accessDate) {
      this.setAccessTime(FileTimes.toFileTime(accessDate));
   }

   public void setAccessDate(long ntfsAccessDate) {
      this.accessDate = FileTimes.ntfsTimeToFileTime(ntfsAccessDate);
   }

   public void setAccessTime(FileTime time) {
      this.hasAccessDate = time != null;
      if (this.hasAccessDate) {
         this.accessDate = time;
      }

   }

   public void setAntiItem(boolean isAntiItem) {
      this.isAntiItem = isAntiItem;
   }

   /** @deprecated */
   @Deprecated
   void setCompressedCrc(int crc) {
      this.compressedCrc = (long)crc;
   }

   void setCompressedCrcValue(long crc) {
      this.compressedCrc = crc;
   }

   void setCompressedSize(long size) {
      this.compressedSize = size;
   }

   public void setContentMethods(Iterable methods) {
      if (methods != null) {
         LinkedList<SevenZMethodConfiguration> l = new LinkedList();
         Objects.requireNonNull(l);
         methods.forEach(l::addLast);
         this.contentMethods = Collections.unmodifiableList(l);
      } else {
         this.contentMethods = null;
      }

   }

   public void setContentMethods(SevenZMethodConfiguration... methods) {
      this.setContentMethods((Iterable)Arrays.asList(methods));
   }

   /** @deprecated */
   @Deprecated
   public void setCrc(int crc) {
      this.crc = (long)crc;
   }

   public void setCrcValue(long crc) {
      this.crc = crc;
   }

   public void setCreationDate(Date creationDate) {
      this.setCreationTime(FileTimes.toFileTime(creationDate));
   }

   public void setCreationDate(long ntfsCreationDate) {
      this.creationDate = FileTimes.ntfsTimeToFileTime(ntfsCreationDate);
   }

   public void setCreationTime(FileTime time) {
      this.hasCreationDate = time != null;
      if (this.hasCreationDate) {
         this.creationDate = time;
      }

   }

   public void setDirectory(boolean isDirectory) {
      this.isDirectory = isDirectory;
   }

   public void setHasAccessDate(boolean hasAcessDate) {
      this.hasAccessDate = hasAcessDate;
   }

   public void setHasCrc(boolean hasCrc) {
      this.hasCrc = hasCrc;
   }

   public void setHasCreationDate(boolean hasCreationDate) {
      this.hasCreationDate = hasCreationDate;
   }

   public void setHasLastModifiedDate(boolean hasLastModifiedDate) {
      this.hasLastModifiedDate = hasLastModifiedDate;
   }

   public void setHasStream(boolean hasStream) {
      this.hasStream = hasStream;
   }

   public void setHasWindowsAttributes(boolean hasWindowsAttributes) {
      this.hasWindowsAttributes = hasWindowsAttributes;
   }

   public void setLastModifiedDate(Date lastModifiedDate) {
      this.setLastModifiedTime(FileTimes.toFileTime(lastModifiedDate));
   }

   public void setLastModifiedDate(long ntfsLastModifiedDate) {
      this.lastModifiedDate = FileTimes.ntfsTimeToFileTime(ntfsLastModifiedDate);
   }

   public void setLastModifiedTime(FileTime time) {
      this.hasLastModifiedDate = time != null;
      if (this.hasLastModifiedDate) {
         this.lastModifiedDate = time;
      }

   }

   public void setName(String name) {
      this.name = name;
   }

   public void setSize(long size) {
      this.size = size;
   }

   public void setWindowsAttributes(int windowsAttributes) {
      this.windowsAttributes = windowsAttributes;
   }
}
