package scala.reflect.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.io.BufferedSource;
import scala.io.Codec;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=x!B\u0014)\u0011\u0003yc!B\u0019)\u0011\u0003\u0011\u0004\"B\u001c\u0002\t\u0003A\u0004\"B\u001d\u0002\t\u0003Q\u0004\"B\"\u0002\t\u0003Q\u0004\"\u0002#\u0002\t\u0003)\u0005bBAi\u0003\u0011\u0005\u00111\u001b\u0005\n\u0003C\f\u0011\u0013!C\u0001\u0003GD\u0011\"a:\u0002#\u0003%\t!a9\t\u0013\u0005%\u0018!%A\u0005\u0002\u0005-h\u0001B\u0019)\u0001!C\u0011b\u0015\u0006\u0003\u0002\u0003\u0006I\u0001\u0016-\t\u0011eS!\u0011!Q\u0001\fiCQa\u000e\u0006\u0005\u0002}Cqa\u0019\u0006C\u0002\u0013\u0005C\r\u0003\u0004f\u0015\u0001\u0006IA\u0017\u0005\u0006M*!\te\u001a\u0005\u0006i*!\t%\u001e\u0005\u0006m*!\te\u001e\u0005\u0006w*!\t%\u001e\u0005\u0006y*!\t%\u001e\u0005\u0006{*!\tE \u0005\b\u0003\u000bQA\u0011IA\u0004\u0011\u001d\t9C\u0003C\u0001\u0003SAq!!\r\u000b\t\u0003\t\u0019\u0004C\u0005\u0002@)\t\n\u0011\"\u0001\u0002B!9\u0011q\u000b\u0006\u0005\u0002\u0005e\u0003\"CA2\u0015E\u0005I\u0011AA!\u0011\u001d\t)G\u0003C\u0001\u0003OBq!!\u001e\u000b\t\u0003\t9\bC\u0004\u0002v)!\t!a \t\u000f\u0005U$\u0002\"\u0001\u0002\u0004\"9\u0011\u0011\u0012\u0006\u0005\u0002\u0005-\u0005bBAJ\u0015\u0011\u0005\u0011Q\u0013\u0005\b\u0003OSA\u0011AAU\u0011\u001d\tiK\u0003C\u0001\u0003_Cq!a-\u000b\t\u0003\t)\fC\u0004\u0002>*!\t!a0\t\u0013\u0005%'\"%A\u0005\u0002\u0005\u0005\u0013\u0001\u0002$jY\u0016T!!\u000b\u0016\u0002\u0005%|'BA\u0016-\u0003\u001d\u0011XM\u001a7fGRT\u0011!L\u0001\u0006g\u000e\fG.Y\u0002\u0001!\t\u0001\u0014!D\u0001)\u0005\u00111\u0015\u000e\\3\u0014\u0005\u0005\u0019\u0004C\u0001\u001b6\u001b\u0005a\u0013B\u0001\u001c-\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\u0012aL\u0001\u000ea\u0006$\bnU3qCJ\fGo\u001c:\u0016\u0003m\u0002\"\u0001P!\u000e\u0003uR!AP \u0002\t1\fgn\u001a\u0006\u0002\u0001\u0006!!.\u0019<b\u0013\t\u0011UH\u0001\u0004TiJLgnZ\u0001\ng\u0016\u0004\u0018M]1u_J\fQ!\u00199qYf$2ARAg)\r9\u00151\u001a\t\u0003a)\u00192AC%M!\t\u0001$*\u0003\u0002LQ\t!\u0001+\u0019;i!\ti\u0005K\u0004\u00021\u001d&\u0011q\nK\u0001\u000b'R\u0014X-Y7bE2,\u0017BA)S\u0005\u0015\u0019\u0005.\u0019:t\u0015\ty\u0005&A\u0003kM&dW\r\u0005\u0002V/6\taK\u0003\u0002*\u007f%\u0011\u0011GV\u0005\u0003'*\u000b\u0001cY8ogR\u0014Xo\u0019;pe\u000e{G-Z2\u0011\u0005mkV\"\u0001/\u000b\u0005%b\u0013B\u00010]\u0005\u0015\u0019u\u000eZ3d)\t\u0001'\r\u0006\u0002HC\")\u0011,\u0004a\u00025\")1+\u0004a\u0001)\u0006i1M]3bi&|gnQ8eK\u000e,\u0012AW\u0001\u000fGJ,\u0017\r^5p]\u000e{G-Z2!\u00031\tG\rZ#yi\u0016t7/[8o)\t9\u0005\u000eC\u0003j!\u0001\u0007!.A\u0002fqR\u0004\"a\u001b:\u000f\u00051\u0004\bCA7-\u001b\u0005q'BA8/\u0003\u0019a$o\\8u}%\u0011\u0011\u000fL\u0001\u0007!J,G-\u001a4\n\u0005\t\u001b(BA9-\u0003)!x.\u00112t_2,H/Z\u000b\u0002\u000f\u0006YAo\u001c#je\u0016\u001cGo\u001c:z+\u0005A\bC\u0001\u0019z\u0013\tQ\bFA\u0005ESJ,7\r^8ss\u00061Ao\u001c$jY\u0016\f\u0011B\\8s[\u0006d\u0017N_3\u0002\r1,gn\u001a;i+\u0005y\bc\u0001\u001b\u0002\u0002%\u0019\u00111\u0001\u0017\u0003\t1{gnZ\u0001\u000bo\u0006d7NR5mi\u0016\u0014H\u0003BA\u0005\u0003/\u0001R!a\u0003\u0002\u0012%s1\u0001NA\u0007\u0013\r\ty\u0001L\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\t\u0019\"!\u0006\u0003\u0011%#XM]1u_JT1!a\u0004-\u0011\u001d\tIB\u0006a\u0001\u00037\tAaY8oIB1A'!\bJ\u0003CI1!a\b-\u0005%1UO\\2uS>t\u0017\u0007E\u00025\u0003GI1!!\n-\u0005\u001d\u0011un\u001c7fC:\f1\"\u001b8qkR\u001cFO]3b[R\u0011\u00111\u0006\t\u0004+\u00065\u0012bAA\u0018-\nya)\u001b7f\u0013:\u0004X\u000f^*ue\u0016\fW.\u0001\u0007pkR\u0004X\u000f^*ue\u0016\fW\u000e\u0006\u0003\u00026\u0005m\u0002cA+\u00028%\u0019\u0011\u0011\b,\u0003!\u0019KG.Z(viB,Ho\u0015;sK\u0006l\u0007\"CA\u001f1A\u0005\t\u0019AA\u0011\u0003\u0019\t\u0007\u000f]3oI\u00061r.\u001e;qkR\u001cFO]3b[\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0002D)\"\u0011\u0011EA#W\t\t9\u0005\u0005\u0003\u0002J\u0005MSBAA&\u0015\u0011\ti%a\u0014\u0002\u0013Ut7\r[3dW\u0016$'bAA)Y\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005U\u00131\n\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017A\u00042vM\u001a,'/\u001a3PkR\u0004X\u000f\u001e\u000b\u0005\u00037\n\t\u0007E\u0002V\u0003;J1!a\u0018W\u0005Q\u0011UO\u001a4fe\u0016$w*\u001e;qkR\u001cFO]3b[\"I\u0011Q\b\u000e\u0011\u0002\u0003\u0007\u0011\u0011E\u0001\u0019EV4g-\u001a:fI>+H\u000f];uI\u0011,g-Y;mi\u0012\n\u0014AB<sSR,'\u000f\u0006\u0004\u0002j\u0005=\u0014\u0011\u000f\t\u0004+\u0006-\u0014bAA7-\n\u0011r*\u001e;qkR\u001cFO]3b[^\u0013\u0018\u000e^3s\u0011\u001d\ti\u0004\ba\u0001\u0003CAa!a\u001d\u001d\u0001\u0004Q\u0016!B2pI\u0016\u001c\u0017A\u00042vM\u001a,'/\u001a3Xe&$XM\u001d\u000b\u0003\u0003s\u00022!VA>\u0013\r\tiH\u0016\u0002\u000f\u0005V4g-\u001a:fI^\u0013\u0018\u000e^3s)\u0011\tI(!!\t\u000f\u0005ub\u00041\u0001\u0002\"Q1\u0011\u0011PAC\u0003\u000fCq!!\u0010 \u0001\u0004\t\t\u0003\u0003\u0004\u0002t}\u0001\rAW\u0001\faJLg\u000e^,sSR,'\u000f\u0006\u0002\u0002\u000eB\u0019Q+a$\n\u0007\u0005EeKA\u0006Qe&tGo\u0016:ji\u0016\u0014\u0018\u0001C<sSR,\u0017\t\u001c7\u0015\t\u0005]\u0015Q\u0014\t\u0004i\u0005e\u0015bAANY\t!QK\\5u\u0011\u001d\ty*\ta\u0001\u0003C\u000bqa\u001d;sS:<7\u000f\u0005\u00035\u0003GS\u0017bAASY\tQAH]3qK\u0006$X\r\u001a \u0002\u0013\u0005\u0004\b/\u001a8e\u00032dG\u0003BAL\u0003WCq!a(#\u0001\u0004\t\t+\u0001\u0006qe&tG\u000f\u001c8BY2$B!a&\u00022\"9\u0011qT\u0012A\u0002\u0005\u0005\u0016!C:bM\u0016\u001cF.\u001e:q)\t\t9\f\u0005\u00035\u0003sS\u0017bAA^Y\t1q\n\u001d;j_:\fQb]3u\u000bb,7-\u001e;bE2,GCBA\u0011\u0003\u0003\f)\rC\u0004\u0002D\u0016\u0002\r!!\t\u0002\u0015\u0015DXmY;uC\ndW\rC\u0005\u0002H\u0016\u0002\n\u00111\u0001\u0002\"\u0005Iqn\u001e8fe>sG._\u0001\u0018g\u0016$X\t_3dkR\f'\r\\3%I\u00164\u0017-\u001e7uIIBa!a\u001d\u0006\u0001\bQ\u0006BBAh\u000b\u0001\u0007\u0011*\u0001\u0003qCRD\u0017\u0001C7bW\u0016$V-\u001c9\u0015\u000f\u001d\u000b).!7\u0002^\"A\u0011q\u001b\u0004\u0011\u0002\u0003\u0007!.\u0001\u0004qe\u00164\u0017\u000e\u001f\u0005\t\u000374\u0001\u0013!a\u0001U\u000611/\u001e4gSbD\u0001\"a8\u0007!\u0003\u0005\r\u0001V\u0001\u0004I&\u0014\u0018AE7bW\u0016$V-\u001c9%I\u00164\u0017-\u001e7uIE*\"!!:+\u0007)\f)%\u0001\nnC.,G+Z7qI\u0011,g-Y;mi\u0012\u0012\u0014AE7bW\u0016$V-\u001c9%I\u00164\u0017-\u001e7uIM*\"!!<+\u0007Q\u000b)\u0005"
)
public class File extends Path implements Streamable.Chars {
   private final Codec creationCodec;

