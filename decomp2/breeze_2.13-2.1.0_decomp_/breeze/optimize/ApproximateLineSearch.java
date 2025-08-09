package breeze.optimize;

import breeze.util.Implicits$;
import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005MeaB\u0011#!\u0003\r\ta\n\u0005\u0006e\u0001!\ta\r\u0004\u0005o\u0001\u0011\u0005\b\u0003\u0005I\u0005\tU\r\u0011\"\u0001J\u0011!i%A!E!\u0002\u0013Q\u0005\u0002\u0003(\u0003\u0005+\u0007I\u0011A%\t\u0011=\u0013!\u0011#Q\u0001\n)C\u0001\u0002\u0015\u0002\u0003\u0016\u0004%\t!\u0013\u0005\t#\n\u0011\t\u0012)A\u0005\u0015\")!K\u0001C\u0001'\"9\u0011LAA\u0001\n\u0003Q\u0006b\u00020\u0003#\u0003%\ta\u0018\u0005\bU\n\t\n\u0011\"\u0001`\u0011\u001dY'!%A\u0005\u0002}Cq\u0001\u001c\u0002\u0002\u0002\u0013\u0005S\u000eC\u0004w\u0005\u0005\u0005I\u0011A<\t\u000fm\u0014\u0011\u0011!C\u0001y\"I\u0011Q\u0001\u0002\u0002\u0002\u0013\u0005\u0013q\u0001\u0005\n\u0003+\u0011\u0011\u0011!C\u0001\u0003/A\u0011\"!\t\u0003\u0003\u0003%\t%a\t\t\u0013\u0005\u001d\"!!A\u0005B\u0005%\u0002\"CA\u0016\u0005\u0005\u0005I\u0011IA\u0017\u0011%\tyCAA\u0001\n\u0003\n\tdB\u0005\u00026\u0001\t\t\u0011#\u0001\u00028\u0019Aq\u0007AA\u0001\u0012\u0003\tI\u0004\u0003\u0004S1\u0011\u0005\u0011\u0011\u000b\u0005\n\u0003WA\u0012\u0011!C#\u0003[A\u0011\"a\u0015\u0019\u0003\u0003%\t)!\u0016\t\u0013\u0005u\u0003$!A\u0005\u0002\u0006}\u0003bBA9\u0001\u0019\u0005\u00111\u000f\u0005\t\u0003\u000f\u0003\u0011\u0013!C\u0001?\"9\u0011\u0011\u0012\u0001\u0005\u0002\u0005-\u0005\u0002CAI\u0001E\u0005I\u0011A0\u0003+\u0005\u0003\bO]8yS6\fG/\u001a'j]\u0016\u001cV-\u0019:dQ*\u00111\u0005J\u0001\t_B$\u0018.\\5{K*\tQ%\u0001\u0004ce\u0016,'0Z\u0002\u0001'\r\u0001\u0001F\f\t\u0003S1j\u0011A\u000b\u0006\u0002W\u0005)1oY1mC&\u0011QF\u000b\u0002\u0007\u0003:L(+\u001a4\u0011\u0005=\u0002T\"\u0001\u0012\n\u0005E\u0012#\u0001F'j]&l\u0017N_5oO2Kg.Z*fCJ\u001c\u0007.\u0001\u0004%S:LG\u000f\n\u000b\u0002iA\u0011\u0011&N\u0005\u0003m)\u0012A!\u00168ji\n)1\u000b^1uKN!!\u0001K\u001d=!\tI#(\u0003\u0002<U\t9\u0001K]8ek\u000e$\bCA\u001fF\u001d\tq4I\u0004\u0002@\u00056\t\u0001I\u0003\u0002BM\u00051AH]8pizJ\u0011aK\u0005\u0003\t*\nq\u0001]1dW\u0006<W-\u0003\u0002G\u000f\na1+\u001a:jC2L'0\u00192mK*\u0011AIK\u0001\u0006C2\u0004\b.Y\u000b\u0002\u0015B\u0011\u0011fS\u0005\u0003\u0019*\u0012a\u0001R8vE2,\u0017AB1ma\"\f\u0007%A\u0003wC2,X-\u0001\u0004wC2,X\rI\u0001\u0006I\u0016\u0014\u0018N^\u0001\u0007I\u0016\u0014\u0018N\u001e\u0011\u0002\rqJg.\u001b;?)\u0011!fk\u0016-\u0011\u0005U\u0013Q\"\u0001\u0001\t\u000b!K\u0001\u0019\u0001&\t\u000b9K\u0001\u0019\u0001&\t\u000bAK\u0001\u0019\u0001&\u0002\t\r|\u0007/\u001f\u000b\u0005)ncV\fC\u0004I\u0015A\u0005\t\u0019\u0001&\t\u000f9S\u0001\u0013!a\u0001\u0015\"9\u0001K\u0003I\u0001\u0002\u0004Q\u0015AD2paf$C-\u001a4bk2$H%M\u000b\u0002A*\u0012!*Y\u0016\u0002EB\u00111\r[\u0007\u0002I*\u0011QMZ\u0001\nk:\u001c\u0007.Z2lK\u0012T!a\u001a\u0016\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002jI\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%e\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\u001a\u0014!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001o!\tyG/D\u0001q\u0015\t\t(/\u0001\u0003mC:<'\"A:\u0002\t)\fg/Y\u0005\u0003kB\u0014aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLX#\u0001=\u0011\u0005%J\u0018B\u0001>+\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\ri\u0018\u0011\u0001\t\u0003SyL!a \u0016\u0003\u0007\u0005s\u0017\u0010\u0003\u0005\u0002\u0004A\t\t\u00111\u0001y\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011\u0011\u0002\t\u0006\u0003\u0017\t\t\"`\u0007\u0003\u0003\u001bQ1!a\u0004+\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003'\tiA\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\r\u0003?\u00012!KA\u000e\u0013\r\tiB\u000b\u0002\b\u0005>|G.Z1o\u0011!\t\u0019AEA\u0001\u0002\u0004i\u0018A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$2A\\A\u0013\u0011!\t\u0019aEA\u0001\u0002\u0004A\u0018\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003a\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002]\u00061Q-];bYN$B!!\u0007\u00024!A\u00111\u0001\f\u0002\u0002\u0003\u0007Q0A\u0003Ti\u0006$X\r\u0005\u0002V1M)\u0001$a\u000f\u0002HAA\u0011QHA\"\u0015*SE+\u0004\u0002\u0002@)\u0019\u0011\u0011\t\u0016\u0002\u000fI,h\u000e^5nK&!\u0011QIA \u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gn\r\t\u0005\u0003\u0013\ny%\u0004\u0002\u0002L)\u0019\u0011Q\n:\u0002\u0005%|\u0017b\u0001$\u0002LQ\u0011\u0011qG\u0001\u0006CB\u0004H.\u001f\u000b\b)\u0006]\u0013\u0011LA.\u0011\u0015A5\u00041\u0001K\u0011\u0015q5\u00041\u0001K\u0011\u0015\u00016\u00041\u0001K\u0003\u001d)h.\u00199qYf$B!!\u0019\u0002nA)\u0011&a\u0019\u0002h%\u0019\u0011Q\r\u0016\u0003\r=\u0003H/[8o!\u0019I\u0013\u0011\u000e&K\u0015&\u0019\u00111\u000e\u0016\u0003\rQ+\b\u000f\\34\u0011!\ty\u0007HA\u0001\u0002\u0004!\u0016a\u0001=%a\u0005Q\u0011\u000e^3sCRLwN\\:\u0015\r\u0005U\u0014\u0011PAB!\u0011i\u0014q\u000f+\n\u0007\u0005Mq\tC\u0004\u0002|u\u0001\r!! \u0002\u0003\u0019\u0004BaLA@\u0015&\u0019\u0011\u0011\u0011\u0012\u0003\u0019\u0011KgM\u001a$v]\u000e$\u0018n\u001c8\t\u0011\u0005\u0015U\u0004%AA\u0002)\u000bA!\u001b8ji\u0006!\u0012\u000e^3sCRLwN\\:%I\u00164\u0017-\u001e7uII\n\u0001\"\\5oS6L'0\u001a\u000b\u0006\u0015\u00065\u0015q\u0012\u0005\b\u0003wz\u0002\u0019AA?\u0011!\t)i\bI\u0001\u0002\u0004Q\u0015AE7j]&l\u0017N_3%I\u00164\u0017-\u001e7uII\u0002"
)
public interface ApproximateLineSearch extends MinimizingLineSearch {
   State$ State();

