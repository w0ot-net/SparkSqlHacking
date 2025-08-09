package scala.reflect.io;

import java.io.RandomAccessFile;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import java.net.URL;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArraySeq;
import scala.io.Codec.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t\rw!B&M\u0011\u0003\u0019f!B+M\u0011\u00031\u0006\"B.\u0002\t\u0003a\u0006\"B/\u0002\t\u0003q\u0006\"B/\u0002\t\u0003Y\u0007\"B=\u0002\t\u0003Q\b\"\u0002?\u0002\t\u0007i\bb\u0002BG\u0003\u0011\r!q\u0012\u0005\b\u0005'\u000bA\u0011\u0001BK\u0011\u001d\u0011\u0019*\u0001C\u0001\u0005;CqAa)\u0002\t\u0003\u0011)\u000bC\u0004\u0003,\u0006!\tA!,\t\u000f\t=\u0016\u0001\"\u0001\u00032\"9!qV\u0001\u0005\u0002\tU\u0006\u0002\u0003B]\u0003\u0011\u0005A*a$\t\u0011\tm\u0016\u0001\"\u0001M\u0005{3A!\u0016'\u0001\u007f\"I1\r\u0005BC\u0002\u0013\u0005\u0011\u0011\u0001\u0005\n\u0003\u0007\u0001\"\u0011!Q\u0001\n\u0011Dqa\u0017\t\u0005\u00021\u000b)\u0001C\u0005\u0002\nA\u0011\r\u0011\"\u0001\u0002\f!A\u00111\u0003\t!\u0002\u0013\ti\u0001C\u0005\u0002\u0016A\u0011\r\u0011\"\u0001\u0002\u0018!A\u00111\u0005\t!\u0002\u0013\tI\u0002C\u0004\u0002&A!\t!a\n\t\u000f\u00055\u0002\u0003\"\u0001\u00020!9\u0011q\u0007\t\u0005\u0002\u0005e\u0002bBA\u001e!\u0011\u0005\u0011\u0011\b\u0005\b\u0003{\u0001B\u0011AA \u0011\u001d\ti\u0005\u0005C\u0001\u0003\u001fBq!a\u0016\u0011\t\u0003\tI\u0006C\u0004\u0002`A!\t!!\u0019\t\u000f\u0005}\u0003\u0003\"\u0001\u0002h!9\u0011q\f\t\u0005\u0002\u0005-\u0004bBA8!\u0011\u0005\u0011\u0011\u000f\u0005\b\u0003\u0017\u0003B\u0011AAG\u0011\u0019i\u0007\u0003\"\u0001\u0002\u0010\"9\u0011\u0011\u0013\t\u0005\u0002\u0005=\u0005bBAJ!\u0011\u0005\u0011\u0011\b\u0005\b\u0003+\u0003B\u0011AAL\u0011\u001d\ti\n\u0005C\u0001\u0003?Cq!a)\u0011\t\u0003\t)\u000bC\u0004\u0002.B!\t!a\f\t\u000f\u0005=\u0006\u0003\"\u0001\u00022\"1\u0011\u0010\u0005C\u0001\u0003\u001fCq!!.\u0011\t\u0003\t9\fC\u0004\u0002HB!\t!a$\t\u000f\u0005%\u0007\u0003\"\u0001\u0002L\"9\u0011q\u001a\t\u0005\u0002\u0005E\u0007bBAk!\u0011\u0005\u0011q\u001b\u0005\b\u0003{\u0004B\u0011AA\u0000\u0011\u001d\u0011i\u0001\u0005C\u0001\u0005\u001fAqA!\u0005\u0011\t\u0003\u0011y\u0001C\u0004\u0003\u0014A!\tAa\u0004\t\u000f\tU\u0001\u0003\"\u0001\u0003\u0010!9!q\u0003\t\u0005\u0002\t=\u0001b\u0002B\r!\u0011\u0005!q\u0002\u0005\b\u00057\u0001B\u0011\u0001B\b\u0011\u001d\u0011i\u0002\u0005C\u0001\u0005?AqAa\n\u0011\t\u0003\u0011y\u0002C\u0004\u0003*A!\tAa\u000b\t\u000f\t=\u0002\u0003\"\u0001\u00032!9!Q\u0007\t\u0005\u0002\t]\u0002b\u0002B\u001e!\u0011\u0005!Q\b\u0005\n\u0005\u000f\u0002\u0012\u0013!C\u0001\u0005\u0013B\u0011Ba\u0018\u0011#\u0003%\tA!\u0013\t\u000f\t\u0005\u0004\u0003\"\u0001\u0003d!I!q\r\t\u0012\u0002\u0013\u0005!\u0011\n\u0005\b\u0005S\u0002B\u0011\u0001B6\u0011\u001d\u0011i\u0007\u0005C\u0001\u0005WBqA!\u001c\u0011\t\u0013\u0011y\u0007C\u0004\u0003tA!\tAa\u001b\t\u000f\tU\u0004\u0003\"\u0011\u0003x!9!\u0011\u0010\t\u0005B\tm\u0004b\u0002B@!\u0011\u0005#\u0011Q\u0001\u0005!\u0006$\bN\u0003\u0002N\u001d\u0006\u0011\u0011n\u001c\u0006\u0003\u001fB\u000bqA]3gY\u0016\u001cGOC\u0001R\u0003\u0015\u00198-\u00197b\u0007\u0001\u0001\"\u0001V\u0001\u000e\u00031\u0013A\u0001U1uQN\u0011\u0011a\u0016\t\u00031fk\u0011\u0001U\u0005\u00035B\u0013a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\bF\u0001T\u0003MI7/\u0012=uK:\u001c\u0018n\u001c8KCJ|%OW5q)\ty&\r\u0005\u0002YA&\u0011\u0011\r\u0015\u0002\b\u0005>|G.Z1o\u0011\u0015\u00197\u00011\u0001e\u0003\u0015Qg-\u001b7f!\t)\u0017.D\u0001g\u0015\tiuMC\u0001i\u0003\u0011Q\u0017M^1\n\u0005)4'\u0001\u0002$jY\u0016$\"a\u00187\t\u000b5$\u0001\u0019\u00018\u0002\t9\fW.\u001a\t\u0003_Zt!\u0001\u001d;\u0011\u0005E\u0004V\"\u0001:\u000b\u0005M\u0014\u0016A\u0002\u001fs_>$h(\u0003\u0002v!\u00061\u0001K]3eK\u001aL!a\u001e=\u0003\rM#(/\u001b8h\u0015\t)\b+A\u0005fqR,gn]5p]R\u0011an\u001f\u0005\u0006[\u0016\u0001\rA\\\u0001\fgR\u0014\u0018N\\43a\u0006$\b\u000eF\u0002\u007f\u0005\u0013\u0003\"\u0001\u0016\t\u0014\u0005A9V#\u00013\u0002\r)4\u0017\u000e\\3!)\rq\u0018q\u0001\u0005\u0006GN\u0001\r\u0001Z\u0001\ng\u0016\u0004\u0018M]1u_J,\"!!\u0004\u0011\u0007a\u000by!C\u0002\u0002\u0012A\u0013Aa\u00115be\u0006Q1/\u001a9be\u0006$xN\u001d\u0011\u0002\u0019M,\u0007/\u0019:bi>\u00148\u000b\u001e:\u0016\u0005\u0005e\u0001\u0003BA\u000e\u0003Ci!!!\b\u000b\u0007\u0005}q-\u0001\u0003mC:<\u0017bA<\u0002\u001e\u0005i1/\u001a9be\u0006$xN]*ue\u0002\na\u0001^8GS2,WCAA\u0015!\r!\u00161F\u0005\u0003U2\u000b1\u0002^8ESJ,7\r^8ssV\u0011\u0011\u0011\u0007\t\u0004)\u0006M\u0012bAA\u001b\u0019\nIA)\u001b:fGR|'/_\u0001\u000bi>\f%m]8mkR,W#\u0001@\u0002\u0017Q|7)\u00198p]&\u001c\u0017\r\\\u0001\u0006i>,&+S\u000b\u0003\u0003\u0003\u0002B!a\u0011\u0002J5\u0011\u0011Q\t\u0006\u0004\u0003\u000f:\u0017a\u00018fi&!\u00111JA#\u0005\r)&+S\u0001\u0006i>,&\u000bT\u000b\u0003\u0003#\u0002B!a\u0011\u0002T%!\u0011QKA#\u0005\r)&\u000bT\u0001\u0013i>\f%m]8mkR,w+\u001b;i%>|G\u000fF\u0002\u007f\u00037Ba!!\u0018\u001f\u0001\u0004q\u0018\u0001\u0002:p_R\fA\u0001\n3jmR\u0019a0a\u0019\t\r\u0005\u0015t\u00041\u0001\u007f\u0003\u0015\u0019\u0007.\u001b7e)\u0011\t\t$!\u001b\t\u000f\u0005\u0015\u0004\u00051\u0001\u00022Q!\u0011\u0011FA7\u0011\u001d\t)'\ta\u0001\u0003S\t!b^1mW\u001aKG\u000e^3s)\u0011\t\u0019(!!\u0011\u000b\u0005U\u00141\u0010@\u000f\u0007a\u000b9(C\u0002\u0002zA\u000bq\u0001]1dW\u0006<W-\u0003\u0003\u0002~\u0005}$\u0001C%uKJ\fGo\u001c:\u000b\u0007\u0005e\u0004\u000bC\u0004\u0002\u0004\n\u0002\r!!\"\u0002\t\r|g\u000e\u001a\t\u00061\u0006\u001depX\u0005\u0004\u0003\u0013\u0003&!\u0003$v]\u000e$\u0018n\u001c82\u0003\u00119\u0018\r\\6\u0016\u0005\u0005MT#\u00018\u0002\tA\fG\u000f[\u0001\n]>\u0014X.\u00197ju\u0016\fqA]3t_24X\rF\u0002\u007f\u00033Ca!a'(\u0001\u0004q\u0018!B8uQ\u0016\u0014\u0018A\u0003:fY\u0006$\u0018N^5{KR\u0019a0!)\t\r\u0005m\u0005\u00061\u0001\u007f\u0003!\u0019XmZ7f]R\u001cXCAAT!\u0015\t)(!+o\u0013\u0011\tY+a \u0003\t1K7\u000f^\u0001\u0007a\u0006\u0014XM\u001c;\u0002\u000fA\f'/\u001a8ugV\u0011\u00111\u0017\t\u0007\u0003k\nI+!\r\u0002\u0019!\f7/\u0012=uK:\u001c\u0018n\u001c8\u0015\u000b}\u000bI,!0\t\r\u0005mV\u00061\u0001o\u0003\r)\u0007\u0010\u001e\u0005\b\u0003\u007fk\u0003\u0019AAa\u0003\u0011)\u0007\u0010^:\u0011\ta\u000b\u0019M\\\u0005\u0004\u0003\u000b\u0004&A\u0003\u001fsKB,\u0017\r^3e}\u0005q1\u000f\u001e:ja\u0016CH/\u001a8tS>t\u0017\u0001D1eI\u0016CH/\u001a8tS>tGc\u0001@\u0002N\"1\u00111X\u0018A\u00029\fqb\u00195b]\u001e,W\t\u001f;f]NLwN\u001c\u000b\u0004}\u0006M\u0007BBA^a\u0001\u0007a.\u0001\u0004jM\u001aKG.Z\u000b\u0005\u00033\f)\u000f\u0006\u0003\u0002\\\u0006]\b#\u0002-\u0002^\u0006\u0005\u0018bAAp!\n1q\n\u001d;j_:\u0004B!a9\u0002f2\u0001AaBAtc\t\u0007\u0011\u0011\u001e\u0002\u0002)F!\u00111^Ay!\rA\u0016Q^\u0005\u0004\u0003_\u0004&a\u0002(pi\"Lgn\u001a\t\u00041\u0006M\u0018bAA{!\n\u0019\u0011I\\=\t\u000f\u0005e\u0018\u00071\u0001\u0002|\u0006\ta\rE\u0004Y\u0003\u000f\u000bI#!9\u0002\u0017%4G)\u001b:fGR|'/_\u000b\u0005\u0005\u0003\u00119\u0001\u0006\u0003\u0003\u0004\t%\u0001#\u0002-\u0002^\n\u0015\u0001\u0003BAr\u0005\u000f!q!a:3\u0005\u0004\tI\u000fC\u0004\u0002zJ\u0002\rAa\u0003\u0011\u000fa\u000b9)!\r\u0003\u0006\u000591-\u00198SK\u0006$W#A0\u0002\u0011\r\fgn\u0016:ji\u0016\fa!\u001a=jgR\u001c\u0018AB5t\r&dW-A\u0006jg\u0012K'/Z2u_JL\u0018AC5t\u0003\n\u001cx\u000e\\;uK\u00069\u0011n]#naRL\u0018\u0001\u00047bgRlu\u000eZ5gS\u0016$WC\u0001B\u0011!\rA&1E\u0005\u0004\u0005K\u0001&\u0001\u0002'p]\u001e\fa\u0001\\3oORD\u0017\u0001C3oIN<\u0016\u000e\u001e5\u0015\u0007}\u0013i\u0003\u0003\u0004\u0002\u001cr\u0002\rA`\u0001\u0007SN\u001c\u0016-\\3\u0015\u0007}\u0013\u0019\u0004\u0003\u0004\u0002\u001cv\u0002\rA`\u0001\nSN4%/Z:iKJ$2a\u0018B\u001d\u0011\u0019\tYJ\u0010a\u0001}\u0006y1M]3bi\u0016$\u0015N]3di>\u0014\u0018\u0010\u0006\u0004\u00022\t}\"1\t\u0005\t\u0005\u0003z\u0004\u0013!a\u0001?\u0006)am\u001c:dK\"A!QI \u0011\u0002\u0003\u0007q,\u0001\u0007gC&d\u0017JZ#ySN$8/A\rde\u0016\fG/\u001a#je\u0016\u001cGo\u001c:zI\u0011,g-Y;mi\u0012\nTC\u0001B&U\ry&QJ\u0016\u0003\u0005\u001f\u0002BA!\u0015\u0003\\5\u0011!1\u000b\u0006\u0005\u0005+\u00129&A\u0005v]\u000eDWmY6fI*\u0019!\u0011\f)\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0003^\tM#!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006I2M]3bi\u0016$\u0015N]3di>\u0014\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133\u0003)\u0019'/Z1uK\u001aKG.\u001a\u000b\u0005\u0003S\u0011)\u0007\u0003\u0005\u0003F\t\u0003\n\u00111\u0001`\u0003Q\u0019'/Z1uK\u001aKG.\u001a\u0013eK\u001a\fW\u000f\u001c;%c\u00051A-\u001a7fi\u0016$\u0012aX\u0001\u0012I\u0016dW\r^3SK\u000e,(o]5wK2LHcA0\u0003r!1\u0011\u0011 $A\u0002\u0011\f\u0001\u0002\u001e:v]\u000e\fG/Z\u0001\ti>\u001cFO]5oOR\ta.\u0001\u0004fcV\fGn\u001d\u000b\u0004?\nu\u0004bBAN\u0013\u0002\u0007\u0011\u0011_\u0001\tQ\u0006\u001c\bnQ8eKR\u0011!1\u0011\t\u00041\n\u0015\u0015b\u0001BD!\n\u0019\u0011J\u001c;\t\r\t-e\u00011\u0001o\u0003\u0005\u0019\u0018A\u00036gS2,'\u0007]1uQR\u0019aP!%\t\u000b\r<\u0001\u0019\u00013\u0002\u0011=tG.\u001f#jeN$BAa&\u0003\u001aB1\u0011QOA>\u0003cAqAa'\t\u0001\u0004\t\u0019(\u0001\u0002ygR!\u00111\u0017BP\u0011\u001d\u0011Y*\u0003a\u0001\u0005C\u0003R!!\u001e\u0002*z\f\u0011b\u001c8ms\u001aKG.Z:\u0015\t\t\u001d&\u0011\u0016\t\u0007\u0003k\nY(!\u000b\t\u000f\tm%\u00021\u0001\u0002t\u0005)!o\\8ugV\u0011!\u0011U\u0001\u0006CB\u0004H.\u001f\u000b\u0004}\nM\u0006BBAI\u0019\u0001\u0007a\u000eF\u0002\u007f\u0005oCQaY\u0007A\u0002\u0011\fAB]1oI>l\u0007K]3gSb\fAAZ1jYR!\u00111\u001eB`\u0011\u0019\u0011\tm\u0004a\u0001]\u0006\u0019Qn]4"
)
public class Path {
   private final java.io.File jfile;
   private final char separator;
   private final String separatorStr;

