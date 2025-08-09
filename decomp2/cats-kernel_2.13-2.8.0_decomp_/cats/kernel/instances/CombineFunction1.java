package cats.kernel.instances;

import cats.kernel.Semigroup;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.util.control.TailCalls;
import scala.util.control.TailCalls.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-h!B\u000f\u001f\u0005z!\u0003\u0002C'\u0001\u0005+\u0007I\u0011\u0001(\t\u0011=\u0003!\u0011#Q\u0001\n1B\u0001\u0002\u0015\u0001\u0003\u0016\u0004%\tA\u0014\u0005\t#\u0002\u0011\t\u0012)A\u0005Y!A!\u000b\u0001BK\u0002\u0013\u00051\u000b\u0003\u0005Y\u0001\tE\t\u0015!\u0003U\u0011\u0015I\u0006\u0001\"\u0001[\u0011\u0015\u0001\u0007\u0001\"\u0003b\u0011\u0015)\b\u0001\"\u0012w\u0011\u001dA\b!!A\u0005\u0002eD\u0011\"a\u0003\u0001#\u0003%\t!!\u0004\t\u0013\u0005%\u0002!%A\u0005\u0002\u0005-\u0002\"CA\u0019\u0001E\u0005I\u0011AA\u001a\u0011%\ti\u0004AA\u0001\n\u0003\ny\u0004C\u0005\u0002R\u0001\t\t\u0011\"\u0001\u0002T!I\u00111\f\u0001\u0002\u0002\u0013\u0005\u0011Q\f\u0005\n\u0003G\u0002\u0011\u0011!C!\u0003KB\u0011\"a\u001d\u0001\u0003\u0003%\t!!\u001e\t\u0013\u0005}\u0004!!A\u0005B\u0005\u0005\u0005\"CAC\u0001\u0005\u0005I\u0011IAD\u0011%\tI\tAA\u0001\n\u0003\nYi\u0002\u0006\u0002\u0010z\t\t\u0011#\u0001\u001f\u0003#3\u0011\"\b\u0010\u0002\u0002#\u0005a$a%\t\re;B\u0011AAP\u0011%\t\tkFA\u0001\n\u000b\n\u0019\u000b\u0003\u0005v/\u0005\u0005I\u0011QAS\u0011%\tilFA\u0001\n\u0003\u000by\fC\u0005\u0002b^\t\t\u0011\"\u0003\u0002d\n\u00012i\\7cS:,g)\u001e8di&|g.\r\u0006\u0003?\u0001\n\u0011\"\u001b8ti\u0006t7-Z:\u000b\u0005\u0005\u0012\u0013AB6fe:,GNC\u0001$\u0003\u0011\u0019\u0017\r^:\u0016\u0007\u0015\nDhE\u0003\u0001M1r\u0014\t\u0005\u0002(U5\t\u0001FC\u0001*\u0003\u0015\u00198-\u00197b\u0013\tY\u0003F\u0001\u0004B]f\u0014VM\u001a\t\u0005O5z3(\u0003\u0002/Q\tIa)\u001e8di&|g.\r\t\u0003aEb\u0001\u0001B\u00033\u0001\t\u0007AGA\u0001B\u0007\u0001\t\"!\u000e\u001d\u0011\u0005\u001d2\u0014BA\u001c)\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aJ\u001d\n\u0005iB#aA!osB\u0011\u0001\u0007\u0010\u0003\u0006{\u0001\u0011\r\u0001\u000e\u0002\u0002\u0005B\u0011qeP\u0005\u0003\u0001\"\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002C\u0015:\u00111\t\u0013\b\u0003\t\u001ek\u0011!\u0012\u0006\u0003\rN\na\u0001\u0010:p_Rt\u0014\"A\u0015\n\u0005%C\u0013a\u00029bG.\fw-Z\u0005\u0003\u00172\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!!\u0013\u0015\u0002\t1,g\r^\u000b\u0002Y\u0005)A.\u001a4uA\u0005)!/[4ii\u00061!/[4ii\u0002\nQa]3nS\n+\u0012\u0001\u0016\t\u0004+Z[T\"\u0001\u0011\n\u0005]\u0003#!C*f[&<'o\\;q\u0003\u0019\u0019X-\\5CA\u00051A(\u001b8jiz\"BaW/_?B!A\fA\u0018<\u001b\u0005q\u0002\"B'\b\u0001\u0004a\u0003\"\u0002)\b\u0001\u0004a\u0003\"\u0002*\b\u0001\u0004!\u0016\u0001B2bY2$2AY9t!\r\u0019gn\u000f\b\u0003I.t!!\u001a5\u000f\u0005\r3\u0017BA4)\u0003\u0011)H/\u001b7\n\u0005%T\u0017aB2p]R\u0014x\u000e\u001c\u0006\u0003O\"J!\u0001\\7\u0002\u0013Q\u000b\u0017\u000e\\\"bY2\u001c(BA5k\u0013\ty\u0007OA\u0004UC&d'+Z2\u000b\u00051l\u0007\"\u0002:\t\u0001\u0004a\u0013A\u00014o\u0011\u0015!\b\u00021\u00010\u0003\u0005\t\u0017!B1qa2LHCA\u001ex\u0011\u0015!\u0018\u00021\u00010\u0003\u0011\u0019w\u000e]=\u0016\u0007ilx\u0010F\u0004|\u0003\u0003\t)!a\u0002\u0011\tq\u0003AP \t\u0003au$QA\r\u0006C\u0002Q\u0002\"\u0001M@\u0005\u000buR!\u0019\u0001\u001b\t\u00115S\u0001\u0013!a\u0001\u0003\u0007\u0001BaJ\u0017}}\"A\u0001K\u0003I\u0001\u0002\u0004\t\u0019\u0001\u0003\u0005S\u0015A\u0005\t\u0019AA\u0005!\r)fK`\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0019\ty!!\n\u0002(U\u0011\u0011\u0011\u0003\u0016\u0004Y\u0005M1FAA\u000b!\u0011\t9\"!\t\u000e\u0005\u0005e!\u0002BA\u000e\u0003;\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005}\u0001&\u0001\u0006b]:|G/\u0019;j_:LA!a\t\u0002\u001a\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\u000bIZ!\u0019\u0001\u001b\u0005\u000buZ!\u0019\u0001\u001b\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU1\u0011qBA\u0017\u0003_!QA\r\u0007C\u0002Q\"Q!\u0010\u0007C\u0002Q\nabY8qs\u0012\"WMZ1vYR$3'\u0006\u0004\u00026\u0005e\u00121H\u000b\u0003\u0003oQ3\u0001VA\n\t\u0015\u0011TB1\u00015\t\u0015iTB1\u00015\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u0011\u0011\t\t\u0005\u0003\u0007\ni%\u0004\u0002\u0002F)!\u0011qIA%\u0003\u0011a\u0017M\\4\u000b\u0005\u0005-\u0013\u0001\u00026bm\u0006LA!a\u0014\u0002F\t11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!!\u0016\u0011\u0007\u001d\n9&C\u0002\u0002Z!\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$2\u0001OA0\u0011%\t\t\u0007EA\u0001\u0002\u0004\t)&A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003O\u0002R!!\u001b\u0002paj!!a\u001b\u000b\u0007\u00055\u0004&\u0001\u0006d_2dWm\u0019;j_:LA!!\u001d\u0002l\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t9(! \u0011\u0007\u001d\nI(C\u0002\u0002|!\u0012qAQ8pY\u0016\fg\u000e\u0003\u0005\u0002bI\t\t\u00111\u00019\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u0005\u0005\u00131\u0011\u0005\n\u0003C\u001a\u0012\u0011!a\u0001\u0003+\n\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003+\na!Z9vC2\u001cH\u0003BA<\u0003\u001bC\u0001\"!\u0019\u0016\u0003\u0003\u0005\r\u0001O\u0001\u0011\u0007>l'-\u001b8f\rVt7\r^5p]F\u0002\"\u0001X\f\u0014\t]1\u0013Q\u0013\t\u0005\u0003/\u000bi*\u0004\u0002\u0002\u001a*!\u00111TA%\u0003\tIw.C\u0002L\u00033#\"!!%\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!\u0011\u0016\r\u0005\u001d\u0016QVAY)!\tI+a-\u00028\u0006e\u0006C\u0002/\u0001\u0003W\u000by\u000bE\u00021\u0003[#QA\r\u000eC\u0002Q\u00022\u0001MAY\t\u0015i$D1\u00015\u0011\u0019i%\u00041\u0001\u00026B1q%LAV\u0003_Ca\u0001\u0015\u000eA\u0002\u0005U\u0006B\u0002*\u001b\u0001\u0004\tY\f\u0005\u0003V-\u0006=\u0016aB;oCB\u0004H._\u000b\u0007\u0003\u0003\f\u0019.a6\u0015\t\u0005\r\u00171\u001c\t\u0006O\u0005\u0015\u0017\u0011Z\u0005\u0004\u0003\u000fD#AB(qi&|g\u000eE\u0005(\u0003\u0017\fy-a4\u0002Z&\u0019\u0011Q\u001a\u0015\u0003\rQ+\b\u000f\\34!\u00199S&!5\u0002VB\u0019\u0001'a5\u0005\u000bIZ\"\u0019\u0001\u001b\u0011\u0007A\n9\u000eB\u0003>7\t\u0007A\u0007\u0005\u0003V-\u0006U\u0007\"CAo7\u0005\u0005\t\u0019AAp\u0003\rAH\u0005\r\t\u00079\u0002\t\t.!6\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005\u0015\b\u0003BA\"\u0003OLA!!;\u0002F\t1qJ\u00196fGR\u0004"
)
public final class CombineFunction1 implements Function1, Product, Serializable {
   private final Function1 left;
   private final Function1 right;
   private final Semigroup semiB;

