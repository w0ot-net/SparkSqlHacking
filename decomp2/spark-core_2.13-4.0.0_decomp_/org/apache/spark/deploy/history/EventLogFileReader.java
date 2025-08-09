package org.apache.spark.deploy.history;

import java.io.InputStream;
import java.lang.invoke.SerializedLambda;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DFSInputStream;
import org.apache.spark.internal.Logging;
import org.apache.spark.util.Utils$;
import org.sparkproject.guava.io.ByteStreams;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-e!\u0002\u000f\u001e\u0003\u0003A\u0003\u0002C\u0018\u0001\u0005\u000b\u0007I\u0011\u0003\u0019\t\u0011e\u0002!\u0011!Q\u0001\nEB\u0001B\u000f\u0001\u0003\u0006\u0004%\ta\u000f\u0005\t\u007f\u0001\u0011\t\u0011)A\u0005y!)\u0001\t\u0001C\u0001\u0003\")a\t\u0001C\t\u000f\")\u0001\u000b\u0001C\t#\")q\u000e\u0001D\u0001a\")\u0011\u000f\u0001D\u0001e\")1\u000f\u0001D\u0001i\")\u0001\u0010\u0001D\u0001a\")\u0011\u0010\u0001D\u0001e\")!\u0010\u0001D\u0001w\")Q\u0010\u0001D\u0001}\"9\u0011q\u0003\u0001\u0007\u0002\u0005e\u0001BBA\u000f\u0001\u0019\u0005!oB\u0004\u0002 uA\t!!\t\u0007\rqi\u0002\u0012AA\u0012\u0011\u0019\u0001%\u0003\"\u0001\u00022!I\u00111\u0007\nC\u0002\u0013%\u0011Q\u0007\u0005\t\u0003\u001f\u0012\u0002\u0015!\u0003\u00028!9\u0011\u0011\u000b\n\u0005\u0002\u0005M\u0003bBA)%\u0011\u0005\u00111\f\u0005\b\u0003#\u0012B\u0011AA2\u0011\u001d\tYG\u0005C\u0001\u0003[Bq!a \u0013\t\u0013\t\t\tC\u0004\u0002\u0006J!I!a\"\u0003%\u00153XM\u001c;M_\u001e4\u0015\u000e\\3SK\u0006$WM\u001d\u0006\u0003=}\tq\u0001[5ti>\u0014\u0018P\u0003\u0002!C\u00051A-\u001a9m_fT!AI\u0012\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u0011*\u0013AB1qC\u000eDWMC\u0001'\u0003\ry'oZ\u0002\u0001'\t\u0001\u0011\u0006\u0005\u0002+[5\t1FC\u0001-\u0003\u0015\u00198-\u00197b\u0013\tq3F\u0001\u0004B]f\u0014VMZ\u0001\u000bM&dWmU=ti\u0016lW#A\u0019\u0011\u0005I:T\"A\u001a\u000b\u0005Q*\u0014A\u00014t\u0015\t14%\u0001\u0004iC\u0012|w\u000e]\u0005\u0003qM\u0012!BR5mKNK8\u000f^3n\u0003-1\u0017\u000e\\3TsN$X-\u001c\u0011\u0002\u0011I|w\u000e\u001e)bi\",\u0012\u0001\u0010\t\u0003euJ!AP\u001a\u0003\tA\u000bG\u000f[\u0001\ne>|G\u000fU1uQ\u0002\na\u0001P5oSRtDc\u0001\"E\u000bB\u00111\tA\u0007\u0002;!)q&\u0002a\u0001c!)!(\u0002a\u0001y\u0005qa-\u001b7f'&TXMR8s\t\u001a\u001bFC\u0001%O!\rQ\u0013jS\u0005\u0003\u0015.\u0012aa\u00149uS>t\u0007C\u0001\u0016M\u0013\ti5F\u0001\u0003M_:<\u0007\"B(\u0007\u0001\u0004a\u0014\u0001\u00029bi\"\f\u0011#\u00193e\r&dW-Q:[SB,e\u000e\u001e:z)\u0011\u0011V+\u00192\u0011\u0005)\u001a\u0016B\u0001+,\u0005\u0011)f.\u001b;\t\u000bY;\u0001\u0019A,\u0002\u0013iL\u0007o\u0015;sK\u0006l\u0007C\u0001-`\u001b\u0005I&B\u0001.\\\u0003\rQ\u0018\u000e\u001d\u0006\u00039v\u000bA!\u001e;jY*\ta,\u0001\u0003kCZ\f\u0017B\u00011Z\u0005=Q\u0016\u000e](viB,Ho\u0015;sK\u0006l\u0007\"B(\b\u0001\u0004a\u0004\"B2\b\u0001\u0004!\u0017!C3oiJLh*Y7f!\t)GN\u0004\u0002gUB\u0011qmK\u0007\u0002Q*\u0011\u0011nJ\u0001\u0007yI|w\u000e\u001e \n\u0005-\\\u0013A\u0002)sK\u0012,g-\u0003\u0002n]\n11\u000b\u001e:j]\u001eT!a[\u0016\u0002\u00131\f7\u000f^%oI\u0016DX#\u0001%\u0002)\u0019LG.Z*ju\u00164uN\u001d'bgRLe\u000eZ3y+\u0005Y\u0015!C2p[BdW\r^3e+\u0005)\bC\u0001\u0016w\u0013\t98FA\u0004C_>dW-\u00198\u00025\u0019LG.Z*ju\u00164uN\u001d'bgRLe\u000eZ3y\r>\u0014HIR*\u0002!5|G-\u001b4jG\u0006$\u0018n\u001c8US6,\u0017\u0001\u0005>ja\u00163XM\u001c;M_\u001e4\u0015\u000e\\3t)\t\u0011F\u0010C\u0003W\u001b\u0001\u0007q+A\tmSN$XI^3oi2{wMR5mKN,\u0012a \t\u0007\u0003\u0003\tY!!\u0005\u000f\t\u0005\r\u0011q\u0001\b\u0004O\u0006\u0015\u0011\"\u0001\u0017\n\u0007\u0005%1&A\u0004qC\u000e\\\u0017mZ3\n\t\u00055\u0011q\u0002\u0002\u0004'\u0016\f(bAA\u0005WA\u0019!'a\u0005\n\u0007\u0005U1G\u0001\u0006GS2,7\u000b^1ukN\f\u0001cY8naJ,7o]5p]\u000e{G-Z2\u0016\u0005\u0005m\u0001c\u0001\u0016JI\u0006IAo\u001c;bYNK'0Z\u0001\u0013\u000bZ,g\u000e\u001e'pO\u001aKG.\u001a*fC\u0012,'\u000f\u0005\u0002D%M!!#KA\u0013!\u0011\t9#!\f\u000e\u0005\u0005%\"bAA\u0016C\u0005A\u0011N\u001c;fe:\fG.\u0003\u0003\u00020\u0005%\"a\u0002'pO\u001eLgn\u001a\u000b\u0003\u0003C\t\u0001bY8eK\u000el\u0015\r]\u000b\u0003\u0003o\u0001r!!\u000f\u0002@\u0011\f\u0019%\u0004\u0002\u0002<)\u0019\u0011QH.\u0002\u0015\r|gnY;se\u0016tG/\u0003\u0003\u0002B\u0005m\"!E\"p]\u000e,(O]3oi\"\u000b7\u000f['baB!\u0011QIA&\u001b\t\t9EC\u0002\u0002J\u0005\n!![8\n\t\u00055\u0013q\t\u0002\u0011\u0007>l\u0007O]3tg&|gnQ8eK\u000e\f\u0011bY8eK\u000el\u0015\r\u001d\u0011\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000f\t\u000b)&a\u0016\u0002Z!)AG\u0006a\u0001c!)qJ\u0006a\u0001y!)qN\u0006a\u0001\u0011R1\u0011QLA0\u0003C\u00022AK%C\u0011\u0015!t\u00031\u00012\u0011\u0015yu\u00031\u0001=)\u0019\ti&!\u001a\u0002h!)A\u0007\u0007a\u0001c!9\u0011\u0011\u000e\rA\u0002\u0005E\u0011AB:uCR,8/\u0001\u0007pa\u0016tWI^3oi2{w\r\u0006\u0004\u0002p\u0005e\u0014Q\u0010\t\u0005\u0003c\n)(\u0004\u0002\u0002t)\u0019\u0011\u0011J/\n\t\u0005]\u00141\u000f\u0002\f\u0013:\u0004X\u000f^*ue\u0016\fW\u000e\u0003\u0004\u0002|e\u0001\r\u0001P\u0001\u0004Y><\u0007\"\u0002\u001b\u001a\u0001\u0004\t\u0014\u0001E5t'&tw\r\\3Fm\u0016tG\u000fT8h)\r)\u00181\u0011\u0005\b\u0003SR\u0002\u0019AA\t\u0003II7OU8mY&tw-\u0012<f]RdunZ:\u0015\u0007U\fI\tC\u0004\u0002jm\u0001\r!!\u0005"
)
public abstract class EventLogFileReader {
   private final FileSystem fileSystem;
   private final Path rootPath;

