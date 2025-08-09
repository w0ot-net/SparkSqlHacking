package org.apache.spark.graphx.impl;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.Partitioner;
import org.apache.spark.graphx.VertexRDD;
import org.apache.spark.rdd.RDD;
import org.apache.spark.rdd.RDD.;
import scala.MatchError;
import scala.Tuple2;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mc!B\f\u0019\u0001a\u0011\u0003\u0002\u0003\u0016\u0001\u0005\u0003\u0007I\u0011\u0001\u0017\t\u0011}\u0002!\u00111A\u0005\u0002\u0001C\u0001B\u0012\u0001\u0003\u0002\u0003\u0006K!\f\u0005\t\u000f\u0002\u0011\t\u0019!C\u0001\u0011\"AA\n\u0001BA\u0002\u0013\u0005Q\n\u0003\u0005P\u0001\t\u0005\t\u0015)\u0003J\u0011!\u0001\u0006A!a\u0001\n\u0003A\u0005\u0002C)\u0001\u0005\u0003\u0007I\u0011\u0001*\t\u0011Q\u0003!\u0011!Q!\n%C\u0001\"\u0016\u0001\u0003\u0004\u0003\u0006YA\u0016\u0005\t9\u0002\u0011\u0019\u0011)A\u0006;\")a\f\u0001C\u0001?\")q\r\u0001C\u0001Q\")1\u0010\u0001C\u0001y\")Q\u0010\u0001C\u0001}\"9\u00111\u0003\u0001\u0005\u0002\u0005U\u0001bBA\u0013\u0001\u0011\u0005\u0011qE\u0004\u000b\u0003[A\u0012\u0011!E\u00011\u0005=b!C\f\u0019\u0003\u0003E\t\u0001GA\u0019\u0011\u0019q6\u0003\"\u0001\u00024!I\u0011QG\n\u0012\u0002\u0013\u0005\u0011q\u0007\u0005\n\u0003'\u001a\u0012\u0013!C\u0001\u0003+\u0012ACU3qY&\u001c\u0017\r^3e-\u0016\u0014H/\u001a=WS\u0016<(BA\r\u001b\u0003\u0011IW\u000e\u001d7\u000b\u0005ma\u0012AB4sCBD\u0007P\u0003\u0002\u001e=\u0005)1\u000f]1sW*\u0011q\u0004I\u0001\u0007CB\f7\r[3\u000b\u0003\u0005\n1a\u001c:h+\r\u0019ShM\n\u0003\u0001\u0011\u0002\"!\n\u0015\u000e\u0003\u0019R\u0011aJ\u0001\u0006g\u000e\fG.Y\u0005\u0003S\u0019\u0012a!\u00118z%\u00164\u0017!B3eO\u0016\u001c8\u0001A\u000b\u0002[A!afL\u0019=\u001b\u0005A\u0012B\u0001\u0019\u0019\u0005-)EmZ3S\t\u0012KU\u000e\u001d7\u0011\u0005I\u001aD\u0002\u0001\u0003\u0006i\u0001\u0011\r!\u000e\u0002\u0003\u000b\u0012\u000b\"AN\u001d\u0011\u0005\u0015:\u0014B\u0001\u001d'\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!\n\u001e\n\u0005m2#aA!osB\u0011!'\u0010\u0003\u0006}\u0001\u0011\r!\u000e\u0002\u0003-\u0012\u000b\u0011\"\u001a3hKN|F%Z9\u0015\u0005\u0005#\u0005CA\u0013C\u0013\t\u0019eE\u0001\u0003V]&$\bbB#\u0003\u0003\u0003\u0005\r!L\u0001\u0004q\u0012\n\u0014AB3eO\u0016\u001c\b%\u0001\u0005iCN\u001c&oY%e+\u0005I\u0005CA\u0013K\u0013\tYeEA\u0004C_>dW-\u00198\u0002\u0019!\f7o\u0015:d\u0013\u0012|F%Z9\u0015\u0005\u0005s\u0005bB#\u0006\u0003\u0003\u0005\r!S\u0001\nQ\u0006\u001c8K]2JI\u0002\n\u0001\u0002[1t\tN$\u0018\nZ\u0001\rQ\u0006\u001cHi\u001d;JI~#S-\u001d\u000b\u0003\u0003NCq!\u0012\u0005\u0002\u0002\u0003\u0007\u0011*A\u0005iCN$5\u000f^%eA\u0005QQM^5eK:\u001cW\rJ\u0019\u0011\u0007]SF(D\u0001Y\u0015\tIf%A\u0004sK\u001adWm\u0019;\n\u0005mC&\u0001C\"mCN\u001cH+Y4\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$#\u0007E\u0002X5F\na\u0001P5oSRtD\u0003\u00021eK\u001a$2!\u00192d!\u0011q\u0003\u0001P\u0019\t\u000bUc\u00019\u0001,\t\u000bqc\u00019A/\t\u000b)b\u0001\u0019A\u0017\t\u000f\u001dc\u0001\u0013!a\u0001\u0013\"9\u0001\u000b\u0004I\u0001\u0002\u0004I\u0015!C<ji\",EmZ3t+\rIW\u000e\u001d\u000b\u0003Ub$2a\u001b:v!\u0011q\u0003\u0001\\8\u0011\u0005IjG!\u00028\u000e\u0005\u0004)$a\u0001,EeA\u0011!\u0007\u001d\u0003\u0006c6\u0011\r!\u000e\u0002\u0004\u000b\u0012\u0013\u0004bB:\u000e\u0003\u0003\u0005\u001d\u0001^\u0001\u000bKZLG-\u001a8dK\u0012\u001a\u0004cA,[Y\"9a/DA\u0001\u0002\b9\u0018AC3wS\u0012,gnY3%iA\u0019qKW8\t\u000bel\u0001\u0019\u0001>\u0002\r}+GmZ3t!\u0011qsf\u001c7\u0002\u000fI,g/\u001a:tKR\t\u0011-A\u0004va\u001e\u0014\u0018\rZ3\u0015\r\u0005{\u00181BA\b\u0011\u001d\t\ta\u0004a\u0001\u0003\u0007\t\u0001B^3si&\u001cWm\u001d\t\u0006\u0003\u000b\t9\u0001P\u0007\u00025%\u0019\u0011\u0011\u0002\u000e\u0003\u0013Y+'\u000f^3y%\u0012#\u0005BBA\u0007\u001f\u0001\u0007\u0011*\u0001\u0006j]\u000edW\u000fZ3Te\u000eDa!!\u0005\u0010\u0001\u0004I\u0015AC5oG2,H-\u001a#ti\u0006iq/\u001b;i\u0003\u000e$\u0018N^3TKR$2!YA\f\u0011\u001d\tI\u0002\u0005a\u0001\u00037\tq!Y2uSZ,7\u000f\r\u0003\u0002\u001e\u0005\u0005\u0002CBA\u0003\u0003\u000f\ty\u0002E\u00023\u0003C!1\"a\t\u0002\u0018\u0005\u0005\t\u0011!B\u0001k\t\u0019q\fJ\u0019\u0002\u001dU\u0004H-\u0019;f-\u0016\u0014H/[2fgR\u0019\u0011-!\u000b\t\u000f\u0005-\u0012\u00031\u0001\u0002\u0004\u00059Q\u000f\u001d3bi\u0016\u001c\u0018\u0001\u0006*fa2L7-\u0019;fIZ+'\u000f^3y-&,w\u000f\u0005\u0002/'M\u00111\u0003\n\u000b\u0003\u0003_\t1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u0012TCBA\u001d\u0003\u001f\n\t&\u0006\u0002\u0002<)\u001a\u0011*!\u0010,\u0005\u0005}\u0002\u0003BA!\u0003\u0017j!!a\u0011\u000b\t\u0005\u0015\u0013qI\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!\u0013'\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003\u001b\n\u0019EA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016$QAP\u000bC\u0002U\"Q\u0001N\u000bC\u0002U\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u001aTCBA\u001d\u0003/\nI\u0006B\u0003?-\t\u0007Q\u0007B\u00035-\t\u0007Q\u0007"
)
public class ReplicatedVertexView {
   private EdgeRDDImpl edges;
   private boolean hasSrcId;
   private boolean hasDstId;
   private final ClassTag evidence$1;
   private final ClassTag evidence$2;

