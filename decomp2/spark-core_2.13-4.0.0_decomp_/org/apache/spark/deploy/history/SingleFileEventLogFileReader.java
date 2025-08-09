package org.apache.spark.deploy.history;

import java.lang.invoke.SerializedLambda;
import java.util.zip.ZipOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005a!\u0002\t\u0012\u0001EY\u0002\"\u0003\u0011\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0012*\u0011%Y\u0003A!A!\u0002\u0013as\u0006\u0003\u00052\u0001\t\u0005\t\u0015!\u00033\u0011\u0015Y\u0004\u0001\"\u0001=\u0011!\t\u0005\u0001#b\u0001\n\u0013\u0011\u0005\"B\u001e\u0001\t\u0003\u0019\u0005\"\u0002$\u0001\t\u0003:\u0005\"\u0002'\u0001\t\u0003j\u0005\"\u0002(\u0001\t\u0003z\u0005\"B*\u0001\t\u0003:\u0005\"\u0002+\u0001\t\u0003j\u0005\"B+\u0001\t\u00032\u0006\"\u00024\u0001\t\u0003:\u0007\"\u0002;\u0001\t\u0003*\b\"B@\u0001\t\u0003j%\u0001H*j]\u001edWMR5mK\u00163XM\u001c;M_\u001e4\u0015\u000e\\3SK\u0006$WM\u001d\u0006\u0003%M\tq\u0001[5ti>\u0014\u0018P\u0003\u0002\u0015+\u00051A-\u001a9m_fT!AF\f\u0002\u000bM\u0004\u0018M]6\u000b\u0005aI\u0012AB1qC\u000eDWMC\u0001\u001b\u0003\ry'oZ\n\u0003\u0001q\u0001\"!\b\u0010\u000e\u0003EI!aH\t\u0003%\u00153XM\u001c;M_\u001e4\u0015\u000e\\3SK\u0006$WM]\u0001\u0003MN\u001c\u0001\u0001\u0005\u0002$O5\tAE\u0003\u0002!K)\u0011aeF\u0001\u0007Q\u0006$wn\u001c9\n\u0005!\"#A\u0003$jY\u0016\u001c\u0016p\u001d;f[&\u0011!FH\u0001\u000bM&dWmU=ti\u0016l\u0017\u0001\u00029bi\"\u0004\"aI\u0017\n\u00059\"#\u0001\u0002)bi\"L!\u0001\r\u0010\u0002\u0011I|w\u000e\u001e)bi\"\f1\"\\1zE\u0016\u001cF/\u0019;vgB\u00191G\u000e\u001d\u000e\u0003QR\u0011!N\u0001\u0006g\u000e\fG.Y\u0005\u0003oQ\u0012aa\u00149uS>t\u0007CA\u0012:\u0013\tQDE\u0001\u0006GS2,7\u000b^1ukN\fa\u0001P5oSRtD\u0003B\u001f?\u007f\u0001\u0003\"!\b\u0001\t\u000b\u0001\"\u0001\u0019\u0001\u0012\t\u000b-\"\u0001\u0019\u0001\u0017\t\u000bE\"\u0001\u0019\u0001\u001a\u0002\rM$\u0018\r^;t+\u0005ADcA\u001fE\u000b\")\u0001E\u0002a\u0001E!)1F\u0002a\u0001Y\u0005IA.Y:u\u0013:$W\r_\u000b\u0002\u0011B\u00191GN%\u0011\u0005MR\u0015BA&5\u0005\u0011auN\\4\u0002)\u0019LG.Z*ju\u00164uN\u001d'bgRLe\u000eZ3y+\u0005I\u0015!C2p[BdW\r^3e+\u0005\u0001\u0006CA\u001aR\u0013\t\u0011FGA\u0004C_>dW-\u00198\u00025\u0019LG.Z*ju\u00164uN\u001d'bgRLe\u000eZ3y\r>\u0014HIR*\u0002!5|G-\u001b4jG\u0006$\u0018n\u001c8US6,\u0017\u0001\u0005>ja\u00163XM\u001c;M_\u001e4\u0015\u000e\\3t)\t9&\f\u0005\u000241&\u0011\u0011\f\u000e\u0002\u0005+:LG\u000fC\u0003\\\u0019\u0001\u0007A,A\u0005{SB\u001cFO]3b[B\u0011Q\fZ\u0007\u0002=*\u0011q\fY\u0001\u0004u&\u0004(BA1c\u0003\u0011)H/\u001b7\u000b\u0003\r\fAA[1wC&\u0011QM\u0018\u0002\u00105&\u0004x*\u001e;qkR\u001cFO]3b[\u0006\tB.[:u\u000bZ,g\u000e\u001e'pO\u001aKG.Z:\u0016\u0003!\u00042![99\u001d\tQwN\u0004\u0002l]6\tAN\u0003\u0002nC\u00051AH]8pizJ\u0011!N\u0005\u0003aR\nq\u0001]1dW\u0006<W-\u0003\u0002sg\n\u00191+Z9\u000b\u0005A$\u0014\u0001E2p[B\u0014Xm]:j_:\u001cu\u000eZ3d+\u00051\bcA\u001a7oB\u0011\u0001\u0010 \b\u0003sj\u0004\"a\u001b\u001b\n\u0005m$\u0014A\u0002)sK\u0012,g-\u0003\u0002~}\n11\u000b\u001e:j]\u001eT!a\u001f\u001b\u0002\u0013Q|G/\u00197TSj,\u0007"
)
public class SingleFileEventLogFileReader extends EventLogFileReader {
   private FileStatus status;
   private Option maybeStatus;
   private volatile boolean bitmap$0;

   private FileStatus status$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.status = (FileStatus)this.maybeStatus.getOrElse(() -> this.fileSystem().getFileStatus(this.rootPath()));
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      this.maybeStatus = null;
      return this.status;
   }

   private FileStatus status() {
      return !this.bitmap$0 ? this.status$lzycompute() : this.status;
   }

   public Option lastIndex() {
      return .MODULE$;
   }

   public long fileSizeForLastIndex() {
      return this.status().getLen();
   }

   public boolean completed() {
      return !scala.collection.StringOps..MODULE$.stripSuffix$extension(scala.Predef..MODULE$.augmentString(this.rootPath().getName()), EventLogFileWriter$.MODULE$.COMPACTED()).endsWith(EventLogFileWriter$.MODULE$.IN_PROGRESS());
   }

   public Option fileSizeForLastIndexForDFS() {
      return (Option)(this.completed() ? new Some(BoxesRunTime.boxToLong(this.fileSizeForLastIndex())) : this.fileSizeForDFS(this.rootPath()));
   }

   public long modificationTime() {
      return this.status().getModificationTime();
   }

   public void zipEventLogFiles(final ZipOutputStream zipStream) {
      this.addFileAsZipEntry(zipStream, this.rootPath(), this.rootPath().getName());
   }

   public Seq listEventLogFiles() {
      return new scala.collection.immutable..colon.colon(this.status(), scala.collection.immutable.Nil..MODULE$);
   }

   public Option compressionCodec() {
      return EventLogFileWriter$.MODULE$.codecName(this.rootPath());
   }

   public long totalSize() {
      return this.fileSizeForLastIndex();
   }

   public SingleFileEventLogFileReader(final FileSystem fs, final Path path, final Option maybeStatus) {
      this.maybeStatus = maybeStatus;
      super(fs, path);
   }

   public SingleFileEventLogFileReader(final FileSystem fs, final Path path) {
      this(fs, path, .MODULE$);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
