package spire.random.rng;

import java.nio.ByteBuffer;
import java.util.Arrays;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import spire.random.IntBasedGenerator;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001de\u0001B\u0014)\u0005=B\u0001\u0002\u000e\u0001\u0003\u0002\u0003\u0006I!\u000e\u0005\t}\u0001\u0011\t\u0011)A\u0005w!1q\b\u0001C\tU\u0001Cq!\u0012\u0001A\u0002\u0013%a\tC\u0004H\u0001\u0001\u0007I\u0011\u0002%\t\r9\u0003\u0001\u0015)\u0003<\u0011\u0015y\u0005\u0001\"\u0001Q\u0011\u0015\t\u0006\u0001\"\u0001S\u0011\u00159\u0006\u0001\"\u0001Y\u0011\u0015Y\u0006\u0001\"\u0001]\u000f\u0015i\u0006\u0006#\u0001_\r\u00159\u0003\u0006#\u0001`\u0011\u0015yD\u0002\"\u0001j\u0011\u001dQGB1A\u0005\n\u0019Caa\u001b\u0007!\u0002\u0013Y\u0004b\u00029\r\u0005\u0004%IA\u0012\u0005\u0007c2\u0001\u000b\u0011B\u001e\t\u000fMd!\u0019!C\u0005\r\"1A\u000f\u0004Q\u0001\nmBqA\u001e\u0007C\u0002\u0013%a\t\u0003\u0004x\u0019\u0001\u0006Ia\u000f\u0005\bs2\u0011\r\u0011\"\u0004G\u0011\u0019QH\u0002)A\u0007w!9A\u0010\u0004b\u0001\n\u001b1\u0005BB?\rA\u000351\bC\u0004\u0000\u0019\t\u0007IQ\u0002$\t\u000f\u0005\u0005A\u0002)A\u0007w!9\u0011Q\u0001\u0007\u0005\u000e\u0005\u001d\u0001bBA\n\u0019\u00115\u0011Q\u0003\u0005\b\u0003;aAQBA\u0010\u0011\u001d\t)\u0003\u0004C\u0007\u0003OAq!a\f\r\t\u001b\t\t\u0004C\u0004\u0002H1!\t!!\u0013\t\u000f\u0005-C\u0002\"\u0001\u0002N!9\u00111\u000b\u0007\u0005\u0002\u0005U\u0003bBA.\u0019\u0011\u0005\u0011Q\f\u0005\b\u0003CbA\u0011AA2\u0011%\ty\u0007DI\u0001\n\u0003\t\tH\u0001\u0006XK2dG\u0007\u000e\u001b:o\tT!!\u000b\u0016\u0002\u0007ItwM\u0003\u0002,Y\u00051!/\u00198e_6T\u0011!L\u0001\u0006gBL'/Z\u0002\u0001'\t\u0001\u0001\u0007\u0005\u00022e5\t!&\u0003\u00024U\t\t\u0012J\u001c;CCN,GmR3oKJ\fGo\u001c:\u0002\u000bM$\u0018\r^3\u0011\u0007YJ4(D\u00018\u0015\u0005A\u0014!B:dC2\f\u0017B\u0001\u001e8\u0005\u0015\t%O]1z!\t1D(\u0003\u0002>o\t\u0019\u0011J\u001c;\u0002\u0005%\u0004\u0014A\u0002\u001fj]&$h\bF\u0002B\u0007\u0012\u0003\"A\u0011\u0001\u000e\u0003!BQ\u0001N\u0002A\u0002UBQAP\u0002A\u0002m\n\u0011![\u000b\u0002w\u0005)\u0011n\u0018\u0013fcR\u0011\u0011\n\u0014\t\u0003m)K!aS\u001c\u0003\tUs\u0017\u000e\u001e\u0005\b\u001b\u0016\t\t\u00111\u0001<\u0003\rAH%M\u0001\u0003S\u0002\n\u0001bY8qs&s\u0017\u000e^\u000b\u0002\u0003\u0006aq-\u001a;TK\u0016$')\u001f;fgV\t1\u000bE\u00027sQ\u0003\"AN+\n\u0005Y;$\u0001\u0002\"zi\u0016\fAb]3u'\u0016,GMQ=uKN$\"!S-\t\u000biK\u0001\u0019A*\u0002\u000b\tLH/Z:\u0002\u000f9,\u0007\u0010^%oiR\t1(\u0001\u0006XK2dG\u0007\u000e\u001b:o\t\u0004\"A\u0011\u0007\u0014\u00071\u00017\r\u0005\u00027C&\u0011!m\u000e\u0002\u0007\u0003:L(+\u001a4\u0011\tE\"\u0017IZ\u0005\u0003K*\u0012!cR3oKJ\fGo\u001c:D_6\u0004\u0018M\\5p]B!agZ\u001b<\u0013\tAwG\u0001\u0004UkBdWM\r\u000b\u0002=\u0006IQ\u000b\u001d9fe6\u000b7o[\u0001\u000b+B\u0004XM]'bg.\u0004\u0003FA\bn!\t1d.\u0003\u0002po\t1\u0011N\u001c7j]\u0016\f\u0011\u0002T8xKJl\u0015m]6\u0002\u00151{w/\u001a:NCN\\\u0007\u0005\u000b\u0002\u0012[\u00069A+Z7qKJ\u0014\u0015\u0001\u0003+f[B,'O\u0011\u0011)\u0005Mi\u0017a\u0002+f[B,'oQ\u0001\t)\u0016l\u0007/\u001a:DA!\u0012Q#\\\u0001\u0002\u0017\u0006\u00111\n\t\u0015\u0003/5\f\u0011AU\u0001\u0003%\u0002B#!G7\u0002\u000b\tKF+R*\u0002\r\tKF+R*!Q\tYR.A\u0004nCR\u0004\u0004o\\:\u0015\u000bm\nI!!\u0004\t\r\u0005-A\u00041\u0001<\u0003\u0005!\bBBA\b9\u0001\u00071(A\u0001wQ\taR.A\u0004nCR\u0004d.Z4\u0015\u000bm\n9\"!\u0007\t\r\u0005-Q\u00041\u0001<\u0011\u0019\ty!\ba\u0001w!\u0012Q$\\\u0001\u0005[\u0006$\u0018\u0007F\u0002<\u0003CAa!a\u0004\u001f\u0001\u0004Y\u0004F\u0001\u0010n\u0003\u001di\u0017\r^\u001aoK\u001e$RaOA\u0015\u0003WAa!a\u0003 \u0001\u0004Y\u0004BBA\b?\u0001\u00071\b\u000b\u0002 [\u0006!Q.\u0019;6)-Y\u00141GA\u001c\u0003w\ty$a\u0011\t\r\u0005U\u0002\u00051\u0001<\u0003\u0005\u0011\bBBA\u001dA\u0001\u00071(A\u0001b\u0011\u0019\ti\u0004\ta\u0001w\u0005\u0011Am\u001d\u0005\u0007\u0003\u0003\u0002\u0003\u0019A\u001e\u0002\u0005\u0011$\bBBA\bA\u0001\u00071\b\u000b\u0002![\u0006Q!/\u00198e_6\u001cV-\u001a3\u0015\u0003\u0019\f\u0001B\u001a:p[N+W\r\u001a\u000b\u0004\u0003\u0006=\u0003BBA)E\u0001\u0007a-\u0001\u0003tK\u0016$\u0017!\u00034s_6\f%O]1z)\r\t\u0015q\u000b\u0005\u0007\u00033\u001a\u0003\u0019A\u001b\u0002\u0007\u0005\u0014(/A\u0005ge>l')\u001f;fgR\u0019\u0011)a\u0018\t\u000bi#\u0003\u0019A*\u0002\u0011\u0019\u0014x.\u001c+j[\u0016$2!QA3\u0011%\t9'\nI\u0001\u0002\u0004\tI'\u0001\u0003uS6,\u0007c\u0001\u001c\u0002l%\u0019\u0011QN\u001c\u0003\t1{gnZ\u0001\u0013MJ|W\u000eV5nK\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0002t)\"\u0011\u0011NA;W\t\t9\b\u0005\u0003\u0002z\u0005\rUBAA>\u0015\u0011\ti(a \u0002\u0013Ut7\r[3dW\u0016$'bAAAo\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005\u0015\u00151\u0010\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0007"
)
public final class Well44497b extends IntBasedGenerator {
   private final int[] state;
   private int i;