   public static java.io.File makeTemp$default$3() {
      File$ var10000 = File$.MODULE$;
      return null;
   }

   public static String makeTemp$default$2() {
      File$ var10000 = File$.MODULE$;
      return null;
   }

   public static String makeTemp$default$1() {
      File$ var10000 = File$.MODULE$;
      return Path$.MODULE$.randomPrefix();
   }

   public static File makeTemp(final String prefix, final String suffix, final java.io.File dir) {
      return File$.MODULE$.makeTemp(prefix, suffix, dir);
   }

   public static File apply(final Path path, final Codec codec) {
      return File$.MODULE$.apply(path, codec);
   }

   public static String pathSeparator() {
      File$ var10000 = File$.MODULE$;
      return java.io.File.pathSeparator;
   }

   public BufferedSource chars(final Codec codec) {
      return Streamable.Chars.chars$(this, codec);
   }

   public Iterator lines() {
      return Streamable.Chars.lines$(this);
   }

   public Iterator lines(final Codec codec) {
      return Streamable.Chars.lines$(this, codec);
   }

   public InputStreamReader reader(final Codec codec) {
      return Streamable.Chars.reader$(this, codec);
   }

   public BufferedReader bufferedReader() {
      return Streamable.Chars.bufferedReader$(this);
   }

