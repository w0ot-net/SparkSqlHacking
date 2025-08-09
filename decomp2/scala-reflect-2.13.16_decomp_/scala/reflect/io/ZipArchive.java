package scala.reflect.io;

import java.net.URL;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import scala.Equals;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.Iterator;
import scala.collection.MapFactory;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.Nothing;

@ScalaSignature(
   bytes = "\u0006\u0005\t}s!\u0002\u001b6\u0011\u0003ad!\u0002 6\u0011\u0003y\u0004\"\u0002#\u0002\t\u0003)\u0005\u0002\u0003$\u0002\u0005\u0004%\t!N$\t\r-\u000b\u0001\u0015!\u0003I\u0011!a\u0015A1A\u0005\u0002Uj\u0005BB)\u0002A\u0003%a\n\u0003\u0005S\u0003\t\u0007IQA\u001bT\u0011\u00199\u0016\u0001)A\u0007)\")\u0001,\u0001C\u00013\")\u0001,\u0001C\u0001E\")!.\u0001C\u0001W\")q/\u0001C\u0001q\")Q0\u0001C\u0005}\"9\u0011\u0011D\u0001\u0005\n\u0005m\u0001bBA\u0010\u0003\u0011%\u0011\u0011\u0005\u0005\b\u0003W\tA\u0011AA\u0017\r\u0019qT'!\u0001\u0002F!Ia,\u0005BC\u0002\u0013\u0005\u0013Q\n\u0005\n\u0003\u001f\n\"\u0011!Q\u0001\n\u0011D!\"!\u0015\u0012\u0005\u0003\u0005\u000b\u0011BA*\u0011\u0019!\u0015\u0003\"\u0001\u0002Z!1A)\u0005C\u0001\u0003CB!\"!\u001a\u0012\u0011\u000b\u0007I\u0011IA4\u0011\u001d\tI'\u0005C!\u0003WBa!a\u001d\u0012\t\u00039\u0005bBA;#\u0011\u0005\u0011q\u000f\u0005\b\u0003\u000f\u000bB\u0011AAE\u0011\u001d\ty)\u0005C\u0001\u0003#Cq!a%\u0012\t\u0003\t\t\nC\u0004\u0002\u0016F!\t!a&\t\u000f\u0005e\u0015\u0003\"\u0001\u0002\u0018\"9\u00111T\t\u0005\u0002\u0005]eaBAO#\u0005\u0005\u0012q\u0014\u0005\f\u0003/\t#\u0011!Q\u0001\n}\f9\u000b\u0003\u0004EC\u0011\u0005\u0011\u0011\u0016\u0005\b\u0003c\u000bC\u0011AAZ\u0011\u001d\tI'\tC!\u0003WBq!!2\"\t\u0003\n9\rC\u0004\u0002T\u0006\"\t%!6\u0007\r\u0005-\u0018\u0003AAw\u0011-\t9\u0002\u000bB\u0001B\u0003%q0a*\t\r\u0011CC\u0011AAx\u0011%\t)\u0010\u000bb\u0001\n\u0003\t9\u0010\u0003\u0005\u0003\n!\u0002\u000b\u0011BA}\u0011\u0019\t\u0019\b\u000bC!\u000f\"9!1\u0002\u0015\u0005B\t5\u0001bBA;Q\u0011\u0005#Q\u0004\u0005\b\u0005W\tB\u0011\u0003B\u0017\u0011\u001d\u0011)%\u0005Q!\n}DqAa\u0014\u0012\t\u0013\u0011\t\u0006C\u0004\u0003VE1\tAa\u0016\u0002\u0015iK\u0007/\u0011:dQ&4XM\u0003\u00027o\u0005\u0011\u0011n\u001c\u0006\u0003qe\nqA]3gY\u0016\u001cGOC\u0001;\u0003\u0015\u00198-\u00197b\u0007\u0001\u0001\"!P\u0001\u000e\u0003U\u0012!BW5q\u0003J\u001c\u0007.\u001b<f'\t\t\u0001\t\u0005\u0002B\u00056\t\u0011(\u0003\u0002Ds\t1\u0011I\\=SK\u001a\fa\u0001P5oSRtD#\u0001\u001f\u0002\u0019\rdwn]3[SB4\u0015\u000e\\3\u0016\u0003!\u0003\"!Q%\n\u0005)K$a\u0002\"p_2,\u0017M\\\u0001\u000eG2|7/\u001a.ja\u001aKG.\u001a\u0011\u0002'iL\u0007OR5mKB{w\u000e\\\"ba\u0006\u001c\u0017\u000e^=\u0016\u00039\u0003\"!Q(\n\u0005AK$aA%oi\u0006!\"0\u001b9GS2,\u0007k\\8m\u0007\u0006\u0004\u0018mY5us\u0002\n\u0011BU8pi\u0016sGO]=\u0016\u0003Q{\u0011!V\u0011\u0002-\u0006\tq&\u0001\u0006S_>$XI\u001c;ss\u0002\n\u0001B\u001a:p[\u001aKG.\u001a\u000b\u00035v\u0003\"!P.\n\u0005q+$A\u0004$jY\u0016T\u0016\u000e]!sG\"Lg/\u001a\u0005\u0006=&\u0001\raX\u0001\u0005M&dW\r\u0005\u0002>A&\u0011\u0011-\u000e\u0002\u0005\r&dW\r\u0006\u0002[G\")aL\u0003a\u0001IB\u0011Q-[\u0007\u0002M*\u0011ag\u001a\u0006\u0002Q\u0006!!.\u0019<b\u0013\t\tg-A\u0004ge>lWK\u0015'\u0015\u00051|\u0007CA\u001fn\u0013\tqWGA\u0007V%2S\u0016\u000e]!sG\"Lg/\u001a\u0005\u0006a.\u0001\r!]\u0001\u0004kJd\u0007C\u0001:v\u001b\u0005\u0019(B\u0001;h\u0003\rqW\r^\u0005\u0003mN\u00141!\u0016*M\u0003=1'o\\7NC:Lg-Z:u+JcECA=}!\ti$0\u0003\u0002|k\ta\u0011IY:ue\u0006\u001cGOR5mK\")\u0001\u000f\u0004a\u0001c\u00069A-\u001b:OC6,GcA@\u0002\u0016A!\u0011\u0011AA\b\u001d\u0011\t\u0019!a\u0003\u0011\u0007\u0005\u0015\u0011(\u0004\u0002\u0002\b)\u0019\u0011\u0011B\u001e\u0002\rq\u0012xn\u001c;?\u0013\r\ti!O\u0001\u0007!J,G-\u001a4\n\t\u0005E\u00111\u0003\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u00055\u0011\b\u0003\u0004\u0002\u00185\u0001\ra`\u0001\u0005a\u0006$\b.\u0001\u0005cCN,g*Y7f)\ry\u0018Q\u0004\u0005\u0007\u0003/q\u0001\u0019A@\u0002\u0013M\u0004H.\u001b;QCRDG#B@\u0002$\u0005\u001d\u0002BBA\u0013\u001f\u0001\u0007q0A\u0003qCRD\u0007\u0007\u0003\u0004\u0002*=\u0001\r\u0001S\u0001\u0006MJ|g\u000e^\u0001\ra\u0006$\b\u000eV8E_R$X\r\u001a\u000b\u0004\u007f\u0006=\u0002BBA\f!\u0001\u0007q\u0010K\u0006\u0011\u0003g\tI$a\u000f\u0002@\u0005\u0005\u0003cA!\u00026%\u0019\u0011qG\u001d\u0003\u0015\u0011,\u0007O]3dCR,G-A\u0004nKN\u001c\u0018mZ3\"\u0005\u0005u\u0012AF&faR\u0004cm\u001c:!G>l\u0007/\u0019;jE&d\u0017\u000e^=\u0002\u000bMLgnY3\"\u0005\u0005\r\u0013A\u0002\u001a/cMr\u0013g\u0005\u0003\u0012s\u0006\u001d\u0003cA!\u0002J%\u0019\u00111J\u001d\u0003\r\u0015\u000bX/\u00197t+\u0005!\u0017!\u00024jY\u0016\u0004\u0013a\u0002:fY\u0016\f7/\u001a\t\u0005\u0003\u0006Us0C\u0002\u0002Xe\u0012aa\u00149uS>tGCBA.\u0003;\ny\u0006\u0005\u0002>#!)a,\u0006a\u0001I\"9\u0011\u0011K\u000bA\u0002\u0005MC\u0003BA.\u0003GBQA\u0018\fA\u0002\u0011\fQbY1o_:L7-\u00197QCRDW#A@\u0002!UtG-\u001a:ms&twmU8ve\u000e,WCAA7!\u0015\t\u0015qNA.\u0013\r\t\t(\u000f\u0002\u0005'>lW-A\u0006jg\u0012K'/Z2u_JL\u0018A\u00037p_.,\bOT1nKR1\u0011\u0011PA@\u0003\u0007\u00032!QA>\u0013\r\ti(\u000f\u0002\b\u001d>$\b.\u001b8h\u0011\u0019\t\tI\u0007a\u0001\u007f\u0006!a.Y7f\u0011\u0019\t)I\u0007a\u0001\u0011\u0006IA-\u001b:fGR|'/_\u0001\u0014Y>|7.\u001e9OC6,WK\\2iK\u000e\\W\r\u001a\u000b\u0007\u0003s\nY)!$\t\r\u0005\u00055\u00041\u0001\u0000\u0011\u0019\t)i\u0007a\u0001\u0011\u000611M]3bi\u0016$\"!!\u001f\u0002\r\u0011,G.\u001a;f\u0003\u0019yW\u000f\u001e9viV\u0011\u0011\u0011P\u0001\nG>tG/Y5oKJ\f\u0001\"\u00192t_2,H/\u001a\u0002\u0006\u000b:$(/_\n\u0004C\u0005\u0005\u0006cA\u001f\u0002$&\u0019\u0011QU\u001b\u0003\u0017YK'\u000f^;bY\u001aKG.Z\u0005\u0005\u0003/\t\u0019\u000b\u0006\u0003\u0002,\u0006=\u0006cAAWC5\t\u0011\u0003\u0003\u0004\u0002\u0018\r\u0002\ra`\u0001\u000bO\u0016$\u0018I]2iSZ,WCAA[!\u0011\t9,!1\u000e\u0005\u0005e&\u0002BA^\u0003{\u000b1A_5q\u0015\r\tylZ\u0001\u0005kRLG.\u0003\u0003\u0002D\u0006e&a\u0002.ja\u001aKG.Z\u0001\ti>\u001cFO]5oOR\u0011\u0011\u0011\u001a\t\u0005\u0003\u0017\f\t.\u0004\u0002\u0002N*\u0019\u0011qZ4\u0002\t1\fgnZ\u0005\u0005\u0003#\ti-A\tv]N\fg-\u001a+p\u0005f$X-\u0011:sCf,\"!a6\u0011\u000b\u0005\u000bI.!8\n\u0007\u0005m\u0017HA\u0003BeJ\f\u0017\u0010E\u0002B\u0003?L1!!9:\u0005\u0011\u0011\u0015\u0010^3*\u0011\u0005\n)\u000f\u000bB\u0012\u0005O1a!a:\"\u0001\u0005%(!\u0004\u001fm_\u000e\fG\u000eI2iS2$gh\u0005\u0003\u0002f\u0006-&\u0001\u0003#je\u0016sGO]=\u0014\u0007!\nY\u000b\u0006\u0003\u0002r\u0006M\bcAAWQ!1\u0011q\u0003\u0016A\u0002}\fq!\u001a8ue&,7/\u0006\u0002\u0002zBA\u00111 B\u0003\u0003\u0013\fY+\u0004\u0002\u0002~*!\u0011q B\u0001\u0003\u001diW\u000f^1cY\u0016T1Aa\u0001:\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0005\u000f\tiPA\u0004ICNDW*\u00199\u0002\u0011\u0015tGO]5fg\u0002\n\u0001\"\u001b;fe\u0006$xN]\u000b\u0003\u0005\u001f\u0001bA!\u0005\u0003\u0018\u0005-fbA!\u0003\u0014%\u0019!QC\u001d\u0002\u000fA\f7m[1hK&!!\u0011\u0004B\u000e\u0005!IE/\u001a:bi>\u0014(b\u0001B\u000bsQ1\u00111\u0016B\u0010\u0005CAa!!!0\u0001\u0004y\bBBAC_\u0001\u0007\u0001*C\u0002\u0003&m\u0013\u0011\u0002T1{s\u0016sGO]=\n\u0007\t%2L\u0001\u0006MK\u0006\\\u00170\u00128uef\faaZ3u\t&\u0014HCBAy\u0005_\u0011Y\u0004C\u0004\u00032A\u0002\rAa\r\u0002\t\u0011L'o\u001d\t\b\u0005k\u00119d`Ay\u001b\t\ti,\u0003\u0003\u0003:\u0005u&aA'ba\"9!Q\b\u0019A\u0002\t}\u0012!B3oiJL\b\u0003BA\\\u0005\u0003JAAa\u0011\u0002:\nA!,\u001b9F]R\u0014\u00180A\u0006mCN$H)\u001b:OC6,\u0007fA\u0019\u0003JA\u0019\u0011Ia\u0013\n\u0007\t5\u0013H\u0001\u0005w_2\fG/\u001b7f\u0003A!\u0017N\u001d(b[\u0016,6/\u001b8h\u0019\u0006\u001cH\u000fF\u0002\u0000\u0005'Ba!!!3\u0001\u0004y\u0018!B2m_N,GC\u0001B-!\r\t%1L\u0005\u0004\u0005;J$\u0001B+oSR\u0004"
)
public abstract class ZipArchive extends AbstractFile implements Equals {
   private String canonicalPath;
   private final java.io.File file;
   private volatile String lastDirName;
   private volatile boolean bitmap$0;

