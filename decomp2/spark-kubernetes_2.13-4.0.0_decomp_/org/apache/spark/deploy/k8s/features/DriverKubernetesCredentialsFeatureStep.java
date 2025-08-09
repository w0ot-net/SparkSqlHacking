package org.apache.spark.deploy.k8s.features;

import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.ContainerBuilder;
import io.fabric8.kubernetes.api.model.ContainerFluent;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.api.model.PodFluent;
import io.fabric8.kubernetes.api.model.PodSpecFluent;
import io.fabric8.kubernetes.api.model.Secret;
import io.fabric8.kubernetes.api.model.SecretBuilder;
import io.fabric8.kubernetes.api.model.SecretFluent;
import io.fabric8.kubernetes.api.model.VolumeFluent;
import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.nio.charset.StandardCharsets;
import org.apache.spark.deploy.k8s.Config$;
import org.apache.spark.deploy.k8s.Constants$;
import org.apache.spark.deploy.k8s.KubernetesConf;
import org.apache.spark.deploy.k8s.KubernetesUtils$;
import org.apache.spark.deploy.k8s.SparkPod;
import org.apache.spark.internal.config.ConfigEntry;
import org.sparkproject.guava.io.BaseEncoding;
import org.sparkproject.guava.io.Files;
import scala.MatchError;
import scala.Option;
import scala.Predef;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.IterableOnce;
import scala.collection.MapOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dd!\u0002\u0011\"\u0001\u001dj\u0003\u0002\u0003\u001d\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001e\t\u000by\u0002A\u0011A \t\u000f\t\u0003!\u0019!C\u0005\u0007\"1!\u000b\u0001Q\u0001\n\u0011Cqa\u0015\u0001C\u0002\u0013%1\t\u0003\u0004U\u0001\u0001\u0006I\u0001\u0012\u0005\b+\u0002\u0011\r\u0011\"\u0003D\u0011\u00191\u0006\u0001)A\u0005\t\"9q\u000b\u0001b\u0001\n\u0013\u0019\u0005B\u0002-\u0001A\u0003%A\tC\u0004Z\u0001\t\u0007I\u0011B\"\t\ri\u0003\u0001\u0015!\u0003E\u0011\u001dY\u0006A1A\u0005\nqCa!\u001a\u0001!\u0002\u0013i\u0006b\u00024\u0001\u0005\u0004%Ia\u0011\u0005\u0007O\u0002\u0001\u000b\u0011\u0002#\t\u000f!\u0004!\u0019!C\u0005\u0007\"1\u0011\u000e\u0001Q\u0001\n\u0011CqA\u001b\u0001C\u0002\u0013%1\t\u0003\u0004l\u0001\u0001\u0006I\u0001\u0012\u0005\bY\u0002\u0011\r\u0011\"\u0003n\u0011\u0019\t\b\u0001)A\u0005]\"9!\u000f\u0001b\u0001\n\u0013\u0019\bB\u0002;\u0001A\u0003%a\fC\u0003v\u0001\u0011\u0005c\u000fC\u0003}\u0001\u0011\u0005S\u0010C\u0004\u0002\u0004\u0001!\t%!\u0002\t\u000f\u0005U\u0002\u0001\"\u0003\u00028!9\u0011\u0011\t\u0001\u0005\n\u0005\r\u0003bBA'\u0001\u0011%\u0011q\n\u0005\b\u0003;\u0002A\u0011BA0\u0005\u0019\"%/\u001b<fe.+(-\u001a:oKR,7o\u0011:fI\u0016tG/[1mg\u001a+\u0017\r^;sKN#X\r\u001d\u0006\u0003E\r\n\u0001BZ3biV\u0014Xm\u001d\u0006\u0003I\u0015\n1a\u001b\u001dt\u0015\t1s%\u0001\u0004eKBdw.\u001f\u0006\u0003Q%\nQa\u001d9be.T!AK\u0016\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005a\u0013aA8sON\u0019\u0001A\f\u001b\u0011\u0005=\u0012T\"\u0001\u0019\u000b\u0003E\nQa]2bY\u0006L!a\r\u0019\u0003\r\u0005s\u0017PU3g!\t)d'D\u0001\"\u0013\t9\u0014EA\u000eLk\n,'O\\3uKN4U-\u0019;ve\u0016\u001cuN\u001c4jON#X\r]\u0001\u000fWV\u0014WM\u001d8fi\u0016\u001c8i\u001c8g\u0007\u0001\u0001\"a\u000f\u001f\u000e\u0003\rJ!!P\u0012\u0003\u001d-+(-\u001a:oKR,7oQ8oM\u00061A(\u001b8jiz\"\"\u0001Q!\u0011\u0005U\u0002\u0001\"\u0002\u001d\u0003\u0001\u0004Q\u0014AG7bs\n,Wj\\;oi\u0016$w*Q;uQR{7.\u001a8GS2,W#\u0001#\u0011\u0007=*u)\u0003\u0002Ga\t1q\n\u001d;j_:\u0004\"\u0001S(\u000f\u0005%k\u0005C\u0001&1\u001b\u0005Y%B\u0001':\u0003\u0019a$o\\8u}%\u0011a\nM\u0001\u0007!J,G-\u001a4\n\u0005A\u000b&AB*ue&twM\u0003\u0002Oa\u0005YR.Y=cK6{WO\u001c;fI>\u000bU\u000f\u001e5U_.,gNR5mK\u0002\n\u0011$\\1zE\u0016lu.\u001e8uK\u0012\u001cE.[3oi.+\u0017PR5mK\u0006QR.Y=cK6{WO\u001c;fI\u000ec\u0017.\u001a8u\u0017\u0016Lh)\u001b7fA\u0005QR.Y=cK6{WO\u001c;fI\u000ec\u0017.\u001a8u\u0007\u0016\u0014HOR5mK\u0006YR.Y=cK6{WO\u001c;fI\u000ec\u0017.\u001a8u\u0007\u0016\u0014HOR5mK\u0002\na#\\1zE\u0016lu.\u001e8uK\u0012\u001c\u0015mQ3si\u001aKG.Z\u0001\u0018[\u0006L(-Z'pk:$X\rZ\"b\u0007\u0016\u0014HOR5mK\u0002\nA\u0003\u001a:jm\u0016\u00148+\u001a:wS\u000e,\u0017iY2pk:$\u0018!\u00063sSZ,'oU3sm&\u001cW-Q2d_VtG\u000fI\u0001\u0011_\u0006,H\u000f\u001b+pW\u0016t')Y:fmQ*\u0012!\u0018\t\u0004_\u0015s\u0006CA0e\u001b\u0005\u0001'BA1c\u0003\u0011a\u0017M\\4\u000b\u0003\r\fAA[1wC&\u0011\u0001\u000bY\u0001\u0012_\u0006,H\u000f\u001b+pW\u0016t')Y:fmQ\u0002\u0013\u0001E2b\u0007\u0016\u0014H\u000fR1uC\n\u000b7/\u001a\u001c5\u0003E\u0019\u0017mQ3si\u0012\u000bG/\u0019\"bg\u00164D\u0007I\u0001\u0014G2LWM\u001c;LKf$\u0015\r^1CCN,g\u0007N\u0001\u0015G2LWM\u001c;LKf$\u0015\r^1CCN,g\u0007\u000e\u0011\u0002)\rd\u0017.\u001a8u\u0007\u0016\u0014H\u000fR1uC\n\u000b7/\u001a\u001c5\u0003U\u0019G.[3oi\u000e+'\u000f\u001e#bi\u0006\u0014\u0015m]37i\u0001\n\u0011c\u001d5pk2$Wj\\;oiN+7M]3u+\u0005q\u0007CA\u0018p\u0013\t\u0001\bGA\u0004C_>dW-\u00198\u0002%MDw.\u001e7e\u001b>,h\u000e^*fGJ,G\u000fI\u0001\u001cIJLg/\u001a:De\u0016$WM\u001c;jC2\u001c8+Z2sKRt\u0015-\\3\u0016\u0003y\u000bA\u0004\u001a:jm\u0016\u00148I]3eK:$\u0018.\u00197t'\u0016\u001c'/\u001a;OC6,\u0007%\u0001\u0007d_:4\u0017nZ;sKB{G\r\u0006\u0002xuB\u00111\b_\u0005\u0003s\u000e\u0012\u0001b\u00159be.\u0004v\u000e\u001a\u0005\u0006wf\u0001\ra^\u0001\u0004a>$\u0017\u0001I4fi\u0006#G-\u001b;j_:\fG\u000eU8e'f\u001cH/Z7Qe>\u0004XM\u001d;jKN$\u0012A \t\u0005\u0011~<u)C\u0002\u0002\u0002E\u00131!T1q\u0003\u0001:W\r^!eI&$\u0018n\u001c8bY.+(-\u001a:oKR,7OU3t_V\u00148-Z:\u0015\u0005\u0005\u001d\u0001CBA\u0005\u0003'\tIB\u0004\u0003\u0002\f\u0005=ab\u0001&\u0002\u000e%\t\u0011'C\u0002\u0002\u0012A\nq\u0001]1dW\u0006<W-\u0003\u0003\u0002\u0016\u0005]!aA*fc*\u0019\u0011\u0011\u0003\u0019\u0011\t\u0005m\u0011\u0011G\u0007\u0003\u0003;QA!a\b\u0002\"\u0005)Qn\u001c3fY*!\u00111EA\u0013\u0003\r\t\u0007/\u001b\u0006\u0005\u0003O\tI#\u0001\u0006lk\n,'O\\3uKNTA!a\u000b\u0002.\u00059a-\u00192sS\u000eD$BAA\u0018\u0003\tIw.\u0003\u0003\u00024\u0005u!a\u0003%bg6+G/\u00193bi\u0006\fAc]1gK\u001aKG.Z\"p]\u001a$vNQ1tKZ\"D#\u0002#\u0002:\u0005u\u0002BBA\u001e9\u0001\u0007q)\u0001\u0003d_:4\u0007BBA 9\u0001\u0007q)\u0001\u0005gS2,G+\u001f9f\u0003E\u0011Xm]8mm\u0016\u001cVm\u0019:fi\u0012\u000bG/\u0019\u000b\u0006}\u0006\u0015\u0013\u0011\n\u0005\u0007\u0003\u000fj\u0002\u0019\u0001#\u0002/U\u001cXM]*qK\u000eLg-[3e\u0007J,G-\u001a8uS\u0006d\u0007BBA&;\u0001\u0007q)\u0001\u0006tK\u000e\u0014X\r\u001e(b[\u0016\fQC]3t_24XmU3de\u0016$Hj\\2bi&|g\u000eF\u0004E\u0003#\n)&!\u0017\t\r\u0005Mc\u00041\u0001E\u0003Qiw.\u001e8uK\u0012,6/\u001a:Ta\u0016\u001c\u0017NZ5fI\"1\u0011q\u000b\u0010A\u0002\u0011\u000b\u0011D^1mk\u0016lu.\u001e8uK\u00124%o\\7Tk\nl\u0017\u000e\u001e;fe\"1\u00111\f\u0010A\u0002\u001d\u000b\u0001$\\8v]R,GmQ1o_:L7-\u00197M_\u000e\fG/[8o\u0003]\u0019'/Z1uK\u000e\u0013X\rZ3oi&\fGn]*fGJ,G\u000f\u0006\u0002\u0002bA!\u00111DA2\u0013\u0011\t)'!\b\u0003\rM+7M]3u\u0001"
)
public class DriverKubernetesCredentialsFeatureStep implements KubernetesFeatureConfigStep {
   private final KubernetesConf kubernetesConf;
   private final Option maybeMountedOAuthTokenFile;
   private final Option maybeMountedClientKeyFile;
   private final Option maybeMountedClientCertFile;
   private final Option maybeMountedCaCertFile;
   private final Option driverServiceAccount;
   private final Option oauthTokenBase64;
   private final Option caCertDataBase64;
   private final Option clientKeyDataBase64;
   private final Option clientCertDataBase64;
   private final boolean shouldMountSecret;
   private final String driverCredentialsSecretName;

