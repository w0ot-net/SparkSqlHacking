package breeze.stats.mcmc;

import breeze.stats.distributions.Rand;
import breeze.stats.distributions.RandBasis;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\tEd\u0001B\u0017/\u0001VB\u0001\u0002\u0017\u0001\u0003\u0016\u0004%\t!\u0017\u0005\tA\u0002\u0011\t\u0012)A\u00055\"A\u0011\r\u0001BK\u0002\u0013\u0005!\r\u0003\u0005k\u0001\tE\t\u0015!\u0003d\u0011!Y\u0007A!f\u0001\n\u0003a\u0007\u0002\u00039\u0001\u0005#\u0005\u000b\u0011B7\t\u0011E\u0004!Q3A\u0005\u0002ID\u0001b\u001d\u0001\u0003\u0012\u0003\u0006Ia\u000f\u0005\ti\u0002\u0011)\u001a!C\u0001k\"A\u0011\u0010\u0001B\tB\u0003%a\u000f\u0003\u0005{\u0001\tU\r\u0011\"\u0001v\u0011!Y\bA!E!\u0002\u00131\bB\u0003?\u0001\u0005\u0003\u0005\u000b1B?\u0002\u0002!9\u00111\u0001\u0001\u0005\u0002\u0005\u0015\u0001bBA\r\u0001\u0011\u0005\u00111\u0004\u0005\b\u0003C\u0001A\u0011AA\u0012\u0011\u001d\ti\u0003\u0001C\u0001\u0003_A\u0011\"a\r\u0001\u0003\u0003%\t!!\u000e\t\u0013\u0005]\u0003!%A\u0005\u0002\u0005e\u0003\"CA:\u0001E\u0005I\u0011AA;\u0011%\ti\bAI\u0001\n\u0003\ty\bC\u0005\u0002\b\u0002\t\n\u0011\"\u0001\u0002\n\"I\u0011\u0011\u0013\u0001\u0012\u0002\u0013\u0005\u00111\u0013\u0005\n\u00037\u0003\u0011\u0013!C\u0001\u0003;C\u0011\"!)\u0001\u0003\u0003%\t%a)\t\u0011\u0005U\u0006!!A\u0005\u0002UD\u0011\"a.\u0001\u0003\u0003%\t!!/\t\u0013\u0005}\u0006!!A\u0005B\u0005\u0005\u0007\"CAh\u0001\u0005\u0005I\u0011AAi\u0011%\tY\u000eAA\u0001\n\u0003\ni\u000eC\u0005\u0002b\u0002\t\t\u0011\"\u0011\u0002d\"I\u0011Q\u001d\u0001\u0002\u0002\u0013\u0005\u0013q\u001d\u0005\n\u0003S\u0004\u0011\u0011!C!\u0003W<\u0011\"a</\u0003\u0003E\t!!=\u0007\u00115r\u0013\u0011!E\u0001\u0003gDq!a\u0001$\t\u0003\u0011)\u0001C\u0005\u0002f\u000e\n\t\u0011\"\u0012\u0002h\"I!qA\u0012\u0002\u0002\u0013\u0005%\u0011\u0002\u0005\n\u0005W\u0019\u0013\u0013!C\u0001\u0005[A\u0011B!\r$#\u0003%\tAa\r\t\u0013\t]2%!A\u0005\u0002\ne\u0002\"\u0003B.GE\u0005I\u0011\u0001B/\u0011%\u0011\tgII\u0001\n\u0003\u0011\u0019\u0007C\u0005\u0003h\r\n\t\u0011\"\u0003\u0003j\tY\u0012I\u001d2jiJ\f'/_'fiJ|\u0007o\u001c7jg\"\u000b7\u000f^5oONT!a\f\u0019\u0002\t5\u001cWn\u0019\u0006\u0003cI\nQa\u001d;biNT\u0011aM\u0001\u0007EJ,WM_3\u0004\u0001U\u0011a'P\n\u0005\u0001]JE\nE\u00029smj\u0011AL\u0005\u0003u9\u0012aCQ1tK6+GO]8q_2L7\u000fS1ti&twm\u001d\t\u0003yub\u0001\u0001B\u0003?\u0001\t\u0007qHA\u0001U#\t\u0001e\t\u0005\u0002B\t6\t!IC\u0001D\u0003\u0015\u00198-\u00197b\u0013\t)%IA\u0004O_RD\u0017N\\4\u0011\u0005\u0005;\u0015B\u0001%C\u0005\r\te.\u001f\t\u0003\u0003*K!a\u0013\"\u0003\u000fA\u0013x\u000eZ;diB\u0011Q*\u0016\b\u0003\u001dNs!a\u0014*\u000e\u0003AS!!\u0015\u001b\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0019\u0015B\u0001+C\u0003\u001d\u0001\u0018mY6bO\u0016L!AV,\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005Q\u0013\u0015!\u00047pO2K7.\u001a7jQ>|G-F\u0001[!\u0011\t5lO/\n\u0005q\u0013%!\u0003$v]\u000e$\u0018n\u001c82!\t\te,\u0003\u0002`\u0005\n1Ai\\;cY\u0016\fa\u0002\\8h\u0019&\\W\r\\5i_>$\u0007%\u0001\u0005qe>\u0004xn]1m+\u0005\u0019\u0007\u0003B!\\w\u0011\u00042!\u001a5<\u001b\u00051'BA41\u00035!\u0017n\u001d;sS\n,H/[8og&\u0011\u0011N\u001a\u0002\u0005%\u0006tG-A\u0005qe>\u0004xn]1mA\u0005\u0011Bn\\4Qe>\u0004xn]1m\t\u0016t7/\u001b;z+\u0005i\u0007#B!owmj\u0016BA8C\u0005%1UO\\2uS>t''A\nm_\u001e\u0004&o\u001c9pg\u0006dG)\u001a8tSRL\b%\u0001\u0003j]&$X#A\u001e\u0002\u000b%t\u0017\u000e\u001e\u0011\u0002\r\t,(O\\%o+\u00051\bCA!x\u0013\tA(IA\u0002J]R\fqAY;s]&s\u0007%A\u0005ee>\u00048i\\;oi\u0006QAM]8q\u0007>,h\u000e\u001e\u0011\u0002\tI\fg\u000e\u001a\t\u0003KzL!a 4\u0003\u0013I\u000bg\u000e\u001a\"bg&\u001c\u0018B\u0001?:\u0003\u0019a\u0014N\\5u}Qq\u0011qAA\u0007\u0003\u001f\t\t\"a\u0005\u0002\u0016\u0005]A\u0003BA\u0005\u0003\u0017\u00012\u0001\u000f\u0001<\u0011\u0015ah\u0002q\u0001~\u0011\u0015Af\u00021\u0001[\u0011\u0015\tg\u00021\u0001d\u0011\u0015Yg\u00021\u0001n\u0011\u0015\th\u00021\u0001<\u0011\u001d!h\u0002%AA\u0002YDqA\u001f\b\u0011\u0002\u0003\u0007a/\u0001\u0007qe>\u0004xn]1m\tJ\fw\u000fF\u0002<\u0003;Aa!a\b\u0010\u0001\u0004Y\u0014!\u0001=\u000211|w\r\u0016:b]NLG/[8o!J|'-\u00192jY&$\u0018\u0010F\u0003^\u0003K\tI\u0003\u0003\u0004\u0002(A\u0001\raO\u0001\u0006gR\f'\u000f\u001e\u0005\u0007\u0003W\u0001\u0002\u0019A\u001e\u0002\u0007\u0015tG-A\u0004pEN,'O^3\u0015\t\u0005%\u0011\u0011\u0007\u0005\u0007\u0003?\t\u0002\u0019A\u001e\u0002\t\r|\u0007/_\u000b\u0005\u0003o\ty\u0004\u0006\b\u0002:\u0005\r\u0013qIA'\u0003#\n\u0019&!\u0016\u0015\t\u0005m\u0012\u0011\t\t\u0005q\u0001\ti\u0004E\u0002=\u0003\u007f!QA\u0010\nC\u0002}BQ\u0001 \nA\u0004uD\u0001\u0002\u0017\n\u0011\u0002\u0003\u0007\u0011Q\t\t\u0006\u0003n\u000bi$\u0018\u0005\tCJ\u0001\n\u00111\u0001\u0002JA1\u0011iWA\u001f\u0003\u0017\u0002B!\u001a5\u0002>!A1N\u0005I\u0001\u0002\u0004\ty\u0005E\u0004B]\u0006u\u0012QH/\t\u0011E\u0014\u0002\u0013!a\u0001\u0003{Aq\u0001\u001e\n\u0011\u0002\u0003\u0007a\u000fC\u0004{%A\u0005\t\u0019\u0001<\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU!\u00111LA9+\t\tiFK\u0002[\u0003?Z#!!\u0019\u0011\t\u0005\r\u0014QN\u0007\u0003\u0003KRA!a\u001a\u0002j\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003W\u0012\u0015AC1o]>$\u0018\r^5p]&!\u0011qNA3\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0003\u0006}M\u0011\raP\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\u0011\t9(a\u001f\u0016\u0005\u0005e$fA2\u0002`\u0011)a\b\u0006b\u0001\u007f\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\u001aT\u0003BAA\u0003\u000b+\"!a!+\u00075\fy\u0006B\u0003?+\t\u0007q(\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001b\u0016\t\u0005-\u0015qR\u000b\u0003\u0003\u001bS3aOA0\t\u0015qdC1\u0001@\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIU*B!!&\u0002\u001aV\u0011\u0011q\u0013\u0016\u0004m\u0006}C!\u0002 \u0018\u0005\u0004y\u0014AD2paf$C-\u001a4bk2$HEN\u000b\u0005\u0003+\u000by\nB\u0003?1\t\u0007q(A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003K\u0003B!a*\u000226\u0011\u0011\u0011\u0016\u0006\u0005\u0003W\u000bi+\u0001\u0003mC:<'BAAX\u0003\u0011Q\u0017M^1\n\t\u0005M\u0016\u0011\u0016\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0019a)a/\t\u0011\u0005u6$!AA\u0002Y\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAAb!\u0015\t)-a3G\u001b\t\t9MC\u0002\u0002J\n\u000b!bY8mY\u0016\u001cG/[8o\u0013\u0011\ti-a2\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003'\fI\u000eE\u0002B\u0003+L1!a6C\u0005\u001d\u0011un\u001c7fC:D\u0001\"!0\u001e\u0003\u0003\u0005\rAR\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0002&\u0006}\u0007\u0002CA_=\u0005\u0005\t\u0019\u0001<\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012A^\u0001\ti>\u001cFO]5oOR\u0011\u0011QU\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005M\u0017Q\u001e\u0005\t\u0003{\u000b\u0013\u0011!a\u0001\r\u0006Y\u0012I\u001d2jiJ\f'/_'fiJ|\u0007o\u001c7jg\"\u000b7\u000f^5oON\u0004\"\u0001O\u0012\u0014\u000b\r\n)0a?\u0011\u0007\u0005\u000b90C\u0002\u0002z\n\u0013a!\u00118z%\u00164\u0007\u0003BA\u007f\u0005\u0007i!!a@\u000b\t\t\u0005\u0011QV\u0001\u0003S>L1AVA\u0000)\t\t\t0A\u0003baBd\u00170\u0006\u0003\u0003\f\tMAC\u0004B\u0007\u0005/\u0011YB!\t\u0003&\t\u001d\"\u0011\u0006\u000b\u0005\u0005\u001f\u0011)\u0002\u0005\u00039\u0001\tE\u0001c\u0001\u001f\u0003\u0014\u0011)aH\nb\u0001\u007f!)AP\na\u0002{\"1\u0001L\na\u0001\u00053\u0001R!Q.\u0003\u0012uCa!\u0019\u0014A\u0002\tu\u0001CB!\\\u0005#\u0011y\u0002\u0005\u0003fQ\nE\u0001BB6'\u0001\u0004\u0011\u0019\u0003E\u0004B]\nE!\u0011C/\t\rE4\u0003\u0019\u0001B\t\u0011\u001d!h\u0005%AA\u0002YDqA\u001f\u0014\u0011\u0002\u0003\u0007a/A\bbaBd\u0017\u0010\n3fM\u0006,H\u000e\u001e\u00136+\u0011\t)Ja\f\u0005\u000by:#\u0019A \u0002\u001f\u0005\u0004\b\u000f\\=%I\u00164\u0017-\u001e7uIY*B!!&\u00036\u0011)a\b\u000bb\u0001\u007f\u00059QO\\1qa2LX\u0003\u0002B\u001e\u0005\u001b\"BA!\u0010\u0003VA)\u0011Ia\u0010\u0003D%\u0019!\u0011\t\"\u0003\r=\u0003H/[8o!5\t%Q\tB%\u0005\u001f\u0012\u0019Fa\u0013wm&\u0019!q\t\"\u0003\rQ+\b\u000f\\37!\u0015\t5La\u0013^!\ra$Q\n\u0003\u0006}%\u0012\ra\u0010\t\u0007\u0003n\u0013YE!\u0015\u0011\t\u0015D'1\n\t\b\u0003:\u0014YEa\u0013^\u0011%\u00119&KA\u0001\u0002\u0004\u0011I&A\u0002yIA\u0002B\u0001\u000f\u0001\u0003L\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIU*B!!&\u0003`\u0011)aH\u000bb\u0001\u007f\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIY*B!!&\u0003f\u0011)ah\u000bb\u0001\u007f\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011!1\u000e\t\u0005\u0003O\u0013i'\u0003\u0003\u0003p\u0005%&AB(cU\u0016\u001cG\u000f"
)
public class ArbitraryMetropolisHastings extends BaseMetropolisHastings implements Product {
   private final Function1 logLikelihood;
   private final Function1 proposal;
   private final Function2 logProposalDensity;
   private final Object init;
   private final int burnIn;
   private final int dropCount;

