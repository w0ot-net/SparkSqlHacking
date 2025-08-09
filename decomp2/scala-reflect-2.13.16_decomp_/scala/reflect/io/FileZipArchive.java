package scala.reflect.io;

import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import scala.Option;
import scala.Some;
import scala.collection.Iterator;
import scala.io.Codec.;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.JDK9Reflectors;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mf\u0001\u0002\u0016,\u0005IB\u0011b\u000e\u0001\u0003\u0002\u0003\u0006I\u0001O \t\u0011\u0001\u0003!\u0011!Q\u0001\n\u0005CQ\u0001\u0015\u0001\u0005\u0002ECQ\u0001\u0015\u0001\u0005\u0002U;Qa\u0016\u0001\t\na3QA\u0017\u0001\t\nmCQ\u0001\u0015\u0004\u0005\u0002}Ca\u0001\u0019\u0004!\u0002\u0013\t\u0007\"B8\u0007\t\u0003\u0001\b\"\u0002!\u0007\t\u0003\t\b\"B<\u0007\t\u0003A\bBB=\u0001A\u0013%!P\u0002\u0004|\u0001\u0001\u0006I\u0001 \u0005\f\u0003\u0003i!\u0011!Q\u0001\n\u0015\u000b\u0019\u0001\u0003\u0006\u0002\f5\u0011\t\u0011)A\u0005\u0003\u001bA!\"a\u0005\u000e\u0005\u0003\u0005\u000b\u0011BA\u000b\u0011\u0019\u0001V\u0002\"\u0001\u0002\u001c!9\u0011QE\u0007\u0005B\u0005\u001d\u0002bBA\u0015\u001b\u0011\u0005\u00131\u0006\u0005\b\u0003giA\u0011IA\u001b\r!\tI\u0004\u0001Q\u0001\n\u0005m\u0002bCA\u0001+\t\u0005\t\u0015!\u0003F\u0003\u0007A!\"a\u0003\u0016\u0005\u0003\u0005\u000b\u0011BA\u0007\u0011)\t\u0019\"\u0006B\u0001B\u0003%\u0011Q\u0003\u0005\u0007!V!\t!!\u0010\t\u000f\u0005\u0015R\u0003\"\u0011\u0002(!9\u0011\u0011F\u000b\u0005B\u0005-\u0002bBA\u001a+\u0011\u0005\u0013Q\u0007\u0005\t\u0003\u000f\u0002\u0001\u0015!\u0003\u0002J!Q\u0011q\u000b\u0001\t\u0006\u0004%\t!!\u0017\t\u0015\u0005m\u0003\u0001#b\u0001\n\u0003\ti\u0006C\u0004\u0002f\u0001!\t!a\u001a\t\u000f\u0005\u0005\u0001\u0001\"\u0001\u0002x!9\u0011Q\u0001\u0001\u0005\u0002\u0005]\u0004bBA\u0015\u0001\u0011\u0005\u00111\u0011\u0005\b\u0003K\u0001A\u0011AA\u0014\u0011\u001d\t\u0019\u0004\u0001C!\u0003\u0017Cq!a%\u0001\t\u0003\n)\nC\u0004\u0002(\u0002!\t%!+\t\u000f\u0005-\u0006\u0001\"\u0011\u0002.\")q\u000f\u0001C!q\nqa)\u001b7f5&\u0004\u0018I]2iSZ,'B\u0001\u0017.\u0003\tIwN\u0003\u0002/_\u00059!/\u001a4mK\u000e$(\"\u0001\u0019\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M\u0011\u0001a\r\t\u0003iUj\u0011aK\u0005\u0003m-\u0012!BW5q\u0003J\u001c\u0007.\u001b<f\u0003\u00111\u0017\u000e\\3\u0011\u0005ejT\"\u0001\u001e\u000b\u00051Z$\"\u0001\u001f\u0002\t)\fg/Y\u0005\u0003}i\u0012AAR5mK&\u0011q'N\u0001\be\u0016dW-Y:f!\r\u00115)R\u0007\u0002_%\u0011Ai\f\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0005\u0019keBA$L!\tAu&D\u0001J\u0015\tQ\u0015'\u0001\u0004=e>|GOP\u0005\u0003\u0019>\na\u0001\u0015:fI\u00164\u0017B\u0001(P\u0005\u0019\u0019FO]5oO*\u0011AjL\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007I\u001bF\u000b\u0005\u00025\u0001!)qg\u0001a\u0001q!)\u0001i\u0001a\u0001\u0003R\u0011!K\u0016\u0005\u0006o\u0011\u0001\r\u0001O\u0001\fu&\u0004h)\u001b7f!>|G\u000e\u0005\u0002Z\r5\t\u0001AA\u0006{SB4\u0015\u000e\\3Q_>d7C\u0001\u0004]!\t\u0011U,\u0003\u0002__\t1\u0011I\\=SK\u001a$\u0012\u0001W\u0001\tu&\u0004h)\u001b7fgB\u0019!mZ5\u000e\u0003\rT!\u0001Z3\u0002\u0015\r|gnY;se\u0016tGO\u0003\u0002gw\u0005!Q\u000f^5m\u0013\tA7M\u0001\nBeJ\f\u0017P\u00117pG.LgnZ)vKV,\u0007C\u00016n\u001b\u0005Y'B\u00017f\u0003\rQ\u0018\u000e]\u0005\u0003].\u0014qAW5q\r&dW-A\u0004bGF,\u0018N]3\u0016\u0003%$\"A];\u0011\u0005\t\u001b\u0018B\u0001;0\u0005\u0011)f.\u001b;\t\u000bYT\u0001\u0019A5\u0002\u0005i4\u0017!B2m_N,G#\u0001:\u0002\u0017=\u0004XM\u001c.ja\u001aKG.\u001a\u000b\u0002S\nIA*\u0019>z\u000b:$(/_\n\u0003\u001bu\u0004\"!\u0017@\n\u0005},$!B#oiJL\u0018\u0001\u00028b[\u0016LA!!\u0002\u0002\b\u0005!\u0001/\u0019;i\u0013\r\tIa\u000b\u0002\f-&\u0014H/^1m\r&dW-\u0001\u0003uS6,\u0007c\u0001\"\u0002\u0010%\u0019\u0011\u0011C\u0018\u0003\t1{gnZ\u0001\u0005g&TX\rE\u0002C\u0003/I1!!\u00070\u0005\rIe\u000e\u001e\u000b\t\u0003;\ty\"!\t\u0002$A\u0011\u0011,\u0004\u0005\u0007\u0003\u0003\t\u0002\u0019A#\t\u000f\u0005-\u0011\u00031\u0001\u0002\u000e!9\u00111C\tA\u0002\u0005U\u0011\u0001\u00047bgRlu\u000eZ5gS\u0016$WCAA\u0007\u0003\u0015Ig\u000e];u+\t\ti\u0003E\u0002:\u0003_I1!!\r;\u0005-Ie\u000e];u'R\u0014X-Y7\u0002\u0015ML'0Z(qi&|g.\u0006\u0002\u00028A!!iQA\u000b\u0005)aU-Y6z\u000b:$(/_\n\u0003+u$\u0002\"a\u0010\u0002B\u0005\r\u0013Q\t\t\u00033VAa!!\u0001\u001a\u0001\u0004)\u0005bBA\u00063\u0001\u0007\u0011Q\u0002\u0005\b\u0003'I\u0002\u0019AA\u000b\u0003\u0011!\u0017N]:\u0011\u000f\u0005-\u0013QJ#\u0002R5\tQ-C\u0002\u0002P\u0015\u0014q\u0001S1tQ6\u000b\u0007\u000fE\u0002Z\u0003'J1!!\u00166\u0005!!\u0015N]#oiJL\u0018\u0001\u0002:p_R,\"!!\u0015\u0002\u000f\u0005dG\u000eR5sgV\u0011\u0011q\f\t\b\u0003\u0017\n\t'RA)\u0013\r\t\u0019'\u001a\u0002\u0004\u001b\u0006\u0004\u0018\u0001C5uKJ\fGo\u001c:\u0016\u0005\u0005%\u0004#BA6\u0003cjhb\u0001\"\u0002n%\u0019\u0011qN\u0018\u0002\u000fA\f7m[1hK&!\u00111OA;\u0005!IE/\u001a:bi>\u0014(bAA8_U\u0011\u0011\u0011\u0010\t\u0005\u0003w\n\t)\u0004\u0002\u0002~)\u0019\u0011qP\u001e\u0002\t1\fgnZ\u0005\u0004\u001d\u0006uTCAAC!\rI\u0014qQ\u0005\u0004\u0003\u0013S$a\u0004$jY\u0016Le\u000e];u'R\u0014X-Y7\u0016\u0005\u00055\u0005#\u0002\"\u0002\u0010\u0006U\u0011bAAI_\t!1k\\7f\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BAL\u0003;\u00032AQAM\u0013\r\tYj\f\u0002\b\u0005>|G.Z1o\u0011\u001d\tyJ\na\u0001\u0003C\u000bQa\u001c;iKJ\u00042AQAR\u0013\r\t)k\f\u0002\u0004\u0003:L\u0018\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005U\u0011AB3rk\u0006d7\u000f\u0006\u0003\u0002\u0018\u0006=\u0006bBAYQ\u0001\u0007\u0011\u0011U\u0001\u0005i\"\fG\u000f"
)
public final class FileZipArchive extends ZipArchive {
   private volatile zipFilePool$ zipFilePool$module;
   private ZipArchive.DirEntry root;
   private Map allDirs;
   private final Option release;
   private final HashMap dirs;
   private volatile byte bitmap$0;

