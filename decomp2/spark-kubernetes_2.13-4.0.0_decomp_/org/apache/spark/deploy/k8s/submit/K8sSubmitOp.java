package org.apache.spark.deploy.k8s.submit;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.Nameable;
import io.fabric8.kubernetes.client.dsl.PodResource;
import org.apache.spark.SparkConf;
import org.apache.spark.util.CommandLineLoggingUtils;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e4q!\u0002\u0004\u0011\u0002\u0007%2\u0003C\u0003!\u0001\u0011\u0005\u0011\u0005C\u0003&\u0001\u0019\u0005a\u0005C\u0003N\u0001\u0019\u0005a\nC\u0003h\u0001\u0011\u0005\u0001NA\u0006LqM\u001cVOY7ji>\u0003(BA\u0004\t\u0003\u0019\u0019XOY7ji*\u0011\u0011BC\u0001\u0004Wb\u001a(BA\u0006\r\u0003\u0019!W\r\u001d7ps*\u0011QBD\u0001\u0006gB\f'o\u001b\u0006\u0003\u001fA\ta!\u00199bG\",'\"A\t\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001!\"\u0004\u0005\u0002\u001615\taCC\u0001\u0018\u0003\u0015\u00198-\u00197b\u0013\tIbC\u0001\u0004B]f\u0014VM\u001a\t\u00037yi\u0011\u0001\b\u0006\u0003;1\tA!\u001e;jY&\u0011q\u0004\b\u0002\u0018\u0007>lW.\u00198e\u0019&tW\rT8hO&tw-\u0016;jYN\fa\u0001J5oSR$C#\u0001\u0012\u0011\u0005U\u0019\u0013B\u0001\u0013\u0017\u0005\u0011)f.\u001b;\u0002\u0019\u0015DXmY;uK>s\u0007k\u001c3\u0015\t\u001d*$i\u0012\u000b\u0003E!BQ!\u000b\u0002A\u0004)\naa\u00197jK:$\bCA\u00164\u001b\u0005a#BA\u0015.\u0015\tqs&\u0001\u0006lk\n,'O\\3uKNT!\u0001M\u0019\u0002\u000f\u0019\f'M]5dq)\t!'\u0001\u0002j_&\u0011A\u0007\f\u0002\u0011\u0017V\u0014WM\u001d8fi\u0016\u001c8\t\\5f]RDQA\u000e\u0002A\u0002]\nQ\u0001\u001d(b[\u0016\u0004\"\u0001O \u000f\u0005ej\u0004C\u0001\u001e\u0017\u001b\u0005Y$B\u0001\u001f\u0013\u0003\u0019a$o\\8u}%\u0011aHF\u0001\u0007!J,G-\u001a4\n\u0005\u0001\u000b%AB*ue&twM\u0003\u0002?-!)1I\u0001a\u0001\t\u0006Ia.Y7fgB\f7-\u001a\t\u0004+\u0015;\u0014B\u0001$\u0017\u0005\u0019y\u0005\u000f^5p]\")\u0001J\u0001a\u0001\u0013\u0006I1\u000f]1sW\u000e{gN\u001a\t\u0003\u0015.k\u0011\u0001D\u0005\u0003\u00192\u0011\u0011b\u00159be.\u001cuN\u001c4\u0002\u001b\u0015DXmY;uK>sw\t\\8c)\u0011y\u0015\u000b\u001a4\u0015\u0005\t\u0002\u0006\"B\u0015\u0004\u0001\bQ\u0003\"\u0002*\u0004\u0001\u0004\u0019\u0016\u0001\u00029pIN\u00042\u0001V-]\u001d\t)vK\u0004\u0002;-&\tq#\u0003\u0002Y-\u00059\u0001/Y2lC\u001e,\u0017B\u0001.\\\u0005\u0011a\u0015n\u001d;\u000b\u0005a3\u0002CA/c\u001b\u0005q&BA0a\u0003\u0015iw\u000eZ3m\u0015\t\tW&A\u0002ba&L!a\u00190\u0003\u0007A{G\rC\u0003f\u0007\u0001\u0007A)\u0001\u0002og\")\u0001j\u0001a\u0001\u0013\u00061q-\u001a;Q_\u0012$2![9s)\tQ\u0007\u000f\u0005\u0002l]6\tAN\u0003\u0002nY\u0005\u0019Am\u001d7\n\u0005=d'a\u0003)pIJ+7o\\;sG\u0016DQ!\u000b\u0003A\u0004)BQa\u0011\u0003A\u0002\u0011CQa\u001d\u0003A\u0002]\nAA\\1nK&\u001a\u0001!^<\n\u0005Y4!aD&jY2\f\u0005\u000f\u001d7jG\u0006$\u0018n\u001c8\n\u0005a4!A\u0003'jgR\u001cF/\u0019;vg\u0002"
)
public interface K8sSubmitOp extends CommandLineLoggingUtils {
   void executeOnPod(final String pName, final Option namespace, final SparkConf sparkConf, final KubernetesClient client);

   void executeOnGlob(final List pods, final Option ns, final SparkConf sparkConf, final KubernetesClient client);

   // $FF: synthetic method
   static PodResource getPod$(final K8sSubmitOp $this, final Option namespace, final String name, final KubernetesClient client) {
      return $this.getPod(namespace, name, client);
   }

   default PodResource getPod(final Option namespace, final String name, final KubernetesClient client) {
      if (namespace instanceof Some var6) {
         String ns = (String)var6.value();
         return (PodResource)((Nameable)client.pods().inNamespace(ns)).withName(name);
      } else if (.MODULE$.equals(namespace)) {
         return (PodResource)client.pods().withName(name);
      } else {
         throw new MatchError(namespace);
      }
   }

   static void $init$(final K8sSubmitOp $this) {
   }
}
