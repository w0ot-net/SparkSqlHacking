package breeze.stats.mcmc;

import breeze.math.VectorSpace;
import breeze.stats.distributions.Rand;
import breeze.stats.distributions.RandBasis;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0015f\u0001\u0002\u0017.\u0001RB\u0001B\u0017\u0001\u0003\u0016\u0004%\ta\u0017\u0005\tE\u0002\u0011\t\u0012)A\u00059\"A1\r\u0001BK\u0002\u0013\u0005A\r\u0003\u0005l\u0001\tE\t\u0015!\u0003f\u0011!a\u0007A!f\u0001\n\u0003i\u0007\u0002\u00038\u0001\u0005#\u0005\u000b\u0011\u0002\u001e\t\u0011=\u0004!Q3A\u0005\u0002AD\u0001\u0002\u001e\u0001\u0003\u0012\u0003\u0006I!\u001d\u0005\tk\u0002\u0011)\u001a!C\u0001a\"Aa\u000f\u0001B\tB\u0003%\u0011\u000fC\u0005x\u0001\t\u0005\t\u0015a\u0003yw\"AA\u0010\u0001B\u0001B\u0003-Q\u0010C\u0004\u0002\u0010\u0001!\t!!\u0005\t\u000f\u00055\u0002\u0001\"\u0001\u00020!9\u0011Q\u0007\u0001\u0005\u0002\u0005]\u0002\"CA\u001e\u0001\u0005\u0005I\u0011AA\u001f\u0011%\t\u0019\u0007AI\u0001\n\u0003\t)\u0007C\u0005\u0002\u0000\u0001\t\n\u0011\"\u0001\u0002\u0002\"I\u0011\u0011\u0012\u0001\u0012\u0002\u0013\u0005\u00111\u0012\u0005\n\u0003'\u0003\u0011\u0013!C\u0001\u0003+C\u0011\"!(\u0001#\u0003%\t!a(\t\u0013\u0005\r\u0006!!A\u0005B\u0005\u0015\u0006\u0002CA\\\u0001\u0005\u0005I\u0011\u00019\t\u0013\u0005e\u0006!!A\u0005\u0002\u0005m\u0006\"CAa\u0001\u0005\u0005I\u0011IAb\u0011%\t\t\u000eAA\u0001\n\u0003\t\u0019\u000eC\u0005\u0002^\u0002\t\t\u0011\"\u0011\u0002`\"I\u00111\u001d\u0001\u0002\u0002\u0013\u0005\u0013Q\u001d\u0005\n\u0003O\u0004\u0011\u0011!C!\u0003SD\u0011\"a;\u0001\u0003\u0003%\t%!<\b\u0013\u0005EX&!A\t\u0002\u0005Mh\u0001\u0003\u0017.\u0003\u0003E\t!!>\t\u000f\u0005=\u0001\u0005\"\u0001\u0003\b!I\u0011q\u001d\u0011\u0002\u0002\u0013\u0015\u0013\u0011\u001e\u0005\n\u0005\u0013\u0001\u0013\u0011!CA\u0005\u0017A\u0011B!\r!#\u0003%\tAa\r\t\u0013\t]\u0002%%A\u0005\u0002\te\u0002\"\u0003B\u001fAE\u0005I\u0011\u0001B \u0011%\u00119\u0006IA\u0001\n\u0003\u0013I\u0006C\u0005\u0003x\u0001\n\n\u0011\"\u0001\u0003z!I!Q\u0010\u0011\u0012\u0002\u0013\u0005!q\u0010\u0005\n\u0005\u0007\u0003\u0013\u0013!C\u0001\u0005\u000bC\u0011Ba'!\u0003\u0003%IA!(\u00039\u00053g-\u001b8f'R,\u0007/T3ue>\u0004x\u000e\\5t\u0011\u0006\u001cH/\u001b8hg*\u0011afL\u0001\u0005[\u000el7M\u0003\u00021c\u0005)1\u000f^1ug*\t!'\u0001\u0004ce\u0016,'0Z\u0002\u0001+\t)DhE\u0003\u0001m![e\nE\u00028qij\u0011!L\u0005\u0003s5\u0012aCQ1tK6+GO]8q_2L7\u000fS1ti&twm\u001d\t\u0003wqb\u0001\u0001B\u0003>\u0001\t\u0007aHA\u0001U#\tyT\t\u0005\u0002A\u00076\t\u0011IC\u0001C\u0003\u0015\u00198-\u00197b\u0013\t!\u0015IA\u0004O_RD\u0017N\\4\u0011\u0005\u00013\u0015BA$B\u0005\r\te.\u001f\t\u0004o%S\u0014B\u0001&.\u0005m\u0019\u00160\\7fiJL7-T3ue>\u0004x\u000e\\5t\u0011\u0006\u001cH/\u001b8hgB\u0011\u0001\tT\u0005\u0003\u001b\u0006\u0013q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002P/:\u0011\u0001+\u0016\b\u0003#Rk\u0011A\u0015\u0006\u0003'N\na\u0001\u0010:p_Rt\u0014\"\u0001\"\n\u0005Y\u000b\u0015a\u00029bG.\fw-Z\u0005\u00031f\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!AV!\u0002\u001b1|w\rT5lK2L\u0007n\\8e+\u0005a\u0006\u0003\u0002!^u}K!AX!\u0003\u0013\u0019+hn\u0019;j_:\f\u0004C\u0001!a\u0013\t\t\u0017I\u0001\u0004E_V\u0014G.Z\u0001\u000fY><G*[6fY&Dwn\u001c3!\u00031\u0001(o\u001c9pg\u0006d7\u000b^3q+\u0005)\u0007c\u00014ju5\tqM\u0003\u0002i_\u0005iA-[:ue&\u0014W\u000f^5p]NL!A[4\u0003\tI\u000bg\u000eZ\u0001\u000eaJ|\u0007o\\:bYN#X\r\u001d\u0011\u0002\t%t\u0017\u000e^\u000b\u0002u\u0005)\u0011N\\5uA\u00051!-\u001e:o\u0013:,\u0012!\u001d\t\u0003\u0001JL!a]!\u0003\u0007%sG/A\u0004ckJt\u0017J\u001c\u0011\u0002\u0013\u0011\u0014x\u000e]\"pk:$\u0018A\u00033s_B\u001cu.\u001e8uA\u0005!!/\u00198e!\t1\u00170\u0003\u0002{O\nI!+\u00198e\u0005\u0006\u001c\u0018n]\u0005\u0003ob\n1B^3di>\u00148\u000b]1dKB\u001aa0a\u0003\u0011\r}\f)AOA\u0005\u001b\t\t\tAC\u0002\u0002\u0004E\nA!\\1uQ&!\u0011qAA\u0001\u0005-1Vm\u0019;peN\u0003\u0018mY3\u0011\u0007m\nY\u0001\u0002\u0006\u0002\u000e1\t\t\u0011!A\u0003\u0002y\u00121a\u0018\u00133\u0003\u0019a\u0014N\\5u}Qa\u00111CA\u0012\u0003K\t9#!\u000b\u0002,Q1\u0011QCA\f\u00033\u00012a\u000e\u0001;\u0011\u001d9X\u0002%AA\u0004aDa\u0001`\u0007A\u0004\u0005m\u0001\u0007BA\u000f\u0003C\u0001ba`A\u0003u\u0005}\u0001cA\u001e\u0002\"\u0011Y\u0011QBA\r\u0003\u0003\u0005\tQ!\u0001?\u0011\u0015QV\u00021\u0001]\u0011\u0015\u0019W\u00021\u0001f\u0011\u0015aW\u00021\u0001;\u0011\u001dyW\u0002%AA\u0002EDq!^\u0007\u0011\u0002\u0003\u0007\u0011/\u0001\u0007qe>\u0004xn]1m\tJ\fw\u000fF\u0002;\u0003cAa!a\r\u000f\u0001\u0004Q\u0014!\u0001=\u0002\u000f=\u00147/\u001a:wKR!\u0011QCA\u001d\u0011\u0019\t\u0019d\u0004a\u0001u\u0005!1m\u001c9z+\u0011\ty$a\u0012\u0015\u0019\u0005\u0005\u0013QKA-\u0003;\ny&!\u0019\u0015\r\u0005\r\u0013\u0011JA&!\u00119\u0004!!\u0012\u0011\u0007m\n9\u0005B\u0003>!\t\u0007a\bC\u0003x!\u0001\u000f\u0001\u0010\u0003\u0004}!\u0001\u000f\u0011Q\n\u0019\u0005\u0003\u001f\n\u0019\u0006E\u0004\u0000\u0003\u000b\t)%!\u0015\u0011\u0007m\n\u0019\u0006B\u0006\u0002\u000e\u0005-\u0013\u0011!A\u0001\u0006\u0003q\u0004\u0002\u0003.\u0011!\u0003\u0005\r!a\u0016\u0011\u000b\u0001k\u0016QI0\t\u0011\r\u0004\u0002\u0013!a\u0001\u00037\u0002BAZ5\u0002F!AA\u000e\u0005I\u0001\u0002\u0004\t)\u0005C\u0004p!A\u0005\t\u0019A9\t\u000fU\u0004\u0002\u0013!a\u0001c\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT\u0003BA4\u0003{*\"!!\u001b+\u0007q\u000bYg\u000b\u0002\u0002nA!\u0011qNA=\u001b\t\t\tH\u0003\u0003\u0002t\u0005U\u0014!C;oG\",7m[3e\u0015\r\t9(Q\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA>\u0003c\u0012\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\t\u0015i\u0014C1\u0001?\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*B!a!\u0002\bV\u0011\u0011Q\u0011\u0016\u0004K\u0006-D!B\u001f\u0013\u0005\u0004q\u0014AD2paf$C-\u001a4bk2$HeM\u000b\u0005\u0003\u001b\u000b\t*\u0006\u0002\u0002\u0010*\u001a!(a\u001b\u0005\u000bu\u001a\"\u0019\u0001 \u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%iU!\u0011qSAN+\t\tIJK\u0002r\u0003W\"Q!\u0010\u000bC\u0002y\nabY8qs\u0012\"WMZ1vYR$S'\u0006\u0003\u0002\u0018\u0006\u0005F!B\u001f\u0016\u0005\u0004q\u0014!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0002(B!\u0011\u0011VAZ\u001b\t\tYK\u0003\u0003\u0002.\u0006=\u0016\u0001\u00027b]\u001eT!!!-\u0002\t)\fg/Y\u0005\u0005\u0003k\u000bYK\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\r)\u0015Q\u0018\u0005\t\u0003\u007fC\u0012\u0011!a\u0001c\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!!2\u0011\u000b\u0005\u001d\u0017QZ#\u000e\u0005\u0005%'bAAf\u0003\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005=\u0017\u0011\u001a\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002V\u0006m\u0007c\u0001!\u0002X&\u0019\u0011\u0011\\!\u0003\u000f\t{w\u000e\\3b]\"A\u0011q\u0018\u000e\u0002\u0002\u0003\u0007Q)\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BAT\u0003CD\u0001\"a0\u001c\u0003\u0003\u0005\r!]\u0001\tQ\u0006\u001c\bnQ8eKR\t\u0011/\u0001\u0005u_N#(/\u001b8h)\t\t9+\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003+\fy\u000f\u0003\u0005\u0002@z\t\t\u00111\u0001F\u0003q\teMZ5oKN#X\r]'fiJ|\u0007o\u001c7jg\"\u000b7\u000f^5oON\u0004\"a\u000e\u0011\u0014\u000b\u0001\n90!@\u0011\u0007\u0001\u000bI0C\u0002\u0002|\u0006\u0013a!\u00118z%\u00164\u0007\u0003BA\u0000\u0005\u000bi!A!\u0001\u000b\t\t\r\u0011qV\u0001\u0003S>L1\u0001\u0017B\u0001)\t\t\u00190A\u0003baBd\u00170\u0006\u0003\u0003\u000e\tUA\u0003\u0004B\b\u0005G\u00119Ca\u000b\u0003.\t=BC\u0002B\t\u0005/\u0011I\u0002\u0005\u00038\u0001\tM\u0001cA\u001e\u0003\u0016\u0011)Qh\tb\u0001}!9qo\tI\u0001\u0002\bA\bB\u0002?$\u0001\b\u0011Y\u0002\r\u0003\u0003\u001e\t\u0005\u0002cB@\u0002\u0006\tM!q\u0004\t\u0004w\t\u0005BaCA\u0007\u00053\t\t\u0011!A\u0003\u0002yBaAW\u0012A\u0002\t\u0015\u0002#\u0002!^\u0005'y\u0006BB2$\u0001\u0004\u0011I\u0003\u0005\u0003gS\nM\u0001B\u00027$\u0001\u0004\u0011\u0019\u0002C\u0004pGA\u0005\t\u0019A9\t\u000fU\u001c\u0003\u0013!a\u0001c\u0006y\u0011\r\u001d9ms\u0012\"WMZ1vYR$C'\u0006\u0003\u0002\u0018\nUB!B\u001f%\u0005\u0004q\u0014aD1qa2LH\u0005Z3gCVdG\u000fJ\u001b\u0016\t\u0005]%1\b\u0003\u0006{\u0015\u0012\rAP\u0001\u0010CB\u0004H.\u001f\u0013eK\u001a\fW\u000f\u001c;%mU!!\u0011\tB&)1\u0011\u0019E!\u0012\u0003N\tE#1\u000bB+U\rA\u00181\u000e\u0005\u00075\u001a\u0002\rAa\u0012\u0011\u000b\u0001k&\u0011J0\u0011\u0007m\u0012Y\u0005B\u0003>M\t\u0007a\b\u0003\u0004dM\u0001\u0007!q\n\t\u0005M&\u0014I\u0005\u0003\u0004mM\u0001\u0007!\u0011\n\u0005\u0006_\u001a\u0002\r!\u001d\u0005\u0006k\u001a\u0002\r!]\u0001\bk:\f\u0007\u000f\u001d7z+\u0011\u0011YF!\u001c\u0015\t\tu#\u0011\u000f\t\u0006\u0001\n}#1M\u0005\u0004\u0005C\n%AB(qi&|g\u000eE\u0006A\u0005K\u0012IGa\u001c\u0003lE\f\u0018b\u0001B4\u0003\n1A+\u001e9mKV\u0002R\u0001Q/\u0003l}\u00032a\u000fB7\t\u0015itE1\u0001?!\u00111\u0017Na\u001b\t\u0013\tMt%!AA\u0002\tU\u0014a\u0001=%aA!q\u0007\u0001B6\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%iU!\u0011q\u0013B>\t\u0015i\u0004F1\u0001?\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%kU!\u0011q\u0013BA\t\u0015i\u0014F1\u0001?\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%mU!!q\u0011BH)1\u0011\u0019E!#\u0003\u0012\nU%q\u0013BM\u0011\u0019Q&\u00061\u0001\u0003\fB)\u0001)\u0018BG?B\u00191Ha$\u0005\u000buR#\u0019\u0001 \t\r\rT\u0003\u0019\u0001BJ!\u00111\u0017N!$\t\r1T\u0003\u0019\u0001BG\u0011\u0015y'\u00061\u0001r\u0011\u0015)(\u00061\u0001r\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\u0011y\n\u0005\u0003\u0002*\n\u0005\u0016\u0002\u0002BR\u0003W\u0013aa\u00142kK\u000e$\b"
)
public class AffineStepMetropolisHastings extends BaseMetropolisHastings implements SymmetricMetropolisHastings, Product {
   private final Function1 logLikelihood;
   private final Rand proposalStep;
   private final Object init;
   private final int burnIn;
   private final int dropCount;
   private final VectorSpace vectorSpace;

