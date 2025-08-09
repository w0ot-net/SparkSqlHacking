package scala.reflect.internal;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Constructor;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime;
import scala.runtime.Statics;
import scala.util.hashing.MurmurHash3.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=d\u0001\u0002\f\u0018\u0005zA\u0001b\r\u0001\u0003\u0016\u0004%\t\u0001\u000e\u0005\t\u0007\u0002\u0011\t\u0012)A\u0005k!)1\n\u0001C\u0001\u0019\"91\u000bAA\u0001\n\u0003!\u0006b\u0002,\u0001#\u0003%\ta\u0016\u0005\bM\u0002\t\t\u0011\"\u0011h\u0011\u001da\u0007!!A\u0005\u00025Dq!\u001d\u0001\u0002\u0002\u0013\u0005!\u000fC\u0004v\u0001\u0005\u0005I\u0011\t<\t\u000fu\u0004\u0011\u0011!C\u0001}\"I\u0011q\u0001\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0002\u0005\n\u0003\u001b\u0001\u0011\u0011!C!\u0003\u001fA\u0011\"!\u0005\u0001\u0003\u0003%\t%a\u0005\t\u0013\u0005U\u0001!!A\u0005B\u0005]q!CA\u000e/\u0005\u0005\t\u0012AA\u000f\r!1r#!A\t\u0002\u0005}\u0001BB&\u0011\t\u0003\ty\u0004C\u0005\u0002\u0012A\t\t\u0011\"\u0012\u0002\u0014!I\u0011\u0011\t\t\u0002\u0002\u0013\u0005\u00151\t\u0005\n\u0003\u001f\u0002\u0012\u0011!CA\u0003#B\u0011\"!\u001a\u0011\u0003\u0003%I!a\u001a\u0003\u0019)\u001buN\\:ueV\u001cGo\u001c:\u000b\u0005aI\u0012\u0001C5oi\u0016\u0014h.\u00197\u000b\u0005iY\u0012a\u0002:fM2,7\r\u001e\u0006\u00029\u0005)1oY1mC\u000e\u00011\u0003\u0002\u0001 G\u001d\u0002\"\u0001I\u0011\u000e\u0003]I!AI\f\u0003))kU\r\u001e5pI>\u00138i\u001c8tiJ,8\r^8s!\t!S%D\u0001\u001c\u0013\t13DA\u0004Qe>$Wo\u0019;\u0011\u0005!\u0002dBA\u0015/\u001d\tQS&D\u0001,\u0015\taS$\u0001\u0004=e>|GOP\u0005\u00029%\u0011qfG\u0001\ba\u0006\u001c7.Y4f\u0013\t\t$G\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u000207\u0005\tQ.F\u00016a\t1\u0014\tE\u00028{}j\u0011\u0001\u000f\u0006\u00035eR!AO\u001e\u0002\t1\fgn\u001a\u0006\u0002y\u0005!!.\u0019<b\u0013\tq\u0004HA\u0006D_:\u001cHO];di>\u0014\bC\u0001!B\u0019\u0001!\u0011B\u0011\u0002\u0002\u0002\u0003\u0005)\u0011\u0001#\u0003\u0007}#C'\u0001\u0002nAE\u0011Q\t\u0013\t\u0003I\u0019K!aR\u000e\u0003\u000f9{G\u000f[5oOB\u0011A%S\u0005\u0003\u0015n\u00111!\u00118z\u0003\u0019a\u0014N\\5u}Q\u0011QJ\u0014\t\u0003A\u0001AQaM\u0002A\u0002=\u0003$\u0001\u0015*\u0011\u0007]j\u0014\u000b\u0005\u0002A%\u0012I!ITA\u0001\u0002\u0003\u0015\t\u0001R\u0001\u0005G>\u0004\u0018\u0010\u0006\u0002N+\"91\u0007\u0002I\u0001\u0002\u0004y\u0015AD2paf$C-\u001a4bk2$H%M\u000b\u00021B\u0012\u0011\f\u0018\u0016\u00035v\u00032aN\u001f\\!\t\u0001E\fB\u0005C\u000b\u0005\u0005\t\u0011!B\u0001\t.\na\f\u0005\u0002`I6\t\u0001M\u0003\u0002bE\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003Gn\t!\"\u00198o_R\fG/[8o\u0013\t)\u0007MA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u00015\u0011\u0005%TW\"A\u001d\n\u0005-L$AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001o!\t!s.\u0003\u0002q7\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011\u0001j\u001d\u0005\bi\"\t\t\u00111\u0001o\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\tq\u000fE\u0002yw\"k\u0011!\u001f\u0006\u0003un\t!bY8mY\u0016\u001cG/[8o\u0013\ta\u0018P\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGcA@\u0002\u0006A\u0019A%!\u0001\n\u0007\u0005\r1DA\u0004C_>dW-\u00198\t\u000fQT\u0011\u0011!a\u0001\u0011\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\rA\u00171\u0002\u0005\bi.\t\t\u00111\u0001o\u0003!A\u0017m\u001d5D_\u0012,G#\u00018\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012\u0001[\u0001\u0007KF,\u0018\r\\:\u0015\u0007}\fI\u0002C\u0004u\u001d\u0005\u0005\t\u0019\u0001%\u0002\u0019)\u001buN\\:ueV\u001cGo\u001c:\u0011\u0005\u0001\u00022#\u0002\t\u0002\"\u0005U\u0002cBA\u0012\u0003S\ti#T\u0007\u0003\u0003KQ1!a\n\u001c\u0003\u001d\u0011XO\u001c;j[\u0016LA!a\u000b\u0002&\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u00191\t\u0005=\u00121\u0007\t\u0005ou\n\t\u0004E\u0002A\u0003g!\u0011B\u0011\t\u0002\u0002\u0003\u0005)\u0011\u0001#\u0011\t\u0005]\u0012QH\u0007\u0003\u0003sQ1!a\u000f<\u0003\tIw.C\u00022\u0003s!\"!!\b\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u00075\u000b)\u0005\u0003\u00044'\u0001\u0007\u0011q\t\u0019\u0005\u0003\u0013\ni\u0005\u0005\u00038{\u0005-\u0003c\u0001!\u0002N\u0011Q!)!\u0012\u0002\u0002\u0003\u0005)\u0011\u0001#\u0002\u000fUt\u0017\r\u001d9msR!\u00111KA1!\u0015!\u0013QKA-\u0013\r\t9f\u0007\u0002\u0007\u001fB$\u0018n\u001c81\t\u0005m\u0013q\f\t\u0005ou\ni\u0006E\u0002A\u0003?\"\u0011B\u0011\u000b\u0002\u0002\u0003\u0005)\u0011\u0001#\t\u0011\u0005\rD#!AA\u00025\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\tI\u0007E\u0002j\u0003WJ1!!\u001c:\u0005\u0019y%M[3di\u0002"
)
public final class JConstructor extends JMethodOrConstructor implements Product, Serializable {
   private final Constructor m;

   public static Option unapply(final JConstructor x$0) {
      return JConstructor$.MODULE$.unapply(x$0);
   }

   public static JConstructor apply(final Constructor m) {
      JConstructor$ var10000 = JConstructor$.MODULE$;
      return new JConstructor(m);
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

   public Constructor m() {
      return this.m;
   }

   public JConstructor copy(final Constructor m) {
      return new JConstructor(m);
   }

   public Constructor copy$default$1() {
      return this.m();
   }

   public String productPrefix() {
      return "JConstructor";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.m();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return new ScalaRunTime..anon.1(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof JConstructor;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "m";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   public int hashCode() {
      return .MODULE$.productHash(this, -889275714, false);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      if (this != x$1) {
         if (x$1 instanceof JConstructor) {
            JConstructor var2 = (JConstructor)x$1;
            Constructor var10000 = this.m();
            Constructor var3 = var2.m();
            if (var10000 == null) {
               if (var3 == null) {
                  return true;
               }
            } else if (var10000.equals(var3)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public JConstructor(final Constructor m) {
      this.m = m;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
