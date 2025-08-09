package org.apache.spark.graphx.impl;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.util.collection.BitSet;
import scala.Function1;
import scala.MatchError;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055vA\u0002\r\u001a\u0011\u0003Y2E\u0002\u0004&3!\u00051D\n\u0005\u0006k\u0005!\taN\u0003\u0005q\u0005\u0001\u0011\bC\u0003R\u0003\u0011%!\u000bC\u0003b\u0003\u0011%!\rC\u0003f\u0003\u0011%a\rC\u0003i\u0003\u0011%\u0011\u000eC\u0004l\u0003\t\u0007I\u0011\u00017\t\u000f\u0005M\u0013\u0001)A\u0005[\"9\u0011QK\u0001\u0005\u0002\u0005]\u0003bBAC\u0003\u0011\u0005\u0011q\u0011\u0005\b\u0003\u001f\u000bA\u0011BAI\u0011%\ti*AA\u0001\n\u0013\tyJB\u0003&3\u0001Yb\u000e\u0003\u0005w\u001d\t\u0015\r\u0011\"\u0003x\u0011%\tyA\u0004B\u0001B\u0003%\u0001\u0010\u0003\u00046\u001d\u0011\u0005\u0011\u0011\u0003\u0005\n\u0003+q!\u0019!C\u0001\u0003/Aq!!\u0007\u000fA\u0003%a\nC\u0004\u0002\u001c9!\t!!\b\t\u000f\u0005\u0005b\u0002\"\u0001\u0002$!1\u00111\u0006\b\u0005\u00021Dq!!\f\u000f\t\u0003\ty#A\u000bS_V$\u0018N\\4UC\ndW\rU1si&$\u0018n\u001c8\u000b\u0005iY\u0012\u0001B5na2T!\u0001H\u000f\u0002\r\u001d\u0014\u0018\r\u001d5y\u0015\tqr$A\u0003ta\u0006\u00148N\u0003\u0002!C\u00051\u0011\r]1dQ\u0016T\u0011AI\u0001\u0004_J<\u0007C\u0001\u0013\u0002\u001b\u0005I\"!\u0006*pkRLgn\u001a+bE2,\u0007+\u0019:uSRLwN\\\n\u0004\u0003\u001dj\u0003C\u0001\u0015,\u001b\u0005I#\"\u0001\u0016\u0002\u000bM\u001c\u0017\r\\1\n\u00051J#AB!osJ+g\r\u0005\u0002/g5\tqF\u0003\u00021c\u0005\u0011\u0011n\u001c\u0006\u0002e\u0005!!.\u0019<b\u0013\t!tF\u0001\u0007TKJL\u0017\r\\5{C\ndW-\u0001\u0004=S:LGOP\u0002\u0001)\u0005\u0019#a\u0005*pkRLgn\u001a+bE2,W*Z:tC\u001e,\u0007\u0003\u0002\u0015;y9K!aO\u0015\u0003\rQ+\b\u000f\\33!\ti4J\u0004\u0002?\u0013:\u0011q\b\u0013\b\u0003\u0001\u001es!!\u0011$\u000f\u0005\t+U\"A\"\u000b\u0005\u00113\u0014A\u0002\u001fs_>$h(C\u0001#\u0013\t\u0001\u0013%\u0003\u0002\u001f?%\u0011A$H\u0005\u0003\u0015n\tq\u0001]1dW\u0006<W-\u0003\u0002M\u001b\nAa+\u001a:uKbLEM\u0003\u0002K7A\u0011\u0001fT\u0005\u0003!&\u00121!\u00138u\u0003%!x.T3tg\u0006<W\r\u0006\u0003T+^c\u0006C\u0001+\u0004\u001b\u0005\t\u0001\"\u0002,\u0005\u0001\u0004a\u0014a\u0001<jI\")\u0001\f\u0002a\u00013\u0006\u0019\u0001/\u001b3\u0011\u0005uR\u0016BA.N\u0005-\u0001\u0016M\u001d;ji&|g.\u0013#\t\u000bu#\u0001\u0019\u00010\u0002\u0011A|7/\u001b;j_:\u0004\"\u0001K0\n\u0005\u0001L#\u0001\u0002\"zi\u0016\faB^5e\rJ|W.T3tg\u0006<W\r\u0006\u0002=G\")A-\u0002a\u0001'\u0006\u0019Qn]4\u0002\u001dALGM\u0012:p[6+7o]1hKR\u0011\u0011l\u001a\u0005\u0006I\u001a\u0001\raU\u0001\u0014a>\u001c\u0018\u000e^5p]\u001a\u0013x.\\'fgN\fw-\u001a\u000b\u0003=*DQ\u0001Z\u0004A\u0002M\u000bQ!Z7qif,\u0012!\u001c\t\u0003I9\u00192AD\u0014p!\t\u0001HO\u0004\u0002rg:\u0011!I]\u0005\u0002U%\u0011!*K\u0005\u0003iUT!AS\u0015\u0002\u0019I|W\u000f^5oOR\u000b'\r\\3\u0016\u0003a\u00042\u0001K=|\u0013\tQ\u0018FA\u0003BeJ\f\u0017\u0010E\u0003)yz|x0\u0003\u0002~S\t1A+\u001e9mKN\u00022\u0001K==!\u0011\t\t!a\u0003\u000e\u0005\u0005\r!\u0002BA\u0003\u0003\u000f\t!bY8mY\u0016\u001cG/[8o\u0015\r\tI!H\u0001\u0005kRLG.\u0003\u0003\u0002\u000e\u0005\r!A\u0002\"jiN+G/A\u0007s_V$\u0018N\\4UC\ndW\r\t\u000b\u0004[\u0006M\u0001\"\u0002<\u0012\u0001\u0004A\u0018!\u00058v[\u0016#w-\u001a)beRLG/[8ogV\ta*\u0001\nok6,EmZ3QCJ$\u0018\u000e^5p]N\u0004\u0013!\u00049beRLG/[8o'&TX\rF\u0002O\u0003?AQ\u0001\u0017\u000bA\u0002e\u000b\u0001\"\u001b;fe\u0006$xN]\u000b\u0003\u0003K\u0001B\u0001]A\u0014y%\u0019\u0011\u0011F;\u0003\u0011%#XM]1u_J\fqA]3wKJ\u001cX-\u0001\u000eg_J,\u0017m\u00195XSRD\u0017N\\#eO\u0016\u0004\u0016M\u001d;ji&|g\u000e\u0006\u0005\u00022\u0005\r\u0013QIA()\u0011\t\u0019$!\u000f\u0011\u0007!\n)$C\u0002\u00028%\u0012A!\u00168ji\"9\u00111H\fA\u0002\u0005u\u0012!\u00014\u0011\r!\ny\u0004PA\u001a\u0013\r\t\t%\u000b\u0002\n\rVt7\r^5p]FBQ\u0001W\fA\u0002eCq!a\u0012\u0018\u0001\u0004\tI%\u0001\u0006j]\u000edW\u000fZ3Te\u000e\u00042\u0001KA&\u0013\r\ti%\u000b\u0002\b\u0005>|G.Z1o\u0011\u001d\t\tf\u0006a\u0001\u0003\u0013\n!\"\u001b8dYV$W\rR:u\u0003\u0019)W\u000e\u001d;zA\u0005\u0019R\rZ4f!\u0006\u0014H/\u001b;j_:$v.T:hgR1\u0011\u0011LA.\u0003;\u0002B\u0001]A\u0014'\")\u0001L\u0003a\u00013\"9\u0011q\f\u0006A\u0002\u0005\u0005\u0014!D3eO\u0016\u0004\u0016M\u001d;ji&|g\u000e\r\u0004\u0002d\u00055\u0014\u0011\u0011\t\bI\u0005\u0015\u0014\u0011NA@\u0013\r\t9'\u0007\u0002\u000e\u000b\u0012<W\rU1si&$\u0018n\u001c8\u0011\t\u0005-\u0014Q\u000e\u0007\u0001\t1\ty'!\u0018\u0002\u0002\u0003\u0005)\u0011AA9\u0005\ryF%M\t\u0005\u0003g\nI\bE\u0002)\u0003kJ1!a\u001e*\u0005\u001dqu\u000e\u001e5j]\u001e\u00042\u0001KA>\u0013\r\ti(\u000b\u0002\u0004\u0003:L\b\u0003BA6\u0003\u0003#A\"a!\u0002^\u0005\u0005\t\u0011!B\u0001\u0003c\u00121a\u0018\u00133\u0003!1'o\\7Ng\u001e\u001cH#B7\u0002\n\u0006-\u0005BBA\u000b\u0017\u0001\u0007a\nC\u0004\u0002\u000e.\u0001\r!!\u0017\u0002\t%$XM]\u0001\ti>\u0014\u0015\u000e^*fiR\u0019q0a%\t\u000f\u0005UE\u00021\u0001\u0002\u0018\u0006)a\r\\1hgB1\u0011\u0011AAM\u0003\u0013JA!a'\u0002\u0004\ty\u0001K]5nSRLg/\u001a,fGR|'/\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002\"B!\u00111UAU\u001b\t\t)KC\u0002\u0002(F\nA\u0001\\1oO&!\u00111VAS\u0005\u0019y%M[3di\u0002"
)
public class RoutingTablePartition implements Serializable {
   private final Tuple3[] routingTable;
   private final int numEdgePartitions;

