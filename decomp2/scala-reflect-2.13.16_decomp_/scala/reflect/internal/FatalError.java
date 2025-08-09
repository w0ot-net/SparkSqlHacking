package scala.reflect.internal;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime;
import scala.runtime.Statics;
import scala.util.hashing.MurmurHash3.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005eb\u0001B\u000b\u0017\u0001vA\u0001B\r\u0001\u0003\u0016\u0004%\ta\r\u0005\ty\u0001\u0011\t\u0012)A\u0005i!)Q\b\u0001C\u0001}!9!\tAA\u0001\n\u0003\u0019\u0005bB#\u0001#\u0003%\tA\u0012\u0005\b#\u0002\t\t\u0011\"\u0011S\u0011\u001dQ\u0006!!A\u0005\u0002mCqa\u0018\u0001\u0002\u0002\u0013\u0005\u0001\rC\u0004g\u0001\u0005\u0005I\u0011I4\t\u000f9\u0004\u0011\u0011!C\u0001_\"9A\u000fAA\u0001\n\u0003*\bbB<\u0001\u0003\u0003%\t\u0005\u001f\u0005\bs\u0002\t\t\u0011\"\u0011{\u000f\u001dah#!A\t\u0002u4q!\u0006\f\u0002\u0002#\u0005a\u0010\u0003\u0004>\u001f\u0011\u0005\u0011Q\u0003\u0005\n\u0003/y\u0011\u0011!C#\u00033A\u0011\"a\u0007\u0010\u0003\u0003%\t)!\b\t\u0013\u0005\u0005r\"!A\u0005\u0002\u0006\r\u0002\"CA\u0018\u001f\u0005\u0005I\u0011BA\u0019\u0005)1\u0015\r^1m\u000bJ\u0014xN\u001d\u0006\u0003/a\t\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u00033i\tqA]3gY\u0016\u001cGOC\u0001\u001c\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019B\u0001\u0001\u0010'SA\u0011qd\t\b\u0003A\u0005j\u0011AG\u0005\u0003Ei\tq\u0001]1dW\u0006<W-\u0003\u0002%K\tIQ\t_2faRLwN\u001c\u0006\u0003Ei\u0001\"\u0001I\u0014\n\u0005!R\"a\u0002)s_\u0012,8\r\u001e\t\u0003UAr!aK\u0011\u000f\u00051zS\"A\u0017\u000b\u00059b\u0012A\u0002\u001fs_>$h(C\u0001\u001c\u0013\t\tTE\u0001\u0007TKJL\u0017\r\\5{C\ndW-A\u0002ng\u001e,\u0012\u0001\u000e\t\u0003ker!AN\u001c\u0011\u00051R\u0012B\u0001\u001d\u001b\u0003\u0019\u0001&/\u001a3fM&\u0011!h\u000f\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005aR\u0012\u0001B7tO\u0002\na\u0001P5oSRtDCA B!\t\u0001\u0005!D\u0001\u0017\u0011\u0015\u00114\u00011\u00015\u0003\u0011\u0019w\u000e]=\u0015\u0005}\"\u0005b\u0002\u001a\u0005!\u0003\u0005\r\u0001N\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u00059%F\u0001\u001bIW\u0005I\u0005C\u0001&P\u001b\u0005Y%B\u0001'N\u0003%)hn\u00195fG.,GM\u0003\u0002O5\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005A[%!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012a\u0015\t\u0003)fk\u0011!\u0016\u0006\u0003-^\u000bA\u0001\\1oO*\t\u0001,\u0001\u0003kCZ\f\u0017B\u0001\u001eV\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005a\u0006C\u0001\u0011^\u0013\tq&DA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002bIB\u0011\u0001EY\u0005\u0003Gj\u00111!\u00118z\u0011\u001d)\u0007\"!AA\u0002q\u000b1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#\u00015\u0011\u0007%d\u0017-D\u0001k\u0015\tY'$\u0001\u0006d_2dWm\u0019;j_:L!!\u001c6\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0003aN\u0004\"\u0001I9\n\u0005IT\"a\u0002\"p_2,\u0017M\u001c\u0005\bK*\t\t\u00111\u0001b\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0005M3\bbB3\f\u0003\u0003\u0005\r\u0001X\u0001\tQ\u0006\u001c\bnQ8eKR\tA,\u0001\u0004fcV\fGn\u001d\u000b\u0003anDq!Z\u0007\u0002\u0002\u0003\u0007\u0011-\u0001\u0006GCR\fG.\u0012:s_J\u0004\"\u0001Q\b\u0014\t=y\u00181\u0002\t\u0007\u0003\u0003\t9\u0001N \u000e\u0005\u0005\r!bAA\u00035\u00059!/\u001e8uS6,\u0017\u0002BA\u0005\u0003\u0007\u0011\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c82!\u0011\ti!a\u0005\u000e\u0005\u0005=!bAA\t/\u0006\u0011\u0011n\\\u0005\u0004c\u0005=A#A?\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012aU\u0001\u0006CB\u0004H.\u001f\u000b\u0004\u007f\u0005}\u0001\"\u0002\u001a\u0013\u0001\u0004!\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0003K\tY\u0003\u0005\u0003!\u0003O!\u0014bAA\u00155\t1q\n\u001d;j_:D\u0001\"!\f\u0014\u0003\u0003\u0005\raP\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA\u001a!\r!\u0016QG\u0005\u0004\u0003o)&AB(cU\u0016\u001cG\u000f"
)
public class FatalError extends Exception implements Product {
   private final String msg;

   public static Option unapply(final FatalError x$0) {
      return FatalError$.MODULE$.unapply(x$0);
   }

   public static FatalError apply(final String msg) {
      FatalError$ var10000 = FatalError$.MODULE$;
      return new FatalError(msg);
   }

   public static Function1 andThen(final Function1 g) {
      return Function1::$anonfun$andThen$1;
   }

   public static Function1 compose(final Function1 g) {
      return Function1::$anonfun$compose$1;
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String msg() {
      return this.msg;
   }

   public FatalError copy(final String msg) {
      return new FatalError(msg);
   }

   public String copy$default$1() {
      return this.msg();
   }

   public String productPrefix() {
      return "FatalError";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.msg();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return new ScalaRunTime..anon.1(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof FatalError;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "msg";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   public int hashCode() {
      return .MODULE$.productHash(this, -889275714, false);
   }

   public boolean equals(final Object x$1) {
      if (this != x$1) {
         if (x$1 instanceof FatalError) {
            FatalError var2 = (FatalError)x$1;
            String var10000 = this.msg();
            String var3 = var2.msg();
            if (var10000 == null) {
               if (var3 != null) {
                  return false;
               }
            } else if (!var10000.equals(var3)) {
               return false;
            }

            if (var2.canEqual(this)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public FatalError(final String msg) {
      super(msg);
      this.msg = msg;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
