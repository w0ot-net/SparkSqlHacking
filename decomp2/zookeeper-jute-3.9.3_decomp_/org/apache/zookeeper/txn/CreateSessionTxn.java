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
public class CreateSessionTxn implements Record {
   private int timeOut;

   public CreateSessionTxn() {
   }

   public CreateSessionTxn(int timeOut) {
      this.timeOut = timeOut;
   }

   public int getTimeOut() {
      return this.timeOut;
   }

   public void setTimeOut(int m_) {
      this.timeOut = m_;
   }

   public void serialize(OutputArchive a_, String tag) throws IOException {
      a_.startRecord(this, tag);
      a_.writeInt(this.timeOut, "timeOut");
      a_.endRecord(this, tag);
   }

   public void deserialize(InputArchive a_, String tag) throws IOException {
      a_.startRecord(tag);
      this.timeOut = a_.readInt("timeOut");
      a_.endRecord(tag);
   }

   public String toString() {
      try {
         ByteArrayOutputStream s = new ByteArrayOutputStream();
         ToStringOutputArchive a_ = new ToStringOutputArchive(s);
         a_.startRecord(this, "");
         a_.writeInt(this.timeOut, "timeOut");
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
      if (!(peer_ instanceof CreateSessionTxn)) {
         throw new ClassCastException("Comparing different types of records.");
      } else {
         CreateSessionTxn peer = (CreateSessionTxn)peer_;
         int ret = 0;
         ret = this.timeOut == peer.timeOut ? 0 : (this.timeOut < peer.timeOut ? -1 : 1);
         return ret != 0 ? ret : ret;
      }
   }

   public boolean equals(Object peer_) {
      if (!(peer_ instanceof CreateSessionTxn)) {
         return false;
      } else if (peer_ == this) {
         return true;
      } else {
         CreateSessionTxn peer = (CreateSessionTxn)peer_;
         boolean ret = false;
         ret = this.timeOut == peer.timeOut;
         return !ret ? ret : ret;
      }
   }

   public int hashCode() {
      int result = 17;
      int ret = this.timeOut;
      result = 37 * result + ret;
      return result;
   }

   public static String signature() {
      return "LCreateSessionTxn(i)";
   }
}
