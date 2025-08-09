package spire.random.rng;

import java.nio.ByteBuffer;
import java.util.Arrays;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import spire.random.IntBasedGenerator;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ec\u0001\u0002\u0012$\u0005)B\u0001b\f\u0001\u0003\u0002\u0003\u0006I\u0001\r\u0005\ts\u0001\u0011\t\u0011)A\u0005m!1!\b\u0001C\tKmBq\u0001\u0011\u0001A\u0002\u0013%\u0011\tC\u0004C\u0001\u0001\u0007I\u0011B\"\t\r%\u0003\u0001\u0015)\u00037\u0011\u0015Q\u0005\u0001\"\u0001L\u0011\u0015a\u0005\u0001\"\u0001N\u0011\u0015\u0011\u0006\u0001\"\u0001T\u0011\u00151\u0006\u0001\"\u0001X\u000f\u0015A6\u0005#\u0001Z\r\u0015\u00113\u0005#\u0001[\u0011\u0015QD\u0002\"\u0001e\u0011\u001d)GB1A\u0005\n\u0005CaA\u001a\u0007!\u0002\u00131\u0004bB6\r\u0005\u0004%I!\u0011\u0005\u0007Y2\u0001\u000b\u0011\u0002\u001c\t\u000f9d!\u0019!C\u0007\u0003\"1q\u000e\u0004Q\u0001\u000eYBq!\u001d\u0007C\u0002\u00135\u0011\t\u0003\u0004s\u0019\u0001\u0006iA\u000e\u0005\bi2\u0011\r\u0011\"\u0004B\u0011\u0019)H\u0002)A\u0007m!)q\u000f\u0004C\u0007q\")a\u0010\u0004C\u0007\u007f\"9\u0011q\u0001\u0007\u0005\u000e\u0005%\u0001bBA\b\u0019\u00115\u0011\u0011\u0003\u0005\b\u00033aA\u0011AA\u000e\u0011\u001d\ti\u0002\u0004C\u0001\u0003?Aq!!\n\r\t\u0003\t9\u0003C\u0004\u0002.1!\t!a\f\t\u000f\u0005MB\u0002\"\u0001\u00026!I\u0011\u0011\t\u0007\u0012\u0002\u0013\u0005\u00111\t\u0002\u000b/\u0016dG.M\u001d:g]\n'B\u0001\u0013&\u0003\r\u0011hn\u001a\u0006\u0003M\u001d\naA]1oI>l'\"\u0001\u0015\u0002\u000bM\u0004\u0018N]3\u0004\u0001M\u0011\u0001a\u000b\t\u0003Y5j\u0011!J\u0005\u0003]\u0015\u0012\u0011#\u00138u\u0005\u0006\u001cX\rZ$f]\u0016\u0014\u0018\r^8s\u0003\u0015\u0019H/\u0019;f!\r\tDGN\u0007\u0002e)\t1'A\u0003tG\u0006d\u0017-\u0003\u00026e\t)\u0011I\u001d:bsB\u0011\u0011gN\u0005\u0003qI\u00121!\u00138u\u0003\tI\u0007'\u0001\u0004=S:LGO\u0010\u000b\u0004yyz\u0004CA\u001f\u0001\u001b\u0005\u0019\u0003\"B\u0018\u0004\u0001\u0004\u0001\u0004\"B\u001d\u0004\u0001\u00041\u0014!A5\u0016\u0003Y\nQ![0%KF$\"\u0001R$\u0011\u0005E*\u0015B\u0001$3\u0005\u0011)f.\u001b;\t\u000f!+\u0011\u0011!a\u0001m\u0005\u0019\u0001\u0010J\u0019\u0002\u0005%\u0004\u0013\u0001C2pafLe.\u001b;\u0016\u0003q\nAbZ3u'\u0016,GMQ=uKN,\u0012A\u0014\t\u0004cQz\u0005CA\u0019Q\u0013\t\t&G\u0001\u0003CsR,\u0017\u0001D:fiN+W\r\u001a\"zi\u0016\u001cHC\u0001#U\u0011\u0015)\u0016\u00021\u0001O\u0003\u0015\u0011\u0017\u0010^3t\u0003\u001dqW\r\u001f;J]R$\u0012AN\u0001\u000b/\u0016dG.M\u001d:g]\n\u0007CA\u001f\r'\ra1L\u0018\t\u0003cqK!!\u0018\u001a\u0003\r\u0005s\u0017PU3g!\u0011as\fP1\n\u0005\u0001,#AE$f]\u0016\u0014\u0018\r^8s\u0007>l\u0007/\u00198j_:\u0004B!\r21m%\u00111M\r\u0002\u0007)V\u0004H.\u001a\u001a\u0015\u0003e\u000b\u0011\"\u00169qKJl\u0015m]6\u0002\u0015U\u0003\b/\u001a:NCN\\\u0007\u0005\u000b\u0002\u0010QB\u0011\u0011'[\u0005\u0003UJ\u0012a!\u001b8mS:,\u0017!\u0003'po\u0016\u0014X*Y:l\u0003)aun^3s\u001b\u0006\u001c8\u000e\t\u0015\u0003#!\f\u0011aS\u0001\u0003\u0017\u0002B#a\u00055\u0002\u0003I\u000b!A\u0015\u0011)\u0005UA\u0017!\u0002\"Z)\u0016\u001b\u0016A\u0002\"Z)\u0016\u001b\u0006\u0005\u000b\u0002\u0018Q\u00069Q.\u0019;1a>\u001cHc\u0001\u001czw\")!\u0010\u0007a\u0001m\u0005\tA\u000fC\u0003}1\u0001\u0007a'A\u0001wQ\tA\u0002.A\u0004nCR\u0004d.Z4\u0015\u000bY\n\t!a\u0001\t\u000biL\u0002\u0019\u0001\u001c\t\u000bqL\u0002\u0019\u0001\u001c)\u0005eA\u0017\u0001B7biF\"2ANA\u0006\u0011\u0015a(\u00041\u00017Q\tQ\u0002.A\u0004nCR\u001c\u0004o\\:\u0015\u000bY\n\u0019\"!\u0006\t\u000bi\\\u0002\u0019\u0001\u001c\t\u000bq\\\u0002\u0019\u0001\u001c)\u0005mA\u0017A\u0003:b]\u0012|WnU3fIR\t\u0011-\u0001\u0005ge>l7+Z3e)\ra\u0014\u0011\u0005\u0005\u0007\u0003Gi\u0002\u0019A1\u0002\tM,W\rZ\u0001\nMJ|W.\u0011:sCf$2\u0001PA\u0015\u0011\u0019\tYC\ba\u0001a\u0005\u0019\u0011M\u001d:\u0002\u0013\u0019\u0014x.\u001c\"zi\u0016\u001cHc\u0001\u001f\u00022!)Qk\ba\u0001\u001d\u0006AaM]8n)&lW\rF\u0002=\u0003oA\u0011\"!\u000f!!\u0003\u0005\r!a\u000f\u0002\tQLW.\u001a\t\u0004c\u0005u\u0012bAA e\t!Aj\u001c8h\u0003I1'o\\7US6,G\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005\u0015#\u0006BA\u001e\u0003\u000fZ#!!\u0013\u0011\t\u0005-\u0013QK\u0007\u0003\u0003\u001bRA!a\u0014\u0002R\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003'\u0012\u0014AC1o]>$\u0018\r^5p]&!\u0011qKA'\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a"
)
public final class Well19937a extends IntBasedGenerator {
   private final int[] state;
   private int i;

