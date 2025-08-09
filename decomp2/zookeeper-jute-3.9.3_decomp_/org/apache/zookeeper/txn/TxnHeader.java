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
public class TxnHeader implements Record {
   private long clientId;
   private int cxid;
   private long zxid;
   private long time;
   private int type;

   public TxnHeader() {
   }

   public TxnHeader(long clientId, int cxid, long zxid, long time, int type) {
      this.clientId = clientId;
      this.cxid = cxid;
      this.zxid = zxid;
      this.time = time;
      this.type = type;
   }

   public long getClientId() {
      return this.clientId;
   }

   public void setClientId(long m_) {
      this.clientId = m_;
   }

   public int getCxid() {
      return this.cxid;
   }

   public void setCxid(int m_) {
      this.cxid = m_;
   }

   public long getZxid() {
      return this.zxid;
   }

   public void setZxid(long m_) {
      this.zxid = m_;
   }

   public long getTime() {
      return this.time;
   }

   public void setTime(long m_) {
      this.time = m_;
   }

   public int getType() {
      return this.type;
   }

   public void setType(int m_) {
      this.type = m_;
   }

   public void serialize(OutputArchive a_, String tag) throws IOException {
      a_.startRecord(this, tag);
      a_.writeLong(this.clientId, "clientId");
      a_.writeInt(this.cxid, "cxid");
      a_.writeLong(this.zxid, "zxid");
      a_.writeLong(this.time, "time");
      a_.writeInt(this.type, "type");
      a_.endRecord(this, tag);
   }

   public void deserialize(InputArchive a_, String tag) throws IOException {
      a_.startRecord(tag);
      this.clientId = a_.readLong("clientId");
      this.cxid = a_.readInt("cxid");
      this.zxid = a_.readLong("zxid");
      this.time = a_.readLong("time");
      this.type = a_.readInt("type");
      a_.endRecord(tag);
   }

   public String toString() {
      try {
         ByteArrayOutputStream s = new ByteArrayOutputStream();
         ToStringOutputArchive a_ = new ToStringOutputArchive(s);
         a_.startRecord(this, "");
         a_.writeLong(this.clientId, "clientId");
         a_.writeInt(this.cxid, "cxid");
         a_.writeLong(this.zxid, "zxid");
         a_.writeLong(this.time, "time");
         a_.writeInt(this.type, "type");
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
      if (!(peer_ instanceof TxnHeader)) {
         throw new ClassCastException("Comparing different types of records.");
      } else {
         TxnHeader peer = (TxnHeader)peer_;
         int ret = 0;
         ret = this.clientId == peer.clientId ? 0 : (this.clientId < peer.clientId ? -1 : 1);
         if (ret != 0) {
            return ret;
         } else {
            ret = this.cxid == peer.cxid ? 0 : (this.cxid < peer.cxid ? -1 : 1);
            if (ret != 0) {
               return ret;
            } else {
               ret = this.zxid == peer.zxid ? 0 : (this.zxid < peer.zxid ? -1 : 1);
               if (ret != 0) {
                  return ret;
               } else {
                  ret = this.time == peer.time ? 0 : (this.time < peer.time ? -1 : 1);
                  if (ret != 0) {
                     return ret;
                  } else {
                     ret = this.type == peer.type ? 0 : (this.type < peer.type ? -1 : 1);
                     return ret != 0 ? ret : ret;
                  }
               }
            }
         }
      }
   }

   public boolean equals(Object peer_) {
      if (!(peer_ instanceof TxnHeader)) {
         return false;
      } else if (peer_ == this) {
         return true;
      } else {
         TxnHeader peer = (TxnHeader)peer_;
         boolean ret = false;
         ret = this.clientId == peer.clientId;
         if (!ret) {
            return ret;
         } else {
            ret = this.cxid == peer.cxid;
            if (!ret) {
               return ret;
            } else {
               ret = this.zxid == peer.zxid;
               if (!ret) {
                  return ret;
               } else {
                  ret = this.time == peer.time;
                  if (!ret) {
                     return ret;
                  } else {
                     ret = this.type == peer.type;
                     return !ret ? ret : ret;
                  }
               }
            }
         }
      }
   }

   public int hashCode() {
      int result = 17;
      int ret = Long.hashCode(this.clientId);
      result = 37 * result + ret;
      ret = this.cxid;
      result = 37 * result + ret;
      ret = Long.hashCode(this.zxid);
      result = 37 * result + ret;
      ret = Long.hashCode(this.time);
      result = 37 * result + ret;
      ret = this.type;
      result = 37 * result + ret;
      return result;
   }

   public static String signature() {
      return "LTxnHeader(lilli)";
   }
}
