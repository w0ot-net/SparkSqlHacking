package org.apache.spark.deploy.k8s.features;

import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.annotation.Unstable;
import org.apache.spark.deploy.k8s.KubernetesExecutorConf;
import scala.reflect.ScalaSignature;

@Unstable
@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005E2qAA\u0002\u0011\u0002G\u0005\u0001\u0003C\u0003\u001c\u0001\u0019\u0005ADA\u0015Lk\n,'O\\3uKN,\u00050Z2vi>\u00148)^:u_64U-\u0019;ve\u0016\u001cuN\u001c4jON#X\r\u001d\u0006\u0003\t\u0015\t\u0001BZ3biV\u0014Xm\u001d\u0006\u0003\r\u001d\t1a\u001b\u001dt\u0015\tA\u0011\"\u0001\u0004eKBdw.\u001f\u0006\u0003\u0015-\tQa\u001d9be.T!\u0001D\u0007\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005q\u0011aA8sO\u000e\u00011c\u0001\u0001\u0012/A\u0011!#F\u0007\u0002')\tA#A\u0003tG\u0006d\u0017-\u0003\u0002\u0017'\t1\u0011I\\=SK\u001a\u0004\"\u0001G\r\u000e\u0003\rI!AG\u0002\u00037-+(-\u001a:oKR,7OR3biV\u0014XmQ8oM&<7\u000b^3q\u0003\u0011Ig.\u001b;\u0015\u0005u\u0001\u0003C\u0001\n\u001f\u0013\ty2C\u0001\u0003V]&$\b\"B\u0011\u0002\u0001\u0004\u0011\u0013AB2p]\u001aLw\r\u0005\u0002$I5\tQ!\u0003\u0002&\u000b\t12*\u001e2fe:,G/Z:Fq\u0016\u001cW\u000f^8s\u0007>tg\r\u000b\u0002\u0001OA\u0011\u0001fK\u0007\u0002S)\u0011!&C\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001\u0017*\u0005!)fn\u001d;bE2,\u0007F\u0001\u0001/!\tAs&\u0003\u00021S\taA)\u001a<fY>\u0004XM]!qS\u0002"
)
public interface KubernetesExecutorCustomFeatureConfigStep extends KubernetesFeatureConfigStep {
   void init(final KubernetesExecutorConf config);
}
