package breeze.linalg.support;

import breeze.math.Field;
import breeze.math.Semiring;
import breeze.math.Semiring$;
import breeze.util.ArrayUtil$;
import java.lang.invoke.SerializedLambda;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uea\u0002\u0011\"!\u0003\r\n\u0001\u000b\u0005\u0006a\u00011\t!M\u0004\u0006\u0005\u0006B\ta\u0011\u0004\u0006A\u0005B\t!\u0012\u0005\u0006\r\u000e!\ta\u0012\u0004\u0005\u0011\u000e\u0001\u0011\n\u0003\u0005W\u000b\t\r\t\u0015a\u0003X\u0011!iVAaA!\u0002\u0017q\u0006\"\u0002$\u0006\t\u0003!\u0007\"\u0002\u0019\u0006\t\u0003Rg\u0001\u00027\u0004\u00015D\u0001\u0002\u001e\u0006\u0003\u0002\u0003\u0006Y!\u001e\u0005\ts*\u0011\t\u0011)A\u0006u\")aI\u0003C\u0001{\"1\u0001G\u0003C\u0001\u0003\u000bAq!a\u0003\u0004\t\u0007\ti\u0001C\u0004\u0002,\r!\u0019!!\f\b\u000f\u0005\r3\u0001c\u0001\u0002F\u00199\u0011qI\u0002\t\u0002\u0005%\u0003B\u0002$\u0013\t\u0003\t\u0019fB\u0004\u0002V\rA\u0019!a\u0016\u0007\u000f\u0005e3\u0001#\u0001\u0002\\!1a)\u0006C\u0001\u0003K:q!a\u001a\u0004\u0011\u0007\tIGB\u0004\u0002l\rA\t!!\u001c\t\r\u0019CB\u0011AA<\u000f\u001d\tIh\u0001E\u0002\u0003w2q!! \u0004\u0011\u0003\ty\b\u0003\u0004G7\u0011\u0005\u0011\u0011R\u0004\b\u0003\u0017\u001b\u00012AAG\r\u001d\tyi\u0001E\u0001\u0003#CaA\u0012\u0010\u0005\u0002\u0005m%AE\"b]\u000e\u0013X-\u0019;f5\u0016\u0014xn\u001d'jW\u0016T!AI\u0012\u0002\u000fM,\b\u000f]8si*\u0011A%J\u0001\u0007Y&t\u0017\r\\4\u000b\u0003\u0019\naA\u0019:fKj,7\u0001A\u000b\u0004S\u0001#4C\u0001\u0001+!\tYc&D\u0001-\u0015\u0005i\u0013!B:dC2\f\u0017BA\u0018-\u0005\u0019\te.\u001f*fM\u0006)\u0011\r\u001d9msR\u0011!'\u0010\t\u0003gQb\u0001\u0001\u0002\u00046\u0001\u0011\u0015\rA\u000e\u0002\u0003)>\f\"a\u000e\u001e\u0011\u0005-B\u0014BA\u001d-\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aK\u001e\n\u0005qb#aA!os\")a(\u0001a\u0001\u007f\u0005!aM]8n!\t\u0019\u0004\tB\u0003B\u0001\t\u0007aG\u0001\u0003Ge>l\u0017AE\"b]\u000e\u0013X-\u0019;f5\u0016\u0014xn\u001d'jW\u0016\u0004\"\u0001R\u0002\u000e\u0003\u0005\u001a\"a\u0001\u0016\u0002\rqJg.\u001b;?)\u0005\u0019%aB(q\u0003J\u0014\u0018-_\u000b\u0003\u0015B\u001b2!\u0002\u0016L!\u0011!\u0005\u0001\u0014'\u0011\u0007-ju*\u0003\u0002OY\t)\u0011I\u001d:bsB\u00111\u0007\u0015\u0003\n#\u0016\u0001\u000b\u0011!AC\u0002Y\u0012\u0011A\u0016\u0015\u0003!N\u0003\"a\u000b+\n\u0005Uc#aC:qK\u000eL\u0017\r\\5{K\u0012\f!\"\u001a<jI\u0016t7-\u001a\u00132!\rA6lT\u0007\u00023*\u0011!\fL\u0001\be\u00164G.Z2u\u0013\ta\u0016L\u0001\u0005DY\u0006\u001c8\u000fV1h\u0003))g/\u001b3f]\u000e,GE\r\t\u0004?\n|U\"\u00011\u000b\u0005\u0005,\u0013\u0001B7bi\"L!a\u00191\u0003\u0011M+W.\u001b:j]\u001e$\u0012!\u001a\u000b\u0004M\"L\u0007cA4\u0006\u001f6\t1\u0001C\u0003W\u0011\u0001\u000fq\u000bC\u0003^\u0011\u0001\u000fa\f\u0006\u0002MW\")a(\u0003a\u0001\u0019\nYq\n]'baZ\u000bG.^3t+\u0011q\u0017o^:\u0014\u0007)Qs\u000e\u0005\u0003E\u0001A\u0014\bCA\u001ar\t\u0015\t%B1\u00017!\t\u00194\u000fB\u00036\u0015\t\u0007a'\u0001\u0002paB\u0019qL\u0019<\u0011\u0005M:H!\u0002=\u000b\u0005\u00041$!A!\u0002\u00075\f\u0007\u000f\u0005\u0004EwB4hO]\u0005\u0003y\u0006\u0012AbQ1o\u001b\u0006\u0004h+\u00197vKN$\u0012A \u000b\u0006\u007f\u0006\u0005\u00111\u0001\t\u0006O*\u0001hO\u001d\u0005\u0006i6\u0001\u001d!\u001e\u0005\u0006s6\u0001\u001dA\u001f\u000b\u0004e\u0006\u001d\u0001BBA\u0005\u001d\u0001\u0007\u0001/A\u0001w\u0003-y\u0007/T1q-\u0006dW/Z:\u0016\u0011\u0005=\u0011QCA\u0011\u00033!b!!\u0005\u0002\u001c\u0005\r\u0002C\u0002#\u0001\u0003'\t9\u0002E\u00024\u0003+!Q!Q\bC\u0002Y\u00022aMA\r\t\u0015)tB1\u00017\u0011\u0019Ix\u0002q\u0001\u0002\u001eAQAi_A\n\u0003?\ty\"a\u0006\u0011\u0007M\n\t\u0003B\u0003y\u001f\t\u0007a\u0007\u0003\u0004u\u001f\u0001\u000f\u0011Q\u0005\t\u0006?\u0006\u001d\u0012qD\u0005\u0004\u0003S\u0001'!\u0002$jK2$\u0017AC(q\u0003J\u0014\u0018-_!osV!\u0011qFA\u001b)\u0019\t\t$a\u000e\u0002>A!q-BA\u001a!\r\u0019\u0014Q\u0007\u0003\u0006#B\u0011\rA\u000e\u0005\n\u0003s\u0001\u0012\u0011!a\u0002\u0003w\t!\"\u001a<jI\u0016t7-\u001a\u00134!\u0011A6,a\r\t\u0013\u0005}\u0002#!AA\u0004\u0005\u0005\u0013AC3wS\u0012,gnY3%iA!qLYA\u001a\u0003!y\u0005/\u0011:sCfL\u0005CA4\u0013\u0005!y\u0005/\u0011:sCfL5c\u0001\n\u0002LA!q-BA'!\rY\u0013qJ\u0005\u0004\u0003#b#aA%oiR\u0011\u0011QI\u0001\t\u001fB\f%O]1z'B\u0011q-\u0006\u0002\t\u001fB\f%O]1z'N\u0019Q#!\u0018\u0011\t\u001d,\u0011q\f\t\u0004W\u0005\u0005\u0014bAA2Y\t)1\u000b[8siR\u0011\u0011qK\u0001\t\u001fB\f%O]1z\u0019B\u0011q\r\u0007\u0002\t\u001fB\f%O]1z\u0019N\u0019\u0001$a\u001c\u0011\t\u001d,\u0011\u0011\u000f\t\u0004W\u0005M\u0014bAA;Y\t!Aj\u001c8h)\t\tI'\u0001\u0005Pa\u0006\u0013(/Y=G!\t97D\u0001\u0005Pa\u0006\u0013(/Y=G'\rY\u0012\u0011\u0011\t\u0005O\u0016\t\u0019\tE\u0002,\u0003\u000bK1!a\"-\u0005\u00151En\\1u)\t\tY(\u0001\u0005Pa\u0006\u0013(/Y=E!\t9gD\u0001\u0005Pa\u0006\u0013(/Y=E'\rq\u00121\u0013\t\u0005O\u0016\t)\nE\u0002,\u0003/K1!!'-\u0005\u0019!u.\u001e2mKR\u0011\u0011Q\u0012"
)
public interface CanCreateZerosLike {
   static OpArray OpArrayAny(final ClassTag evidence$3, final Semiring evidence$4) {
      return CanCreateZerosLike$.MODULE$.OpArrayAny(evidence$3, evidence$4);
   }

