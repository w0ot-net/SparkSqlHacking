package breeze.linalg.support;

import breeze.math.Field;
import breeze.util.ArrayUtil$;
import java.lang.invoke.SerializedLambda;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=eaB\u0010!!\u0003\r\na\n\u0005\u0006_\u00011\t\u0001M\u0004\u0006}\u0001B\ta\u0010\u0004\u0006?\u0001B\t!\u0011\u0005\u0006\u0005\u000e!\ta\u0011\u0004\u0005\t\u000e\u0001Q\tC\u0003C\u000b\u0011\u0005\u0011\u000bC\u00030\u000b\u0011\u0005CK\u0002\u0003X\u0007\u0001A\u0006\u0002\u00030\t\u0005\u0003\u0005\u000b1B0\t\u0011\tD!\u0011!Q\u0001\f\rDQA\u0011\u0005\u0005\u0002\u0019DQa\f\u0005\u0005\u0002-DQA\\\u0002\u0005\u0004=DQa_\u0002\u0005\u0004q<q!a\t\u0004\u0011\u0007\t)CB\u0004\u0002(\rA\t!!\u000b\t\r\t\u0003B\u0011AA\u001a\u000f\u001d\t)d\u0001E\u0002\u0003o1q!!\u000f\u0004\u0011\u0003\tY\u0004\u0003\u0004C'\u0011\u0005\u0011QI\u0004\b\u0003\u000f\u001a\u00012AA%\r\u001d\tYe\u0001E\u0001\u0003\u001bBaA\u0011\f\u0005\u0002\u0005]saBA-\u0007!\r\u00111\f\u0004\b\u0003;\u001a\u0001\u0012AA0\u0011\u0019\u0011\u0015\u0004\"\u0001\u0002j\u001d9\u00111N\u0002\t\u0004\u00055daBA8\u0007!\u0005\u0011\u0011\u000f\u0005\u0007\u0005r!\t!a\u001f\t\u000f\u0005u4\u0001b\u0001\u0002\u0000\t91)\u00198D_BL(BA\u0011#\u0003\u001d\u0019X\u000f\u001d9peRT!a\t\u0013\u0002\r1Lg.\u00197h\u0015\u0005)\u0013A\u00022sK\u0016TXm\u0001\u0001\u0016\u0005!\u001a4C\u0001\u0001*!\tQS&D\u0001,\u0015\u0005a\u0013!B:dC2\f\u0017B\u0001\u0018,\u0005\u0019\te.\u001f*fM\u0006)\u0011\r\u001d9msR\u0011\u0011\u0007\u0010\t\u0003eMb\u0001\u0001B\u00035\u0001\t\u0007QGA\u0001W#\t1\u0014\b\u0005\u0002+o%\u0011\u0001h\u000b\u0002\b\u001d>$\b.\u001b8h!\tQ#(\u0003\u0002<W\t\u0019\u0011I\\=\t\u000bu\n\u0001\u0019A\u0019\u0002\u0003Q\fqaQ1o\u0007>\u0004\u0018\u0010\u0005\u0002A\u00075\t\u0001e\u0005\u0002\u0004S\u00051A(\u001b8jiz\"\u0012a\u0010\u0002\b\u001fB\f%O]1z+\t1EjE\u0002\u0006S\u001d\u00032\u0001\u0011\u0001I!\rQ\u0013jS\u0005\u0003\u0015.\u0012Q!\u0011:sCf\u0004\"A\r'\u0005\u0013Q*\u0001\u0015!A\u0001\u0006\u0004)\u0004F\u0001'O!\tQs*\u0003\u0002QW\tY1\u000f]3dS\u0006d\u0017N_3e)\u0005\u0011\u0006cA*\u0006\u00176\t1\u0001\u0006\u0002I+\")ak\u0002a\u0001\u0011\u0006!aM]8n\u0005-y\u0005/T1q-\u0006dW/Z:\u0016\u0007ec\u0016mE\u0002\tSi\u00032\u0001\u0011\u0001\\!\t\u0011D\fB\u0003^\u0011\t\u0007QG\u0001\u0003Ge>l\u0017AA8q!\r\u0001\u0005\u0001\u0019\t\u0003e\u0005$Q\u0001\u000e\u0005C\u0002U\n1aY7w!\u0019\u0001Em\u00171a7&\u0011Q\r\t\u0002\r\u0007\u0006tW*\u00199WC2,Xm\u001d\u000b\u0002OR\u0019\u0001.\u001b6\u0011\tMC1\f\u0019\u0005\u0006=.\u0001\u001da\u0018\u0005\u0006E.\u0001\u001da\u0019\u000b\u000372DQ!\u001c\u0007A\u0002m\u000b\u0011A^\u0001\f_Bl\u0015\r\u001d,bYV,7/F\u0002qgb$2!\u001d;z!\r\u0001\u0005A\u001d\t\u0003eM$Q!X\u0007C\u0002UBQ!^\u0007A\u0004Y\f1!\\1q!\u0019\u0001EM]<xeB\u0011!\u0007\u001f\u0003\u0006i5\u0011\r!\u000e\u0005\u0006=6\u0001\u001dA\u001f\t\u0004\u0001\u00029\u0018AC8q\u0003J\u0014\u0018-_!osV\u0019Q0!\u0001\u0015\u000by\f\u0019!a\u0005\u0011\u0007M+q\u0010E\u00023\u0003\u0003!Q\u0001\u000e\bC\u0002UB\u0011\"!\u0002\u000f\u0003\u0003\u0005\u001d!a\u0002\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u0003\u0002\n\u0005=q0\u0004\u0002\u0002\f)\u0019\u0011QB\u0016\u0002\u000fI,g\r\\3di&!\u0011\u0011CA\u0006\u0005!\u0019E.Y:t)\u0006<\u0007\"CA\u000b\u001d\u0005\u0005\t9AA\f\u0003))g/\u001b3f]\u000e,GE\r\t\u0006\u00033\tyb`\u0007\u0003\u00037Q1!!\b%\u0003\u0011i\u0017\r\u001e5\n\t\u0005\u0005\u00121\u0004\u0002\u0006\r&,G\u000eZ\u0001\t\u001fB\f%O]1z\u0013B\u00111\u000b\u0005\u0002\t\u001fB\f%O]1z\u0013N\u0019\u0001#a\u000b\u0011\tM+\u0011Q\u0006\t\u0004U\u0005=\u0012bAA\u0019W\t\u0019\u0011J\u001c;\u0015\u0005\u0005\u0015\u0012\u0001C(q\u0003J\u0014\u0018-_*\u0011\u0005M\u001b\"\u0001C(q\u0003J\u0014\u0018-_*\u0014\u0007M\ti\u0004\u0005\u0003T\u000b\u0005}\u0002c\u0001\u0016\u0002B%\u0019\u00111I\u0016\u0003\u000bMCwN\u001d;\u0015\u0005\u0005]\u0012\u0001C(q\u0003J\u0014\u0018-\u001f'\u0011\u0005M3\"\u0001C(q\u0003J\u0014\u0018-\u001f'\u0014\u0007Y\ty\u0005\u0005\u0003T\u000b\u0005E\u0003c\u0001\u0016\u0002T%\u0019\u0011QK\u0016\u0003\t1{gn\u001a\u000b\u0003\u0003\u0013\n\u0001b\u00149BeJ\f\u0017P\u0012\t\u0003'f\u0011\u0001b\u00149BeJ\f\u0017PR\n\u00043\u0005\u0005\u0004\u0003B*\u0006\u0003G\u00022AKA3\u0013\r\t9g\u000b\u0002\u0006\r2|\u0017\r\u001e\u000b\u0003\u00037\n\u0001b\u00149BeJ\f\u0017\u0010\u0012\t\u0003'r\u0011\u0001b\u00149BeJ\f\u0017\u0010R\n\u00049\u0005M\u0004\u0003B*\u0006\u0003k\u00022AKA<\u0013\r\tIh\u000b\u0002\u0007\t>,(\r\\3\u0015\u0005\u00055\u0014\u0001D2b]\u000e{\u0007/\u001f$jK2$W\u0003BAA\u0003\u000f#B!a!\u0002\nB!\u0001\tAAC!\r\u0011\u0014q\u0011\u0003\u0006iy\u0011\r!\u000e\u0005\n\u0003\u0017s\u0012\u0011!a\u0002\u0003\u001b\u000b!\"\u001a<jI\u0016t7-\u001a\u00134!\u0019\tI\"a\b\u0002\u0006\u0002"
)
public interface CanCopy {
   static CanCopy canCopyField(final Field evidence$3) {
      return CanCopy$.MODULE$.canCopyField(evidence$3);
   }

