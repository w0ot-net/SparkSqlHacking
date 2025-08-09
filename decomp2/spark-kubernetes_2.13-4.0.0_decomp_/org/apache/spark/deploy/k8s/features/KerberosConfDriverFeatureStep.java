package org.apache.spark.deploy.k8s.features;

import io.fabric8.kubernetes.api.model.ConfigMapBuilder;
import io.fabric8.kubernetes.api.model.ConfigMapFluent;
import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.ContainerBuilder;
import io.fabric8.kubernetes.api.model.ContainerFluent;
import io.fabric8.kubernetes.api.model.EnvVarFluent;
import io.fabric8.kubernetes.api.model.KeyToPath;
import io.fabric8.kubernetes.api.model.KeyToPathBuilder;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.api.model.PodFluent;
import io.fabric8.kubernetes.api.model.PodSpecFluent;
import io.fabric8.kubernetes.api.model.SecretBuilder;
import io.fabric8.kubernetes.api.model.SecretFluent;
import io.fabric8.kubernetes.api.model.Volume;
import io.fabric8.kubernetes.api.model.VolumeBuilder;
import io.fabric8.kubernetes.api.model.VolumeFluent;
import java.io.File;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.apache.hadoop.security.Credentials;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.spark.deploy.SparkHadoopUtil.;
import org.apache.spark.deploy.k8s.Config$;
import org.apache.spark.deploy.k8s.Constants$;
import org.apache.spark.deploy.k8s.KubernetesDriverConf;
import org.apache.spark.deploy.k8s.KubernetesUtils$;
import org.apache.spark.deploy.k8s.SparkPod;
import org.apache.spark.deploy.security.HadoopDelegationTokenManager;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.rpc.RpcEndpointRef;
import org.slf4j.Logger;
import org.sparkproject.guava.io.Files;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.Predef;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.IterableOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015b!\u0002\r\u001a\u0001})\u0003\u0002\u0003\u001c\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001d\t\u000bq\u0002A\u0011A\u001f\t\u000f\u0001\u0003!\u0019!C\u0005\u0003\"1\u0001\u000b\u0001Q\u0001\n\tCq!\u0015\u0001C\u0002\u0013%\u0011\t\u0003\u0004S\u0001\u0001\u0006IA\u0011\u0005\b'\u0002\u0011\r\u0011\"\u0003B\u0011\u0019!\u0006\u0001)A\u0005\u0005\"9Q\u000b\u0001b\u0001\n\u0013\t\u0005B\u0002,\u0001A\u0003%!\tC\u0004X\u0001\t\u0007I\u0011B!\t\ra\u0003\u0001\u0015!\u0003C\u0011\u001dI\u0006A1A\u0005\n\u0005CaA\u0017\u0001!\u0002\u0013\u0011\u0005\u0002C.\u0001\u0011\u000b\u0007I\u0011\u0002/\t\u000b\r\u0004A\u0011\u00023\t\u000b!\u0004A\u0011B5\t\u000b)\u0004A\u0011B5\t\u000b-\u0004A\u0011\u00023\t\u000b1\u0004A\u0011B5\t\u000b5\u0004A\u0011\t8\t\u000bQ\u0004A\u0011I;\t\u000be\u0004A\u0011\t>\u0003;-+'OY3s_N\u001cuN\u001c4Ee&4XM\u001d$fCR,(/Z*uKBT!AG\u000e\u0002\u0011\u0019,\u0017\r^;sKNT!\u0001H\u000f\u0002\u0007-D4O\u0003\u0002\u001f?\u00051A-\u001a9m_fT!\u0001I\u0011\u0002\u000bM\u0004\u0018M]6\u000b\u0005\t\u001a\u0013AB1qC\u000eDWMC\u0001%\u0003\ry'oZ\n\u0005\u0001\u0019b\u0003\u0007\u0005\u0002(U5\t\u0001FC\u0001*\u0003\u0015\u00198-\u00197b\u0013\tY\u0003F\u0001\u0004B]f\u0014VM\u001a\t\u0003[9j\u0011!G\u0005\u0003_e\u00111dS;cKJtW\r^3t\r\u0016\fG/\u001e:f\u0007>tg-[4Ti\u0016\u0004\bCA\u00195\u001b\u0005\u0011$BA\u001a \u0003!Ig\u000e^3s]\u0006d\u0017BA\u001b3\u0005\u001daunZ4j]\u001e\fab[;cKJtW\r^3t\u0007>tgm\u0001\u0001\u0011\u0005eRT\"A\u000e\n\u0005mZ\"\u0001F&vE\u0016\u0014h.\u001a;fg\u0012\u0013\u0018N^3s\u0007>tg-\u0001\u0004=S:LGO\u0010\u000b\u0003}}\u0002\"!\f\u0001\t\u000bY\u0012\u0001\u0019\u0001\u001d\u0002\u0013A\u0014\u0018N\\2ja\u0006dW#\u0001\"\u0011\u0007\u001d\u001aU)\u0003\u0002EQ\t1q\n\u001d;j_:\u0004\"AR'\u000f\u0005\u001d[\u0005C\u0001%)\u001b\u0005I%B\u0001&8\u0003\u0019a$o\\8u}%\u0011A\nK\u0001\u0007!J,G-\u001a4\n\u00059{%AB*ue&twM\u0003\u0002MQ\u0005Q\u0001O]5oG&\u0004\u0018\r\u001c\u0011\u0002\r-,\u0017\u0010^1c\u0003\u001dYW-\u001f;bE\u0002\n!#\u001a=jgRLgnZ*fGJ,GOT1nK\u0006\u0019R\r_5ti&twmU3de\u0016$h*Y7fA\u0005)R\r_5ti&twmU3de\u0016$\u0018\n^3n\u0017\u0016L\u0018AF3ySN$\u0018N\\4TK\u000e\u0014X\r^%uK6\\U-\u001f\u0011\u0002\u0011-\u0014(-\u000e$jY\u0016\f\u0011b\u001b:ck\u0019KG.\u001a\u0011\u0002\u0011-\u0014(-N\"NCB\f\u0011b\u001b:ck\rk\u0015\r\u001d\u0011\u0002!\u0011,G.Z4bi&|g\u000eV8lK:\u001cX#A/\u0011\u0007\u001dr\u0006-\u0003\u0002`Q\t)\u0011I\u001d:bsB\u0011q%Y\u0005\u0003E\"\u0012AAQ=uK\u0006\u0001b.Z3e\u0017\u0016LH/\u00192Va2|\u0017\rZ\u000b\u0002KB\u0011qEZ\u0005\u0003O\"\u0012qAQ8pY\u0016\fg.\u0001\u0007eiN+7M]3u\u001d\u0006lW-F\u0001F\u00031YGoU3de\u0016$h*Y7f\u0003=A\u0017m]&fe\n,'o\\:D_:4\u0017\u0001\u00058fo\u000e{gNZ5h\u001b\u0006\u0004h*Y7f\u00031\u0019wN\u001c4jOV\u0014X\rU8e)\ty'\u000f\u0005\u0002:a&\u0011\u0011o\u0007\u0002\t'B\f'o\u001b)pI\")1/\u0006a\u0001_\u0006AqN]5hS:\fG.\u0001\u0011hKR\fE\rZ5uS>t\u0017\r\u001c)pINK8\u000f^3n!J|\u0007/\u001a:uS\u0016\u001cH#\u0001<\u0011\t\u0019;X)R\u0005\u0003q>\u00131!T1q\u0003\u0001:W\r^!eI&$\u0018n\u001c8bY.+(-\u001a:oKR,7OU3t_V\u00148-Z:\u0015\u0003m\u0004R\u0001`A\u0002\u0003\u0013q!!`@\u000f\u0005!s\u0018\"A\u0015\n\u0007\u0005\u0005\u0001&A\u0004qC\u000e\\\u0017mZ3\n\t\u0005\u0015\u0011q\u0001\u0002\u0004'\u0016\f(bAA\u0001QA!\u00111BA\u0011\u001b\t\tiA\u0003\u0003\u0002\u0010\u0005E\u0011!B7pI\u0016d'\u0002BA\n\u0003+\t1!\u00199j\u0015\u0011\t9\"!\u0007\u0002\u0015-,(-\u001a:oKR,7O\u0003\u0003\u0002\u001c\u0005u\u0011a\u00024bEJL7\r\u000f\u0006\u0003\u0003?\t!![8\n\t\u0005\r\u0012Q\u0002\u0002\f\u0011\u0006\u001cX*\u001a;bI\u0006$\u0018\r"
)
public class KerberosConfDriverFeatureStep implements KubernetesFeatureConfigStep, Logging {
   private byte[] org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$delegationTokens;
   private final KubernetesDriverConf kubernetesConf;
   private final Option principal;
   private final Option keytab;
   private final Option org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$existingSecretName;
   private final Option org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$existingSecretItemKey;
   private final Option org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$krb5File;
   private final Option org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$krb5CMap;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private volatile boolean bitmap$0;

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Seq getAdditionalPreKubernetesResources() {
      return KubernetesFeatureConfigStep.getAdditionalPreKubernetesResources$(this);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private Option principal() {
      return this.principal;
   }

   private Option keytab() {
      return this.keytab;
   }

   public Option org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$existingSecretName() {
      return this.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$existingSecretName;
   }

   public Option org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$existingSecretItemKey() {
      return this.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$existingSecretItemKey;
   }

   public Option org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$krb5File() {
      return this.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$krb5File;
   }

   public Option org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$krb5CMap() {
      return this.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$krb5CMap;
   }

   private byte[] delegationTokens$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            byte[] var10001;
            if (this.keytab().isEmpty() && this.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$existingSecretName().isEmpty()) {
               HadoopDelegationTokenManager tokenManager = new HadoopDelegationTokenManager(this.kubernetesConf.sparkConf(), .MODULE$.get().newConfiguration(this.kubernetesConf.sparkConf()), (RpcEndpointRef)null);
               var10001 = this.liftedTree1$1(tokenManager);
            } else {
               var10001 = null;
            }

            this.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$delegationTokens = var10001;
            this.bitmap$0 = true;
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return this.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$delegationTokens;
   }

