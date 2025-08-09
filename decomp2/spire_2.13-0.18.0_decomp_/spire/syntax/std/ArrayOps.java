package spire.syntax.std;

import algebra.ring.AdditiveMonoid;
import algebra.ring.Field;
import algebra.ring.MultiplicativeMonoid;
import algebra.ring.Signed;
import cats.kernel.Monoid;
import cats.kernel.Order;
import scala.Function1;
import scala.Function2;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;
import spire.algebra.NRoot;
import spire.math.Searching$;
import spire.math.Selection$;
import spire.math.Sorting$;
import spire.random.Generator;

@ScalaSignature(
   bytes = "\u0006\u0005\tea\u0001\u0002\r\u001a\u0005\u0001B\u0001\u0002\u000b\u0001\u0003\u0002\u0003\u0006I!\u000b\u0005\u0006w\u0001!\t\u0001\u0010\u0005\u0006\u0001\u0002!\t!\u0011\u0005\u0006)\u0002!\t!\u0016\u0005\u00065\u0002!\ta\u0017\u0005\u0006A\u0002!\t!\u0019\u0005\u0006o\u0002!\t\u0001\u001f\u0005\b\u0003O\u0001A\u0011AA\u0015\u0011\u001d\t\u0019\u0004\u0001C\u0001\u0003kAq!!\u000f\u0001\t\u0003\tY\u0004C\u0004\u0002@\u0001!\t!!\u0011\t\u000f\u0005e\u0003\u0001\"\u0001\u0002\\!9\u0011Q\r\u0001\u0005\u0002\u0005\u001d\u0004bBAB\u0001\u0011\u0005\u0011Q\u0011\u0005\b\u0003;\u0003A\u0011AAP\u0011\u001d\ti\u000b\u0001C\u0001\u0003_Cq!!.\u0001\t\u0003\t9\fC\u0004\u0002N\u0002!\t!a4\t\u000f\u0005]\u0007\u0001\"\u0001\u0002Z\"9\u0011Q\u001d\u0001\u0005\u0002\u0005\u001d\bbBAy\u0001\u0011\u0005\u00111\u001f\u0005\b\u0005\u000b\u0001A\u0011\u0001B\u0004\u0011\u001d\u0011Y\u0001\u0001C\u0001\u0005\u001b\u0011\u0001\"\u0011:sCf|\u0005o\u001d\u0006\u00035m\t1a\u001d;e\u0015\taR$\u0001\u0004ts:$\u0018\r\u001f\u0006\u0002=\u0005)1\u000f]5sK\u000e\u0001QCA\u0011/'\t\u0001!\u0005\u0005\u0002$M5\tAEC\u0001&\u0003\u0015\u00198-\u00197b\u0013\t9CE\u0001\u0004B]f\u0014VMZ\u0001\u0004CJ\u0014\bcA\u0012+Y%\u00111\u0006\n\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003[9b\u0001\u0001B\u00050\u0001\u0001\u0006\t\u0011!b\u0001a\t\t\u0011)\u0005\u00022iA\u00111EM\u0005\u0003g\u0011\u0012qAT8uQ&tw\r\u0005\u0002$k%\u0011a\u0007\n\u0002\u0004\u0003:L\bF\u0001\u00189!\t\u0019\u0013(\u0003\u0002;I\tY1\u000f]3dS\u0006d\u0017N_3e\u0003\u0019a\u0014N\\5u}Q\u0011Qh\u0010\t\u0004}\u0001aS\"A\r\t\u000b!\u0012\u0001\u0019A\u0015\u0002\tE\u001cX/\u001c\u000b\u0003Y\tCQaQ\u0002A\u0004\u0011\u000b!!\u001a<\u0011\u0007\u0015\u000bFF\u0004\u0002G\u001d:\u0011q\t\u0014\b\u0003\u0011.k\u0011!\u0013\u0006\u0003\u0015~\ta\u0001\u0010:p_Rt\u0014\"\u0001\u0010\n\u00055k\u0012aB1mO\u0016\u0014'/Y\u0005\u0003\u001fB\u000bq\u0001]1dW\u0006<WM\u0003\u0002N;%\u0011!k\u0015\u0002\u000f\u0003\u0012$\u0017\u000e^5wK6{gn\\5e\u0015\ty\u0005+\u0001\u0005raJ|G-^2u)\tac\u000bC\u0003D\t\u0001\u000fq\u000bE\u0002F12J!!W*\u0003)5+H\u000e^5qY&\u001c\u0017\r^5wK6{gn\\5e\u0003!\t8m\\7cS:,GC\u0001\u0017]\u0011\u0015\u0019U\u0001q\u0001^!\r)e\fL\u0005\u0003?N\u0013a!T8o_&$\u0017!B9o_JlGC\u00012s)\u0011a3m\u001a7\t\u000b\r3\u00019\u00013\u0011\u0007\u0015+G&\u0003\u0002g'\n)a)[3mI\")\u0001N\u0002a\u0002S\u0006\t1\u000fE\u0002FU2J!a[*\u0003\rMKwM\\3e\u0011\u0015ig\u0001q\u0001o\u0003\tq'\u000fE\u0002pa2j\u0011\u0001U\u0005\u0003cB\u0013QA\u0014*p_RDQa\u001d\u0004A\u0002Q\f\u0011\u0001\u001d\t\u0003GUL!A\u001e\u0013\u0003\u0007%sG/A\u0005r]>\u0014XnV5uQV\u0011\u00110 \u000b\u0004u\u0006\u0015BcA>\u0002\u001cQ9A0a\u0004\u0002\u0014\u0005]\u0001CA\u0017~\t%qx\u0001)A\u0001\u0002\u000b\u0007\u0001GA\u0001SQ\u0011i\b(!\u00012\u0013\r\n\u0019!!\u0002\u0002\n\u0005\u001dabA\u0012\u0002\u0006%\u0019\u0011q\u0001\u0013\u0002\r\u0011{WO\u00197fc\u0019!\u00131BA\u0007K9\u0019\u0001*!\u0004\n\u0003\u0015BaaQ\u0004A\u0004\u0005E\u0001cA#fy\"1\u0001n\u0002a\u0002\u0003+\u00012!\u00126}\u0011\u0019iw\u0001q\u0001\u0002\u001aA\u0019q\u000e\u001d?\t\u000f\u0005uq\u00011\u0001\u0002 \u0005\ta\rE\u0003$\u0003CaC0C\u0002\u0002$\u0011\u0012\u0011BR;oGRLwN\\\u0019\t\u000bM<\u0001\u0019\u0001;\u0002\tEl\u0017N\u001c\u000b\u0004Y\u0005-\u0002BB\"\t\u0001\b\ti\u0003\u0005\u0003F\u0003_a\u0013bAA\u0019'\n)qJ\u001d3fe\u0006!\u0011/\\1y)\ra\u0013q\u0007\u0005\u0007\u0007&\u0001\u001d!!\f\u0002\u000bElW-\u00198\u0015\u00071\ni\u0004C\u0003D\u0015\u0001\u000fA-A\u0005r[\u0016\fgnV5uQV!\u00111IA%)\u0011\t)%!\u0016\u0015\t\u0005\u001d\u0013\u0011\u000b\t\u0004[\u0005%C!\u0003@\fA\u0003\u0005\tQ1\u00011Q\u0015\tI\u0005OA'c%\u0019\u00131AA\u0003\u0003\u001f\n9!\r\u0004%\u0003\u0017\ti!\n\u0005\u0007\u0007.\u0001\u001d!a\u0015\u0011\t\u0015+\u0017q\t\u0005\b\u0003;Y\u0001\u0019AA,!\u0019\u0019\u0013\u0011\u0005\u0017\u0002H\u00059\u0011o]3be\u000eDG\u0003BA/\u0003C\"2\u0001^A0\u0011\u0019\u0019E\u0002q\u0001\u0002.!1\u00111\r\u0007A\u00021\n\u0011!Y\u0001\u0006cN|'\u000f\u001e\u000b\u0007\u0003S\ny'!\u001d\u0011\u0007\r\nY'C\u0002\u0002n\u0011\u0012A!\u00168ji\"11)\u0004a\u0002\u0003[Aq!a\u001d\u000e\u0001\b\t)(\u0001\u0002diB)\u0011qOA?Y9!\u0011\u0011PA>\u001b\u0005i\u0012BA(\u001e\u0013\u0011\ty(!!\u0003\u0011\rc\u0017m]:UC\u001eT!aT\u000f\u0002\u000fE\u001cxN\u001d;CsV!\u0011qQAI)\u0011\tI)!'\u0015\r\u0005%\u00141RAL\u0011\u0019\u0019e\u0002q\u0001\u0002\u000eB)Q)a\f\u0002\u0010B\u0019Q&!%\u0005\u0015\u0005Me\u0002)A\u0001\u0002\u000b\u0007\u0001GA\u0001CQ\r\t\t\n\u000f\u0005\b\u0003gr\u00019AA;\u0011\u001d\tiB\u0004a\u0001\u00037\u0003baIA\u0011Y\u0005=\u0015!C9t_J$x+\u001b;i)\u0011\t\t+!*\u0015\t\u0005%\u00141\u0015\u0005\b\u0003gz\u00019AA;\u0011\u001d\tib\u0004a\u0001\u0003O\u0003baIAUY1\"\u0018bAAVI\tIa)\u001e8di&|gNM\u0001\bcN|'\u000f^3e)\u0015I\u0013\u0011WAZ\u0011\u0019\u0019\u0005\u0003q\u0001\u0002.!9\u00111\u000f\tA\u0004\u0005U\u0014!C9t_J$X\r\u001a\"z+\u0011\tI,a1\u0015\t\u0005m\u0016\u0011\u001a\u000b\u0006S\u0005u\u0016q\u0019\u0005\u0007\u0007F\u0001\u001d!a0\u0011\u000b\u0015\u000by#!1\u0011\u00075\n\u0019\r\u0002\u0006\u0002\u0014F\u0001\u000b\u0011!AC\u0002AB3!a19\u0011\u001d\t\u0019(\u0005a\u0002\u0003kBq!!\b\u0012\u0001\u0004\tY\r\u0005\u0004$\u0003Ca\u0013\u0011Y\u0001\fcN|'\u000f^3e/&$\b\u000e\u0006\u0003\u0002R\u0006UGcA\u0015\u0002T\"9\u00111\u000f\nA\u0004\u0005U\u0004bBA\u000f%\u0001\u0007\u0011qU\u0001\bcN,G.Z2u)\u0011\tY.!9\u0015\r\u0005%\u0014Q\\Ap\u0011\u0019\u00195\u0003q\u0001\u0002.!9\u00111O\nA\u0004\u0005U\u0004BBAr'\u0001\u0007A/A\u0001l\u0003%\t8/\u001a7fGR,G\r\u0006\u0003\u0002j\u0006=H#B\u0015\u0002l\u00065\bBB\"\u0015\u0001\b\ti\u0003C\u0004\u0002tQ\u0001\u001d!!\u001e\t\r\u0005\rH\u00031\u0001u\u0003!\t8\u000f[;gM2,G\u0003BA5\u0003kDq!a>\u0016\u0001\b\tI0A\u0002hK:\u0004B!a?\u0003\u00025\u0011\u0011Q \u0006\u0004\u0003\u007fl\u0012A\u0002:b]\u0012|W.\u0003\u0003\u0003\u0004\u0005u(!C$f]\u0016\u0014\u0018\r^8s\u0003%\t8\u000f[;gM2,G\rF\u0002*\u0005\u0013Aq!a>\u0017\u0001\b\tI0\u0001\u0005rg\u0006l\u0007\u000f\\3e)\u0011\u0011yA!\u0006\u0015\u000b%\u0012\tBa\u0005\t\u000f\u0005]x\u0003q\u0001\u0002z\"9\u00111O\fA\u0004\u0005U\u0004B\u0002B\f/\u0001\u0007A/A\u0001o\u0001"
)
public class ArrayOps {
   public final Object arr;

