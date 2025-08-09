package scala.reflect.io;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.collection.Iterator;
import scala.io.Codec.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mr!\u0002\r\u001a\u0011\u0003\u0001c!\u0002\u0012\u001a\u0011\u0003\u0019\u0003\"\u0002\u0015\u0002\t\u0003I\u0003\"\u0002\u0016\u0002\t\u0013Y\u0003bBA\u0002\u0003\u0011\u0005\u0011Q\u0001\u0005\b\u0003\u001b\tA\u0011AA\b\u0011\u001d\t)\"\u0001C\u0001\u0003/A\u0011\"!\n\u0002#\u0003%\t!a\n\t\u0013\u0005-\u0012!%A\u0005\u0002\u0005\u001d\u0002\"CA\u0017\u0003E\u0005I\u0011AA\u0018\r\u0011\u0011\u0013\u0004\u0001\u0019\t\u0013QR!\u0011!Q\u0001\nUb\u0004\"\u0002\u0015\u000b\t\u0003i\u0004\"B \u000b\t\u0003\u0002\u0005\"B!\u000b\t\u0003\u0002\u0005\"\u0002\"\u000b\t\u0003\u001a\u0005\"\u0002$\u000b\t\u0003\u0002\u0005\"B$\u000b\t\u0003A\u0005\"\u0002)\u000b\t\u0003\t\u0006\"B*\u000b\t\u0003!\u0006\"\u0002,\u000b\t\u0003:\u0006\"\u00021\u000b\t\u0003!\u0006\"B1\u000b\t\u0003\u0011\u0007b\u00025\u000b#\u0003%\t![\u0001\n\t&\u0014Xm\u0019;pefT!AG\u000e\u0002\u0005%|'B\u0001\u000f\u001e\u0003\u001d\u0011XM\u001a7fGRT\u0011AH\u0001\u0006g\u000e\fG.Y\u0002\u0001!\t\t\u0013!D\u0001\u001a\u0005%!\u0015N]3di>\u0014\u0018p\u0005\u0002\u0002IA\u0011QEJ\u0007\u0002;%\u0011q%\b\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?)\u0005\u0001\u0013!\u00048pe6\fG.\u001b>f!\u0006$\b\u000e\u0006\u0002-iB\u0019Q%L\u0018\n\u00059j\"\u0001B*p[\u0016\u0004\"!\t\u0006\u0014\u0005)\t\u0004CA\u00113\u0013\t\u0019\u0014D\u0001\u0003QCRD\u0017!\u00026gS2,\u0007C\u0001\u001c;\u001b\u00059$B\u0001\u000e9\u0015\u0005I\u0014\u0001\u00026bm\u0006L!aO\u001c\u0003\t\u0019KG.Z\u0005\u0003iI\"\"a\f \t\u000bQb\u0001\u0019A\u001b\u0002\u0015Q|\u0017IY:pYV$X-F\u00010\u0003-!x\u000eR5sK\u000e$xN]=\u0002\rQ|g)\u001b7f+\u0005!\u0005CA\u0011F\u0013\tY\u0014$A\u0005o_Jl\u0017\r\\5{K\u0006!A.[:u+\u0005I\u0005c\u0001&Nc9\u0011QeS\u0005\u0003\u0019v\tq\u0001]1dW\u0006<W-\u0003\u0002O\u001f\nA\u0011\n^3sCR|'O\u0003\u0002M;\u0005!A-\u001b:t+\u0005\u0011\u0006c\u0001&N_\u0005)a-\u001b7fgV\tQ\u000bE\u0002K\u001b\u0012\u000b!b^1mW\u001aKG\u000e^3s)\tI\u0005\fC\u0003Z)\u0001\u0007!,\u0001\u0003d_:$\u0007\u0003B\u0013\\cuK!\u0001X\u000f\u0003\u0013\u0019+hn\u0019;j_:\f\u0004CA\u0013_\u0013\tyVDA\u0004C_>dW-\u00198\u0002\u0013\u0011,W\r\u001d$jY\u0016\u001c\u0018\u0001\u00033fKBd\u0015n\u001d;\u0015\u0005%\u001b\u0007b\u00023\u0017!\u0003\u0005\r!Z\u0001\u0006I\u0016\u0004H\u000f\u001b\t\u0003K\u0019L!aZ\u000f\u0003\u0007%sG/\u0001\neK\u0016\u0004H*[:uI\u0011,g-Y;mi\u0012\nT#\u00016+\u0005\u0015\\7&\u00017\u0011\u00055\u0014X\"\u00018\u000b\u0005=\u0004\u0018!C;oG\",7m[3e\u0015\t\tX$\u0001\u0006b]:|G/\u0019;j_:L!a\u001d8\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\rC\u0003v\u0007\u0001\u0007a/A\u0001t!\t9hP\u0004\u0002yyB\u0011\u00110H\u0007\u0002u*\u00111pH\u0001\u0007yI|w\u000e\u001e \n\u0005ul\u0012A\u0002)sK\u0012,g-C\u0002\u0000\u0003\u0003\u0011aa\u0015;sS:<'BA?\u001e\u0003\u001d\u0019UO\u001d:f]R,\"!a\u0002\u0011\t\u0015\nIaL\u0005\u0004\u0003\u0017i\"AB(qi&|g.A\u0003baBd\u0017\u0010F\u00020\u0003#Aa!a\u0005\u0006\u0001\u0004\t\u0014\u0001\u00029bi\"\f\u0001\"\\1lKR+W\u000e\u001d\u000b\b_\u0005e\u0011QDA\u0011\u0011!\tYB\u0002I\u0001\u0002\u00041\u0018A\u00029sK\u001aL\u0007\u0010\u0003\u0005\u0002 \u0019\u0001\n\u00111\u0001w\u0003\u0019\u0019XO\u001a4jq\"A\u00111\u0005\u0004\u0011\u0002\u0003\u0007Q'A\u0002eSJ\f!#\\1lKR+W\u000e\u001d\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u0011\u0011\u0006\u0016\u0003m.\f!#\\1lKR+W\u000e\u001d\u0013eK\u001a\fW\u000f\u001c;%e\u0005\u0011R.Y6f)\u0016l\u0007\u000f\n3fM\u0006,H\u000e\u001e\u00134+\t\t\tD\u000b\u00026W\u0002"
)
public class Directory extends Path {
   public static java.io.File makeTemp$default$3() {
      Directory$ var10000 = Directory$.MODULE$;
      return null;
   }

