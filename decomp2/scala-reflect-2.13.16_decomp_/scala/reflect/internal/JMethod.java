package scala.reflect.internal;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime;
import scala.runtime.Statics;
import scala.util.hashing.MurmurHash3.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ub\u0001\u0002\f\u0018\u0005zA\u0001b\r\u0001\u0003\u0016\u0004%\t\u0001\u000e\u0005\t}\u0001\u0011\t\u0012)A\u0005k!)q\b\u0001C\u0001\u0001\"91\tAA\u0001\n\u0003!\u0005b\u0002$\u0001#\u0003%\ta\u0012\u0005\b%\u0002\t\t\u0011\"\u0011T\u0011\u001dA\u0006!!A\u0005\u0002eCq!\u0018\u0001\u0002\u0002\u0013\u0005a\fC\u0004e\u0001\u0005\u0005I\u0011I3\t\u000f1\u0004\u0011\u0011!C\u0001[\"9!\u000fAA\u0001\n\u0003\u001a\bbB;\u0001\u0003\u0003%\tE\u001e\u0005\bo\u0002\t\t\u0011\"\u0011y\u0011\u001dI\b!!A\u0005Bi<q\u0001`\f\u0002\u0002#\u0005QPB\u0004\u0017/\u0005\u0005\t\u0012\u0001@\t\r}\u0002B\u0011AA\u000b\u0011\u001d9\b#!A\u0005FaD\u0011\"a\u0006\u0011\u0003\u0003%\t)!\u0007\t\u0013\u0005u\u0001#!A\u0005\u0002\u0006}\u0001\"CA\u0016!\u0005\u0005I\u0011BA\u0017\u0005\u001dQU*\u001a;i_\u0012T!\u0001G\r\u0002\u0011%tG/\u001a:oC2T!AG\u000e\u0002\u000fI,g\r\\3di*\tA$A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\t\u0001y2e\n\t\u0003A\u0005j\u0011aF\u0005\u0003E]\u0011ACS'fi\"|Gm\u0014:D_:\u001cHO];di>\u0014\bC\u0001\u0013&\u001b\u0005Y\u0012B\u0001\u0014\u001c\u0005\u001d\u0001&o\u001c3vGR\u0004\"\u0001\u000b\u0019\u000f\u0005%rcB\u0001\u0016.\u001b\u0005Y#B\u0001\u0017\u001e\u0003\u0019a$o\\8u}%\tA$\u0003\u000207\u00059\u0001/Y2lC\u001e,\u0017BA\u00193\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\ty3$A\u0001n+\u0005)\u0004C\u0001\u001c=\u001b\u00059$B\u0001\u000e9\u0015\tI$(\u0001\u0003mC:<'\"A\u001e\u0002\t)\fg/Y\u0005\u0003{]\u0012a!T3uQ>$\u0017AA7!\u0003\u0019a\u0014N\\5u}Q\u0011\u0011I\u0011\t\u0003A\u0001AQaM\u0002A\u0002U\nAaY8qsR\u0011\u0011)\u0012\u0005\bg\u0011\u0001\n\u00111\u00016\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012\u0001\u0013\u0016\u0003k%[\u0013A\u0013\t\u0003\u0017Bk\u0011\u0001\u0014\u0006\u0003\u001b:\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005=[\u0012AC1o]>$\u0018\r^5p]&\u0011\u0011\u000b\u0014\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001U!\t)f+D\u00019\u0013\t9\u0006H\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u00025B\u0011AeW\u0005\u00039n\u00111!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"a\u00182\u0011\u0005\u0011\u0002\u0017BA1\u001c\u0005\r\te.\u001f\u0005\bG\"\t\t\u00111\u0001[\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\ta\rE\u0002hU~k\u0011\u0001\u001b\u0006\u0003Sn\t!bY8mY\u0016\u001cG/[8o\u0013\tY\u0007N\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGC\u00018r!\t!s.\u0003\u0002q7\t9!i\\8mK\u0006t\u0007bB2\u000b\u0003\u0003\u0005\raX\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0002Ui\"91mCA\u0001\u0002\u0004Q\u0016\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003i\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0002)\u00061Q-];bYN$\"A\\>\t\u000f\rt\u0011\u0011!a\u0001?\u00069!*T3uQ>$\u0007C\u0001\u0011\u0011'\u0011\u0001r0a\u0003\u0011\r\u0005\u0005\u0011qA\u001bB\u001b\t\t\u0019AC\u0002\u0002\u0006m\tqA];oi&lW-\u0003\u0003\u0002\n\u0005\r!!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ocA!\u0011QBA\n\u001b\t\tyAC\u0002\u0002\u0012i\n!![8\n\u0007E\ny\u0001F\u0001~\u0003\u0015\t\u0007\u000f\u001d7z)\r\t\u00151\u0004\u0005\u0006gM\u0001\r!N\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t\t#a\n\u0011\t\u0011\n\u0019#N\u0005\u0004\u0003KY\"AB(qi&|g\u000e\u0003\u0005\u0002*Q\t\t\u00111\u0001B\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003_\u00012!VA\u0019\u0013\r\t\u0019\u0004\u000f\u0002\u0007\u001f\nTWm\u0019;"
)
public final class JMethod extends JMethodOrConstructor implements Product, Serializable {
   private final Method m;

   public static Option unapply(final JMethod x$0) {
      return JMethod$.MODULE$.unapply(x$0);
   }

   public static JMethod apply(final Method m) {
      JMethod$ var10000 = JMethod$.MODULE$;
      return new JMethod(m);
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

   public Method m() {
      return this.m;
   }

   public JMethod copy(final Method m) {
      return new JMethod(m);
   }

   public Method copy$default$1() {
      return this.m();
   }

   public String productPrefix() {
      return "JMethod";
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
      return x$1 instanceof JMethod;
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
         if (x$1 instanceof JMethod) {
            JMethod var2 = (JMethod)x$1;
            Method var10000 = this.m();
            Method var3 = var2.m();
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

   public JMethod(final Method m) {
      this.m = m;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
