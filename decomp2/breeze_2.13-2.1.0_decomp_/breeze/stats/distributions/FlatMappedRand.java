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
   bytes = "\u0006\u0005\t]c\u0001\u0002\u000f\u001e\r\u0012B\u0001\u0002\u0017\u0001\u0003\u0016\u0004%\t!\u0017\u0005\tG\u0002\u0011\t\u0012)A\u00055\"AA\r\u0001BK\u0002\u0013\u0005Q\r\u0003\u0005j\u0001\tE\t\u0015!\u0003g\u0011\u0015Q\u0007\u0001\"\u0001l\u0011\u0015y\u0007\u0001\"\u0001q\u0011\u0015\t\b\u0001\"\u0011s\u0011\u00151\b\u0001\"\u0011x\u0011%\t\t\u0001AA\u0001\n\u0003\t\u0019\u0001C\u0005\u00020\u0001\t\n\u0011\"\u0001\u00022!I\u0011\u0011\r\u0001\u0012\u0002\u0013\u0005\u00111\r\u0005\n\u0003\u0003\u0003\u0011\u0011!C!\u0003\u0007C\u0011\"!&\u0001\u0003\u0003%\t!a&\t\u0013\u0005}\u0005!!A\u0005\u0002\u0005\u0005\u0006\"CAT\u0001\u0005\u0005I\u0011IAU\u0011%\t9\fAA\u0001\n\u0003\tI\fC\u0005\u0002D\u0002\t\t\u0011\"\u0011\u0002F\"I\u0011\u0011\u001a\u0001\u0002\u0002\u0013\u0005\u00131\u001a\u0005\n\u0003\u001b\u0004\u0011\u0011!C!\u0003\u001fD\u0011\"!5\u0001\u0003\u0003%\t%a5\b\u0013\u0005]W$!A\t\n\u0005eg\u0001\u0003\u000f\u001e\u0003\u0003EI!a7\t\r)4B\u0011AAt\u0011%\tiMFA\u0001\n\u000b\ny\rC\u0005\u0002jZ\t\t\u0011\"!\u0002l\"I!q\u0003\f\u0002\u0002\u0013\u0005%\u0011\u0004\u0005\n\u0005\u001b2\u0012\u0011!C\u0005\u0005\u001f\u0012aB\u00127bi6\u000b\u0007\u000f]3e%\u0006tGM\u0003\u0002\u001f?\u0005iA-[:ue&\u0014W\u000f^5p]NT!\u0001I\u0011\u0002\u000bM$\u0018\r^:\u000b\u0003\t\naA\u0019:fKj,7\u0001A\u000b\u0004Kq\u00134#\u0002\u0001'Y9\u000b\u0006CA\u0014+\u001b\u0005A#\"A\u0015\u0002\u000bM\u001c\u0017\r\\1\n\u0005-B#AB!osJ+g\rE\u0002.]Aj\u0011!H\u0005\u0003_u\u0011AAU1oIB\u0011\u0011G\r\u0007\u0001\t%\u0019\u0004\u0001)A\u0001\u0002\u000b\u0007AGA\u0001V#\t)\u0004\b\u0005\u0002(m%\u0011q\u0007\u000b\u0002\b\u001d>$\b.\u001b8h!\t9\u0013(\u0003\u0002;Q\t\u0019\u0011I\\=)\tIbt(\u0013\t\u0003OuJ!A\u0010\u0015\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\u0006G\u0001\u000b5I\u0011\b\u0003O\u0005K!A\u0011\u0015\u0002\u0007%sG/\r\u0003%\t\"KcBA#I\u001b\u00051%BA$$\u0003\u0019a$o\\8u}%\t\u0011&M\u0003$\u0015.kEJ\u0004\u0002(\u0017&\u0011A\nK\u0001\u0007\t>,(\r\\32\t\u0011\"\u0005*\u000b\t\u0003O=K!\u0001\u0015\u0015\u0003\u000fA\u0013x\u000eZ;diB\u0011!+\u0016\b\u0003\tNK!\u0001\u0016\u0015\u0002\u000fA\f7m[1hK&\u0011ak\u0016\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003)\"\nAA]1oIV\t!\fE\u0002.]m\u0003\"!\r/\u0005\u0013u\u0003\u0001\u0015!A\u0001\u0006\u0004!$!\u0001+)\tqct,Y\u0019\u0006G\u0001\u000b\u0005MQ\u0019\u0005I\u0011C\u0015&M\u0003$\u0015.\u0013G*\r\u0003%\t\"K\u0013!\u0002:b]\u0012\u0004\u0013\u0001\u00024v]\u000e,\u0012A\u001a\t\u0005O\u001d\\F&\u0003\u0002iQ\tIa)\u001e8di&|g.M\u0001\u0006MVt7\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00071lg\u000e\u0005\u0003.\u0001m\u0003\u0004\"\u0002-\u0006\u0001\u0004Q\u0006\"\u00023\u0006\u0001\u00041\u0017\u0001\u00023sC^$\u0012\u0001M\u0001\bIJ\fwo\u00149u)\u0005\u0019\bcA\u0014ua%\u0011Q\u000f\u000b\u0002\u0007\u001fB$\u0018n\u001c8\u0002\u000f\u0019d\u0017\r^'baV\u0011\u0001p\u001f\u000b\u0003sv\u00042!\f\u0018{!\t\t4\u0010B\u0003}\u0011\t\u0007AGA\u0001F\u0011\u0015q\b\u00021\u0001\u0000\u0003\u00051\u0007\u0003B\u0014hae\fAaY8qsV1\u0011QAA\u0006\u00033!b!a\u0002\u0002&\u0005%\u0002CB\u0017\u0001\u0003\u0013\t9\u0002E\u00022\u0003\u0017!\u0011\"X\u0005!\u0002\u0003\u0005)\u0019\u0001\u001b)\u000f\u0005-A(a\u0004\u0002\u0014E21\u0005Q!\u0002\u0012\t\u000bD\u0001\n#ISE21ES&\u0002\u00161\u000bD\u0001\n#ISA\u0019\u0011'!\u0007\u0005\u0013MJ\u0001\u0015!A\u0001\u0006\u0004!\u0004fBA\ry\u0005u\u0011\u0011E\u0019\u0007G\u0001\u000b\u0015q\u0004\"2\t\u0011\"\u0005*K\u0019\u0007G)[\u00151\u0005'2\t\u0011\"\u0005*\u000b\u0005\t1&\u0001\n\u00111\u0001\u0002(A!QFLA\u0005\u0011!!\u0017\u0002%AA\u0002\u0005-\u0002CB\u0014h\u0003\u0013\ti\u0003\u0005\u0003.]\u0005]\u0011AD2paf$C-\u001a4bk2$H%M\u000b\u0007\u0003g\tI%!\u0016\u0016\u0005\u0005U\"f\u0001.\u00028-\u0012\u0011\u0011\b\t\u0005\u0003w\t)%\u0004\u0002\u0002>)!\u0011qHA!\u0003%)hn\u00195fG.,GMC\u0002\u0002D!\n!\"\u00198o_R\fG/[8o\u0013\u0011\t9%!\u0010\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\rB\u0005^\u0015\u0001\u0006\t\u0011!b\u0001i!:\u0011\u0011\n\u001f\u0002N\u0005E\u0013GB\u0012A\u0003\u0006=#)\r\u0003%\t\"K\u0013GB\u0012K\u0017\u0006MC*\r\u0003%\t\"KC!C\u001a\u000bA\u0003\u0005\tQ1\u00015Q\u001d\t)\u0006PA-\u0003;\nda\t!B\u00037\u0012\u0015\u0007\u0002\u0013E\u0011&\nda\t&L\u0003?b\u0015\u0007\u0002\u0013E\u0011&\nabY8qs\u0012\"WMZ1vYR$#'\u0006\u0004\u0002f\u0005%\u0014QO\u000b\u0003\u0003OR3AZA\u001c\t%i6\u0002)A\u0001\u0002\u000b\u0007A\u0007K\u0004\u0002jq\ni'!\u001d2\r\r\u0002\u0015)a\u001cCc\u0011!C\tS\u00152\r\rR5*a\u001dMc\u0011!C\tS\u0015\u0005\u0013MZ\u0001\u0015!A\u0001\u0006\u0004!\u0004fBA;y\u0005e\u0014QP\u0019\u0007G\u0001\u000b\u00151\u0010\"2\t\u0011\"\u0005*K\u0019\u0007G)[\u0015q\u0010'2\t\u0011\"\u0005*K\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005\u0015\u0005\u0003BAD\u0003#k!!!#\u000b\t\u0005-\u0015QR\u0001\u0005Y\u0006twM\u0003\u0002\u0002\u0010\u0006!!.\u0019<b\u0013\u0011\t\u0019*!#\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\t\tI\nE\u0002(\u00037K1!!()\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\rA\u00141\u0015\u0005\n\u0003Ks\u0011\u0011!a\u0001\u00033\u000b1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAAV!\u0015\ti+a-9\u001b\t\tyKC\u0002\u00022\"\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\t),a,\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003w\u000b\t\rE\u0002(\u0003{K1!a0)\u0005\u001d\u0011un\u001c7fC:D\u0001\"!*\u0011\u0003\u0003\u0005\r\u0001O\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0002\u0006\u0006\u001d\u0007\"CAS#\u0005\u0005\t\u0019AAM\u0003!A\u0017m\u001d5D_\u0012,GCAAM\u0003!!xn\u0015;sS:<GCAAC\u0003\u0019)\u0017/^1mgR!\u00111XAk\u0011!\t)\u000bFA\u0001\u0002\u0004A\u0014A\u0004$mCRl\u0015\r\u001d9fIJ\u000bg\u000e\u001a\t\u0003[Y\u0019BA\u0006\u0014\u0002^B!\u0011q\\As\u001b\t\t\tO\u0003\u0003\u0002d\u00065\u0015AA5p\u0013\r1\u0016\u0011\u001d\u000b\u0003\u00033\fQ!\u00199qYf,b!!<\u0002t\n\u0005ACBAx\u0005\u001b\u0011\t\u0002\u0005\u0004.\u0001\u0005E\u0018q \t\u0004c\u0005MH!C/\u001aA\u0003\u0005\tQ1\u00015Q\u001d\t\u0019\u0010PA|\u0003w\fda\t!B\u0003s\u0014\u0015\u0007\u0002\u0013E\u0011&\nda\t&L\u0003{d\u0015\u0007\u0002\u0013E\u0011&\u00022!\rB\u0001\t%\u0019\u0014\u0004)A\u0001\u0002\u000b\u0007A\u0007K\u0004\u0003\u0002q\u0012)A!\u00032\r\r\u0002\u0015Ia\u0002Cc\u0011!C\tS\u00152\r\rR5Ja\u0003Mc\u0011!C\tS\u0015\t\raK\u0002\u0019\u0001B\b!\u0011ic&!=\t\r\u0011L\u0002\u0019\u0001B\n!\u00199s-!=\u0003\u0016A!QFLA\u0000\u0003\u001d)h.\u00199qYf,bAa\u0007\u0003*\tmB\u0003\u0002B\u000f\u0005\u000f\u0002Ba\n;\u0003 A9qE!\t\u0003&\tU\u0012b\u0001B\u0012Q\t1A+\u001e9mKJ\u0002B!\f\u0018\u0003(A\u0019\u0011G!\u000b\u0005\u0013uS\u0002\u0015!A\u0001\u0006\u0004!\u0004f\u0002B\u0015y\t5\"\u0011G\u0019\u0007G\u0001\u000b%q\u0006\"2\t\u0011\"\u0005*K\u0019\u0007G)[%1\u0007'2\t\u0011\"\u0005*\u000b\t\u0007O\u001d\u00149Ca\u000e\u0011\t5r#\u0011\b\t\u0004c\tmB!C\u001a\u001bA\u0003\u0005\tQ1\u00015Q\u001d\u0011Y\u0004\u0010B \u0005\u0007\nda\t!B\u0005\u0003\u0012\u0015\u0007\u0002\u0013E\u0011&\nda\t&L\u0005\u000bb\u0015\u0007\u0002\u0013E\u0011&B\u0011B!\u0013\u001b\u0003\u0003\u0005\rAa\u0013\u0002\u0007a$\u0003\u0007\u0005\u0004.\u0001\t\u001d\"\u0011H\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0005#\u0002B!a\"\u0003T%!!QKAE\u0005\u0019y%M[3di\u0002"
)
public class FlatMappedRand implements Rand, Product {
   public final Rand rand;
   public final Function1 func;

