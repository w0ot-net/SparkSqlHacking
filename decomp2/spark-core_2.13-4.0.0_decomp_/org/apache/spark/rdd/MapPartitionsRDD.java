package org.apache.spark.rdd;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.Dependency;
import org.apache.spark.Partition;
import org.apache.spark.TaskContext;
import scala.Enumeration;
import scala.Function3;
import scala.Option;
import scala.None.;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ue!B\r\u001b\u0001q\u0011\u0003\u0002C\u001c\u0001\u0005\u0003\u0007I\u0011\u0001\u001d\t\u0011u\u0002!\u00111A\u0005\u0002yB\u0001\u0002\u0012\u0001\u0003\u0002\u0003\u0006K!\u000f\u0005\t\u000b\u0002\u0011\t\u0011)A\u0005\r\"AQ\f\u0001B\u0001B\u0003%a\f\u0003\u0005b\u0001\t\u0005\t\u0015!\u0003_\u0011!\u0011\u0007A!A!\u0002\u0013q\u0006\u0002C2\u0001\u0005\u0007\u0005\u000b1\u00023\t\u0011)\u0004!1!Q\u0001\f-DQ\u0001\u001c\u0001\u0005\u00025Dqa\u001e\u0001C\u0002\u0013\u0005\u0003\u0010\u0003\u0004\u0000\u0001\u0001\u0006I!\u001f\u0005\b\u0003\u0003\u0001A\u0011IA\u0002\u0011\u001d\t\t\u0002\u0001C!\u0003'Aq!!\b\u0001\t\u0003\ny\u0002\u0003\u0006\u0002\"\u0001A)\u0019!C)\u0003GAq!!\f\u0001\t#\nyc\u0002\u0006\u0002Bi\t\t\u0011#\u0001\u001d\u0003\u00072\u0011\"\u0007\u000e\u0002\u0002#\u0005A$!\u0012\t\r1\u001cB\u0011AA/\u0011%\tyfEI\u0001\n\u0003\t\t\u0007C\u0005\u0002~M\t\n\u0011\"\u0001\u0002\u0000!I\u0011QQ\n\u0012\u0002\u0013\u0005\u0011q\u0011\u0005\n\u0003\u001b\u001b\u0012\u0011!C\u0005\u0003\u001f\u0013\u0001#T1q!\u0006\u0014H/\u001b;j_:\u001c(\u000b\u0012#\u000b\u0005ma\u0012a\u0001:eI*\u0011QDH\u0001\u0006gB\f'o\u001b\u0006\u0003?\u0001\na!\u00199bG\",'\"A\u0011\u0002\u0007=\u0014x-F\u0002$Um\u001a\"\u0001\u0001\u0013\u0011\u0007\u00152\u0003&D\u0001\u001b\u0013\t9#DA\u0002S\t\u0012\u0003\"!\u000b\u0016\r\u0001\u0011)1\u0006\u0001b\u0001[\t\tQk\u0001\u0001\u0012\u00059\"\u0004CA\u00183\u001b\u0005\u0001$\"A\u0019\u0002\u000bM\u001c\u0017\r\\1\n\u0005M\u0002$a\u0002(pi\"Lgn\u001a\t\u0003_UJ!A\u000e\u0019\u0003\u0007\u0005s\u00170\u0001\u0003qe\u00164X#A\u001d\u0011\u0007\u00152#\b\u0005\u0002*w\u0011)A\b\u0001b\u0001[\t\tA+\u0001\u0005qe\u00164x\fJ3r)\ty$\t\u0005\u00020\u0001&\u0011\u0011\t\r\u0002\u0005+:LG\u000fC\u0004D\u0005\u0005\u0005\t\u0019A\u001d\u0002\u0007a$\u0013'A\u0003qe\u00164\b%A\u0001g!\u0019ys)S'Q9&\u0011\u0001\n\r\u0002\n\rVt7\r^5p]N\u0002\"AS&\u000e\u0003qI!\u0001\u0014\u000f\u0003\u0017Q\u000b7o[\"p]R,\u0007\u0010\u001e\t\u0003_9K!a\u0014\u0019\u0003\u0007%sG\u000fE\u0002R3jr!AU,\u000f\u0005M3V\"\u0001+\u000b\u0005Uc\u0013A\u0002\u001fs_>$h(C\u00012\u0013\tA\u0006'A\u0004qC\u000e\\\u0017mZ3\n\u0005i[&\u0001C%uKJ\fGo\u001c:\u000b\u0005a\u0003\u0004cA)ZQ\u0005)\u0002O]3tKJ4Xm\u001d)beRLG/[8oS:<\u0007CA\u0018`\u0013\t\u0001\u0007GA\u0004C_>dW-\u00198\u0002\u001b%\u001chI]8n\u0005\u0006\u0014(/[3s\u0003AI7o\u0014:eKJ\u001cVM\\:ji&4X-\u0001\u0006fm&$WM\\2fIE\u00022!\u001a5)\u001b\u00051'BA41\u0003\u001d\u0011XM\u001a7fGRL!!\u001b4\u0003\u0011\rc\u0017m]:UC\u001e\f!\"\u001a<jI\u0016t7-\u001a\u00133!\r)\u0007NO\u0001\u0007y%t\u0017\u000e\u001e \u0015\r9\u00148\u000f^;w)\ry\u0007/\u001d\t\u0005K\u0001A#\bC\u0003d\u0015\u0001\u000fA\rC\u0003k\u0015\u0001\u000f1\u000eC\u00038\u0015\u0001\u0007\u0011\bC\u0003F\u0015\u0001\u0007a\tC\u0004^\u0015A\u0005\t\u0019\u00010\t\u000f\u0005T\u0001\u0013!a\u0001=\"9!M\u0003I\u0001\u0002\u0004q\u0016a\u00039beRLG/[8oKJ,\u0012!\u001f\t\u0004_id\u0018BA>1\u0005\u0019y\u0005\u000f^5p]B\u0011!*`\u0005\u0003}r\u00111\u0002U1si&$\u0018n\u001c8fe\u0006a\u0001/\u0019:uSRLwN\\3sA\u0005iq-\u001a;QCJ$\u0018\u000e^5p]N,\"!!\u0002\u0011\u000b=\n9!a\u0003\n\u0007\u0005%\u0001GA\u0003BeJ\f\u0017\u0010E\u0002K\u0003\u001bI1!a\u0004\u001d\u0005%\u0001\u0016M\u001d;ji&|g.A\u0004d_6\u0004X\u000f^3\u0015\u000bq\u000b)\"!\u0007\t\u000f\u0005]a\u00021\u0001\u0002\f\u0005)1\u000f\u001d7ji\"1\u00111\u0004\bA\u0002%\u000bqaY8oi\u0016DH/A\tdY\u0016\f'\u000fR3qK:$WM\\2jKN$\u0012aP\u0001\u000bSN\u0014\u0015M\u001d:jKJ|V#\u00010)\u0007A\t9\u0003E\u00020\u0003SI1!a\u000b1\u0005%!(/\u00198tS\u0016tG/A\u000ehKR|U\u000f\u001e9vi\u0012+G/\u001a:nS:L7\u000f^5d\u0019\u00164X\r\\\u000b\u0003\u0003c\u0001B!a\r\u0002:9\u0019Q%!\u000e\n\u0007\u0005]\"$\u0001\nEKR,'/\\5oSN$\u0018n\u0019'fm\u0016d\u0017\u0002BA\u001e\u0003{\u0011QAV1mk\u0016L1!a\u00101\u0005-)e.^7fe\u0006$\u0018n\u001c8\u0002!5\u000b\u0007\u000fU1si&$\u0018n\u001c8t%\u0012#\u0005CA\u0013\u0014'\u0015\u0019\u0012qIA'!\ry\u0013\u0011J\u0005\u0004\u0003\u0017\u0002$AB!osJ+g\r\u0005\u0003\u0002P\u0005eSBAA)\u0015\u0011\t\u0019&!\u0016\u0002\u0005%|'BAA,\u0003\u0011Q\u0017M^1\n\t\u0005m\u0013\u0011\u000b\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u000b\u0003\u0003\u0007\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u001aTCBA2\u0003s\nY(\u0006\u0002\u0002f)\u001aa,a\u001a,\u0005\u0005%\u0004\u0003BA6\u0003kj!!!\u001c\u000b\t\u0005=\u0014\u0011O\u0001\nk:\u001c\u0007.Z2lK\u0012T1!a\u001d1\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003o\niGA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016$QaK\u000bC\u00025\"Q\u0001P\u000bC\u00025\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\"TCBA2\u0003\u0003\u000b\u0019\tB\u0003,-\t\u0007Q\u0006B\u0003=-\t\u0007Q&A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H%N\u000b\u0007\u0003G\nI)a#\u0005\u000b-:\"\u0019A\u0017\u0005\u000bq:\"\u0019A\u0017\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005E\u0005\u0003BAJ\u00033k!!!&\u000b\t\u0005]\u0015QK\u0001\u0005Y\u0006tw-\u0003\u0003\u0002\u001c\u0006U%AB(cU\u0016\u001cG\u000f"
)
public class MapPartitionsRDD extends RDD {
   private transient boolean isBarrier_;
   private RDD prev;
   private final Function3 f;
   private final boolean isFromBarrier;
   private final boolean isOrderSensitive;
   private final ClassTag evidence$2;
   private final Option partitioner;
   private transient volatile boolean bitmap$trans$0;

