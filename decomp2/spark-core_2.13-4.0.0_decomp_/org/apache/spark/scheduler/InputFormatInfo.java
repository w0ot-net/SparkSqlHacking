package org.apache.spark.scheduler;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.ReflectionUtils;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.deploy.SparkHadoopUtil$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.ArrayBuffer;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005}c\u0001B\r\u001b\u0001\rB\u0001\u0002\r\u0001\u0003\u0006\u0004%\t!\r\u0005\tu\u0001\u0011\t\u0011)A\u0005e!A1\b\u0001BC\u0002\u0013\u0005A\b\u0003\u0005N\u0001\t\u0005\t\u0015!\u0003>\u0011!)\u0006A!b\u0001\n\u00031\u0006\u0002\u0003.\u0001\u0005\u0003\u0005\u000b\u0011B,\t\u000bm\u0003A\u0011\u0001/\t\u000f\u0019\u0004\u0001\u0019!C\u0001O\"91\u000e\u0001a\u0001\n\u0003a\u0007B\u0002:\u0001A\u0003&\u0001\u000eC\u0004t\u0001\u0001\u0007I\u0011A4\t\u000fQ\u0004\u0001\u0019!C\u0001k\"1q\u000f\u0001Q!\n!DQ\u0001\u001f\u0001\u0005BeDQA\u001f\u0001\u0005BmDaa \u0001\u0005B\u0005\u0005\u0001bBA\u0004\u0001\u0011%\u0011\u0011\u0002\u0005\b\u0003\u0017\u0001A\u0011BA\u0007\u0011\u001d\t)\u0003\u0001C\u0005\u0003\u001bAq!a\n\u0001\t\u0013\tiaB\u0004\u00028iA\t!!\u000f\u0007\reQ\u0002\u0012AA\u001e\u0011\u0019Yf\u0003\"\u0001\u0002>!9\u0011q\b\f\u0005\u0002\u0005\u0005#aD%oaV$hi\u001c:nCRLeNZ8\u000b\u0005ma\u0012!C:dQ\u0016$W\u000f\\3s\u0015\tib$A\u0003ta\u0006\u00148N\u0003\u0002 A\u00051\u0011\r]1dQ\u0016T\u0011!I\u0001\u0004_J<7\u0001A\n\u0004\u0001\u0011R\u0003CA\u0013)\u001b\u00051#\"A\u0014\u0002\u000bM\u001c\u0017\r\\1\n\u0005%2#AB!osJ+g\r\u0005\u0002,]5\tAF\u0003\u0002.9\u0005A\u0011N\u001c;fe:\fG.\u0003\u00020Y\t9Aj\\4hS:<\u0017!D2p]\u001aLw-\u001e:bi&|g.F\u00013!\t\u0019\u0004(D\u00015\u0015\t)d'\u0001\u0003d_:4'BA\u001c\u001f\u0003\u0019A\u0017\rZ8pa&\u0011\u0011\b\u000e\u0002\u000e\u0007>tg-[4ve\u0006$\u0018n\u001c8\u0002\u001d\r|gNZ5hkJ\fG/[8oA\u0005\u0001\u0012N\u001c9vi\u001a{'/\\1u\u00072\f'P_\u000b\u0002{A\u0012ah\u0013\t\u0004\u007f\u0019KeB\u0001!E!\t\te%D\u0001C\u0015\t\u0019%%\u0001\u0004=e>|GOP\u0005\u0003\u000b\u001a\na\u0001\u0015:fI\u00164\u0017BA$I\u0005\u0015\u0019E.Y:t\u0015\t)e\u0005\u0005\u0002K\u00172\u0001A!\u0003'\u0005\u0003\u0003\u0005\tQ!\u0001O\u0005\ryF%M\u0001\u0012S:\u0004X\u000f\u001e$pe6\fGo\u00117buj\u0004\u0013CA(S!\t)\u0003+\u0003\u0002RM\t9aj\u001c;iS:<\u0007CA\u0013T\u0013\t!fEA\u0002B]f\fA\u0001]1uQV\tq\u000b\u0005\u0002@1&\u0011\u0011\f\u0013\u0002\u0007'R\u0014\u0018N\\4\u0002\u000bA\fG\u000f\u001b\u0011\u0002\rqJg.\u001b;?)\u0011iv\fY3\u0011\u0005y\u0003Q\"\u0001\u000e\t\u000bA:\u0001\u0019\u0001\u001a\t\u000bm:\u0001\u0019A11\u0005\t$\u0007cA GGB\u0011!\n\u001a\u0003\n\u0019\u0002\f\t\u0011!A\u0003\u00029CQ!V\u0004A\u0002]\u000bA#\\1qe\u0016$WoY3J]B,HOR8s[\u0006$X#\u00015\u0011\u0005\u0015J\u0017B\u00016'\u0005\u001d\u0011un\u001c7fC:\f\u0001$\\1qe\u0016$WoY3J]B,HOR8s[\u0006$x\fJ3r)\ti\u0007\u000f\u0005\u0002&]&\u0011qN\n\u0002\u0005+:LG\u000fC\u0004r\u0013\u0005\u0005\t\u0019\u00015\u0002\u0007a$\u0013'A\u000bnCB\u0014X\rZ;dK&s\u0007/\u001e;G_Jl\u0017\r\u001e\u0011\u0002#5\f\u0007O]3e\u0013:\u0004X\u000f\u001e$pe6\fG/A\u000bnCB\u0014X\rZ%oaV$hi\u001c:nCR|F%Z9\u0015\u000554\bbB9\r\u0003\u0003\u0005\r\u0001[\u0001\u0013[\u0006\u0004(/\u001a3J]B,HOR8s[\u0006$\b%\u0001\u0005u_N#(/\u001b8h)\u00059\u0016\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003q\u0004\"!J?\n\u0005y4#aA%oi\u00061Q-];bYN$2\u0001[A\u0002\u0011\u0019\t)\u0001\u0005a\u0001%\u0006)q\u000e\u001e5fe\u0006Aa/\u00197jI\u0006$X\rF\u0001n\u0003\u0001\u0002(/\u001a4M_\u000e\u001chI]8n\u001b\u0006\u0004(/\u001a3vG\u0016Le\u000e];u\r>\u0014X.\u0019;\u0015\u0005\u0005=\u0001CBA\t\u00037\ty\"\u0004\u0002\u0002\u0014)!\u0011QCA\f\u0003%IW.\\;uC\ndWMC\u0002\u0002\u001a\u0019\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\ti\"a\u0005\u0003\u0007M+G\u000fE\u0002_\u0003CI1!a\t\u001b\u0005%\u0019\u0006\u000f\\5u\u0013:4w.A\u000fqe\u00164Gj\\2t\rJ|W.T1qe\u0016$\u0017J\u001c9vi\u001a{'/\\1u\u0003Y1\u0017N\u001c3Qe\u00164WM\u001d:fI2{7-\u0019;j_:\u001c\bf\u0001\u0001\u0002,A!\u0011QFA\u001a\u001b\t\tyCC\u0002\u00022q\t!\"\u00198o_R\fG/[8o\u0013\u0011\t)$a\f\u0003\u0019\u0011+g/\u001a7pa\u0016\u0014\u0018\t]5\u0002\u001f%s\u0007/\u001e;G_Jl\u0017\r^%oM>\u0004\"A\u0018\f\u0014\u0005Y!CCAA\u001d\u0003e\u0019w.\u001c9vi\u0016\u0004&/\u001a4feJ,G\rT8dCRLwN\\:\u0015\t\u0005\r\u0013\u0011\n\t\u0007\u007f\u0005\u0015s+a\u0004\n\u0007\u0005\u001d\u0003JA\u0002NCBDq!a\u0013\u0019\u0001\u0004\ti%A\u0004g_Jl\u0017\r^:\u0011\u000b\u0005=\u0013\u0011L/\u000f\t\u0005E\u0013Q\u000b\b\u0004\u0003\u0006M\u0013\"A\u0014\n\u0007\u0005]c%A\u0004qC\u000e\\\u0017mZ3\n\t\u0005m\u0013Q\f\u0002\u0004'\u0016\f(bAA,M\u0001"
)
public class InputFormatInfo implements Logging {
   private final Configuration configuration;
   private final Class inputFormatClazz;
   private final String path;
   private boolean mapreduceInputFormat;
   private boolean mapredInputFormat;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Map computePreferredLocations(final Seq formats) {
      return InputFormatInfo$.MODULE$.computePreferredLocations(formats);
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

   public void withLogContext(final java.util.Map context, final Function0 body) {
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

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public Configuration configuration() {
      return this.configuration;
   }

   public Class inputFormatClazz() {
      return this.inputFormatClazz;
   }

   public String path() {
      return this.path;
   }

   public boolean mapreduceInputFormat() {
      return this.mapreduceInputFormat;
   }

   public void mapreduceInputFormat_$eq(final boolean x$1) {
      this.mapreduceInputFormat = x$1;
   }

   public boolean mapredInputFormat() {
      return this.mapredInputFormat;
   }

   public void mapredInputFormat_$eq(final boolean x$1) {
      this.mapredInputFormat = x$1;
   }

   public String toString() {
      String var10000 = super.toString();
      return "InputFormatInfo " + var10000 + " .. inputFormatClazz " + this.inputFormatClazz() + ", path : " + this.path();
   }

   public int hashCode() {
      int hashCode = this.inputFormatClazz().hashCode();
      hashCode = hashCode * 31 + this.path().hashCode();
      return hashCode;
   }

   public boolean equals(final Object other) {
      if (!(other instanceof InputFormatInfo var4)) {
         return false;
      } else {
         boolean var8;
         label39: {
            label28: {
               Class var10000 = this.inputFormatClazz();
               Class var5 = var4.inputFormatClazz();
               if (var10000 == null) {
                  if (var5 != null) {
                     break label28;
                  }
               } else if (!var10000.equals(var5)) {
                  break label28;
               }

               String var7 = this.path();
               String var6 = var4.path();
               if (var7 == null) {
                  if (var6 == null) {
                     break label39;
                  }
               } else if (var7.equals(var6)) {
                  break label39;
               }
            }

            var8 = false;
            return var8;
         }

         var8 = true;
         return var8;
      }
   }

   private void validate() {
      this.logDebug((Function0)(() -> {
         Class var10000 = this.inputFormatClazz();
         return "validate InputFormatInfo : " + var10000 + ", path  " + this.path();
      }));

      try {
         if (InputFormat.class.isAssignableFrom(this.inputFormatClazz())) {
            this.logDebug((Function0)(() -> "inputformat is from mapreduce package"));
            this.mapreduceInputFormat_$eq(true);
         } else {
            if (!org.apache.hadoop.mapred.InputFormat.class.isAssignableFrom(this.inputFormatClazz())) {
               throw new IllegalArgumentException("Specified inputformat " + this.inputFormatClazz() + " is NOT a supported input format ? does not implement either of the supported hadoop api's");
            }

            this.logDebug((Function0)(() -> "inputformat is from mapred package"));
            this.mapredInputFormat_$eq(true);
         }

      } catch (ClassNotFoundException var2) {
         throw new IllegalArgumentException("Specified inputformat " + this.inputFormatClazz() + " cannot be found ?", var2);
      }
   }

   private Set prefLocsFromMapreduceInputFormat() {
      JobConf conf = new JobConf(this.configuration());
      SparkHadoopUtil$.MODULE$.get().addCredentials(conf);
      FileInputFormat.setInputPaths(conf, this.path());
      InputFormat instance = (InputFormat)ReflectionUtils.newInstance(this.inputFormatClazz(), conf);
      Job job = Job.getInstance(conf);
      ArrayBuffer retval = new ArrayBuffer();
      List list = instance.getSplits(job);
      .MODULE$.ListHasAsScala(list).asScala().foreach((split) -> (ArrayBuffer)retval.$plus$plus$eq(SplitInfo$.MODULE$.toSplitInfo(this.inputFormatClazz(), this.path(), split)));
      return retval.toSet();
   }

   private Set prefLocsFromMapredInputFormat() {
      JobConf jobConf = new JobConf(this.configuration());
      SparkHadoopUtil$.MODULE$.get().addCredentials(jobConf);
      FileInputFormat.setInputPaths(jobConf, this.path());
      org.apache.hadoop.mapred.InputFormat instance = (org.apache.hadoop.mapred.InputFormat)ReflectionUtils.newInstance(this.inputFormatClazz(), jobConf);
      ArrayBuffer retval = new ArrayBuffer();
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])instance.getSplits(jobConf, jobConf.getNumMapTasks())), (elem) -> (ArrayBuffer)retval.$plus$plus$eq(SplitInfo$.MODULE$.toSplitInfo(this.inputFormatClazz(), this.path(), elem)));
      return retval.toSet();
   }

   public Set org$apache$spark$scheduler$InputFormatInfo$$findPreferredLocations() {
      this.logDebug((Function0)(() -> {
         boolean var10000 = this.mapreduceInputFormat();
         return "mapreduceInputFormat : " + var10000 + ", mapredInputFormat : " + this.mapredInputFormat() + ", inputFormatClazz : " + this.inputFormatClazz();
      }));
      if (this.mapreduceInputFormat()) {
         return this.prefLocsFromMapreduceInputFormat();
      } else {
         scala.Predef..MODULE$.assert(this.mapredInputFormat());
         return this.prefLocsFromMapredInputFormat();
      }
   }

   public InputFormatInfo(final Configuration configuration, final Class inputFormatClazz, final String path) {
      this.configuration = configuration;
      this.inputFormatClazz = inputFormatClazz;
      this.path = path;
      Logging.$init$(this);
      this.mapreduceInputFormat = false;
      this.mapredInputFormat = false;
      this.validate();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
