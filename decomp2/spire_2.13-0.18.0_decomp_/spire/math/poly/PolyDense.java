package spire.math.poly;

import algebra.ring.Field;
import algebra.ring.Rig;
import algebra.ring.Ring;
import algebra.ring.Rng;
import algebra.ring.Semiring;
import algebra.ring.Signed;
import cats.kernel.Eq;
import cats.kernel.Order;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.collection.BufferedIterator;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;
import spire.math.Polynomial;
import spire.math.Polynomial$;

@ScalaSignature(
   bytes = "\u0006\u0005\tec\u0001B\u0012%\u0001-B\u0001\u0002\u0015\u0001\u0003\u0006\u0004%\t!\u0015\u0005\t+\u0002\u0011\t\u0011)A\u0005%\"Aa\u000b\u0001BC\u0002\u0013\rq\u000b\u0003\u0005a\u0001\t\u0005\t\u0015!\u0003Y\u0011\u0019\t\u0007\u0001\"\u0001)E\")\u0001\u000e\u0001C\u0001S\")Q\u000e\u0001C\u0001]\"9\u00111\u0002\u0001\u0005\u0002\u00055\u0001bBA\n\u0001\u0011\u0005\u0011Q\u0003\u0005\b\u0003_\u0001A\u0011IA\u0019\u0011\u001d\t\u0019\u0005\u0001C\u0001\u0003\u000b2a!!\u0017\u0001\u0001\u0005m\u0003BB1\r\t\u0003\ti\u0006C\u0004\u0002d1\u0001\u000b\u0015\u00026\t\u0011\u0005\u0015D\u0002)C\u0005\u0003OBq!!\u001b\r\t\u0003\tY\u0007C\u0004\u0002t1!\t%!\u001e\t\u000f\u0005]\u0004\u0001\"\u0001\u0002z!9\u0011Q\u0010\u0001\u0005\u0002\u0005}\u0004bBAE\u0001\u0011\u0005\u00111\u0012\u0005\b\u0003\u001f\u0003A\u0011AAI\u0011\u001d\tI\n\u0001C\u0001\u0003WBq!a'\u0001\t\u0003\ti\nC\u0004\u0002(\u0002!\t!!+\t\u000f\u0005U\u0006\u0001\"\u0001\u00028\"9\u0011Q\u0019\u0001\u0005\u0002\u0005\u001d\u0007bBAi\u0001\u0011\u0005\u00111\u001b\u0005\b\u0003?\u0004A\u0011AAq\u0011\u001d\tY\u000f\u0001C\u0001\u0003[<q!!?%\u0011\u0003\tYP\u0002\u0004$I!\u0005\u0011Q \u0005\u0007C~!\t!a@\t\u000f\t\u0005q\u0004\"\u0004\u0003\u0004!A!qE\u0010\u0005\u0006\u0019\u0012ICA\u0005Q_2LH)\u001a8tK*\u0011QEJ\u0001\u0005a>d\u0017P\u0003\u0002(Q\u0005!Q.\u0019;i\u0015\u0005I\u0013!B:qSJ,7\u0001A\u000b\u0003Ye\u001a2\u0001A\u00174!\tq\u0013'D\u00010\u0015\u0005\u0001\u0014!B:dC2\f\u0017B\u0001\u001a0\u0005\u0019\te.\u001f*fMB\u0019A'N\u001c\u000e\u0003\u0019J!A\u000e\u0014\u0003\u0015A{G.\u001f8p[&\fG\u000e\u0005\u00029s1\u0001A!\u0003\u001e\u0001A\u0003\u0005\tQ1\u0001<\u0005\u0005\u0019\u0015C\u0001\u001f@!\tqS(\u0003\u0002?_\t9aj\u001c;iS:<\u0007C\u0001\u0018A\u0013\t\tuFA\u0002B]fD3!O\"G!\tqC)\u0003\u0002F_\tY1\u000f]3dS\u0006d\u0017N_3ec\u0015\u0019s\t\u0013&J\u001d\tq\u0003*\u0003\u0002J_\u00051Ai\\;cY\u0016\fD\u0001J&Pa9\u0011AjT\u0007\u0002\u001b*\u0011aJK\u0001\u0007yI|w\u000e\u001e \n\u0003A\naaY8fM\u001a\u001cX#\u0001*\u0011\u00079\u001av'\u0003\u0002U_\t)\u0011I\u001d:bs\u000691m\\3gMN\u0004\u0013AA2u+\u0005A\u0006cA-^o9\u0011!lW\u0007\u0002Q%\u0011A\fK\u0001\ba\u0006\u001c7.Y4f\u0013\tqvL\u0001\u0005DY\u0006\u001c8\u000fV1h\u0015\ta\u0006&A\u0002di\u0002\na\u0001P5oSRtDCA2h)\t!g\rE\u0002f\u0001]j\u0011\u0001\n\u0005\u0006-\u0016\u0001\u001d\u0001\u0017\u0005\u0006!\u0016\u0001\rAU\u0001\u0007I\u0016<'/Z3\u0016\u0003)\u0004\"AL6\n\u00051|#aA%oi\u0006AAo\\*qCJ\u001cX\r\u0006\u0003pe\u0006\u0005\u0001cA3qo%\u0011\u0011\u000f\n\u0002\u000b!>d\u0017p\u00159beN,\u0007\"B:\b\u0001\b!\u0018\u0001\u0002:j]\u001e\u00042!^?8\u001d\t18P\u0004\u0002xs:\u0011A\n_\u0005\u0002S%\u0011!\u0010K\u0001\bC2<WM\u0019:b\u0013\taFP\u0003\u0002{Q%\u0011ap \u0002\t'\u0016l\u0017N]5oO*\u0011A\f \u0005\b\u0003\u00079\u00019AA\u0003\u0003\t)\u0017\u000f\u0005\u0003v\u0003\u000f9\u0014bAA\u0005\u007f\n\u0011Q)]\u0001\bi>$UM\\:f)\u0015!\u0017qBA\t\u0011\u0015\u0019\b\u0002q\u0001u\u0011\u001d\t\u0019\u0001\u0003a\u0002\u0003\u000b\tqAZ8sK\u0006\u001c\u0007.\u0006\u0003\u0002\u0018\u0005-B\u0003BA\r\u0003?\u00012ALA\u000e\u0013\r\tib\f\u0002\u0005+:LG\u000fC\u0004\u0002\"%\u0001\r!a\t\u0002\u0003\u0019\u0004rALA\u0013U^\nI#C\u0002\u0002(=\u0012\u0011BR;oGRLwN\u001c\u001a\u0011\u0007a\nY\u0003\u0002\u0004\u0002.%\u0011\ra\u000f\u0002\u0002+\u0006qam\u001c:fC\u000eDgj\u001c8[KJ|W\u0003BA\u001a\u0003\u0003\"B!!\u000e\u0002<Q1\u0011\u0011DA\u001c\u0003sAQa\u001d\u0006A\u0004QDq!a\u0001\u000b\u0001\b\t)\u0001C\u0004\u0002\")\u0001\r!!\u0010\u0011\u000f9\n)C[\u001c\u0002@A\u0019\u0001(!\u0011\u0005\r\u00055\"B1\u0001<\u00035!XM]7t\u0013R,'/\u0019;peV\u0011\u0011q\t\t\u0007\u0003\u0013\ni%a\u0015\u000f\u0007-\u000bY%\u0003\u0002]_%!\u0011qJA)\u0005!IE/\u001a:bi>\u0014(B\u0001/0!\u0011)\u0017QK\u001c\n\u0007\u0005]CE\u0001\u0003UKJl'\u0001\u0004+fe6LE/\u001a:bi>\u00148\u0003\u0002\u0007.\u0003\u000f\"\"!a\u0018\u0011\u0007\u0005\u0005D\"D\u0001\u0001\u0003\u0005)\u0017\u0001\u00034j]\u0012tU\r\u001f;\u0015\u0005\u0005e\u0011a\u00025bg:+\u0007\u0010^\u000b\u0003\u0003[\u00022ALA8\u0013\r\t\th\f\u0002\b\u0005>|G.Z1o\u0003\u0011qW\r\u001f;\u0015\u0005\u0005M\u0013aC2pK\u001a47/\u0011:sCf$2AUA>\u0011\u0015\u0019(\u0003q\u0001u\u0003\rqG\u000f\u001b\u000b\u0005\u0003\u0003\u000b)\tF\u00028\u0003\u0007CQa]\nA\u0004QDa!a\"\u0014\u0001\u0004Q\u0017!\u00018\u0002#5\f\u0007p\u0014:eKJ$VM]7D_\u00164g\rF\u00028\u0003\u001bCQa\u001d\u000bA\u0004Q\f\u0001B]3ek\u000e$X/\u001c\u000b\bg\u0005M\u0015QSAL\u0011\u001d\t\u0019'\u0006a\u0002\u0003\u000bAQa]\u000bA\u0004QDQAV\u000bA\u0004a\u000ba![:[KJ|\u0017!B1qa2LH\u0003BAP\u0003G#2aNAQ\u0011\u0015\u0019x\u0003q\u0001u\u0011\u0019\t)k\u0006a\u0001o\u0005\t\u00010\u0001\u0006eKJLg/\u0019;jm\u0016$RaMAV\u0003gCaa\u001d\rA\u0004\u00055\u0006\u0003B;\u00020^J1!!-\u0000\u0005\u0011\u0011\u0016N\\4\t\u000f\u0005\r\u0001\u0004q\u0001\u0002\u0006\u0005A\u0011N\u001c;fOJ\fG\u000eF\u00034\u0003s\u000b\u0019\rC\u0004\u0002<f\u0001\u001d!!0\u0002\u000b\u0019LW\r\u001c3\u0011\tU\fylN\u0005\u0004\u0003\u0003|(!\u0002$jK2$\u0007bBA\u00023\u0001\u000f\u0011QA\u0001\rk:\f'/_0%[&tWo\u001d\u000b\u0004g\u0005%\u0007BB:\u001b\u0001\b\tY\r\u0005\u0003v\u0003\u001b<\u0014bAAh\u007f\n\u0019!K\\4\u0002\u000b\u0011\u0002H.^:\u0015\t\u0005U\u00171\u001c\u000b\u0006g\u0005]\u0017\u0011\u001c\u0005\u0006gn\u0001\u001d\u0001\u001e\u0005\b\u0003\u0007Y\u00029AA\u0003\u0011\u0019\tin\u0007a\u0001g\u0005\u0019!\u000f[:\u0002\r\u0011\"\u0018.\\3t)\u0011\t\u0019/!;\u0015\u000bM\n)/a:\t\u000bMd\u00029\u0001;\t\u000f\u0005\rA\u0004q\u0001\u0002\u0006!1\u0011Q\u001c\u000fA\u0002M\nA\u0002\n;j[\u0016\u001cHeY8m_:$B!a<\u0002vR)1'!=\u0002t\")1/\ba\u0002i\"9\u00111A\u000fA\u0004\u0005\u0015\u0001BBA|;\u0001\u0007q'A\u0001l\u0003%\u0001v\u000e\\=EK:\u001cX\r\u0005\u0002f?M\u0011q$\f\u000b\u0003\u0003w\f\u0011\u0002\u001d7vg\u0012+gn]3\u0016\t\t\u0015!Q\u0002\u000b\u0007\u0005\u000f\u0011\tC!\n\u0015\u0011\t%!q\u0002B\u000b\u00057\u0001B\u0001N\u001b\u0003\fA\u0019\u0001H!\u0004\u0005\u000bi\n#\u0019A\u001e\t\u0013\tE\u0011%!AA\u0004\tM\u0011AC3wS\u0012,gnY3%cA!Q/ B\u0006\u0011%\u00119\"IA\u0001\u0002\b\u0011I\"\u0001\u0006fm&$WM\\2fII\u0002R!^A\u0004\u0005\u0017A\u0011B!\b\"\u0003\u0003\u0005\u001dAa\b\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$3\u0007\u0005\u0003Z;\n-\u0001b\u0002B\u0012C\u0001\u0007!\u0011B\u0001\u0004Y\"\u001c\bbBAoC\u0001\u0007!\u0011B\u0001\rcV|G/\\8e\t\u0016t7/Z\u000b\u0005\u0005W\u0011I\u0004\u0006\u0004\u0003.\tM#q\u000b\u000b\t\u0005_\u0011\tEa\u0012\u0003NA9aF!\r\u00036\tU\u0012b\u0001B\u001a_\t1A+\u001e9mKJ\u0002B\u0001N\u001b\u00038A\u0019\u0001H!\u000f\u0005\u0013i\u0012\u0003\u0015!A\u0001\u0006\u0004Y\u0004&\u0002B\u001d\u0007\nu\u0012GB\u0012H\u0011\n}\u0012*\r\u0003%\u0017>\u0003\u0004\"\u0003B\"E\u0005\u0005\t9\u0001B#\u0003))g/\u001b3f]\u000e,G\u0005\u000e\t\u0006k\u0006}&q\u0007\u0005\n\u0005\u0013\u0012\u0013\u0011!a\u0002\u0005\u0017\n!\"\u001a<jI\u0016t7-\u001a\u00136!\u0015)\u0018q\u0001B\u001c\u0011%\u0011yEIA\u0001\u0002\b\u0011\t&\u0001\u0006fm&$WM\\2fIY\u0002B!W/\u00038!9!1\u0005\u0012A\u0002\tU\u0003\u0003B3\u0001\u0005oAq!!8#\u0001\u0004\u0011)\u0004"
)
public class PolyDense implements Polynomial {
   public final Object coeffs;
   private final ClassTag ct;

