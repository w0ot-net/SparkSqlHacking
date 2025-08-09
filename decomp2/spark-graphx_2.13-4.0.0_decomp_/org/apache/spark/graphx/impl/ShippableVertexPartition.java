package org.apache.spark.graphx.impl;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.util.collection.BitSet;
import org.apache.spark.util.collection.OpenHashSet;
import org.apache.spark.util.collection.PrimitiveVector;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.package.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\tmqA\u0002\u000e\u001c\u0011\u0003iRE\u0002\u0004(7!\u0005Q\u0004\u000b\u0005\u0006o\u0005!\t!\u000f\u0005\u0006u\u0005!\ta\u000f\u0005\u0007u\u0005!\t!!\u001f\t\ri\nA\u0011AAL\u0011\u001d\ti,\u0001C\u0002\u0003\u007f;q!a7\u0002\u0011\u0007\tiNB\u0004\u0002b\u0006A\t!a9\t\r]BA\u0011AAw\u0011\u001d\ty\u000f\u0003C\u0001\u0003cD\u0011Ba\u0003\u0002\u0003\u0003%IA!\u0004\u0007\u000b\u001dZ\u0002!H \t\u0011=c!Q1A\u0005\u0002AC\u0001\u0002\u0017\u0007\u0003\u0002\u0003\u0006I!\u0015\u0005\t32\u0011)\u0019!C\u00015\"Aa\f\u0004B\u0001B\u0003%1\f\u0003\u0005`\u0019\t\u0015\r\u0011\"\u0001a\u0011!IGB!A!\u0002\u0013\t\u0007\u0002\u00036\r\u0005\u000b\u0007I\u0011A6\t\u0011=d!\u0011!Q\u0001\n1D\u0001\u0002\u001d\u0007\u0003\u0004\u0003\u0006Y!\u001d\u0005\u0006o1!\ta\u001e\u0005\u0007\u007f2!\t!!\u0001\t\u000f\u0005\u001dA\u0002\"\u0001\u0002\n!9\u0011q\u000b\u0007\u0005\u0002\u0005e\u0013\u0001G*iSB\u0004\u0018M\u00197f-\u0016\u0014H/\u001a=QCJ$\u0018\u000e^5p]*\u0011A$H\u0001\u0005S6\u0004HN\u0003\u0002\u001f?\u00051qM]1qQbT!\u0001I\u0011\u0002\u000bM\u0004\u0018M]6\u000b\u0005\t\u001a\u0013AB1qC\u000eDWMC\u0001%\u0003\ry'o\u001a\t\u0003M\u0005i\u0011a\u0007\u0002\u0019'\"L\u0007\u000f]1cY\u00164VM\u001d;fqB\u000b'\u000f^5uS>t7cA\u0001*_A\u0011!&L\u0007\u0002W)\tA&A\u0003tG\u0006d\u0017-\u0003\u0002/W\t1\u0011I\\=SK\u001a\u0004\"\u0001M\u001b\u000e\u0003ER!AM\u001a\u0002\u0005%|'\"\u0001\u001b\u0002\t)\fg/Y\u0005\u0003mE\u0012AbU3sS\u0006d\u0017N_1cY\u0016\fa\u0001P5oSRt4\u0001\u0001\u000b\u0002K\u0005)\u0011\r\u001d9msV\u0019A(!\u001b\u0015\u0007u\n\t\bF\u0002?\u0003W\u0002BA\n\u0007\u0002hU\u0011\u0001IR\n\u0003\u0019\u0005\u00032A\n\"E\u0013\t\u00195DA\nWKJ$X\r\u001f)beRLG/[8o\u0005\u0006\u001cX\r\u0005\u0002F\r2\u0001A!B$\r\u0005\u0004A%A\u0001,E#\tIE\n\u0005\u0002+\u0015&\u00111j\u000b\u0002\b\u001d>$\b.\u001b8h!\tQS*\u0003\u0002OW\t\u0019\u0011I\\=\u0002\u000b%tG-\u001a=\u0016\u0003E\u0003\"AU+\u000f\u0005\u0019\u001a\u0016B\u0001+\u001c\u0003\u001d\u0001\u0018mY6bO\u0016L!AV,\u0003%Y+'\u000f^3y\u0013\u0012$v.\u00138eKbl\u0015\r\u001d\u0006\u0003)n\ta!\u001b8eKb\u0004\u0013A\u0002<bYV,7/F\u0001\\!\rQC\fR\u0005\u0003;.\u0012Q!\u0011:sCf\fqA^1mk\u0016\u001c\b%\u0001\u0003nCN\\W#A1\u0011\u0005\t<W\"A2\u000b\u0005\u0011,\u0017AC2pY2,7\r^5p]*\u0011amH\u0001\u0005kRLG.\u0003\u0002iG\n1!)\u001b;TKR\fQ!\\1tW\u0002\nAB]8vi&tw\rV1cY\u0016,\u0012\u0001\u001c\t\u0003M5L!A\\\u000e\u0003+I{W\u000f^5oOR\u000b'\r\\3QCJ$\u0018\u000e^5p]\u0006i!o\\;uS:<G+\u00192mK\u0002\n!\"\u001a<jI\u0016t7-\u001a\u00138!\r\u0011X\u000fR\u0007\u0002g*\u0011AoK\u0001\be\u00164G.Z2u\u0013\t18O\u0001\u0005DY\u0006\u001c8\u000fV1h)\u0015A8\u0010`?\u007f)\tI(\u0010E\u0002'\u0019\u0011CQ\u0001\u001d\fA\u0004EDQa\u0014\fA\u0002ECQ!\u0017\fA\u0002mCQa\u0018\fA\u0002\u0005DQA\u001b\fA\u00021\f\u0001c^5uQJ{W\u000f^5oOR\u000b'\r\\3\u0015\u0007e\f\u0019\u0001\u0003\u0004\u0002\u0006]\u0001\r\u0001\\\u0001\u000e?J|W\u000f^5oOR\u000b'\r\\3\u0002)MD\u0017\u000e\u001d,feR,\u00070\u0011;ue&\u0014W\u000f^3t)\u0019\tY!!\u0013\u0002TA1\u0011QBA\u000e\u0003CqA!a\u0004\u0002\u001a9!\u0011\u0011CA\f\u001b\t\t\u0019BC\u0002\u0002\u0016a\na\u0001\u0010:p_Rt\u0014\"\u0001\u0017\n\u0005Q[\u0013\u0002BA\u000f\u0003?\u0011\u0001\"\u0013;fe\u0006$xN\u001d\u0006\u0003).\u0002rAKA\u0012\u0003O\t\u0019%C\u0002\u0002&-\u0012a\u0001V;qY\u0016\u0014\u0004\u0003BA\u0015\u0003{qA!a\u000b\u0002<9!\u0011QFA\u001d\u001d\u0011\ty#a\u000e\u000f\t\u0005E\u0012Q\u0007\b\u0005\u0003#\t\u0019$C\u0001%\u0013\t\u00113%\u0003\u0002!C%\u0011adH\u0005\u0003)vIA!a\u0010\u0002B\tY\u0001+\u0019:uSRLwN\\%E\u0015\t!V\u0004\u0005\u0003'\u0003\u000b\"\u0015bAA$7\t!b+\u001a:uKb\fE\u000f\u001e:jEV$XM\u00117pG.Dq!a\u0013\u0019\u0001\u0004\ti%A\u0004tQ&\u00048K]2\u0011\u0007)\ny%C\u0002\u0002R-\u0012qAQ8pY\u0016\fg\u000eC\u0004\u0002Va\u0001\r!!\u0014\u0002\u000fMD\u0017\u000e\u001d#ti\u0006i1\u000f[5q-\u0016\u0014H/\u001a=JIN$\"!a\u0017\u0011\r\u00055\u00111DA/!\u001dQ\u00131EA\u0014\u0003?\u0002BA\u000b/\u0002bA!\u0011\u0011FA2\u0013\u0011\t)'!\u0011\u0003\u0011Y+'\u000f^3y\u0013\u0012\u00042!RA5\t\u001595A1\u0001I\u0011%\tigAA\u0001\u0002\b\ty'\u0001\u0006fm&$WM\\2fII\u0002BA];\u0002h!9\u00111O\u0002A\u0002\u0005U\u0014\u0001B5uKJ\u0004b!!\u0004\u0002\u001c\u0005]\u0004c\u0002\u0016\u0002$\u0005\u0005\u0014qM\u000b\u0005\u0003w\n\u0019\t\u0006\u0005\u0002~\u0005-\u0015\u0011SAJ)\u0011\ty(!\"\u0011\t\u0019b\u0011\u0011\u0011\t\u0004\u000b\u0006\rE!B$\u0005\u0005\u0004A\u0005\"CAD\t\u0005\u0005\t9AAE\u0003))g/\u001b3f]\u000e,Ge\r\t\u0005eV\f\t\tC\u0004\u0002t\u0011\u0001\r!!$\u0011\r\u00055\u00111DAH!\u001dQ\u00131EA1\u0003\u0003CQA\u001b\u0003A\u00021Dq!!&\u0005\u0001\u0004\t\t)\u0001\u0006eK\u001a\fW\u000f\u001c;WC2,B!!'\u0002\"RQ\u00111TAU\u0003_\u000b\t,a-\u0015\t\u0005u\u00151\u0015\t\u0005M1\ty\nE\u0002F\u0003C#QaR\u0003C\u0002!C\u0011\"!*\u0006\u0003\u0003\u0005\u001d!a*\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$C\u0007\u0005\u0003sk\u0006}\u0005bBA:\u000b\u0001\u0007\u00111\u0016\t\u0007\u0003\u001b\tY\"!,\u0011\u000f)\n\u0019#!\u0019\u0002 \")!.\u0002a\u0001Y\"9\u0011QS\u0003A\u0002\u0005}\u0005bBA[\u000b\u0001\u0007\u0011qW\u0001\n[\u0016\u0014x-\u001a$v]\u000e\u0004\u0012BKA]\u0003?\u000by*a(\n\u0007\u0005m6FA\u0005Gk:\u001cG/[8oe\u000592\u000f[5qa\u0006\u0014G.\u001a)beRLG/[8o)>|\u0005o]\u000b\u0005\u0003\u0003\fi\r\u0006\u0003\u0002D\u0006UG\u0003BAc\u0003\u001f\u0004RAJAd\u0003\u0017L1!!3\u001c\u0005m\u0019\u0006.\u001b9qC\ndWMV3si\u0016D\b+\u0019:uSRLwN\\(qgB\u0019Q)!4\u0005\u000b\u001d3!\u0019\u0001%\t\u0013\u0005Eg!!AA\u0004\u0005M\u0017AC3wS\u0012,gnY3%kA!!/^Af\u0011\u001d\t9N\u0002a\u0001\u00033\f\u0011\u0002]1si&$\u0018n\u001c8\u0011\t\u0019b\u00111Z\u0001''\"L\u0007\u000f]1cY\u00164VM\u001d;fqB\u000b'\u000f^5uS>tw\n]:D_:\u001cHO];di>\u0014\bcAAp\u00115\t\u0011A\u0001\u0014TQ&\u0004\b/\u00192mKZ+'\u000f^3y!\u0006\u0014H/\u001b;j_:|\u0005o]\"p]N$(/^2u_J\u001cB\u0001C\u0015\u0002fB)a%a:\u0002l&\u0019\u0011\u0011^\u000e\u0003CY+'\u000f^3y!\u0006\u0014H/\u001b;j_:\u0014\u0015m]3PaN\u001cuN\\:ueV\u001cGo\u001c:\u0011\u0005\u0019bACAAo\u0003\u0015!xn\u00149t+\u0011\t\u00190a@\u0015\t\u0005U(q\u0001\u000b\u0005\u0003o\u0014\t\u0001E\u0004'\u0003s\fi0a;\n\u0007\u0005m8D\u0001\fWKJ$X\r\u001f)beRLG/[8o\u0005\u0006\u001cXm\u00149t!\r)\u0015q \u0003\u0006\u000f*\u0011\r\u0001\u0013\u0005\n\u0005\u0007Q\u0011\u0011!a\u0002\u0005\u000b\t!\"\u001a<jI\u0016t7-\u001a\u00137!\u0011\u0011X/!@\t\u000f\u0005]'\u00021\u0001\u0003\nA!a\u0005DA\u007f\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\u0011y\u0001\u0005\u0003\u0003\u0012\t]QB\u0001B\n\u0015\r\u0011)bM\u0001\u0005Y\u0006tw-\u0003\u0003\u0003\u001a\tM!AB(cU\u0016\u001cG\u000f"
)
public class ShippableVertexPartition extends VertexPartitionBase {
   private final OpenHashSet index;
   private final Object values;
   private final BitSet mask;
   private final RoutingTablePartition routingTable;
   private final ClassTag evidence$7;

