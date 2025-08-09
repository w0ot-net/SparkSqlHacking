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

@Public
public class GetChildrenResponse implements Record {
   private List children;

   public GetChildrenResponse() {
   }

   public GetChildrenResponse(List children) {
      this.children = children;
   }

   public List getChildren() {
      return this.children;
   }

   public void setChildren(List m_) {
      this.children = m_;
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
      throw new UnsupportedOperationException("comparing GetChildrenResponse is unimplemented");
   }

   public boolean equals(Object peer_) {
      if (!(peer_ instanceof GetChildrenResponse)) {
         return false;
      } else if (peer_ == this) {
         return true;
      } else {
         GetChildrenResponse peer = (GetChildrenResponse)peer_;
         boolean ret = false;
         ret = this.children.equals(peer.children);
         return !ret ? ret : ret;
      }
   }

   public int hashCode() {
      int result = 17;
      int ret = this.children.hashCode();
      result = 37 * result + ret;
      return result;
   }

   public static String signature() {
      return "LGetChildrenResponse([s])";
   }
}