   public static RoutingTablePartition fromMsgs(final int numEdgePartitions, final Iterator iter) {
      return RoutingTablePartition$.MODULE$.fromMsgs(numEdgePartitions, iter);
   }

   public static Iterator edgePartitionToMsgs(final int pid, final EdgePartition edgePartition) {
      return RoutingTablePartition$.MODULE$.edgePartitionToMsgs(pid, edgePartition);
   }

   public static RoutingTablePartition empty() {
      return RoutingTablePartition$.MODULE$.empty();
   }

   private Tuple3[] routingTable() {
      return this.routingTable;
   }

   public int numEdgePartitions() {
      return this.numEdgePartitions;
   }

   public int partitionSize(final int pid) {
      return ((long[])this.routingTable()[pid]._1()).length;
   }

   public Iterator iterator() {
      return .MODULE$.iterator$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.routingTable())).flatMap((x$1) -> .MODULE$.iterator$extension(scala.Predef..MODULE$.longArrayOps((long[])x$1._1())));
   }

   public RoutingTablePartition reverse() {
      return new RoutingTablePartition((Tuple3[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.routingTable()), (x0$1) -> {
         if (x0$1 != null) {
            long[] vids = (long[])x0$1._1();
            BitSet srcVids = (BitSet)x0$1._2();
            BitSet dstVids = (BitSet)x0$1._3();
            return new Tuple3(vids, dstVids, srcVids);
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple3.class)));
   }

   public void foreachWithinEdgePartition(final int pid, final boolean includeSrc, final boolean includeDst, final Function1 f) {
      Tuple3 var7 = this.routingTable()[pid];
      if (var7 != null) {
         long[] vidsCandidate = (long[])var7._1();
         BitSet srcVids = (BitSet)var7._2();
         BitSet dstVids = (BitSet)var7._3();
         Tuple3 var6 = new Tuple3(vidsCandidate, srcVids, dstVids);
         long[] vidsCandidate = (long[])var6._1();
         BitSet srcVids = (BitSet)var6._2();
         BitSet dstVids = (BitSet)var6._3();
         int var14 = vidsCandidate.length;
         if (includeSrc && includeDst) {
            .MODULE$.iterator$extension(scala.Predef..MODULE$.longArrayOps(vidsCandidate)).foreach(f);
         } else if (includeSrc || includeDst) {
            BitSet relevantVids = includeSrc ? srcVids : dstVids;
            relevantVids.iterator().foreach((JFunction1.mcVI.sp)(i) -> f.apply$mcVJ$sp(vidsCandidate[i]));
         }
      } else {
         throw new MatchError(var7);
      }
   }

   public RoutingTablePartition(final Tuple3[] routingTable) {
      this.routingTable = routingTable;
      this.numEdgePartitions = routingTable.length;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
