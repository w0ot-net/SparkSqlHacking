package org.apache.spark.deploy.k8s.features;

import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.api.model.ServiceBuilder;
import io.fabric8.kubernetes.api.model.ServiceFluent;
import io.fabric8.kubernetes.api.model.ServiceSpecFluent;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import java.util.Map;
import org.apache.spark.deploy.k8s.Config$;
import org.apache.spark.deploy.k8s.Constants$;
import org.apache.spark.deploy.k8s.KubernetesDriverConf;
import org.apache.spark.deploy.k8s.SparkPod;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rc!B\u000f\u001f\u0001\u0011R\u0003\u0002C\u001e\u0001\u0005\u0003\u0005\u000b\u0011B\u001f\t\u000b\u0005\u0003A\u0011\u0001\"\t\u000f\u0015\u0003!\u0019!C\u0005\r\"1!\u000b\u0001Q\u0001\n\u001dCqa\u0015\u0001C\u0002\u0013%a\t\u0003\u0004U\u0001\u0001\u0006Ia\u0012\u0005\b+\u0002\u0011\r\u0011\"\u0003W\u0011\u0019!\u0007\u0001)A\u0005/\"9Q\r\u0001b\u0001\n\u00131\u0007B\u00026\u0001A\u0003%q\rC\u0004l\u0001\t\u0007I\u0011\u00024\t\r1\u0004\u0001\u0015!\u0003h\u0011\u001di\u0007A1A\u0005\n\u0019DaA\u001c\u0001!\u0002\u00139\u0007\"B8\u0001\t\u0003\u0002\b\"\u0002<\u0001\t\u0003:\b\"B>\u0001\t\u0003bx\u0001CA\u0015=!\u0005A%a\u000b\u0007\u000fuq\u0002\u0012\u0001\u0013\u0002.!1\u0011i\u0005C\u0001\u0003_A\u0001\"!\r\u0014\u0005\u0004%\tA\u0012\u0005\b\u0003g\u0019\u0002\u0015!\u0003H\u0011!\t)d\u0005b\u0001\n\u00031\u0005bBA\u001c'\u0001\u0006Ia\u0012\u0005\n\u0003s\u0019\"\u0019!C\u0001\u0003wAq!!\u0010\u0014A\u0003%q\f\u0003\u0005\u0002@M\u0011\r\u0011\"\u0001g\u0011\u001d\t\te\u0005Q\u0001\n\u001d\u0014\u0001\u0004\u0012:jm\u0016\u00148+\u001a:wS\u000e,g)Z1ukJ,7\u000b^3q\u0015\ty\u0002%\u0001\u0005gK\u0006$XO]3t\u0015\t\t#%A\u0002lqMT!a\t\u0013\u0002\r\u0011,\u0007\u000f\\8z\u0015\t)c%A\u0003ta\u0006\u00148N\u0003\u0002(Q\u00051\u0011\r]1dQ\u0016T\u0011!K\u0001\u0004_J<7\u0003\u0002\u0001,cU\u0002\"\u0001L\u0018\u000e\u00035R\u0011AL\u0001\u0006g\u000e\fG.Y\u0005\u0003a5\u0012a!\u00118z%\u00164\u0007C\u0001\u001a4\u001b\u0005q\u0012B\u0001\u001b\u001f\u0005mYUOY3s]\u0016$Xm\u001d$fCR,(/Z\"p]\u001aLwm\u0015;faB\u0011a'O\u0007\u0002o)\u0011\u0001\bJ\u0001\tS:$XM\u001d8bY&\u0011!h\u000e\u0002\b\u0019><w-\u001b8h\u00039YWOY3s]\u0016$Xm]\"p]\u001a\u001c\u0001\u0001\u0005\u0002?\u007f5\t\u0001%\u0003\u0002AA\t!2*\u001e2fe:,G/Z:Ee&4XM]\"p]\u001a\fa\u0001P5oSRtDCA\"E!\t\u0011\u0004\u0001C\u0003<\u0005\u0001\u0007Q(A\nsKN|GN^3e'\u0016\u0014h/[2f\u001d\u0006lW-F\u0001H!\tAuJ\u0004\u0002J\u001bB\u0011!*L\u0007\u0002\u0017*\u0011A\nP\u0001\u0007yI|w\u000e\u001e \n\u00059k\u0013A\u0002)sK\u0012,g-\u0003\u0002Q#\n11\u000b\u001e:j]\u001eT!AT\u0017\u0002)I,7o\u001c7wK\u0012\u001cVM\u001d<jG\u0016t\u0015-\\3!\u00039I\u0007OR1nS2L\bk\u001c7jGf\fq\"\u001b9GC6LG.\u001f)pY&\u001c\u0017\u0010I\u0001\u000bSB4\u0015-\\5mS\u0016\u001cX#A,\u0011\u0007akv,D\u0001Z\u0015\tQ6,\u0001\u0003vi&d'\"\u0001/\u0002\t)\fg/Y\u0005\u0003=f\u0013A\u0001T5tiB\u0011\u0001mY\u0007\u0002C*\u0011!mW\u0001\u0005Y\u0006tw-\u0003\u0002QC\u0006Y\u0011\u000e\u001d$b[&d\u0017.Z:!\u0003)!'/\u001b<feB{'\u000f^\u000b\u0002OB\u0011A\u0006[\u0005\u0003S6\u00121!\u00138u\u0003-!'/\u001b<feB{'\u000f\u001e\u0011\u0002-\u0011\u0014\u0018N^3s\u00052|7m['b]\u0006<WM\u001d)peR\fq\u0003\u001a:jm\u0016\u0014(\t\\8dW6\u000bg.Y4feB{'\u000f\u001e\u0011\u0002\u0019\u0011\u0014\u0018N^3s+&\u0003vN\u001d;\u0002\u001b\u0011\u0014\u0018N^3s+&\u0003vN\u001d;!\u00031\u0019wN\u001c4jOV\u0014X\rU8e)\t\tH\u000f\u0005\u0002?e&\u00111\u000f\t\u0002\t'B\f'o\u001b)pI\")Qo\u0004a\u0001c\u0006\u0019\u0001o\u001c3\u0002A\u001d,G/\u00113eSRLwN\\1m!>$7+_:uK6\u0004&o\u001c9feRLWm\u001d\u000b\u0002qB!\u0001*_$H\u0013\tQ\u0018KA\u0002NCB\f\u0001eZ3u\u0003\u0012$\u0017\u000e^5p]\u0006d7*\u001e2fe:,G/Z:SKN|WO]2fgR\tQ\u0010E\u0003\u007f\u0003\u000f\tiAD\u0002\u0000\u0003\u0007q1ASA\u0001\u0013\u0005q\u0013bAA\u0003[\u00059\u0001/Y2lC\u001e,\u0017\u0002BA\u0005\u0003\u0017\u00111aU3r\u0015\r\t)!\f\t\u0005\u0003\u001f\t)#\u0004\u0002\u0002\u0012)!\u00111CA\u000b\u0003\u0015iw\u000eZ3m\u0015\u0011\t9\"!\u0007\u0002\u0007\u0005\u0004\u0018N\u0003\u0003\u0002\u001c\u0005u\u0011AC6vE\u0016\u0014h.\u001a;fg*!\u0011qDA\u0011\u0003\u001d1\u0017M\u0019:jGbR!!a\t\u0002\u0005%|\u0017\u0002BA\u0014\u0003#\u00111\u0002S1t\u001b\u0016$\u0018\rZ1uC\u0006ABI]5wKJ\u001cVM\u001d<jG\u00164U-\u0019;ve\u0016\u001cF/\u001a9\u0011\u0005I\u001a2CA\n,)\t\tY#A\fE%&3VIU0C\u0013:#u,\u0011#E%\u0016\u001b6kX&F3\u0006ABIU%W\u000bJ{&)\u0013(E?\u0006#EIU#T'~[U)\u0017\u0011\u0002\u001f\u0011\u0013\u0016JV#S?\"{5\u000bV0L\u000bf\u000b\u0001\u0003\u0012*J-\u0016\u0013v\fS(T)~[U)\u0017\u0011\u0002%\u0011\u0013\u0016JV#S?N36i\u0018)P'R3\u0015\nW\u000b\u0002?\u0006\u0019BIU%W\u000bJ{6KV\"`!>\u001bFKR%YA\u00059R*\u0011-`'\u0016\u0013f+S\"F?:\u000bU*R0M\u000b:;E\u000bS\u0001\u0019\u001b\u0006CvlU#S-&\u001bUi\u0018(B\u001b\u0016{F*\u0012(H)\"\u0003\u0003"
)
public class DriverServiceFeatureStep implements KubernetesFeatureConfigStep, Logging {
   private final KubernetesDriverConf kubernetesConf;
   private final String resolvedServiceName;
   private final String ipFamilyPolicy;
   private final List ipFamilies;
   private final int driverPort;
   private final int driverBlockManagerPort;
   private final int driverUIPort;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static int MAX_SERVICE_NAME_LENGTH() {
      return DriverServiceFeatureStep$.MODULE$.MAX_SERVICE_NAME_LENGTH();
   }

