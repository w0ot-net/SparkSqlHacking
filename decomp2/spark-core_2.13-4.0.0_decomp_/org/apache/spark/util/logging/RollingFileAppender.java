package org.apache.spark.util.logging;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.invoke.SerializedLambda;
import java.util.zip.GZIPOutputStream;
import org.apache.commons.io.IOUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.internal.config.package$;
import org.sparkproject.guava.io.Files;
import scala.StringContext;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ed!\u0002\u0010 \u0001\rJ\u0003\u0002\u0003\u0018\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0019\t\u0011a\u0002!\u0011!Q\u0001\neB\u0001\u0002\u0010\u0001\u0003\u0006\u0004%\t!\u0010\u0005\t\u0003\u0002\u0011\t\u0011)A\u0005}!A!\t\u0001B\u0001B\u0003%1\t\u0003\u0005H\u0001\t\u0005\t\u0015!\u0003I\u0011!q\u0005A!A!\u0002\u0013y\u0005\"\u0002*\u0001\t\u0003\u0019\u0006bB.\u0001\u0005\u0004%I\u0001\u0018\u0005\u0007;\u0002\u0001\u000b\u0011\u0002%\t\u000fy\u0003!\u0019!C\u0005?\"1\u0001\r\u0001Q\u0001\n=CQ!\u0019\u0001\u0005B\tDQA\u001a\u0001\u0005R\u001dDQA\u001d\u0001\u0005\n\tDQa\u001d\u0001\u0005\nQDQ\u0001\u001f\u0001\u0005\neDQ\u0001 \u0001\u0005\n\tDa! \u0001\u0005\u0002\u0005\u0012wA\u0002@ \u0011\u0003\u0019sPB\u0004\u001f?!\u00051%!\u0001\t\rI+B\u0011AA\u0005\u0011!\tY!\u0006b\u0001\n\u0003a\u0006bBA\u0007+\u0001\u0006I\u0001\u0013\u0005\n\u0003\u001f)\"\u0019!C\u0001\u0003#A\u0001\"a\b\u0016A\u0003%\u00111\u0003\u0005\b\u0003C)B\u0011AA\u0012\u0011%\t\u0019&FI\u0001\n\u0003\t)\u0006C\u0005\u0002lU\t\n\u0011\"\u0001\u0002n\t\u0019\"k\u001c7mS:<g)\u001b7f\u0003B\u0004XM\u001c3fe*\u0011\u0001%I\u0001\bY><w-\u001b8h\u0015\t\u00113%\u0001\u0003vi&d'B\u0001\u0013&\u0003\u0015\u0019\b/\u0019:l\u0015\t1s%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002Q\u0005\u0019qN]4\u0014\u0005\u0001Q\u0003CA\u0016-\u001b\u0005y\u0012BA\u0017 \u000511\u0015\u000e\\3BaB,g\u000eZ3s\u0003-Ig\u000e];u'R\u0014X-Y7\u0004\u0001A\u0011\u0011GN\u0007\u0002e)\u00111\u0007N\u0001\u0003S>T\u0011!N\u0001\u0005U\u00064\u0018-\u0003\u00028e\tY\u0011J\u001c9viN#(/Z1n\u0003)\t7\r^5wK\u001aKG.\u001a\t\u0003ciJ!a\u000f\u001a\u0003\t\u0019KG.Z\u0001\u000ee>dG.\u001b8h!>d\u0017nY=\u0016\u0003y\u0002\"aK \n\u0005\u0001{\"!\u0004*pY2Lgn\u001a)pY&\u001c\u00170\u0001\bs_2d\u0017N\\4Q_2L7-\u001f\u0011\u0002\t\r|gN\u001a\t\u0003\t\u0016k\u0011aI\u0005\u0003\r\u000e\u0012\u0011b\u00159be.\u001cuN\u001c4\u0002\u0015\t,hMZ3s'&TX\r\u0005\u0002J\u00196\t!JC\u0001L\u0003\u0015\u00198-\u00197b\u0013\ti%JA\u0002J]R\fAb\u00197pg\u0016\u001cFO]3b[N\u0004\"!\u0013)\n\u0005ES%a\u0002\"p_2,\u0017M\\\u0001\u0007y%t\u0017\u000e\u001e \u0015\u000fQ+fk\u0016-Z5B\u00111\u0006\u0001\u0005\u0006]!\u0001\r\u0001\r\u0005\u0006q!\u0001\r!\u000f\u0005\u0006y!\u0001\rA\u0010\u0005\u0006\u0005\"\u0001\ra\u0011\u0005\b\u000f\"\u0001\n\u00111\u0001I\u0011\u001dq\u0005\u0002%AA\u0002=\u000b\u0001#\\1y%\u0016$\u0018-\u001b8fI\u001aKG.Z:\u0016\u0003!\u000b\u0011#\\1y%\u0016$\u0018-\u001b8fI\u001aKG.Z:!\u0003E)g.\u00192mK\u000e{W\u000e\u001d:fgNLwN\\\u000b\u0002\u001f\u0006\u0011RM\\1cY\u0016\u001cu.\u001c9sKN\u001c\u0018n\u001c8!\u0003\u0011\u0019Ho\u001c9\u0015\u0003\r\u0004\"!\u00133\n\u0005\u0015T%\u0001B+oSR\fA\"\u00199qK:$Gk\u001c$jY\u0016$2a\u00195q\u0011\u0015Ig\u00021\u0001k\u0003\u0015\u0011\u0017\u0010^3t!\rI5.\\\u0005\u0003Y*\u0013Q!\u0011:sCf\u0004\"!\u00138\n\u0005=T%\u0001\u0002\"zi\u0016DQ!\u001d\bA\u0002!\u000b1\u0001\\3o\u0003!\u0011x\u000e\u001c7pm\u0016\u0014\u0018A\u0003:pi\u0006$XMR5mKR\u00191-\u001e<\t\u000ba\u0002\u0002\u0019A\u001d\t\u000b]\u0004\u0002\u0019A\u001d\u0002\u0019I|G\u000e\\8wKJ4\u0015\u000e\\3\u0002#I|G\u000e\\8wKJ4\u0015\u000e\\3Fq&\u001cH\u000f\u0006\u0002Pu\")10\u0005a\u0001s\u0005!a-\u001b7f\u0003!iwN^3GS2,\u0017A\u00043fY\u0016$Xm\u00147e\r&dWm]\u0001\u0014%>dG.\u001b8h\r&dW-\u00119qK:$WM\u001d\t\u0003WU\u00192!FA\u0002!\rI\u0015QA\u0005\u0004\u0003\u000fQ%AB!osJ+g\rF\u0001\u0000\u0003M!UIR!V\u0019R{&)\u0016$G\u000bJ{6+\u0013.F\u0003Q!UIR!V\u0019R{&)\u0016$G\u000bJ{6+\u0013.FA\u0005yqIW%Q?2{uiX*V\r\u001aK\u0005,\u0006\u0002\u0002\u0014A!\u0011QCA\u000e\u001b\t\t9BC\u0002\u0002\u001aQ\nA\u0001\\1oO&!\u0011QDA\f\u0005\u0019\u0019FO]5oO\u0006\u0001rIW%Q?2{uiX*V\r\u001aK\u0005\fI\u0001\u0019O\u0016$8k\u001c:uK\u0012\u0014v\u000e\u001c7fI>3XM\u001d$jY\u0016\u001cHCBA\u0013\u0003{\ty\u0005E\u0003\u0002(\u0005]\u0012H\u0004\u0003\u0002*\u0005Mb\u0002BA\u0016\u0003ci!!!\f\u000b\u0007\u0005=r&\u0001\u0004=e>|GOP\u0005\u0002\u0017&\u0019\u0011Q\u0007&\u0002\u000fA\f7m[1hK&!\u0011\u0011HA\u001e\u0005\r\u0019V-\u001d\u0006\u0004\u0003kQ\u0005bBA 7\u0001\u0007\u0011\u0011I\u0001\nI&\u0014Xm\u0019;pef\u0004B!a\u0011\u0002L9!\u0011QIA$!\r\tYCS\u0005\u0004\u0003\u0013R\u0015A\u0002)sK\u0012,g-\u0003\u0003\u0002\u001e\u00055#bAA%\u0015\"9\u0011\u0011K\u000eA\u0002\u0005\u0005\u0013AD1di&4XMR5mK:\u000bW.Z\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001b\u0016\u0005\u0005]#f\u0001%\u0002Z-\u0012\u00111\f\t\u0005\u0003;\n9'\u0004\u0002\u0002`)!\u0011\u0011MA2\u0003%)hn\u00195fG.,GMC\u0002\u0002f)\u000b!\"\u00198o_R\fG/[8o\u0013\u0011\tI'a\u0018\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HEN\u000b\u0003\u0003_R3aTA-\u0001"
)
public class RollingFileAppender extends FileAppender {
   public final File org$apache$spark$util$logging$RollingFileAppender$$activeFile;
   private final RollingPolicy rollingPolicy;
   private final int maxRetainedFiles;
   private final boolean enableCompression;