   public List terms(final Semiring ring, final Eq eq) {
      return Polynomial.terms$(this, ring, eq);
   }

   public List terms$mcD$sp(final Semiring ring, final Eq eq) {
      return Polynomial.terms$mcD$sp$(this, ring, eq);
   }

   public Map data(final Semiring ring, final Eq eq) {
      return Polynomial.data$(this, ring, eq);
   }

   public Map data$mcD$sp(final Semiring ring, final Eq eq) {
      return Polynomial.data$mcD$sp$(this, ring, eq);
   }

   public Roots roots(final RootFinder finder) {
      return Polynomial.roots$(this, finder);
   }

   public Term maxTerm(final Semiring ring) {
      return Polynomial.maxTerm$(this, ring);
   }

   public Term maxTerm$mcD$sp(final Semiring ring) {
      return Polynomial.maxTerm$mcD$sp$(this, ring);
   }

   public Term minTerm(final Semiring ring, final Eq eq) {
      return Polynomial.minTerm$(this, ring, eq);
   }

   public Term minTerm$mcD$sp(final Semiring ring, final Eq eq) {
      return Polynomial.minTerm$mcD$sp$(this, ring, eq);
   }

   public boolean isConstant() {
      return Polynomial.isConstant$(this);
   }

   public Object evalWith(final Object x, final Function1 f, final Semiring evidence$56, final Eq evidence$57, final ClassTag evidence$58) {
      return Polynomial.evalWith$(this, x, f, evidence$56, evidence$57, evidence$58);
   }

