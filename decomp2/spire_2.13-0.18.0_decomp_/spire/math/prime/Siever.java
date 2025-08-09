package spire.math.prime;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.StringOps.;
import scala.collection.immutable.LazyList;
import scala.collection.immutable.Stream;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import spire.math.SafeLong;
import spire.math.SafeLong$;

@ScalaSignature(
   bytes = "\u0006\u0005\tmb\u0001B\u00181\u0001^B\u0001\"\u0014\u0001\u0003\u0016\u0004%\tA\u0014\u0005\t%\u0002\u0011\t\u0012)A\u0005\u001f\"A1\u000b\u0001BK\u0002\u0013\u0005A\u000b\u0003\u0005Z\u0001\tE\t\u0015!\u0003V\u0011\u0015Q\u0006\u0001\"\u0001\\\u0011\u001d\u0001\u0007A1A\u0005\u0002\u0005Da!\u001a\u0001!\u0002\u0013\u0011\u0007b\u00024\u0001\u0001\u0004%\t\u0001\u0016\u0005\bO\u0002\u0001\r\u0011\"\u0001i\u0011\u0019q\u0007\u0001)Q\u0005+\"9q\u000e\u0001a\u0001\n\u0003!\u0006b\u00029\u0001\u0001\u0004%\t!\u001d\u0005\u0007g\u0002\u0001\u000b\u0015B+\t\u000fQ\u0004!\u0019!C\u0001k\"1Q\u0010\u0001Q\u0001\nYDqA \u0001C\u0002\u0013\u0005q\u0010\u0003\u0005\u0002\b\u0001\u0001\u000b\u0011BA\u0001\u0011%\tI\u0001\u0001a\u0001\n\u0003\tY\u0001C\u0005\u0002\u0014\u0001\u0001\r\u0011\"\u0001\u0002\u0016!A\u0011\u0011\u0004\u0001!B\u0013\ti\u0001C\u0004\u0002\u001c\u0001!\t!!\b\t\u000f\u0005\r\u0002\u0001\"\u0001\u0002&!9\u0011q\u0006\u0001\u0005\n\u0005E\u0002bBA\u001a\u0001\u0011\u0005\u0011Q\u0007\u0005\b\u0003s\u0001A\u0011AA\u001e\u0011\u001d\t\t\u0006\u0001C\u0001\u0003'Bq!!\u001d\u0001\t\u0003\t\u0019\bC\u0005\u0002\u0004\u0002\t\t\u0011\"\u0001\u0002\u0006\"I\u00111\u0012\u0001\u0012\u0002\u0013\u0005\u0011Q\u0012\u0005\n\u0003G\u0003\u0011\u0013!C\u0001\u0003KC\u0011\"!+\u0001\u0003\u0003%\t%a+\t\u0011\u0005u\u0006!!A\u0005\u00029C\u0011\"a0\u0001\u0003\u0003%\t!!1\t\u0013\u0005-\u0007!!A\u0005B\u00055\u0007\"CAl\u0001\u0005\u0005I\u0011AAm\u0011%\t\u0019\u000fAA\u0001\n\u0003\n)\u000fC\u0005\u0002j\u0002\t\t\u0011\"\u0011\u0002l\"I\u0011Q\u001e\u0001\u0002\u0002\u0013\u0005\u0013q\u001e\u0005\n\u0003c\u0004\u0011\u0011!C!\u0003g<\u0011\"a>1\u0003\u0003E\t!!?\u0007\u0011=\u0002\u0014\u0011!E\u0001\u0003wDaAW\u0015\u0005\u0002\tM\u0001\"CAwS\u0005\u0005IQIAx\u0011%\u0011)\"KA\u0001\n\u0003\u00139\u0002C\u0005\u0003\u001e%\n\t\u0011\"!\u0003 !I!\u0011G\u0015\u0002\u0002\u0013%!1\u0007\u0002\u0007'&,g/\u001a:\u000b\u0005E\u0012\u0014!\u00029sS6,'BA\u001a5\u0003\u0011i\u0017\r\u001e5\u000b\u0003U\nQa\u001d9je\u0016\u001c\u0001a\u0005\u0003\u0001qy\n\u0005CA\u001d=\u001b\u0005Q$\"A\u001e\u0002\u000bM\u001c\u0017\r\\1\n\u0005uR$AB!osJ+g\r\u0005\u0002:\u007f%\u0011\u0001I\u000f\u0002\b!J|G-^2u!\t\u0011%J\u0004\u0002D\u0011:\u0011AiR\u0007\u0002\u000b*\u0011aIN\u0001\u0007yI|w\u000e\u001e \n\u0003mJ!!\u0013\u001e\u0002\u000fA\f7m[1hK&\u00111\n\u0014\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003\u0013j\n\u0011b\u00195v].\u001c\u0016N_3\u0016\u0003=\u0003\"!\u000f)\n\u0005ES$aA%oi\u0006Q1\r[;oWNK'0\u001a\u0011\u0002\r\r,Ho\u001c4g+\u0005)\u0006C\u0001,X\u001b\u0005\u0011\u0014B\u0001-3\u0005!\u0019\u0016MZ3M_:<\u0017aB2vi>4g\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007qsv\f\u0005\u0002^\u00015\t\u0001\u0007C\u0003N\u000b\u0001\u0007q\nC\u0003T\u000b\u0001\u0007Q+A\u0002beJ,\u0012A\u0019\t\u0003;\u000eL!\u0001\u001a\u0019\u0003\r\tKGoU3u\u0003\u0011\t'O\u001d\u0011\u0002\u000bM$\u0018M\u001d;\u0002\u0013M$\u0018M\u001d;`I\u0015\fHCA5m!\tI$.\u0003\u0002lu\t!QK\\5u\u0011\u001di\u0017\"!AA\u0002U\u000b1\u0001\u001f\u00132\u0003\u0019\u0019H/\u0019:uA\u0005)A.[7ji\u0006IA.[7ji~#S-\u001d\u000b\u0003SJDq!\u001c\u0007\u0002\u0002\u0003\u0007Q+\u0001\u0004mS6LG\u000fI\u0001\u0006M\u0006\u001cH/]\u000b\u0002mB\u0011qO\u001f\b\u0003;bL!!\u001f\u0019\u0002\u0013MKWM^3Vi&d\u0017BA>}\u0005-1\u0015m\u001d;GC\u000e$xN]:\u000b\u0005e\u0004\u0014A\u00024bgR\f\b%A\u0003tY><\u0018/\u0006\u0002\u0002\u0002A\u0019Q,a\u0001\n\u0007\u0005\u0015\u0001G\u0001\u0006GC\u000e$xN\u001d%fCB\faa\u001d7poF\u0004\u0013!B:jKZ,WCAA\u0007!\ri\u0016qB\u0005\u0004\u0003#\u0001$\u0001D*jKZ,7+Z4nK:$\u0018!C:jKZ,w\fJ3r)\rI\u0017q\u0003\u0005\t[N\t\t\u00111\u0001\u0002\u000e\u000511/[3wK\u0002\nA\u0002\\1sO\u0016\u001cHOQ3m_^$2!VA\u0010\u0011\u0019\t\t#\u0006a\u0001+\u0006\ta.A\u0002oi\"$2!VA\u0014\u0011\u001d\t\tC\u0006a\u0001\u0003S\u00012!OA\u0016\u0013\r\tiC\u000f\u0002\u0005\u0019>tw-A\u0007j]&$h*\u001a=u'&,g/\u001a\u000b\u0002S\u0006Ia.\u001a=u\u0003\u001a$XM\u001d\u000b\u0004+\u0006]\u0002BBA\u00111\u0001\u0007Q+A\u0007mCjLH*[:u\u0003\u001a$XM\u001d\u000b\u0005\u0003{\ti\u0005E\u0003\u0002@\u0005%S+\u0004\u0002\u0002B)!\u00111IA#\u0003%IW.\\;uC\ndWMC\u0002\u0002Hi\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\tY%!\u0011\u0003\u00111\u000b'0\u001f'jgRDa!a\u0014\u001a\u0001\u0004)\u0016A\u000191\u0003-\u0019HO]3b[\u00063G/\u001a:\u0015\t\u0005U\u00131\f\t\u0005\u0005\u0006]S+C\u0002\u0002Z1\u0013aa\u0015;sK\u0006l\u0007BBA(5\u0001\u0007Q\u000bK\u0006\u001b\u0003?\n)'a\u001a\u0002l\u00055\u0004cA\u001d\u0002b%\u0019\u00111\r\u001e\u0003\u0015\u0011,\u0007O]3dCR,G-A\u0004nKN\u001c\u0018mZ3\"\u0005\u0005%\u0014!\u000b9sK\u001a,'\u000f\t'bufd\u0015n\u001d;!C:$\u0007\u0005\\1{s2K7\u000f^!gi\u0016\u0014\b%\u001b8ti\u0016\fG-A\u0003tS:\u001cW-\t\u0002\u0002p\u00051\u0001GL\u00198]A\nq!\u0019:sCf\fE\u000f\u0006\u0004\u0002v\u0005m\u0014q\u0010\t\u0005s\u0005]T+C\u0002\u0002zi\u0012Q!\u0011:sCfDa!! \u001c\u0001\u0004)\u0016!\u00019\t\r\u0005\u00055\u00041\u0001P\u0003\u0011\u0019\u0018N_3\u0002\t\r|\u0007/\u001f\u000b\u00069\u0006\u001d\u0015\u0011\u0012\u0005\b\u001br\u0001\n\u00111\u0001P\u0011\u001d\u0019F\u0004%AA\u0002U\u000babY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0002\u0010*\u001aq*!%,\u0005\u0005M\u0005\u0003BAK\u0003?k!!a&\u000b\t\u0005e\u00151T\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!(;\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003C\u000b9JA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'\u0006\u0002\u0002(*\u001aQ+!%\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\ti\u000b\u0005\u0003\u00020\u0006eVBAAY\u0015\u0011\t\u0019,!.\u0002\t1\fgn\u001a\u0006\u0003\u0003o\u000bAA[1wC&!\u00111XAY\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5us\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BAb\u0003\u0013\u00042!OAc\u0013\r\t9M\u000f\u0002\u0004\u0003:L\bbB7\"\u0003\u0003\u0005\raT\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011q\u001a\t\u0007\u0003#\f\u0019.a1\u000e\u0005\u0005\u0015\u0013\u0002BAk\u0003\u000b\u0012\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u00111\\Aq!\rI\u0014Q\\\u0005\u0004\u0003?T$a\u0002\"p_2,\u0017M\u001c\u0005\t[\u000e\n\t\u00111\u0001\u0002D\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\ti+a:\t\u000f5$\u0013\u0011!a\u0001\u001f\u0006A\u0001.Y:i\u0007>$W\rF\u0001P\u0003!!xn\u0015;sS:<GCAAW\u0003\u0019)\u0017/^1mgR!\u00111\\A{\u0011!iw%!AA\u0002\u0005\r\u0017AB*jKZ,'\u000f\u0005\u0002^SM)\u0011&!@\u0003\nA9\u0011q B\u0003\u001fVcVB\u0001B\u0001\u0015\r\u0011\u0019AO\u0001\beVtG/[7f\u0013\u0011\u00119A!\u0001\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t'\u0007\u0005\u0003\u0003\f\tEQB\u0001B\u0007\u0015\u0011\u0011y!!.\u0002\u0005%|\u0017bA&\u0003\u000eQ\u0011\u0011\u0011`\u0001\u0006CB\u0004H.\u001f\u000b\u00069\ne!1\u0004\u0005\u0006\u001b2\u0002\ra\u0014\u0005\u0006'2\u0002\r!V\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\u0011\tC!\f\u0011\u000be\u0012\u0019Ca\n\n\u0007\t\u0015\"H\u0001\u0004PaRLwN\u001c\t\u0006s\t%r*V\u0005\u0004\u0005WQ$A\u0002+va2,'\u0007\u0003\u0005\u000305\n\t\u00111\u0001]\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0005k\u0001B!a,\u00038%!!\u0011HAY\u0005\u0019y%M[3di\u0002"
)
public class Siever implements Product, Serializable {
   private final int chunkSize;
   private final SafeLong cutoff;
   private final BitSet arr;
   private SafeLong start;
   private SafeLong limit;
   private final SieveUtil.FastFactors fastq;
   private final FactorHeap slowq;
   private SieveSegment sieve;

