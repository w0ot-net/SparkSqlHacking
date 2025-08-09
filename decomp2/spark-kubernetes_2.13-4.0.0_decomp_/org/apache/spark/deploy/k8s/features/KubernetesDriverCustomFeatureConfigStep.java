package org.apache.spark.deploy.k8s.features;

import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.annotation.Unstable;
import org.apache.spark.deploy.k8s.KubernetesDriverConf;
import scala.reflect.ScalaSignature;

@Unstable
@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005E2qAA\u0002\u0011\u0002G\u0005\u0001\u0003C\u0003\u001c\u0001\u0019\u0005ADA\u0014Lk\n,'O\\3uKN$%/\u001b<fe\u000e+8\u000f^8n\r\u0016\fG/\u001e:f\u0007>tg-[4Ti\u0016\u0004(B\u0001\u0003\u0006\u0003!1W-\u0019;ve\u0016\u001c(B\u0001\u0004\b\u0003\rY\u0007h\u001d\u0006\u0003\u0011%\ta\u0001Z3qY>L(B\u0001\u0006\f\u0003\u0015\u0019\b/\u0019:l\u0015\taQ\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001d\u0005\u0019qN]4\u0004\u0001M\u0019\u0001!E\f\u0011\u0005I)R\"A\n\u000b\u0003Q\tQa]2bY\u0006L!AF\n\u0003\r\u0005s\u0017PU3g!\tA\u0012$D\u0001\u0004\u0013\tQ2AA\u000eLk\n,'O\\3uKN4U-\u0019;ve\u0016\u001cuN\u001c4jON#X\r]\u0001\u0005S:LG\u000f\u0006\u0002\u001eAA\u0011!CH\u0005\u0003?M\u0011A!\u00168ji\")\u0011%\u0001a\u0001E\u000511m\u001c8gS\u001e\u0004\"a\t\u0013\u000e\u0003\u0015I!!J\u0003\u0003)-+(-\u001a:oKR,7\u000f\u0012:jm\u0016\u00148i\u001c8gQ\t\u0001q\u0005\u0005\u0002)W5\t\u0011F\u0003\u0002+\u0013\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u00051J#\u0001C+ogR\f'\r\\3)\u0005\u0001q\u0003C\u0001\u00150\u0013\t\u0001\u0014F\u0001\u0007EKZ,Gn\u001c9fe\u0006\u0003\u0018\u000e"
)
public interface KubernetesDriverCustomFeatureConfigStep extends KubernetesFeatureConfigStep {
   void init(final KubernetesDriverConf config);
}
