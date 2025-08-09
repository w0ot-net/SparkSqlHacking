package spire.algebra;

import algebra.ring.CommutativeRing;
import algebra.ring.Signed;
import algebra.ring.Signed.Negative.;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import spire.math.Integral;
import spire.math.SafeLong;
import spire.math.prime.Factors;

@ScalaSignature(
   bytes = "\u0006\u0005\teca\u0002\u0013&!\u0003\r\nA\u000b\u0005\u0006e\u00011\ta\r\u0005\u0006=\u00021\taX\u0004\u0006G\u0016B\t\u0001\u001a\u0004\u0006I\u0015B\t!\u001a\u0005\u0006S\u0012!\tA\u001b\u0004\bW\u0012\u0001\n1%\u0001m\u0011\u0015qgA\"\u0001p\u0011\u0015YhA\"\u0001}\u0011\u001d\t)\u0002\u0002C\u0001\u0003/1a!!\n\u0005\u0001\u0006\u001d\u0002BCA \u0015\tU\r\u0011\"\u0001\u0002B!Q\u00111\u000b\u0006\u0003\u0012\u0003\u0006I!a\u0011\t\u0015\u0005U#BaA!\u0002\u0017\t9\u0006\u0003\u0004j\u0015\u0011\u0005\u00111\r\u0005\u0007]*!\t!!\u001c\t\rmTA\u0011IA8\u0011%\t\tICA\u0001\n\u0003\t\u0019\tC\u0005\u0002\u0016*\t\n\u0011\"\u0001\u0002\u0018\"I\u0011\u0011\u0017\u0006\u0002\u0002\u0013\u0005\u00131\u0017\u0005\n\u0003\u000bT\u0011\u0011!C\u0001\u0003\u000fD\u0011\"!3\u000b\u0003\u0003%\t!a3\t\u0013\u0005E'\"!A\u0005B\u0005M\u0007\"CAq\u0015\u0005\u0005I\u0011AAr\u0011%\t9OCA\u0001\n\u0003\nI\u000fC\u0005\u0002n*\t\t\u0011\"\u0011\u0002p\"I\u0011\u0011\u001f\u0006\u0002\u0002\u0013\u0005\u00131\u001f\u0005\n\u0003kT\u0011\u0011!C!\u0003o<\u0011\"a?\u0005\u0003\u0003E\t!!@\u0007\u0013\u0005\u0015B!!A\t\u0002\u0005}\bBB5\u001e\t\u0003\u0011Y\u0001C\u0005\u0002rv\t\t\u0011\"\u0012\u0002t\"I\u0011QC\u000f\u0002\u0002\u0013\u0005%Q\u0002\u0005\n\u0005?i\u0012\u0011!CA\u0005CA\u0011B!\u000e\u001e\u0003\u0003%IAa\u000e\t\u000f\t}B\u0001b\u0001\u0003B\tIRK\\5rk\u00164\u0015m\u0019;pe&T\u0018\r^5p]\u0012{W.Y5o\u0015\t1s%A\u0004bY\u001e,'M]1\u000b\u0003!\nQa\u001d9je\u0016\u001c\u0001!\u0006\u0002,wM\u0011\u0001\u0001\f\t\u0003[Aj\u0011A\f\u0006\u0002_\u0005)1oY1mC&\u0011\u0011G\f\u0002\u0004\u0003:L\u0018aB5t!JLW.\u001a\u000b\u0003i]\u0002\"!L\u001b\n\u0005Yr#a\u0002\"p_2,\u0017M\u001c\u0005\u0006q\u0005\u0001\r!O\u0001\u0002CB\u0011!h\u000f\u0007\u0001\t%a\u0004\u0001)A\u0001\u0002\u000b\u0007QHA\u0001B#\tqD\u0006\u0005\u0002.\u007f%\u0011\u0001I\f\u0002\b\u001d>$\b.\u001b8hQ\u0019Y$)R(U3B\u0011QfQ\u0005\u0003\t:\u00121b\u001d9fG&\fG.\u001b>fIF*1ER$J\u0011:\u0011QfR\u0005\u0003\u0011:\nAAQ=uKF\"AE\u0013(0\u001d\tYe*D\u0001M\u0015\ti\u0015&\u0001\u0004=e>|GOP\u0005\u0002_E*1\u0005U)T%:\u0011Q&U\u0005\u0003%:\nQa\u00155peR\fD\u0001\n&O_E*1%\u0016,Y/:\u0011QFV\u0005\u0003/:\n1!\u00138uc\u0011!#JT\u00182\u000b\rR6,\u0018/\u000f\u00055Z\u0016B\u0001//\u0003\u0011auN\\42\t\u0011RejL\u0001\u0007M\u0006\u001cGo\u001c:\u0015\u0007\u0001\u00149\u0006E\u0002b\rer!AY\u0002\u000e\u0003\u0015\n\u0011$\u00168jcV,g)Y2u_JL'0\u0019;j_:$u.\\1j]B\u0011!\rB\n\u0003\t\u0019\u0004\"!L4\n\u0005!t#AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0002I\niA)Z2p[B|7/\u001b;j_:,\"!\\9\u0014\u0005\u00191\u0017\u0001B;oSR,\u0012\u0001\u001d\t\u0003uE$\u0011\u0002\u0010\u0004!\u0002\u0003\u0005)\u0019A\u001f)\rE\u00145/^<zc\u0015\u0019ci\u0012;Ic\u0011!#JT\u00182\u000b\r\u0002\u0016K\u001e*2\t\u0011RejL\u0019\u0006GU3\u0006pV\u0019\u0005I)su&M\u0003$5nSH,\r\u0003%\u0015:{\u0013\u0001C3mK6,g\u000e^:\u0016\u0003u\u0004RA`A\u0002\u0003\u0013q!AS@\n\u0007\u0005\u0005a&A\u0004qC\u000e\\\u0017mZ3\n\t\u0005\u0015\u0011q\u0001\u0002\t\u0013R,'/\u00192mK*\u0019\u0011\u0011\u0001\u0018\u0011\r5\nY\u0001]A\b\u0013\r\tiA\f\u0002\u0007)V\u0004H.\u001a\u001a\u0011\u00075\n\t\"C\u0002\u0002\u00149\u00121!\u00138u\u0003\u0015\t\u0007\u000f\u001d7z+\u0011\tI\"a\b\u0015\t\u0005m\u0011\u0011\u0005\t\u0005E\u0002\ti\u0002E\u0002;\u0003?!Q\u0001P\u0005C\u0002uBq!a\t\n\u0001\b\tY\"\u0001\u0002fm\n\trK]1q\t\u0016\u001cw.\u001c9pg&$\u0018n\u001c8\u0016\t\u0005%\u0012\u0011G\n\t\u0015\u0019\fY#a\r\u0002:A)\u0011Q\u0006\u0004\u000205\tA\u0001E\u0002;\u0003c!Q\u0001\u0010\u0006C\u0002u\u00022!LA\u001b\u0013\r\t9D\f\u0002\b!J|G-^2u!\rq\u00181H\u0005\u0005\u0003{\t9A\u0001\u0007TKJL\u0017\r\\5{C\ndW-A\btC\u001a,Gj\u001c8h\r\u0006\u001cGo\u001c:t+\t\t\u0019\u0005\u0005\u0003\u0002F\u0005=SBAA$\u0015\u0011\tI%a\u0013\u0002\u000bA\u0014\u0018.\\3\u000b\u0007\u00055s%\u0001\u0003nCRD\u0017\u0002BA)\u0003\u000f\u0012qAR1di>\u00148/\u0001\ttC\u001a,Gj\u001c8h\r\u0006\u001cGo\u001c:tA\u0005QQM^5eK:\u001cW\rJ\u0019\u0011\r\u0005e\u0013QLA\u0018\u001d\r\u0011\u00171L\u0005\u0004\u0003\u0003)\u0013\u0002BA0\u0003C\u0012Qa\u0011*j]\u001eT1!!\u0001&)\u0011\t)'a\u001b\u0015\t\u0005\u001d\u0014\u0011\u000e\t\u0006\u0003[Q\u0011q\u0006\u0005\b\u0003+r\u00019AA,\u0011\u001d\tyD\u0004a\u0001\u0003\u0007*\"!a\f\u0016\u0005\u0005E\u0004\u0003CA:\u0003w\ny#a\u0004\u000f\t\u0005U\u0014q\u000f\t\u0003\u0017:J1!!\u001f/\u0003\u0019\u0001&/\u001a3fM&!\u0011QPA@\u0005\ri\u0015\r\u001d\u0006\u0004\u0003sr\u0013\u0001B2paf,B!!\"\u0002\u000eR!\u0011qQAJ)\u0011\tI)a$\u0011\u000b\u00055\"\"a#\u0011\u0007i\ni\tB\u0003=#\t\u0007Q\bC\u0004\u0002VE\u0001\u001d!!%\u0011\r\u0005e\u0013QLAF\u0011%\ty$\u0005I\u0001\u0002\u0004\t\u0019%\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\t\u0005e\u0015qV\u000b\u0003\u00037SC!a\u0011\u0002\u001e.\u0012\u0011q\u0014\t\u0005\u0003C\u000bY+\u0004\u0002\u0002$*!\u0011QUAT\u0003%)hn\u00195fG.,GMC\u0002\u0002*:\n!\"\u00198o_R\fG/[8o\u0013\u0011\ti+a)\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\rB\u0003=%\t\u0007Q(A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003k\u0003B!a.\u0002B6\u0011\u0011\u0011\u0018\u0006\u0005\u0003w\u000bi,\u0001\u0003mC:<'BAA`\u0003\u0011Q\u0017M^1\n\t\u0005\r\u0017\u0011\u0018\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\u0005=\u0011A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0004Y\u00055\u0007\"CAh+\u0005\u0005\t\u0019AA\b\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011Q\u001b\t\u0006\u0003/\fi\u000eL\u0007\u0003\u00033T1!a7/\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003?\fIN\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGc\u0001\u001b\u0002f\"A\u0011qZ\f\u0002\u0002\u0003\u0007A&\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA[\u0003WD\u0011\"a4\u0019\u0003\u0003\u0005\r!a\u0004\u0002\u0011!\f7\u000f[\"pI\u0016$\"!a\u0004\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!.\u0002\r\u0015\fX/\u00197t)\r!\u0014\u0011 \u0005\t\u0003\u001f\\\u0012\u0011!a\u0001Y\u0005\trK]1q\t\u0016\u001cw.\u001c9pg&$\u0018n\u001c8\u0011\u0007\u00055Rd\u0005\u0003\u001eM\n\u0005\u0001\u0003\u0002B\u0002\u0005\u0013i!A!\u0002\u000b\t\t\u001d\u0011QX\u0001\u0003S>LA!!\u0010\u0003\u0006Q\u0011\u0011Q`\u000b\u0005\u0005\u001f\u00119\u0002\u0006\u0003\u0003\u0012\tuA\u0003\u0002B\n\u00053\u0001R!!\f\u000b\u0005+\u00012A\u000fB\f\t\u0015a\u0004E1\u0001>\u0011\u001d\t)\u0006\ta\u0002\u00057\u0001b!!\u0017\u0002^\tU\u0001bBA A\u0001\u0007\u00111I\u0001\bk:\f\u0007\u000f\u001d7z+\u0011\u0011\u0019Ca\r\u0015\t\t\u0015\"1\u0006\t\u0006[\t\u001d\u00121I\u0005\u0004\u0005Sq#AB(qi&|g\u000eC\u0005\u0003.\u0005\n\t\u00111\u0001\u00030\u0005\u0019\u0001\u0010\n\u0019\u0011\u000b\u00055\"B!\r\u0011\u0007i\u0012\u0019\u0004B\u0003=C\t\u0007Q(\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0003:A!\u0011q\u0017B\u001e\u0013\u0011\u0011i$!/\u0003\r=\u0013'.Z2u\u0003\u0015*h.[9vK\u001a\u000b7\r^8sSj\fG/[8o\t>l\u0017-\u001b8Ge>l\u0017J\u001c;fOJ\fG.\u0006\u0003\u0003D\t%C\u0003\u0002B#\u0005\u0017\u0002BA\u0019\u0001\u0003HA\u0019!H!\u0013\u0005\u000bq\u001a#\u0019A\u001f\t\u000f\t53\u0005q\u0001\u0003P\u0005\t\u0011\t\u0005\u0004\u0003R\tM#qI\u0007\u0003\u0003\u0017JAA!\u0016\u0002L\tA\u0011J\u001c;fOJ\fG\u000eC\u00039\u0005\u0001\u0007\u0011\b"
)
public interface UniqueFactorizationDomain {
   static UniqueFactorizationDomain uniqueFactorizationDomainFromIntegral(final Integral A) {
      return UniqueFactorizationDomain$.MODULE$.uniqueFactorizationDomainFromIntegral(A);
   }

