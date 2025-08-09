package org.apache.spark.deploy.k8s.features;

import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.ContainerBuilder;
import io.fabric8.kubernetes.api.model.ContainerFluent;
import io.fabric8.kubernetes.api.model.EnvVarSourceBuilder;
import io.fabric8.kubernetes.api.model.LocalObjectReference;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.api.model.PodFluent;
import io.fabric8.kubernetes.api.model.PodSpec;
import io.fabric8.kubernetes.api.model.Quantity;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import org.apache.spark.SparkException;
import org.apache.spark.deploy.k8s.Config$;
import org.apache.spark.deploy.k8s.Constants$;
import org.apache.spark.deploy.k8s.KubernetesDriverConf;
import org.apache.spark.deploy.k8s.KubernetesUtils$;
import org.apache.spark.deploy.k8s.SparkPod;
import org.apache.spark.deploy.k8s.submit.NonJVMResource;
import org.apache.spark.internal.config.ConfigEntry;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005m4Qa\u0007\u000f\u0001E!B\u0001b\r\u0001\u0003\u0002\u0003\u0006I!\u000e\u0005\u0006s\u0001!\tA\u000f\u0005\b{\u0001\u0011\r\u0011\"\u0003?\u0011\u0019Q\u0005\u0001)A\u0005\u007f!91\n\u0001b\u0001\n\u0013q\u0004B\u0002'\u0001A\u0003%q\bC\u0004N\u0001\t\u0007I\u0011\u0002(\t\rI\u0003\u0001\u0015!\u0003P\u0011\u001d\u0019\u0006A1A\u0005\nyBa\u0001\u0016\u0001!\u0002\u0013y\u0004bB+\u0001\u0005\u0004%IA\u0016\u0005\u00075\u0002\u0001\u000b\u0011B,\t\u000fm\u0003!\u0019!C\u00059\"1\u0001\r\u0001Q\u0001\nuCq!\u0019\u0001C\u0002\u0013%!\r\u0003\u0004g\u0001\u0001\u0006Ia\u0019\u0005\bO\u0002\u0011\r\u0011\"\u0003]\u0011\u0019A\u0007\u0001)A\u0005;\"9\u0011\u000e\u0001b\u0001\n\u0013\u0011\u0007B\u00026\u0001A\u0003%1\rC\u0004l\u0001\t\u0007I\u0011\u0002/\t\r1\u0004\u0001\u0015!\u0003^\u0011\u001di\u0007A1A\u0005\nqCaA\u001c\u0001!\u0002\u0013i\u0006\"B8\u0001\t\u0003\u0002\b\"\u0002<\u0001\t\u0003:(A\u0006\"bg&\u001cGI]5wKJ4U-\u0019;ve\u0016\u001cF/\u001a9\u000b\u0005uq\u0012\u0001\u00034fCR,(/Z:\u000b\u0005}\u0001\u0013aA69g*\u0011\u0011EI\u0001\u0007I\u0016\u0004Hn\\=\u000b\u0005\r\"\u0013!B:qCJ\\'BA\u0013'\u0003\u0019\t\u0007/Y2iK*\tq%A\u0002pe\u001e\u001c2\u0001A\u00150!\tQS&D\u0001,\u0015\u0005a\u0013!B:dC2\f\u0017B\u0001\u0018,\u0005\u0019\te.\u001f*fMB\u0011\u0001'M\u0007\u00029%\u0011!\u0007\b\u0002\u001c\u0017V\u0014WM\u001d8fi\u0016\u001ch)Z1ukJ,7i\u001c8gS\u001e\u001cF/\u001a9\u0002\t\r|gNZ\u0002\u0001!\t1t'D\u0001\u001f\u0013\tAdD\u0001\u000bLk\n,'O\\3uKN$%/\u001b<fe\u000e{gNZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005mb\u0004C\u0001\u0019\u0001\u0011\u0015\u0019$\u00011\u00016\u00035!'/\u001b<feB{GMT1nKV\tq\b\u0005\u0002A\u000f:\u0011\u0011)\u0012\t\u0003\u0005.j\u0011a\u0011\u0006\u0003\tR\na\u0001\u0010:p_Rt\u0014B\u0001$,\u0003\u0019\u0001&/\u001a3fM&\u0011\u0001*\u0013\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u0019[\u0013A\u00043sSZ,'\u000fU8e\u001d\u0006lW\rI\u0001\u0015IJLg/\u001a:D_:$\u0018-\u001b8fe&k\u0017mZ3\u0002+\u0011\u0014\u0018N^3s\u0007>tG/Y5oKJLU.Y4fA\u0005qAM]5wKJ\u001c\u0005/^\"pe\u0016\u001cX#A(\u0011\u0005)\u0002\u0016BA),\u0005\rIe\u000e^\u0001\u0010IJLg/\u001a:DaV\u001cuN]3tA\u0005\u0011BM]5wKJ\u001cuN]3t%\u0016\fX/Z:u\u0003M!'/\u001b<fe\u000e{'/Z:SKF,Xm\u001d;!\u0003A!'/\u001b<fe2KW.\u001b;D_J,7/F\u0001X!\rQ\u0003lP\u0005\u00033.\u0012aa\u00149uS>t\u0017!\u00053sSZ,'\u000fT5nSR\u001cuN]3tA\u0005yAM]5wKJlU-\\8ss6K')F\u0001^!\tQc,\u0003\u0002`W\t!Aj\u001c8h\u0003A!'/\u001b<fe6+Wn\u001c:z\u001b&\u0014\u0005%A\u000beK\u001a\fW\u000f\u001c;Pm\u0016\u0014\b.Z1e\r\u0006\u001cGo\u001c:\u0016\u0003\r\u0004\"A\u000b3\n\u0005\u0015\\#A\u0002#pk\ndW-\u0001\feK\u001a\fW\u000f\u001c;Pm\u0016\u0014\b.Z1e\r\u0006\u001cGo\u001c:!\u0003m!'/\u001b<fe6Kg.[7v[6+Wn\u001c:z\u001fZ,'\u000f[3bI\u0006aBM]5wKJl\u0015N\\5nk6lU-\\8ss>3XM\u001d5fC\u0012\u0004\u0013\u0001F7f[>\u0014\u0018p\u0014<fe\",\u0017\r\u001a$bGR|'/A\u000bnK6|'/_(wKJDW-\u00193GC\u000e$xN\u001d\u0011\u0002#5,Wn\u001c:z\u001fZ,'\u000f[3bI6K')\u0001\nnK6|'/_(wKJDW-\u00193NS\n\u0003\u0013a\u00073sSZ,'/T3n_JLx+\u001b;i\u001fZ,'\u000f[3bI6K')\u0001\u000fee&4XM]'f[>\u0014\u0018pV5uQ>3XM\u001d5fC\u0012l\u0015N\u0011\u0011\u0002\u0019\r|gNZ5hkJ,\u0007k\u001c3\u0015\u0005E$\bC\u0001\u001cs\u0013\t\u0019hD\u0001\u0005Ta\u0006\u00148\u000eU8e\u0011\u0015)\u0018\u00041\u0001r\u0003\r\u0001x\u000eZ\u0001!O\u0016$\u0018\t\u001a3ji&|g.\u00197Q_\u0012\u001c\u0016p\u001d;f[B\u0013x\u000e]3si&,7\u000fF\u0001y!\u0011\u0001\u0015pP \n\u0005iL%aA'ba\u0002"
)
public class BasicDriverFeatureStep implements KubernetesFeatureConfigStep {
   private final KubernetesDriverConf conf;
   private final String driverPodName;
   private final String driverContainerImage;
   private final int driverCpuCores;
   private final String driverCoresRequest;
   private final Option driverLimitCores;
   private final long driverMemoryMiB;
   private final double defaultOverheadFactor;
   private final long driverMinimumMemoryOverhead;
   private final double memoryOverheadFactor;
   private final long memoryOverheadMiB;
   private final long driverMemoryWithOverheadMiB;

