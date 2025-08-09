package org.apache.spark.graphx.impl;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.HashPartitioner;
import org.apache.spark.OneToOneDependency;
import org.apache.spark.graphx.Edge;
import org.apache.spark.graphx.EdgeRDD;
import org.apache.spark.rdd.RDD;
import org.apache.spark.storage.StorageLevel;
import scala.Function1;
import scala.Function2;
import scala.Function4;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\tee\u0001B\u0011#\u00015B\u0001\"\u0011\u0001\u0003\u0006\u0004%\tE\u0011\u0005\tK\u0002\u0011\t\u0011)A\u0005\u0007\"A!\u000e\u0001BC\u0002\u0013\u00051\u000e\u0003\u0005s\u0001\t\u0005\t\u0015!\u0003m\u0011!\u0019\bAaA!\u0002\u0017!\b\u0002\u0003>\u0001\u0005\u0007\u0005\u000b1B>\t\rq\u0004A\u0011\u0001\u0013~\u0011\u001d\tI\u0001\u0001C!\u0003\u0017A\u0011\"a\t\u0001\u0005\u0004%\t%!\n\t\u0011\u0005U\u0002\u0001)A\u0005\u0003OAq!a\u000e\u0001\t\u0003\nI\u0004C\u0004\u0002H\u0001!\t%!\u0013\t\u000f\u0005=\u0003\u0001\"\u0011\u0002R!I\u0011Q\f\u0001\u0012\u0002\u0013\u0005\u0011q\f\u0005\b\u0003k\u0002A\u0011IA<\u0011\u0019\tI\b\u0001C!W\"9\u00111\u0010\u0001\u0005B\u0005u\u0004bBAC\u0001\u0011\u0005\u0013q\u0011\u0005\b\u0003\u0013\u0003A\u0011IAF\u0011\u001d\ty\t\u0001C!\u0003#Cq!!'\u0001\t\u0003\nY\nC\u0004\u0002:\u0002!\t%a/\t\u000f\u0005u\u0006\u0001\"\u0001\u0002@\"9\u0011Q\u001c\u0001\u0005B\u0005}\u0007b\u0002B\u0007\u0001\u0011\u0005!q\u0002\u0005\t\u0005g\u0001A\u0011\u0001\u0013\u00036!A!\u0011\f\u0001\u0005B\u0011\u0012YfB\u0005\u0003`\t\n\t\u0011#\u0001\u0003b\u0019A\u0011EIA\u0001\u0012\u0003\u0011\u0019\u0007\u0003\u0004};\u0011\u0005!1\u0010\u0005\u000b\u0005{j\u0012\u0013!C\u0001I\t}\u0004\"\u0003BE;\u0005\u0005I\u0011\u0002BF\u0005-)EmZ3S\t\u0012KU\u000e\u001d7\u000b\u0005\r\"\u0013\u0001B5na2T!!\n\u0014\u0002\r\u001d\u0014\u0018\r\u001d5y\u0015\t9\u0003&A\u0003ta\u0006\u00148N\u0003\u0002*U\u00051\u0011\r]1dQ\u0016T\u0011aK\u0001\u0004_J<7\u0001A\u000b\u0004]U\u001a7C\u0001\u00010!\r\u0001\u0014gM\u0007\u0002I%\u0011!\u0007\n\u0002\b\u000b\u0012<WM\u0015#E!\t!T\u0007\u0004\u0001\u0005\u000bY\u0002!\u0019A\u001c\u0003\u0005\u0015#\u0015C\u0001\u001d?!\tID(D\u0001;\u0015\u0005Y\u0014!B:dC2\f\u0017BA\u001f;\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!O \n\u0005\u0001S$aA!os\u0006i\u0001/\u0019:uSRLwN\\:S\t\u0012+\u0012a\u0011\t\u0004\t\u001eKU\"A#\u000b\u0005\u00193\u0013a\u0001:eI&\u0011\u0001*\u0012\u0002\u0004%\u0012#\u0005\u0003B\u001dK\u0019zK!a\u0013\u001e\u0003\rQ+\b\u000f\\33!\ti5L\u0004\u0002O3:\u0011q\n\u0017\b\u0003!^s!!\u0015,\u000f\u0005I+V\"A*\u000b\u0005Qc\u0013A\u0002\u001fs_>$h(C\u0001,\u0013\tI#&\u0003\u0002(Q%\u0011QEJ\u0005\u00035\u0012\nq\u0001]1dW\u0006<W-\u0003\u0002];\nY\u0001+\u0019:uSRLwN\\%E\u0015\tQF\u0005\u0005\u0003`AN\u0012W\"\u0001\u0012\n\u0005\u0005\u0014#!D#eO\u0016\u0004\u0016M\u001d;ji&|g\u000e\u0005\u00025G\u0012)A\r\u0001b\u0001o\t\u0011a\u000bR\u0001\u000fa\u0006\u0014H/\u001b;j_:\u001c(\u000b\u0012#!Q\t\u0011q\r\u0005\u0002:Q&\u0011\u0011N\u000f\u0002\niJ\fgn]5f]R\f!\u0003^1sO\u0016$8\u000b^8sC\u001e,G*\u001a<fYV\tA\u000e\u0005\u0002na6\taN\u0003\u0002pM\u000591\u000f^8sC\u001e,\u0017BA9o\u00051\u0019Fo\u001c:bO\u0016dUM^3m\u0003M!\u0018M]4fiN#xN]1hK2+g/\u001a7!\u0003))g/\u001b3f]\u000e,G%\r\t\u0004kb\u001cT\"\u0001<\u000b\u0005]T\u0014a\u0002:fM2,7\r^\u0005\u0003sZ\u0014\u0001b\u00117bgN$\u0016mZ\u0001\u000bKZLG-\u001a8dK\u0012\u0012\u0004cA;yE\u00061A(\u001b8jiz\"RA`A\u0003\u0003\u000f!Ra`A\u0001\u0003\u0007\u0001Ba\u0018\u00014E\")1o\u0002a\u0002i\")!p\u0002a\u0002w\")\u0011i\u0002a\u0001\u0007\"9!n\u0002I\u0001\u0002\u0004a\u0017aB:fi:\u000bW.\u001a\u000b\u0005\u0003\u001b\ty!D\u0001\u0001\u0011\u001d\t\t\u0002\u0003a\u0001\u0003'\tQa\u00188b[\u0016\u0004B!!\u0006\u0002\u001e9!\u0011qCA\r!\t\u0011&(C\u0002\u0002\u001ci\na\u0001\u0015:fI\u00164\u0017\u0002BA\u0010\u0003C\u0011aa\u0015;sS:<'bAA\u000eu\u0005Y\u0001/\u0019:uSRLwN\\3s+\t\t9\u0003E\u0003:\u0003S\ti#C\u0002\u0002,i\u0012aa\u00149uS>t\u0007\u0003BA\u0018\u0003ci\u0011AJ\u0005\u0004\u0003g1#a\u0003)beRLG/[8oKJ\fA\u0002]1si&$\u0018n\u001c8fe\u0002\nqaY8mY\u0016\u001cG\u000f\u0006\u0002\u0002<A)\u0011(!\u0010\u0002B%\u0019\u0011q\b\u001e\u0003\u000b\u0005\u0013(/Y=\u0011\tA\n\u0019eM\u0005\u0004\u0003\u000b\"#\u0001B#eO\u0016\fq\u0001]3sg&\u001cH\u000f\u0006\u0003\u0002\u000e\u0005-\u0003BBA'\u0019\u0001\u0007A.\u0001\u0005oK^dUM^3m\u0003%)h\u000e]3sg&\u001cH\u000f\u0006\u0003\u0002\u000e\u0005M\u0003\"CA+\u001bA\u0005\t\u0019AA,\u0003!\u0011Gn\\2lS:<\u0007cA\u001d\u0002Z%\u0019\u00111\f\u001e\u0003\u000f\t{w\u000e\\3b]\u0006\u0019RO\u001c9feNL7\u000f\u001e\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u0011\u0011\r\u0016\u0005\u0003/\n\u0019g\u000b\u0002\u0002fA!\u0011qMA9\u001b\t\tIG\u0003\u0003\u0002l\u00055\u0014!C;oG\",7m[3e\u0015\r\tyGO\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA:\u0003S\u0012\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0003\u0015\u0019\u0017m\u00195f)\t\ti!A\bhKR\u001cFo\u001c:bO\u0016dUM^3m\u0003)\u0019\u0007.Z2la>Lg\u000e\u001e\u000b\u0003\u0003\u007f\u00022!OAA\u0013\r\t\u0019I\u000f\u0002\u0005+:LG/\u0001\bjg\u000eCWmY6q_&tG/\u001a3\u0016\u0005\u0005]\u0013!E4fi\u000eCWmY6q_&tGOR5mKV\u0011\u0011Q\u0012\t\u0006s\u0005%\u00121C\u0001\u0006G>,h\u000e\u001e\u000b\u0003\u0003'\u00032!OAK\u0013\r\t9J\u000f\u0002\u0005\u0019>tw-A\u0005nCB4\u0016\r\\;fgV!\u0011QTAS)\u0011\ty*a,\u0015\t\u0005\u0005\u0016\u0011\u0016\t\u0006?\u0002\t\u0019K\u0019\t\u0004i\u0005\u0015FABAT+\t\u0007qGA\u0002F\tJB\u0011\"a+\u0016\u0003\u0003\u0005\u001d!!,\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$3\u0007\u0005\u0003vq\u0006\r\u0006bBAY+\u0001\u0007\u00111W\u0001\u0002MB9\u0011(!.\u0002B\u0005\r\u0016bAA\\u\tIa)\u001e8di&|g.M\u0001\be\u00164XM]:f+\u0005y\u0018A\u00024jYR,'\u000fF\u0003\u0000\u0003\u0003\fi\rC\u0004\u0002D^\u0001\r!!2\u0002\u000b\u0015\u0004(/\u001a3\u0011\u000fe\n),a2\u0002XA)\u0001'!3cg%\u0019\u00111\u001a\u0013\u0003\u0017\u0015#w-\u001a+sSBdW\r\u001e\u0005\b\u0003\u001f<\u0002\u0019AAi\u0003\u00151\bO]3e!!I\u00141[AlE\u0006]\u0013bAAku\tIa)\u001e8di&|gN\r\t\u0004\u001b\u0006e\u0017bAAn;\nAa+\u001a:uKbLE-A\u0005j]:,'OS8j]V1\u0011\u0011]A|\u0003W$B!a9\u0003\bQ!\u0011Q]A\u0000)\u0019\t9/a<\u0002zB)q\fAAuEB\u0019A'a;\u0005\r\u00055\bD1\u00018\u0005\r)Ei\r\u0005\n\u0003cD\u0012\u0011!a\u0002\u0003g\f!\"\u001a<jI\u0016t7-\u001a\u00135!\u0011)\b0!>\u0011\u0007Q\n9\u0010\u0002\u0004\u0002(b\u0011\ra\u000e\u0005\n\u0003wD\u0012\u0011!a\u0002\u0003{\f!\"\u001a<jI\u0016t7-\u001a\u00136!\u0011)\b0!;\t\u000f\u0005E\u0006\u00041\u0001\u0003\u0002Aa\u0011Ha\u0001\u0002X\u0006]7'!>\u0002j&\u0019!Q\u0001\u001e\u0003\u0013\u0019+hn\u0019;j_:$\u0004b\u0002B\u00051\u0001\u0007!1B\u0001\u0006_RDWM\u001d\t\u0005aE\n)0A\tnCB,EmZ3QCJ$\u0018\u000e^5p]N,bA!\u0005\u0003\u001a\tuA\u0003\u0002B\n\u0005[!bA!\u0006\u0003\"\t\u001d\u0002CB0\u0001\u0005/\u0011Y\u0002E\u00025\u00053!a!a*\u001a\u0005\u00049\u0004c\u0001\u001b\u0003\u001e\u00111!qD\rC\u0002]\u00121A\u0016#3\u0011%\u0011\u0019#GA\u0001\u0002\b\u0011)#\u0001\u0006fm&$WM\\2fIY\u0002B!\u001e=\u0003\u0018!I!\u0011F\r\u0002\u0002\u0003\u000f!1F\u0001\u000bKZLG-\u001a8dK\u0012:\u0004\u0003B;y\u00057Aq!!-\u001a\u0001\u0004\u0011y\u0003E\u0004:\u0003'deL!\r\u0011\r}\u0003'q\u0003B\u000e\u0003E9\u0018\u000e\u001e5QCJ$\u0018\u000e^5p]N\u0014F\tR\u000b\u0007\u0005o\u0011yDa\u0011\u0015\t\te\"\u0011\u000b\u000b\u0007\u0005w\u0011)Ea\u0013\u0011\r}\u0003!Q\bB!!\r!$q\b\u0003\u0007\u0003OS\"\u0019A\u001c\u0011\u0007Q\u0012\u0019\u0005\u0002\u0004\u0003 i\u0011\ra\u000e\u0005\n\u0005\u000fR\u0012\u0011!a\u0002\u0005\u0013\n!\"\u001a<jI\u0016t7-\u001a\u00139!\u0011)\bP!\u0010\t\u0013\t5#$!AA\u0004\t=\u0013AC3wS\u0012,gnY3%sA!Q\u000f\u001fB!\u0011\u0019\t%\u00041\u0001\u0003TA!Ai\u0012B+!\u0015I$\n\u0014B,!\u0019y\u0006M!\u0010\u0003B\u00051r/\u001b;i)\u0006\u0014x-\u001a;Ti>\u0014\u0018mZ3MKZ,G\u000eF\u0002\u0000\u0005;BQA[\u000eA\u00021\f1\"\u00123hKJ#E)S7qYB\u0011q,H\n\u0006;\t\u0015$1\u000e\t\u0004s\t\u001d\u0014b\u0001B5u\t1\u0011I\\=SK\u001a\u0004BA!\u001c\u0003x5\u0011!q\u000e\u0006\u0005\u0005c\u0012\u0019(\u0001\u0002j_*\u0011!QO\u0001\u0005U\u00064\u0018-\u0003\u0003\u0003z\t=$\u0001D*fe&\fG.\u001b>bE2,GC\u0001B1\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%eU1!\u0011\u0011BC\u0005\u000f+\"Aa!+\u00071\f\u0019\u0007B\u00037?\t\u0007q\u0007B\u0003e?\t\u0007q'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0003\u000eB!!q\u0012BK\u001b\t\u0011\tJ\u0003\u0003\u0003\u0014\nM\u0014\u0001\u00027b]\u001eLAAa&\u0003\u0012\n1qJ\u00196fGR\u0004"
)
public class EdgeRDDImpl extends EdgeRDD {
   private final transient RDD partitionsRDD;
   private final StorageLevel targetStorageLevel;
   private final ClassTag evidence$1;
   private final ClassTag evidence$2;
   private final Option partitioner;

