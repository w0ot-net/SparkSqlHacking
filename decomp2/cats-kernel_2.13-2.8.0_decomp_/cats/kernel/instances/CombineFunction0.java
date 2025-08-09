package cats.kernel.instances;

import cats.kernel.Semigroup;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.util.control.TailCalls;
import scala.util.control.TailCalls.;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055g!B\u000f\u001f\u0005z!\u0003\u0002\u0003&\u0001\u0005+\u0007I\u0011A&\t\u00111\u0003!\u0011#Q\u0001\n1B\u0001\"\u0014\u0001\u0003\u0016\u0004%\ta\u0013\u0005\t\u001d\u0002\u0011\t\u0012)A\u0005Y!Aq\n\u0001BK\u0002\u0013\u0005\u0001\u000b\u0003\u0005V\u0001\tE\t\u0015!\u0003R\u0011\u00151\u0006\u0001\"\u0001X\u0011\u0015i\u0006\u0001\"\u0003_\u0011\u0015\u0001\b\u0001\"\u0012r\u0011\u001d\u0011\b!!A\u0005\u0002MDq! \u0001\u0012\u0002\u0013\u0005a\u0010C\u0005\u0002\u0018\u0001\t\n\u0011\"\u0001\u0002\u001a!I\u0011Q\u0004\u0001\u0012\u0002\u0013\u0005\u0011q\u0004\u0005\n\u0003O\u0001\u0011\u0011!C!\u0003SA\u0011\"a\u000f\u0001\u0003\u0003%\t!!\u0010\t\u0013\u0005\u0015\u0003!!A\u0005\u0002\u0005\u001d\u0003\"CA'\u0001\u0005\u0005I\u0011IA(\u0011%\ti\u0006AA\u0001\n\u0003\ty\u0006C\u0005\u0002j\u0001\t\t\u0011\"\u0011\u0002l!I\u0011q\u000e\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u000f\u0005\n\u0003g\u0002\u0011\u0011!C!\u0003k:!\"!\u001f\u001f\u0003\u0003E\tAHA>\r%ib$!A\t\u0002y\ti\b\u0003\u0004W/\u0011\u0005\u0011\u0011\u0012\u0005\n\u0003\u0017;\u0012\u0011!C#\u0003\u001bC\u0001\u0002]\f\u0002\u0002\u0013\u0005\u0015q\u0012\u0005\n\u0003G;\u0012\u0011!CA\u0003KC\u0011\"a1\u0018\u0003\u0003%I!!2\u0003!\r{WNY5oK\u001a+hn\u0019;j_:\u0004$BA\u0010!\u0003%Ign\u001d;b]\u000e,7O\u0003\u0002\"E\u000511.\u001a:oK2T\u0011aI\u0001\u0005G\u0006$8/\u0006\u0002&cM)\u0001A\n\u0017<}A\u0011qEK\u0007\u0002Q)\t\u0011&A\u0003tG\u0006d\u0017-\u0003\u0002,Q\t1\u0011I\\=SK\u001a\u00042aJ\u00170\u0013\tq\u0003FA\u0005Gk:\u001cG/[8oaA\u0011\u0001'\r\u0007\u0001\t\u0015\u0011\u0004A1\u00015\u0005\u0005\t5\u0001A\t\u0003ka\u0002\"a\n\u001c\n\u0005]B#a\u0002(pi\"Lgn\u001a\t\u0003OeJ!A\u000f\u0015\u0003\u0007\u0005s\u0017\u0010\u0005\u0002(y%\u0011Q\b\u000b\u0002\b!J|G-^2u!\tytI\u0004\u0002A\u000b:\u0011\u0011\tR\u0007\u0002\u0005*\u00111iM\u0001\u0007yI|w\u000e\u001e \n\u0003%J!A\u0012\u0015\u0002\u000fA\f7m[1hK&\u0011\u0001*\u0013\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003\r\"\nA\u0001\\3giV\tA&A\u0003mK\u001a$\b%A\u0003sS\u001eDG/\u0001\u0004sS\u001eDG\u000fI\u0001\u0006g\u0016l\u0017.Q\u000b\u0002#B\u0019!kU\u0018\u000e\u0003\u0001J!\u0001\u0016\u0011\u0003\u0013M+W.[4s_V\u0004\u0018AB:f[&\f\u0005%\u0001\u0004=S:LGO\u0010\u000b\u00051j[F\fE\u0002Z\u0001=j\u0011A\b\u0005\u0006\u0015\u001e\u0001\r\u0001\f\u0005\u0006\u001b\u001e\u0001\r\u0001\f\u0005\u0006\u001f\u001e\u0001\r!U\u0001\u0005G\u0006dG\u000e\u0006\u0002`]B\u0019\u0001m[\u0018\u000f\u0005\u0005DgB\u00012f\u001d\t\u00015-\u0003\u0002eQ\u0005!Q\u000f^5m\u0013\t1w-A\u0004d_:$(o\u001c7\u000b\u0005\u0011D\u0013BA5k\u0003%!\u0016-\u001b7DC2d7O\u0003\u0002gO&\u0011A.\u001c\u0002\b)\u0006LGNU3d\u0015\tI'\u000eC\u0003p\u0011\u0001\u0007A&\u0001\u0002g]\u0006)\u0011\r\u001d9msR\tq&\u0001\u0003d_BLXC\u0001;x)\u0011)\bP_>\u0011\u0007e\u0003a\u000f\u0005\u00021o\u0012)!G\u0003b\u0001i!9!J\u0003I\u0001\u0002\u0004I\bcA\u0014.m\"9QJ\u0003I\u0001\u0002\u0004I\bbB(\u000b!\u0003\u0005\r\u0001 \t\u0004%N3\u0018AD2paf$C-\u001a4bk2$H%M\u000b\u0004\u007f\u0006UQCAA\u0001U\ra\u00131A\u0016\u0003\u0003\u000b\u0001B!a\u0002\u0002\u00125\u0011\u0011\u0011\u0002\u0006\u0005\u0003\u0017\ti!A\u0005v]\u000eDWmY6fI*\u0019\u0011q\u0002\u0015\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002\u0014\u0005%!!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0012)!g\u0003b\u0001i\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012TcA@\u0002\u001c\u0011)!\u0007\u0004b\u0001i\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\u001aT\u0003BA\u0011\u0003K)\"!a\t+\u0007E\u000b\u0019\u0001B\u00033\u001b\t\u0007A'A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003W\u0001B!!\f\u000285\u0011\u0011q\u0006\u0006\u0005\u0003c\t\u0019$\u0001\u0003mC:<'BAA\u001b\u0003\u0011Q\u0017M^1\n\t\u0005e\u0012q\u0006\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\u0005}\u0002cA\u0014\u0002B%\u0019\u00111\t\u0015\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0007a\nI\u0005C\u0005\u0002LA\t\t\u00111\u0001\u0002@\u0005\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!!\u0015\u0011\u000b\u0005M\u0013\u0011\f\u001d\u000e\u0005\u0005U#bAA,Q\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005m\u0013Q\u000b\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002b\u0005\u001d\u0004cA\u0014\u0002d%\u0019\u0011Q\r\u0015\u0003\u000f\t{w\u000e\\3b]\"A\u00111\n\n\u0002\u0002\u0003\u0007\u0001(\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA\u0016\u0003[B\u0011\"a\u0013\u0014\u0003\u0003\u0005\r!a\u0010\u0002\u0011!\f7\u000f[\"pI\u0016$\"!a\u0010\u0002\r\u0015\fX/\u00197t)\u0011\t\t'a\u001e\t\u0011\u0005-S#!AA\u0002a\n\u0001cQ8nE&tWMR;oGRLwN\u001c\u0019\u0011\u0005e;2\u0003B\f'\u0003\u007f\u0002B!!!\u0002\b6\u0011\u00111\u0011\u0006\u0005\u0003\u000b\u000b\u0019$\u0001\u0002j_&\u0019\u0001*a!\u0015\u0005\u0005m\u0014\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005-R\u0003BAI\u0003/#\u0002\"a%\u0002\u001a\u0006u\u0015q\u0014\t\u00053\u0002\t)\nE\u00021\u0003/#QA\r\u000eC\u0002QBaA\u0013\u000eA\u0002\u0005m\u0005\u0003B\u0014.\u0003+Ca!\u0014\u000eA\u0002\u0005m\u0005BB(\u001b\u0001\u0004\t\t\u000b\u0005\u0003S'\u0006U\u0015aB;oCB\u0004H._\u000b\u0005\u0003O\u000bI\f\u0006\u0003\u0002*\u0006u\u0006#B\u0014\u0002,\u0006=\u0016bAAWQ\t1q\n\u001d;j_:\u0004\u0012bJAY\u0003k\u000b),a/\n\u0007\u0005M\u0006F\u0001\u0004UkBdWm\r\t\u0005O5\n9\fE\u00021\u0003s#QAM\u000eC\u0002Q\u0002BAU*\u00028\"I\u0011qX\u000e\u0002\u0002\u0003\u0007\u0011\u0011Y\u0001\u0004q\u0012\u0002\u0004\u0003B-\u0001\u0003o\u000bAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a2\u0011\t\u00055\u0012\u0011Z\u0005\u0005\u0003\u0017\fyC\u0001\u0004PE*,7\r\u001e"
)
public final class CombineFunction0 implements Function0, Product, Serializable {
   private final Function0 left;
   private final Function0 right;
   private final Semigroup semiA;

