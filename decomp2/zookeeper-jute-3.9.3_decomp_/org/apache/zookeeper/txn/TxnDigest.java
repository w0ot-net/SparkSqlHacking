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
public class TxnDigest implements Record {
   private int version;
   private long treeDigest;

   public TxnDigest() {
   }

   public TxnDigest(int version, long treeDigest) {
      this.version = version;
      this.treeDigest = treeDigest;
   }

   public int getVersion() {
      return this.version;
   }

   public void setVersion(int m_) {
      this.version = m_;
   }

   public long getTreeDigest() {
      return this.treeDigest;
   }

   public void setTreeDigest(long m_) {
      this.treeDigest = m_;
   }

   public void serialize(OutputArchive a_, String tag) throws IOException {
      a_.startRecord(this, tag);
      a_.writeInt(this.version, "version");
      a_.writeLong(this.treeDigest, "treeDigest");
      a_.endRecord(this, tag);
   }

   public void deserialize(InputArchive a_, String tag) throws IOException {
      a_.startRecord(tag);
      this.version = a_.readInt("version");
      this.treeDigest = a_.readLong("treeDigest");
      a_.endRecord(tag);
   }

   public String toString() {
      try {
         ByteArrayOutputStream s = new ByteArrayOutputStream();
         ToStringOutputArchive a_ = new ToStringOutputArchive(s);
         a_.startRecord(this, "");
         a_.writeInt(this.version, "version");
         a_.writeLong(this.treeDigest, "treeDigest");
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
      if (!(peer_ instanceof TxnDigest)) {
         throw new ClassCastException("Comparing different types of records.");
      } else {
         TxnDigest peer = (TxnDigest)peer_;
         int ret = 0;
         ret = this.version == peer.version ? 0 : (this.version < peer.version ? -1 : 1);
         if (ret != 0) {
            return ret;
         } else {
            ret = this.treeDigest == peer.treeDigest ? 0 : (this.treeDigest < peer.treeDigest ? -1 : 1);
            return ret != 0 ? ret : ret;
         }
      }
   }

   public boolean equals(Object peer_) {
      if (!(peer_ instanceof TxnDigest)) {
         return false;
      } else if (peer_ == this) {
         return true;
      } else {
         TxnDigest peer = (TxnDigest)peer_;
         boolean ret = false;
         ret = this.version == peer.version;
         if (!ret) {
            return ret;
         } else {
            ret = this.treeDigest == peer.treeDigest;
            return !ret ? ret : ret;
         }
      }
   }

   public int hashCode() {
      int result = 17;
      int ret = this.version;
      result = 37 * result + ret;
      ret = Long.hashCode(this.treeDigest);
      result = 37 * result + ret;
      return result;
   }

   public static String signature() {
      return "LTxnDigest(il)";
   }
}
