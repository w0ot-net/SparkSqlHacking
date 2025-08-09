package org.apache.spark.metrics.sink;

import com.codahale.metrics.CsvReporter;
import com.codahale.metrics.MetricRegistry;
import java.io.File;
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
   bytes = "\u0006\u0005\u0005\ra!B\u000f\u001f\u0001\tB\u0003\u0002C\u001a\u0001\u0005\u000b\u0007I\u0011A\u001b\t\u0011y\u0002!\u0011!Q\u0001\nYB\u0001b\u0010\u0001\u0003\u0006\u0004%\t\u0001\u0011\u0005\t\u0015\u0002\u0011\t\u0011)A\u0005\u0003\")1\n\u0001C\u0001\u0019\"9\u0001\u000b\u0001b\u0001\n\u0003\t\u0006B\u0002-\u0001A\u0003%!\u000bC\u0004Z\u0001\t\u0007I\u0011A)\t\ri\u0003\u0001\u0015!\u0003S\u0011\u001dY\u0006A1A\u0005\u0002ECa\u0001\u0018\u0001!\u0002\u0013\u0011\u0006bB/\u0001\u0005\u0004%\tA\u0018\u0005\u0007E\u0002\u0001\u000b\u0011B0\t\u000f\r\u0004!\u0019!C\u0001#\"1A\r\u0001Q\u0001\nICq!\u001a\u0001C\u0002\u0013\u0005\u0011\u000b\u0003\u0004g\u0001\u0001\u0006IA\u0015\u0005\bO\u0002\u0011\r\u0011\"\u0001_\u0011\u0019A\u0007\u0001)A\u0005?\"9\u0011\u000e\u0001b\u0001\n\u0003Q\u0007BB9\u0001A\u0003%1\u000eC\u0004s\u0001\t\u0007I\u0011A)\t\rM\u0004\u0001\u0015!\u0003S\u0011\u001d!\bA1A\u0005\u0002UDa!\u001f\u0001!\u0002\u00131\b\"\u0002>\u0001\t\u0003Z\b\"B@\u0001\t\u0003Z\bBBA\u0001\u0001\u0011\u00053PA\u0004DgZ\u001c\u0016N\\6\u000b\u0005}\u0001\u0013\u0001B:j].T!!\t\u0012\u0002\u000f5,GO]5dg*\u00111\u0005J\u0001\u0006gB\f'o\u001b\u0006\u0003K\u0019\na!\u00199bG\",'\"A\u0014\u0002\u0007=\u0014xmE\u0002\u0001S=\u0002\"AK\u0017\u000e\u0003-R\u0011\u0001L\u0001\u0006g\u000e\fG.Y\u0005\u0003]-\u0012a!\u00118z%\u00164\u0007C\u0001\u00192\u001b\u0005q\u0012B\u0001\u001a\u001f\u0005\u0011\u0019\u0016N\\6\u0002\u0011A\u0014x\u000e]3sif\u001c\u0001!F\u00017!\t9D(D\u00019\u0015\tI$(\u0001\u0003vi&d'\"A\u001e\u0002\t)\fg/Y\u0005\u0003{a\u0012!\u0002\u0015:pa\u0016\u0014H/[3t\u0003%\u0001(o\u001c9feRL\b%\u0001\u0005sK\u001eL7\u000f\u001e:z+\u0005\t\u0005C\u0001\"I\u001b\u0005\u0019%BA\u0011E\u0015\t)e)\u0001\u0005d_\u0012\f\u0007.\u00197f\u0015\u00059\u0015aA2p[&\u0011\u0011j\u0011\u0002\u000f\u001b\u0016$(/[2SK\u001eL7\u000f\u001e:z\u0003%\u0011XmZ5tiJL\b%\u0001\u0004=S:LGO\u0010\u000b\u0004\u001b:{\u0005C\u0001\u0019\u0001\u0011\u0015\u0019T\u00011\u00017\u0011\u0015yT\u00011\u0001B\u00039\u00195KV0L\u000bf{\u0006+\u0012*J\u001f\u0012+\u0012A\u0015\t\u0003'Zk\u0011\u0001\u0016\u0006\u0003+j\nA\u0001\\1oO&\u0011q\u000b\u0016\u0002\u0007'R\u0014\u0018N\\4\u0002\u001f\r\u001bfkX&F3~\u0003VIU%P\t\u0002\nAbQ*W?.+\u0015lX+O\u0013R\u000bQbQ*W?.+\u0015lX+O\u0013R\u0003\u0013aC\"T-~[U)W0E\u0013J\u000bAbQ*W?.+\u0015l\u0018#J%\u0002\n!cQ*W?\u0012+e)Q+M)~\u0003VIU%P\tV\tq\f\u0005\u0002+A&\u0011\u0011m\u000b\u0002\u0004\u0013:$\u0018aE\"T-~#UIR!V\u0019R{\u0006+\u0012*J\u001f\u0012\u0003\u0013\u0001E\"T-~#UIR!V\u0019R{VKT%U\u0003E\u00195KV0E\u000b\u001a\u000bU\u000b\u0014+`+:KE\u000bI\u0001\u0010\u0007N3v\fR#G\u0003VcEk\u0018#J%\u0006\u00012i\u0015,`\t\u00163\u0015)\u0016'U?\u0012K%\u000bI\u0001\u000ba>dG\u000eU3sS>$\u0017a\u00039pY2\u0004VM]5pI\u0002\n\u0001\u0002]8mYVs\u0017\u000e^\u000b\u0002WB\u0011An\\\u0007\u0002[*\u0011a\u000eO\u0001\u000bG>t7-\u001e:sK:$\u0018B\u00019n\u0005!!\u0016.\\3V]&$\u0018!\u00039pY2,f.\u001b;!\u0003\u001d\u0001x\u000e\u001c7ESJ\f\u0001\u0002]8mY\u0012K'\u000fI\u0001\te\u0016\u0004xN\u001d;feV\ta\u000f\u0005\u0002Co&\u0011\u0001p\u0011\u0002\f\u0007N4(+\u001a9peR,'/A\u0005sKB|'\u000f^3sA\u0005)1\u000f^1siR\tA\u0010\u0005\u0002+{&\u0011ap\u000b\u0002\u0005+:LG/\u0001\u0003ti>\u0004\u0018A\u0002:fa>\u0014H\u000f"
)
public class CsvSink implements Sink {
   private final Properties property;
   private final MetricRegistry registry;
   private final String CSV_KEY_PERIOD;
   private final String CSV_KEY_UNIT;
   private final String CSV_KEY_DIR;
   private final int CSV_DEFAULT_PERIOD;
   private final String CSV_DEFAULT_UNIT;
   private final String CSV_DEFAULT_DIR;
   private final int pollPeriod;
   private final TimeUnit pollUnit;
   private final String pollDir;
   private final CsvReporter reporter;