   public static RandBasis $lessinit$greater$default$6(final Function1 logLikelihood, final Rand proposalStep, final Object init, final int burnIn, final int dropCount) {
      return AffineStepMetropolisHastings$.MODULE$.$lessinit$greater$default$6(logLikelihood, proposalStep, init, burnIn, dropCount);
   }

   public static int $lessinit$greater$default$5() {
      return AffineStepMetropolisHastings$.MODULE$.$lessinit$greater$default$5();
   }

   public static int $lessinit$greater$default$4() {
      return AffineStepMetropolisHastings$.MODULE$.$lessinit$greater$default$4();
   }

   public static Option unapply(final AffineStepMetropolisHastings x$0) {
      return AffineStepMetropolisHastings$.MODULE$.unapply(x$0);
   }

   public static RandBasis apply$default$6(final Function1 logLikelihood, final Rand proposalStep, final Object init, final int burnIn, final int dropCount) {
      return AffineStepMetropolisHastings$.MODULE$.apply$default$6(logLikelihood, proposalStep, init, burnIn, dropCount);
   }

   public static int apply$default$5() {
      return AffineStepMetropolisHastings$.MODULE$.apply$default$5();
   }

   public static int apply$default$4() {
      return AffineStepMetropolisHastings$.MODULE$.apply$default$4();
   }

