package org.apache.spark.sql;

import java.lang.invoke.SerializedLambda;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.spark.util.SparkThreadUtils.;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.concurrent.Future;
import scala.concurrent.Promise;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]c\u0001B\u000b\u0017\u0001}A\u0001B\n\u0001\u0003\u0006\u0004%\ta\n\u0005\tg\u0001\u0011\t\u0011)A\u0005Q!)A\u0007\u0001C\u0001k!)A\u0007\u0001C\u0001s!9!\b\u0001b\u0001\n\u0013Y\u0004B\u0002%\u0001A\u0003%A\bC\u0004J\u0001\t\u0007I\u0011\u0002&\t\rY\u0003\u0001\u0015!\u0003L\u0011\u001d9\u0006A1A\u0005\u0002aCa\u0001\u0018\u0001!\u0002\u0013I\u0006\"B/\u0001\t\u0003q\u0006bBA\u0004\u0001\u0011\u0005\u0011\u0011\u0002\u0005\b\u0003;\u0001A\u0011\u0001\f_\u0011!\tY\u0003\u0001C\u0001-\u00055\u0002\u0002CA\u001b\u0001\u0011\u0005a#a\u000e\b\u000f\u0005%c\u0003#\u0001\u0002L\u00191QC\u0006E\u0001\u0003\u001bBa\u0001N\t\u0005\u0002\u0005=\u0003BBA)#\u0011\u0005\u0011\bC\u0004\u0002RE!\t!a\u0015\u0003\u0017=\u00137/\u001a:wCRLwN\u001c\u0006\u0003/a\t1a]9m\u0015\tI\"$A\u0003ta\u0006\u00148N\u0003\u0002\u001c9\u00051\u0011\r]1dQ\u0016T\u0011!H\u0001\u0004_J<7\u0001A\n\u0003\u0001\u0001\u0002\"!\t\u0013\u000e\u0003\tR\u0011aI\u0001\u0006g\u000e\fG.Y\u0005\u0003K\t\u0012a!\u00118z%\u00164\u0017\u0001\u00028b[\u0016,\u0012\u0001\u000b\t\u0003SAr!A\u000b\u0018\u0011\u0005-\u0012S\"\u0001\u0017\u000b\u00055r\u0012A\u0002\u001fs_>$h(\u0003\u00020E\u00051\u0001K]3eK\u001aL!!\r\u001a\u0003\rM#(/\u001b8h\u0015\ty#%A\u0003oC6,\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0003ma\u0002\"a\u000e\u0001\u000e\u0003YAQAJ\u0002A\u0002!\"\u0012AN\u0001\rSN\u0014VmZ5ti\u0016\u0014X\rZ\u000b\u0002yA\u0011QHR\u0007\u0002})\u0011q\bQ\u0001\u0007CR|W.[2\u000b\u0005\u0005\u0013\u0015AC2p]\u000e,(O]3oi*\u00111\tR\u0001\u0005kRLGNC\u0001F\u0003\u0011Q\u0017M^1\n\u0005\u001ds$!D!u_6L7MQ8pY\u0016\fg.A\u0007jgJ+w-[:uKJ,G\rI\u0001\baJ|W.[:f+\u0005Y\u0005c\u0001'O!6\tQJ\u0003\u0002BE%\u0011q*\u0014\u0002\b!J|W.[:f!\u0011I\u0013\u000bK*\n\u0005I\u0013$aA'baB\u0011\u0011\u0005V\u0005\u0003+\n\u00121!\u00118z\u0003!\u0001(o\\7jg\u0016\u0004\u0013A\u00024viV\u0014X-F\u0001Z!\ra%\fU\u0005\u000376\u0013aAR;ukJ,\u0017a\u00024viV\u0014X\rI\u0001\u0004O\u0016$X#\u0001))\u0007-\u0001G\u000eE\u0002\"C\u000eL!A\u0019\u0012\u0003\rQD'o\\<t!\t!\u0017N\u0004\u0002fO:\u00111FZ\u0005\u0002G%\u0011\u0001NI\u0001\ba\u0006\u001c7.Y4f\u0013\tQ7N\u0001\u000bJ]R,'O];qi\u0016$W\t_2faRLwN\u001c\u0006\u0003Q\n\nTA\b\u0015n\u0003\u000b\tTa\t8r{J,\"aJ8\u0005\u000bAt\"\u0019A;\u0003\u0003QK!A]:\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00132\u0015\t!(%\u0001\u0004uQJ|wo]\t\u0003mf\u0004\"!I<\n\u0005a\u0014#a\u0002(pi\"Lgn\u001a\t\u0003unt!!I4\n\u0005q\\'!\u0003+ie><\u0018M\u00197fc\u0019\u0019cp`A\u0001i:\u0011\u0011e`\u0005\u0003i\n\nTAI\u0011#\u0003\u0007\u0011Qa]2bY\u0006\f$AJ2\u0002\u0013\u001d,G/Q:KCZ\fWCAA\u0006!\u0019\ti!a\u0004)'6\t!)\u0003\u0002S\u0005\"\"A\u0002YA\nc\u0019q\u0002&!\u0006\u0002\u001cE21E\\9\u0002\u0018I\fda\t@\u0000\u00033!\u0018'\u0002\u0012\"E\u0005\r\u0011G\u0001\u0014d\u0003)9W\r^(s\u000b6\u0004H/\u001f\u0015\u0005\u001b\u0001\f\t#\r\u0004\u001fQ\u0005\r\u0012\u0011F\u0019\u0007G9\f\u0018Q\u0005:2\r\rrx0a\nuc\u0015\u0011\u0013EIA\u0002c\t13-\u0001\bnCJ\\'+Z4jgR,'/\u001a3\u0015\u0005\u0005=\u0002cA\u0011\u00022%\u0019\u00111\u0007\u0012\u0003\tUs\u0017\u000e^\u0001\u0014g\u0016$X*\u001a;sS\u000e\u001c\u0018I\u001c3O_RLg-\u001f\u000b\u0005\u0003s\ty\u0004E\u0002\"\u0003wI1!!\u0010#\u0005\u001d\u0011un\u001c7fC:Dq!!\u0011\u0010\u0001\u0004\t\u0019%A\u0004nKR\u0014\u0018nY:\u0011\u0007]\n)%C\u0002\u0002HY\u00111AU8x\u0003-y%m]3sm\u0006$\u0018n\u001c8\u0011\u0005]\n2CA\t!)\t\tY%A\u0003baBd\u0017\u0010F\u00027\u0003+BQA\n\u000bA\u0002!\u0002"
)
public class Observation {
   private final String name;
   private final AtomicBoolean isRegistered;
   private final Promise promise;
   private final Future future;

