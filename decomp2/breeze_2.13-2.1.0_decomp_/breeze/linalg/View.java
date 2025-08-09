package breeze.linalg;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dba\u0002\u0014(!\u0003\r\n\u0003L\u0004\u0007\u0003K9\u0003\u0012\u0001\u001d\u0007\u000b\u0019:\u0003\u0012A\u001b\t\u000bY\u0012A\u0011A\u001c\t\u000bi\u0012A1A\u001e\b\u000b\t\u0013\u0001\u0012Q\"\u0007\u000b\u0015\u0013\u0001\u0012\u0011$\t\u000bY2A\u0011\u0001,\t\u000f]3\u0011\u0011!C!1\"9\u0011MBA\u0001\n\u0003\u0011\u0007b\u00024\u0007\u0003\u0003%\ta\u001a\u0005\b[\u001a\t\t\u0011\"\u0011o\u0011\u001d)h!!A\u0005\u0002YDq\u0001\u001f\u0004\u0002\u0002\u0013\u0005\u0013\u0010C\u0004{\r\u0005\u0005I\u0011I>\t\u000fq4\u0011\u0011!C\u0005{\u001e9\u00111\u0001\u0002\t\u0002\u0006\u0015aA\u0002\u001b\u0003\u0011\u0003\u000bI\u0002\u0003\u00047#\u0011\u0005\u00111\u0004\u0005\b/F\t\t\u0011\"\u0011Y\u0011\u001d\t\u0017#!A\u0005\u0002\tD\u0001BZ\t\u0002\u0002\u0013\u0005\u0011Q\u0004\u0005\b[F\t\t\u0011\"\u0011o\u0011!)\u0018#!A\u0005\u0002\u0005\u0005\u0002b\u0002=\u0012\u0003\u0003%\t%\u001f\u0005\buF\t\t\u0011\"\u0011|\u0011\u001da\u0018#!A\u0005\nu<q!a\u0002\u0003\u0011\u0003\u000bIAB\u0004\u0002\f\tA\t)!\u0004\t\rYbB\u0011AA\b\u0011\u001d9F$!A\u0005BaCq!\u0019\u000f\u0002\u0002\u0013\u0005!\r\u0003\u0005g9\u0005\u0005I\u0011AA\t\u0011\u001diG$!A\u0005B9D\u0001\"\u001e\u000f\u0002\u0002\u0013\u0005\u0011Q\u0003\u0005\bqr\t\t\u0011\"\u0011z\u0011\u001dQH$!A\u0005BmDq\u0001 \u000f\u0002\u0002\u0013%QP\u0001\u0003WS\u0016<(B\u0001\u0015*\u0003\u0019a\u0017N\\1mO*\t!&\u0001\u0004ce\u0016,'0Z\u0002\u0001'\t\u0001Q\u0006\u0005\u0002/c5\tqFC\u00011\u0003\u0015\u00198-\u00197b\u0013\t\u0011tF\u0001\u0004B]f\u0014VMZ\u0015\u0005\u0001EabA\u0001\u0003D_BL8C\u0001\u0002.\u0003\u0019a\u0014N\\5u}Q\t\u0001\b\u0005\u0002:\u00055\tq%A\rwS\u0016<\bK]3gKJ,gnY3Ge>l'i\\8mK\u0006tGC\u0001\u001f>!\tI\u0004\u0001C\u0003?\t\u0001\u0007q(A\u0001c!\tq\u0003)\u0003\u0002B_\t9!i\\8mK\u0006t\u0017a\u0002*fcVL'/\u001a\t\u0003\t\u001ai\u0011A\u0001\u0002\b%\u0016\fX/\u001b:f'\u00151Q\u0006P$K!\tq\u0003*\u0003\u0002J_\t9\u0001K]8ek\u000e$\bCA&T\u001d\ta\u0015K\u0004\u0002N!6\taJ\u0003\u0002PW\u00051AH]8pizJ\u0011\u0001M\u0005\u0003%>\nq\u0001]1dW\u0006<W-\u0003\u0002U+\na1+\u001a:jC2L'0\u00192mK*\u0011!k\f\u000b\u0002\u0007\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012!\u0017\t\u00035~k\u0011a\u0017\u0006\u00039v\u000bA\u0001\\1oO*\ta,\u0001\u0003kCZ\f\u0017B\u00011\\\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\t1\r\u0005\u0002/I&\u0011Qm\f\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003Q.\u0004\"AL5\n\u0005)|#aA!os\"9ANCA\u0001\u0002\u0004\u0019\u0017a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001p!\r\u00018\u000f[\u0007\u0002c*\u0011!oL\u0001\u000bG>dG.Z2uS>t\u0017B\u0001;r\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u0005}:\bb\u00027\r\u0003\u0003\u0005\r\u0001[\u0001\tQ\u0006\u001c\bnQ8eKR\t1-\u0001\u0005u_N#(/\u001b8h)\u0005I\u0016\u0001D<sSR,'+\u001a9mC\u000e,G#\u0001@\u0011\u0005i{\u0018bAA\u00017\n1qJ\u00196fGR\fAaQ8qsB\u0011A)E\u0001\u0007!J,g-\u001a:\u0011\u0005\u0011c\"A\u0002)sK\u001a,'oE\u0003\u001d[q:%\n\u0006\u0002\u0002\nQ\u0019\u0001.a\u0005\t\u000f1\u0004\u0013\u0011!a\u0001GR\u0019q(a\u0006\t\u000f1\u0014\u0013\u0011!a\u0001QN)\u0011#\f\u001fH\u0015R\u0011\u0011Q\u0001\u000b\u0004Q\u0006}\u0001b\u00027\u0016\u0003\u0003\u0005\ra\u0019\u000b\u0004\u007f\u0005\r\u0002b\u00027\u0018\u0003\u0003\u0005\r\u0001[\u0001\u0005-&,w\u000f"
)
public interface View {
   static View viewPreferenceFromBoolean(final boolean b) {
      return View$.MODULE$.viewPreferenceFromBoolean(b);
   }

   public static class Require$ implements View, Product, Serializable {
      public static final Require$ MODULE$ = new Require$();

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
         return "Require";
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
         return x$1 instanceof Require$;
      }

      public int hashCode() {
         return -1534617275;
      }

      public String toString() {
         return "Require";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Require$.class);
      }
   }

   public static class Copy$ implements View, Product, Serializable {
      public static final Copy$ MODULE$ = new Copy$();

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
         return "Copy";
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
         return x$1 instanceof Copy$;
      }

      public int hashCode() {
         return 2106261;
      }

      public String toString() {
         return "Copy";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Copy$.class);
      }
   }

   public static class Prefer$ implements View, Product, Serializable {
      public static final Prefer$ MODULE$ = new Prefer$();

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
         return "Prefer";
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
         return x$1 instanceof Prefer$;
      }

      public int hashCode() {
         return -1896243664;
      }

      public String toString() {
         return "Prefer";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Prefer$.class);
      }
   }
}
