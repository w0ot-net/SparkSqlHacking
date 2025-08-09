package org.apache.spark.metrics.sink;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
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
   bytes = "\u0006\u0005U4Qa\u0006\r\u00019\tB\u0001\"\f\u0001\u0003\u0006\u0004%\ta\f\u0005\tq\u0001\u0011\t\u0011)A\u0005a!A\u0011\b\u0001BC\u0002\u0013\u0005!\b\u0003\u0005E\u0001\t\u0005\t\u0015!\u0003<\u0011\u0015)\u0005\u0001\"\u0001G\u0011\u001dQ\u0005A1A\u0005\u0002-Caa\u0014\u0001!\u0002\u0013a\u0005b\u0002)\u0001\u0005\u0004%\t!\u0015\u0005\u00071\u0002\u0001\u000b\u0011\u0002*\t\u000fe\u0003!\u0019!C\u0001#\"1!\f\u0001Q\u0001\nICqa\u0017\u0001C\u0002\u0013\u0005\u0011\u000b\u0003\u0004]\u0001\u0001\u0006IA\u0015\u0005\b;\u0002\u0011\r\u0011\"\u0001L\u0011\u0019q\u0006\u0001)A\u0005\u0019\"9q\f\u0001b\u0001\n\u0003\u0001\u0007BB4\u0001A\u0003%\u0011\rC\u0004i\u0001\t\u0007I\u0011A5\t\r5\u0004\u0001\u0015!\u0003k\u0011\u0015q\u0007\u0001\"\u0011p\u0011\u0015\u0019\b\u0001\"\u0011p\u0011\u0015!\b\u0001\"\u0011p\u0005-\u0019uN\\:pY\u0016\u001c\u0016N\\6\u000b\u0005eQ\u0012\u0001B:j].T!a\u0007\u000f\u0002\u000f5,GO]5dg*\u0011QDH\u0001\u0006gB\f'o\u001b\u0006\u0003?\u0001\na!\u00199bG\",'\"A\u0011\u0002\u0007=\u0014xmE\u0002\u0001G%\u0002\"\u0001J\u0014\u000e\u0003\u0015R\u0011AJ\u0001\u0006g\u000e\fG.Y\u0005\u0003Q\u0015\u0012a!\u00118z%\u00164\u0007C\u0001\u0016,\u001b\u0005A\u0012B\u0001\u0017\u0019\u0005\u0011\u0019\u0016N\\6\u0002\u0011A\u0014x\u000e]3sif\u001c\u0001!F\u00011!\t\td'D\u00013\u0015\t\u0019D'\u0001\u0003vi&d'\"A\u001b\u0002\t)\fg/Y\u0005\u0003oI\u0012!\u0002\u0015:pa\u0016\u0014H/[3t\u0003%\u0001(o\u001c9feRL\b%\u0001\u0005sK\u001eL7\u000f\u001e:z+\u0005Y\u0004C\u0001\u001fC\u001b\u0005i$BA\u000e?\u0015\ty\u0004)\u0001\u0005d_\u0012\f\u0007.\u00197f\u0015\u0005\t\u0015aA2p[&\u00111)\u0010\u0002\u000f\u001b\u0016$(/[2SK\u001eL7\u000f\u001e:z\u0003%\u0011XmZ5tiJL\b%\u0001\u0004=S:LGO\u0010\u000b\u0004\u000f\"K\u0005C\u0001\u0016\u0001\u0011\u0015iS\u00011\u00011\u0011\u0015IT\u00011\u0001<\u0003Y\u0019uJT*P\u0019\u0016{F)\u0012$B+2#v\fU#S\u0013>#U#\u0001'\u0011\u0005\u0011j\u0015B\u0001(&\u0005\rIe\u000e^\u0001\u0018\u0007>s5k\u0014'F?\u0012+e)Q+M)~\u0003VIU%P\t\u0002\nAcQ(O'>cUi\u0018#F\r\u0006+F\nV0V\u001d&#V#\u0001*\u0011\u0005M3V\"\u0001+\u000b\u0005U#\u0014\u0001\u00027b]\u001eL!a\u0016+\u0003\rM#(/\u001b8h\u0003U\u0019uJT*P\u0019\u0016{F)\u0012$B+2#v,\u0016(J)\u0002\n!cQ(O'>cUiX&F3~\u0003VIU%P\t\u0006\u00192i\u0014(T\u001f2+ulS#Z?B+%+S(EA\u0005\u00012i\u0014(T\u001f2+ulS#Z?Vs\u0015\nV\u0001\u0012\u0007>s5k\u0014'F?.+\u0015lX+O\u0013R\u0003\u0013A\u00039pY2\u0004VM]5pI\u0006Y\u0001o\u001c7m!\u0016\u0014\u0018n\u001c3!\u0003!\u0001x\u000e\u001c7V]&$X#A1\u0011\u0005\t,W\"A2\u000b\u0005\u0011\u0014\u0014AC2p]\u000e,(O]3oi&\u0011am\u0019\u0002\t)&lW-\u00168ji\u0006I\u0001o\u001c7m+:LG\u000fI\u0001\te\u0016\u0004xN\u001d;feV\t!\u000e\u0005\u0002=W&\u0011A.\u0010\u0002\u0010\u0007>t7o\u001c7f%\u0016\u0004xN\u001d;fe\u0006I!/\u001a9peR,'\u000fI\u0001\u0006gR\f'\u000f\u001e\u000b\u0002aB\u0011A%]\u0005\u0003e\u0016\u0012A!\u00168ji\u0006!1\u000f^8q\u0003\u0019\u0011X\r]8si\u0002"
)
public class ConsoleSink implements Sink {
   private final Properties property;
   private final MetricRegistry registry;
   private final int CONSOLE_DEFAULT_PERIOD;
   private final String CONSOLE_DEFAULT_UNIT;
   private final String CONSOLE_KEY_PERIOD;
   private final String CONSOLE_KEY_UNIT;
   private final int pollPeriod;
   private final TimeUnit pollUnit;
   private final ConsoleReporter reporter;