   public Object qsum(final AdditiveMonoid ev) {
      Object result = ev.zero();

      for(int index$macro$1 = 0; index$macro$1 < .MODULE$.array_length(this.arr); ++index$macro$1) {
         result = ev.plus(result, .MODULE$.array_apply(this.arr, index$macro$1));
      }

      return result;
   }

   public Object qproduct(final MultiplicativeMonoid ev) {
      Object result = ev.one();

      for(int index$macro$1 = 0; index$macro$1 < .MODULE$.array_length(this.arr); ++index$macro$1) {
         result = ev.times(result, .MODULE$.array_apply(this.arr, index$macro$1));
      }

      return result;
   }

   public Object qcombine(final Monoid ev) {
      Object result = ev.empty();

      for(int index$macro$1 = 0; index$macro$1 < .MODULE$.array_length(this.arr); ++index$macro$1) {
         result = ev.combine(result, .MODULE$.array_apply(this.arr, index$macro$1));
      }

      return result;
   }

   public Object qnorm(final int p, final Field ev, final Signed s, final NRoot nr) {
      Object result = ev.one();

      for(int index$macro$1 = 0; index$macro$1 < .MODULE$.array_length(this.arr); ++index$macro$1) {
         result = ev.plus(result, ev.pow(s.abs(.MODULE$.array_apply(this.arr, index$macro$1)), p));
      }

      return nr.nroot(result, p);
   }

   public Object qnormWith(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      Object result = ev.one();

      for(int index$macro$1 = 0; index$macro$1 < .MODULE$.array_length(this.arr); ++index$macro$1) {
         result = ev.plus(result, ev.pow(s.abs(f.apply(.MODULE$.array_apply(this.arr, index$macro$1))), p));
      }

      return nr.nroot(result, p);
   }

   public Object qmin(final Order ev) {
      if (.MODULE$.array_length(this.arr) == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         Object result = .MODULE$.array_apply(this.arr, 0);

         for(int index$macro$1 = 1; index$macro$1 < .MODULE$.array_length(this.arr); ++index$macro$1) {
            result = ev.min(result, .MODULE$.array_apply(this.arr, index$macro$1));
         }

         return result;
      }
   }

   public Object qmax(final Order ev) {
      if (.MODULE$.array_length(this.arr) == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         Object result = .MODULE$.array_apply(this.arr, 0);

         for(int index$macro$1 = 1; index$macro$1 < .MODULE$.array_length(this.arr); ++index$macro$1) {
            result = ev.max(result, .MODULE$.array_apply(this.arr, index$macro$1));
         }

         return result;
      }
   }

   public Object qmean(final Field ev) {
      if (.MODULE$.array_length(this.arr) == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         Object result = ev.zero();

         for(int index$macro$1 = 0; index$macro$1 < .MODULE$.array_length(this.arr); ++index$macro$1) {
            result = ev.plus(ev.div(ev.times(result, ev.fromInt(index$macro$1)), ev.fromInt(index$macro$1 + 1)), ev.div(.MODULE$.array_apply(this.arr, index$macro$1), ev.fromInt(index$macro$1 + 1)));
         }

         return result;
      }
   }

   public Object qmeanWith(final Function1 f, final Field ev) {
      if (.MODULE$.array_length(this.arr) == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         Object result = ev.zero();

         for(int index$macro$1 = 0; index$macro$1 < .MODULE$.array_length(this.arr); ++index$macro$1) {
            result = ev.plus(ev.div(ev.times(result, ev.fromInt(index$macro$1)), ev.fromInt(index$macro$1 + 1)), ev.div(f.apply(.MODULE$.array_apply(this.arr, index$macro$1)), ev.fromInt(index$macro$1 + 1)));
         }

         return result;
      }
   }

   public int qsearch(final Object a, final Order ev) {
      return Searching$.MODULE$.search(this.arr, a, ev);
   }

   public void qsort(final Order ev, final ClassTag ct) {
      Sorting$.MODULE$.sort(this.arr, ev, ct);
   }

   public void qsortBy(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Sorting$.MODULE$.sort(this.arr, ord, ct);
   }