   public static ShippableVertexPartitionOps shippablePartitionToOps(final ShippableVertexPartition partition, final ClassTag evidence$5) {
      return ShippableVertexPartition$.MODULE$.shippablePartitionToOps(partition, evidence$5);
   }

   public OpenHashSet index() {
      return this.index;
   }

   public Object values() {
      return this.values;
   }

   public BitSet mask() {
      return this.mask;
   }

   public RoutingTablePartition routingTable() {
      return this.routingTable;
   }

   public ShippableVertexPartition withRoutingTable(final RoutingTablePartition _routingTable) {
      return new ShippableVertexPartition(this.index(), this.values(), this.mask(), _routingTable, this.evidence$7);
   }

   public Iterator shipVertexAttributes(final boolean shipSrc, final boolean shipDst) {
      return .MODULE$.Iterator().tabulate(this.routingTable().numEdgePartitions(), (pid) -> $anonfun$shipVertexAttributes$1(this, shipSrc, shipDst, BoxesRunTime.unboxToInt(pid)));
   }

   public Iterator shipVertexIds() {
      return .MODULE$.Iterator().tabulate(this.routingTable().numEdgePartitions(), (pid) -> $anonfun$shipVertexIds$1(this, BoxesRunTime.unboxToInt(pid)));
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$shipVertexAttributes$1(final ShippableVertexPartition $this, final boolean shipSrc$1, final boolean shipDst$1, final int pid) {
      int initialSize = shipSrc$1 && shipDst$1 ? $this.routingTable().partitionSize(pid) : 64;
      PrimitiveVector vids = new PrimitiveVector.mcJ.sp(initialSize, scala.reflect.ClassTag..MODULE$.apply(Long.TYPE));
      PrimitiveVector attrs = new PrimitiveVector(initialSize, $this.evidence$7);
      $this.routingTable().foreachWithinEdgePartition(pid, shipSrc$1, shipDst$1, (JFunction1.mcVJ.sp)(vid) -> {
         if ($this.isDefined(vid)) {
            vids.$plus$eq$mcJ$sp(vid);
            attrs.$plus$eq($this.apply(vid));
         }
      });
      return new Tuple2(BoxesRunTime.boxToInteger(pid), new VertexAttributeBlock(vids.trim$mcJ$sp().array$mcJ$sp(), attrs.trim().array(), $this.evidence$7));
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$shipVertexIds$1(final ShippableVertexPartition $this, final int pid) {
      PrimitiveVector vids = new PrimitiveVector.mcJ.sp($this.routingTable().partitionSize(pid), scala.reflect.ClassTag..MODULE$.apply(Long.TYPE));
      $this.routingTable().foreachWithinEdgePartition(pid, true, true, (JFunction1.mcVJ.sp)(vid) -> {
         if ($this.isDefined(vid)) {
            vids.$plus$eq$mcJ$sp(vid);
         }
      });
      return new Tuple2(BoxesRunTime.boxToInteger(pid), vids.trim$mcJ$sp().array$mcJ$sp());
   }

   public ShippableVertexPartition(final OpenHashSet index, final Object values, final BitSet mask, final RoutingTablePartition routingTable, final ClassTag evidence$7) {
      super(evidence$7);
      this.index = index;
      this.values = values;
      this.mask = mask;
      this.routingTable = routingTable;
      this.evidence$7 = evidence$7;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class ShippableVertexPartitionOpsConstructor$ implements VertexPartitionBaseOpsConstructor {
      public static final ShippableVertexPartitionOpsConstructor$ MODULE$ = new ShippableVertexPartitionOpsConstructor$();

      public VertexPartitionBaseOps toOps(final ShippableVertexPartition partition, final ClassTag evidence$6) {
         return ShippableVertexPartition$.MODULE$.shippablePartitionToOps(partition, evidence$6);
      }
   }
}
