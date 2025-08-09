package org.apache.spark.deploy.k8s.features;

import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.annotation.Unstable;
import org.apache.spark.deploy.k8s.SparkPod;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@Unstable
@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005u3qAB\u0004\u0011\u0002\u0007\u0005A\u0003C\u0003\u001c\u0001\u0011\u0005A\u0004C\u0003!\u0001\u0019\u0005\u0011\u0005C\u0003)\u0001\u0011\u0005\u0011\u0006C\u00039\u0001\u0011\u0005\u0011\bC\u0003R\u0001\u0011\u0005\u0011HA\u000eLk\n,'O\\3uKN4U-\u0019;ve\u0016\u001cuN\u001c4jON#X\r\u001d\u0006\u0003\u0011%\t\u0001BZ3biV\u0014Xm\u001d\u0006\u0003\u0015-\t1a\u001b\u001dt\u0015\taQ\"\u0001\u0004eKBdw.\u001f\u0006\u0003\u001d=\tQa\u001d9be.T!\u0001E\t\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0011\u0012aA8sO\u000e\u00011C\u0001\u0001\u0016!\t1\u0012$D\u0001\u0018\u0015\u0005A\u0012!B:dC2\f\u0017B\u0001\u000e\u0018\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\"\u0012!\b\t\u0003-yI!aH\f\u0003\tUs\u0017\u000e^\u0001\rG>tg-[4ve\u0016\u0004v\u000e\u001a\u000b\u0003E\u0019\u0002\"a\t\u0013\u000e\u0003%I!!J\u0005\u0003\u0011M\u0003\u0018M]6Q_\u0012DQa\n\u0002A\u0002\t\n1\u0001]8e\u0003\u0001:W\r^!eI&$\u0018n\u001c8bYB{GmU=ti\u0016l\u0007K]8qKJ$\u0018.Z:\u0015\u0003)\u0002Ba\u000b\u001a6k9\u0011A\u0006\r\t\u0003[]i\u0011A\f\u0006\u0003_M\ta\u0001\u0010:p_Rt\u0014BA\u0019\u0018\u0003\u0019\u0001&/\u001a3fM&\u00111\u0007\u000e\u0002\u0004\u001b\u0006\u0004(BA\u0019\u0018!\tYc'\u0003\u00028i\t11\u000b\u001e:j]\u001e\f1eZ3u\u0003\u0012$\u0017\u000e^5p]\u0006d\u0007K]3Lk\n,'O\\3uKN\u0014Vm]8ve\u000e,7\u000fF\u0001;!\rY\u0004i\u0011\b\u0003yyr!!L\u001f\n\u0003aI!aP\f\u0002\u000fA\f7m[1hK&\u0011\u0011I\u0011\u0002\u0004'\u0016\f(BA \u0018!\t!u*D\u0001F\u0015\t1u)A\u0003n_\u0012,GN\u0003\u0002I\u0013\u0006\u0019\u0011\r]5\u000b\u0005)[\u0015AC6vE\u0016\u0014h.\u001a;fg*\u0011A*T\u0001\bM\u0006\u0014'/[29\u0015\u0005q\u0015AA5p\u0013\t\u0001VIA\u0006ICNlU\r^1eCR\f\u0017\u0001I4fi\u0006#G-\u001b;j_:\fGnS;cKJtW\r^3t%\u0016\u001cx.\u001e:dKND#\u0001A*\u0011\u0005Q;V\"A+\u000b\u0005Yk\u0011AC1o]>$\u0018\r^5p]&\u0011\u0001,\u0016\u0002\t+:\u001cH/\u00192mK\"\u0012\u0001A\u0017\t\u0003)nK!\u0001X+\u0003\u0019\u0011+g/\u001a7pa\u0016\u0014\u0018\t]5"
)
public interface KubernetesFeatureConfigStep {
   SparkPod configurePod(final SparkPod pod);

   // $FF: synthetic method
   static Map getAdditionalPodSystemProperties$(final KubernetesFeatureConfigStep $this) {
      return $this.getAdditionalPodSystemProperties();
   }

   default Map getAdditionalPodSystemProperties() {
      return .MODULE$.Map().empty();
   }

   // $FF: synthetic method
   static Seq getAdditionalPreKubernetesResources$(final KubernetesFeatureConfigStep $this) {
      return $this.getAdditionalPreKubernetesResources();
   }

   default Seq getAdditionalPreKubernetesResources() {
      return (Seq)scala.package..MODULE$.Seq().empty();
   }

   // $FF: synthetic method
   static Seq getAdditionalKubernetesResources$(final KubernetesFeatureConfigStep $this) {
      return $this.getAdditionalKubernetesResources();
   }

   default Seq getAdditionalKubernetesResources() {
      return (Seq)scala.package..MODULE$.Seq().empty();
   }

   static void $init$(final KubernetesFeatureConfigStep $this) {
   }
}
