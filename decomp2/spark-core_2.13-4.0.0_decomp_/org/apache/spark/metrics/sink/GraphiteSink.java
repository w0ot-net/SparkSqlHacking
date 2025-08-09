package org.apache.spark.metrics.sink;

import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;
import com.codahale.metrics.graphite.GraphiteSender;
import com.codahale.metrics.graphite.GraphiteUDP;
import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.spark.errors.SparkCoreErrors$;
import org.apache.spark.metrics.MetricsSystem$;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Option.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%e!\u0002\u00180\u0001MJ\u0004\u0002\u0003#\u0001\u0005\u000b\u0007I\u0011\u0001$\t\u0011=\u0003!\u0011!Q\u0001\n\u001dC\u0001\u0002\u0015\u0001\u0003\u0006\u0004%\t!\u0015\u0005\t7\u0002\u0011\t\u0011)A\u0005%\")A\f\u0001C\u0001;\"9\u0011\r\u0001b\u0001\n\u0003\u0011\u0007B\u00024\u0001A\u0003%1\rC\u0004h\u0001\t\u0007I\u0011\u00015\t\r=\u0004\u0001\u0015!\u0003j\u0011\u001d\u0001\bA1A\u0005\u0002!Da!\u001d\u0001!\u0002\u0013I\u0007b\u0002:\u0001\u0005\u0004%\t\u0001\u001b\u0005\u0007g\u0002\u0001\u000b\u0011B5\t\u000fQ\u0004!\u0019!C\u0001Q\"1Q\u000f\u0001Q\u0001\n%DqA\u001e\u0001C\u0002\u0013\u0005\u0001\u000e\u0003\u0004x\u0001\u0001\u0006I!\u001b\u0005\bq\u0002\u0011\r\u0011\"\u0001i\u0011\u0019I\b\u0001)A\u0005S\"9!\u0010\u0001b\u0001\n\u0003A\u0007BB>\u0001A\u0003%\u0011\u000eC\u0004}\u0001\t\u0007I\u0011\u00015\t\ru\u0004\u0001\u0015!\u0003j\u0011\u001dq\bA1A\u0005\u0002!Daa \u0001!\u0002\u0013I\u0007bBA\u0001\u0001\u0011\u0005\u00111\u0001\u0005\n\u0003G\u0001!\u0019!C\u0001\u0003KA\u0001\"a\n\u0001A\u0003%\u00111\u0002\u0005\t\u0003S\u0001!\u0019!C\u0001E\"9\u00111\u0006\u0001!\u0002\u0013\u0019\u0007\u0002CA\u0017\u0001\t\u0007I\u0011\u00012\t\u000f\u0005=\u0002\u0001)A\u0005G\"I\u0011\u0011\u0007\u0001C\u0002\u0013\u0005\u00111\u0007\u0005\t\u0003\u0003\u0002\u0001\u0015!\u0003\u00026!I\u00111\t\u0001C\u0002\u0013\u0005\u0011Q\u0005\u0005\t\u0003\u000b\u0002\u0001\u0015!\u0003\u0002\f!I\u0011q\t\u0001C\u0002\u0013\u0005\u0011\u0011\n\u0005\t\u0003+\u0002\u0001\u0015!\u0003\u0002L!I\u0011q\u000b\u0001C\u0002\u0013\u0005\u0011\u0011\f\u0005\t\u0003C\u0002\u0001\u0015!\u0003\u0002\\!I\u0011q\u000e\u0001C\u0002\u0013\u0005\u0011\u0011\u000f\u0005\t\u0003s\u0002\u0001\u0015!\u0003\u0002t!9\u00111\u0010\u0001\u0005B\u0005u\u0004bBAC\u0001\u0011\u0005\u0013Q\u0010\u0005\b\u0003\u000f\u0003A\u0011IA?\u000519%/\u00199iSR,7+\u001b8l\u0015\t\u0001\u0014'\u0001\u0003tS:\\'B\u0001\u001a4\u0003\u001diW\r\u001e:jGNT!\u0001N\u001b\u0002\u000bM\u0004\u0018M]6\u000b\u0005Y:\u0014AB1qC\u000eDWMC\u00019\u0003\ry'oZ\n\u0004\u0001i\u0002\u0005CA\u001e?\u001b\u0005a$\"A\u001f\u0002\u000bM\u001c\u0017\r\\1\n\u0005}b$AB!osJ+g\r\u0005\u0002B\u00056\tq&\u0003\u0002D_\t!1+\u001b8l\u0003!\u0001(o\u001c9feRL8\u0001A\u000b\u0002\u000fB\u0011\u0001*T\u0007\u0002\u0013*\u0011!jS\u0001\u0005kRLGNC\u0001M\u0003\u0011Q\u0017M^1\n\u00059K%A\u0003)s_B,'\u000f^5fg\u0006I\u0001O]8qKJ$\u0018\u0010I\u0001\te\u0016<\u0017n\u001d;ssV\t!\u000b\u0005\u0002T36\tAK\u0003\u00023+*\u0011akV\u0001\tG>$\u0017\r[1mK*\t\u0001,A\u0002d_6L!A\u0017+\u0003\u001d5+GO]5d%\u0016<\u0017n\u001d;ss\u0006I!/Z4jgR\u0014\u0018\u0010I\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007y{\u0006\r\u0005\u0002B\u0001!)A)\u0002a\u0001\u000f\")\u0001+\u0002a\u0001%\u00069rIU!Q\u0011&#Vi\u0018#F\r\u0006+F\nV0Q\u000bJKu\nR\u000b\u0002GB\u00111\bZ\u0005\u0003Kr\u00121!\u00138u\u0003a9%+\u0011)I\u0013R+u\fR#G\u0003VcEk\u0018)F%&{E\tI\u0001\u0016\u000fJ\u000b\u0005\u000bS%U\u000b~#UIR!V\u0019R{VKT%U+\u0005I\u0007C\u00016n\u001b\u0005Y'B\u00017L\u0003\u0011a\u0017M\\4\n\u00059\\'AB*ue&tw-\u0001\fH%\u0006\u0003\u0006*\u0013+F?\u0012+e)Q+M)~+f*\u0013+!\u0003]9%+\u0011)I\u0013R+u\fR#G\u0003VcEk\u0018)S\u000b\u001aK\u0005,\u0001\rH%\u0006\u0003\u0006*\u0013+F?\u0012+e)Q+M)~\u0003&+\u0012$J1\u0002\n\u0011c\u0012*B!\"KE+R0L\u000bf{\u0006jT*U\u0003I9%+\u0011)I\u0013R+ulS#Z?\"{5\u000b\u0016\u0011\u0002#\u001d\u0013\u0016\t\u0015%J)\u0016{6*R-`!>\u0013F+\u0001\nH%\u0006\u0003\u0006*\u0013+F?.+\u0015l\u0018)P%R\u0003\u0013aE$S\u0003BC\u0015\nV#`\u0017\u0016Kv\fU#S\u0013>#\u0015\u0001F$S\u0003BC\u0015\nV#`\u0017\u0016Kv\fU#S\u0013>#\u0005%A\tH%\u0006\u0003\u0006*\u0013+F?.+\u0015lX+O\u0013R\u000b!c\u0012*B!\"KE+R0L\u000bf{VKT%UA\u0005\u0019rIU!Q\u0011&#ViX&F3~\u0003&+\u0012$J1\u0006!rIU!Q\u0011&#ViX&F3~\u0003&+\u0012$J1\u0002\nQc\u0012*B!\"KE+R0L\u000bf{\u0006KU(U\u001f\u000e{E*\u0001\fH%\u0006\u0003\u0006*\u0013+F?.+\u0015l\u0018)S\u001fR{5i\u0014'!\u0003I9%+\u0011)I\u0013R+ulS#Z?J+u)\u0012-\u0002'\u001d\u0013\u0016\t\u0015%J)\u0016{6*R-`%\u0016;U\t\u0017\u0011\u0002!A\u0014x\u000e]3sif$vn\u00149uS>tG\u0003BA\u0003\u0003?\u0001RaOA\u0004\u0003\u0017I1!!\u0003=\u0005\u0019y\u0005\u000f^5p]B!\u0011QBA\u000e\u001d\u0011\ty!a\u0006\u0011\u0007\u0005EA(\u0004\u0002\u0002\u0014)\u0019\u0011QC#\u0002\rq\u0012xn\u001c;?\u0013\r\tI\u0002P\u0001\u0007!J,G-\u001a4\n\u00079\fiBC\u0002\u0002\u001aqBq!!\t\u001b\u0001\u0004\tY!\u0001\u0003qe>\u0004\u0018\u0001\u00025pgR,\"!a\u0003\u0002\u000b!|7\u000f\u001e\u0011\u0002\tA|'\u000f^\u0001\u0006a>\u0014H\u000fI\u0001\u000ba>dG\u000eU3sS>$\u0017a\u00039pY2\u0004VM]5pI\u0002\n\u0001\u0002]8mYVs\u0017\u000e^\u000b\u0003\u0003k\u0001B!a\u000e\u0002>5\u0011\u0011\u0011\b\u0006\u0004\u0003wI\u0015AC2p]\u000e,(O]3oi&!\u0011qHA\u001d\u0005!!\u0016.\\3V]&$\u0018!\u00039pY2,f.\u001b;!\u0003\u0019\u0001(/\u001a4jq\u00069\u0001O]3gSb\u0004\u0013\u0001C4sCBD\u0017\u000e^3\u0016\u0005\u0005-\u0003\u0003BA'\u0003#j!!a\u0014\u000b\u0007\u0005\u001dC+\u0003\u0003\u0002T\u0005=#AD$sCBD\u0017\u000e^3TK:$WM]\u0001\nOJ\f\u0007\u000f[5uK\u0002\naAZ5mi\u0016\u0014XCAA.%\u0019\ti&a\u0019\u0002j\u00191\u0011q\f\u0015\u0001\u00037\u0012A\u0002\u0010:fM&tW-\\3oiz\nqAZ5mi\u0016\u0014\b\u0005E\u0002k\u0003KJ1!a\u001al\u0005\u0019y%M[3diB\u00191+a\u001b\n\u0007\u00055DK\u0001\u0007NKR\u0014\u0018n\u0019$jYR,'/\u0001\u0005sKB|'\u000f^3s+\t\t\u0019\b\u0005\u0003\u0002N\u0005U\u0014\u0002BA<\u0003\u001f\u0012\u0001c\u0012:ba\"LG/\u001a*fa>\u0014H/\u001a:\u0002\u0013I,\u0007o\u001c:uKJ\u0004\u0013!B:uCJ$HCAA@!\rY\u0014\u0011Q\u0005\u0004\u0003\u0007c$\u0001B+oSR\fAa\u001d;pa\u00061!/\u001a9peR\u0004"
)
public class GraphiteSink implements Sink {
   private final Properties property;
   private final MetricRegistry registry;
   private final int GRAPHITE_DEFAULT_PERIOD;
   private final String GRAPHITE_DEFAULT_UNIT;
   private final String GRAPHITE_DEFAULT_PREFIX;
   private final String GRAPHITE_KEY_HOST;
   private final String GRAPHITE_KEY_PORT;
   private final String GRAPHITE_KEY_PERIOD;
   private final String GRAPHITE_KEY_UNIT;
   private final String GRAPHITE_KEY_PREFIX;
   private final String GRAPHITE_KEY_PROTOCOL;
   private final String GRAPHITE_KEY_REGEX;
   private final String host;
   private final int port;
   private final int pollPeriod;
   private final TimeUnit pollUnit;
   private final String prefix;
   private final GraphiteSender graphite;
   private final MetricFilter filter;
   private final GraphiteReporter reporter;

