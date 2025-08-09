package org.apache.spark.deploy.k8s;

import io.fabric8.kubernetes.api.model.LocalObjectReferenceBuilder;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.k8s.submit.MainAppResource;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.package.;
import scala.Option;
import scala.collection.IterableOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005MhA\u0002\u0012$\u0003\u00039S\u0006\u0003\u00055\u0001\t\u0015\r\u0011\"\u00017\u0011!Y\u0004A!A!\u0002\u00139\u0004\"\u0002\u001f\u0001\t\u0003i\u0004bB!\u0001\u0005\u00045\tA\u0011\u0005\u0006\u001d\u00021\ta\u0014\u0005\u0006'\u00021\ta\u0014\u0005\u0006)\u00021\ta\u0014\u0005\u0006+\u00021\ta\u0014\u0005\u0006-\u00021\ta\u0014\u0005\u0006/\u00021\t\u0001\u0017\u0005\u0006K\u00021\tA\u001a\u0005\u0006U\u00021\tA\u0011\u0005\u0006W\u0002!\tA\u0011\u0005\u0006Y\u0002!\tA\u0011\u0005\u0006[\u0002!\tA\u0011\u0005\u0006]\u0002!\ta\u001c\u0005\u0007\u007f\u0002!\t!!\u0001\t\r\u0005%\u0001\u0001\"\u0001P\u0011\u001d\tY\u0001\u0001C\u0001\u0003\u001bAq!!\u000f\u0001\t\u0003\tY\u0004C\u0004\u0002:\u0001!\t!!\u0013\t\u000f\u0005e\u0002\u0001\"\u0001\u0002P!9\u0011q\u000b\u0001\u0005\u0002\u0005es\u0001CA0G!\u0005q%!\u0019\u0007\u000f\t\u001a\u0003\u0012A\u0014\u0002d!1A(\u0007C\u0001\u0003KBq!a\u001a\u001a\t\u0003\tI\u0007C\u0004\u0002\u0018f!\t!!'\t\u0013\u0005}\u0016$%A\u0005\u0002\u0005\u0005\u0007bBAl3\u0011\u0005\u0011\u0011\u001c\u0005\b\u00037LB\u0011AAo\u0011\u001d\t\t/\u0007C\u0001\u0003GDq!a:\u001a\t\u0003\tIO\u0001\bLk\n,'O\\3uKN\u001cuN\u001c4\u000b\u0005\u0011*\u0013aA69g*\u0011aeJ\u0001\u0007I\u0016\u0004Hn\\=\u000b\u0005!J\u0013!B:qCJ\\'B\u0001\u0016,\u0003\u0019\t\u0007/Y2iK*\tA&A\u0002pe\u001e\u001c\"\u0001\u0001\u0018\u0011\u0005=\u0012T\"\u0001\u0019\u000b\u0003E\nQa]2bY\u0006L!a\r\u0019\u0003\r\u0005s\u0017PU3g\u0003%\u0019\b/\u0019:l\u0007>tgm\u0001\u0001\u0016\u0003]\u0002\"\u0001O\u001d\u000e\u0003\u001dJ!AO\u0014\u0003\u0013M\u0003\u0018M]6D_:4\u0017AC:qCJ\\7i\u001c8gA\u00051A(\u001b8jiz\"\"A\u0010!\u0011\u0005}\u0002Q\"A\u0012\t\u000bQ\u001a\u0001\u0019A\u001c\u0002%I,7o\\;sG\u0016t\u0015-\\3Qe\u00164\u0017\u000e_\u000b\u0002\u0007B\u0011Ai\u0013\b\u0003\u000b&\u0003\"A\u0012\u0019\u000e\u0003\u001dS!\u0001S\u001b\u0002\rq\u0012xn\u001c;?\u0013\tQ\u0005'\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u00196\u0013aa\u0015;sS:<'B\u0001&1\u0003\u0019a\u0017MY3mgV\t\u0001\u000b\u0005\u0003E#\u000e\u001b\u0015B\u0001*N\u0005\ri\u0015\r]\u0001\fK:4\u0018N]8o[\u0016tG/A\u0006b]:|G/\u0019;j_:\u001c\u0018aF:fGJ,G/\u00128w\u001d\u0006lWm\u001d+p\u0017\u0016L(+\u001a4t\u0003]\u0019Xm\u0019:fi:\u000bW.Z:U_6{WO\u001c;QCRD7/A\u0004w_2,X.Z:\u0016\u0003e\u00032AW0c\u001d\tYVL\u0004\u0002G9&\t\u0011'\u0003\u0002_a\u00059\u0001/Y2lC\u001e,\u0017B\u00011b\u0005\r\u0019V-\u001d\u0006\u0003=B\u0002\"aP2\n\u0005\u0011\u001c#\u0001F&vE\u0016\u0014h.\u001a;fgZ{G.^7f'B,7-A\u0007tG\",G-\u001e7fe:\u000bW.Z\u000b\u0002OB\u0019q\u0006[\"\n\u0005%\u0004$AB(qi&|g.A\u0003baBLE-A\u0004baBt\u0015-\\3\u0002\u00139\fW.Z:qC\u000e,\u0017aD5nC\u001e,\u0007+\u001e7m!>d\u0017nY=\u0002!%l\u0017mZ3Qk2d7+Z2sKR\u001cX#\u00019\u0011\u0007i{\u0016\u000f\u0005\u0002s{6\t1O\u0003\u0002uk\u0006)Qn\u001c3fY*\u0011ao^\u0001\u0004CBL'B\u0001=z\u0003)YWOY3s]\u0016$Xm\u001d\u0006\u0003un\fqAZ1ce&\u001c\u0007HC\u0001}\u0003\tIw.\u0003\u0002\u007fg\n!Bj\\2bY>\u0013'.Z2u%\u00164WM]3oG\u0016\fQc^8sW\u0016\u0014H)Z2p[6L7o]5p]&tw-\u0006\u0002\u0002\u0004A\u0019q&!\u0002\n\u0007\u0005\u001d\u0001GA\u0004C_>dW-\u00198\u0002\u00199|G-Z*fY\u0016\u001cGo\u001c:\u0002\u0011\r|g\u000e^1j]N$B!a\u0001\u0002\u0010!9\u0011\u0011C\nA\u0002\u0005M\u0011AB2p]\u001aLw\r\r\u0003\u0002\u0016\u0005\u001d\u0002CBA\f\u0003?\t\u0019#\u0004\u0002\u0002\u001a)!\u0011\u0011CA\u000e\u0015\r\tibJ\u0001\tS:$XM\u001d8bY&!\u0011\u0011EA\r\u0005-\u0019uN\u001c4jO\u0016sGO]=\u0011\t\u0005\u0015\u0012q\u0005\u0007\u0001\t1\tI#a\u0004\u0002\u0002\u0003\u0005)\u0011AA\u0016\u0005\ryF%M\t\u0005\u0003[\t\u0019\u0004E\u00020\u0003_I1!!\r1\u0005\u001dqu\u000e\u001e5j]\u001e\u00042aLA\u001b\u0013\r\t9\u0004\r\u0002\u0004\u0003:L\u0018aA4fiV!\u0011QHA!)\u0011\ty$!\u0012\u0011\t\u0005\u0015\u0012\u0011\t\u0003\b\u0003\u0007\"\"\u0019AA\u0016\u0005\u0005!\u0006bBA\t)\u0001\u0007\u0011q\t\t\u0007\u0003/\ty\"a\u0010\u0015\u0007\r\u000bY\u0005\u0003\u0004\u0002NU\u0001\raQ\u0001\u0005G>tg\rF\u0003D\u0003#\n\u0019\u0006\u0003\u0004\u0002NY\u0001\ra\u0011\u0005\u0007\u0003+2\u0002\u0019A\"\u0002\u0019\u0011,g-Y;miZ\u000bG.^3\u0002\u0013\u001d,Go\u00149uS>tGcA4\u0002\\!1\u0011QL\fA\u0002\r\u000b1a[3z\u00039YUOY3s]\u0016$Xm]\"p]\u001a\u0004\"aP\r\u0014\u0005eqCCAA1\u0003A\u0019'/Z1uK\u0012\u0013\u0018N^3s\u0007>tg\r\u0006\b\u0002l\u0005E\u00141OA;\u0003\u000b\u000bI)a%\u0011\u0007}\ni'C\u0002\u0002p\r\u0012AcS;cKJtW\r^3t\tJLg/\u001a:D_:4\u0007\"\u0002\u001b\u001c\u0001\u00049\u0004\"\u00026\u001c\u0001\u0004\u0019\u0005bBA<7\u0001\u0007\u0011\u0011P\u0001\u0010[\u0006Lg.\u00119q%\u0016\u001cx.\u001e:dKB!\u00111PAA\u001b\t\tiHC\u0002\u0002\u0000\r\naa];c[&$\u0018\u0002BAB\u0003{\u0012q\"T1j]\u0006\u0003\bOU3t_V\u00148-\u001a\u0005\u0007\u0003\u000f[\u0002\u0019A\"\u0002\u00135\f\u0017N\\\"mCN\u001c\bbBAF7\u0001\u0007\u0011QR\u0001\bCB\u0004\u0018I]4t!\u0011y\u0013qR\"\n\u0007\u0005E\u0005GA\u0003BeJ\f\u0017\u0010\u0003\u0004\u0002\u0016n\u0001\raZ\u0001\naJ|\u00070_+tKJ\f!c\u0019:fCR,W\t_3dkR|'oQ8oMRa\u00111TAQ\u0003G\u000b9+!+\u00026B\u0019q(!(\n\u0007\u0005}5E\u0001\fLk\n,'O\\3uKN,\u00050Z2vi>\u00148i\u001c8g\u0011\u0015!D\u00041\u00018\u0011\u0019\t)\u000b\ba\u0001\u0007\u0006QQ\r_3dkR|'/\u00133\t\u000b)d\u0002\u0019A\"\t\u000f\u0005-F\u00041\u0001\u0002.\u0006IAM]5wKJ\u0004v\u000e\u001a\t\u0005_!\fy\u000bE\u0002s\u0003cK1!a-t\u0005\r\u0001v\u000e\u001a\u0005\n\u0003oc\u0002\u0013!a\u0001\u0003s\u000b\u0011C]3t_V\u00148-\u001a)s_\u001aLG.Z%e!\ry\u00131X\u0005\u0004\u0003{\u0003$aA%oi\u0006a2M]3bi\u0016,\u00050Z2vi>\u00148i\u001c8gI\u0011,g-Y;mi\u0012*TCAAbU\u0011\tI,!2,\u0005\u0005\u001d\u0007\u0003BAe\u0003'l!!a3\u000b\t\u00055\u0017qZ\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!51\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003+\fYMA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\f!cZ3u\u0017V\u0014WM\u001d8fi\u0016\u001c\u0018\t\u001d9JIR\t1)A\u000bhKR\u0014Vm]8ve\u000e,g*Y7f!J,g-\u001b=\u0015\u0007\r\u000by\u000eC\u0003l?\u0001\u00071)A\bhKR\f\u0005\u000f\u001d(b[\u0016d\u0015MY3m)\r\u0019\u0015Q\u001d\u0005\u0006W\u0002\u0002\raQ\u0001\u001cEVLG\u000eZ&vE\u0016\u0014h.\u001a;fgJ+7o\\;sG\u0016t\u0015-\\3\u0015\u000b\r\u000bY/a<\t\r\u00055\u0018\u00051\u0001D\u000311XM\u001c3pe\u0012{W.Y5o\u0011\u0019\t\t0\ta\u0001\u0007\u0006a!/Z:pkJ\u001cWMT1nK\u0002"
)
public abstract class KubernetesConf {
   private final SparkConf sparkConf;

