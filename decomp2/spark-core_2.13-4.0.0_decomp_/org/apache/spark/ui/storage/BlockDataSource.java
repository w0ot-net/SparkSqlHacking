package org.apache.spark.ui.storage;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.status.api.v1.RDDPartitionInfo;
import org.apache.spark.ui.PagedDataSource;
import scala.collection.IterableOnceOps;
import scala.collection.Seq;
import scala.collection.SeqOps;
import scala.collection.immutable.Map;
import scala.math.Ordering;
import scala.math.Ordering.String.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005Q4Q!\u0004\b\u0001!aA\u0001\"\t\u0001\u0003\u0002\u0003\u0006Ia\t\u0005\nk\u0001\u0011\t\u0011)A\u0005miB\u0001b\u000f\u0001\u0003\u0002\u0003\u0006I\u0001\u0010\u0005\t\u000f\u0002\u0011\t\u0011)A\u0005\u0011\"A1\n\u0001B\u0001B\u0003%A\nC\u0003P\u0001\u0011\u0005\u0001\u000bC\u0004X\u0001\t\u0007I\u0011\u0002-\t\ri\u0003\u0001\u0015!\u0003Z\u0011\u0015Y\u0006\u0001\"\u0011]\u0011\u0015i\u0006\u0001\"\u0011_\u0011\u0015\u0019\u0007\u0001\"\u0003e\u0011\u00159\u0007\u0001\"\u0003i\u0005=\u0011En\\2l\t\u0006$\u0018mU8ve\u000e,'BA\b\u0011\u0003\u001d\u0019Ho\u001c:bO\u0016T!!\u0005\n\u0002\u0005UL'BA\n\u0015\u0003\u0015\u0019\b/\u0019:l\u0015\t)b#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002/\u0005\u0019qN]4\u0014\u0005\u0001I\u0002c\u0001\u000e\u001c;5\t\u0001#\u0003\u0002\u001d!\ty\u0001+Y4fI\u0012\u000bG/Y*pkJ\u001cW\r\u0005\u0002\u001f?5\ta\"\u0003\u0002!\u001d\t\t\"\t\\8dWR\u000b'\r\\3S_^$\u0015\r^1\u0002\u001bI$G\rU1si&$\u0018n\u001c8t\u0007\u0001\u00012\u0001J\u0015,\u001b\u0005)#B\u0001\u0014(\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002Q\u0005)1oY1mC&\u0011!&\n\u0002\u0004'\u0016\f\bC\u0001\u00174\u001b\u0005i#B\u0001\u00180\u0003\t1\u0018G\u0003\u00021c\u0005\u0019\u0011\r]5\u000b\u0005I\u0012\u0012AB:uCR,8/\u0003\u00025[\t\u0001\"\u000b\u0012#QCJ$\u0018\u000e^5p]&sgm\\\u0001\ta\u0006<WmU5{KB\u0011q\u0007O\u0007\u0002O%\u0011\u0011h\n\u0002\u0004\u0013:$\u0018BA\u001b\u001c\u0003)\u0019xN\u001d;D_2,XN\u001c\t\u0003{\u0011s!A\u0010\"\u0011\u0005}:S\"\u0001!\u000b\u0005\u0005\u0013\u0013A\u0002\u001fs_>$h(\u0003\u0002DO\u00051\u0001K]3eK\u001aL!!\u0012$\u0003\rM#(/\u001b8h\u0015\t\u0019u%\u0001\u0003eKN\u001c\u0007CA\u001cJ\u0013\tQuEA\u0004C_>dW-\u00198\u0002'\u0015DXmY;u_JLE\rV8BI\u0012\u0014Xm]:\u0011\tujE\bP\u0005\u0003\u001d\u001a\u00131!T1q\u0003\u0019a\u0014N\\5u}Q1\u0011KU*U+Z\u0003\"A\b\u0001\t\u000b\u00052\u0001\u0019A\u0012\t\u000bU2\u0001\u0019\u0001\u001c\t\u000bm2\u0001\u0019\u0001\u001f\t\u000b\u001d3\u0001\u0019\u0001%\t\u000b-3\u0001\u0019\u0001'\u0002\t\u0011\fG/Y\u000b\u00023B\u0019A%K\u000f\u0002\u000b\u0011\fG/\u0019\u0011\u0002\u0011\u0011\fG/Y*ju\u0016,\u0012AN\u0001\ng2L7-\u001a#bi\u0006$2!W0b\u0011\u0015\u0001'\u00021\u00017\u0003\u00111'o\\7\t\u000b\tT\u0001\u0019\u0001\u001c\u0002\u0005Q|\u0017\u0001\u00032m_\u000e\\'k\\<\u0015\u0005u)\u0007\"\u00024\f\u0001\u0004Y\u0013\u0001\u0004:eIB\u000b'\u000f^5uS>t\u0017\u0001C8sI\u0016\u0014\u0018N\\4\u0015\u0007%\u00148\u000fE\u0002k_vq!a[7\u000f\u0005}b\u0017\"\u0001\u0015\n\u00059<\u0013a\u00029bG.\fw-Z\u0005\u0003aF\u0014\u0001b\u0014:eKJLgn\u001a\u0006\u0003]\u001eBQa\u000f\u0007A\u0002qBQa\u0012\u0007A\u0002!\u0003"
)
public class BlockDataSource extends PagedDataSource {
   private final Map executorIdToAddress;
   private final Seq data;