   /** @deprecated */
   public static String pathToDotted(final String path) {
      return ZipArchive$.MODULE$.pathToDotted(path);
   }

   public static AbstractFile fromManifestURL(final URL url) {
      ZipArchive$ var10000 = ZipArchive$.MODULE$;
      return new ManifestResources(url);
   }

   public static URLZipArchive fromURL(final URL url) {
      ZipArchive$ var10000 = ZipArchive$.MODULE$;
      return new URLZipArchive(url);
   }

   public static FileZipArchive fromFile(final java.io.File file) {
      return ZipArchive$.MODULE$.fromFile(file);
   }

   public static FileZipArchive fromFile(final File file) {
      return ZipArchive$.MODULE$.fromFile(file);
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

   public boolean isDirectory() {
      return true;
   }

   public Nothing lookupName(final String name, final boolean directory) {
      return this.unsupported();
   }

   public Nothing lookupNameUnchecked(final String name, final boolean directory) {
      return this.unsupported();
   }

   public Nothing create() {
      return this.unsupported();
   }

   public Nothing delete() {
      return this.unsupported();
   }

   public Nothing output() {
      return this.unsupported();
   }

   public Nothing container() {
      return this.unsupported();
   }

   public Nothing absolute() {
      return this.unsupported();
   }

   public DirEntry getDir(final Map dirs, final ZipEntry entry) {
      String name = entry.isDirectory() ? entry.getName() : this.dirNameUsingLast(entry.getName());
      return this.ensureDir$1(name, dirs);
   }

   private String dirNameUsingLast(final String name) {
      String last = this.lastDirName;
      if (name.length() > last.length() + 1 && name.startsWith(last) && name.charAt(last.length()) == '/' && name.indexOf(47, last.length() + 1) == -1) {
         return this.lastDirName;
      } else {
         String result = ZipArchive$.MODULE$.scala$reflect$io$ZipArchive$$dirName(name);
         this.lastDirName = result;
         return result;
      }
   }

   public abstract void close();

   private final DirEntry ensureDir$1(final String path, final Map dirs$1) {
      DirEntry var3 = (DirEntry)dirs$1.get(path);
      if (var3 == null) {
         DirEntry parent = this.ensureDir$1(ZipArchive$.MODULE$.scala$reflect$io$ZipArchive$$dirName(path), dirs$1);
         DirEntry dir = new DirEntry(path);
         parent.entries().update(ZipArchive$.MODULE$.scala$reflect$io$ZipArchive$$baseName(path), dir);
         dirs$1.put(path, dir);
         return dir;
      } else {
         return var3;
      }
   }

   public ZipArchive(final java.io.File file, final Option release) {
      this.file = file;
      this.lastDirName = "/";
   }

   public ZipArchive(final java.io.File file) {
      this(file, .MODULE$);
   }

   public abstract class Entry extends VirtualFile {
      // $FF: synthetic field
      public final ZipArchive $outer;

      public ZipFile getArchive() {
         return null;
      }

      public Some underlyingSource() {
         return new Some(this.scala$reflect$io$ZipArchive$Entry$$$outer());
      }

      public String toString() {
         return (new StringBuilder(2)).append(this.scala$reflect$io$ZipArchive$Entry$$$outer().path()).append("(").append(super.path()).append(")").toString();
      }

      public byte[] unsafeToByteArray() {
         return this.toByteArray();
      }

      // $FF: synthetic method
      public ZipArchive scala$reflect$io$ZipArchive$Entry$$$outer() {
         return this.$outer;
      }

      public Entry(final String path) {
         if (ZipArchive.this == null) {
            throw null;
         } else {
            this.$outer = ZipArchive.this;
            super(ZipArchive$.MODULE$.scala$reflect$io$ZipArchive$$baseName(path), path);
         }
      }
   }

   public class DirEntry extends Entry {
      private final HashMap entries;

      public HashMap entries() {
         return this.entries;
      }

      public boolean isDirectory() {
         return true;
      }

      public Iterator iterator() {
         return this.entries().valuesIterator();
      }

      public Entry lookupName(final String name, final boolean directory) {
         if (directory) {
            Option var7 = this.entries().get((new StringBuilder(1)).append(name).append("/").toString());
            scala..eq.colon.eq orNull_evx = scala..less.colon.less..MODULE$.refl();
            if (var7 == null) {
               throw null;
            } else {
               Option orNull_this = var7;
               return (Entry)(orNull_this.isEmpty() ? ((scala..less.colon.less)orNull_evx).apply((Object)null) : orNull_this.get());
            }
         } else {
            Option var10000 = this.entries().get(name);
            scala..eq.colon.eq orNull_ev = scala..less.colon.less..MODULE$.refl();
            if (var10000 == null) {
               throw null;
            } else {
               Option orNull_this = var10000;
               return (Entry)(orNull_this.isEmpty() ? ((scala..less.colon.less)orNull_ev).apply((Object)null) : orNull_this.get());
            }
         }
      }

      // $FF: synthetic method
      public ZipArchive scala$reflect$io$ZipArchive$DirEntry$$$outer() {
         return this.$outer;
      }

      public DirEntry(final String path) {
         super(path);
         this.entries = (HashMap)MapFactory.apply$(scala.collection.mutable.HashMap..MODULE$, scala.collection.immutable.Nil..MODULE$);
      }
   }
}