   static OpArray opArrayAny(final ClassTag evidence$1, final Field evidence$2) {
      return CanCopy$.MODULE$.opArrayAny(evidence$1, evidence$2);
   }

   static CanCopy opMapValues(final CanMapValues map, final CanCopy op) {
      return CanCopy$.MODULE$.opMapValues(map, op);
   }

   Object apply(final Object t);

   public static class OpArray implements CanCopy {
      public Object apply(final Object from) {
         return ArrayUtil$.MODULE$.copyOf(from, .MODULE$.array_length(from));
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
   }

   public static class OpMapValues implements CanCopy {
      private final CanCopy op;
      private final CanMapValues cmv;

      public Object apply(final Object v) {
         return this.cmv.map(v, (x$1) -> this.op.apply(x$1));
      }

      public OpMapValues(final CanCopy op, final CanMapValues cmv) {
         this.op = op;
         this.cmv = cmv;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class OpArrayI$ extends CanCopy$OpArray$mcI$sp {
      public static final OpArrayI$ MODULE$ = new OpArrayI$();
   }

   public static class OpArrayS$ extends CanCopy$OpArray$mcS$sp {
      public static final OpArrayS$ MODULE$ = new OpArrayS$();
   }

   public static class OpArrayL$ extends CanCopy$OpArray$mcJ$sp {
      public static final OpArrayL$ MODULE$ = new OpArrayL$();
   }

   public static class OpArrayF$ extends CanCopy$OpArray$mcF$sp {
      public static final OpArrayF$ MODULE$ = new OpArrayF$();
   }

   public static class OpArrayD$ extends CanCopy$OpArray$mcD$sp {
      public static final OpArrayD$ MODULE$ = new OpArrayD$();
   }
}
