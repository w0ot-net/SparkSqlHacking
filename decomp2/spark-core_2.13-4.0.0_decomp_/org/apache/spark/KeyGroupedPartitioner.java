package org.apache.spark;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.util.Utils$;
import scala.collection.immutable.ArraySeq;
import scala.collection.immutable.Seq;
import scala.collection.immutable.ArraySeq.;
import scala.collection.mutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005u2QAB\u0004\u0001\u000f5A\u0001B\u0005\u0001\u0003\u0002\u0003\u0006I\u0001\u0006\u0005\tc\u0001\u0011)\u0019!C!e!A1\u0007\u0001B\u0001B\u0003%a\u0006C\u00035\u0001\u0011\u0005Q\u0007C\u0003:\u0001\u0011\u0005#HA\u000bLKf<%o\\;qK\u0012\u0004\u0016M\u001d;ji&|g.\u001a:\u000b\u0005!I\u0011!B:qCJ\\'B\u0001\u0006\f\u0003\u0019\t\u0007/Y2iK*\tA\"A\u0002pe\u001e\u001c\"\u0001\u0001\b\u0011\u0005=\u0001R\"A\u0004\n\u0005E9!a\u0003)beRLG/[8oKJ\f\u0001B^1mk\u0016l\u0015\r]\u0002\u0001!\u0011)BD\b\u0018\u000e\u0003YQ!a\u0006\r\u0002\u000f5,H/\u00192mK*\u0011\u0011DG\u0001\u000bG>dG.Z2uS>t'\"A\u000e\u0002\u000bM\u001c\u0017\r\\1\n\u0005u1\"aA'baB\u0019qd\n\u0016\u000f\u0005\u0001*cBA\u0011%\u001b\u0005\u0011#BA\u0012\u0014\u0003\u0019a$o\\8u}%\t1$\u0003\u0002'5\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u0015*\u0005\r\u0019V-\u001d\u0006\u0003Mi\u0001\"a\u000b\u0017\u000e\u0003iI!!\f\u000e\u0003\u0007\u0005s\u0017\u0010\u0005\u0002,_%\u0011\u0001G\u0007\u0002\u0004\u0013:$\u0018!\u00048v[B\u000b'\u000f^5uS>t7/F\u0001/\u00039qW/\u001c)beRLG/[8og\u0002\na\u0001P5oSRtDc\u0001\u001c8qA\u0011q\u0002\u0001\u0005\u0006%\u0011\u0001\r\u0001\u0006\u0005\u0006c\u0011\u0001\rAL\u0001\rO\u0016$\b+\u0019:uSRLwN\u001c\u000b\u0003]mBQ\u0001P\u0003A\u0002)\n1a[3z\u0001"
)
public class KeyGroupedPartitioner extends Partitioner {
   private final Map valueMap;
   private final int numPartitions;

   public int numPartitions() {
      return this.numPartitions;
   }

   public int getPartition(final Object key) {
      Seq keys = (Seq)key;
      ArraySeq normalizedKeys = .MODULE$.from(keys, scala.reflect.ClassTag..MODULE$.Any());
      return BoxesRunTime.unboxToInt(this.valueMap.getOrElseUpdate(normalizedKeys, (JFunction0.mcI.sp)() -> Utils$.MODULE$.nonNegativeMod(normalizedKeys.hashCode(), this.numPartitions())));
   }

   public KeyGroupedPartitioner(final Map valueMap, final int numPartitions) {
      this.valueMap = valueMap;
      this.numPartitions = numPartitions;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
