package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.linalg.scaleAdd$;
import breeze.linalg.support.ScalarOf;
import breeze.math.Ring;
import breeze.math.Semiring;
import java.lang.invoke.SerializedLambda;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eda\u0002\u0006\f!\u0003\r\tA\u0005\u0005\u0006;\u0001!\tA\b\u0005\u0006E\u0001!\u0019a\t\u0005\u0006#\u0002!\u0019A\u0015\u0005\u0006K\u0002!\u0019AZ\u0004\b\u0003\u001bY\u0001\u0012AA\b\r\u0019Q1\u0002#\u0001\u0002\u0012!9\u00111\u0003\u0004\u0005\u0002\u0005U\u0001bBA\f\r\u0011\u0005\u0011\u0011\u0004\u0005\t\u0003/2A\u0011A\b\u0002Z\tQq)\u001a8fe&\u001cw\n]:\u000b\u00051i\u0011!C8qKJ\fGo\u001c:t\u0015\tqq\"\u0001\u0004mS:\fGn\u001a\u0006\u0002!\u00051!M]3fu\u0016\u001c\u0001aE\u0002\u0001'e\u0001\"\u0001F\f\u000e\u0003UQ\u0011AF\u0001\u0006g\u000e\fG.Y\u0005\u00031U\u0011a!\u00118z%\u00164\u0007C\u0001\u000e\u001c\u001b\u0005Y\u0011B\u0001\u000f\f\u0005E9UM\\3sS\u000e|\u0005o\u001d'poB\u0013\u0018n\\\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003}\u0001\"\u0001\u0006\u0011\n\u0005\u0005*\"\u0001B+oSR\fA'[7qY~{\u0005/\u00113e?&s\u0007\u000b\\1dK~#v,V0HK:,'/[2`MJ|WnX:dC2,\u0017\t\u001a3`\u0013:\u0004F.Y2f+\u0011!\u0013gR\u001e\u0015\u0007\u0015j\u0014\n\u0005\u0003'S=RdB\u0001\u000e(\u0013\tA3\"A\u0003Pa\u0006#G-\u0003\u0002+W\ta\u0011J\u001c)mC\u000e,\u0017*\u001c9me%\u0011A&\f\u0002\u0006+\u001a+hn\u0019\u0006\u0003]=\tqaZ3oKJL7\r\u0005\u00021c1\u0001A!\u0002\u001a\u0003\u0005\u0004\u0019$!\u0001+\u0012\u0005Q:\u0004C\u0001\u000b6\u0013\t1TCA\u0004O_RD\u0017N\\4\u0011\u0005QA\u0014BA\u001d\u0016\u0005\r\te.\u001f\t\u0003am\"Q\u0001\u0010\u0002C\u0002M\u0012\u0011A\u0016\u0005\u0006}\t\u0001\u001daP\u0001\u0003g\u0006\u0004R\u0001\u0011#0\rjr!!\u0011\"\u000e\u00035I!aQ\u0007\u0002\u0011M\u001c\u0017\r\\3BI\u0012L!!R\u0016\u0003\u0019%s\u0007\u000b\\1dK&k\u0007\u000f\\\u001a\u0011\u0005A:E!\u0002%\u0003\u0005\u0004\u0019$!A+\t\u000b)\u0013\u00019A&\u0002\tM,W.\u001b\t\u0004\u0019>3U\"A'\u000b\u00059{\u0011\u0001B7bi\"L!\u0001U'\u0003\u0011M+W.\u001b:j]\u001e\fA'[7qY~{\u0005oU;c?&s\u0007\u000b\\1dK~#v,V0HK:,'/[2`MJ|WnX:dC2,\u0017\t\u001a3`\u0013:\u0004F.Y2f+\u0011\u0019\u0016lX.\u0015\u0007Qc\u0006\r\u0005\u0003VSaSfB\u0001\u000eW\u0013\t96\"A\u0003PaN+(\r\u0005\u000213\u0012)!g\u0001b\u0001gA\u0011\u0001g\u0017\u0003\u0006y\r\u0011\ra\r\u0005\u0006}\r\u0001\u001d!\u0018\t\u0006\u0001\u0012CfL\u0017\t\u0003a}#Q\u0001S\u0002C\u0002MBQ!Y\u0002A\u0004\t\fAA]5oOB\u0019Aj\u00190\n\u0005\u0011l%\u0001\u0002*j]\u001e\fQ%[7qY~{\u0005OT3h?R{v)\u001a8fe&\u001cwL\u001a:p[~{\u0005/T;m'\u000e\fG.\u0019:\u0016\t\u001d|\u0017o\u001f\u000b\u0005QJdh\u0010\u0005\u0003jY:\u0004hB\u0001\u000ek\u0013\tY7\"A\u0003Pa:+w-\u0003\u0002nW\t!\u0011*\u001c9m!\t\u0001t\u000eB\u00033\t\t\u00071\u0007\u0005\u00021c\u0012)\u0001\n\u0002b\u0001g!)1\u000f\u0002a\u0002i\u0006A1oY1mCJ|e\r\u0005\u0003vq:TX\"\u0001<\u000b\u0005]l\u0011aB:vaB|'\u000f^\u0005\u0003sZ\u0014\u0001bU2bY\u0006\u0014xJ\u001a\t\u0003am$Q\u0001\u0010\u0003C\u0002MBQ!\u0019\u0003A\u0004u\u00042\u0001T2{\u0011\u0019yH\u0001q\u0001\u0002\u0002\u0005)1oY1mKB9\u00111AA\u0005]j\u0004hb\u0001\u000e\u0002\u0006%\u0019\u0011qA\u0006\u0002\u0017=\u0003X*\u001e7TG\u0006d\u0017M]\u0005\u0004\u0003\u0017Y#!B%na2\u0014\u0014AC$f]\u0016\u0014\u0018nY(qgB\u0011!DB\n\u0003\rM\ta\u0001P5oSRtDCAA\b\u00039)\b\u000fZ1uK\u001a\u0013x.\u001c)ve\u0016,\"\"a\u0007\u0002.\u0005M\u0012qGA$)\u0019\ti\"a\u000f\u0002LAQ\u0011qDA\u0014\u0003W\t\t$!\u000e\u000f\t\u0005\u0005\u00121E\u0007\u0002[%\u0019\u0011QE\u0017\u0002\u000bU3UO\\2\n\u0007)\nICC\u0002\u0002&5\u00022\u0001MA\u0017\t\u0019\ty\u0003\u0003b\u0001g\t\u0011q\n\u001d\t\u0004a\u0005MB!\u0002\u001a\t\u0005\u0004\u0019\u0004c\u0001\u0019\u00028\u00111\u0011\u0011\b\u0005C\u0002M\u0012Qa\u0014;iKJDq!!\u0010\t\u0001\b\ty$\u0001\u0002paBa\u0011qDA!\u0003W\t\t$!\u000e\u0002F%!\u00111IA\u0015\u0005\u0019)\u0016*\u001c9meA\u0019\u0001'a\u0012\u0005\r\u0005%\u0003B1\u00014\u0005\u0005\u0011\u0006bBA'\u0011\u0001\u000f\u0011qJ\u0001\u0004g\u0016$\bcBA)S\u0005E\u0012Q\t\b\u00045\u0005M\u0013bAA+\u0017\u0005)q\n]*fi\u0006i2\u000f]1sg\u0016,en\\;hQ\u001a{'/Q2uSZ,\u0017\n^3sCR|'/\u0006\u0003\u0002\\\u0005=D\u0003BA/\u0003G\u00022\u0001FA0\u0013\r\t\t'\u0006\u0002\b\u0005>|G.Z1o\u0011\u001d\t)'\u0003a\u0001\u0003O\n\u0011A\u001e\t\u0006\u0003\u0006%\u0014QN\u0005\u0004\u0003Wj!A\u0002,fGR|'\u000fE\u00021\u0003_\"QAM\u0005C\u0002M\u0002"
)
public interface GenericOps extends GenericOpsLowPrio {
   static UFunc.InPlaceImpl2 updateFromPure(final UFunc.UImpl2 op, final UFunc.InPlaceImpl2 set) {
      return GenericOps$.MODULE$.updateFromPure(op, set);
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpAdd_InPlace_T_U_Generic_from_scaleAdd_InPlace$(final GenericOps $this, final UFunc.InPlaceImpl3 sa, final Semiring semi) {
      return $this.impl_OpAdd_InPlace_T_U_Generic_from_scaleAdd_InPlace(sa, semi);
   }

   default UFunc.InPlaceImpl2 impl_OpAdd_InPlace_T_U_Generic_from_scaleAdd_InPlace(final UFunc.InPlaceImpl3 sa, final Semiring semi) {
      return (t, v) -> scaleAdd$.MODULE$.inPlace(t, semi.one(), v, sa);
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpSub_InPlace_T_U_Generic_from_scaleAdd_InPlace$(final GenericOps $this, final UFunc.InPlaceImpl3 sa, final Ring ring) {
      return $this.impl_OpSub_InPlace_T_U_Generic_from_scaleAdd_InPlace(sa, ring);
   }

   default UFunc.InPlaceImpl2 impl_OpSub_InPlace_T_U_Generic_from_scaleAdd_InPlace(final UFunc.InPlaceImpl3 sa, final Ring ring) {
      return (t, v) -> scaleAdd$.MODULE$.inPlace(t, ring.negate(ring.one()), v, sa);
   }

   // $FF: synthetic method
   static UFunc.UImpl impl_OpNeg_T_Generic_from_OpMulScalar$(final GenericOps $this, final ScalarOf scalarOf, final Ring ring, final UFunc.UImpl2 scale) {
      return $this.impl_OpNeg_T_Generic_from_OpMulScalar(scalarOf, ring, scale);
   }

   default UFunc.UImpl impl_OpNeg_T_Generic_from_OpMulScalar(final ScalarOf scalarOf, final Ring ring, final UFunc.UImpl2 scale) {
      return new UFunc.UImpl(scale, ring) {
         private final UFunc.UImpl2 scale$1;
         private final Ring ring$2;

         public double apply$mcDD$sp(final double v) {
            return UFunc.UImpl.apply$mcDD$sp$(this, v);
         }

         public float apply$mcDF$sp(final double v) {
            return UFunc.UImpl.apply$mcDF$sp$(this, v);
         }

         public int apply$mcDI$sp(final double v) {
            return UFunc.UImpl.apply$mcDI$sp$(this, v);
         }

         public double apply$mcFD$sp(final float v) {
            return UFunc.UImpl.apply$mcFD$sp$(this, v);
         }

         public float apply$mcFF$sp(final float v) {
            return UFunc.UImpl.apply$mcFF$sp$(this, v);
         }

         public int apply$mcFI$sp(final float v) {
            return UFunc.UImpl.apply$mcFI$sp$(this, v);
         }

         public double apply$mcID$sp(final int v) {
            return UFunc.UImpl.apply$mcID$sp$(this, v);
         }

         public float apply$mcIF$sp(final int v) {
            return UFunc.UImpl.apply$mcIF$sp$(this, v);
         }

         public int apply$mcII$sp(final int v) {
            return UFunc.UImpl.apply$mcII$sp$(this, v);
         }

         public Object apply(final Object a) {
            return this.scale$1.apply(a, this.ring$2.negate(this.ring$2.one()));
         }

         public {
            this.scale$1 = scale$1;
            this.ring$2 = ring$2;
         }
      };
   }

   static void $init$(final GenericOps $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