   static UniqueFactorizationDomain apply(final UniqueFactorizationDomain ev) {
      return UniqueFactorizationDomain$.MODULE$.apply(ev);
   }

   boolean isPrime(final Object a);

   Decomposition factor(final Object a);

   // $FF: synthetic method
   static boolean isPrime$mcB$sp$(final UniqueFactorizationDomain $this, final byte a) {
      return $this.isPrime$mcB$sp(a);
   }

   default boolean isPrime$mcB$sp(final byte a) {
      return this.isPrime(BoxesRunTime.boxToByte(a));
   }

   // $FF: synthetic method
   static boolean isPrime$mcI$sp$(final UniqueFactorizationDomain $this, final int a) {
      return $this.isPrime$mcI$sp(a);
   }

   default boolean isPrime$mcI$sp(final int a) {
      return this.isPrime(BoxesRunTime.boxToInteger(a));
   }

   // $FF: synthetic method
   static boolean isPrime$mcJ$sp$(final UniqueFactorizationDomain $this, final long a) {
      return $this.isPrime$mcJ$sp(a);
   }

   default boolean isPrime$mcJ$sp(final long a) {
      return this.isPrime(BoxesRunTime.boxToLong(a));
   }

   // $FF: synthetic method
   static boolean isPrime$mcS$sp$(final UniqueFactorizationDomain $this, final short a) {
      return $this.isPrime$mcS$sp(a);
   }