   public zipFilePool$ scala$reflect$io$FileZipArchive$$zipFilePool() {
      if (this.zipFilePool$module == null) {
         this.zipFilePool$lzycompute$1();
      }

      return this.zipFilePool$module;
   }

   public ZipFile scala$reflect$io$FileZipArchive$$openZipFile() {
      try {
         Option var1 = this.release;
         Object var10000;
         if (var1 instanceof Some) {
            String r = (String)((Some)var1).value();
            if (super.file().getName().endsWith(".jar")) {
               Object releaseVersion = JDK9Reflectors.runtimeVersionParse(r);
               var10000 = JDK9Reflectors.newJarFile(super.file(), true, 1, releaseVersion);
               return (ZipFile)var10000;
            }
         }

         var10000 = new ZipFile(super.file());
         return (ZipFile)var10000;
      } catch (IOException var5) {
         throw new IOException((new StringBuilder(16)).append("Error accessing ").append(super.file().getPath()).toString(), var5);
      }
   }

   private ZipArchive.DirEntry root$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            ZipArchive.DirEntry root = new ZipArchive.DirEntry("/");
            this.dirs.put("/", root);
            ZipFile zipFile = this.scala$reflect$io$FileZipArchive$$openZipFile();
            Enumeration entries = zipFile.entries();

