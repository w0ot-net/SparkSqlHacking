package org.apache.spark.deploy.history;

import java.lang.invoke.SerializedLambda;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.util.ArrayImplicits.;
import scala.Option;
import scala.Predef;
import scala.Some;
import scala.collection.IterableOnceOps;
import scala.collection.SeqOps;
import scala.collection.immutable.ArraySeq;
import scala.collection.immutable.NumericRange;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichLong;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015a!\u0002\n\u0014\u0001Mi\u0002\"\u0003\u0012\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0013,\u0011%i\u0003A!A!\u0002\u0013q\u0013\u0007C\u00034\u0001\u0011\u0005A\u0007\u0003\u00059\u0001!\u0015\r\u0011\"\u0003:\u0011!Y\u0005\u0001#b\u0001\n\u0013a\u0005\u0002C'\u0001\u0011\u000b\u0007I\u0011B\u001d\t\u000b9\u0003A\u0011I(\t\u000b]\u0003A\u0011\t-\t\u000be\u0003A\u0011\t.\t\u000by\u0003A\u0011I(\t\u000b}\u0003A\u0011\t-\t\u000b\u0001\u0004A\u0011I1\t\u000bE\u0004A\u0011I\u001d\t\u000bI\u0004A\u0011I:\t\u000bu\u0004A\u0011\t-\t\u000by\u0004A\u0011\u0002'\t\r}\u0004A\u0011BA\u0001\u0005y\u0011v\u000e\u001c7j]\u001e,e/\u001a8u\u0019><g)\u001b7fg\u001aKG.\u001a*fC\u0012,'O\u0003\u0002\u0015+\u00059\u0001.[:u_JL(B\u0001\f\u0018\u0003\u0019!W\r\u001d7ps*\u0011\u0001$G\u0001\u0006gB\f'o\u001b\u0006\u00035m\ta!\u00199bG\",'\"\u0001\u000f\u0002\u0007=\u0014xm\u0005\u0002\u0001=A\u0011q\u0004I\u0007\u0002'%\u0011\u0011e\u0005\u0002\u0013\u000bZ,g\u000e\u001e'pO\u001aKG.\u001a*fC\u0012,'/\u0001\u0002gg\u000e\u0001\u0001CA\u0013*\u001b\u00051#B\u0001\u0012(\u0015\tA\u0013$\u0001\u0004iC\u0012|w\u000e]\u0005\u0003U\u0019\u0012!BR5mKNK8\u000f^3n\u0013\ta\u0003%\u0001\u0006gS2,7+_:uK6\fA\u0001]1uQB\u0011QeL\u0005\u0003a\u0019\u0012A\u0001U1uQ&\u0011!\u0007I\u0001\te>|G\u000fU1uQ\u00061A(\u001b8jiz\"2!\u000e\u001c8!\ty\u0002\u0001C\u0003#\u0007\u0001\u0007A\u0005C\u0003.\u0007\u0001\u0007a&A\u0003gS2,7/F\u0001;!\rYT\t\u0013\b\u0003y\ts!!\u0010!\u000e\u0003yR!aP\u0012\u0002\rq\u0012xn\u001c;?\u0013\u0005\t\u0015!B:dC2\f\u0017BA\"E\u0003\u001d\u0001\u0018mY6bO\u0016T\u0011!Q\u0005\u0003\r\u001e\u00131aU3r\u0015\t\u0019E\t\u0005\u0002&\u0013&\u0011!J\n\u0002\u000b\r&dWm\u0015;biV\u001c\u0018!D1qaN#\u0018\r^;t\r&dW-F\u0001I\u00035)g/\u001a8u\u0019><g)\u001b7fg\u0006IA.Y:u\u0013:$W\r_\u000b\u0002!B\u0019\u0011K\u0015+\u000e\u0003\u0011K!a\u0015#\u0003\r=\u0003H/[8o!\t\tV+\u0003\u0002W\t\n!Aj\u001c8h\u0003Q1\u0017\u000e\\3TSj,gi\u001c:MCN$\u0018J\u001c3fqV\tA+A\u0005d_6\u0004H.\u001a;fIV\t1\f\u0005\u0002R9&\u0011Q\f\u0012\u0002\b\u0005>|G.Z1o\u0003i1\u0017\u000e\\3TSj,gi\u001c:MCN$\u0018J\u001c3fq\u001a{'\u000f\u0012$T\u0003Aiw\u000eZ5gS\u000e\fG/[8o)&lW-\u0001\t{SB,e/\u001a8u\u0019><g)\u001b7fgR\u0011!-\u001a\t\u0003#\u000eL!\u0001\u001a#\u0003\tUs\u0017\u000e\u001e\u0005\u0006M2\u0001\raZ\u0001\nu&\u00048\u000b\u001e:fC6\u0004\"\u0001[8\u000e\u0003%T!A[6\u0002\u0007iL\u0007O\u0003\u0002m[\u0006!Q\u000f^5m\u0015\u0005q\u0017\u0001\u00026bm\u0006L!\u0001]5\u0003\u001fiK\u0007oT;uaV$8\u000b\u001e:fC6\f\u0011\u0003\\5ti\u00163XM\u001c;M_\u001e4\u0015\u000e\\3t\u0003A\u0019w.\u001c9sKN\u001c\u0018n\u001c8D_\u0012,7-F\u0001u!\r\t&+\u001e\t\u0003mjt!a\u001e=\u0011\u0005u\"\u0015BA=E\u0003\u0019\u0001&/\u001a3fM&\u00111\u0010 \u0002\u0007'R\u0014\u0018N\\4\u000b\u0005e$\u0015!\u0003;pi\u0006d7+\u001b>f\u0003Aa\u0017m\u001d;Fm\u0016tG\u000fT8h\r&dW-A\ree>\u0004()\u001a4pe\u0016d\u0015m\u001d;D_6\u0004\u0018m\u0019;GS2,Gc\u0001\u001e\u0002\u0004!)Q*\u0005a\u0001u\u0001"
)
public class RollingEventLogFilesFileReader extends EventLogFileReader {
   private Seq files;
   private FileStatus appStatusFile;
   private Seq eventLogFiles;
   private volatile byte bitmap$0;

