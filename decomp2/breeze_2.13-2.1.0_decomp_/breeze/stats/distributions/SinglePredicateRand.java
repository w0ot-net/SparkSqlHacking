package breeze.stats.distributions;

import breeze.linalg.DenseVector;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mh\u0001B\u000e\u001d\r\u000eB\u0001b\u0016\u0001\u0003\u0016\u0004%\t\u0001\u0017\u0005\t9\u0002\u0011\t\u0012)A\u00053\"AQ\f\u0001BK\u0002\u0013\u0005a\f\u0003\u0005f\u0001\tE\t\u0015!\u0003`\u0011\u00151\u0007\u0001\"\u0001h\u0011\u0015Y\u0007\u0001\"\u0006m\u0011\u0015y\u0007\u0001\"\u0011q\u0011\u001d\u0019\b!!A\u0005\u0002QD\u0011\"!\u0002\u0001#\u0003%\t!a\u0002\t\u0013\u0005-\u0002!%A\u0005\u0002\u00055\u0002\"CA \u0001\u0005\u0005I\u0011IA!\u0011%\t\u0019\u0006AA\u0001\n\u0003\t)\u0006C\u0005\u0002^\u0001\t\t\u0011\"\u0001\u0002`!I\u0011Q\r\u0001\u0002\u0002\u0013\u0005\u0013q\r\u0005\n\u0003k\u0002\u0011\u0011!C\u0001\u0003oB\u0011\"a\u001f\u0001\u0003\u0003%\t%! \t\u0013\u0005\u0005\u0005!!A\u0005B\u0005\r\u0005\"CAC\u0001\u0005\u0005I\u0011IAD\u0011%\tI\tAA\u0001\n\u0003\nYiB\u0005\u0002\u0010r\t\t\u0011#\u0003\u0002\u0012\u001aA1\u0004HA\u0001\u0012\u0013\t\u0019\n\u0003\u0004g+\u0011\u0005\u0011q\u0014\u0005\n\u0003\u000b+\u0012\u0011!C#\u0003\u000fC\u0011\"!)\u0016\u0003\u0003%\t)a)\t\u0013\u0005}V#!A\u0005\u0002\u0006\u0005\u0007\"CAu+\u0005\u0005I\u0011BAv\u0005M\u0019\u0016N\\4mKB\u0013X\rZ5dCR,'+\u00198e\u0015\tib$A\u0007eSN$(/\u001b2vi&|gn\u001d\u0006\u0003?\u0001\nQa\u001d;biNT\u0011!I\u0001\u0007EJ,WM_3\u0004\u0001U\u0011A%M\n\u0006\u0001\u0015ZS\n\u0015\t\u0003M%j\u0011a\n\u0006\u0002Q\u0005)1oY1mC&\u0011!f\n\u0002\u0007\u0003:L(+\u001a4\u0011\u00071js&D\u0001\u001d\u0013\tqCD\u0001\nQe\u0016$\u0017nY1uKJ\u000bg\u000e\u001a#sC^\u001c\bC\u0001\u00192\u0019\u0001!\u0011B\r\u0001!\u0002\u0003\u0005)\u0019A\u001a\u0003\u0003Q\u000b\"\u0001N\u001c\u0011\u0005\u0019*\u0014B\u0001\u001c(\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"A\n\u001d\n\u0005e:#aA!os\"\"\u0011g\u000f I!\t1C(\u0003\u0002>O\tY1\u000f]3dS\u0006d\u0017N_3ec\u0015\u0019s\b\u0011\"B\u001d\t1\u0003)\u0003\u0002BO\u0005\u0019\u0011J\u001c;2\t\u0011\u001au\t\u000b\b\u0003\t\u001ek\u0011!\u0012\u0006\u0003\r\n\na\u0001\u0010:p_Rt\u0014\"\u0001\u00152\u000b\rJ%\nT&\u000f\u0005\u0019R\u0015BA&(\u0003\u0019!u.\u001e2mKF\"AeQ$)!\t1c*\u0003\u0002PO\t9\u0001K]8ek\u000e$\bCA)U\u001d\t\u0019%+\u0003\u0002TO\u00059\u0001/Y2lC\u001e,\u0017BA+W\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\u0019v%\u0001\u0003sC:$W#A-\u0011\u00071Rv&\u0003\u0002\\9\t!!+\u00198e\u0003\u0015\u0011\u0018M\u001c3!\u0003\u0011\u0001(/\u001a3\u0016\u0003}\u0003BA\n10E&\u0011\u0011m\n\u0002\n\rVt7\r^5p]F\u0002\"AJ2\n\u0005\u0011<#a\u0002\"p_2,\u0017M\\\u0001\u0006aJ,G\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007!L'\u000eE\u0002-\u0001=BQaV\u0003A\u0002eCQ!X\u0003A\u0002}\u000b\u0011\u0002\u001d:fI&\u001c\u0017\r^3\u0015\u0005\tl\u0007\"\u00028\u0007\u0001\u0004y\u0013!\u0001=\u0002\u0013\r|g\u000eZ5uS>tGCA-r\u0011\u0015\u0011x\u00011\u0001`\u0003\u0005\u0001\u0018\u0001B2paf,\"!\u001e=\u0015\tYt\u0018\u0011\u0001\t\u0004Y\u00019\bC\u0001\u0019y\t%\u0011\u0004\u0002)A\u0001\u0002\u000b\u00071\u0007\u000b\u0003ywid\u0018'B\u0012@\u0001n\f\u0015\u0007\u0002\u0013D\u000f\"\nTaI%K{.\u000bD\u0001J\"HQ!9q\u000b\u0003I\u0001\u0002\u0004y\bc\u0001\u0017[o\"AQ\f\u0003I\u0001\u0002\u0004\t\u0019\u0001\u0005\u0003'A^\u0014\u0017AD2paf$C-\u001a4bk2$H%M\u000b\u0005\u0003\u0013\ty\"\u0006\u0002\u0002\f)\u001a\u0011,!\u0004,\u0005\u0005=\u0001\u0003BA\t\u00037i!!a\u0005\u000b\t\u0005U\u0011qC\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!\u0007(\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003;\t\u0019BA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016$\u0011BM\u0005!\u0002\u0003\u0005)\u0019A\u001a)\u000f\u0005}1(a\t\u0002(E21e\u0010!\u0002&\u0005\u000bD\u0001J\"HQE21%\u0013&\u0002*-\u000bD\u0001J\"HQ\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T\u0003BA\u0018\u0003g)\"!!\r+\u0007}\u000bi\u0001B\u00053\u0015\u0001\u0006\t\u0011!b\u0001g!:\u00111G\u001e\u00028\u0005m\u0012GB\u0012@\u0001\u0006e\u0012)\r\u0003%\u0007\u001eC\u0013GB\u0012J\u0015\u0006u2*\r\u0003%\u0007\u001eC\u0013!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0002DA!\u0011QIA(\u001b\t\t9E\u0003\u0003\u0002J\u0005-\u0013\u0001\u00027b]\u001eT!!!\u0014\u0002\t)\fg/Y\u0005\u0005\u0003#\n9E\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u0003/\u00022AJA-\u0013\r\tYf\n\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0004o\u0005\u0005\u0004\"CA2\u001b\u0005\u0005\t\u0019AA,\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011\u0011\u000e\t\u0006\u0003W\n\thN\u0007\u0003\u0003[R1!a\u001c(\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003g\niG\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGc\u00012\u0002z!A\u00111M\b\u0002\u0002\u0003\u0007q'\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA\"\u0003\u007fB\u0011\"a\u0019\u0011\u0003\u0003\u0005\r!a\u0016\u0002\u0011!\f7\u000f[\"pI\u0016$\"!a\u0016\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!a\u0011\u0002\r\u0015\fX/\u00197t)\r\u0011\u0017Q\u0012\u0005\t\u0003G\u001a\u0012\u0011!a\u0001o\u0005\u00192+\u001b8hY\u0016\u0004&/\u001a3jG\u0006$XMU1oIB\u0011A&F\n\u0005+\u0015\n)\n\u0005\u0003\u0002\u0018\u0006uUBAAM\u0015\u0011\tY*a\u0013\u0002\u0005%|\u0017bA+\u0002\u001aR\u0011\u0011\u0011S\u0001\u0006CB\u0004H._\u000b\u0005\u0003K\u000bY\u000b\u0006\u0004\u0002(\u0006]\u00161\u0018\t\u0005Y\u0001\tI\u000bE\u00021\u0003W#\u0011B\r\r!\u0002\u0003\u0005)\u0019A\u001a)\u000f\u0005-6(a,\u00024F21e\u0010!\u00022\u0006\u000bD\u0001J\"HQE21%\u0013&\u00026.\u000bD\u0001J\"HQ!1q\u000b\u0007a\u0001\u0003s\u0003B\u0001\f.\u0002*\"1Q\f\u0007a\u0001\u0003{\u0003RA\n1\u0002*\n\fq!\u001e8baBd\u00170\u0006\u0003\u0002D\u0006UG\u0003BAc\u0003G\u0004RAJAd\u0003\u0017L1!!3(\u0005\u0019y\u0005\u000f^5p]B9a%!4\u0002R\u0006\u0005\u0018bAAhO\t1A+\u001e9mKJ\u0002B\u0001\f.\u0002TB\u0019\u0001'!6\u0005\u0013IJ\u0002\u0015!A\u0001\u0006\u0004\u0019\u0004fBAkw\u0005e\u0017Q\\\u0019\u0007G}\u0002\u00151\\!2\t\u0011\u001au\tK\u0019\u0007G%S\u0015q\\&2\t\u0011\u001au\t\u000b\t\u0006M\u0001\f\u0019N\u0019\u0005\n\u0003KL\u0012\u0011!a\u0001\u0003O\f1\u0001\u001f\u00131!\u0011a\u0003!a5\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u00055\b\u0003BA#\u0003_LA!!=\u0002H\t1qJ\u00196fGR\u0004"
)
public class SinglePredicateRand implements PredicateRandDraws, Product {
   public final Rand rand;
   public final Function1 pred;