   public Object evalWith$mcD$sp(final Object x, final Function1 f, final Semiring evidence$56, final Eq evidence$57, final ClassTag evidence$58) {
      return Polynomial.evalWith$mcD$sp$(this, x, f, evidence$56, evidence$57, evidence$58);
   }

   public Polynomial compose(final Polynomial y, final Rig ring, final Eq eq) {
      return Polynomial.compose$(this, y, ring, eq);
   }

   public Polynomial compose$mcD$sp(final Polynomial y, final Rig ring, final Eq eq) {
      return Polynomial.compose$mcD$sp$(this, y, ring, eq);
   }

   public Polynomial shift(final Object h, final Ring ring, final Eq eq) {
      return Polynomial.shift$(this, h, ring, eq);
   }

   public Polynomial shift$mcD$sp(final double h, final Ring ring, final Eq eq) {
      return Polynomial.shift$mcD$sp$(this, h, ring, eq);
   }

   public Polynomial monic(final Field f, final Eq eq) {
      return Polynomial.monic$(this, f, eq);
   }

   public Polynomial monic$mcD$sp(final Field f, final Eq eq) {
      return Polynomial.monic$mcD$sp$(this, f, eq);
   }

   public int signVariations(final Semiring ring, final Order order, final Signed signed) {
      return Polynomial.signVariations$(this, ring, order, signed);
   }

