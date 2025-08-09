package org.apache.spark.metrics.sink;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Slf4jReporter;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.spark.metrics.MetricsSystem$;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Option.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U4Qa\u0006\r\u00019\tB\u0001\"\f\u0001\u0003\u0006\u0004%\ta\f\u0005\tq\u0001\u0011\t\u0011)A\u0005a!A\u0011\b\u0001BC\u0002\u0013\u0005!\b\u0003\u0005E\u0001\t\u0005\t\u0015!\u0003<\u0011\u0015)\u0005\u0001\"\u0001G\u0011\u001dQ\u0005A1A\u0005\u0002-Caa\u0014\u0001!\u0002\u0013a\u0005b\u0002)\u0001\u0005\u0004%\t!\u0015\u0005\u00071\u0002\u0001\u000b\u0011\u0002*\t\u000fe\u0003!\u0019!C\u0001#\"1!\f\u0001Q\u0001\nICqa\u0017\u0001C\u0002\u0013\u0005\u0011\u000b\u0003\u0004]\u0001\u0001\u0006IA\u0015\u0005\b;\u0002\u0011\r\u0011\"\u0001L\u0011\u0019q\u0006\u0001)A\u0005\u0019\"9q\f\u0001b\u0001\n\u0003\u0001\u0007BB4\u0001A\u0003%\u0011\rC\u0004i\u0001\t\u0007I\u0011A5\t\r5\u0004\u0001\u0015!\u0003k\u0011\u0015q\u0007\u0001\"\u0011p\u0011\u0015\u0019\b\u0001\"\u0011p\u0011\u0015!\b\u0001\"\u0011p\u0005%\u0019FN\u001a\u001bk'&t7N\u0003\u0002\u001a5\u0005!1/\u001b8l\u0015\tYB$A\u0004nKR\u0014\u0018nY:\u000b\u0005uq\u0012!B:qCJ\\'BA\u0010!\u0003\u0019\t\u0007/Y2iK*\t\u0011%A\u0002pe\u001e\u001c2\u0001A\u0012*!\t!s%D\u0001&\u0015\u00051\u0013!B:dC2\f\u0017B\u0001\u0015&\u0005\u0019\te.\u001f*fMB\u0011!fK\u0007\u00021%\u0011A\u0006\u0007\u0002\u0005'&t7.\u0001\u0005qe>\u0004XM\u001d;z\u0007\u0001)\u0012\u0001\r\t\u0003cYj\u0011A\r\u0006\u0003gQ\nA!\u001e;jY*\tQ'\u0001\u0003kCZ\f\u0017BA\u001c3\u0005)\u0001&o\u001c9feRLWm]\u0001\naJ|\u0007/\u001a:us\u0002\n\u0001B]3hSN$(/_\u000b\u0002wA\u0011AHQ\u0007\u0002{)\u00111D\u0010\u0006\u0003\u007f\u0001\u000b\u0001bY8eC\"\fG.\u001a\u0006\u0002\u0003\u0006\u00191m\\7\n\u0005\rk$AD'fiJL7MU3hSN$(/_\u0001\ne\u0016<\u0017n\u001d;ss\u0002\na\u0001P5oSRtDcA$I\u0013B\u0011!\u0006\u0001\u0005\u0006[\u0015\u0001\r\u0001\r\u0005\u0006s\u0015\u0001\raO\u0001\u0015'23EGS0E\u000b\u001a\u000bU\u000b\u0014+`!\u0016\u0013\u0016j\u0014#\u0016\u00031\u0003\"\u0001J'\n\u00059+#aA%oi\u0006)2\u000b\u0014$5\u0015~#UIR!V\u0019R{\u0006+\u0012*J\u001f\u0012\u0003\u0013AE*M\rRRu\fR#G\u0003VcEkX+O\u0013R+\u0012A\u0015\t\u0003'Zk\u0011\u0001\u0016\u0006\u0003+R\nA\u0001\\1oO&\u0011q\u000b\u0016\u0002\u0007'R\u0014\u0018N\\4\u0002'Mce\t\u000e&`\t\u00163\u0015)\u0016'U?Vs\u0015\n\u0016\u0011\u0002!Mce\t\u000e&`\u0017\u0016Kv\fU#S\u0013>#\u0015!E*M\rRRulS#Z?B+%+S(EA\u0005q1\u000b\u0014$5\u0015~[U)W0V\u001d&#\u0016aD*M\rRRulS#Z?Vs\u0015\n\u0016\u0011\u0002\u0015A|G\u000e\u001c)fe&|G-A\u0006q_2d\u0007+\u001a:j_\u0012\u0004\u0013\u0001\u00039pY2,f.\u001b;\u0016\u0003\u0005\u0004\"AY3\u000e\u0003\rT!\u0001\u001a\u001a\u0002\u0015\r|gnY;se\u0016tG/\u0003\u0002gG\nAA+[7f+:LG/A\u0005q_2dWK\\5uA\u0005A!/\u001a9peR,'/F\u0001k!\ta4.\u0003\u0002m{\ti1\u000b\u001c45UJ+\u0007o\u001c:uKJ\f\u0011B]3q_J$XM\u001d\u0011\u0002\u000bM$\u0018M\u001d;\u0015\u0003A\u0004\"\u0001J9\n\u0005I,#\u0001B+oSR\fAa\u001d;pa\u00061!/\u001a9peR\u0004"
)
public class Slf4jSink implements Sink {
   private final Properties property;
   private final MetricRegistry registry;
   private final int SLF4J_DEFAULT_PERIOD;
   private final String SLF4J_DEFAULT_UNIT;
   private final String SLF4J_KEY_PERIOD;
   private final String SLF4J_KEY_UNIT;
   private final int pollPeriod;
   private final TimeUnit pollUnit;
   private final Slf4jReporter reporter;

