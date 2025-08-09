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
public class MultiHeader implements Record {
   private int type;
   private boolean done;
   private int err;

   public MultiHeader() {
   }

   public MultiHeader(int type, boolean done, int err) {
      this.type = type;
      this.done = done;
      this.err = err;
   }

   public int getType() {
      return this.type;
   }

   public void setType(int m_) {
      this.type = m_;
   }

   public boolean getDone() {
      return this.done;
   }

   public void setDone(boolean m_) {
      this.done = m_;
   }

   public int getErr() {
      return this.err;
   }

   public void setErr(int m_) {
      this.err = m_;
   }

   public void serialize(OutputArchive a_, String tag) throws IOException {
      a_.startRecord(this, tag);
      a_.writeInt(this.type, "type");
      a_.writeBool(this.done, "done");
      a_.writeInt(this.err, "err");
      a_.endRecord(this, tag);
   }

   public void deserialize(InputArchive a_, String tag) throws IOException {
      a_.startRecord(tag);
      this.type = a_.readInt("type");
      this.done = a_.readBool("done");
      this.err = a_.readInt("err");
      a_.endRecord(tag);
   }

   public String toString() {
      try {
         ByteArrayOutputStream s = new ByteArrayOutputStream();
         ToStringOutputArchive a_ = new ToStringOutputArchive(s);
         a_.startRecord(this, "");
         a_.writeInt(this.type, "type");
         a_.writeBool(this.done, "done");
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
      if (!(peer_ instanceof MultiHeader)) {
         throw new ClassCastException("Comparing different types of records.");
      } else {
         MultiHeader peer = (MultiHeader)peer_;
         int ret = 0;
         ret = this.type == peer.type ? 0 : (this.type < peer.type ? -1 : 1);
         if (ret != 0) {
            return ret;
         } else {
            ret = this.done == peer.done ? 0 : (this.done ? 1 : -1);
            if (ret != 0) {
               return ret;
            } else {
               ret = this.err == peer.err ? 0 : (this.err < peer.err ? -1 : 1);
               return ret != 0 ? ret : ret;
            }
         }
      }
   }

   public boolean equals(Object peer_) {
      if (!(peer_ instanceof MultiHeader)) {
         return false;
      } else if (peer_ == this) {
         return true;
      } else {
         MultiHeader peer = (MultiHeader)peer_;
         boolean ret = false;
         ret = this.type == peer.type;
         if (!ret) {
            return ret;
         } else {
            ret = this.done == peer.done;
            if (!ret) {
               return ret;
            } else {
               ret = this.err == peer.err;
               return !ret ? ret : ret;
            }
         }
      }
   }

   public int hashCode() {
      int result = 17;
      int ret = this.type;
      result = 37 * result + ret;
      ret = Boolean.hashCode(this.done);
      result = 37 * result + ret;
      ret = this.err;
      result = 37 * result + ret;
      return result;
   }

   public static String signature() {
      return "LMultiHeader(izi)";
   }
}
