package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.support.CanCollapseAxis;
import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=faB\u000f\u001f!\u0003\r\ncI\u0004\u0007\u0003[s\u0002\u0012A\u0018\u0007\u000buq\u0002\u0012\u0001\u0017\t\u000b5\u0012A\u0011\u0001\u0018\u0006\tE\u0012\u0001AM\u0004\u0006g\tA\t\t\u000e\u0004\u0007W\tA\t)!)\t\r52A\u0011AAR\u0011\u001dQe!!A\u0005B-Cq\u0001\u0016\u0004\u0002\u0002\u0013\u0005Q\u000b\u0003\u0005Z\r\u0005\u0005I\u0011AAS\u0011\u001d\u0001g!!A\u0005B\u0005D\u0001\u0002\u001b\u0004\u0002\u0002\u0013\u0005\u0011\u0011\u0016\u0005\b]\u001a\t\t\u0011\"\u0011p\u0011\u001d\u0001h!!A\u0005BEDqA\u001d\u0004\u0002\u0002\u0013%1oB\u00037\u0005!\u0005uGB\u00039\u0005!\u0005\u0015\bC\u0003.#\u0011\u0005\u0011\nC\u0004K#\u0005\u0005I\u0011I&\t\u000fQ\u000b\u0012\u0011!C\u0001+\"9\u0011,EA\u0001\n\u0003Q\u0006b\u00021\u0012\u0003\u0003%\t%\u0019\u0005\bQF\t\t\u0011\"\u0001j\u0011\u001dq\u0017#!A\u0005B=Dq\u0001]\t\u0002\u0002\u0013\u0005\u0013\u000fC\u0004s#\u0005\u0005I\u0011B:\t\u000b]\u0014A1\u0001=\t\u000f\u0005-$\u0001b\u0001\u0002n\t!\u0011\t_5t\u0015\ty\u0002%\u0001\u0004mS:\fGn\u001a\u0006\u0002C\u00051!M]3fu\u0016\u001c\u0001a\u0005\u0002\u0001IA\u0011Q\u0005K\u0007\u0002M)\tq%A\u0003tG\u0006d\u0017-\u0003\u0002*M\t1\u0011I\\=SK\u001aL3\u0001\u0001\u0004\u0012\u0005\ty\u0006g\u0005\u0002\u0003I\u00051A(\u001b8jiz\"\u0012a\f\t\u0003a\ti\u0011A\b\u0002\u0006-\u0006dW/\u001a\t\u0003a\u0001\t!a\u0018\u0019\u0011\u0005U2Q\"\u0001\u0002\u0002\u0005}\u000b\u0004CA\u001b\u0012\u0005\ty\u0016gE\u0003\u0012IIRT\b\u0005\u0002&w%\u0011AH\n\u0002\b!J|G-^2u!\tqdI\u0004\u0002@\t:\u0011\u0001iQ\u0007\u0002\u0003*\u0011!II\u0001\u0007yI|w\u000e\u001e \n\u0003\u001dJ!!\u0012\u0014\u0002\u000fA\f7m[1hK&\u0011q\t\u0013\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003\u000b\u001a\"\u0012aN\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u00031\u0003\"!\u0014*\u000e\u00039S!a\u0014)\u0002\t1\fgn\u001a\u0006\u0002#\u0006!!.\u0019<b\u0013\t\u0019fJ\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002-B\u0011QeV\u0005\u00031\u001a\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"a\u00170\u0011\u0005\u0015b\u0016BA/'\u0005\r\te.\u001f\u0005\b?V\t\t\u00111\u0001W\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\t!\rE\u0002dMnk\u0011\u0001\u001a\u0006\u0003K\u001a\n!bY8mY\u0016\u001cG/[8o\u0013\t9GM\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGC\u00016n!\t)3.\u0003\u0002mM\t9!i\\8mK\u0006t\u0007bB0\u0018\u0003\u0003\u0005\raW\u0001\tQ\u0006\u001c\bnQ8eKR\ta+\u0001\u0005u_N#(/\u001b8h)\u0005a\u0015\u0001D<sSR,'+\u001a9mC\u000e,G#\u0001;\u0011\u00055+\u0018B\u0001<O\u0005\u0019y%M[3di\u0006a1m\u001c7mCB\u001cX-\u0016:fIVi\u00110a\u0005\u0002\"\u0005\u001d\u0012QJA/\u0003_!rA_A\u001a\u0003#\n\t\u0007E\u0006|\u0003\u0013\ty!a\b\u0002&\u00055bb\u0001?\u0002\u00049\u0011Qp \b\u0003\u0001zL\u0011!I\u0005\u0004\u0003\u0003\u0001\u0013aB4f]\u0016\u0014\u0018nY\u0005\u0005\u0003\u000b\t9!A\u0003V\rVt7MC\u0002\u0002\u0002\u0001JA!a\u0003\u0002\u000e\t1Q+S7qYJRA!!\u0002\u0002\bA!\u0011\u0011CA\n\u0019\u0001!q!!\u0006\u001c\u0005\u0004\t9BA\u0002UC\u001e\f2!!\u0007\\!\r)\u00131D\u0005\u0004\u0003;1#a\u0002(pi\"Lgn\u001a\t\u0005\u0003#\t\t\u0003B\u0004\u0002$m\u0011\r!a\u0006\u0003\u0005Y\u000b\u0004\u0003BA\t\u0003O!q!!\u000b\u001c\u0005\u0004\tYCA\u0003Bq&\u001cH+E\u0002\u0002\u001aI\u0002B!!\u0005\u00020\u00119\u0011\u0011G\u000eC\u0002\u0005]!A\u0002*fgVdG\u000fC\u0004\u00026m\u0001\u001d!a\u000e\u0002\u0011!\fg\u000e\u001a5pY\u0012\u0004\"\"!\u000f\u0002F\u0005}\u0011QEA&\u001d\u0011\tY$!\u0011\u000e\u0005\u0005u\"bAA =\u000591/\u001e9q_J$\u0018\u0002BA\"\u0003{\tqbQ1o\u0007>dG.\u00199tK\u0006C\u0018n]\u0005\u0005\u0003\u000f\nIE\u0001\u0005IC:$\u0007j\u001c7e\u0015\u0011\t\u0019%!\u0010\u0011\t\u0005E\u0011Q\n\u0003\b\u0003\u001fZ\"\u0019AA\f\u0005\t!\u0016\tC\u0004\u0002Tm\u0001\u001d!!\u0016\u0002\t%l\u0007\u000f\u001c\t\nw\u0006]\u0013qBA&\u00037JA!!\u0017\u0002\u000e\t)Q+S7qYB!\u0011\u0011CA/\t\u001d\tyf\u0007b\u0001\u0003/\u0011!A\u0016*\t\u000f\u0005\r4\u0004q\u0001\u0002f\u0005A1m\u001c7mCB\u001cX\r\u0005\b\u0002<\u0005\u001d\u0014qDA\u0013\u0003\u0017\nY&!\f\n\t\u0005%\u0014Q\b\u0002\u0010\u0007\u0006t7i\u001c7mCB\u001cX-\u0011=jg\u0006i1m\u001c7mCB\u001cX-\u0016:fIN*\u0002#a\u001c\u0002z\u0005u\u0014\u0011QAC\u0003'\u000bY*a#\u0015\u0011\u0005E\u0014QRAK\u0003;\u0003Rb_A:\u0003o\nY(a \u0002\u0004\u0006%\u0015\u0002BA;\u0003\u001b\u0011a!V%na2\u001c\u0004\u0003BA\t\u0003s\"q!!\u0006\u001d\u0005\u0004\t9\u0002\u0005\u0003\u0002\u0012\u0005uDaBA\u00129\t\u0007\u0011q\u0003\t\u0005\u0003#\t\t\tB\u0004\u0002*q\u0011\r!a\u000b\u0011\t\u0005E\u0011Q\u0011\u0003\b\u0003\u000fc\"\u0019AA\f\u0005\t16\u0007\u0005\u0003\u0002\u0012\u0005-EaBA\u00199\t\u0007\u0011q\u0003\u0005\b\u0003ka\u00029AAH!)\tI$!\u0012\u0002|\u0005}\u0014\u0011\u0013\t\u0005\u0003#\t\u0019\nB\u0004\u0002Pq\u0011\r!a\u0006\t\u000f\u0005MC\u0004q\u0001\u0002\u0018BY10!\u0003\u0002x\u0005E\u00151QAM!\u0011\t\t\"a'\u0005\u000f\u0005}CD1\u0001\u0002\u0018!9\u00111\r\u000fA\u0004\u0005}\u0005CDA\u001e\u0003O\nY(a \u0002\u0012\u0006e\u0015\u0011R\n\u0006\r\u0011\u0012$(\u0010\u000b\u0002iQ\u00191,a*\t\u000f}S\u0011\u0011!a\u0001-R\u0019!.a+\t\u000f}c\u0011\u0011!a\u00017\u0006!\u0011\t_5t\u0001"
)
public interface Axis {
   static UFunc.UImpl3 collapseUred3(final CanCollapseAxis.HandHold handhold, final UFunc.UImpl2 impl, final CanCollapseAxis collapse) {
      return Axis$.MODULE$.collapseUred3(handhold, impl, collapse);
   }

   static UFunc.UImpl2 collapseUred(final CanCollapseAxis.HandHold handhold, final UFunc.UImpl impl, final CanCollapseAxis collapse) {
      return Axis$.MODULE$.collapseUred(handhold, impl, collapse);
   }

   public static class _0$ implements Axis, Product, Serializable {
      public static final _0$ MODULE$ = new _0$();

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
         return "_0";
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
         return x$1 instanceof _0$;
      }

      public int hashCode() {
         return 2993;
      }

      public String toString() {
         return "_0";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(_0$.class);
      }
   }

   public static class _1$ implements Axis, Product, Serializable {
      public static final _1$ MODULE$ = new _1$();

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
         return "_1";
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
         return x$1 instanceof _1$;
      }

      public int hashCode() {
         return 2994;
      }

      public String toString() {
         return "_1";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(_1$.class);
      }
   }
}
