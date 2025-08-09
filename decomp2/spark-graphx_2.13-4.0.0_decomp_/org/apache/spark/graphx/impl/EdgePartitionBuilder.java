package org.apache.spark.graphx.impl;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.graphx.Edge;
import org.apache.spark.graphx.Edge$;
import org.apache.spark.graphx.util.collection.GraphXPrimitiveKeyOpenHashMap;
import org.apache.spark.graphx.util.collection.GraphXPrimitiveKeyOpenHashMap$mcJI$sp;
import org.apache.spark.util.collection.PrimitiveVector;
import org.apache.spark.util.collection.Sorter;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rc!\u0002\u0007\u000e\u0001=9\u0002\u0002C\u0010\u0001\u0005\u0003\u0005\u000b\u0011B\u0011\t\u0011\u0011\u0002!1!Q\u0001\f\u0015B\u0001B\u0014\u0001\u0003\u0004\u0003\u0006Ya\u0014\u0005\u0006'\u0002!\t\u0001\u0016\u0005\u00077\u0002\u0001\u000b\u0011\u0002/\t\u000b!\u0004A\u0011A5\t\u000f\u0005\u0015\u0001\u0001\"\u0001\u0002\b\u001dQ\u0011qB\u0007\u0002\u0002#\u0005q\"!\u0005\u0007\u00131i\u0011\u0011!E\u0001\u001f\u0005M\u0001BB*\n\t\u0003\t)\u0002C\u0005\u0002\u0018%\t\n\u0011\"\u0001\u0002\u001a\t!R\tZ4f!\u0006\u0014H/\u001b;j_:\u0014U/\u001b7eKJT!AD\b\u0002\t%l\u0007\u000f\u001c\u0006\u0003!E\taa\u001a:ba\"D(B\u0001\n\u0014\u0003\u0015\u0019\b/\u0019:l\u0015\t!R#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002-\u0005\u0019qN]4\u0016\u0007ai\u0013k\u0005\u0002\u00013A\u0011!$H\u0007\u00027)\tA$A\u0003tG\u0006d\u0017-\u0003\u0002\u001f7\t1\u0011I\\=SK\u001a\fAa]5{K\u000e\u0001\u0001C\u0001\u000e#\u0013\t\u00193DA\u0002J]R\f!\"\u001a<jI\u0016t7-\u001a\u00132!\r1\u0013fK\u0007\u0002O)\u0011\u0001fG\u0001\be\u00164G.Z2u\u0013\tQsE\u0001\u0005DY\u0006\u001c8\u000fV1h!\taS\u0006\u0004\u0001\u0005\u00139\u0002\u0001\u0015!A\u0001\u0006\u0004y#AA#E#\t\u00014\u0007\u0005\u0002\u001bc%\u0011!g\u0007\u0002\b\u001d>$\b.\u001b8h!\tQB'\u0003\u000267\t\u0019\u0011I\\=)\u000b5:$\bR%\u0011\u0005iA\u0014BA\u001d\u001c\u0005-\u0019\b/Z2jC2L'0\u001a32\u000b\rZDHP\u001f\u000f\u0005ia\u0014BA\u001f\u001c\u0003\u0011auN\\42\t\u0011z4\t\b\b\u0003\u0001\u000ek\u0011!\u0011\u0006\u0003\u0005\u0002\na\u0001\u0010:p_Rt\u0014\"\u0001\u000f2\u000b\r*e\tS$\u000f\u0005i1\u0015BA$\u001c\u0003\rIe\u000e^\u0019\u0005I}\u001aE$M\u0003$\u0015.kEJ\u0004\u0002\u001b\u0017&\u0011AjG\u0001\u0007\t>,(\r\\32\t\u0011z4\tH\u0001\u000bKZLG-\u001a8dK\u0012\u0012\u0004c\u0001\u0014*!B\u0011A&\u0015\u0003\u0006%\u0002\u0011\ra\f\u0002\u0003-\u0012\u000ba\u0001P5oSRtDCA+[)\r1\u0006,\u0017\t\u0005/\u0002Y\u0003+D\u0001\u000e\u0011\u0015!C\u0001q\u0001&\u0011\u0015qE\u0001q\u0001P\u0011\u001dyB\u0001%AA\u0002\u0005\nQ!\u001a3hKN\u00042!\u00182e\u001b\u0005q&BA0a\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0003CF\tA!\u001e;jY&\u00111M\u0018\u0002\u0010!JLW.\u001b;jm\u00164Vm\u0019;peB\u0019QMZ\u0016\u000e\u0003=I!aZ\b\u0003\t\u0015#w-Z\u0001\u0004C\u0012$G#\u00026n}\u0006\u0005\u0001C\u0001\u000el\u0013\ta7D\u0001\u0003V]&$\b\"\u00028\u0007\u0001\u0004y\u0017aA:sGB\u0011\u0001o\u001f\b\u0003cft!A\u001d=\u000f\u0005M<hB\u0001;w\u001d\t\u0001U/C\u0001\u0017\u0013\t!R#\u0003\u0002\u0013'%\u0011\u0001#E\u0005\u0003u>\tq\u0001]1dW\u0006<W-\u0003\u0002}{\nAa+\u001a:uKbLEM\u0003\u0002{\u001f!)qP\u0002a\u0001_\u0006\u0019Am\u001d;\t\r\u0005\ra\u00011\u0001,\u0003\u0005!\u0017a\u0004;p\u000b\u0012<W\rU1si&$\u0018n\u001c8\u0016\u0005\u0005%\u0001#B,\u0002\f-\u0002\u0016bAA\u0007\u001b\tiQ\tZ4f!\u0006\u0014H/\u001b;j_:\fA#\u00123hKB\u000b'\u000f^5uS>t')^5mI\u0016\u0014\bCA,\n'\tI\u0011\u0004\u0006\u0002\u0002\u0012\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIE*b!a\u0007\u00022\u0005\u0005SCAA\u000fU\r\t\u0013qD\u0016\u0003\u0003C\u0001B!a\t\u0002.5\u0011\u0011Q\u0005\u0006\u0005\u0003O\tI#A\u0005v]\u000eDWmY6fI*\u0019\u00111F\u000e\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u00020\u0005\u0015\"!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0012Iaf\u0003Q\u0001\u0002\u0003\u0015\ra\f\u0015\n\u0003c9\u0014QGA\u001d\u0003{\tdaI\u001e=\u0003oi\u0014\u0007\u0002\u0013@\u0007r\tdaI#G\u0003w9\u0015\u0007\u0002\u0013@\u0007r\tda\t&L\u0003\u007fa\u0015\u0007\u0002\u0013@\u0007r!QAU\u0006C\u0002=\u0002"
)
public class EdgePartitionBuilder {
   public final ClassTag org$apache$spark$graphx$impl$EdgePartitionBuilder$$evidence$1;
   public final ClassTag org$apache$spark$graphx$impl$EdgePartitionBuilder$$evidence$2;
   public final PrimitiveVector edges;

