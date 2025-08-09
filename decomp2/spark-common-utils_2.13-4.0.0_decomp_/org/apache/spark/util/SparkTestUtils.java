package org.apache.spark.util;

import java.io.File;
import java.io.Writer;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import java.nio.file.Files;
import java.util.Arrays;
import javax.tools.DiagnosticListener;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;
import javax.tools.JavaFileObject.Kind;
import scala.Option;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rd\u0001\u0003\f\u0018!\u0003\r\t!G\u0010\t\u000b\u0019\u0002A\u0011\u0001\u0015\t\u000f1\u0002!\u0019!C\u0005[!)\u0001\b\u0001C\u0005s\u0019)q\n\u0001\u0001\u001a!\"A1\t\u0002BC\u0002\u0013\u0005Q\u000b\u0003\u0005W\t\t\u0005\t\u0015!\u0003E\u0011!9FA!b\u0001\n\u0003)\u0006\u0002\u0003-\u0005\u0005\u0003\u0005\u000b\u0011\u0002#\t\u000be#A\u0011\u0001.\t\u000b}#A\u0011\t1\t\u000b\u0019\u0004A\u0011A4\t\r\u0019\u0004A\u0011AA\u0003\u0011%\tI\u0003AI\u0001\n\u0003\tY\u0003C\u0005\u0002B\u0001\t\n\u0011\"\u0001\u0002,!I\u00111\t\u0001\u0012\u0002\u0013\u0005\u0011Q\t\u0005\n\u0003\u0013\u0002\u0011\u0013!C\u0001\u0003\u0017B\u0011\"a\u0014\u0001#\u0003%\t!a\u000b\t\u0013\u0005E\u0003!%A\u0005\u0002\u0005Ms\u0001CA,/!\u0005\u0011$!\u0017\u0007\u000fY9\u0002\u0012A\r\u0002^!1\u0011\f\u0006C\u0001\u0003C\u0012ab\u00159be.$Vm\u001d;Vi&d7O\u0003\u0002\u00193\u0005!Q\u000f^5m\u0015\tQ2$A\u0003ta\u0006\u00148N\u0003\u0002\u001d;\u00051\u0011\r]1dQ\u0016T\u0011AH\u0001\u0004_J<7C\u0001\u0001!!\t\tC%D\u0001#\u0015\u0005\u0019\u0013!B:dC2\f\u0017BA\u0013#\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\u001a\u0001\u0001F\u0001*!\t\t#&\u0003\u0002,E\t!QK\\5u\u0003\u0019\u0019v*\u0016*D\u000bV\ta\u0006\u0005\u00020m5\t\u0001G\u0003\u00022e\u0005q!*\u0019<b\r&dWm\u00142kK\u000e$(BA\u001a5\u0003\u0015!xn\u001c7t\u0015\u0005)\u0014!\u00026bm\u0006D\u0018BA\u001c1\u0005\u0011Y\u0015N\u001c3\u0002\u0013\r\u0014X-\u0019;f+JKEC\u0001\u001eC!\tY\u0004)D\u0001=\u0015\tid(A\u0002oKRT\u0011aP\u0001\u0005U\u00064\u0018-\u0003\u0002By\t\u0019QKU%\t\u000b\r\u001b\u0001\u0019\u0001#\u0002\t9\fW.\u001a\t\u0003\u000b2s!A\u0012&\u0011\u0005\u001d\u0013S\"\u0001%\u000b\u0005%;\u0013A\u0002\u001fs_>$h(\u0003\u0002LE\u00051\u0001K]3eK\u001aL!!\u0014(\u0003\rM#(/\u001b8h\u0015\tY%E\u0001\u000bKCZ\f7k\\;sG\u00164%o\\7TiJLgnZ\n\u0003\tE\u0003\"AU*\u000e\u0003IJ!\u0001\u0016\u001a\u0003)MKW\u000e\u001d7f\u0015\u00064\u0018MR5mK>\u0013'.Z2u+\u0005!\u0015!\u00028b[\u0016\u0004\u0013\u0001B2pI\u0016\fQaY8eK\u0002\na\u0001P5oSRtDcA.^=B\u0011A\fB\u0007\u0002\u0001!)1)\u0003a\u0001\t\")q+\u0003a\u0001\t\u0006qq-\u001a;DQ\u0006\u00148i\u001c8uK:$HC\u0001#b\u0011\u0015\u0011'\u00021\u0001d\u0003QIwM\\8sK\u0016s7m\u001c3j]\u001e,%O]8sgB\u0011\u0011\u0005Z\u0005\u0003K\n\u0012qAQ8pY\u0016\fg.A\nde\u0016\fG/Z\"p[BLG.\u001a3DY\u0006\u001c8\u000fF\u0003i]B\u0014H\u000f\u0005\u0002jY6\t!N\u0003\u0002l}\u0005\u0011\u0011n\\\u0005\u0003[*\u0014AAR5mK\")qn\u0003a\u0001\t\u0006I1\r\\1tg:\u000bW.\u001a\u0005\u0006c.\u0001\r\u0001[\u0001\bI\u0016\u001cH\u000fR5s\u0011\u0015\u00198\u00021\u0001\\\u0003)\u0019x.\u001e:dK\u001aKG.\u001a\u0005\u0006k.\u0001\rA^\u0001\u000eG2\f7o\u001d9bi\",&\u000f\\:\u0011\u0007]dxP\u0004\u0002yu:\u0011q)_\u0005\u0002G%\u00111PI\u0001\ba\u0006\u001c7.Y4f\u0013\tihPA\u0002TKFT!a\u001f\u0012\u0011\u0007m\n\t!C\u0002\u0002\u0004q\u00121!\u0016*M)EA\u0017qAA\u0005\u0003\u0017\ty!a\u0005\u0002\u0016\u0005m\u0011q\u0004\u0005\u0006_2\u0001\r\u0001\u0012\u0005\u0006c2\u0001\r\u0001\u001b\u0005\t\u0003\u001ba\u0001\u0013!a\u0001\t\u0006iAo\\*ue&twMV1mk\u0016D\u0001\"!\u0005\r!\u0003\u0005\r\u0001R\u0001\nE\u0006\u001cXm\u00117bgNDq!\u001e\u0007\u0011\u0002\u0003\u0007a\u000fC\u0005\u0002\u00181\u0001\n\u00111\u0001\u0002\u001a\u0005\t\u0012.\u001c9mK6,g\u000e^:DY\u0006\u001c8/Z:\u0011\u0007]dH\t\u0003\u0005\u0002\u001e1\u0001\n\u00111\u0001E\u00035)\u0007\u0010\u001e:b\u0007>$WMQ8es\"I\u0011\u0011\u0005\u0007\u0011\u0002\u0003\u0007\u00111E\u0001\fa\u0006\u001c7.Y4f\u001d\u0006lW\r\u0005\u0003\"\u0003K!\u0015bAA\u0014E\t1q\n\u001d;j_:\fQd\u0019:fCR,7i\\7qS2,Gm\u00117bgN$C-\u001a4bk2$HeM\u000b\u0003\u0003[Q3\u0001RA\u0018W\t\t\t\u0004\u0005\u0003\u00024\u0005uRBAA\u001b\u0015\u0011\t9$!\u000f\u0002\u0013Ut7\r[3dW\u0016$'bAA\u001eE\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005}\u0012Q\u0007\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!H2sK\u0006$XmQ8na&dW\rZ\"mCN\u001cH\u0005Z3gCVdG\u000f\n\u001b\u0002;\r\u0014X-\u0019;f\u0007>l\u0007/\u001b7fI\u000ec\u0017m]:%I\u00164\u0017-\u001e7uIU*\"!a\u0012+\u0007Y\fy#A\u000fde\u0016\fG/Z\"p[BLG.\u001a3DY\u0006\u001c8\u000f\n3fM\u0006,H\u000e\u001e\u00137+\t\tiE\u000b\u0003\u0002\u001a\u0005=\u0012!H2sK\u0006$XmQ8na&dW\rZ\"mCN\u001cH\u0005Z3gCVdG\u000fJ\u001c\u0002;\r\u0014X-\u0019;f\u0007>l\u0007/\u001b7fI\u000ec\u0017m]:%I\u00164\u0017-\u001e7uIa*\"!!\u0016+\t\u0005\r\u0012qF\u0001\u000f'B\f'o\u001b+fgR,F/\u001b7t!\r\tY\u0006F\u0007\u0002/M!A\u0003IA0!\r\tY\u0006\u0001\u000b\u0003\u00033\u0002"
)
public interface SparkTestUtils {
   void org$apache$spark$util$SparkTestUtils$_setter_$org$apache$spark$util$SparkTestUtils$$SOURCE_$eq(final JavaFileObject.Kind x$1);