   public static boolean $lessinit$greater$default$6() {
      return RollingFileAppender$.MODULE$.$lessinit$greater$default$6();
   }

   public static int $lessinit$greater$default$5() {
      return RollingFileAppender$.MODULE$.$lessinit$greater$default$5();
   }

   public static Seq getSortedRolledOverFiles(final String directory, final String activeFileName) {
      return RollingFileAppender$.MODULE$.getSortedRolledOverFiles(directory, activeFileName);
   }

   public static String GZIP_LOG_SUFFIX() {
      return RollingFileAppender$.MODULE$.GZIP_LOG_SUFFIX();
   }

   public static int DEFAULT_BUFFER_SIZE() {
      return RollingFileAppender$.MODULE$.DEFAULT_BUFFER_SIZE();
   }

   public RollingPolicy rollingPolicy() {
      return this.rollingPolicy;
   }

   private int maxRetainedFiles() {
      return this.maxRetainedFiles;
   }

   private boolean enableCompression() {
      return this.enableCompression;
   }

   public void stop() {
      super.stop();
   }

   public void appendToFile(final byte[] bytes, final int len) {
      if (this.rollingPolicy().shouldRollover((long)len)) {
         this.rollover();
         this.rollingPolicy().rolledOver();
      }

      super.appendToFile(bytes, len);
      this.rollingPolicy().bytesWritten((long)len);
   }

