package breeze.stats.mcmc;

import breeze.linalg.DenseVector;
import breeze.stats.distributions.Rand;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
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
   bytes = "\u0006\u0005\teb\u0001B\u0017/\u0001VB\u0001\"\u0018\u0001\u0003\u0016\u0004%\tA\u0018\u0005\t?\u0002\u0011\t\u0012)A\u0005{!A\u0001\r\u0001BK\u0002\u0013\u0005\u0011\r\u0003\u0005f\u0001\tE\t\u0015!\u0003c\u0011!1\u0007A!A!\u0002\u00179\u0007\"B7\u0001\t\u0003q\u0007bB;\u0001\u0005\u0004%IA\u001e\u0005\b\u0003\u0013\u0001\u0001\u0015!\u0003x\u0011!\tY\u0001\u0001b\u0001\n\u00131\bbBA\u0007\u0001\u0001\u0006Ia\u001e\u0005\n\u0003\u001f\u0001!\u0019!C\u0005\u0003#A\u0001\"a\b\u0001A\u0003%\u00111\u0003\u0005\n\u0003C\u0001\u0001\u0019!C\u0005\u0003GA\u0011\"!\n\u0001\u0001\u0004%I!a\n\t\u0011\u0005M\u0002\u0001)Q\u0005\u0003\u0007A\u0001\"!\u000e\u0001\u0001\u0004%I!\u0019\u0005\n\u0003o\u0001\u0001\u0019!C\u0005\u0003sAq!!\u0010\u0001A\u0003&!\rC\u0004\u0002@\u0001!\t!!\u0011\t\u000f\u0005\r\u0003\u0001\"\u0001\u0002F!I\u0011q\t\u0001\u0002\u0002\u0013\u0005\u0011\u0011\n\u0005\n\u0003?\u0002\u0011\u0013!C\u0001\u0003CB\u0011\"a\u001f\u0001#\u0003%\t!! \t\u0013\u0005\u0015\u0005\u00011A\u0005\n\u0005\u001d\u0005\"CAH\u0001\u0001\u0007I\u0011BAI\u0011!\t)\n\u0001Q!\n\u0005%\u0005\"CAP\u0001\u0005\u0005I\u0011IAQ\u0011!\tI\u000bAA\u0001\n\u0003\t\u0007\"CAV\u0001\u0005\u0005I\u0011AAW\u0011%\t\t\fAA\u0001\n\u0003\n\u0019\fC\u0005\u0002B\u0002\t\t\u0011\"\u0001\u0002D\"I\u0011q\u0019\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u001a\u0005\n\u0003\u001b\u0004\u0011\u0011!C!\u0003\u001fD\u0011\"!5\u0001\u0003\u0003%\t%a5\t\u0013\u0005U\u0007!!A\u0005B\u0005]w!CAn]\u0005\u0005\t\u0012AAo\r!ic&!A\t\u0002\u0005}\u0007BB7&\t\u0003\tY\u000fC\u0005\u0002R\u0016\n\t\u0011\"\u0012\u0002T\"I\u0011Q^\u0013\u0002\u0002\u0013\u0005\u0015q\u001e\u0005\n\u0005\u000b)\u0013\u0013!C\u0001\u0005\u000fA\u0011Ba\u0003&\u0003\u0003%\tI!\u0004\t\u0013\t%R%%A\u0005\u0002\t-\u0002\"\u0003B\u0018K\u0005\u0005I\u0011\u0002B\u0019\u0005Q!\u0006N]3bI\u0016$')\u001e4gKJ,GMU1oI*\u0011q\u0006M\u0001\u0005[\u000el7M\u0003\u00022e\u0005)1\u000f^1ug*\t1'\u0001\u0004ce\u0016,'0Z\u0002\u0001+\t1TiE\u0003\u0001our\u0015\u000b\u0005\u00029w5\t\u0011HC\u0001;\u0003\u0015\u00198-\u00197b\u0013\ta\u0014H\u0001\u0004B]f\u0014VM\u001a\t\u0004}\u0005\u001bU\"A \u000b\u0005\u0001\u0003\u0014!\u00043jgR\u0014\u0018NY;uS>t7/\u0003\u0002C\u007f\t!!+\u00198e!\t!U\t\u0004\u0001\u0005\u000b\u0019\u0003!\u0019A$\u0003\u0003Q\u000b\"\u0001S&\u0011\u0005aJ\u0015B\u0001&:\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\u000f'\n\u00055K$aA!osB\u0011\u0001hT\u0005\u0003!f\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002S5:\u00111\u000b\u0017\b\u0003)^k\u0011!\u0016\u0006\u0003-R\na\u0001\u0010:p_Rt\u0014\"\u0001\u001e\n\u0005eK\u0014a\u00029bG.\fw-Z\u0005\u00037r\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!!W\u001d\u0002\u000f]\u0014\u0018\r\u001d9fIV\tQ(\u0001\u0005xe\u0006\u0004\b/\u001a3!\u0003)\u0011WO\u001a4feNK'0Z\u000b\u0002EB\u0011\u0001hY\u0005\u0003If\u00121!\u00138u\u0003-\u0011WO\u001a4feNK'0\u001a\u0011\u0002\u00035\u00042\u0001[6D\u001b\u0005I'B\u00016:\u0003\u001d\u0011XM\u001a7fGRL!\u0001\\5\u0003\u0011\rc\u0017m]:UC\u001e\fa\u0001P5oSRtDcA8tiR\u0011\u0001O\u001d\t\u0004c\u0002\u0019U\"\u0001\u0018\t\u000b\u00194\u00019A4\t\u000bu3\u0001\u0019A\u001f\t\u000f\u00014\u0001\u0013!a\u0001E\u0006qQo]3e\u0003J\u0014\u0018-_)vKV,W#A<\u0011\ta|\u00181A\u0007\u0002s*\u0011!p_\u0001\u000bG>t7-\u001e:sK:$(B\u0001?~\u0003\u0011)H/\u001b7\u000b\u0003y\fAA[1wC&\u0019\u0011\u0011A=\u0003'1Kgn[3e\u00052|7m[5oOF+X-^3\u0011\ta\n)aQ\u0005\u0004\u0003\u000fI$!B!se\u0006L\u0018aD;tK\u0012\f%O]1z#V,W/\u001a\u0011\u0002\u001b9,w/\u0011:sCf\fV/Z;f\u00039qWm^!se\u0006L\u0018+^3vK\u0002\naa^8sW\u0016\u0014XCAA\n!\u0011\t)\"a\u0007\u000e\u0005\u0005]!bAA\r{\u0006!A.\u00198h\u0013\u0011\ti\"a\u0006\u0003\rQC'/Z1e\u0003\u001d9xN]6fe\u0002\naAY;gM\u0016\u0014XCAA\u0002\u0003)\u0011WO\u001a4fe~#S-\u001d\u000b\u0005\u0003S\ty\u0003E\u00029\u0003WI1!!\f:\u0005\u0011)f.\u001b;\t\u0013\u0005Eb\"!AA\u0002\u0005\r\u0011a\u0001=%c\u00059!-\u001e4gKJ\u0004\u0013\u0001\u00039pg&$\u0018n\u001c8\u0002\u0019A|7/\u001b;j_:|F%Z9\u0015\t\u0005%\u00121\b\u0005\t\u0003c\t\u0012\u0011!a\u0001E\u0006I\u0001o\\:ji&|g\u000eI\u0001\u0005gR|\u0007\u000f\u0006\u0002\u0002*\u0005!AM]1x)\u0005\u0019\u0015\u0001B2paf,B!a\u0013\u0002TQ1\u0011QJA-\u0003;\"B!a\u0014\u0002VA!\u0011\u000fAA)!\r!\u00151\u000b\u0003\u0006\rV\u0011\ra\u0012\u0005\u0007MV\u0001\u001d!a\u0016\u0011\t!\\\u0017\u0011\u000b\u0005\t;V\u0001\n\u00111\u0001\u0002\\A!a(QA)\u0011\u001d\u0001W\u0003%AA\u0002\t\fabY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0003\u0002d\u0005eTCAA3U\ri\u0014qM\u0016\u0003\u0003S\u0002B!a\u001b\u0002v5\u0011\u0011Q\u000e\u0006\u0005\u0003_\n\t(A\u0005v]\u000eDWmY6fI*\u0019\u00111O\u001d\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002x\u00055$!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0012)aI\u0006b\u0001\u000f\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T\u0003BA@\u0003\u0007+\"!!!+\u0007\t\f9\u0007B\u0003G/\t\u0007q)\u0001\u0006ti>\u0004xk\u001c:lKJ,\"!!#\u0011\u0007a\nY)C\u0002\u0002\u000ef\u0012qAQ8pY\u0016\fg.\u0001\bti>\u0004xk\u001c:lKJ|F%Z9\u0015\t\u0005%\u00121\u0013\u0005\n\u0003cI\u0012\u0011!a\u0001\u0003\u0013\u000b1b\u001d;pa^{'o[3sA!\u001a!$!'\u0011\u0007a\nY*C\u0002\u0002\u001ef\u0012\u0001B^8mCRLG.Z\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005\r\u0006\u0003BA\u000b\u0003KKA!a*\u0002\u0018\t11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000fF\u0002L\u0003_C\u0001\"!\r\u001e\u0003\u0003\u0005\rAY\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011Q\u0017\t\u0006\u0003o\u000bilS\u0007\u0003\u0003sS1!a/:\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003\u007f\u000bIL\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BAE\u0003\u000bD\u0001\"!\r \u0003\u0003\u0005\raS\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0002$\u0006-\u0007\u0002CA\u0019A\u0005\u0005\t\u0019\u00012\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012AY\u0001\ti>\u001cFO]5oOR\u0011\u00111U\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005%\u0015\u0011\u001c\u0005\t\u0003c\u0019\u0013\u0011!a\u0001\u0017\u0006!B\u000b\u001b:fC\u0012,GMQ;gM\u0016\u0014X\r\u001a*b]\u0012\u0004\"!]\u0013\u0014\t\u0015:\u0014\u0011\u001d\t\u0005\u0003G\fI/\u0004\u0002\u0002f*\u0019\u0011q]?\u0002\u0005%|\u0017bA.\u0002fR\u0011\u0011Q\\\u0001\u0006CB\u0004H._\u000b\u0005\u0003c\fI\u0010\u0006\u0004\u0002t\u0006}(1\u0001\u000b\u0005\u0003k\fY\u0010\u0005\u0003r\u0001\u0005]\bc\u0001#\u0002z\u0012)a\t\u000bb\u0001\u000f\"1a\r\u000ba\u0002\u0003{\u0004B\u0001[6\u0002x\"1Q\f\u000ba\u0001\u0005\u0003\u0001BAP!\u0002x\"9\u0001\r\u000bI\u0001\u0002\u0004\u0011\u0017aD1qa2LH\u0005Z3gCVdG\u000f\n\u001a\u0016\t\u0005}$\u0011\u0002\u0003\u0006\r&\u0012\raR\u0001\bk:\f\u0007\u000f\u001d7z+\u0011\u0011yA!\t\u0015\t\tE!1\u0005\t\u0006q\tM!qC\u0005\u0004\u0005+I$AB(qi&|g\u000e\u0005\u00049\u00053\u0011iBY\u0005\u0004\u00057I$A\u0002+va2,'\u0007\u0005\u0003?\u0003\n}\u0001c\u0001#\u0003\"\u0011)aI\u000bb\u0001\u000f\"I!Q\u0005\u0016\u0002\u0002\u0003\u0007!qE\u0001\u0004q\u0012\u0002\u0004\u0003B9\u0001\u0005?\t1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u0012T\u0003BA@\u0005[!QAR\u0016C\u0002\u001d\u000bAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"Aa\r\u0011\t\u0005U!QG\u0005\u0005\u0005o\t9B\u0001\u0004PE*,7\r\u001e"
)
public class ThreadedBufferedRand implements Rand, Product {
   private final Rand wrapped;
   private final int bufferSize;
   private final LinkedBlockingQueue breeze$stats$mcmc$ThreadedBufferedRand$$usedArrayQueue;
   private final LinkedBlockingQueue breeze$stats$mcmc$ThreadedBufferedRand$$newArrayQueue;
   private final Thread worker;
   private Object buffer;
   private int position;
   private volatile boolean breeze$stats$mcmc$ThreadedBufferedRand$$stopWorker;