   JavaFileObject.Kind org$apache$spark$util$SparkTestUtils$$SOURCE();

   // $FF: synthetic method
   static URI org$apache$spark$util$SparkTestUtils$$createURI$(final SparkTestUtils $this, final String name) {
      return $this.org$apache$spark$util$SparkTestUtils$$createURI(name);
   }

   default URI org$apache$spark$util$SparkTestUtils$$createURI(final String name) {
      String var10000 = name.replace(".", "/");
      return URI.create("string:///" + var10000 + this.org$apache$spark$util$SparkTestUtils$$SOURCE().extension);
   }

   // $FF: synthetic method
   static File createCompiledClass$(final SparkTestUtils $this, final String className, final File destDir, final JavaSourceFromString sourceFile, final Seq classpathUrls) {
      return $this.createCompiledClass(className, destDir, sourceFile, classpathUrls);
   }

   default File createCompiledClass(final String className, final File destDir, final JavaSourceFromString sourceFile, final Seq classpathUrls) {
      JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
      Seq options = (Seq)(classpathUrls.nonEmpty() ? new .colon.colon("-classpath", new .colon.colon(((IterableOnceOps)classpathUrls.map((x$1) -> x$1.getFile())).mkString(File.pathSeparator), scala.collection.immutable.Nil..MODULE$)) : (Seq)scala.package..MODULE$.Seq().empty());
      compiler.getTask((Writer)null, (JavaFileManager)null, (DiagnosticListener)null, scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(options).asJava(), (Iterable)null, Arrays.asList((Object[])(new JavaSourceFromString[]{sourceFile}))).call();
      String fileName = className + ".class";
      File result = new File(fileName);
      scala.Predef..MODULE$.assert(result.exists(), () -> "Compiled file not found: " + result.getAbsolutePath());
      File out = new File(destDir, fileName);
      Files.move(result.toPath(), out.toPath());
      scala.Predef..MODULE$.assert(out.exists(), () -> "Destination file not moved: " + out.getAbsolutePath());
      return out;
   }