   public Properties property() {
      return this.property;
   }

   public MetricRegistry registry() {
      return this.registry;
   }

   public String CSV_KEY_PERIOD() {
      return this.CSV_KEY_PERIOD;
   }

   public String CSV_KEY_UNIT() {
      return this.CSV_KEY_UNIT;
   }

   public String CSV_KEY_DIR() {
      return this.CSV_KEY_DIR;
   }

   public int CSV_DEFAULT_PERIOD() {
      return this.CSV_DEFAULT_PERIOD;
   }

   public String CSV_DEFAULT_UNIT() {
      return this.CSV_DEFAULT_UNIT;
   }

   public String CSV_DEFAULT_DIR() {
      return this.CSV_DEFAULT_DIR;
   }

   public int pollPeriod() {
      return this.pollPeriod;
   }

   public TimeUnit pollUnit() {
      return this.pollUnit;
   }

   public String pollDir() {
      return this.pollDir;
   }

   public CsvReporter reporter() {
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

   public CsvSink(final Properties property, final MetricRegistry registry) {
      this.property = property;
      this.registry = registry;
      this.CSV_KEY_PERIOD = "period";
      this.CSV_KEY_UNIT = "unit";
      this.CSV_KEY_DIR = "directory";
      this.CSV_DEFAULT_PERIOD = 10;
      this.CSV_DEFAULT_UNIT = "SECONDS";
      this.CSV_DEFAULT_DIR = "/tmp/";
      Option var6 = .MODULE$.apply(property.getProperty(this.CSV_KEY_PERIOD()));
      int var10001;
      if (var6 instanceof Some var7) {
         String s = (String)var7.value();
         var10001 = scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(s));
      } else {
         if (!scala.None..MODULE$.equals(var6)) {
            throw new MatchError(var6);
         }

         var10001 = this.CSV_DEFAULT_PERIOD();
      }

      this.pollPeriod = var10001;
      Option var9 = .MODULE$.apply(property.getProperty(this.CSV_KEY_UNIT()));
      TimeUnit var15;
      if (var9 instanceof Some var10) {
         String s = (String)var10.value();
         var15 = TimeUnit.valueOf(s.toUpperCase(Locale.ROOT));
      } else {
         if (!scala.None..MODULE$.equals(var9)) {
            throw new MatchError(var9);
         }

         var15 = TimeUnit.valueOf(this.CSV_DEFAULT_UNIT());
      }

      this.pollUnit = var15;
      MetricsSystem$.MODULE$.checkMinimalPollingPeriod(this.pollUnit(), this.pollPeriod());
      Option var12 = .MODULE$.apply(property.getProperty(this.CSV_KEY_DIR()));
      String var16;
      if (var12 instanceof Some var13) {
         String s = (String)var13.value();
         var16 = s;
      } else {
         if (!scala.None..MODULE$.equals(var12)) {
            throw new MatchError(var12);
         }

         var16 = this.CSV_DEFAULT_DIR();
      }

      this.pollDir = var16;
      this.reporter = CsvReporter.forRegistry(registry).formatFor(Locale.US).convertDurationsTo(TimeUnit.MILLISECONDS).convertRatesTo(TimeUnit.SECONDS).build(new File(this.pollDir()));
   }
}