   public int signVariations$mcD$sp(final Semiring ring, final Order order, final Signed signed) {
      return Polynomial.signVariations$mcD$sp$(this, ring, order, signed);
   }

   public Polynomial removeZeroRoots(final Semiring ring, final Eq eq) {
      return Polynomial.removeZeroRoots$(this, ring, eq);
   }

   public Polynomial removeZeroRoots$mcD$sp(final Semiring ring, final Eq eq) {
      return Polynomial.removeZeroRoots$mcD$sp$(this, ring, eq);
   }

   public Polynomial map(final Function1 f, final Semiring evidence$59, final Eq evidence$60, final ClassTag evidence$61) {
      return Polynomial.map$(this, f, evidence$59, evidence$60, evidence$61);
   }

   public Polynomial map$mcD$sp(final Function1 f, final Semiring evidence$59, final Eq evidence$60, final ClassTag evidence$61) {
      return Polynomial.map$mcD$sp$(this, f, evidence$59, evidence$60, evidence$61);
   }

   public Polynomial mapTerms(final Function1 f, final Semiring evidence$62, final Eq evidence$63, final ClassTag evidence$64) {
      return Polynomial.mapTerms$(this, f, evidence$62, evidence$63, evidence$64);
   }

   public Polynomial mapTerms$mcD$sp(final Function1 f, final Semiring evidence$62, final Eq evidence$63, final ClassTag evidence$64) {
      return Polynomial.mapTerms$mcD$sp$(this, f, evidence$62, evidence$63, evidence$64);
   }

   public Polynomial flip(final Rng ring, final Eq eq) {
      return Polynomial.flip$(this, ring, eq);
   }

   public Polynomial flip$mcD$sp(final Rng ring, final Eq eq) {
      return Polynomial.flip$mcD$sp$(this, ring, eq);
   }

   public Polynomial reciprocal(final Semiring ring, final Eq eq) {
      return Polynomial.reciprocal$(this, ring, eq);
   }

   public Polynomial reciprocal$mcD$sp(final Semiring ring, final Eq eq) {
      return Polynomial.reciprocal$mcD$sp$(this, ring, eq);
   }

   public Polynomial $minus(final Polynomial rhs, final Rng ring, final Eq eq) {
      return Polynomial.$minus$(this, rhs, ring, eq);
   }

   public Polynomial $minus$mcD$sp(final Polynomial rhs, final Rng ring, final Eq eq) {
      return Polynomial.$minus$mcD$sp$(this, rhs, ring, eq);
   }

   public Polynomial $times$times(final int k, final Rig ring, final Eq eq) {
      return Polynomial.$times$times$(this, k, ring, eq);
   }

   public Polynomial $times$times$mcD$sp(final int k, final Rig ring, final Eq eq) {
      return Polynomial.$times$times$mcD$sp$(this, k, ring, eq);
   }

   public Polynomial pow(final int k, final Rig ring, final Eq eq) {
      return Polynomial.pow$(this, k, ring, eq);
   }

   public Polynomial pow$mcD$sp(final int k, final Rig ring, final Eq eq) {
      return Polynomial.pow$mcD$sp$(this, k, ring, eq);
   }

   public Polynomial $colon$times(final Object k, final Semiring ring, final Eq eq) {
      return Polynomial.$colon$times$(this, k, ring, eq);
   }

   public Polynomial $colon$times$mcD$sp(final double k, final Semiring ring, final Eq eq) {
      return Polynomial.$colon$times$mcD$sp$(this, k, ring, eq);
   }

   public Polynomial $colon$div(final Object k, final Field field, final Eq eq) {
      return Polynomial.$colon$div$(this, k, field, eq);
   }

   public Polynomial $colon$div$mcD$sp(final double k, final Field field, final Eq eq) {
      return Polynomial.$colon$div$mcD$sp$(this, k, field, eq);
   }

   public int hashCode() {
      return Polynomial.hashCode$(this);
   }

   public boolean equals(final Object that) {
      return Polynomial.equals$(this, that);
   }

   public String toString() {
      return Polynomial.toString$(this);
   }

   public Object coeffs() {
      return this.coeffs;
   }

   public ClassTag ct() {
      return this.ct;
   }

   public int degree() {
      return this.isZero() ? 0 : .MODULE$.array_length(this.coeffs()) - 1;
   }

   public PolySparse toSparse(final Semiring ring, final Eq eq) {
      return Polynomial$.MODULE$.sparse(this.data(ring, eq), ring, eq, this.ct());
   }

   public PolyDense toDense(final Semiring ring, final Eq eq) {
      return this;
   }

   public void foreach(final Function2 f) {
      for(int index$macro$1 = 0; index$macro$1 < .MODULE$.array_length(this.coeffs()); ++index$macro$1) {
         f.apply(BoxesRunTime.boxToInteger(index$macro$1), .MODULE$.array_apply(this.coeffs(), index$macro$1));
      }

   }

