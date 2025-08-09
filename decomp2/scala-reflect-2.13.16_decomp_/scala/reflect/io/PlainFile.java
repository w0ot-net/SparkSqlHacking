package scala.reflect.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;
import scala.Some;
import scala.collection.Iterator;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ub\u0001B\u000e\u001d\u0001\rB\u0001\u0002\u000b\u0001\u0003\u0006\u0004%\t!\u000b\u0005\t[\u0001\u0011\t\u0011)A\u0005U!)a\u0006\u0001C\u0001_!9!\u0007\u0001b\u0001\n\u0003\u0019\u0004BB\u001e\u0001A\u0003%A\u0007\u0003\u0005=\u0001!\u0015\r\u0011\"\u0011>\u0011\u0015I\u0005\u0001\"\u0011K\u0011\u001dy\u0005A1A\u0005\n%Ba\u0001\u0015\u0001!\u0002\u0013Q\u0003\"B)\u0001\t\u0003i\u0004\"\u0002*\u0001\t\u0003i\u0004\"B*\u0001\t\u0003!\u0006\"B+\u0001\t\u00032\u0006\"B,\u0001\t\u0003B\u0006\"\u0002/\u0001\t\u0003j\u0006\"B1\u0001\t\u0003\u0012\u0007\"B4\u0001\t\u0003B\u0007\"B8\u0001\t\u0003\u0002\b\"B9\u0001\t\u0003\u0012\b\"B>\u0001\t\u0003a\b\"B?\u0001\t\u0003q\bbBA\u0003\u0001\u0011\u0005\u0011q\u0001\u0005\b\u0003/\u0001A\u0011AA\r\u0011\u001d\t\t\u0003\u0001C\u0001\u0003GAq!a\u000b\u0001\t\u0003\t\u0019\u0003C\u0004\u0002.\u0001!\t!a\f\u0003\u0013Ac\u0017-\u001b8GS2,'BA\u000f\u001f\u0003\tIwN\u0003\u0002 A\u00059!/\u001a4mK\u000e$(\"A\u0011\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M\u0011\u0001\u0001\n\t\u0003K\u0019j\u0011\u0001H\u0005\u0003Oq\u0011A\"\u00112tiJ\f7\r\u001e$jY\u0016\f\u0011bZ5wK:\u0004\u0016\r\u001e5\u0016\u0003)\u0002\"!J\u0016\n\u00051b\"\u0001\u0002)bi\"\f!bZ5wK:\u0004\u0016\r\u001e5!\u0003\u0019a\u0014N\\5u}Q\u0011\u0001'\r\t\u0003K\u0001AQ\u0001K\u0002A\u0002)\nAAZ5mKV\tA\u0007\u0005\u00026s5\taG\u0003\u0002\u001eo)\t\u0001(\u0001\u0003kCZ\f\u0017B\u0001\u001e7\u0005\u00111\u0015\u000e\\3\u0002\u000b\u0019LG.\u001a\u0011\u0002\u001b\r\fgn\u001c8jG\u0006d\u0007+\u0019;i+\u0005q\u0004CA G\u001d\t\u0001E\t\u0005\u0002BA5\t!I\u0003\u0002DE\u00051AH]8pizJ!!\u0012\u0011\u0002\rA\u0013X\rZ3g\u0013\t9\u0005J\u0001\u0004TiJLgn\u001a\u0006\u0003\u000b\u0002\n\u0001#\u001e8eKJd\u00170\u001b8h'>,(oY3\u0016\u0003-\u00032\u0001T'1\u001b\u0005\u0001\u0013B\u0001(!\u0005\u0011\u0019v.\\3\u0002\u000b\u0019\u0004\u0018\r\u001e5\u0002\r\u0019\u0004\u0018\r\u001e5!\u0003\u0011q\u0017-\\3\u0002\tA\fG\u000f[\u0001\tC\n\u001cx\u000e\\;uKV\t\u0001'A\u0005d_:$\u0018-\u001b8feV\tA%A\u0003j]B,H/F\u0001Z!\t)$,\u0003\u0002\\m\tya)\u001b7f\u0013:\u0004X\u000f^*ue\u0016\fW.\u0001\u0004pkR\u0004X\u000f^\u000b\u0002=B\u0011QgX\u0005\u0003AZ\u0012\u0001CR5mK>+H\u000f];u'R\u0014X-Y7\u0002\u0015ML'0Z(qi&|g.F\u0001d!\raU\n\u001a\t\u0003\u0019\u0016L!A\u001a\u0011\u0003\u0007%sG/\u0001\u0007u_\nKH/\u001a\"vM\u001a,'/F\u0001j!\tQW.D\u0001l\u0015\taw'A\u0002oS>L!A\\6\u0003\u0015\tKH/\u001a\"vM\u001a,'/\u0001\u0005iCND7i\u001c3f)\u0005!\u0017AB3rk\u0006d7\u000f\u0006\u0002tmB\u0011A\n^\u0005\u0003k\u0002\u0012qAQ8pY\u0016\fg\u000eC\u0003x'\u0001\u0007\u00010\u0001\u0003uQ\u0006$\bC\u0001'z\u0013\tQ\bEA\u0002B]f\f1\"[:ESJ,7\r^8ssV\t1/\u0001\u0007mCN$Xj\u001c3jM&,G-F\u0001\u0000!\ra\u0015\u0011A\u0005\u0004\u0003\u0007\u0001#\u0001\u0002'p]\u001e\f\u0001\"\u001b;fe\u0006$xN]\u000b\u0003\u0003\u0013\u0001R!a\u0003\u0002\u0012\u0011r1\u0001TA\u0007\u0013\r\ty\u0001I\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\t\u0019\"!\u0006\u0003\u0011%#XM]1u_JT1!a\u0004!\u0003)awn\\6va:\u000bW.\u001a\u000b\u0006I\u0005m\u0011Q\u0004\u0005\u0006#^\u0001\rA\u0010\u0005\u0007\u0003?9\u0002\u0019A:\u0002\u0013\u0011L'/Z2u_JL\u0018AB2sK\u0006$X\r\u0006\u0002\u0002&A\u0019A*a\n\n\u0007\u0005%\u0002E\u0001\u0003V]&$\u0018A\u00023fY\u0016$X-A\nm_>\\W\u000f\u001d(b[\u0016,fn\u00195fG.,G\rF\u0003%\u0003c\t\u0019\u0004C\u0003R5\u0001\u0007a\b\u0003\u0004\u0002 i\u0001\ra\u001d"
)
public class PlainFile extends AbstractFile {
   private String canonicalPath;
   private final Path givenPath;
   private final java.io.File file;
   private final Path fpath;
   private volatile boolean bitmap$0;

