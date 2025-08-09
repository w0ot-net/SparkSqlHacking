package org.apache.spark.status;

import org.apache.spark.status.api.v1.RDDPartitionInfo;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.storage.StorageLevel.;
import org.apache.spark.util.Utils$;
import scala.collection.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M4AAE\n\u00059!A1\u0005\u0001BC\u0002\u0013\u0005A\u0005\u0003\u00051\u0001\t\u0005\t\u0015!\u0003&\u0011!\t\u0004A!A!\u0002\u0013\u0011\u0004\"\u0002\u001d\u0001\t\u0003I\u0004b\u0002 \u0001\u0001\u0004%\ta\u0010\u0005\b\u0001\u0002\u0001\r\u0011\"\u0001B\u0011\u00199\u0005\u0001)Q\u0005u!9A\n\u0001a\u0001\n\u0003y\u0004bB'\u0001\u0001\u0004%\tA\u0014\u0005\u0007!\u0002\u0001\u000b\u0015\u0002\u001e\t\u000fI\u0003\u0001\u0019!C\u0001'\"9A\f\u0001a\u0001\n\u0003i\u0006BB0\u0001A\u0003&A\u000bC\u0003a\u0001\u0011\u0005\u0011\rC\u0003i\u0001\u0011\u0005\u0011\u000eC\u0003n\u0001\u0011\u0005\u0011\u000eC\u0003o\u0001\u0011\u0005qN\u0001\tMSZ,'\u000b\u0012#QCJ$\u0018\u000e^5p]*\u0011A#F\u0001\u0007gR\fG/^:\u000b\u0005Y9\u0012!B:qCJ\\'B\u0001\r\u001a\u0003\u0019\t\u0007/Y2iK*\t!$A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001;A\u0011a$I\u0007\u0002?)\t\u0001%A\u0003tG\u0006d\u0017-\u0003\u0002#?\t1\u0011I\\=SK\u001a\f\u0011B\u00197pG.t\u0015-\\3\u0016\u0003\u0015\u0002\"AJ\u0017\u000f\u0005\u001dZ\u0003C\u0001\u0015 \u001b\u0005I#B\u0001\u0016\u001c\u0003\u0019a$o\\8u}%\u0011AfH\u0001\u0007!J,G-\u001a4\n\u00059z#AB*ue&twM\u0003\u0002-?\u0005Q!\r\\8dW:\u000bW.\u001a\u0011\u0002\u0011I$G\rT3wK2\u0004\"a\r\u001c\u000e\u0003QR!!N\u000b\u0002\u000fM$xN]1hK&\u0011q\u0007\u000e\u0002\r'R|'/Y4f\u0019\u00164X\r\\\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007ibT\b\u0005\u0002<\u00015\t1\u0003C\u0003$\t\u0001\u0007Q\u0005C\u00032\t\u0001\u0007!'\u0001\u0003qe\u00164X#\u0001\u001e\u0002\u0011A\u0014XM^0%KF$\"AQ#\u0011\u0005y\u0019\u0015B\u0001# \u0005\u0011)f.\u001b;\t\u000f\u00193\u0011\u0011!a\u0001u\u0005\u0019\u0001\u0010J\u0019\u0002\u000bA\u0014XM\u001e\u0011)\u0005\u001dI\u0005C\u0001\u0010K\u0013\tYuD\u0001\u0005w_2\fG/\u001b7f\u0003\u0011qW\r\u001f;\u0002\u00119,\u0007\u0010^0%KF$\"AQ(\t\u000f\u0019K\u0011\u0011!a\u0001u\u0005)a.\u001a=uA!\u0012!\"S\u0001\u0006m\u0006dW/Z\u000b\u0002)B\u0011QKW\u0007\u0002-*\u0011q\u000bW\u0001\u0003mFR!!W\n\u0002\u0007\u0005\u0004\u0018.\u0003\u0002\\-\n\u0001\"\u000b\u0012#QCJ$\u0018\u000e^5p]&sgm\\\u0001\nm\u0006dW/Z0%KF$\"A\u00110\t\u000f\u0019c\u0011\u0011!a\u0001)\u00061a/\u00197vK\u0002\n\u0011\"\u001a=fGV$xN]:\u0016\u0003\t\u00042a\u00194&\u001b\u0005!'BA3 \u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003O\u0012\u00141aU3r\u0003)iW-\\8ssV\u001bX\rZ\u000b\u0002UB\u0011ad[\u0005\u0003Y~\u0011A\u0001T8oO\u0006AA-[:l+N,G-\u0001\u0004va\u0012\fG/\u001a\u000b\u0005\u0005B\f(\u000fC\u0003a#\u0001\u0007!\rC\u0003i#\u0001\u0007!\u000eC\u0003n#\u0001\u0007!\u000e"
)
public class LiveRDDPartition {
   private final String blockName;
   private final StorageLevel rddLevel;
   private volatile LiveRDDPartition prev;
   private volatile LiveRDDPartition next;
   private RDDPartitionInfo value;

   public String blockName() {
      return this.blockName;
   }

   public LiveRDDPartition prev() {
      return this.prev;
   }

   public void prev_$eq(final LiveRDDPartition x$1) {
      this.prev = x$1;
   }

   public LiveRDDPartition next() {
      return this.next;
   }

   public void next_$eq(final LiveRDDPartition x$1) {
      this.next = x$1;
   }

   public RDDPartitionInfo value() {
      return this.value;
   }

   public void value_$eq(final RDDPartitionInfo x$1) {
      this.value = x$1;
   }

   public Seq executors() {
      return this.value().executors();
   }

   public long memoryUsed() {
      return this.value().memoryUsed();
   }

   public long diskUsed() {
      return this.value().diskUsed();
   }

   public void update(final Seq executors, final long memoryUsed, final long diskUsed) {
      StorageLevel level = .MODULE$.apply(diskUsed > 0L, memoryUsed > 0L, this.rddLevel.useOffHeap(), memoryUsed > 0L ? this.rddLevel.deserialized() : false, executors.size());
      this.value_$eq(new RDDPartitionInfo(this.blockName(), Utils$.MODULE$.weakIntern(level.description()), memoryUsed, diskUsed, executors));
   }

   public LiveRDDPartition(final String blockName, final StorageLevel rddLevel) {
      this.blockName = blockName;
      this.rddLevel = rddLevel;
      this.prev = null;
      this.next = null;
      this.value = null;
   }
}