   public byte[] org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$delegationTokens() {
      return !this.bitmap$0 ? this.delegationTokens$lzycompute() : this.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$delegationTokens;
   }

   public boolean org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$needKeytabUpload() {
      return this.keytab().exists((x$1) -> BoxesRunTime.boxToBoolean($anonfun$needKeytabUpload$1(x$1)));
   }

   public String org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$dtSecretName() {
      return this.kubernetesConf.resourceNamePrefix() + "-delegation-tokens";
   }

   public String org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$ktSecretName() {
      return this.kubernetesConf.resourceNamePrefix() + "-kerberos-keytab";
   }

   public boolean org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$hasKerberosConf() {
      return this.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$krb5CMap().isDefined() | this.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$krb5File().isDefined();
   }

   public String org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$newConfigMapName() {
      return this.kubernetesConf.resourceNamePrefix() + "-krb5-file";
   }

   public SparkPod configurePod(final SparkPod original) {
      return original.transform(new Serializable() {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final KerberosConfDriverFeatureStep $outer;

         public final Object applyOrElse(final SparkPod x1, final Function1 default) {
            if (this.$outer.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$hasKerberosConf()) {
               Volume var10000;
               if (this.$outer.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$krb5CMap().isDefined()) {
                  var10000 = ((VolumeBuilder)((VolumeFluent.ConfigMapNested)(new VolumeBuilder()).withName(Constants$.MODULE$.KRB_FILE_VOLUME()).withNewConfigMap().withName((String)this.$outer.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$krb5CMap().get())).endConfigMap()).build();
               } else {
                  File krb5Conf = new File((String)this.$outer.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$krb5File().get());
                  var10000 = ((VolumeBuilder)((VolumeFluent.ConfigMapNested)(new VolumeBuilder()).withName(Constants$.MODULE$.KRB_FILE_VOLUME()).withNewConfigMap().withName(this.$outer.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$newConfigMapName()).withItems(new KeyToPath[]{((KeyToPathBuilder)(new KeyToPathBuilder()).withKey(krb5Conf.getName()).withPath(krb5Conf.getName())).build()})).endConfigMap()).build();
               }

               Volume configMapVolume = var10000;
               Pod podWithVolume = ((PodBuilder)((PodFluent.SpecNested)(new PodBuilder(x1.pod())).editSpec().addNewVolumeLike(configMapVolume).endVolume()).endSpec()).build();
               Container containerWithMount = ((ContainerBuilder)((ContainerFluent.VolumeMountsNested)(new ContainerBuilder(x1.container())).addNewVolumeMount().withName(Constants$.MODULE$.KRB_FILE_VOLUME()).withMountPath(Constants$.MODULE$.KRB_FILE_DIR_PATH() + "/krb5.conf").withSubPath("krb5.conf")).endVolumeMount()).build();
               return new SparkPod(podWithVolume, containerWithMount);
            } else {
               return default.apply(x1);
            }
         }

         public final boolean isDefinedAt(final SparkPod x1) {
            return this.$outer.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$hasKerberosConf();
         }

         public {
            if (KerberosConfDriverFeatureStep.this == null) {
               throw null;
            } else {
               this.$outer = KerberosConfDriverFeatureStep.this;
            }
         }
      }).transform(new Serializable() {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final KerberosConfDriverFeatureStep $outer;

         public final Object applyOrElse(final SparkPod x2, final Function1 default) {
            if (this.$outer.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$needKeytabUpload()) {
               Pod podWitKeytab = ((PodBuilder)((PodFluent.SpecNested)((PodSpecFluent.VolumesNested)((VolumeFluent.SecretNested)(new PodBuilder(x2.pod())).editOrNewSpec().addNewVolume().withName(Constants$.MODULE$.KERBEROS_KEYTAB_VOLUME()).withNewSecret().withSecretName(this.$outer.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$ktSecretName())).endSecret()).endVolume()).endSpec()).build();
               Container containerWithKeytab = ((ContainerBuilder)((ContainerFluent.VolumeMountsNested)(new ContainerBuilder(x2.container())).addNewVolumeMount().withName(Constants$.MODULE$.KERBEROS_KEYTAB_VOLUME()).withMountPath(Constants$.MODULE$.KERBEROS_KEYTAB_MOUNT_POINT())).endVolumeMount()).build();
               return new SparkPod(podWitKeytab, containerWithKeytab);
            } else if (this.$outer.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$existingSecretName().isDefined() | this.$outer.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$delegationTokens() != null) {
               String secretName = (String)this.$outer.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$existingSecretName().getOrElse(() -> this.$outer.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$dtSecretName());
               String itemKey = (String)this.$outer.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$existingSecretItemKey().getOrElse(() -> Constants$.MODULE$.KERBEROS_SECRET_KEY());
               Pod podWithTokens = ((PodBuilder)((PodFluent.SpecNested)((PodSpecFluent.VolumesNested)((VolumeFluent.SecretNested)(new PodBuilder(x2.pod())).editOrNewSpec().addNewVolume().withName(Constants$.MODULE$.SPARK_APP_HADOOP_SECRET_VOLUME_NAME()).withNewSecret().withSecretName(secretName)).endSecret()).endVolume()).endSpec()).build();
               EnvVarFluent var10000 = ((ContainerFluent)((ContainerFluent.VolumeMountsNested)(new ContainerBuilder(x2.container())).addNewVolumeMount().withName(Constants$.MODULE$.SPARK_APP_HADOOP_SECRET_VOLUME_NAME()).withMountPath(Constants$.MODULE$.SPARK_APP_HADOOP_CREDENTIALS_BASE_DIR())).endVolumeMount()).addNewEnv().withName(Constants$.MODULE$.ENV_HADOOP_TOKEN_FILE_LOCATION());
               String var10001 = Constants$.MODULE$.SPARK_APP_HADOOP_CREDENTIALS_BASE_DIR();
               Container containerWithTokens = ((ContainerBuilder)((ContainerFluent.EnvNested)var10000.withValue(var10001 + "/" + itemKey)).endEnv()).build();
               return new SparkPod(podWithTokens, containerWithTokens);
            } else {
               return default.apply(x2);
            }
         }

         public final boolean isDefinedAt(final SparkPod x2) {
            if (this.$outer.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$needKeytabUpload()) {
               return true;
            } else {
               return this.$outer.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$existingSecretName().isDefined() | this.$outer.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$delegationTokens() != null;
            }
         }

         public {
            if (KerberosConfDriverFeatureStep.this == null) {
               throw null;
            } else {
               this.$outer = KerberosConfDriverFeatureStep.this;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
   }

   public scala.collection.immutable.Map getAdditionalPodSystemProperties() {
      if (this.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$needKeytabUpload()) {
         String ktName = (new File((String)this.keytab().get())).getName();
         scala.collection.immutable.Map var10000 = scala.Predef..MODULE$.Map();
         ScalaRunTime var10001 = scala.runtime.ScalaRunTime..MODULE$;
         Tuple2[] var10002 = new Tuple2[1];
         Predef.ArrowAssoc var10005 = scala.Predef.ArrowAssoc..MODULE$;
         Object var10006 = scala.Predef..MODULE$.ArrowAssoc(org.apache.spark.internal.config.package..MODULE$.KEYTAB().key());
         String var10007 = Constants$.MODULE$.KERBEROS_KEYTAB_MOUNT_POINT();
         var10002[0] = var10005.$minus$greater$extension(var10006, var10007 + "/" + ktName);
         return (scala.collection.immutable.Map)var10000.apply(var10001.wrapRefArray((Object[])var10002));
      } else {
         return scala.Predef..MODULE$.Map().empty();
      }
   }

   public Seq getAdditionalKubernetesResources() {
      IterableOps var10000 = (IterableOps)scala.collection.immutable.Nil..MODULE$.$plus$plus(this.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$krb5File().map((path) -> {
         File file = new File(path);
         return ((ConfigMapBuilder)((ConfigMapFluent)((ConfigMapFluent.MetadataNested)(new ConfigMapBuilder()).withNewMetadata().withName(this.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$newConfigMapName())).endMetadata()).withImmutable(scala.Predef..MODULE$.boolean2Boolean(true)).addToData(scala.jdk.CollectionConverters..MODULE$.MapHasAsJava((scala.collection.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(file.getName()), Files.asCharSource(file, StandardCharsets.UTF_8).read())})))).asJava())).build();
      }));
      Object var10001;
      if (this.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$needKeytabUpload()) {
         File kt = new File((String)this.keytab().get());
         var10001 = new scala.collection.immutable..colon.colon(((SecretBuilder)((SecretFluent)((SecretFluent.MetadataNested)(new SecretBuilder()).withNewMetadata().withName(this.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$ktSecretName())).endMetadata()).withImmutable(scala.Predef..MODULE$.boolean2Boolean(true)).addToData(kt.getName(), Base64.encodeBase64String(Files.toByteArray(kt)))).build(), scala.collection.immutable.Nil..MODULE$);
      } else {
         var10001 = scala.collection.immutable.Nil..MODULE$;
      }

      return (Seq)((IterableOps)var10000.$plus$plus((IterableOnce)var10001)).$plus$plus((IterableOnce)(this.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$delegationTokens() != null ? new scala.collection.immutable..colon.colon(((SecretBuilder)((SecretFluent)((SecretFluent.MetadataNested)(new SecretBuilder()).withNewMetadata().withName(this.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$dtSecretName())).endMetadata()).withImmutable(scala.Predef..MODULE$.boolean2Boolean(true)).addToData(Constants$.MODULE$.KERBEROS_SECRET_KEY(), Base64.encodeBase64String(this.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$delegationTokens()))).build(), scala.collection.immutable.Nil..MODULE$) : scala.collection.immutable.Nil..MODULE$));
   }

