package org.apache.commons.compress.archivers.dump;

import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.compress.archivers.ArchiveEntry;

public class DumpArchiveEntry implements ArchiveEntry {
   private String name;
   private TYPE type;
   private int mode;
   private Set permissions;
   private long size;
   private long atime;
   private long mtime;
   private int uid;
   private int gid;
   private final DumpArchiveSummary summary;
   private final TapeSegmentHeader header;
   private String simpleName;
   private String originalName;
   private int volume;
   private long offset;
   private int ino;
   private int nlink;
   private long ctime;
   private int generation;
   private boolean isDeleted;

   static DumpArchiveEntry parse(byte[] buffer) {
      DumpArchiveEntry entry = new DumpArchiveEntry();
      TapeSegmentHeader header = entry.header;
      header.type = DumpArchiveConstants.SEGMENT_TYPE.find(DumpArchiveUtil.convert32(buffer, 0));
      header.volume = DumpArchiveUtil.convert32(buffer, 12);
      entry.ino = header.ino = DumpArchiveUtil.convert32(buffer, 20);
      int m = DumpArchiveUtil.convert16(buffer, 32);
      entry.setType(DumpArchiveEntry.TYPE.find(m >> 12 & 15));
      entry.setMode(m);
      entry.nlink = DumpArchiveUtil.convert16(buffer, 34);
      entry.setSize(DumpArchiveUtil.convert64(buffer, 40));
      long t = 1000L * (long)DumpArchiveUtil.convert32(buffer, 48) + (long)(DumpArchiveUtil.convert32(buffer, 52) / 1000);
      entry.setAccessTime(new Date(t));
      t = 1000L * (long)DumpArchiveUtil.convert32(buffer, 56) + (long)(DumpArchiveUtil.convert32(buffer, 60) / 1000);
      entry.setLastModifiedDate(new Date(t));
      t = 1000L * (long)DumpArchiveUtil.convert32(buffer, 64) + (long)(DumpArchiveUtil.convert32(buffer, 68) / 1000);
      entry.ctime = t;
      entry.generation = DumpArchiveUtil.convert32(buffer, 140);
      entry.setUserId(DumpArchiveUtil.convert32(buffer, 144));
      entry.setGroupId(DumpArchiveUtil.convert32(buffer, 148));
      header.count = DumpArchiveUtil.convert32(buffer, 160);
      header.holes = 0;

      for(int i = 0; i < 512 && i < header.count; ++i) {
         if (buffer[164 + i] == 0) {
            header.holes++;
         }
      }

      System.arraycopy(buffer, 164, header.cdata, 0, 512);
      entry.volume = header.getVolume();
      return entry;
   }

   public DumpArchiveEntry() {
      this.type = DumpArchiveEntry.TYPE.UNKNOWN;
      this.permissions = Collections.emptySet();
      this.summary = null;
      this.header = new TapeSegmentHeader();
   }

   public DumpArchiveEntry(String name, String simpleName) {
      this.type = DumpArchiveEntry.TYPE.UNKNOWN;
      this.permissions = Collections.emptySet();
      this.summary = null;
      this.header = new TapeSegmentHeader();
      this.setName(name);
      this.simpleName = simpleName;
   }

   protected DumpArchiveEntry(String name, String simpleName, int ino, TYPE type) {
      this.type = DumpArchiveEntry.TYPE.UNKNOWN;
      this.permissions = Collections.emptySet();
      this.summary = null;
      this.header = new TapeSegmentHeader();
      this.setType(type);
      this.setName(name);
      this.simpleName = simpleName;
      this.ino = ino;
      this.offset = 0L;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (o != null && o.getClass().equals(this.getClass())) {
         DumpArchiveEntry rhs = (DumpArchiveEntry)o;
         if (this.ino != rhs.ino) {
            return false;
         } else {
            return (this.summary != null || rhs.summary == null) && (this.summary == null || this.summary.equals(rhs.summary));
         }
      } else {
         return false;
      }
   }

   public Date getAccessTime() {
      return new Date(this.atime);
   }

   public Date getCreationTime() {
      return new Date(this.ctime);
   }

   long getEntrySize() {
      return this.size;
   }

   public int getGeneration() {
      return this.generation;
   }

   public int getGroupId() {
      return this.gid;
   }

   public int getHeaderCount() {
      return this.header.getCount();
   }

   public int getHeaderHoles() {
      return this.header.getHoles();
   }

   public DumpArchiveConstants.SEGMENT_TYPE getHeaderType() {
      return this.header.getType();
   }

   public int getIno() {
      return this.header.getIno();
   }

   public Date getLastModifiedDate() {
      return new Date(this.mtime);
   }

   public int getMode() {
      return this.mode;
   }

   public String getName() {
      return this.name;
   }

   public int getNlink() {
      return this.nlink;
   }

   public long getOffset() {
      return this.offset;
   }

   String getOriginalName() {
      return this.originalName;
   }

   public Set getPermissions() {
      return this.permissions;
   }

   public String getSimpleName() {
      return this.simpleName;
   }

   public long getSize() {
      return this.isDirectory() ? -1L : this.size;
   }

   public TYPE getType() {
      return this.type;
   }

   public int getUserId() {
      return this.uid;
   }

   public int getVolume() {
      return this.volume;
   }

   public int hashCode() {
      return this.ino;
   }