   public Seq getAdditionalPreKubernetesResources() {
      return KubernetesFeatureConfigStep.getAdditionalPreKubernetesResources$(this);
   }

   public Seq getAdditionalKubernetesResources() {
      return KubernetesFeatureConfigStep.getAdditionalKubernetesResources$(this);
   }

   private String driverPodName() {
      return this.driverPodName;
   }

   private String driverContainerImage() {
      return this.driverContainerImage;
   }

   private int driverCpuCores() {
      return this.driverCpuCores;
   }

   private String driverCoresRequest() {
      return this.driverCoresRequest;
   }

   private Option driverLimitCores() {
      return this.driverLimitCores;
   }

   private long driverMemoryMiB() {
      return this.driverMemoryMiB;
   }

   private double defaultOverheadFactor() {
      return this.defaultOverheadFactor;
   }

   private long driverMinimumMemoryOverhead() {
      return this.driverMinimumMemoryOverhead;
   }

   private double memoryOverheadFactor() {
      return this.memoryOverheadFactor;
   }

   private long memoryOverheadMiB() {
      return this.memoryOverheadMiB;
   }

   private long driverMemoryWithOverheadMiB() {
      return this.driverMemoryWithOverheadMiB;
   }

   public SparkPod configurePod(final SparkPod pod) {
      Seq driverCustomEnvs = KubernetesUtils$.MODULE$.buildEnvVars((Seq)(new .colon.colon(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(Constants$.MODULE$.ENV_APPLICATION_ID()), this.conf.appId()), scala.collection.immutable.Nil..MODULE$)).$plus$plus(this.conf.environment()));
      Quantity driverCpuQuantity = new Quantity(this.driverCoresRequest());
      Quantity driverMemoryQuantity = new Quantity(this.driverMemoryWithOverheadMiB() + "Mi");
      Option maybeCpuLimitQuantity = this.driverLimitCores().map((limitCores) -> new Tuple2("cpu", new Quantity(limitCores)));
      Map driverResourceQuantities = KubernetesUtils$.MODULE$.buildResourcesQuantities(org.apache.spark.internal.config.package..MODULE$.SPARK_DRIVER_PREFIX(), this.conf.sparkConf());
      int driverPort = this.conf.sparkConf().getInt(org.apache.spark.internal.config.package..MODULE$.DRIVER_PORT().key(), Constants$.MODULE$.DEFAULT_DRIVER_PORT());
      int driverBlockManagerPort = this.conf.sparkConf().getInt(org.apache.spark.internal.config.package..MODULE$.DRIVER_BLOCK_MANAGER_PORT().key(), this.conf.sparkConf().getInt(org.apache.spark.internal.config.package..MODULE$.BLOCK_MANAGER_PORT().key(), Constants$.MODULE$.DEFAULT_BLOCKMANAGER_PORT()));
      int driverUIPort = org.apache.spark.ui.SparkUI..MODULE$.getUIPort(this.conf.sparkConf());
      Container driverContainer = ((ContainerBuilder)((ContainerFluent.ResourcesNested)((ContainerFluent)((ContainerFluent.EnvNested)((ContainerFluent)((ContainerFluent.EnvNested)((ContainerFluent)((ContainerFluent.PortsNested)((ContainerFluent)((ContainerFluent.PortsNested)((ContainerFluent)((ContainerFluent.PortsNested)(new ContainerBuilder(pod.container())).withName((String)scala.Option..MODULE$.apply(pod.container().getName()).getOrElse(() -> Constants$.MODULE$.DEFAULT_DRIVER_CONTAINER_NAME())).withImage(this.driverContainerImage()).withImagePullPolicy(this.conf.imagePullPolicy()).addNewPort().withName(Constants$.MODULE$.DRIVER_PORT_NAME()).withContainerPort(scala.Predef..MODULE$.int2Integer(driverPort)).withProtocol("TCP")).endPort()).addNewPort().withName(Constants$.MODULE$.BLOCK_MANAGER_PORT_NAME()).withContainerPort(scala.Predef..MODULE$.int2Integer(driverBlockManagerPort)).withProtocol("TCP")).endPort()).addNewPort().withName(Constants$.MODULE$.UI_PORT_NAME()).withContainerPort(scala.Predef..MODULE$.int2Integer(driverUIPort)).withProtocol("TCP")).endPort()).addNewEnv().withName(Constants$.MODULE$.ENV_SPARK_USER()).withValue(org.apache.spark.util.Utils..MODULE$.getCurrentUserName())).endEnv()).addAllToEnv(scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(driverCustomEnvs).asJava()).addNewEnv().withName(Constants$.MODULE$.ENV_DRIVER_BIND_ADDRESS()).withValueFrom(((EnvVarSourceBuilder)(new EnvVarSourceBuilder()).withNewFieldRef("v1", "status.podIP")).build())).endEnv()).editOrNewResources().addToRequests("cpu", driverCpuQuantity).addToLimits(scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(scala.Option..MODULE$.option2Iterable(maybeCpuLimitQuantity).toMap(scala..less.colon.less..MODULE$.refl())).asJava()).addToRequests("memory", driverMemoryQuantity).addToLimits("memory", driverMemoryQuantity).addToLimits(scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(driverResourceQuantities).asJava())).endResources()).build();
      Pod driverPod = ((PodBuilder)((PodFluent.SpecNested)((PodFluent)((PodFluent.MetadataNested)(new PodBuilder(pod.pod())).editOrNewMetadata().withName(this.driverPodName()).addToLabels(scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(this.conf.labels()).asJava()).addToAnnotations(scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(this.conf.annotations()).asJava())).endMetadata()).editOrNewSpec().withRestartPolicy("Never").addToNodeSelector(scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(this.conf.nodeSelector()).asJava()).addToNodeSelector(scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(this.conf.driverNodeSelector()).asJava()).addToImagePullSecrets((LocalObjectReference[])this.conf.imagePullSecrets().toArray(scala.reflect.ClassTag..MODULE$.apply(LocalObjectReference.class)))).endSpec()).build();
      Option var10000 = this.conf.schedulerName();
      PodSpec var12 = driverPod.getSpec();
      var10000.foreach((x$1) -> {
         $anonfun$configurePod$3(var12, x$1);
         return BoxedUnit.UNIT;
      });
      return new SparkPod(driverPod, driverContainer);
   }

   public Map getAdditionalPodSystemProperties() {
      scala.collection.mutable.Map additionalProps = (scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(Config$.MODULE$.KUBERNETES_DRIVER_POD_NAME().key()), this.driverPodName()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("spark.app.id"), this.conf.appId()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(Config$.MODULE$.KUBERNETES_DRIVER_SUBMIT_CHECK().key()), "true"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(Config$.MODULE$.MEMORY_OVERHEAD_FACTOR().key()), Double.toString(this.defaultOverheadFactor()))})));
      (new .colon.colon(org.apache.spark.internal.config.package..MODULE$.JARS(), new .colon.colon(org.apache.spark.internal.config.package..MODULE$.FILES(), new .colon.colon(org.apache.spark.internal.config.package..MODULE$.ARCHIVES(), new .colon.colon(org.apache.spark.internal.config.package..MODULE$.SUBMIT_PYTHON_FILES(), scala.collection.immutable.Nil..MODULE$))))).foreach((key) -> {
         Tuple2 var5 = ((IterableOps)this.conf.get(key)).partition((uri) -> BoxesRunTime.boxToBoolean($anonfun$getAdditionalPodSystemProperties$2(uri)));
         if (var5 == null) {
            throw new MatchError(var5);
         } else {
            Seq localUrisx;
            Seq remoteUrisx;
            Seq var10000;
            label40: {
               label39: {
                  Seq localUris = (Seq)var5._1();
                  Seq remoteUris = (Seq)var5._2();
                  Tuple2 var4 = new Tuple2(localUris, remoteUris);
                  localUrisx = (Seq)var4._1();
                  remoteUrisx = (Seq)var4._2();
                  ConfigEntry var11 = org.apache.spark.internal.config.package..MODULE$.ARCHIVES();
                  if (key == null) {
                     if (var11 == null) {
                        break label39;
                     }
                  } else if (key.equals(var11)) {
                     break label39;
                  }

                  var10000 = localUrisx;
                  break label40;
               }

               var10000 = (Seq)((IterableOps)localUrisx.map((x$2) -> org.apache.spark.util.Utils..MODULE$.getUriBuilder(x$2).fragment((String)null).build(new Object[0]))).map((x$3) -> x$3.toString());
            }

            Seq value = var10000;
            Iterable resolved = KubernetesUtils$.MODULE$.uploadAndTransformFileUris(value, new Some(this.conf.sparkConf()));
            if (!resolved.nonEmpty()) {
               return BoxedUnit.UNIT;
            } else {
               label31: {
                  label30: {
                     ConfigEntry var14 = org.apache.spark.internal.config.package..MODULE$.ARCHIVES();
                     if (key == null) {
                        if (var14 == null) {
                           break label30;
                        }
                     } else if (key.equals(var14)) {
                        break label30;
                     }

                     var15 = resolved;
                     break label31;
                  }

                  var15 = (Iterable)((IterableOps)localUrisx.zip(resolved)).map((x0$1) -> {
                     if (x0$1 != null) {
                        String uri = (String)x0$1._1();
                        String r = (String)x0$1._2();
                        return org.apache.spark.util.Utils..MODULE$.getUriBuilder(r).fragment((new URI(uri)).getFragment()).build(new Object[0]).toString();
                     } else {
                        throw new MatchError(x0$1);
                     }
                  });
               }

               Iterable resolvedValue = var15;
               return additionalProps.put(key.key(), ((IterableOnceOps)resolvedValue.$plus$plus(remoteUrisx)).mkString(","));
            }
         }
      });
      return additionalProps.toMap(scala..less.colon.less..MODULE$.refl());
   }

   // $FF: synthetic method
   public static final void $anonfun$configurePod$3(final PodSpec eta$0$1$1, final String x$1) {
      eta$0$1$1.setSchedulerName(x$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getAdditionalPodSystemProperties$2(final String uri) {
      return KubernetesUtils$.MODULE$.isLocalAndResolvable(uri);
   }

   public BasicDriverFeatureStep(final KubernetesDriverConf conf) {
      this.conf = conf;
      KubernetesFeatureConfigStep.$init$(this);
      this.driverPodName = (String)((Option)conf.get(Config$.MODULE$.KUBERNETES_DRIVER_POD_NAME())).getOrElse(() -> this.conf.resourceNamePrefix() + "-driver");
      this.driverContainerImage = (String)((Option)conf.get(Config$.MODULE$.DRIVER_CONTAINER_IMAGE())).getOrElse(() -> {
         throw new SparkException("Must specify the driver container image");
      });
      this.driverCpuCores = BoxesRunTime.unboxToInt(conf.get(org.apache.spark.internal.config.package..MODULE$.DRIVER_CORES()));
      this.driverCoresRequest = (String)((Option)conf.get(Config$.MODULE$.KUBERNETES_DRIVER_REQUEST_CORES())).getOrElse(() -> Integer.toString(this.driverCpuCores()));
      this.driverLimitCores = (Option)conf.get(Config$.MODULE$.KUBERNETES_DRIVER_LIMIT_CORES());
      this.driverMemoryMiB = BoxesRunTime.unboxToLong(conf.get(org.apache.spark.internal.config.package..MODULE$.DRIVER_MEMORY()));
      this.defaultOverheadFactor = conf.mainAppResource() instanceof NonJVMResource ? (conf.contains(Config$.MODULE$.MEMORY_OVERHEAD_FACTOR()) ? BoxesRunTime.unboxToDouble(conf.get(Config$.MODULE$.MEMORY_OVERHEAD_FACTOR())) : Constants$.MODULE$.NON_JVM_MEMORY_OVERHEAD_FACTOR()) : BoxesRunTime.unboxToDouble(conf.get(Config$.MODULE$.MEMORY_OVERHEAD_FACTOR()));
      this.driverMinimumMemoryOverhead = BoxesRunTime.unboxToLong(conf.get(org.apache.spark.internal.config.package..MODULE$.DRIVER_MIN_MEMORY_OVERHEAD()));
      this.memoryOverheadFactor = conf.contains(org.apache.spark.internal.config.package..MODULE$.DRIVER_MEMORY_OVERHEAD_FACTOR()) ? BoxesRunTime.unboxToDouble(conf.get(org.apache.spark.internal.config.package..MODULE$.DRIVER_MEMORY_OVERHEAD_FACTOR())) : this.defaultOverheadFactor();
      this.memoryOverheadMiB = BoxesRunTime.unboxToLong(((Option)conf.get(org.apache.spark.internal.config.package..MODULE$.DRIVER_MEMORY_OVERHEAD())).getOrElse((JFunction0.mcJ.sp)() -> scala.math.package..MODULE$.max((long)((int)(this.memoryOverheadFactor() * (double)this.driverMemoryMiB())), this.driverMinimumMemoryOverhead())));
      this.driverMemoryWithOverheadMiB = this.driverMemoryMiB() + this.memoryOverheadMiB();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
