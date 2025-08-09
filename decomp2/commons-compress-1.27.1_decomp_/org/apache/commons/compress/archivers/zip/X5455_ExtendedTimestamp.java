package org.apache.commons.compress.archivers.zip;

import java.io.Serializable;
import java.nio.file.attribute.FileTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.zip.ZipException;
import org.apache.commons.compress.utils.TimeUtils;
import org.apache.commons.io.file.attribute.FileTimes;

public class X5455_ExtendedTimestamp implements ZipExtraField, Cloneable, Serializable {
   private static final long serialVersionUID = 1L;
   public static final ZipShort HEADER_ID = new ZipShort(21589);
   public static final byte MODIFY_TIME_BIT = 1;
   public static final byte ACCESS_TIME_BIT = 2;
   public static final byte CREATE_TIME_BIT = 4;
   private byte flags;
   private boolean bit0_modifyTimePresent;
   private boolean bit1_accessTimePresent;
   private boolean bit2_createTimePresent;
   private ZipLong modifyTime;
   private ZipLong accessTime;
   private ZipLong createTime;

   private static ZipLong dateToZipLong(Date d) {
      return d == null ? null : unixTimeToZipLong(d.getTime() / 1000L);
   }

   private static ZipLong fileTimeToZipLong(FileTime time) {
      return time == null ? null : unixTimeToZipLong(TimeUtils.toUnixTime(time));
   }

   private static FileTime unixTimeToFileTime(ZipLong unixTime) {
      return unixTime != null ? FileTimes.fromUnixTime((long)unixTime.getIntValue()) : null;
   }

   private static ZipLong unixTimeToZipLong(long unixTime) {
      if (!FileTimes.isUnixTime(unixTime)) {
         throw new IllegalArgumentException("X5455 timestamps must fit in a signed 32 bit integer: " + unixTime);
      } else {
         return new ZipLong(unixTime);
      }
   }

   private static Date zipLongToDate(ZipLong unixTime) {
      return unixTime != null ? new Date((long)unixTime.getIntValue() * 1000L) : null;
   }

   public Object clone() throws CloneNotSupportedException {
      return super.clone();
   }

   public boolean equals(Object o) {
      if (!(o instanceof X5455_ExtendedTimestamp)) {
         return false;
      } else {
         X5455_ExtendedTimestamp xf = (X5455_ExtendedTimestamp)o;
         return (this.flags & 7) == (xf.flags & 7) && Objects.equals(this.modifyTime, xf.modifyTime) && Objects.equals(this.accessTime, xf.accessTime) && Objects.equals(this.createTime, xf.createTime);
      }
   }

   public FileTime getAccessFileTime() {
      return unixTimeToFileTime(this.accessTime);
   }

   public Date getAccessJavaTime() {
      return zipLongToDate(this.accessTime);
   }

   public ZipLong getAccessTime() {
      return this.accessTime;
   }

   public byte[] getCentralDirectoryData() {
      return Arrays.copyOf(this.getLocalFileDataData(), this.getCentralDirectoryLength().getValue());
   }

   public ZipShort getCentralDirectoryLength() {
      return new ZipShort(1 + (this.bit0_modifyTimePresent ? 4 : 0));
   }

   public FileTime getCreateFileTime() {
      return unixTimeToFileTime(this.createTime);
   }

   public Date getCreateJavaTime() {
      return zipLongToDate(this.createTime);
   }

   public ZipLong getCreateTime() {
      return this.createTime;
   }

   public byte getFlags() {
      return this.flags;
   }

   public ZipShort getHeaderId() {
      return HEADER_ID;
   }

   public byte[] getLocalFileDataData() {
      byte[] data = new byte[this.getLocalFileDataLength().getValue()];
      int pos = 0;
      data[pos++] = 0;
      if (this.bit0_modifyTimePresent) {
         data[0] = (byte)(data[0] | 1);
         System.arraycopy(this.modifyTime.getBytes(), 0, data, pos, 4);
         pos += 4;
      }

      if (this.bit1_accessTimePresent && this.accessTime != null) {
         data[0] = (byte)(data[0] | 2);
         System.arraycopy(this.accessTime.getBytes(), 0, data, pos, 4);
         pos += 4;
      }

      if (this.bit2_createTimePresent && this.createTime != null) {
         data[0] = (byte)(data[0] | 4);
         System.arraycopy(this.createTime.getBytes(), 0, data, pos, 4);
         pos += 4;
      }

      return data;
   }

   public ZipShort getLocalFileDataLength() {
      return new ZipShort(1 + (this.bit0_modifyTimePresent ? 4 : 0) + (this.bit1_accessTimePresent && this.accessTime != null ? 4 : 0) + (this.bit2_createTimePresent && this.createTime != null ? 4 : 0));
   }