   // $FF: synthetic method
   private final byte[] liftedTree1$1(final HadoopDelegationTokenManager tokenManager$1) {
      byte[] var10000;
      try {
         Credentials creds = UserGroupInformation.getCurrentUser().getCredentials();
         tokenManager$1.obtainDelegationTokens(creds);
         var10000 = creds.numberOfTokens() <= 0 && creds.numberOfSecretKeys() <= 0 ? null : .MODULE$.get().serialize(creds);
      } catch (Throwable var7) {
         if (var7 == null || !scala.util.control.NonFatal..MODULE$.apply(var7)) {
            throw var7;
         }

         this.logWarning((Function0)(() -> "Fail to get credentials"), var7);
         var10000 = null;
      }

      return var10000;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$needKeytabUpload$1(final String x$1) {
      return !org.apache.spark.util.Utils..MODULE$.isLocalUri(x$1);
   }

   public KerberosConfDriverFeatureStep(final KubernetesDriverConf kubernetesConf) {
      this.kubernetesConf = kubernetesConf;
      KubernetesFeatureConfigStep.$init$(this);
      Logging.$init$(this);
      this.principal = (Option)kubernetesConf.get(org.apache.spark.internal.config.package..MODULE$.PRINCIPAL());
      this.keytab = (Option)kubernetesConf.get(org.apache.spark.internal.config.package..MODULE$.KEYTAB());
      this.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$existingSecretName = (Option)kubernetesConf.get(Config$.MODULE$.KUBERNETES_KERBEROS_DT_SECRET_NAME());
      this.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$existingSecretItemKey = (Option)kubernetesConf.get(Config$.MODULE$.KUBERNETES_KERBEROS_DT_SECRET_ITEM_KEY());
      this.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$krb5File = (Option)kubernetesConf.get(Config$.MODULE$.KUBERNETES_KERBEROS_KRB5_FILE());
      this.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$krb5CMap = (Option)kubernetesConf.get(Config$.MODULE$.KUBERNETES_KERBEROS_KRB5_CONFIG_MAP());
      KubernetesUtils$.MODULE$.requireNandDefined(this.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$krb5File(), this.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$krb5CMap(), "Do not specify both a Krb5 local file and the ConfigMap as the creation of an additional ConfigMap, when one is already specified, is extraneous");
      KubernetesUtils$.MODULE$.requireBothOrNeitherDefined(this.keytab(), this.principal(), "If a Kerberos principal is specified you must also specify a Kerberos keytab", "If a Kerberos keytab is specified you must also specify a Kerberos principal");
      KubernetesUtils$.MODULE$.requireBothOrNeitherDefined(this.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$existingSecretName(), this.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$existingSecretItemKey(), "If a secret data item-key where the data of the Kerberos Delegation Token is specified you must also specify the name of the secret", "If a secret storing a Kerberos Delegation Token is specified you must also specify the item-key where the data is stored");
      if (!this.org$apache$spark$deploy$k8s$features$KerberosConfDriverFeatureStep$$hasKerberosConf()) {
         this.logInfo((Function0)(() -> "You have not specified a krb5.conf file locally or via a ConfigMap. Make sure that you have the krb5.conf locally on the driver image."));
      }

   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
