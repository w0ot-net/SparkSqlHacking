package org.apache.spark.streaming.dstream;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.rdd.RDD;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.Time$;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.StrictOptimizedMapOps;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashMap;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]c!B\n\u0015\u0001Yq\u0002\u0002C\u000b\u0001\u0005\u0003\u0005\u000b\u0011B\u001d\t\u0011!\u0003!1!Q\u0001\f%CQa\u0014\u0001\u0005\u0002ACq!\u0016\u0001C\u0002\u0013Ea\u000b\u0003\u0004d\u0001\u0001\u0006Ia\u0016\u0005\bI\u0002\u0001\r\u0011\"\u0003f\u0011\u001dy\u0007\u00011A\u0005\nADaA\u001e\u0001!B\u00131\u0007bB>\u0001\u0001\u0004%I\u0001 \u0005\b}\u0002\u0001\r\u0011\"\u0003\u0000\u0011\u001d\t\u0019\u0001\u0001Q!\nuDq!a\u0002\u0001\t#1R\rC\u0004\u0002\n\u0001!\t!a\u0003\t\u000f\u0005E\u0001\u0001\"\u0001\u0002\u0014!9\u0011q\u0003\u0001\u0005\u0002\u0005e\u0001bBA\u000e\u0001\u0011\u0005\u0013Q\u0004\u0005\b\u0003?\u0001A\u0011BA\u0011\u0011\u001d\t9\u0005\u0001C\u0005\u0003\u0013\u0012Q\u0003R*ue\u0016\fWn\u00115fG.\u0004x.\u001b8u\t\u0006$\u0018M\u0003\u0002\u0016-\u00059Am\u001d;sK\u0006l'BA\f\u0019\u0003%\u0019HO]3b[&twM\u0003\u0002\u001a5\u0005)1\u000f]1sW*\u00111\u0004H\u0001\u0007CB\f7\r[3\u000b\u0003u\t1a\u001c:h+\tyrh\u0005\u0003\u0001A\u0019\u001a\u0004CA\u0011%\u001b\u0005\u0011#\"A\u0012\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0015\u0012#AB!osJ+g\r\u0005\u0002(a9\u0011\u0001F\f\b\u0003S5j\u0011A\u000b\u0006\u0003W1\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002G%\u0011qFI\u0001\ba\u0006\u001c7.Y4f\u0013\t\t$G\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u00020EA\u0011AgN\u0007\u0002k)\u0011a\u0007G\u0001\tS:$XM\u001d8bY&\u0011\u0001(\u000e\u0002\b\u0019><w-\u001b8h!\rQ4(P\u0007\u0002)%\u0011A\b\u0006\u0002\b\tN#(/Z1n!\tqt\b\u0004\u0001\u0005\u000b\u0001\u0003!\u0019A!\u0003\u0003Q\u000b\"AQ#\u0011\u0005\u0005\u001a\u0015B\u0001##\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!\t$\n\u0005\u001d\u0013#aA!os\u0006QQM^5eK:\u001cW\rJ\u0019\u0011\u0007)kU(D\u0001L\u0015\ta%%A\u0004sK\u001adWm\u0019;\n\u00059[%\u0001C\"mCN\u001cH+Y4\u0002\rqJg.\u001b;?)\t\tF\u000b\u0006\u0002S'B\u0019!\bA\u001f\t\u000b!\u001b\u00019A%\t\u000bU\u0019\u0001\u0019A\u001d\u0002\t\u0011\fG/Y\u000b\u0002/B!\u0001,X0!\u001b\u0005I&B\u0001.\\\u0003\u001diW\u000f^1cY\u0016T!\u0001\u0018\u0012\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002_3\n9\u0001*Y:i\u001b\u0006\u0004\bC\u00011b\u001b\u00051\u0012B\u00012\u0017\u0005\u0011!\u0016.\\3\u0002\u000b\u0011\fG/\u0019\u0011\u0002)QLW.\u001a+p\u0007\",7m\u001b9pS:$h)\u001b7f+\u00051\u0007\u0003\u0002-^?\u001e\u0004\"\u0001\u001b7\u000f\u0005%T\u0007CA\u0015#\u0013\tY'%\u0001\u0004Qe\u0016$WMZ\u0005\u0003[:\u0014aa\u0015;sS:<'BA6#\u0003a!\u0018.\\3U_\u000eCWmY6q_&tGOR5mK~#S-\u001d\u000b\u0003cR\u0004\"!\t:\n\u0005M\u0014#\u0001B+oSRDq!^\u0004\u0002\u0002\u0003\u0007a-A\u0002yIE\nQ\u0003^5nKR{7\t[3dWB|\u0017N\u001c;GS2,\u0007\u0005\u000b\u0002\tqB\u0011\u0011%_\u0005\u0003u\n\u0012\u0011\u0002\u001e:b]NLWM\u001c;\u0002=QLW.\u001a+p\u001f2$Wm\u001d;DQ\u0016\u001c7\u000e]8j]R4\u0015\u000e\\3US6,W#A?\u0011\takvlX\u0001#i&lW\rV8PY\u0012,7\u000f^\"iK\u000e\\\u0007o\\5oi\u001aKG.\u001a+j[\u0016|F%Z9\u0015\u0007E\f\t\u0001C\u0004v\u0015\u0005\u0005\t\u0019A?\u0002?QLW.\u001a+p\u001f2$Wm\u001d;DQ\u0016\u001c7\u000e]8j]R4\u0015\u000e\\3US6,\u0007\u0005\u000b\u0002\fq\u000612-\u001e:sK:$8\t[3dWB|\u0017N\u001c;GS2,7/\u0001\u0004va\u0012\fG/\u001a\u000b\u0004c\u00065\u0001BBA\b\u001b\u0001\u0007q,\u0001\u0003uS6,\u0017aB2mK\u0006tW\u000f\u001d\u000b\u0004c\u0006U\u0001BBA\b\u001d\u0001\u0007q,A\u0004sKN$xN]3\u0015\u0003E\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002O\u0006YqO]5uK>\u0013'.Z2u)\r\t\u00181\u0005\u0005\b\u0003K\t\u0002\u0019AA\u0014\u0003\rywn\u001d\t\u0005\u0003S\t\u0019$\u0004\u0002\u0002,)!\u0011QFA\u0018\u0003\tIwN\u0003\u0002\u00022\u0005!!.\u0019<b\u0013\u0011\t)$a\u000b\u0003%=\u0013'.Z2u\u001fV$\b/\u001e;TiJ,\u0017-\u001c\u0015\u0006#\u0005e\u0012Q\t\t\u0006C\u0005m\u0012qH\u0005\u0004\u0003{\u0011#A\u0002;ie><8\u000f\u0005\u0003\u0002*\u0005\u0005\u0013\u0002BA\"\u0003W\u00111\"S(Fq\u000e,\u0007\u000f^5p]\u000e\u0012\u0011qH\u0001\u000be\u0016\fGm\u00142kK\u000e$HcA9\u0002L!9\u0011Q\n\nA\u0002\u0005=\u0013aA8jgB!\u0011\u0011FA)\u0013\u0011\t\u0019&a\u000b\u0003#=\u0013'.Z2u\u0013:\u0004X\u000f^*ue\u0016\fW\u000eK\u0003\u0013\u0003s\t)\u0005"
)
public class DStreamCheckpointData implements Serializable, Logging {
   private final DStream dstream;
   private final ClassTag evidence$1;
   private final HashMap data;
   private transient HashMap timeToCheckpointFile;
   private transient HashMap timeToOldestCheckpointFileTime;
   private transient Logger org$apache$spark$internal$Logging$$log_;

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

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public HashMap data() {
      return this.data;
   }