   public void qsortWith(final Function2 f, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().from(f);
      Sorting$.MODULE$.sort(this.arr, ord, ct);
   }

   public Object qsorted(final Order ev, final ClassTag ct) {
      Object arr2 = .MODULE$.array_clone(this.arr);
      Sorting$.MODULE$.sort(arr2, ev, ct);
      return arr2;
   }

   public Object qsortedBy(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Object arr2 = .MODULE$.array_clone(this.arr);
      Sorting$.MODULE$.sort(arr2, ord, ct);
      return arr2;
   }

   public Object qsortedWith(final Function2 f, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().from(f);
      Object arr2 = .MODULE$.array_clone(this.arr);
      Sorting$.MODULE$.sort(arr2, ord, ct);
      return arr2;
   }

   public void qselect(final int k, final Order ev, final ClassTag ct) {
      Selection$.MODULE$.select(this.arr, k, ev, ct);
   }

   public Object qselected(final int k, final Order ev, final ClassTag ct) {
      Object arr2 = .MODULE$.array_clone(this.arr);
      Selection$.MODULE$.select(arr2, k, ev, ct);
      return arr2;
   }

   public void qshuffle(final Generator gen) {
      gen.shuffle(this.arr, gen);
   }

   public Object qshuffled(final Generator gen) {
      Object arr2 = .MODULE$.array_clone(this.arr);
      gen.shuffle(arr2, gen);
      return arr2;
   }

   public Object qsampled(final int n, final Generator gen, final ClassTag ct) {
      return gen.sampleFromArray(this.arr, n, ct, gen);
   }

   public boolean qsum$mcZ$sp(final AdditiveMonoid ev) {
      return BoxesRunTime.unboxToBoolean(this.qsum(ev));
   }

   public byte qsum$mcB$sp(final AdditiveMonoid ev) {
      return BoxesRunTime.unboxToByte(this.qsum(ev));
   }

   public char qsum$mcC$sp(final AdditiveMonoid ev) {
      return BoxesRunTime.unboxToChar(this.qsum(ev));
   }

   public double qsum$mcD$sp(final AdditiveMonoid ev) {
      return BoxesRunTime.unboxToDouble(this.qsum(ev));
   }

   public float qsum$mcF$sp(final AdditiveMonoid ev) {
      return BoxesRunTime.unboxToFloat(this.qsum(ev));
   }

   public int qsum$mcI$sp(final AdditiveMonoid ev) {
      return BoxesRunTime.unboxToInt(this.qsum(ev));
   }

   public long qsum$mcJ$sp(final AdditiveMonoid ev) {
      return BoxesRunTime.unboxToLong(this.qsum(ev));
   }

   public short qsum$mcS$sp(final AdditiveMonoid ev) {
      return BoxesRunTime.unboxToShort(this.qsum(ev));
   }

   public void qsum$mcV$sp(final AdditiveMonoid ev) {
      this.qsum(ev);
   }

   public boolean qproduct$mcZ$sp(final MultiplicativeMonoid ev) {
      return BoxesRunTime.unboxToBoolean(this.qproduct(ev));
   }

   public byte qproduct$mcB$sp(final MultiplicativeMonoid ev) {
      return BoxesRunTime.unboxToByte(this.qproduct(ev));
   }

   public char qproduct$mcC$sp(final MultiplicativeMonoid ev) {
      return BoxesRunTime.unboxToChar(this.qproduct(ev));
   }

   public double qproduct$mcD$sp(final MultiplicativeMonoid ev) {
      return BoxesRunTime.unboxToDouble(this.qproduct(ev));
   }

   public float qproduct$mcF$sp(final MultiplicativeMonoid ev) {
      return BoxesRunTime.unboxToFloat(this.qproduct(ev));
   }

   public int qproduct$mcI$sp(final MultiplicativeMonoid ev) {
      return BoxesRunTime.unboxToInt(this.qproduct(ev));
   }

   public long qproduct$mcJ$sp(final MultiplicativeMonoid ev) {
      return BoxesRunTime.unboxToLong(this.qproduct(ev));
   }

   public short qproduct$mcS$sp(final MultiplicativeMonoid ev) {
      return BoxesRunTime.unboxToShort(this.qproduct(ev));
   }

   public void qproduct$mcV$sp(final MultiplicativeMonoid ev) {
      this.qproduct(ev);
   }

   public boolean qcombine$mcZ$sp(final Monoid ev) {
      return BoxesRunTime.unboxToBoolean(this.qcombine(ev));
   }

   public byte qcombine$mcB$sp(final Monoid ev) {
      return BoxesRunTime.unboxToByte(this.qcombine(ev));
   }

   public char qcombine$mcC$sp(final Monoid ev) {
      return BoxesRunTime.unboxToChar(this.qcombine(ev));
   }

   public double qcombine$mcD$sp(final Monoid ev) {
      return BoxesRunTime.unboxToDouble(this.qcombine(ev));
   }

   public float qcombine$mcF$sp(final Monoid ev) {
      return BoxesRunTime.unboxToFloat(this.qcombine(ev));
   }

   public int qcombine$mcI$sp(final Monoid ev) {
      return BoxesRunTime.unboxToInt(this.qcombine(ev));
   }

   public long qcombine$mcJ$sp(final Monoid ev) {
      return BoxesRunTime.unboxToLong(this.qcombine(ev));
   }

   public short qcombine$mcS$sp(final Monoid ev) {
      return BoxesRunTime.unboxToShort(this.qcombine(ev));
   }

   public void qcombine$mcV$sp(final Monoid ev) {
      this.qcombine(ev);
   }

