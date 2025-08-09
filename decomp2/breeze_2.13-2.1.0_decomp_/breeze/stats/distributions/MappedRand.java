package breeze.stats.distributions;

import breeze.linalg.DenseVector;
import java.lang.invoke.SerializedLambda;
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
   bytes = "\u0006\u0005\tEc\u0001\u0002\u000f\u001e\r\u0012B\u0001\u0002\u0017\u0001\u0003\u0016\u0004%\t!\u0017\u0005\tG\u0002\u0011\t\u0012)A\u00055\"AA\r\u0001BK\u0002\u0013\u0005Q\r\u0003\u0005j\u0001\tE\t\u0015!\u0003g\u0011\u0015Q\u0007\u0001\"\u0001l\u0011\u0015y\u0007\u0001\"\u0001q\u0011\u0015\t\b\u0001\"\u0011s\u0011\u00151\b\u0001\"\u0011x\u0011%\t\t\u0001AA\u0001\n\u0003\t\u0019\u0001C\u0005\u0002.\u0001\t\n\u0011\"\u0001\u00020!I\u0011q\f\u0001\u0012\u0002\u0013\u0005\u0011\u0011\r\u0005\n\u0003\u007f\u0002\u0011\u0011!C!\u0003\u0003C\u0011\"a%\u0001\u0003\u0003%\t!!&\t\u0013\u0005u\u0005!!A\u0005\u0002\u0005}\u0005\"CAS\u0001\u0005\u0005I\u0011IAT\u0011%\t)\fAA\u0001\n\u0003\t9\fC\u0005\u0002B\u0002\t\t\u0011\"\u0011\u0002D\"I\u0011q\u0019\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u001a\u0005\n\u0003\u0017\u0004\u0011\u0011!C!\u0003\u001bD\u0011\"a4\u0001\u0003\u0003%\t%!5\b\u0013\u0005UW$!A\t\n\u0005]g\u0001\u0003\u000f\u001e\u0003\u0003EI!!7\t\r)4B\u0011AAs\u0011%\tYMFA\u0001\n\u000b\ni\rC\u0005\u0002hZ\t\t\u0011\"!\u0002j\"I!1\u0003\f\u0002\u0002\u0013\u0005%Q\u0003\u0005\n\u0005\u000f2\u0012\u0011!C\u0005\u0005\u0013\u0012!\"T1qa\u0016$'+\u00198e\u0015\tqr$A\u0007eSN$(/\u001b2vi&|gn\u001d\u0006\u0003A\u0005\nQa\u001d;biNT\u0011AI\u0001\u0007EJ,WM_3\u0004\u0001U\u0019Q\u0005\u0018\u001a\u0014\u000b\u00011CFT)\u0011\u0005\u001dRS\"\u0001\u0015\u000b\u0003%\nQa]2bY\u0006L!a\u000b\u0015\u0003\r\u0005s\u0017PU3g!\ric\u0006M\u0007\u0002;%\u0011q&\b\u0002\u0005%\u0006tG\r\u0005\u00022e1\u0001A!C\u001a\u0001A\u0003\u0005\tQ1\u00015\u0005\u0005)\u0016CA\u001b9!\t9c'\u0003\u00028Q\t9aj\u001c;iS:<\u0007CA\u0014:\u0013\tQ\u0004FA\u0002B]fDCA\r\u001f@\u0013B\u0011q%P\u0005\u0003}!\u00121b\u001d9fG&\fG.\u001b>fIF*1\u0005Q!D\u0005:\u0011q%Q\u0005\u0003\u0005\"\n1!\u00138uc\u0011!C\tS\u0015\u000f\u0005\u0015CU\"\u0001$\u000b\u0005\u001d\u001b\u0013A\u0002\u001fs_>$h(C\u0001*c\u0015\u0019#jS'M\u001d\t93*\u0003\u0002MQ\u00051Ai\\;cY\u0016\fD\u0001\n#ISA\u0011qeT\u0005\u0003!\"\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002S+:\u0011AiU\u0005\u0003)\"\nq\u0001]1dW\u0006<W-\u0003\u0002W/\na1+\u001a:jC2L'0\u00192mK*\u0011A\u000bK\u0001\u0005e\u0006tG-F\u0001[!\ricf\u0017\t\u0003cq#\u0011\"\u0018\u0001!\u0002\u0003\u0005)\u0019\u0001\u001b\u0003\u0003QCC\u0001\u0018\u001f`CF*1\u0005Q!a\u0005F\"A\u0005\u0012%*c\u0015\u0019#j\u00132Mc\u0011!C\tS\u0015\u0002\u000bI\fg\u000e\u001a\u0011\u0002\t\u0019,hnY\u000b\u0002MB!qeZ.1\u0013\tA\u0007FA\u0005Gk:\u001cG/[8oc\u0005)a-\u001e8dA\u00051A(\u001b8jiz\"2\u0001\\7o!\u0011i\u0003a\u0017\u0019\t\u000ba+\u0001\u0019\u0001.\t\u000b\u0011,\u0001\u0019\u00014\u0002\t\u0011\u0014\u0018m\u001e\u000b\u0002a\u00059AM]1x\u001fB$H#A:\u0011\u0007\u001d\"\b'\u0003\u0002vQ\t1q\n\u001d;j_:\f1!\\1q+\tA8\u0010\u0006\u0002z{B\u0019QF\f>\u0011\u0005EZH!\u0002?\t\u0005\u0004!$!A#\t\u000byD\u0001\u0019A@\u0002\u0003\u0019\u0004BaJ41u\u0006!1m\u001c9z+\u0019\t)!a\u0003\u0002\u001aQ1\u0011qAA\u0013\u0003S\u0001b!\f\u0001\u0002\n\u0005]\u0001cA\u0019\u0002\f\u0011IQ,\u0003Q\u0001\u0002\u0003\u0015\r\u0001\u000e\u0015\b\u0003\u0017a\u0014qBA\nc\u0019\u0019\u0003)QA\t\u0005F\"A\u0005\u0012%*c\u0019\u0019#jSA\u000b\u0019F\"A\u0005\u0012%*!\r\t\u0014\u0011\u0004\u0003\ng%\u0001\u000b\u0011!AC\u0002QBs!!\u0007=\u0003;\t\t#\r\u0004$\u0001\u0006\u000byBQ\u0019\u0005I\u0011C\u0015&\r\u0004$\u0015.\u000b\u0019\u0003T\u0019\u0005I\u0011C\u0015\u0006\u0003\u0005Y\u0013A\u0005\t\u0019AA\u0014!\u0011ic&!\u0003\t\u0011\u0011L\u0001\u0013!a\u0001\u0003W\u0001baJ4\u0002\n\u0005]\u0011AD2paf$C-\u001a4bk2$H%M\u000b\u0007\u0003c\t9%a\u0015\u0016\u0005\u0005M\"f\u0001.\u00026-\u0012\u0011q\u0007\t\u0005\u0003s\t\u0019%\u0004\u0002\u0002<)!\u0011QHA \u0003%)hn\u00195fG.,GMC\u0002\u0002B!\n!\"\u00198o_R\fG/[8o\u0013\u0011\t)%a\u000f\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\rB\u0005^\u0015\u0001\u0006\t\u0011!b\u0001i!:\u0011q\t\u001f\u0002L\u0005=\u0013GB\u0012A\u0003\u00065#)\r\u0003%\t\"K\u0013GB\u0012K\u0017\u0006EC*\r\u0003%\t\"KC!C\u001a\u000bA\u0003\u0005\tQ1\u00015Q\u001d\t\u0019\u0006PA,\u00037\nda\t!B\u00033\u0012\u0015\u0007\u0002\u0013E\u0011&\nda\t&L\u0003;b\u0015\u0007\u0002\u0013E\u0011&\nabY8qs\u0012\"WMZ1vYR$#'\u0006\u0004\u0002d\u0005\u001d\u00141O\u000b\u0003\u0003KR3AZA\u001b\t%i6\u0002)A\u0001\u0002\u000b\u0007A\u0007K\u0004\u0002hq\nY'a\u001c2\r\r\u0002\u0015)!\u001cCc\u0011!C\tS\u00152\r\rR5*!\u001dMc\u0011!C\tS\u0015\u0005\u0013MZ\u0001\u0015!A\u0001\u0006\u0004!\u0004fBA:y\u0005]\u00141P\u0019\u0007G\u0001\u000b\u0015\u0011\u0010\"2\t\u0011\"\u0005*K\u0019\u0007G)[\u0015Q\u0010'2\t\u0011\"\u0005*K\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005\r\u0005\u0003BAC\u0003\u001fk!!a\"\u000b\t\u0005%\u00151R\u0001\u0005Y\u0006twM\u0003\u0002\u0002\u000e\u0006!!.\u0019<b\u0013\u0011\t\t*a\"\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\t\t9\nE\u0002(\u00033K1!a')\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\rA\u0014\u0011\u0015\u0005\n\u0003Gs\u0011\u0011!a\u0001\u0003/\u000b1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAAU!\u0015\tY+!-9\u001b\t\tiKC\u0002\u00020\"\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\t\u0019,!,\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003s\u000by\fE\u0002(\u0003wK1!!0)\u0005\u001d\u0011un\u001c7fC:D\u0001\"a)\u0011\u0003\u0003\u0005\r\u0001O\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0002\u0004\u0006\u0015\u0007\"CAR#\u0005\u0005\t\u0019AAL\u0003!A\u0017m\u001d5D_\u0012,GCAAL\u0003!!xn\u0015;sS:<GCAAB\u0003\u0019)\u0017/^1mgR!\u0011\u0011XAj\u0011!\t\u0019\u000bFA\u0001\u0002\u0004A\u0014AC'baB,GMU1oIB\u0011QFF\n\u0005-\u0019\nY\u000e\u0005\u0003\u0002^\u0006\rXBAAp\u0015\u0011\t\t/a#\u0002\u0005%|\u0017b\u0001,\u0002`R\u0011\u0011q[\u0001\u0006CB\u0004H._\u000b\u0007\u0003W\f\t0a@\u0015\r\u00055(1\u0002B\b!\u0019i\u0003!a<\u0002~B\u0019\u0011'!=\u0005\u0013uK\u0002\u0015!A\u0001\u0006\u0004!\u0004fBAyy\u0005U\u0018\u0011`\u0019\u0007G\u0001\u000b\u0015q\u001f\"2\t\u0011\"\u0005*K\u0019\u0007G)[\u00151 '2\t\u0011\"\u0005*\u000b\t\u0004c\u0005}H!C\u001a\u001aA\u0003\u0005\tQ1\u00015Q\u001d\ty\u0010\u0010B\u0002\u0005\u000f\tda\t!B\u0005\u000b\u0011\u0015\u0007\u0002\u0013E\u0011&\nda\t&L\u0005\u0013a\u0015\u0007\u0002\u0013E\u0011&Ba\u0001W\rA\u0002\t5\u0001\u0003B\u0017/\u0003_Da\u0001Z\rA\u0002\tE\u0001CB\u0014h\u0003_\fi0A\u0004v]\u0006\u0004\b\u000f\\=\u0016\r\t]!Q\u0005B\u001b)\u0011\u0011IB!\u0011\u0011\t\u001d\"(1\u0004\t\bO\tu!\u0011\u0005B\u0019\u0013\r\u0011y\u0002\u000b\u0002\u0007)V\u0004H.\u001a\u001a\u0011\t5r#1\u0005\t\u0004c\t\u0015B!C/\u001bA\u0003\u0005\tQ1\u00015Q\u001d\u0011)\u0003\u0010B\u0015\u0005[\tda\t!B\u0005W\u0011\u0015\u0007\u0002\u0013E\u0011&\nda\t&L\u0005_a\u0015\u0007\u0002\u0013E\u0011&\u0002baJ4\u0003$\tM\u0002cA\u0019\u00036\u0011I1G\u0007Q\u0001\u0002\u0003\u0015\r\u0001\u000e\u0015\b\u0005ka$\u0011\bB\u001fc\u0019\u0019\u0003)\u0011B\u001e\u0005F\"A\u0005\u0012%*c\u0019\u0019#j\u0013B \u0019F\"A\u0005\u0012%*\u0011%\u0011\u0019EGA\u0001\u0002\u0004\u0011)%A\u0002yIA\u0002b!\f\u0001\u0003$\tM\u0012\u0001D<sSR,'+\u001a9mC\u000e,GC\u0001B&!\u0011\t)I!\u0014\n\t\t=\u0013q\u0011\u0002\u0007\u001f\nTWm\u0019;"
)
public class MappedRand implements Rand, Product {
   public final Rand rand;
   public final Function1 func;