   Iterator iterations(final DiffFunction f, final double init);

   // $FF: synthetic method
   static double iterations$default$2$(final ApproximateLineSearch $this) {
      return $this.iterations$default$2();
   }

   default double iterations$default$2() {
      return (double)1.0F;
   }

   // $FF: synthetic method
   static double minimize$(final ApproximateLineSearch $this, final DiffFunction f, final double init) {
      return $this.minimize(f, init);
   }

   default double minimize(final DiffFunction f, final double init) {
      return ((State)Implicits$.MODULE$.scEnrichIterator(this.iterations(f, init)).last()).alpha();
   }

   // $FF: synthetic method
   static double minimize$default$2$(final ApproximateLineSearch $this) {
      return $this.minimize$default$2();
   }

   default double minimize$default$2() {
      return (double)1.0F;
   }

   static void $init$(final ApproximateLineSearch $this) {
   }

   public final class State implements Product, Serializable {
      private final double alpha;
      private final double value;
      private final double deriv;
      // $FF: synthetic field
      private final ApproximateLineSearch $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public double alpha() {
         return this.alpha;
      }

      public double value() {
         return this.value;
      }

      public double deriv() {
         return this.deriv;
      }

      public State copy(final double alpha, final double value, final double deriv) {
         return this.$outer.new State(alpha, value, deriv);
      }