   public static Option unapply(final SinglePredicateRand x$0) {
      return SinglePredicateRand$.MODULE$.unapply(x$0);
   }

   public static SinglePredicateRand apply(final Rand rand, final Function1 pred) {
      return SinglePredicateRand$.MODULE$.apply(rand, pred);
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

   public Rand rand() {
      return this.rand;
   }

   public Function1 pred() {
      return this.pred;
   }

   public boolean predicate(final Object x) {
      return BoxesRunTime.unboxToBoolean(this.pred().apply(x));
   }

   public Rand condition(final Function1 p) {
      Function1[] newPredicates = new Function1[2];
      newPredicates[0] = this.pred();
      newPredicates[1] = p;
      return new MultiplePredicatesRand(this.rand(), newPredicates);
   }

   public SinglePredicateRand copy(final Rand rand, final Function1 pred) {
      return new SinglePredicateRand(rand, pred);
   }

   public Rand copy$default$1() {
      return this.rand();
   }

   public Function1 copy$default$2() {
      return this.pred();
   }

   public String productPrefix() {
      return "SinglePredicateRand";
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
            var10000 = this.pred();
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
      return x$1 instanceof SinglePredicateRand;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "rand";
            break;
         case 1:
            var10000 = "pred";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var9;
      if (this != x$1) {
         label60: {
            boolean var2;
            if (x$1 instanceof SinglePredicateRand) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label43: {
                  label42: {
                     label41: {
                        SinglePredicateRand var4 = (SinglePredicateRand)x$1;
                        Rand var10000 = this.rand();
                        Rand var5 = var4.rand();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label41;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label41;
                        }

                        Function1 var7 = this.pred();
                        Function1 var6 = var4.pred();
                        if (var7 == null) {
                           if (var6 == null) {
                              break label42;
                           }
                        } else if (var7.equals(var6)) {
                           break label42;
                        }
                     }

                     var9 = false;
                     break label43;
                  }

                  var9 = true;
               }

               if (var9) {
                  break label60;
               }
            }

            var9 = false;
            return var9;
         }
      }

