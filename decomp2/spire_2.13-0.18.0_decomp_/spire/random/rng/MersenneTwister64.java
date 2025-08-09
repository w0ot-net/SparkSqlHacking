package spire.random.rng;

import java.nio.ByteBuffer;
import java.util.Arrays;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import spire.random.LongBasedGenerator;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015d\u0001\u0002\u0015*\u0005AB\u0001\"\u000e\u0001\u0003\u0002\u0003\u0006IA\u000e\u0005\t\u007f\u0001\u0011\t\u0011)A\u0005\u0001\"11\t\u0001C\tW\u0011Cq!\u0013\u0001A\u0002\u0013%!\nC\u0004L\u0001\u0001\u0007I\u0011\u0002'\t\rI\u0003\u0001\u0015)\u0003A\u0011\u0015\u0019\u0006\u0001\"\u0001U\u0011\u0015)\u0006\u0001\"\u0001W\u0011\u0015Y\u0006\u0001\"\u0001]\u0011\u0015y\u0006\u0001\"\u0011a\u000f\u0015\t\u0017\u0006#\u0001c\r\u0015A\u0013\u0006#\u0001d\u0011\u0015\u0019E\u0002\"\u0001n\u0011\u001dqGB1A\u0005\n=Da\u0001\u001d\u0007!\u0002\u0013a\u0004bB;\r\u0005\u0004%Ia\u001c\u0005\u0007m2\u0001\u000b\u0011\u0002\u001f\t\u000fad!\u0019!C\u0005\u0015\"1\u0011\u0010\u0004Q\u0001\n\u0001Cqa\u001f\u0007C\u0002\u0013%!\n\u0003\u0004}\u0019\u0001\u0006I\u0001\u0011\u0005\b}2\u0011\r\u0011\"\u0003K\u0011\u0019yH\u0002)A\u0005\u0001\"A\u00111\u0001\u0007C\u0002\u0013%!\nC\u0004\u0002\u00061\u0001\u000b\u0011\u0002!\t\u0011\u0005%AB1A\u0005\n)Cq!a\u0003\rA\u0003%\u0001\t\u0003\u0005\u0002\u00101\u0011\r\u0011\"\u0003K\u0011\u001d\t\t\u0002\u0004Q\u0001\n\u0001C\u0001\"!\u0006\r\u0005\u0004%IA\u0013\u0005\b\u0003/a\u0001\u0015!\u0003A\u0011\u001d\tY\u0002\u0004C\u0005\u0003;Aq!!\n\r\t\u0003\t9\u0003C\u0004\u0002*1!\t!a\u000b\t\u000f\u0005EB\u0002\"\u0001\u00024!9\u0011\u0011\b\u0007\u0005\u0002\u0005m\u0002bBA \u0019\u0011\u0005\u0011\u0011\t\u0005\n\u0003\u000fb\u0011\u0013!C\u0001\u0003\u0013B!\"a\u0018\r#\u0003%\tbKA1\u0005EiUM]:f]:,Gk^5ti\u0016\u0014h\u0007\u000e\u0006\u0003U-\n1A\u001d8h\u0015\taS&\u0001\u0004sC:$w.\u001c\u0006\u0002]\u0005)1\u000f]5sK\u000e\u00011C\u0001\u00012!\t\u00114'D\u0001,\u0013\t!4F\u0001\nM_:<')Y:fI\u001e+g.\u001a:bi>\u0014\u0018AA7u!\r9$\bP\u0007\u0002q)\t\u0011(A\u0003tG\u0006d\u0017-\u0003\u0002<q\t)\u0011I\u001d:bsB\u0011q'P\u0005\u0003}a\u0012A\u0001T8oO\u0006!Q\u000e^51!\t9\u0014)\u0003\u0002Cq\t\u0019\u0011J\u001c;\u0002\rqJg.\u001b;?)\r)u\t\u0013\t\u0003\r\u0002i\u0011!\u000b\u0005\u0006k\r\u0001\rA\u000e\u0005\b\u007f\r\u0001\n\u00111\u0001A\u0003\riG/[\u000b\u0002\u0001\u00069Q\u000e^5`I\u0015\fHCA'Q!\t9d*\u0003\u0002Pq\t!QK\\5u\u0011\u001d\tV!!AA\u0002\u0001\u000b1\u0001\u001f\u00132\u0003\u0011iG/\u001b\u0011\u0002\u0011\r|\u0007/_%oSR,\u0012!R\u0001\rO\u0016$8+Z3e\u0005f$Xm]\u000b\u0002/B\u0019qG\u000f-\u0011\u0005]J\u0016B\u0001.9\u0005\u0011\u0011\u0015\u0010^3\u0002\u0019M,GoU3fI\nKH/Z:\u0015\u00055k\u0006\"\u00020\n\u0001\u00049\u0016!\u00022zi\u0016\u001c\u0018\u0001\u00038fqRduN\\4\u0015\u0003q\n\u0011#T3sg\u0016tg.\u001a+xSN$XM\u001d\u001c5!\t1EbE\u0002\rI\u001e\u0004\"aN3\n\u0005\u0019D$AB!osJ+g\r\u0005\u00033Q\u0016S\u0017BA5,\u0005I9UM\\3sCR|'oQ8na\u0006t\u0017n\u001c8\u0011\t]Zg\u0007Q\u0005\u0003Yb\u0012a\u0001V;qY\u0016\u0014D#\u00012\u0002\u0013U\u0003\b/\u001a:NCN\\W#\u0001\u001f\u0002\u0015U\u0003\b/\u001a:NCN\\\u0007\u0005\u000b\u0002\u0010eB\u0011qg]\u0005\u0003ib\u0012a!\u001b8mS:,\u0017!\u0003'po\u0016\u0014X*Y:l\u0003)aun^3s\u001b\u0006\u001c8\u000e\t\u0015\u0003#I\f\u0011AT\u0001\u0003\u001d\u0002B#a\u0005:\u0002\u00035\u000b!!\u0014\u0011)\u0005U\u0011\u0018a\u0001(`\u001b\u0006!ajX'!Q\t9\"/A\u0002O?F\nAAT02A!\u0012\u0011D]\u0001\u0004\u001b~s\u0015\u0001B'`\u001d\u0002B#a\u0007:\u0002\u00075{\u0016'\u0001\u0003N?F\u0002\u0003FA\u000fs\u0003\u0015\u0011\u0015\fV#T\u0003\u0019\u0011\u0015\fV#TA!\u0012qD]\u0001\u0006[\u0006<\u0007'\r\u000b\u0004y\u0005}\u0001BBA\u0011A\u0001\u0007A(A\u0001yQ\t\u0001#/\u0001\u0006sC:$w.\\*fK\u0012$\u0012A[\u0001\tMJ|WnU3fIR\u0019Q)!\f\t\r\u0005=\"\u00051\u0001k\u0003\u0011\u0019X-\u001a3\u0002\u0013\u0019\u0014x.\\!se\u0006LHcA#\u00026!1\u0011qG\u0012A\u0002Y\n1!\u0019:s\u0003%1'o\\7CsR,7\u000fF\u0002F\u0003{AQA\u0018\u0013A\u0002]\u000b\u0001B\u001a:p[RKW.\u001a\u000b\u0004\u000b\u0006\r\u0003\u0002CA#KA\u0005\t\u0019\u0001\u001f\u0002\tQLW.Z\u0001\u0013MJ|W\u000eV5nK\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0002L)\u001aA(!\u0014,\u0005\u0005=\u0003\u0003BA)\u00037j!!a\u0015\u000b\t\u0005U\u0013qK\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!\u00179\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003;\n\u0019FA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\f1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u0012TCAA2U\r\u0001\u0015Q\n"
)
public final class MersenneTwister64 extends LongBasedGenerator {
   private final long[] mt;
   private int mti;