   public RDD partitionsRDD() {
      return this.partitionsRDD;
   }

   public StorageLevel targetStorageLevel() {
      return this.targetStorageLevel;
   }

   public EdgeRDDImpl setName(final String _name) {
      if (this.partitionsRDD().name() != null) {
         RDD var10000 = this.partitionsRDD();
         String var10001 = this.partitionsRDD().name();
         var10000.setName(var10001 + ", " + _name);
      } else {
         this.partitionsRDD().setName(_name);
      }

      return this;
   }

   public Option partitioner() {
      return this.partitioner;
   }

   public Edge[] collect() {
      return (Edge[])this.map((x$1) -> x$1.copy(x$1.copy$default$1(), x$1.copy$default$2(), x$1.copy$default$3()), .MODULE$.apply(Edge.class)).collect();
   }

   public EdgeRDDImpl persist(final StorageLevel newLevel) {
      this.partitionsRDD().persist(newLevel);
      return this;
   }

   public EdgeRDDImpl unpersist(final boolean blocking) {
      this.partitionsRDD().unpersist(blocking);
      return this;
   }

   public boolean unpersist$default$1() {
      return false;
   }

   public EdgeRDDImpl cache() {
      this.partitionsRDD().persist(this.targetStorageLevel());
      return this;
   }