   static CanCreateZerosLike opMapValues(final CanMapValues map, final Field op) {
      return CanCreateZerosLike$.MODULE$.opMapValues(map, op);
   }

   Object apply(final Object from);

   public static class OpArray implements CanCreateZerosLike {
      public final Semiring evidence$2;

      public Object apply(final Object from) {
         return ArrayUtil$.MODULE$.fillNewArrayLike(from, .MODULE$.array_length(from), ((Semiring)scala.Predef..MODULE$.implicitly(this.evidence$2)).zero());
      }

      public boolean[] apply$mcZ$sp(final boolean[] from) {
         return (boolean[])this.apply(from);
      }

      public byte[] apply$mcB$sp(final byte[] from) {
         return (byte[])this.apply(from);
      }

      public char[] apply$mcC$sp(final char[] from) {
         return (char[])this.apply(from);
      }

      public double[] apply$mcD$sp(final double[] from) {
         return (double[])this.apply(from);
      }

      public float[] apply$mcF$sp(final float[] from) {
         return (float[])this.apply(from);
      }

      public int[] apply$mcI$sp(final int[] from) {
         return (int[])this.apply(from);
      }

      public long[] apply$mcJ$sp(final long[] from) {
         return (long[])this.apply(from);
      }

      public short[] apply$mcS$sp(final short[] from) {
         return (short[])this.apply(from);
      }

