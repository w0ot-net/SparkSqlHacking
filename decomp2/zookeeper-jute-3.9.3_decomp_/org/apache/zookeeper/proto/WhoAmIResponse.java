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
import org.apache.zookeeper.data.ClientInfo;

@Public
public class WhoAmIResponse implements Record {
   private List clientInfo;

   public WhoAmIResponse() {
   }

   public WhoAmIResponse(List clientInfo) {
      this.clientInfo = clientInfo;
   }

   public List getClientInfo() {
      return this.clientInfo;
   }

   public void setClientInfo(List m_) {
      this.clientInfo = m_;
   }

   public void serialize(OutputArchive a_, String tag) throws IOException {
      a_.startRecord(this, tag);
      a_.startVector(this.clientInfo, "clientInfo");
      if (this.clientInfo != null) {
         int len1 = this.clientInfo.size();

         for(int vidx1 = 0; vidx1 < len1; ++vidx1) {
            ClientInfo e1 = (ClientInfo)this.clientInfo.get(vidx1);
            a_.writeRecord(e1, "e1");
         }
      }

      a_.endVector(this.clientInfo, "clientInfo");
      a_.endRecord(this, tag);
   }

   public void deserialize(InputArchive a_, String tag) throws IOException {
      a_.startRecord(tag);
      Index vidx1 = a_.startVector("clientInfo");
      if (vidx1 != null) {
         this.clientInfo = new ArrayList();

         while(!vidx1.done()) {
            ClientInfo e1 = new ClientInfo();
            a_.readRecord(e1, "e1");
            this.clientInfo.add(e1);
            vidx1.incr();
         }
      }

      a_.endVector("clientInfo");
      a_.endRecord(tag);
   }

   public String toString() {
      try {
         ByteArrayOutputStream s = new ByteArrayOutputStream();
         ToStringOutputArchive a_ = new ToStringOutputArchive(s);
         a_.startRecord(this, "");
         a_.startVector(this.clientInfo, "clientInfo");
         if (this.clientInfo != null) {
            int len1 = this.clientInfo.size();

            for(int vidx1 = 0; vidx1 < len1; ++vidx1) {
               ClientInfo e1 = (ClientInfo)this.clientInfo.get(vidx1);
               a_.writeRecord(e1, "e1");
            }
         }

         a_.endVector(this.clientInfo, "clientInfo");
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
      throw new UnsupportedOperationException("comparing WhoAmIResponse is unimplemented");
   }

   public boolean equals(Object peer_) {
      if (!(peer_ instanceof WhoAmIResponse)) {
         return false;
      } else if (peer_ == this) {
         return true;
      } else {
         WhoAmIResponse peer = (WhoAmIResponse)peer_;
         boolean ret = false;
         ret = this.clientInfo.equals(peer.clientInfo);
         return !ret ? ret : ret;
      }
   }

   public int hashCode() {
      int result = 17;
      int ret = this.clientInfo.hashCode();
      result = 37 * result + ret;
      return result;
   }

   public static String signature() {
      return "LWhoAmIResponse([LClientInfo(ss)])";
   }
}