   public StorageLevel getStorageLevel() {
      return this.partitionsRDD().getStorageLevel();
   }

   public void checkpoint() {
      this.partitionsRDD().checkpoint();
   }

   public boolean isCheckpointed() {
      return this.firstParent(.MODULE$.apply(Tuple2.class)).isCheckpointed();
   }

   public Option getCheckpointFile() {
      return this.partitionsRDD().getCheckpointFile();
   }

   public long count() {
      return BoxesRunTime.unboxToLong(this.partitionsRDD().map((x$2) -> BoxesRunTime.boxToLong($anonfun$count$1(x$2)), .MODULE$.Long()).fold(BoxesRunTime.boxToLong(0L), (JFunction2.mcJJJ.sp)(x$3, x$4) -> x$3 + x$4));
   }

   public EdgeRDDImpl mapValues(final Function1 f, final ClassTag evidence$3) {
      return this.mapEdgePartitions((pid, part) -> $anonfun$mapValues$1(f, evidence$3, BoxesRunTime.unboxToInt(pid), part), evidence$3, this.evidence$2);
   }

   public EdgeRDDImpl reverse() {
      return this.mapEdgePartitions((pid, part) -> $anonfun$reverse$1(BoxesRunTime.unboxToInt(pid), part), this.evidence$1, this.evidence$2);
   }