   private HashMap timeToCheckpointFile() {
      return this.timeToCheckpointFile;
   }

   private void timeToCheckpointFile_$eq(final HashMap x$1) {
      this.timeToCheckpointFile = x$1;
   }

   private HashMap timeToOldestCheckpointFileTime() {
      return this.timeToOldestCheckpointFileTime;
   }

   private void timeToOldestCheckpointFileTime_$eq(final HashMap x$1) {
      this.timeToOldestCheckpointFileTime = x$1;
   }

   public HashMap currentCheckpointFiles() {
      return this.data();
   }

   public void update(final Time time) {
      HashMap checkpointFiles = (HashMap)((StrictOptimizedMapOps)this.dstream.generatedRDDs().filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$update$1(x$1)))).map((x) -> new Tuple2(x._1(), ((RDD)x._2()).getCheckpointFile().get()));
      this.logDebug((Function0)(() -> {
         Seq var10000 = checkpointFiles.toSeq();
         return "Current checkpoint files:\n" + var10000.mkString("\n");
      }));
      if (!checkpointFiles.isEmpty()) {
         this.currentCheckpointFiles().clear();
         this.currentCheckpointFiles().$plus$plus$eq(checkpointFiles);
         this.timeToCheckpointFile().$plus$plus$eq(this.currentCheckpointFiles());
         this.timeToOldestCheckpointFileTime().update(time, this.currentCheckpointFiles().keys().min(Time$.MODULE$.ordering()));
      }
   }

   public void cleanup(final Time time) {
      Option var3 = this.timeToOldestCheckpointFileTime().remove(time);
      if (var3 instanceof Some var4) {
         Time lastCheckpointFileTime = (Time)var4.value();
         HashMap filesToDelete = (HashMap)this.timeToCheckpointFile().filter((x$2) -> BoxesRunTime.boxToBoolean($anonfun$cleanup$1(lastCheckpointFileTime, x$2)));
         this.logDebug((Function0)(() -> "Files to delete:\n" + filesToDelete.mkString(",")));
         ObjectRef fileSystem = ObjectRef.create((Object)null);
         filesToDelete.foreach((x0$1) -> {
            if (x0$1 != null) {
               Time time = (Time)x0$1._1();
               String file = (String)x0$1._2();

               Object var10000;
               try {
                  Path path = new Path(file);
                  if ((FileSystem)fileSystem.elem == null) {
                     fileSystem.elem = path.getFileSystem(this.dstream.ssc().sparkContext().hadoopConfiguration());
                  }

                  if (((FileSystem)fileSystem.elem).delete(path, true)) {
                     this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Deleted checkpoint file ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_NAME..MODULE$, file)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"for time ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME..MODULE$, time)}))))));
                  } else {
                     this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error deleting old checkpoint file '", "' for time "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, file)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME..MODULE$, time)}))))));
                  }

                  var10000 = this.timeToCheckpointFile().$minus$eq(time);
               } catch (Exception var9) {
                  this.logWarning((LogEntry).MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error deleting old checkpoint file '", "' for time "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, file)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME..MODULE$, time)}))))), var9);
                  fileSystem.elem = null;
                  var10000 = BoxedUnit.UNIT;
               }

               return var10000;
            } else {
               throw new MatchError(x0$1);
            }
         });
         BoxedUnit var8 = BoxedUnit.UNIT;
      } else if (scala.None..MODULE$.equals(var3)) {
         this.logDebug((Function0)(() -> "Nothing to delete"));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(var3);
      }
   }

   public void restore() {
      this.currentCheckpointFiles().foreach((x0$1) -> {
         if (x0$1 != null) {
            Time time = (Time)x0$1._1();
            String file = (String)x0$1._2();
            this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Restoring checkpointed RDD for time ", " from file "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME..MODULE$, time)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"'", "'"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_NAME..MODULE$, file)}))))));
            return (HashMap)this.dstream.generatedRDDs().$plus$eq(new Tuple2(time, this.dstream.context().sparkContext().checkpointFile(file, this.evidence$1)));
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   public String toString() {
      int var10000 = this.currentCheckpointFiles().size();
      return "[\n" + var10000 + " checkpoint files \n" + this.currentCheckpointFiles().mkString("\n") + "\n]";
   }

   private void writeObject(final ObjectOutputStream oos) throws IOException {
      org.apache.spark.util.Utils..MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         this.logDebug((Function0)(() -> this.getClass().getSimpleName() + ".writeObject used"));
         if (this.dstream.context().graph() != null) {
            synchronized(this.dstream.context().graph()){}

            try {
               if (!this.dstream.context().graph().checkpointInProgress()) {
                  String msg = "Object of " + this.getClass().getName() + " is being serialized  possibly as a part of closure of an RDD operation. This is because  the DStream object is being referred to from within the closure.  Please rewrite the RDD operation inside this DStream to avoid this.  This has been enforced to avoid bloating of Spark tasks  with unnecessary objects.";
                  throw new NotSerializableException(msg);
               }

               oos.defaultWriteObject();
            } catch (Throwable var5) {
               throw var5;
            }

         } else {
            throw new NotSerializableException("Graph is unexpectedly null when DStream is being serialized.");
         }
      });
   }

   private void readObject(final ObjectInputStream ois) throws IOException {
      org.apache.spark.util.Utils..MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         this.logDebug((Function0)(() -> this.getClass().getSimpleName() + ".readObject used"));
         ois.defaultReadObject();
         this.timeToOldestCheckpointFileTime_$eq(new HashMap());
         this.timeToCheckpointFile_$eq(new HashMap());
      });
   }

   // $FF: synthetic method
   public static final boolean $anonfun$update$1(final Tuple2 x$1) {
      return ((RDD)x$1._2()).getCheckpointFile().isDefined();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$cleanup$1(final Time lastCheckpointFileTime$1, final Tuple2 x$2) {
      return ((Time)x$2._1()).$less(lastCheckpointFileTime$1);
   }

   public DStreamCheckpointData(final DStream dstream, final ClassTag evidence$1) {
      this.dstream = dstream;
      this.evidence$1 = evidence$1;
      Logging.$init$(this);
      this.data = new HashMap();
      this.timeToCheckpointFile = new HashMap();
      this.timeToOldestCheckpointFileTime = new HashMap();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
