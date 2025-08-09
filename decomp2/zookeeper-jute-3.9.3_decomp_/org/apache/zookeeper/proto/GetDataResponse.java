package org.apache.zookeeper.proto;

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
import org.apache.zookeeper.data.Stat;

@Public
public class GetDataResponse implements Record {
   private byte[] data;
   private Stat stat;

   public GetDataResponse() {
   }

   public GetDataResponse(byte[] data, Stat stat) {
      this.data = data;
      this.stat = stat;
   }

   public byte[] getData() {
      return this.data;
   }

   public void setData(byte[] m_) {
      this.data = m_;
   }

   public Stat getStat() {
      return this.stat;
   }

   public void setStat(Stat m_) {
      this.stat = m_;
   }

   public void serialize(OutputArchive a_, String tag) throws IOException {
      a_.startRecord(this, tag);
      a_.writeBuffer(this.data, "data");
      a_.writeRecord(this.stat, "stat");
      a_.endRecord(this, tag);
   }

   public void deserialize(InputArchive a_, String tag) throws IOException {
      a_.startRecord(tag);
      this.data = a_.readBuffer("data");
      this.stat = new Stat();
      a_.readRecord(this.stat, "stat");
      a_.endRecord(tag);
   }

   public String toString() {
      try {
         ByteArrayOutputStream s = new ByteArrayOutputStream();
         ToStringOutputArchive a_ = new ToStringOutputArchive(s);
         a_.startRecord(this, "");
         a_.writeBuffer(this.data, "data");
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
      if (!(peer_ instanceof GetDataResponse)) {
         throw new ClassCastException("Comparing different types of records.");
      } else {
         GetDataResponse peer = (GetDataResponse)peer_;
         int ret = 0;
         byte[] my = this.data;
         byte[] ur = peer.data;
         ret = Utils.compareBytes(my, 0, my.length, ur, 0, ur.length);
         if (ret != 0) {
            return ret;
         } else {
            ret = this.stat.compareTo(peer.stat);
            return ret != 0 ? ret : ret;
         }
      }
   }

   public boolean equals(Object peer_) {
      if (!(peer_ instanceof GetDataResponse)) {
         return false;
      } else if (peer_ == this) {
         return true;
      } else {
         GetDataResponse peer = (GetDataResponse)peer_;
         boolean ret = false;
         ret = Arrays.equals(this.data, peer.data);
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
      int ret = Arrays.hashCode(this.data);
      result = 37 * result + ret;
      ret = this.stat.hashCode();
      result = 37 * result + ret;
      return result;
   }

   public static String signature() {
      return "LGetDataResponse(BLStat(lllliiiliil))";
   }
}