            try {
               while(entries.hasMoreElements()) {
                  ZipEntry zipEntry = (ZipEntry)entries.nextElement();
                  if (!zipEntry.getName().startsWith("META-INF/versions/") && !zipEntry.isDirectory()) {
                     ZipArchive.DirEntry dir = this.getDir(this.dirs, zipEntry);
                     ZipEntry mrEntry = this.release.isDefined() ? zipFile.getEntry(zipEntry.getName()) : zipEntry;
                     ZipArchive.Entry f = (ZipArchive.Entry)(ZipArchive$.MODULE$.closeZipFile() ? new LazyEntry(zipEntry.getName(), mrEntry.getTime(), (int)mrEntry.getSize()) : new LeakyEntry(zipEntry.getName(), mrEntry.getTime(), (int)mrEntry.getSize()));
                     dir.entries().update(f.name(), f);
                  }
               }
            } finally {
               if (!ZipArchive$.MODULE$.closeZipFile()) {
                  this.scala$reflect$io$FileZipArchive$$zipFilePool().release(zipFile);
               }

            }

            this.root = root;
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var14) {
         throw var14;
      }

      return this.root;
   }

   public ZipArchive.DirEntry root() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.root$lzycompute() : this.root;
   }

   private Map allDirs$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.root();
            this.allDirs = this.dirs;
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.allDirs;
   }

   public Map allDirs() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.allDirs$lzycompute() : this.allDirs;
   }

   public Iterator iterator() {
      return this.root().iterator();
   }

   public String name() {
      return super.file().getName();
   }

   public String path() {
      return super.file().getPath();
   }

   public FileInputStream input() {
      return File$.MODULE$.apply(Path$.MODULE$.apply(super.file()), .MODULE$.fallbackSystemCodec()).inputStream();
   }

   public long lastModified() {
      return super.file().lastModified();
   }

   public Some sizeOption() {
      return new Some((int)super.file().length());
   }

   public boolean canEqual(final Object other) {
      return other instanceof FileZipArchive;
   }

   public int hashCode() {
      return super.file().hashCode();
   }

   public boolean equals(final Object that) {
      if (!(that instanceof FileZipArchive)) {
         return false;
      } else {
         FileZipArchive var2 = (FileZipArchive)that;
         java.io.File var10000 = super.file().getAbsoluteFile();
         java.io.File var3 = var2.file().getAbsoluteFile();
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

   public void close() {
      this.scala$reflect$io$FileZipArchive$$zipFilePool().close();
   }

   private final void zipFilePool$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.zipFilePool$module == null) {
            this.zipFilePool$module = new zipFilePool$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   public FileZipArchive(final java.io.File file, final Option release) {
      super(file, release);
      this.release = release;
      this.dirs = new HashMap();
   }

   public FileZipArchive(final java.io.File file) {
      this(file, scala.None..MODULE$);
   }

   private class zipFilePool$ {
      private final ArrayBlockingQueue zipFiles;
      // $FF: synthetic field
      private final FileZipArchive $outer;

      public ZipFile acquire() {
         ZipFile zf = (ZipFile)this.zipFiles.poll(0L, TimeUnit.MILLISECONDS);
         return zf == null ? this.$outer.scala$reflect$io$FileZipArchive$$openZipFile() : zf;
      }

      public void release(final ZipFile zf) {
         if (!this.zipFiles.offer(zf, 0L, TimeUnit.MILLISECONDS)) {
            zf.close();
         }
      }

      public void close() {
         ArrayList zipFilesToClose = new ArrayList();
         this.zipFiles.drainTo(zipFilesToClose);
         zipFilesToClose.iterator().forEachRemaining((x$2) -> x$2.close());
      }

      public zipFilePool$() {
         if (FileZipArchive.this == null) {
            throw null;
         } else {
            this.$outer = FileZipArchive.this;
            super();
            this.zipFiles = new ArrayBlockingQueue(ZipArchive$.MODULE$.zipFilePoolCapacity());
         }
      }
   }

   private class LazyEntry extends ZipArchive.Entry {
      private final long time;
      private final int size;

      public long lastModified() {
         return this.time;
      }

      public InputStream input() {
         ZipFile zipFile = this.scala$reflect$io$FileZipArchive$LazyEntry$$$outer().scala$reflect$io$FileZipArchive$$openZipFile();
         ZipEntry entry = zipFile.getEntry(super.path());
         InputStream delegate = zipFile.getInputStream(entry);
         return new FilterInputStream(delegate, zipFile) {
            private final ZipFile zipFile$1;

            public void close() {
               this.zipFile$1.close();
            }

            public {
               this.zipFile$1 = zipFile$1;
            }
         };
      }

      public Option sizeOption() {
         return new Some(this.size);
      }

      // $FF: synthetic method
      public FileZipArchive scala$reflect$io$FileZipArchive$LazyEntry$$$outer() {
         return (FileZipArchive)this.$outer;
      }

      public LazyEntry(final String name, final long time, final int size) {
         super(name);
         this.time = time;
         this.size = size;
      }
   }

   private class LeakyEntry extends ZipArchive.Entry {
      private final long time;
      private final int size;

      public long lastModified() {
         return this.time;
      }

      public InputStream input() {
         ZipFile zipFile = this.scala$reflect$io$FileZipArchive$LeakyEntry$$$outer().scala$reflect$io$FileZipArchive$$zipFilePool().acquire();
         ZipEntry entry = zipFile.getEntry(super.path());
         InputStream delegate = zipFile.getInputStream(entry);
         return new FilterInputStream(delegate, zipFile) {
            // $FF: synthetic field
            private final LeakyEntry $outer;
            private final ZipFile zipFile$2;

            public void close() {
               this.$outer.scala$reflect$io$FileZipArchive$LeakyEntry$$$outer().scala$reflect$io$FileZipArchive$$zipFilePool().release(this.zipFile$2);
            }

            public {
               if (LeakyEntry.this == null) {
                  throw null;
               } else {
                  this.$outer = LeakyEntry.this;
                  this.zipFile$2 = zipFile$2;
               }
            }
         };
      }

      public Option sizeOption() {
         return new Some(this.size);
      }

      // $FF: synthetic method
      public FileZipArchive scala$reflect$io$FileZipArchive$LeakyEntry$$$outer() {
         return (FileZipArchive)this.$outer;
      }

      public LeakyEntry(final String name, final long time, final int size) {
         super(name);
         this.time = time;
         this.size = size;
      }
   }
}
