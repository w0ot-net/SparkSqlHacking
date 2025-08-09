package scala.xml.parsing;

import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]b\u0001B\u000b\u0017\u0001vA\u0001B\u000e\u0001\u0003\u0016\u0004%\ta\u000e\u0005\t\u0001\u0002\u0011\t\u0012)A\u0005q!)\u0011\t\u0001C\u0001\u0005\"9a\tAA\u0001\n\u00039\u0005bB%\u0001#\u0003%\tA\u0013\u0005\b+\u0002\t\t\u0011\"\u0011W\u0011\u001dI\u0006!!A\u0005\u0002iCqA\u0018\u0001\u0002\u0002\u0013\u0005q\fC\u0004f\u0001\u0005\u0005I\u0011\t4\t\u000f5\u0004\u0011\u0011!C\u0001]\"91\u000fAA\u0001\n\u0003\"\bb\u0002<\u0001\u0003\u0003%\te\u001e\u0005\bq\u0002\t\t\u0011\"\u0011z\u000f\u001dYh#!A\t\u0002q4q!\u0006\f\u0002\u0002#\u0005Q\u0010\u0003\u0004B\u001f\u0011\u0005\u00111\u0003\u0005\n\u0003+y\u0011\u0011!C#\u0003/A\u0011\"!\u0007\u0010\u0003\u0003%\t)a\u0007\t\u0013\u0005}q\"!A\u0005\u0002\u0006\u0005\u0002\"CA\u0017\u001f\u0005\u0005I\u0011BA\u0018\u0005)1\u0015\r^1m\u000bJ\u0014xN\u001d\u0006\u0003/a\tq\u0001]1sg&twM\u0003\u0002\u001a5\u0005\u0019\u00010\u001c7\u000b\u0003m\tQa]2bY\u0006\u001c\u0001a\u0005\u0003\u0001=\u0019R\u0003CA\u0010%\u001b\u0005\u0001#BA\u0011#\u0003\u0011a\u0017M\\4\u000b\u0003\r\nAA[1wC&\u0011Q\u0005\t\u0002\u0011%VtG/[7f\u000bb\u001cW\r\u001d;j_:\u0004\"a\n\u0015\u000e\u0003iI!!\u000b\u000e\u0003\u000fA\u0013x\u000eZ;diB\u00111f\r\b\u0003YEr!!\f\u0019\u000e\u00039R!a\f\u000f\u0002\rq\u0012xn\u001c;?\u0013\u0005Y\u0012B\u0001\u001a\u001b\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001N\u001b\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005IR\u0012aA7tOV\t\u0001\b\u0005\u0002:{9\u0011!h\u000f\t\u0003[iI!\u0001\u0010\u000e\u0002\rA\u0013X\rZ3g\u0013\tqtH\u0001\u0004TiJLgn\u001a\u0006\u0003yi\tA!\\:hA\u00051A(\u001b8jiz\"\"aQ#\u0011\u0005\u0011\u0003Q\"\u0001\f\t\u000bY\u001a\u0001\u0019\u0001\u001d\u0002\t\r|\u0007/\u001f\u000b\u0003\u0007\"CqA\u000e\u0003\u0011\u0002\u0003\u0007\u0001(\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003-S#\u0001\u000f',\u00035\u0003\"AT*\u000e\u0003=S!\u0001U)\u0002\u0013Ut7\r[3dW\u0016$'B\u0001*\u001b\u0003)\tgN\\8uCRLwN\\\u0005\u0003)>\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\tq\u000b\u0005\u0002 1&\u0011a\bI\u0001\raJ|G-^2u\u0003JLG/_\u000b\u00027B\u0011q\u0005X\u0005\u0003;j\u00111!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"\u0001Y2\u0011\u0005\u001d\n\u0017B\u00012\u001b\u0005\r\te.\u001f\u0005\bI\"\t\t\u00111\u0001\\\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\tq\rE\u0002iW\u0002l\u0011!\u001b\u0006\u0003Uj\t!bY8mY\u0016\u001cG/[8o\u0013\ta\u0017N\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGCA8s!\t9\u0003/\u0003\u0002r5\t9!i\\8mK\u0006t\u0007b\u00023\u000b\u0003\u0003\u0005\r\u0001Y\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0002Xk\"9AmCA\u0001\u0002\u0004Y\u0016\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003m\u000ba!Z9vC2\u001cHCA8{\u0011\u001d!W\"!AA\u0002\u0001\f!BR1uC2,%O]8s!\t!ub\u0005\u0003\u0010}\u0006%\u0001#B@\u0002\u0006a\u001aUBAA\u0001\u0015\r\t\u0019AG\u0001\beVtG/[7f\u0013\u0011\t9!!\u0001\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0005\u0003\u0002\f\u0005EQBAA\u0007\u0015\r\tyAI\u0001\u0003S>L1\u0001NA\u0007)\u0005a\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003]\u000bQ!\u00199qYf$2aQA\u000f\u0011\u00151$\u00031\u00019\u0003\u001d)h.\u00199qYf$B!a\t\u0002*A!q%!\n9\u0013\r\t9C\u0007\u0002\u0007\u001fB$\u0018n\u001c8\t\u0011\u0005-2#!AA\u0002\r\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t\t\u0004E\u0002 \u0003gI1!!\u000e!\u0005\u0019y%M[3di\u0002"
)
public class FatalError extends RuntimeException implements Product {
   private final String msg;

   public static Option unapply(final FatalError x$0) {
      return FatalError$.MODULE$.unapply(x$0);
   }

   public static FatalError apply(final String msg) {
      return FatalError$.MODULE$.apply(msg);
   }

   public static Function1 andThen(final Function1 g) {
      return FatalError$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return FatalError$.MODULE$.compose(g);
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
      return .MODULE$.typedProductIterator(this);
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
      return .MODULE$._hashCode(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label47: {
            if (x$1 instanceof FatalError) {
               label40: {
                  FatalError var4 = (FatalError)x$1;
                  String var10000 = this.msg();
                  String var5 = var4.msg();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label40;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label40;
                  }

                  if (var4.canEqual(this)) {
                     break label47;
                  }
               }
            }

            var6 = false;
            return var6;
         }
      }

      var6 = true;
      return var6;
   }

   public FatalError(final String msg) {
      super(msg);
      this.msg = msg;
      Product.$init$(this);
   }
}
