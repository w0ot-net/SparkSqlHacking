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
public class SetSASLRequest implements Record {
   private byte[] token;

   public SetSASLRequest() {
   }

   public SetSASLRequest(byte[] token) {
      this.token = token;
   }

   public byte[] getToken() {
      return this.token;
   }

   public void setToken(byte[] m_) {
      this.token = m_;
   }

   public void serialize(OutputArchive a_, String tag) throws IOException {
      a_.startRecord(this, tag);
      a_.writeBuffer(this.token, "token");
      a_.endRecord(this, tag);
   }

   public void deserialize(InputArchive a_, String tag) throws IOException {
      a_.startRecord(tag);
      this.token = a_.readBuffer("token");
      a_.endRecord(tag);
   }

   public String toString() {
      try {
         ByteArrayOutputStream s = new ByteArrayOutputStream();
         ToStringOutputArchive a_ = new ToStringOutputArchive(s);
         a_.startRecord(this, "");
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
      if (!(peer_ instanceof SetSASLRequest)) {
         throw new ClassCastException("Comparing different types of records.");
      } else {
         SetSASLRequest peer = (SetSASLRequest)peer_;
         int ret = 0;
         byte[] my = this.token;
         byte[] ur = peer.token;
         ret = Utils.compareBytes(my, 0, my.length, ur, 0, ur.length);
         return ret != 0 ? ret : ret;
      }
   }

   public boolean equals(Object peer_) {
      if (!(peer_ instanceof SetSASLRequest)) {
         return false;
      } else if (peer_ == this) {
         return true;
      } else {
         SetSASLRequest peer = (SetSASLRequest)peer_;
         boolean ret = false;
         ret = Arrays.equals(this.token, peer.token);
         return !ret ? ret : ret;
      }
   }

   public int hashCode() {
      int result = 17;
      int ret = Arrays.hashCode(this.token);
      result = 37 * result + ret;
      return result;
   }

   public static String signature() {
      return "LSetSASLRequest(B)";
   }
}
