package spire.random.rng;

import scala.reflect.ScalaSignature;
import spire.random.LongBasedGenerator;
import spire.util.Pack.;

@ScalaSignature(
   bytes = "\u0006\u0005\u00194AAE\n\u00035!Aq\u0004\u0001B\u0001B\u0003%\u0001\u0005C\u0003'\u0001\u0011\u0005q\u0005\u0003\u0004,\u0001\u0001\u0006K\u0001\t\u0005\u0006Y\u0001!\t!\f\u0005\u0006]\u0001!\ta\f\u0005\u0006a\u0001!\t!\r\u0005\u0006o\u0001!\t\u0005\u000f\u0005\u0006\u007f\u0001!\t\u0001\u0011\u0005\u0006\u0007\u0002!\t\u0001R\u0004\u0006\u000bNA\tA\u0012\u0004\u0006%MA\ta\u0012\u0005\u0006M-!\tA\u0014\u0005\u0006\u001f.!\t\u0001\u0012\u0005\u0006!.!\t!\u0015\u0005\u0006'.!\t\u0001\u0016\u0005\u0006-.!\ta\u0016\u0005\b5.\t\n\u0011\"\u0001\\\u0005\u0019\u0019VM]5bY*\u0011A#F\u0001\u0004e:<'B\u0001\f\u0018\u0003\u0019\u0011\u0018M\u001c3p[*\t\u0001$A\u0003ta&\u0014Xm\u0001\u0001\u0014\u0005\u0001Y\u0002C\u0001\u000f\u001e\u001b\u0005)\u0012B\u0001\u0010\u0016\u0005IauN\\4CCN,GmR3oKJ\fGo\u001c:\u0002\u000bM,W\r\u001a\u0019\u0011\u0005\u0005\"S\"\u0001\u0012\u000b\u0003\r\nQa]2bY\u0006L!!\n\u0012\u0003\t1{gnZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005!R\u0003CA\u0015\u0001\u001b\u0005\u0019\u0002\"B\u0010\u0003\u0001\u0004\u0001\u0013\u0001B:fK\u0012\f\u0001bY8qs&s\u0017\u000e^\u000b\u0002Q\u00059q-\u001a;TK\u0016$W#\u0001\u0011\u0002\u000fM,GoU3fIR\u0011!'\u000e\t\u0003CMJ!\u0001\u000e\u0012\u0003\tUs\u0017\u000e\u001e\u0005\u0006m\u0019\u0001\r\u0001I\u0001\u0002]\u0006aq-\u001a;TK\u0016$')\u001f;fgV\t\u0011\bE\u0002\"uqJ!a\u000f\u0012\u0003\u000b\u0005\u0013(/Y=\u0011\u0005\u0005j\u0014B\u0001 #\u0005\u0011\u0011\u0015\u0010^3\u0002\u0019M,GoU3fI\nKH/Z:\u0015\u0005I\n\u0005\"\u0002\"\t\u0001\u0004I\u0014!\u00022zi\u0016\u001c\u0018\u0001\u00038fqRduN\\4\u0015\u0003\u0001\naaU3sS\u0006d\u0007CA\u0015\f'\rY\u0001j\u0013\t\u0003C%K!A\u0013\u0012\u0003\r\u0005s\u0017PU3g!\u0011aB\n\u000b\u0011\n\u00055+\"AE$f]\u0016\u0014\u0018\r^8s\u0007>l\u0007/\u00198j_:$\u0012AR\u0001\u000be\u0006tGm\\7TK\u0016$\u0017!\u00034s_6\u0014\u0015\u0010^3t)\tA#\u000bC\u0003C\u001d\u0001\u0007\u0011(\u0001\u0005ge>l7+Z3e)\tAS\u000bC\u0003,\u001f\u0001\u0007\u0001%\u0001\u0005ge>lG+[7f)\tA\u0003\fC\u0004Z!A\u0005\t\u0019\u0001\u0011\u0002\tQLW.Z\u0001\u0013MJ|W\u000eV5nK\u0012\"WMZ1vYR$\u0013'F\u0001]U\t\u0001SlK\u0001_!\tyF-D\u0001a\u0015\t\t'-A\u0005v]\u000eDWmY6fI*\u00111MI\u0001\u000bC:tw\u000e^1uS>t\u0017BA3a\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a"
)
public final class Serial extends LongBasedGenerator {
   private long seed;

   public static long fromTime$default$1() {
      return Serial$.MODULE$.fromTime$default$1();
   }

   public static Serial fromTime(final long time) {
      return Serial$.MODULE$.fromTime(time);
   }

   public static Serial fromSeed(final long seed) {
      return Serial$.MODULE$.fromSeed(seed);
   }

   public static Serial fromBytes(final byte[] bytes) {
      return Serial$.MODULE$.fromBytes(bytes);
   }

   public static long randomSeed() {
      return Serial$.MODULE$.randomSeed();
   }

   public static Object apply(final Object seed) {
      return Serial$.MODULE$.apply(seed);
   }

   public static Object apply() {
      return Serial$.MODULE$.apply();
   }

   public Serial copyInit() {
      return new Serial(this.seed);
   }

   public long getSeed() {
      return this.seed;
   }

   public void setSeed(final long n) {
      this.seed = n;
   }

   public byte[] getSeedBytes() {
      return .MODULE$.longToBytes(this.seed);
   }

   public void setSeedBytes(final byte[] bytes) {
      this.seed = .MODULE$.longFromBytes(bytes);
   }

   public long nextLong() {
      ++this.seed;
      return this.seed;
   }

   public Serial(final long seed0) {
      this.seed = seed0;
   }
}
