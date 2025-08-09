package spire.random.rng;

import java.nio.ByteBuffer;
import java.util.Arrays;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import spire.random.IntBasedGenerator;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rd\u0001\u0002\u0015*\u0005AB\u0001\"\u000e\u0001\u0003\u0002\u0003\u0006IA\u000e\u0005\t\u007f\u0001\u0011\t\u0011)A\u0005y!1\u0001\t\u0001C\tW\u0005CqA\u0012\u0001A\u0002\u0013%q\tC\u0004I\u0001\u0001\u0007I\u0011B%\t\r=\u0003\u0001\u0015)\u0003=\u0011\u0015\u0001\u0006\u0001\"\u0001R\u0011\u0015\u0011\u0006\u0001\"\u0001T\u0011\u0015A\u0006\u0001\"\u0001Z\u0011\u0015a\u0006\u0001\"\u0001^\u000f\u0015q\u0016\u0006#\u0001`\r\u0015A\u0013\u0006#\u0001a\u0011\u0015\u0001E\u0002\"\u0001k\u0011\u001dYGB1A\u0005\n\u001dCa\u0001\u001c\u0007!\u0002\u0013a\u0004bB9\r\u0005\u0004%Ia\u0012\u0005\u0007e2\u0001\u000b\u0011\u0002\u001f\t\u000fQd!\u0019!C\u0005\u000f\"1Q\u000f\u0004Q\u0001\nqBqa\u001e\u0007C\u0002\u0013%q\t\u0003\u0004y\u0019\u0001\u0006I\u0001\u0010\u0005\bu2\u0011\r\u0011\"\u0003H\u0011\u0019YH\u0002)A\u0005y!9Q\u0010\u0004b\u0001\n\u00139\u0005B\u0002@\rA\u0003%A\b\u0003\u0005\u0002\u00021\u0011\r\u0011\"\u0003H\u0011\u001d\t\u0019\u0001\u0004Q\u0001\nqB\u0001\"a\u0002\r\u0005\u0004%Ia\u0012\u0005\b\u0003\u0013a\u0001\u0015!\u0003=\u0011!\ti\u0001\u0004b\u0001\n\u00139\u0005bBA\b\u0019\u0001\u0006I\u0001\u0010\u0005\b\u0003'aA\u0011BA\u000b\u0011\u001d\ti\u0002\u0004C\u0001\u0003?Aq!!\t\r\t\u0003\t\u0019\u0003C\u0004\u0002*1!\t!a\u000b\t\u000f\u0005EB\u0002\"\u0001\u00024!9\u0011q\u0007\u0007\u0005\u0002\u0005e\u0002\"CA#\u0019E\u0005I\u0011AA$\u0011)\ti\u0006DI\u0001\n#Y\u0013q\f\u0002\u0012\u001b\u0016\u00148/\u001a8oKR;\u0018n\u001d;feN\u0012$B\u0001\u0016,\u0003\r\u0011hn\u001a\u0006\u0003Y5\naA]1oI>l'\"\u0001\u0018\u0002\u000bM\u0004\u0018N]3\u0004\u0001M\u0011\u0001!\r\t\u0003eMj\u0011aK\u0005\u0003i-\u0012\u0011#\u00138u\u0005\u0006\u001cX\rZ$f]\u0016\u0014\u0018\r^8s\u0003\tiG\u000fE\u00028uqj\u0011\u0001\u000f\u0006\u0002s\u0005)1oY1mC&\u00111\b\u000f\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003ouJ!A\u0010\u001d\u0003\u0007%sG/\u0001\u0003ni&\u0004\u0014A\u0002\u001fj]&$h\bF\u0002C\t\u0016\u0003\"a\u0011\u0001\u000e\u0003%BQ!N\u0002A\u0002YBqaP\u0002\u0011\u0002\u0003\u0007A(A\u0002ni&,\u0012\u0001P\u0001\b[RLw\fJ3r)\tQU\n\u0005\u00028\u0017&\u0011A\n\u000f\u0002\u0005+:LG\u000fC\u0004O\u000b\u0005\u0005\t\u0019\u0001\u001f\u0002\u0007a$\u0013'\u0001\u0003ni&\u0004\u0013\u0001C2pafLe.\u001b;\u0016\u0003\t\u000bAbZ3u'\u0016,GMQ=uKN,\u0012\u0001\u0016\t\u0004oi*\u0006CA\u001cW\u0013\t9\u0006H\u0001\u0003CsR,\u0017\u0001D:fiN+W\r\u001a\"zi\u0016\u001cHC\u0001&[\u0011\u0015Y\u0016\u00021\u0001U\u0003\u0015\u0011\u0017\u0010^3t\u0003\u001dqW\r\u001f;J]R$\u0012\u0001P\u0001\u0012\u001b\u0016\u00148/\u001a8oKR;\u0018n\u001d;feN\u0012\u0004CA\"\r'\ra\u0011\r\u001a\t\u0003o\tL!a\u0019\u001d\u0003\r\u0005s\u0017PU3g!\u0011\u0011TMQ4\n\u0005\u0019\\#AE$f]\u0016\u0014\u0018\r^8s\u0007>l\u0007/\u00198j_:\u0004Ba\u000e57y%\u0011\u0011\u000e\u000f\u0002\u0007)V\u0004H.\u001a\u001a\u0015\u0003}\u000b\u0011\"\u00169qKJl\u0015m]6\u0002\u0015U\u0003\b/\u001a:NCN\\\u0007\u0005\u000b\u0002\u0010]B\u0011qg\\\u0005\u0003ab\u0012a!\u001b8mS:,\u0017!\u0003'po\u0016\u0014X*Y:l\u0003)aun^3s\u001b\u0006\u001c8\u000e\t\u0015\u0003#9\f\u0011AT\u0001\u0003\u001d\u0002B#a\u00058\u0002\u00035\u000b!!\u0014\u0011)\u0005Uq\u0017a\u0001(`\u001b\u0006!ajX'!Q\t9b.A\u0002O?F\nAAT02A!\u0012\u0011D\\\u0001\u0004\u001b~s\u0015\u0001B'`\u001d\u0002B#a\u00078\u0002\u00075{\u0016'\u0001\u0003N?F\u0002\u0003FA\u000fo\u0003\u0015\u0011\u0015\fV#T\u0003\u0019\u0011\u0015\fV#TA!\u0012qD\\\u0001\u0006[\u0006<\u0007'\r\u000b\u0004y\u0005]\u0001BBA\rA\u0001\u0007A(A\u0001yQ\t\u0001c.\u0001\u0006sC:$w.\\*fK\u0012$\u0012aZ\u0001\tMJ|WnU3fIR\u0019!)!\n\t\r\u0005\u001d\"\u00051\u0001h\u0003\u0011\u0019X-\u001a3\u0002\u0013\u0019\u0014x.\\!se\u0006LHc\u0001\"\u0002.!1\u0011qF\u0012A\u0002Y\n1!\u0019:s\u0003%1'o\\7CsR,7\u000fF\u0002C\u0003kAQa\u0017\u0013A\u0002Q\u000b\u0001B\u001a:p[RKW.\u001a\u000b\u0004\u0005\u0006m\u0002\"CA\u001fKA\u0005\t\u0019AA \u0003\u0011!\u0018.\\3\u0011\u0007]\n\t%C\u0002\u0002Da\u0012A\u0001T8oO\u0006\u0011bM]8n)&lW\r\n3fM\u0006,H\u000e\u001e\u00132+\t\tIE\u000b\u0003\u0002@\u0005-3FAA'!\u0011\ty%!\u0017\u000e\u0005\u0005E#\u0002BA*\u0003+\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005]\u0003(\u0001\u0006b]:|G/\u0019;j_:LA!a\u0017\u0002R\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00133+\t\t\tGK\u0002=\u0003\u0017\u0002"
)
public final class MersenneTwister32 extends IntBasedGenerator {
   private final int[] mt;
   private int mti;

