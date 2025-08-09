package spire.math.prime;

import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.Predef.;
import scala.collection.Iterator;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import spire.math.SafeLong;
import spire.math.SafeLong$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ex!\u0002\u0015*\u0011\u0003\u0001d!\u0002\u001a*\u0011\u0003\u0019\u0004\"\u0002\"\u0002\t\u0003\u0019\u0005b\u0002#\u0002\u0005\u0004%\t!\u0012\u0005\u0007\u0019\u0006\u0001\u000b\u0011\u0002$\t\u000f5\u000b\u0011\u0011!CA\u001d\"I\u00111\\\u0001\u0002\u0002\u0013\u0005\u0015Q\u001c\u0005\n\u0003_\f\u0011\u0011!C\u0005\u0003c4AAM\u0015A!\"Aq\f\u0003BK\u0002\u0013\u0005\u0001\r\u0003\u0005f\u0011\tE\t\u0015!\u0003b\u0011!1\u0007B!f\u0001\n\u00039\u0007\u0002C6\t\u0005#\u0005\u000b\u0011\u00025\t\u00111D!Q3A\u0005\u0002\u0001D\u0001\"\u001c\u0005\u0003\u0012\u0003\u0006I!\u0019\u0005\u0006\u0005\"!\tA\u001c\u0005\u0006e\"!\ta\u001d\u0005\u0006s\"!\tA\u001f\u0005\u0006y\"!\t! \u0005\b\u0003\u000bAA\u0011AA\u0004\u0011\u001d\tY\u0001\u0003C\u0001\u0003\u001bAq!!\u0005\t\t\u0003\t\u0019\u0002C\u0004\u00022!!\t!a\r\t\u000f\u0005U\u0002\u0002\"\u0003\u00028!9\u00111\b\u0005\u0005\n\u0005u\u0002bBA+\u0011\u0011\u0005\u0011q\u000b\u0005\b\u0003;BA\u0011AA0\u0011%\t\u0019\u0007CA\u0001\n\u0003\t)\u0007C\u0005\u0002n!\t\n\u0011\"\u0001\u0002p!I\u0011\u0011\u0011\u0005\u0012\u0002\u0013\u0005\u00111\u0011\u0005\n\u0003\u000fC\u0011\u0013!C\u0001\u0003_B\u0011\"!#\t\u0003\u0003%\t%a#\t\u0013\u0005e\u0005\"!A\u0005\u0002\u0005m\u0005\"CAO\u0011\u0005\u0005I\u0011AAP\u0011%\tY\u000bCA\u0001\n\u0003\ni\u000bC\u0005\u0002<\"\t\t\u0011\"\u0001\u0002>\"I\u0011\u0011\u0019\u0005\u0002\u0002\u0013\u0005\u00131\u0019\u0005\n\u0003\u000fD\u0011\u0011!C!\u0003\u0013D\u0011\"a3\t\u0003\u0003%\t%!4\t\u0013\u0005=\u0007\"!A\u0005B\u0005E\u0017\u0001D*jKZ,7+Z4nK:$(B\u0001\u0016,\u0003\u0015\u0001(/[7f\u0015\taS&\u0001\u0003nCRD'\"\u0001\u0018\u0002\u000bM\u0004\u0018N]3\u0004\u0001A\u0011\u0011'A\u0007\u0002S\ta1+[3wKN+w-\\3oiN\u0019\u0011\u0001\u000e\u001e\u0011\u0005UBT\"\u0001\u001c\u000b\u0003]\nQa]2bY\u0006L!!\u000f\u001c\u0003\r\u0005s\u0017PU3g!\tY\u0004)D\u0001=\u0015\tid(\u0001\u0002j_*\tq(\u0001\u0003kCZ\f\u0017BA!=\u00051\u0019VM]5bY&T\u0018M\u00197f\u0003\u0019a\u0014N\\5u}Q\t\u0001'A\u0004xQ\u0016,Gn\r\u0019\u0016\u0003\u0019\u00032!N$J\u0013\tAeGA\u0003BeJ\f\u0017\u0010\u0005\u00026\u0015&\u00111J\u000e\u0002\u0004\u0013:$\u0018\u0001C<iK\u0016d7\u0007\r\u0011\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000f=\u000b).a6\u0002ZB\u0011\u0011\u0007C\n\u0005\u0011Q\nF\u000b\u0005\u00026%&\u00111K\u000e\u0002\b!J|G-^2u!\t)VL\u0004\u0002W7:\u0011qKW\u0007\u00021*\u0011\u0011lL\u0001\u0007yI|w\u000e\u001e \n\u0003]J!\u0001\u0018\u001c\u0002\u000fA\f7m[1hK&\u0011\u0011I\u0018\u0006\u00039Z\nQa\u001d;beR,\u0012!\u0019\t\u0003E\u000el\u0011aK\u0005\u0003I.\u0012\u0001bU1gK2{gnZ\u0001\u0007gR\f'\u000f\u001e\u0011\u0002\rA\u0014\u0018.\\3t+\u0005A\u0007CA\u0019j\u0013\tQ\u0017F\u0001\u0004CSR\u001cV\r^\u0001\baJLW.Z:!\u0003\u0019\u0019W\u000f^8gM\u000691-\u001e;pM\u001a\u0004C\u0003B(paFDQaX\bA\u0002\u0005DQAZ\bA\u0002!DQ\u0001\\\bA\u0002\u0005\fq![:Qe&lW\r\u0006\u0002uoB\u0011Q'^\u0005\u0003mZ\u0012qAQ8pY\u0016\fg\u000eC\u0003y!\u0001\u0007\u0011-A\u0001o\u0003-I7oQ8na>\u001c\u0018\u000e^3\u0015\u0005Q\\\b\"\u0002=\u0012\u0001\u0004\t\u0017aA:fiR\u0019a0a\u0001\u0011\u0005Uz\u0018bAA\u0001m\t!QK\\5u\u0011\u0015A(\u00031\u0001b\u0003\u0015)hn]3u)\rq\u0018\u0011\u0002\u0005\u0006qN\u0001\r!Y\u0001\n]\u0016DH/\u00114uKJ$2!YA\b\u0011\u0015AH\u00031\u0001b\u0003\u0011Ig.\u001b;\u0015\u000by\f)\"a\n\t\u000f\u0005]Q\u00031\u0001\u0002\u001a\u0005)a-Y:ucB!\u00111DA\u0011\u001d\r\t\u0014QD\u0005\u0004\u0003?I\u0013!C*jKZ,W\u000b^5m\u0013\u0011\t\u0019#!\n\u0003\u0017\u0019\u000b7\u000f\u001e$bGR|'o\u001d\u0006\u0004\u0003?I\u0003bBA\u0015+\u0001\u0007\u00111F\u0001\u0006g2|w/\u001d\t\u0004c\u00055\u0012bAA\u0018S\tQa)Y2u_JDU-\u00199\u0002\u0013%t\u0017\u000e^'pIN\u0002D#\u0001@\u0002\u001b%t\u0017\u000e\u001e$s_6\f%O]1z)\rq\u0018\u0011\b\u0005\b\u0003/9\u0002\u0019AA\r\u00035Ig.\u001b;Ge>l\u0017+^3vKR)a0a\u0010\u0002D!1\u0011\u0011\t\rA\u0002\u0005\fQ\u0001\\5nSRDq!!\u0012\u0019\u0001\u0004\tY#A\u0001rQ\rA\u0012\u0011\n\t\u0005\u0003\u0017\n\t&\u0004\u0002\u0002N)\u0019\u0011q\n\u001c\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002T\u00055#a\u0002;bS2\u0014XmY\u0001\nS:LGOR5sgR$RA`A-\u00037Bq!a\u0006\u001a\u0001\u0004\tI\u0002C\u0004\u0002*e\u0001\r!a\u000b\u0002\u0011%t\u0017\u000e\u001e*fgR$2A`A1\u0011\u001d\tIC\u0007a\u0001\u0003W\tAaY8qsR9q*a\u001a\u0002j\u0005-\u0004bB0\u001c!\u0003\u0005\r!\u0019\u0005\bMn\u0001\n\u00111\u0001i\u0011\u001da7\u0004%AA\u0002\u0005\fabY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0002r)\u001a\u0011-a\u001d,\u0005\u0005U\u0004\u0003BA<\u0003{j!!!\u001f\u000b\t\u0005m\u0014QJ\u0001\nk:\u001c\u0007.Z2lK\u0012LA!a \u0002z\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u0011Q\u0011\u0016\u0004Q\u0006M\u0014AD2paf$C-\u001a4bk2$HeM\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u00055\u0005\u0003BAH\u0003+k!!!%\u000b\u0007\u0005Me(\u0001\u0003mC:<\u0017\u0002BAL\u0003#\u0013aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLX#A%\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u0011\u0011UAT!\r)\u00141U\u0005\u0004\u0003K3$aA!os\"A\u0011\u0011V\u0011\u0002\u0002\u0003\u0007\u0011*A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003_\u0003b!!-\u00028\u0006\u0005VBAAZ\u0015\r\t)LN\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA]\u0003g\u0013\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR\u0019A/a0\t\u0013\u0005%6%!AA\u0002\u0005\u0005\u0016A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!!$\u0002F\"A\u0011\u0011\u0016\u0013\u0002\u0002\u0003\u0007\u0011*\u0001\u0005iCND7i\u001c3f)\u0005I\u0015\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u00055\u0015AB3rk\u0006d7\u000fF\u0002u\u0003'D\u0011\"!+(\u0003\u0003\u0005\r!!)\t\u000b}+\u0001\u0019A1\t\u000b\u0019,\u0001\u0019\u00015\t\u000b1,\u0001\u0019A1\u0002\u000fUt\u0017\r\u001d9msR!\u0011q\\Av!\u0015)\u0014\u0011]As\u0013\r\t\u0019O\u000e\u0002\u0007\u001fB$\u0018n\u001c8\u0011\rU\n9/\u00195b\u0013\r\tIO\u000e\u0002\u0007)V\u0004H.Z\u001a\t\u0011\u00055h!!AA\u0002=\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t\u0019\u0010\u0005\u0003\u0002\u0010\u0006U\u0018\u0002BA|\u0003#\u0013aa\u00142kK\u000e$\b"
)
public class SieveSegment implements Product, Serializable {
   private final SafeLong start;
   private final BitSet primes;
   private final SafeLong cutoff;

