package org.apache.zookeeper.server.persistence;

import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.jute.BinaryInputArchive;
import org.apache.jute.BinaryOutputArchive;
import org.apache.jute.InputArchive;
import org.apache.jute.OutputArchive;
import org.apache.jute.Record;
import org.apache.jute.ToStringOutputArchive;
import org.apache.yetus.audience.InterfaceAudience.Public;

@Public
public class FileHeader implements Record {
   private int magic;
   private int version;
   private long dbid;

   public FileHeader() {
   }

   public FileHeader(int magic, int version, long dbid) {
      this.magic = magic;
      this.version = version;
      this.dbid = dbid;
   }

   public int getMagic() {
      return this.magic;
   }

   public void setMagic(int m_) {
      this.magic = m_;
   }

   public int getVersion() {
      return this.version;
   }

   public void setVersion(int m_) {
      this.version = m_;
   }

   public long getDbid() {
      return this.dbid;
   }

   public void setDbid(long m_) {
      this.dbid = m_;
   }

   public void serialize(OutputArchive a_, String tag) throws IOException {
      a_.startRecord(this, tag);
      a_.writeInt(this.magic, "magic");
      a_.writeInt(this.version, "version");
      a_.writeLong(this.dbid, "dbid");
      a_.endRecord(this, tag);
   }

   public void deserialize(InputArchive a_, String tag) throws IOException {
      a_.startRecord(tag);
      this.magic = a_.readInt("magic");
      this.version = a_.readInt("version");
      this.dbid = a_.readLong("dbid");
      a_.endRecord(tag);
   }

   public String toString() {
      try {
         ByteArrayOutputStream s = new ByteArrayOutputStream();
         ToStringOutputArchive a_ = new ToStringOutputArchive(s);
         a_.startRecord(this, "");
         a_.writeInt(this.magic, "magic");
         a_.writeInt(this.version, "version");
         a_.writeLong(this.dbid, "dbid");
         a_.endRecord(this, "");
         return new String(s.toByteArray(), StandardCharsets.UTF_8);
      } catch (Throwable ex) {
         ex.printStackTrace();
         return "ERROR";
      }
   }

   public void write(DataOutput out) throws IOException {
      BinaryOutputArchive archive = new BinaryOutputArchive(out);
      this.serialize(archive, "");
   }

   public void readFields(DataInput in) throws IOException {
      BinaryInputArchive archive = new BinaryInputArchive(in);
      this.deserialize(archive, "");
   }

   public int compareTo(Object peer_) throws ClassCastException {
      if (!(peer_ instanceof FileHeader)) {
         throw new ClassCastException("Comparing different types of records.");
      } else {
         FileHeader peer = (FileHeader)peer_;
         int ret = 0;
         ret = this.magic == peer.magic ? 0 : (this.magic < peer.magic ? -1 : 1);
         if (ret != 0) {
            return ret;
         } else {
            ret = this.version == peer.version ? 0 : (this.version < peer.version ? -1 : 1);
            if (ret != 0) {
               return ret;
            } else {
               ret = this.dbid == peer.dbid ? 0 : (this.dbid < peer.dbid ? -1 : 1);
               return ret != 0 ? ret : ret;
            }
         }
      }
   }

   public boolean equals(Object peer_) {
      if (!(peer_ instanceof FileHeader)) {
         return false;
      } else if (peer_ == this) {
         return true;
      } else {
         FileHeader peer = (FileHeader)peer_;
         boolean ret = false;
         ret = this.magic == peer.magic;
         if (!ret) {
            return ret;
         } else {
            ret = this.version == peer.version;
            if (!ret) {
               return ret;
            } else {
               ret = this.dbid == peer.dbid;
               return !ret ? ret : ret;
            }
         }
      }
   }

   public int hashCode() {
      int result = 17;
      int ret = this.magic;
      result = 37 * result + ret;
      ret = this.version;
      result = 37 * result + ret;
      ret = Long.hashCode(this.dbid);
      result = 37 * result + ret;
      return result;
   }

   public static String signature() {
      return "LFileHeader(iil)";
   }
}