   public static long fromTime$default$1() {
      return MersenneTwister64$.MODULE$.fromTime$default$1();
   }

   public static MersenneTwister64 fromTime(final long time) {
      return MersenneTwister64$.MODULE$.fromTime(time);
   }

   public static MersenneTwister64 fromBytes(final byte[] bytes) {
      return MersenneTwister64$.MODULE$.fromBytes(bytes);
   }

   public static MersenneTwister64 fromArray(final long[] arr) {
      return MersenneTwister64$.MODULE$.fromArray(arr);
   }

   public static MersenneTwister64 fromSeed(final Tuple2 seed) {
      return MersenneTwister64$.MODULE$.fromSeed(seed);
   }

   public static Tuple2 randomSeed() {
      return MersenneTwister64$.MODULE$.randomSeed();
   }

   public static Object apply(final Object seed) {
      return MersenneTwister64$.MODULE$.apply(seed);
   }

   public static Object apply() {
      return MersenneTwister64$.MODULE$.apply();
   }

   private int mti() {
      return this.mti;
   }

   private void mti_$eq(final int x$1) {
      this.mti = x$1;
   }

   public MersenneTwister64 copyInit() {
      return new MersenneTwister64((long[])this.mt.clone(), this.mti());
   }

   public byte[] getSeedBytes() {
      byte[] bytes = new byte[MersenneTwister64$.MODULE$.spire$random$rng$MersenneTwister64$$BYTES()];
      ByteBuffer bb = ByteBuffer.wrap(bytes);

      for(int index$macro$1 = 0; index$macro$1 < MersenneTwister64$.MODULE$.spire$random$rng$MersenneTwister64$$N(); ++index$macro$1) {
         bb.putLong(this.mt[index$macro$1]);
      }

      bb.putInt(this.mti());
      return bytes;
   }