   public BufferedReader bufferedReader(final Codec codec) {
      return Streamable.Chars.bufferedReader$(this, codec);
   }

   public Object applyReader(final Function1 f) {
      return Streamable.Chars.applyReader$(this, f);
   }

   public String slurp() {
      return Streamable.Chars.slurp$(this);
   }

   public String slurp(final Codec codec) {
      return Streamable.Chars.slurp$(this, codec);
   }

   public BufferedInputStream bufferedInput() {
      return Streamable.Bytes.bufferedInput$(this);
   }

   public Iterator bytes() {
      return Streamable.Bytes.bytes$(this);
   }

   public Iterator bytesAsInts() {
      return Streamable.Bytes.bytesAsInts$(this);
   }

   public byte[] toByteArray() {
      return Streamable.Bytes.toByteArray$(this);
   }

   public Codec creationCodec() {
      return this.creationCodec;
   }

   public File addExtension(final String ext) {
      return super.addExtension(ext).toFile();
   }

   public File toAbsolute() {
      return this.isAbsolute() ? this : super.toAbsolute().toFile();
   }

   public Directory toDirectory() {
      return new Directory(super.jfile());
   }

   public File toFile() {
      return this;
   }

   public File normalize() {
      return super.normalize().toFile();
   }

   public long length() {
      return super.length();
   }

