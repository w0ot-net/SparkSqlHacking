package breeze.optimize.linear;

import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005eb\u0001B\u000b\u0017\u0001vA\u0001b\r\u0001\u0003\u0016\u0004%\t\u0001\u000e\u0005\ty\u0001\u0011\t\u0012)A\u0005k!)Q\b\u0001C\u0001}!9\u0011\tAA\u0001\n\u0003\u0011\u0005b\u0002#\u0001#\u0003%\t!\u0012\u0005\b!\u0002\t\t\u0011\"\u0011R\u0011\u001dQ\u0006!!A\u0005\u0002mCqa\u0018\u0001\u0002\u0002\u0013\u0005\u0001\rC\u0004g\u0001\u0005\u0005I\u0011I4\t\u000f9\u0004\u0011\u0011!C\u0001_\"9A\u000fAA\u0001\n\u0003*\bbB<\u0001\u0003\u0003%\t\u0005\u001f\u0005\bs\u0002\t\t\u0011\"\u0011{\u000f\u001dah#!A\t\u0002u4q!\u0006\f\u0002\u0002#\u0005a\u0010\u0003\u0004>\u001f\u0011\u0005\u0011Q\u0003\u0005\n\u0003/y\u0011\u0011!C#\u00033A\u0011\"a\u0007\u0010\u0003\u0003%\t)!\b\t\u0013\u0005\u0005r\"!A\u0005\u0002\u0006\r\u0002\"CA\u0018\u001f\u0005\u0005I\u0011BA\u0019\u0005EIeNZ3bg&\u0014G.\u001a)s_\ndW-\u001c\u0006\u0003/a\ta\u0001\\5oK\u0006\u0014(BA\r\u001b\u0003!y\u0007\u000f^5nSj,'\"A\u000e\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0019B\u0001\u0001\u0010-aA\u0011q$\u000b\b\u0003A\u0019r!!\t\u0013\u000e\u0003\tR!a\t\u000f\u0002\rq\u0012xn\u001c;?\u0013\u0005)\u0013!B:dC2\f\u0017BA\u0014)\u0003\u001d\u0001\u0018mY6bO\u0016T\u0011!J\u0005\u0003U-\u0012\u0001CU;oi&lW-\u0012=dKB$\u0018n\u001c8\u000b\u0005\u001dB\u0003CA\u0017/\u001b\u0005A\u0013BA\u0018)\u0005\u001d\u0001&o\u001c3vGR\u0004\"aH\u0019\n\u0005IZ#\u0001D*fe&\fG.\u001b>bE2,\u0017\u0001\u00029s_\n,\u0012!\u000e\t\u0003mi\u0002\"a\u000e\u001d\u000e\u0003YI!!\u000f\f\u0003\u001b1Kg.Z1s!J|wM]1n\u0013\tY\u0004HA\u0004Qe>\u0014G.Z7\u0002\u000bA\u0014xN\u0019\u0011\u0002\rqJg.\u001b;?)\ty\u0004\t\u0005\u00028\u0001!)1g\u0001a\u0001k\u0005!1m\u001c9z)\ty4\tC\u00044\tA\u0005\t\u0019A\u001b\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\taI\u000b\u00026\u000f.\n\u0001\n\u0005\u0002J\u001d6\t!J\u0003\u0002L\u0019\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003\u001b\"\n!\"\u00198o_R\fG/[8o\u0013\ty%JA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u0001*\u0011\u0005MCV\"\u0001+\u000b\u0005U3\u0016\u0001\u00027b]\u001eT\u0011aV\u0001\u0005U\u00064\u0018-\u0003\u0002Z)\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012\u0001\u0018\t\u0003[uK!A\u0018\u0015\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005\u0005$\u0007CA\u0017c\u0013\t\u0019\u0007FA\u0002B]fDq!\u001a\u0005\u0002\u0002\u0003\u0007A,A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002QB\u0019\u0011\u000e\\1\u000e\u0003)T!a\u001b\u0015\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002nU\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\t\u00018\u000f\u0005\u0002.c&\u0011!\u000f\u000b\u0002\b\u0005>|G.Z1o\u0011\u001d)'\"!AA\u0002\u0005\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0011!K\u001e\u0005\bK.\t\t\u00111\u0001]\u0003!A\u0017m\u001d5D_\u0012,G#\u0001/\u0002\r\u0015\fX/\u00197t)\t\u00018\u0010C\u0004f\u001b\u0005\u0005\t\u0019A1\u0002#%sg-Z1tS\ndW\r\u0015:pE2,W\u000e\u0005\u00028\u001fM!qb`A\u0006!\u0019\t\t!a\u00026\u007f5\u0011\u00111\u0001\u0006\u0004\u0003\u000bA\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003\u0013\t\u0019AA\tBEN$(/Y2u\rVt7\r^5p]F\u0002B!!\u0004\u0002\u00145\u0011\u0011q\u0002\u0006\u0004\u0003#1\u0016AA5p\u0013\r\u0011\u0014q\u0002\u000b\u0002{\u0006AAo\\*ue&tw\rF\u0001S\u0003\u0015\t\u0007\u000f\u001d7z)\ry\u0014q\u0004\u0005\u0006gI\u0001\r!N\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t)#a\u000b\u0011\t5\n9#N\u0005\u0004\u0003SA#AB(qi&|g\u000e\u0003\u0005\u0002.M\t\t\u00111\u0001@\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003g\u00012aUA\u001b\u0013\r\t9\u0004\u0016\u0002\u0007\u001f\nTWm\u0019;"
)
public class InfeasibleProblem extends RuntimeException implements Product {
   private final LinearProgram.Problem prob;

   public static Option unapply(final InfeasibleProblem x$0) {
      return InfeasibleProblem$.MODULE$.unapply(x$0);
   }

   public static InfeasibleProblem apply(final LinearProgram.Problem prob) {
      return InfeasibleProblem$.MODULE$.apply(prob);
   }

   public static Function1 andThen(final Function1 g) {
      return InfeasibleProblem$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return InfeasibleProblem$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public LinearProgram.Problem prob() {
      return this.prob;
   }

   public InfeasibleProblem copy(final LinearProgram.Problem prob) {
      return new InfeasibleProblem(prob);
   }

   public LinearProgram.Problem copy$default$1() {
      return this.prob();
   }

   public String productPrefix() {
      return "InfeasibleProblem";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.prob();
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
      return x$1 instanceof InfeasibleProblem;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "prob";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public boolean equals(final Object x$1) {
      boolean var7;
      if (this != x$1) {
         label53: {
            boolean var2;
            if (x$1 instanceof InfeasibleProblem) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label36: {
                  label35: {
                     InfeasibleProblem var4 = (InfeasibleProblem)x$1;
                     LinearProgram.Problem var10000 = this.prob();
                     LinearProgram.Problem var5 = var4.prob();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label35;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label35;
                     }

                     if (var4.canEqual(this)) {
                        var7 = true;
                        break label36;
                     }
                  }

                  var7 = false;
               }

               if (var7) {
                  break label53;
               }
            }

            var7 = false;
            return var7;
         }
      }

      var7 = true;
      return var7;
   }

   public InfeasibleProblem(final LinearProgram.Problem prob) {
      this.prob = prob;
      Product.$init$(this);
   }
}
