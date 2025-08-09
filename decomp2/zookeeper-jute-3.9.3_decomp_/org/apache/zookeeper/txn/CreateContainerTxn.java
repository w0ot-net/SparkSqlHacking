package org.apache.zookeeper.txn;

import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
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
public class CreateContainerTxn implements Record {
   private String path;
   private byte[] data;
   private List acl;
   private int parentCVersion;

   public CreateContainerTxn() {
   }

   public CreateContainerTxn(String path, byte[] data, List acl, int parentCVersion) {
      this.path = path;
      this.data = data;
      this.acl = acl;
      this.parentCVersion = parentCVersion;
   }

   public String getPath() {
      return this.path;
   }

   public void setPath(String m_) {
      this.path = m_;
   }

   public byte[] getData() {
      return this.data;
   }

   public void setData(byte[] m_) {
      this.data = m_;
   }

   public List getAcl() {
      return this.acl;
   }

   public void setAcl(List m_) {
      this.acl = m_;
   }

   public int getParentCVersion() {
      return this.parentCVersion;
   }

   public void setParentCVersion(int m_) {
      this.parentCVersion = m_;
   }

   public void serialize(OutputArchive a_, String tag) throws IOException {
      a_.startRecord(this, tag);
      a_.writeString(this.path, "path");
      a_.writeBuffer(this.data, "data");
      a_.startVector(this.acl, "acl");
      if (this.acl != null) {
         int len1 = this.acl.size();

         for(int vidx1 = 0; vidx1 < len1; ++vidx1) {
            ACL e1 = (ACL)this.acl.get(vidx1);
            a_.writeRecord(e1, "e1");
         }
      }

      a_.endVector(this.acl, "acl");
      a_.writeInt(this.parentCVersion, "parentCVersion");
      a_.endRecord(this, tag);
   }

   public void deserialize(InputArchive a_, String tag) throws IOException {
      a_.startRecord(tag);
      this.path = a_.readString("path");
      this.data = a_.readBuffer("data");
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
      this.parentCVersion = a_.readInt("parentCVersion");
      a_.endRecord(tag);
   }

   public String toString() {
      try {
         ByteArrayOutputStream s = new ByteArrayOutputStream();
         ToStringOutputArchive a_ = new ToStringOutputArchive(s);
         a_.startRecord(this, "");
         a_.writeString(this.path, "path");
         a_.writeBuffer(this.data, "data");
         a_.startVector(this.acl, "acl");
         if (this.acl != null) {
            int len1 = this.acl.size();

            for(int vidx1 = 0; vidx1 < len1; ++vidx1) {
               ACL e1 = (ACL)this.acl.get(vidx1);
               a_.writeRecord(e1, "e1");
            }
         }

         a_.endVector(this.acl, "acl");
         a_.writeInt(this.parentCVersion, "parentCVersion");
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
      throw new UnsupportedOperationException("comparing CreateContainerTxn is unimplemented");
   }

   public boolean equals(Object peer_) {
      if (!(peer_ instanceof CreateContainerTxn)) {
         return false;
      } else if (peer_ == this) {
         return true;
      } else {
         CreateContainerTxn peer = (CreateContainerTxn)peer_;
         boolean ret = false;
         ret = this.path.equals(peer.path);
         if (!ret) {
            return ret;
         } else {
            ret = Arrays.equals(this.data, peer.data);
            if (!ret) {
               return ret;
            } else {
               ret = this.acl.equals(peer.acl);
               if (!ret) {
                  return ret;
               } else {
                  ret = this.parentCVersion == peer.parentCVersion;
                  return !ret ? ret : ret;
               }
            }
         }
      }
   }

   public int hashCode() {
      int result = 17;
      int ret = this.path.hashCode();
      result = 37 * result + ret;
      ret = Arrays.hashCode(this.data);
      result = 37 * result + ret;
      ret = this.acl.hashCode();
      result = 37 * result + ret;
      ret = this.parentCVersion;
      result = 37 * result + ret;
      return result;
   }

   public static String signature() {
      return "LCreateContainerTxn(sB[LACL(iLId(ss))]i)";
   }
}