   public static Option unapply(final CombineFunction0 x$0) {
      return CombineFunction0$.MODULE$.unapply(x$0);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean apply$mcZ$sp() {
      return Function0.apply$mcZ$sp$(this);
   }

   public byte apply$mcB$sp() {
      return Function0.apply$mcB$sp$(this);
   }

   public char apply$mcC$sp() {
      return Function0.apply$mcC$sp$(this);
   }

   public double apply$mcD$sp() {
      return Function0.apply$mcD$sp$(this);
   }

   public float apply$mcF$sp() {
      return Function0.apply$mcF$sp$(this);
   }

   public int apply$mcI$sp() {
      return Function0.apply$mcI$sp$(this);
   }

   public long apply$mcJ$sp() {
      return Function0.apply$mcJ$sp$(this);
   }

   public short apply$mcS$sp() {
      return Function0.apply$mcS$sp$(this);
   }

   public void apply$mcV$sp() {
      Function0.apply$mcV$sp$(this);
   }

   public String toString() {
      return Function0.toString$(this);
   }

   public Function0 left() {
      return this.left;
   }

   public Function0 right() {
      return this.right;
   }

   public Semigroup semiA() {
      return this.semiA;
   }

   private TailCalls.TailRec call(final Function0 fn) {
      TailCalls.TailRec var2;
      if (fn instanceof CombineFunction0) {
         CombineFunction0 var4 = (CombineFunction0)fn;
         var2 = .MODULE$.tailcall(() -> this.call(var4.left())).flatMap((la) -> .MODULE$.tailcall(() -> this.call(var4.right())).map((ra) -> var4.semiA().combine(la, ra)));
      } else {
         var2 = .MODULE$.done(fn.apply());
      }

      return var2;
   }

   public final Object apply() {
      return this.call(this).result();
   }

   public CombineFunction0 copy(final Function0 left, final Function0 right, final Semigroup semiA) {
      return new CombineFunction0(left, right, semiA);
   }

   public Function0 copy$default$1() {
      return this.left();
   }

   public Function0 copy$default$2() {
      return this.right();
   }

   public Semigroup copy$default$3() {
      return this.semiA();
   }

   public String productPrefix() {
      return "CombineFunction0";
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
            var10000 = this.semiA();
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
      return x$1 instanceof CombineFunction0;
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
            var10000 = "semiA";
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
            if (x$1 instanceof CombineFunction0) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label51: {
                  label50: {
                     label60: {
                        CombineFunction0 var4 = (CombineFunction0)x$1;
                        Function0 var10000 = this.left();
                        Function0 var5 = var4.left();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label60;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label60;
                        }

                        var10000 = this.right();
                        Function0 var6 = var4.right();
                        if (var10000 == null) {
                           if (var6 != null) {
                              break label60;
                           }
                        } else if (!var10000.equals(var6)) {
                           break label60;
                        }

                        Semigroup var9 = this.semiA();
                        Semigroup var7 = var4.semiA();
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

   public CombineFunction0(final Function0 left, final Function0 right, final Semigroup semiA) {
      this.left = left;
      this.right = right;
      this.semiA = semiA;
      Function0.$init$(this);
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