   private void rollover() {
      try {
         this.closeFile();
         this.moveFile();
         this.openFile();
         if (this.maxRetainedFiles() > 0) {
            this.deleteOldFiles();
         }
      } catch (Exception var2) {
         this.logError(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error rolling over ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, this.org$apache$spark$util$logging$RollingFileAppender$$activeFile)})))), var2);
      }

   }

   private void rotateFile(final File activeFile, final File rolloverFile) {
      if (this.enableCompression()) {
         String var10002 = rolloverFile.getAbsolutePath();
         File gzFile = new File(var10002 + RollingFileAppender$.MODULE$.GZIP_LOG_SUFFIX());
         GZIPOutputStream gzOutputStream = null;
         InputStream inputStream = null;

         try {
            inputStream = new FileInputStream(activeFile);
            gzOutputStream = new GZIPOutputStream(new FileOutputStream(gzFile));
            IOUtils.copy(inputStream, gzOutputStream);
            inputStream.close();
            gzOutputStream.close();
            activeFile.delete();
         } finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(gzOutputStream);
         }

      } else {
         Files.move(activeFile, rolloverFile);
      }
   }

   private boolean rolloverFileExist(final File file) {
      boolean var10000;
      if (!file.exists()) {
         String var10002 = file.getAbsolutePath();
         if (!(new File(var10002 + RollingFileAppender$.MODULE$.GZIP_LOG_SUFFIX())).exists()) {
            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   private void moveFile() {
      String rolloverSuffix = this.rollingPolicy().generateRolledOverFileSuffix();
      File var10002 = this.org$apache$spark$util$logging$RollingFileAppender$$activeFile.getParentFile();
      String var10003 = this.org$apache$spark$util$logging$RollingFileAppender$$activeFile.getName();
      File rolloverFile = (new File(var10002, var10003 + rolloverSuffix)).getAbsoluteFile();
      this.logDebug(() -> "Attempting to rollover file " + this.org$apache$spark$util$logging$RollingFileAppender$$activeFile + " to file " + rolloverFile);
      if (!this.org$apache$spark$util$logging$RollingFileAppender$$activeFile.exists()) {
         this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"File ", " does not exist"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_NAME..MODULE$, this.org$apache$spark$util$logging$RollingFileAppender$$activeFile)})))));
      } else if (!this.rolloverFileExist(rolloverFile)) {
         this.rotateFile(this.org$apache$spark$util$logging$RollingFileAppender$$activeFile, rolloverFile);
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Rolled over ", " to ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_NAME..MODULE$, this.org$apache$spark$util$logging$RollingFileAppender$$activeFile), new MDC(org.apache.spark.internal.LogKeys.FILE_NAME2..MODULE$, rolloverFile)})))));
      } else {
         int i = 0;
         ObjectRef altRolloverFile = ObjectRef.create((Object)null);

         do {
            altRolloverFile.elem = (new File(this.org$apache$spark$util$logging$RollingFileAppender$$activeFile.getParent(), this.org$apache$spark$util$logging$RollingFileAppender$$activeFile.getName() + rolloverSuffix + "--" + i)).getAbsoluteFile();
            ++i;
         } while(i < 10000 && this.rolloverFileExist((File)altRolloverFile.elem));

         this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Rollover file ", " already exists, "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_NAME..MODULE$, rolloverFile)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"rolled over ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_NAME2..MODULE$, this.org$apache$spark$util$logging$RollingFileAppender$$activeFile)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"to file ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_NAME3..MODULE$, (File)altRolloverFile.elem)}))))));
         this.rotateFile(this.org$apache$spark$util$logging$RollingFileAppender$$activeFile, (File)altRolloverFile.elem);
      }
   }

   public void deleteOldFiles() {
      try {
         File[] rolledoverFiles = (File[])scala.collection.ArrayOps..MODULE$.sorted$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.org$apache$spark$util$logging$RollingFileAppender$$activeFile.getParentFile().listFiles(new FileFilter() {
            // $FF: synthetic field
            private final RollingFileAppender $outer;

            public boolean accept(final File f) {
               boolean var10000;
               label25: {
                  if (f.getName().startsWith(this.$outer.org$apache$spark$util$logging$RollingFileAppender$$activeFile.getName())) {
                     File var2 = this.$outer.org$apache$spark$util$logging$RollingFileAppender$$activeFile;
                     if (f == null) {
                        if (var2 != null) {
                           break label25;
                        }
                     } else if (!f.equals(var2)) {
                        break label25;
                     }
                  }

                  var10000 = false;
                  return var10000;
               }

               var10000 = true;
               return var10000;
            }

            public {
               if (RollingFileAppender.this == null) {
                  throw null;
               } else {
                  this.$outer = RollingFileAppender.this;
               }
            }
         })), scala.math.Ordering..MODULE$.ordered(scala.Predef..MODULE$.$conforms()));
         File[] filesToBeDeleted = (File[])scala.collection.ArrayOps..MODULE$.take$extension(scala.Predef..MODULE$.refArrayOps((Object[])rolledoverFiles), scala.math.package..MODULE$.max(0, rolledoverFiles.length - this.maxRetainedFiles()));
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])filesToBeDeleted), (file) -> BoxesRunTime.boxToBoolean($anonfun$deleteOldFiles$1(this, file)));
      } catch (Exception var5) {
         String path = this.org$apache$spark$util$logging$RollingFileAppender$$activeFile.getParentFile().getAbsolutePath();
         this.logError(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error cleaning logs in directory ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, path)})))), var5);
      }

   }

   // $FF: synthetic method
   public static final boolean $anonfun$deleteOldFiles$1(final RollingFileAppender $this, final File file) {
      $this.logInfo(.MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Deleting file executor log file"})))).log(scala.collection.immutable.Nil..MODULE$).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_ABSOLUTE_PATH..MODULE$, file.getAbsolutePath())}))))));
      return file.delete();
   }

   public RollingFileAppender(final InputStream inputStream, final File activeFile, final RollingPolicy rollingPolicy, final SparkConf conf, final int bufferSize, final boolean closeStreams) {
      super(inputStream, activeFile, bufferSize, closeStreams);
      this.org$apache$spark$util$logging$RollingFileAppender$$activeFile = activeFile;
      this.rollingPolicy = rollingPolicy;
      this.maxRetainedFiles = BoxesRunTime.unboxToInt(conf.get(package$.MODULE$.EXECUTOR_LOGS_ROLLING_MAX_RETAINED_FILES()));
      this.enableCompression = BoxesRunTime.unboxToBoolean(conf.get(package$.MODULE$.EXECUTOR_LOGS_ROLLING_ENABLE_COMPRESSION()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