   public static String makeTemp$default$2() {
      Directory$ var10000 = Directory$.MODULE$;
      return null;
   }

   public static String makeTemp$default$1() {
      Directory$ var10000 = Directory$.MODULE$;
      return Path$.MODULE$.randomPrefix();
   }

   public static Directory makeTemp(final String prefix, final String suffix, final java.io.File dir) {
      return Directory$.MODULE$.makeTemp(prefix, suffix, dir);
   }

   public static Directory apply(final Path path) {
      Directory$ var10000 = Directory$.MODULE$;
      return path.toDirectory();
   }

   public static Option Current() {
      return Directory$.MODULE$.Current();
   }

   public Directory toAbsolute() {
      return this.isAbsolute() ? this : super.toAbsolute().toDirectory();
   }

   public Directory toDirectory() {
      return this;
   }

   public File toFile() {
      return new File(super.jfile(), .MODULE$.fallbackSystemCodec());
   }

   public Directory normalize() {
      return super.normalize().toDirectory();
   }

   public Iterator list() {
      java.io.File[] var1 = super.jfile().listFiles();
      if (var1 == null) {
         if (scala.package..MODULE$.Iterator() == null) {
            throw null;
         } else {
            return scala.collection.Iterator..scala$collection$Iterator$$_empty;
         }
      } else {
         return scala.collection.ArrayOps..MODULE$.iterator$extension(var1).map((jfile) -> Path$.MODULE$.apply(jfile));
      }
   }

   public Iterator dirs() {
      return this.list().collect(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Object applyOrElse(final Path x1, final Function1 default) {
            return x1 instanceof Directory ? (Directory)x1 : default.apply(x1);
         }

         public final boolean isDefinedAt(final Path x1) {
            return x1 instanceof Directory;
         }
      });
   }

   public Iterator files() {
      return this.list().collect(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Object applyOrElse(final Path x1, final Function1 default) {
            return x1 instanceof File ? (File)x1 : default.apply(x1);
         }

         public final boolean isDefinedAt(final Path x1) {
            return x1 instanceof File;
         }
      });
   }

   public Iterator walkFilter(final Function1 cond) {
      return this.list().filter(cond).flatMap((x$1) -> x$1.walkFilter(cond));
   }

   public Iterator deepFiles() {
      return Path$.MODULE$.onlyFiles(this.deepList(this.deepList$default$1()));
   }

   public Iterator deepList(final int depth) {
      if (depth < 0) {
         Iterator var4 = this.list();
         Function0 $plus$plus_xs = () -> this.dirs().flatMap((x$2) -> x$2.deepList(depth));
         if (var4 == null) {
            throw null;
         } else {
            return var4.concat($plus$plus_xs);
         }
      } else if (depth == 0) {
         if (scala.package..MODULE$.Iterator() == null) {
            throw null;
         } else {
            return scala.collection.Iterator..scala$collection$Iterator$$_empty;
         }
      } else {
         Iterator var10000 = this.list();
         Function0 $plus$plus_xs = () -> this.dirs().flatMap((x$3) -> x$3.deepList(depth - 1));
         if (var10000 == null) {
            throw null;
         } else {
            return var10000.concat($plus$plus_xs);
         }
      }
   }

   public int deepList$default$1() {
      return -1;
   }

   public Directory(final java.io.File jfile) {
      super(jfile);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