   public static AffineStepMetropolisHastings apply(final Function1 logLikelihood, final Rand proposalStep, final Object init, final int burnIn, final int dropCount, final RandBasis rand, final VectorSpace vectorSpace) {
      return AffineStepMetropolisHastings$.MODULE$.apply(logLikelihood, proposalStep, init, burnIn, dropCount, rand, vectorSpace);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public double logTransitionProbability(final Object start, final Object end) {
      return SymmetricMetropolisHastings.logTransitionProbability$(this, start, end);
   }

   public double logLikelihoodRatio(final Object start, final Object end) {
      return SymmetricMetropolisHastings.logLikelihoodRatio$(this, start, end);
   }

   public Function1 logLikelihood() {
      return this.logLikelihood;
   }

   public Rand proposalStep() {
      return this.proposalStep;
   }

   public Object init() {
      return this.init;
   }

   public int burnIn() {
      return this.burnIn;
   }

   public int dropCount() {
      return this.dropCount;
   }

   public Object proposalDraw(final Object x) {
      return this.vectorSpace.addVV().apply(this.proposalStep().draw(), x);
   }

   public AffineStepMetropolisHastings observe(final Object x) {
      int x$1 = 0;
      Function1 x$3 = this.copy$default$1();
      Rand x$4 = this.copy$default$2();
      int x$5 = this.copy$default$5();
      return this.copy(x$3, x$4, x, 0, x$5, super.rand(), this.vectorSpace);
   }

   public AffineStepMetropolisHastings copy(final Function1 logLikelihood, final Rand proposalStep, final Object init, final int burnIn, final int dropCount, final RandBasis rand, final VectorSpace vectorSpace) {
      return new AffineStepMetropolisHastings(logLikelihood, proposalStep, init, burnIn, dropCount, rand, vectorSpace);
   }

   public Function1 copy$default$1() {
      return this.logLikelihood();
   }

   public Rand copy$default$2() {
      return this.proposalStep();
   }

   public Object copy$default$3() {
      return this.init();
   }

   public int copy$default$4() {
      return this.burnIn();
   }

   public int copy$default$5() {
      return this.dropCount();
   }

   public String productPrefix() {
      return "AffineStepMetropolisHastings";
   }

   public int productArity() {
      return 5;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.logLikelihood();
            break;
         case 1:
            var10000 = this.proposalStep();
            break;
         case 2:
            var10000 = this.init();
            break;
         case 3:
            var10000 = BoxesRunTime.boxToInteger(this.burnIn());
            break;
         case 4:
            var10000 = BoxesRunTime.boxToInteger(this.dropCount());
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
      return x$1 instanceof AffineStepMetropolisHastings;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "logLikelihood";
            break;
         case 1:
            var10000 = "proposalStep";
            break;
         case 2:
            var10000 = "init";
            break;
         case 3:
            var10000 = "burnIn";
            break;
         case 4:
            var10000 = "dropCount";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.logLikelihood()));
      var1 = Statics.mix(var1, Statics.anyHash(this.proposalStep()));
      var1 = Statics.mix(var1, Statics.anyHash(this.init()));
      var1 = Statics.mix(var1, this.burnIn());
      var1 = Statics.mix(var1, this.dropCount());
      return Statics.finalizeHash(var1, 5);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var9;
      if (this != x$1) {
         label69: {
            boolean var2;
            if (x$1 instanceof AffineStepMetropolisHastings) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label51: {
                  AffineStepMetropolisHastings var4 = (AffineStepMetropolisHastings)x$1;
                  if (this.burnIn() == var4.burnIn() && this.dropCount() == var4.dropCount()) {
                     label60: {
                        Function1 var10000 = this.logLikelihood();
                        Function1 var5 = var4.logLikelihood();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label60;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label60;
                        }

                        Rand var7 = this.proposalStep();
                        Rand var6 = var4.proposalStep();
                        if (var7 == null) {
                           if (var6 != null) {
                              break label60;
                           }
                        } else if (!var7.equals(var6)) {
                           break label60;
                        }

                        if (BoxesRunTime.equals(this.init(), var4.init()) && var4.canEqual(this)) {
                           var9 = true;
                           break label51;
                        }
                     }
                  }

                  var9 = false;
               }

               if (var9) {
                  break label69;
               }
            }

            var9 = false;
            return var9;
         }
      }

      var9 = true;
      return var9;
   }

   public AffineStepMetropolisHastings(final Function1 logLikelihood, final Rand proposalStep, final Object init, final int burnIn, final int dropCount, final RandBasis rand, final VectorSpace vectorSpace) {
      super(logLikelihood, init, burnIn, dropCount, rand);
      this.logLikelihood = logLikelihood;
      this.proposalStep = proposalStep;
      this.init = init;
      this.burnIn = burnIn;
      this.dropCount = dropCount;
      this.vectorSpace = vectorSpace;
      SymmetricMetropolisHastings.$init$(this);
      Product.$init$(this);
   }
}
