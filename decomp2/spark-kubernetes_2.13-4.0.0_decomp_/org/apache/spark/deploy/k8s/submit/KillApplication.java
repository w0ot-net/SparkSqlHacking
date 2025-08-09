package org.apache.spark.deploy.k8s.submit;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.Deletable;
import io.fabric8.kubernetes.client.dsl.PodResource;
import java.io.PrintStream;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkConf;
import org.apache.spark.util.CommandLineLoggingUtils;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.Option.;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00114A\u0001B\u0003\u0005%!)Q\u0004\u0001C\u0001=!)\u0001\u0005\u0001C!C!)1\n\u0001C!\u0019\ny1*\u001b7m\u0003B\u0004H.[2bi&|gN\u0003\u0002\u0007\u000f\u000511/\u001e2nSRT!\u0001C\u0005\u0002\u0007-D4O\u0003\u0002\u000b\u0017\u00051A-\u001a9m_fT!\u0001D\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00059y\u0011AB1qC\u000eDWMC\u0001\u0011\u0003\ry'oZ\u0002\u0001'\r\u00011#\u0007\t\u0003)]i\u0011!\u0006\u0006\u0002-\u0005)1oY1mC&\u0011\u0001$\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0005iYR\"A\u0003\n\u0005q)!aC&9gN+(-\\5u\u001fB\fa\u0001P5oSRtD#A\u0010\u0011\u0005i\u0001\u0011\u0001D3yK\u000e,H/Z(o!>$G\u0003\u0002\u00124\u0001\u0016#\"a\t\u0014\u0011\u0005Q!\u0013BA\u0013\u0016\u0005\u0011)f.\u001b;\t\u000b\u001d\u0012\u00019\u0001\u0015\u0002\r\rd\u0017.\u001a8u!\tI\u0013'D\u0001+\u0015\t93F\u0003\u0002-[\u0005Q1.\u001e2fe:,G/Z:\u000b\u00059z\u0013a\u00024bEJL7\r\u000f\u0006\u0002a\u0005\u0011\u0011n\\\u0005\u0003e)\u0012\u0001cS;cKJtW\r^3t\u00072LWM\u001c;\t\u000bQ\u0012\u0001\u0019A\u001b\u0002\u000bAt\u0015-\\3\u0011\u0005YjdBA\u001c<!\tAT#D\u0001:\u0015\tQ\u0014#\u0001\u0004=e>|GOP\u0005\u0003yU\ta\u0001\u0015:fI\u00164\u0017B\u0001 @\u0005\u0019\u0019FO]5oO*\u0011A(\u0006\u0005\u0006\u0003\n\u0001\rAQ\u0001\n]\u0006lWm\u001d9bG\u0016\u00042\u0001F\"6\u0013\t!UC\u0001\u0004PaRLwN\u001c\u0005\u0006\r\n\u0001\raR\u0001\ngB\f'o[\"p]\u001a\u0004\"\u0001S%\u000e\u0003-I!AS\u0006\u0003\u0013M\u0003\u0018M]6D_:4\u0017!D3yK\u000e,H/Z(o\u000f2|'\r\u0006\u0003N\u001f\n\u001cGCA\u0012O\u0011\u001593\u0001q\u0001)\u0011\u0015\u00016\u00011\u0001R\u0003\u0011\u0001x\u000eZ:\u0011\u0007I;&L\u0004\u0002T+:\u0011\u0001\bV\u0005\u0002-%\u0011a+F\u0001\ba\u0006\u001c7.Y4f\u0013\tA\u0016L\u0001\u0003MSN$(B\u0001,\u0016!\tY\u0006-D\u0001]\u0015\tif,A\u0003n_\u0012,GN\u0003\u0002`W\u0005\u0019\u0011\r]5\n\u0005\u0005d&a\u0001)pI\")\u0011i\u0001a\u0001\u0005\")ai\u0001a\u0001\u000f\u0002"
)
public class KillApplication implements K8sSubmitOp {
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
      PodResource podToDelete = this.getPod(namespace, pName, client);
      if (.MODULE$.apply(podToDelete).isDefined()) {
         Option var7 = K8SSparkSubmitOperation$.MODULE$.getGracePeriod(sparkConf);
         if (var7 instanceof Some) {
            Some var8 = (Some)var7;
            long period = BoxesRunTime.unboxToLong(var8.value());
            ((Deletable)podToDelete.withGracePeriod(period)).delete();
            BoxedUnit var11 = BoxedUnit.UNIT;
         } else {
            podToDelete.delete();
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } else {
         this.printMessage("Application not found.");
      }
   }

   public void executeOnGlob(final List pods, final Option namespace, final SparkConf sparkConf, final KubernetesClient client) {
      if (pods.nonEmpty()) {
         pods.foreach((pod) -> {
            $anonfun$executeOnGlob$1(this, pod);
            return BoxedUnit.UNIT;
         });
         Option var6 = K8SSparkSubmitOperation$.MODULE$.getGracePeriod(sparkConf);
         if (var6 instanceof Some) {
            Some var7 = (Some)var6;
            long period = BoxesRunTime.unboxToLong(var7.value());
            ((Deletable)client.resourceList(scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(pods).asJava()).withGracePeriod(period)).delete();
            BoxedUnit var10 = BoxedUnit.UNIT;
         } else {
            client.resourceList(scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(pods).asJava()).delete();
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } else {
         this.printMessage("No applications found.");
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$executeOnGlob$1(final KillApplication $this, final Pod pod) {
      $this.printMessage("Deleting driver pod: " + pod.getMetadata().getName() + ".");
   }

   public KillApplication() {
      CommandLineLoggingUtils.$init$(this);
      K8sSubmitOp.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
