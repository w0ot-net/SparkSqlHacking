package scala.reflect.internal;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Member;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M3\u0001BB\u0004\u0011\u0002\u0007\u0005a\u0002\u0015\u0005\u0006'\u0001!\t\u0001\u0006\u0005\u00061\u0001!\t!\u0007\u0005\u00061\u0001!\ta\u000f\u0005\u00061\u0001!\t\u0001\u0012\u0005\u0006\u0019\u0002!\t!\u0014\u0002\u000e!JLg/\u0019;f/&$\b.\u001b8\u000b\u0005!I\u0011\u0001C5oi\u0016\u0014h.\u00197\u000b\u0005)Y\u0011a\u0002:fM2,7\r\u001e\u0006\u0002\u0019\u0005)1oY1mC\u000e\u00011C\u0001\u0001\u0010!\t\u0001\u0012#D\u0001\f\u0013\t\u00112B\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003U\u0001\"\u0001\u0005\f\n\u0005]Y!\u0001B+oSR\f\u0001\u0004\u001d:pa\u0006<\u0017\r^3QC\u000e\\\u0017mZ3C_VtG-\u0019:z)\r)\"\u0004\r\u0005\u00067\t\u0001\r\u0001H\u0001\u0002GB\u0012Qd\n\t\u0004=\r*S\"A\u0010\u000b\u0005\u0001\n\u0013\u0001\u00027b]\u001eT\u0011AI\u0001\u0005U\u00064\u0018-\u0003\u0002%?\t)1\t\\1tgB\u0011ae\n\u0007\u0001\t%A#$!A\u0001\u0002\u000b\u0005\u0011FA\u0002`IE\n\"AK\u0017\u0011\u0005AY\u0013B\u0001\u0017\f\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\u0005\u0018\n\u0005=Z!aA!os\")\u0011G\u0001a\u0001e\u0005!1/_7t!\r\u00012'N\u0005\u0003i-\u0011!\u0002\u0010:fa\u0016\fG/\u001a3?!\t1t'D\u0001\u0001\u0013\tA\u0014H\u0001\u0004Ts6\u0014w\u000e\\\u0005\u0003u\u001d\u0011qaU=nE>d7\u000fF\u0002\u0016y\rCQ!P\u0002A\u0002y\n\u0011!\u001c\t\u0003\u007f\u0005k\u0011\u0001\u0011\u0006\u0003\u0015}I!A\u0011!\u0003\r5+WNY3s\u0011\u0015\t4\u00011\u00013)\r)Ri\u0013\u0005\u0006\r\u0012\u0001\raR\u0001\u0007U\u001ad\u0017mZ:\u0011\u0005!KU\"A\u0004\n\u0005);!\u0001\u0004&bm\u0006\f5m\u0019$mC\u001e\u001c\b\"B\u0019\u0005\u0001\u0004\u0011\u0014\u0001G:fiB\u000b7m[1hK\u0006\u001b7-Z:t\u0005>,h\u000eZ1ssR\u0011QG\u0014\u0005\u0006\u001f\u0016\u0001\r!N\u0001\u0004gfl\u0007C\u0001%R\u0013\t\u0011vAA\u0006Ts6\u0014w\u000e\u001c+bE2,\u0007"
)
public interface PrivateWithin {
   // $FF: synthetic method
   static void propagatePackageBoundary$(final PrivateWithin $this, final Class c, final Seq syms) {
      $this.propagatePackageBoundary(c, syms);
   }

   default void propagatePackageBoundary(final Class c, final Seq syms) {
      this.propagatePackageBoundary(JavaAccFlags$.MODULE$.apply(c), syms);
   }

   // $FF: synthetic method
   static void propagatePackageBoundary$(final PrivateWithin $this, final Member m, final Seq syms) {
      $this.propagatePackageBoundary(m, syms);
   }

   default void propagatePackageBoundary(final Member m, final Seq syms) {
      this.propagatePackageBoundary(JavaAccFlags$.MODULE$.apply(m), syms);
   }

   // $FF: synthetic method
   static void propagatePackageBoundary$(final PrivateWithin $this, final int jflags, final Seq syms) {
      $this.propagatePackageBoundary(jflags, syms);
   }

   default void propagatePackageBoundary(final int jflags, final Seq syms) {
      if (JavaAccFlags$.MODULE$.hasPackageAccessBoundary$extension(jflags)) {
         syms.foreach((sym) -> this.setPackageAccessBoundary(sym));
      }
   }

   // $FF: synthetic method
   static Symbols.Symbol setPackageAccessBoundary$(final PrivateWithin $this, final Symbols.Symbol sym) {
      return $this.setPackageAccessBoundary(sym);
   }

   default Symbols.Symbol setPackageAccessBoundary(final Symbols.Symbol sym) {
      Symbols.Symbol topLevel = sym.enclosingTopLevelClass();
      return topLevel == ((Symbols)this).NoSymbol() ? sym : sym.setPrivateWithin(topLevel.owner());
   }

   static void $init$(final PrivateWithin $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
