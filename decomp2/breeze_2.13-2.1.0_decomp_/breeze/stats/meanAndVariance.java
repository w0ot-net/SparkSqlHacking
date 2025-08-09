package breeze.stats;

import breeze.generic.UFunc;
import breeze.linalg.support.CanTraverseValues;
import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005x!\u0002\u0013&\u0011\u0003Qc!\u0002\u0017&\u0011\u0003i\u0003\"\u0002\u001e\u0002\t\u0003Y\u0004\"\u0002\u001f\u0002\t\u0007i\u0004bBAF\u0003\u0011\r\u0011Q\u0012\u0004\u0005\u001f\u0006\u0001\u0005\u000b\u0003\u0005a\u000b\tU\r\u0011\"\u0001b\u0011!)WA!E!\u0002\u0013\u0011\u0007\u0002\u00034\u0006\u0005+\u0007I\u0011A1\t\u0011\u001d,!\u0011#Q\u0001\n\tD\u0001\u0002[\u0003\u0003\u0016\u0004%\t!\u001b\u0005\t[\u0016\u0011\t\u0012)A\u0005U\")!(\u0002C\u0001]\")!/\u0002C\u0001C\")1/\u0002C\u0001C\")A/\u0002C\u0001k\"9\u00010BA\u0001\n\u0003I\bbB?\u0006#\u0003%\tA \u0005\t\u0003')\u0011\u0013!C\u0001}\"I\u0011QC\u0003\u0012\u0002\u0013\u0005\u0011q\u0003\u0005\n\u00037)\u0011\u0011!C!\u0003;A\u0011\"a\f\u0006\u0003\u0003%\t!!\r\t\u0013\u0005eR!!A\u0005\u0002\u0005m\u0002\"CA!\u000b\u0005\u0005I\u0011IA\"\u0011%\t\t&BA\u0001\n\u0003\t\u0019\u0006C\u0005\u0002^\u0015\t\t\u0011\"\u0011\u0002`!I\u00111M\u0003\u0002\u0002\u0013\u0005\u0013Q\r\u0005\n\u0003O*\u0011\u0011!C!\u0003SB\u0011\"a\u001b\u0006\u0003\u0003%\t%!\u001c\b\u0013\u0005m\u0015!!A\t\u0002\u0005ue\u0001C(\u0002\u0003\u0003E\t!a(\t\rirB\u0011AA\\\u0011%\t9GHA\u0001\n\u000b\nI\u0007C\u0005\u0002:z\t\t\u0011\"!\u0002<\"I\u00111\u0019\u0010\u0002\u0002\u0013\u0005\u0015Q\u0019\u0005\n\u0003/t\u0012\u0011!C\u0005\u00033\fq\"\\3b]\u0006sGMV1sS\u0006t7-\u001a\u0006\u0003M\u001d\nQa\u001d;biNT\u0011\u0001K\u0001\u0007EJ,WM_3\u0004\u0001A\u00111&A\u0007\u0002K\tyQ.Z1o\u0003:$g+\u0019:jC:\u001cWmE\u0002\u0002]Q\u0002\"a\f\u001a\u000e\u0003AR\u0011!M\u0001\u0006g\u000e\fG.Y\u0005\u0003gA\u0012a!\u00118z%\u00164\u0007CA\u001b9\u001b\u00051$BA\u001c(\u0003\u001d9WM\\3sS\u000eL!!\u000f\u001c\u0003\u000bU3UO\\2\u0002\rqJg.\u001b;?)\u0005Q\u0013\u0001\u0004:fIV\u001cWm\u0018$m_\u0006$XC\u0001 F)\ry\u0014\u0011\u000f\t\u0005\u0001\u0006\u001be*D\u0001\u0002\u0013\t\u0011\u0005H\u0001\u0003J[Bd\u0007C\u0001#F\u0019\u0001!QAR\u0002C\u0002\u001d\u0013\u0011\u0001V\t\u0003\u0011.\u0003\"aL%\n\u0005)\u0003$a\u0002(pi\"Lgn\u001a\t\u0003_1K!!\u0014\u0019\u0003\u0007\u0005s\u0017\u0010\u0005\u0002A\u000b\tyQ*Z1o\u0003:$g+\u0019:jC:\u001cWm\u0005\u0003\u0006]E#\u0006CA\u0018S\u0013\t\u0019\u0006GA\u0004Qe>$Wo\u0019;\u0011\u0005UkfB\u0001,\\\u001d\t9&,D\u0001Y\u0015\tI\u0016&\u0001\u0004=e>|GOP\u0005\u0002c%\u0011A\fM\u0001\ba\u0006\u001c7.Y4f\u0013\tqvL\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002]a\u0005!Q.Z1o+\u0005\u0011\u0007CA\u0018d\u0013\t!\u0007G\u0001\u0004E_V\u0014G.Z\u0001\u0006[\u0016\fg\u000eI\u0001\tm\u0006\u0014\u0018.\u00198dK\u0006Ia/\u0019:jC:\u001cW\rI\u0001\u0006G>,h\u000e^\u000b\u0002UB\u0011qf[\u0005\u0003YB\u0012A\u0001T8oO\u000611m\\;oi\u0002\"BAT8qc\")\u0001\r\u0004a\u0001E\")a\r\u0004a\u0001E\")\u0001\u000e\u0004a\u0001U\u000611\u000f\u001e3EKZ\f!\u0003]8qk2\fG/[8o-\u0006\u0014\u0018.\u00198dK\u0006)A\u0005\u001d7vgR\u0011aJ\u001e\u0005\u0006o>\u0001\rAT\u0001\u0006_RDWM]\u0001\u0005G>\u0004\u0018\u0010\u0006\u0003Ound\bb\u00021\u0011!\u0003\u0005\rA\u0019\u0005\bMB\u0001\n\u00111\u0001c\u0011\u001dA\u0007\u0003%AA\u0002)\fabY8qs\u0012\"WMZ1vYR$\u0013'F\u0001\u0000U\r\u0011\u0017\u0011A\u0016\u0003\u0003\u0007\u0001B!!\u0002\u0002\u00105\u0011\u0011q\u0001\u0006\u0005\u0003\u0013\tY!A\u0005v]\u000eDWmY6fI*\u0019\u0011Q\u0002\u0019\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002\u0012\u0005\u001d!!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012\u0014AD2paf$C-\u001a4bk2$HeM\u000b\u0003\u00033Q3A[A\u0001\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u0011q\u0004\t\u0005\u0003C\tY#\u0004\u0002\u0002$)!\u0011QEA\u0014\u0003\u0011a\u0017M\\4\u000b\u0005\u0005%\u0012\u0001\u00026bm\u0006LA!!\f\u0002$\t11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!a\r\u0011\u0007=\n)$C\u0002\u00028A\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$2aSA\u001f\u0011%\tyDFA\u0001\u0002\u0004\t\u0019$A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003\u000b\u0002R!a\u0012\u0002N-k!!!\u0013\u000b\u0007\u0005-\u0003'\u0001\u0006d_2dWm\u0019;j_:LA!a\u0014\u0002J\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t)&a\u0017\u0011\u0007=\n9&C\u0002\u0002ZA\u0012qAQ8pY\u0016\fg\u000e\u0003\u0005\u0002@a\t\t\u00111\u0001L\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u0005}\u0011\u0011\r\u0005\n\u0003\u007fI\u0012\u0011!a\u0001\u0003g\t\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003g\t\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003?\ta!Z9vC2\u001cH\u0003BA+\u0003_B\u0001\"a\u0010\u001d\u0003\u0003\u0005\ra\u0013\u0005\b\u0003g\u001a\u00019AA;\u0003\u0011IG/\u001a:\u0011\u000f\u0005]\u0014\u0011Q\"\u0002\u00066\u0011\u0011\u0011\u0010\u0006\u0005\u0003w\ni(A\u0004tkB\u0004xN\u001d;\u000b\u0007\u0005}t%\u0001\u0004mS:\fGnZ\u0005\u0005\u0003\u0007\u000bIHA\tDC:$&/\u0019<feN,g+\u00197vKN\u00042aLAD\u0013\r\tI\t\r\u0002\u0006\r2|\u0017\r^\u0001\u000ee\u0016$WoY3`\t>,(\r\\3\u0016\t\u0005=\u0015Q\u0013\u000b\u0005\u0003#\u000b9\nE\u0003A\u0003\u0006Me\nE\u0002E\u0003+#QA\u0012\u0003C\u0002\u001dCq!a\u001d\u0005\u0001\b\tI\nE\u0004\u0002x\u0005\u0005\u00151\u00132\u0002\u001f5+\u0017M\\!oIZ\u000b'/[1oG\u0016\u0004\"\u0001\u0011\u0010\u0014\u000by\t\t+!,\u0011\u0011\u0005\r\u0016\u0011\u00162cU:k!!!*\u000b\u0007\u0005\u001d\u0006'A\u0004sk:$\u0018.\\3\n\t\u0005-\u0016Q\u0015\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u001c\u0004\u0003BAX\u0003kk!!!-\u000b\t\u0005M\u0016qE\u0001\u0003S>L1AXAY)\t\ti*A\u0003baBd\u0017\u0010F\u0004O\u0003{\u000by,!1\t\u000b\u0001\f\u0003\u0019\u00012\t\u000b\u0019\f\u0003\u0019\u00012\t\u000b!\f\u0003\u0019\u00016\u0002\u000fUt\u0017\r\u001d9msR!\u0011qYAj!\u0015y\u0013\u0011ZAg\u0013\r\tY\r\r\u0002\u0007\u001fB$\u0018n\u001c8\u0011\r=\nyM\u00192k\u0013\r\t\t\u000e\r\u0002\u0007)V\u0004H.Z\u001a\t\u0011\u0005U'%!AA\u00029\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\tY\u000e\u0005\u0003\u0002\"\u0005u\u0017\u0002BAp\u0003G\u0011aa\u00142kK\u000e$\b"
)
public final class meanAndVariance {
   public static UFunc.UImpl reduce_Double(final CanTraverseValues iter) {
      return meanAndVariance$.MODULE$.reduce_Double(iter);
   }

