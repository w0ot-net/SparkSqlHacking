package spire.random;

import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00114q!\u0003\u0006\u0011\u0002\u0007\u0005q\u0002C\u0003\u0018\u0001\u0011\u0005\u0001\u0004C\u0003\u001d\u0001\u0019\u0005Q\u0004C\u0003=\u0001\u0019\u0005Q\bC\u0003J\u0001\u0019\u0005!\nC\u0003N\u0001\u0019\u0005a\nC\u0004U\u0001E\u0005I\u0011A+\t\u000b\u0001\u0004AQA1\t\u000b\u0001\u0004AQ\u00012\u0003%\u001d+g.\u001a:bi>\u00148i\\7qC:LwN\u001c\u0006\u0003\u00171\taA]1oI>l'\"A\u0007\u0002\u000bM\u0004\u0018N]3\u0004\u0001U\u0019\u0001c\u0010\u0011\u0014\u0005\u0001\t\u0002C\u0001\n\u0016\u001b\u0005\u0019\"\"\u0001\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Y\u0019\"AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u00023A\u0011!CG\u0005\u00037M\u0011A!\u00168ji\u0006Q!/\u00198e_6\u001cV-\u001a3\u0015\u0003y\u0001\"a\b\u0011\r\u0001\u0011I\u0011\u0005\u0001Q\u0001\u0002\u0003\u0015\rA\t\u0002\u0002'F\u00111E\n\t\u0003%\u0011J!!J\n\u0003\u000f9{G\u000f[5oOB\u0011!cJ\u0005\u0003QM\u00111!\u00118zQ\u0011\u0001#&L\u001c\u0011\u0005IY\u0013B\u0001\u0017\u0014\u0005-\u0019\b/Z2jC2L'0\u001a32\u000b\rrs&\r\u0019\u000f\u0005Iy\u0013B\u0001\u0019\u0014\u0003\rIe\u000e^\u0019\u0005II2DC\u0004\u00024m5\tAG\u0003\u00026\u001d\u00051AH]8pizJ\u0011\u0001F\u0019\u0006GaJ4H\u000f\b\u0003%eJ!AO\n\u0002\t1{gnZ\u0019\u0005II2D#A\u0005ge>l')\u001f;fgR\u0011a(\u0011\t\u0003?}\"Q\u0001\u0011\u0001C\u0002\t\u0012\u0011a\u0012\u0005\u0006\u0005\u000e\u0001\raQ\u0001\u0006Ef$Xm\u001d\t\u0004%\u00113\u0015BA#\u0014\u0005\u0015\t%O]1z!\t\u0011r)\u0003\u0002I'\t!!)\u001f;f\u0003!1'o\\7TK\u0016$GC\u0001 L\u0011\u0015aE\u00011\u0001\u001f\u0003\u0011\u0019X-\u001a3\u0002\u0011\u0019\u0014x.\u001c+j[\u0016$\"AP(\t\u000fA+\u0001\u0013!a\u0001#\u0006!A/[7f!\t\u0011\"+\u0003\u0002T'\t!Aj\u001c8h\u0003I1'o\\7US6,G\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003YS#!U,,\u0003a\u0003\"!\u00170\u000e\u0003iS!a\u0017/\u0002\u0013Ut7\r[3dW\u0016$'BA/\u0014\u0003)\tgN\\8uCRLwN\\\u0005\u0003?j\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0003\u0015\t\u0007\u000f\u001d7z)\u0005qDC\u0001 d\u0011\u0015a\u0005\u00021\u0001\u001f\u0001"
)
public interface GeneratorCompanion {
   Object randomSeed();

   Object fromBytes(final byte[] bytes);

   Object fromSeed(final Object seed);

   Object fromTime(final long time);

   // $FF: synthetic method
   static long fromTime$default$1$(final GeneratorCompanion $this) {
      return $this.fromTime$default$1();
   }

   default long fromTime$default$1() {
      return System.nanoTime();
   }

   // $FF: synthetic method
   static Object apply$(final GeneratorCompanion $this) {
      return $this.apply();
   }

   default Object apply() {
      return this.fromTime(this.fromTime$default$1());
   }

   // $FF: synthetic method
   static Object apply$(final GeneratorCompanion $this, final Object seed) {
      return $this.apply(seed);
   }

   default Object apply(final Object seed) {
      return this.fromSeed(seed);
   }

   // $FF: synthetic method
   static int randomSeed$mcI$sp$(final GeneratorCompanion $this) {
      return $this.randomSeed$mcI$sp();
   }

   default int randomSeed$mcI$sp() {
      return BoxesRunTime.unboxToInt(this.randomSeed());
   }

   // $FF: synthetic method
   static long randomSeed$mcJ$sp$(final GeneratorCompanion $this) {
      return $this.randomSeed$mcJ$sp();
   }

   default long randomSeed$mcJ$sp() {
      return BoxesRunTime.unboxToLong(this.randomSeed());
   }

   // $FF: synthetic method
   static Object fromSeed$mcI$sp$(final GeneratorCompanion $this, final int seed) {
      return $this.fromSeed$mcI$sp(seed);
   }

   default Object fromSeed$mcI$sp(final int seed) {
      return this.fromSeed(BoxesRunTime.boxToInteger(seed));
   }

   // $FF: synthetic method
   static Object fromSeed$mcJ$sp$(final GeneratorCompanion $this, final long seed) {
      return $this.fromSeed$mcJ$sp(seed);
   }

   default Object fromSeed$mcJ$sp(final long seed) {
      return this.fromSeed(BoxesRunTime.boxToLong(seed));
   }

   // $FF: synthetic method
   static Object apply$mcI$sp$(final GeneratorCompanion $this, final int seed) {
      return $this.apply$mcI$sp(seed);
   }

   default Object apply$mcI$sp(final int seed) {
      return this.apply(BoxesRunTime.boxToInteger(seed));
   }

   // $FF: synthetic method
   static Object apply$mcJ$sp$(final GeneratorCompanion $this, final long seed) {
      return $this.apply$mcJ$sp(seed);
   }

   default Object apply$mcJ$sp(final long seed) {
      return this.apply(BoxesRunTime.boxToLong(seed));
   }

   static void $init$(final GeneratorCompanion $this) {
   }
}
