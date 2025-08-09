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
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

@Public
public class GetACLResponse implements Record {
   private List acl;
   private Stat stat;

   public GetACLResponse() {
   }

   public GetACLResponse(List acl, Stat stat) {
      this.acl = acl;
      this.stat = stat;
   }

   public List getAcl() {
      return this.acl;
   }

   public void setAcl(List m_) {
      this.acl = m_;
   }

   public Stat getStat() {
      return this.stat;
   }

   public void setStat(Stat m_) {
      this.stat = m_;
   }

   public void serialize(OutputArchive a_, String tag) throws IOException {
      a_.startRecord(this, tag);
      a_.startVector(this.acl, "acl");
      if (this.acl != null) {
         int len1 = this.acl.size();

         for(int vidx1 = 0; vidx1 < len1; ++vidx1) {
            ACL e1 = (ACL)this.acl.get(vidx1);
            a_.writeRecord(e1, "e1");
         }
      }

      a_.endVector(this.acl, "acl");
      a_.writeRecord(this.stat, "stat");
      a_.endRecord(this, tag);
   }

   public void deserialize(InputArchive a_, String tag) throws IOException {
      a_.startRecord(tag);
      Index vidx1 = a_.startVector("acl");
      if (vidx1 != null) {
         this.acl = new ArrayList();

         while(!vidx1.done()) {
            ACL e1 = new ACL();
            a_.readRecord(e1, "e1");
            this.acl.add(e1);
            vidx1.incr();
         }
      }

      a_.endVector("acl");
      this.stat = new Stat();
      a_.readRecord(this.stat, "stat");
      a_.endRecord(tag);
   }

   public String toString() {
      try {
         ByteArrayOutputStream s = new ByteArrayOutputStream();
         ToStringOutputArchive a_ = new ToStringOutputArchive(s);
         a_.startRecord(this, "");
         a_.startVector(this.acl, "acl");
         if (this.acl != null) {
            int len1 = this.acl.size();

            for(int vidx1 = 0; vidx1 < len1; ++vidx1) {
               ACL e1 = (ACL)this.acl.get(vidx1);
               a_.writeRecord(e1, "e1");
            }
         }

         a_.endVector(this.acl, "acl");
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
      throw new UnsupportedOperationException("comparing GetACLResponse is unimplemented");
   }

   public boolean equals(Object peer_) {
      if (!(peer_ instanceof GetACLResponse)) {
         return false;
      } else if (peer_ == this) {
         return true;
      } else {
         GetACLResponse peer = (GetACLResponse)peer_;
         boolean ret = false;
         ret = this.acl.equals(peer.acl);
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
      int ret = this.acl.hashCode();
      result = 37 * result + ret;
      ret = this.stat.hashCode();
      result = 37 * result + ret;
      return result;
   }

   public static String signature() {
      return "LGetACLResponse([LACL(iLId(ss))]LStat(lllliiiliil))";
   }
}
