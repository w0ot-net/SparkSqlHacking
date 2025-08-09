package org.apache.spark.deploy.k8s.submit;

import io.fabric8.kubernetes.client.KubernetesClient;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkException;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.annotation.Unstable;
import org.apache.spark.deploy.k8s.Config$;
import org.apache.spark.deploy.k8s.KubernetesDriverConf;
import org.apache.spark.deploy.k8s.KubernetesDriverSpec;
import org.apache.spark.deploy.k8s.KubernetesUtils$;
import org.apache.spark.deploy.k8s.SparkPod;
import org.apache.spark.deploy.k8s.SparkPod$;
import org.apache.spark.deploy.k8s.features.BasicDriverFeatureStep;
import org.apache.spark.deploy.k8s.features.DriverCommandFeatureStep;
import org.apache.spark.deploy.k8s.features.DriverKubernetesCredentialsFeatureStep;
import org.apache.spark.deploy.k8s.features.DriverServiceFeatureStep;
import org.apache.spark.deploy.k8s.features.EnvSecretsFeatureStep;
import org.apache.spark.deploy.k8s.features.HadoopConfDriverFeatureStep;
import org.apache.spark.deploy.k8s.features.KerberosConfDriverFeatureStep;
import org.apache.spark.deploy.k8s.features.KubernetesDriverCustomFeatureConfigStep;
import org.apache.spark.deploy.k8s.features.KubernetesExecutorCustomFeatureConfigStep;
import org.apache.spark.deploy.k8s.features.KubernetesFeatureConfigStep;
import org.apache.spark.deploy.k8s.features.LocalDirsFeatureStep;
import org.apache.spark.deploy.k8s.features.LocalDirsFeatureStep$;
import org.apache.spark.deploy.k8s.features.MountSecretsFeatureStep;
import org.apache.spark.deploy.k8s.features.MountVolumesFeatureStep;
import org.apache.spark.deploy.k8s.features.PodTemplateConfigMapStep;
import org.apache.spark.util.Utils.;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@Unstable
@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u00153Aa\u0001\u0003\u0001#!)\u0001\u0004\u0001C\u00013!)A\u0004\u0001C\u0001;\t92*\u001e2fe:,G/Z:Ee&4XM\u001d\"vS2$WM\u001d\u0006\u0003\u000b\u0019\taa];c[&$(BA\u0004\t\u0003\rY\u0007h\u001d\u0006\u0003\u0013)\ta\u0001Z3qY>L(BA\u0006\r\u0003\u0015\u0019\b/\u0019:l\u0015\tia\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001f\u0005\u0019qN]4\u0004\u0001M\u0011\u0001A\u0005\t\u0003'Yi\u0011\u0001\u0006\u0006\u0002+\u0005)1oY1mC&\u0011q\u0003\u0006\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?)\u0005Q\u0002CA\u000e\u0001\u001b\u0005!\u0011!\u00052vS2$gI]8n\r\u0016\fG/\u001e:fgR\u0019aDI\u0014\u0011\u0005}\u0001S\"\u0001\u0004\n\u0005\u00052!\u0001F&vE\u0016\u0014h.\u001a;fg\u0012\u0013\u0018N^3s'B,7\rC\u0003$\u0005\u0001\u0007A%\u0001\u0003d_:4\u0007CA\u0010&\u0013\t1cA\u0001\u000bLk\n,'O\\3uKN$%/\u001b<fe\u000e{gN\u001a\u0005\u0006Q\t\u0001\r!K\u0001\u0007G2LWM\u001c;\u0011\u0005)\u0012T\"A\u0016\u000b\u0005!b#BA\u0017/\u0003)YWOY3s]\u0016$Xm\u001d\u0006\u0003_A\nqAZ1ce&\u001c\u0007HC\u00012\u0003\tIw.\u0003\u00024W\t\u00012*\u001e2fe:,G/Z:DY&,g\u000e\u001e\u0015\u0004\u0005UZ\u0004C\u0001\u001c:\u001b\u00059$B\u0001\u001d\u000b\u0003)\tgN\\8uCRLwN\\\u0005\u0003u]\u0012QaU5oG\u0016\f\u0013\u0001P\u0001\u0006g9\u0002d\u0006\r\u0015\u0003\u0001y\u0002\"AN \n\u0005\u0001;$\u0001C+ogR\f'\r\\3)\u0005\u0001\u0011\u0005C\u0001\u001cD\u0013\t!uG\u0001\u0007EKZ,Gn\u001c9fe\u0006\u0003\u0018\u000e"
)
public class KubernetesDriverBuilder {
   public KubernetesDriverSpec buildFromFeatures(final KubernetesDriverConf conf, final KubernetesClient client) {
      SparkPod initialPod = (SparkPod)((Option)conf.get(Config$.MODULE$.KUBERNETES_DRIVER_PODTEMPLATE_FILE())).map((file) -> KubernetesUtils$.MODULE$.loadPodFromTemplate(client, file, (Option)conf.get(Config$.MODULE$.KUBERNETES_DRIVER_PODTEMPLATE_CONTAINER_NAME()), conf.sparkConf())).getOrElse(() -> SparkPod$.MODULE$.initialPod());
      Seq userFeatures = (Seq)((IterableOps)conf.get(Config$.MODULE$.KUBERNETES_DRIVER_POD_FEATURE_STEPS())).map((className) -> {
         Object feature = .MODULE$.classForName(className, .MODULE$.classForName$default$2(), .MODULE$.classForName$default$3()).getConstructor().newInstance();
         Object var10000;
         if (feature instanceof KubernetesDriverCustomFeatureConfigStep var6) {
            var6.init(conf);
            var10000 = new Some(var6);
         } else if (feature instanceof KubernetesExecutorCustomFeatureConfigStep) {
            var10000 = scala.None..MODULE$;
         } else if (feature instanceof KubernetesFeatureConfigStep) {
            KubernetesFeatureConfigStep var7 = (KubernetesFeatureConfigStep)feature;
            var10000 = new Some(var7);
         } else {
            var10000 = scala.None..MODULE$;
         }

         Option initializedFeature = (Option)var10000;
         return (KubernetesFeatureConfigStep)initializedFeature.getOrElse(() -> {
            throw new SparkException("Failed to initialize feature step: " + className + ", please make sure your driver side feature steps are implemented by `" + KubernetesDriverCustomFeatureConfigStep.class.getName() + "` or `" + KubernetesFeatureConfigStep.class.getName() + "`.");
         });
      });
      Seq features = (Seq)scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new KubernetesFeatureConfigStep[]{new BasicDriverFeatureStep(conf), new DriverKubernetesCredentialsFeatureStep(conf), new DriverServiceFeatureStep(conf), new MountSecretsFeatureStep(conf), new EnvSecretsFeatureStep(conf), new MountVolumesFeatureStep(conf), new DriverCommandFeatureStep(conf), new HadoopConfDriverFeatureStep(conf), new KerberosConfDriverFeatureStep(conf), new PodTemplateConfigMapStep(conf), new LocalDirsFeatureStep(conf, LocalDirsFeatureStep$.MODULE$.$lessinit$greater$default$2())})).$plus$plus(userFeatures);
      KubernetesDriverSpec spec = new KubernetesDriverSpec(initialPod, (Seq)scala.package..MODULE$.Seq().empty(), (Seq)scala.package..MODULE$.Seq().empty(), scala.Predef..MODULE$.wrapRefArray((Object[])conf.sparkConf().getAll()).toMap(scala..less.colon.less..MODULE$.refl()));
      return (KubernetesDriverSpec)features.foldLeft(spec, (x0$1, x1$1) -> {
         Tuple2 var3 = new Tuple2(x0$1, x1$1);
         if (var3 != null) {
            KubernetesDriverSpec spec = (KubernetesDriverSpec)var3._1();
            KubernetesFeatureConfigStep feature = (KubernetesFeatureConfigStep)var3._2();
            SparkPod configuredPod = feature.configurePod(spec.pod());
            Map addedSystemProperties = feature.getAdditionalPodSystemProperties();
            Seq addedPreResources = feature.getAdditionalPreKubernetesResources();
            Seq addedResources = feature.getAdditionalKubernetesResources();
            return new KubernetesDriverSpec(configuredPod, (Seq)spec.driverPreKubernetesResources().$plus$plus(addedPreResources), (Seq)spec.driverKubernetesResources().$plus$plus(addedResources), (Map)spec.systemProperties().$plus$plus(addedSystemProperties));
         } else {
            throw new MatchError(var3);
         }
      });
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
