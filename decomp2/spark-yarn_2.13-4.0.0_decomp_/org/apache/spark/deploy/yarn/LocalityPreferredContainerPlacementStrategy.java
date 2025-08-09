package org.apache.spark.deploy.yarn;

import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.yarn.client.api.AMRMClient;
import org.apache.spark.SparkConf;
import org.apache.spark.resource.ResourceProfile;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.MapOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.HashMap;
import scala.math.Numeric.IntIsIntegral.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mc!B\u0006\r\u000111\u0002\u0002C\u000f\u0001\u0005\u000b\u0007I\u0011A\u0010\t\u0011\u0011\u0002!\u0011!Q\u0001\n\u0001B\u0001\"\n\u0001\u0003\u0006\u0004%\tA\n\u0005\t_\u0001\u0011\t\u0011)A\u0005O!A\u0001\u0007\u0001B\u0001B\u0003%\u0011\u0007C\u00036\u0001\u0011\u0005a\u0007C\u0003<\u0001\u0011\u0005A\bC\u0004\u00024\u0001!I!!\u000e\t\u000f\u0005u\u0002\u0001\"\u0003\u0002@!9\u0011Q\n\u0001\u0005\n\u0005=#a\u000b'pG\u0006d\u0017\u000e^=Qe\u00164WM\u001d:fI\u000e{g\u000e^1j]\u0016\u0014\b\u000b\\1dK6,g\u000e^*ue\u0006$XmZ=\u000b\u00055q\u0011\u0001B=be:T!a\u0004\t\u0002\r\u0011,\u0007\u000f\\8z\u0015\t\t\"#A\u0003ta\u0006\u00148N\u0003\u0002\u0014)\u00051\u0011\r]1dQ\u0016T\u0011!F\u0001\u0004_J<7C\u0001\u0001\u0018!\tA2$D\u0001\u001a\u0015\u0005Q\u0012!B:dC2\f\u0017B\u0001\u000f\u001a\u0005\u0019\te.\u001f*fM\u0006I1\u000f]1sW\u000e{gNZ\u0002\u0001+\u0005\u0001\u0003CA\u0011#\u001b\u0005\u0001\u0012BA\u0012\u0011\u0005%\u0019\u0006/\u0019:l\u0007>tg-\u0001\u0006ta\u0006\u00148nQ8oM\u0002\n\u0001\"_1s]\u000e{gNZ\u000b\u0002OA\u0011\u0001&L\u0007\u0002S)\u0011!fK\u0001\u0005G>tgM\u0003\u0002-%\u00051\u0001.\u00193p_BL!AL\u0015\u0003\u001b\r{gNZ5hkJ\fG/[8o\u0003%I\u0018M\u001d8D_:4\u0007%\u0001\u0005sKN|GN^3s!\t\u00114'D\u0001\r\u0013\t!DBA\tTa\u0006\u00148NU1dWJ+7o\u001c7wKJ\fa\u0001P5oSRtD\u0003B\u001c9si\u0002\"A\r\u0001\t\u000bu1\u0001\u0019\u0001\u0011\t\u000b\u00152\u0001\u0019A\u0014\t\u000bA2\u0001\u0019A\u0019\u0002;1|7-\u00197jif|eMU3rk\u0016\u001cH/\u001a3D_:$\u0018-\u001b8feN$\u0002\"P\"I\u0015j\u0003\u00181\u0005\t\u00041y\u0002\u0015BA \u001a\u0005\u0015\t%O]1z!\t\u0011\u0014)\u0003\u0002C\u0019\ta2i\u001c8uC&tWM\u001d'pG\u0006d\u0017\u000e^=Qe\u00164WM]3oG\u0016\u001c\b\"\u0002#\b\u0001\u0004)\u0015\u0001\u00048v[\u000e{g\u000e^1j]\u0016\u0014\bC\u0001\rG\u0013\t9\u0015DA\u0002J]RDQ!S\u0004A\u0002\u0015\u000bQC\\;n\u0019>\u001c\u0017\r\\5us\u0006;\u0018M]3UCN\\7\u000fC\u0003L\u000f\u0001\u0007A*\u0001\u000bi_N$Hk\u001c'pG\u0006dG+Y:l\u0007>,h\u000e\u001e\t\u0005\u001bR;VI\u0004\u0002O%B\u0011q*G\u0007\u0002!*\u0011\u0011KH\u0001\u0007yI|w\u000e\u001e \n\u0005MK\u0012A\u0002)sK\u0012,g-\u0003\u0002V-\n\u0019Q*\u00199\u000b\u0005MK\u0002CA'Y\u0013\tIfK\u0001\u0004TiJLgn\u001a\u0005\u00067\u001e\u0001\r\u0001X\u0001\u001dC2dwnY1uK\u0012Dun\u001d;U_\u000e{g\u000e^1j]\u0016\u00148/T1q!\u0011i&m\u00163\u000e\u0003yS!a\u00181\u0002\u000f5,H/\u00192mK*\u0011\u0011-G\u0001\u000bG>dG.Z2uS>t\u0017BA2_\u0005\u001dA\u0015m\u001d5NCB\u00042!X3h\u0013\t1gLA\u0002TKR\u0004\"\u0001\u001b8\u000e\u0003%T!A[6\u0002\u000fI,7m\u001c:eg*\u0011A.\\\u0001\u0004CBL'BA\u0007,\u0013\ty\u0017NA\u0006D_:$\u0018-\u001b8fe&#\u0007\"B9\b\u0001\u0004\u0011\u0018!\t7pG\u0006d\u0017\u000e^=NCR\u001c\u0007.\u001a3QK:$\u0017N\\4BY2|7-\u0019;j_:\u001c\bcA:yw:\u0011AO\u001e\b\u0003\u001fVL\u0011AG\u0005\u0003of\tq\u0001]1dW\u0006<W-\u0003\u0002zu\n\u00191+Z9\u000b\u0005]L\u0002c\u0001?\u0002\u001e9\u0019Q0a\u0006\u000f\u0007y\f\u0019BD\u0002\u0000\u0003\u001fqA!!\u0001\u0002\u000e9!\u00111AA\u0006\u001d\u0011\t)!!\u0003\u000f\u0007=\u000b9!C\u0001\u0016\u0013\t\u0019B#\u0003\u0002-%%\u0011QbK\u0005\u0004\u0003#i\u0017AB2mS\u0016tG/C\u0002m\u0003+Q1!!\u0005n\u0013\u0011\tI\"a\u0007\u0002\u0015\u0005k%+T\"mS\u0016tGOC\u0002m\u0003+IA!a\b\u0002\"\t\u00012i\u001c8uC&tWM\u001d*fcV,7\u000f\u001e\u0006\u0005\u00033\tY\u0002C\u0004\u0002&\u001d\u0001\r!a\n\u0002\u0005I\u0004\b\u0003BA\u0015\u0003_i!!a\u000b\u000b\u0007\u00055\u0002#\u0001\u0005sKN|WO]2f\u0013\u0011\t\t$a\u000b\u0003\u001fI+7o\\;sG\u0016\u0004&o\u001c4jY\u0016\f1C\\;n\u000bb,7-\u001e;peN\u0004VM\u001c3j]\u001e$R!RA\u001c\u0003wAa!!\u000f\t\u0001\u0004)\u0015a\u00048v[R\u000b7o[:QK:$\u0017N\\4\t\u000f\u0005\u0015\u0002\u00021\u0001\u0002(\u0005aR\r\u001f9fGR,G\rS8tiR{7i\u001c8uC&tWM]\"pk:$Hc\u0003'\u0002B\u0005\u0015\u0013qIA%\u0003\u0017Ba!a\u0011\n\u0001\u0004)\u0015A\u00057pG\u0006d\u0017\u000e^=Bo\u0006\u0014X\rV1tWNDQaS\u0005A\u00021CQaW\u0005A\u0002qCQ!]\u0005A\u0002IDq!!\n\n\u0001\u0004\t9#A\u000eqK:$\u0017N\\4I_N$Hk\\\"p]R\f\u0017N\\3s\u0007>,h\u000e\u001e\u000b\u0005\u0003#\nI\u0006E\u0003N)^\u000b\u0019\u0006E\u0002\u0019\u0003+J1!a\u0016\u001a\u0005\u0019!u.\u001e2mK\")\u0011O\u0003a\u0001e\u0002"
)
public class LocalityPreferredContainerPlacementStrategy {
   private final SparkConf sparkConf;
   private final Configuration yarnConf;
   private final SparkRackResolver resolver;

