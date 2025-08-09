package scala.reflect.io;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.invoke.SerializedLambda;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.LinkOption;
import java.nio.file.NotDirectoryException;
import java.nio.file.Paths;
import java.util.Iterator;
import scala.Option;
import scala.Some;
import scala.collection.StringOps.;
import scala.collection.convert.AsScalaExtensions;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]b\u0001B\r\u001b\u0005\u0005B\u0001B\n\u0001\u0003\u0006\u0004%\ta\n\u0005\te\u0001\u0011\t\u0011)A\u0005Q!)1\u0007\u0001C\u0001i!)1\u0006\u0001C!o!AQ\b\u0001EC\u0002\u0013\u0005c\bC\u0003K\u0001\u0011\u00053\nC\u0004Q\u0001\t\u0007I\u0011B)\t\r]\u0003\u0001\u0015!\u0003S\u0011\u0015A\u0006\u0001\"\u0001R\u0011\u0015I\u0006\u0001\"\u0001R\u0011\u0015Q\u0006\u0001\"\u0001\\\u0011\u0015a\u0006\u0001\"\u0011^\u0011\u0015q\u0006\u0001\"\u0011`\u0011\u0015\u0019\u0007\u0001\"\u0011e\u0011\u0015A\u0007\u0001\"\u0011j\u0011\u0015\u0001\b\u0001\"\u0011r\u0011\u0015\u0011\b\u0001\"\u0011t\u0011\u0015a\b\u0001\"\u0001~\u0011\u0015q\b\u0001\"\u0001\u0000\u0011\u001d\t9\u0001\u0001C\u0001\u0003\u0013Aq!!\u0007\u0001\t\u0003\tY\u0002C\u0004\u0002$\u0001!\t!!\n\t\u000f\u00055\u0002\u0001\"\u0001\u0002&!9\u0011q\u0006\u0001\u0005\u0002\u0005E\"\u0001\u0004)mC&tg*[8GS2,'BA\u000e\u001d\u0003\tIwN\u0003\u0002\u001e=\u00059!/\u001a4mK\u000e$(\"A\u0010\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M\u0011\u0001A\t\t\u0003G\u0011j\u0011AG\u0005\u0003Ki\u0011A\"\u00112tiJ\f7\r\u001e$jY\u0016\fqA\\5p!\u0006$\b.F\u0001)!\tI\u0003'D\u0001+\u0015\tYC&\u0001\u0003gS2,'BA\u0017/\u0003\rq\u0017n\u001c\u0006\u0002_\u0005!!.\u0019<b\u0013\t\t$F\u0001\u0003QCRD\u0017\u0001\u00038j_B\u000bG\u000f\u001b\u0011\u0002\rqJg.\u001b;?)\t)d\u0007\u0005\u0002$\u0001!)ae\u0001a\u0001QU\t\u0001\b\u0005\u0002:w5\t!H\u0003\u0002\u001c]%\u0011AH\u000f\u0002\u0005\r&dW-A\u0007dC:|g.[2bYB\u000bG\u000f[\u000b\u0002\u007fA\u0011\u0001i\u0012\b\u0003\u0003\u0016\u0003\"A\u0011\u0010\u000e\u0003\rS!\u0001\u0012\u0011\u0002\rq\u0012xn\u001c;?\u0013\t1e$\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u0011&\u0013aa\u0015;sS:<'B\u0001$\u001f\u0003A)h\u000eZ3sYfLgnZ*pkJ\u001cW-F\u0001M!\rieJI\u0007\u0002=%\u0011qJ\b\u0002\u0007\u001fB$\u0018n\u001c8\u0002\u000b\u0019\u0004\u0018\r\u001e5\u0016\u0003I\u0003\"a\u0015,\u000e\u0003QS!!\u0016\u0018\u0002\t1\fgnZ\u0005\u0003\u0011R\u000baA\u001a9bi\"\u0004\u0013\u0001\u00028b[\u0016\fA\u0001]1uQ\u0006A\u0011MY:pYV$X-F\u00016\u0003%\u0019wN\u001c;bS:,'/F\u0001#\u0003\u0015Ig\u000e];u+\u0005\u0001\u0007CA\u001db\u0013\t\u0011'HA\u0006J]B,Ho\u0015;sK\u0006l\u0017AB8viB,H/F\u0001f!\tId-\u0003\u0002hu\taq*\u001e;qkR\u001cFO]3b[\u0006Q1/\u001b>f\u001fB$\u0018n\u001c8\u0016\u0003)\u00042!T6n\u0013\tagD\u0001\u0003T_6,\u0007CA'o\u0013\tygDA\u0002J]R\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002[\u00061Q-];bYN$\"\u0001^<\u0011\u00055+\u0018B\u0001<\u001f\u0005\u001d\u0011un\u001c7fC:DQ\u0001_\tA\u0002e\fA\u0001\u001e5biB\u0011QJ_\u0005\u0003wz\u00111!\u00118z\u0003-I7\u000fR5sK\u000e$xN]=\u0016\u0003Q\fA\u0002\\1ti6{G-\u001b4jK\u0012,\"!!\u0001\u0011\u00075\u000b\u0019!C\u0002\u0002\u0006y\u0011A\u0001T8oO\u0006A\u0011\u000e^3sCR|'/\u0006\u0002\u0002\fA)\u0011QBA\nE9\u0019Q*a\u0004\n\u0007\u0005Ea$A\u0004qC\u000e\\\u0017mZ3\n\t\u0005U\u0011q\u0003\u0002\t\u0013R,'/\u0019;pe*\u0019\u0011\u0011\u0003\u0010\u0002\u00151|wn[;q\u001d\u0006lW\rF\u0003#\u0003;\ty\u0002C\u0003Y+\u0001\u0007q\b\u0003\u0004\u0002\"U\u0001\r\u0001^\u0001\nI&\u0014Xm\u0019;pef\faa\u0019:fCR,GCAA\u0014!\ri\u0015\u0011F\u0005\u0004\u0003Wq\"\u0001B+oSR\fa\u0001Z3mKR,\u0017a\u00057p_.,\bOT1nKVs7\r[3dW\u0016$G#\u0002\u0012\u00024\u0005U\u0002\"\u0002-\u0019\u0001\u0004y\u0004BBA\u00111\u0001\u0007A\u000f"
)
public final class PlainNioFile extends AbstractFile {
   private String canonicalPath;
   private final java.nio.file.Path nioPath;
   private final String fpath;
   private volatile boolean bitmap$0;