   public static boolean $lessinit$greater$default$5() {
      return MapPartitionsRDD$.MODULE$.$lessinit$greater$default$5();
   }

   public static boolean $lessinit$greater$default$4() {
      return MapPartitionsRDD$.MODULE$.$lessinit$greater$default$4();
   }

   public static boolean $lessinit$greater$default$3() {
      return MapPartitionsRDD$.MODULE$.$lessinit$greater$default$3();
   }

   public RDD prev() {
      return this.prev;
   }

   public void prev_$eq(final RDD x$1) {
      this.prev = x$1;
   }

   public Option partitioner() {
      return this.partitioner;
   }

   public Partition[] getPartitions() {
      return this.firstParent(this.evidence$2).partitions();
   }

   public Iterator compute(final Partition split, final TaskContext context) {
      return (Iterator)this.f.apply(context, BoxesRunTime.boxToInteger(split.index()), this.firstParent(this.evidence$2).iterator(split, context));
   }

   public void clearDependencies() {
      super.clearDependencies();
      this.prev_$eq((RDD)null);
   }

   private boolean isBarrier_$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$trans$0) {
            this.isBarrier_ = this.isFromBarrier || this.dependencies().exists((x$1) -> BoxesRunTime.boxToBoolean($anonfun$isBarrier_$1(x$1)));
            this.bitmap$trans$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.isBarrier_;
   }

   public boolean isBarrier_() {
      return !this.bitmap$trans$0 ? this.isBarrier_$lzycompute() : this.isBarrier_;
   }

   public Enumeration.Value getOutputDeterministicLevel() {
      if (this.isOrderSensitive) {
         Enumeration.Value var10000 = this.prev().outputDeterministicLevel();
         Enumeration.Value var1 = DeterministicLevel$.MODULE$.UNORDERED();
         if (var10000 == null) {
            if (var1 == null) {
               return DeterministicLevel$.MODULE$.INDETERMINATE();
            }
         } else if (var10000.equals(var1)) {
            return DeterministicLevel$.MODULE$.INDETERMINATE();
         }
      }

      return super.getOutputDeterministicLevel();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isBarrier_$1(final Dependency x$1) {
      return x$1.rdd().isBarrier();
   }

   public MapPartitionsRDD(final RDD prev, final Function3 f, final boolean preservesPartitioning, final boolean isFromBarrier, final boolean isOrderSensitive, final ClassTag evidence$1, final ClassTag evidence$2) {
      this.prev = prev;
      this.f = f;
      this.isFromBarrier = isFromBarrier;
      this.isOrderSensitive = isOrderSensitive;
      this.evidence$2 = evidence$2;
      super(prev, evidence$1);
      this.partitioner = (Option)(preservesPartitioning ? this.firstParent(evidence$2).partitioner() : .MODULE$);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
