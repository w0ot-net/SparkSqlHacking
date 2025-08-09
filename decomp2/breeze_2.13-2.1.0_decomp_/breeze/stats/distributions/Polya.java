package breeze.stats.distributions;

import breeze.linalg.DenseVector;
import breeze.linalg.QuasiTensor;
import breeze.linalg.sum$;
import breeze.math.MutableEnumeratedCoordinateField;
import breeze.numerics.package;
import breeze.numerics.package$lbeta$impl2Double$;
import scala.Function1;
import scala.Option;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.math.package.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001db\u0001\u0002\t\u0012\u0001aA\u0001\"\u0010\u0001\u0003\u0002\u0003\u0006IA\u0010\u0005\t\u0003\u0002\u0011\t\u0011)A\u0006\u0005\"A1\n\u0001B\u0001B\u0003-A\nC\u0003P\u0001\u0011\u0005\u0001\u000bC\u0004W\u0001\t\u0007I\u0011B,\t\rm\u0003\u0001\u0015!\u0003Y\u0011\u0015a\u0006\u0001\"\u0001^\u0011!q\u0006\u0001#b\u0001\n\u0003y\u0006\"\u00021\u0001\t\u0003\tw!\u00023\u0012\u0011\u0003)g!\u0002\t\u0012\u0011\u00031\u0007\"B(\f\t\u0003y\u0007\"\u00029\f\t\u0003\t\bbBA\u0003\u0017\u0011\u0005\u0011q\u0001\u0005\n\u0003/Y\u0011\u0011!C\u0005\u00033\u0011Q\u0001U8ms\u0006T!AE\n\u0002\u001b\u0011L7\u000f\u001e:jEV$\u0018n\u001c8t\u0015\t!R#A\u0003ti\u0006$8OC\u0001\u0017\u0003\u0019\u0011'/Z3{K\u000e\u0001QcA\r@MM\u0019\u0001A\u0007\u0011\u0011\u0005mqR\"\u0001\u000f\u000b\u0003u\tQa]2bY\u0006L!a\b\u000f\u0003\r\u0005s\u0017PU3g!\r\t#\u0005J\u0007\u0002#%\u00111%\u0005\u0002\u000e\t&\u001c8M]3uK\u0012K7\u000f\u001e:\u0011\u0005\u00152C\u0002\u0001\u0003\nO\u0001\u0001\u000b\u0011!AC\u0002!\u0012\u0011!S\t\u0003S1\u0002\"a\u0007\u0016\n\u0005-b\"a\u0002(pi\"Lgn\u001a\t\u000375J!A\f\u000f\u0003\u0007\u0005s\u0017\u0010K\u0002'aM\u0002\"aG\u0019\n\u0005Ib\"aC:qK\u000eL\u0017\r\\5{K\u0012\fTa\t\u001b6oYr!aG\u001b\n\u0005Yb\u0012aA%oiF\"A\u0005\u000f\u001f\u001e\u001d\tID(D\u0001;\u0015\tYt#\u0001\u0004=e>|GOP\u0005\u0002;\u00051\u0001/\u0019:b[N\u0004\"!J \u0005\u000b\u0001\u0003!\u0019\u0001\u0015\u0003\u0003Q\u000bQa\u001d9bG\u0016\u0004Ra\u0011$?I!k\u0011\u0001\u0012\u0006\u0003\u000bV\tA!\\1uQ&\u0011q\t\u0012\u0002!\u001bV$\u0018M\u00197f\u000b:,X.\u001a:bi\u0016$7i\\8sI&t\u0017\r^3GS\u0016dG\r\u0005\u0002\u001c\u0013&\u0011!\n\b\u0002\u0007\t>,(\r\\3\u0002\tI\fg\u000e\u001a\t\u0003C5K!AT\t\u0003\u0013I\u000bg\u000e\u001a\"bg&\u001c\u0018A\u0002\u001fj]&$h\b\u0006\u0002R+R\u0019!k\u0015+\u0011\t\u0005\u0002a\b\n\u0005\u0006\u0003\u0012\u0001\u001dA\u0011\u0005\u0006\u0017\u0012\u0001\u001d\u0001\u0014\u0005\u0006{\u0011\u0001\rAP\u0001\u000fS:tWM\u001d#je&\u001c\u0007\u000e\\3u+\u0005A\u0006\u0003B\u0011Z}\u0011J!AW\t\u0003\u0013\u0011K'/[2iY\u0016$\u0018aD5o]\u0016\u0014H)\u001b:jG\"dW\r\u001e\u0011\u0002\t\u0011\u0014\u0018m\u001e\u000b\u0002I\u0005iAn\\4O_Jl\u0017\r\\5{KJ,\u0012\u0001S\u0001\u000eaJ|'-\u00192jY&$\u0018p\u00144\u0015\u0005!\u0013\u0007\"B2\n\u0001\u0004!\u0013!\u0001=\u0002\u000bA{G._1\u0011\u0005\u0005Z1cA\u0006\u001bOB\u0011\u0001.\\\u0007\u0002S*\u0011!n[\u0001\u0003S>T\u0011\u0001\\\u0001\u0005U\u00064\u0018-\u0003\u0002oS\na1+\u001a:jC2L'0\u00192mKR\tQ-A\u0002ts6$BA\u001d@\u0002\u0002Q\u00111/ \t\u0005C\u0001!(\u0010E\u0002vq\"k\u0011A\u001e\u0006\u0003oV\ta\u0001\\5oC2<\u0017BA=w\u0005-!UM\\:f-\u0016\u001cGo\u001c:\u0011\u0005mY\u0018B\u0001?\u001d\u0005\rIe\u000e\u001e\u0005\u0006\u00176\u0001\u001d\u0001\u0014\u0005\u0006\u007f6\u0001\r\u0001S\u0001\u0006C2\u0004\b.\u0019\u0005\u0007\u0003\u0007i\u0001\u0019\u0001>\u0002\u0003-\fQ!\u00199qYf$B!!\u0003\u0002\u000eQ\u00191/a\u0003\t\u000b-s\u00019\u0001'\t\u000f\u0005=a\u00021\u0001\u0002\u0012\u0005\u0019\u0011M\u001d:\u0011\tm\t\u0019\u0002S\u0005\u0004\u0003+a\"!B!se\u0006L\u0018\u0001D<sSR,'+\u001a9mC\u000e,GCAA\u000e!\u0011\ti\"a\t\u000e\u0005\u0005}!bAA\u0011W\u0006!A.\u00198h\u0013\u0011\t)#a\b\u0003\r=\u0013'.Z2u\u0001"
)
public class Polya implements DiscreteDistr {
   private double logNormalizer;
   public final Object breeze$stats$distributions$Polya$$params;
   public final MutableEnumeratedCoordinateField breeze$stats$distributions$Polya$$space;
   public final RandBasis breeze$stats$distributions$Polya$$rand;
   public final Dirichlet innerDirichlet;
   private volatile boolean bitmap$0;

