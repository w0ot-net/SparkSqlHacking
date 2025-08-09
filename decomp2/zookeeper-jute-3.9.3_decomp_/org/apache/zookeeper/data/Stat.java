package org.apache.zookeeper.data;

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
public class Stat implements Record {
   private long czxid;
   private long mzxid;
   private long ctime;
   private long mtime;
   private int version;
   private int cversion;
   private int aversion;
   private long ephemeralOwner;
   private int dataLength;
   private int numChildren;
   private long pzxid;

   public Stat() {
   }

   public Stat(long czxid, long mzxid, long ctime, long mtime, int version, int cversion, int aversion, long ephemeralOwner, int dataLength, int numChildren, long pzxid) {
      this.czxid = czxid;
      this.mzxid = mzxid;
      this.ctime = ctime;
      this.mtime = mtime;
      this.version = version;
      this.cversion = cversion;
      this.aversion = aversion;
      this.ephemeralOwner = ephemeralOwner;
      this.dataLength = dataLength;
      this.numChildren = numChildren;
      this.pzxid = pzxid;
   }

   public long getCzxid() {
      return this.czxid;
   }

   public void setCzxid(long m_) {
      this.czxid = m_;
   }

   public long getMzxid() {
      return this.mzxid;
   }

   public void setMzxid(long m_) {
      this.mzxid = m_;
   }

   public long getCtime() {
      return this.ctime;
   }

   public void setCtime(long m_) {
      this.ctime = m_;
   }

   public long getMtime() {
      return this.mtime;
   }

   public void setMtime(long m_) {
      this.mtime = m_;
   }

   public int getVersion() {
      return this.version;
   }

   public void setVersion(int m_) {
      this.version = m_;
   }

   public int getCversion() {
      return this.cversion;
   }

   public void setCversion(int m_) {
      this.cversion = m_;
   }

   public int getAversion() {
      return this.aversion;
   }

   public void setAversion(int m_) {
      this.aversion = m_;
   }

   public long getEphemeralOwner() {
      return this.ephemeralOwner;
   }

   public void setEphemeralOwner(long m_) {
      this.ephemeralOwner = m_;
   }

   public int getDataLength() {
      return this.dataLength;
   }

   public void setDataLength(int m_) {
      this.dataLength = m_;
   }

   public int getNumChildren() {
      return this.numChildren;
   }

   public void setNumChildren(int m_) {
      this.numChildren = m_;
   }

   public long getPzxid() {
      return this.pzxid;
   }

   public void setPzxid(long m_) {
      this.pzxid = m_;
   }

   public void serialize(OutputArchive a_, String tag) throws IOException {
      a_.startRecord(this, tag);
      a_.writeLong(this.czxid, "czxid");
      a_.writeLong(this.mzxid, "mzxid");
      a_.writeLong(this.ctime, "ctime");
      a_.writeLong(this.mtime, "mtime");
      a_.writeInt(this.version, "version");
      a_.writeInt(this.cversion, "cversion");
      a_.writeInt(this.aversion, "aversion");
      a_.writeLong(this.ephemeralOwner, "ephemeralOwner");
      a_.writeInt(this.dataLength, "dataLength");
      a_.writeInt(this.numChildren, "numChildren");
      a_.writeLong(this.pzxid, "pzxid");
      a_.endRecord(this, tag);
   }

   public void deserialize(InputArchive a_, String tag) throws IOException {
      a_.startRecord(tag);
      this.czxid = a_.readLong("czxid");
      this.mzxid = a_.readLong("mzxid");
      this.ctime = a_.readLong("ctime");
      this.mtime = a_.readLong("mtime");
      this.version = a_.readInt("version");
      this.cversion = a_.readInt("cversion");
      this.aversion = a_.readInt("aversion");
      this.ephemeralOwner = a_.readLong("ephemeralOwner");
      this.dataLength = a_.readInt("dataLength");
      this.numChildren = a_.readInt("numChildren");
      this.pzxid = a_.readLong("pzxid");
      a_.endRecord(tag);
   }

