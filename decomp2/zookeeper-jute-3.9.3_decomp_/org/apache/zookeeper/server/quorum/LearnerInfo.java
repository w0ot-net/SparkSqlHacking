package org.apache.zookeeper.server.quorum;

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
public class LearnerInfo implements Record {
   private long serverid;
   private int protocolVersion;
   private long configVersion;

   public LearnerInfo() {
   }

   public LearnerInfo(long serverid, int protocolVersion, long configVersion) {
      this.serverid = serverid;
      this.protocolVersion = protocolVersion;
      this.configVersion = configVersion;
   }

   public long getServerid() {
      return this.serverid;
   }

   public void setServerid(long m_) {
      this.serverid = m_;
   }

   public int getProtocolVersion() {
      return this.protocolVersion;
   }

   public void setProtocolVersion(int m_) {
      this.protocolVersion = m_;
   }

   public long getConfigVersion() {
      return this.configVersion;
   }

   public void setConfigVersion(long m_) {
      this.configVersion = m_;
   }

   public void serialize(OutputArchive a_, String tag) throws IOException {
      a_.startRecord(this, tag);
      a_.writeLong(this.serverid, "serverid");
      a_.writeInt(this.protocolVersion, "protocolVersion");
      a_.writeLong(this.configVersion, "configVersion");
      a_.endRecord(this, tag);
   }

   public void deserialize(InputArchive a_, String tag) throws IOException {
      a_.startRecord(tag);
      this.serverid = a_.readLong("serverid");
      this.protocolVersion = a_.readInt("protocolVersion");
      this.configVersion = a_.readLong("configVersion");
      a_.endRecord(tag);
   }

   public String toString() {
      try {
         ByteArrayOutputStream s = new ByteArrayOutputStream();
         ToStringOutputArchive a_ = new ToStringOutputArchive(s);
         a_.startRecord(this, "");
         a_.writeLong(this.serverid, "serverid");
         a_.writeInt(this.protocolVersion, "protocolVersion");
         a_.writeLong(this.configVersion, "configVersion");
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
      if (!(peer_ instanceof LearnerInfo)) {
         throw new ClassCastException("Comparing different types of records.");
      } else {
         LearnerInfo peer = (LearnerInfo)peer_;
         int ret = 0;
         ret = this.serverid == peer.serverid ? 0 : (this.serverid < peer.serverid ? -1 : 1);
         if (ret != 0) {
            return ret;
         } else {
            ret = this.protocolVersion == peer.protocolVersion ? 0 : (this.protocolVersion < peer.protocolVersion ? -1 : 1);
            if (ret != 0) {
               return ret;
            } else {
               ret = this.configVersion == peer.configVersion ? 0 : (this.configVersion < peer.configVersion ? -1 : 1);
               return ret != 0 ? ret : ret;
            }
         }
      }
   }

   public boolean equals(Object peer_) {
      if (!(peer_ instanceof LearnerInfo)) {
         return false;
      } else if (peer_ == this) {
         return true;
      } else {
         LearnerInfo peer = (LearnerInfo)peer_;
         boolean ret = false;
         ret = this.serverid == peer.serverid;
         if (!ret) {
            return ret;
         } else {
            ret = this.protocolVersion == peer.protocolVersion;
            if (!ret) {
               return ret;
            } else {
               ret = this.configVersion == peer.configVersion;
               return !ret ? ret : ret;
            }
         }
      }
   }

   public int hashCode() {
      int result = 17;
      int ret = Long.hashCode(this.serverid);
      result = 37 * result + ret;
      ret = this.protocolVersion;
      result = 37 * result + ret;
      ret = Long.hashCode(this.configVersion);
      result = 37 * result + ret;
      return result;
   }

   public static String signature() {
      return "LLearnerInfo(lil)";
   }
}
