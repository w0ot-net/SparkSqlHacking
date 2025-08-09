package org.apache.spark.metrics.sink;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.json.MetricsModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import java.lang.invoke.SerializedLambda;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.spark.SparkConf;
import org.apache.spark.ui.JettyUtils;
import org.apache.spark.ui.JettyUtils$;
import org.sparkproject.jetty.servlet.ServletContextHandler;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}b!B\f\u0019\u0001q\u0011\u0003\u0002C\u0017\u0001\u0005\u000b\u0007I\u0011A\u0018\t\u0011a\u0002!\u0011!Q\u0001\nAB\u0001\"\u000f\u0001\u0003\u0006\u0004%\tA\u000f\u0005\t\t\u0002\u0011\t\u0011)A\u0005w!)Q\t\u0001C\u0001\r\"9!\n\u0001b\u0001\n\u0003Y\u0005B\u0002*\u0001A\u0003%A\nC\u0004T\u0001\t\u0007I\u0011A&\t\rQ\u0003\u0001\u0015!\u0003M\u0011\u001d)\u0006A1A\u0005\u0002YCaA\u0017\u0001!\u0002\u00139\u0006bB.\u0001\u0005\u0004%\ta\u0013\u0005\u00079\u0002\u0001\u000b\u0011\u0002'\t\u000fu\u0003!\u0019!C\u0001-\"1a\f\u0001Q\u0001\n]Cqa\u0018\u0001C\u0002\u0013\u0005\u0001\r\u0003\u0004l\u0001\u0001\u0006I!\u0019\u0005\u0006Y\u0002!\t!\u001c\u0005\b\u0003\u0007\u0001A\u0011AA\u0003\u0011\u001d\t\t\u0004\u0001C!\u0003gAq!a\u000f\u0001\t\u0003\n\u0019\u0004C\u0004\u0002>\u0001!\t%a\r\u0003\u001d5+GO]5dgN+'O\u001e7fi*\u0011\u0011DG\u0001\u0005g&t7N\u0003\u0002\u001c9\u00059Q.\u001a;sS\u000e\u001c(BA\u000f\u001f\u0003\u0015\u0019\b/\u0019:l\u0015\ty\u0002%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002C\u0005\u0019qN]4\u0014\u0007\u0001\u0019\u0013\u0006\u0005\u0002%O5\tQEC\u0001'\u0003\u0015\u00198-\u00197b\u0013\tASE\u0001\u0004B]f\u0014VM\u001a\t\u0003U-j\u0011\u0001G\u0005\u0003Ya\u0011AaU5oW\u0006A\u0001O]8qKJ$\u0018p\u0001\u0001\u0016\u0003A\u0002\"!\r\u001c\u000e\u0003IR!a\r\u001b\u0002\tU$\u0018\u000e\u001c\u0006\u0002k\u0005!!.\u0019<b\u0013\t9$G\u0001\u0006Qe>\u0004XM\u001d;jKN\f\u0011\u0002\u001d:pa\u0016\u0014H/\u001f\u0011\u0002\u0011I,w-[:uef,\u0012a\u000f\t\u0003y\tk\u0011!\u0010\u0006\u00037yR!a\u0010!\u0002\u0011\r|G-\u00195bY\u0016T\u0011!Q\u0001\u0004G>l\u0017BA\">\u00059iU\r\u001e:jGJ+w-[:uef\f\u0011B]3hSN$(/\u001f\u0011\u0002\rqJg.\u001b;?)\r9\u0005*\u0013\t\u0003U\u0001AQ!L\u0003A\u0002ABQ!O\u0003A\u0002m\n\u0001cU#S-2+EkX&F3~\u0003\u0016\t\u0016%\u0016\u00031\u0003\"!\u0014)\u000e\u00039S!a\u0014\u001b\u0002\t1\fgnZ\u0005\u0003#:\u0013aa\u0015;sS:<\u0017!E*F%ZcU\tV0L\u000bf{\u0006+\u0011+IA\u0005\u00112+\u0012*W\u0019\u0016#vlS#Z?N\u000bU\n\u0015'F\u0003M\u0019VI\u0015,M\u000bR{6*R-`'\u0006k\u0005\u000bT#!\u0003Y\u0019VI\u0015,M\u000bR{F)\u0012$B+2#vlU!N!2+U#A,\u0011\u0005\u0011B\u0016BA-&\u0005\u001d\u0011un\u001c7fC:\fqcU#S-2+Ek\u0018#F\r\u0006+F\nV0T\u00036\u0003F*\u0012\u0011\u0002\u0017M,'O\u001e7fiB\u000bG\u000f[\u0001\rg\u0016\u0014h\u000f\\3u!\u0006$\b\u000eI\u0001\u0012g\u0016\u0014h\u000f\\3u'\"|woU1na2,\u0017AE:feZdW\r^*i_^\u001c\u0016-\u001c9mK\u0002\na!\\1qa\u0016\u0014X#A1\u0011\u0005\tLW\"A2\u000b\u0005\u0011,\u0017\u0001\u00033bi\u0006\u0014\u0017N\u001c3\u000b\u0005\u0019<\u0017a\u00026bG.\u001cxN\u001c\u0006\u0003Q\u0002\u000b\u0011BZ1ti\u0016\u0014\b0\u001c7\n\u0005)\u001c'\u0001D(cU\u0016\u001cG/T1qa\u0016\u0014\u0018aB7baB,'\u000fI\u0001\fO\u0016$\b*\u00198eY\u0016\u00148\u000f\u0006\u0002owB\u0019Ae\\9\n\u0005A,#!B!se\u0006L\bC\u0001:z\u001b\u0005\u0019(B\u0001;v\u0003\u001d\u0019XM\u001d<mKRT!A^<\u0002\u000b),G\u000f^=\u000b\u0005a\u0004\u0013aB3dY&\u00048/Z\u0005\u0003uN\u0014QcU3sm2,GoQ8oi\u0016DH\u000fS1oI2,'\u000fC\u0003}%\u0001\u0007Q0\u0001\u0003d_:4\u0007C\u0001@\u0000\u001b\u0005a\u0012bAA\u00019\tI1\u000b]1sW\u000e{gNZ\u0001\u0013O\u0016$X*\u001a;sS\u000e\u001c8K\\1qg\"|G\u000f\u0006\u0003\u0002\b\u0005m\u0001\u0003BA\u0005\u0003/qA!a\u0003\u0002\u0014A\u0019\u0011QB\u0013\u000e\u0005\u0005=!bAA\t]\u00051AH]8pizJ1!!\u0006&\u0003\u0019\u0001&/\u001a3fM&\u0019\u0011+!\u0007\u000b\u0007\u0005UQ\u0005C\u0004\u0002\u001eM\u0001\r!a\b\u0002\u000fI,\u0017/^3tiB!\u0011\u0011EA\u0017\u001b\t\t\u0019C\u0003\u0003\u0002&\u0005\u001d\u0012\u0001\u00025uiBT1\u0001^A\u0015\u0015\t\tY#A\u0004kC.\f'\u000f^1\n\t\u0005=\u00121\u0005\u0002\u0013\u0011R$\boU3sm2,GOU3rk\u0016\u001cH/A\u0003ti\u0006\u0014H\u000f\u0006\u0002\u00026A\u0019A%a\u000e\n\u0007\u0005eRE\u0001\u0003V]&$\u0018\u0001B:u_B\faA]3q_J$\b"
)
public class MetricsServlet implements Sink {
   private final Properties property;
   private final MetricRegistry registry;
   private final String SERVLET_KEY_PATH;
   private final String SERVLET_KEY_SAMPLE;
   private final boolean SERVLET_DEFAULT_SAMPLE;
   private final String servletPath;
   private final boolean servletShowSample;
   private final ObjectMapper mapper;

