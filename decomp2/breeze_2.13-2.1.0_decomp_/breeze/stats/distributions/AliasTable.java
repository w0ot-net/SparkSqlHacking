package breeze.stats.distributions;

import breeze.linalg.DenseVector;
import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]g\u0001\u0002\u0011\"\u0001\"B\u0001b\u0010\u0001\u0003\u0016\u0004%\t\u0001\u0011\u0005\t\u0015\u0002\u0011\t\u0012)A\u0005\u0003\"A1\n\u0001BK\u0002\u0013\u0005A\n\u0003\u0005R\u0001\tE\t\u0015!\u0003N\u0011!\u0011\u0006A!f\u0001\n\u0003\u0019\u0006\u0002\u00032\u0001\u0005#\u0005\u000b\u0011\u0002+\t\u0011\r\u0004!Q3A\u0005\u0002\u0011D\u0001\"\u001b\u0001\u0003\u0012\u0003\u0006I!\u001a\u0005\u0006U\u0002!\ta\u001b\u0005\u0006c\u0002!\tA\u001d\u0005\bg\u0002\t\t\u0011\"\u0001u\u0011\u001dq\b!%A\u0005\u0002}D\u0011\"!\u0007\u0001#\u0003%\t!a\u0007\t\u0013\u0005\r\u0002!%A\u0005\u0002\u0005\u0015\u0002\"CA\u0017\u0001E\u0005I\u0011AA\u0018\u0011%\t9\u0004AA\u0001\n\u0003\nI\u0004C\u0005\u0002L\u0001\t\t\u0011\"\u0001\u0002N!I\u0011q\n\u0001\u0002\u0002\u0013\u0005\u0011\u0011\u000b\u0005\n\u0003/\u0002\u0011\u0011!C!\u00033B\u0011\"a\u001a\u0001\u0003\u0003%\t!!\u001b\t\u0013\u0005M\u0004!!A\u0005B\u0005U\u0004\"CA=\u0001\u0005\u0005I\u0011IA>\u0011%\ti\bAA\u0001\n\u0003\ny\bC\u0005\u0002\u0002\u0002\t\t\u0011\"\u0011\u0002\u0004\u001eI\u0011qQ\u0011\u0002\u0002#\u0005\u0011\u0011\u0012\u0004\tA\u0005\n\t\u0011#\u0001\u0002\f\"1!N\u0007C\u0001\u0003/C\u0011\"! \u001b\u0003\u0003%)%a \t\u0013\u0005e%$!A\u0005\u0002\u0006m\u0005\"CAX5\u0005\u0005I\u0011QAY\u0011%\tiMGA\u0001\n\u0013\tyM\u0001\u0006BY&\f7\u000fV1cY\u0016T!AI\u0012\u0002\u001b\u0011L7\u000f\u001e:jEV$\u0018n\u001c8t\u0015\t!S%A\u0003ti\u0006$8OC\u0001'\u0003\u0019\u0011'/Z3{K\u000e\u0001QCA\u0015Z'\u0011\u0001!\u0006M\u001a\u0011\u0005-rS\"\u0001\u0017\u000b\u00035\nQa]2bY\u0006L!a\f\u0017\u0003\r\u0005s\u0017PU3g!\tY\u0013'\u0003\u00023Y\t9\u0001K]8ek\u000e$\bC\u0001\u001b=\u001d\t)$H\u0004\u00027s5\tqG\u0003\u00029O\u00051AH]8pizJ\u0011!L\u0005\u0003w1\nq\u0001]1dW\u0006<W-\u0003\u0002>}\ta1+\u001a:jC2L'0\u00192mK*\u00111\bL\u0001\u0006aJ|'m]\u000b\u0002\u0003B\u0019!)R$\u000e\u0003\rS!\u0001R\u0013\u0002\r1Lg.\u00197h\u0013\t15IA\u0006EK:\u001cXMV3di>\u0014\bCA\u0016I\u0013\tIEF\u0001\u0004E_V\u0014G.Z\u0001\u0007aJ|'m\u001d\u0011\u0002\u000f\u0005d\u0017.Y:fgV\tQ\nE\u0002C\u000b:\u0003\"aK(\n\u0005Ac#aA%oi\u0006A\u0011\r\\5bg\u0016\u001c\b%\u0001\u0005pkR\u001cw.\\3t+\u0005!\u0006c\u0001\u001bV/&\u0011aK\u0010\u0002\u000b\u0013:$W\r_3e'\u0016\f\bC\u0001-Z\u0019\u0001!QA\u0017\u0001C\u0002m\u0013\u0011!S\t\u00039~\u0003\"aK/\n\u0005yc#a\u0002(pi\"Lgn\u001a\t\u0003W\u0001L!!\u0019\u0017\u0003\u0007\u0005s\u00170A\u0005pkR\u001cw.\\3tA\u0005!!/\u00198e+\u0005)\u0007C\u00014h\u001b\u0005\t\u0013B\u00015\"\u0005%\u0011\u0016M\u001c3CCNL7/A\u0003sC:$\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0006Y6tw\u000e\u001d\t\u0004M\u00029\u0006\"B \n\u0001\u0004\t\u0005\"B&\n\u0001\u0004i\u0005\"\u0002*\n\u0001\u0004!\u0006\"B2\n\u0001\u0004)\u0017\u0001\u00023sC^$\u0012aV\u0001\u0005G>\u0004\u00180\u0006\u0002vqR)a/\u001f>|{B\u0019a\rA<\u0011\u0005aCH!\u0002.\f\u0005\u0004Y\u0006bB \f!\u0003\u0005\r!\u0011\u0005\b\u0017.\u0001\n\u00111\u0001N\u0011\u001d\u00116\u0002%AA\u0002q\u00042\u0001N+x\u0011\u001d\u00197\u0002%AA\u0002\u0015\fabY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0003\u0002\u0002\u0005]QCAA\u0002U\r\t\u0015QA\u0016\u0003\u0003\u000f\u0001B!!\u0003\u0002\u00145\u0011\u00111\u0002\u0006\u0005\u0003\u001b\ty!A\u0005v]\u000eDWmY6fI*\u0019\u0011\u0011\u0003\u0017\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002\u0016\u0005-!!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0012)!\f\u0004b\u00017\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T\u0003BA\u000f\u0003C)\"!a\b+\u00075\u000b)\u0001B\u0003[\u001b\t\u00071,\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001a\u0016\t\u0005\u001d\u00121F\u000b\u0003\u0003SQ3\u0001VA\u0003\t\u0015QfB1\u0001\\\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIQ*B!!\r\u00026U\u0011\u00111\u0007\u0016\u0004K\u0006\u0015A!\u0002.\u0010\u0005\u0004Y\u0016!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0002<A!\u0011QHA$\u001b\t\tyD\u0003\u0003\u0002B\u0005\r\u0013\u0001\u00027b]\u001eT!!!\u0012\u0002\t)\fg/Y\u0005\u0005\u0003\u0013\nyD\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002\u001d\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HcA0\u0002T!A\u0011Q\u000b\n\u0002\u0002\u0003\u0007a*A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u00037\u0002R!!\u0018\u0002d}k!!a\u0018\u000b\u0007\u0005\u0005D&\u0001\u0006d_2dWm\u0019;j_:LA!!\u001a\u0002`\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\tY'!\u001d\u0011\u0007-\ni'C\u0002\u0002p1\u0012qAQ8pY\u0016\fg\u000e\u0003\u0005\u0002VQ\t\t\u00111\u0001`\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u0005m\u0012q\u000f\u0005\t\u0003+*\u0012\u0011!a\u0001\u001d\u0006A\u0001.Y:i\u0007>$W\rF\u0001O\u0003!!xn\u0015;sS:<GCAA\u001e\u0003\u0019)\u0017/^1mgR!\u00111NAC\u0011!\t)\u0006GA\u0001\u0002\u0004y\u0016AC!mS\u0006\u001cH+\u00192mKB\u0011aMG\n\u00055)\ni\t\u0005\u0003\u0002\u0010\u0006UUBAAI\u0015\u0011\t\u0019*a\u0011\u0002\u0005%|\u0017bA\u001f\u0002\u0012R\u0011\u0011\u0011R\u0001\u0006CB\u0004H._\u000b\u0005\u0003;\u000b\u0019\u000b\u0006\u0006\u0002 \u0006\u0015\u0016qUAU\u0003[\u0003BA\u001a\u0001\u0002\"B\u0019\u0001,a)\u0005\u000bik\"\u0019A.\t\u000b}j\u0002\u0019A!\t\u000b-k\u0002\u0019A'\t\rIk\u0002\u0019AAV!\u0011!T+!)\t\u000b\rl\u0002\u0019A3\u0002\u000fUt\u0017\r\u001d9msV!\u00111WAc)\u0011\t),a2\u0011\u000b-\n9,a/\n\u0007\u0005eFF\u0001\u0004PaRLwN\u001c\t\tW\u0005u\u0016)TAaK&\u0019\u0011q\u0018\u0017\u0003\rQ+\b\u000f\\35!\u0011!T+a1\u0011\u0007a\u000b)\rB\u0003[=\t\u00071\fC\u0005\u0002Jz\t\t\u00111\u0001\u0002L\u0006\u0019\u0001\u0010\n\u0019\u0011\t\u0019\u0004\u00111Y\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003#\u0004B!!\u0010\u0002T&!\u0011Q[A \u0005\u0019y%M[3di\u0002"
)
public class AliasTable implements Product, Serializable {
   private final DenseVector probs;
   private final DenseVector aliases;
   private final IndexedSeq outcomes;
   private final RandBasis rand;