   public static Path apply(final java.io.File jfile) {
      return Path$.MODULE$.apply(jfile);
   }

   public static Path apply(final String path) {
      return Path$.MODULE$.apply(path);
   }

   public static List roots() {
      return Path$.MODULE$.roots();
   }

   public static Iterator onlyFiles(final Iterator xs) {
      return Path$.MODULE$.onlyFiles(xs);
   }

   public static List onlyDirs(final List xs) {
      return Path$.MODULE$.onlyDirs(xs);
   }

   public static Iterator onlyDirs(final Iterator xs) {
      return Path$.MODULE$.onlyDirs(xs);
   }

   public static Path jfile2path(final java.io.File jfile) {
      return Path$.MODULE$.apply(jfile);
   }

   public static Path string2path(final String s) {
      return Path$.MODULE$.apply(s);
   }

   public static boolean isExtensionJarOrZip(final String name) {
      return Path$.MODULE$.isExtensionJarOrZip(name);
   }

   public static boolean isExtensionJarOrZip(final java.io.File jfile) {
      return Path$.MODULE$.isExtensionJarOrZip(jfile);
   }

   public java.io.File jfile() {
      return this.jfile;
   }

   public char separator() {
      return this.separator;
   }

   public String separatorStr() {
      return this.separatorStr;
   }