   public static String buildKubernetesResourceName(final String vendorDomain, final String resourceName) {
      return KubernetesConf$.MODULE$.buildKubernetesResourceName(vendorDomain, resourceName);
   }

   public static String getAppNameLabel(final String appName) {
      return KubernetesConf$.MODULE$.getAppNameLabel(appName);
   }

   public static String getResourceNamePrefix(final String appName) {
      return KubernetesConf$.MODULE$.getResourceNamePrefix(appName);
   }

   public static String getKubernetesAppId() {
      return KubernetesConf$.MODULE$.getKubernetesAppId();
   }

   public static int createExecutorConf$default$5() {
      return KubernetesConf$.MODULE$.createExecutorConf$default$5();
   }

   public static KubernetesExecutorConf createExecutorConf(final SparkConf sparkConf, final String executorId, final String appId, final Option driverPod, final int resourceProfileId) {
      return KubernetesConf$.MODULE$.createExecutorConf(sparkConf, executorId, appId, driverPod, resourceProfileId);
   }

   public static KubernetesDriverConf createDriverConf(final SparkConf sparkConf, final String appId, final MainAppResource mainAppResource, final String mainClass, final String[] appArgs, final Option proxyUser) {
      return KubernetesConf$.MODULE$.createDriverConf(sparkConf, appId, mainAppResource, mainClass, appArgs, proxyUser);
   }