   private Seq files$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            ArraySeq ret = .MODULE$.SparkArrayOps(super.fileSystem().listStatus(this.rootPath())).toImmutableArraySeq();
            scala.Predef..MODULE$.require(ret.exists((status) -> BoxesRunTime.boxToBoolean($anonfun$files$1(status))), () -> "Log directory must contain at least one event log file!");
            scala.Predef..MODULE$.require(ret.exists((status) -> BoxesRunTime.boxToBoolean($anonfun$files$3(status))), () -> "Log directory must contain an appstatus file!");
            this.files = ret;
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return this.files;
   }

   private Seq files() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.files$lzycompute() : this.files;
   }

   private FileStatus appStatusFile$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.appStatusFile = (FileStatus)this.files().find((status) -> BoxesRunTime.boxToBoolean($anonfun$appStatusFile$1(status))).get();
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.appStatusFile;
   }

   private FileStatus appStatusFile() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.appStatusFile$lzycompute() : this.appStatusFile;
   }

   private Seq eventLogFiles$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 4) == 0) {
            Seq filesToRead;
            Seq indices;
            boolean var8;
            Predef var10001;
            label53: {
               label52: {
                  Seq eventLogFiles = (Seq)((SeqOps)this.files().filter((status) -> BoxesRunTime.boxToBoolean($anonfun$eventLogFiles$1(status)))).sortBy((status) -> BoxesRunTime.boxToDouble($anonfun$eventLogFiles$2(status)), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$);
                  filesToRead = this.dropBeforeLastCompactFile(eventLogFiles);
                  indices = (Seq)filesToRead.map((file) -> BoxesRunTime.boxToLong($anonfun$eventLogFiles$3(file)));
                  var10001 = scala.Predef..MODULE$;
                  NumericRange.Inclusive var10002 = (new RichLong(scala.Predef..MODULE$.longWrapper(BoxesRunTime.unboxToLong(indices.head())))).to(indices.last());
                  if (var10002 == null) {
                     if (indices == null) {
                        break label52;
                     }
                  } else if (var10002.equals(indices)) {
                     break label52;
                  }

                  var8 = false;
                  break label53;
               }

               var8 = true;
            }

            var10001.require(var8, () -> {
               NumericRange.Inclusive var10000 = (new RichLong(scala.Predef..MODULE$.longWrapper(BoxesRunTime.unboxToLong(indices.head())))).to(indices.last());
               return "Found missing event log file, expected indices: " + var10000 + ", actual: " + indices;
            });
            this.eventLogFiles = filesToRead;
            this.bitmap$0 = (byte)(this.bitmap$0 | 4);
         }
      } catch (Throwable var7) {
         throw var7;
      }

      return this.eventLogFiles;
   }

   private Seq eventLogFiles() {
      return (byte)(this.bitmap$0 & 4) == 0 ? this.eventLogFiles$lzycompute() : this.eventLogFiles;
   }

   public Option lastIndex() {
      return new Some(BoxesRunTime.boxToLong(RollingEventLogFilesWriter$.MODULE$.getEventLogFileIndex(this.lastEventLogFile().getPath().getName())));
   }

   public long fileSizeForLastIndex() {
      return this.lastEventLogFile().getLen();
   }

   public boolean completed() {
      return !this.appStatusFile().getPath().getName().endsWith(EventLogFileWriter$.MODULE$.IN_PROGRESS());
   }

   public Option fileSizeForLastIndexForDFS() {
      return (Option)(this.completed() ? new Some(BoxesRunTime.boxToLong(this.fileSizeForLastIndex())) : this.fileSizeForDFS(this.lastEventLogFile().getPath()));
   }

   public long modificationTime() {
      return this.lastEventLogFile().getModificationTime();
   }

   public void zipEventLogFiles(final ZipOutputStream zipStream) {
      String dirEntryName = this.rootPath().getName() + "/";
      zipStream.putNextEntry(new ZipEntry(dirEntryName));
      this.files().foreach((file) -> {
         $anonfun$zipEventLogFiles$1(this, zipStream, dirEntryName, file);
         return BoxedUnit.UNIT;
      });
   }

   public Seq listEventLogFiles() {
      return this.eventLogFiles();
   }

   public Option compressionCodec() {
      return EventLogFileWriter$.MODULE$.codecName(((FileStatus)this.eventLogFiles().head()).getPath());
   }

   public long totalSize() {
      return BoxesRunTime.unboxToLong(((IterableOnceOps)this.eventLogFiles().map((x$3) -> BoxesRunTime.boxToLong($anonfun$totalSize$1(x$3)))).sum(scala.math.Numeric.LongIsIntegral..MODULE$));
   }

   private FileStatus lastEventLogFile() {
      return (FileStatus)this.eventLogFiles().last();
   }

   private Seq dropBeforeLastCompactFile(final Seq eventLogFiles) {
      int lastCompactedFileIdx = eventLogFiles.lastIndexWhere((fs) -> BoxesRunTime.boxToBoolean($anonfun$dropBeforeLastCompactFile$1(fs)));
      return (Seq)eventLogFiles.drop(lastCompactedFileIdx);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$files$1(final FileStatus status) {
      return RollingEventLogFilesWriter$.MODULE$.isEventLogFile(status);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$files$3(final FileStatus status) {
      return RollingEventLogFilesWriter$.MODULE$.isAppStatusFile(status);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$appStatusFile$1(final FileStatus status) {
      return RollingEventLogFilesWriter$.MODULE$.isAppStatusFile(status);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$eventLogFiles$1(final FileStatus status) {
      return RollingEventLogFilesWriter$.MODULE$.isEventLogFile(status);
   }

   // $FF: synthetic method
   public static final double $anonfun$eventLogFiles$2(final FileStatus status) {
      Path filePath = status.getPath();
      double idx = (double)RollingEventLogFilesWriter$.MODULE$.getEventLogFileIndex(filePath.getName());
      if (EventLogFileWriter$.MODULE$.isCompacted(filePath)) {
         idx += 0.1;
      }

      return idx;
   }

   // $FF: synthetic method
   public static final long $anonfun$eventLogFiles$3(final FileStatus file) {
      return RollingEventLogFilesWriter$.MODULE$.getEventLogFileIndex(file.getPath().getName());
   }

   // $FF: synthetic method
   public static final void $anonfun$zipEventLogFiles$1(final RollingEventLogFilesFileReader $this, final ZipOutputStream zipStream$2, final String dirEntryName$1, final FileStatus file) {
      $this.addFileAsZipEntry(zipStream$2, file.getPath(), dirEntryName$1 + file.getPath().getName());
   }

   // $FF: synthetic method
   public static final long $anonfun$totalSize$1(final FileStatus x$3) {
      return x$3.getLen();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$dropBeforeLastCompactFile$1(final FileStatus fs) {
      return EventLogFileWriter$.MODULE$.isCompacted(fs.getPath());
   }

   public RollingEventLogFilesFileReader(final FileSystem fs, final Path path) {
      super(fs, path);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
