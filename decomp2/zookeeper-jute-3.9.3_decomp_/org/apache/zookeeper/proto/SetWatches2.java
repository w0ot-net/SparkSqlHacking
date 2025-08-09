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
public class SetWatches2 implements Record {
   private long relativeZxid;
   private List dataWatches;
   private List existWatches;
   private List childWatches;
   private List persistentWatches;
   private List persistentRecursiveWatches;

   public SetWatches2() {
   }

   public SetWatches2(long relativeZxid, List dataWatches, List existWatches, List childWatches, List persistentWatches, List persistentRecursiveWatches) {
      this.relativeZxid = relativeZxid;
      this.dataWatches = dataWatches;
      this.existWatches = existWatches;
      this.childWatches = childWatches;
      this.persistentWatches = persistentWatches;
      this.persistentRecursiveWatches = persistentRecursiveWatches;
   }

   public long getRelativeZxid() {
      return this.relativeZxid;
   }

   public void setRelativeZxid(long m_) {
      this.relativeZxid = m_;
   }

   public List getDataWatches() {
      return this.dataWatches;
   }

   public void setDataWatches(List m_) {
      this.dataWatches = m_;
   }

   public List getExistWatches() {
      return this.existWatches;
   }

   public void setExistWatches(List m_) {
      this.existWatches = m_;
   }

   public List getChildWatches() {
      return this.childWatches;
   }

   public void setChildWatches(List m_) {
      this.childWatches = m_;
   }

   public List getPersistentWatches() {
      return this.persistentWatches;
   }

   public void setPersistentWatches(List m_) {
      this.persistentWatches = m_;
   }

   public List getPersistentRecursiveWatches() {
      return this.persistentRecursiveWatches;
   }

   public void setPersistentRecursiveWatches(List m_) {
      this.persistentRecursiveWatches = m_;
   }

   public void serialize(OutputArchive a_, String tag) throws IOException {
      a_.startRecord(this, tag);
      a_.writeLong(this.relativeZxid, "relativeZxid");
      a_.startVector(this.dataWatches, "dataWatches");
      if (this.dataWatches != null) {
         int len1 = this.dataWatches.size();

         for(int vidx1 = 0; vidx1 < len1; ++vidx1) {
            String e1 = (String)this.dataWatches.get(vidx1);
            a_.writeString(e1, "e1");
         }
      }

      a_.endVector(this.dataWatches, "dataWatches");
      a_.startVector(this.existWatches, "existWatches");
      if (this.existWatches != null) {
         int len1 = this.existWatches.size();

         for(int vidx1 = 0; vidx1 < len1; ++vidx1) {
            String e1 = (String)this.existWatches.get(vidx1);
            a_.writeString(e1, "e1");
         }
      }

      a_.endVector(this.existWatches, "existWatches");
      a_.startVector(this.childWatches, "childWatches");
      if (this.childWatches != null) {
         int len1 = this.childWatches.size();

         for(int vidx1 = 0; vidx1 < len1; ++vidx1) {
            String e1 = (String)this.childWatches.get(vidx1);
            a_.writeString(e1, "e1");
         }
      }

      a_.endVector(this.childWatches, "childWatches");
      a_.startVector(this.persistentWatches, "persistentWatches");
      if (this.persistentWatches != null) {
         int len1 = this.persistentWatches.size();

         for(int vidx1 = 0; vidx1 < len1; ++vidx1) {
            String e1 = (String)this.persistentWatches.get(vidx1);
            a_.writeString(e1, "e1");
         }
      }

      a_.endVector(this.persistentWatches, "persistentWatches");
      a_.startVector(this.persistentRecursiveWatches, "persistentRecursiveWatches");
      if (this.persistentRecursiveWatches != null) {
         int len1 = this.persistentRecursiveWatches.size();

         for(int vidx1 = 0; vidx1 < len1; ++vidx1) {
            String e1 = (String)this.persistentRecursiveWatches.get(vidx1);
            a_.writeString(e1, "e1");
         }
      }

      a_.endVector(this.persistentRecursiveWatches, "persistentRecursiveWatches");
      a_.endRecord(this, tag);
   }

   public void deserialize(InputArchive a_, String tag) throws IOException {
      a_.startRecord(tag);
      this.relativeZxid = a_.readLong("relativeZxid");
      Index vidx1 = a_.startVector("dataWatches");
      if (vidx1 != null) {
         this.dataWatches = new ArrayList();

         while(!vidx1.done()) {
            String e1 = a_.readString("e1");
            this.dataWatches.add(e1);
            vidx1.incr();
         }
      }

      a_.endVector("dataWatches");
      vidx1 = a_.startVector("existWatches");
      if (vidx1 != null) {
         this.existWatches = new ArrayList();

         while(!vidx1.done()) {
            String e1 = a_.readString("e1");
            this.existWatches.add(e1);
            vidx1.incr();
         }
      }

      a_.endVector("existWatches");
      vidx1 = a_.startVector("childWatches");
      if (vidx1 != null) {
         this.childWatches = new ArrayList();

         while(!vidx1.done()) {
            String e1 = a_.readString("e1");
            this.childWatches.add(e1);
            vidx1.incr();
         }
      }

      a_.endVector("childWatches");
      vidx1 = a_.startVector("persistentWatches");
      if (vidx1 != null) {
         this.persistentWatches = new ArrayList();

         while(!vidx1.done()) {
            String e1 = a_.readString("e1");
            this.persistentWatches.add(e1);
            vidx1.incr();
         }
      }

      a_.endVector("persistentWatches");
      vidx1 = a_.startVector("persistentRecursiveWatches");
      if (vidx1 != null) {
         this.persistentRecursiveWatches = new ArrayList();

         while(!vidx1.done()) {
            String e1 = a_.readString("e1");
            this.persistentRecursiveWatches.add(e1);
            vidx1.incr();
         }
      }

      a_.endVector("persistentRecursiveWatches");
      a_.endRecord(tag);
   }

