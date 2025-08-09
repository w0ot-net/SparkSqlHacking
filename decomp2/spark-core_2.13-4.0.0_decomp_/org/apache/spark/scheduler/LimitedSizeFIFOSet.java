package org.apache.spark.scheduler;

import scala.collection.mutable.LinkedHashSet;
import scala.collection.mutable.LinkedHashSet.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!3Q\u0001C\u0005\u0001\u0013EA\u0001\"\u0007\u0001\u0003\u0006\u0004%\ta\u0007\u0005\t?\u0001\u0011\t\u0011)A\u00059!)\u0001\u0005\u0001C\u0001C!9\u0001\u0007\u0001b\u0001\n\u0013\t\u0004B\u0002\u001e\u0001A\u0003%!\u0007C\u0003<\u0001\u0011\u0005A\bC\u0003C\u0001\u0011\u00051I\u0001\nMS6LG/\u001a3TSj,g)\u0013$P'\u0016$(B\u0001\u0006\f\u0003%\u00198\r[3ek2,'O\u0003\u0002\r\u001b\u0005)1\u000f]1sW*\u0011abD\u0001\u0007CB\f7\r[3\u000b\u0003A\t1a\u001c:h+\t\u0011be\u0005\u0002\u0001'A\u0011AcF\u0007\u0002+)\ta#A\u0003tG\u0006d\u0017-\u0003\u0002\u0019+\t1\u0011I\\=SK\u001a\f\u0001bY1qC\u000eLG/_\u0002\u0001+\u0005a\u0002C\u0001\u000b\u001e\u0013\tqRCA\u0002J]R\f\u0011bY1qC\u000eLG/\u001f\u0011\u0002\rqJg.\u001b;?)\t\u0011s\u0006E\u0002$\u0001\u0011j\u0011!\u0003\t\u0003K\u0019b\u0001\u0001B\u0003(\u0001\t\u0007\u0001FA\u0001U#\tIC\u0006\u0005\u0002\u0015U%\u00111&\u0006\u0002\b\u001d>$\b.\u001b8h!\t!R&\u0003\u0002/+\t\u0019\u0011I\\=\t\u000be\u0019\u0001\u0019\u0001\u000f\u0002\u0007M,G/F\u00013!\r\u0019\u0004\bJ\u0007\u0002i)\u0011QGN\u0001\b[V$\u0018M\u00197f\u0015\t9T#\u0001\u0006d_2dWm\u0019;j_:L!!\u000f\u001b\u0003\u001b1Kgn[3e\u0011\u0006\u001c\bnU3u\u0003\u0011\u0019X\r\u001e\u0011\u0002\u0007\u0005$G\r\u0006\u0002>\u0001B\u0011ACP\u0005\u0003\u007fU\u0011A!\u00168ji\")\u0011I\u0002a\u0001I\u0005\tA/\u0001\u0005d_:$\u0018-\u001b8t)\t!u\t\u0005\u0002\u0015\u000b&\u0011a)\u0006\u0002\b\u0005>|G.Z1o\u0011\u0015\tu\u00011\u0001%\u0001"
)
public class LimitedSizeFIFOSet {
   private final int capacity;
   private final LinkedHashSet set;

   public int capacity() {
      return this.capacity;
   }

   private LinkedHashSet set() {
      return this.set;
   }

   public void add(final Object t) {
      this.set().$plus$eq(t);
      if (this.set().size() > this.capacity()) {
         this.set().$minus$eq(this.set().head());
      }
   }

   public boolean contains(final Object t) {
      return this.set().contains(t);
   }

   public LimitedSizeFIFOSet(final int capacity) {
      this.capacity = capacity;
      this.set = (LinkedHashSet).MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
   }
}
