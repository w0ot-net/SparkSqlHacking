package org.apache.zookeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.jute.InputArchive;
import org.apache.jute.OutputArchive;
import org.apache.jute.Record;
import org.apache.zookeeper.proto.Create2Response;
import org.apache.zookeeper.proto.CreateResponse;
import org.apache.zookeeper.proto.ErrorResponse;
import org.apache.zookeeper.proto.GetChildrenResponse;
import org.apache.zookeeper.proto.GetDataResponse;
import org.apache.zookeeper.proto.MultiHeader;
import org.apache.zookeeper.proto.SetDataResponse;

public class MultiResponse implements Record, Iterable {
   private List results = new ArrayList();

   public void add(OpResult x) {
      this.results.add(x);
   }

   public Iterator iterator() {
      return this.results.iterator();
   }

   public int size() {
      return this.results.size();
   }

   public void serialize(OutputArchive archive, String tag) throws IOException {
      archive.startRecord(this, tag);

      for(OpResult result : this.results) {
         int err = result.getType() == -1 ? ((OpResult.ErrorResult)result).getErr() : 0;
         (new MultiHeader(result.getType(), false, err)).serialize(archive, tag);
         switch (result.getType()) {
            case -1:
               (new ErrorResponse(((OpResult.ErrorResult)result).getErr())).serialize(archive, tag);
               break;
            case 0:
            case 3:
            case 6:
            case 7:
            case 9:
            case 10:
            case 11:
            case 12:
            case 14:
            default:
               throw new IOException("Invalid type " + result.getType() + " in MultiResponse");
            case 1:
               (new CreateResponse(((OpResult.CreateResult)result).getPath())).serialize(archive, tag);
            case 2:
            case 13:
               break;
            case 4:
               (new GetDataResponse(((OpResult.GetDataResult)result).getData(), ((OpResult.GetDataResult)result).getStat())).serialize(archive, tag);
               break;
            case 5:
               (new SetDataResponse(((OpResult.SetDataResult)result).getStat())).serialize(archive, tag);
               break;
            case 8:
               (new GetChildrenResponse(((OpResult.GetChildrenResult)result).getChildren())).serialize(archive, tag);
               break;
            case 15:
               OpResult.CreateResult createResult = (OpResult.CreateResult)result;
               (new Create2Response(createResult.getPath(), createResult.getStat())).serialize(archive, tag);
         }
      }

      (new MultiHeader(-1, true, -1)).serialize(archive, tag);
      archive.endRecord(this, tag);
   }

   public void deserialize(InputArchive archive, String tag) throws IOException {
      this.results = new ArrayList();
      archive.startRecord(tag);
      MultiHeader h = new MultiHeader();
      h.deserialize(archive, tag);

      for(; !h.getDone(); h.deserialize(archive, tag)) {
         switch (h.getType()) {
            case -1:
               ErrorResponse er = new ErrorResponse();
               er.deserialize(archive, tag);
               this.results.add(new OpResult.ErrorResult(er.getErr()));
               break;
            case 0:
            case 3:
            case 6:
            case 7:
            case 9:
            case 10:
            case 11:
            case 12:
            case 14:
            default:
               throw new IOException("Invalid type " + h.getType() + " in MultiResponse");
            case 1:
               CreateResponse cr = new CreateResponse();
               cr.deserialize(archive, tag);
               this.results.add(new OpResult.CreateResult(cr.getPath()));
               break;
            case 2:
               this.results.add(new OpResult.DeleteResult());
               break;
            case 4:
               GetDataResponse gdr = new GetDataResponse();
               gdr.deserialize(archive, tag);
               this.results.add(new OpResult.GetDataResult(gdr.getData(), gdr.getStat()));
               break;
            case 5:
               SetDataResponse sdr = new SetDataResponse();
               sdr.deserialize(archive, tag);
               this.results.add(new OpResult.SetDataResult(sdr.getStat()));
               break;
            case 8:
               GetChildrenResponse gcr = new GetChildrenResponse();
               gcr.deserialize(archive, tag);
               this.results.add(new OpResult.GetChildrenResult(gcr.getChildren()));
               break;
            case 13:
               this.results.add(new OpResult.CheckResult());
               break;
            case 15:
               Create2Response cr2 = new Create2Response();
               cr2.deserialize(archive, tag);
               this.results.add(new OpResult.CreateResult(cr2.getPath(), cr2.getStat()));
         }
      }

      archive.endRecord(tag);
   }

   public List getResultList() {
      return this.results;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof MultiResponse)) {
         return false;
      } else {
         MultiResponse other = (MultiResponse)o;
         if (this.results != null) {
            Iterator<OpResult> i = other.results.iterator();

            for(OpResult result : this.results) {
               if (!i.hasNext()) {
                  return false;
               }

               if (!result.equals(i.next())) {
                  return false;
               }
            }

            return !i.hasNext();
         } else {
            return other.results == null;
         }
      }
   }

   public int hashCode() {
      int hash = this.results.size();

      for(OpResult result : this.results) {
         hash = hash * 35 + result.hashCode();
      }

      return hash;
   }
}
