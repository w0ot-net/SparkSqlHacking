package spire.random.rng;

import scala.reflect.ScalaSignature;
import spire.random.LongBasedGenerator;
import spire.util.Pack.;

@ScalaSignature(
   bytes = "\u0006\u0005E4A!\u0006\f\u0003;!A!\u0005\u0001B\u0001B\u0003%1\u0005C\u0003*\u0001\u0011\u0005!\u0006C\u0004/\u0001\u0001\u0007I\u0011B\u0018\t\u000fA\u0002\u0001\u0019!C\u0005c!1q\u0007\u0001Q!\n\rBQ\u0001\u000f\u0001\u0005\u0002eBQA\u000f\u0001\u0005\u0002=BQa\u000f\u0001\u0005\u0002qBQa\u0010\u0001\u0005B\u0001CQa\u0012\u0001\u0005\u0002!CQa\u0013\u0001\u0005\u00021;Q!\u0014\f\t\u000293Q!\u0006\f\t\u0002=CQ!K\u0007\u0005\u0002YCQaV\u0007\u0005\u00021CQ\u0001W\u0007\u0005\u0002eCQaW\u0007\u0005\u0002qCQAX\u0007\u0005\u0002}CqAY\u0007\u0012\u0002\u0013\u00051\rC\u0003o\u001b\u0011\u0005qNA\u0003MG\u001e4DG\u0003\u0002\u00181\u0005\u0019!O\\4\u000b\u0005eQ\u0012A\u0002:b]\u0012|WNC\u0001\u001c\u0003\u0015\u0019\b/\u001b:f\u0007\u0001\u0019\"\u0001\u0001\u0010\u0011\u0005}\u0001S\"\u0001\r\n\u0005\u0005B\"A\u0005'p]\u001e\u0014\u0015m]3e\u000f\u0016tWM]1u_J\fQaX:fK\u0012\u0004\"\u0001J\u0014\u000e\u0003\u0015R\u0011AJ\u0001\u0006g\u000e\fG.Y\u0005\u0003Q\u0015\u0012A\u0001T8oO\u00061A(\u001b8jiz\"\"aK\u0017\u0011\u00051\u0002Q\"\u0001\f\t\u000b\t\u0012\u0001\u0019A\u0012\u0002\tM,W\rZ\u000b\u0002G\u0005A1/Z3e?\u0012*\u0017\u000f\u0006\u00023kA\u0011AeM\u0005\u0003i\u0015\u0012A!\u00168ji\"9a\u0007BA\u0001\u0002\u0004\u0019\u0013a\u0001=%c\u0005)1/Z3eA\u0005A1m\u001c9z\u0013:LG/F\u0001,\u0003\u001d9W\r^*fK\u0012\fqa]3u'\u0016,G\r\u0006\u00023{!)a\b\u0003a\u0001G\u0005\ta.\u0001\u0007hKR\u001cV-\u001a3CsR,7/F\u0001B!\r!#\tR\u0005\u0003\u0007\u0016\u0012Q!\u0011:sCf\u0004\"\u0001J#\n\u0005\u0019+#\u0001\u0002\"zi\u0016\fAb]3u'\u0016,GMQ=uKN$\"AM%\t\u000b)S\u0001\u0019A!\u0002\u000b\tLH/Z:\u0002\u00119,\u0007\u0010\u001e'p]\u001e$\u0012aI\u0001\u0006\u0019\u000e<g\u0007\u000e\t\u0003Y5\u00192!\u0004)T!\t!\u0013+\u0003\u0002SK\t1\u0011I\\=SK\u001a\u0004Ba\b+,G%\u0011Q\u000b\u0007\u0002\u0013\u000f\u0016tWM]1u_J\u001cu.\u001c9b]&|g\u000eF\u0001O\u0003)\u0011\u0018M\u001c3p[N+W\rZ\u0001\nMJ|WNQ=uKN$\"a\u000b.\t\u000b)\u0003\u0002\u0019A!\u0002\u0011\u0019\u0014x.\\*fK\u0012$\"aK/\t\u000b9\n\u0002\u0019A\u0012\u0002\u0011\u0019\u0014x.\u001c+j[\u0016$\"a\u000b1\t\u000f\u0005\u0014\u0002\u0013!a\u0001G\u0005!A/[7f\u0003I1'o\\7US6,G\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003\u0011T#aI3,\u0003\u0019\u0004\"a\u001a7\u000e\u0003!T!!\u001b6\u0002\u0013Ut7\r[3dW\u0016$'BA6&\u0003)\tgN\\8uCRLwN\\\u0005\u0003[\"\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0003\u0011\u0019H/\u001a9\u0015\u0005\r\u0002\b\"\u0002 \u0015\u0001\u0004\u0019\u0003"
)
public final class Lcg64 extends LongBasedGenerator {
   private long seed;

   public static long step(final long n) {
      return Lcg64$.MODULE$.step(n);
   }

   public static long fromTime$default$1() {
      return Lcg64$.MODULE$.fromTime$default$1();
   }

   public static Lcg64 fromTime(final long time) {
      return Lcg64$.MODULE$.fromTime(time);
   }

   public static Lcg64 fromSeed(final long seed) {
      return Lcg64$.MODULE$.fromSeed(seed);
   }

   public static Lcg64 fromBytes(final byte[] bytes) {
      return Lcg64$.MODULE$.fromBytes(bytes);
   }

   public static long randomSeed() {
      return Lcg64$.MODULE$.randomSeed();
   }

   public static Object apply(final Object seed) {
      return Lcg64$.MODULE$.apply(seed);
   }

   public static Object apply() {
      return Lcg64$.MODULE$.apply();
   }

   private long seed() {
      return this.seed;
   }

   private void seed_$eq(final long x$1) {
      this.seed = x$1;
   }

   public Lcg64 copyInit() {
      return new Lcg64(this.seed());
   }

   public long getSeed() {
      return this.seed();
   }

   public void setSeed(final long n) {
      this.seed_$eq(n);
   }

   public byte[] getSeedBytes() {
      return .MODULE$.longToBytes(this.seed());
   }

   public void setSeedBytes(final byte[] bytes) {
      this.seed_$eq(.MODULE$.longFromBytes(bytes));
   }

   public long nextLong() {
      this.seed_$eq(6364136223846793005L * this.seed() + 1442695040888963407L);
      return this.seed();
   }

   public Lcg64(final long _seed) {
      this.seed = _seed;
   }
}