   public File toFile() {
      return new File(this.jfile(), .MODULE$.fallbackSystemCodec());
   }

   public Directory toDirectory() {
      return new Directory(this.jfile());
   }

   public Path toAbsolute() {
      return this.isAbsolute() ? this : Path$.MODULE$.apply(this.jfile().getAbsolutePath());
   }

   public Path toCanonical() {
      return Path$.MODULE$.apply(this.jfile().getCanonicalPath());
   }

   public URI toURI() {
      return this.jfile().toURI();
   }

   public URL toURL() {
      return this.toURI().toURL();
   }

   public Path toAbsoluteWithRoot(final Path root) {
      return this.isAbsolute() ? this : root.toAbsolute().$div(this);
   }

   public Path $div(final Path child) {
      return this.isEmpty() ? child : new Path(new java.io.File(this.jfile(), child.path()));
   }

   public Directory $div(final Directory child) {
      return this.$div((Path)child).toDirectory();
   }

   public File $div(final File child) {
      return this.$div((Path)child).toFile();
   }

   public Iterator walkFilter(final Function1 cond) {
      if (this.isFile()) {
         return this.toFile().walkFilter(cond);
      } else if (this.isDirectory()) {
         return this.toDirectory().walkFilter(cond);
      } else if (scala.package..MODULE$.Iterator() == null) {
         throw null;
      } else {
         return scala.collection.Iterator..scala$collection$Iterator$$_empty;
      }
   }

