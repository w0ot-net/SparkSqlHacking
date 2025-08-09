package breeze.optimize.proximal;

import breeze.linalg.DenseVector;
import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mc\u0001B\f\u0019\u0001~A\u0001\"\u000f\u0001\u0003\u0016\u0004%\tA\u000f\u0005\t\t\u0002\u0011\t\u0012)A\u0005w!)Q\t\u0001C\u0001\r\")\u0011\n\u0001C\u0001\u0015\"9!\u000bAA\u0001\n\u0003\u0019\u0006bB+\u0001#\u0003%\tA\u0016\u0005\bC\u0002\t\t\u0011\"\u0011c\u0011\u001dY\u0007!!A\u0005\u00021Dq\u0001\u001d\u0001\u0002\u0002\u0013\u0005\u0011\u000fC\u0004x\u0001\u0005\u0005I\u0011\t=\t\u0011}\u0004\u0011\u0011!C\u0001\u0003\u0003A\u0011\"a\u0003\u0001\u0003\u0003%\t%!\u0004\t\u0013\u0005E\u0001!!A\u0005B\u0005M\u0001\"CA\u000b\u0001\u0005\u0005I\u0011IA\f\u0011%\tI\u0002AA\u0001\n\u0003\nYbB\u0005\u0002 a\t\t\u0011#\u0001\u0002\"\u0019Aq\u0003GA\u0001\u0012\u0003\t\u0019\u0003\u0003\u0004F#\u0011\u0005\u00111\b\u0005\n\u0003+\t\u0012\u0011!C#\u0003/A\u0011\"!\u0010\u0012\u0003\u0003%\t)a\u0010\t\u0013\u0005\r\u0013#!A\u0005\u0002\u0006\u0015\u0003\"CA)#\u0005\u0005I\u0011BA*\u00059\u0001&o\u001c=j[\u0006dG*\u001b8fCJT!!\u0007\u000e\u0002\u0011A\u0014x\u000e_5nC2T!a\u0007\u000f\u0002\u0011=\u0004H/[7ju\u0016T\u0011!H\u0001\u0007EJ,WM_3\u0004\u0001M)\u0001\u0001\t\u0014+[A\u0011\u0011\u0005J\u0007\u0002E)\t1%A\u0003tG\u0006d\u0017-\u0003\u0002&E\t1\u0011I\\=SK\u001a\u0004\"a\n\u0015\u000e\u0003aI!!\u000b\r\u0003\u0011A\u0013x\u000e_5nC2\u0004\"!I\u0016\n\u00051\u0012#a\u0002)s_\u0012,8\r\u001e\t\u0003]Yr!a\f\u001b\u000f\u0005A\u001aT\"A\u0019\u000b\u0005Ir\u0012A\u0002\u001fs_>$h(C\u0001$\u0013\t)$%A\u0004qC\u000e\\\u0017mZ3\n\u0005]B$\u0001D*fe&\fG.\u001b>bE2,'BA\u001b#\u0003\u0005\u0019W#A\u001e\u0011\u0007qz\u0014)D\u0001>\u0015\tqD$\u0001\u0004mS:\fGnZ\u0005\u0003\u0001v\u00121\u0002R3og\u00164Vm\u0019;peB\u0011\u0011EQ\u0005\u0003\u0007\n\u0012a\u0001R8vE2,\u0017AA2!\u0003\u0019a\u0014N\\5u}Q\u0011q\t\u0013\t\u0003O\u0001AQ!O\u0002A\u0002m\nA\u0001\u001d:pqR\u00191J\u0014)\u0011\u0005\u0005b\u0015BA'#\u0005\u0011)f.\u001b;\t\u000b=#\u0001\u0019A\u001e\u0002\u0003aDq!\u0015\u0003\u0011\u0002\u0003\u0007\u0011)A\u0002sQ>\fAaY8qsR\u0011q\t\u0016\u0005\bs\u0015\u0001\n\u00111\u0001<\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012a\u0016\u0016\u0003wa[\u0013!\u0017\t\u00035~k\u0011a\u0017\u0006\u00039v\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005y\u0013\u0013AC1o]>$\u0018\r^5p]&\u0011\u0001m\u0017\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001d!\t!\u0017.D\u0001f\u0015\t1w-\u0001\u0003mC:<'\"\u00015\u0002\t)\fg/Y\u0005\u0003U\u0016\u0014aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLX#A7\u0011\u0005\u0005r\u0017BA8#\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\t\u0011X\u000f\u0005\u0002\"g&\u0011AO\t\u0002\u0004\u0003:L\bb\u0002<\n\u0003\u0003\u0005\r!\\\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0003e\u00042A_?s\u001b\u0005Y(B\u0001?#\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003}n\u0014\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u00111AA\u0005!\r\t\u0013QA\u0005\u0004\u0003\u000f\u0011#a\u0002\"p_2,\u0017M\u001c\u0005\bm.\t\t\u00111\u0001s\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0007\r\fy\u0001C\u0004w\u0019\u0005\u0005\t\u0019A7\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012!\\\u0001\ti>\u001cFO]5oOR\t1-\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003\u0007\ti\u0002C\u0004w\u001f\u0005\u0005\t\u0019\u0001:\u0002\u001dA\u0013x\u000e_5nC2d\u0015N\\3beB\u0011q%E\n\u0006#\u0005\u0015\u0012\u0011\u0007\t\u0007\u0003O\ticO$\u000e\u0005\u0005%\"bAA\u0016E\u00059!/\u001e8uS6,\u0017\u0002BA\u0018\u0003S\u0011\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c82!\u0011\t\u0019$!\u000f\u000e\u0005\u0005U\"bAA\u001cO\u0006\u0011\u0011n\\\u0005\u0004o\u0005UBCAA\u0011\u0003\u0015\t\u0007\u000f\u001d7z)\r9\u0015\u0011\t\u0005\u0006sQ\u0001\raO\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t9%!\u0014\u0011\t\u0005\nIeO\u0005\u0004\u0003\u0017\u0012#AB(qi&|g\u000e\u0003\u0005\u0002PU\t\t\u00111\u0001H\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003+\u00022\u0001ZA,\u0013\r\tI&\u001a\u0002\u0007\u001f\nTWm\u0019;"
)
public class ProximalLinear implements Proximal, Product, Serializable {
   private final DenseVector c;

