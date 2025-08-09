package breeze.optimize.linear;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uu!B\u000f\u001f\u0011\u0003)c!B\u0014\u001f\u0011\u0003A\u0003\"B\u0018\u0002\t\u0003\u0001d\u0001B\u0019\u0002\tJB\u0001BQ\u0002\u0003\u0016\u0004%\ta\u0011\u0005\t\u000f\u000e\u0011\t\u0012)A\u0005\t\"A\u0001j\u0001BK\u0002\u0013\u0005\u0011\n\u0003\u0005Q\u0007\tE\t\u0015!\u0003K\u0011\u0015y3\u0001\"\u0001R\u0011\u001d16!!A\u0005\u0002]CqAW\u0002\u0012\u0002\u0013\u00051\fC\u0004g\u0007E\u0005I\u0011A4\t\u000f%\u001c\u0011\u0011!C!U\"91oAA\u0001\n\u0003!\bbB;\u0004\u0003\u0003%\tA\u001e\u0005\by\u000e\t\t\u0011\"\u0011~\u0011%\tIaAA\u0001\n\u0003\tY\u0001C\u0005\u0002\u0016\r\t\t\u0011\"\u0011\u0002\u0018!I\u00111D\u0002\u0002\u0002\u0013\u0005\u0013Q\u0004\u0005\n\u0003?\u0019\u0011\u0011!C!\u0003CA\u0011\"a\t\u0004\u0003\u0003%\t%!\n\b\u0013\u0005%\u0012!!A\t\n\u0005-b\u0001C\u0019\u0002\u0003\u0003EI!!\f\t\r=2B\u0011AA#\u0011%\tyBFA\u0001\n\u000b\n\t\u0003C\u0005\u0002HY\t\t\u0011\"!\u0002J!I\u0011q\n\f\u0002\u0002\u0013\u0005\u0015\u0011\u000b\u0005\n\u0003G2\u0012\u0011!C\u0005\u0003KBq!!\u001c\u0002\t\u0003\ty'\u0001\bCe\u0006t7\r[!oI\n{WO\u001c3\u000b\u0005}\u0001\u0013A\u00027j]\u0016\f'O\u0003\u0002\"E\u0005Aq\u000e\u001d;j[&TXMC\u0001$\u0003\u0019\u0011'/Z3{K\u000e\u0001\u0001C\u0001\u0014\u0002\u001b\u0005q\"A\u0004\"sC:\u001c\u0007.\u00118e\u0005>,h\u000eZ\n\u0003\u0003%\u0002\"AK\u0017\u000e\u0003-R\u0011\u0001L\u0001\u0006g\u000e\fG.Y\u0005\u0003]-\u0012a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\bF\u0001&\u0005\u0015\u0019F/\u0019;f'\u0011\u0019\u0011f\r\u001c\u0011\u0005)\"\u0014BA\u001b,\u0005\u001d\u0001&o\u001c3vGR\u0004\"aN \u000f\u0005ajdBA\u001d=\u001b\u0005Q$BA\u001e%\u0003\u0019a$o\\8u}%\tA&\u0003\u0002?W\u00059\u0001/Y2lC\u001e,\u0017B\u0001!B\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tq4&\u0001\u0005ma~3\u0018\r\\;f+\u0005!\u0005C\u0001\u0016F\u0013\t15F\u0001\u0004E_V\u0014G.Z\u0001\nYB|f/\u00197vK\u0002\n\u0011B]3nC&t\u0017N\\4\u0016\u0003)\u00032aN&N\u0013\ta\u0015IA\u0002TKF\u0004\"A\u000b(\n\u0005=[#aA%oi\u0006Q!/Z7bS:Lgn\u001a\u0011\u0015\u0007I#V\u000b\u0005\u0002T\u00075\t\u0011\u0001C\u0003C\u0011\u0001\u0007A\tC\u0003I\u0011\u0001\u0007!*\u0001\u0003d_BLHc\u0001*Y3\"9!)\u0003I\u0001\u0002\u0004!\u0005b\u0002%\n!\u0003\u0005\rAS\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005a&F\u0001#^W\u0005q\u0006CA0e\u001b\u0005\u0001'BA1c\u0003%)hn\u00195fG.,GM\u0003\u0002dW\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005\u0015\u0004'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T#\u00015+\u0005)k\u0016!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001l!\ta\u0017/D\u0001n\u0015\tqw.\u0001\u0003mC:<'\"\u00019\u0002\t)\fg/Y\u0005\u0003e6\u0014aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLX#A'\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011qO\u001f\t\u0003UaL!!_\u0016\u0003\u0007\u0005s\u0017\u0010C\u0004|\u001d\u0005\u0005\t\u0019A'\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\u0005q\b\u0003B@\u0002\u0006]l!!!\u0001\u000b\u0007\u0005\r1&\u0001\u0006d_2dWm\u0019;j_:LA!a\u0002\u0002\u0002\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\ti!a\u0005\u0011\u0007)\ny!C\u0002\u0002\u0012-\u0012qAQ8pY\u0016\fg\u000eC\u0004|!\u0005\u0005\t\u0019A<\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004W\u0006e\u0001bB>\u0012\u0003\u0003\u0005\r!T\u0001\tQ\u0006\u001c\bnQ8eKR\tQ*\u0001\u0005u_N#(/\u001b8h)\u0005Y\u0017AB3rk\u0006d7\u000f\u0006\u0003\u0002\u000e\u0005\u001d\u0002bB>\u0015\u0003\u0003\u0005\ra^\u0001\u0006'R\fG/\u001a\t\u0003'Z\u0019RAFA\u0018\u0003w\u0001r!!\r\u00028\u0011S%+\u0004\u0002\u00024)\u0019\u0011QG\u0016\u0002\u000fI,h\u000e^5nK&!\u0011\u0011HA\u001a\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gN\r\t\u0005\u0003{\t\u0019%\u0004\u0002\u0002@)\u0019\u0011\u0011I8\u0002\u0005%|\u0017b\u0001!\u0002@Q\u0011\u00111F\u0001\u0006CB\u0004H.\u001f\u000b\u0006%\u0006-\u0013Q\n\u0005\u0006\u0005f\u0001\r\u0001\u0012\u0005\u0006\u0011f\u0001\rAS\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t\u0019&a\u0018\u0011\u000b)\n)&!\u0017\n\u0007\u0005]3F\u0001\u0004PaRLwN\u001c\t\u0006U\u0005mCIS\u0005\u0004\u0003;Z#A\u0002+va2,'\u0007\u0003\u0005\u0002bi\t\t\u00111\u0001S\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003O\u00022\u0001\\A5\u0013\r\tY'\u001c\u0002\u0007\u001f\nTWm\u0019;\u0002\u00115Lg.[7ju\u0016$B\"!\u001d\u0002x\u0005\u001d\u0015\u0011SAK\u00033\u00032AKA:\u0013\r\t)h\u000b\u0002\u0005+:LG\u000fC\u0004\u0002zq\u0001\r!a\u001f\u0002\u0003\u0005\u0003R!! \u0002\u0004\u0012k!!a \u000b\u0007\u0005\u0005%%\u0001\u0004mS:\fGnZ\u0005\u0005\u0003\u000b\u000byHA\u0006EK:\u001cX-T1ue&D\bbBAE9\u0001\u0007\u00111R\u0001\u0002EB)\u0011QPAG\t&!\u0011qRA@\u0005-!UM\\:f-\u0016\u001cGo\u001c:\t\u000f\u0005ME\u00041\u0001\u0002\f\u0006\t1\rC\u0004\u0002\u0018r\u0001\r!a#\u0002\u0005a\u0004\u0004BBAN9\u0001\u0007!*\u0001\u0005j]R,w-\u001a:t\u0001"
)
public final class BranchAndBound {
   public static void minimize(final DenseMatrix A, final DenseVector b, final DenseVector c, final DenseVector x0, final Seq integers) {
      BranchAndBound$.MODULE$.minimize(A, b, c, x0, integers);
   }

