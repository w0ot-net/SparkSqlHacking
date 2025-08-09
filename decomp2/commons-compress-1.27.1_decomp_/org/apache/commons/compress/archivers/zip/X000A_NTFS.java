package org.apache.commons.compress.archivers.zip;

import java.nio.file.attribute.FileTime;
import java.util.Date;
import java.util.Objects;
import java.util.zip.ZipException;
import org.apache.commons.io.file.attribute.FileTimes;

public class X000A_NTFS implements ZipExtraField {
   public static final ZipShort HEADER_ID = new ZipShort(10);
   private static final ZipShort TIME_ATTR_TAG = new ZipShort(1);
   private static final ZipShort TIME_ATTR_SIZE = new ZipShort(24);
   private ZipEightByteInteger modifyTime;
   private ZipEightByteInteger accessTime;
   private ZipEightByteInteger createTime;

   public X000A_NTFS() {
      this.modifyTime = ZipEightByteInteger.ZERO;
      this.accessTime = ZipEightByteInteger.ZERO;
      this.createTime = ZipEightByteInteger.ZERO;
   }

   private static ZipEightByteInteger dateToZip(Date d) {
      return d == null ? null : new ZipEightByteInteger(FileTimes.toNtfsTime(d));
   }

   private static ZipEightByteInteger fileTimeToZip(FileTime time) {
      return time == null ? null : new ZipEightByteInteger(FileTimes.toNtfsTime(time));
   }

   private static Date zipToDate(ZipEightByteInteger z) {
      return z != null && !ZipEightByteInteger.ZERO.equals(z) ? FileTimes.ntfsTimeToDate(z.getLongValue()) : null;
   }

   private static FileTime zipToFileTime(ZipEightByteInteger z) {
      return z != null && !ZipEightByteInteger.ZERO.equals(z) ? FileTimes.ntfsTimeToFileTime(z.getLongValue()) : null;
   }

   public boolean equals(Object o) {
      if (!(o instanceof X000A_NTFS)) {
         return false;
      } else {
         X000A_NTFS xf = (X000A_NTFS)o;
         return Objects.equals(this.modifyTime, xf.modifyTime) && Objects.equals(this.accessTime, xf.accessTime) && Objects.equals(this.createTime, xf.createTime);
      }
   }

   public FileTime getAccessFileTime() {
      return zipToFileTime(this.accessTime);
   }

   public Date getAccessJavaTime() {
      return zipToDate(this.accessTime);
   }

   public ZipEightByteInteger getAccessTime() {
      return this.accessTime;
   }

   public byte[] getCentralDirectoryData() {
      return this.getLocalFileDataData();
   }

   public ZipShort getCentralDirectoryLength() {
      return this.getLocalFileDataLength();
   }

   public FileTime getCreateFileTime() {
      return zipToFileTime(this.createTime);
   }

   public Date getCreateJavaTime() {
      return zipToDate(this.createTime);
   }

   public ZipEightByteInteger getCreateTime() {
      return this.createTime;
   }

   public ZipShort getHeaderId() {
      return HEADER_ID;
   }

   public byte[] getLocalFileDataData() {
      byte[] data = new byte[this.getLocalFileDataLength().getValue()];
      int pos = 4;
      System.arraycopy(TIME_ATTR_TAG.getBytes(), 0, data, pos, 2);
      pos += 2;
      System.arraycopy(TIME_ATTR_SIZE.getBytes(), 0, data, pos, 2);
      pos += 2;
      System.arraycopy(this.modifyTime.getBytes(), 0, data, pos, 8);
      pos += 8;
      System.arraycopy(this.accessTime.getBytes(), 0, data, pos, 8);
      pos += 8;
      System.arraycopy(this.createTime.getBytes(), 0, data, pos, 8);
      return data;
   }

   public ZipShort getLocalFileDataLength() {
      return new ZipShort(32);
   }

   public FileTime getModifyFileTime() {
      return zipToFileTime(this.modifyTime);
   }

   public Date getModifyJavaTime() {
      return zipToDate(this.modifyTime);
   }

   public ZipEightByteInteger getModifyTime() {
      return this.modifyTime;
   }

   public int hashCode() {
      int hc = -123;
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

   public void parseFromCentralDirectoryData(byte[] buffer, int offset, int length) throws ZipException {
      this.reset();
      this.parseFromLocalFileData(buffer, offset, length);
   }

   public void parseFromLocalFileData(byte[] data, int offset, int length) throws ZipException {
      int len = offset + length;

      ZipShort size;
      for(offset += 4; offset + 4 <= len; offset += 2 + size.getValue()) {
         ZipShort tag = new ZipShort(data, offset);
         offset += 2;
         if (tag.equals(TIME_ATTR_TAG)) {
            this.readTimeAttr(data, offset, len - offset);
            break;
         }

         size = new ZipShort(data, offset);
      }

   }

   private void readTimeAttr(byte[] data, int offset, int length) {
      if (length >= 26) {
         ZipShort tagValueLength = new ZipShort(data, offset);
         if (TIME_ATTR_SIZE.equals(tagValueLength)) {
            offset += 2;
            this.modifyTime = new ZipEightByteInteger(data, offset);
            offset += 8;
            this.accessTime = new ZipEightByteInteger(data, offset);
            offset += 8;
            this.createTime = new ZipEightByteInteger(data, offset);
         }
      }

   }

   private void reset() {
      this.modifyTime = ZipEightByteInteger.ZERO;
      this.accessTime = ZipEightByteInteger.ZERO;
      this.createTime = ZipEightByteInteger.ZERO;
   }

   public void setAccessFileTime(FileTime time) {
      this.setAccessTime(fileTimeToZip(time));
   }

   public void setAccessJavaTime(Date d) {
      this.setAccessTime(dateToZip(d));
   }

   public void setAccessTime(ZipEightByteInteger t) {
      this.accessTime = t == null ? ZipEightByteInteger.ZERO : t;
   }

   public void setCreateFileTime(FileTime time) {
      this.setCreateTime(fileTimeToZip(time));
   }

   public void setCreateJavaTime(Date d) {
      this.setCreateTime(dateToZip(d));
   }

   public void setCreateTime(ZipEightByteInteger t) {
      this.createTime = t == null ? ZipEightByteInteger.ZERO : t;
   }

   public void setModifyFileTime(FileTime time) {
      this.setModifyTime(fileTimeToZip(time));
   }

   public void setModifyJavaTime(Date d) {
      this.setModifyTime(dateToZip(d));
   }

   public void setModifyTime(ZipEightByteInteger t) {
      this.modifyTime = t == null ? ZipEightByteInteger.ZERO : t;
   }

   public String toString() {
      return "0x000A Zip Extra Field:" + " Modify:[" + this.getModifyFileTime() + "] " + " Access:[" + this.getAccessFileTime() + "] " + " Create:[" + this.getCreateFileTime() + "] ";
   }
}