   public static long fromTime$default$1() {
      return MersenneTwister32$.MODULE$.fromTime$default$1();
   }

   public static MersenneTwister32 fromTime(final long time) {
      return MersenneTwister32$.MODULE$.fromTime(time);
   }

   public static MersenneTwister32 fromBytes(final byte[] bytes) {
      return MersenneTwister32$.MODULE$.fromBytes(bytes);
   }

   public static MersenneTwister32 fromArray(final int[] arr) {
      return MersenneTwister32$.MODULE$.fromArray(arr);
   }

   public static MersenneTwister32 fromSeed(final Tuple2 seed) {
      return MersenneTwister32$.MODULE$.fromSeed(seed);
   }

   public static Tuple2 randomSeed() {
      return MersenneTwister32$.MODULE$.randomSeed();
   }

   public static Object apply(final Object seed) {
      return MersenneTwister32$.MODULE$.apply(seed);
   }

   public static Object apply() {
      return MersenneTwister32$.MODULE$.apply();
   }

   private int mti() {
      return this.mti;
   }

   private void mti_$eq(final int x$1) {
      this.mti = x$1;
   }

   public MersenneTwister32 copyInit() {
      return new MersenneTwister32((int[])this.mt.clone(), this.mti());
   }

   public byte[] getSeedBytes() {
      byte[] bytes = new byte[MersenneTwister32$.MODULE$.spire$random$rng$MersenneTwister32$$BYTES()];
      ByteBuffer bb = ByteBuffer.wrap(bytes);

      for(int index$macro$1 = 0; index$macro$1 < MersenneTwister32$.MODULE$.spire$random$rng$MersenneTwister32$$N(); ++index$macro$1) {
         bb.putInt(this.mt[index$macro$1]);
      }

      bb.putInt(this.mti());
      return bytes;
   }