   public static Observation apply(final String name) {
      return Observation$.MODULE$.apply(name);
   }

   public static Observation apply() {
      return Observation$.MODULE$.apply();
   }

   public String name() {
      return this.name;
   }

   private AtomicBoolean isRegistered() {
      return this.isRegistered;
   }

   private Promise promise() {
      return this.promise;
   }

   public Future future() {
      return this.future;
   }

   public Map get() throws InterruptedException {
      return (Map).MODULE$.awaitResult(this.future(), scala.concurrent.duration.Duration..MODULE$.Inf());
   }

   public java.util.Map getAsJava() throws InterruptedException {
      return scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(this.get()).asJava();
   }

   public Map getOrEmpty() throws InterruptedException {
      return (Map)scala.util.Try..MODULE$.apply(() -> (Map).MODULE$.awaitResult(this.future(), (new scala.concurrent.duration.package.DurationInt(scala.concurrent.duration.package..MODULE$.DurationInt(100))).millis())).getOrElse(() -> scala.Predef..MODULE$.Map().empty());
   }

   public void markRegistered() {
      if (!this.isRegistered().compareAndSet(false, true)) {
         throw new IllegalArgumentException("An Observation can be used with a Dataset only once");
      }
   }

   public boolean setMetricsAndNotify(final Row metrics) {
      Map metricsMap = metrics.getValuesMap((Seq)metrics.schema().map((x$1) -> x$1.name()));
      return this.promise().trySuccess(metricsMap);
   }

   public Observation(final String name) {
      this.name = name;
      scala.Predef..MODULE$.require(scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(name)), () -> "Name must not be empty");
      this.isRegistered = new AtomicBoolean();
      this.promise = scala.concurrent.Promise..MODULE$.apply();
      this.future = this.promise().future();
   }

   public Observation() {
      this(UUID.randomUUID().toString());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