   public Properties property() {
      return this.property;
   }

   public MetricRegistry registry() {
      return this.registry;
   }

   public int SLF4J_DEFAULT_PERIOD() {
      return this.SLF4J_DEFAULT_PERIOD;
   }

   public String SLF4J_DEFAULT_UNIT() {
      return this.SLF4J_DEFAULT_UNIT;
   }

   public String SLF4J_KEY_PERIOD() {
      return this.SLF4J_KEY_PERIOD;
   }

   public String SLF4J_KEY_UNIT() {
      return this.SLF4J_KEY_UNIT;
   }

   public int pollPeriod() {
      return this.pollPeriod;
   }

   public TimeUnit pollUnit() {
      return this.pollUnit;
   }

   public Slf4jReporter reporter() {
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

   public Slf4jSink(final Properties property, final MetricRegistry registry) {
      this.property = property;
      this.registry = registry;
      this.SLF4J_DEFAULT_PERIOD = 10;
      this.SLF4J_DEFAULT_UNIT = "SECONDS";
      this.SLF4J_KEY_PERIOD = "period";
      this.SLF4J_KEY_UNIT = "unit";
      Option var5 = .MODULE$.apply(property.getProperty(this.SLF4J_KEY_PERIOD()));
      int var10001;
      if (var5 instanceof Some var6) {
         String s = (String)var6.value();
         var10001 = scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(s));
      } else {
         if (!scala.None..MODULE$.equals(var5)) {
            throw new MatchError(var5);
         }

         var10001 = this.SLF4J_DEFAULT_PERIOD();
      }

      this.pollPeriod = var10001;
      Option var8 = .MODULE$.apply(property.getProperty(this.SLF4J_KEY_UNIT()));
      TimeUnit var11;
      if (var8 instanceof Some var9) {
         String s = (String)var9.value();
         var11 = TimeUnit.valueOf(s.toUpperCase(Locale.ROOT));
      } else {
         if (!scala.None..MODULE$.equals(var8)) {
            throw new MatchError(var8);
         }

         var11 = TimeUnit.valueOf(this.SLF4J_DEFAULT_UNIT());
      }

      this.pollUnit = var11;
      MetricsSystem$.MODULE$.checkMinimalPollingPeriod(this.pollUnit(), this.pollPeriod());
      this.reporter = Slf4jReporter.forRegistry(registry).convertDurationsTo(TimeUnit.MILLISECONDS).convertRatesTo(TimeUnit.SECONDS).build();
   }
}
