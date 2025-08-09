package org.apache.spark.deploy.k8s.submit;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.Listable;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import java.io.PrintStream;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.SparkSubmitOperation;
import org.apache.spark.deploy.k8s.Config$;
import org.apache.spark.deploy.k8s.Constants$;
import org.apache.spark.deploy.k8s.KubernetesUtils$;
import org.apache.spark.deploy.k8s.SparkKubernetesClientFactory;
import org.apache.spark.deploy.k8s.SparkKubernetesClientFactory$;
import org.apache.spark.util.CommandLineLoggingUtils;
import scala.Array;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.collection.IterableOnceOps;
import scala.collection.StringOps.;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u000514Qa\u0003\u0007\u0001%aAQ!\u000b\u0001\u0005\u0002-BQA\f\u0001\u0005\n=BQ\u0001\u0011\u0001\u0005\u0002\u0005CQA\u0015\u0001\u0005BMCQa\u0016\u0001\u0005BaCQa\u0017\u0001\u0005Bq;Qa\u0018\u0007\t\n\u00014Qa\u0003\u0007\t\n\u0005DQ!\u000b\u0005\u0005\u0002\tDQa\u0019\u0005\u0005\u0002\u0011\u0014qc\u0013\u001dT'B\f'o[*vE6LGo\u00149fe\u0006$\u0018n\u001c8\u000b\u00055q\u0011AB:vE6LGO\u0003\u0002\u0010!\u0005\u00191\u000eO:\u000b\u0005E\u0011\u0012A\u00023fa2|\u0017P\u0003\u0002\u0014)\u0005)1\u000f]1sW*\u0011QCF\u0001\u0007CB\f7\r[3\u000b\u0003]\t1a\u001c:h'\u0011\u0001\u0011dH\u0012\u0011\u0005iiR\"A\u000e\u000b\u0003q\tQa]2bY\u0006L!AH\u000e\u0003\r\u0005s\u0017PU3g!\t\u0001\u0013%D\u0001\u0011\u0013\t\u0011\u0003C\u0001\u000bTa\u0006\u00148nU;c[&$x\n]3sCRLwN\u001c\t\u0003I\u001dj\u0011!\n\u0006\u0003MI\tA!\u001e;jY&\u0011\u0001&\n\u0002\u0018\u0007>lW.\u00198e\u0019&tW\rT8hO&tw-\u0016;jYN\fa\u0001P5oSRt4\u0001\u0001\u000b\u0002YA\u0011Q\u0006A\u0007\u0002\u0019\u00051\u0011n]$m_\n$\"\u0001M\u001a\u0011\u0005i\t\u0014B\u0001\u001a\u001c\u0005\u001d\u0011un\u001c7fC:DQ\u0001\u000e\u0002A\u0002U\nAA\\1nKB\u0011a'\u0010\b\u0003om\u0002\"\u0001O\u000e\u000e\u0003eR!A\u000f\u0016\u0002\rq\u0012xn\u001c;?\u0013\ta4$\u0001\u0004Qe\u0016$WMZ\u0005\u0003}}\u0012aa\u0015;sS:<'B\u0001\u001f\u001c\u0003\u001d)\u00070Z2vi\u0016$BAQ#H\u001bB\u0011!dQ\u0005\u0003\tn\u0011A!\u00168ji\")ai\u0001a\u0001k\u0005a1/\u001e2nSN\u001c\u0018n\u001c8JI\")\u0001j\u0001a\u0001\u0013\u0006I1\u000f]1sW\u000e{gN\u001a\t\u0003\u0015.k\u0011AE\u0005\u0003\u0019J\u0011\u0011b\u00159be.\u001cuN\u001c4\t\u000b9\u001b\u0001\u0019A(\u0002\u0005=\u0004\bCA\u0017Q\u0013\t\tFBA\u0006LqM\u001cVOY7ji>\u0003\u0018\u0001B6jY2$2A\u0011+V\u0011\u00151E\u00011\u00016\u0011\u00151F\u00011\u0001J\u0003\u0011\u0019wN\u001c4\u0002+A\u0014\u0018N\u001c;Tk\nl\u0017n]:j_:\u001cF/\u0019;vgR\u0019!)\u0017.\t\u000b\u0019+\u0001\u0019A\u001b\t\u000bY+\u0001\u0019A%\u0002\u0011M,\b\u000f]8siN$\"\u0001M/\t\u000by3\u0001\u0019A\u001b\u0002\r5\f7\u000f^3s\u0003]Y\u0005hU*qCJ\\7+\u001e2nSR|\u0005/\u001a:bi&|g\u000e\u0005\u0002.\u0011M\u0011\u0001\"\u0007\u000b\u0002A\u0006qq-\u001a;He\u0006\u001cW\rU3sS>$GCA3l!\rQb\r[\u0005\u0003On\u0011aa\u00149uS>t\u0007C\u0001\u000ej\u0013\tQ7D\u0001\u0003M_:<\u0007\"\u0002%\u000b\u0001\u0004I\u0005"
)
public class K8SSparkSubmitOperation implements SparkSubmitOperation, CommandLineLoggingUtils {
   private Function1 exitFn;
   private PrintStream printStream;