   private Seq data() {
      return this.data;
   }

   public int dataSize() {
      return this.data().size();
   }

   public Seq sliceData(final int from, final int to) {
      return (Seq)this.data().slice(from, to);
   }

   private BlockTableRowData blockRow(final RDDPartitionInfo rddPartition) {
      return new BlockTableRowData(rddPartition.blockName(), rddPartition.storageLevel(), rddPartition.memoryUsed(), rddPartition.diskUsed(), ((IterableOnceOps)((SeqOps)rddPartition.executors().map((id) -> (String)this.executorIdToAddress.getOrElse(id, () -> id))).sorted(.MODULE$)).mkString(" "));
   }

   private Ordering ordering(final String sortColumn, final boolean desc) {
      Ordering var10000;
      switch (sortColumn == null ? 0 : sortColumn.hashCode()) {
         case -1720097987:
            if (!"Size in Memory".equals(sortColumn)) {
               throw new IllegalArgumentException("Unknown column: " + sortColumn);
            }

            var10000 = scala.package..MODULE$.Ordering().by((x$6) -> BoxesRunTime.boxToLong($anonfun$ordering$3(x$6)), scala.math.Ordering.Long..MODULE$);
            break;
         case -1074280834:
            if (!"Block Name".equals(sortColumn)) {
               throw new IllegalArgumentException("Unknown column: " + sortColumn);
            }

            var10000 = scala.package..MODULE$.Ordering().by((x$4) -> x$4.blockName(), .MODULE$);
            break;
         case 603420255:
            if (!"Size on Disk".equals(sortColumn)) {
               throw new IllegalArgumentException("Unknown column: " + sortColumn);
            }

            var10000 = scala.package..MODULE$.Ordering().by((x$7) -> BoxesRunTime.boxToLong($anonfun$ordering$4(x$7)), scala.math.Ordering.Long..MODULE$);
            break;
         case 912998880:
            if (!"Executors".equals(sortColumn)) {
               throw new IllegalArgumentException("Unknown column: " + sortColumn);
            }

            var10000 = scala.package..MODULE$.Ordering().by((x$8) -> x$8.executors(), .MODULE$);
            break;
         case 1009658207:
            if ("Storage Level".equals(sortColumn)) {
               var10000 = scala.package..MODULE$.Ordering().by((x$5) -> x$5.storageLevel(), .MODULE$);
               break;
            }

            throw new IllegalArgumentException("Unknown column: " + sortColumn);
         default:
            throw new IllegalArgumentException("Unknown column: " + sortColumn);
      }

      Ordering ordering = var10000;
      if (desc) {
         return ordering.reverse();
      } else {
         return ordering;
      }
   }

   // $FF: synthetic method
   public static final long $anonfun$ordering$3(final BlockTableRowData x$6) {
      return x$6.memoryUsed();
   }

   // $FF: synthetic method
   public static final long $anonfun$ordering$4(final BlockTableRowData x$7) {
      return x$7.diskUsed();
   }

   public BlockDataSource(final Seq rddPartitions, final int pageSize, final String sortColumn, final boolean desc, final Map executorIdToAddress) {
      super(pageSize);
      this.executorIdToAddress = executorIdToAddress;
      this.data = (Seq)((SeqOps)rddPartitions.map((rddPartition) -> this.blockRow(rddPartition))).sorted(this.ordering(sortColumn, desc));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
