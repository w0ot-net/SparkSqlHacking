package org.apache.zookeeper.data;

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
public class ACL implements Record {
   private int perms;
   private Id id;

   public ACL() {
   }

   public ACL(int perms, Id id) {
      this.perms = perms;
      this.id = id;
   }

   public int getPerms() {
      return this.perms;
   }

   public void setPerms(int m_) {
      this.perms = m_;
   }

   public Id getId() {
      return this.id;
   }

   public void setId(Id m_) {
      this.id = m_;
   }

   public void serialize(OutputArchive a_, String tag) throws IOException {
      a_.startRecord(this, tag);
      a_.writeInt(this.perms, "perms");
      a_.writeRecord(this.id, "id");
      a_.endRecord(this, tag);
   }

   public void deserialize(InputArchive a_, String tag) throws IOException {
      a_.startRecord(tag);
      this.perms = a_.readInt("perms");
      this.id = new Id();
      a_.readRecord(this.id, "id");
      a_.endRecord(tag);
   }

   public String toString() {
      try {
         ByteArrayOutputStream s = new ByteArrayOutputStream();
         ToStringOutputArchive a_ = new ToStringOutputArchive(s);
         a_.startRecord(this, "");
         a_.writeInt(this.perms, "perms");
         a_.writeRecord(this.id, "id");
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
      if (!(peer_ instanceof ACL)) {
         throw new ClassCastException("Comparing different types of records.");
      } else {
         ACL peer = (ACL)peer_;
         int ret = 0;
         ret = this.perms == peer.perms ? 0 : (this.perms < peer.perms ? -1 : 1);
         if (ret != 0) {
            return ret;
         } else {
            ret = this.id.compareTo(peer.id);
            return ret != 0 ? ret : ret;
         }
      }
   }

   public boolean equals(Object peer_) {
      if (!(peer_ instanceof ACL)) {
         return false;
      } else if (peer_ == this) {
         return true;
      } else {
         ACL peer = (ACL)peer_;
         boolean ret = false;
         ret = this.perms == peer.perms;
         if (!ret) {
            return ret;
         } else {
            ret = this.id.equals(peer.id);
            return !ret ? ret : ret;
         }
      }
   }

   public int hashCode() {
      int result = 17;
      int ret = this.perms;
      result = 37 * result + ret;
      ret = this.id.hashCode();
      result = 37 * result + ret;
      return result;
   }

   public static String signature() {
      return "LACL(iLId(ss))";
   }
}