   default boolean isPrime$mcS$sp(final short a) {
      return this.isPrime(BoxesRunTime.boxToShort(a));
   }

   // $FF: synthetic method
   static Decomposition factor$mcB$sp$(final UniqueFactorizationDomain $this, final byte a) {
      return $this.factor$mcB$sp(a);
   }

   default Decomposition factor$mcB$sp(final byte a) {
      return this.factor(BoxesRunTime.boxToByte(a));
   }

   // $FF: synthetic method
   static Decomposition factor$mcI$sp$(final UniqueFactorizationDomain $this, final int a) {
      return $this.factor$mcI$sp(a);
   }

   default Decomposition factor$mcI$sp(final int a) {
      return this.factor(BoxesRunTime.boxToInteger(a));
   }

   // $FF: synthetic method
   static Decomposition factor$mcJ$sp$(final UniqueFactorizationDomain $this, final long a) {
      return $this.factor$mcJ$sp(a);
   }

   default Decomposition factor$mcJ$sp(final long a) {
      return this.factor(BoxesRunTime.boxToLong(a));
   }

   // $FF: synthetic method
   static Decomposition factor$mcS$sp$(final UniqueFactorizationDomain $this, final short a) {
      return $this.factor$mcS$sp(a);
   }

   default Decomposition factor$mcS$sp(final short a) {
      return this.factor(BoxesRunTime.boxToShort(a));
   }

