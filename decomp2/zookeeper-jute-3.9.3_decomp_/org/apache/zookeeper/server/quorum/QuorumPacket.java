package org.apache.zookeeper.server.quorum;

import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.jute.BinaryInputArchive;
import org.apache.jute.BinaryOutputArchive;
import org.apache.jute.Index;
import org.apache.jute.InputArchive;
import org.apache.jute.OutputArchive;
import org.apache.jute.Record;
import org.apache.jute.ToStringOutputArchive;
import org.apache.yetus.audience.InterfaceAudience.Public;
import org.apache.zookeeper.data.Id;

@Public
public class QuorumPacket implements Record {
   private int type;
   private long zxid;
   private byte[] data;
   private List authinfo;

   public QuorumPacket() {
   }

   public QuorumPacket(int type, long zxid, byte[] data, List authinfo) {
      this.type = type;
      this.zxid = zxid;
      this.data = data;
      this.authinfo = authinfo;
   }

   public int getType() {
      return this.type;
   }

   public void setType(int m_) {
      this.type = m_;
   }

   public long getZxid() {
      return this.zxid;
   }

   public void setZxid(long m_) {
      this.zxid = m_;
   }

   public byte[] getData() {
      return this.data;
   }

   public void setData(byte[] m_) {
      this.data = m_;
   }

   public List getAuthinfo() {
      return this.authinfo;
   }

   public void setAuthinfo(List m_) {
      this.authinfo = m_;
   }

   public void serialize(OutputArchive a_, String tag) throws IOException {
      a_.startRecord(this, tag);
      a_.writeInt(this.type, "type");
      a_.writeLong(this.zxid, "zxid");
      a_.writeBuffer(this.data, "data");
      a_.startVector(this.authinfo, "authinfo");
      if (this.authinfo != null) {
         int len1 = this.authinfo.size();

         for(int vidx1 = 0; vidx1 < len1; ++vidx1) {
            Id e1 = (Id)this.authinfo.get(vidx1);
            a_.writeRecord(e1, "e1");
         }
      }

      a_.endVector(this.authinfo, "authinfo");
      a_.endRecord(this, tag);
   }

   public void deserialize(InputArchive a_, String tag) throws IOException {
      a_.startRecord(tag);
      this.type = a_.readInt("type");
      this.zxid = a_.readLong("zxid");
      this.data = a_.readBuffer("data");
      Index vidx1 = a_.startVector("authinfo");
      if (vidx1 != null) {
         this.authinfo = new ArrayList();

         while(!vidx1.done()) {
            Id e1 = new Id();
            a_.readRecord(e1, "e1");
            this.authinfo.add(e1);
            vidx1.incr();
         }
      }

      a_.endVector("authinfo");
      a_.endRecord(tag);
   }

   public String toString() {
      try {
         ByteArrayOutputStream s = new ByteArrayOutputStream();
         ToStringOutputArchive a_ = new ToStringOutputArchive(s);
         a_.startRecord(this, "");
         a_.writeInt(this.type, "type");
         a_.writeLong(this.zxid, "zxid");
         a_.writeBuffer(this.data, "data");
         a_.startVector(this.authinfo, "authinfo");
         if (this.authinfo != null) {
            int len1 = this.authinfo.size();

            for(int vidx1 = 0; vidx1 < len1; ++vidx1) {
               Id e1 = (Id)this.authinfo.get(vidx1);
               a_.writeRecord(e1, "e1");
            }
         }

         a_.endVector(this.authinfo, "authinfo");
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
      throw new UnsupportedOperationException("comparing QuorumPacket is unimplemented");
   }

   public boolean equals(Object peer_) {
      if (!(peer_ instanceof QuorumPacket)) {
         return false;
      } else if (peer_ == this) {
         return true;
      } else {
         QuorumPacket peer = (QuorumPacket)peer_;
         boolean ret = false;
         ret = this.type == peer.type;
         if (!ret) {
            return ret;
         } else {
            ret = this.zxid == peer.zxid;
            if (!ret) {
               return ret;
            } else {
               ret = Arrays.equals(this.data, peer.data);
               if (!ret) {
                  return ret;
               } else {
                  ret = this.authinfo.equals(peer.authinfo);
                  return !ret ? ret : ret;
               }
            }
         }
      }
   }

   public int hashCode() {
      int result = 17;
      int ret = this.type;
      result = 37 * result + ret;
      ret = Long.hashCode(this.zxid);
      result = 37 * result + ret;
      ret = Arrays.hashCode(this.data);
      result = 37 * result + ret;
      ret = this.authinfo.hashCode();
      result = 37 * result + ret;
      return result;
   }

   public static String signature() {
      return "LQuorumPacket(ilB[LId(ss)])";
   }
}
