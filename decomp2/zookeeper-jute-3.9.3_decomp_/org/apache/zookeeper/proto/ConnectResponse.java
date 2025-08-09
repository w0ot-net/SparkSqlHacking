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

@Public
public class ConnectResponse implements Record {
   private int protocolVersion;
   private int timeOut;
   private long sessionId;
   private byte[] passwd;
   private boolean readOnly;

   public ConnectResponse() {
   }

   public ConnectResponse(int protocolVersion, int timeOut, long sessionId, byte[] passwd, boolean readOnly) {
      this.protocolVersion = protocolVersion;
      this.timeOut = timeOut;
      this.sessionId = sessionId;
      this.passwd = passwd;
      this.readOnly = readOnly;
   }

   public int getProtocolVersion() {
      return this.protocolVersion;
   }

   public void setProtocolVersion(int m_) {
      this.protocolVersion = m_;
   }

   public int getTimeOut() {
      return this.timeOut;
   }

   public void setTimeOut(int m_) {
      this.timeOut = m_;
   }

   public long getSessionId() {
      return this.sessionId;
   }

   public void setSessionId(long m_) {
      this.sessionId = m_;
   }

   public byte[] getPasswd() {
      return this.passwd;
   }

   public void setPasswd(byte[] m_) {
      this.passwd = m_;
   }

   public boolean getReadOnly() {
      return this.readOnly;
   }

   public void setReadOnly(boolean m_) {
      this.readOnly = m_;
   }

   public void serialize(OutputArchive a_, String tag) throws IOException {
      a_.startRecord(this, tag);
      a_.writeInt(this.protocolVersion, "protocolVersion");
      a_.writeInt(this.timeOut, "timeOut");
      a_.writeLong(this.sessionId, "sessionId");
      a_.writeBuffer(this.passwd, "passwd");
      a_.writeBool(this.readOnly, "readOnly");
      a_.endRecord(this, tag);
   }

   public void deserialize(InputArchive a_, String tag) throws IOException {
      a_.startRecord(tag);
      this.protocolVersion = a_.readInt("protocolVersion");
      this.timeOut = a_.readInt("timeOut");
      this.sessionId = a_.readLong("sessionId");
      this.passwd = a_.readBuffer("passwd");
      this.readOnly = a_.readBool("readOnly");
      a_.endRecord(tag);
   }

   public String toString() {
      try {
         ByteArrayOutputStream s = new ByteArrayOutputStream();
         ToStringOutputArchive a_ = new ToStringOutputArchive(s);
         a_.startRecord(this, "");
         a_.writeInt(this.protocolVersion, "protocolVersion");
         a_.writeInt(this.timeOut, "timeOut");
         a_.writeLong(this.sessionId, "sessionId");
         a_.writeBuffer(this.passwd, "passwd");
         a_.writeBool(this.readOnly, "readOnly");
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
      if (!(peer_ instanceof ConnectResponse)) {
         throw new ClassCastException("Comparing different types of records.");
      } else {
         ConnectResponse peer = (ConnectResponse)peer_;
         int ret = 0;
         ret = this.protocolVersion == peer.protocolVersion ? 0 : (this.protocolVersion < peer.protocolVersion ? -1 : 1);
         if (ret != 0) {
            return ret;
         } else {
            ret = this.timeOut == peer.timeOut ? 0 : (this.timeOut < peer.timeOut ? -1 : 1);
            if (ret != 0) {
               return ret;
            } else {
               ret = this.sessionId == peer.sessionId ? 0 : (this.sessionId < peer.sessionId ? -1 : 1);
               if (ret != 0) {
                  return ret;
               } else {
                  byte[] my = this.passwd;
                  byte[] ur = peer.passwd;
                  ret = Utils.compareBytes(my, 0, my.length, ur, 0, ur.length);
                  if (ret != 0) {
                     return ret;
                  } else {
                     ret = this.readOnly == peer.readOnly ? 0 : (this.readOnly ? 1 : -1);
                     return ret != 0 ? ret : ret;
                  }
               }
            }
         }
      }
   }

   public boolean equals(Object peer_) {
      if (!(peer_ instanceof ConnectResponse)) {
         return false;
      } else if (peer_ == this) {
         return true;
      } else {
         ConnectResponse peer = (ConnectResponse)peer_;
         boolean ret = false;
         ret = this.protocolVersion == peer.protocolVersion;
         if (!ret) {
            return ret;
         } else {
            ret = this.timeOut == peer.timeOut;
            if (!ret) {
               return ret;
            } else {
               ret = this.sessionId == peer.sessionId;
               if (!ret) {
                  return ret;
               } else {
                  ret = Arrays.equals(this.passwd, peer.passwd);
                  if (!ret) {
                     return ret;
                  } else {
                     ret = this.readOnly == peer.readOnly;
                     return !ret ? ret : ret;
                  }
               }
            }
         }
      }
   }

   public int hashCode() {
      int result = 17;
      int ret = this.protocolVersion;
      result = 37 * result + ret;
      ret = this.timeOut;
      result = 37 * result + ret;
      ret = Long.hashCode(this.sessionId);
      result = 37 * result + ret;
      ret = Arrays.hashCode(this.passwd);
      result = 37 * result + ret;
      ret = Boolean.hashCode(this.readOnly);
      result = 37 * result + ret;
      return result;
   }

   public static String signature() {
      return "LConnectResponse(iilBz)";
   }
}
