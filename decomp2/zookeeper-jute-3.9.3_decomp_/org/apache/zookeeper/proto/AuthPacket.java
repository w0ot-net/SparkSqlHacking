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
public class AuthPacket implements Record {
   private int type;
   private String scheme;
   private byte[] auth;

   public AuthPacket() {
   }

   public AuthPacket(int type, String scheme, byte[] auth) {
      this.type = type;
      this.scheme = scheme;
      this.auth = auth;
   }

   public int getType() {
      return this.type;
   }

   public void setType(int m_) {
      this.type = m_;
   }

   public String getScheme() {
      return this.scheme;
   }

   public void setScheme(String m_) {
      this.scheme = m_;
   }

   public byte[] getAuth() {
      return this.auth;
   }

   public void setAuth(byte[] m_) {
      this.auth = m_;
   }

   public void serialize(OutputArchive a_, String tag) throws IOException {
      a_.startRecord(this, tag);
      a_.writeInt(this.type, "type");
      a_.writeString(this.scheme, "scheme");
      a_.writeBuffer(this.auth, "auth");
      a_.endRecord(this, tag);
   }

   public void deserialize(InputArchive a_, String tag) throws IOException {
      a_.startRecord(tag);
      this.type = a_.readInt("type");
      this.scheme = a_.readString("scheme");
      this.auth = a_.readBuffer("auth");
      a_.endRecord(tag);
   }

   public String toString() {
      try {
         ByteArrayOutputStream s = new ByteArrayOutputStream();
         ToStringOutputArchive a_ = new ToStringOutputArchive(s);
         a_.startRecord(this, "");
         a_.writeInt(this.type, "type");
         a_.writeString(this.scheme, "scheme");
         a_.writeBuffer(this.auth, "auth");
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
      if (!(peer_ instanceof AuthPacket)) {
         throw new ClassCastException("Comparing different types of records.");
      } else {
         AuthPacket peer = (AuthPacket)peer_;
         int ret = 0;
         ret = this.type == peer.type ? 0 : (this.type < peer.type ? -1 : 1);
         if (ret != 0) {
            return ret;
         } else {
            ret = this.scheme.compareTo(peer.scheme);
            if (ret != 0) {
               return ret;
            } else {
               byte[] my = this.auth;
               byte[] ur = peer.auth;
               ret = Utils.compareBytes(my, 0, my.length, ur, 0, ur.length);
               return ret != 0 ? ret : ret;
            }
         }
      }
   }

   public boolean equals(Object peer_) {
      if (!(peer_ instanceof AuthPacket)) {
         return false;
      } else if (peer_ == this) {
         return true;
      } else {
         AuthPacket peer = (AuthPacket)peer_;
         boolean ret = false;
         ret = this.type == peer.type;
         if (!ret) {
            return ret;
         } else {
            ret = this.scheme.equals(peer.scheme);
            if (!ret) {
               return ret;
            } else {
               ret = Arrays.equals(this.auth, peer.auth);
               return !ret ? ret : ret;
            }
         }
      }
   }

   public int hashCode() {
      int result = 17;
      int ret = this.type;
      result = 37 * result + ret;
      ret = this.scheme.hashCode();
      result = 37 * result + ret;
      ret = Arrays.hashCode(this.auth);
      result = 37 * result + ret;
      return result;
   }

   public static String signature() {
      return "LAuthPacket(isB)";
   }
}
