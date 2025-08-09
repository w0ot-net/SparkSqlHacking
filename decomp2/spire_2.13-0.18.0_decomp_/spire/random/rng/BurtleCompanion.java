package spire.random.rng;

import java.nio.ByteBuffer;
import java.util.Arrays;
import scala.reflect.ScalaSignature;
import spire.random.GeneratorCompanion;
import spire.random.GlobalRng$;

@ScalaSignature(
   bytes = "\u0006\u0005q3Q\u0001C\u0005\u0002\u0002AAQA\f\u0001\u0005\u0002=BQ!\r\u0001\u0007\u0012IBQa\u000f\u0001\u0005BqBQ!\u0010\u0001\u0005\u0002yBQ!\u0012\u0001\u0005\u0002\u0019CQ!\u0013\u0001\u0005\u0002)Cq\u0001\u0015\u0001\u0012\u0002\u0013\u0005\u0011KA\bCkJ$H.Z\"p[B\fg.[8o\u0015\tQ1\"A\u0002s]\u001eT!\u0001D\u0007\u0002\rI\fg\u000eZ8n\u0015\u0005q\u0011!B:qSJ,7\u0001A\u000b\u0003#y\u00192\u0001\u0001\n\u0019!\t\u0019b#D\u0001\u0015\u0015\u0005)\u0012!B:dC2\f\u0017BA\f\u0015\u0005\u0019\te.\u001f*fMB!\u0011D\u0007\u000f)\u001b\u0005Y\u0011BA\u000e\f\u0005I9UM\\3sCR|'oQ8na\u0006t\u0017n\u001c8\u0011\u0005uqB\u0002\u0001\u0003\u0006?\u0001\u0011\r\u0001\t\u0002\u0002\u000fF\u0011\u0011\u0005\n\t\u0003'\tJ!a\t\u000b\u0003\u000f9{G\u000f[5oOB\u0011QEJ\u0007\u0002\u0013%\u0011q%\u0003\u0002\f\u0005V\u0014H\u000f\\3S_R\u001c$\u0007E\u0002\u0014S-J!A\u000b\u000b\u0003\u000b\u0005\u0013(/Y=\u0011\u0005Ma\u0013BA\u0017\u0015\u0005\rIe\u000e^\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003A\u00022!\n\u0001\u001d\u0003\u0019\u0019'/Z1uKR)AdM\u001b8s!)AG\u0001a\u0001W\u0005\u0011q,\u0019\u0005\u0006m\t\u0001\raK\u0001\u0003?\nDQ\u0001\u000f\u0002A\u0002-\n!aX2\t\u000bi\u0012\u0001\u0019A\u0016\u0002\u0005}#\u0017A\u0003:b]\u0012|WnU3fIR\t\u0001&A\u0005ge>l')\u001f;fgR\u0011Ad\u0010\u0005\u0006\u0001\u0012\u0001\r!Q\u0001\u0006Ef$Xm\u001d\t\u0004'%\u0012\u0005CA\nD\u0013\t!EC\u0001\u0003CsR,\u0017\u0001\u00034s_6\u001cV-\u001a3\u0015\u0005q9\u0005\"\u0002%\u0006\u0001\u0004A\u0013\u0001B5oiN\f\u0001B\u001a:p[RKW.\u001a\u000b\u00039-Cq\u0001\u0014\u0004\u0011\u0002\u0003\u0007Q*\u0001\u0003uS6,\u0007CA\nO\u0013\tyEC\u0001\u0003M_:<\u0017A\u00054s_6$\u0016.\\3%I\u00164\u0017-\u001e7uIE*\u0012A\u0015\u0016\u0003\u001bN[\u0013\u0001\u0016\t\u0003+jk\u0011A\u0016\u0006\u0003/b\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005e#\u0012AC1o]>$\u0018\r^5p]&\u00111L\u0016\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0007"
)
public abstract class BurtleCompanion implements GeneratorCompanion {
   public int randomSeed$mcI$sp() {
      return GeneratorCompanion.randomSeed$mcI$sp$(this);
   }

   public long randomSeed$mcJ$sp() {
      return GeneratorCompanion.randomSeed$mcJ$sp$(this);
   }

   public Object fromSeed$mcI$sp(final int seed) {
      return GeneratorCompanion.fromSeed$mcI$sp$(this, seed);
   }

   public Object fromSeed$mcJ$sp(final long seed) {
      return GeneratorCompanion.fromSeed$mcJ$sp$(this, seed);
   }

   public final Object apply() {
      return GeneratorCompanion.apply$(this);
   }

   public Object apply(final Object seed) {
      return GeneratorCompanion.apply$(this, seed);
   }

   public Object apply$mcI$sp(final int seed) {
      return GeneratorCompanion.apply$mcI$sp$(this, seed);
   }

   public Object apply$mcJ$sp(final long seed) {
      return GeneratorCompanion.apply$mcJ$sp$(this, seed);
   }

   public abstract BurtleRot32 create(final int _a, final int _b, final int _c, final int _d);

   public int[] randomSeed() {
      return GlobalRng$.MODULE$.generateInts(4);
   }

   public BurtleRot32 fromBytes(final byte[] bytes) {
      byte[] bs = bytes.length < 16 ? Arrays.copyOf(bytes, 16) : bytes;
      ByteBuffer bb = ByteBuffer.wrap(bs);
      return this.create(bb.getInt(), bb.getInt(), bb.getInt(), bb.getInt());
   }

   public BurtleRot32 fromSeed(final int[] ints) {
      int[] zs = ints.length < 4 ? Arrays.copyOf(ints, 4) : ints;
      return this.create(zs[0], zs[1], zs[2], zs[3]);
   }

   public BurtleRot32 fromTime(final long time) {
      Lcg64 lcg = Lcg64$.MODULE$.fromTime(time);
      return this.create(lcg.nextInt(), lcg.nextInt(), lcg.nextInt(), lcg.nextInt());
   }

   public long fromTime$default$1() {
      return System.nanoTime();
   }

   public BurtleCompanion() {
      GeneratorCompanion.$init$(this);
   }
}
