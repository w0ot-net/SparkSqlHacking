package org.apache.spark.deploy.k8s.submit;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.PodResource;
import java.io.PrintStream;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.k8s.KubernetesUtils$;
import org.apache.spark.util.CommandLineLoggingUtils;
import scala.Function1;
import scala.Option;
import scala.Option.;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u00154A\u0001B\u0003\u0005%!)Q\u0004\u0001C\u0001=!)\u0001\u0005\u0001C!C!)1\n\u0001C!\u0019\nQA*[:u'R\fG/^:\u000b\u0005\u00199\u0011AB:vE6LGO\u0003\u0002\t\u0013\u0005\u00191\u000eO:\u000b\u0005)Y\u0011A\u00023fa2|\u0017P\u0003\u0002\r\u001b\u0005)1\u000f]1sW*\u0011abD\u0001\u0007CB\f7\r[3\u000b\u0003A\t1a\u001c:h\u0007\u0001\u00192\u0001A\n\u001a!\t!r#D\u0001\u0016\u0015\u00051\u0012!B:dC2\f\u0017B\u0001\r\u0016\u0005\u0019\te.\u001f*fMB\u0011!dG\u0007\u0002\u000b%\u0011A$\u0002\u0002\f\u0017b\u001a8+\u001e2nSR|\u0005/\u0001\u0004=S:LGO\u0010\u000b\u0002?A\u0011!\u0004A\u0001\rKb,7-\u001e;f\u001f:\u0004v\u000e\u001a\u000b\u0005EM\u0002U\t\u0006\u0002$MA\u0011A\u0003J\u0005\u0003KU\u0011A!\u00168ji\")qE\u0001a\u0002Q\u000511\r\\5f]R\u0004\"!K\u0019\u000e\u0003)R!aJ\u0016\u000b\u00051j\u0013AC6vE\u0016\u0014h.\u001a;fg*\u0011afL\u0001\bM\u0006\u0014'/[29\u0015\u0005\u0001\u0014AA5p\u0013\t\u0011$F\u0001\tLk\n,'O\\3uKN\u001cE.[3oi\")AG\u0001a\u0001k\u0005)\u0001OT1nKB\u0011a'\u0010\b\u0003om\u0002\"\u0001O\u000b\u000e\u0003eR!AO\t\u0002\rq\u0012xn\u001c;?\u0013\taT#\u0001\u0004Qe\u0016$WMZ\u0005\u0003}}\u0012aa\u0015;sS:<'B\u0001\u001f\u0016\u0011\u0015\t%\u00011\u0001C\u0003%q\u0017-\\3ta\u0006\u001cW\rE\u0002\u0015\u0007VJ!\u0001R\u000b\u0003\r=\u0003H/[8o\u0011\u00151%\u00011\u0001H\u0003%\u0019\b/\u0019:l\u0007>tg\r\u0005\u0002I\u00136\t1\"\u0003\u0002K\u0017\tI1\u000b]1sW\u000e{gNZ\u0001\u000eKb,7-\u001e;f\u001f:<En\u001c2\u0015\t5{%\r\u001a\u000b\u0003G9CQaJ\u0002A\u0004!BQ\u0001U\u0002A\u0002E\u000bA\u0001]8egB\u0019!k\u0016.\u000f\u0005M+fB\u0001\u001dU\u0013\u00051\u0012B\u0001,\u0016\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001W-\u0003\t1K7\u000f\u001e\u0006\u0003-V\u0001\"a\u00171\u000e\u0003qS!!\u00180\u0002\u000b5|G-\u001a7\u000b\u0005}[\u0013aA1qS&\u0011\u0011\r\u0018\u0002\u0004!>$\u0007\"B2\u0004\u0001\u0004\u0011\u0015A\u00018t\u0011\u001515\u00011\u0001H\u0001"
)
public class ListStatus implements K8sSubmitOp {
   private Function1 exitFn;
   private PrintStream printStream;

   public PodResource getPod(final Option namespace, final String name, final KubernetesClient client) {
      return K8sSubmitOp.getPod$(this, namespace, name, client);
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

   public void executeOnPod(final String pName, final Option namespace, final SparkConf sparkConf, final KubernetesClient client) {
      Pod pod = (Pod)this.getPod(namespace, pName, client).get();
      if (.MODULE$.apply(pod).isDefined()) {
         Option var10001 = .MODULE$.apply(pod).map((podx) -> KubernetesUtils$.MODULE$.formatPodState(podx));
         this.printMessage("Application status (driver): " + var10001.getOrElse(() -> "unknown."));
      } else {
         this.printMessage("Application not found.");
      }
   }

   public void executeOnGlob(final List pods, final Option ns, final SparkConf sparkConf, final KubernetesClient client) {
      if (pods.nonEmpty()) {
         pods.foreach((pod) -> {
            $anonfun$executeOnGlob$2(this, pod);
            return BoxedUnit.UNIT;
         });
      } else {
         this.printMessage("No applications found.");
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$executeOnGlob$2(final ListStatus $this, final Pod pod) {
      Option var10001 = .MODULE$.apply(pod).map((podx) -> KubernetesUtils$.MODULE$.formatPodState(podx));
      $this.printMessage("Application status (driver): " + var10001.getOrElse(() -> "unknown."));
   }

   public ListStatus() {
      CommandLineLoggingUtils.$init$(this);
      K8sSubmitOp.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