   public Iterator walk() {
      return this.walkFilter((x$7) -> BoxesRunTime.boxToBoolean($anonfun$walk$1(x$7)));
   }

   public String name() {
      return this.jfile().getName();
   }

   public String path() {
      return this.jfile().getPath();
   }

   public Path normalize() {
      return Path$.MODULE$.apply(this.jfile().getAbsolutePath());
   }

   public Path resolve(final Path other) {
      return !other.isAbsolute() && !this.isEmpty() ? this.$div(other) : other;
   }

   public Path relativize(final Path other) {
      if (this.isAbsolute() != other.isAbsolute()) {
         throw new AssertionError((new StringBuilder(18)).append("assertion failed: ").append($anonfun$relativize$1(this, other)).toString());
      } else {
         return Path$.MODULE$.apply(this.createRelativePath$1(this.segments(), other.segments()));
      }
   }

   public List segments() {
      ArraySeq.ofRef var10000 = scala.Predef..MODULE$.wrapRefArray(scala.collection.StringOps..MODULE$.split$extension(this.path(), this.separator()));
      if (var10000 == null) {
         throw null;
      } else {
         List var48 = IterableOnceOps.toList$(var10000);
         if (var48 == null) {
            throw null;
         } else {
            List filterNot_this = var48;
            boolean filterNot_filterCommon_isFlipped = true;
            List filterNot_filterCommon_noneIn$1_l = filterNot_this;

            while(true) {
               if (filterNot_filterCommon_noneIn$1_l.isEmpty()) {
                  var49 = scala.collection.immutable.Nil..MODULE$;
                  break;
               }

               Object filterNot_filterCommon_noneIn$1_h = filterNot_filterCommon_noneIn$1_l.head();
               List filterNot_filterCommon_noneIn$1_t = (List)filterNot_filterCommon_noneIn$1_l.tail();
               if ($anonfun$segments$1((String)filterNot_filterCommon_noneIn$1_h) != filterNot_filterCommon_isFlipped) {
                  List filterNot_filterCommon_noneIn$1_allIn$1_remaining = filterNot_filterCommon_noneIn$1_t;

                  while(true) {
                     if (filterNot_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                        var49 = filterNot_filterCommon_noneIn$1_l;
                        break;
                     }

                     Object filterNot_filterCommon_noneIn$1_allIn$1_x = filterNot_filterCommon_noneIn$1_allIn$1_remaining.head();
                     if ($anonfun$segments$1((String)filterNot_filterCommon_noneIn$1_allIn$1_x) == filterNot_filterCommon_isFlipped) {
                        scala.collection.immutable..colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = new scala.collection.immutable..colon.colon(filterNot_filterCommon_noneIn$1_l.head(), scala.collection.immutable.Nil..MODULE$);
                        List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filterNot_filterCommon_noneIn$1_l.tail();

                        scala.collection.immutable..colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast;
                        for(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead; filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess != filterNot_filterCommon_noneIn$1_allIn$1_remaining; filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                           scala.collection.immutable..colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.head(), scala.collection.immutable.Nil..MODULE$);
                           filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                           filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                        }

                        List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                        List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next;

                        while(!filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                           Object filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.head();
                           if ($anonfun$segments$1((String)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head) != filterNot_filterCommon_isFlipped) {
                              filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                           } else {
                              while(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy != filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next) {
                                 scala.collection.immutable..colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), scala.collection.immutable.Nil..MODULE$);
                                 filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                                 filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                                 filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.tail();
                              }

                              filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                              filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                           }
                        }

                        if (!filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.isEmpty()) {
                           filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy);
                        }

                        var49 = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
                        Object var24 = null;
                        Object var27 = null;
                        Object var30 = null;
                        Object var33 = null;
                        Object var36 = null;
                        Object var39 = null;
                        Object var42 = null;
                        Object var45 = null;
                        break;
                     }

                     filterNot_filterCommon_noneIn$1_allIn$1_remaining = (List)filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                  }

                  Object var20 = null;
                  Object var22 = null;
                  Object var25 = null;
                  Object var28 = null;
                  Object var31 = null;
                  Object var34 = null;
                  Object var37 = null;
                  Object var40 = null;
                  Object var43 = null;
                  Object var46 = null;
                  break;
               }