      var9 = true;
      return var9;
   }

   public Rand rand$mcD$sp() {
      return this.rand();
   }

   public Rand rand$mcI$sp() {
      return this.rand();
   }

   public Function1 pred$mcD$sp() {
      return this.pred();
   }

   public Function1 pred$mcI$sp() {
      return this.pred();
   }

   public boolean predicate$mcD$sp(final double x) {
      return this.predicate(BoxesRunTime.boxToDouble(x));
   }

   public boolean predicate$mcI$sp(final int x) {
      return this.predicate(BoxesRunTime.boxToInteger(x));
   }

   public Rand condition$mcD$sp(final Function1 p) {
      return this.condition(p);
   }

   public Rand condition$mcI$sp(final Function1 p) {
      return this.condition(p);
   }

   public SinglePredicateRand copy$mDc$sp(final Rand rand, final Function1 pred) {
      return new SinglePredicateRand$mcD$sp(rand, pred);
   }

   public SinglePredicateRand copy$mIc$sp(final Rand rand, final Function1 pred) {
      return new SinglePredicateRand$mcI$sp(rand, pred);
   }

   public Rand copy$default$1$mcD$sp() {
      return this.copy$default$1();
   }

   public Rand copy$default$1$mcI$sp() {
      return this.copy$default$1();
   }

   public Function1 copy$default$2$mcD$sp() {
      return this.copy$default$2();
   }

   public Function1 copy$default$2$mcI$sp() {
      return this.copy$default$2();
   }

   public boolean specInstance$() {
      return false;
   }

   public SinglePredicateRand(final Rand rand, final Function1 pred) {
      this.rand = rand;
      this.pred = pred;
      Rand.$init$(this);
      PredicateRandDraws.$init$(this);
      Product.$init$(this);
   }
}
