package org.apache.spark.scheduler.cluster.k8s;

import io.fabric8.kubernetes.client.KubernetesClient;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SecurityManager;
import org.apache.spark.SparkException;
import org.apache.spark.deploy.k8s.Config$;
import org.apache.spark.deploy.k8s.KubernetesExecutorConf;
import org.apache.spark.deploy.k8s.KubernetesExecutorSpec;
import org.apache.spark.deploy.k8s.KubernetesUtils$;
import org.apache.spark.deploy.k8s.SparkPod;
import org.apache.spark.deploy.k8s.SparkPod$;
import org.apache.spark.deploy.k8s.features.BasicExecutorFeatureStep;
import org.apache.spark.deploy.k8s.features.EnvSecretsFeatureStep;
import org.apache.spark.deploy.k8s.features.ExecutorKubernetesCredentialsFeatureStep;
import org.apache.spark.deploy.k8s.features.HadoopConfExecutorFeatureStep;
import org.apache.spark.deploy.k8s.features.KubernetesDriverCustomFeatureConfigStep;
import org.apache.spark.deploy.k8s.features.KubernetesExecutorCustomFeatureConfigStep;
import org.apache.spark.deploy.k8s.features.KubernetesFeatureConfigStep;
import org.apache.spark.deploy.k8s.features.LocalDirsFeatureStep;
import org.apache.spark.deploy.k8s.features.LocalDirsFeatureStep$;
import org.apache.spark.deploy.k8s.features.MountSecretsFeatureStep;
import org.apache.spark.deploy.k8s.features.MountVolumesFeatureStep;
import org.apache.spark.resource.ResourceProfile;
import org.apache.spark.util.Utils.;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00153Qa\u0001\u0003\u0001\u0015AAQa\u0006\u0001\u0005\u0002eAQ\u0001\b\u0001\u0005\u0002u\u0011\u0011dS;cKJtW\r^3t\u000bb,7-\u001e;pe\n+\u0018\u000e\u001c3fe*\u0011QAB\u0001\u0004Wb\u001a(BA\u0004\t\u0003\u001d\u0019G.^:uKJT!!\u0003\u0006\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014(BA\u0006\r\u0003\u0015\u0019\b/\u0019:l\u0015\tia\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001f\u0005\u0019qN]4\u0014\u0005\u0001\t\u0002C\u0001\n\u0016\u001b\u0005\u0019\"\"\u0001\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Y\u0019\"AB!osJ+g-\u0001\u0004=S:LGOP\u0002\u0001)\u0005Q\u0002CA\u000e\u0001\u001b\u0005!\u0011!\u00052vS2$gI]8n\r\u0016\fG/\u001e:fgR)a$\n\u00161{A\u0011qdI\u0007\u0002A)\u0011Q!\t\u0006\u0003E)\ta\u0001Z3qY>L\u0018B\u0001\u0013!\u0005YYUOY3s]\u0016$Xm]#yK\u000e,Ho\u001c:Ta\u0016\u001c\u0007\"\u0002\u0014\u0003\u0001\u00049\u0013\u0001B2p]\u001a\u0004\"a\b\u0015\n\u0005%\u0002#AF&vE\u0016\u0014h.\u001a;fg\u0016CXmY;u_J\u001cuN\u001c4\t\u000b-\u0012\u0001\u0019\u0001\u0017\u0002\rM,7-T4s!\tic&D\u0001\u000b\u0013\ty#BA\bTK\u000e,(/\u001b;z\u001b\u0006t\u0017mZ3s\u0011\u0015\t$\u00011\u00013\u0003\u0019\u0019G.[3oiB\u00111gO\u0007\u0002i)\u0011\u0011'\u000e\u0006\u0003m]\n!b[;cKJtW\r^3t\u0015\tA\u0014(A\u0004gC\n\u0014\u0018n\u0019\u001d\u000b\u0003i\n!![8\n\u0005q\"$\u0001E&vE\u0016\u0014h.\u001a;fg\u000ec\u0017.\u001a8u\u0011\u0015q$\u00011\u0001@\u0003=\u0011Xm]8ve\u000e,\u0007K]8gS2,\u0007C\u0001!D\u001b\u0005\t%B\u0001\"\u000b\u0003!\u0011Xm]8ve\u000e,\u0017B\u0001#B\u0005=\u0011Vm]8ve\u000e,\u0007K]8gS2,\u0007"
)
public class KubernetesExecutorBuilder {
   public KubernetesExecutorSpec buildFromFeatures(final KubernetesExecutorConf conf, final SecurityManager secMgr, final KubernetesClient client, final ResourceProfile resourceProfile) {
      SparkPod initialPod = (SparkPod)((Option)conf.get(Config$.MODULE$.KUBERNETES_EXECUTOR_PODTEMPLATE_FILE())).map((file) -> KubernetesUtils$.MODULE$.loadPodFromTemplate(client, file, (Option)conf.get(Config$.MODULE$.KUBERNETES_EXECUTOR_PODTEMPLATE_CONTAINER_NAME()), conf.sparkConf())).getOrElse(() -> SparkPod$.MODULE$.initialPod());
      Seq userFeatures = (Seq)((IterableOps)conf.get(Config$.MODULE$.KUBERNETES_EXECUTOR_POD_FEATURE_STEPS())).map((className) -> {
         Object feature = .MODULE$.classForName(className, .MODULE$.classForName$default$2(), .MODULE$.classForName$default$3()).getConstructor().newInstance();
         Object var10000;
         if (feature instanceof KubernetesExecutorCustomFeatureConfigStep var6) {
            var6.init(conf);
            var10000 = new Some(var6);
         } else if (feature instanceof KubernetesDriverCustomFeatureConfigStep) {
            var10000 = scala.None..MODULE$;
         } else if (feature instanceof KubernetesFeatureConfigStep) {
            KubernetesFeatureConfigStep var7 = (KubernetesFeatureConfigStep)feature;
            var10000 = new Some(var7);
         } else {
            var10000 = scala.None..MODULE$;
         }

         Option initializedFeature = (Option)var10000;
         return (KubernetesFeatureConfigStep)initializedFeature.getOrElse(() -> {
            throw new SparkException("Failed to initialize feature step: " + className + ", please make sure your executor side feature steps are implemented by `" + KubernetesExecutorCustomFeatureConfigStep.class.getSimpleName() + "` or `" + KubernetesFeatureConfigStep.class.getSimpleName() + "`.");
         });
      });
      Seq features = (Seq)(new scala.collection.immutable..colon.colon(new BasicExecutorFeatureStep(conf, secMgr, resourceProfile), new scala.collection.immutable..colon.colon(new ExecutorKubernetesCredentialsFeatureStep(conf), new scala.collection.immutable..colon.colon(new MountSecretsFeatureStep(conf), new scala.collection.immutable..colon.colon(new EnvSecretsFeatureStep(conf), new scala.collection.immutable..colon.colon(new MountVolumesFeatureStep(conf), new scala.collection.immutable..colon.colon(new HadoopConfExecutorFeatureStep(conf), new scala.collection.immutable..colon.colon(new LocalDirsFeatureStep(conf, LocalDirsFeatureStep$.MODULE$.$lessinit$greater$default$2()), scala.collection.immutable.Nil..MODULE$)))))))).$plus$plus(userFeatures);
      KubernetesExecutorSpec spec = new KubernetesExecutorSpec(initialPod, (Seq)scala.package..MODULE$.Seq().empty());
      return (KubernetesExecutorSpec)features.foldLeft(spec, (x0$1, x1$1) -> {
         Tuple2 var3 = new Tuple2(x0$1, x1$1);
         if (var3 != null) {
            KubernetesExecutorSpec spec = (KubernetesExecutorSpec)var3._1();
            KubernetesFeatureConfigStep feature = (KubernetesFeatureConfigStep)var3._2();
            SparkPod configuredPod = feature.configurePod(spec.pod());
            Seq addedResources = feature.getAdditionalKubernetesResources();
            return new KubernetesExecutorSpec(configuredPod, (Seq)spec.executorKubernetesResources().$plus$plus(addedResources));
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
