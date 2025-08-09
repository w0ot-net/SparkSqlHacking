package breeze.stats.distributions;

import breeze.linalg.DenseVector;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.ArrayOps.;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t\ra\u0001\u0002\u000f\u001e\r\u0012B\u0001\u0002\u0017\u0001\u0003\u0016\u0004%\t!\u0017\u0005\t;\u0002\u0011\t\u0012)A\u00055\"Aa\f\u0001BC\u0002\u0013%q\f\u0003\u0005j\u0001\tE\t\u0015!\u0003a\u0011\u0015Q\u0007\u0001\"\u0001l\u0011\u0015y\u0007\u0001\"\u0011q\u0011\u0015\u0019\b\u0001\"\u0006u\u0011\u001d9\b!!A\u0005\u0002aD\u0011\"a\u0004\u0001#\u0003%\t!!\u0005\t\u0013\u0005U\u0002!%A\u0005\u0002\u0005]\u0002\u0002CA%\u0001-\u0005I\u0011A0\t\u0013\u0005-\u0003!!A\u0005B\u00055\u0003\"CA0\u0001\u0005\u0005I\u0011AA1\u0011%\tI\u0007AA\u0001\n\u0003\tY\u0007C\u0005\u0002r\u0001\t\t\u0011\"\u0011\u0002t!I\u0011\u0011\u0011\u0001\u0002\u0002\u0013\u0005\u00111\u0011\u0005\n\u0003\u000f\u0003\u0011\u0011!C!\u0003\u0013C\u0011\"!$\u0001\u0003\u0003%\t%a$\t\u0013\u0005E\u0005!!A\u0005B\u0005M\u0005\"CAK\u0001\u0005\u0005I\u0011IAL\u000f%\tY*HA\u0001\u0012\u0013\tiJ\u0002\u0005\u001d;\u0005\u0005\t\u0012BAP\u0011\u0019Qg\u0003\"\u0001\u0002,\"I\u0011\u0011\u0013\f\u0002\u0002\u0013\u0015\u00131\u0013\u0005\n\u0003[3\u0012\u0011!CA\u0003_C\u0011\"!4\u0017\u0003\u0003%\t)a4\t\u0013\u0005eh#!A\u0005\n\u0005m(AF'vYRL\u0007\u000f\\3Qe\u0016$\u0017nY1uKN\u0014\u0016M\u001c3\u000b\u0005yy\u0012!\u00043jgR\u0014\u0018NY;uS>t7O\u0003\u0002!C\u0005)1\u000f^1ug*\t!%\u0001\u0004ce\u0016,'0Z\u0002\u0001+\t)#gE\u0003\u0001M1r\u0015\u000b\u0005\u0002(U5\t\u0001FC\u0001*\u0003\u0015\u00198-\u00197b\u0013\tY\u0003F\u0001\u0004B]f\u0014VM\u001a\t\u0004[9\u0002T\"A\u000f\n\u0005=j\"A\u0005)sK\u0012L7-\u0019;f%\u0006tG\r\u0012:boN\u0004\"!\r\u001a\r\u0001\u0011I1\u0007\u0001Q\u0001\u0002\u0003\u0015\r\u0001\u000e\u0002\u0002)F\u0011Q\u0007\u000f\t\u0003OYJ!a\u000e\u0015\u0003\u000f9{G\u000f[5oOB\u0011q%O\u0005\u0003u!\u00121!\u00118zQ\u0011\u0011DhP%\u0011\u0005\u001dj\u0014B\u0001 )\u0005-\u0019\b/Z2jC2L'0\u001a32\u000b\r\u0002\u0015i\u0011\"\u000f\u0005\u001d\n\u0015B\u0001\")\u0003\rIe\u000e^\u0019\u0005I\u0011C\u0015F\u0004\u0002F\u00116\taI\u0003\u0002HG\u00051AH]8pizJ\u0011!K\u0019\u0006G)[U\n\u0014\b\u0003O-K!\u0001\u0014\u0015\u0002\r\u0011{WO\u00197fc\u0011!C\tS\u0015\u0011\u0005\u001dz\u0015B\u0001))\u0005\u001d\u0001&o\u001c3vGR\u0004\"AU+\u000f\u0005\u0011\u001b\u0016B\u0001+)\u0003\u001d\u0001\u0018mY6bO\u0016L!AV,\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005QC\u0013\u0001\u0002:b]\u0012,\u0012A\u0017\t\u0004[m\u0003\u0014B\u0001/\u001e\u0005\u0011\u0011\u0016M\u001c3\u0002\u000bI\fg\u000e\u001a\u0011\u0002\u0015A\u0014X\rZ5dCR,7/F\u0001a!\r9\u0013mY\u0005\u0003E\"\u0012Q!\u0011:sCf\u0004Ba\n31M&\u0011Q\r\u000b\u0002\n\rVt7\r^5p]F\u0002\"aJ4\n\u0005!D#a\u0002\"p_2,\u0017M\\\u0001\faJ,G-[2bi\u0016\u001c\b%\u0001\u0004=S:LGO\u0010\u000b\u0004Y6t\u0007cA\u0017\u0001a!)\u0001,\u0002a\u00015\")a,\u0002a\u0001A\u0006I1m\u001c8eSRLwN\u001c\u000b\u00035FDQA\u001d\u0004A\u0002\r\f\u0011\u0001]\u0001\naJ,G-[2bi\u0016$\"AZ;\t\u000bY<\u0001\u0019\u0001\u0019\u0002\u0003a\fAaY8qsV\u0011\u0011\u0010 \u000b\u0006u\u0006\u0015\u0011\u0011\u0002\t\u0004[\u0001Y\bCA\u0019}\t%\u0019\u0004\u0002)A\u0001\u0002\u000b\u0007A\u0007K\u0003}yy\f\t!M\u0003$\u0001\u0006{()\r\u0003%\t\"K\u0013GB\u0012K\u0017\u0006\rA*\r\u0003%\t\"K\u0003\u0002\u0003-\t!\u0003\u0005\r!a\u0002\u0011\u00075Z6\u0010\u0003\u0005_\u0011A\u0005\t\u0019AA\u0006!\u00119\u0013-!\u0004\u0011\t\u001d\"7PZ\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0011\t\u0019\"!\u000b\u0016\u0005\u0005U!f\u0001.\u0002\u0018-\u0012\u0011\u0011\u0004\t\u0005\u00037\t)#\u0004\u0002\u0002\u001e)!\u0011qDA\u0011\u0003%)hn\u00195fG.,GMC\u0002\u0002$!\n!\"\u00198o_R\fG/[8o\u0013\u0011\t9#!\b\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\rB\u00054\u0013\u0001\u0006\t\u0011!b\u0001i!:\u0011\u0011\u0006\u001f\u0002.\u0005E\u0012GB\u0012A\u0003\u0006=\")\r\u0003%\t\"K\u0013GB\u0012K\u0017\u0006MB*\r\u0003%\t\"K\u0013AD2paf$C-\u001a4bk2$HEM\u000b\u0005\u0003s\ti$\u0006\u0002\u0002<)\u001a\u0001-a\u0006\u0005\u0013MR\u0001\u0015!A\u0001\u0006\u0004!\u0004fBA\u001fy\u0005\u0005\u0013QI\u0019\u0007G\u0001\u000b\u00151\t\"2\t\u0011\"\u0005*K\u0019\u0007G)[\u0015q\t'2\t\u0011\"\u0005*K\u0001\u0014aJ,G-[2bi\u0016\u001cH%Y2dKN\u001cH%M\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005=\u0003\u0003BA)\u00037j!!a\u0015\u000b\t\u0005U\u0013qK\u0001\u0005Y\u0006twM\u0003\u0002\u0002Z\u0005!!.\u0019<b\u0013\u0011\ti&a\u0015\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\t\t\u0019\u0007E\u0002(\u0003KJ1!a\u001a)\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\rA\u0014Q\u000e\u0005\n\u0003_r\u0011\u0011!a\u0001\u0003G\n1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA;!\u0015\t9(! 9\u001b\t\tIHC\u0002\u0002|!\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\ty(!\u001f\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0004M\u0006\u0015\u0005\u0002CA8!\u0005\u0005\t\u0019\u0001\u001d\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003\u001f\nY\tC\u0005\u0002pE\t\t\u00111\u0001\u0002d\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002d\u0005AAo\\*ue&tw\r\u0006\u0002\u0002P\u00051Q-];bYN$2AZAM\u0011!\ty\u0007FA\u0001\u0002\u0004A\u0014AF'vYRL\u0007\u000f\\3Qe\u0016$\u0017nY1uKN\u0014\u0016M\u001c3\u0011\u0005522\u0003\u0002\f'\u0003C\u0003B!a)\u0002*6\u0011\u0011Q\u0015\u0006\u0005\u0003O\u000b9&\u0001\u0002j_&\u0019a+!*\u0015\u0005\u0005u\u0015!B1qa2LX\u0003BAY\u0003o#b!a-\u0002D\u0006\u001d\u0007\u0003B\u0017\u0001\u0003k\u00032!MA\\\t%\u0019\u0014\u0004)A\u0001\u0002\u000b\u0007A\u0007K\u0004\u00028r\nY,a02\r\r\u0002\u0015)!0Cc\u0011!C\tS\u00152\r\rR5*!1Mc\u0011!C\tS\u0015\t\raK\u0002\u0019AAc!\u0011i3,!.\t\ryK\u0002\u0019AAe!\u00119\u0013-a3\u0011\u000b\u001d\"\u0017Q\u00174\u0002\u000fUt\u0017\r\u001d9msV!\u0011\u0011[Ar)\u0011\t\u0019.a=\u0011\u000b\u001d\n).!7\n\u0007\u0005]\u0007F\u0001\u0004PaRLwN\u001c\t\bO\u0005m\u0017q\\Ax\u0013\r\ti\u000e\u000b\u0002\u0007)V\u0004H.\u001a\u001a\u0011\t5Z\u0016\u0011\u001d\t\u0004c\u0005\rH!C\u001a\u001bA\u0003\u0005\tQ1\u00015Q\u001d\t\u0019\u000fPAt\u0003W\fda\t!B\u0003S\u0014\u0015\u0007\u0002\u0013E\u0011&\nda\t&L\u0003[d\u0015\u0007\u0002\u0013E\u0011&\u0002BaJ1\u0002rB)q\u0005ZAqM\"I\u0011Q\u001f\u000e\u0002\u0002\u0003\u0007\u0011q_\u0001\u0004q\u0012\u0002\u0004\u0003B\u0017\u0001\u0003C\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!@\u0011\t\u0005E\u0013q`\u0005\u0005\u0005\u0003\t\u0019F\u0001\u0004PE*,7\r\u001e"
)
public class MultiplePredicatesRand implements PredicateRandDraws, Product {
   public final Rand rand;
   public final Function1[] predicates;

