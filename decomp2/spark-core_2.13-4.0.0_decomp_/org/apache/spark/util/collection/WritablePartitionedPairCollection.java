package org.apache.spark.util.collection;

import java.util.Comparator;
import scala.Option;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y4\u0001BC\u0006\u0011\u0002\u0007\u0005q\"\u0006\u0005\u0006;\u0001!\ta\b\u0005\u0006G\u00011\t\u0001\n\u0005\u0006y\u00011\t!\u0010\u0005\u00065\u0002!\taW\u0004\u0007C.A\ta\u00042\u0007\r)Y\u0001\u0012A\bd\u0011\u0015!g\u0001\"\u0001f\u0011\u00151g\u0001\"\u0001h\u0011\u0015ig\u0001\"\u0001o\u0005\u0005:&/\u001b;bE2,\u0007+\u0019:uSRLwN\\3e!\u0006L'oQ8mY\u0016\u001cG/[8o\u0015\taQ\"\u0001\u0006d_2dWm\u0019;j_:T!AD\b\u0002\tU$\u0018\u000e\u001c\u0006\u0003!E\tQa\u001d9be.T!AE\n\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005!\u0012aA8sOV\u0019aC\f\u001e\u0014\u0005\u00019\u0002C\u0001\r\u001c\u001b\u0005I\"\"\u0001\u000e\u0002\u000bM\u001c\u0017\r\\1\n\u0005qI\"AB!osJ+g-\u0001\u0004%S:LG\u000fJ\u0002\u0001)\u0005\u0001\u0003C\u0001\r\"\u0013\t\u0011\u0013D\u0001\u0003V]&$\u0018AB5og\u0016\u0014H\u000f\u0006\u0003!K):\u0004\"\u0002\u0014\u0003\u0001\u00049\u0013!\u00039beRLG/[8o!\tA\u0002&\u0003\u0002*3\t\u0019\u0011J\u001c;\t\u000b-\u0012\u0001\u0019\u0001\u0017\u0002\u0007-,\u0017\u0010\u0005\u0002.]1\u0001A!B\u0018\u0001\u0005\u0004\u0001$!A&\u0012\u0005E\"\u0004C\u0001\r3\u0013\t\u0019\u0014DA\u0004O_RD\u0017N\\4\u0011\u0005a)\u0014B\u0001\u001c\u001a\u0005\r\te.\u001f\u0005\u0006q\t\u0001\r!O\u0001\u0006m\u0006dW/\u001a\t\u0003[i\"Qa\u000f\u0001C\u0002A\u0012\u0011AV\u0001%a\u0006\u0014H/\u001b;j_:,G\rR3tiJ,8\r^5wKN{'\u000f^3e\u0013R,'/\u0019;peR\u0011aH\u0014\t\u0004\u007f\u001dSeB\u0001!F\u001d\t\tE)D\u0001C\u0015\t\u0019e$\u0001\u0004=e>|GOP\u0005\u00025%\u0011a)G\u0001\ba\u0006\u001c7.Y4f\u0013\tA\u0015J\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0015\t1\u0015\u0004\u0005\u0003\u0019\u00176K\u0014B\u0001'\u001a\u0005\u0019!V\u000f\u001d7feA!\u0001dS\u0014-\u0011\u0015y5\u00011\u0001Q\u00035YW-_\"p[B\f'/\u0019;peB\u0019\u0001$U*\n\u0005IK\"AB(qi&|g\u000eE\u0002U12j\u0011!\u0016\u0006\u0003\u001dYS\u0011aV\u0001\u0005U\u00064\u0018-\u0003\u0002Z+\nQ1i\\7qCJ\fGo\u001c:\u0002Y\u0011,7\u000f\u001e:vGRLg/Z*peR,Gm\u0016:ji\u0006\u0014G.\u001a)beRLG/[8oK\u0012LE/\u001a:bi>\u0014HC\u0001/a!\u0011if\fL\u001d\u000e\u0003-I!aX\u0006\u00037]\u0013\u0018\u000e^1cY\u0016\u0004\u0016M\u001d;ji&|g.\u001a3Ji\u0016\u0014\u0018\r^8s\u0011\u0015yE\u00011\u0001Q\u0003\u0005:&/\u001b;bE2,\u0007+\u0019:uSRLwN\\3e!\u0006L'oQ8mY\u0016\u001cG/[8o!\tifa\u0005\u0002\u0007/\u00051A(\u001b8jiz\"\u0012AY\u0001\u0014a\u0006\u0014H/\u001b;j_:\u001cu.\u001c9be\u0006$xN]\u000b\u0003Q2,\u0012!\u001b\t\u0004)bS\u0007\u0003\u0002\rLO-\u0004\"!\f7\u0005\u000b=B!\u0019\u0001\u0019\u0002-A\f'\u000f^5uS>t7*Z=D_6\u0004\u0018M]1u_J,\"a\\:\u0015\u0005A$\bc\u0001+YcB!\u0001dS\u0014s!\ti3\u000fB\u00030\u0013\t\u0007\u0001\u0007C\u0003P\u0013\u0001\u0007Q\u000fE\u0002U1J\u0004"
)
public interface WritablePartitionedPairCollection {
   static Comparator partitionKeyComparator(final Comparator keyComparator) {
      return WritablePartitionedPairCollection$.MODULE$.partitionKeyComparator(keyComparator);
   }

   static Comparator partitionComparator() {
      return WritablePartitionedPairCollection$.MODULE$.partitionComparator();
   }

   void insert(final int partition, final Object key, final Object value);

   Iterator partitionedDestructiveSortedIterator(final Option keyComparator);

   // $FF: synthetic method
   static WritablePartitionedIterator destructiveSortedWritablePartitionedIterator$(final WritablePartitionedPairCollection $this, final Option keyComparator) {
      return $this.destructiveSortedWritablePartitionedIterator(keyComparator);
   }

   default WritablePartitionedIterator destructiveSortedWritablePartitionedIterator(final Option keyComparator) {
      Iterator it = this.partitionedDestructiveSortedIterator(keyComparator);
      return new WritablePartitionedIterator(it);
   }

   static void $init$(final WritablePartitionedPairCollection $this) {
   }
}