   public void foreachNonZero(final Function2 f, final Semiring ring, final Eq eq) {
      for(int index$macro$1 = 0; index$macro$1 < .MODULE$.array_length(this.coeffs()); ++index$macro$1) {
         Object c = .MODULE$.array_apply(this.coeffs(), index$macro$1);
         if (eq.neqv(c, ring.zero())) {
            f.apply(BoxesRunTime.boxToInteger(index$macro$1), c);
         }
      }

   }

   public Iterator termsIterator() {
      return new TermIterator();
   }

   public Object coeffsArray(final Semiring ring) {
      return this.coeffs();
   }

   public Object nth(final int n, final Semiring ring) {
      return n < .MODULE$.array_length(this.coeffs()) ? .MODULE$.array_apply(this.coeffs(), n) : ring.zero();
   }

   public Object maxOrderTermCoeff(final Semiring ring) {
      return this.isZero() ? ring.zero() : .MODULE$.array_apply(this.coeffs(), this.degree());
   }

   public Polynomial reductum(final Eq e, final Semiring ring, final ClassTag ct) {
      int i;
      for(i = .MODULE$.array_length(this.coeffs()) - 2; i >= 0 && e.eqv(.MODULE$.array_apply(this.coeffs(), i), ring.zero()); --i) {
      }

      PolyDense var10000;
      if (i < 0) {
         var10000 = new PolyDense(ct.newArray(0), ct);
      } else {
         Object arr = ct.newArray(i + 1);
         System.arraycopy(this.coeffs(), 0, arr, 0, i + 1);
         var10000 = new PolyDense(arr, ct);
      }

      return var10000;
   }

   public boolean isZero() {
      return .MODULE$.array_length(this.coeffs()) == 0;
   }

   public Object apply(final Object x, final Semiring ring) {
      if (this.isZero()) {
         return ring.zero();
      } else {
         int even = .MODULE$.array_length(this.coeffs()) - 1;
         int odd = .MODULE$.array_length(this.coeffs()) - 2;
         if ((even & 1) == 1) {
            even = odd;
            odd = .MODULE$.array_length(this.coeffs()) - 1;
         }

         Object c0 = .MODULE$.array_apply(this.coeffs(), even);
         Object x2 = ring.pow(x, 2);

         for(int index$macro$1 = even - 2; index$macro$1 >= 0; index$macro$1 -= 2) {
            c0 = ring.plus(.MODULE$.array_apply(this.coeffs(), index$macro$1), ring.times(c0, x2));
         }

         Object var10000;
         if (odd >= 1) {
            Object c1 = .MODULE$.array_apply(this.coeffs(), odd);

            for(int index$macro$2 = odd - 2; index$macro$2 >= 1; index$macro$2 -= 2) {
               c1 = ring.plus(.MODULE$.array_apply(this.coeffs(), index$macro$2), ring.times(c1, x2));
            }

            var10000 = ring.plus(c0, ring.times(c1, x));
         } else {
            var10000 = c0;
         }

         return var10000;
      }
   }

   public Polynomial derivative(final Ring ring, final Eq eq) {
      if (this.isZero()) {
         return this;
      } else {
         Object cs = this.ct().newArray(this.degree());
         int j = .MODULE$.array_length(this.coeffs()) - 1;

         for(int index$macro$1 = .MODULE$.array_length(cs) - 1; index$macro$1 >= 0; --index$macro$1) {
            .MODULE$.array_update(cs, index$macro$1, ring.times(ring.fromInt(j), .MODULE$.array_apply(this.coeffs(), j)));
            --j;
         }

         return Polynomial$.MODULE$.dense(cs, ring, eq, this.ct());
      }
   }

   public Polynomial integral(final Field field, final Eq eq) {
      Object cs = this.ct().newArray(.MODULE$.array_length(this.coeffs()) + 1);
      .MODULE$.array_update(cs, 0, field.zero());

      for(int index$macro$1 = 0; index$macro$1 < .MODULE$.array_length(this.coeffs()); ++index$macro$1) {
         .MODULE$.array_update(cs, index$macro$1 + 1, field.div(.MODULE$.array_apply(this.coeffs(), index$macro$1), field.fromInt(index$macro$1 + 1)));
      }

      return Polynomial$.MODULE$.dense(cs, field, eq, this.ct());
   }

   public Polynomial unary_$minus(final Rng ring) {
      Object negArray = this.ct().newArray(.MODULE$.array_length(this.coeffs()));

      for(int index$macro$1 = 0; index$macro$1 < .MODULE$.array_length(this.coeffs()); ++index$macro$1) {
         .MODULE$.array_update(negArray, index$macro$1, ring.negate(.MODULE$.array_apply(this.coeffs(), index$macro$1)));
      }

      return new PolyDense(negArray, this.ct());
   }

   public Polynomial $plus(final Polynomial rhs, final Semiring ring, final Eq eq) {
      return PolyDense$.MODULE$.spire$math$poly$PolyDense$$plusDense(this, rhs, ring, eq, this.ct());
   }

   public Polynomial $times(final Polynomial rhs, final Semiring ring, final Eq eq) {
      if (rhs.isZero()) {
         return rhs;
      } else if (this.isZero()) {
         return this;
      } else {
         Object lcs = this.coeffsArray(ring);
         Object rcs = rhs.coeffsArray(ring);
         Object cs = this.ct().newArray(.MODULE$.array_length(lcs) + .MODULE$.array_length(rcs) - 1);

         for(int index$macro$1 = 0; index$macro$1 < .MODULE$.array_length(cs); ++index$macro$1) {
            .MODULE$.array_update(cs, index$macro$1, ring.zero());
         }

         for(int index$macro$3 = 0; index$macro$3 < .MODULE$.array_length(lcs); ++index$macro$3) {
            Object c = .MODULE$.array_apply(lcs, index$macro$3);
            int k = index$macro$3;

            for(int index$macro$2 = 0; index$macro$2 < .MODULE$.array_length(rcs); ++index$macro$2) {
               .MODULE$.array_update(cs, k, ring.plus(.MODULE$.array_apply(cs, k), ring.times(c, .MODULE$.array_apply(rcs, index$macro$2))));
               ++k;
            }
         }

         return Polynomial$.MODULE$.dense(cs, ring, eq, this.ct());
      }
   }

