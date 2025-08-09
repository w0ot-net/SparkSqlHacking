package spire.math;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%b\u0001\u0002\f\u0018\u0001rA\u0001B\r\u0001\u0003\u0016\u0004%\ta\r\u0005\to\u0001\u0011\t\u0012)A\u0005i!)\u0001\b\u0001C\u0001s!9Q\bAA\u0001\n\u0003q\u0004b\u0002!\u0001#\u0003%\t!\u0011\u0005\b\u0019\u0002\t\t\u0011\"\u0011N\u0011\u001d1\u0006!!A\u0005\u0002MBqa\u0016\u0001\u0002\u0002\u0013\u0005\u0001\fC\u0004_\u0001\u0005\u0005I\u0011I0\t\u000f\u0019\u0004\u0011\u0011!C\u0001O\"9A\u000eAA\u0001\n\u0003j\u0007bB8\u0001\u0003\u0003%\t\u0005\u001d\u0005\bc\u0002\t\t\u0011\"\u0011s\u0011\u001d\u0019\b!!A\u0005BQ<qA^\f\u0002\u0002#\u0005qOB\u0004\u0017/\u0005\u0005\t\u0012\u0001=\t\ra\u0002B\u0011AA\u0005\u0011\u001d\t\b#!A\u0005FID\u0011\"a\u0003\u0011\u0003\u0003%\t)!\u0004\t\u0013\u0005E\u0001#!A\u0005\u0002\u0006M\u0001\"CA\u0010!\u0005\u0005I\u0011BA\u0011\u0005\u0019QU\r\u001e#j[*\u0011\u0001$G\u0001\u0005[\u0006$\bNC\u0001\u001b\u0003\u0015\u0019\b/\u001b:f\u0007\u0001\u0019B\u0001A\u000f$MA\u0011a$I\u0007\u0002?)\t\u0001%A\u0003tG\u0006d\u0017-\u0003\u0002#?\t1\u0011I\\=SK\u001a\u0004\"A\b\u0013\n\u0005\u0015z\"a\u0002)s_\u0012,8\r\u001e\t\u0003O=r!\u0001K\u0017\u000f\u0005%bS\"\u0001\u0016\u000b\u0005-Z\u0012A\u0002\u001fs_>$h(C\u0001!\u0013\tqs$A\u0004qC\u000e\\\u0017mZ3\n\u0005A\n$\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u0018 \u0003%!\u0017.\\3og&|g.F\u00015!\tqR'\u0003\u00027?\t\u0019\u0011J\u001c;\u0002\u0015\u0011LW.\u001a8tS>t\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0003uq\u0002\"a\u000f\u0001\u000e\u0003]AQAM\u0002A\u0002Q\nAaY8qsR\u0011!h\u0010\u0005\be\u0011\u0001\n\u00111\u00015\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012A\u0011\u0016\u0003i\r[\u0013\u0001\u0012\t\u0003\u000b*k\u0011A\u0012\u0006\u0003\u000f\"\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005%{\u0012AC1o]>$\u0018\r^5p]&\u00111J\u0012\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001O!\tyE+D\u0001Q\u0015\t\t&+\u0001\u0003mC:<'\"A*\u0002\t)\fg/Y\u0005\u0003+B\u0013aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRL\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u00033r\u0003\"A\b.\n\u0005m{\"aA!os\"9Q\fCA\u0001\u0002\u0004!\u0014a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001a!\r\tG-W\u0007\u0002E*\u00111mH\u0001\u000bG>dG.Z2uS>t\u0017BA3c\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u0005!\\\u0007C\u0001\u0010j\u0013\tQwDA\u0004C_>dW-\u00198\t\u000fuS\u0011\u0011!a\u00013\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\tqe\u000eC\u0004^\u0017\u0005\u0005\t\u0019\u0001\u001b\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012\u0001N\u0001\ti>\u001cFO]5oOR\ta*\u0001\u0004fcV\fGn\u001d\u000b\u0003QVDq!\u0018\b\u0002\u0002\u0003\u0007\u0011,\u0001\u0004KKR$\u0015.\u001c\t\u0003wA\u00192\u0001E=\u0000!\u0011QX\u0010\u000e\u001e\u000e\u0003mT!\u0001`\u0010\u0002\u000fI,h\u000e^5nK&\u0011ap\u001f\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\f\u0004\u0003BA\u0001\u0003\u000fi!!a\u0001\u000b\u0007\u0005\u0015!+\u0001\u0002j_&\u0019\u0001'a\u0001\u0015\u0003]\fQ!\u00199qYf$2AOA\b\u0011\u0015\u00114\u00031\u00015\u0003\u001d)h.\u00199qYf$B!!\u0006\u0002\u001cA!a$a\u00065\u0013\r\tIb\b\u0002\u0007\u001fB$\u0018n\u001c8\t\u0011\u0005uA#!AA\u0002i\n1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t\u0019\u0003E\u0002P\u0003KI1!a\nQ\u0005\u0019y%M[3di\u0002"
)
public class JetDim implements Product, Serializable {
   private final int dimension;

   public static Option unapply(final JetDim x$0) {
      return JetDim$.MODULE$.unapply(x$0);
   }

   public static JetDim apply(final int dimension) {
      return JetDim$.MODULE$.apply(dimension);
   }

   public static Function1 andThen(final Function1 g) {
      return JetDim$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return JetDim$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int dimension() {
      return this.dimension;
   }

   public JetDim copy(final int dimension) {
      return new JetDim(dimension);
   }

   public int copy$default$1() {
      return this.dimension();
   }

   public String productPrefix() {
      return "JetDim";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToInteger(this.dimension());
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
      return x$1 instanceof JetDim;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "dimension";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.dimension());
      return Statics.finalizeHash(var1, 1);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label49: {
            boolean var2;
            if (x$1 instanceof JetDim) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               JetDim var4 = (JetDim)x$1;
               if (this.dimension() == var4.dimension() && var4.canEqual(this)) {
                  break label49;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public JetDim(final int dimension) {
      this.dimension = dimension;
      Product.$init$(this);
      scala.Predef..MODULE$.require(dimension > 0);
   }
}
