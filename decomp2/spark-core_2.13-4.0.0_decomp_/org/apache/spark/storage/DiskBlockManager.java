package org.apache.spark.storage;

import [Ljava.io.File;;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.apache.spark.SparkConf;
import org.apache.spark.errors.SparkCoreErrors$;
import org.apache.spark.executor.ExecutorExitCode$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.package$;
import org.apache.spark.network.shuffle.ExecutorDiskUtils;
import org.apache.spark.util.ShutdownHookManager$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.StringOps.;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mg!\u0002\u00192\u0001MJ\u0004\u0002\u0003$\u0001\u0005\u0003\u0005\u000b\u0011\u0002%\t\u00111\u0003!\u00111A\u0005\u00025C\u0001\"\u0015\u0001\u0003\u0002\u0004%\tA\u0015\u0005\t1\u0002\u0011\t\u0011)Q\u0005\u001d\"A\u0011\f\u0001B\u0001B\u0003%a\nC\u0003[\u0001\u0011\u00051\f\u0003\u0005b\u0001\t\u0007I\u0011A\u001ac\u0011\u00191\u0007\u0001)A\u0005G\"Aq\r\u0001b\u0001\n\u0003\u0019\u0004\u000e\u0003\u0004u\u0001\u0001\u0006I!\u001b\u0005\tk\u0002\u0011\r\u0011\"\u00014m\"9\u0011q\u0001\u0001!\u0002\u00139\b\"CA\u0005\u0001\t\u0007I\u0011BA\u0006\u0011!\ty\u0001\u0001Q\u0001\n\u00055\u0001\"CA\t\u0001\t\u0007I\u0011BA\n\u0011!\ty\u0002\u0001Q\u0001\n\u0005U\u0001\"CA\u0011\u0001\t\u0007I\u0011BA\u0012\u0011\u001d\t)\u0003\u0001Q\u0001\niB\u0001\"a\n\u0001\u0005\u0004%I!\u0014\u0005\b\u0003S\u0001\u0001\u0015!\u0003O\u0011\u001d\tY\u0003\u0001C\u0001\u0003[Aq!a\u000b\u0001\t\u0003\t\u0019\u0004C\u0004\u0002@\u0001!\t!!\u0011\t\u000f\u0005}\u0002\u0001\"\u0003\u0002P!9\u0011Q\u000b\u0001\u0005\u0002\u0005]\u0003bBA.\u0001\u0011\u0005\u0011Q\f\u0005\b\u0003c\u0002A\u0011AA:\u0011\u001d\t9\b\u0001C\u0001\u0003sBq!a \u0001\t\u0003\t\t\tC\u0004\u0002\u0006\u0002!\t!a\"\t\u000f\u0005U\u0005\u0001\"\u0001\u0002\u0018\"9\u0011\u0011\u0015\u0001\u0005\n\u0005\r\u0006bBAT\u0001\u0011%\u0011\u0011\u0016\u0005\b\u0003W\u0003A\u0011AAW\u0011\u001d\t\u0019\f\u0001C\u0001\u0003kCq!a.\u0001\t\u0013\tI\f\u0003\u0005\u0002<\u0002!\taMAU\u0011\u001d\ti\f\u0001C\u0005\u0003S;\u0001\"a02\u0011\u0003\u0019\u0014\u0011\u0019\u0004\baEB\taMAb\u0011\u0019Q\u0006\u0006\"\u0001\u0002F\"I\u0011q\u0019\u0015C\u0002\u0013\u0005\u00111\u0003\u0005\t\u0003\u0013D\u0003\u0015!\u0003\u0002\u0016!I\u00111\u001a\u0015C\u0002\u0013\u0005\u00111\u0003\u0005\t\u0003\u001bD\u0003\u0015!\u0003\u0002\u0016!I\u0011q\u001a\u0015C\u0002\u0013\u0005\u00111\u0003\u0005\t\u0003#D\u0003\u0015!\u0003\u0002\u0016\t\u0001B)[:l\u00052|7m['b]\u0006<WM\u001d\u0006\u0003eM\nqa\u001d;pe\u0006<WM\u0003\u00025k\u0005)1\u000f]1sW*\u0011agN\u0001\u0007CB\f7\r[3\u000b\u0003a\n1a\u001c:h'\r\u0001!\b\u0011\t\u0003wyj\u0011\u0001\u0010\u0006\u0002{\u0005)1oY1mC&\u0011q\b\u0010\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\u0005#U\"\u0001\"\u000b\u0005\r\u001b\u0014\u0001C5oi\u0016\u0014h.\u00197\n\u0005\u0015\u0013%a\u0002'pO\u001eLgnZ\u0001\u0005G>tgm\u0001\u0001\u0011\u0005%SU\"A\u001a\n\u0005-\u001b$!C*qCJ\\7i\u001c8g\u0003E!W\r\\3uK\u001aKG.Z:P]N#x\u000e]\u000b\u0002\u001dB\u00111hT\u0005\u0003!r\u0012qAQ8pY\u0016\fg.A\u000beK2,G/\u001a$jY\u0016\u001cxJ\\*u_B|F%Z9\u0015\u0005M3\u0006CA\u001eU\u0013\t)FH\u0001\u0003V]&$\bbB,\u0004\u0003\u0003\u0005\rAT\u0001\u0004q\u0012\n\u0014A\u00053fY\u0016$XMR5mKN|en\u0015;pa\u0002\n\u0001\"[:Ee&4XM]\u0001\u0007y%t\u0017\u000e\u001e \u0015\tqsv\f\u0019\t\u0003;\u0002i\u0011!\r\u0005\u0006\r\u001a\u0001\r\u0001\u0013\u0005\u0006\u0019\u001a\u0001\rA\u0014\u0005\u00063\u001a\u0001\rAT\u0001\u0013gV\u0014G)\u001b:t!\u0016\u0014Hj\\2bY\u0012K'/F\u0001d!\tYD-\u0003\u0002fy\t\u0019\u0011J\u001c;\u0002'M,(\rR5sgB+'\u000fT8dC2$\u0015N\u001d\u0011\u0002\u00131|7-\u00197ESJ\u001cX#A5\u0011\u0007mRG.\u0003\u0002ly\t)\u0011I\u001d:bsB\u0011QN]\u0007\u0002]*\u0011q\u000e]\u0001\u0003S>T\u0011!]\u0001\u0005U\u00064\u0018-\u0003\u0002t]\n!a)\u001b7f\u0003)awnY1m\t&\u00148\u000fI\u0001\u0010Y>\u001c\u0017\r\u001c#jeN\u001cFO]5oOV\tq\u000fE\u0002<Ub\u00042!_A\u0001\u001d\tQh\u0010\u0005\u0002|y5\tAP\u0003\u0002~\u000f\u00061AH]8pizJ!a \u001f\u0002\rA\u0013X\rZ3g\u0013\u0011\t\u0019!!\u0002\u0003\rM#(/\u001b8h\u0015\tyH(\u0001\tm_\u000e\fG\u000eR5sgN#(/\u001b8hA\u000591/\u001e2ESJ\u001cXCAA\u0007!\rY$.[\u0001\tgV\u0014G)\u001b:tA\u0005aQ.\u001a:hK\u0012K'OT1nKV\u0011\u0011Q\u0003\t\u0005\u0003/\ti\"\u0004\u0002\u0002\u001a)\u0019\u00111\u00049\u0002\t1\fgnZ\u0005\u0005\u0003\u0007\tI\"A\u0007nKJ<W\rR5s\u001d\u0006lW\rI\u0001\rg\",H\u000fZ8x]\"{wn[\u000b\u0002u\u0005i1\u000f[;uI><h\u000eS8pW\u0002\n!\u0004]3s[&\u001c8/[8o\u0007\"\fgnZ5oOJ+\u0017/^5sK\u0012\f1\u0004]3s[&\u001c8/[8o\u0007\"\fgnZ5oOJ+\u0017/^5sK\u0012\u0004\u0013aB4fi\u001aKG.\u001a\u000b\u0004Y\u0006=\u0002BBA\u0019+\u0001\u0007\u00010\u0001\u0005gS2,g.Y7f)\ra\u0017Q\u0007\u0005\b\u0003o1\u0002\u0019AA\u001d\u0003\u001d\u0011Gn\\2l\u0013\u0012\u00042!XA\u001e\u0013\r\ti$\r\u0002\b\u00052|7m[%e\u0003Q9W\r^'fe\u001e,Gm\u00155vM\u001adWMR5mKR)A.a\u0011\u0002F!9\u0011qG\fA\u0002\u0005e\u0002bBA$/\u0001\u0007\u0011\u0011J\u0001\u0005I&\u00148\u000f\u0005\u0003<\u0003\u0017:\u0018bAA'y\t1q\n\u001d;j_:$R\u0001\\A)\u0003'Ba!!\r\u0019\u0001\u0004A\bbBA$1\u0001\u0007\u0011\u0011J\u0001\u000eG>tG/Y5og\ncwnY6\u0015\u00079\u000bI\u0006C\u0004\u00028e\u0001\r!!\u000f\u0002\u0017\u001d,G/\u00117m\r&dWm\u001d\u000b\u0003\u0003?\u0002R!!\u0019\u0002l1tA!a\u0019\u0002h9\u001910!\u001a\n\u0003uJ1!!\u001b=\u0003\u001d\u0001\u0018mY6bO\u0016LA!!\u001c\u0002p\t\u00191+Z9\u000b\u0007\u0005%D(\u0001\u0007hKR\fE\u000e\u001c\"m_\u000e\\7\u000f\u0006\u0002\u0002vA1\u0011\u0011MA6\u0003s\tqc\u0019:fCR,wk\u001c:mIJ+\u0017\rZ1cY\u00164\u0015\u000e\\3\u0015\u0007M\u000bY\b\u0003\u0004\u0002~q\u0001\r\u0001\\\u0001\u0005M&dW-\u0001\nde\u0016\fG/\u001a+f[B4\u0015\u000e\\3XSRDGc\u00017\u0002\u0004\"1\u0011QP\u000fA\u00021\fAc\u0019:fCR,G+Z7q\u0019>\u001c\u0017\r\u001c\"m_\u000e\\GCAAE!\u0019Y\u00141RAHY&\u0019\u0011Q\u0012\u001f\u0003\rQ+\b\u000f\\33!\ri\u0016\u0011S\u0005\u0004\u0003'\u000b$\u0001\u0005+f[BdunY1m\u00052|7m[%e\u0003Y\u0019'/Z1uKR+W\u000e]*ik\u001a4G.\u001a\"m_\u000e\\GCAAM!\u0019Y\u00141RANYB\u0019Q,!(\n\u0007\u0005}\u0015G\u0001\nUK6\u00048\u000b[;gM2,'\t\\8dW&#\u0017aD2sK\u0006$X\rT8dC2$\u0015N]:\u0015\u0007%\f)\u000bC\u0003GA\u0001\u0007\u0001*A\u0013de\u0016\fG/\u001a'pG\u0006dG)\u001b:t\r>\u0014X*\u001a:hK\u0012\u001c\u0006.\u001e4gY\u0016\u0014En\\2lgR\t1+\u0001\u000ede\u0016\fG/\u001a#je^KG\u000f\u001b)fe6L7o]5p]^:\u0004\u0007F\u0002T\u0003_Ca!!-#\u0001\u0004a\u0017a\u00033jeR{7I]3bi\u0016\fqeZ3u\u001b\u0016\u0014x-\u001a#je\u0016\u001cGo\u001c:z\u0003:$\u0017\t\u001e;f[B$\u0018\n\u0012&t_:\u001cFO]5oOR\t\u00010A\bbI\u0012\u001c\u0006.\u001e;e_^t\u0007j\\8l)\u0005Q\u0014\u0001B:u_B\fa\u0001Z8Ti>\u0004\u0018\u0001\u0005#jg.\u0014En\\2l\u001b\u0006t\u0017mZ3s!\ti\u0006f\u0005\u0002)uQ\u0011\u0011\u0011Y\u0001\u0010\u001b\u0016\u0013v)R0E\u0013J+5\tV(S3\u0006\u0001R*\u0012*H\u000b~#\u0015JU#D)>\u0013\u0016\fI\u0001\u000e\u001b\u0016\u0013v)R0E\u0013J{6*R-\u0002\u001d5+%kR#`\t&\u0013vlS#ZA\u0005q\u0011\t\u0016+F\u001bB#v,\u0013#`\u0017\u0016K\u0016aD!U)\u0016k\u0005\u000bV0J\t~[U)\u0017\u0011"
)
public class DiskBlockManager implements Logging {
   private final SparkConf conf;
   private boolean deleteFilesOnStop;
   private final boolean isDriver;
   private final int subDirsPerLocalDir;
   private final File[] localDirs;
   private final String[] localDirsString;
   private final File[][] subDirs;
   private final String mergeDirName;
   private final Object shutdownHook;
   private final boolean permissionChangingRequired;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static String ATTEMPT_ID_KEY() {
      return DiskBlockManager$.MODULE$.ATTEMPT_ID_KEY();
   }