   public boolean qnorm$mcZ$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      return BoxesRunTime.unboxToBoolean(this.qnorm(p, ev, s, nr));
   }

   public byte qnorm$mcB$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      return BoxesRunTime.unboxToByte(this.qnorm(p, ev, s, nr));
   }

   public char qnorm$mcC$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      return BoxesRunTime.unboxToChar(this.qnorm(p, ev, s, nr));
   }

   public double qnorm$mcD$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      return BoxesRunTime.unboxToDouble(this.qnorm(p, ev, s, nr));
   }

   public float qnorm$mcF$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      return BoxesRunTime.unboxToFloat(this.qnorm(p, ev, s, nr));
   }

   public int qnorm$mcI$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      return BoxesRunTime.unboxToInt(this.qnorm(p, ev, s, nr));
   }

   public long qnorm$mcJ$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      return BoxesRunTime.unboxToLong(this.qnorm(p, ev, s, nr));
   }

   public short qnorm$mcS$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      return BoxesRunTime.unboxToShort(this.qnorm(p, ev, s, nr));
   }

   public void qnorm$mcV$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      this.qnorm(p, ev, s, nr);
   }

   public Object qnormWith$mcZ$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith(p, f, ev, s, nr);
   }

   public Object qnormWith$mcB$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith(p, f, ev, s, nr);
   }

   public Object qnormWith$mcC$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith(p, f, ev, s, nr);
   }

   public Object qnormWith$mcD$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith(p, f, ev, s, nr);
   }

   public Object qnormWith$mcF$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith(p, f, ev, s, nr);
   }

   public Object qnormWith$mcI$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith(p, f, ev, s, nr);
   }

   public Object qnormWith$mcJ$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith(p, f, ev, s, nr);
   }

   public Object qnormWith$mcS$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith(p, f, ev, s, nr);
   }

   public Object qnormWith$mcV$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith(p, f, ev, s, nr);
   }

   public double qnormWith$mDc$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      double result = ev.one$mcD$sp();

      for(int index$macro$1 = 0; index$macro$1 < .MODULE$.array_length(this.arr); ++index$macro$1) {
         result = ev.plus$mcD$sp(result, ev.pow$mcD$sp(s.abs$mcD$sp(BoxesRunTime.unboxToDouble(f.apply(.MODULE$.array_apply(this.arr, index$macro$1)))), p));
      }

      return nr.nroot$mcD$sp(result, p);
   }

   public double qnormWith$mDcZ$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mDc$sp(p, f, ev, s, nr);
   }

   public double qnormWith$mDcB$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mDc$sp(p, f, ev, s, nr);
   }

   public double qnormWith$mDcC$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mDc$sp(p, f, ev, s, nr);
   }

   public double qnormWith$mDcD$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mDc$sp(p, f, ev, s, nr);
   }

   public double qnormWith$mDcF$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mDc$sp(p, f, ev, s, nr);
   }

   public double qnormWith$mDcI$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mDc$sp(p, f, ev, s, nr);
   }

   public double qnormWith$mDcJ$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mDc$sp(p, f, ev, s, nr);
   }

   public double qnormWith$mDcS$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mDc$sp(p, f, ev, s, nr);
   }

   public double qnormWith$mDcV$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith$mDc$sp(p, f, ev, s, nr);
   }

   public boolean qmin$mcZ$sp(final Order ev) {
      return BoxesRunTime.unboxToBoolean(this.qmin(ev));
   }

   public byte qmin$mcB$sp(final Order ev) {
      return BoxesRunTime.unboxToByte(this.qmin(ev));
   }

   public char qmin$mcC$sp(final Order ev) {
      return BoxesRunTime.unboxToChar(this.qmin(ev));
   }

   public double qmin$mcD$sp(final Order ev) {
      return BoxesRunTime.unboxToDouble(this.qmin(ev));
   }

   public float qmin$mcF$sp(final Order ev) {
      return BoxesRunTime.unboxToFloat(this.qmin(ev));
   }

   public int qmin$mcI$sp(final Order ev) {
      return BoxesRunTime.unboxToInt(this.qmin(ev));
   }

   public long qmin$mcJ$sp(final Order ev) {
      return BoxesRunTime.unboxToLong(this.qmin(ev));
   }

   public short qmin$mcS$sp(final Order ev) {
      return BoxesRunTime.unboxToShort(this.qmin(ev));
   }

   public void qmin$mcV$sp(final Order ev) {
      this.qmin(ev);
   }

   public boolean qmax$mcZ$sp(final Order ev) {
      return BoxesRunTime.unboxToBoolean(this.qmax(ev));
   }

   public byte qmax$mcB$sp(final Order ev) {
      return BoxesRunTime.unboxToByte(this.qmax(ev));
   }

   public char qmax$mcC$sp(final Order ev) {
      return BoxesRunTime.unboxToChar(this.qmax(ev));
   }

   public double qmax$mcD$sp(final Order ev) {
      return BoxesRunTime.unboxToDouble(this.qmax(ev));
   }

   public float qmax$mcF$sp(final Order ev) {
      return BoxesRunTime.unboxToFloat(this.qmax(ev));
   }

   public int qmax$mcI$sp(final Order ev) {
      return BoxesRunTime.unboxToInt(this.qmax(ev));
   }

   public long qmax$mcJ$sp(final Order ev) {
      return BoxesRunTime.unboxToLong(this.qmax(ev));
   }

   public short qmax$mcS$sp(final Order ev) {
      return BoxesRunTime.unboxToShort(this.qmax(ev));
   }

   public void qmax$mcV$sp(final Order ev) {
      this.qmax(ev);
   }

   public boolean qmean$mcZ$sp(final Field ev) {
      return BoxesRunTime.unboxToBoolean(this.qmean(ev));
   }

   public byte qmean$mcB$sp(final Field ev) {
      return BoxesRunTime.unboxToByte(this.qmean(ev));
   }

   public char qmean$mcC$sp(final Field ev) {
      return BoxesRunTime.unboxToChar(this.qmean(ev));
   }

   public double qmean$mcD$sp(final Field ev) {
      return BoxesRunTime.unboxToDouble(this.qmean(ev));
   }

   public float qmean$mcF$sp(final Field ev) {
      return BoxesRunTime.unboxToFloat(this.qmean(ev));
   }

   public int qmean$mcI$sp(final Field ev) {
      return BoxesRunTime.unboxToInt(this.qmean(ev));
   }

   public long qmean$mcJ$sp(final Field ev) {
      return BoxesRunTime.unboxToLong(this.qmean(ev));
   }

   public short qmean$mcS$sp(final Field ev) {
      return BoxesRunTime.unboxToShort(this.qmean(ev));
   }

   public void qmean$mcV$sp(final Field ev) {
      this.qmean(ev);
   }

   public Object qmeanWith$mcZ$sp(final Function1 f, final Field ev) {
      return this.qmeanWith(f, ev);
   }

   public Object qmeanWith$mcB$sp(final Function1 f, final Field ev) {
      return this.qmeanWith(f, ev);
   }

   public Object qmeanWith$mcC$sp(final Function1 f, final Field ev) {
      return this.qmeanWith(f, ev);
   }

   public Object qmeanWith$mcD$sp(final Function1 f, final Field ev) {
      return this.qmeanWith(f, ev);
   }

   public Object qmeanWith$mcF$sp(final Function1 f, final Field ev) {
      return this.qmeanWith(f, ev);
   }

   public Object qmeanWith$mcI$sp(final Function1 f, final Field ev) {
      return this.qmeanWith(f, ev);
   }

   public Object qmeanWith$mcJ$sp(final Function1 f, final Field ev) {
      return this.qmeanWith(f, ev);
   }

   public Object qmeanWith$mcS$sp(final Function1 f, final Field ev) {
      return this.qmeanWith(f, ev);
   }

   public Object qmeanWith$mcV$sp(final Function1 f, final Field ev) {
      return this.qmeanWith(f, ev);
   }

   public double qmeanWith$mDc$sp(final Function1 f, final Field ev) {
      if (.MODULE$.array_length(this.arr) == 0) {
         throw new UnsupportedOperationException("empty array");
      } else {
         double result = ev.zero$mcD$sp();

         for(int index$macro$1 = 0; index$macro$1 < .MODULE$.array_length(this.arr); ++index$macro$1) {
            result = ev.plus$mcD$sp(ev.div$mcD$sp(ev.times$mcD$sp(result, ev.fromInt$mcD$sp(index$macro$1)), ev.fromInt$mcD$sp(index$macro$1 + 1)), ev.div$mcD$sp(BoxesRunTime.unboxToDouble(f.apply(.MODULE$.array_apply(this.arr, index$macro$1))), ev.fromInt$mcD$sp(index$macro$1 + 1)));
         }

         return result;
      }
   }

   public double qmeanWith$mDcZ$sp(final Function1 f, final Field ev) {
      return this.qmeanWith$mDc$sp(f, ev);
   }

   public double qmeanWith$mDcB$sp(final Function1 f, final Field ev) {
      return this.qmeanWith$mDc$sp(f, ev);
   }

   public double qmeanWith$mDcC$sp(final Function1 f, final Field ev) {
      return this.qmeanWith$mDc$sp(f, ev);
   }

   public double qmeanWith$mDcD$sp(final Function1 f, final Field ev) {
      return this.qmeanWith$mDc$sp(f, ev);
   }

   public double qmeanWith$mDcF$sp(final Function1 f, final Field ev) {
      return this.qmeanWith$mDc$sp(f, ev);
   }

   public double qmeanWith$mDcI$sp(final Function1 f, final Field ev) {
      return this.qmeanWith$mDc$sp(f, ev);
   }

   public double qmeanWith$mDcJ$sp(final Function1 f, final Field ev) {
      return this.qmeanWith$mDc$sp(f, ev);
   }

   public double qmeanWith$mDcS$sp(final Function1 f, final Field ev) {
      return this.qmeanWith$mDc$sp(f, ev);
   }

   public double qmeanWith$mDcV$sp(final Function1 f, final Field ev) {
      return this.qmeanWith$mDc$sp(f, ev);
   }

   public int qsearch$mcZ$sp(final boolean a, final Order ev) {
      return this.qsearch(BoxesRunTime.boxToBoolean(a), ev);
   }

   public int qsearch$mcB$sp(final byte a, final Order ev) {
      return this.qsearch(BoxesRunTime.boxToByte(a), ev);
   }

   public int qsearch$mcC$sp(final char a, final Order ev) {
      return this.qsearch(BoxesRunTime.boxToCharacter(a), ev);
   }

   public int qsearch$mcD$sp(final double a, final Order ev) {
      return this.qsearch(BoxesRunTime.boxToDouble(a), ev);
   }

   public int qsearch$mcF$sp(final float a, final Order ev) {
      return this.qsearch(BoxesRunTime.boxToFloat(a), ev);
   }

   public int qsearch$mcI$sp(final int a, final Order ev) {
      return this.qsearch(BoxesRunTime.boxToInteger(a), ev);
   }

   public int qsearch$mcJ$sp(final long a, final Order ev) {
      return this.qsearch(BoxesRunTime.boxToLong(a), ev);
   }

   public int qsearch$mcS$sp(final short a, final Order ev) {
      return this.qsearch(BoxesRunTime.boxToShort(a), ev);
   }

   public int qsearch$mcV$sp(final BoxedUnit a, final Order ev) {
      return this.qsearch(a, ev);
   }

   public void qsort$mcZ$sp(final Order ev, final ClassTag ct) {
      this.qsort(ev, ct);
   }

   public void qsort$mcB$sp(final Order ev, final ClassTag ct) {
      this.qsort(ev, ct);
   }

   public void qsort$mcC$sp(final Order ev, final ClassTag ct) {
      this.qsort(ev, ct);
   }

   public void qsort$mcD$sp(final Order ev, final ClassTag ct) {
      this.qsort(ev, ct);
   }

   public void qsort$mcF$sp(final Order ev, final ClassTag ct) {
      this.qsort(ev, ct);
   }

   public void qsort$mcI$sp(final Order ev, final ClassTag ct) {
      this.qsort(ev, ct);
   }

   public void qsort$mcJ$sp(final Order ev, final ClassTag ct) {
      this.qsort(ev, ct);
   }

   public void qsort$mcS$sp(final Order ev, final ClassTag ct) {
      this.qsort(ev, ct);
   }

   public void qsort$mcV$sp(final Order ev, final ClassTag ct) {
      this.qsort(ev, ct);
   }

   public void qsortBy$mcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy(f, ev, ct);
   }

   public void qsortBy$mcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy(f, ev, ct);
   }

   public void qsortBy$mcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy(f, ev, ct);
   }

   public void qsortBy$mcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy(f, ev, ct);
   }

   public void qsortBy$mcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy(f, ev, ct);
   }

   public void qsortBy$mcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy(f, ev, ct);
   }

   public void qsortBy$mcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy(f, ev, ct);
   }

   public void qsortBy$mcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy(f, ev, ct);
   }

   public void qsortBy$mcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy(f, ev, ct);
   }

   public void qsortBy$mZc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Sorting$.MODULE$.sort(this.arr, ord, ct);
   }

   public void qsortBy$mZcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mZc$sp(f, ev, ct);
   }

   public void qsortBy$mZcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mZc$sp(f, ev, ct);
   }

   public void qsortBy$mZcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mZc$sp(f, ev, ct);
   }

   public void qsortBy$mZcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mZc$sp(f, ev, ct);
   }

   public void qsortBy$mZcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mZc$sp(f, ev, ct);
   }

   public void qsortBy$mZcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mZc$sp(f, ev, ct);
   }

   public void qsortBy$mZcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mZc$sp(f, ev, ct);
   }

   public void qsortBy$mZcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mZc$sp(f, ev, ct);
   }

   public void qsortBy$mZcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mZc$sp(f, ev, ct);
   }

   public void qsortBy$mBc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Sorting$.MODULE$.sort(this.arr, ord, ct);
   }

   public void qsortBy$mBcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mBc$sp(f, ev, ct);
   }

   public void qsortBy$mBcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mBc$sp(f, ev, ct);
   }

   public void qsortBy$mBcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mBc$sp(f, ev, ct);
   }

   public void qsortBy$mBcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mBc$sp(f, ev, ct);
   }

   public void qsortBy$mBcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mBc$sp(f, ev, ct);
   }

   public void qsortBy$mBcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mBc$sp(f, ev, ct);
   }

   public void qsortBy$mBcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mBc$sp(f, ev, ct);
   }

   public void qsortBy$mBcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mBc$sp(f, ev, ct);
   }

   public void qsortBy$mBcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mBc$sp(f, ev, ct);
   }

   public void qsortBy$mCc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Sorting$.MODULE$.sort(this.arr, ord, ct);
   }

   public void qsortBy$mCcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mCc$sp(f, ev, ct);
   }

   public void qsortBy$mCcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mCc$sp(f, ev, ct);
   }

   public void qsortBy$mCcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mCc$sp(f, ev, ct);
   }

   public void qsortBy$mCcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mCc$sp(f, ev, ct);
   }

   public void qsortBy$mCcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mCc$sp(f, ev, ct);
   }

   public void qsortBy$mCcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mCc$sp(f, ev, ct);
   }

   public void qsortBy$mCcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mCc$sp(f, ev, ct);
   }

   public void qsortBy$mCcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mCc$sp(f, ev, ct);
   }

   public void qsortBy$mCcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mCc$sp(f, ev, ct);
   }

   public void qsortBy$mDc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Sorting$.MODULE$.sort(this.arr, ord, ct);
   }

   public void qsortBy$mDcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mDc$sp(f, ev, ct);
   }

   public void qsortBy$mDcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mDc$sp(f, ev, ct);
   }

   public void qsortBy$mDcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mDc$sp(f, ev, ct);
   }

   public void qsortBy$mDcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mDc$sp(f, ev, ct);
   }

   public void qsortBy$mDcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mDc$sp(f, ev, ct);
   }

   public void qsortBy$mDcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mDc$sp(f, ev, ct);
   }

   public void qsortBy$mDcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mDc$sp(f, ev, ct);
   }

   public void qsortBy$mDcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mDc$sp(f, ev, ct);
   }

   public void qsortBy$mDcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mDc$sp(f, ev, ct);
   }

   public void qsortBy$mFc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Sorting$.MODULE$.sort(this.arr, ord, ct);
   }

   public void qsortBy$mFcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mFc$sp(f, ev, ct);
   }

   public void qsortBy$mFcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mFc$sp(f, ev, ct);
   }

   public void qsortBy$mFcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mFc$sp(f, ev, ct);
   }

   public void qsortBy$mFcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mFc$sp(f, ev, ct);
   }

   public void qsortBy$mFcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mFc$sp(f, ev, ct);
   }

   public void qsortBy$mFcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mFc$sp(f, ev, ct);
   }

   public void qsortBy$mFcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mFc$sp(f, ev, ct);
   }

   public void qsortBy$mFcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mFc$sp(f, ev, ct);
   }

   public void qsortBy$mFcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mFc$sp(f, ev, ct);
   }

   public void qsortBy$mIc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Sorting$.MODULE$.sort(this.arr, ord, ct);
   }

   public void qsortBy$mIcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mIc$sp(f, ev, ct);
   }

   public void qsortBy$mIcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mIc$sp(f, ev, ct);
   }

   public void qsortBy$mIcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mIc$sp(f, ev, ct);
   }

   public void qsortBy$mIcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mIc$sp(f, ev, ct);
   }

   public void qsortBy$mIcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mIc$sp(f, ev, ct);
   }

   public void qsortBy$mIcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mIc$sp(f, ev, ct);
   }

   public void qsortBy$mIcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mIc$sp(f, ev, ct);
   }

   public void qsortBy$mIcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mIc$sp(f, ev, ct);
   }

   public void qsortBy$mIcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mIc$sp(f, ev, ct);
   }

   public void qsortBy$mJc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Sorting$.MODULE$.sort(this.arr, ord, ct);
   }

   public void qsortBy$mJcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mJc$sp(f, ev, ct);
   }

   public void qsortBy$mJcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mJc$sp(f, ev, ct);
   }

   public void qsortBy$mJcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mJc$sp(f, ev, ct);
   }

   public void qsortBy$mJcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mJc$sp(f, ev, ct);
   }

   public void qsortBy$mJcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mJc$sp(f, ev, ct);
   }

   public void qsortBy$mJcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mJc$sp(f, ev, ct);
   }

   public void qsortBy$mJcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mJc$sp(f, ev, ct);
   }

   public void qsortBy$mJcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mJc$sp(f, ev, ct);
   }

   public void qsortBy$mJcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mJc$sp(f, ev, ct);
   }

   public void qsortBy$mSc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Sorting$.MODULE$.sort(this.arr, ord, ct);
   }

   public void qsortBy$mScZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mSc$sp(f, ev, ct);
   }

   public void qsortBy$mScB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mSc$sp(f, ev, ct);
   }

   public void qsortBy$mScC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mSc$sp(f, ev, ct);
   }

   public void qsortBy$mScD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mSc$sp(f, ev, ct);
   }

   public void qsortBy$mScF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mSc$sp(f, ev, ct);
   }

   public void qsortBy$mScI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mSc$sp(f, ev, ct);
   }

   public void qsortBy$mScJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mSc$sp(f, ev, ct);
   }

   public void qsortBy$mScS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mSc$sp(f, ev, ct);
   }

   public void qsortBy$mScV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mSc$sp(f, ev, ct);
   }

   public void qsortBy$mVc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Sorting$.MODULE$.sort(this.arr, ord, ct);
   }

   public void qsortBy$mVcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mVc$sp(f, ev, ct);
   }

   public void qsortBy$mVcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mVc$sp(f, ev, ct);
   }

   public void qsortBy$mVcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mVc$sp(f, ev, ct);
   }

   public void qsortBy$mVcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mVc$sp(f, ev, ct);
   }

   public void qsortBy$mVcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mVc$sp(f, ev, ct);
   }

   public void qsortBy$mVcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mVc$sp(f, ev, ct);
   }

   public void qsortBy$mVcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mVc$sp(f, ev, ct);
   }

   public void qsortBy$mVcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mVc$sp(f, ev, ct);
   }

   public void qsortBy$mVcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      this.qsortBy$mVc$sp(f, ev, ct);
   }

   public void qsortWith$mcZ$sp(final Function2 f, final ClassTag ct) {
      this.qsortWith(f, ct);
   }

   public void qsortWith$mcB$sp(final Function2 f, final ClassTag ct) {
      this.qsortWith(f, ct);
   }

   public void qsortWith$mcC$sp(final Function2 f, final ClassTag ct) {
      this.qsortWith(f, ct);
   }

   public void qsortWith$mcD$sp(final Function2 f, final ClassTag ct) {
      this.qsortWith(f, ct);
   }

   public void qsortWith$mcF$sp(final Function2 f, final ClassTag ct) {
      this.qsortWith(f, ct);
   }

   public void qsortWith$mcI$sp(final Function2 f, final ClassTag ct) {
      this.qsortWith(f, ct);
   }

   public void qsortWith$mcJ$sp(final Function2 f, final ClassTag ct) {
      this.qsortWith(f, ct);
   }

   public void qsortWith$mcS$sp(final Function2 f, final ClassTag ct) {
      this.qsortWith(f, ct);
   }

   public void qsortWith$mcV$sp(final Function2 f, final ClassTag ct) {
      this.qsortWith(f, ct);
   }

   public boolean[] qsorted$mcZ$sp(final Order ev, final ClassTag ct) {
      return (boolean[])this.qsorted(ev, ct);
   }

   public byte[] qsorted$mcB$sp(final Order ev, final ClassTag ct) {
      return (byte[])this.qsorted(ev, ct);
   }

   public char[] qsorted$mcC$sp(final Order ev, final ClassTag ct) {
      return (char[])this.qsorted(ev, ct);
   }

   public double[] qsorted$mcD$sp(final Order ev, final ClassTag ct) {
      return (double[])this.qsorted(ev, ct);
   }

   public float[] qsorted$mcF$sp(final Order ev, final ClassTag ct) {
      return (float[])this.qsorted(ev, ct);
   }

   public int[] qsorted$mcI$sp(final Order ev, final ClassTag ct) {
      return (int[])this.qsorted(ev, ct);
   }

   public long[] qsorted$mcJ$sp(final Order ev, final ClassTag ct) {
      return (long[])this.qsorted(ev, ct);
   }

   public short[] qsorted$mcS$sp(final Order ev, final ClassTag ct) {
      return (short[])this.qsorted(ev, ct);
   }

   public BoxedUnit[] qsorted$mcV$sp(final Order ev, final ClassTag ct) {
      return (BoxedUnit[])this.qsorted(ev, ct);
   }

   public boolean[] qsortedBy$mcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (boolean[])this.qsortedBy(f, ev, ct);
   }

   public byte[] qsortedBy$mcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (byte[])this.qsortedBy(f, ev, ct);
   }

   public char[] qsortedBy$mcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (char[])this.qsortedBy(f, ev, ct);
   }

   public double[] qsortedBy$mcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (double[])this.qsortedBy(f, ev, ct);
   }

   public float[] qsortedBy$mcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (float[])this.qsortedBy(f, ev, ct);
   }

   public int[] qsortedBy$mcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (int[])this.qsortedBy(f, ev, ct);
   }

   public long[] qsortedBy$mcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (long[])this.qsortedBy(f, ev, ct);
   }

   public short[] qsortedBy$mcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (short[])this.qsortedBy(f, ev, ct);
   }

   public BoxedUnit[] qsortedBy$mcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (BoxedUnit[])this.qsortedBy(f, ev, ct);
   }

   public Object qsortedBy$mZc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Object arr2 = .MODULE$.array_clone(this.arr);
      Sorting$.MODULE$.sort(arr2, ord, ct);
      return arr2;
   }

   public boolean[] qsortedBy$mZcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (boolean[])this.qsortedBy$mZc$sp(f, ev, ct);
   }

   public byte[] qsortedBy$mZcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (byte[])this.qsortedBy$mZc$sp(f, ev, ct);
   }

   public char[] qsortedBy$mZcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (char[])this.qsortedBy$mZc$sp(f, ev, ct);
   }

   public double[] qsortedBy$mZcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (double[])this.qsortedBy$mZc$sp(f, ev, ct);
   }

   public float[] qsortedBy$mZcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (float[])this.qsortedBy$mZc$sp(f, ev, ct);
   }

   public int[] qsortedBy$mZcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (int[])this.qsortedBy$mZc$sp(f, ev, ct);
   }

   public long[] qsortedBy$mZcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (long[])this.qsortedBy$mZc$sp(f, ev, ct);
   }

   public short[] qsortedBy$mZcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (short[])this.qsortedBy$mZc$sp(f, ev, ct);
   }

   public BoxedUnit[] qsortedBy$mZcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (BoxedUnit[])this.qsortedBy$mZc$sp(f, ev, ct);
   }

   public Object qsortedBy$mBc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Object arr2 = .MODULE$.array_clone(this.arr);
      Sorting$.MODULE$.sort(arr2, ord, ct);
      return arr2;
   }

   public boolean[] qsortedBy$mBcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (boolean[])this.qsortedBy$mBc$sp(f, ev, ct);
   }

   public byte[] qsortedBy$mBcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (byte[])this.qsortedBy$mBc$sp(f, ev, ct);
   }

   public char[] qsortedBy$mBcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (char[])this.qsortedBy$mBc$sp(f, ev, ct);
   }

   public double[] qsortedBy$mBcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (double[])this.qsortedBy$mBc$sp(f, ev, ct);
   }

   public float[] qsortedBy$mBcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (float[])this.qsortedBy$mBc$sp(f, ev, ct);
   }

   public int[] qsortedBy$mBcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (int[])this.qsortedBy$mBc$sp(f, ev, ct);
   }

   public long[] qsortedBy$mBcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (long[])this.qsortedBy$mBc$sp(f, ev, ct);
   }

   public short[] qsortedBy$mBcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (short[])this.qsortedBy$mBc$sp(f, ev, ct);
   }

   public BoxedUnit[] qsortedBy$mBcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (BoxedUnit[])this.qsortedBy$mBc$sp(f, ev, ct);
   }

   public Object qsortedBy$mCc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Object arr2 = .MODULE$.array_clone(this.arr);
      Sorting$.MODULE$.sort(arr2, ord, ct);
      return arr2;
   }

   public boolean[] qsortedBy$mCcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (boolean[])this.qsortedBy$mCc$sp(f, ev, ct);
   }

   public byte[] qsortedBy$mCcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (byte[])this.qsortedBy$mCc$sp(f, ev, ct);
   }

   public char[] qsortedBy$mCcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (char[])this.qsortedBy$mCc$sp(f, ev, ct);
   }

   public double[] qsortedBy$mCcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (double[])this.qsortedBy$mCc$sp(f, ev, ct);
   }

   public float[] qsortedBy$mCcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (float[])this.qsortedBy$mCc$sp(f, ev, ct);
   }

   public int[] qsortedBy$mCcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (int[])this.qsortedBy$mCc$sp(f, ev, ct);
   }

   public long[] qsortedBy$mCcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (long[])this.qsortedBy$mCc$sp(f, ev, ct);
   }

   public short[] qsortedBy$mCcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (short[])this.qsortedBy$mCc$sp(f, ev, ct);
   }

   public BoxedUnit[] qsortedBy$mCcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (BoxedUnit[])this.qsortedBy$mCc$sp(f, ev, ct);
   }

   public Object qsortedBy$mDc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Object arr2 = .MODULE$.array_clone(this.arr);
      Sorting$.MODULE$.sort(arr2, ord, ct);
      return arr2;
   }

   public boolean[] qsortedBy$mDcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (boolean[])this.qsortedBy$mDc$sp(f, ev, ct);
   }

   public byte[] qsortedBy$mDcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (byte[])this.qsortedBy$mDc$sp(f, ev, ct);
   }

   public char[] qsortedBy$mDcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (char[])this.qsortedBy$mDc$sp(f, ev, ct);
   }

   public double[] qsortedBy$mDcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (double[])this.qsortedBy$mDc$sp(f, ev, ct);
   }

   public float[] qsortedBy$mDcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (float[])this.qsortedBy$mDc$sp(f, ev, ct);
   }

   public int[] qsortedBy$mDcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (int[])this.qsortedBy$mDc$sp(f, ev, ct);
   }

   public long[] qsortedBy$mDcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (long[])this.qsortedBy$mDc$sp(f, ev, ct);
   }

   public short[] qsortedBy$mDcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (short[])this.qsortedBy$mDc$sp(f, ev, ct);
   }

   public BoxedUnit[] qsortedBy$mDcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (BoxedUnit[])this.qsortedBy$mDc$sp(f, ev, ct);
   }

   public Object qsortedBy$mFc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Object arr2 = .MODULE$.array_clone(this.arr);
      Sorting$.MODULE$.sort(arr2, ord, ct);
      return arr2;
   }

   public boolean[] qsortedBy$mFcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (boolean[])this.qsortedBy$mFc$sp(f, ev, ct);
   }

   public byte[] qsortedBy$mFcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (byte[])this.qsortedBy$mFc$sp(f, ev, ct);
   }

   public char[] qsortedBy$mFcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (char[])this.qsortedBy$mFc$sp(f, ev, ct);
   }

   public double[] qsortedBy$mFcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (double[])this.qsortedBy$mFc$sp(f, ev, ct);
   }

   public float[] qsortedBy$mFcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (float[])this.qsortedBy$mFc$sp(f, ev, ct);
   }

   public int[] qsortedBy$mFcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (int[])this.qsortedBy$mFc$sp(f, ev, ct);
   }

   public long[] qsortedBy$mFcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (long[])this.qsortedBy$mFc$sp(f, ev, ct);
   }

   public short[] qsortedBy$mFcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (short[])this.qsortedBy$mFc$sp(f, ev, ct);
   }

   public BoxedUnit[] qsortedBy$mFcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (BoxedUnit[])this.qsortedBy$mFc$sp(f, ev, ct);
   }

   public Object qsortedBy$mIc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Object arr2 = .MODULE$.array_clone(this.arr);
      Sorting$.MODULE$.sort(arr2, ord, ct);
      return arr2;
   }

   public boolean[] qsortedBy$mIcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (boolean[])this.qsortedBy$mIc$sp(f, ev, ct);
   }

   public byte[] qsortedBy$mIcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (byte[])this.qsortedBy$mIc$sp(f, ev, ct);
   }

   public char[] qsortedBy$mIcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (char[])this.qsortedBy$mIc$sp(f, ev, ct);
   }

   public double[] qsortedBy$mIcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (double[])this.qsortedBy$mIc$sp(f, ev, ct);
   }

   public float[] qsortedBy$mIcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (float[])this.qsortedBy$mIc$sp(f, ev, ct);
   }

   public int[] qsortedBy$mIcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (int[])this.qsortedBy$mIc$sp(f, ev, ct);
   }

   public long[] qsortedBy$mIcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (long[])this.qsortedBy$mIc$sp(f, ev, ct);
   }

   public short[] qsortedBy$mIcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (short[])this.qsortedBy$mIc$sp(f, ev, ct);
   }

   public BoxedUnit[] qsortedBy$mIcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (BoxedUnit[])this.qsortedBy$mIc$sp(f, ev, ct);
   }

   public Object qsortedBy$mJc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Object arr2 = .MODULE$.array_clone(this.arr);
      Sorting$.MODULE$.sort(arr2, ord, ct);
      return arr2;
   }

   public boolean[] qsortedBy$mJcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (boolean[])this.qsortedBy$mJc$sp(f, ev, ct);
   }

   public byte[] qsortedBy$mJcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (byte[])this.qsortedBy$mJc$sp(f, ev, ct);
   }

   public char[] qsortedBy$mJcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (char[])this.qsortedBy$mJc$sp(f, ev, ct);
   }

   public double[] qsortedBy$mJcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (double[])this.qsortedBy$mJc$sp(f, ev, ct);
   }

   public float[] qsortedBy$mJcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (float[])this.qsortedBy$mJc$sp(f, ev, ct);
   }

   public int[] qsortedBy$mJcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (int[])this.qsortedBy$mJc$sp(f, ev, ct);
   }

   public long[] qsortedBy$mJcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (long[])this.qsortedBy$mJc$sp(f, ev, ct);
   }

   public short[] qsortedBy$mJcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (short[])this.qsortedBy$mJc$sp(f, ev, ct);
   }

   public BoxedUnit[] qsortedBy$mJcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (BoxedUnit[])this.qsortedBy$mJc$sp(f, ev, ct);
   }

   public Object qsortedBy$mSc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Object arr2 = .MODULE$.array_clone(this.arr);
      Sorting$.MODULE$.sort(arr2, ord, ct);
      return arr2;
   }

   public boolean[] qsortedBy$mScZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (boolean[])this.qsortedBy$mSc$sp(f, ev, ct);
   }

   public byte[] qsortedBy$mScB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (byte[])this.qsortedBy$mSc$sp(f, ev, ct);
   }

   public char[] qsortedBy$mScC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (char[])this.qsortedBy$mSc$sp(f, ev, ct);
   }

   public double[] qsortedBy$mScD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (double[])this.qsortedBy$mSc$sp(f, ev, ct);
   }

   public float[] qsortedBy$mScF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (float[])this.qsortedBy$mSc$sp(f, ev, ct);
   }

   public int[] qsortedBy$mScI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (int[])this.qsortedBy$mSc$sp(f, ev, ct);
   }

   public long[] qsortedBy$mScJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (long[])this.qsortedBy$mSc$sp(f, ev, ct);
   }

   public short[] qsortedBy$mScS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (short[])this.qsortedBy$mSc$sp(f, ev, ct);
   }

   public BoxedUnit[] qsortedBy$mScV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (BoxedUnit[])this.qsortedBy$mSc$sp(f, ev, ct);
   }

   public Object qsortedBy$mVc$sp(final Function1 f, final Order ev, final ClassTag ct) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Object arr2 = .MODULE$.array_clone(this.arr);
      Sorting$.MODULE$.sort(arr2, ord, ct);
      return arr2;
   }

   public boolean[] qsortedBy$mVcZ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (boolean[])this.qsortedBy$mVc$sp(f, ev, ct);
   }

   public byte[] qsortedBy$mVcB$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (byte[])this.qsortedBy$mVc$sp(f, ev, ct);
   }

   public char[] qsortedBy$mVcC$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (char[])this.qsortedBy$mVc$sp(f, ev, ct);
   }

   public double[] qsortedBy$mVcD$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (double[])this.qsortedBy$mVc$sp(f, ev, ct);
   }

   public float[] qsortedBy$mVcF$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (float[])this.qsortedBy$mVc$sp(f, ev, ct);
   }

   public int[] qsortedBy$mVcI$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (int[])this.qsortedBy$mVc$sp(f, ev, ct);
   }

   public long[] qsortedBy$mVcJ$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (long[])this.qsortedBy$mVc$sp(f, ev, ct);
   }

   public short[] qsortedBy$mVcS$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (short[])this.qsortedBy$mVc$sp(f, ev, ct);
   }

   public BoxedUnit[] qsortedBy$mVcV$sp(final Function1 f, final Order ev, final ClassTag ct) {
      return (BoxedUnit[])this.qsortedBy$mVc$sp(f, ev, ct);
   }

   public boolean[] qsortedWith$mcZ$sp(final Function2 f, final ClassTag ct) {
      return (boolean[])this.qsortedWith(f, ct);
   }

   public byte[] qsortedWith$mcB$sp(final Function2 f, final ClassTag ct) {
      return (byte[])this.qsortedWith(f, ct);
   }

   public char[] qsortedWith$mcC$sp(final Function2 f, final ClassTag ct) {
      return (char[])this.qsortedWith(f, ct);
   }

   public double[] qsortedWith$mcD$sp(final Function2 f, final ClassTag ct) {
      return (double[])this.qsortedWith(f, ct);
   }

   public float[] qsortedWith$mcF$sp(final Function2 f, final ClassTag ct) {
      return (float[])this.qsortedWith(f, ct);
   }

   public int[] qsortedWith$mcI$sp(final Function2 f, final ClassTag ct) {
      return (int[])this.qsortedWith(f, ct);
   }

   public long[] qsortedWith$mcJ$sp(final Function2 f, final ClassTag ct) {
      return (long[])this.qsortedWith(f, ct);
   }

   public short[] qsortedWith$mcS$sp(final Function2 f, final ClassTag ct) {
      return (short[])this.qsortedWith(f, ct);
   }

   public BoxedUnit[] qsortedWith$mcV$sp(final Function2 f, final ClassTag ct) {
      return (BoxedUnit[])this.qsortedWith(f, ct);
   }

   public void qselect$mcZ$sp(final int k, final Order ev, final ClassTag ct) {
      this.qselect(k, ev, ct);
   }

   public void qselect$mcB$sp(final int k, final Order ev, final ClassTag ct) {
      this.qselect(k, ev, ct);
   }

   public void qselect$mcC$sp(final int k, final Order ev, final ClassTag ct) {
      this.qselect(k, ev, ct);
   }

   public void qselect$mcD$sp(final int k, final Order ev, final ClassTag ct) {
      this.qselect(k, ev, ct);
   }

   public void qselect$mcF$sp(final int k, final Order ev, final ClassTag ct) {
      this.qselect(k, ev, ct);
   }

   public void qselect$mcI$sp(final int k, final Order ev, final ClassTag ct) {
      this.qselect(k, ev, ct);
   }

   public void qselect$mcJ$sp(final int k, final Order ev, final ClassTag ct) {
      this.qselect(k, ev, ct);
   }

   public void qselect$mcS$sp(final int k, final Order ev, final ClassTag ct) {
      this.qselect(k, ev, ct);
   }

   public void qselect$mcV$sp(final int k, final Order ev, final ClassTag ct) {
      this.qselect(k, ev, ct);
   }

   public boolean[] qselected$mcZ$sp(final int k, final Order ev, final ClassTag ct) {
      return (boolean[])this.qselected(k, ev, ct);
   }

   public byte[] qselected$mcB$sp(final int k, final Order ev, final ClassTag ct) {
      return (byte[])this.qselected(k, ev, ct);
   }

   public char[] qselected$mcC$sp(final int k, final Order ev, final ClassTag ct) {
      return (char[])this.qselected(k, ev, ct);
   }

   public double[] qselected$mcD$sp(final int k, final Order ev, final ClassTag ct) {
      return (double[])this.qselected(k, ev, ct);
   }

   public float[] qselected$mcF$sp(final int k, final Order ev, final ClassTag ct) {
      return (float[])this.qselected(k, ev, ct);
   }

   public int[] qselected$mcI$sp(final int k, final Order ev, final ClassTag ct) {
      return (int[])this.qselected(k, ev, ct);
   }

   public long[] qselected$mcJ$sp(final int k, final Order ev, final ClassTag ct) {
      return (long[])this.qselected(k, ev, ct);
   }

   public short[] qselected$mcS$sp(final int k, final Order ev, final ClassTag ct) {
      return (short[])this.qselected(k, ev, ct);
   }

   public BoxedUnit[] qselected$mcV$sp(final int k, final Order ev, final ClassTag ct) {
      return (BoxedUnit[])this.qselected(k, ev, ct);
   }

   public boolean[] qshuffled$mcZ$sp(final Generator gen) {
      return (boolean[])this.qshuffled(gen);
   }

   public byte[] qshuffled$mcB$sp(final Generator gen) {
      return (byte[])this.qshuffled(gen);
   }

   public char[] qshuffled$mcC$sp(final Generator gen) {
      return (char[])this.qshuffled(gen);
   }

   public double[] qshuffled$mcD$sp(final Generator gen) {
      return (double[])this.qshuffled(gen);
   }

   public float[] qshuffled$mcF$sp(final Generator gen) {
      return (float[])this.qshuffled(gen);
   }

   public int[] qshuffled$mcI$sp(final Generator gen) {
      return (int[])this.qshuffled(gen);
   }

   public long[] qshuffled$mcJ$sp(final Generator gen) {
      return (long[])this.qshuffled(gen);
   }

   public short[] qshuffled$mcS$sp(final Generator gen) {
      return (short[])this.qshuffled(gen);
   }

   public BoxedUnit[] qshuffled$mcV$sp(final Generator gen) {
      return (BoxedUnit[])this.qshuffled(gen);
   }

   public boolean[] qsampled$mcZ$sp(final int n, final Generator gen, final ClassTag ct) {
      return (boolean[])this.qsampled(n, gen, ct);
   }

   public byte[] qsampled$mcB$sp(final int n, final Generator gen, final ClassTag ct) {
      return (byte[])this.qsampled(n, gen, ct);
   }

   public char[] qsampled$mcC$sp(final int n, final Generator gen, final ClassTag ct) {
      return (char[])this.qsampled(n, gen, ct);
   }

   public double[] qsampled$mcD$sp(final int n, final Generator gen, final ClassTag ct) {
      return (double[])this.qsampled(n, gen, ct);
   }

   public float[] qsampled$mcF$sp(final int n, final Generator gen, final ClassTag ct) {
      return (float[])this.qsampled(n, gen, ct);
   }

   public int[] qsampled$mcI$sp(final int n, final Generator gen, final ClassTag ct) {
      return (int[])this.qsampled(n, gen, ct);
   }

   public long[] qsampled$mcJ$sp(final int n, final Generator gen, final ClassTag ct) {
      return (long[])this.qsampled(n, gen, ct);
   }

   public short[] qsampled$mcS$sp(final int n, final Generator gen, final ClassTag ct) {
      return (short[])this.qsampled(n, gen, ct);
   }

   public BoxedUnit[] qsampled$mcV$sp(final int n, final Generator gen, final ClassTag ct) {
      return (BoxedUnit[])this.qsampled(n, gen, ct);
   }

   public ArrayOps(final Object arr) {
      this.arr = arr;
   }
}