   public Iterator walkFilter(final Function1 cond) {
      if (BoxesRunTime.unboxToBoolean(cond.apply(this))) {
         if (.MODULE$.Iterator() == null) {
            throw null;
         } else {
            return new Iterator..anon.20(this);
         }
      } else if (.MODULE$.Iterator() == null) {
         throw null;
      } else {
         return scala.collection.Iterator..scala$collection$Iterator$$_empty;
      }
   }

   public FileInputStream inputStream() {
      return new FileInputStream(super.jfile());
   }

   public FileOutputStream outputStream(final boolean append) {
      return new FileOutputStream(super.jfile(), append);
   }

   public boolean outputStream$default$1() {
      return false;
   }

   public BufferedOutputStream bufferedOutput(final boolean append) {
      return new BufferedOutputStream(this.outputStream(append));
   }

   public boolean bufferedOutput$default$1() {
      return false;
   }

   public OutputStreamWriter writer(final boolean append, final Codec codec) {
      return new OutputStreamWriter(this.outputStream(append), codec.charSet());
   }

   public BufferedWriter bufferedWriter() {
      return this.bufferedWriter(false);
   }

   public BufferedWriter bufferedWriter(final boolean append) {
      return this.bufferedWriter(append, this.creationCodec());
   }

   public BufferedWriter bufferedWriter(final boolean append, final Codec codec) {
      return new BufferedWriter(this.writer(append, codec));
   }

   public PrintWriter printWriter() {
      return new PrintWriter(this.bufferedWriter(), true);
   }

   public void writeAll(final Seq strings) {
      BufferedWriter out = this.bufferedWriter();

      try {
         strings.foreach((x$1) -> {
            $anonfun$writeAll$1(out, x$1);
            return BoxedUnit.UNIT;
         });
      } finally {
         out.close();
      }

   }

   public void appendAll(final Seq strings) {
      BufferedWriter out = this.bufferedWriter(true);

      try {
         strings.foreach((x$2) -> {
            $anonfun$appendAll$1(out, x$2);
            return BoxedUnit.UNIT;
         });
      } finally {
         out.close();
      }

   }

   public void printlnAll(final Seq strings) {
      PrintWriter out = this.printWriter();

      try {
         strings.foreach((x$3) -> {
            $anonfun$printlnAll$1(out, x$3);
            return BoxedUnit.UNIT;
         });
      } finally {
         out.close();
      }

   }

   public Option safeSlurp() {
      try {
         return new Some(this.slurp());
      } catch (IOException var1) {
         return scala.None..MODULE$;
      }
   }

   public boolean setExecutable(final boolean executable, final boolean ownerOnly) {
      try {
         return super.jfile().setExecutable(executable, ownerOnly);
      } catch (SecurityException var3) {
         return false;
      }
   }

   public boolean setExecutable$default$2() {
      return true;
   }

   // $FF: synthetic method
   public static final void $anonfun$writeAll$1(final BufferedWriter out$1, final String x$1) {
      out$1.write(x$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$appendAll$1(final BufferedWriter out$2, final String x$2) {
      out$2.write(x$2);
   }

   // $FF: synthetic method
   public static final void $anonfun$printlnAll$1(final PrintWriter out$3, final String x$3) {
      out$3.println(x$3);
   }

   public File(final java.io.File jfile, final Codec constructorCodec) {
      super(jfile);
      this.creationCodec = constructorCodec;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
