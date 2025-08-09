package spire.math;

import algebra.ring.EuclideanRing;
import algebra.ring.Field;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Tuple2;
import scala.math.BigInt;
import scala.math.BigInt.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import spire.algebra.FieldAssociativeAlgebra;
import spire.math.poly.PolyDense;
import spire.math.poly.PolyDense$;
import spire.math.poly.PolySparse;
import spire.math.poly.PolySparse$;

@ScalaSignature(
   bytes = "\u0006\u0005M4q\u0001C\u0005\u0011\u0002\u0007\u0005a\u0002C\u0003K\u0001\u0011\u00051\nC\u0004P\u0001\t\u0007i1\t)\t\u000bQ\u0003A\u0011I+\t\u000bi\u0003A\u0011A.\t\u000b\r\u0004A\u0011\u00013\t\u000b!\u0004A\u0011A5\t\u000b1\u0004A\u0011I7\u0003'A{G.\u001f8p[&\fGn\u0014<fe\u001aKW\r\u001c3\u000b\u0005)Y\u0011\u0001B7bi\"T\u0011\u0001D\u0001\u0006gBL'/Z\u0002\u0001+\tyAd\u0005\u0004\u0001!Y\u00194i\u0012\t\u0003#Qi\u0011A\u0005\u0006\u0002'\u0005)1oY1mC&\u0011QC\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0007]A\"$D\u0001\n\u0013\tI\u0012B\u0001\nQ_2Lhn\\7jC2|e/\u001a:SS:<\u0007CA\u000e\u001d\u0019\u0001!\u0011\"\b\u0001!\u0002\u0003\u0005)\u0019\u0001\u0010\u0003\u0003\r\u000b\"a\b\u0012\u0011\u0005E\u0001\u0013BA\u0011\u0013\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!E\u0012\n\u0005\u0011\u0012\"aA!os\"\u001aADJ\u0015\u0011\u0005E9\u0013B\u0001\u0015\u0013\u0005-\u0019\b/Z2jC2L'0\u001a32\u000b\rR3&\f\u0017\u000f\u0005EY\u0013B\u0001\u0017\u0013\u0003\u0019!u.\u001e2mKF\"AE\f\u001a\u0014\u001d\ty#'D\u00011\u0015\t\tT\"\u0001\u0004=e>|GOP\u0005\u0002'A\u0019A'\u0010!\u000f\u0005URdB\u0001\u001c9\u001d\tys'C\u0001\r\u0013\tI4\"A\u0004bY\u001e,'M]1\n\u0005mb\u0014a\u00029bG.\fw-\u001a\u0006\u0003s-I!AP \u0003\u001b\u0015+8\r\\5eK\u0006t'+\u001b8h\u0015\tYD\bE\u0002\u0018\u0003jI!AQ\u0005\u0003\u0015A{G.\u001f8p[&\fG\u000e\u0005\u0003E\u000b\u0002SR\"\u0001\u001f\n\u0005\u0019c$a\u0003,fGR|'o\u00159bG\u0016\u0004B\u0001\u0012%A5%\u0011\u0011\n\u0010\u0002\u0018\r&,G\u000eZ!tg>\u001c\u0017.\u0019;jm\u0016\fEnZ3ce\u0006\fa\u0001J5oSR$C#\u0001'\u0011\u0005Ei\u0015B\u0001(\u0013\u0005\u0011)f.\u001b;\u0002\rM\u001c\u0017\r\\1s+\u0005\t\u0006c\u0001\u001bS5%\u00111k\u0010\u0002\u0006\r&,G\u000eZ\u0001\u0005I&4(\u000fF\u0002A-bCQaV\u0002A\u0002\u0001\u000b\u0011\u0001\u001f\u0005\u00063\u000e\u0001\rAG\u0001\u0002W\u0006\tR-^2mS\u0012,\u0017M\u001c$v]\u000e$\u0018n\u001c8\u0015\u0005q\u0013\u0007CA/`\u001d\tqc,\u0003\u0002<%%\u0011\u0001-\u0019\u0002\u0007\u0005&<\u0017J\u001c;\u000b\u0005m\u0012\u0002\"B,\u0005\u0001\u0004\u0001\u0015!B3rk>$Hc\u0001!fM\")q+\u0002a\u0001\u0001\")q-\u0002a\u0001\u0001\u0006\t\u00110\u0001\u0003f[>$Gc\u0001!kW\")qK\u0002a\u0001\u0001\")qM\u0002a\u0001\u0001\u0006AQ-];pi6|G\rF\u0002ocJ\u0004B!E8A\u0001&\u0011\u0001O\u0005\u0002\u0007)V\u0004H.\u001a\u001a\t\u000b];\u0001\u0019\u0001!\t\u000b\u001d<\u0001\u0019\u0001!"
)
public interface PolynomialOverField extends PolynomialOverRing, EuclideanRing, FieldAssociativeAlgebra {
   Field scalar();