   public EdgeRDDImpl filter(final Function1 epred, final Function2 vpred) {
      return this.mapEdgePartitions((pid, part) -> $anonfun$filter$1(epred, vpred, BoxesRunTime.unboxToInt(pid), part), this.evidence$1, this.evidence$2);
   }

   public EdgeRDDImpl innerJoin(final EdgeRDD other, final Function4 f, final ClassTag evidence$4, final ClassTag evidence$5) {
      ClassTag ed2Tag = scala.reflect.package..MODULE$.classTag(evidence$4);
      ClassTag ed3Tag = scala.reflect.package..MODULE$.classTag(evidence$5);
      return this.withPartitionsRDD(this.partitionsRDD().zipPartitions(other.partitionsRDD(), true, (thisIter, otherIter) -> {
         Tuple2 var8 = (Tuple2)thisIter.next();
         if (var8 != null) {
            int pid = var8._1$mcI$sp();
            EdgePartition thisEPart = (EdgePartition)var8._2();
            Tuple2 var7 = new Tuple2(BoxesRunTime.boxToInteger(pid), thisEPart);
            int pid = var7._1$mcI$sp();
            EdgePartition thisEPart = (EdgePartition)var7._2();
            Tuple2 var14 = (Tuple2)otherIter.next();
            if (var14 != null) {
               EdgePartition otherEPart = (EdgePartition)var14._2();
               return scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2(BoxesRunTime.boxToInteger(pid), thisEPart.innerJoin(otherEPart, f, ed2Tag, ed3Tag))})));
            } else {
               throw new MatchError(var14);
            }
         } else {
            throw new MatchError(var8);
         }
      }, .MODULE$.apply(Tuple2.class), .MODULE$.apply(Tuple2.class)), evidence$5, this.evidence$2);
   }

   public EdgeRDDImpl mapEdgePartitions(final Function2 f, final ClassTag evidence$6, final ClassTag evidence$7) {
      return this.withPartitionsRDD(this.partitionsRDD().mapPartitions((iter) -> {
         if (iter.hasNext()) {
            Tuple2 var4 = (Tuple2)iter.next();
            if (var4 != null) {
               int pid = var4._1$mcI$sp();
               EdgePartition ep = (EdgePartition)var4._2();
               Tuple2 var3 = new Tuple2(BoxesRunTime.boxToInteger(pid), ep);
               int pid = var3._1$mcI$sp();
               EdgePartition epx = (EdgePartition)var3._2();
               return scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2(BoxesRunTime.boxToInteger(pid), f.apply(BoxesRunTime.boxToInteger(pid), epx))})));
            } else {
               throw new MatchError(var4);
            }
         } else {
            return scala.package..MODULE$.Iterator().empty();
         }
      }, true, .MODULE$.apply(Tuple2.class)), evidence$6, evidence$7);
   }

   public EdgeRDDImpl withPartitionsRDD(final RDD partitionsRDD, final ClassTag evidence$8, final ClassTag evidence$9) {
      return new EdgeRDDImpl(partitionsRDD, this.targetStorageLevel(), evidence$8, evidence$9);
   }

   public EdgeRDDImpl withTargetStorageLevel(final StorageLevel targetStorageLevel) {
      return new EdgeRDDImpl(this.partitionsRDD(), targetStorageLevel, this.evidence$1, this.evidence$2);
   }

   // $FF: synthetic method
   public static final long $anonfun$count$1(final Tuple2 x$2) {
      return (long)((EdgePartition)x$2._2()).size();
   }

   // $FF: synthetic method
   public static final EdgePartition $anonfun$mapValues$1(final Function1 f$1, final ClassTag evidence$3$1, final int pid, final EdgePartition part) {
      return part.map(f$1, evidence$3$1);
   }

   // $FF: synthetic method
   public static final EdgePartition $anonfun$reverse$1(final int pid, final EdgePartition part) {
      return part.reverse();
   }

   // $FF: synthetic method
   public static final EdgePartition $anonfun$filter$1(final Function1 epred$1, final Function2 vpred$1, final int pid, final EdgePartition part) {
      return part.filter(epred$1, vpred$1);
   }

   public EdgeRDDImpl(final RDD partitionsRDD, final StorageLevel targetStorageLevel, final ClassTag evidence$1, final ClassTag evidence$2) {
      super(partitionsRDD.context(), new scala.collection.immutable..colon.colon(new OneToOneDependency(partitionsRDD), scala.collection.immutable.Nil..MODULE$));
      this.partitionsRDD = partitionsRDD;
      this.targetStorageLevel = targetStorageLevel;
      this.evidence$1 = evidence$1;
      this.evidence$2 = evidence$2;
      this.setName("EdgeRDD");
      this.partitioner = partitionsRDD.partitioner().orElse(() -> new Some(new HashPartitioner(this.partitions().length)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