   public Properties property() {
      return this.property;
   }

   public MetricRegistry registry() {
      return this.registry;
   }

   public String SERVLET_KEY_PATH() {
      return this.SERVLET_KEY_PATH;
   }

   public String SERVLET_KEY_SAMPLE() {
      return this.SERVLET_KEY_SAMPLE;
   }

   public boolean SERVLET_DEFAULT_SAMPLE() {
      return this.SERVLET_DEFAULT_SAMPLE;
   }

   public String servletPath() {
      return this.servletPath;
   }

   public boolean servletShowSample() {
      return this.servletShowSample;
   }

   public ObjectMapper mapper() {
      return this.mapper;
   }

   public ServletContextHandler[] getHandlers(final SparkConf conf) {
      return (ServletContextHandler[])(new ServletContextHandler[]{JettyUtils$.MODULE$.createServletHandler(this.servletPath(), new JettyUtils.ServletParams((request) -> this.getMetricsSnapshot(request), "text/json", JettyUtils.ServletParams$.MODULE$.$lessinit$greater$default$3()), conf, JettyUtils$.MODULE$.createServletHandler$default$4())});
   }

   public String getMetricsSnapshot(final HttpServletRequest request) {
      return this.mapper().writeValueAsString(this.registry());
   }

   public void start() {
   }

   public void stop() {
   }

   public void report() {
   }

   // $FF: synthetic method
   public static final boolean $anonfun$servletShowSample$1(final String x$1) {
      return .MODULE$.toBoolean$extension(scala.Predef..MODULE$.augmentString(x$1));
   }

   public MetricsServlet(final Properties property, final MetricRegistry registry) {
      this.property = property;
      this.registry = registry;
      this.SERVLET_KEY_PATH = "path";
      this.SERVLET_KEY_SAMPLE = "sample";
      this.SERVLET_DEFAULT_SAMPLE = false;
      this.servletPath = property.getProperty(this.SERVLET_KEY_PATH());
      this.servletShowSample = BoxesRunTime.unboxToBoolean(scala.Option..MODULE$.apply(property.getProperty(this.SERVLET_KEY_SAMPLE())).map((x$1) -> BoxesRunTime.boxToBoolean($anonfun$servletShowSample$1(x$1))).getOrElse((JFunction0.mcZ.sp)() -> this.SERVLET_DEFAULT_SAMPLE()));
      this.mapper = (new ObjectMapper()).registerModule(new MetricsModule(TimeUnit.SECONDS, TimeUnit.MILLISECONDS, this.servletShowSample()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