   public static Option unapply(final MultiplePredicatesRand x$0) {
      return MultiplePredicatesRand$.MODULE$.unapply(x$0);
   }

   public static MultiplePredicatesRand apply(final Rand rand, final Function1[] predicates) {
      return MultiplePredicatesRand$.MODULE$.apply(rand, predicates);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Object draw() {
      return PredicateRandDraws.draw$(this);
   }

   public double draw$mcD$sp() {
      return PredicateRandDraws.draw$mcD$sp$(this);
   }

   public int draw$mcI$sp() {
      return PredicateRandDraws.draw$mcI$sp$(this);
   }

   public Option drawOpt() {
      return PredicateRandDraws.drawOpt$(this);
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

   public Function1[] predicates$access$1() {
      return this.predicates;
   }

   public Rand rand() {
      return this.rand;
   }

   public Function1[] predicates() {
      return this.predicates;
   }

   public Rand condition(final Function1 p) {
      Function1[] newPredicates = new Function1[.MODULE$.size$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.predicates())) + 1];
      int index$macro$2 = 0;

      for(int limit$macro$4 = .MODULE$.size$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.predicates())); index$macro$2 < limit$macro$4; ++index$macro$2) {
         newPredicates[index$macro$2] = this.predicates()[index$macro$2];
      }

      newPredicates[.MODULE$.size$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.predicates()))] = p;
      return new MultiplePredicatesRand(this.rand(), newPredicates);
   }

   public boolean predicate(final Object x) {
      boolean result = true;

      for(int i = 0; i < .MODULE$.size$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.predicates())) && result; ++i) {
         result = result && BoxesRunTime.unboxToBoolean(this.predicates()[i].apply(x));
      }

      return result;
   }

   public MultiplePredicatesRand copy(final Rand rand, final Function1[] predicates) {
      return new MultiplePredicatesRand(rand, predicates);
   }

   public Rand copy$default$1() {
      return this.rand();
   }

   public Function1[] copy$default$2() {
      return this.predicates();
   }

   public String productPrefix() {
      return "MultiplePredicatesRand";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.rand();
            break;
         case 1:
            var10000 = this.predicates$access$1();
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof MultiplePredicatesRand;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "rand";
            break;
         case 1:
            var10000 = "predicates";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var7;
      if (this != x$1) {
         label53: {
            boolean var2;
            if (x$1 instanceof MultiplePredicatesRand) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label36: {
                  label35: {
                     MultiplePredicatesRand var4 = (MultiplePredicatesRand)x$1;
                     Rand var10000 = this.rand();
                     Rand var5 = var4.rand();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label35;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label35;
                     }

                     if (this.predicates$access$1() == var4.predicates$access$1()) {
                        var7 = true;
                        break label36;
                     }
                  }

                  var7 = false;
               }

               if (var7) {
                  break label53;
               }
            }

            var7 = false;
            return var7;
         }
      }

      var7 = true;
      return var7;
   }

   public Rand rand$mcD$sp() {
      return this.rand();
   }

   public Rand rand$mcI$sp() {
      return this.rand();
   }

   public Function1[] predicates$mcD$sp() {
      return this.predicates();
   }

   public Function1[] predicates$mcI$sp() {
      return this.predicates();
   }

   public Rand condition$mcD$sp(final Function1 p) {
      return this.condition(p);
   }

   public Rand condition$mcI$sp(final Function1 p) {
      return this.condition(p);
   }

   public boolean predicate$mcD$sp(final double x) {
      return this.predicate(BoxesRunTime.boxToDouble(x));
   }

   public boolean predicate$mcI$sp(final int x) {
      return this.predicate(BoxesRunTime.boxToInteger(x));
   }

   public MultiplePredicatesRand copy$mDc$sp(final Rand rand, final Function1[] predicates) {
      return new MultiplePredicatesRand$mcD$sp(rand, predicates);
   }

   public MultiplePredicatesRand copy$mIc$sp(final Rand rand, final Function1[] predicates) {
      return new MultiplePredicatesRand$mcI$sp(rand, predicates);
   }

   public Rand copy$default$1$mcD$sp() {
      return this.copy$default$1();
   }

   public Rand copy$default$1$mcI$sp() {
      return this.copy$default$1();
   }

   public Function1[] copy$default$2$mcD$sp() {
      return this.copy$default$2();
   }

   public Function1[] copy$default$2$mcI$sp() {
      return this.copy$default$2();
   }

   public Function1[] predicates$access$1$mcD$sp() {
      return this.predicates$access$1();
   }

   public Function1[] predicates$access$1$mcI$sp() {
      return this.predicates$access$1();
   }

   public boolean specInstance$() {
      return false;
   }

   public MultiplePredicatesRand(final Rand rand, final Function1[] predicates) {
      this.rand = rand;
      this.predicates = predicates;
      Rand.$init$(this);
      PredicateRandDraws.$init$(this);
      Product.$init$(this);
   }
}
