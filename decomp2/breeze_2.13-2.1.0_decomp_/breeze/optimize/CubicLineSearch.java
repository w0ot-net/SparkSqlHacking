package breeze.optimize;

import breeze.util.LazyLogger;
import breeze.util.SerializableLogging;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ue!\u0002\u0011\"\u0003\u00031\u0003\"B\u001c\u0001\t\u0003Ad\u0001\u0002\u001e\u0001\u0001nB\u0001b\u0013\u0002\u0003\u0016\u0004%\t\u0001\u0014\u0005\t!\n\u0011\t\u0012)A\u0005\u001b\"A\u0011K\u0001BK\u0002\u0013\u0005A\n\u0003\u0005S\u0005\tE\t\u0015!\u0003N\u0011!\u0019&A!f\u0001\n\u0003a\u0005\u0002\u0003+\u0003\u0005#\u0005\u000b\u0011B'\t\u000b]\u0012A\u0011A+\t\u000fm\u0013\u0011\u0011!C\u00019\"9\u0001MAI\u0001\n\u0003\t\u0007b\u00027\u0003#\u0003%\t!\u0019\u0005\b[\n\t\n\u0011\"\u0001b\u0011\u001dq'!!A\u0005B=Dq\u0001\u001f\u0002\u0002\u0002\u0013\u0005\u0011\u0010C\u0004~\u0005\u0005\u0005I\u0011\u0001@\t\u0013\u0005%!!!A\u0005B\u0005-\u0001\"CA\r\u0005\u0005\u0005I\u0011AA\u000e\u0011%\t)CAA\u0001\n\u0003\n9\u0003C\u0005\u0002,\t\t\t\u0011\"\u0011\u0002.!I\u0011q\u0006\u0002\u0002\u0002\u0013\u0005\u0013\u0011\u0007\u0005\n\u0003g\u0011\u0011\u0011!C!\u0003k9\u0011\"!\u000f\u0001\u0003\u0003E\t!a\u000f\u0007\u0011i\u0002\u0011\u0011!E\u0001\u0003{Aaa\u000e\r\u0005\u0002\u0005U\u0003\"CA\u00181\u0005\u0005IQIA\u0019\u0011%\t9\u0006GA\u0001\n\u0003\u000bI\u0006C\u0005\u0002ba\t\t\u0011\"!\u0002d!9\u0011Q\u000f\u0001\u0007\u0002\u0005]\u0004\u0002CAD\u0001E\u0005I\u0011A1\t\u000f\u0005%\u0005\u0001\"\u0001\u0002\f\ny1)\u001e2jG2Kg.Z*fCJ\u001c\u0007N\u0003\u0002#G\u0005Aq\u000e\u001d;j[&TXMC\u0001%\u0003\u0019\u0011'/Z3{K\u000e\u00011\u0003\u0002\u0001([M\u0002\"\u0001K\u0016\u000e\u0003%R\u0011AK\u0001\u0006g\u000e\fG.Y\u0005\u0003Y%\u0012a!\u00118z%\u00164\u0007C\u0001\u00182\u001b\u0005y#B\u0001\u0019$\u0003\u0011)H/\u001b7\n\u0005Iz#aE*fe&\fG.\u001b>bE2,Gj\\4hS:<\u0007C\u0001\u001b6\u001b\u0005\t\u0013B\u0001\u001c\"\u0005Qi\u0015N\\5nSjLgn\u001a'j]\u0016\u001cV-\u0019:dQ\u00061A(\u001b8jiz\"\u0012!\u000f\t\u0003i\u0001\u0011qA\u0011:bG.,Go\u0005\u0003\u0003Oqz\u0004C\u0001\u0015>\u0013\tq\u0014FA\u0004Qe>$Wo\u0019;\u0011\u0005\u0001CeBA!G\u001d\t\u0011U)D\u0001D\u0015\t!U%\u0001\u0004=e>|GOP\u0005\u0002U%\u0011q)K\u0001\ba\u0006\u001c7.Y4f\u0013\tI%J\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002HS\u0005\tA/F\u0001N!\tAc*\u0003\u0002PS\t1Ai\\;cY\u0016\f!\u0001\u001e\u0011\u0002\u0005\u0011$\u0017a\u00013eA\u0005!aM^1m\u0003\u00151g/\u00197!)\u00111\u0006,\u0017.\u0011\u0005]\u0013Q\"\u0001\u0001\t\u000b-K\u0001\u0019A'\t\u000bEK\u0001\u0019A'\t\u000bMK\u0001\u0019A'\u0002\t\r|\u0007/\u001f\u000b\u0005-vsv\fC\u0004L\u0015A\u0005\t\u0019A'\t\u000fES\u0001\u0013!a\u0001\u001b\"91K\u0003I\u0001\u0002\u0004i\u0015AD2paf$C-\u001a4bk2$H%M\u000b\u0002E*\u0012QjY\u0016\u0002IB\u0011QM[\u0007\u0002M*\u0011q\r[\u0001\nk:\u001c\u0007.Z2lK\u0012T!![\u0015\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002lM\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%e\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\u001a\u0014!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001q!\t\th/D\u0001s\u0015\t\u0019H/\u0001\u0003mC:<'\"A;\u0002\t)\fg/Y\u0005\u0003oJ\u0014aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLX#\u0001>\u0011\u0005!Z\u0018B\u0001?*\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\ry\u0018Q\u0001\t\u0004Q\u0005\u0005\u0011bAA\u0002S\t\u0019\u0011I\\=\t\u0011\u0005\u001d\u0001#!AA\u0002i\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA\u0007!\u0015\ty!!\u0006\u0000\u001b\t\t\tBC\u0002\u0002\u0014%\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\t9\"!\u0005\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003;\t\u0019\u0003E\u0002)\u0003?I1!!\t*\u0005\u001d\u0011un\u001c7fC:D\u0001\"a\u0002\u0013\u0003\u0003\u0005\ra`\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002q\u0003SA\u0001\"a\u0002\u0014\u0003\u0003\u0005\rA_\u0001\tQ\u0006\u001c\bnQ8eKR\t!0\u0001\u0005u_N#(/\u001b8h)\u0005\u0001\u0018AB3rk\u0006d7\u000f\u0006\u0003\u0002\u001e\u0005]\u0002\u0002CA\u0004-\u0005\u0005\t\u0019A@\u0002\u000f\t\u0013\u0018mY6fiB\u0011q\u000bG\n\u00061\u0005}\u00121\n\t\t\u0003\u0003\n9%T'N-6\u0011\u00111\t\u0006\u0004\u0003\u000bJ\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003\u0013\n\u0019EA\tBEN$(/Y2u\rVt7\r^5p]N\u0002B!!\u0014\u0002T5\u0011\u0011q\n\u0006\u0004\u0003#\"\u0018AA5p\u0013\rI\u0015q\n\u000b\u0003\u0003w\tQ!\u00199qYf$rAVA.\u0003;\ny\u0006C\u0003L7\u0001\u0007Q\nC\u0003R7\u0001\u0007Q\nC\u0003T7\u0001\u0007Q*A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005\u0015\u0014\u0011\u000f\t\u0006Q\u0005\u001d\u00141N\u0005\u0004\u0003SJ#AB(qi&|g\u000e\u0005\u0004)\u0003[jU*T\u0005\u0004\u0003_J#A\u0002+va2,7\u0007\u0003\u0005\u0002tq\t\t\u00111\u0001W\u0003\rAH\u0005M\u0001\t[&t\u0017.\\5{KR)Q*!\u001f\u0002\u0004\"9\u00111P\u000fA\u0002\u0005u\u0014!\u00014\u0011\tQ\ny(T\u0005\u0004\u0003\u0003\u000b#\u0001\u0004#jM\u001a4UO\\2uS>t\u0007\u0002CAC;A\u0005\t\u0019A'\u0002\t%t\u0017\u000e^\u0001\u0013[&t\u0017.\\5{K\u0012\"WMZ1vYR$#'\u0001\u0004j]R,'\u000f\u001d\u000b\u0006\u001b\u00065\u0015\u0011\u0013\u0005\u0007\u0003\u001f{\u0002\u0019\u0001,\u0002\u00031Da!a% \u0001\u00041\u0016!\u0001:"
)
public abstract class CubicLineSearch implements SerializableLogging, MinimizingLineSearch {
   private volatile Bracket$ Bracket$module;
   private transient volatile LazyLogger breeze$util$SerializableLogging$$_the_logger;

