package org.apache.spark.storage;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.metrics.source.Source;
import scala.Function1;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u00014Q!\u0003\u0006\u0001\u0019IA\u0001\"\t\u0001\u0003\u0006\u0004%\ta\t\u0005\tQ\u0001\u0011\t\u0011)A\u0005I!)\u0011\u0006\u0001C\u0001U!9Q\u0006\u0001b\u0001\n\u0003r\u0003B\u0002\u001d\u0001A\u0003%q\u0006C\u0004:\u0001\t\u0007I\u0011\t\u001e\t\r\r\u0003\u0001\u0015!\u0003<\u0011\u0015!\u0005\u0001\"\u0003F\u0005I\u0011En\\2l\u001b\u0006t\u0017mZ3s'>,(oY3\u000b\u0005-a\u0011aB:u_J\fw-\u001a\u0006\u0003\u001b9\tQa\u001d9be.T!a\u0004\t\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\t\u0012aA8sON\u0019\u0001aE\r\u0011\u0005Q9R\"A\u000b\u000b\u0003Y\tQa]2bY\u0006L!\u0001G\u000b\u0003\r\u0005s\u0017PU3g!\tQr$D\u0001\u001c\u0015\taR$\u0001\u0004t_V\u00148-\u001a\u0006\u0003=1\tq!\\3ue&\u001c7/\u0003\u0002!7\t11k\\;sG\u0016\fAB\u00197pG.l\u0015M\\1hKJ\u001c\u0001!F\u0001%!\t)c%D\u0001\u000b\u0013\t9#B\u0001\u0007CY>\u001c7.T1oC\u001e,'/A\u0007cY>\u001c7.T1oC\u001e,'\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005-b\u0003CA\u0013\u0001\u0011\u0015\t3\u00011\u0001%\u00039iW\r\u001e:jGJ+w-[:uef,\u0012a\f\t\u0003aYj\u0011!\r\u0006\u0003=IR!a\r\u001b\u0002\u0011\r|G-\u00195bY\u0016T\u0011!N\u0001\u0004G>l\u0017BA\u001c2\u00059iU\r\u001e:jGJ+w-[:uef\fq\"\\3ue&\u001c'+Z4jgR\u0014\u0018\u0010I\u0001\u000bg>,(oY3OC6,W#A\u001e\u0011\u0005q\nU\"A\u001f\u000b\u0005yz\u0014\u0001\u00027b]\u001eT\u0011\u0001Q\u0001\u0005U\u00064\u0018-\u0003\u0002C{\t11\u000b\u001e:j]\u001e\f1b]8ve\u000e,g*Y7fA\u0005i!/Z4jgR,'oR1vO\u0016$2AR%V!\t!r)\u0003\u0002I+\t!QK\\5u\u0011\u0015Q\u0005\u00021\u0001L\u0003\u0011q\u0017-\\3\u0011\u00051\u001bfBA'R!\tqU#D\u0001P\u0015\t\u0001&%\u0001\u0004=e>|GOP\u0005\u0003%V\ta\u0001\u0015:fI\u00164\u0017B\u0001\"U\u0015\t\u0011V\u0003C\u0003W\u0011\u0001\u0007q+\u0001\u0003gk:\u001c\u0007\u0003\u0002\u000bY5vK!!W\u000b\u0003\u0013\u0019+hn\u0019;j_:\f\u0004CA\u0013\\\u0013\ta&B\u0001\nCY>\u001c7.T1oC\u001e,'/T1ti\u0016\u0014\bC\u0001\u000b_\u0013\tyVC\u0001\u0003M_:<\u0007"
)
public class BlockManagerSource implements Source {
   private final BlockManager blockManager;
   private final MetricRegistry metricRegistry;
   private final String sourceName;

   public BlockManager blockManager() {
      return this.blockManager;
   }

   public MetricRegistry metricRegistry() {
      return this.metricRegistry;
   }

   public String sourceName() {
      return this.sourceName;
   }

   private void registerGauge(final String name, final Function1 func) {
      this.metricRegistry().register(name, new Gauge(func) {
         // $FF: synthetic field
         private final BlockManagerSource $outer;
         private final Function1 func$1;

         public long getValue() {
            return BoxesRunTime.unboxToLong(this.func$1.apply(this.$outer.blockManager().master())) / 1024L / 1024L;
         }

         public {
            if (BlockManagerSource.this == null) {
               throw null;
            } else {
               this.$outer = BlockManagerSource.this;
               this.func$1 = func$1;
            }
         }
      });
   }