      public double copy$default$1() {
         return this.alpha();
      }

      public double copy$default$2() {
         return this.value();
      }

      public double copy$default$3() {
         return this.deriv();
      }

      public String productPrefix() {
         return "State";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = BoxesRunTime.boxToDouble(this.alpha());
               break;
            case 1:
               var10000 = BoxesRunTime.boxToDouble(this.value());
               break;
            case 2:
               var10000 = BoxesRunTime.boxToDouble(this.deriv());
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
         return x$1 instanceof State;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "alpha";
               break;
            case 1:
               var10000 = "value";
               break;
            case 2:
               var10000 = "deriv";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.doubleHash(this.alpha()));
         var1 = Statics.mix(var1, Statics.doubleHash(this.value()));
         var1 = Statics.mix(var1, Statics.doubleHash(this.deriv()));
         return Statics.finalizeHash(var1, 3);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label56: {
               boolean var2;
               if (x$1 instanceof State && true) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  State var4 = (State)x$1;
                  if (this.alpha() == var4.alpha() && this.value() == var4.value() && this.deriv() == var4.deriv()) {
                     break label56;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public State(final double alpha, final double value, final double deriv) {
         this.alpha = alpha;
         this.value = value;
         this.deriv = deriv;
         if (ApproximateLineSearch.this == null) {
            throw null;
         } else {
            this.$outer = ApproximateLineSearch.this;
            super();
            Product.$init$(this);
         }
      }
   }

   public class State$ extends AbstractFunction3 implements Serializable {
      // $FF: synthetic field
      private final ApproximateLineSearch $outer;

      public final String toString() {
         return "State";
      }

      public State apply(final double alpha, final double value, final double deriv) {
         return this.$outer.new State(alpha, value, deriv);
      }

      public Option unapply(final State x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToDouble(x$0.alpha()), BoxesRunTime.boxToDouble(x$0.value()), BoxesRunTime.boxToDouble(x$0.deriv()))));
      }

      public State$() {
         if (ApproximateLineSearch.this == null) {
            throw null;
         } else {
            this.$outer = ApproximateLineSearch.this;
            super();
         }
      }
   }
}