   public LazyLogger logger() {
      return SerializableLogging.logger$(this);
   }

   public Bracket$ Bracket() {
      if (this.Bracket$module == null) {
         this.Bracket$lzycompute$1();
      }

      return this.Bracket$module;
   }

   public LazyLogger breeze$util$SerializableLogging$$_the_logger() {
      return this.breeze$util$SerializableLogging$$_the_logger;
   }

   public void breeze$util$SerializableLogging$$_the_logger_$eq(final LazyLogger x$1) {
      this.breeze$util$SerializableLogging$$_the_logger = x$1;
   }

   public abstract double minimize(final DiffFunction f, final double init);

   public double minimize$default$2() {
      return (double)1.0F;
   }

   public double interp(final Bracket l, final Bracket r) {
      double d1 = l.dd() + r.dd() - (double)3 * (l.fval() - r.fval()) / (l.t() - r.t());
      double d2 = .MODULE$.sqrt(d1 * d1 - l.dd() * r.dd());
      double multipler = r.t() - l.t();
      double t = r.t() - multipler * (r.dd() + d2 - d1) / (r.dd() - l.dd() + (double)2 * d2);
      double lbound = l.t() + 0.1 * (r.t() - l.t());
      double ubound = l.t() + 0.9 * (r.t() - l.t());
      double var3;
      if (t < lbound) {
         this.logger().debug(() -> (new StringBuilder(24)).append("Cubic ").append(t).append(" below LHS limit: ").append(lbound).toString());
         var3 = lbound;
      } else if (t > ubound) {
         this.logger().debug(() -> (new StringBuilder(24)).append("Cubic ").append(t).append(" above RHS limit: ").append(ubound).toString());
         var3 = ubound;
      } else {
         var3 = t;
      }

      return var3;
   }

