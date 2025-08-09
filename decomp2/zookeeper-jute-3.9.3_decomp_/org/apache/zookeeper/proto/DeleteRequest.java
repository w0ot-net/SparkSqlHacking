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
public class DeleteRequest implements Record {
   private String path;
   private int version;

   public DeleteRequest() {
   }

   public DeleteRequest(String path, int version) {
      this.path = path;
      this.version = version;
   }

   public String getPath() {
      return this.path;
   }

   public void setPath(String m_) {
      this.path = m_;
   }

   public int getVersion() {
      return this.version;
   }

   public void setVersion(int m_) {
      this.version = m_;
   }

   public void serialize(OutputArchive a_, String tag) throws IOException {
      a_.startRecord(this, tag);
      a_.writeString(this.path, "path");
      a_.writeInt(this.version, "version");
      a_.endRecord(this, tag);
   }

   public void deserialize(InputArchive a_, String tag) throws IOException {
      a_.startRecord(tag);
      this.path = a_.readString("path");
      this.version = a_.readInt("version");
      a_.endRecord(tag);
   }

   public String toString() {
      try {
         ByteArrayOutputStream s = new ByteArrayOutputStream();
         ToStringOutputArchive a_ = new ToStringOutputArchive(s);
         a_.startRecord(this, "");
         a_.writeString(this.path, "path");
         a_.writeInt(this.version, "version");
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
      if (!(peer_ instanceof DeleteRequest)) {
         throw new ClassCastException("Comparing different types of records.");
      } else {
         DeleteRequest peer = (DeleteRequest)peer_;
         int ret = 0;
         ret = this.path.compareTo(peer.path);
         if (ret != 0) {
            return ret;
         } else {
            ret = this.version == peer.version ? 0 : (this.version < peer.version ? -1 : 1);
            return ret != 0 ? ret : ret;
         }
      }
   }

   public boolean equals(Object peer_) {
      if (!(peer_ instanceof DeleteRequest)) {
         return false;
      } else if (peer_ == this) {
         return true;
      } else {
         DeleteRequest peer = (DeleteRequest)peer_;
         boolean ret = false;
         ret = this.path.equals(peer.path);
         if (!ret) {
            return ret;
         } else {
            ret = this.version == peer.version;
            return !ret ? ret : ret;
         }
      }
   }

   public int hashCode() {
      int result = 17;
      int ret = this.path.hashCode();
      result = 37 * result + ret;
      ret = this.version;
      result = 37 * result + ret;
      return result;
   }

   public static String signature() {
      return "LDeleteRequest(si)";
   }
}