   public Path givenPath() {
      return this.givenPath;
   }

   public java.io.File file() {
      return this.file;
   }

   private String canonicalPath$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.canonicalPath = super.canonicalPath();
            this.bitmap$0 = true;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.canonicalPath;
   }

   public String canonicalPath() {
      return !this.bitmap$0 ? this.canonicalPath$lzycompute() : this.canonicalPath;
   }

   public Some underlyingSource() {
      return new Some(this);
   }

   private Path fpath() {
      return this.fpath;
   }

   public String name() {
      return this.givenPath().name();
   }

   public String path() {
      return this.givenPath().path();
   }

   public PlainFile absolute() {
      return new PlainFile(this.givenPath().toAbsolute());
   }

   public AbstractFile container() {
      return new PlainFile(this.givenPath().parent());
   }

   public FileInputStream input() {
      return this.givenPath().toFile().inputStream();
   }

   public FileOutputStream output() {
      File qual$1 = this.givenPath().toFile();
      boolean x$1 = qual$1.outputStream$default$1();
      return qual$1.outputStream(x$1);
   }

   public Some sizeOption() {
      return new Some((int)this.givenPath().length());
   }

   public ByteBuffer toByteBuffer() {
      SeekableByteChannel chan = Files.newByteChannel(this.file().toPath(), EnumSet.of(StandardOpenOption.READ));

      ByteBuffer var10000;
      try {
         ByteBuffer buffer = ByteBuffer.allocate((int)chan.size());
         boolean endOfInput = false;

         while(!endOfInput) {
            endOfInput = chan.read(buffer) < 0;
            buffer.compact();
         }

         buffer.flip();
         var10000 = buffer;
      } finally {
         chan.close();
      }

      return var10000;
   }

   public int hashCode() {
      return this.fpath().hashCode();
   }

   public boolean equals(final Object that) {
      if (!(that instanceof PlainFile)) {
         return false;
      } else {
         PlainFile var2 = (PlainFile)that;
         Path var10000 = this.fpath();
         Path var3 = var2.fpath();
         if (var10000 == null) {
            if (var3 == null) {
               return true;
            }
         } else if (var10000.equals(var3)) {
            return true;
         }

         return false;
      }
   }

   public boolean isDirectory() {
      return this.givenPath().isDirectory();
   }

   public long lastModified() {
      return this.givenPath().lastModified();
   }

   public Iterator iterator() {
      if (!this.isDirectory()) {
         if (.MODULE$.Iterator() == null) {
            throw null;
         } else {
            return scala.collection.Iterator..scala$collection$Iterator$$_empty;
         }
      } else {
         return this.givenPath().toDirectory().list().filter((path) -> BoxesRunTime.boxToBoolean($anonfun$iterator$3(path))).map((x$2) -> new PlainFile(x$2));
      }
   }

   public AbstractFile lookupName(final String name, final boolean directory) {
      Path child = this.givenPath().$div(Path$.MODULE$.apply(name));
      return (!child.isDirectory() || !directory) && (!child.isFile() || directory) ? null : new PlainFile(child);
   }

   public void create() {
      if (!this.exists()) {
         this.givenPath().createFile(this.givenPath().createFile$default$1());
      }
   }

   public void delete() {
      if (this.givenPath().isFile()) {
         this.givenPath().delete();
      } else if (this.givenPath().isDirectory()) {
         this.givenPath().toDirectory().deleteRecursively();
      }
   }

   public AbstractFile lookupNameUnchecked(final String name, final boolean directory) {
      return new PlainFile(this.givenPath().$div(Path$.MODULE$.apply(name)));
   }

   private static final boolean existsFast$1(final Path path) {
      return (path instanceof Directory ? true : path instanceof File) ? true : path.exists();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$iterator$3(final Path path) {
      return existsFast$1(path);
   }

   public PlainFile(final Path givenPath) {
      this.givenPath = givenPath;
      scala.Predef..MODULE$.assert(this.path() != null);
      this.file = givenPath.jfile();
      this.fpath = givenPath.toAbsolute();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