   // $FF: synthetic method
   public static final long $anonfun$new$2(final StorageStatus x$2) {
      return x$2.maxMem();
   }

   // $FF: synthetic method
   public static final long $anonfun$new$1(final BlockManagerMaster x$1) {
      return BoxesRunTime.unboxToLong(.MODULE$.wrapLongArray((long[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(x$1.getStorageStatus()), (x$2) -> BoxesRunTime.boxToLong($anonfun$new$2(x$2)), scala.reflect.ClassTag..MODULE$.Long())).sum(scala.math.Numeric.LongIsIntegral..MODULE$));
   }

   // $FF: synthetic method
   public static final long $anonfun$new$4(final StorageStatus x$4) {
      return BoxesRunTime.unboxToLong(x$4.maxOnHeapMem().getOrElse((JFunction0.mcJ.sp)() -> 0L));
   }

   // $FF: synthetic method
   public static final long $anonfun$new$3(final BlockManagerMaster x$3) {
      return BoxesRunTime.unboxToLong(.MODULE$.wrapLongArray((long[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(x$3.getStorageStatus()), (x$4) -> BoxesRunTime.boxToLong($anonfun$new$4(x$4)), scala.reflect.ClassTag..MODULE$.Long())).sum(scala.math.Numeric.LongIsIntegral..MODULE$));
   }

   // $FF: synthetic method
   public static final long $anonfun$new$7(final StorageStatus x$6) {
      return BoxesRunTime.unboxToLong(x$6.maxOffHeapMem().getOrElse((JFunction0.mcJ.sp)() -> 0L));
   }

   // $FF: synthetic method
   public static final long $anonfun$new$6(final BlockManagerMaster x$5) {
      return BoxesRunTime.unboxToLong(.MODULE$.wrapLongArray((long[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(x$5.getStorageStatus()), (x$6) -> BoxesRunTime.boxToLong($anonfun$new$7(x$6)), scala.reflect.ClassTag..MODULE$.Long())).sum(scala.math.Numeric.LongIsIntegral..MODULE$));
   }

   // $FF: synthetic method
   public static final long $anonfun$new$10(final StorageStatus x$8) {
      return x$8.memRemaining();
   }

   // $FF: synthetic method
   public static final long $anonfun$new$9(final BlockManagerMaster x$7) {
      return BoxesRunTime.unboxToLong(.MODULE$.wrapLongArray((long[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(x$7.getStorageStatus()), (x$8) -> BoxesRunTime.boxToLong($anonfun$new$10(x$8)), scala.reflect.ClassTag..MODULE$.Long())).sum(scala.math.Numeric.LongIsIntegral..MODULE$));
   }

   // $FF: synthetic method
   public static final long $anonfun$new$12(final StorageStatus x$10) {
      return BoxesRunTime.unboxToLong(x$10.onHeapMemRemaining().getOrElse((JFunction0.mcJ.sp)() -> 0L));
   }

   // $FF: synthetic method
   public static final long $anonfun$new$11(final BlockManagerMaster x$9) {
      return BoxesRunTime.unboxToLong(.MODULE$.wrapLongArray((long[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(x$9.getStorageStatus()), (x$10) -> BoxesRunTime.boxToLong($anonfun$new$12(x$10)), scala.reflect.ClassTag..MODULE$.Long())).sum(scala.math.Numeric.LongIsIntegral..MODULE$));
   }

   // $FF: synthetic method
   public static final long $anonfun$new$15(final StorageStatus x$12) {
      return BoxesRunTime.unboxToLong(x$12.offHeapMemRemaining().getOrElse((JFunction0.mcJ.sp)() -> 0L));
   }

   // $FF: synthetic method
   public static final long $anonfun$new$14(final BlockManagerMaster x$11) {
      return BoxesRunTime.unboxToLong(.MODULE$.wrapLongArray((long[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(x$11.getStorageStatus()), (x$12) -> BoxesRunTime.boxToLong($anonfun$new$15(x$12)), scala.reflect.ClassTag..MODULE$.Long())).sum(scala.math.Numeric.LongIsIntegral..MODULE$));
   }

   // $FF: synthetic method
   public static final long $anonfun$new$18(final StorageStatus x$14) {
      return x$14.memUsed();
   }

   // $FF: synthetic method
   public static final long $anonfun$new$17(final BlockManagerMaster x$13) {
      return BoxesRunTime.unboxToLong(.MODULE$.wrapLongArray((long[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(x$13.getStorageStatus()), (x$14) -> BoxesRunTime.boxToLong($anonfun$new$18(x$14)), scala.reflect.ClassTag..MODULE$.Long())).sum(scala.math.Numeric.LongIsIntegral..MODULE$));
   }

   // $FF: synthetic method
   public static final long $anonfun$new$20(final StorageStatus x$16) {
      return BoxesRunTime.unboxToLong(x$16.onHeapMemUsed().getOrElse((JFunction0.mcJ.sp)() -> 0L));
   }

   // $FF: synthetic method
   public static final long $anonfun$new$19(final BlockManagerMaster x$15) {
      return BoxesRunTime.unboxToLong(.MODULE$.wrapLongArray((long[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(x$15.getStorageStatus()), (x$16) -> BoxesRunTime.boxToLong($anonfun$new$20(x$16)), scala.reflect.ClassTag..MODULE$.Long())).sum(scala.math.Numeric.LongIsIntegral..MODULE$));
   }

   // $FF: synthetic method
   public static final long $anonfun$new$23(final StorageStatus x$18) {
      return BoxesRunTime.unboxToLong(x$18.offHeapMemUsed().getOrElse((JFunction0.mcJ.sp)() -> 0L));
   }

   // $FF: synthetic method
   public static final long $anonfun$new$22(final BlockManagerMaster x$17) {
      return BoxesRunTime.unboxToLong(.MODULE$.wrapLongArray((long[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(x$17.getStorageStatus()), (x$18) -> BoxesRunTime.boxToLong($anonfun$new$23(x$18)), scala.reflect.ClassTag..MODULE$.Long())).sum(scala.math.Numeric.LongIsIntegral..MODULE$));
   }

   // $FF: synthetic method
   public static final long $anonfun$new$26(final StorageStatus x$20) {
      return x$20.diskUsed();
   }

   // $FF: synthetic method
   public static final long $anonfun$new$25(final BlockManagerMaster x$19) {
      return BoxesRunTime.unboxToLong(.MODULE$.wrapLongArray((long[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(x$19.getStorageStatus()), (x$20) -> BoxesRunTime.boxToLong($anonfun$new$26(x$20)), scala.reflect.ClassTag..MODULE$.Long())).sum(scala.math.Numeric.LongIsIntegral..MODULE$));
   }

   public BlockManagerSource(final BlockManager blockManager) {
      this.blockManager = blockManager;
      this.metricRegistry = new MetricRegistry();
      this.sourceName = "BlockManager";
      this.registerGauge(MetricRegistry.name("memory", new String[]{"maxMem_MB"}), (x$1) -> BoxesRunTime.boxToLong($anonfun$new$1(x$1)));
      this.registerGauge(MetricRegistry.name("memory", new String[]{"maxOnHeapMem_MB"}), (x$3) -> BoxesRunTime.boxToLong($anonfun$new$3(x$3)));
      this.registerGauge(MetricRegistry.name("memory", new String[]{"maxOffHeapMem_MB"}), (x$5) -> BoxesRunTime.boxToLong($anonfun$new$6(x$5)));
      this.registerGauge(MetricRegistry.name("memory", new String[]{"remainingMem_MB"}), (x$7) -> BoxesRunTime.boxToLong($anonfun$new$9(x$7)));
      this.registerGauge(MetricRegistry.name("memory", new String[]{"remainingOnHeapMem_MB"}), (x$9) -> BoxesRunTime.boxToLong($anonfun$new$11(x$9)));
      this.registerGauge(MetricRegistry.name("memory", new String[]{"remainingOffHeapMem_MB"}), (x$11) -> BoxesRunTime.boxToLong($anonfun$new$14(x$11)));
      this.registerGauge(MetricRegistry.name("memory", new String[]{"memUsed_MB"}), (x$13) -> BoxesRunTime.boxToLong($anonfun$new$17(x$13)));
      this.registerGauge(MetricRegistry.name("memory", new String[]{"onHeapMemUsed_MB"}), (x$15) -> BoxesRunTime.boxToLong($anonfun$new$19(x$15)));
      this.registerGauge(MetricRegistry.name("memory", new String[]{"offHeapMemUsed_MB"}), (x$17) -> BoxesRunTime.boxToLong($anonfun$new$22(x$17)));
      this.registerGauge(MetricRegistry.name("disk", new String[]{"diskSpaceUsed_MB"}), (x$19) -> BoxesRunTime.boxToLong($anonfun$new$25(x$19)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