   public boolean isBlkDev() {
      return this.type == DumpArchiveEntry.TYPE.BLKDEV;
   }

   public boolean isChrDev() {
      return this.type == DumpArchiveEntry.TYPE.CHRDEV;
   }

   public boolean isDeleted() {
      return this.isDeleted;
   }

   public boolean isDirectory() {
      return this.type == DumpArchiveEntry.TYPE.DIRECTORY;
   }

   public boolean isFifo() {
      return this.type == DumpArchiveEntry.TYPE.FIFO;
   }

   public boolean isFile() {
      return this.type == DumpArchiveEntry.TYPE.FILE;
   }

   public boolean isSocket() {
      return this.type == DumpArchiveEntry.TYPE.SOCKET;
   }

   public boolean isSparseRecord(int idx) {
      return (this.header.getCdata(idx) & 1) == 0;
   }

   public void setAccessTime(Date atime) {
      this.atime = atime.getTime();
   }

   public void setCreationTime(Date ctime) {
      this.ctime = ctime.getTime();
   }

   public void setDeleted(boolean isDeleted) {
      this.isDeleted = isDeleted;
   }

   public void setGeneration(int generation) {
      this.generation = generation;
   }

   public void setGroupId(int gid) {
      this.gid = gid;
   }

   public void setLastModifiedDate(Date mtime) {
      this.mtime = mtime.getTime();
   }

   public void setMode(int mode) {
      this.mode = mode & 4095;
      this.permissions = DumpArchiveEntry.PERMISSION.find(mode);
   }

   public final void setName(String name) {
      this.originalName = name;
      if (name != null) {
         if (this.isDirectory() && !name.endsWith("/")) {
            name = name + "/";
         }

         if (name.startsWith("./")) {
            name = name.substring(2);
         }
      }

      this.name = name;
   }

   public void setNlink(int nlink) {
      this.nlink = nlink;
   }

   public void setOffset(long offset) {
      this.offset = offset;
   }

   protected void setSimpleName(String simpleName) {
      this.simpleName = simpleName;
   }

   public void setSize(long size) {
      this.size = size;
   }

   public void setType(TYPE type) {
      this.type = type;
   }

   public void setUserId(int uid) {
      this.uid = uid;
   }

   public void setVolume(int volume) {
      this.volume = volume;
   }

   public String toString() {
      return this.getName();
   }

   void update(byte[] buffer) {
      this.header.volume = DumpArchiveUtil.convert32(buffer, 16);
      this.header.count = DumpArchiveUtil.convert32(buffer, 160);
      this.header.holes = 0;

      for(int i = 0; i < 512 && i < this.header.count; ++i) {
         if (buffer[164 + i] == 0) {
            this.header.holes++;
         }
      }

      System.arraycopy(buffer, 164, this.header.cdata, 0, 512);
   }

   public static enum PERMISSION {
      SETUID(2048),
      SETGUI(1024),
      STICKY(512),
      USER_READ(256),
      USER_WRITE(128),
      USER_EXEC(64),
      GROUP_READ(32),
      GROUP_WRITE(16),
      GROUP_EXEC(8),
      WORLD_READ(4),
      WORLD_WRITE(2),
      WORLD_EXEC(1);

      private final int code;

      public static Set find(int code) {
         Set<PERMISSION> set = new HashSet();

         for(PERMISSION p : values()) {
            if ((code & p.code) == p.code) {
               set.add(p);
            }
         }

         if (set.isEmpty()) {
            return Collections.emptySet();
         } else {
            return EnumSet.copyOf(set);
         }
      }

      private PERMISSION(int code) {
         this.code = code;
      }

      // $FF: synthetic method
      private static PERMISSION[] $values() {
         return new PERMISSION[]{SETUID, SETGUI, STICKY, USER_READ, USER_WRITE, USER_EXEC, GROUP_READ, GROUP_WRITE, GROUP_EXEC, WORLD_READ, WORLD_WRITE, WORLD_EXEC};
      }
   }

   static class TapeSegmentHeader {
      private DumpArchiveConstants.SEGMENT_TYPE type;
      private int volume;
      private int ino;
      private int count;
      private int holes;
      private final byte[] cdata = new byte[512];

      public int getCdata(int idx) {
         return this.cdata[idx];
      }

      public int getCount() {
         return this.count;
      }

      public int getHoles() {
         return this.holes;
      }

      public int getIno() {
         return this.ino;
      }

      public DumpArchiveConstants.SEGMENT_TYPE getType() {
         return this.type;
      }

      public int getVolume() {
         return this.volume;
      }

      void setIno(int ino) {
         this.ino = ino;
      }
   }

   public static enum TYPE {
      WHITEOUT(14),
      SOCKET(12),
      LINK(10),
      FILE(8),
      BLKDEV(6),
      DIRECTORY(4),
      CHRDEV(2),
      FIFO(1),
      UNKNOWN(15);

      private final int code;

      public static TYPE find(int code) {
         TYPE type = UNKNOWN;

         for(TYPE t : values()) {
            if (code == t.code) {
               type = t;
            }
         }

         return type;
      }

      private TYPE(int code) {
         this.code = code;
      }

      // $FF: synthetic method
      private static TYPE[] $values() {
         return new TYPE[]{WHITEOUT, SOCKET, LINK, FILE, BLKDEV, DIRECTORY, CHRDEV, FIFO, UNKNOWN};
      }
   }
}
