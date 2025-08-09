package spire.random;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import scala.util.Either;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mc\u0001\u0002\f\u0018\u0001rA\u0001B\u0011\u0001\u0003\u0016\u0004%\ta\u0011\u0005\t\t\u0002\u0011\t\u0012)A\u0005Q!)Q\t\u0001C\u0001\r\"9\u0011\nAA\u0001\n\u0003Q\u0005b\u0002)\u0001#\u0003%\t!\u0015\u0005\b=\u0002\t\t\u0011\"\u0011`\u0011\u001dA\u0007!!A\u0005\u0002%Dq!\u001c\u0001\u0002\u0002\u0013\u0005a\u000eC\u0004r\u0001\u0005\u0005I\u0011\t:\t\u000fe\u0004\u0011\u0011!C\u0001u\"Aq\u0010AA\u0001\n\u0003\n\t\u0001C\u0005\u0002\u0006\u0001\t\t\u0011\"\u0011\u0002\b!I\u0011\u0011\u0002\u0001\u0002\u0002\u0013\u0005\u00131\u0002\u0005\n\u0003\u001b\u0001\u0011\u0011!C!\u0003\u001f9\u0011\"a\u0005\u0018\u0003\u0003E\t!!\u0006\u0007\u0011Y9\u0012\u0011!E\u0001\u0003/Aa!\u0012\t\u0005\u0002\u0005\r\u0002\"CA\u0005!\u0005\u0005IQIA\u0006\u0011%\t)\u0003EA\u0001\n\u0003\u000b9\u0003C\u0005\u00024A\t\t\u0011\"!\u00026!I\u0011\u0011\n\t\u0002\u0002\u0013%\u00111\n\u0002\u0006\u0007>t7\u000f\u001e\u0006\u00031e\taA]1oI>l'\"\u0001\u000e\u0002\u000bM\u0004\u0018N]3\u0004\u0001U\u0011QDK\n\u0006\u0001y!3G\u000e\t\u0003?\tj\u0011\u0001\t\u0006\u0002C\u0005)1oY1mC&\u00111\u0005\t\u0002\u0007\u0003:L(+\u001a4\u0011\u0007\u00152\u0003&D\u0001\u0018\u0013\t9sC\u0001\u0002PaB\u0011\u0011F\u000b\u0007\u0001\t\u0019Y\u0003\u0001\"b\u0001Y\t\t\u0011)\u0005\u0002.aA\u0011qDL\u0005\u0003_\u0001\u0012qAT8uQ&tw\r\u0005\u0002 c%\u0011!\u0007\t\u0002\u0004\u0003:L\bCA\u00105\u0013\t)\u0004EA\u0004Qe>$Wo\u0019;\u0011\u0005]zdB\u0001\u001d>\u001d\tID(D\u0001;\u0015\tY4$\u0001\u0004=e>|GOP\u0005\u0002C%\u0011a\bI\u0001\ba\u0006\u001c7.Y4f\u0013\t\u0001\u0015I\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002?A\u0005\t\u0011-F\u0001)\u0003\t\t\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0003\u000f\"\u00032!\n\u0001)\u0011\u0015\u00115\u00011\u0001)\u0003\u0011\u0019w\u000e]=\u0016\u0005-sEC\u0001'P!\r)\u0003!\u0014\t\u0003S9#Qa\u000b\u0003C\u00021BqA\u0011\u0003\u0011\u0002\u0003\u0007Q*\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005IkV#A*+\u0005!\"6&A+\u0011\u0005Y[V\"A,\u000b\u0005aK\u0016!C;oG\",7m[3e\u0015\tQ\u0006%\u0001\u0006b]:|G/\u0019;j_:L!\u0001X,\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\rB\u0003,\u000b\t\u0007A&A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002AB\u0011\u0011MZ\u0007\u0002E*\u00111\rZ\u0001\u0005Y\u0006twMC\u0001f\u0003\u0011Q\u0017M^1\n\u0005\u001d\u0014'AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001k!\ty2.\u0003\u0002mA\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011\u0001g\u001c\u0005\ba\"\t\t\u00111\u0001k\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\t1\u000fE\u0002uoBj\u0011!\u001e\u0006\u0003m\u0002\n!bY8mY\u0016\u001cG/[8o\u0013\tAXO\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGCA>\u007f!\tyB0\u0003\u0002~A\t9!i\\8mK\u0006t\u0007b\u00029\u000b\u0003\u0003\u0005\r\u0001M\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002a\u0003\u0007Aq\u0001]\u0006\u0002\u0002\u0003\u0007!.\u0001\u0005iCND7i\u001c3f)\u0005Q\u0017\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003\u0001\fa!Z9vC2\u001cHcA>\u0002\u0012!9\u0001ODA\u0001\u0002\u0004\u0001\u0014!B\"p]N$\bCA\u0013\u0011'\u0011\u0001b$!\u0007\u0011\t\u0005m\u0011\u0011E\u0007\u0003\u0003;Q1!a\be\u0003\tIw.C\u0002A\u0003;!\"!!\u0006\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\t\u0005%\u0012q\u0006\u000b\u0005\u0003W\t\t\u0004\u0005\u0003&\u0001\u00055\u0002cA\u0015\u00020\u0011)1f\u0005b\u0001Y!1!i\u0005a\u0001\u0003[\tq!\u001e8baBd\u00170\u0006\u0003\u00028\u0005\u0005C\u0003BA\u001d\u0003\u0007\u0002RaHA\u001e\u0003\u007fI1!!\u0010!\u0005\u0019y\u0005\u000f^5p]B\u0019\u0011&!\u0011\u0005\u000b-\"\"\u0019\u0001\u0017\t\u0013\u0005\u0015C#!AA\u0002\u0005\u001d\u0013a\u0001=%aA!Q\u0005AA \u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\ti\u0005E\u0002b\u0003\u001fJ1!!\u0015c\u0005\u0019y%M[3di\u0002"
)
public class Const implements Op, Product, Serializable {
   private final Object a;

   public static Option unapply(final Const x$0) {
      return Const$.MODULE$.unapply(x$0);
   }

   public static Const apply(final Object a) {
      return Const$.MODULE$.apply(a);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Op flatMap(final Function1 f) {
      return Op.flatMap$(this, f);
   }

   public Op map(final Function1 f) {
      return Op.map$(this, f);
   }

   public final Either resume(final Generator gen) {
      return Op.resume$(this, gen);
   }

   public Object run(final Generator gen) {
      return Op.run$(this, gen);
   }

   public Object a() {
      return this.a;
   }

   public Const copy(final Object a) {
      return new Const(a);
   }

   public Object copy$default$1() {
      return this.a();
   }

   public String productPrefix() {
      return "Const";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.a();
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof Const;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "a";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label49: {
            boolean var2;
            if (x$1 instanceof Const) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Const var4 = (Const)x$1;
               if (BoxesRunTime.equals(this.a(), var4.a()) && var4.canEqual(this)) {
                  break label49;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public Const(final Object a) {
      this.a = a;
      Op.$init$(this);
      Product.$init$(this);
   }
}
