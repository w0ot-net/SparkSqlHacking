package scala.reflect.io;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import scala.Function3;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.collection.AbstractIterable;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.Nothing;

@ScalaSignature(
   bytes = "\u0006\u0005\tuq!B\u00193\u0011\u0003Id!B\u001e3\u0011\u0003a\u0004\"B!\u0002\t\u0003\u0011\u0005\"B\"\u0002\t\u0003!\u0005BB\"\u0002\t\u0003\t\u0019\u0010\u0003\u0004D\u0003\u0011\u0005\u0011Q \u0005\b\u0005\u000b\tA\u0011\u0001B\u0004\u0011\u001d\u0011)!\u0001C\u0001\u0005\u0017AqAa\u0004\u0002\t\u0003\u0011\t\u0002C\u0004\u0003\u0018\u0005!\tA!\u0007\u0007\u000bm\u0012\u0014\u0011\u0001$\t\u000b\u0005SA\u0011A'\t\u000b9Sa\u0011A(\t\u000bmSa\u0011A(\t\u000bqSA\u0011A(\t\u000buSA\u0011\u00010\t\u0011\u0011T\u0001R1A\u0005\n=CQ!\u001a\u0006\u0007\u0002\u0019DQa\u001a\u0006\u0007\u0002\u0019DQ\u0001\u001b\u0006\u0007\u0002%DQ!\u001d\u0006\u0005\u0002IDQA\u001e\u0006\u0005\u0002]DQ\u0001\u001f\u0006\u0005\u0002]DQ!\u001f\u0006\u0007\u0002iDQA \u0006\u0007\u0002iDQa \u0006\u0007\u0002]Da!!\u0001\u000b\t\u00039\bbBA\u0002\u0015\u0019\u0005\u0011Q\u0001\u0005\b\u0003\u001bQa\u0011AA\b\u0011\u001d\t9B\u0003D\u0001\u00033Aq!!\t\u000b\t\u0003\t\u0019\u0003C\u0004\u0002,)!\t!!\f\t\u000f\u0005]\"\u0002\"\u0001\u0002:!9\u0011q\t\u0006\u0005\u0002\u0005%\u0003bBA4\u0015\u0011\u0005\u0011\u0011\u000e\u0005\b\u0003kRA\u0011AA<\u0011\u001d\t)I\u0003C\u0001\u0003SBq!a\"\u000b\r\u0003\tI\t\u0003\u0004\u0002\u001a*!\te\u001e\u0005\b\u00037Sa\u0011AAO\u0011\u001d\t)K\u0003D\u0001\u0003OCq!!,\u000b\t\u0003\ty\u000bC\u0004\u00026*!I!a.\t\u000f\u0005\u001d'\u0002\"\u0003\u0002J\"9\u0011\u0011\u001b\u0006\u0005\u0002\u0005M\u0007bBAl\u0015\u0011\u0005\u0011\u0011\u001c\u0005\b\u0003;TA\u0011CAp\u0011\u001d\tiN\u0003C\t\u0003ODq!!<\u000b\t\u0003\ny/\u0001\u0007BEN$(/Y2u\r&dWM\u0003\u00024i\u0005\u0011\u0011n\u001c\u0006\u0003kY\nqA]3gY\u0016\u001cGOC\u00018\u0003\u0015\u00198-\u00197b\u0007\u0001\u0001\"AO\u0001\u000e\u0003I\u0012A\"\u00112tiJ\f7\r\u001e$jY\u0016\u001c\"!A\u001f\u0011\u0005yzT\"\u0001\u001c\n\u0005\u00013$AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0002s\u00059q-\u001a;GS2,GcA#\u0002rB\u0011!HC\n\u0003\u0015\u001d\u00032\u0001S&F\u001b\u0005I%B\u0001&7\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003\u0019&\u0013\u0001#\u00112tiJ\f7\r^%uKJ\f'\r\\3\u0015\u0003\u0015\u000bAA\\1nKV\t\u0001\u000b\u0005\u0002R1:\u0011!K\u0016\t\u0003'Zj\u0011\u0001\u0016\u0006\u0003+b\na\u0001\u0010:p_Rt\u0014BA,7\u0003\u0019\u0001&/\u001a3fM&\u0011\u0011L\u0017\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005]3\u0014\u0001\u00029bi\"\fQbY1o_:L7-\u00197QCRD\u0017\u0001\u00045bg\u0016CH/\u001a8tS>tGCA0c!\tq\u0004-\u0003\u0002bm\t9!i\\8mK\u0006t\u0007\"B2\u0010\u0001\u0004\u0001\u0016!B8uQ\u0016\u0014\u0018!C3yi\u0016t7/[8o\u0003!\t'm]8mkR,W#A#\u0002\u0013\r|g\u000e^1j]\u0016\u0014\u0018\u0001\u00024jY\u0016,\u0012A\u001b\t\u0003W>l\u0011\u0001\u001c\u0006\u0003g5T\u0011A\\\u0001\u0005U\u00064\u0018-\u0003\u0002qY\n!a)\u001b7f\u0003A)h\u000eZ3sYfLgnZ*pkJ\u001cW-F\u0001t!\rqD/R\u0005\u0003kZ\u0012aa\u00149uS>t\u0017AB3ySN$8/F\u0001`\u0003AI7o\u00117bgN\u001cuN\u001c;bS:,'/\u0001\u0004de\u0016\fG/\u001a\u000b\u0002wB\u0011a\b`\u0005\u0003{Z\u0012A!\u00168ji\u00061A-\u001a7fi\u0016\f1\"[:ESJ,7\r^8ss\u0006I\u0011n\u001d,jeR,\u0018\r\\\u0001\rY\u0006\u001cH/T8eS\u001aLW\rZ\u000b\u0003\u0003\u000f\u00012APA\u0005\u0013\r\tYA\u000e\u0002\u0005\u0019>tw-A\u0003j]B,H/\u0006\u0002\u0002\u0012A\u00191.a\u0005\n\u0007\u0005UANA\u0006J]B,Ho\u0015;sK\u0006l\u0017AB8viB,H/\u0006\u0002\u0002\u001cA\u00191.!\b\n\u0007\u0005}AN\u0001\u0007PkR\u0004X\u000f^*ue\u0016\fW.\u0001\bck\u001a4WM]3e\u001fV$\b/\u001e;\u0016\u0005\u0005\u0015\u0002cA6\u0002(%\u0019\u0011\u0011\u00067\u0003)\t+hMZ3sK\u0012|U\u000f\u001e9viN#(/Z1n\u0003)\u0019\u0018N_3PaRLwN\\\u000b\u0003\u0003_\u0001BA\u0010;\u00022A\u0019a(a\r\n\u0007\u0005UbGA\u0002J]R\fQ\u0001^8V%2+\"!a\u000f\u0011\t\u0005u\u00121I\u0007\u0003\u0003\u007fQ1!!\u0011n\u0003\rqW\r^\u0005\u0005\u0003\u000b\nyDA\u0002V%2\u000b1\u0002^8DQ\u0006\u0014\u0018I\u001d:bsV\u0011\u00111\n\t\u0006}\u00055\u0013\u0011K\u0005\u0004\u0003\u001f2$!B!se\u0006L\bc\u0001 \u0002T%\u0019\u0011Q\u000b\u001c\u0003\t\rC\u0017M\u001d\u0015\u0006C\u0005e\u0013Q\r\t\u0006}\u0005m\u0013qL\u0005\u0004\u0003;2$A\u0002;ie><8\u000fE\u0002l\u0003CJ1!a\u0019m\u0005-Iu*\u0012=dKB$\u0018n\u001c8$\u0005\u0005}\u0013a\u0003;p\u0005f$X-\u0011:sCf,\"!a\u001b\u0011\u000by\ni%!\u001c\u0011\u0007y\ny'C\u0002\u0002rY\u0012AAQ=uK\"*!%!\u0017\u0002f\u0005aAo\u001c\"zi\u0016\u0014UO\u001a4feV\u0011\u0011\u0011\u0010\t\u0005\u0003w\n\t)\u0004\u0002\u0002~)\u0019\u0011qP7\u0002\u00079Lw.\u0003\u0003\u0002\u0004\u0006u$A\u0003\"zi\u0016\u0014UO\u001a4fe\u0006\tRO\\:bM\u0016$vNQ=uK\u0006\u0013(/Y=\u0002\u0011%$XM]1u_J,\"!a#\u0011\u000b\u00055\u00151S#\u000f\u0007y\ny)C\u0002\u0002\u0012Z\nq\u0001]1dW\u0006<W-\u0003\u0003\u0002\u0016\u0006]%\u0001C%uKJ\fGo\u001c:\u000b\u0007\u0005Ee'A\u0004jg\u0016k\u0007\u000f^=\u0002\u00151|wn[;q\u001d\u0006lW\rF\u0003F\u0003?\u000b\t\u000bC\u0003OO\u0001\u0007\u0001\u000b\u0003\u0004\u0002$\u001e\u0002\raX\u0001\nI&\u0014Xm\u0019;pef\f1\u0003\\8pWV\u0004h*Y7f+:\u001c\u0007.Z2lK\u0012$R!RAU\u0003WCQA\u0014\u0015A\u0002ACa!a))\u0001\u0004y\u0016a\u00057p_.,\b\u000fU1uQVs7\r[3dW\u0016$G#B#\u00022\u0006M\u0006\"B.*\u0001\u0004\u0001\u0006BBARS\u0001\u0007q,\u0001\u0004m_>\\W\u000f\u001d\u000b\b\u000b\u0006e\u0016\u0011YAc\u0011\u0019\u0019%\u00061\u0001\u0002<B9a(!0F!~+\u0015bAA`m\tIa)\u001e8di&|gn\r\u0005\u0007\u0003\u0007T\u0003\u0019\u0001)\u0002\u000bA\fG\u000f\u001b\u0019\t\r\u0005\r&\u00061\u0001`\u0003]1\u0017\u000e\\3PeN+(\rZ5sK\u000e$xN]=OC6,G\rF\u0003F\u0003\u0017\fi\rC\u0003OW\u0001\u0007\u0001\u000b\u0003\u0004\u0002P.\u0002\raX\u0001\u0006SN$\u0015N]\u0001\nM&dWMT1nK\u0012$2!RAk\u0011\u0015qE\u00061\u0001Q\u0003E\u0019XO\u00193je\u0016\u001cGo\u001c:z\u001d\u0006lW\r\u001a\u000b\u0004\u000b\u0006m\u0007\"\u0002(.\u0001\u0004\u0001\u0016aC;ogV\u0004\bo\u001c:uK\u0012$\"!!9\u0011\u0007y\n\u0019/C\u0002\u0002fZ\u0012qAT8uQ&tw\r\u0006\u0003\u0002b\u0006%\bBBAv_\u0001\u0007\u0001+A\u0002ng\u001e\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002!\")1l\u0001a\u0001!R\u0019Q)!>\t\rm#\u0001\u0019AA|!\rQ\u0014\u0011`\u0005\u0004\u0003w\u0014$\u0001\u0002)bi\"$2!RA\u0000\u0011\u0019AW\u00011\u0001\u0003\u0002A\u0019!Ha\u0001\n\u0005A\u0014\u0014\u0001D4fi\u0012K'/Z2u_JLHcA#\u0003\n!11L\u0002a\u0001\u0003o$2!\u0012B\u0007\u0011\u0019Aw\u00011\u0001\u0003\u0002\u00051q-\u001a;V%2#2!\u0012B\n\u0011\u001d\u0011)\u0002\u0003a\u0001\u0003w\t1!\u001e:m\u000319W\r\u001e*fg>,(oY3t)\r)%1\u0004\u0005\b\u0005+I\u0001\u0019AA\u001e\u0001"
)
public abstract class AbstractFile extends AbstractIterable {
   private String extension;
   private volatile boolean bitmap$0;

