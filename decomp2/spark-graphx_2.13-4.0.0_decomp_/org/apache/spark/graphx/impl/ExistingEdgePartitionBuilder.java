package org.apache.spark.graphx.impl;

import org.apache.spark.graphx.util.collection.GraphXPrimitiveKeyOpenHashMap;
import org.apache.spark.graphx.util.collection.GraphXPrimitiveKeyOpenHashMap$mcJI$sp;
import org.apache.spark.util.collection.PrimitiveVector;
import org.apache.spark.util.collection.Sorter;
import scala.Option;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005e!\u0002\t\u0012\u0001EY\u0002\u0002C\u0012\u0001\u0005\u0003\u0005\u000b\u0011B\u0013\t\u0011\t\u0003!\u0011!Q\u0001\n\rC\u0001B\u0012\u0001\u0003\u0002\u0003\u0006Ia\u0012\u0005\t'\u0002\u0011\t\u0011)A\u0005)\"A!\f\u0001B\u0001B\u0003%q\b\u0003\u0005\\\u0001\t\r\t\u0015a\u0003]\u0011!Q\bAaA!\u0002\u0017Y\b\"\u0002?\u0001\t\u0003i\b\u0002CA\t\u0001\u0001\u0006I!a\u0005\t\u000f\u0005\u0015\u0002\u0001\"\u0001\u0002(!9\u00111\t\u0001\u0005\u0002\u0005\u0015sACA'#\u0005\u0005\t\u0012A\t\u0002P\u0019I\u0001#EA\u0001\u0012\u0003\t\u0012\u0011\u000b\u0005\u0007y6!\t!a\u0015\t\u0013\u0005US\"%A\u0005\u0002\u0005]#\u0001H#ySN$\u0018N\\4FI\u001e,\u0007+\u0019:uSRLwN\u001c\"vS2$WM\u001d\u0006\u0003%M\tA![7qY*\u0011A#F\u0001\u0007OJ\f\u0007\u000f\u001b=\u000b\u0005Y9\u0012!B:qCJ\\'B\u0001\r\u001a\u0003\u0019\t\u0007/Y2iK*\t!$A\u0002pe\u001e,2\u0001H2K'\t\u0001Q\u0004\u0005\u0002\u001fC5\tqDC\u0001!\u0003\u0015\u00198-\u00197b\u0013\t\u0011sD\u0001\u0004B]f\u0014VMZ\u0001\rO2|'-\u001973Y>\u001c\u0017\r\\\u0002\u0001!\u001113&L \u000e\u0003\u001dR!\u0001K\u0015\u0002\u0015\r|G\u000e\\3di&|gN\u0003\u0002+'\u0005!Q\u000f^5m\u0013\tasEA\u000fHe\u0006\u0004\b\u000e\u0017)sS6LG/\u001b<f\u0017\u0016Lx\n]3o\u0011\u0006\u001c\b.T1q!\tqCH\u0004\u00020u9\u0011\u0001'\u000f\b\u0003car!AM\u001c\u000f\u0005M2T\"\u0001\u001b\u000b\u0005U\"\u0013A\u0002\u001fs_>$h(C\u0001\u001b\u0013\tA\u0012$\u0003\u0002\u0017/%\u0011A#F\u0005\u0003wM\tq\u0001]1dW\u0006<W-\u0003\u0002>}\tAa+\u001a:uKbLEM\u0003\u0002<'A\u0011a\u0004Q\u0005\u0003\u0003~\u00111!\u00138u\u00031awnY1me\u001ddwNY1m!\rqB)L\u0005\u0003\u000b~\u0011Q!\u0011:sCf\f1B^3si\u0016D\u0018\t\u001e;sgB\u0019a\u0004\u0012%\u0011\u0005%SE\u0002\u0001\u0003\u0006\u0017\u0002\u0011\r\u0001\u0014\u0002\u0003-\u0012\u000b\"!\u0014)\u0011\u0005yq\u0015BA( \u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"AH)\n\u0005I{\"aA!os\u0006I\u0011m\u0019;jm\u0016\u001cV\r\u001e\t\u0004=U;\u0016B\u0001, \u0005\u0019y\u0005\u000f^5p]B\u0011a\u0006W\u0005\u00033z\u0012\u0011BV3si\u0016D8+\u001a;\u0002\tML'0Z\u0001\u000bKZLG-\u001a8dK\u0012\u001a\u0004cA/aE6\taL\u0003\u0002`?\u00059!/\u001a4mK\u000e$\u0018BA1_\u0005!\u0019E.Y:t)\u0006<\u0007CA%d\t%!\u0007\u0001)A\u0001\u0002\u000b\u0007AJ\u0001\u0002F\t\"*1MZ5qkB\u0011adZ\u0005\u0003Q~\u00111b\u001d9fG&\fG.\u001b>fIF*1E[6nY:\u0011ad[\u0005\u0003Y~\tA\u0001T8oOF\"AE\\8!\u001d\t\u0019t.C\u0001!c\u0015\u0019\u0013O\u001d;t\u001d\tq\"/\u0003\u0002t?\u0005\u0019\u0011J\u001c;2\t\u0011rw\u000eI\u0019\u0006GY<\u0018\u0010\u001f\b\u0003=]L!\u0001_\u0010\u0002\r\u0011{WO\u00197fc\u0011!cn\u001c\u0011\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$C\u0007E\u0002^A\"\u000ba\u0001P5oSRtDc\u0003@\u0002\b\u0005%\u00111BA\u0007\u0003\u001f!Ra`A\u0002\u0003\u000b\u0001R!!\u0001\u0001E\"k\u0011!\u0005\u0005\u00067\"\u0001\u001d\u0001\u0018\u0005\u0006u\"\u0001\u001da\u001f\u0005\u0006G!\u0001\r!\n\u0005\u0006\u0005\"\u0001\ra\u0011\u0005\u0006\r\"\u0001\ra\u0012\u0005\u0006'\"\u0001\r\u0001\u0016\u0005\b5\"\u0001\n\u00111\u0001@\u0003\u0015)GmZ3t!\u0019\t)\"a\u0007\u0002 5\u0011\u0011q\u0003\u0006\u0004Q\u0005e!B\u0001\u0016\u0016\u0013\u0011\ti\"a\u0006\u0003\u001fA\u0013\u0018.\\5uSZ,g+Z2u_J\u0004R!!\u0001\u0002\"\tL1!a\t\u0012\u0005A)EmZ3XSRDGj\\2bY&#7/A\u0002bI\u0012$B\"!\u000b\u00020\u0005M\u0012qGA\u001e\u0003\u007f\u00012AHA\u0016\u0013\r\tic\b\u0002\u0005+:LG\u000f\u0003\u0004\u00022)\u0001\r!L\u0001\u0004gJ\u001c\u0007BBA\u001b\u0015\u0001\u0007Q&A\u0002egRDa!!\u000f\u000b\u0001\u0004y\u0014\u0001\u00037pG\u0006d7K]2\t\r\u0005u\"\u00021\u0001@\u0003!awnY1m\tN$\bBBA!\u0015\u0001\u0007!-A\u0001e\u0003=!x.\u00123hKB\u000b'\u000f^5uS>tWCAA$!\u0019\t\t!!\u0013c\u0011&\u0019\u00111J\t\u0003\u001b\u0015#w-\u001a)beRLG/[8o\u0003q)\u00050[:uS:<W\tZ4f!\u0006\u0014H/\u001b;j_:\u0014U/\u001b7eKJ\u00042!!\u0001\u000e'\tiQ\u0004\u0006\u0002\u0002P\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIU*b!!\u0017\u0002p\u0005}TCAA.U\ry\u0014QL\u0016\u0003\u0003?\u0002B!!\u0019\u0002l5\u0011\u00111\r\u0006\u0005\u0003K\n9'A\u0005v]\u000eDWmY6fI*\u0019\u0011\u0011N\u0010\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002n\u0005\r$!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0012IAm\u0004Q\u0001\u0002\u0003\u0015\r\u0001\u0014\u0015\n\u0003_2\u00171OA<\u0003w\nda\t6l\u0003kb\u0017\u0007\u0002\u0013o_\u0002\ndaI9s\u0003s\u001a\u0018\u0007\u0002\u0013o_\u0002\nda\t<x\u0003{B\u0018\u0007\u0002\u0013o_\u0002\"QaS\bC\u00021\u0003"
)
public class ExistingEdgePartitionBuilder {
   public final GraphXPrimitiveKeyOpenHashMap org$apache$spark$graphx$impl$ExistingEdgePartitionBuilder$$global2local;
   public final long[] org$apache$spark$graphx$impl$ExistingEdgePartitionBuilder$$local2global;
   public final Object org$apache$spark$graphx$impl$ExistingEdgePartitionBuilder$$vertexAttrs;
   public final Option org$apache$spark$graphx$impl$ExistingEdgePartitionBuilder$$activeSet;
   public final ClassTag org$apache$spark$graphx$impl$ExistingEdgePartitionBuilder$$evidence$3;
   public final ClassTag org$apache$spark$graphx$impl$ExistingEdgePartitionBuilder$$evidence$4;
   public final PrimitiveVector edges;

