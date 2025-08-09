package org.apache.zookeeper.proto;

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
import org.apache.zookeeper.data.Stat;

@Public
public class Create2Response implements Record {
   private String path;
   private Stat stat;

   public Create2Response() {
   }

   public Create2Response(String path, Stat stat) {
      this.path = path;
      this.stat = stat;
   }

   public String getPath() {
      return this.path;
   }

   public void setPath(String m_) {
      this.path = m_;
   }

   public Stat getStat() {
      return this.stat;
   }

   public void setStat(Stat m_) {
      this.stat = m_;
   }

   public void serialize(OutputArchive a_, String tag) throws IOException {
      a_.startRecord(this, tag);
      a_.writeString(this.path, "path");
      a_.writeRecord(this.stat, "stat");
      a_.endRecord(this, tag);
   }

   public void deserialize(InputArchive a_, String tag) throws IOException {
      a_.startRecord(tag);
      this.path = a_.readString("path");
      this.stat = new Stat();
      a_.readRecord(this.stat, "stat");
      a_.endRecord(tag);
   }

   public String toString() {
      try {
         ByteArrayOutputStream s = new ByteArrayOutputStream();
         ToStringOutputArchive a_ = new ToStringOutputArchive(s);
         a_.startRecord(this, "");
         a_.writeString(this.path, "path");
         a_.writeRecord(this.stat, "stat");
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
      if (!(peer_ instanceof Create2Response)) {
         throw new ClassCastException("Comparing different types of records.");
      } else {
         Create2Response peer = (Create2Response)peer_;
         int ret = 0;
         ret = this.path.compareTo(peer.path);
         if (ret != 0) {
            return ret;
         } else {
            ret = this.stat.compareTo(peer.stat);
            return ret != 0 ? ret : ret;
         }
      }
   }

   public boolean equals(Object peer_) {
      if (!(peer_ instanceof Create2Response)) {
         return false;
      } else if (peer_ == this) {
         return true;
      } else {
         Create2Response peer = (Create2Response)peer_;
         boolean ret = false;
         ret = this.path.equals(peer.path);
         if (!ret) {
            return ret;
         } else {
            ret = this.stat.equals(peer.stat);
            return !ret ? ret : ret;
         }
      }
   }

   public int hashCode() {
      int result = 17;
      int ret = this.path.hashCode();
      result = 37 * result + ret;
      ret = this.stat.hashCode();
      result = 37 * result + ret;
      return result;
   }

   public static String signature() {
      return "LCreate2Response(sLStat(lllliiiliil))";
   }
}