   public SparkConf sparkConf() {
      return this.sparkConf;
   }

   public Configuration yarnConf() {
      return this.yarnConf;
   }

   public ContainerLocalityPreferences[] localityOfRequestedContainers(final int numContainer, final int numLocalityAwareTasks, final Map hostToLocalTaskCount, final HashMap allocatedHostToContainersMap, final Seq localityMatchedPendingAllocations, final ResourceProfile rp) {
      Map updatedHostToContainerCount = this.expectedHostToContainerCount(numLocalityAwareTasks, hostToLocalTaskCount, allocatedHostToContainersMap, localityMatchedPendingAllocations, rp);
      int updatedLocalityAwareContainerNum = BoxesRunTime.unboxToInt(updatedHostToContainerCount.values().sum(.MODULE$));
      int requiredLocalityFreeContainerNum = scala.math.package..MODULE$.max(0, numContainer - updatedLocalityAwareContainerNum);
      int requiredLocalityAwareContainerNum = numContainer - requiredLocalityFreeContainerNum;
      ArrayBuffer containerLocalityPreferences = (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      if (requiredLocalityFreeContainerNum > 0) {
         scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), requiredLocalityFreeContainerNum).foreach((i) -> $anonfun$localityOfRequestedContainers$1(containerLocalityPreferences, BoxesRunTime.unboxToInt(i)));
      }