   public static long fromTime$default$1() {
      return Well19937a$.MODULE$.fromTime$default$1();
   }

   public static Well19937a fromTime(final long time) {
      return Well19937a$.MODULE$.fromTime(time);
   }

   public static Well19937a fromBytes(final byte[] bytes) {
      return Well19937a$.MODULE$.fromBytes(bytes);
   }

   public static Well19937a fromArray(final int[] arr) {
      return Well19937a$.MODULE$.fromArray(arr);
   }

   public static Well19937a fromSeed(final Tuple2 seed) {
      return Well19937a$.MODULE$.fromSeed(seed);
   }

   public static Tuple2 randomSeed() {
      return Well19937a$.MODULE$.randomSeed();
   }

   public static Object apply(final Object seed) {
      return Well19937a$.MODULE$.apply(seed);
   }

   public static Object apply() {
      return Well19937a$.MODULE$.apply();
   }

   private int i() {
      return this.i;
   }

   private void i_$eq(final int x$1) {
      this.i = x$1;
   }

   public Well19937a copyInit() {
      return new Well19937a((int[])this.state.clone(), this.i());
   }

   public byte[] getSeedBytes() {
      byte[] bytes = new byte[Well19937a$.MODULE$.spire$random$rng$Well19937a$$BYTES()];
      ByteBuffer bb = ByteBuffer.wrap(bytes);

      for(int index$macro$1 = 0; index$macro$1 < Well19937a$.MODULE$.spire$random$rng$Well19937a$$R(); ++index$macro$1) {
         bb.putInt(this.state[index$macro$1]);
      }

      bb.putInt(this.i());
      return bytes;
   }

