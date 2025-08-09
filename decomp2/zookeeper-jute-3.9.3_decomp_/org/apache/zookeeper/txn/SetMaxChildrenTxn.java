package org.apache.zookeeper.txn;

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
public class SetMaxChildrenTxn implements Record {
   private String path;
   private int max;

   public SetMaxChildrenTxn() {
   }

   public SetMaxChildrenTxn(String path, int max) {
      this.path = path;
      this.max = max;
   }

   public String getPath() {
      return this.path;
   }

   public void setPath(String m_) {
      this.path = m_;
   }

   public int getMax() {
      return this.max;
   }

   public void setMax(int m_) {
      this.max = m_;
   }

   public void serialize(OutputArchive a_, String tag) throws IOException {
      a_.startRecord(this, tag);
      a_.writeString(this.path, "path");
      a_.writeInt(this.max, "max");
      a_.endRecord(this, tag);
   }

   public void deserialize(InputArchive a_, String tag) throws IOException {
      a_.startRecord(tag);
      this.path = a_.readString("path");
      this.max = a_.readInt("max");
      a_.endRecord(tag);
   }

   public String toString() {
      try {
         ByteArrayOutputStream s = new ByteArrayOutputStream();
         ToStringOutputArchive a_ = new ToStringOutputArchive(s);
         a_.startRecord(this, "");
         a_.writeString(this.path, "path");
         a_.writeInt(this.max, "max");
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
      if (!(peer_ instanceof SetMaxChildrenTxn)) {
         throw new ClassCastException("Comparing different types of records.");
      } else {
         SetMaxChildrenTxn peer = (SetMaxChildrenTxn)peer_;
         int ret = 0;
         ret = this.path.compareTo(peer.path);
         if (ret != 0) {
            return ret;
         } else {
            ret = this.max == peer.max ? 0 : (this.max < peer.max ? -1 : 1);
            return ret != 0 ? ret : ret;
         }
      }
   }

   public boolean equals(Object peer_) {
      if (!(peer_ instanceof SetMaxChildrenTxn)) {
         return false;
      } else if (peer_ == this) {
         return true;
      } else {
         SetMaxChildrenTxn peer = (SetMaxChildrenTxn)peer_;
         boolean ret = false;
         ret = this.path.equals(peer.path);
         if (!ret) {
            return ret;
         } else {
            ret = this.max == peer.max;
            return !ret ? ret : ret;
         }
      }
   }

   public int hashCode() {
      int result = 17;
      int ret = this.path.hashCode();
      result = 37 * result + ret;
      ret = this.max;
      result = 37 * result + ret;
      return result;
   }

   public static String signature() {
      return "LSetMaxChildrenTxn(si)";
   }
}
