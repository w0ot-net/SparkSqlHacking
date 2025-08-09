package org.apache.zookeeper.proto;

import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.apache.jute.BinaryInputArchive;
import org.apache.jute.BinaryOutputArchive;
import org.apache.jute.Index;
import org.apache.jute.InputArchive;
import org.apache.jute.OutputArchive;
import org.apache.jute.Record;
import org.apache.jute.ToStringOutputArchive;
import org.apache.yetus.audience.InterfaceAudience.Public;
import org.apache.zookeeper.data.Stat;

@Public
public class GetChildren2Response implements Record {
   private List children;
   private Stat stat;

   public GetChildren2Response() {
   }

   public GetChildren2Response(List children, Stat stat) {
      this.children = children;
      this.stat = stat;
   }

   public List getChildren() {
      return this.children;
   }

   public void setChildren(List m_) {
      this.children = m_;
   }

   public Stat getStat() {
      return this.stat;
   }

   public void setStat(Stat m_) {
      this.stat = m_;
   }

   public void serialize(OutputArchive a_, String tag) throws IOException {
      a_.startRecord(this, tag);
      a_.startVector(this.children, "children");
      if (this.children != null) {
         int len1 = this.children.size();

         for(int vidx1 = 0; vidx1 < len1; ++vidx1) {
            String e1 = (String)this.children.get(vidx1);
            a_.writeString(e1, "e1");
         }
      }

      a_.endVector(this.children, "children");
      a_.writeRecord(this.stat, "stat");
      a_.endRecord(this, tag);
   }

   public void deserialize(InputArchive a_, String tag) throws IOException {
      a_.startRecord(tag);
      Index vidx1 = a_.startVector("children");
      if (vidx1 != null) {
         this.children = new ArrayList();

         while(!vidx1.done()) {
            String e1 = a_.readString("e1");
            this.children.add(e1);
            vidx1.incr();
         }
      }

      a_.endVector("children");
      this.stat = new Stat();
      a_.readRecord(this.stat, "stat");
      a_.endRecord(tag);
   }

   public String toString() {
      try {
         ByteArrayOutputStream s = new ByteArrayOutputStream();
         ToStringOutputArchive a_ = new ToStringOutputArchive(s);
         a_.startRecord(this, "");
         a_.startVector(this.children, "children");
         if (this.children != null) {
            int len1 = this.children.size();

            for(int vidx1 = 0; vidx1 < len1; ++vidx1) {
               String e1 = (String)this.children.get(vidx1);
               a_.writeString(e1, "e1");
            }
         }

         a_.endVector(this.children, "children");
         a_.writeRecord(this.stat, "stat");
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
      throw new UnsupportedOperationException("comparing GetChildren2Response is unimplemented");
   }

   public boolean equals(Object peer_) {
      if (!(peer_ instanceof GetChildren2Response)) {
         return false;
      } else if (peer_ == this) {
         return true;
      } else {
         GetChildren2Response peer = (GetChildren2Response)peer_;
         boolean ret = false;
         ret = this.children.equals(peer.children);
         if (!ret) {
            return ret;
         } else {
            ret = this.stat.equals(peer.stat);
            return !ret ? ret : ret;
         }
      }
   }

   public int hashCode() {
      int result = 17;
      int ret = this.children.hashCode();
      result = 37 * result + ret;
      ret = this.stat.hashCode();
      result = 37 * result + ret;
      return result;
   }

   public static String signature() {
      return "LGetChildren2Response([s]LStat(lllliiiliil))";
   }
}