   public static int $lessinit$greater$default$6() {
      return ArbitraryMetropolisHastings$.MODULE$.$lessinit$greater$default$6();
   }

   public static int $lessinit$greater$default$5() {
      return ArbitraryMetropolisHastings$.MODULE$.$lessinit$greater$default$5();
   }

   public static Option unapply(final ArbitraryMetropolisHastings x$0) {
      return ArbitraryMetropolisHastings$.MODULE$.unapply(x$0);
   }

   public static int apply$default$6() {
      return ArbitraryMetropolisHastings$.MODULE$.apply$default$6();
   }

   public static int apply$default$5() {
      return ArbitraryMetropolisHastings$.MODULE$.apply$default$5();
   }

   public static ArbitraryMetropolisHastings apply(final Function1 logLikelihood, final Function1 proposal, final Function2 logProposalDensity, final Object init, final int burnIn, final int dropCount, final RandBasis rand) {
      return ArbitraryMetropolisHastings$.MODULE$.apply(logLikelihood, proposal, logProposalDensity, init, burnIn, dropCount, rand);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Function1 logLikelihood() {
      return this.logLikelihood;
   }

   public Function1 proposal() {
      return this.proposal;
   }

   public Function2 logProposalDensity() {
      return this.logProposalDensity;
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
      return ((Rand)this.proposal().apply(x)).draw();
   }

   public double logTransitionProbability(final Object start, final Object end) {
      return BoxesRunTime.unboxToDouble(this.logProposalDensity().apply(start, end));
   }

   public ArbitraryMetropolisHastings observe(final Object x) {
      int x$1 = 0;
      Function1 x$3 = this.copy$default$1();
      Function1 x$4 = this.copy$default$2();
      Function2 x$5 = this.copy$default$3();
      int x$6 = this.copy$default$6();
      return this.copy(x$3, x$4, x$5, x, 0, x$6, super.rand());
   }

   public ArbitraryMetropolisHastings copy(final Function1 logLikelihood, final Function1 proposal, final Function2 logProposalDensity, final Object init, final int burnIn, final int dropCount, final RandBasis rand) {
      return new ArbitraryMetropolisHastings(logLikelihood, proposal, logProposalDensity, init, burnIn, dropCount, rand);
   }

   public Function1 copy$default$1() {
      return this.logLikelihood();
   }

   public Function1 copy$default$2() {
      return this.proposal();
   }

   public Function2 copy$default$3() {
      return this.logProposalDensity();
   }

   public Object copy$default$4() {
      return this.init();
   }

   public int copy$default$5() {
      return this.burnIn();
   }

   public int copy$default$6() {
      return this.dropCount();
   }

   public String productPrefix() {
      return "ArbitraryMetropolisHastings";
   }

   public int productArity() {
      return 6;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.logLikelihood();
            break;
         case 1:
            var10000 = this.proposal();
            break;
         case 2:
            var10000 = this.logProposalDensity();
            break;
         case 3:
            var10000 = this.init();
            break;
         case 4:
            var10000 = BoxesRunTime.boxToInteger(this.burnIn());
            break;
         case 5:
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
      return x$1 instanceof ArbitraryMetropolisHastings;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "logLikelihood";
            break;
         case 1:
            var10000 = "proposal";
            break;
         case 2:
            var10000 = "logProposalDensity";
            break;
         case 3:
            var10000 = "init";
            break;
         case 4:
            var10000 = "burnIn";
            break;
         case 5:
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
      var1 = Statics.mix(var1, Statics.anyHash(this.proposal()));
      var1 = Statics.mix(var1, Statics.anyHash(this.logProposalDensity()));
      var1 = Statics.mix(var1, Statics.anyHash(this.init()));
      var1 = Statics.mix(var1, this.burnIn());
      var1 = Statics.mix(var1, this.dropCount());
      return Statics.finalizeHash(var1, 6);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var11;
      if (this != x$1) {
         label78: {
            boolean var2;
            if (x$1 instanceof ArbitraryMetropolisHastings) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label60: {
                  ArbitraryMetropolisHastings var4 = (ArbitraryMetropolisHastings)x$1;
                  if (this.burnIn() == var4.burnIn() && this.dropCount() == var4.dropCount()) {
                     label69: {
                        Function1 var10000 = this.logLikelihood();
                        Function1 var5 = var4.logLikelihood();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label69;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label69;
                        }

                        var10000 = this.proposal();
                        Function1 var6 = var4.proposal();
                        if (var10000 == null) {
                           if (var6 != null) {
                              break label69;
                           }
                        } else if (!var10000.equals(var6)) {
                           break label69;
                        }

                        Function2 var9 = this.logProposalDensity();
                        Function2 var7 = var4.logProposalDensity();
                        if (var9 == null) {
                           if (var7 != null) {
                              break label69;
                           }
                        } else if (!var9.equals(var7)) {
                           break label69;
                        }

                        if (BoxesRunTime.equals(this.init(), var4.init()) && var4.canEqual(this)) {
                           var11 = true;
                           break label60;
                        }
                     }
                  }

                  var11 = false;
               }

               if (var11) {
                  break label78;
               }
            }

            var11 = false;
            return var11;
         }
      }

      var11 = true;
      return var11;
   }

   public ArbitraryMetropolisHastings(final Function1 logLikelihood, final Function1 proposal, final Function2 logProposalDensity, final Object init, final int burnIn, final int dropCount, final RandBasis rand) {
      super(logLikelihood, init, burnIn, dropCount, rand);
      this.logLikelihood = logLikelihood;
      this.proposal = proposal;
      this.logProposalDensity = logProposalDensity;
      this.init = init;
      this.burnIn = burnIn;
      this.dropCount = dropCount;
      Product.$init$(this);
   }
}