               filterNot_filterCommon_noneIn$1_l = filterNot_filterCommon_noneIn$1_t;
            }

            Object var17 = null;
            Object var18 = null;
            Object var19 = null;
            Object var21 = null;
            Object var23 = null;
            Object var26 = null;
            Object var29 = null;
            Object var32 = null;
            Object var35 = null;
            Object var38 = null;
            Object var41 = null;
            Object var44 = null;
            Object var47 = null;
            List filterNot_filterCommon_result = (List)var49;
            Statics.releaseFence();
            return filterNot_filterCommon_result;
         }
      }
   }

   public Directory parent() {
      label50: {
         String var1 = this.path();
         switch (var1 == null ? 0 : var1.hashCode()) {
            case 0:
               if ("".equals(var1)) {
                  break label50;
               }
               break;
            case 46:
               if (".".equals(var1)) {
                  break label50;
               }
         }

         if (this.segments().nonEmpty()) {
            Object var4 = this.segments().last();
            String var2 = "..";
            if (var4 != null) {
               if (var4.equals(var2)) {
                  return Path$.MODULE$.apply(this.path()).$div(Path$.MODULE$.apply("..")).toDirectory();
               }
            }
         }

         String var3 = this.jfile().getParent();
         switch (var3 == null ? 0 : var3.hashCode()) {
            case 0:
               if (var3 == null) {
                  if (this.isAbsolute()) {
                     return this.toDirectory();
                  }

                  Directory$ var5 = Directory$.MODULE$;
                  return Path$.MODULE$.apply(".").toDirectory();
               }
            default:
               Directory$ var6 = Directory$.MODULE$;
               return Path$.MODULE$.apply(var3).toDirectory();
         }
      }

      Directory$ var10000 = Directory$.MODULE$;
      return Path$.MODULE$.apply("..").toDirectory();
   }

   public List parents() {
      Directory p = this.parent();
      if (p.isSame(this)) {
         return scala.collection.immutable.Nil..MODULE$;
      } else {
         List var10000 = p.parents();
         if (var10000 == null) {
            throw null;
         } else {
            List $colon$colon_this = var10000;
            return new scala.collection.immutable..colon.colon(p, $colon$colon_this);
         }
      }
   }

   public String extension() {
      int i;
      for(i = this.name().length() - 1; i >= 0 && this.name().charAt(i) != '.'; --i) {
      }

      return i < 0 ? "" : this.name().substring(i + 1);
   }

   public boolean hasExtension(final String ext, final Seq exts) {
      String lower = this.extension().toLowerCase();
      String var10000 = ext.toLowerCase();
      if (var10000 == null) {
         if (lower == null) {
            return true;
         }
      } else if (var10000.equals(lower)) {
         return true;
      }

      if (!exts.exists((x$9) -> BoxesRunTime.boxToBoolean($anonfun$hasExtension$1(lower, x$9)))) {
         return false;
      } else {
         return true;
      }
   }

   public String stripExtension() {
      return scala.collection.StringOps..MODULE$.stripSuffix$extension(this.name(), (new StringBuilder(1)).append(".").append(this.extension()).toString());
   }

   public Path addExtension(final String ext) {
      return Path$.MODULE$.apply((new StringBuilder(1)).append(this.path()).append(".").append(ext).toString());
   }

   public Path changeExtension(final String ext) {
      String var10000 = this.extension();
      String var2 = "";
      if (var10000 != null) {
         if (var10000.equals(var2)) {
            return this.addExtension(ext);
         }
      }

      return Path$.MODULE$.apply((new StringBuilder(0)).append(scala.collection.StringOps..MODULE$.stripSuffix$extension(this.path(), this.extension())).append(ext).toString());
   }

   public Option ifFile(final Function1 f) {
      return (Option)(this.isFile() ? new Some(f.apply(this.toFile())) : scala.None..MODULE$);
   }

   public Option ifDirectory(final Function1 f) {
      return (Option)(this.isDirectory() ? new Some(f.apply(this.toDirectory())) : scala.None..MODULE$);
   }

   public boolean canRead() {
      return this.jfile().canRead();
   }

   public boolean canWrite() {
      return this.jfile().canWrite();
   }

   public boolean exists() {
      try {
         return this.jfile().exists();
      } catch (SecurityException var1) {
         return false;
      }
   }

   public boolean isFile() {
      try {
         return this.jfile().isFile();
      } catch (SecurityException var1) {
         return false;
      }
   }

   public boolean isDirectory() {
      try {
         return this.jfile().isDirectory();
      } catch (SecurityException var2) {
         String var10000 = this.jfile().getPath();
         String var1 = ".";
         if (var10000 != null) {
            if (var10000.equals(var1)) {
               return true;
            }
         }

         return false;
      }
   }

   public boolean isAbsolute() {
      return this.jfile().isAbsolute();
   }

   public boolean isEmpty() {
      return this.path().length() == 0;
   }

   public long lastModified() {
      return this.jfile().lastModified();
   }

   public long length() {
      return this.jfile().length();
   }

   public boolean endsWith(final Path other) {
      return this.segments().endsWith(other.segments());
   }

   public boolean isSame(final Path other) {
      Path var10000 = this.toCanonical();
      Path var2 = other.toCanonical();
      if (var10000 == null) {
         if (var2 == null) {
            return true;
         }
      } else if (var10000.equals(var2)) {
         return true;
      }

      return false;
   }

   public boolean isFresher(final Path other) {
      return this.lastModified() > other.lastModified();
   }

   public Directory createDirectory(final boolean force, final boolean failIfExists) {
      if (!(force ? this.jfile().mkdirs() : this.jfile().mkdir()) && failIfExists && this.exists()) {
         Path$ var10000 = Path$.MODULE$;
         String fail_msg = (new StringBuilder(28)).append("Directory '").append(this.name()).append("' already exists.").toString();
         throw new FileOperationException(fail_msg);
      } else {
         return this.isDirectory() ? this.toDirectory() : new Directory(this.jfile());
      }
   }

   public boolean createDirectory$default$1() {
      return true;
   }

   public boolean createDirectory$default$2() {
      return false;
   }

   public File createFile(final boolean failIfExists) {
      if (!this.jfile().createNewFile() && failIfExists && this.exists()) {
         Path$ var10000 = Path$.MODULE$;
         String fail_msg = (new StringBuilder(23)).append("File '").append(this.name()).append("' already exists.").toString();
         throw new FileOperationException(fail_msg);
      } else {
         return this.isFile() ? this.toFile() : new File(this.jfile(), .MODULE$.fallbackSystemCodec());
      }
   }

   public boolean createFile$default$1() {
      return false;
   }

   public boolean delete() {
      return this.jfile().delete();
   }

   public boolean deleteRecursively() {
      return this.deleteRecursively(this.jfile());
   }

   private boolean deleteRecursively(final java.io.File f) {
      if (f.isDirectory()) {
         java.io.File[] var2 = f.listFiles();
         if (var2 != null) {
            for(java.io.File var5 : var2) {
               $anonfun$deleteRecursively$1(this, var5);
            }
         }
      }

      return f.delete();
   }

   public boolean truncate() {
      if (this.isFile()) {
         RandomAccessFile raf = new RandomAccessFile(this.jfile(), "rw");
         raf.setLength(0L);
         raf.close();
         if (this.length() == 0L) {
            return true;
         }
      }

      return false;
   }

   public String toString() {
      return this.path();
   }

   public boolean equals(final Object other) {
      if (!(other instanceof Path)) {
         return false;
      } else {
         Path var2 = (Path)other;
         String var10000 = this.path();
         String var3 = var2.path();
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

   public int hashCode() {
      return this.path().hashCode();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$walk$1(final Path x$7) {
      return true;
   }

   // $FF: synthetic method
   public static final String $anonfun$relativize$1(final Path $this, final Path other$1) {
      return (new StringBuilder(26)).append("Paths not of same type: ").append($this).append(", ").append(other$1).toString();
   }

   private final String createRelativePath$1(final List baseSegs, final List otherSegs) {
      while(true) {
         List bs;
         List os;
         label25: {
            if (baseSegs instanceof scala.collection.immutable..colon.colon) {
               scala.collection.immutable..colon.colon var3 = (scala.collection.immutable..colon.colon)baseSegs;
               String b = (String)var3.head();
               bs = var3.next$access$1();
               if (otherSegs instanceof scala.collection.immutable..colon.colon) {
                  scala.collection.immutable..colon.colon var6 = (scala.collection.immutable..colon.colon)otherSegs;
                  String o = (String)var6.head();
                  os = var6.next$access$1();
                  if (b == null) {
                     if (o == null) {
                        break label25;
                     }
                  } else if (b.equals(o)) {
                     break label25;
                  }
               }
            }

            StringBuilder var10000 = (new StringBuilder(0)).append(scala.collection.StringOps..MODULE$.$times$extension((new StringBuilder(2)).append("..").append(this.separator()).toString(), baseSegs.length()));
            String mkString_sep = this.separatorStr();
            if (otherSegs == null) {
               throw null;
            }

            String mkString_end = "";
            String mkString_start = "";
            String var10001 = IterableOnceOps.mkString$(otherSegs, mkString_start, mkString_sep, mkString_end);
            Object var13 = null;
            Object var14 = null;
            Object var12 = null;
            return var10000.append(var10001).toString();
         }

         otherSegs = os;
         baseSegs = bs;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$segments$1(final String x$8) {
      return x$8.length() == 0;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$hasExtension$1(final String lower$1, final String x$9) {
      String var10000 = x$9.toLowerCase();
      if (var10000 == null) {
         if (lower$1 == null) {
            return true;
         }
      } else if (var10000.equals(lower$1)) {
         return true;
      }

      return false;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$deleteRecursively$1(final Path $this, final java.io.File f) {
      return $this.deleteRecursively(f);
   }

   public Path(final java.io.File jfile) {
      this.jfile = jfile;
      this.separator = java.io.File.separatorChar;
      this.separatorStr = java.io.File.separator;
   }

   // $FF: synthetic method
   public static final Object $anonfun$segments$1$adapted(final String x$8) {
      return BoxesRunTime.boxToBoolean($anonfun$segments$1(x$8));
   }

   // $FF: synthetic method
   public static final Object $anonfun$deleteRecursively$1$adapted(final Path $this, final java.io.File f) {
      return BoxesRunTime.boxToBoolean($anonfun$deleteRecursively$1($this, f));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