   public static UFunc.UImpl reduce_Float(final CanTraverseValues iter) {
      return meanAndVariance$.MODULE$.reduce_Float(iter);
   }

   public static Object withSink(final Object s) {
      return meanAndVariance$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return meanAndVariance$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return meanAndVariance$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return meanAndVariance$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return meanAndVariance$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return meanAndVariance$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return meanAndVariance$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return meanAndVariance$.MODULE$.apply(v, impl);
   }

   public static class MeanAndVariance implements Product, Serializable {
      private final double mean;
      private final double variance;
      private final long count;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public double mean() {
         return this.mean;
      }

      public double variance() {
         return this.variance;
      }

      public long count() {
         return this.count;
      }

      public double stdDev() {
         return .MODULE$.sqrt(this.variance());
      }

      public double populationVariance() {
         return this.count() == 0L ? (double)0.0F : (double)(this.count() - 1L) / (double)this.count() * this.variance();
      }

      public MeanAndVariance $plus(final MeanAndVariance other) {
         double d = other.mean() - this.mean();
         double newMean = this.mean() + d * (double)other.count() / (double)(other.count() + this.count());
         double m2a = this.variance() * (double)(this.count() - 1L);
         double m2b = other.variance() * (double)(other.count() - 1L);
         double m2x = m2a + m2b + d * d * (double)(other.count() * this.count()) / (double)(other.count() + this.count());
         double newVariance = m2x / (double)(other.count() + this.count() - 1L);
         return new MeanAndVariance(newMean, newVariance, this.count() + other.count());
      }