   public static int $lessinit$greater$default$5() {
      return ExistingEdgePartitionBuilder$.MODULE$.$lessinit$greater$default$5();
   }

   public void add(final long src, final long dst, final int localSrc, final int localDst, final Object d) {
      this.edges.$plus$eq(new EdgeWithLocalIds(src, dst, localSrc, localDst, d));
   }

   public EdgePartition toEdgePartition() {
      EdgeWithLocalIds[] edgeArray = (EdgeWithLocalIds[])this.edges.trim().array();
      (new Sorter(EdgeWithLocalIds$.MODULE$.edgeArraySortDataFormat())).sort(edgeArray, 0, edgeArray.length, EdgeWithLocalIds$.MODULE$.lexicographicOrdering());
      int[] localSrcIds = new int[edgeArray.length];
      int[] localDstIds = new int[edgeArray.length];
      Object data = this.org$apache$spark$graphx$impl$ExistingEdgePartitionBuilder$$evidence$3.newArray(edgeArray.length);
      GraphXPrimitiveKeyOpenHashMap index = new GraphXPrimitiveKeyOpenHashMap$mcJI$sp(.MODULE$.apply(Long.TYPE), .MODULE$.Int());
      if (edgeArray.length > 0) {
         index.update$mcJI$sp(edgeArray[0].srcId(), 0);
         long currSrcId = edgeArray[0].srcId();

         for(int i = 0; i < edgeArray.length; ++i) {
            localSrcIds[i] = edgeArray[i].localSrcId();
            localDstIds[i] = edgeArray[i].localDstId();
            scala.runtime.ScalaRunTime..MODULE$.array_update(data, i, edgeArray[i].attr());
            if (edgeArray[i].srcId() != currSrcId) {
               currSrcId = edgeArray[i].srcId();
               index.update$mcJI$sp(currSrcId, i);
            }
         }
      }

      return new EdgePartition(localSrcIds, localDstIds, data, index, this.org$apache$spark$graphx$impl$ExistingEdgePartitionBuilder$$global2local, this.org$apache$spark$graphx$impl$ExistingEdgePartitionBuilder$$local2global, this.org$apache$spark$graphx$impl$ExistingEdgePartitionBuilder$$vertexAttrs, this.org$apache$spark$graphx$impl$ExistingEdgePartitionBuilder$$activeSet, this.org$apache$spark$graphx$impl$ExistingEdgePartitionBuilder$$evidence$3, this.org$apache$spark$graphx$impl$ExistingEdgePartitionBuilder$$evidence$4);
   }