   public static String DRIVER_SVC_POSTFIX() {
      return DriverServiceFeatureStep$.MODULE$.DRIVER_SVC_POSTFIX();
   }

   public static String DRIVER_HOST_KEY() {
      return DriverServiceFeatureStep$.MODULE$.DRIVER_HOST_KEY();
   }

   public static String DRIVER_BIND_ADDRESS_KEY() {
      return DriverServiceFeatureStep$.MODULE$.DRIVER_BIND_ADDRESS_KEY();
   }

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

   private String resolvedServiceName() {
      return this.resolvedServiceName;
   }

   private String ipFamilyPolicy() {
      return this.ipFamilyPolicy;
   }

   private List ipFamilies() {
      return this.ipFamilies;
   }

   private int driverPort() {
      return this.driverPort;
   }

   private int driverBlockManagerPort() {
      return this.driverBlockManagerPort;
   }

   private int driverUIPort() {
      return this.driverUIPort;
   }

   public SparkPod configurePod(final SparkPod pod) {
      return pod;
   }

   public scala.collection.immutable.Map getAdditionalPodSystemProperties() {
      String var10000 = this.resolvedServiceName();
      String driverHostname = var10000 + "." + this.kubernetesConf.namespace() + ".svc";
      return (scala.collection.immutable.Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(DriverServiceFeatureStep$.MODULE$.DRIVER_HOST_KEY()), driverHostname), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(org.apache.spark.internal.config.package..MODULE$.DRIVER_PORT().key()), Integer.toString(this.driverPort())), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(org.apache.spark.internal.config.package..MODULE$.DRIVER_BLOCK_MANAGER_PORT().key()), Integer.toString(this.driverBlockManagerPort()))})));
   }

   public Seq getAdditionalKubernetesResources() {
      Service driverService = ((ServiceBuilder)((ServiceFluent.SpecNested)((ServiceSpecFluent.PortsNested)((ServiceSpecFluent)((ServiceSpecFluent.PortsNested)((ServiceSpecFluent)((ServiceSpecFluent.PortsNested)((ServiceFluent)((ServiceFluent.MetadataNested)(new ServiceBuilder()).withNewMetadata().withName(this.resolvedServiceName()).addToAnnotations(scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(this.kubernetesConf.serviceAnnotations()).asJava()).addToLabels(Constants$.MODULE$.SPARK_APP_ID_LABEL(), this.kubernetesConf.appId()).addToLabels(scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(this.kubernetesConf.serviceLabels()).asJava())).endMetadata()).withNewSpec().withClusterIP("None").withIpFamilyPolicy(this.ipFamilyPolicy()).withIpFamilies(this.ipFamilies()).withSelector(scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(this.kubernetesConf.labels()).asJava()).addNewPort().withName(Constants$.MODULE$.DRIVER_PORT_NAME()).withPort(.MODULE$.int2Integer(this.driverPort())).withNewTargetPort(BoxesRunTime.boxToInteger(this.driverPort()))).endPort()).addNewPort().withName(Constants$.MODULE$.BLOCK_MANAGER_PORT_NAME()).withPort(.MODULE$.int2Integer(this.driverBlockManagerPort())).withNewTargetPort(BoxesRunTime.boxToInteger(this.driverBlockManagerPort()))).endPort()).addNewPort().withName(Constants$.MODULE$.UI_PORT_NAME()).withPort(.MODULE$.int2Integer(this.driverUIPort())).withNewTargetPort(BoxesRunTime.boxToInteger(this.driverUIPort()))).endPort()).endSpec()).build();
      return new scala.collection.immutable..colon.colon(driverService, scala.collection.immutable.Nil..MODULE$);
   }

   public DriverServiceFeatureStep(final KubernetesDriverConf kubernetesConf) {
      this.kubernetesConf = kubernetesConf;
      KubernetesFeatureConfigStep.$init$(this);
      Logging.$init$(this);
      .MODULE$.require(kubernetesConf.getOption(DriverServiceFeatureStep$.MODULE$.DRIVER_BIND_ADDRESS_KEY()).isEmpty(), () -> DriverServiceFeatureStep$.MODULE$.DRIVER_BIND_ADDRESS_KEY() + " is not supported in Kubernetes mode, as the driver's bind address is managed and set to the driver pod's IP address.");
      .MODULE$.require(kubernetesConf.getOption(DriverServiceFeatureStep$.MODULE$.DRIVER_HOST_KEY()).isEmpty(), () -> DriverServiceFeatureStep$.MODULE$.DRIVER_HOST_KEY() + " is not supported in Kubernetes mode, as the driver's hostname will be managed via a Kubernetes service.");
      this.resolvedServiceName = kubernetesConf.driverServiceName();
      this.ipFamilyPolicy = (String)kubernetesConf.sparkConf().get(Config$.MODULE$.KUBERNETES_DRIVER_SERVICE_IP_FAMILY_POLICY());
      this.ipFamilies = scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(.MODULE$.wrapRefArray((Object[])((String)kubernetesConf.sparkConf().get(Config$.MODULE$.KUBERNETES_DRIVER_SERVICE_IP_FAMILIES())).split(",")).toList()).asJava();
      this.driverPort = kubernetesConf.sparkConf().getInt(org.apache.spark.internal.config.package..MODULE$.DRIVER_PORT().key(), Constants$.MODULE$.DEFAULT_DRIVER_PORT());
      this.driverBlockManagerPort = kubernetesConf.sparkConf().getInt(org.apache.spark.internal.config.package..MODULE$.DRIVER_BLOCK_MANAGER_PORT().key(), Constants$.MODULE$.DEFAULT_BLOCKMANAGER_PORT());
      this.driverUIPort = BoxesRunTime.unboxToInt(kubernetesConf.get(org.apache.spark.internal.config.UI..MODULE$.UI_PORT()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