   public Properties property() {
      return this.property;
   }

   public MetricRegistry registry() {
      return this.registry;
   }

   public int GRAPHITE_DEFAULT_PERIOD() {
      return this.GRAPHITE_DEFAULT_PERIOD;
   }

   public String GRAPHITE_DEFAULT_UNIT() {
      return this.GRAPHITE_DEFAULT_UNIT;
   }

   public String GRAPHITE_DEFAULT_PREFIX() {
      return this.GRAPHITE_DEFAULT_PREFIX;
   }

   public String GRAPHITE_KEY_HOST() {
      return this.GRAPHITE_KEY_HOST;
   }

   public String GRAPHITE_KEY_PORT() {
      return this.GRAPHITE_KEY_PORT;
   }

   public String GRAPHITE_KEY_PERIOD() {
      return this.GRAPHITE_KEY_PERIOD;
   }

   public String GRAPHITE_KEY_UNIT() {
      return this.GRAPHITE_KEY_UNIT;
   }

   public String GRAPHITE_KEY_PREFIX() {
      return this.GRAPHITE_KEY_PREFIX;
   }

   public String GRAPHITE_KEY_PROTOCOL() {
      return this.GRAPHITE_KEY_PROTOCOL;
   }

   public String GRAPHITE_KEY_REGEX() {
      return this.GRAPHITE_KEY_REGEX;
   }