   public Seq getAdditionalPreKubernetesResources() {
      return KubernetesFeatureConfigStep.getAdditionalPreKubernetesResources$(this);
   }

   private Option maybeMountedOAuthTokenFile() {
      return this.maybeMountedOAuthTokenFile;
   }

   private Option maybeMountedClientKeyFile() {
      return this.maybeMountedClientKeyFile;
   }

   private Option maybeMountedClientCertFile() {
      return this.maybeMountedClientCertFile;
   }

   private Option maybeMountedCaCertFile() {
      return this.maybeMountedCaCertFile;
   }

   private Option driverServiceAccount() {
      return this.driverServiceAccount;
   }

   private Option oauthTokenBase64() {
      return this.oauthTokenBase64;
   }

   private Option caCertDataBase64() {
      return this.caCertDataBase64;
   }

   private Option clientKeyDataBase64() {
      return this.clientKeyDataBase64;
   }

   private Option clientCertDataBase64() {
      return this.clientCertDataBase64;
   }

   private boolean shouldMountSecret() {
      return this.shouldMountSecret;
   }

   private String driverCredentialsSecretName() {
      return this.driverCredentialsSecretName;
   }

   public SparkPod configurePod(final SparkPod pod) {
      if (!this.shouldMountSecret()) {
         return pod.copy((Pod)KubernetesUtils$.MODULE$.buildPodWithServiceAccount(this.driverServiceAccount(), pod).getOrElse(() -> pod.pod()), pod.copy$default$2());
      } else {
         Pod driverPodWithMountedKubernetesCredentials = ((PodBuilder)((PodFluent.SpecNested)((PodSpecFluent.VolumesNested)((VolumeFluent.SecretNested)(new PodBuilder(pod.pod())).editOrNewSpec().addNewVolume().withName(Constants$.MODULE$.DRIVER_CREDENTIALS_SECRET_VOLUME_NAME()).withNewSecret().withSecretName(this.driverCredentialsSecretName())).endSecret()).endVolume()).endSpec()).build();
         Container driverContainerWithMountedSecretVolume = ((ContainerBuilder)((ContainerFluent.VolumeMountsNested)(new ContainerBuilder(pod.container())).addNewVolumeMount().withName(Constants$.MODULE$.DRIVER_CREDENTIALS_SECRET_VOLUME_NAME()).withMountPath(Constants$.MODULE$.DRIVER_CREDENTIALS_SECRETS_BASE_DIR())).endVolumeMount()).build();
         return new SparkPod(driverPodWithMountedKubernetesCredentials, driverContainerWithMountedSecretVolume);
      }
   }

