package org.apache.spark.graphx.impl;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.graphx.Edge;
import org.apache.spark.graphx.util.collection.GraphXPrimitiveKeyOpenHashMap;
import org.apache.spark.util.collection.BitSet;
import org.apache.spark.util.collection.PrimitiveVector;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.Array.;
import scala.collection.Iterator;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction0;

public final class RoutingTablePartition$ implements Serializable {
   public static final RoutingTablePartition$ MODULE$ = new RoutingTablePartition$();
   private static final RoutingTablePartition empty;

   static {
      empty = new RoutingTablePartition((Tuple3[]).MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(Tuple3.class)));
   }

   private Tuple2 toMessage(final long vid, final int pid, final byte position) {
      int positionUpper2 = position << 30;
      int pidLower30 = pid & 1073741823;
      return new Tuple2.mcJI.sp(vid, positionUpper2 | pidLower30);
   }

   private long vidFromMessage(final Tuple2 msg) {
      return msg._1$mcJ$sp();
   }

   private int pidFromMessage(final Tuple2 msg) {
      return msg._2$mcI$sp() & 1073741823;
   }

   private byte positionFromMessage(final Tuple2 msg) {
      return (byte)(msg._2$mcI$sp() >> 30);
   }

   public RoutingTablePartition empty() {
      return empty;
   }

   public Iterator edgePartitionToMsgs(final int pid, final EdgePartition edgePartition) {
      GraphXPrimitiveKeyOpenHashMap map = new GraphXPrimitiveKeyOpenHashMap(scala.reflect.ClassTag..MODULE$.apply(Long.TYPE), scala.reflect.ClassTag..MODULE$.Byte());
      edgePartition.iterator().foreach((e) -> BoxesRunTime.boxToByte($anonfun$edgePartitionToMsgs$1(map, e)));
      return map.iterator().map((vidAndPosition) -> {
         long vid = vidAndPosition._1$mcJ$sp();
         byte position = BoxesRunTime.unboxToByte(vidAndPosition._2());
         return MODULE$.toMessage(vid, pid, position);
      });
   }

   public RoutingTablePartition fromMsgs(final int numEdgePartitions, final Iterator iter) {
      PrimitiveVector[] pid2vid = (PrimitiveVector[]).MODULE$.fill(numEdgePartitions, () -> new PrimitiveVector.mcJ.sp(org.apache.spark.util.collection.PrimitiveVector..MODULE$.$lessinit$greater$default$1(), scala.reflect.ClassTag..MODULE$.apply(Long.TYPE)), scala.reflect.ClassTag..MODULE$.apply(PrimitiveVector.class));
      PrimitiveVector[] srcFlags = (PrimitiveVector[]).MODULE$.fill(numEdgePartitions, () -> new PrimitiveVector(org.apache.spark.util.collection.PrimitiveVector..MODULE$.$lessinit$greater$default$1(), scala.reflect.ClassTag..MODULE$.Boolean()), scala.reflect.ClassTag..MODULE$.apply(PrimitiveVector.class));
      PrimitiveVector[] dstFlags = (PrimitiveVector[]).MODULE$.fill(numEdgePartitions, () -> new PrimitiveVector(org.apache.spark.util.collection.PrimitiveVector..MODULE$.$lessinit$greater$default$1(), scala.reflect.ClassTag..MODULE$.Boolean()), scala.reflect.ClassTag..MODULE$.apply(PrimitiveVector.class));
      iter.foreach((msg) -> {
         $anonfun$fromMsgs$4(pid2vid, srcFlags, dstFlags, msg);
         return BoxedUnit.UNIT;
      });
      return new RoutingTablePartition((Tuple3[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps((Object[])pid2vid))), (x0$1) -> {
         if (x0$1 != null) {
            PrimitiveVector vids = (PrimitiveVector)x0$1._1();
            int pid = x0$1._2$mcI$sp();
            return new Tuple3(vids.trim$mcJ$sp().array$mcJ$sp(), MODULE$.toBitSet(srcFlags[pid]), MODULE$.toBitSet(dstFlags[pid]));
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple3.class)));
   }

   private BitSet toBitSet(final PrimitiveVector flags) {
      BitSet bitset = new BitSet(flags.size());

      for(int i = 0; i < flags.size(); ++i) {
         if (BoxesRunTime.unboxToBoolean(flags.apply(i))) {
            bitset.set(i);
         }
      }

      return bitset;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RoutingTablePartition$.class);
   }

   // $FF: synthetic method
   public static final byte $anonfun$edgePartitionToMsgs$3(final byte b) {
      return (byte)(b | 1);
   }

   // $FF: synthetic method
   public static final byte $anonfun$edgePartitionToMsgs$5(final byte b) {
      return (byte)(b | 2);
   }

   // $FF: synthetic method
   public static final byte $anonfun$edgePartitionToMsgs$1(final GraphXPrimitiveKeyOpenHashMap map$1, final Edge e) {
      map$1.changeValue(BoxesRunTime.boxToLong(e.srcId()), (JFunction0.mcB.sp)() -> 1, (b) -> BoxesRunTime.boxToByte($anonfun$edgePartitionToMsgs$3(BoxesRunTime.unboxToByte(b))));
      return BoxesRunTime.unboxToByte(map$1.changeValue(BoxesRunTime.boxToLong(e.dstId()), (JFunction0.mcB.sp)() -> 2, (b) -> BoxesRunTime.boxToByte($anonfun$edgePartitionToMsgs$5(BoxesRunTime.unboxToByte(b)))));
   }

   // $FF: synthetic method
   public static final void $anonfun$fromMsgs$4(final PrimitiveVector[] pid2vid$1, final PrimitiveVector[] srcFlags$1, final PrimitiveVector[] dstFlags$1, final Tuple2 msg) {
      long vid = MODULE$.vidFromMessage(msg);
      int pid = MODULE$.pidFromMessage(msg);
      byte position = MODULE$.positionFromMessage(msg);
      pid2vid$1[pid].$plus$eq$mcJ$sp(vid);
      srcFlags$1[pid].$plus$eq(BoxesRunTime.boxToBoolean((position & 1) != 0));
      dstFlags$1[pid].$plus$eq(BoxesRunTime.boxToBoolean((position & 2) != 0));
   }

   private RoutingTablePartition$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
