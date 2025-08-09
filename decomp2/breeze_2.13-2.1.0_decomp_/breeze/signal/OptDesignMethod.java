package breeze.signal;

import breeze.util.Opt;
import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\ra!B\u000e\u001d\u0003\u0003\t\u0003\"\u0002\u0015\u0001\t\u0003Is!\u0002\u0017\u001d\u0011\u0003ic!B\u000e\u001d\u0011\u0003q\u0003\"\u0002\u0015\u0004\t\u0003)t!\u0002\u001c\u0004\u0011\u0003;d!B\u001d\u0004\u0011\u0003S\u0004\"\u0002\u0015\u0007\t\u0003Q\u0005bB&\u0007\u0003\u0003%\t\u0005\u0014\u0005\b+\u001a\t\t\u0011\"\u0001W\u0011\u001dQf!!A\u0005\u0002mCq!\u0019\u0004\u0002\u0002\u0013\u0005#\rC\u0004j\r\u0005\u0005I\u0011\u00016\t\u000f=4\u0011\u0011!C!a\"9\u0011OBA\u0001\n\u0003\u0012\bbB:\u0007\u0003\u0003%I\u0001^\u0004\u0006q\u000eA\t)\u001f\u0004\u0006u\u000eA\ti\u001f\u0005\u0006QE!\t\u0001 \u0005\b\u0017F\t\t\u0011\"\u0011M\u0011\u001d)\u0016#!A\u0005\u0002YCqAW\t\u0002\u0002\u0013\u0005Q\u0010C\u0004b#\u0005\u0005I\u0011\t2\t\u000f%\f\u0012\u0011!C\u0001\u007f\"9q.EA\u0001\n\u0003\u0002\bbB9\u0012\u0003\u0003%\tE\u001d\u0005\bgF\t\t\u0011\"\u0003u\u0005=y\u0005\u000f\u001e#fg&<g.T3uQ>$'BA\u000f\u001f\u0003\u0019\u0019\u0018n\u001a8bY*\tq$\u0001\u0004ce\u0016,'0Z\u0002\u0001'\t\u0001!\u0005\u0005\u0002$M5\tAE\u0003\u0002&=\u0005!Q\u000f^5m\u0013\t9CEA\u0002PaR\fa\u0001P5oSRtD#\u0001\u0016\u0011\u0005-\u0002Q\"\u0001\u000f\u0002\u001f=\u0003H\u000fR3tS\u001etW*\u001a;i_\u0012\u0004\"aK\u0002\u0014\u0005\ry\u0003C\u0001\u00194\u001b\u0005\t$\"\u0001\u001a\u0002\u000bM\u001c\u0017\r\\1\n\u0005Q\n$AB!osJ+g\rF\u0001.\u0003\u00191\u0015N]<j]B\u0011\u0001HB\u0007\u0002\u0007\t1a)\u001b:xS:\u001cBA\u0002\u0016<}A\u0011\u0001\u0007P\u0005\u0003{E\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002@\u000f:\u0011\u0001)\u0012\b\u0003\u0003\u0012k\u0011A\u0011\u0006\u0003\u0007\u0002\na\u0001\u0010:p_Rt\u0014\"\u0001\u001a\n\u0005\u0019\u000b\u0014a\u00029bG.\fw-Z\u0005\u0003\u0011&\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!AR\u0019\u0015\u0003]\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A'\u0011\u00059\u001bV\"A(\u000b\u0005A\u000b\u0016\u0001\u00027b]\u001eT\u0011AU\u0001\u0005U\u00064\u0018-\u0003\u0002U\u001f\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012a\u0016\t\u0003aaK!!W\u0019\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005q{\u0006C\u0001\u0019^\u0013\tq\u0016GA\u0002B]fDq\u0001\u0019\u0006\u0002\u0002\u0003\u0007q+A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002GB\u0019Am\u001a/\u000e\u0003\u0015T!AZ\u0019\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002iK\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\tYg\u000e\u0005\u00021Y&\u0011Q.\r\u0002\b\u0005>|G.Z1o\u0011\u001d\u0001G\"!AA\u0002q\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002/\u0006AAo\\*ue&tw\rF\u0001N\u000319(/\u001b;f%\u0016\u0004H.Y2f)\u0005)\bC\u0001(w\u0013\t9xJ\u0001\u0004PE*,7\r^\u0001\u0007\u0007\",'-_\u0019\u0011\u0005a\n\"AB\"iK\nL\u0018g\u0005\u0003\u0012UmrD#A=\u0015\u0005qs\bb\u00021\u0016\u0003\u0003\u0005\ra\u0016\u000b\u0004W\u0006\u0005\u0001b\u00021\u0018\u0003\u0003\u0005\r\u0001\u0018"
)
public abstract class OptDesignMethod extends Opt {
   public static class Firwin$ extends OptDesignMethod implements Product, Serializable {
      public static final Firwin$ MODULE$ = new Firwin$();

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
         return "Firwin";
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
         return x$1 instanceof Firwin$;
      }

      public int hashCode() {
         return 2104524173;
      }

      public String toString() {
         return "Firwin";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Firwin$.class);
      }
   }

   public static class Cheby1$ extends OptDesignMethod implements Product, Serializable {
      public static final Cheby1$ MODULE$ = new Cheby1$();

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
         return "Cheby1";
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
         return x$1 instanceof Cheby1$;
      }

      public int hashCode() {
         return 2017306170;
      }

      public String toString() {
         return "Cheby1";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Cheby1$.class);
      }
   }
}