   public FileTime getModifyFileTime() {
      return unixTimeToFileTime(this.modifyTime);
   }

   public Date getModifyJavaTime() {
      return zipLongToDate(this.modifyTime);
   }

   public ZipLong getModifyTime() {
      return this.modifyTime;
   }

   public int hashCode() {
      int hc = -123 * (this.flags & 7);
      if (this.modifyTime != null) {
         hc ^= this.modifyTime.hashCode();
      }

      if (this.accessTime != null) {
         hc ^= Integer.rotateLeft(this.accessTime.hashCode(), 11);
      }

      if (this.createTime != null) {
         hc ^= Integer.rotateLeft(this.createTime.hashCode(), 22);
      }

      return hc;
   }

   public boolean isBit0_modifyTimePresent() {
      return this.bit0_modifyTimePresent;
   }

   public boolean isBit1_accessTimePresent() {
      return this.bit1_accessTimePresent;
   }

   public boolean isBit2_createTimePresent() {
      return this.bit2_createTimePresent;
   }

   public void parseFromCentralDirectoryData(byte[] buffer, int offset, int length) throws ZipException {
      this.reset();
      this.parseFromLocalFileData(buffer, offset, length);
   }

   public void parseFromLocalFileData(byte[] data, int offset, int length) throws ZipException {
      this.reset();
      if (length < 1) {
         throw new ZipException("X5455_ExtendedTimestamp too short, only " + length + " bytes");
      } else {
         int len = offset + length;
         this.setFlags(data[offset++]);
         if (this.bit0_modifyTimePresent && offset + 4 <= len) {
            this.modifyTime = new ZipLong(data, offset);
            offset += 4;
         } else {
            this.bit0_modifyTimePresent = false;
         }

         if (this.bit1_accessTimePresent && offset + 4 <= len) {
            this.accessTime = new ZipLong(data, offset);
            offset += 4;
         } else {
            this.bit1_accessTimePresent = false;
         }

         if (this.bit2_createTimePresent && offset + 4 <= len) {
            this.createTime = new ZipLong(data, offset);
            offset += 4;
         } else {
            this.bit2_createTimePresent = false;
         }

      }
   }

   private void reset() {
      this.setFlags((byte)0);
      this.modifyTime = null;
      this.accessTime = null;
      this.createTime = null;
   }

   public void setAccessFileTime(FileTime time) {
      this.setAccessTime(fileTimeToZipLong(time));
   }

   public void setAccessJavaTime(Date d) {
      this.setAccessTime(dateToZipLong(d));
   }

   public void setAccessTime(ZipLong l) {
      this.bit1_accessTimePresent = l != null;
      this.flags = (byte)(l != null ? this.flags | 2 : this.flags & -3);
      this.accessTime = l;
   }

   public void setCreateFileTime(FileTime time) {
      this.setCreateTime(fileTimeToZipLong(time));
   }

   public void setCreateJavaTime(Date d) {
      this.setCreateTime(dateToZipLong(d));
   }

   public void setCreateTime(ZipLong l) {
      this.bit2_createTimePresent = l != null;
      this.flags = (byte)(l != null ? this.flags | 4 : this.flags & -5);
      this.createTime = l;
   }

   public void setFlags(byte flags) {
      this.flags = flags;
      this.bit0_modifyTimePresent = (flags & 1) == 1;
      this.bit1_accessTimePresent = (flags & 2) == 2;
      this.bit2_createTimePresent = (flags & 4) == 4;
   }

   public void setModifyFileTime(FileTime time) {
      this.setModifyTime(fileTimeToZipLong(time));
   }

   public void setModifyJavaTime(Date d) {
      this.setModifyTime(dateToZipLong(d));
   }

   public void setModifyTime(ZipLong l) {
      this.bit0_modifyTimePresent = l != null;
      this.flags = (byte)(l != null ? this.flags | 1 : this.flags & -2);
      this.modifyTime = l;
   }

   public String toString() {
      StringBuilder buf = new StringBuilder();
      buf.append("0x5455 Zip Extra Field: Flags=");
      buf.append(Integer.toBinaryString(ZipUtil.unsignedIntToSignedByte(this.flags))).append(" ");
      if (this.bit0_modifyTimePresent && this.modifyTime != null) {
         Date m = this.getModifyJavaTime();
         buf.append(" Modify:[").append(m).append("] ");
      }

      if (this.bit1_accessTimePresent && this.accessTime != null) {
         Date a = this.getAccessJavaTime();
         buf.append(" Access:[").append(a).append("] ");
      }

      if (this.bit2_createTimePresent && this.createTime != null) {
         Date c = this.getCreateJavaTime();
         buf.append(" Create:[").append(c).append("] ");
      }

      return buf.toString();
   }
}