   public static int $lessinit$greater$default$1() {
      return EdgePartitionBuilder$.MODULE$.$lessinit$greater$default$1();
   }

   public void add(final long src, final long dst, final Object d) {
      this.edges.$plus$eq(new Edge(src, dst, d));
   }

   public EdgePartition toEdgePartition() {
      Edge[] edgeArray = (Edge[])this.edges.trim().array();
      (new Sorter(Edge$.MODULE$.edgeArraySortDataFormat())).sort(edgeArray, 0, edgeArray.length, Edge$.MODULE$.lexicographicOrdering());
      int[] localSrcIds = new int[edgeArray.length];
      int[] localDstIds = new int[edgeArray.length];
      Object data = this.org$apache$spark$graphx$impl$EdgePartitionBuilder$$evidence$1.newArray(edgeArray.length);
      GraphXPrimitiveKeyOpenHashMap index = new GraphXPrimitiveKeyOpenHashMap$mcJI$sp(.MODULE$.apply(Long.TYPE), .MODULE$.Int());
      GraphXPrimitiveKeyOpenHashMap global2local = new GraphXPrimitiveKeyOpenHashMap$mcJI$sp(.MODULE$.apply(Long.TYPE), .MODULE$.Int());
      PrimitiveVector local2global = new PrimitiveVector.mcJ.sp(org.apache.spark.util.collection.PrimitiveVector..MODULE$.$lessinit$greater$default$1(), .MODULE$.apply(Long.TYPE));
      Object vertexAttrs = scala.Array..MODULE$.empty(this.org$apache$spark$graphx$impl$EdgePartitionBuilder$$evidence$2);
      if (edgeArray.length > 0) {
         index.update$mcJI$sp(edgeArray[0].srcId(), 0);
         long currSrcId = edgeArray[0].srcId();
         IntRef currLocalId = IntRef.create(-1);

         for(int i = 0; i < edgeArray.length; ++i) {
            long srcId = edgeArray[i].srcId();
            long dstId = edgeArray[i].dstId();
            localSrcIds[i] = global2local.changeValue$mcJI$sp(srcId, (JFunction0.mcI.sp)() -> {
               ++currLocalId.elem;
               local2global.$plus$eq$mcJ$sp(srcId);
               return currLocalId.elem;
            }, (JFunction1.mcII.sp)(x) -> BoxesRunTime.unboxToInt(scala.Predef..MODULE$.identity(BoxesRunTime.boxToInteger(x))));
            localDstIds[i] = global2local.changeValue$mcJI$sp(dstId, (JFunction0.mcI.sp)() -> {
               ++currLocalId.elem;
               local2global.$plus$eq$mcJ$sp(dstId);
               return currLocalId.elem;
            }, (JFunction1.mcII.sp)(x) -> BoxesRunTime.unboxToInt(scala.Predef..MODULE$.identity(BoxesRunTime.boxToInteger(x))));
            scala.runtime.ScalaRunTime..MODULE$.array_update(data, i, edgeArray[i].attr());
            if (srcId != currSrcId) {
               currSrcId = srcId;
               index.update$mcJI$sp(srcId, i);
            }
         }

         vertexAttrs = this.org$apache$spark$graphx$impl$EdgePartitionBuilder$$evidence$2.newArray(currLocalId.elem + 1);
      }

      return new EdgePartition(localSrcIds, localDstIds, data, index, global2local, local2global.trim$mcJ$sp().array$mcJ$sp(), vertexAttrs, scala.None..MODULE$, this.org$apache$spark$graphx$impl$EdgePartitionBuilder$$evidence$1, this.org$apache$spark$graphx$impl$EdgePartitionBuilder$$evidence$2);
   }

   public void add$mcD$sp(final long src, final long dst, final double d) {
      this.add(src, dst, BoxesRunTime.boxToDouble(d));
   }

   public void add$mcI$sp(final long src, final long dst, final int d) {
      this.add(src, dst, BoxesRunTime.boxToInteger(d));
   }

   public void add$mcJ$sp(final long src, final long dst, final long d) {
      this.add(src, dst, BoxesRunTime.boxToLong(d));
   }

   public EdgePartition toEdgePartition$mcD$sp() {
      return this.toEdgePartition();
   }

   public EdgePartition toEdgePartition$mcI$sp() {
      return this.toEdgePartition();
   }

   public EdgePartition toEdgePartition$mcJ$sp() {
      return this.toEdgePartition();
   }

   public EdgePartitionBuilder(final int size, final ClassTag evidence$1, final ClassTag evidence$2) {
      this.org$apache$spark$graphx$impl$EdgePartitionBuilder$$evidence$1 = evidence$1;
      this.org$apache$spark$graphx$impl$EdgePartitionBuilder$$evidence$2 = evidence$2;
      this.edges = new PrimitiveVector(size, .MODULE$.apply(Edge.class));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