   public static Option unapply(final Siever x$0) {
      return Siever$.MODULE$.unapply(x$0);
   }

   public static Siever apply(final int chunkSize, final SafeLong cutoff) {
      return Siever$.MODULE$.apply(chunkSize, cutoff);
   }

   public static Function1 tupled() {
      return Siever$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return Siever$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int chunkSize() {
      return this.chunkSize;
   }

   public SafeLong cutoff() {
      return this.cutoff;
   }

   public BitSet arr() {
      return this.arr;
   }

   public SafeLong start() {
      return this.start;
   }

   public void start_$eq(final SafeLong x$1) {
      this.start = x$1;
   }

   public SafeLong limit() {
      return this.limit;
   }

   public void limit_$eq(final SafeLong x$1) {
      this.limit = x$1;
   }

   public SieveUtil.FastFactors fastq() {
      return this.fastq;
   }

   public FactorHeap slowq() {
      return this.slowq;
   }

   public SieveSegment sieve() {
      return this.sieve;
   }

   public void sieve_$eq(final SieveSegment x$1) {
      this.sieve = x$1;
   }

   public SafeLong largestBelow(final SafeLong n) {
      if (n.$less(SafeLong$.MODULE$.apply(3))) {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("invalid argument: %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{n})));
      } else if (BoxesRunTime.equalsNumObject(n, BoxesRunTime.boxToInteger(3))) {
         return SafeLong$.MODULE$.apply(2);
      } else {
         int i = 3;
         SafeLong last = SafeLong$.MODULE$.apply(2);

         while(true) {
            BitSet primes = this.sieve().primes();
            int len = primes.length();
            if (n.$minus(this.start()).$less(SafeLong$.MODULE$.apply(len))) {
               int i = 1;

               for(int goal = n.$minus(this.start()).toInt(); i < goal; i += 2) {
                  if (primes.apply(i)) {
                     last = this.start().$plus((long)i);
                  }
               }

               return last;
            }

            int i;
            for(i = len - 1; 1 <= i && !primes.apply(i); i -= 2) {
            }

            if (1 <= i) {
               last = this.start().$plus((long)i);
            }

            this.initNextSieve();
            i = 1;
         }
      }
   }

   public SafeLong nth(final long n) {
      if (n == 1L) {
         return SafeLong$.MODULE$.apply(2);
      } else {
         int i = 3;
         long k = n - 1L;

         while(true) {
            BitSet primes = this.sieve().primes();

            for(int len = primes.length(); i < len; i += 2) {
               if (primes.apply(i)) {
                  --k;
                  if (k < 1L) {
                     return this.sieve().start().$plus((long)i);
                  }
               }
            }

            this.initNextSieve();
            i = 1;
         }
      }
   }

   private void initNextSieve() {
      this.start_$eq(this.start().$plus((long)this.chunkSize()));
      this.limit_$eq(this.limit().$plus((long)this.chunkSize()));
      SafeLong csq = this.cutoff().$times$times(2);
      if (this.limit().$greater$eq(csq)) {
         throw scala.sys.package..MODULE$.error(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("too big: %s > %s (%s)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.limit(), csq, this.cutoff()})));
      } else {
         this.arr().clear();
         this.sieve_$eq(new SieveSegment(this.start(), this.arr(), this.cutoff()));
         this.sieve().init(this.fastq(), this.slowq());
      }
   }

   public SafeLong nextAfter(final SafeLong n) {
      SafeLong nn;
      for(nn = this.sieve().nextAfter(n); BoxesRunTime.equalsNumObject(nn, BoxesRunTime.boxToLong(-1L)); nn = this.sieve().nextAfter(this.start().$minus(1L))) {
         this.initNextSieve();
      }

      return nn;
   }

   public LazyList lazyListAfter(final SafeLong p0) {
      SafeLong p = this.nextAfter(p0);
      return scala.collection.immutable.LazyList.Deferrer..MODULE$.$hash$colon$colon$extension(scala.collection.immutable.LazyList..MODULE$.toDeferrer(() -> this.lazyListAfter(p)), () -> p);
   }

   /** @deprecated */
   public Stream streamAfter(final SafeLong p0) {
      SafeLong p = this.nextAfter(p0);
      return scala.collection.immutable.Stream.Deferrer..MODULE$.$hash$colon$colon$extension(scala.collection.immutable.Stream..MODULE$.toDeferrer(() -> this.streamAfter(p)), p);
   }

   public SafeLong[] arrayAt(final SafeLong p, final int size) {
      SafeLong[] arr = new SafeLong[size];
      this.loop$1(0, p, arr);
      return arr;
   }

   public Siever copy(final int chunkSize, final SafeLong cutoff) {
      return new Siever(chunkSize, cutoff);
   }

   public int copy$default$1() {
      return this.chunkSize();
   }

   public SafeLong copy$default$2() {
      return this.cutoff();
   }

   public String productPrefix() {
      return "Siever";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToInteger(this.chunkSize());
            break;
         case 1:
            var10000 = this.cutoff();
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
      return x$1 instanceof Siever;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "chunkSize";
            break;
         case 1:
            var10000 = "cutoff";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.chunkSize());
      var1 = Statics.mix(var1, Statics.anyHash(this.cutoff()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label51: {
            boolean var2;
            if (x$1 instanceof Siever) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Siever var4 = (Siever)x$1;
               if (this.chunkSize() == var4.chunkSize() && BoxesRunTime.equalsNumNum(this.cutoff(), var4.cutoff()) && var4.canEqual(this)) {
                  break label51;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   private final void loop$1(final int i, final SafeLong p, final SafeLong[] arr$1) {
      while(i < arr$1.length) {
         arr$1[i] = p;
         int var10000 = i + 1;
         p = this.nextAfter(p);
         i = var10000;
      }

      BoxedUnit var4 = BoxedUnit.UNIT;
   }

   public Siever(final int chunkSize, final SafeLong cutoff) {
      this.chunkSize = chunkSize;
      this.cutoff = cutoff;
      Product.$init$(this);
      scala.Predef..MODULE$.require(chunkSize % 480 == 0, () -> "chunkSize must be a multiple of 480");
      this.arr = BitSet$.MODULE$.alloc(chunkSize);
      this.start = SafeLong$.MODULE$.apply(0);
      this.limit = this.start().$plus((long)chunkSize);
      this.fastq = SieveUtil.FastFactors$.MODULE$.empty();
      this.slowq = new FactorHeap();
      this.sieve = new SieveSegment(this.start(), this.arr(), cutoff);
      this.sieve().init(this.fastq(), this.slowq());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