   public static Option unapply(final AliasTable x$0) {
      return AliasTable$.MODULE$.unapply(x$0);
   }

   public static AliasTable apply(final DenseVector probs, final DenseVector aliases, final IndexedSeq outcomes, final RandBasis rand) {
      return AliasTable$.MODULE$.apply(probs, aliases, outcomes, rand);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public DenseVector probs() {
      return this.probs;
   }

   public DenseVector aliases() {
      return this.aliases;
   }

   public IndexedSeq outcomes() {
      return this.outcomes;
   }

   public RandBasis rand() {
      return this.rand;
   }

   public Object draw() {
      int roll = this.rand().randInt(this.outcomes().length()).draw$mcI$sp();
      double toss = this.rand().uniform().draw$mcD$sp();
      return toss < this.probs().apply$mcD$sp(roll) ? this.outcomes().apply(roll) : this.outcomes().apply(this.aliases().apply$mcI$sp(roll));
   }

   public AliasTable copy(final DenseVector probs, final DenseVector aliases, final IndexedSeq outcomes, final RandBasis rand) {
      return new AliasTable(probs, aliases, outcomes, rand);
   }

   public DenseVector copy$default$1() {
      return this.probs();
   }

   public DenseVector copy$default$2() {
      return this.aliases();
   }

   public IndexedSeq copy$default$3() {
      return this.outcomes();
   }

   public RandBasis copy$default$4() {
      return this.rand();
   }

   public String productPrefix() {
      return "AliasTable";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.probs();
            break;
         case 1:
            var10000 = this.aliases();
            break;
         case 2:
            var10000 = this.outcomes();
            break;
         case 3:
            var10000 = this.rand();
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
      return x$1 instanceof AliasTable;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "probs";
            break;
         case 1:
            var10000 = "aliases";
            break;
         case 2:
            var10000 = "outcomes";
            break;
         case 3:
            var10000 = "rand";
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
      boolean var13;
      if (this != x$1) {
         label81: {
            boolean var2;
            if (x$1 instanceof AliasTable) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label63: {
                  label72: {
                     AliasTable var4 = (AliasTable)x$1;
                     DenseVector var10000 = this.probs();
                     DenseVector var5 = var4.probs();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label72;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label72;
                     }

                     var10000 = this.aliases();
                     DenseVector var6 = var4.aliases();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label72;
                        }
                     } else if (!var10000.equals(var6)) {
                        break label72;
                     }

                     IndexedSeq var10 = this.outcomes();
                     IndexedSeq var7 = var4.outcomes();
                     if (var10 == null) {
                        if (var7 != null) {
                           break label72;
                        }
                     } else if (!var10.equals(var7)) {
                        break label72;
                     }

                     RandBasis var11 = this.rand();
                     RandBasis var8 = var4.rand();
                     if (var11 == null) {
                        if (var8 != null) {
                           break label72;
                        }
                     } else if (!var11.equals(var8)) {
                        break label72;
                     }

                     if (var4.canEqual(this)) {
                        var13 = true;
                        break label63;
                     }
                  }

                  var13 = false;
               }

               if (var13) {
                  break label81;
               }
            }

            var13 = false;
            return var13;
         }
      }

      var13 = true;
      return var13;
   }

   public AliasTable(final DenseVector probs, final DenseVector aliases, final IndexedSeq outcomes, final RandBasis rand) {
      this.probs = probs;
      this.aliases = aliases;
      this.outcomes = outcomes;
      this.rand = rand;
      Product.$init$(this);
   }
}