   public interface Decomposition {
      Object unit();

      Iterable elements();

      default byte unit$mcB$sp() {
         return BoxesRunTime.unboxToByte(this.unit());
      }

      default int unit$mcI$sp() {
         return BoxesRunTime.unboxToInt(this.unit());
      }

      default long unit$mcJ$sp() {
         return BoxesRunTime.unboxToLong(this.unit());
      }

      default short unit$mcS$sp() {
         return BoxesRunTime.unboxToShort(this.unit());
      }
   }

   public static class WrapDecomposition implements Decomposition, Product, Serializable {
      private final Factors safeLongFactors;
      private final CommutativeRing evidence$1;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public byte unit$mcB$sp() {
         return UniqueFactorizationDomain.Decomposition.super.unit$mcB$sp();
      }

      public int unit$mcI$sp() {
         return UniqueFactorizationDomain.Decomposition.super.unit$mcI$sp();
      }

      public long unit$mcJ$sp() {
         return UniqueFactorizationDomain.Decomposition.super.unit$mcJ$sp();
      }

      public short unit$mcS$sp() {
         return UniqueFactorizationDomain.Decomposition.super.unit$mcS$sp();
      }

      public Factors safeLongFactors() {
         return this.safeLongFactors;
      }

      public Object unit() {
         Signed.Sign var2 = this.safeLongFactors().sign();
         Object var1;
         if (.MODULE$.equals(var2)) {
            var1 = package$.MODULE$.CRing().apply(this.evidence$1).negate(package$.MODULE$.CRing().apply(this.evidence$1).one());
         } else {
            if (!algebra.ring.Signed.Positive..MODULE$.equals(var2)) {
               throw new ArithmeticException("Factorization of zero is undefined.");
            }

            var1 = package$.MODULE$.CRing().apply(this.evidence$1).one();
         }

         return var1;
      }

