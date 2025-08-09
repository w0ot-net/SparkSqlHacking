package org.apache.zookeeper.server.quorum;

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
public class QuorumAuthPacket implements Record {
   private long magic;
   private int status;
   private byte[] token;

   public QuorumAuthPacket() {
   }

   public QuorumAuthPacket(long magic, int status, byte[] token) {
      this.magic = magic;
      this.status = status;
      this.token = token;
   }

   public long getMagic() {
      return this.magic;
   }

   public void setMagic(long m_) {
      this.magic = m_;
   }

   public int getStatus() {
      return this.status;
   }

   public void setStatus(int m_) {
      this.status = m_;
   }

   public byte[] getToken() {
      return this.token;
   }

   public void setToken(byte[] m_) {
      this.token = m_;
   }

   public void serialize(OutputArchive a_, String tag) throws IOException {
      a_.startRecord(this, tag);
      a_.writeLong(this.magic, "magic");
      a_.writeInt(this.status, "status");
      a_.writeBuffer(this.token, "token");
      a_.endRecord(this, tag);
   }

   public void deserialize(InputArchive a_, String tag) throws IOException {
      a_.startRecord(tag);
      this.magic = a_.readLong("magic");
      this.status = a_.readInt("status");
      this.token = a_.readBuffer("token");
      a_.endRecord(tag);
   }

   public String toString() {
      try {
         ByteArrayOutputStream s = new ByteArrayOutputStream();
         ToStringOutputArchive a_ = new ToStringOutputArchive(s);
         a_.startRecord(this, "");
         a_.writeLong(this.magic, "magic");
         a_.writeInt(this.status, "status");
         a_.writeBuffer(this.token, "token");
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
      if (!(peer_ instanceof QuorumAuthPacket)) {
         throw new ClassCastException("Comparing different types of records.");
      } else {
         QuorumAuthPacket peer = (QuorumAuthPacket)peer_;
         int ret = 0;
         ret = this.magic == peer.magic ? 0 : (this.magic < peer.magic ? -1 : 1);
         if (ret != 0) {
            return ret;
         } else {
            ret = this.status == peer.status ? 0 : (this.status < peer.status ? -1 : 1);
            if (ret != 0) {
               return ret;
            } else {
               byte[] my = this.token;
               byte[] ur = peer.token;
               ret = Utils.compareBytes(my, 0, my.length, ur, 0, ur.length);
               return ret != 0 ? ret : ret;
            }
         }
      }
   }

   public boolean equals(Object peer_) {
      if (!(peer_ instanceof QuorumAuthPacket)) {
         return false;
      } else if (peer_ == this) {
         return true;
      } else {
         QuorumAuthPacket peer = (QuorumAuthPacket)peer_;
         boolean ret = false;
         ret = this.magic == peer.magic;
         if (!ret) {
            return ret;
         } else {
            ret = this.status == peer.status;
            if (!ret) {
               return ret;
            } else {
               ret = Arrays.equals(this.token, peer.token);
               return !ret ? ret : ret;
            }
         }
      }
   }

   public int hashCode() {
      int result = 17;
      int ret = Long.hashCode(this.magic);
      result = 37 * result + ret;
      ret = this.status;
      result = 37 * result + ret;
      ret = Arrays.hashCode(this.token);
      result = 37 * result + ret;
      return result;
   }

   public static String signature() {
      return "LQuorumAuthPacket(liB)";
   }
}