   public static Option unapply(final ProximalLinear x$0) {
      return ProximalLinear$.MODULE$.unapply(x$0);
   }

   public static ProximalLinear apply(final DenseVector c) {
      return ProximalLinear$.MODULE$.apply(c);
   }

   public static Function1 andThen(final Function1 g) {
      return ProximalLinear$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return ProximalLinear$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public double prox$default$2() {
      return Proximal.prox$default$2$(this);
   }

   public double valueAt(final DenseVector x) {
      return Proximal.valueAt$(this, x);
   }

   public DenseVector c() {
      return this.c;
   }

   public void prox(final DenseVector x, final double rho) {
      int index$macro$2 = 0;

      for(int limit$macro$4 = x.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
         x.update$mcD$sp(index$macro$2, x.apply$mcD$sp(index$macro$2) - this.c().apply$mcD$sp(index$macro$2) / rho);
      }

   }

   public ProximalLinear copy(final DenseVector c) {
      return new ProximalLinear(c);
   }

   public DenseVector copy$default$1() {
      return this.c();
   }

   public String productPrefix() {
      return "ProximalLinear";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.c();
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
      return x$1 instanceof ProximalLinear;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "c";
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
      boolean var7;
      if (this != x$1) {
         label53: {
            boolean var2;
            if (x$1 instanceof ProximalLinear) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label36: {
                  label35: {
                     ProximalLinear var4 = (ProximalLinear)x$1;
                     DenseVector var10000 = this.c();
                     DenseVector var5 = var4.c();
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

   public ProximalLinear(final DenseVector c) {
      this.c = c;
      Proximal.$init$(this);
      Product.$init$(this);
   }
}
