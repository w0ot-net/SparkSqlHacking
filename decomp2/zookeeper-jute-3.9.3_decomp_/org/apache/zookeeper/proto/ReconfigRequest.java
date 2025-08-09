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
public class ReconfigRequest implements Record {
   private String joiningServers;
   private String leavingServers;
   private String newMembers;
   private long curConfigId;

   public ReconfigRequest() {
   }

   public ReconfigRequest(String joiningServers, String leavingServers, String newMembers, long curConfigId) {
      this.joiningServers = joiningServers;
      this.leavingServers = leavingServers;
      this.newMembers = newMembers;
      this.curConfigId = curConfigId;
   }

   public String getJoiningServers() {
      return this.joiningServers;
   }

   public void setJoiningServers(String m_) {
      this.joiningServers = m_;
   }

   public String getLeavingServers() {
      return this.leavingServers;
   }

   public void setLeavingServers(String m_) {
      this.leavingServers = m_;
   }

   public String getNewMembers() {
      return this.newMembers;
   }

   public void setNewMembers(String m_) {
      this.newMembers = m_;
   }

   public long getCurConfigId() {
      return this.curConfigId;
   }

   public void setCurConfigId(long m_) {
      this.curConfigId = m_;
   }

   public void serialize(OutputArchive a_, String tag) throws IOException {
      a_.startRecord(this, tag);
      a_.writeString(this.joiningServers, "joiningServers");
      a_.writeString(this.leavingServers, "leavingServers");
      a_.writeString(this.newMembers, "newMembers");
      a_.writeLong(this.curConfigId, "curConfigId");
      a_.endRecord(this, tag);
   }

   public void deserialize(InputArchive a_, String tag) throws IOException {
      a_.startRecord(tag);
      this.joiningServers = a_.readString("joiningServers");
      this.leavingServers = a_.readString("leavingServers");
      this.newMembers = a_.readString("newMembers");
      this.curConfigId = a_.readLong("curConfigId");
      a_.endRecord(tag);
   }

   public String toString() {
      try {
         ByteArrayOutputStream s = new ByteArrayOutputStream();
         ToStringOutputArchive a_ = new ToStringOutputArchive(s);
         a_.startRecord(this, "");
         a_.writeString(this.joiningServers, "joiningServers");
         a_.writeString(this.leavingServers, "leavingServers");
         a_.writeString(this.newMembers, "newMembers");
         a_.writeLong(this.curConfigId, "curConfigId");
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
      if (!(peer_ instanceof ReconfigRequest)) {
         throw new ClassCastException("Comparing different types of records.");
      } else {
         ReconfigRequest peer = (ReconfigRequest)peer_;
         int ret = 0;
         ret = this.joiningServers.compareTo(peer.joiningServers);
         if (ret != 0) {
            return ret;
         } else {
            ret = this.leavingServers.compareTo(peer.leavingServers);
            if (ret != 0) {
               return ret;
            } else {
               ret = this.newMembers.compareTo(peer.newMembers);
               if (ret != 0) {
                  return ret;
               } else {
                  ret = this.curConfigId == peer.curConfigId ? 0 : (this.curConfigId < peer.curConfigId ? -1 : 1);
                  return ret != 0 ? ret : ret;
               }
            }
         }
      }
   }

   public boolean equals(Object peer_) {
      if (!(peer_ instanceof ReconfigRequest)) {
         return false;
      } else if (peer_ == this) {
         return true;
      } else {
         ReconfigRequest peer = (ReconfigRequest)peer_;
         boolean ret = false;
         ret = this.joiningServers.equals(peer.joiningServers);
         if (!ret) {
            return ret;
         } else {
            ret = this.leavingServers.equals(peer.leavingServers);
            if (!ret) {
               return ret;
            } else {
               ret = this.newMembers.equals(peer.newMembers);
               if (!ret) {
                  return ret;
               } else {
                  ret = this.curConfigId == peer.curConfigId;
                  return !ret ? ret : ret;
               }
            }
         }
      }
   }

   public int hashCode() {
      int result = 17;
      int ret = this.joiningServers.hashCode();
      result = 37 * result + ret;
      ret = this.leavingServers.hashCode();
      result = 37 * result + ret;
      ret = this.newMembers.hashCode();
      result = 37 * result + ret;
      ret = Long.hashCode(this.curConfigId);
      result = 37 * result + ret;
      return result;
   }

   public static String signature() {
      return "LReconfigRequest(sssl)";
   }
}