   public void setSeedBytes(final byte[] bytes) {
      byte[] bs = bytes.length < MersenneTwister64$.MODULE$.spire$random$rng$MersenneTwister64$$BYTES() ? Arrays.copyOf(bytes, MersenneTwister64$.MODULE$.spire$random$rng$MersenneTwister64$$BYTES()) : bytes;
      ByteBuffer bb = ByteBuffer.wrap(bs);

      for(int index$macro$1 = 0; index$macro$1 < MersenneTwister64$.MODULE$.spire$random$rng$MersenneTwister64$$N(); ++index$macro$1) {
         this.mt[index$macro$1] = bb.getLong();
      }

      this.mti_$eq(bb.getInt());
   }

   public long nextLong() {
      long x = 0L;
      if (this.mti() >= MersenneTwister64$.MODULE$.spire$random$rng$MersenneTwister64$$N()) {
         int kk;
         for(kk = 0; kk < MersenneTwister64$.MODULE$.spire$random$rng$MersenneTwister64$$N_M(); ++kk) {
            x = this.mt[kk] & MersenneTwister64$.MODULE$.spire$random$rng$MersenneTwister64$$UpperMask() | this.mt[kk + 1] & MersenneTwister64$.MODULE$.spire$random$rng$MersenneTwister64$$LowerMask();
            this.mt[kk] = this.mt[kk + MersenneTwister64$.MODULE$.spire$random$rng$MersenneTwister64$$M()] ^ x >>> 1 ^ MersenneTwister64$.MODULE$.spire$random$rng$MersenneTwister64$$mag01(x);
         }

         while(kk < MersenneTwister64$.MODULE$.spire$random$rng$MersenneTwister64$$N_1()) {
            x = this.mt[kk] & MersenneTwister64$.MODULE$.spire$random$rng$MersenneTwister64$$UpperMask() | this.mt[kk + 1] & MersenneTwister64$.MODULE$.spire$random$rng$MersenneTwister64$$LowerMask();
            this.mt[kk] = this.mt[kk + MersenneTwister64$.MODULE$.spire$random$rng$MersenneTwister64$$M_N()] ^ x >>> 1 ^ MersenneTwister64$.MODULE$.spire$random$rng$MersenneTwister64$$mag01(x);
            ++kk;
         }

         x = this.mt[MersenneTwister64$.MODULE$.spire$random$rng$MersenneTwister64$$N_1()] & MersenneTwister64$.MODULE$.spire$random$rng$MersenneTwister64$$UpperMask() | this.mt[0] & MersenneTwister64$.MODULE$.spire$random$rng$MersenneTwister64$$LowerMask();
         this.mt[MersenneTwister64$.MODULE$.spire$random$rng$MersenneTwister64$$N_1()] = this.mt[MersenneTwister64$.MODULE$.spire$random$rng$MersenneTwister64$$M_1()] ^ x >>> 1 ^ MersenneTwister64$.MODULE$.spire$random$rng$MersenneTwister64$$mag01(x);
         this.mti_$eq(0);
      }

      x = this.mt[this.mti()];
      this.mti_$eq(this.mti() + 1);
      x ^= x >>> 29 & 6148914691236517205L;
      x ^= x << 17 & 8202884508482404352L;
      x ^= x << 37 & -2270628950310912L;
      x ^= x >>> 43;
      return x;
   }

   public MersenneTwister64(final long[] mt, final int mti0) {
      this.mt = mt;
      this.mti = mti0;
   }
}
