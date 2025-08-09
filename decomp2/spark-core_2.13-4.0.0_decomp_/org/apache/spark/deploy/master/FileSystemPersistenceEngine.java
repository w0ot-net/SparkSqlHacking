package org.apache.spark.deploy.master;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.invoke.SerializedLambda;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.io.CompressionCodec;
import org.apache.spark.serializer.DeserializationStream;
import org.apache.spark.serializer.SerializationStream;
import org.apache.spark.serializer.Serializer;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rd!B\t\u0013\u0001Ia\u0002\u0002C\u0014\u0001\u0005\u000b\u0007I\u0011A\u0015\t\u0011]\u0002!\u0011!Q\u0001\n)B\u0001\u0002\u000f\u0001\u0003\u0006\u0004%\t!\u000f\u0005\t\u007f\u0001\u0011\t\u0011)A\u0005u!A\u0001\t\u0001BC\u0002\u0013\u0005\u0011\t\u0003\u0005M\u0001\t\u0005\t\u0015!\u0003C\u0011\u0015i\u0005\u0001\"\u0001O\u0011\u0015\u0019\u0006\u0001\"\u0011U\u0011\u0015!\u0007\u0001\"\u0011f\u0011\u00159\u0007\u0001\"\u0011i\u0011\u001d\t\u0019\u0002\u0001C\u0005\u0003+Aq!a\f\u0001\t\u0013\t\td\u0002\u0006\u0002DI\t\t\u0011#\u0001\u0013\u0003\u000b2\u0011\"\u0005\n\u0002\u0002#\u0005!#a\u0012\t\r5sA\u0011AA%\u0011%\tYEDI\u0001\n\u0003\tiEA\u000eGS2,7+_:uK6\u0004VM]:jgR,gnY3F]\u001eLg.\u001a\u0006\u0003'Q\ta!\\1ti\u0016\u0014(BA\u000b\u0017\u0003\u0019!W\r\u001d7ps*\u0011q\u0003G\u0001\u0006gB\f'o\u001b\u0006\u00033i\ta!\u00199bG\",'\"A\u000e\u0002\u0007=\u0014xmE\u0002\u0001;\u0005\u0002\"AH\u0010\u000e\u0003II!\u0001\t\n\u0003#A+'o]5ti\u0016t7-Z#oO&tW\r\u0005\u0002#K5\t1E\u0003\u0002%-\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002'G\t9Aj\\4hS:<\u0017a\u00013je\u000e\u0001Q#\u0001\u0016\u0011\u0005-\"dB\u0001\u00173!\ti\u0003'D\u0001/\u0015\ty\u0003&\u0001\u0004=e>|GO\u0010\u0006\u0002c\u0005)1oY1mC&\u00111\u0007M\u0001\u0007!J,G-\u001a4\n\u0005U2$AB*ue&twM\u0003\u00024a\u0005!A-\u001b:!\u0003)\u0019XM]5bY&TXM]\u000b\u0002uA\u00111(P\u0007\u0002y)\u0011\u0001HF\u0005\u0003}q\u0012!bU3sS\u0006d\u0017N_3s\u0003-\u0019XM]5bY&TXM\u001d\u0011\u0002\u000b\r|G-Z2\u0016\u0003\t\u00032a\u0011#G\u001b\u0005\u0001\u0014BA#1\u0005\u0019y\u0005\u000f^5p]B\u0011qIS\u0007\u0002\u0011*\u0011\u0011JF\u0001\u0003S>L!a\u0013%\u0003!\r{W\u000e\u001d:fgNLwN\\\"pI\u0016\u001c\u0017AB2pI\u0016\u001c\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0005\u001fB\u000b&\u000b\u0005\u0002\u001f\u0001!)qe\u0002a\u0001U!)\u0001h\u0002a\u0001u!9\u0001i\u0002I\u0001\u0002\u0004\u0011\u0015a\u00029feNL7\u000f\u001e\u000b\u0004+bS\u0006CA\"W\u0013\t9\u0006G\u0001\u0003V]&$\b\"B-\t\u0001\u0004Q\u0013\u0001\u00028b[\u0016DQa\u0017\u0005A\u0002q\u000b1a\u001c2k!\ti&-D\u0001_\u0015\ty\u0006-\u0001\u0003mC:<'\"A1\u0002\t)\fg/Y\u0005\u0003Gz\u0013aa\u00142kK\u000e$\u0018!C;oa\u0016\u00148/[:u)\t)f\rC\u0003Z\u0013\u0001\u0007!&\u0001\u0003sK\u0006$WCA5w)\rQ\u0017q\u0002\u000b\u0003W~\u00042\u0001\\9u\u001d\tiwN\u0004\u0002.]&\t\u0011'\u0003\u0002qa\u00059\u0001/Y2lC\u001e,\u0017B\u0001:t\u0005\r\u0019V-\u001d\u0006\u0003aB\u0002\"!\u001e<\r\u0001\u0011)qO\u0003b\u0001q\n\tA+\u0005\u0002zyB\u00111I_\u0005\u0003wB\u0012qAT8uQ&tw\r\u0005\u0002D{&\u0011a\u0010\r\u0002\u0004\u0003:L\b\"CA\u0001\u0015\u0005\u0005\t9AA\u0002\u0003))g/\u001b3f]\u000e,G%\r\t\u0006\u0003\u000b\tY\u0001^\u0007\u0003\u0003\u000fQ1!!\u00031\u0003\u001d\u0011XM\u001a7fGRLA!!\u0004\u0002\b\tA1\t\\1tgR\u000bw\r\u0003\u0004\u0002\u0012)\u0001\rAK\u0001\u0007aJ,g-\u001b=\u0002#M,'/[1mSj,\u0017J\u001c;p\r&dW\rF\u0003V\u0003/\t)\u0003C\u0004\u0002\u001a-\u0001\r!a\u0007\u0002\t\u0019LG.\u001a\t\u0005\u0003;\t\t#\u0004\u0002\u0002 )\u0011\u0011\nY\u0005\u0005\u0003G\tyB\u0001\u0003GS2,\u0007bBA\u0014\u0017\u0001\u0007\u0011\u0011F\u0001\u0006m\u0006dW/\u001a\t\u0004\u0007\u0006-\u0012bAA\u0017a\t1\u0011I\\=SK\u001a\f1\u0003Z3tKJL\u0017\r\\5{K\u001a\u0013x.\u001c$jY\u0016,B!a\r\u0002:Q!\u0011QGA!)\u0011\t9$a\u000f\u0011\u0007U\fI\u0004B\u0003x\u0019\t\u0007\u0001\u0010C\u0004\u0002>1\u0001\u001d!a\u0010\u0002\u00035\u0004b!!\u0002\u0002\f\u0005]\u0002bBA\r\u0019\u0001\u0007\u00111D\u0001\u001c\r&dWmU=ti\u0016l\u0007+\u001a:tSN$XM\\2f\u000b:<\u0017N\\3\u0011\u0005yq1c\u0001\b\u0002*Q\u0011\u0011QI\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001a\u0016\u0005\u0005=#f\u0001\"\u0002R-\u0012\u00111\u000b\t\u0005\u0003+\ny&\u0004\u0002\u0002X)!\u0011\u0011LA.\u0003%)hn\u00195fG.,GMC\u0002\u0002^A\n!\"\u00198o_R\fG/[8o\u0013\u0011\t\t'a\u0016\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\r"
)
public class FileSystemPersistenceEngine extends PersistenceEngine implements Logging {
   private final String dir;
   private final Serializer serializer;
   private final Option codec;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Option $lessinit$greater$default$3() {
      return FileSystemPersistenceEngine$.MODULE$.$lessinit$greater$default$3();
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

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public String dir() {
      return this.dir;
   }

   public Serializer serializer() {
      return this.serializer;
   }

   public Option codec() {
      return this.codec;
   }

   public void persist(final String name, final Object obj) {
      String var10003 = this.dir();
      this.serializeIntoFile(new File(var10003 + File.separator + name), obj);
   }

   public void unpersist(final String name) {
      String var10002 = this.dir();
      File f = new File(var10002 + File.separator + name);
      if (!f.delete()) {
         this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error deleting ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, f.getPath())})))));
      }
   }

   public Seq read(final String prefix, final ClassTag evidence$1) {
      File[] files = (File[])scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])(new File(this.dir())).listFiles()), (x$1) -> BoxesRunTime.boxToBoolean($anonfun$read$1(prefix, x$1)));
      return org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])files), (file) -> this.deserializeFromFile(file, evidence$1), evidence$1)).toImmutableArraySeq();
   }

   private void serializeIntoFile(final File file, final Object value) {
      if (file.exists()) {
         throw new IllegalStateException("File already exists: " + file);
      } else {
         boolean created = file.createNewFile();
         if (!created) {
            throw new IllegalStateException("Could not create file: " + file);
         } else {
            ObjectRef fileOut = ObjectRef.create(new FileOutputStream(file));
            this.codec().foreach((c) -> {
               $anonfun$serializeIntoFile$1(fileOut, c);
               return BoxedUnit.UNIT;
            });
            ObjectRef out = ObjectRef.create((Object)null);
            Utils$.MODULE$.tryWithSafeFinally(() -> {
               out.elem = this.serializer().newInstance().serializeStream((OutputStream)fileOut.elem);
               return ((SerializationStream)out.elem).writeObject(value, scala.reflect.ClassTag..MODULE$.AnyRef());
            }, (JFunction0.mcV.sp)() -> {
               if ((SerializationStream)out.elem != null) {
                  ((SerializationStream)out.elem).close();
               }

               ((OutputStream)fileOut.elem).close();
            });
         }
      }
   }

   private Object deserializeFromFile(final File file, final ClassTag m) {
      ObjectRef fileIn = ObjectRef.create(new FileInputStream(file));
      this.codec().foreach((c) -> {
         $anonfun$deserializeFromFile$1(fileIn, file, c);
         return BoxedUnit.UNIT;
      });
      DeserializationStream in = null;

      Object var10000;
      try {
         in = this.serializer().newInstance().deserializeStream((InputStream)fileIn.elem);
         var10000 = in.readObject(m);
      } finally {
         ((InputStream)fileIn.elem).close();
         if (in != null) {
            in.close();
         }

      }

      return var10000;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$read$1(final String prefix$1, final File x$1) {
      return x$1.getName().startsWith(prefix$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$serializeIntoFile$1(final ObjectRef fileOut$1, final CompressionCodec c) {
      fileOut$1.elem = c.compressedOutputStream((OutputStream)fileOut$1.elem);
   }

   // $FF: synthetic method
   public static final void $anonfun$deserializeFromFile$1(final ObjectRef fileIn$1, final File file$1, final CompressionCodec c) {
      fileIn$1.elem = c.compressedInputStream(new FileInputStream(file$1));
   }

   public FileSystemPersistenceEngine(final String dir, final Serializer serializer, final Option codec) {
      this.dir = dir;
      this.serializer = serializer;
      this.codec = codec;
      Logging.$init$(this);

      try {
         Files.createDirectories(Paths.get(dir));
      } catch (Throwable var7) {
         if (!(var7 instanceof FileAlreadyExistsException) || !Files.isSymbolicLink(Paths.get(dir))) {
            throw var7;
         }

         Files.createDirectories(Paths.get(dir).toRealPath());
      }

   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