   public static boolean $lessinit$greater$default$3() {
      return ReplicatedVertexView$.MODULE$.$lessinit$greater$default$3();
   }

   public static boolean $lessinit$greater$default$2() {
      return ReplicatedVertexView$.MODULE$.$lessinit$greater$default$2();
   }

   public EdgeRDDImpl edges() {
      return this.edges;
   }

   public void edges_$eq(final EdgeRDDImpl x$1) {
      this.edges = x$1;
   }

   public boolean hasSrcId() {
      return this.hasSrcId;
   }

   public void hasSrcId_$eq(final boolean x$1) {
      this.hasSrcId = x$1;
   }

   public boolean hasDstId() {
      return this.hasDstId;
   }

   public void hasDstId_$eq(final boolean x$1) {
      this.hasDstId = x$1;
   }

   public ReplicatedVertexView withEdges(final EdgeRDDImpl _edges, final ClassTag evidence$3, final ClassTag evidence$4) {
      return new ReplicatedVertexView(_edges, this.hasSrcId(), this.hasDstId(), evidence$3, evidence$4);
   }

   public ReplicatedVertexView reverse() {
      EdgeRDDImpl newEdges = this.edges().mapEdgePartitions((pid, part) -> $anonfun$reverse$1(BoxesRunTime.unboxToInt(pid), part), this.evidence$2, this.evidence$1);
      return new ReplicatedVertexView(newEdges, this.hasDstId(), this.hasSrcId(), this.evidence$1, this.evidence$2);
   }

