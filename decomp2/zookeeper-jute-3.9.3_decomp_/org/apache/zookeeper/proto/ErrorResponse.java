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
public class ErrorResponse implements Record {
   private int err;

   public ErrorResponse() {
   }

   public ErrorResponse(int err) {
      this.err = err;
   }

   public int getErr() {
      return this.err;
   }

   public void setErr(int m_) {
      this.err = m_;
   }

   public void serialize(OutputArchive a_, String tag) throws IOException {
      a_.startRecord(this, tag);
      a_.writeInt(this.err, "err");
      a_.endRecord(this, tag);
   }

   public void deserialize(InputArchive a_, String tag) throws IOException {
      a_.startRecord(tag);
      this.err = a_.readInt("err");
      a_.endRecord(tag);
   }

   public String toString() {
      try {
         ByteArrayOutputStream s = new ByteArrayOutputStream();
         ToStringOutputArchive a_ = new ToStringOutputArchive(s);
         a_.startRecord(this, "");
         a_.writeInt(this.err, "err");
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
      if (!(peer_ instanceof ErrorResponse)) {
         throw new ClassCastException("Comparing different types of records.");
      } else {
         ErrorResponse peer = (ErrorResponse)peer_;
         int ret = 0;
         ret = this.err == peer.err ? 0 : (this.err < peer.err ? -1 : 1);
         return ret != 0 ? ret : ret;
      }
   }

   public boolean equals(Object peer_) {
      if (!(peer_ instanceof ErrorResponse)) {
         return false;
      } else if (peer_ == this) {
         return true;
      } else {
         ErrorResponse peer = (ErrorResponse)peer_;
         boolean ret = false;
         ret = this.err == peer.err;
         return !ret ? ret : ret;
      }
   }

   public int hashCode() {
      int result = 17;
      int ret = this.err;
      result = 37 * result + ret;
      return result;
   }

   public static String signature() {
      return "LErrorResponse(i)";
   }
}