   public static AbstractFile getResources(final URL url) {
      AbstractFile$ var10000 = AbstractFile$.MODULE$;
      ZipArchive$ var1 = ZipArchive$.MODULE$;
      return new ManifestResources(url);
   }

   public static AbstractFile getURL(final URL url) {
      return AbstractFile$.MODULE$.getURL(url);
   }

   public static AbstractFile getDirectory(final File file) {
      return AbstractFile$.MODULE$.getDirectory(file);
   }

   public static AbstractFile getDirectory(final Path path) {
      return AbstractFile$.MODULE$.getDirectory(path);
   }

   public static AbstractFile getFile(final File file) {
      return AbstractFile$.MODULE$.getFile(file);
   }

   public static AbstractFile getFile(final Path path) {
      return AbstractFile$.MODULE$.getFile(path);
   }

   public static AbstractFile getFile(final String path) {
      return AbstractFile$.MODULE$.getFile(path);
   }

   public abstract String name();

   public abstract String path();

   public String canonicalPath() {
      return this.file() == null ? this.path() : this.file().getCanonicalPath();
   }

   public boolean hasExtension(final String other) {
      String var10000 = this.extension();
      String var2 = other.toLowerCase();
      if (var10000 == null) {
         if (var2 == null) {
            return true;
         }
      } else if (var10000.equals(var2)) {
         return true;
      }

      return false;
   }