   public SparkConf sparkConf() {
      return this.sparkConf;
   }

   public abstract String resourceNamePrefix();

   public abstract Map labels();

   public abstract Map environment();

   public abstract Map annotations();

   public abstract Map secretEnvNamesToKeyRefs();

   public abstract Map secretNamesToMountPaths();

   public abstract Seq volumes();

   public abstract Option schedulerName();

   public abstract String appId();

   public String appName() {
      return this.get("spark.app.name", "spark");
   }

   public String namespace() {
      return (String)this.get(Config$.MODULE$.KUBERNETES_NAMESPACE());
   }

   public String imagePullPolicy() {
      return (String)this.get(Config$.MODULE$.CONTAINER_IMAGE_PULL_POLICY());
   }

   public Seq imagePullSecrets() {
      return (Seq)((IterableOps)this.sparkConf().get(Config$.MODULE$.IMAGE_PULL_SECRETS())).map((secret) -> ((LocalObjectReferenceBuilder)(new LocalObjectReferenceBuilder()).withName(secret)).build());
   }

   public boolean workerDecommissioning() {
      return BoxesRunTime.unboxToBoolean(this.sparkConf().get(.MODULE$.DECOMMISSION_ENABLED()));
   }

   public Map nodeSelector() {
      return KubernetesUtils$.MODULE$.parsePrefixedKeyValuePairs(this.sparkConf(), Config$.MODULE$.KUBERNETES_NODE_SELECTOR_PREFIX());
   }

   public boolean contains(final ConfigEntry config) {
      return this.sparkConf().contains(config);
   }

   public Object get(final ConfigEntry config) {
      return this.sparkConf().get(config);
   }

   public String get(final String conf) {
      return this.sparkConf().get(conf);
   }

   public String get(final String conf, final String defaultValue) {
      return this.sparkConf().get(conf, defaultValue);
   }

   public Option getOption(final String key) {
      return this.sparkConf().getOption(key);
   }

   public KubernetesConf(final SparkConf sparkConf) {
      this.sparkConf = sparkConf;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
