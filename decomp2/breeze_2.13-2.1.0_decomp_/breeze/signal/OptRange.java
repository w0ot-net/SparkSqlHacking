package breeze.signal;

import breeze.util.Opt;
import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.collection.Iterator;
import scala.collection.immutable.Range;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%e!B\u0014)\u0003\u0003i\u0003\"\u0002\u001b\u0001\t\u0003)t!\u0002\u001d)\u0011\u0003Id!B\u0014)\u0011\u0003Q\u0004\"\u0002\u001b\u0004\t\u0003\tu!\u0002\"\u0004\u0011\u0003\u001be!B#\u0004\u0011\u00033\u0005\"\u0002\u001b\u0007\t\u00031\u0006\"B,\u0007\t\u0003B\u0006bB1\u0007\u0003\u0003%\tE\u0019\u0005\bG\u001a\t\t\u0011\"\u0001e\u0011\u001dAg!!A\u0005\u0002%Dqa\u001c\u0004\u0002\u0002\u0013\u0005\u0003\u000fC\u0004x\r\u0005\u0005I\u0011\u0001=\t\u000fu4\u0011\u0011!C!}\"AqPBA\u0001\n\u0013\t\tA\u0002\u0004\u0002\n\r\u0001\u00151\u0002\u0005\u000b\u0003\u001b\u0001\"Q3A\u0005\u0002\u0005=\u0001BCA\f!\tE\t\u0015!\u0003\u0002\u0012!1A\u0007\u0005C\u0001\u00033AQa\u0016\t\u0005BaC\u0011\"a\b\u0011\u0003\u0003%\t!!\t\t\u0013\u0005\u0015\u0002#%A\u0005\u0002\u0005\u001d\u0002bB1\u0011\u0003\u0003%\tE\u0019\u0005\bGB\t\t\u0011\"\u0001e\u0011!A\u0007#!A\u0005\u0002\u0005u\u0002bB8\u0011\u0003\u0003%\t\u0005\u001d\u0005\toB\t\t\u0011\"\u0001\u0002B!I\u0011Q\t\t\u0002\u0002\u0013\u0005\u0013q\t\u0005\b{B\t\t\u0011\"\u0011\u007f\u0011%\tY\u0005EA\u0001\n\u0003\nieB\u0005\u0002R\r\t\t\u0011#\u0001\u0002T\u0019I\u0011\u0011B\u0002\u0002\u0002#\u0005\u0011Q\u000b\u0005\u0007i\u0001\"\t!!\u001c\t\u000f]\u0003\u0013\u0011!C#1\"I\u0011q\u000e\u0011\u0002\u0002\u0013\u0005\u0015\u0011\u000f\u0005\n\u0003k\u0002\u0013\u0011!CA\u0003oB\u0001b \u0011\u0002\u0002\u0013%\u0011\u0011\u0001\u0005\b\u0003\u0007\u001bA1AAC\u0005!y\u0005\u000f\u001e*b]\u001e,'BA\u0015+\u0003\u0019\u0019\u0018n\u001a8bY*\t1&\u0001\u0004ce\u0016,'0Z\u0002\u0001'\t\u0001a\u0006\u0005\u00020e5\t\u0001G\u0003\u00022U\u0005!Q\u000f^5m\u0013\t\u0019\u0004GA\u0002PaR\fa\u0001P5oSRtD#\u0001\u001c\u0011\u0005]\u0002Q\"\u0001\u0015\u0002\u0011=\u0003HOU1oO\u0016\u0004\"aN\u0002\u0014\u0005\rY\u0004C\u0001\u001f@\u001b\u0005i$\"\u0001 \u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0001k$AB!osJ+g\rF\u0001:\u0003\r\tE\u000e\u001c\t\u0003\t\u001ai\u0011a\u0001\u0002\u0004\u00032d7\u0003\u0002\u00047\u000f*\u0003\"\u0001\u0010%\n\u0005%k$a\u0002)s_\u0012,8\r\u001e\t\u0003\u0017Ns!\u0001T)\u000f\u00055\u0003V\"\u0001(\u000b\u0005=c\u0013A\u0002\u001fs_>$h(C\u0001?\u0013\t\u0011V(A\u0004qC\u000e\\\u0017mZ3\n\u0005Q+&\u0001D*fe&\fG.\u001b>bE2,'B\u0001*>)\u0005\u0019\u0015\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003e\u0003\"AW0\u000e\u0003mS!\u0001X/\u0002\t1\fgn\u001a\u0006\u0002=\u0006!!.\u0019<b\u0013\t\u00017L\u0001\u0004TiJLgnZ\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003e\u000bA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012!\u001a\t\u0003y\u0019L!aZ\u001f\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005)l\u0007C\u0001\u001fl\u0013\taWHA\u0002B]fDqA\\\u0006\u0002\u0002\u0003\u0007Q-A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002cB\u0019!/\u001e6\u000e\u0003MT!\u0001^\u001f\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002wg\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\tIH\u0010\u0005\u0002=u&\u001110\u0010\u0002\b\u0005>|G.Z1o\u0011\u001dqW\"!AA\u0002)\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002K\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\u0001\t\u00045\u0006\u0015\u0011bAA\u00047\n1qJ\u00196fGR\u0014\u0001BU1oO\u0016|\u0005\u000f^\n\u0005!Y:%*A\u0001s+\t\t\t\u0002E\u0002L\u0003'I1!!\u0006V\u0005\u0015\u0011\u0016M\\4f\u0003\t\u0011\b\u0005\u0006\u0003\u0002\u001c\u0005u\u0001C\u0001#\u0011\u0011\u001d\tia\u0005a\u0001\u0003#\tAaY8qsR!\u00111DA\u0012\u0011%\ti!\u0006I\u0001\u0002\u0004\t\t\"\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005%\"\u0006BA\t\u0003WY#!!\f\u0011\t\u0005=\u0012\u0011H\u0007\u0003\u0003cQA!a\r\u00026\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003oi\u0014AC1o]>$\u0018\r^5p]&!\u00111HA\u0019\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u000b\u0004U\u0006}\u0002b\u00028\u001a\u0003\u0003\u0005\r!\u001a\u000b\u0004s\u0006\r\u0003b\u00028\u001c\u0003\u0003\u0005\rA[\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002Z\u0003\u0013BqA\u001c\u000f\u0002\u0002\u0003\u0007Q-\u0001\u0004fcV\fGn\u001d\u000b\u0004s\u0006=\u0003b\u00028\u001f\u0003\u0003\u0005\rA[\u0001\t%\u0006tw-Z(qiB\u0011A\tI\n\u0006A\u0005]\u00131\r\t\t\u00033\ny&!\u0005\u0002\u001c5\u0011\u00111\f\u0006\u0004\u0003;j\u0014a\u0002:v]RLW.Z\u0005\u0005\u0003C\nYFA\tBEN$(/Y2u\rVt7\r^5p]F\u0002B!!\u001a\u0002l5\u0011\u0011q\r\u0006\u0004\u0003Sj\u0016AA5p\u0013\r!\u0016q\r\u000b\u0003\u0003'\nQ!\u00199qYf$B!a\u0007\u0002t!9\u0011QB\u0012A\u0002\u0005E\u0011aB;oCB\u0004H.\u001f\u000b\u0005\u0003s\ny\bE\u0003=\u0003w\n\t\"C\u0002\u0002~u\u0012aa\u00149uS>t\u0007\"CAAI\u0005\u0005\t\u0019AA\u000e\u0003\rAH\u0005M\u0001\u0010e\u0006tw-\u001a+p%\u0006tw-Z(qiR!\u00111DAD\u0011\u001d\tiA\na\u0001\u0003#\u0001"
)
public abstract class OptRange extends Opt {
   public static RangeOpt rangeToRangeOpt(final Range r) {
      return OptRange$.MODULE$.rangeToRangeOpt(r);
   }

