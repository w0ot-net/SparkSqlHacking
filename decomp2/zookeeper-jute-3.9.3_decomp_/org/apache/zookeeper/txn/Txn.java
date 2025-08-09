package org.apache.zookeeper.txn;

import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import org.apache.jute.BinaryInputArchive;
import org.apache.jute.BinaryOutputArchive;
import org.apache.jute.InputArchive;
import org.apache.jute.OutputArchive;
import org.apache.jute.Record;
import org.apache.jute.ToStringOutputArchive;
import org.apache.jute.Utils;
import org.apache.yetus.audience.InterfaceAudience.Public;

@Public
public class Txn implements Record {
   private int type;
   private byte[] data;

   public Txn() {
   }

   public Txn(int type, byte[] data) {
      this.type = type;
      this.data = data;
   }

   public int getType() {
      return this.type;
   }

   public void setType(int m_) {
      this.type = m_;
   }

   public byte[] getData() {
      return this.data;
   }

   public void setData(byte[] m_) {
      this.data = m_;
   }

   public void serialize(OutputArchive a_, String tag) throws IOException {
      a_.startRecord(this, tag);
      a_.writeInt(this.type, "type");
      a_.writeBuffer(this.data, "data");
      a_.endRecord(this, tag);
   }

   public void deserialize(InputArchive a_, String tag) throws IOException {
      a_.startRecord(tag);
      this.type = a_.readInt("type");
      this.data = a_.readBuffer("data");
      a_.endRecord(tag);
   }

   public String toString() {
      try {
         ByteArrayOutputStream s = new ByteArrayOutputStream();
         ToStringOutputArchive a_ = new ToStringOutputArchive(s);
         a_.startRecord(this, "");
         a_.writeInt(this.type, "type");
         a_.writeBuffer(this.data, "data");
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
      if (!(peer_ instanceof Txn)) {
         throw new ClassCastException("Comparing different types of records.");
      } else {
         Txn peer = (Txn)peer_;
         int ret = 0;
         ret = this.type == peer.type ? 0 : (this.type < peer.type ? -1 : 1);
         if (ret != 0) {
            return ret;
         } else {
            byte[] my = this.data;
            byte[] ur = peer.data;
            ret = Utils.compareBytes(my, 0, my.length, ur, 0, ur.length);
            return ret != 0 ? ret : ret;
         }
      }
   }

   public boolean equals(Object peer_) {
      if (!(peer_ instanceof Txn)) {
         return false;
      } else if (peer_ == this) {
         return true;
      } else {
         Txn peer = (Txn)peer_;
         boolean ret = false;
         ret = this.type == peer.type;
         if (!ret) {
            return ret;
         } else {
            ret = Arrays.equals(this.data, peer.data);
            return !ret ? ret : ret;
         }
      }
   }

   public int hashCode() {
      int result = 17;
      int ret = this.type;
      result = 37 * result + ret;
      ret = Arrays.hashCode(this.data);
      result = 37 * result + ret;
      return result;
   }

   public static String signature() {
      return "LTxn(iB)";
   }
}