   public void upgrade(final VertexRDD vertices, final boolean includeSrc, final boolean includeDst) {
      boolean shipSrc = includeSrc && !this.hasSrcId();
      boolean shipDst = includeDst && !this.hasDstId();
      if (shipSrc || shipDst) {
         RDD shippedVerts = .MODULE$.rddToPairRDDFunctions(vertices.shipVertexAttributes(shipSrc, shipDst).setName(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("ReplicatedVertexView.upgrade(%s, %s) - shippedVerts %s %s (broadcast)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToBoolean(includeSrc), BoxesRunTime.boxToBoolean(includeDst), BoxesRunTime.boxToBoolean(shipSrc), BoxesRunTime.boxToBoolean(shipDst)}))), scala.reflect.ClassTag..MODULE$.apply(Integer.TYPE), scala.reflect.ClassTag..MODULE$.apply(VertexAttributeBlock.class), scala.math.Ordering.Int..MODULE$).partitionBy((Partitioner)this.edges().partitioner().get());
         EdgeRDDImpl newEdges = this.edges().withPartitionsRDD(this.edges().partitionsRDD().zipPartitions(shippedVerts, (ePartIter, shippedVertsIter) -> ePartIter.map((x0$1) -> {
               if (x0$1 != null) {
                  int pid = x0$1._1$mcI$sp();
                  EdgePartition edgePartition = (EdgePartition)x0$1._2();
                  return new Tuple2(BoxesRunTime.boxToInteger(pid), edgePartition.updateVertices(shippedVertsIter.flatMap((x$1) -> ((VertexAttributeBlock)x$1._2()).iterator())));
               } else {
                  throw new MatchError(x0$1);
               }
            }), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), this.evidence$2, this.evidence$1);
         this.edges_$eq(newEdges);
         this.hasSrcId_$eq(includeSrc);
         this.hasDstId_$eq(includeDst);
      }
   }

   public ReplicatedVertexView withActiveSet(final VertexRDD actives) {
      RDD shippedActives = .MODULE$.rddToPairRDDFunctions(actives.shipVertexIds().setName("ReplicatedVertexView.withActiveSet - shippedActives (broadcast)"), scala.reflect.ClassTag..MODULE$.apply(Integer.TYPE), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Long.TYPE)), scala.math.Ordering.Int..MODULE$).partitionBy((Partitioner)this.edges().partitioner().get());
      EdgeRDDImpl newEdges = this.edges().withPartitionsRDD(this.edges().partitionsRDD().zipPartitions(shippedActives, (ePartIter, shippedActivesIter) -> ePartIter.map((x0$1) -> {
            if (x0$1 != null) {
               int pid = x0$1._1$mcI$sp();
               EdgePartition edgePartition = (EdgePartition)x0$1._2();
               return new Tuple2(BoxesRunTime.boxToInteger(pid), edgePartition.withActiveSet(shippedActivesIter.flatMap((x$2) -> scala.collection.ArrayOps..MODULE$.iterator$extension(scala.Predef..MODULE$.longArrayOps((long[])x$2._2())))));
            } else {
               throw new MatchError(x0$1);
            }
         }), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), this.evidence$2, this.evidence$1);
      return new ReplicatedVertexView(newEdges, this.hasSrcId(), this.hasDstId(), this.evidence$1, this.evidence$2);
   }

   public ReplicatedVertexView updateVertices(final VertexRDD updates) {
      RDD shippedVerts = .MODULE$.rddToPairRDDFunctions(updates.shipVertexAttributes(this.hasSrcId(), this.hasDstId()).setName(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("ReplicatedVertexView.updateVertices - shippedVerts %s %s (broadcast)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToBoolean(this.hasSrcId()), BoxesRunTime.boxToBoolean(this.hasDstId())}))), scala.reflect.ClassTag..MODULE$.apply(Integer.TYPE), scala.reflect.ClassTag..MODULE$.apply(VertexAttributeBlock.class), scala.math.Ordering.Int..MODULE$).partitionBy((Partitioner)this.edges().partitioner().get());
      EdgeRDDImpl newEdges = this.edges().withPartitionsRDD(this.edges().partitionsRDD().zipPartitions(shippedVerts, (ePartIter, shippedVertsIter) -> ePartIter.map((x0$1) -> {
            if (x0$1 != null) {
               int pid = x0$1._1$mcI$sp();
               EdgePartition edgePartition = (EdgePartition)x0$1._2();
               return new Tuple2(BoxesRunTime.boxToInteger(pid), edgePartition.updateVertices(shippedVertsIter.flatMap((x$3) -> ((VertexAttributeBlock)x$3._2()).iterator())));
            } else {
               throw new MatchError(x0$1);
            }
         }), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), this.evidence$2, this.evidence$1);
      return new ReplicatedVertexView(newEdges, this.hasSrcId(), this.hasDstId(), this.evidence$1, this.evidence$2);
   }

   // $FF: synthetic method
   public static final EdgePartition $anonfun$reverse$1(final int pid, final EdgePartition part) {
      return part.reverse();
   }

   public ReplicatedVertexView(final EdgeRDDImpl edges, final boolean hasSrcId, final boolean hasDstId, final ClassTag evidence$1, final ClassTag evidence$2) {
      this.edges = edges;
      this.hasSrcId = hasSrcId;
      this.hasDstId = hasDstId;
      this.evidence$1 = evidence$1;
      this.evidence$2 = evidence$2;
      super();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