      public MeanAndVariance copy(final double mean, final double variance, final long count) {
         return new MeanAndVariance(mean, variance, count);
      }

      public double copy$default$1() {
         return this.mean();
      }

      public double copy$default$2() {
         return this.variance();
      }

      public long copy$default$3() {
         return this.count();
      }

      public String productPrefix() {
         return "MeanAndVariance";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = BoxesRunTime.boxToDouble(this.mean());
               break;
            case 1:
               var10000 = BoxesRunTime.boxToDouble(this.variance());
               break;
            case 2:
               var10000 = BoxesRunTime.boxToLong(this.count());
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
         return x$1 instanceof MeanAndVariance;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "mean";
               break;
            case 1:
               var10000 = "variance";
               break;
            case 2:
               var10000 = "count";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.doubleHash(this.mean()));
         var1 = Statics.mix(var1, Statics.doubleHash(this.variance()));
         var1 = Statics.mix(var1, Statics.longHash(this.count()));
         return Statics.finalizeHash(var1, 3);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label53: {
               boolean var2;
               if (x$1 instanceof MeanAndVariance) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  MeanAndVariance var4 = (MeanAndVariance)x$1;
                  if (this.mean() == var4.mean() && this.variance() == var4.variance() && this.count() == var4.count() && var4.canEqual(this)) {
                     break label53;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public MeanAndVariance(final double mean, final double variance, final long count) {
         this.mean = mean;
         this.variance = variance;
         this.count = count;
         Product.$init$(this);
      }
   }

   public static class MeanAndVariance$ extends AbstractFunction3 implements Serializable {
      public static final MeanAndVariance$ MODULE$ = new MeanAndVariance$();

      public final String toString() {
         return "MeanAndVariance";
      }

      public MeanAndVariance apply(final double mean, final double variance, final long count) {
         return new MeanAndVariance(mean, variance, count);
      }

      public Option unapply(final MeanAndVariance x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToDouble(x$0.mean()), BoxesRunTime.boxToDouble(x$0.variance()), BoxesRunTime.boxToLong(x$0.count()))));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(MeanAndVariance$.class);
      }
   }
}