   public Polynomial $times$colon(final Object k, final Semiring ring, final Eq eq) {
      PolyDense var10000;
      if (eq.eqv(k, ring.zero())) {
         var10000 = Polynomial$.MODULE$.dense(this.ct().newArray(0), ring, eq, this.ct());
      } else {
         Object cs = this.ct().newArray(.MODULE$.array_length(this.coeffs()));

         for(int index$macro$1 = 0; index$macro$1 < .MODULE$.array_length(cs); ++index$macro$1) {
            .MODULE$.array_update(cs, index$macro$1, ring.times(k, .MODULE$.array_apply(this.coeffs(), index$macro$1)));
         }

         var10000 = Polynomial$.MODULE$.dense(cs, ring, eq, this.ct());
      }

      return var10000;
   }

   public double[] coeffs$mcD$sp() {
      return (double[])this.coeffs();
   }

   public PolySparse toSparse$mcD$sp(final Semiring ring, final Eq eq) {
      return this.toSparse(ring, eq);
   }

   public PolyDense toDense$mcD$sp(final Semiring ring, final Eq eq) {
      return this.toDense(ring, eq);
   }

   public void foreach$mcD$sp(final Function2 f) {
      this.foreach(f);
   }

   public void foreachNonZero$mcD$sp(final Function2 f, final Semiring ring, final Eq eq) {
      this.foreachNonZero(f, ring, eq);
   }

   public double[] coeffsArray$mcD$sp(final Semiring ring) {
      return (double[])this.coeffsArray(ring);
   }

   public double nth$mcD$sp(final int n, final Semiring ring) {
      return BoxesRunTime.unboxToDouble(this.nth(n, ring));
   }

   public double maxOrderTermCoeff$mcD$sp(final Semiring ring) {
      return BoxesRunTime.unboxToDouble(this.maxOrderTermCoeff(ring));
   }

   public Polynomial reductum$mcD$sp(final Eq e, final Semiring ring, final ClassTag ct) {
      return this.reductum(e, ring, ct);
   }