   public static InputStream openEventLog(final Path log, final FileSystem fs) {
      return EventLogFileReader$.MODULE$.openEventLog(log, fs);
   }

   public static Option apply(final FileSystem fs, final FileStatus status) {
      return EventLogFileReader$.MODULE$.apply(fs, status);
   }

   public static Option apply(final FileSystem fs, final Path path) {
      return EventLogFileReader$.MODULE$.apply(fs, path);
   }

   public static EventLogFileReader apply(final FileSystem fs, final Path path, final Option lastIndex) {
      return EventLogFileReader$.MODULE$.apply(fs, path, lastIndex);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return EventLogFileReader$.MODULE$.LogStringContext(sc);
   }

   public FileSystem fileSystem() {
      return this.fileSystem;
   }

   public Path rootPath() {
      return this.rootPath;
   }

   public Option fileSizeForDFS(final Path path) {
      return (Option)Utils$.MODULE$.tryWithResource(() -> this.fileSystem().open(path), (in) -> {
         InputStream var2 = in.getWrappedStream();
         if (var2 instanceof DFSInputStream var3) {
            return new Some(BoxesRunTime.boxToLong(var3.getFileLength()));
         } else {
            return .MODULE$;
         }
      });
   }

   public void addFileAsZipEntry(final ZipOutputStream zipStream, final Path path, final String entryName) {
      Utils$.MODULE$.tryWithResource(() -> this.fileSystem().open(path, 1048576), (inputStream) -> {
         $anonfun$addFileAsZipEntry$2(zipStream, entryName, inputStream);
         return BoxedUnit.UNIT;
      });
   }

   public abstract Option lastIndex();

   public abstract long fileSizeForLastIndex();

   public abstract boolean completed();

   public abstract Option fileSizeForLastIndexForDFS();

   public abstract long modificationTime();

   public abstract void zipEventLogFiles(final ZipOutputStream zipStream);

   public abstract Seq listEventLogFiles();

   public abstract Option compressionCodec();

   public abstract long totalSize();

   // $FF: synthetic method
   public static final void $anonfun$addFileAsZipEntry$2(final ZipOutputStream zipStream$1, final String entryName$1, final FSDataInputStream inputStream) {
      zipStream$1.putNextEntry(new ZipEntry(entryName$1));
      ByteStreams.copy(inputStream, zipStream$1);
      zipStream$1.closeEntry();
   }

   public EventLogFileReader(final FileSystem fileSystem, final Path rootPath) {
      this.fileSystem = fileSystem;
      this.rootPath = rootPath;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