   // $FF: synthetic method
   static Polynomial divr$(final PolynomialOverField $this, final Polynomial x, final Object k) {
      return $this.divr(x, k);
   }

   default Polynomial divr(final Polynomial x, final Object k) {
      return x.$colon$div(k, this.scalar(), this.eq());
   }

   // $FF: synthetic method
   static BigInt euclideanFunction$(final PolynomialOverField $this, final Polynomial x) {
      return $this.euclideanFunction(x);
   }

   default BigInt euclideanFunction(final Polynomial x) {
      return .MODULE$.int2bigInt(x.degree());
   }

   // $FF: synthetic method
   static Polynomial equot$(final PolynomialOverField $this, final Polynomial x, final Polynomial y) {
      return $this.equot(x, y);
   }

   default Polynomial equot(final Polynomial x, final Polynomial y) {
      return (Polynomial)this.equotmod(x, y)._1();
   }

   // $FF: synthetic method
   static Polynomial emod$(final PolynomialOverField $this, final Polynomial x, final Polynomial y) {
      return $this.emod(x, y);
   }

   default Polynomial emod(final Polynomial x, final Polynomial y) {
      return (Polynomial)this.equotmod(x, y)._2();
   }

   // $FF: synthetic method
   static Tuple2 equotmod$(final PolynomialOverField $this, final Polynomial x, final Polynomial y) {
      return $this.equotmod(x, y);
   }

   default Tuple2 equotmod(final Polynomial x, final Polynomial y) {
      scala.Predef..MODULE$.require(!y.isZero(), () -> "Can't divide by polynomial of zero!");
      Tuple2 var3;
      if (x instanceof PolyDense) {
         PolyDense var6 = (PolyDense)x;
         var3 = PolyDense$.MODULE$.quotmodDense(var6, y, this.scalar(), this.eq(), this.ct());
      } else {
         if (!(x instanceof PolySparse)) {
            throw new MatchError(x);
         }

         PolySparse var7 = (PolySparse)x;
         PolySparse ys;
         if (y instanceof PolyDense) {
            PolyDense var10 = (PolyDense)y;
            ys = PolySparse$.MODULE$.dense2sparse(var10, this.scalar(), this.eq(), this.ct());
         } else {
            if (!(y instanceof PolySparse)) {
               throw new MatchError(y);
            }

            PolySparse var11 = (PolySparse)y;
            ys = var11;
         }

         var3 = PolySparse$.MODULE$.quotmodSparse(var7, ys, this.scalar(), this.eq(), this.ct());
      }

      return var3;
   }

   // $FF: synthetic method
   static Field scalar$mcD$sp$(final PolynomialOverField $this) {
      return $this.scalar$mcD$sp();
   }

   default Field scalar$mcD$sp() {
      return this.scalar();
   }

   // $FF: synthetic method
   static Polynomial divr$mcD$sp$(final PolynomialOverField $this, final Polynomial x, final double k) {
      return $this.divr$mcD$sp(x, k);
   }

   default Polynomial divr$mcD$sp(final Polynomial x, final double k) {
      return this.divr(x, BoxesRunTime.boxToDouble(k));
   }

   // $FF: synthetic method
   static BigInt euclideanFunction$mcD$sp$(final PolynomialOverField $this, final Polynomial x) {
      return $this.euclideanFunction$mcD$sp(x);
   }

   default BigInt euclideanFunction$mcD$sp(final Polynomial x) {
      return this.euclideanFunction(x);
   }

   // $FF: synthetic method
   static Polynomial equot$mcD$sp$(final PolynomialOverField $this, final Polynomial x, final Polynomial y) {
      return $this.equot$mcD$sp(x, y);
   }

   default Polynomial equot$mcD$sp(final Polynomial x, final Polynomial y) {
      return this.equot(x, y);
   }

   // $FF: synthetic method
   static Polynomial emod$mcD$sp$(final PolynomialOverField $this, final Polynomial x, final Polynomial y) {
      return $this.emod$mcD$sp(x, y);
   }

   default Polynomial emod$mcD$sp(final Polynomial x, final Polynomial y) {
      return this.emod(x, y);
   }

   // $FF: synthetic method
   static Tuple2 equotmod$mcD$sp$(final PolynomialOverField $this, final Polynomial x, final Polynomial y) {
      return $this.equotmod$mcD$sp(x, y);
   }

   default Tuple2 equotmod$mcD$sp(final Polynomial x, final Polynomial y) {
      return this.equotmod(x, y);
   }

   // $FF: synthetic method
   static boolean specInstance$$(final PolynomialOverField $this) {
      return $this.specInstance$();
   }

   default boolean specInstance$() {
      return false;
   }

   static void $init$(final PolynomialOverField $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