   public String toString() {
      try {
         ByteArrayOutputStream s = new ByteArrayOutputStream();
         ToStringOutputArchive a_ = new ToStringOutputArchive(s);
         a_.startRecord(this, "");
         a_.writeLong(this.relativeZxid, "relativeZxid");
         a_.startVector(this.dataWatches, "dataWatches");
         if (this.dataWatches != null) {
            int len1 = this.dataWatches.size();

            for(int vidx1 = 0; vidx1 < len1; ++vidx1) {
               String e1 = (String)this.dataWatches.get(vidx1);
               a_.writeString(e1, "e1");
            }
         }

         a_.endVector(this.dataWatches, "dataWatches");
         a_.startVector(this.existWatches, "existWatches");
         if (this.existWatches != null) {
            int len1 = this.existWatches.size();

            for(int vidx1 = 0; vidx1 < len1; ++vidx1) {
               String e1 = (String)this.existWatches.get(vidx1);
               a_.writeString(e1, "e1");
            }
         }

         a_.endVector(this.existWatches, "existWatches");
         a_.startVector(this.childWatches, "childWatches");
         if (this.childWatches != null) {
            int len1 = this.childWatches.size();

            for(int vidx1 = 0; vidx1 < len1; ++vidx1) {
               String e1 = (String)this.childWatches.get(vidx1);
               a_.writeString(e1, "e1");
            }
         }

         a_.endVector(this.childWatches, "childWatches");
         a_.startVector(this.persistentWatches, "persistentWatches");
         if (this.persistentWatches != null) {
            int len1 = this.persistentWatches.size();

            for(int vidx1 = 0; vidx1 < len1; ++vidx1) {
               String e1 = (String)this.persistentWatches.get(vidx1);
               a_.writeString(e1, "e1");
            }
         }

         a_.endVector(this.persistentWatches, "persistentWatches");
         a_.startVector(this.persistentRecursiveWatches, "persistentRecursiveWatches");
         if (this.persistentRecursiveWatches != null) {
            int len1 = this.persistentRecursiveWatches.size();

            for(int vidx1 = 0; vidx1 < len1; ++vidx1) {
               String e1 = (String)this.persistentRecursiveWatches.get(vidx1);
               a_.writeString(e1, "e1");
            }
         }

         a_.endVector(this.persistentRecursiveWatches, "persistentRecursiveWatches");
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
      throw new UnsupportedOperationException("comparing SetWatches2 is unimplemented");
   }

   public boolean equals(Object peer_) {
      if (!(peer_ instanceof SetWatches2)) {
         return false;
      } else if (peer_ == this) {
         return true;
      } else {
         SetWatches2 peer = (SetWatches2)peer_;
         boolean ret = false;
         ret = this.relativeZxid == peer.relativeZxid;
         if (!ret) {
            return ret;
         } else {
            ret = this.dataWatches.equals(peer.dataWatches);
            if (!ret) {
               return ret;
            } else {
               ret = this.existWatches.equals(peer.existWatches);
               if (!ret) {
                  return ret;
               } else {
                  ret = this.childWatches.equals(peer.childWatches);
                  if (!ret) {
                     return ret;
                  } else {
                     ret = this.persistentWatches.equals(peer.persistentWatches);
                     if (!ret) {
                        return ret;
                     } else {
                        ret = this.persistentRecursiveWatches.equals(peer.persistentRecursiveWatches);
                        return !ret ? ret : ret;
                     }
                  }
               }
            }
         }
      }
   }

   public int hashCode() {
      int result = 17;
      int ret = Long.hashCode(this.relativeZxid);
      result = 37 * result + ret;
      ret = this.dataWatches.hashCode();
      result = 37 * result + ret;
      ret = this.existWatches.hashCode();
      result = 37 * result + ret;
      ret = this.childWatches.hashCode();
      result = 37 * result + ret;
      ret = this.persistentWatches.hashCode();
      result = 37 * result + ret;
      ret = this.persistentRecursiveWatches.hashCode();
      result = 37 * result + ret;
      return result;
   }

   public static String signature() {
      return "LSetWatches2(l[s][s][s][s][s])";
   }
}