      if (requiredLocalityAwareContainerNum > 0) {
         int largestRatio = BoxesRunTime.unboxToInt(updatedHostToContainerCount.values().max(scala.math.Ordering.Int..MODULE$));
         ObjectRef preferredLocalityRatio = ObjectRef.create((Map)updatedHostToContainerCount.map((x0$1) -> {
            if (x0$1 != null) {
               String k = (String)x0$1._1();
               int ratio = x0$1._2$mcI$sp();
               double adjustedRatio = (double)ratio * (double)requiredLocalityAwareContainerNum / (double)largestRatio;
               return new Tuple2(k, BoxesRunTime.boxToInteger((int)scala.runtime.RichDouble..MODULE$.ceil$extension(scala.Predef..MODULE$.doubleWrapper(adjustedRatio))));
            } else {
               throw new MatchError(x0$1);
            }
         }));
         scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), requiredLocalityAwareContainerNum).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
            String[] hosts = (String[])((MapOps)((Map)preferredLocalityRatio.elem).filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$localityOfRequestedContainers$4(x$1)))).keys().toArray(scala.reflect.ClassTag..MODULE$.apply(String.class));
            Set racks = ((IterableOnceOps)((IterableOps)this.resolver.resolve((Seq)org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(hosts).toImmutableArraySeq()).map((x$2) -> x$2.getNetworkLocation())).filter((x$3) -> BoxesRunTime.boxToBoolean($anonfun$localityOfRequestedContainers$6(x$3)))).toSet();
            containerLocalityPreferences.$plus$eq(new ContainerLocalityPreferences(hosts, (String[])racks.toArray(scala.reflect.ClassTag..MODULE$.apply(String.class))));
            preferredLocalityRatio.elem = (Map)((Map)preferredLocalityRatio.elem).map((x0$2) -> {
               if (x0$2 != null) {
                  String k = (String)x0$2._1();
                  int v = x0$2._2$mcI$sp();
                  return new Tuple2(k, BoxesRunTime.boxToInteger(v - 1));
               } else {
                  throw new MatchError(x0$2);
               }
            });
         });
      }

      return (ContainerLocalityPreferences[])containerLocalityPreferences.toArray(scala.reflect.ClassTag..MODULE$.apply(ContainerLocalityPreferences.class));
   }

   private int numExecutorsPending(final int numTasksPending, final ResourceProfile rp) {
      int tasksPerExec = rp.maxTasksPerExecutor(this.sparkConf());
      return (int)scala.math.package..MODULE$.ceil((double)numTasksPending / (double)tasksPerExec);
   }

   private Map expectedHostToContainerCount(final int localityAwareTasks, final Map hostToLocalTaskCount, final HashMap allocatedHostToContainersMap, final Seq localityMatchedPendingAllocations, final ResourceProfile rp) {
      int totalLocalTaskNum = BoxesRunTime.unboxToInt(hostToLocalTaskCount.values().sum(.MODULE$));
      Map pendingHostToContainersMap = this.pendingHostToContainerCount(localityMatchedPendingAllocations);
      return (Map)hostToLocalTaskCount.map((x0$1) -> {
         if (x0$1 != null) {
            String host = (String)x0$1._1();
            int count = x0$1._2$mcI$sp();
            double expectedCount = (double)count * (double)this.numExecutorsPending(localityAwareTasks, rp) / (double)totalLocalTaskNum;
            double existedCount = (double)BoxesRunTime.unboxToInt(allocatedHostToContainersMap.get(host).map((x$4) -> BoxesRunTime.boxToInteger($anonfun$expectedHostToContainerCount$2(x$4))).getOrElse((JFunction0.mcI.sp)() -> 0)) + BoxesRunTime.unboxToDouble(pendingHostToContainersMap.getOrElse(host, (JFunction0.mcD.sp)() -> (double)0.0F));
            return new Tuple2(host, BoxesRunTime.boxToInteger(scala.math.package..MODULE$.max(0, (int)scala.runtime.RichDouble..MODULE$.ceil$extension(scala.Predef..MODULE$.doubleWrapper(expectedCount - existedCount)))));
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   private Map pendingHostToContainerCount(final Seq localityMatchedPendingAllocations) {
      HashMap pendingHostToContainerCount = new HashMap();
      localityMatchedPendingAllocations.foreach((cr) -> {
         $anonfun$pendingHostToContainerCount$1(pendingHostToContainerCount, cr);
         return BoxedUnit.UNIT;
      });
      int possibleTotalContainerNum = BoxesRunTime.unboxToInt(pendingHostToContainerCount.values().sum(.MODULE$));
      double localityMatchedPendingNum = (double)localityMatchedPendingAllocations.size();
      return pendingHostToContainerCount.map((x0$1) -> {
         if (x0$1 != null) {
            String k = (String)x0$1._1();
            int v = x0$1._2$mcI$sp();
            return new Tuple2(k, BoxesRunTime.boxToDouble((double)v * localityMatchedPendingNum / (double)possibleTotalContainerNum));
         } else {
            throw new MatchError(x0$1);
         }
      }).toMap(scala..less.colon.less..MODULE$.refl());
   }

   // $FF: synthetic method
   public static final ArrayBuffer $anonfun$localityOfRequestedContainers$1(final ArrayBuffer containerLocalityPreferences$1, final int i) {
      return (ArrayBuffer)containerLocalityPreferences$1.$plus$eq(new ContainerLocalityPreferences((String[])null, (String[])null));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$localityOfRequestedContainers$4(final Tuple2 x$1) {
      return x$1._2$mcI$sp() > 0;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$localityOfRequestedContainers$6(final String x$3) {
      return x$3 != null;
   }

   // $FF: synthetic method
   public static final int $anonfun$expectedHostToContainerCount$2(final scala.collection.mutable.Set x$4) {
      return x$4.size();
   }

   // $FF: synthetic method
   public static final void $anonfun$pendingHostToContainerCount$2(final HashMap pendingHostToContainerCount$1, final String n) {
      int count = BoxesRunTime.unboxToInt(pendingHostToContainerCount$1.getOrElse(n, (JFunction0.mcI.sp)() -> 0)) + 1;
      pendingHostToContainerCount$1.update(n, BoxesRunTime.boxToInteger(count));
   }

   // $FF: synthetic method
   public static final void $anonfun$pendingHostToContainerCount$1(final HashMap pendingHostToContainerCount$1, final AMRMClient.ContainerRequest cr) {
      scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(cr.getNodes()).asScala().foreach((n) -> {
         $anonfun$pendingHostToContainerCount$2(pendingHostToContainerCount$1, n);
         return BoxedUnit.UNIT;
      });
   }

   public LocalityPreferredContainerPlacementStrategy(final SparkConf sparkConf, final Configuration yarnConf, final SparkRackResolver resolver) {
      this.sparkConf = sparkConf;
      this.yarnConf = yarnConf;
      this.resolver = resolver;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