   public static Polya sym(final double alpha, final int k, final RandBasis rand) {
      return Polya$.MODULE$.sym(alpha, k, rand);
   }

   public double logProbabilityOf(final Object x) {
      return DiscreteDistr.logProbabilityOf$(this, x);
   }

   public double unnormalizedProbabilityOf(final Object x) {
      return DiscreteDistr.unnormalizedProbabilityOf$(this, x);
   }

   public double unnormalizedLogProbabilityOf(final Object x) {
      return DiscreteDistr.unnormalizedLogProbabilityOf$(this, x);
   }

   public double apply(final Object x) {
      return DiscreteDistr.apply$(this, x);
   }

   public double logApply(final Object x) {
      return DiscreteDistr.logApply$(this, x);
   }

   public double draw$mcD$sp() {
      return Rand.draw$mcD$sp$(this);
   }

   public Object get() {
      return Rand.get$(this);
   }

   public double get$mcD$sp() {
      return Rand.get$mcD$sp$(this);
   }

   public int get$mcI$sp() {
      return Rand.get$mcI$sp$(this);
   }

   public Option drawOpt() {
      return Rand.drawOpt$(this);
   }

   public Object sample() {
      return Rand.sample$(this);
   }

   public double sample$mcD$sp() {
      return Rand.sample$mcD$sp$(this);
   }

   public int sample$mcI$sp() {
      return Rand.sample$mcI$sp$(this);
   }

   public IndexedSeq sample(final int n) {
      return Rand.sample$(this, n);
   }

   public Iterator samples() {
      return Rand.samples$(this);
   }

   public DenseVector samplesVector(final int size, final ClassTag m) {
      return Rand.samplesVector$(this, size, m);
   }

   public DenseVector samplesVector$mcD$sp(final int size, final ClassTag m) {
      return Rand.samplesVector$mcD$sp$(this, size, m);
   }

   public DenseVector samplesVector$mcI$sp(final int size, final ClassTag m) {
      return Rand.samplesVector$mcI$sp$(this, size, m);
   }

   public Rand flatMap(final Function1 f) {
      return Rand.flatMap$(this, f);
   }