   public Option propertyToOption(final String prop) {
      return .MODULE$.apply(this.property().getProperty(prop));
   }

   public String host() {
      return this.host;
   }

   public int port() {
      return this.port;
   }

   public int pollPeriod() {
      return this.pollPeriod;
   }

   public TimeUnit pollUnit() {
      return this.pollUnit;
   }

   public String prefix() {
      return this.prefix;
   }

   public GraphiteSender graphite() {
      return this.graphite;
   }

   public MetricFilter filter() {
      return this.filter;
   }

   public GraphiteReporter reporter() {
      return this.reporter;
   }

   public void start() {
      this.reporter().start((long)this.pollPeriod(), this.pollUnit());
   }

   public void stop() {
      this.reporter().stop();
   }

   public void report() {
      this.reporter().report();
   }

   public GraphiteSink(final Properties property, final MetricRegistry registry) {
      this.property = property;
      this.registry = registry;
      this.GRAPHITE_DEFAULT_PERIOD = 10;
      this.GRAPHITE_DEFAULT_UNIT = "SECONDS";
      this.GRAPHITE_DEFAULT_PREFIX = "";
      this.GRAPHITE_KEY_HOST = "host";
      this.GRAPHITE_KEY_PORT = "port";
      this.GRAPHITE_KEY_PERIOD = "period";
      this.GRAPHITE_KEY_UNIT = "unit";
      this.GRAPHITE_KEY_PREFIX = "prefix";
      this.GRAPHITE_KEY_PROTOCOL = "protocol";
      this.GRAPHITE_KEY_REGEX = "regex";
      if (this.propertyToOption(this.GRAPHITE_KEY_HOST()).isEmpty()) {
         throw SparkCoreErrors$.MODULE$.graphiteSinkPropertyMissingError("host");
      } else if (this.propertyToOption(this.GRAPHITE_KEY_PORT()).isEmpty()) {
         throw SparkCoreErrors$.MODULE$.graphiteSinkPropertyMissingError("port");
      } else {
         this.host = (String)this.propertyToOption(this.GRAPHITE_KEY_HOST()).get();
         this.port = scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString((String)this.propertyToOption(this.GRAPHITE_KEY_PORT()).get()));
         Option var8 = this.propertyToOption(this.GRAPHITE_KEY_PERIOD());
         int var10001;
         if (var8 instanceof Some) {
            Some var9 = (Some)var8;
            String s = (String)var9.value();
            var10001 = scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(s));
         } else {
            if (!scala.None..MODULE$.equals(var8)) {
               throw new MatchError(var8);
            }

            var10001 = this.GRAPHITE_DEFAULT_PERIOD();
         }

