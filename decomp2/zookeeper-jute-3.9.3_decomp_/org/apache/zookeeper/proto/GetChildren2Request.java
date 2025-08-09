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

@Public
public class GetChildren2Request implements Record {
   private String path;
   private boolean watch;

   public GetChildren2Request() {
   }

   public GetChildren2Request(String path, boolean watch) {
      this.path = path;
      this.watch = watch;
   }

   public String getPath() {
      return this.path;
   }

   public void setPath(String m_) {
      this.path = m_;
   }

   public boolean getWatch() {
      return this.watch;
   }

   public void setWatch(boolean m_) {
      this.watch = m_;
   }

   public void serialize(OutputArchive a_, String tag) throws IOException {
      a_.startRecord(this, tag);
      a_.writeString(this.path, "path");
      a_.writeBool(this.watch, "watch");
      a_.endRecord(this, tag);
   }

   public void deserialize(InputArchive a_, String tag) throws IOException {
      a_.startRecord(tag);
      this.path = a_.readString("path");
      this.watch = a_.readBool("watch");
      a_.endRecord(tag);
   }

   public String toString() {
      try {
         ByteArrayOutputStream s = new ByteArrayOutputStream();
         ToStringOutputArchive a_ = new ToStringOutputArchive(s);
         a_.startRecord(this, "");
         a_.writeString(this.path, "path");
         a_.writeBool(this.watch, "watch");
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
      if (!(peer_ instanceof GetChildren2Request)) {
         throw new ClassCastException("Comparing different types of records.");
      } else {
         GetChildren2Request peer = (GetChildren2Request)peer_;
         int ret = 0;
         ret = this.path.compareTo(peer.path);
         if (ret != 0) {
            return ret;
         } else {
            ret = this.watch == peer.watch ? 0 : (this.watch ? 1 : -1);
            return ret != 0 ? ret : ret;
         }
      }
   }

   public boolean equals(Object peer_) {
      if (!(peer_ instanceof GetChildren2Request)) {
         return false;
      } else if (peer_ == this) {
         return true;
      } else {
         GetChildren2Request peer = (GetChildren2Request)peer_;
         boolean ret = false;
         ret = this.path.equals(peer.path);
         if (!ret) {
            return ret;
         } else {
            ret = this.watch == peer.watch;
            return !ret ? ret : ret;
         }
      }
   }

   public int hashCode() {
      int result = 17;
      int ret = this.path.hashCode();
      result = 37 * result + ret;
      ret = Boolean.hashCode(this.watch);
      result = 37 * result + ret;
      return result;
   }

   public static String signature() {
      return "LGetChildren2Request(sz)";
   }
}