   public static long fromTime$default$1() {
      return Well44497b$.MODULE$.fromTime$default$1();
   }

   public static Well44497b fromTime(final long time) {
      return Well44497b$.MODULE$.fromTime(time);
   }

   public static Well44497b fromBytes(final byte[] bytes) {
      return Well44497b$.MODULE$.fromBytes(bytes);
   }

   public static Well44497b fromArray(final int[] arr) {
      return Well44497b$.MODULE$.fromArray(arr);
   }

   public static Well44497b fromSeed(final Tuple2 seed) {
      return Well44497b$.MODULE$.fromSeed(seed);
   }

   public static Tuple2 randomSeed() {
      return Well44497b$.MODULE$.randomSeed();
   }

   public static Object apply(final Object seed) {
      return Well44497b$.MODULE$.apply(seed);
   }

   public static Object apply() {
      return Well44497b$.MODULE$.apply();
   }

   private int i() {
      return this.i;
   }

   private void i_$eq(final int x$1) {
      this.i = x$1;
   }

   public Well44497b copyInit() {
      return new Well44497b((int[])this.state.clone(), this.i());
   }

   public byte[] getSeedBytes() {
      byte[] bytes = new byte[Well44497b$.MODULE$.spire$random$rng$Well44497b$$BYTES()];
      ByteBuffer bb = ByteBuffer.wrap(bytes);

      for(int index$macro$1 = 0; index$macro$1 < Well44497b$.MODULE$.spire$random$rng$Well44497b$$R(); ++index$macro$1) {
         bb.putInt(this.state[index$macro$1]);
      }

      bb.putInt(this.i());
      return bytes;
   }