         this.pollPeriod = var10001;
         Option var11 = this.propertyToOption(this.GRAPHITE_KEY_UNIT());
         TimeUnit var24;
         if (var11 instanceof Some) {
            Some var12 = (Some)var11;
            String s = (String)var12.value();
            var24 = TimeUnit.valueOf(s.toUpperCase(Locale.ROOT));
         } else {
            if (!scala.None..MODULE$.equals(var11)) {
               throw new MatchError(var11);
            }

            var24 = TimeUnit.valueOf(this.GRAPHITE_DEFAULT_UNIT());
         }

         label83: {
            this.pollUnit = var24;
            this.prefix = (String)this.propertyToOption(this.GRAPHITE_KEY_PREFIX()).getOrElse(() -> this.GRAPHITE_DEFAULT_PREFIX());
            MetricsSystem$.MODULE$.checkMinimalPollingPeriod(this.pollUnit(), this.pollPeriod());
            boolean var14 = false;
            Some var15 = null;
            Option var16 = this.propertyToOption(this.GRAPHITE_KEY_PROTOCOL()).map((x$1) -> x$1.toLowerCase(Locale.ROOT));
            if (var16 instanceof Some) {
               var14 = true;
               var15 = (Some)var16;
               String var17 = (String)var15.value();
               if ("udp".equals(var17)) {
                  var26 = new GraphiteUDP(this.host(), this.port());
                  break label83;
               }
            }

            label71: {
               if (var16 instanceof Some) {
                  Some var18 = (Some)var16;
                  String var19 = (String)var18.value();
                  if ("tcp".equals(var19)) {
                     var25 = true;
                     break label71;
                  }
               }

               var25 = scala.None..MODULE$.equals(var16);
            }

            if (!var25) {
               if (var14) {
                  String p = (String)var15.value();
                  throw SparkCoreErrors$.MODULE$.graphiteSinkInvalidProtocolError(p);
               }

               throw new MatchError(var16);
            }

            var26 = new Graphite(this.host(), this.port());
         }

         this.graphite = (GraphiteSender)var26;
         Option var21 = this.propertyToOption(this.GRAPHITE_KEY_REGEX());
         MetricFilter var27;
         if (var21 instanceof Some) {
            Some var22 = (Some)var21;
            String pattern = (String)var22.value();
            var27 = new MetricFilter(pattern) {
               private final String pattern$1;

               public boolean matches(final String name, final Metric metric) {
                  return scala.collection.StringOps..MODULE$.r$extension(scala.Predef..MODULE$.augmentString(this.pattern$1)).findFirstMatchIn(name).isDefined();
               }

               public {
                  this.pattern$1 = pattern$1;
               }
            };
         } else {
            if (!scala.None..MODULE$.equals(var21)) {
               throw new MatchError(var21);
            }

            var27 = MetricFilter.ALL;
         }

         this.filter = var27;
         this.reporter = GraphiteReporter.forRegistry(registry).convertDurationsTo(TimeUnit.MILLISECONDS).convertRatesTo(TimeUnit.SECONDS).prefixedWith(this.prefix()).filter(this.filter()).build(this.graphite());
      }
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