   public static Option unapply(final MappedRand x$0) {
      return MappedRand$.MODULE$.unapply(x$0);
   }

   public static MappedRand apply(final Rand rand, final Function1 func) {
      return MappedRand$.MODULE$.apply(rand, func);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
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

   public Rand rand() {
      return this.rand;
   }

   public Function1 func() {
      return this.func;
   }

   public Object draw() {
      return this.func().apply(this.rand().draw());
   }

   public Option drawOpt() {
      return this.rand().drawOpt().map(this.func());
   }

   public Rand map(final Function1 f) {
      return new MappedRand(this.rand(), (x) -> f.apply(this.func().apply(x)));
   }

   public MappedRand copy(final Rand rand, final Function1 func) {
      return new MappedRand(rand, func);
   }

   public Rand copy$default$1() {
      return this.rand();
   }

   public Function1 copy$default$2() {
      return this.func();
   }

   public String productPrefix() {
      return "MappedRand";
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
            var10000 = this.func();
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
      return x$1 instanceof MappedRand;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "rand";
            break;
         case 1:
            var10000 = "func";
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
            if (x$1 instanceof MappedRand) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label43: {
                  label42: {
                     label41: {
                        MappedRand var4 = (MappedRand)x$1;
                        Rand var10000 = this.rand();
                        Rand var5 = var4.rand();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label41;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label41;
                        }

                        Function1 var7 = this.func();
                        Function1 var6 = var4.func();
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

   public Function1 func$mcDD$sp() {
      return this.func();
   }

   public Function1 func$mcDI$sp() {
      return this.func();
   }

   public Function1 func$mcID$sp() {
      return this.func();
   }

   public Function1 func$mcII$sp() {
      return this.func();
   }

   public double draw$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.draw());
   }

   public int draw$mcI$sp() {
      return BoxesRunTime.unboxToInt(this.draw());
   }

   public Rand map$mcD$sp(final Function1 f) {
      return this.map(f);
   }

   public Rand map$mcI$sp(final Function1 f) {
      return this.map(f);
   }

   public MappedRand copy$mDDc$sp(final Rand rand, final Function1 func) {
      return new MappedRand$mcDD$sp(rand, func);
   }

   public MappedRand copy$mDIc$sp(final Rand rand, final Function1 func) {
      return new MappedRand$mcDI$sp(rand, func);
   }

   public MappedRand copy$mIDc$sp(final Rand rand, final Function1 func) {
      return new MappedRand$mcID$sp(rand, func);
   }

   public MappedRand copy$mIIc$sp(final Rand rand, final Function1 func) {
      return new MappedRand$mcII$sp(rand, func);
   }

   public Rand copy$default$1$mcD$sp() {
      return this.copy$default$1();
   }

   public Rand copy$default$1$mcI$sp() {
      return this.copy$default$1();
   }

   public Function1 copy$default$2$mcDD$sp() {
      return this.copy$default$2();
   }

   public Function1 copy$default$2$mcDI$sp() {
      return this.copy$default$2();
   }

   public Function1 copy$default$2$mcID$sp() {
      return this.copy$default$2();
   }

   public Function1 copy$default$2$mcII$sp() {
      return this.copy$default$2();
   }

   public boolean specInstance$() {
      return false;
   }

   public MappedRand(final Rand rand, final Function1 func) {
      this.rand = rand;
      this.func = func;
      Rand.$init$(this);
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
