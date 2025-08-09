package spire.random;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import scala.util.Either;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015d\u0001\u0002\f\u0018\u0001rA\u0001B\u0011\u0001\u0003\u0016\u0004%\ta\u0011\u0005\t\u0015\u0002\u0011\t\u0012)A\u0005\t\")1\n\u0001C\u0001\u0019\"9q\nAA\u0001\n\u0003\u0001\u0006bB,\u0001#\u0003%\t\u0001\u0017\u0005\bK\u0002\t\t\u0011\"\u0011g\u0011\u001dy\u0007!!A\u0005\u0002ADq\u0001\u001e\u0001\u0002\u0002\u0013\u0005Q\u000fC\u0004y\u0001\u0005\u0005I\u0011I=\t\u0013\u0005\u0005\u0001!!A\u0005\u0002\u0005\r\u0001\"CA\u0007\u0001\u0005\u0005I\u0011IA\b\u0011%\t\u0019\u0002AA\u0001\n\u0003\n)\u0002C\u0005\u0002\u0018\u0001\t\t\u0011\"\u0011\u0002\u001a!I\u00111\u0004\u0001\u0002\u0002\u0013\u0005\u0013QD\u0004\n\u0003C9\u0012\u0011!E\u0001\u0003G1\u0001BF\f\u0002\u0002#\u0005\u0011Q\u0005\u0005\u0007\u0017B!\t!!\r\t\u0013\u0005]\u0001#!A\u0005F\u0005e\u0001\"CA\u001a!\u0005\u0005I\u0011QA\u001b\u0011%\t\u0019\u0005EA\u0001\n\u0003\u000b)\u0005C\u0005\u0002\\A\t\t\u0011\"\u0003\u0002^\t!a*\u001a=u\u0015\tA\u0012$\u0001\u0004sC:$w.\u001c\u0006\u00025\u0005)1\u000f]5sK\u000e\u0001QCA\u000f+'\u0015\u0001a\u0004J\u001a7!\ty\"%D\u0001!\u0015\u0005\t\u0013!B:dC2\f\u0017BA\u0012!\u0005\u0019\te.\u001f*fMB\u0019QE\n\u0015\u000e\u0003]I!aJ\f\u0003\u0005=\u0003\bCA\u0015+\u0019\u0001!aa\u000b\u0001\u0005\u0006\u0004a#!A!\u0012\u00055\u0002\u0004CA\u0010/\u0013\ty\u0003EA\u0004O_RD\u0017N\\4\u0011\u0005}\t\u0014B\u0001\u001a!\u0005\r\te.\u001f\t\u0003?QJ!!\u000e\u0011\u0003\u000fA\u0013x\u000eZ;diB\u0011qg\u0010\b\u0003qur!!\u000f\u001f\u000e\u0003iR!aO\u000e\u0002\rq\u0012xn\u001c;?\u0013\u0005\t\u0013B\u0001 !\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001Q!\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005y\u0002\u0013!\u00014\u0016\u0003\u0011\u0003BaH#HQ%\u0011a\t\t\u0002\n\rVt7\r^5p]F\u0002\"!\n%\n\u0005%;\"!C$f]\u0016\u0014\u0018\r^8s\u0003\t1\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0003\u001b:\u00032!\n\u0001)\u0011\u0015\u00115\u00011\u0001E\u0003\u0011\u0019w\u000e]=\u0016\u0005E#FC\u0001*V!\r)\u0003a\u0015\t\u0003SQ#Qa\u000b\u0003C\u00021BqA\u0011\u0003\u0011\u0002\u0003\u0007a\u000b\u0005\u0003 \u000b\u001e\u001b\u0016AD2paf$C-\u001a4bk2$H%M\u000b\u00033\u0012,\u0012A\u0017\u0016\u0003\tn[\u0013\u0001\u0018\t\u0003;\nl\u0011A\u0018\u0006\u0003?\u0002\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005\u0005\u0004\u0013AC1o]>$\u0018\r^5p]&\u00111M\u0018\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,G!B\u0016\u0006\u0005\u0004a\u0013!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001h!\tAW.D\u0001j\u0015\tQ7.\u0001\u0003mC:<'\"\u00017\u0002\t)\fg/Y\u0005\u0003]&\u0014aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLX#A9\u0011\u0005}\u0011\u0018BA:!\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\t\u0001d\u000fC\u0004x\u0011\u0005\u0005\t\u0019A9\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\u0005Q\bcA>\u007fa5\tAP\u0003\u0002~A\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005}d(\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!!\u0002\u0002\fA\u0019q$a\u0002\n\u0007\u0005%\u0001EA\u0004C_>dW-\u00198\t\u000f]T\u0011\u0011!a\u0001a\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\r9\u0017\u0011\u0003\u0005\bo.\t\t\u00111\u0001r\u0003!A\u0017m\u001d5D_\u0012,G#A9\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012aZ\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005\u0015\u0011q\u0004\u0005\bo:\t\t\u00111\u00011\u0003\u0011qU\r\u001f;\u0011\u0005\u0015\u00022\u0003\u0002\t\u001f\u0003O\u0001B!!\u000b\u000205\u0011\u00111\u0006\u0006\u0004\u0003[Y\u0017AA5p\u0013\r\u0001\u00151\u0006\u000b\u0003\u0003G\tQ!\u00199qYf,B!a\u000e\u0002>Q!\u0011\u0011HA !\u0011)\u0003!a\u000f\u0011\u0007%\ni\u0004B\u0003,'\t\u0007A\u0006\u0003\u0004C'\u0001\u0007\u0011\u0011\t\t\u0006?\u0015;\u00151H\u0001\bk:\f\u0007\u000f\u001d7z+\u0011\t9%a\u0015\u0015\t\u0005%\u0013Q\u000b\t\u0006?\u0005-\u0013qJ\u0005\u0004\u0003\u001b\u0002#AB(qi&|g\u000eE\u0003 \u000b\u001e\u000b\t\u0006E\u0002*\u0003'\"Qa\u000b\u000bC\u00021B\u0011\"a\u0016\u0015\u0003\u0003\u0005\r!!\u0017\u0002\u0007a$\u0003\u0007\u0005\u0003&\u0001\u0005E\u0013\u0001D<sSR,'+\u001a9mC\u000e,GCAA0!\rA\u0017\u0011M\u0005\u0004\u0003GJ'AB(cU\u0016\u001cG\u000f"
)
public class Next implements Op, Product, Serializable {
   private final Function1 f;

   public static Option unapply(final Next x$0) {
      return Next$.MODULE$.unapply(x$0);
   }

   public static Next apply(final Function1 f) {
      return Next$.MODULE$.apply(f);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Op flatMap(final Function1 f) {
      return Op.flatMap$(this, f);
   }

   public Op map(final Function1 f) {
      return Op.map$(this, f);
   }

   public final Either resume(final Generator gen) {
      return Op.resume$(this, gen);
   }

   public Object run(final Generator gen) {
      return Op.run$(this, gen);
   }

   public Function1 f() {
      return this.f;
   }

   public Next copy(final Function1 f) {
      return new Next(f);
   }

   public Function1 copy$default$1() {
      return this.f();
   }

   public String productPrefix() {
      return "Next";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.f();
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
      return x$1 instanceof Next;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "f";
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
            if (x$1 instanceof Next) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label36: {
                  label35: {
                     Next var4 = (Next)x$1;
                     Function1 var10000 = this.f();
                     Function1 var5 = var4.f();
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

   public Next(final Function1 f) {
      this.f = f;
      Op.$init$(this);
      Product.$init$(this);
   }
}
