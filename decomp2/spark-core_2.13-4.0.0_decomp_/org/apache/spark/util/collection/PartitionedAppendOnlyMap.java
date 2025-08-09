package org.apache.spark.util.collection;

import java.lang.invoke.SerializedLambda;
import java.util.Comparator;
import scala.Option;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005e3Q\u0001B\u0003\u0001\u0013=AQ\u0001\r\u0001\u0005\u0002EBQa\r\u0001\u0005\u0002QBQA\u0014\u0001\u0005\u0002=\u0013\u0001\u0004U1si&$\u0018n\u001c8fI\u0006\u0003\b/\u001a8e\u001f:d\u00170T1q\u0015\t1q!\u0001\u0006d_2dWm\u0019;j_:T!\u0001C\u0005\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u0015-\tQa\u001d9be.T!\u0001D\u0007\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005q\u0011aA8sOV\u0019\u0001\u0003I\u0016\u0014\u0007\u0001\tR\u0006\u0005\u0003\u0013'UQS\"A\u0003\n\u0005Q)!!G*ju\u0016$&/Y2lS:<\u0017\t\u001d9f]\u0012|e\u000e\\=NCB\u0004BAF\r\u001c=5\tqCC\u0001\u0019\u0003\u0015\u00198-\u00197b\u0013\tQrC\u0001\u0004UkBdWM\r\t\u0003-qI!!H\f\u0003\u0007%sG\u000f\u0005\u0002 A1\u0001A!B\u0011\u0001\u0005\u0004\u0019#!A&\u0004\u0001E\u0011Ae\n\t\u0003-\u0015J!AJ\f\u0003\u000f9{G\u000f[5oOB\u0011a\u0003K\u0005\u0003S]\u00111!\u00118z!\ty2\u0006B\u0003-\u0001\t\u00071EA\u0001W!\u0011\u0011bF\b\u0016\n\u0005=*!!I,sSR\f'\r\\3QCJ$\u0018\u000e^5p]\u0016$\u0007+Y5s\u0007>dG.Z2uS>t\u0017A\u0002\u001fj]&$h\bF\u00013!\u0011\u0011\u0002A\b\u0016\u0002IA\f'\u000f^5uS>tW\r\u001a#fgR\u0014Xo\u0019;jm\u0016\u001cvN\u001d;fI&#XM]1u_J$\"!\u000e\"\u0011\u0007Yr\u0014I\u0004\u00028y9\u0011\u0001hO\u0007\u0002s)\u0011!HI\u0001\u0007yI|w\u000e\u001e \n\u0003aI!!P\f\u0002\u000fA\f7m[1hK&\u0011q\b\u0011\u0002\t\u0013R,'/\u0019;pe*\u0011Qh\u0006\t\u0005-e)\"\u0006C\u0003D\u0005\u0001\u0007A)A\u0007lKf\u001cu.\u001c9be\u0006$xN\u001d\t\u0004-\u0015;\u0015B\u0001$\u0018\u0005\u0019y\u0005\u000f^5p]B\u0019\u0001\n\u0014\u0010\u000e\u0003%S!\u0001\u0003&\u000b\u0003-\u000bAA[1wC&\u0011Q*\u0013\u0002\u000b\u0007>l\u0007/\u0019:bi>\u0014\u0018AB5og\u0016\u0014H\u000f\u0006\u0003Q'V;\u0006C\u0001\fR\u0013\t\u0011vC\u0001\u0003V]&$\b\"\u0002+\u0004\u0001\u0004Y\u0012!\u00039beRLG/[8o\u0011\u001516\u00011\u0001\u001f\u0003\rYW-\u001f\u0005\u00061\u000e\u0001\rAK\u0001\u0006m\u0006dW/\u001a"
)
public class PartitionedAppendOnlyMap extends SizeTrackingAppendOnlyMap implements WritablePartitionedPairCollection {
   public WritablePartitionedIterator destructiveSortedWritablePartitionedIterator(final Option keyComparator) {
      return WritablePartitionedPairCollection.destructiveSortedWritablePartitionedIterator$(this, keyComparator);
   }

   public Iterator partitionedDestructiveSortedIterator(final Option keyComparator) {
      Comparator comparator = (Comparator)keyComparator.map((keyComparatorx) -> WritablePartitionedPairCollection$.MODULE$.partitionKeyComparator(keyComparatorx)).getOrElse(() -> WritablePartitionedPairCollection$.MODULE$.partitionComparator());
      return this.destructiveSortedIterator(comparator);
   }

   public void insert(final int partition, final Object key, final Object value) {
      this.update(new Tuple2(BoxesRunTime.boxToInteger(partition), key), value);
   }

   public PartitionedAppendOnlyMap() {
      WritablePartitionedPairCollection.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