   private String extension$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.extension = Path$.MODULE$.extension(this.name());
            this.bitmap$0 = true;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.extension;
   }

   private String extension() {
      return !this.bitmap$0 ? this.extension$lzycompute() : this.extension;
   }

   public abstract AbstractFile absolute();

   public abstract AbstractFile container();

   public abstract java.io.File file();

   public Option underlyingSource() {
      return .MODULE$;
   }

   public boolean exists() {
      return this.file() == null || this.file().exists();
   }

   public boolean isClassContainer() {
      if (!this.isDirectory()) {
         if (this.file() != null) {
            String var10000 = this.extension();
            String var1 = "jar";
            if (var10000 != null) {
               if (var10000.equals(var1)) {
                  return true;
               }
            }

            var10000 = this.extension();
            String var2 = "zip";
            if (var10000 != null) {
               if (var10000.equals(var2)) {
                  return true;
               }
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public abstract void create();

   public abstract void delete();

   public abstract boolean isDirectory();

   public boolean isVirtual() {
      return false;
   }

   public abstract long lastModified();

   public abstract InputStream input();

   public abstract OutputStream output();

   public BufferedOutputStream bufferedOutput() {
      return new BufferedOutputStream(this.output());
   }

   public Option sizeOption() {
      return .MODULE$;
   }

   public URL toURL() {
      return this.file() == null ? null : this.file().toURI().toURL();
   }

   public char[] toCharArray() throws IOException {
      return (new String(this.toByteArray())).toCharArray();
   }

   public byte[] toByteArray() throws IOException {
      InputStream in = this.input();
      Option var2 = this.sizeOption();
      if (var2 instanceof Some) {
         int size = BoxesRunTime.unboxToInt(((Some)var2).value());
         int rest = size;

         byte[] arr;
         int res;
         for(arr = new byte[size]; rest > 0; rest -= res) {
            res = in.read(arr, arr.length - rest, rest);
            if (res == -1) {
               throw new IOException("read error");
            }
         }

         in.close();
         return arr;
      } else if (!.MODULE$.equals(var2)) {
         throw new MatchError(var2);
      } else {
         ByteArrayOutputStream out = new ByteArrayOutputStream();

         for(int c = in.read(); c != -1; c = in.read()) {
            out.write(c);
         }

         in.close();
         return out.toByteArray();
      }
   }

   public ByteBuffer toByteBuffer() {
      return ByteBuffer.wrap(this.toByteArray());
   }

   public byte[] unsafeToByteArray() {
      return this.toByteArray();
   }

   public abstract Iterator iterator();

   public boolean isEmpty() {
      return this.iterator().isEmpty();
   }

   public abstract AbstractFile lookupName(final String name, final boolean directory);

   public abstract AbstractFile lookupNameUnchecked(final String name, final boolean directory);

   public AbstractFile lookupPathUnchecked(final String path, final boolean directory) {
      char lookup_separator = java.io.File.separatorChar;
      String lookup_path = scala.collection.StringOps..MODULE$.last$extension(path) == lookup_separator ? scala.collection.StringOps..MODULE$.dropRight$extension(path, 1) : path;
      int lookup_length = lookup_path.length();
      if (lookup_length <= 0 || scala.collection.StringOps..MODULE$.last$extension(lookup_path) == lookup_separator) {
         throw new AssertionError((new StringBuilder(18)).append("assertion failed: ").append(lookup_path).toString());
      } else {
         AbstractFile lookup_file = this;
         int var9 = 0;

         while(true) {
            int lookup_index = lookup_path.indexOf(lookup_separator, var9);
            if (lookup_index >= 0 && var9 >= lookup_index) {
               throw new AssertionError((new StringBuilder(18)).append("assertion failed: ").append(new Tuple4(lookup_path, directory, var9, lookup_index)).toString());
            }

            String lookup_name = lookup_path.substring(var9, lookup_index < 0 ? lookup_length : lookup_index);
            lookup_file = lookup_file.lookupNameUnchecked(lookup_name, lookup_index < 0 ? directory : true);
            if (lookup_file == null || lookup_index < 0) {
               return lookup_file;
            }

            var9 = lookup_index + 1;
         }
      }
   }

   private AbstractFile lookup(final Function3 getFile, final String path0, final boolean directory) {
      char separator = java.io.File.separatorChar;
      String path = scala.collection.StringOps..MODULE$.last$extension(path0) == separator ? scala.collection.StringOps..MODULE$.dropRight$extension(path0, 1) : path0;
      int length = path.length();
      if (length <= 0 || scala.collection.StringOps..MODULE$.last$extension(path) == separator) {
         throw new AssertionError((new StringBuilder(18)).append("assertion failed: ").append(path).toString());
      } else {
         AbstractFile file = this;
         int var10 = 0;

         while(true) {
            int index = path.indexOf(separator, var10);
            if (index >= 0 && var10 >= index) {
               throw new AssertionError((new StringBuilder(18)).append("assertion failed: ").append(new Tuple4(path, directory, var10, index)).toString());
            }

            String name = path.substring(var10, index < 0 ? length : index);
            file = (AbstractFile)getFile.apply(file, name, index < 0 ? directory : true);
            if (file == null || index < 0) {
               return file;
            }

            var10 = index + 1;
         }
      }
   }

   private AbstractFile fileOrSubdirectoryNamed(final String name, final boolean isDir) {
      AbstractFile lookup = this.lookupName(name, isDir);
      if (lookup != null) {
         return lookup;
      } else {
         java.io.File jfile = new java.io.File(this.file(), name);
         if (isDir) {
            jfile.mkdirs();
         } else {
            jfile.createNewFile();
         }

         return new PlainFile(Path$.MODULE$.apply(jfile));
      }
   }

   public AbstractFile fileNamed(final String name) {
      if (!this.isDirectory()) {
         throw new AssertionError((new StringBuilder(18)).append("assertion failed: ").append($anonfun$fileNamed$1(this, name)).toString());
      } else {
         return this.fileOrSubdirectoryNamed(name, false);
      }
   }

   public AbstractFile subdirectoryNamed(final String name) {
      if (!this.isDirectory()) {
         throw new AssertionError((new StringBuilder(18)).append("assertion failed: ").append($anonfun$subdirectoryNamed$1(this, name)).toString());
      } else {
         return this.fileOrSubdirectoryNamed(name, true);
      }
   }

   public Nothing unsupported() {
      return this.unsupported((String)null);
   }

   public Nothing unsupported(final String msg) {
      throw new UnsupportedOperationException(msg);
   }

   public String toString() {
      return this.path();
   }

   // $FF: synthetic method
   public static final AbstractFile $anonfun$lookupPathUnchecked$1(final AbstractFile f, final String p, final boolean dir) {
      return f.lookupNameUnchecked(p, dir);
   }

   // $FF: synthetic method
   public static final String $anonfun$lookup$1(final String path$1) {
      return path$1;
   }

   // $FF: synthetic method
   public static final Tuple4 $anonfun$lookup$2(final String path$1, final boolean directory$1, final IntRef start$1, final int index$1) {
      return new Tuple4(path$1, directory$1, start$1.elem, index$1);
   }

   // $FF: synthetic method
   public static final String $anonfun$fileNamed$1(final AbstractFile $this, final String name$1) {
      return scala.collection.StringOps..MODULE$.format$extension("Tried to find '%s' in '%s' but it is not a directory", scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{name$1, $this.path()}));
   }

   // $FF: synthetic method
   public static final String $anonfun$subdirectoryNamed$1(final AbstractFile $this, final String name$2) {
      return scala.collection.StringOps..MODULE$.format$extension("Tried to find '%s' in '%s' but it is not a directory", scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{name$2, $this.path()}));
   }

   // $FF: synthetic method
   public static final AbstractFile $anonfun$lookupPathUnchecked$1$adapted(final AbstractFile f, final String p, final Object dir) {
      return $anonfun$lookupPathUnchecked$1(f, p, BoxesRunTime.unboxToBoolean(dir));
   }
}