   public Map getAdditionalPodSystemProperties() {
      Option resolvedMountedOAuthTokenFile = this.resolveSecretLocation(this.maybeMountedOAuthTokenFile(), this.oauthTokenBase64(), Constants$.MODULE$.DRIVER_CREDENTIALS_OAUTH_TOKEN_PATH());
      Option resolvedMountedClientKeyFile = this.resolveSecretLocation(this.maybeMountedClientKeyFile(), this.clientKeyDataBase64(), Constants$.MODULE$.DRIVER_CREDENTIALS_CLIENT_KEY_PATH());
      Option resolvedMountedClientCertFile = this.resolveSecretLocation(this.maybeMountedClientCertFile(), this.clientCertDataBase64(), Constants$.MODULE$.DRIVER_CREDENTIALS_CLIENT_CERT_PATH());
      Option resolvedMountedCaCertFile = this.resolveSecretLocation(this.maybeMountedCaCertFile(), this.caCertDataBase64(), Constants$.MODULE$.DRIVER_CREDENTIALS_CA_CERT_PATH());
      Map redactedTokens = (Map).MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.filter$extension(.MODULE$.refArrayOps((Object[])this.kubernetesConf.sparkConf().getAll()), (x$1) -> BoxesRunTime.boxToBoolean($anonfun$getAdditionalPodSystemProperties$1(x$1)))).toMap(scala..less.colon.less..MODULE$.refl()).map((x0$1) -> {
         if (x0$1 != null) {
            String k = (String)x0$1._1();
            return new Tuple2(k, "<present_but_redacted>");
         } else {
            throw new MatchError(x0$1);
         }
      });
      return (Map)((MapOps)((MapOps)((MapOps)redactedTokens.$plus$plus((IterableOnce)resolvedMountedCaCertFile.map((file) -> {
         Map var10000 = .MODULE$.Map();
         ScalaRunTime var10001 = scala.runtime.ScalaRunTime..MODULE$;
         Tuple2[] var10002 = new Tuple2[1];
         Predef.ArrowAssoc var10005 = scala.Predef.ArrowAssoc..MODULE$;
         Predef var10006 = .MODULE$;
         String var10007 = Config$.MODULE$.KUBERNETES_AUTH_DRIVER_MOUNTED_CONF_PREFIX();
         var10002[0] = var10005.$minus$greater$extension(var10006.ArrowAssoc(var10007 + "." + Config$.MODULE$.CA_CERT_FILE_CONF_SUFFIX()), file);
         return (Map)var10000.apply(var10001.wrapRefArray((Object[])var10002));
      }).getOrElse(() -> .MODULE$.Map().empty()))).$plus$plus((IterableOnce)resolvedMountedClientKeyFile.map((file) -> {
         Map var10000 = .MODULE$.Map();
         ScalaRunTime var10001 = scala.runtime.ScalaRunTime..MODULE$;
         Tuple2[] var10002 = new Tuple2[1];
         Predef.ArrowAssoc var10005 = scala.Predef.ArrowAssoc..MODULE$;
         Predef var10006 = .MODULE$;
         String var10007 = Config$.MODULE$.KUBERNETES_AUTH_DRIVER_MOUNTED_CONF_PREFIX();
         var10002[0] = var10005.$minus$greater$extension(var10006.ArrowAssoc(var10007 + "." + Config$.MODULE$.CLIENT_KEY_FILE_CONF_SUFFIX()), file);
         return (Map)var10000.apply(var10001.wrapRefArray((Object[])var10002));
      }).getOrElse(() -> .MODULE$.Map().empty()))).$plus$plus((IterableOnce)resolvedMountedClientCertFile.map((file) -> {
         Map var10000 = .MODULE$.Map();
         ScalaRunTime var10001 = scala.runtime.ScalaRunTime..MODULE$;
         Tuple2[] var10002 = new Tuple2[1];
         Predef.ArrowAssoc var10005 = scala.Predef.ArrowAssoc..MODULE$;
         Predef var10006 = .MODULE$;
         String var10007 = Config$.MODULE$.KUBERNETES_AUTH_DRIVER_MOUNTED_CONF_PREFIX();
         var10002[0] = var10005.$minus$greater$extension(var10006.ArrowAssoc(var10007 + "." + Config$.MODULE$.CLIENT_CERT_FILE_CONF_SUFFIX()), file);
         return (Map)var10000.apply(var10001.wrapRefArray((Object[])var10002));
      }).getOrElse(() -> .MODULE$.Map().empty()))).$plus$plus((IterableOnce)resolvedMountedOAuthTokenFile.map((file) -> {
         Map var10000 = .MODULE$.Map();
         ScalaRunTime var10001 = scala.runtime.ScalaRunTime..MODULE$;
         Tuple2[] var10002 = new Tuple2[1];
         Predef.ArrowAssoc var10005 = scala.Predef.ArrowAssoc..MODULE$;
         Predef var10006 = .MODULE$;
         String var10007 = Config$.MODULE$.KUBERNETES_AUTH_DRIVER_MOUNTED_CONF_PREFIX();
         var10002[0] = var10005.$minus$greater$extension(var10006.ArrowAssoc(var10007 + "." + Config$.MODULE$.OAUTH_TOKEN_FILE_CONF_SUFFIX()), file);
         return (Map)var10000.apply(var10001.wrapRefArray((Object[])var10002));
      }).getOrElse(() -> .MODULE$.Map().empty()));
   }

   public Seq getAdditionalKubernetesResources() {
      return (Seq)(this.shouldMountSecret() ? new scala.collection.immutable..colon.colon(this.createCredentialsSecret(), scala.collection.immutable.Nil..MODULE$) : (Seq)scala.package..MODULE$.Seq().empty());
   }

   private Option safeFileConfToBase64(final String conf, final String fileType) {
      return this.kubernetesConf.getOption(conf).map((x$2) -> new File(x$2)).map((file) -> {
         .MODULE$.require(file.isFile(), () -> String.format("%s provided at %s does not exist or is not a file.", fileType, file.getAbsolutePath()));
         return BaseEncoding.base64().encode(Files.toByteArray(file));
      });
   }

   private Map resolveSecretData(final Option userSpecifiedCredential, final String secretName) {
      return (Map)userSpecifiedCredential.map((valueBase64) -> (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(secretName), valueBase64)})))).getOrElse(() -> .MODULE$.Map().empty());
   }

   private Option resolveSecretLocation(final Option mountedUserSpecified, final Option valueMountedFromSubmitter, final String mountedCanonicalLocation) {
      return mountedUserSpecified.orElse(() -> valueMountedFromSubmitter.map((x$3) -> mountedCanonicalLocation));
   }

   private Secret createCredentialsSecret() {
      Map allSecretData = (Map)((MapOps)((MapOps)this.resolveSecretData(this.clientKeyDataBase64(), Constants$.MODULE$.DRIVER_CREDENTIALS_CLIENT_KEY_SECRET_NAME()).$plus$plus(this.resolveSecretData(this.clientCertDataBase64(), Constants$.MODULE$.DRIVER_CREDENTIALS_CLIENT_CERT_SECRET_NAME()))).$plus$plus(this.resolveSecretData(this.caCertDataBase64(), Constants$.MODULE$.DRIVER_CREDENTIALS_CA_CERT_SECRET_NAME()))).$plus$plus(this.resolveSecretData(this.oauthTokenBase64(), Constants$.MODULE$.DRIVER_CREDENTIALS_OAUTH_TOKEN_SECRET_NAME()));
      return ((SecretBuilder)((SecretFluent)((SecretFluent.MetadataNested)(new SecretBuilder()).withNewMetadata().withName(this.driverCredentialsSecretName())).endMetadata()).withImmutable(.MODULE$.boolean2Boolean(true)).withData(scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(allSecretData).asJava())).build();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getAdditionalPodSystemProperties$1(final Tuple2 x$1) {
      return ((String)x$1._1()).endsWith(Config$.MODULE$.OAUTH_TOKEN_CONF_SUFFIX());
   }

   public DriverKubernetesCredentialsFeatureStep(final KubernetesConf kubernetesConf) {
      this.kubernetesConf = kubernetesConf;
      KubernetesFeatureConfigStep.$init$(this);
      String var10002 = Config$.MODULE$.KUBERNETES_AUTH_DRIVER_MOUNTED_CONF_PREFIX();
      this.maybeMountedOAuthTokenFile = kubernetesConf.getOption(var10002 + "." + Config$.MODULE$.OAUTH_TOKEN_FILE_CONF_SUFFIX());
      var10002 = Config$.MODULE$.KUBERNETES_AUTH_DRIVER_MOUNTED_CONF_PREFIX();
      this.maybeMountedClientKeyFile = kubernetesConf.getOption(var10002 + "." + Config$.MODULE$.CLIENT_KEY_FILE_CONF_SUFFIX());
      var10002 = Config$.MODULE$.KUBERNETES_AUTH_DRIVER_MOUNTED_CONF_PREFIX();
      this.maybeMountedClientCertFile = kubernetesConf.getOption(var10002 + "." + Config$.MODULE$.CLIENT_CERT_FILE_CONF_SUFFIX());
      var10002 = Config$.MODULE$.KUBERNETES_AUTH_DRIVER_MOUNTED_CONF_PREFIX();
      this.maybeMountedCaCertFile = kubernetesConf.getOption(var10002 + "." + Config$.MODULE$.CA_CERT_FILE_CONF_SUFFIX());
      this.driverServiceAccount = (Option)kubernetesConf.get((ConfigEntry)Config$.MODULE$.KUBERNETES_DRIVER_SERVICE_ACCOUNT_NAME());
      var10002 = Config$.MODULE$.KUBERNETES_AUTH_DRIVER_CONF_PREFIX();
      this.oauthTokenBase64 = kubernetesConf.getOption(var10002 + "." + Config$.MODULE$.OAUTH_TOKEN_CONF_SUFFIX()).map((token) -> BaseEncoding.base64().encode(token.getBytes(StandardCharsets.UTF_8)));
      var10002 = Config$.MODULE$.KUBERNETES_AUTH_DRIVER_CONF_PREFIX();
      this.caCertDataBase64 = this.safeFileConfToBase64(var10002 + "." + Config$.MODULE$.CA_CERT_FILE_CONF_SUFFIX(), "Driver CA cert file");
      var10002 = Config$.MODULE$.KUBERNETES_AUTH_DRIVER_CONF_PREFIX();
      this.clientKeyDataBase64 = this.safeFileConfToBase64(var10002 + "." + Config$.MODULE$.CLIENT_KEY_FILE_CONF_SUFFIX(), "Driver client key file");
      var10002 = Config$.MODULE$.KUBERNETES_AUTH_DRIVER_CONF_PREFIX();
      this.clientCertDataBase64 = this.safeFileConfToBase64(var10002 + "." + Config$.MODULE$.CLIENT_CERT_FILE_CONF_SUFFIX(), "Driver client cert file");
      this.shouldMountSecret = this.oauthTokenBase64().isDefined() || this.caCertDataBase64().isDefined() || this.clientKeyDataBase64().isDefined() || this.clientCertDataBase64().isDefined();
      this.driverCredentialsSecretName = kubernetesConf.resourceNamePrefix() + "-kubernetes-credentials";
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
