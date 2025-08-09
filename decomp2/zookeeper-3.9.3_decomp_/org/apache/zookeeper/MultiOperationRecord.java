package org.apache.zookeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.jute.InputArchive;
import org.apache.jute.OutputArchive;
import org.apache.jute.Record;
import org.apache.zookeeper.proto.CheckVersionRequest;
import org.apache.zookeeper.proto.CreateRequest;
import org.apache.zookeeper.proto.CreateTTLRequest;
import org.apache.zookeeper.proto.DeleteRequest;
import org.apache.zookeeper.proto.GetChildrenRequest;
import org.apache.zookeeper.proto.GetDataRequest;
import org.apache.zookeeper.proto.MultiHeader;
import org.apache.zookeeper.proto.SetDataRequest;

public class MultiOperationRecord implements Record, Iterable {
   private List ops = new ArrayList();
   private Op.OpKind opKind = null;

   public MultiOperationRecord() {
   }

   public MultiOperationRecord(Iterable ops) throws IllegalArgumentException {
      for(Op op : ops) {
         this.setOrCheckOpKind(op.getKind());
         this.add(op);
      }

   }

   public Iterator iterator() {
      return this.ops.iterator();
   }

   public void add(Op op) throws IllegalArgumentException {
      this.setOrCheckOpKind(op.getKind());
      this.ops.add(op);
   }

   public int size() {
      return this.ops.size();
   }

   public Op.OpKind getOpKind() {
      return this.opKind;
   }

   private void setOrCheckOpKind(Op.OpKind ok) throws IllegalArgumentException {
      if (this.opKind == null) {
         this.opKind = ok;
      } else if (ok != this.opKind) {
         throw new IllegalArgumentException("Mixing read and write operations (transactions) is not allowed in a multi request.");
      }

   }

   public void serialize(OutputArchive archive, String tag) throws IOException {
      archive.startRecord(this, tag);

      for(Op op : this.ops) {
         MultiHeader h = new MultiHeader(op.getType(), false, -1);
         h.serialize(archive, tag);
         switch (op.getType()) {
            case 1:
            case 2:
            case 4:
            case 5:
            case 8:
            case 13:
            case 15:
            case 19:
            case 21:
               op.toRequestRecord().serialize(archive, tag);
               break;
            case 3:
            case 6:
            case 7:
            case 9:
            case 10:
            case 11:
            case 12:
            case 14:
            case 16:
            case 17:
            case 18:
            case 20:
            default:
               throw new IOException("Invalid type of op");
         }
      }

      (new MultiHeader(-1, true, -1)).serialize(archive, tag);
      archive.endRecord(this, tag);
   }

   public void deserialize(InputArchive archive, String tag) throws IOException {
      archive.startRecord(tag);
      MultiHeader h = new MultiHeader();
      h.deserialize(archive, tag);

      try {
         for(; !h.getDone(); h.deserialize(archive, tag)) {
            switch (h.getType()) {
               case 1:
               case 15:
               case 19:
                  CreateRequest cr = new CreateRequest();
                  cr.deserialize(archive, tag);
                  CreateMode createMode = CreateMode.fromFlag(cr.getFlags(), (CreateMode)null);
                  if (createMode == null) {
                     throw new IOException("invalid flag " + cr.getFlags() + " for create mode");
                  }

                  CreateOptions options = CreateOptions.newBuilder(cr.getAcl(), createMode).build();
                  this.add(Op.create(cr.getPath(), cr.getData(), options, h.getType()));
                  break;
               case 2:
                  DeleteRequest dr = new DeleteRequest();
                  dr.deserialize(archive, tag);
                  this.add(Op.delete(dr.getPath(), dr.getVersion()));
                  break;
               case 3:
               case 6:
               case 7:
               case 9:
               case 10:
               case 11:
               case 12:
               case 14:
               case 16:
               case 17:
               case 18:
               case 20:
               default:
                  throw new IOException("Invalid type of op");
               case 4:
                  GetDataRequest gdr = new GetDataRequest();
                  gdr.deserialize(archive, tag);
                  this.add(Op.getData(gdr.getPath()));
                  break;
               case 5:
                  SetDataRequest sdr = new SetDataRequest();
                  sdr.deserialize(archive, tag);
                  this.add(Op.setData(sdr.getPath(), sdr.getData(), sdr.getVersion()));
                  break;
               case 8:
                  GetChildrenRequest gcr = new GetChildrenRequest();
                  gcr.deserialize(archive, tag);
                  this.add(Op.getChildren(gcr.getPath()));
                  break;
               case 13:
                  CheckVersionRequest cvr = new CheckVersionRequest();
                  cvr.deserialize(archive, tag);
                  this.add(Op.check(cvr.getPath(), cvr.getVersion()));
                  break;
               case 21:
                  CreateTTLRequest crTtl = new CreateTTLRequest();
                  crTtl.deserialize(archive, tag);
                  this.add(Op.create(crTtl.getPath(), crTtl.getData(), crTtl.getAcl(), crTtl.getFlags(), crTtl.getTtl()));
            }
         }
      } catch (IllegalArgumentException var13) {
         throw new IOException("Mixing different kind of ops");
      }

      archive.endRecord(tag);
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof MultiOperationRecord)) {
         return false;
      } else {
         MultiOperationRecord that = (MultiOperationRecord)o;
         if (this.ops != null) {
            Iterator<Op> other = that.ops.iterator();

            for(Op op : this.ops) {
               boolean hasMoreData = other.hasNext();
               if (!hasMoreData) {
                  return false;
               }

               Op otherOp = (Op)other.next();
               if (!op.equals(otherOp)) {
                  return false;
               }
            }

            return !other.hasNext();
         } else {
            return that.ops == null;
         }
      }
   }

   public int hashCode() {
      int h = 1023;

      for(Op op : this.ops) {
         h = h * 25 + op.hashCode();
      }

      return h;
   }
}