   private final void Bracket$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Bracket$module == null) {
            this.Bracket$module = new Bracket$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   public CubicLineSearch() {
      SerializableLogging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class Bracket implements Product, Serializable {
      private final double t;
      private final double dd;
      private final double fval;
      // $FF: synthetic field
      public final CubicLineSearch $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public double t() {
         return this.t;
      }

      public double dd() {
         return this.dd;
      }

      public double fval() {
         return this.fval;
      }

      public Bracket copy(final double t, final double dd, final double fval) {
         return this.breeze$optimize$CubicLineSearch$Bracket$$$outer().new Bracket(t, dd, fval);
      }

      public double copy$default$1() {
         return this.t();
      }

      public double copy$default$2() {
         return this.dd();
      }

      public double copy$default$3() {
         return this.fval();
      }

      public String productPrefix() {
         return "Bracket";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = BoxesRunTime.boxToDouble(this.t());
               break;
            case 1:
               var10000 = BoxesRunTime.boxToDouble(this.dd());
               break;
            case 2:
               var10000 = BoxesRunTime.boxToDouble(this.fval());
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
         return x$1 instanceof Bracket;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "t";
               break;
            case 1:
               var10000 = "dd";
               break;
            case 2:
               var10000 = "fval";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.doubleHash(this.t()));
         var1 = Statics.mix(var1, Statics.doubleHash(this.dd()));
         var1 = Statics.mix(var1, Statics.doubleHash(this.fval()));
         return Statics.finalizeHash(var1, 3);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label58: {
               boolean var2;
               if (x$1 instanceof Bracket && ((Bracket)x$1).breeze$optimize$CubicLineSearch$Bracket$$$outer() == this.breeze$optimize$CubicLineSearch$Bracket$$$outer()) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  Bracket var4 = (Bracket)x$1;
                  if (this.t() == var4.t() && this.dd() == var4.dd() && this.fval() == var4.fval() && var4.canEqual(this)) {
                     break label58;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      // $FF: synthetic method
      public CubicLineSearch breeze$optimize$CubicLineSearch$Bracket$$$outer() {
         return this.$outer;
      }

      public Bracket(final double t, final double dd, final double fval) {
         this.t = t;
         this.dd = dd;
         this.fval = fval;
         if (CubicLineSearch.this == null) {
            throw null;
         } else {
            this.$outer = CubicLineSearch.this;
            super();
            Product.$init$(this);
         }
      }
   }

   public class Bracket$ extends AbstractFunction3 implements Serializable {
      // $FF: synthetic field
      private final CubicLineSearch $outer;

      public final String toString() {
         return "Bracket";
      }

      public Bracket apply(final double t, final double dd, final double fval) {
         return this.$outer.new Bracket(t, dd, fval);
      }

      public Option unapply(final Bracket x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToDouble(x$0.t()), BoxesRunTime.boxToDouble(x$0.dd()), BoxesRunTime.boxToDouble(x$0.fval()))));
      }

      public Bracket$() {
         if (CubicLineSearch.this == null) {
            throw null;
         } else {
            this.$outer = CubicLineSearch.this;
            super();
         }
      }
   }
}