   public void setSeedBytes(final byte[] bytes) {
      byte[] bs = bytes.length < Well19937a$.MODULE$.spire$random$rng$Well19937a$$BYTES() ? Arrays.copyOf(bytes, Well19937a$.MODULE$.spire$random$rng$Well19937a$$BYTES()) : bytes;
      ByteBuffer bb = ByteBuffer.wrap(bs);

      for(int index$macro$1 = 0; index$macro$1 < Well19937a$.MODULE$.spire$random$rng$Well19937a$$R(); ++index$macro$1) {
         this.state[index$macro$1] = bb.getInt();
      }

      this.i_$eq(bb.getInt());
   }

   public int nextInt() {
      int z0 = this.state[Well19937acIndexCache$.MODULE$.vrm1()[this.i()]] & Well19937a$.MODULE$.spire$random$rng$Well19937a$$LowerMask() | this.state[Well19937acIndexCache$.MODULE$.vrm2()[this.i()]] & Well19937a$.MODULE$.spire$random$rng$Well19937a$$UpperMask();
      int z1 = Well19937a$.MODULE$.spire$random$rng$Well19937a$$mat0neg(-25, this.state[this.i()]) ^ Well19937a$.MODULE$.spire$random$rng$Well19937a$$mat0pos(27, this.state[Well19937acIndexCache$.MODULE$.vm1()[this.i()]]);
      int z2 = Well19937a$.MODULE$.spire$random$rng$Well19937a$$mat3pos(9, this.state[Well19937acIndexCache$.MODULE$.vm2()[this.i()]]) ^ Well19937a$.MODULE$.spire$random$rng$Well19937a$$mat0pos(1, this.state[Well19937acIndexCache$.MODULE$.vm3()[this.i()]]);
      this.state[this.i()] = z1 ^ z2;
      this.state[Well19937acIndexCache$.MODULE$.vrm1()[this.i()]] = Well19937a$.MODULE$.spire$random$rng$Well19937a$$mat1(z0) ^ Well19937a$.MODULE$.spire$random$rng$Well19937a$$mat0neg(-9, z1) ^ Well19937a$.MODULE$.spire$random$rng$Well19937a$$mat0neg(-21, z2) ^ Well19937a$.MODULE$.spire$random$rng$Well19937a$$mat0pos(21, this.state[this.i()]);
      this.i_$eq(Well19937acIndexCache$.MODULE$.vrm1()[this.i()]);
      return this.state[this.i()];
   }

   public Well19937a(final int[] state, final int i0) {
      this.state = state;
      this.i = i0;
   }
}