   public double apply$mcD$sp(final double x, final Semiring ring) {
      return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(x), ring));
   }

   public Polynomial derivative$mcD$sp(final Ring ring, final Eq eq) {
      return this.derivative(ring, eq);
   }

   public Polynomial integral$mcD$sp(final Field field, final Eq eq) {
      return this.integral(field, eq);
   }

   public Polynomial unary_$minus$mcD$sp(final Rng ring) {
      return this.unary_$minus(ring);
   }

   public Polynomial $plus$mcD$sp(final Polynomial rhs, final Semiring ring, final Eq eq) {
      return this.$plus(rhs, ring, eq);
   }

   public Polynomial $times$mcD$sp(final Polynomial rhs, final Semiring ring, final Eq eq) {
      return this.$times(rhs, ring, eq);
   }

   public Polynomial $times$colon$mcD$sp(final double k, final Semiring ring, final Eq eq) {
      return this.$times$colon(BoxesRunTime.boxToDouble(k), ring, eq);
   }

   public boolean specInstance$() {
      return false;
   }

   public PolyDense(final Object coeffs, final ClassTag ct) {
      this.coeffs = coeffs;
      this.ct = ct;
      Polynomial.$init$(this);
   }

   public class TermIterator implements Iterator {
      private int e;
      // $FF: synthetic field
      public final PolyDense $outer;

      /** @deprecated */
      public final boolean hasDefiniteSize() {
         return Iterator.hasDefiniteSize$(this);
      }

      public final Iterator iterator() {
         return Iterator.iterator$(this);
      }

      public Option nextOption() {
         return Iterator.nextOption$(this);
      }

      public boolean contains(final Object elem) {
         return Iterator.contains$(this, elem);
      }

      public BufferedIterator buffered() {
         return Iterator.buffered$(this);
      }

      public Iterator padTo(final int len, final Object elem) {
         return Iterator.padTo$(this, len, elem);
      }

      public Tuple2 partition(final Function1 p) {
         return Iterator.partition$(this, p);
      }

      public Iterator.GroupedIterator grouped(final int size) {
         return Iterator.grouped$(this, size);
      }

      public Iterator.GroupedIterator sliding(final int size, final int step) {
         return Iterator.sliding$(this, size, step);
      }

      public int sliding$default$2() {
         return Iterator.sliding$default$2$(this);
      }

      public Iterator scanLeft(final Object z, final Function2 op) {
         return Iterator.scanLeft$(this, z, op);
      }

      /** @deprecated */
      public Iterator scanRight(final Object z, final Function2 op) {
         return Iterator.scanRight$(this, z, op);
      }

      public int indexWhere(final Function1 p, final int from) {
         return Iterator.indexWhere$(this, p, from);
      }

      public int indexWhere$default$2() {
         return Iterator.indexWhere$default$2$(this);
      }

      public int indexOf(final Object elem) {
         return Iterator.indexOf$(this, elem);
      }

      public int indexOf(final Object elem, final int from) {
         return Iterator.indexOf$(this, elem, from);
      }

      public final int length() {
         return Iterator.length$(this);
      }

      public boolean isEmpty() {
         return Iterator.isEmpty$(this);
      }

      public Iterator filter(final Function1 p) {
         return Iterator.filter$(this, p);
      }

      public Iterator filterNot(final Function1 p) {
         return Iterator.filterNot$(this, p);
      }

      public Iterator filterImpl(final Function1 p, final boolean isFlipped) {
         return Iterator.filterImpl$(this, p, isFlipped);
      }

      public Iterator withFilter(final Function1 p) {
         return Iterator.withFilter$(this, p);
      }

      public Iterator collect(final PartialFunction pf) {
         return Iterator.collect$(this, pf);
      }

      public Iterator distinct() {
         return Iterator.distinct$(this);
      }

      public Iterator distinctBy(final Function1 f) {
         return Iterator.distinctBy$(this, f);
      }

      public Iterator map(final Function1 f) {
         return Iterator.map$(this, f);
      }

      public Iterator flatMap(final Function1 f) {
         return Iterator.flatMap$(this, f);
      }

      public Iterator flatten(final Function1 ev) {
         return Iterator.flatten$(this, ev);
      }

      public Iterator concat(final Function0 xs) {
         return Iterator.concat$(this, xs);
      }

      public final Iterator $plus$plus(final Function0 xs) {
         return Iterator.$plus$plus$(this, xs);
      }

      public Iterator take(final int n) {
         return Iterator.take$(this, n);
      }

      public Iterator takeWhile(final Function1 p) {
         return Iterator.takeWhile$(this, p);
      }

      public Iterator drop(final int n) {
         return Iterator.drop$(this, n);
      }

      public Iterator dropWhile(final Function1 p) {
         return Iterator.dropWhile$(this, p);
      }

      public Tuple2 span(final Function1 p) {
         return Iterator.span$(this, p);
      }

      public Iterator slice(final int from, final int until) {
         return Iterator.slice$(this, from, until);
      }

      public Iterator sliceIterator(final int from, final int until) {
         return Iterator.sliceIterator$(this, from, until);
      }

      public Iterator zip(final IterableOnce that) {
         return Iterator.zip$(this, that);
      }

      public Iterator zipAll(final IterableOnce that, final Object thisElem, final Object thatElem) {
         return Iterator.zipAll$(this, that, thisElem, thatElem);
      }

      public Iterator zipWithIndex() {
         return Iterator.zipWithIndex$(this);
      }

      public boolean sameElements(final IterableOnce that) {
         return Iterator.sameElements$(this, that);
      }

      public Tuple2 duplicate() {
         return Iterator.duplicate$(this);
      }

      public Iterator patch(final int from, final Iterator patchElems, final int replaced) {
         return Iterator.patch$(this, from, patchElems, replaced);
      }

      public Iterator tapEach(final Function1 f) {
         return Iterator.tapEach$(this, f);
      }

      public String toString() {
         return Iterator.toString$(this);
      }

      /** @deprecated */
      public Iterator seq() {
         return Iterator.seq$(this);
      }

      public Tuple2 splitAt(final int n) {
         return IterableOnceOps.splitAt$(this, n);
      }

      public boolean isTraversableAgain() {
         return IterableOnceOps.isTraversableAgain$(this);
      }

      public void foreach(final Function1 f) {
         IterableOnceOps.foreach$(this, f);
      }

      public boolean forall(final Function1 p) {
         return IterableOnceOps.forall$(this, p);
      }

      public boolean exists(final Function1 p) {
         return IterableOnceOps.exists$(this, p);
      }

      public int count(final Function1 p) {
         return IterableOnceOps.count$(this, p);
      }

      public Option find(final Function1 p) {
         return IterableOnceOps.find$(this, p);
      }

      public Object foldLeft(final Object z, final Function2 op) {
         return IterableOnceOps.foldLeft$(this, z, op);
      }

      public Object foldRight(final Object z, final Function2 op) {
         return IterableOnceOps.foldRight$(this, z, op);
      }

      /** @deprecated */
      public final Object $div$colon(final Object z, final Function2 op) {
         return IterableOnceOps.$div$colon$(this, z, op);
      }

      /** @deprecated */
      public final Object $colon$bslash(final Object z, final Function2 op) {
         return IterableOnceOps.$colon$bslash$(this, z, op);
      }

      public Object fold(final Object z, final Function2 op) {
         return IterableOnceOps.fold$(this, z, op);
      }

      public Object reduce(final Function2 op) {
         return IterableOnceOps.reduce$(this, op);
      }

      public Option reduceOption(final Function2 op) {
         return IterableOnceOps.reduceOption$(this, op);
      }

      public Object reduceLeft(final Function2 op) {
         return IterableOnceOps.reduceLeft$(this, op);
      }

      public Object reduceRight(final Function2 op) {
         return IterableOnceOps.reduceRight$(this, op);
      }

      public Option reduceLeftOption(final Function2 op) {
         return IterableOnceOps.reduceLeftOption$(this, op);
      }

      public Option reduceRightOption(final Function2 op) {
         return IterableOnceOps.reduceRightOption$(this, op);
      }

      public boolean nonEmpty() {
         return IterableOnceOps.nonEmpty$(this);
      }

      public int size() {
         return IterableOnceOps.size$(this);
      }

      /** @deprecated */
      public final void copyToBuffer(final Buffer dest) {
         IterableOnceOps.copyToBuffer$(this, dest);
      }

      public int copyToArray(final Object xs) {
         return IterableOnceOps.copyToArray$(this, xs);
      }

      public int copyToArray(final Object xs, final int start) {
         return IterableOnceOps.copyToArray$(this, xs, start);
      }

      public int copyToArray(final Object xs, final int start, final int len) {
         return IterableOnceOps.copyToArray$(this, xs, start, len);
      }

      public Object sum(final Numeric num) {
         return IterableOnceOps.sum$(this, num);
      }

      public Object product(final Numeric num) {
         return IterableOnceOps.product$(this, num);
      }

      public Object min(final Ordering ord) {
         return IterableOnceOps.min$(this, ord);
      }

      public Option minOption(final Ordering ord) {
         return IterableOnceOps.minOption$(this, ord);
      }

      public Object max(final Ordering ord) {
         return IterableOnceOps.max$(this, ord);
      }

      public Option maxOption(final Ordering ord) {
         return IterableOnceOps.maxOption$(this, ord);
      }

      public Object maxBy(final Function1 f, final Ordering cmp) {
         return IterableOnceOps.maxBy$(this, f, cmp);
      }

      public Option maxByOption(final Function1 f, final Ordering cmp) {
         return IterableOnceOps.maxByOption$(this, f, cmp);
      }

      public Object minBy(final Function1 f, final Ordering cmp) {
         return IterableOnceOps.minBy$(this, f, cmp);
      }

      public Option minByOption(final Function1 f, final Ordering cmp) {
         return IterableOnceOps.minByOption$(this, f, cmp);
      }

      public Option collectFirst(final PartialFunction pf) {
         return IterableOnceOps.collectFirst$(this, pf);
      }

      /** @deprecated */
      public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
         return IterableOnceOps.aggregate$(this, z, seqop, combop);
      }

      public boolean corresponds(final IterableOnce that, final Function2 p) {
         return IterableOnceOps.corresponds$(this, that, p);
      }

      public final String mkString(final String start, final String sep, final String end) {
         return IterableOnceOps.mkString$(this, start, sep, end);
      }

      public final String mkString(final String sep) {
         return IterableOnceOps.mkString$(this, sep);
      }

      public final String mkString() {
         return IterableOnceOps.mkString$(this);
      }

      public StringBuilder addString(final StringBuilder b, final String start, final String sep, final String end) {
         return IterableOnceOps.addString$(this, b, start, sep, end);
      }

      public final StringBuilder addString(final StringBuilder b, final String sep) {
         return IterableOnceOps.addString$(this, b, sep);
      }

      public final StringBuilder addString(final StringBuilder b) {
         return IterableOnceOps.addString$(this, b);
      }

      public Object to(final Factory factory) {
         return IterableOnceOps.to$(this, factory);
      }

      /** @deprecated */
      public final Iterator toIterator() {
         return IterableOnceOps.toIterator$(this);
      }

      public List toList() {
         return IterableOnceOps.toList$(this);
      }

      public Vector toVector() {
         return IterableOnceOps.toVector$(this);
      }

      public Map toMap(final scala..less.colon.less ev) {
         return IterableOnceOps.toMap$(this, ev);
      }

      public Set toSet() {
         return IterableOnceOps.toSet$(this);
      }

      public Seq toSeq() {
         return IterableOnceOps.toSeq$(this);
      }

      public IndexedSeq toIndexedSeq() {
         return IterableOnceOps.toIndexedSeq$(this);
      }

      /** @deprecated */
      public final Stream toStream() {
         return IterableOnceOps.toStream$(this);
      }

      public final Buffer toBuffer() {
         return IterableOnceOps.toBuffer$(this);
      }

      public Object toArray(final ClassTag evidence$2) {
         return IterableOnceOps.toArray$(this, evidence$2);
      }

      public Iterable reversed() {
         return IterableOnceOps.reversed$(this);
      }

      public Stepper stepper(final StepperShape shape) {
         return IterableOnce.stepper$(this, shape);
      }

      public int knownSize() {
         return IterableOnce.knownSize$(this);
      }

      private void findNext() {
         while(this.e < .MODULE$.array_length(this.spire$math$poly$PolyDense$TermIterator$$$outer().coeffs()) && BoxesRunTime.equals(.MODULE$.array_apply(this.spire$math$poly$PolyDense$TermIterator$$$outer().coeffs(), this.e), BoxesRunTime.boxToInteger(0))) {
            ++this.e;
         }

      }

      public boolean hasNext() {
         return this.e < .MODULE$.array_length(this.spire$math$poly$PolyDense$TermIterator$$$outer().coeffs());
      }

      public Term next() {
         Term term = new Term(.MODULE$.array_apply(this.spire$math$poly$PolyDense$TermIterator$$$outer().coeffs(), this.e), this.e);
         ++this.e;
         this.findNext();
         return term;
      }

      // $FF: synthetic method
      public PolyDense spire$math$poly$PolyDense$TermIterator$$$outer() {
         return this.$outer;
      }

      public TermIterator() {
         if (PolyDense.this == null) {
            throw null;
         } else {
            this.$outer = PolyDense.this;
            super();
            IterableOnce.$init$(this);
            IterableOnceOps.$init$(this);
            Iterator.$init$(this);
            this.e = 0;
            this.findNext();
         }
      }
   }
}