   public static Option unapply(final SieveSegment x$0) {
      return SieveSegment$.MODULE$.unapply(x$0);
   }

   public static SieveSegment apply(final SafeLong start, final BitSet primes, final SafeLong cutoff) {
      return SieveSegment$.MODULE$.apply(start, primes, cutoff);
   }

   public static int[] wheel30() {
      return SieveSegment$.MODULE$.wheel30();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public SafeLong start() {
      return this.start;
   }

   public BitSet primes() {
      return this.primes;
   }

   public SafeLong cutoff() {
      return this.cutoff;
   }

   public boolean isPrime(final SafeLong n) {
      return this.primes().apply(n.$minus(this.start()).toInt());
   }

   public boolean isComposite(final SafeLong n) {
      return !this.primes().apply(n.$minus(this.start()).toInt());
   }

   public void set(final SafeLong n) {
      this.primes().$plus$eq(n.$minus(this.start()).toInt());
   }

   public void unset(final SafeLong n) {
      this.primes().$minus$eq(n.$minus(this.start()).toInt());
   }

   public SafeLong nextAfter(final SafeLong n) {
      int i = n.$minus(this.start()).$plus(2L).toInt();

      for(int len = this.primes().length(); i < len; i += 2) {
         if (this.primes().apply(i)) {
            return this.start().$plus((long)i);
         }
      }

      return SafeLong$.MODULE$.apply(-1L);
   }

   public void init(final SieveUtil.FastFactors fastq, final FactorHeap slowq) {
      this.initMod30();
      if (BoxesRunTime.equalsNumObject(this.start(), BoxesRunTime.boxToInteger(0))) {
         this.initFirst(fastq, slowq);
      } else {
         SafeLong limit = (SafeLong)spire.math.package$.MODULE$.min(this.cutoff().$times$times(2), this.start().$plus((long)this.primes().length()), SafeLong$.MODULE$.SafeLongIsReal());
         this.initFromArray(fastq);
         this.initFromQueue(limit, slowq);
         this.initRest(slowq);
      }

   }

   public void initMod30() {
      int[] arr = this.primes().array();
      .MODULE$.assert(arr.length % 15 == 0);
      int limit = arr.length;
      int[] wheel = SieveSegment$.MODULE$.wheel30();

      for(int index$macro$1 = 0; index$macro$1 < limit; index$macro$1 += 15) {
         System.arraycopy(wheel, 0, arr, index$macro$1, 15);
      }

      if (BoxesRunTime.equalsNumObject(this.start(), BoxesRunTime.boxToLong(0L))) {
         this.primes().$minus$eq(1);
         this.primes().$plus$eq(2);
         this.primes().$plus$eq(3);
         this.primes().$plus$eq(5);
      }

   }

   private void initFromArray(final SieveUtil.FastFactors fastq) {
      SieveUtil.FastFactor[] arr = fastq.arr();
      int i = 0;

      for(long len = this.start().$plus((long)this.primes().length()).$less(this.cutoff()) ? this.cutoff().$minus(this.start()).toLong() : (long)this.primes().length(); i < arr.length; ++i) {
         SieveUtil.FastFactor factor = arr[i];
         int j = factor.m().$minus(this.start()).toInt();
         int k = factor.p();
         int kk = k + k;
         long lim = len - (long)kk;
         this.primes().$minus$eq(j);

         while((long)j < lim) {
            j += kk;
            this.primes().$minus$eq(j);
         }

         factor.m_$eq(this.start().$plus((long)j).$plus((long)kk));
      }

   }

   private void initFromQueue(final SafeLong limit, final FactorHeap q) {
      while(!q.isEmpty()) {
         SieveUtil.Factor factor = q.dequeue();
         SafeLong m = factor.next();
         if (!m.$less(limit)) {
            q.$plus$eq(factor);
            BoxedUnit var12 = BoxedUnit.UNIT;
            return;
         }

         SafeLong p = factor.p();
         int len = this.primes().length();
         int i = m.$minus(this.start()).toInt();
         SafeLong var10000;
         if (p.$less(SafeLong$.MODULE$.apply(len))) {
            int k = p.toInt();

            for(int kk = k + k; i < len; i += kk) {
               this.primes().$minus$eq(i);
            }

            var10000 = this.start().$plus((long)i);
         } else {
            this.primes().$minus$eq(i);
            var10000 = m.$plus(p);
         }

         SafeLong m2 = var10000;
         factor.next_$eq(m2);
         q.$plus$eq(factor);
         q = q;
         limit = limit;
      }

   }

   public void initFirst(final SieveUtil.FastFactors fastq, final FactorHeap slowq) {
      int p = 1;
      int len = this.primes().length();

      ArrayBuffer buf;
      for(buf = scala.collection.mutable.ArrayBuffer..MODULE$.empty(); p < len; p += 2) {
         if (!this.primes().apply(p)) {
            BoxedUnit var12 = BoxedUnit.UNIT;
         } else {
            long m = (long)p * (long)p;
            if (m < (long)len) {
               int pp = p + p;
               int k = (int)m;
               this.primes().$minus$eq(k);
               int lim = len - pp;

               while(k < lim) {
                  k += pp;
                  this.primes().$minus$eq(k);
               }

               m = (long)k + (long)pp;
            }

            if (p < 7) {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            } else if (m - (long)this.primes().length() < (long)this.primes().length()) {
               buf.$plus$eq(new SieveUtil.FastFactor(p, SafeLong$.MODULE$.apply(m)));
            } else {
               if (this.cutoff().$greater(SafeLong$.MODULE$.apply(p))) {
                  slowq.$plus$eq(new SieveUtil.Factor(SafeLong$.MODULE$.apply(p), SafeLong$.MODULE$.apply(m)));
               }

               BoxedUnit var11 = BoxedUnit.UNIT;
            }
         }
      }

      fastq.arr_$eq((SieveUtil.FastFactor[])buf.toArray(scala.reflect.ClassTag..MODULE$.apply(SieveUtil.FastFactor.class)));
   }

   public void initRest(final FactorHeap slowq) {
      if (!this.start().$greater$eq(this.cutoff())) {
         long len = this.start().$plus((long)this.primes().length()).$greater$eq(this.cutoff()) ? this.cutoff().$minus(this.start()).toLong() : (long)this.primes().length();

         for(int i = 1; (long)i < len; i += 2) {
            if (this.primes().apply(i)) {
               SafeLong p = this.start().$plus((long)i);
               slowq.$plus$eq(new SieveUtil.Factor(p, p.$times$times(2)));
            }
         }

      }
   }

   public SieveSegment copy(final SafeLong start, final BitSet primes, final SafeLong cutoff) {
      return new SieveSegment(start, primes, cutoff);
   }

   public SafeLong copy$default$1() {
      return this.start();
   }

   public BitSet copy$default$2() {
      return this.primes();
   }

   public SafeLong copy$default$3() {
      return this.cutoff();
   }

   public String productPrefix() {
      return "SieveSegment";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.start();
            break;
         case 1:
            var10000 = this.primes();
            break;
         case 2:
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
      return x$1 instanceof SieveSegment;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "start";
            break;
         case 1:
            var10000 = "primes";
            break;
         case 2:
            var10000 = "cutoff";
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
         label57: {
            boolean var2;
            if (x$1 instanceof SieveSegment) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label40: {
                  SieveSegment var4 = (SieveSegment)x$1;
                  if (BoxesRunTime.equalsNumNum(this.start(), var4.start())) {
                     label38: {
                        BitSet var10000 = this.primes();
                        BitSet var5 = var4.primes();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label38;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label38;
                        }

                        if (BoxesRunTime.equalsNumNum(this.cutoff(), var4.cutoff()) && var4.canEqual(this)) {
                           var7 = true;
                           break label40;
                        }
                     }
                  }

                  var7 = false;
               }

               if (var7) {
                  break label57;
               }
            }

            var7 = false;
            return var7;
         }
      }

      var7 = true;
      return var7;
   }

   public SieveSegment(final SafeLong start, final BitSet primes, final SafeLong cutoff) {
      this.start = start;
      this.primes = primes;
      this.cutoff = cutoff;
      Product.$init$(this);
   }
}