   public java.nio.file.Path nioPath() {
      return this.nioPath;
   }

   public java.io.File file() {
      try {
         return this.nioPath().toFile();
      } catch (UnsupportedOperationException var1) {
         return null;
      }
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

   public Option underlyingSource() {
      FileSystem fileSystem = this.nioPath().getFileSystem();
      String var2 = fileSystem.provider().getScheme();
      switch (var2 == null ? 0 : var2.hashCode()) {
         case 104987:
            if ("jar".equals(var2)) {
               Iterator fileStores = fileSystem.getFileStores().iterator();
               if (fileStores.hasNext()) {
                  String jarPath = ((FileStore)fileStores.next()).name();

                  try {
                     return new Some(new PlainNioFile(Paths.get(.MODULE$.stripSuffix$extension(jarPath, fileSystem.getSeparator()))));
                  } catch (InvalidPathException var6) {
                     return scala.None..MODULE$;
                  }
               }

               return scala.None..MODULE$;
            }
            break;
         case 105516:
            if ("jrt".equals(var2)) {
               if (this.nioPath().getNameCount() > 2 && this.nioPath().startsWith("/modules")) {
                  java.nio.file.Path moduleName = this.nioPath().getName(1);
                  return new Some(new PlainNioFile(Paths.get(System.getProperty("java.home"), "jmods", (new StringBuilder(5)).append(moduleName.toString()).append(".jmod").toString())));
               }

               return scala.None..MODULE$;
            }
      }

      return scala.None..MODULE$;
   }

   private String fpath() {
      return this.fpath;
   }

   public String name() {
      return this.nioPath().getFileName().toString();
   }

   public String path() {
      return this.nioPath().toString();
   }

   public PlainNioFile absolute() {
      return new PlainNioFile(this.nioPath().toAbsolutePath());
   }

   public AbstractFile container() {
      return new PlainNioFile(this.nioPath().getParent());
   }

   public InputStream input() {
      return Files.newInputStream(this.nioPath());
   }

   public OutputStream output() {
      return Files.newOutputStream(this.nioPath());
   }

   public Some sizeOption() {
      return new Some((int)Files.size(this.nioPath()));
   }

   public int hashCode() {
      return this.fpath().hashCode();
   }

   public boolean equals(final Object that) {
      if (!(that instanceof PlainNioFile)) {
         return false;
      } else {
         PlainNioFile var2 = (PlainNioFile)that;
         String var10000 = this.fpath();
         String var3 = var2.fpath();
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
      return Files.isDirectory(this.nioPath(), new LinkOption[0]);
   }

   public long lastModified() {
      return Files.getLastModifiedTime(this.nioPath()).toMillis();
   }

   public scala.collection.Iterator iterator() {
      try {
         Iterator it = Files.newDirectoryStream(this.nioPath()).iterator();
         return AsScalaExtensions.IteratorHasAsScala$(scala.jdk.CollectionConverters..MODULE$, it).asScala().map((x$3) -> new PlainNioFile(x$3));
      } catch (NotDirectoryException var2) {
         if (scala.package..MODULE$.Iterator() == null) {
            throw null;
         } else {
            return scala.collection.Iterator..scala$collection$Iterator$$_empty;
         }
      }
   }

   public AbstractFile lookupName(final String name, final boolean directory) {
      java.nio.file.Path child = this.nioPath().resolve(name);
      return (!Files.isDirectory(child, new LinkOption[0]) || !directory) && (!Files.isRegularFile(child, new LinkOption[0]) || directory) ? null : new PlainNioFile(child);
   }

   public void create() {
      if (!this.exists()) {
         Files.createFile(this.nioPath());
      }
   }

   public void delete() {
      if (Files.isRegularFile(this.nioPath(), new LinkOption[0])) {
         Files.deleteIfExists(this.nioPath());
      } else if (Files.isDirectory(this.nioPath(), new LinkOption[0])) {
         (new Directory(this.nioPath().toFile())).deleteRecursively();
      }
   }

   public AbstractFile lookupNameUnchecked(final String name, final boolean directory) {
      return new PlainNioFile(this.nioPath().resolve(name));
   }

   public PlainNioFile(final java.nio.file.Path nioPath) {
      this.nioPath = nioPath;
      scala.Predef..MODULE$.assert(nioPath != null);
      this.fpath = nioPath.toAbsolutePath().toString();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
