package org.apache.zookeeper.txn;

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

@Public
public class SetACLTxn implements Record {
   private String path;
   private List acl;
   private int version;

   public SetACLTxn() {
   }

   public SetACLTxn(String path, List acl, int version) {
      this.path = path;
      this.acl = acl;
      this.version = version;
   }

   public String getPath() {
      return this.path;
   }

   public void setPath(String m_) {
      this.path = m_;
   }

   public List getAcl() {
      return this.acl;
   }

   public void setAcl(List m_) {
      this.acl = m_;
   }

   public int getVersion() {
      return this.version;
   }

   public void setVersion(int m_) {
      this.version = m_;
   }

   public void serialize(OutputArchive a_, String tag) throws IOException {
      a_.startRecord(this, tag);
      a_.writeString(this.path, "path");
      a_.startVector(this.acl, "acl");
      if (this.acl != null) {
         int len1 = this.acl.size();

         for(int vidx1 = 0; vidx1 < len1; ++vidx1) {
            ACL e1 = (ACL)this.acl.get(vidx1);
            a_.writeRecord(e1, "e1");
         }
      }

      a_.endVector(this.acl, "acl");
      a_.writeInt(this.version, "version");
      a_.endRecord(this, tag);
   }

   public void deserialize(InputArchive a_, String tag) throws IOException {
      a_.startRecord(tag);
      this.path = a_.readString("path");
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
      this.version = a_.readInt("version");
      a_.endRecord(tag);
   }

   public String toString() {
      try {
         ByteArrayOutputStream s = new ByteArrayOutputStream();
         ToStringOutputArchive a_ = new ToStringOutputArchive(s);
         a_.startRecord(this, "");
         a_.writeString(this.path, "path");
         a_.startVector(this.acl, "acl");
         if (this.acl != null) {
            int len1 = this.acl.size();

            for(int vidx1 = 0; vidx1 < len1; ++vidx1) {
               ACL e1 = (ACL)this.acl.get(vidx1);
               a_.writeRecord(e1, "e1");
            }
         }

         a_.endVector(this.acl, "acl");
         a_.writeInt(this.version, "version");
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
      throw new UnsupportedOperationException("comparing SetACLTxn is unimplemented");
   }

   public boolean equals(Object peer_) {
      if (!(peer_ instanceof SetACLTxn)) {
         return false;
      } else if (peer_ == this) {
         return true;
      } else {
         SetACLTxn peer = (SetACLTxn)peer_;
         boolean ret = false;
         ret = this.path.equals(peer.path);
         if (!ret) {
            return ret;
         } else {
            ret = this.acl.equals(peer.acl);
            if (!ret) {
               return ret;
            } else {
               ret = this.version == peer.version;
               return !ret ? ret : ret;
            }
         }
      }
   }

   public int hashCode() {
      int result = 17;
      int ret = this.path.hashCode();
      result = 37 * result + ret;
      ret = this.acl.hashCode();
      result = 37 * result + ret;
      ret = this.version;
      result = 37 * result + ret;
      return result;
   }

   public static String signature() {
      return "LSetACLTxn(s[LACL(iLId(ss))]i)";
   }
}