      public BoxedUnit[] apply$mcV$sp(final BoxedUnit[] from) {
         return (BoxedUnit[])this.apply(from);
      }

      public OpArray(final ClassTag evidence$1, final Semiring evidence$2) {
         this.evidence$2 = evidence$2;
      }
   }

   public static class OpMapValues implements CanCreateZerosLike {
      private final Semiring op;
      private final CanMapValues map;

      public Object apply(final Object v) {
         return this.map.mapActive(v, (x$1) -> this.op.zero());
      }

      public OpMapValues(final Semiring op, final CanMapValues map) {
         this.op = op;
         this.map = map;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class OpArrayI$ extends CanCreateZerosLike$OpArray$mcI$sp {
      public static final OpArrayI$ MODULE$ = new OpArrayI$();

      public OpArrayI$() {
         super(scala.reflect.ClassTag..MODULE$.Int(), Semiring$.MODULE$.semiringInt());
      }
   }

   public static class OpArrayS$ extends CanCreateZerosLike$OpArray$mcS$sp {
      public static final OpArrayS$ MODULE$ = new OpArrayS$();

      public OpArrayS$() {
         super(scala.reflect.ClassTag..MODULE$.Short(), Semiring$.MODULE$.semiringShort());
      }
   }

   public static class OpArrayL$ extends CanCreateZerosLike$OpArray$mcJ$sp {
      public static final OpArrayL$ MODULE$ = new OpArrayL$();

      public OpArrayL$() {
         super(scala.reflect.ClassTag..MODULE$.Long(), Semiring$.MODULE$.semiringLong());
      }
   }

   public static class OpArrayF$ extends CanCreateZerosLike$OpArray$mcF$sp {
      public static final OpArrayF$ MODULE$ = new OpArrayF$();

      public OpArrayF$() {
         super(scala.reflect.ClassTag..MODULE$.Float(), Semiring$.MODULE$.semiringFloat());
      }
   }

   public static class OpArrayD$ extends CanCreateZerosLike$OpArray$mcD$sp {
      public static final OpArrayD$ MODULE$ = new OpArrayD$();

      public OpArrayD$() {
         super(scala.reflect.ClassTag..MODULE$.Double(), Semiring$.MODULE$.semiringD());
      }
   }
}