   // $FF: synthetic method
   static File createCompiledClass$(final SparkTestUtils $this, final String className, final File destDir, final String toStringValue, final String baseClass, final Seq classpathUrls, final Seq implementsClasses, final String extraCodeBody, final Option packageName) {
      return $this.createCompiledClass(className, destDir, toStringValue, baseClass, classpathUrls, implementsClasses, extraCodeBody, packageName);
   }

   default File createCompiledClass(final String className, final File destDir, final String toStringValue, final String baseClass, final Seq classpathUrls, final Seq implementsClasses, final String extraCodeBody, final Option packageName) {
      String extendsText = (String)scala.Option..MODULE$.apply(baseClass).map((c) -> " extends " + c).getOrElse(() -> "");
      IterableOnceOps var10000 = (IterableOnceOps)implementsClasses.$colon$plus("java.io.Serializable");
      String implementsText = "implements " + var10000.mkString(", ");
      String packageText = (String)packageName.map((p) -> "package " + p + ";\n").getOrElse(() -> "");
      JavaSourceFromString sourceFile = new JavaSourceFromString(className, scala.collection.StringOps..MODULE$.stripMargin$extension(scala.Predef..MODULE$.augmentString("\n         |" + packageText + "\n         |public class " + className + " " + extendsText + " " + implementsText + " {\n         |  @Override public String toString() { return \"" + toStringValue + "\"; }\n         |\n         |  " + extraCodeBody + "\n         |}\n        ")));
      return this.createCompiledClass(className, destDir, sourceFile, classpathUrls);
   }

   // $FF: synthetic method
   static String createCompiledClass$default$3$(final SparkTestUtils $this) {
      return $this.createCompiledClass$default$3();
   }

   default String createCompiledClass$default$3() {
      return "";
   }

   // $FF: synthetic method
   static String createCompiledClass$default$4$(final SparkTestUtils $this) {
      return $this.createCompiledClass$default$4();
   }

   default String createCompiledClass$default$4() {
      return null;
   }

   // $FF: synthetic method
   static Seq createCompiledClass$default$5$(final SparkTestUtils $this) {
      return $this.createCompiledClass$default$5();
   }

   default Seq createCompiledClass$default$5() {
      return (Seq)scala.package..MODULE$.Seq().empty();
   }

   // $FF: synthetic method
   static Seq createCompiledClass$default$6$(final SparkTestUtils $this) {
      return $this.createCompiledClass$default$6();
   }

   default Seq createCompiledClass$default$6() {
      return (Seq)scala.package..MODULE$.Seq().empty();
   }

   // $FF: synthetic method
   static String createCompiledClass$default$7$(final SparkTestUtils $this) {
      return $this.createCompiledClass$default$7();
   }

   default String createCompiledClass$default$7() {
      return "";
   }

   // $FF: synthetic method
   static Option createCompiledClass$default$8$(final SparkTestUtils $this) {
      return $this.createCompiledClass$default$8();
   }

   default Option createCompiledClass$default$8() {
      return scala.None..MODULE$;
   }

   static void $init$(final SparkTestUtils $this) {
      $this.org$apache$spark$util$SparkTestUtils$_setter_$org$apache$spark$util$SparkTestUtils$$SOURCE_$eq(Kind.SOURCE);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class JavaSourceFromString extends SimpleJavaFileObject {
      private final String name;
      private final String code;
      // $FF: synthetic field
      public final SparkTestUtils $outer;

      public String name() {
         return this.name;
      }

      public String code() {
         return this.code;
      }

      public String getCharContent(final boolean ignoreEncodingErrors) {
         return this.code();
      }

      // $FF: synthetic method
      public SparkTestUtils org$apache$spark$util$SparkTestUtils$JavaSourceFromString$$$outer() {
         return this.$outer;
      }

      public JavaSourceFromString(final String name, final String code) {
         this.name = name;
         this.code = code;
         if (SparkTestUtils.this == null) {
            throw null;
         } else {
            this.$outer = SparkTestUtils.this;
            super(SparkTestUtils.this.org$apache$spark$util$SparkTestUtils$$createURI(name), SparkTestUtils.this.org$apache$spark$util$SparkTestUtils$$SOURCE());
         }
      }
   }
}
