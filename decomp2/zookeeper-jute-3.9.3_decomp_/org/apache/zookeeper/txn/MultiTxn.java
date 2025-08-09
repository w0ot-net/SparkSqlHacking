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

@Public
public class MultiTxn implements Record {
   private List txns;

   public MultiTxn() {
   }

   public MultiTxn(List txns) {
      this.txns = txns;
   }

   public List getTxns() {
      return this.txns;
   }

   public void setTxns(List m_) {
      this.txns = m_;
   }

   public void serialize(OutputArchive a_, String tag) throws IOException {
      a_.startRecord(this, tag);
      a_.startVector(this.txns, "txns");
      if (this.txns != null) {
         int len1 = this.txns.size();

         for(int vidx1 = 0; vidx1 < len1; ++vidx1) {
            Txn e1 = (Txn)this.txns.get(vidx1);
            a_.writeRecord(e1, "e1");
         }
      }

      a_.endVector(this.txns, "txns");
      a_.endRecord(this, tag);
   }

   public void deserialize(InputArchive a_, String tag) throws IOException {
      a_.startRecord(tag);
      Index vidx1 = a_.startVector("txns");
      if (vidx1 != null) {
         this.txns = new ArrayList();

         while(!vidx1.done()) {
            Txn e1 = new Txn();
            a_.readRecord(e1, "e1");
            this.txns.add(e1);
            vidx1.incr();
         }
      }

      a_.endVector("txns");
      a_.endRecord(tag);
   }

   public String toString() {
      try {
         ByteArrayOutputStream s = new ByteArrayOutputStream();
         ToStringOutputArchive a_ = new ToStringOutputArchive(s);
         a_.startRecord(this, "");
         a_.startVector(this.txns, "txns");
         if (this.txns != null) {
            int len1 = this.txns.size();

            for(int vidx1 = 0; vidx1 < len1; ++vidx1) {
               Txn e1 = (Txn)this.txns.get(vidx1);
               a_.writeRecord(e1, "e1");
            }
         }

         a_.endVector(this.txns, "txns");
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
      throw new UnsupportedOperationException("comparing MultiTxn is unimplemented");
   }

   public boolean equals(Object peer_) {
      if (!(peer_ instanceof MultiTxn)) {
         return false;
      } else if (peer_ == this) {
         return true;
      } else {
         MultiTxn peer = (MultiTxn)peer_;
         boolean ret = false;
         ret = this.txns.equals(peer.txns);
         return !ret ? ret : ret;
      }
   }

   public int hashCode() {
      int result = 17;
      int ret = this.txns.hashCode();
      result = 37 * result + ret;
      return result;
   }

   public static String signature() {
      return "LMultiTxn([LTxn(iB)])";
   }
}