   private static class State implements Product, Serializable {
      private final double lp_value;
      private final Seq remaining;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public double lp_value() {
         return this.lp_value;
      }

      public Seq remaining() {
         return this.remaining;
      }

      public State copy(final double lp_value, final Seq remaining) {
         return new State(lp_value, remaining);
      }

      public double copy$default$1() {
         return this.lp_value();
      }

      public Seq copy$default$2() {
         return this.remaining();
      }

      public String productPrefix() {
         return "State";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = BoxesRunTime.boxToDouble(this.lp_value());
               break;
            case 1:
               var10000 = this.remaining();
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
               var10000 = "lp_value";
               break;
            case 1:
               var10000 = "remaining";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.doubleHash(this.lp_value()));
         var1 = Statics.mix(var1, Statics.anyHash(this.remaining()));
         return Statics.finalizeHash(var1, 2);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var7;
         if (this != x$1) {
            label55: {
               boolean var2;
               if (x$1 instanceof State) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label38: {
                     State var4 = (State)x$1;
                     if (this.lp_value() == var4.lp_value()) {
                        label36: {
                           Seq var10000 = this.remaining();
                           Seq var5 = var4.remaining();
                           if (var10000 == null) {
                              if (var5 != null) {
                                 break label36;
                              }
                           } else if (!var10000.equals(var5)) {
                              break label36;
                           }

                           if (var4.canEqual(this)) {
                              var7 = true;
                              break label38;
                           }
                        }
                     }

                     var7 = false;
                  }

                  if (var7) {
                     break label55;
                  }
               }

               var7 = false;
               return var7;
            }
         }

         var7 = true;
         return var7;
      }

      public State(final double lp_value, final Seq remaining) {
         this.lp_value = lp_value;
         this.remaining = remaining;
         Product.$init$(this);
      }
   }

   private static class State$ extends AbstractFunction2 implements Serializable {
      public static final State$ MODULE$ = new State$();

      public final String toString() {
         return "State";
      }

      public State apply(final double lp_value, final Seq remaining) {
         return new State(lp_value, remaining);
      }

      public Option unapply(final State x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToDouble(x$0.lp_value()), x$0.remaining())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(State$.class);
      }

      public State$() {
      }
   }
}