   public void setSeedBytes(final byte[] bytes) {
      byte[] bs = bytes.length < MersenneTwister32$.MODULE$.spire$random$rng$MersenneTwister32$$BYTES() ? Arrays.copyOf(bytes, MersenneTwister32$.MODULE$.spire$random$rng$MersenneTwister32$$BYTES()) : bytes;
      ByteBuffer bb = ByteBuffer.wrap(bs);

      for(int index$macro$1 = 0; index$macro$1 < MersenneTwister32$.MODULE$.spire$random$rng$MersenneTwister32$$N(); ++index$macro$1) {
         this.mt[index$macro$1] = bb.getInt();
      }

      this.mti_$eq(bb.getInt());
   }

   public int nextInt() {
      int y = 0;
      if (this.mti() >= MersenneTwister32$.MODULE$.spire$random$rng$MersenneTwister32$$N()) {
         int kk;
         for(kk = 0; kk < MersenneTwister32$.MODULE$.spire$random$rng$MersenneTwister32$$N_M(); ++kk) {
            y = this.mt[kk] & MersenneTwister32$.MODULE$.spire$random$rng$MersenneTwister32$$UpperMask() | this.mt[kk + 1] & MersenneTwister32$.MODULE$.spire$random$rng$MersenneTwister32$$LowerMask();
            this.mt[kk] = this.mt[kk + MersenneTwister32$.MODULE$.spire$random$rng$MersenneTwister32$$M()] ^ y >>> 1 ^ MersenneTwister32$.MODULE$.spire$random$rng$MersenneTwister32$$mag01(y);
         }

         while(kk < MersenneTwister32$.MODULE$.spire$random$rng$MersenneTwister32$$N_1()) {
            y = this.mt[kk] & MersenneTwister32$.MODULE$.spire$random$rng$MersenneTwister32$$UpperMask() | this.mt[kk + 1] & MersenneTwister32$.MODULE$.spire$random$rng$MersenneTwister32$$LowerMask();
            this.mt[kk] = this.mt[kk + MersenneTwister32$.MODULE$.spire$random$rng$MersenneTwister32$$M_N()] ^ y >>> 1 ^ MersenneTwister32$.MODULE$.spire$random$rng$MersenneTwister32$$mag01(y);
            ++kk;
         }

         y = this.mt[MersenneTwister32$.MODULE$.spire$random$rng$MersenneTwister32$$N_1()] & MersenneTwister32$.MODULE$.spire$random$rng$MersenneTwister32$$UpperMask() | this.mt[0] & MersenneTwister32$.MODULE$.spire$random$rng$MersenneTwister32$$LowerMask();
         this.mt[MersenneTwister32$.MODULE$.spire$random$rng$MersenneTwister32$$N_1()] = this.mt[MersenneTwister32$.MODULE$.spire$random$rng$MersenneTwister32$$M_1()] ^ y >>> 1 ^ MersenneTwister32$.MODULE$.spire$random$rng$MersenneTwister32$$mag01(y);
         this.mti_$eq(0);
      }

      y = this.mt[this.mti()];
      this.mti_$eq(this.mti() + 1);
      y ^= y >>> 11;
      y ^= y << 7 & -1658038656;
      y ^= y << 15 & -272236544;
      y ^= y >>> 18;
      return y;
   }

   public MersenneTwister32(final int[] mt, final int mti0) {
      this.mt = mt;
      this.mti = mti0;
   }
}