   public static Option getGracePeriod(final SparkConf sparkConf) {
      return K8SSparkSubmitOperation$.MODULE$.getGracePeriod(sparkConf);
   }

   public void printMessage(final String str) {
      CommandLineLoggingUtils.printMessage$(this, str);
   }

   public void printErrorAndExit(final String str) {
      CommandLineLoggingUtils.printErrorAndExit$(this, str);
   }

   public Function1 exitFn() {
      return this.exitFn;
   }

   public void exitFn_$eq(final Function1 x$1) {
      this.exitFn = x$1;
   }

   public PrintStream printStream() {
      return this.printStream;
   }

   public void printStream_$eq(final PrintStream x$1) {
      this.printStream = x$1;
   }

   private boolean isGlob(final String name) {
      return .MODULE$.last$extension(scala.Predef..MODULE$.augmentString(name)) == '*';
   }

   public void execute(final String submissionId, final SparkConf sparkConf, final K8sSubmitOp op) {
      String master = KubernetesUtils$.MODULE$.parseMasterUrl(sparkConf.get("spark.master"));
      String[] var6 = submissionId.split(":", 2);
      if (var6 != null) {
         Object var7 = scala.Array..MODULE$.unapplySeq(var6);
         if (!scala.Array.UnapplySeqWrapper..MODULE$.isEmpty$extension(var7) && new Array.UnapplySeqWrapper(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var7)) != null && scala.Array.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var7), 1) >= 0) {
            String part1 = (String)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var7), 0);
            Seq part2 = scala.Array.UnapplySeqWrapper..MODULE$.drop$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var7), 1);
            Option namespace = (Option)(part2.isEmpty() ? scala.None..MODULE$ : new Some(part1));
            String pName = part2.isEmpty() ? part1 : (String)part2.headOption().get();
            BoxedUnit var12 = (BoxedUnit)org.apache.spark.util.Utils..MODULE$.tryWithResource(() -> SparkKubernetesClientFactory$.MODULE$.createKubernetesClient(master, namespace, Config$.MODULE$.KUBERNETES_AUTH_SUBMISSION_CONF_PREFIX(), SparkKubernetesClientFactory.ClientType$.MODULE$.Submission(), sparkConf, scala.None..MODULE$), (kubernetesClient) -> {
               $anonfun$execute$2(this, pName, namespace, op, sparkConf, kubernetesClient);
               return BoxedUnit.UNIT;
            });
            return;
         }
      }

      this.printErrorAndExit("Submission ID: {" + submissionId + "} is invalid.");
      BoxedUnit var10000 = BoxedUnit.UNIT;
   }

   public void kill(final String submissionId, final SparkConf conf) {
      this.printMessage("Submitting a request to kill submission " + submissionId + " in " + conf.get("spark.master") + ". Grace period in secs: " + K8SSparkSubmitOperation$.MODULE$.getGracePeriod(conf).getOrElse(() -> "not set."));
      this.execute(submissionId, conf, new KillApplication());
   }

   public void printSubmissionStatus(final String submissionId, final SparkConf conf) {
      this.printMessage("Submitting a request for the status of submission " + submissionId + " in " + conf.get("spark.master") + ".");
      this.execute(submissionId, conf, new ListStatus());
   }

   public boolean supports(final String master) {
      return master.startsWith("k8s://");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$execute$3(final String pName$1, final Pod pod) {
      return pod.getMetadata().getName().startsWith(.MODULE$.stripSuffix$extension(scala.Predef..MODULE$.augmentString(pName$1), "*"));
   }

   // $FF: synthetic method
   public static final void $anonfun$execute$2(final K8SSparkSubmitOperation $this, final String pName$1, final Option namespace$1, final K8sSubmitOp op$1, final SparkConf sparkConf$1, final KubernetesClient kubernetesClient) {
      if ($this.isGlob(pName$1)) {
         Object var10000;
         if (namespace$1 instanceof Some) {
            Some var10 = (Some)namespace$1;
            String ns = (String)var10.value();
            var10000 = (NonNamespaceOperation)kubernetesClient.pods().inNamespace(ns);
         } else {
            if (!scala.None..MODULE$.equals(namespace$1)) {
               throw new MatchError(namespace$1);
            }

            var10000 = kubernetesClient.pods();
         }

         NonNamespaceOperation ops = (NonNamespaceOperation)var10000;
         List pods = ((IterableOnceOps)scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(((PodList)((Listable)ops.withLabel(Constants$.MODULE$.SPARK_ROLE_LABEL(), Constants$.MODULE$.SPARK_POD_DRIVER_ROLE())).list()).getItems()).asScala().filter((pod) -> BoxesRunTime.boxToBoolean($anonfun$execute$3(pName$1, pod)))).toList();
         op$1.executeOnGlob(pods, namespace$1, sparkConf$1, kubernetesClient);
      } else {
         op$1.executeOnPod(pName$1, namespace$1, sparkConf$1, kubernetesClient);
      }
   }

   public K8SSparkSubmitOperation() {
      CommandLineLoggingUtils.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