   public static class All$ extends OptRange implements Product, Serializable {
      public static final All$ MODULE$ = new All$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String toString() {
         return "OptRange.All";
      }

      public String productPrefix() {
         return "All";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         Object var2 = Statics.ioobe(x$1);
         return var2;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof All$;
      }

      public int hashCode() {
         return 65921;
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(All$.class);
      }
   }

   public static class RangeOpt extends OptRange implements Product, Serializable {
      private final Range r;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Range r() {
         return this.r;
      }

      public String toString() {
         return (new StringBuilder(38)).append("OptRange.RangeOpt( ").append(this.r().start()).append(", ").append(this.r().end()).append(", ").append(this.r().step()).append("), isInclusive=").append(this.r().isInclusive()).toString();
      }

      public RangeOpt copy(final Range r) {
         return new RangeOpt(r);
      }

      public Range copy$default$1() {
         return this.r();
      }

      public String productPrefix() {
         return "RangeOpt";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.r();
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
         return x$1 instanceof RangeOpt;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "r";
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
               if (x$1 instanceof RangeOpt) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label36: {
                     label35: {
                        RangeOpt var4 = (RangeOpt)x$1;
                        Range var10000 = this.r();
                        Range var5 = var4.r();
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

      public RangeOpt(final Range r) {
         this.r = r;
         Product.$init$(this);
      }
   }

   public static class RangeOpt$ extends AbstractFunction1 implements Serializable {
      public static final RangeOpt$ MODULE$ = new RangeOpt$();

      public final String toString() {
         return "RangeOpt";
      }

      public RangeOpt apply(final Range r) {
         return new RangeOpt(r);
      }

      public Option unapply(final RangeOpt x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.r()));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(RangeOpt$.class);
      }
   }
}