   public void add$mcD$sp(final long src, final long dst, final int localSrc, final int localDst, final double d) {
      this.add(src, dst, localSrc, localDst, BoxesRunTime.boxToDouble(d));
   }

   public void add$mcI$sp(final long src, final long dst, final int localSrc, final int localDst, final int d) {
      this.add(src, dst, localSrc, localDst, BoxesRunTime.boxToInteger(d));
   }

   public void add$mcJ$sp(final long src, final long dst, final int localSrc, final int localDst, final long d) {
      this.add(src, dst, localSrc, localDst, BoxesRunTime.boxToLong(d));
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

   public ExistingEdgePartitionBuilder(final GraphXPrimitiveKeyOpenHashMap global2local, final long[] local2global, final Object vertexAttrs, final Option activeSet, final int size, final ClassTag evidence$3, final ClassTag evidence$4) {
      this.org$apache$spark$graphx$impl$ExistingEdgePartitionBuilder$$global2local = global2local;
      this.org$apache$spark$graphx$impl$ExistingEdgePartitionBuilder$$local2global = local2global;
      this.org$apache$spark$graphx$impl$ExistingEdgePartitionBuilder$$vertexAttrs = vertexAttrs;
      this.org$apache$spark$graphx$impl$ExistingEdgePartitionBuilder$$activeSet = activeSet;
      this.org$apache$spark$graphx$impl$ExistingEdgePartitionBuilder$$evidence$3 = evidence$3;
      this.org$apache$spark$graphx$impl$ExistingEdgePartitionBuilder$$evidence$4 = evidence$4;
      this.edges = new PrimitiveVector(size, .MODULE$.apply(EdgeWithLocalIds.class));
   }
}