      public Map elements() {
         return (Map)this.safeLongFactors().elements().map((x0$1) -> {
            if (x0$1 != null) {
               SafeLong f = (SafeLong)x0$1._1();
               int exp = x0$1._2$mcI$sp();
               Tuple2 var2 = new Tuple2(package$.MODULE$.CRing().apply(this.evidence$1).fromBigInt(f.toBigInt()), BoxesRunTime.boxToInteger(exp));
               return var2;
            } else {
               throw new MatchError(x0$1);
            }
         });
      }

      public WrapDecomposition copy(final Factors safeLongFactors, final CommutativeRing evidence$1) {
         return new WrapDecomposition(safeLongFactors, evidence$1);
      }

      public Factors copy$default$1() {
         return this.safeLongFactors();
      }

      public String productPrefix() {
         return "WrapDecomposition";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.safeLongFactors();
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
         return x$1 instanceof WrapDecomposition;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "safeLongFactors";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var7;
         if (this != x$1) {
            label53: {
               boolean var2;
               if (x$1 instanceof WrapDecomposition) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label36: {
                     label35: {
                        WrapDecomposition var4 = (WrapDecomposition)x$1;
                        Factors var10000 = this.safeLongFactors();
                        Factors var5 = var4.safeLongFactors();
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

      public WrapDecomposition(final Factors safeLongFactors, final CommutativeRing evidence$1) {
         this.safeLongFactors = safeLongFactors;
         this.evidence$1 = evidence$1;
         Product.$init$(this);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class WrapDecomposition$ implements Serializable {
      public static final WrapDecomposition$ MODULE$ = new WrapDecomposition$();

      public final String toString() {
         return "WrapDecomposition";
      }

      public WrapDecomposition apply(final Factors safeLongFactors, final CommutativeRing evidence$1) {
         return new WrapDecomposition(safeLongFactors, evidence$1);
      }

      public Option unapply(final WrapDecomposition x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.safeLongFactors()));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(WrapDecomposition$.class);
      }
   }
}