   public static int $lessinit$greater$default$2() {
      return ThreadedBufferedRand$.MODULE$.$lessinit$greater$default$2();
   }

   public static Option unapply(final ThreadedBufferedRand x$0) {
      return ThreadedBufferedRand$.MODULE$.unapply(x$0);
   }

   public static int apply$default$2() {
      return ThreadedBufferedRand$.MODULE$.apply$default$2();
   }

   public static ThreadedBufferedRand apply(final Rand wrapped, final int bufferSize, final ClassTag m) {
      return ThreadedBufferedRand$.MODULE$.apply(wrapped, bufferSize, m);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public double draw$mcD$sp() {
      return Rand.draw$mcD$sp$(this);
   }

   public int draw$mcI$sp() {
      return Rand.draw$mcI$sp$(this);
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

   public Rand wrapped() {
      return this.wrapped;
   }

   public int bufferSize() {
      return this.bufferSize;
   }

   public LinkedBlockingQueue breeze$stats$mcmc$ThreadedBufferedRand$$usedArrayQueue() {
      return this.breeze$stats$mcmc$ThreadedBufferedRand$$usedArrayQueue;
   }

   public LinkedBlockingQueue breeze$stats$mcmc$ThreadedBufferedRand$$newArrayQueue() {
      return this.breeze$stats$mcmc$ThreadedBufferedRand$$newArrayQueue;
   }

   public boolean breeze$stats$mcmc$ThreadedBufferedRand$$stopWorker() {
      return this.breeze$stats$mcmc$ThreadedBufferedRand$$stopWorker;
   }

   private void stopWorker_$eq(final boolean x$1) {
      this.breeze$stats$mcmc$ThreadedBufferedRand$$stopWorker = x$1;
   }

   private Thread worker() {
      return this.worker;
   }

   private Object buffer() {
      return this.buffer;
   }

   private void buffer_$eq(final Object x$1) {
      this.buffer = x$1;
   }

   private int position() {
      return this.position;
   }

   private void position_$eq(final int x$1) {
      this.position = x$1;
   }

   public void stop() {
      this.stopWorker_$eq(true);
   }

   public Object draw() {
      Object var10000;
      if (this.position() < this.bufferSize()) {
         this.position_$eq(this.position() + 1);
         var10000 = .MODULE$.array_apply(this.buffer(), this.position() - 1);
      } else {
         this.breeze$stats$mcmc$ThreadedBufferedRand$$usedArrayQueue().put(this.buffer());
         this.buffer_$eq(this.breeze$stats$mcmc$ThreadedBufferedRand$$newArrayQueue().take());
         this.position_$eq(1);
         var10000 = .MODULE$.array_apply(this.buffer(), 0);
      }

      return var10000;
   }

   public ThreadedBufferedRand copy(final Rand wrapped, final int bufferSize, final ClassTag m) {
      return new ThreadedBufferedRand(wrapped, bufferSize, m);
   }

   public Rand copy$default$1() {
      return this.wrapped();
   }

   public int copy$default$2() {
      return this.bufferSize();
   }

   public String productPrefix() {
      return "ThreadedBufferedRand";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.wrapped();
            break;
         case 1:
            var10000 = BoxesRunTime.boxToInteger(this.bufferSize());
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
      return x$1 instanceof ThreadedBufferedRand;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "wrapped";
            break;
         case 1:
            var10000 = "bufferSize";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.wrapped()));
      var1 = Statics.mix(var1, this.bufferSize());
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var7;
      if (this != x$1) {
         label55: {
            boolean var2;
            if (x$1 instanceof ThreadedBufferedRand) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label38: {
                  ThreadedBufferedRand var4 = (ThreadedBufferedRand)x$1;
                  if (this.bufferSize() == var4.bufferSize()) {
                     label36: {
                        Rand var10000 = this.wrapped();
                        Rand var5 = var4.wrapped();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label36;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label36;
                        }

                        if (var4.canEqual(this)) {
                           var7 = true;
                           break label38;
                        }
                     }
                  }

                  var7 = false;
               }

               if (var7) {
                  break label55;
               }
            }

            var7 = false;
            return var7;
         }
      }

      var7 = true;
      return var7;
   }

   public ThreadedBufferedRand(final Rand wrapped, final int bufferSize, final ClassTag m) {
      this.wrapped = wrapped;
      this.bufferSize = bufferSize;
      Rand.$init$(this);
      Product.$init$(this);
      scala.Predef..MODULE$.require(bufferSize > 0);
      this.breeze$stats$mcmc$ThreadedBufferedRand$$usedArrayQueue = new LinkedBlockingQueue(2);
      this.breeze$stats$mcmc$ThreadedBufferedRand$$newArrayQueue = new LinkedBlockingQueue(2);
      this.breeze$stats$mcmc$ThreadedBufferedRand$$usedArrayQueue().put(m.newArray(bufferSize));
      this.breeze$stats$mcmc$ThreadedBufferedRand$$usedArrayQueue().put(m.newArray(bufferSize));
      this.breeze$stats$mcmc$ThreadedBufferedRand$$stopWorker = false;
      this.worker = new Thread() {
         // $FF: synthetic field
         private final ThreadedBufferedRand $outer;

         public void run() {
            while(!this.$outer.breeze$stats$mcmc$ThreadedBufferedRand$$stopWorker()) {
               Object buff = this.$outer.breeze$stats$mcmc$ThreadedBufferedRand$$usedArrayQueue().poll(1L, TimeUnit.SECONDS);
               if (buff != null) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = this.$outer.bufferSize(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     .MODULE$.array_update(buff, index$macro$2, this.$outer.wrapped().draw());
                  }

                  this.$outer.breeze$stats$mcmc$ThreadedBufferedRand$$newArrayQueue().put(buff);
               }
            }

         }

         public {
            if (ThreadedBufferedRand.this == null) {
               throw null;
            } else {
               this.$outer = ThreadedBufferedRand.this;
            }
         }
      };
      this.worker().setDaemon(true);
      this.worker().setName((new StringBuilder(18)).append("worker thread for ").append(this).toString());
      this.worker().start();
      this.buffer = this.breeze$stats$mcmc$ThreadedBufferedRand$$newArrayQueue().take();
      this.position = 0;
   }
}