   public Rand flatMap$mcD$sp(final Function1 f) {
      return Rand.flatMap$mcD$sp$(this, f);
   }

   public Rand flatMap$mcI$sp(final Function1 f) {
      return Rand.flatMap$mcI$sp$(this, f);
   }

   public Rand map(final Function1 f) {
      return Rand.map$(this, f);
   }

   public Rand map$mcD$sp(final Function1 f) {
      return Rand.map$mcD$sp$(this, f);
   }

   public Rand map$mcI$sp(final Function1 f) {
      return Rand.map$mcI$sp$(this, f);
   }

   public void foreach(final Function1 f) {
      Rand.foreach$(this, f);
   }

   public void foreach$mcD$sp(final Function1 f) {
      Rand.foreach$mcD$sp$(this, f);
   }

   public void foreach$mcI$sp(final Function1 f) {
      Rand.foreach$mcI$sp$(this, f);
   }

   public Rand filter(final Function1 p) {
      return Rand.filter$(this, p);
   }

   public Rand filter$mcD$sp(final Function1 p) {
      return Rand.filter$mcD$sp$(this, p);
   }

   public Rand filter$mcI$sp(final Function1 p) {
      return Rand.filter$mcI$sp$(this, p);
   }

   public Rand withFilter(final Function1 p) {
      return Rand.withFilter$(this, p);
   }

   public Rand withFilter$mcD$sp(final Function1 p) {
      return Rand.withFilter$mcD$sp$(this, p);
   }

   public Rand withFilter$mcI$sp(final Function1 p) {
      return Rand.withFilter$mcI$sp$(this, p);
   }

   public Rand condition(final Function1 p) {
      return Rand.condition$(this, p);
   }

   public Rand condition$mcD$sp(final Function1 p) {
      return Rand.condition$mcD$sp$(this, p);
   }

   public Rand condition$mcI$sp(final Function1 p) {
      return Rand.condition$mcI$sp$(this, p);
   }

   public Dirichlet innerDirichlet() {
      return this.innerDirichlet;
   }

   public Object draw() {
      return Multinomial$.MODULE$.apply(this.innerDirichlet().draw(), this.breeze$stats$distributions$Polya$$space.hasOps(), sum$.MODULE$.reduce_Double(this.breeze$stats$distributions$Polya$$space.iterateValues()), this.breeze$stats$distributions$Polya$$rand).draw();
   }

   private double logNormalizer$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.logNormalizer = -BoxesRunTime.unboxToDouble(package.lbeta$.MODULE$.apply(this.breeze$stats$distributions$Polya$$params, package.lbeta$.MODULE$.reduceDouble(this.breeze$stats$distributions$Polya$$space.iterateValues())));
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.logNormalizer;
   }

   public double logNormalizer() {
      return !this.bitmap$0 ? this.logNormalizer$lzycompute() : this.logNormalizer;
   }

   public double probabilityOf(final Object x) {
      return .MODULE$.exp(package.lbeta$.MODULE$.apply$mDDDc$sp(BoxesRunTime.unboxToDouble(sum$.MODULE$.apply(this.breeze$stats$distributions$Polya$$params, sum$.MODULE$.reduce_Double(this.breeze$stats$distributions$Polya$$space.iterateValues()))), (double)1.0F, package$lbeta$impl2Double$.MODULE$) - package.lbeta$.MODULE$.apply$mDDDc$sp(BoxesRunTime.unboxToDouble(((QuasiTensor)this.breeze$stats$distributions$Polya$$space.hasOps().apply(this.breeze$stats$distributions$Polya$$params)).apply(x)), (double)1.0F, package$lbeta$impl2Double$.MODULE$));
   }

   public Dirichlet innerDirichlet$mcI$sp() {
      return this.innerDirichlet();
   }

   public int draw$mcI$sp() {
      return BoxesRunTime.unboxToInt(this.draw());
   }

   public double probabilityOf$mcI$sp(final int x) {
      return this.probabilityOf(BoxesRunTime.boxToInteger(x));
   }

   public boolean specInstance$() {
      return false;
   }

   public Polya(final Object params, final MutableEnumeratedCoordinateField space, final RandBasis rand) {
      this.breeze$stats$distributions$Polya$$params = params;
      this.breeze$stats$distributions$Polya$$space = space;
      this.breeze$stats$distributions$Polya$$rand = rand;
      Density.$init$(this);
      Rand.$init$(this);
      DiscreteDistr.$init$(this);
      if (!this.specInstance$()) {
         this.innerDirichlet = new Dirichlet(params, space, rand);
      }

   }
}