   public static String MERGE_DIR_KEY() {
      return DiskBlockManager$.MODULE$.MERGE_DIR_KEY();
   }

   public static String MERGE_DIRECTORY() {
      return DiskBlockManager$.MODULE$.MERGE_DIRECTORY();
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

   public boolean deleteFilesOnStop() {
      return this.deleteFilesOnStop;
   }

   public void deleteFilesOnStop_$eq(final boolean x$1) {
      this.deleteFilesOnStop = x$1;
   }

   public int subDirsPerLocalDir() {
      return this.subDirsPerLocalDir;
   }

   public File[] localDirs() {
      return this.localDirs;
   }

   public String[] localDirsString() {
      return this.localDirsString;
   }

   private File[][] subDirs() {
      return this.subDirs;
   }

   private String mergeDirName() {
      return this.mergeDirName;
   }

   private Object shutdownHook() {
      return this.shutdownHook;
   }

   private boolean permissionChangingRequired() {
      return this.permissionChangingRequired;
   }

   public File getFile(final String filename) {
      int hash = Utils$.MODULE$.nonNegativeHash(filename);
      int dirId = hash % this.localDirs().length;
      int subDirId = hash / this.localDirs().length % this.subDirsPerLocalDir();
      synchronized(this.subDirs()[dirId]){}

      File var7;
      try {
         File old = this.subDirs()[dirId][subDirId];
         File var10000;
         if (old != null) {
            var10000 = old;
         } else {
            File newDir = new File(this.localDirs()[dirId], .MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%02x"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(subDirId)})));
            if (!newDir.exists()) {
               Path path = newDir.toPath();
               Files.createDirectory(path);
               if (this.permissionChangingRequired()) {
                  Set currentPerms = Files.getPosixFilePermissions(path);
                  currentPerms.add(PosixFilePermission.GROUP_WRITE);
                  Files.setPosixFilePermissions(path, currentPerms);
               } else {
                  BoxedUnit var14 = BoxedUnit.UNIT;
               }
            } else {
               BoxedUnit var15 = BoxedUnit.UNIT;
            }

            this.subDirs()[dirId][subDirId] = newDir;
            var10000 = newDir;
         }

         var7 = var10000;
      } catch (Throwable var13) {
         throw var13;
      }

      return new File(var7, filename);
   }

   public File getFile(final BlockId blockId) {
      return this.getFile(blockId.name());
   }

   public File getMergedShuffleFile(final BlockId blockId, final Option dirs) {
      if (blockId instanceof ShuffleMergedDataBlockId var5) {
         return this.getMergedShuffleFile(var5.name(), dirs);
      } else if (blockId instanceof ShuffleMergedIndexBlockId var6) {
         return this.getMergedShuffleFile(var6.name(), dirs);
      } else if (blockId instanceof ShuffleMergedMetaBlockId var7) {
         return this.getMergedShuffleFile(var7.name(), dirs);
      } else {
         throw org.apache.spark.SparkException..MODULE$.internalError("Only merged block ID is supported, but got " + blockId, "STORAGE");
      }
   }

   private File getMergedShuffleFile(final String filename, final Option dirs) {
      if (!dirs.exists((x$2) -> BoxesRunTime.boxToBoolean($anonfun$getMergedShuffleFile$1(x$2)))) {
         throw org.apache.spark.SparkException..MODULE$.internalError("Cannot read " + filename + " because merged shuffle dirs is empty", "STORAGE");
      } else {
         return new File(ExecutorDiskUtils.getFilePath((String[])dirs.get(), this.subDirsPerLocalDir(), filename));
      }
   }

   public boolean containsBlock(final BlockId blockId) {
      return this.getFile(blockId.name()).exists();
   }

   public Seq getAllFiles() {
      return org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.subDirs()), (dir) -> {
         synchronized(dir){}

         File[] var2;
         try {
            var2 = (File[])((File;)dir).clone();
         } catch (Throwable var4) {
            throw var4;
         }

         return var2;
      }, (xs) -> scala.Predef..MODULE$.wrapRefArray((Object[])xs), scala.reflect.ClassTag..MODULE$.apply(File.class))), (x$3) -> BoxesRunTime.boxToBoolean($anonfun$getAllFiles$3(x$3)))), (dir) -> {
         File[] files = dir.listFiles();
         return (Seq)(files != null ? org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(files).toImmutableArraySeq() : (Seq)scala.package..MODULE$.Seq().empty());
      }, scala.reflect.ClassTag..MODULE$.apply(File.class))).toImmutableArraySeq();
   }

   public Seq getAllBlocks() {
      return (Seq)this.getAllFiles().flatMap((f) -> {
         Object var10000;
         try {
            var10000 = new Some(BlockId$.MODULE$.apply(f.getName()));
         } catch (UnrecognizedBlockId var1) {
            var10000 = scala.None..MODULE$;
         }

         return (IterableOnce)var10000;
      });
   }

   public void createWorldReadableFile(final File file) {
      Path path = file.toPath();
      Files.createFile(path);
      Set currentPerms = Files.getPosixFilePermissions(path);
      currentPerms.add(PosixFilePermission.OTHERS_READ);
      Files.setPosixFilePermissions(path, currentPerms);
   }

   public File createTempFileWith(final File file) {
      File tmpFile = Utils$.MODULE$.tempFileWith(file);
      if (this.permissionChangingRequired()) {
         this.createWorldReadableFile(tmpFile);
      }

      return tmpFile;
   }

   public Tuple2 createTempLocalBlock() {
      TempLocalBlockId blockId;
      for(blockId = new TempLocalBlockId(UUID.randomUUID()); this.getFile((BlockId)blockId).exists(); blockId = new TempLocalBlockId(UUID.randomUUID())) {
      }

      return new Tuple2(blockId, this.getFile((BlockId)blockId));
   }

   public Tuple2 createTempShuffleBlock() {
      TempShuffleBlockId blockId;
      for(blockId = new TempShuffleBlockId(UUID.randomUUID()); this.getFile((BlockId)blockId).exists(); blockId = new TempShuffleBlockId(UUID.randomUUID())) {
      }

      File tmpFile = this.getFile((BlockId)blockId);
      if (this.permissionChangingRequired()) {
         this.createWorldReadableFile(tmpFile);
      }

      return new Tuple2(blockId, tmpFile);
   }

   private File[] createLocalDirs(final SparkConf conf) {
      return (File[])scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps((Object[])Utils$.MODULE$.getConfiguredLocalDirs(conf)), (rootDir) -> {
         Object var10000;
         try {
            File localDir = Utils$.MODULE$.createDirectory(rootDir, "blockmgr");
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Created local directory at ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, localDir)})))));
            var10000 = new Some(localDir);
         } catch (IOException var4) {
            this.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to create local dir in ", ". Ignoring this directory."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, rootDir)})))), var4);
            var10000 = scala.None..MODULE$;
         }

         return (Option)var10000;
      }, scala.reflect.ClassTag..MODULE$.apply(File.class));
   }

   private void createLocalDirsForMergedShuffleBlocks() {
      if (Utils$.MODULE$.isPushBasedShuffleEnabled(this.conf, this.isDriver, false)) {
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])Utils$.MODULE$.getConfiguredLocalDirs(this.conf)), (rootDir) -> {
            $anonfun$createLocalDirsForMergedShuffleBlocks$1(this, rootDir);
            return BoxedUnit.UNIT;
         });
      }
   }

   public void createDirWithPermission770(final File dirToCreate) {
      int attempts = 0;
      int maxAttempts = Utils$.MODULE$.MAX_DIR_CREATION_ATTEMPTS();
      File created = null;

      while(created == null) {
         ++attempts;
         if (attempts > maxAttempts) {
            throw SparkCoreErrors$.MODULE$.failToCreateDirectoryError(dirToCreate.getAbsolutePath(), maxAttempts);
         }

         try {
            dirToCreate.mkdirs();
            Files.setPosixFilePermissions(dirToCreate.toPath(), PosixFilePermissions.fromString("rwxrwx---"));
            if (dirToCreate.exists()) {
               created = dirToCreate;
            }

            this.logDebug((Function0)(() -> "Created directory at " + dirToCreate.getAbsolutePath() + " with permission 770"));
         } catch (SecurityException var6) {
            this.logWarning((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to create directory ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, dirToCreate.getAbsolutePath())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"with permission 770"})))).log(scala.collection.immutable.Nil..MODULE$))), var6);
            created = null;
         }
      }

   }

   public String getMergeDirectoryAndAttemptIDJsonString() {
      HashMap mergedMetaMap = new HashMap();
      mergedMetaMap.put(DiskBlockManager$.MODULE$.MERGE_DIR_KEY(), this.mergeDirName());
      ((Option)this.conf.get((ConfigEntry)package$.MODULE$.APP_ATTEMPT_ID())).foreach((attemptId) -> mergedMetaMap.put(DiskBlockManager$.MODULE$.ATTEMPT_ID_KEY(), attemptId));
      ObjectMapper mapper = new ObjectMapper();
      mapper.registerModule(com.fasterxml.jackson.module.scala.DefaultScalaModule..MODULE$);
      String jsonString = mapper.writeValueAsString(mergedMetaMap);
      return jsonString;
   }

   private Object addShutdownHook() {
      this.logDebug((Function0)(() -> "Adding shutdown hook"));
      return ShutdownHookManager$.MODULE$.addShutdownHook(ShutdownHookManager$.MODULE$.TEMP_DIR_SHUTDOWN_PRIORITY() + 1, (JFunction0.mcV.sp)() -> {
         this.logInfo((Function0)(() -> "Shutdown hook called"));
         this.doStop();
      });
   }

   public void stop() {
      try {
         BoxesRunTime.boxToBoolean(ShutdownHookManager$.MODULE$.removeShutdownHook(this.shutdownHook()));
      } catch (Exception var2) {
         this.logError((Function0)(() -> "Exception while removing shutdown hook."), var2);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      this.doStop();
   }

   private void doStop() {
      if (this.deleteFilesOnStop()) {
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.localDirs()), (localDir) -> {
            $anonfun$doStop$1(this, localDir);
            return BoxedUnit.UNIT;
         });
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getMergedShuffleFile$1(final String[] x$2) {
      return scala.collection.ArrayOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.refArrayOps((Object[])x$2));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getAllFiles$3(final File x$3) {
      return x$3 != null;
   }

   // $FF: synthetic method
   public static final void $anonfun$createLocalDirsForMergedShuffleBlocks$1(final DiskBlockManager $this, final String rootDir) {
      try {
         File mergeDir = new File(rootDir, $this.mergeDirName());
         if (!mergeDir.exists() || mergeDir.listFiles().length < $this.subDirsPerLocalDir()) {
            $this.logDebug((Function0)(() -> "Try to create " + mergeDir + " and its sub dirs since the " + $this.mergeDirName() + " dir does not exist"));
            scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), $this.subDirsPerLocalDir()).foreach$mVc$sp((JFunction1.mcVI.sp)(dirNum) -> {
               File subDir = new File(mergeDir, .MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%02x"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(dirNum)})));
               if (!subDir.exists()) {
                  $this.createDirWithPermission770(subDir);
               }
            });
         }

         $this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Merge directory and its sub dirs get created at ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, mergeDir)})))));
      } catch (IOException var4) {
         $this.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to create ", " dir in "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MERGE_DIR_NAME..MODULE$, $this.mergeDirName())}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ". Ignoring this directory."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, rootDir)}))))), var4);
      }

   }

   // $FF: synthetic method
   public static final void $anonfun$doStop$1(final DiskBlockManager $this, final File localDir) {
      if (localDir.isDirectory() && localDir.exists()) {
         try {
            if (!ShutdownHookManager$.MODULE$.hasRootAsShutdownDeleteDir(localDir)) {
               Utils$.MODULE$.deleteRecursively(localDir);
            }
         } catch (Exception var3) {
            $this.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Exception while deleting local spark dir: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, localDir)})))), var3);
         }

      }
   }

   public DiskBlockManager(final SparkConf conf, final boolean deleteFilesOnStop, final boolean isDriver) {
      this.conf = conf;
      this.deleteFilesOnStop = deleteFilesOnStop;
      this.isDriver = isDriver;
      super();
      Logging.$init$(this);
      this.subDirsPerLocalDir = BoxesRunTime.unboxToInt(conf.get(package$.MODULE$.DISKSTORE_SUB_DIRECTORIES()));
      this.localDirs = this.createLocalDirs(conf);
      if (scala.collection.ArrayOps..MODULE$.isEmpty$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.localDirs()))) {
         this.logError((Function0)(() -> "Failed to create any local dir."));
         System.exit(ExecutorExitCode$.MODULE$.DISK_STORE_FAILED_TO_CREATE_DIR());
      }

      this.localDirsString = (String[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.localDirs()), (x$1) -> x$1.toString(), scala.reflect.ClassTag..MODULE$.apply(String.class));
      this.subDirs = (File[][])scala.Array..MODULE$.fill(this.localDirs().length, () -> new File[this.subDirsPerLocalDir()], scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(File.class)));
      String var10001 = DiskBlockManager$.MODULE$.MERGE_DIRECTORY();
      this.mergeDirName = var10001 + ((Option)conf.get((ConfigEntry)package$.MODULE$.APP_ATTEMPT_ID())).map((id) -> "_" + id).getOrElse(() -> "");
      this.createLocalDirsForMergedShuffleBlocks();
      this.shutdownHook = this.addShutdownHook();
      this.permissionChangingRequired = BoxesRunTime.unboxToBoolean(conf.get(package$.MODULE$.SHUFFLE_SERVICE_ENABLED())) && (BoxesRunTime.unboxToBoolean(conf.get(package$.MODULE$.SHUFFLE_SERVICE_REMOVE_SHUFFLE_ENABLED())) || BoxesRunTime.unboxToBoolean(conf.get(package$.MODULE$.SHUFFLE_SERVICE_FETCH_RDD_ENABLED())));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