   public void setSeedBytes(final byte[] bytes) {
      byte[] bs = bytes.length < Well44497b$.MODULE$.spire$random$rng$Well44497b$$BYTES() ? Arrays.copyOf(bytes, Well44497b$.MODULE$.spire$random$rng$Well44497b$$BYTES()) : bytes;
      ByteBuffer bb = ByteBuffer.wrap(bs);

      for(int index$macro$1 = 0; index$macro$1 < Well44497b$.MODULE$.spire$random$rng$Well44497b$$R(); ++index$macro$1) {
         this.state[index$macro$1] = bb.getInt();
      }

      this.i_$eq(bb.getInt());
   }

   public int nextInt() {
      int z0 = this.state[Well44497abIndexCache$.MODULE$.vrm1()[this.i()]] & Well44497b$.MODULE$.spire$random$rng$Well44497b$$LowerMask() | this.state[Well44497abIndexCache$.MODULE$.vrm2()[this.i()]] & Well44497b$.MODULE$.spire$random$rng$Well44497b$$UpperMask();
      int z1 = Well44497b$.MODULE$.spire$random$rng$Well44497b$$mat0neg(-24, this.state[this.i()]) ^ Well44497b$.MODULE$.spire$random$rng$Well44497b$$mat0pos(30, this.state[Well44497abIndexCache$.MODULE$.vm1()[this.i()]]);
      int z2 = Well44497b$.MODULE$.spire$random$rng$Well44497b$$mat0neg(-10, this.state[Well44497abIndexCache$.MODULE$.vm2()[this.i()]]) ^ Well44497b$.MODULE$.spire$random$rng$Well44497b$$mat3neg(-26, this.state[Well44497abIndexCache$.MODULE$.vm3()[this.i()]]);
      this.state[this.i()] = z1 ^ z2;
      this.state[Well44497abIndexCache$.MODULE$.vrm1()[this.i()]] = Well44497b$.MODULE$.spire$random$rng$Well44497b$$mat1(z0) ^ Well44497b$.MODULE$.spire$random$rng$Well44497b$$mat0pos(20, z1) ^ Well44497b$.MODULE$.spire$random$rng$Well44497b$$mat5(9, -1221985044, -67108865, 131072, z2) ^ Well44497b$.MODULE$.spire$random$rng$Well44497b$$mat1(this.state[this.i()]);
      this.i_$eq(Well44497abIndexCache$.MODULE$.vrm1()[this.i()]);
      int t0 = this.state[this.i()];
      int t1 = t0 ^ t0 << 7 & Well44497b$.MODULE$.spire$random$rng$Well44497b$$TemperB();
      int t2 = t1 ^ t1 << 15 & Well44497b$.MODULE$.spire$random$rng$Well44497b$$TemperC();
      return t2;
   }

   public Well44497b(final int[] state, final int i0) {
      this.state = state;
      this.i = i0;
   }
}