   public static Option unapply(final CombineFunction1 x$0) {
      return CombineFunction1$.MODULE$.unapply(x$0);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean apply$mcZD$sp(final double v1) {
      return Function1.apply$mcZD$sp$(this, v1);
   }

   public double apply$mcDD$sp(final double v1) {
      return Function1.apply$mcDD$sp$(this, v1);
   }

   public float apply$mcFD$sp(final double v1) {
      return Function1.apply$mcFD$sp$(this, v1);
   }

   public int apply$mcID$sp(final double v1) {
      return Function1.apply$mcID$sp$(this, v1);
   }

   public long apply$mcJD$sp(final double v1) {
      return Function1.apply$mcJD$sp$(this, v1);
   }

   public void apply$mcVD$sp(final double v1) {
      Function1.apply$mcVD$sp$(this, v1);
   }

   public boolean apply$mcZF$sp(final float v1) {
      return Function1.apply$mcZF$sp$(this, v1);
   }

   public double apply$mcDF$sp(final float v1) {
      return Function1.apply$mcDF$sp$(this, v1);
   }

   public float apply$mcFF$sp(final float v1) {
      return Function1.apply$mcFF$sp$(this, v1);
   }

   public int apply$mcIF$sp(final float v1) {
      return Function1.apply$mcIF$sp$(this, v1);
   }

   public long apply$mcJF$sp(final float v1) {
      return Function1.apply$mcJF$sp$(this, v1);
   }

   public void apply$mcVF$sp(final float v1) {
      Function1.apply$mcVF$sp$(this, v1);
   }

   public boolean apply$mcZI$sp(final int v1) {
      return Function1.apply$mcZI$sp$(this, v1);
   }

   public double apply$mcDI$sp(final int v1) {
      return Function1.apply$mcDI$sp$(this, v1);
   }

   public float apply$mcFI$sp(final int v1) {
      return Function1.apply$mcFI$sp$(this, v1);
   }

   public int apply$mcII$sp(final int v1) {
      return Function1.apply$mcII$sp$(this, v1);
   }

   public long apply$mcJI$sp(final int v1) {
      return Function1.apply$mcJI$sp$(this, v1);
   }

   public void apply$mcVI$sp(final int v1) {
      Function1.apply$mcVI$sp$(this, v1);
   }

   public boolean apply$mcZJ$sp(final long v1) {
      return Function1.apply$mcZJ$sp$(this, v1);
   }

   public double apply$mcDJ$sp(final long v1) {
      return Function1.apply$mcDJ$sp$(this, v1);
   }

   public float apply$mcFJ$sp(final long v1) {
      return Function1.apply$mcFJ$sp$(this, v1);
   }

   public int apply$mcIJ$sp(final long v1) {
      return Function1.apply$mcIJ$sp$(this, v1);
   }

   public long apply$mcJJ$sp(final long v1) {
      return Function1.apply$mcJJ$sp$(this, v1);
   }

   public void apply$mcVJ$sp(final long v1) {
      Function1.apply$mcVJ$sp$(this, v1);
   }

   public Function1 compose(final Function1 g) {
      return Function1.compose$(this, g);
   }

   public Function1 andThen(final Function1 g) {
      return Function1.andThen$(this, g);
   }

   public String toString() {
      return Function1.toString$(this);
   }

   public Function1 left() {
      return this.left;
   }

   public Function1 right() {
      return this.right;
   }

   public Semigroup semiB() {
      return this.semiB;
   }

   private TailCalls.TailRec call(final Function1 fn, final Object a) {
      TailCalls.TailRec var3;
      if (fn instanceof CombineFunction1) {
         CombineFunction1 var5 = (CombineFunction1)fn;
         var3 = .MODULE$.tailcall(() -> this.call(var5.left(), a)).flatMap((lb) -> .MODULE$.tailcall(() -> this.call(var5.right(), a)).map((rb) -> var5.semiB().combine(lb, rb)));
      } else {
         var3 = .MODULE$.done(fn.apply(a));
      }

      return var3;
   }

   public final Object apply(final Object a) {
      return this.call(this, a).result();
   }

   public CombineFunction1 copy(final Function1 left, final Function1 right, final Semigroup semiB) {
      return new CombineFunction1(left, right, semiB);
   }

   public Function1 copy$default$1() {
      return this.left();
   }

   public Function1 copy$default$2() {
      return this.right();
   }

   public Semigroup copy$default$3() {
      return this.semiB();
   }

   public String productPrefix() {
      return "CombineFunction1";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.left();
            break;
         case 1:
            var10000 = this.right();
            break;
         case 2:
            var10000 = this.semiB();
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof CombineFunction1;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "left";
            break;
         case 1:
            var10000 = "right";
            break;
         case 2:
            var10000 = "semiB";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
   }

   public boolean equals(final Object x$1) {
      boolean var11;
      if (this != x$1) {
         label69: {
            boolean var2;
            if (x$1 instanceof CombineFunction1) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label51: {
                  label50: {
                     label60: {
                        CombineFunction1 var4 = (CombineFunction1)x$1;
                        Function1 var10000 = this.left();
                        Function1 var5 = var4.left();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label60;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label60;
                        }

                        var10000 = this.right();
                        Function1 var6 = var4.right();
                        if (var10000 == null) {
                           if (var6 != null) {
                              break label60;
                           }
                        } else if (!var10000.equals(var6)) {
                           break label60;
                        }

                        Semigroup var9 = this.semiB();
                        Semigroup var7 = var4.semiB();
                        if (var9 == null) {
                           if (var7 == null) {
                              break label50;
                           }
                        } else if (var9.equals(var7)) {
                           break label50;
                        }
                     }

                     var11 = false;
                     break label51;
                  }

                  var11 = true;
               }

               if (var11) {
                  break label69;
               }
            }

            var11 = false;
            return var11;
         }
      }

      var11 = true;
      return var11;
   }

   public CombineFunction1(final Function1 left, final Function1 right, final Semigroup semiB) {
      this.left = left;
      this.right = right;
      this.semiB = semiB;
      Function1.$init$(this);
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
