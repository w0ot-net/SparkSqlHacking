package breeze.signal;

import breeze.util.Opt;
import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ed!\u0002\u0014(\u0003\u0003a\u0003\"B\u001a\u0001\t\u0003!t!B\u001c(\u0011\u0003Ad!\u0002\u0014(\u0011\u0003I\u0004\"B\u001a\u0004\t\u0003\u0001u!B!\u0004\u0011\u0003\u0013e!\u0002#\u0004\u0011\u0003+\u0005\"B\u001a\u0007\t\u0003)\u0006b\u0002,\u0007\u0003\u0003%\te\u0016\u0005\bA\u001a\t\t\u0011\"\u0001b\u0011\u001d)g!!A\u0005\u0002\u0019Dq\u0001\u001c\u0004\u0002\u0002\u0013\u0005S\u000eC\u0004u\r\u0005\u0005I\u0011A;\t\u000fi4\u0011\u0011!C!w\"9APBA\u0001\n\u0003j\bb\u0002@\u0007\u0003\u0003%Ia \u0004\u0007\u0003\u000f\u0019\u0001)!\u0003\t\u0013\u0005-\u0001C!f\u0001\n\u0003\t\u0007\"CA\u0007!\tE\t\u0015!\u0003c\u0011\u0019\u0019\u0004\u0003\"\u0001\u0002\u0010!I\u0011Q\u0003\t\u0002\u0002\u0013\u0005\u0011q\u0003\u0005\n\u00037\u0001\u0012\u0013!C\u0001\u0003;AqA\u0016\t\u0002\u0002\u0013\u0005s\u000bC\u0004a!\u0005\u0005I\u0011A1\t\u0011\u0015\u0004\u0012\u0011!C\u0001\u0003gAq\u0001\u001c\t\u0002\u0002\u0013\u0005S\u000e\u0003\u0005u!\u0005\u0005I\u0011AA\u001c\u0011%\tY\u0004EA\u0001\n\u0003\ni\u0004C\u0004{!\u0005\u0005I\u0011I>\t\u000fq\u0004\u0012\u0011!C!{\"I\u0011\u0011\t\t\u0002\u0002\u0013\u0005\u00131I\u0004\n\u0003\u000f\u001a\u0011\u0011!E\u0001\u0003\u00132\u0011\"a\u0002\u0004\u0003\u0003E\t!a\u0013\t\rM\u0002C\u0011AA2\u0011\u001da\b%!A\u0005FuD\u0011\"!\u001a!\u0003\u0003%\t)a\u001a\t\u0013\u0005-\u0004%!A\u0005\u0002\u00065\u0004b\u0002@!\u0003\u0003%Ia \u0002\u000e\u001fB$h)\u001b7uKJ$\u0016\r]:\u000b\u0005!J\u0013AB:jO:\fGNC\u0001+\u0003\u0019\u0011'/Z3{K\u000e\u00011C\u0001\u0001.!\tq\u0013'D\u00010\u0015\t\u0001\u0014&\u0001\u0003vi&d\u0017B\u0001\u001a0\u0005\ry\u0005\u000f^\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003U\u0002\"A\u000e\u0001\u000e\u0003\u001d\nQb\u00149u\r&dG/\u001a:UCB\u001c\bC\u0001\u001c\u0004'\t\u0019!\b\u0005\u0002<}5\tAHC\u0001>\u0003\u0015\u00198-\u00197b\u0013\tyDH\u0001\u0004B]f\u0014VM\u001a\u000b\u0002q\u0005I\u0011)\u001e;p[\u0006$\u0018n\u0019\t\u0003\u0007\u001ai\u0011a\u0001\u0002\n\u0003V$x.\\1uS\u000e\u001cBAB\u001bG\u0013B\u00111hR\u0005\u0003\u0011r\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002K%:\u00111\n\u0015\b\u0003\u0019>k\u0011!\u0014\u0006\u0003\u001d.\na\u0001\u0010:p_Rt\u0014\"A\u001f\n\u0005Ec\u0014a\u00029bG.\fw-Z\u0005\u0003'R\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!!\u0015\u001f\u0015\u0003\t\u000bQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u0001-\u0011\u0005esV\"\u0001.\u000b\u0005mc\u0016\u0001\u00027b]\u001eT\u0011!X\u0001\u0005U\u00064\u0018-\u0003\u0002`5\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012A\u0019\t\u0003w\rL!\u0001\u001a\u001f\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005\u001dT\u0007CA\u001ei\u0013\tIGHA\u0002B]fDqa\u001b\u0006\u0002\u0002\u0003\u0007!-A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002]B\u0019qN]4\u000e\u0003AT!!\u001d\u001f\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002ta\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\t1\u0018\u0010\u0005\u0002<o&\u0011\u0001\u0010\u0010\u0002\b\u0005>|G.Z1o\u0011\u001dYG\"!AA\u0002\u001d\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002E\u0006AAo\\*ue&tw\rF\u0001Y\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t\t\u0001E\u0002Z\u0003\u0007I1!!\u0002[\u0005\u0019y%M[3di\n1\u0011J\u001c;PaR\u001cB\u0001E\u001bG\u0013\u0006\ta.\u0001\u0002oAQ!\u0011\u0011CA\n!\t\u0019\u0005\u0003\u0003\u0004\u0002\fM\u0001\rAY\u0001\u0005G>\u0004\u0018\u0010\u0006\u0003\u0002\u0012\u0005e\u0001\u0002CA\u0006)A\u0005\t\u0019\u00012\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u0011q\u0004\u0016\u0004E\u0006\u00052FAA\u0012!\u0011\t)#a\f\u000e\u0005\u0005\u001d\"\u0002BA\u0015\u0003W\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u00055B(\u0001\u0006b]:|G/\u0019;j_:LA!!\r\u0002(\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0015\u0007\u001d\f)\u0004C\u0004l1\u0005\u0005\t\u0019\u00012\u0015\u0007Y\fI\u0004C\u0004l5\u0005\u0005\t\u0019A4\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u00041\u0006}\u0002bB6\u001c\u0003\u0003\u0005\rAY\u0001\u0007KF,\u0018\r\\:\u0015\u0007Y\f)\u0005C\u0004l=\u0005\u0005\t\u0019A4\u0002\r%sGo\u00149u!\t\u0019\u0005eE\u0003!\u0003\u001b\nI\u0006E\u0004\u0002P\u0005U#-!\u0005\u000e\u0005\u0005E#bAA*y\u00059!/\u001e8uS6,\u0017\u0002BA,\u0003#\u0012\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c82!\u0011\tY&!\u0019\u000e\u0005\u0005u#bAA09\u0006\u0011\u0011n\\\u0005\u0004'\u0006uCCAA%\u0003\u0015\t\u0007\u000f\u001d7z)\u0011\t\t\"!\u001b\t\r\u0005-1\u00051\u0001c\u0003\u001d)h.\u00199qYf$B!a\u001c\u0002vA!1(!\u001dc\u0013\r\t\u0019\b\u0010\u0002\u0007\u001fB$\u0018n\u001c8\t\u0013\u0005]D%!AA\u0002\u0005E\u0011a\u0001=%a\u0001"
)
public abstract class OptFilterTaps extends Opt {
   public static class Automatic$ extends OptFilterTaps implements Product, Serializable {
      public static final Automatic$ MODULE$ = new Automatic$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "Automatic";
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
         return x$1 instanceof Automatic$;
      }

      public int hashCode() {
         return -617328117;
      }

      public String toString() {
         return "Automatic";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Automatic$.class);
      }
   }

   public static class IntOpt extends OptFilterTaps implements Product, Serializable {
      private final int n;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int n() {
         return this.n;
      }

      public IntOpt copy(final int n) {
         return new IntOpt(n);
      }

      public int copy$default$1() {
         return this.n();
      }

      public String productPrefix() {
         return "IntOpt";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = BoxesRunTime.boxToInteger(this.n());
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
         return x$1 instanceof IntOpt;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "n";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, this.n());
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
               if (x$1 instanceof IntOpt) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  IntOpt var4 = (IntOpt)x$1;
                  if (this.n() == var4.n() && var4.canEqual(this)) {
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

      public IntOpt(final int n) {
         this.n = n;
         Product.$init$(this);
      }
   }

   public static class IntOpt$ extends AbstractFunction1 implements Serializable {
      public static final IntOpt$ MODULE$ = new IntOpt$();

      public final String toString() {
         return "IntOpt";
      }

      public IntOpt apply(final int n) {
         return new IntOpt(n);
      }

      public Option unapply(final IntOpt x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(BoxesRunTime.boxToInteger(x$0.n())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(IntOpt$.class);
      }
   }
}
