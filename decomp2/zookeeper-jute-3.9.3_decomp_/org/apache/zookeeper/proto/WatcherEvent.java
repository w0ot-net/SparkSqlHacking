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
public class WatcherEvent implements Record {
   private int type;
   private int state;
   private String path;

   public WatcherEvent() {
   }

   public WatcherEvent(int type, int state, String path) {
      this.type = type;
      this.state = state;
      this.path = path;
   }

   public int getType() {
      return this.type;
   }

   public void setType(int m_) {
      this.type = m_;
   }

   public int getState() {
      return this.state;
   }

   public void setState(int m_) {
      this.state = m_;
   }

   public String getPath() {
      return this.path;
   }

   public void setPath(String m_) {
      this.path = m_;
   }

   public void serialize(OutputArchive a_, String tag) throws IOException {
      a_.startRecord(this, tag);
      a_.writeInt(this.type, "type");
      a_.writeInt(this.state, "state");
      a_.writeString(this.path, "path");
      a_.endRecord(this, tag);
   }

   public void deserialize(InputArchive a_, String tag) throws IOException {
      a_.startRecord(tag);
      this.type = a_.readInt("type");
      this.state = a_.readInt("state");
      this.path = a_.readString("path");
      a_.endRecord(tag);
   }

   public String toString() {
      try {
         ByteArrayOutputStream s = new ByteArrayOutputStream();
         ToStringOutputArchive a_ = new ToStringOutputArchive(s);
         a_.startRecord(this, "");
         a_.writeInt(this.type, "type");
         a_.writeInt(this.state, "state");
         a_.writeString(this.path, "path");
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
      if (!(peer_ instanceof WatcherEvent)) {
         throw new ClassCastException("Comparing different types of records.");
      } else {
         WatcherEvent peer = (WatcherEvent)peer_;
         int ret = 0;
         ret = this.type == peer.type ? 0 : (this.type < peer.type ? -1 : 1);
         if (ret != 0) {
            return ret;
         } else {
            ret = this.state == peer.state ? 0 : (this.state < peer.state ? -1 : 1);
            if (ret != 0) {
               return ret;
            } else {
               ret = this.path.compareTo(peer.path);
               return ret != 0 ? ret : ret;
            }
         }
      }
   }

   public boolean equals(Object peer_) {
      if (!(peer_ instanceof WatcherEvent)) {
         return false;
      } else if (peer_ == this) {
         return true;
      } else {
         WatcherEvent peer = (WatcherEvent)peer_;
         boolean ret = false;
         ret = this.type == peer.type;
         if (!ret) {
            return ret;
         } else {
            ret = this.state == peer.state;
            if (!ret) {
               return ret;
            } else {
               ret = this.path.equals(peer.path);
               return !ret ? ret : ret;
            }
         }
      }
   }

   public int hashCode() {
      int result = 17;
      int ret = this.type;
      result = 37 * result + ret;
      ret = this.state;
      result = 37 * result + ret;
      ret = this.path.hashCode();
      result = 37 * result + ret;
      return result;
   }

   public static String signature() {
      return "LWatcherEvent(iis)";
   }
}