   public String toString() {
      try {
         ByteArrayOutputStream s = new ByteArrayOutputStream();
         ToStringOutputArchive a_ = new ToStringOutputArchive(s);
         a_.startRecord(this, "");
         a_.writeLong(this.czxid, "czxid");
         a_.writeLong(this.mzxid, "mzxid");
         a_.writeLong(this.ctime, "ctime");
         a_.writeLong(this.mtime, "mtime");
         a_.writeInt(this.version, "version");
         a_.writeInt(this.cversion, "cversion");
         a_.writeInt(this.aversion, "aversion");
         a_.writeLong(this.ephemeralOwner, "ephemeralOwner");
         a_.writeInt(this.dataLength, "dataLength");
         a_.writeInt(this.numChildren, "numChildren");
         a_.writeLong(this.pzxid, "pzxid");
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
      if (!(peer_ instanceof Stat)) {
         throw new ClassCastException("Comparing different types of records.");
      } else {
         Stat peer = (Stat)peer_;
         int ret = 0;
         ret = this.czxid == peer.czxid ? 0 : (this.czxid < peer.czxid ? -1 : 1);
         if (ret != 0) {
            return ret;
         } else {
            ret = this.mzxid == peer.mzxid ? 0 : (this.mzxid < peer.mzxid ? -1 : 1);
            if (ret != 0) {
               return ret;
            } else {
               ret = this.ctime == peer.ctime ? 0 : (this.ctime < peer.ctime ? -1 : 1);
               if (ret != 0) {
                  return ret;
               } else {
                  ret = this.mtime == peer.mtime ? 0 : (this.mtime < peer.mtime ? -1 : 1);
                  if (ret != 0) {
                     return ret;
                  } else {
                     ret = this.version == peer.version ? 0 : (this.version < peer.version ? -1 : 1);
                     if (ret != 0) {
                        return ret;
                     } else {
                        ret = this.cversion == peer.cversion ? 0 : (this.cversion < peer.cversion ? -1 : 1);
                        if (ret != 0) {
                           return ret;
                        } else {
                           ret = this.aversion == peer.aversion ? 0 : (this.aversion < peer.aversion ? -1 : 1);
                           if (ret != 0) {
                              return ret;
                           } else {
                              ret = this.ephemeralOwner == peer.ephemeralOwner ? 0 : (this.ephemeralOwner < peer.ephemeralOwner ? -1 : 1);
                              if (ret != 0) {
                                 return ret;
                              } else {
                                 ret = this.dataLength == peer.dataLength ? 0 : (this.dataLength < peer.dataLength ? -1 : 1);
                                 if (ret != 0) {
                                    return ret;
                                 } else {
                                    ret = this.numChildren == peer.numChildren ? 0 : (this.numChildren < peer.numChildren ? -1 : 1);
                                    if (ret != 0) {
                                       return ret;
                                    } else {
                                       ret = this.pzxid == peer.pzxid ? 0 : (this.pzxid < peer.pzxid ? -1 : 1);
                                       return ret != 0 ? ret : ret;
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public boolean equals(Object peer_) {
      if (!(peer_ instanceof Stat)) {
         return false;
      } else if (peer_ == this) {
         return true;
      } else {
         Stat peer = (Stat)peer_;
         boolean ret = false;
         ret = this.czxid == peer.czxid;
         if (!ret) {
            return ret;
         } else {
            ret = this.mzxid == peer.mzxid;
            if (!ret) {
               return ret;
            } else {
               ret = this.ctime == peer.ctime;
               if (!ret) {
                  return ret;
               } else {
                  ret = this.mtime == peer.mtime;
                  if (!ret) {
                     return ret;
                  } else {
                     ret = this.version == peer.version;
                     if (!ret) {
                        return ret;
                     } else {
                        ret = this.cversion == peer.cversion;
                        if (!ret) {
                           return ret;
                        } else {
                           ret = this.aversion == peer.aversion;
                           if (!ret) {
                              return ret;
                           } else {
                              ret = this.ephemeralOwner == peer.ephemeralOwner;
                              if (!ret) {
                                 return ret;
                              } else {
                                 ret = this.dataLength == peer.dataLength;
                                 if (!ret) {
                                    return ret;
                                 } else {
                                    ret = this.numChildren == peer.numChildren;
                                    if (!ret) {
                                       return ret;
                                    } else {
                                       ret = this.pzxid == peer.pzxid;
                                       return !ret ? ret : ret;
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public int hashCode() {
      int result = 17;
      int ret = Long.hashCode(this.czxid);
      result = 37 * result + ret;
      ret = Long.hashCode(this.mzxid);
      result = 37 * result + ret;
      ret = Long.hashCode(this.ctime);
      result = 37 * result + ret;
      ret = Long.hashCode(this.mtime);
      result = 37 * result + ret;
      ret = this.version;
      result = 37 * result + ret;
      ret = this.cversion;
      result = 37 * result + ret;
      ret = this.aversion;
      result = 37 * result + ret;
      ret = Long.hashCode(this.ephemeralOwner);
      result = 37 * result + ret;
      ret = this.dataLength;
      result = 37 * result + ret;
      ret = this.numChildren;
      result = 37 * result + ret;
      ret = Long.hashCode(this.pzxid);
      result = 37 * result + ret;
      return result;
   }

   public static String signature() {
      return "LStat(lllliiiliil)";
   }
}
