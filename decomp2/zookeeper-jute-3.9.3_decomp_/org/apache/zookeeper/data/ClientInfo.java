package org.apache.zookeeper.data;

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
public class ClientInfo implements Record {
   private String authScheme;
   private String user;

   public ClientInfo() {
   }

   public ClientInfo(String authScheme, String user) {
      this.authScheme = authScheme;
      this.user = user;
   }

   public String getAuthScheme() {
      return this.authScheme;
   }

   public void setAuthScheme(String m_) {
      this.authScheme = m_;
   }

   public String getUser() {
      return this.user;
   }

   public void setUser(String m_) {
      this.user = m_;
   }

   public void serialize(OutputArchive a_, String tag) throws IOException {
      a_.startRecord(this, tag);
      a_.writeString(this.authScheme, "authScheme");
      a_.writeString(this.user, "user");
      a_.endRecord(this, tag);
   }

   public void deserialize(InputArchive a_, String tag) throws IOException {
      a_.startRecord(tag);
      this.authScheme = a_.readString("authScheme");
      this.user = a_.readString("user");
      a_.endRecord(tag);
   }

   public String toString() {
      try {
         ByteArrayOutputStream s = new ByteArrayOutputStream();
         ToStringOutputArchive a_ = new ToStringOutputArchive(s);
         a_.startRecord(this, "");
         a_.writeString(this.authScheme, "authScheme");
         a_.writeString(this.user, "user");
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
      if (!(peer_ instanceof ClientInfo)) {
         throw new ClassCastException("Comparing different types of records.");
      } else {
         ClientInfo peer = (ClientInfo)peer_;
         int ret = 0;
         ret = this.authScheme.compareTo(peer.authScheme);
         if (ret != 0) {
            return ret;
         } else {
            ret = this.user.compareTo(peer.user);
            return ret != 0 ? ret : ret;
         }
      }
   }

   public boolean equals(Object peer_) {
      if (!(peer_ instanceof ClientInfo)) {
         return false;
      } else if (peer_ == this) {
         return true;
      } else {
         ClientInfo peer = (ClientInfo)peer_;
         boolean ret = false;
         ret = this.authScheme.equals(peer.authScheme);
         if (!ret) {
            return ret;
         } else {
            ret = this.user.equals(peer.user);
            return !ret ? ret : ret;
         }
      }
   }

   public int hashCode() {
      int result = 17;
      int ret = this.authScheme.hashCode();
      result = 37 * result + ret;
      ret = this.user.hashCode();
      result = 37 * result + ret;
      return result;
   }

   public static String signature() {
      return "LClientInfo(ss)";
   }
}