   public Properties property() {
      return this.property;
   }

   public MetricRegistry registry() {
      return this.registry;
   }

   public int CONSOLE_DEFAULT_PERIOD() {
      return this.CONSOLE_DEFAULT_PERIOD;
   }

   public String CONSOLE_DEFAULT_UNIT() {
      return this.CONSOLE_DEFAULT_UNIT;
   }

   public String CONSOLE_KEY_PERIOD() {
      return this.CONSOLE_KEY_PERIOD;
   }

   public String CONSOLE_KEY_UNIT() {
      return this.CONSOLE_KEY_UNIT;
   }

   public int pollPeriod() {
      return this.pollPeriod;
   }

   public TimeUnit pollUnit() {
      return this.pollUnit;
   }

   public ConsoleReporter reporter() {
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

   public ConsoleSink(final Properties property, final MetricRegistry registry) {
      this.property = property;
      this.registry = registry;
      this.CONSOLE_DEFAULT_PERIOD = 10;
      this.CONSOLE_DEFAULT_UNIT = "SECONDS";
      this.CONSOLE_KEY_PERIOD = "period";
      this.CONSOLE_KEY_UNIT = "unit";
      Option var5 = .MODULE$.apply(property.getProperty(this.CONSOLE_KEY_PERIOD()));
      int var10001;
      if (var5 instanceof Some var6) {
         String s = (String)var6.value();
         var10001 = scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(s));
      } else {
         if (!scala.None..MODULE$.equals(var5)) {
            throw new MatchError(var5);
         }

         var10001 = this.CONSOLE_DEFAULT_PERIOD();
      }

      this.pollPeriod = var10001;
      Option var8 = .MODULE$.apply(property.getProperty(this.CONSOLE_KEY_UNIT()));
      TimeUnit var11;
      if (var8 instanceof Some var9) {
         String s = (String)var9.value();
         var11 = TimeUnit.valueOf(s.toUpperCase(Locale.ROOT));
      } else {
         if (!scala.None..MODULE$.equals(var8)) {
            throw new MatchError(var8);
         }

         var11 = TimeUnit.valueOf(this.CONSOLE_DEFAULT_UNIT());
      }

      this.pollUnit = var11;
      MetricsSystem$.MODULE$.checkMinimalPollingPeriod(this.pollUnit(), this.pollPeriod());
      this.reporter = ConsoleReporter.forRegistry(registry).convertDurationsTo(TimeUnit.MILLISECONDS).convertRatesTo(TimeUnit.SECONDS).build();
   }
}
