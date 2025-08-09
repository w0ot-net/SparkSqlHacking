package scala.jdk;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import scala.$less$colon$less;
import scala.collection.IterableOnce;
import scala.collection.MapOps;
import scala.collection.Stepper;
import scala.collection.convert.StreamExtensions;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m9Qa\u0001\u0003\t\u0002%1Qa\u0003\u0003\t\u00021AQ!G\u0001\u0005\u0002i\t\u0001c\u0015;sK\u0006l7i\u001c8wKJ$XM]:\u000b\u0005\u00151\u0011a\u00016eW*\tq!A\u0003tG\u0006d\u0017m\u0001\u0001\u0011\u0005)\tQ\"\u0001\u0003\u0003!M#(/Z1n\u0007>tg/\u001a:uKJ\u001c8cA\u0001\u000e#A\u0011abD\u0007\u0002\r%\u0011\u0001C\u0002\u0002\u0007\u0003:L(+\u001a4\u0011\u0005I9R\"A\n\u000b\u0005Q)\u0012aB2p]Z,'\u000f\u001e\u0006\u0003-\u0019\t!bY8mY\u0016\u001cG/[8o\u0013\tA2C\u0001\tTiJ,\u0017-\\#yi\u0016t7/[8og\u00061A(\u001b8jiz\"\u0012!\u0003"
)
public final class StreamConverters {
   public static StreamExtensions.DoubleStreamHasToScala DoubleStreamHasToScala(final DoubleStream stream) {
      return StreamConverters$.MODULE$.DoubleStreamHasToScala(stream);
   }

   public static StreamExtensions.LongStreamHasToScala LongStreamHasToScala(final LongStream stream) {
      return StreamConverters$.MODULE$.LongStreamHasToScala(stream);
   }

   public static StreamExtensions.IntStreamHasToScala IntStreamHasToScala(final IntStream stream) {
      return StreamConverters$.MODULE$.IntStreamHasToScala(stream);
   }

   public static StreamExtensions.StreamHasToScala StreamHasToScala(final Stream stream) {
      return StreamConverters$.MODULE$.StreamHasToScala(stream);
   }

   public static StreamExtensions.StringHasSeqParStream StringHasSeqParStream(final String s) {
      return StreamConverters$.MODULE$.StringHasSeqParStream(s);
   }

   public static StreamExtensions.FloatArrayHasSeqParStream FloatArrayHasSeqParStream(final float[] a) {
      return StreamConverters$.MODULE$.FloatArrayHasSeqParStream(a);
   }

   public static StreamExtensions.CharArrayHasSeqParStream CharArrayHasSeqParStream(final char[] a) {
      return StreamConverters$.MODULE$.CharArrayHasSeqParStream(a);
   }

   public static StreamExtensions.ShortArrayHasSeqParStream ShortArrayHasSeqParStream(final short[] a) {
      return StreamConverters$.MODULE$.ShortArrayHasSeqParStream(a);
   }

   public static StreamExtensions.ByteArrayHasSeqParStream ByteArrayHasSeqParStream(final byte[] a) {
      return StreamConverters$.MODULE$.ByteArrayHasSeqParStream(a);
   }

   public static StreamExtensions.AnyArrayHasSeqParStream AnyArrayHasSeqParStream(final Object[] a) {
      return StreamConverters$.MODULE$.AnyArrayHasSeqParStream(a);
   }

   public static StreamExtensions.LongArrayHasSeqParStream LongArrayHasSeqParStream(final long[] a) {
      return StreamConverters$.MODULE$.LongArrayHasSeqParStream(a);
   }

   public static StreamExtensions.IntArrayHasSeqParStream IntArrayHasSeqParStream(final int[] a) {
      return StreamConverters$.MODULE$.IntArrayHasSeqParStream(a);
   }

   public static StreamExtensions.DoubleArrayHasSeqParStream DoubleArrayHasSeqParStream(final double[] a) {
      return StreamConverters$.MODULE$.DoubleArrayHasSeqParStream(a);
   }

   public static StreamExtensions.StepperHasParStream StepperHasParStream(final Stepper stepper) {
      return StreamConverters$.MODULE$.StepperHasParStream(stepper);
   }

   public static StreamExtensions.StepperHasSeqStream StepperHasSeqStream(final Stepper stepper) {
      return StreamConverters$.MODULE$.StepperHasSeqStream(stepper);
   }

   public static StreamExtensions.MapHasParKeyValueStream MapHasParKeyValueStream(final MapOps cc) {
      return StreamConverters$.MODULE$.MapHasParKeyValueStream(cc);
   }

   public static StreamExtensions.MapHasSeqKeyValueStream MapHasSeqKeyValueStream(final MapOps cc) {
      return StreamConverters$.MODULE$.MapHasSeqKeyValueStream(cc);
   }

   public static StreamExtensions.IterableNonGenericHasParStream IterableNonGenericHasParStream(final IterableOnce c, final $less$colon$less ev) {
      return StreamConverters$.MODULE$.IterableNonGenericHasParStream(c, ev);
   }

   public static StreamExtensions.IterableHasSeqStream IterableHasSeqStream(final IterableOnce cc) {
      return StreamConverters$.MODULE$.IterableHasSeqStream(cc);
   }
}