   public static Option unapply(final FlatMappedRand x$0) {
      return FlatMappedRand$.MODULE$.unapply(x$0);
   }

   public static FlatMappedRand apply(final Rand rand, final Function1 func) {
      return FlatMappedRand$.MODULE$.apply(rand, func);
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

   public Rand rand() {
      return this.rand;
   }

   public Function1 func() {
      return this.func;
   }

   public Object draw() {
      return ((Rand)this.func().apply(this.rand().draw())).draw();
   }

   public Option drawOpt() {
      return this.rand().drawOpt().flatMap((x) -> ((Rand)this.func().apply(x)).drawOpt());
   }

   public Rand flatMap(final Function1 f) {
      return new FlatMappedRand(this.rand(), (x) -> (Rand)f.apply(((Rand)this.func().apply(x)).draw()));
   }

   public FlatMappedRand copy(final Rand rand, final Function1 func) {
      return new FlatMappedRand(rand, func);
   }

   public Rand copy$default$1() {
      return this.rand();
   }

   public Function1 copy$default$2() {
      return this.func();
   }

   public String productPrefix() {
      return "FlatMappedRand";
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
      return x$1 instanceof FlatMappedRand;
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
            if (x$1 instanceof FlatMappedRand) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label43: {
                  label42: {
                     label41: {
                        FlatMappedRand var4 = (FlatMappedRand)x$1;
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

   public Rand flatMap$mcD$sp(final Function1 f) {
      return this.flatMap(f);
   }

   public Rand flatMap$mcI$sp(final Function1 f) {
      return this.flatMap(f);
   }

   public FlatMappedRand copy$mDDc$sp(final Rand rand, final Function1 func) {
      return new FlatMappedRand$mcDD$sp(rand, func);
   }

   public FlatMappedRand copy$mDIc$sp(final Rand rand, final Function1 func) {
      return new FlatMappedRand$mcDI$sp(rand, func);
   }

   public FlatMappedRand copy$mIDc$sp(final Rand rand, final Function1 func) {
      return new FlatMappedRand$mcID$sp(rand, func);
   }

   public FlatMappedRand copy$mIIc$sp(final Rand rand, final Function1 func) {
      return new FlatMappedRand$mcII$sp(rand, func);
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

   public FlatMappedRand(final Rand rand, final Function1 func) {
      this.rand = rand;
      this.func = func;
      Rand.$init$(this);
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
