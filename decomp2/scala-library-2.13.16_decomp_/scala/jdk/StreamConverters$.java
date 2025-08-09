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

public final class StreamConverters$ implements StreamExtensions {
   public static final StreamConverters$ MODULE$ = new StreamConverters$();

   static {
      StreamConverters$ var10000 = MODULE$;
   }

   public StreamExtensions.IterableHasSeqStream IterableHasSeqStream(final IterableOnce cc) {
      return StreamExtensions.IterableHasSeqStream$(this, cc);
   }

   public StreamExtensions.IterableNonGenericHasParStream IterableNonGenericHasParStream(final IterableOnce c, final $less$colon$less ev) {
      return StreamExtensions.IterableNonGenericHasParStream$(this, c, ev);
   }

   public StreamExtensions.MapHasSeqKeyValueStream MapHasSeqKeyValueStream(final MapOps cc) {
      return StreamExtensions.MapHasSeqKeyValueStream$(this, cc);
   }

   public StreamExtensions.MapHasParKeyValueStream MapHasParKeyValueStream(final MapOps cc) {
      return StreamExtensions.MapHasParKeyValueStream$(this, cc);
   }

   public StreamExtensions.StepperHasSeqStream StepperHasSeqStream(final Stepper stepper) {
      return StreamExtensions.StepperHasSeqStream$(this, stepper);
   }

   public StreamExtensions.StepperHasParStream StepperHasParStream(final Stepper stepper) {
      return StreamExtensions.StepperHasParStream$(this, stepper);
   }

   public StreamExtensions.DoubleArrayHasSeqParStream DoubleArrayHasSeqParStream(final double[] a) {
      return StreamExtensions.DoubleArrayHasSeqParStream$(this, a);
   }

   public StreamExtensions.IntArrayHasSeqParStream IntArrayHasSeqParStream(final int[] a) {
      return StreamExtensions.IntArrayHasSeqParStream$(this, a);
   }

   public StreamExtensions.LongArrayHasSeqParStream LongArrayHasSeqParStream(final long[] a) {
      return StreamExtensions.LongArrayHasSeqParStream$(this, a);
   }

   public StreamExtensions.AnyArrayHasSeqParStream AnyArrayHasSeqParStream(final Object[] a) {
      return StreamExtensions.AnyArrayHasSeqParStream$(this, a);
   }

   public StreamExtensions.ByteArrayHasSeqParStream ByteArrayHasSeqParStream(final byte[] a) {
      return StreamExtensions.ByteArrayHasSeqParStream$(this, a);
   }

   public StreamExtensions.ShortArrayHasSeqParStream ShortArrayHasSeqParStream(final short[] a) {
      return StreamExtensions.ShortArrayHasSeqParStream$(this, a);
   }

   public StreamExtensions.CharArrayHasSeqParStream CharArrayHasSeqParStream(final char[] a) {
      return StreamExtensions.CharArrayHasSeqParStream$(this, a);
   }

   public StreamExtensions.FloatArrayHasSeqParStream FloatArrayHasSeqParStream(final float[] a) {
      return StreamExtensions.FloatArrayHasSeqParStream$(this, a);
   }

   public StreamExtensions.StringHasSeqParStream StringHasSeqParStream(final String s) {
      return StreamExtensions.StringHasSeqParStream$(this, s);
   }

   public StreamExtensions.StreamHasToScala StreamHasToScala(final Stream stream) {
      return StreamExtensions.StreamHasToScala$(this, stream);
   }

   public StreamExtensions.IntStreamHasToScala IntStreamHasToScala(final IntStream stream) {
      return StreamExtensions.IntStreamHasToScala$(this, stream);
   }

   public StreamExtensions.LongStreamHasToScala LongStreamHasToScala(final LongStream stream) {
      return StreamExtensions.LongStreamHasToScala$(this, stream);
   }

   public StreamExtensions.DoubleStreamHasToScala DoubleStreamHasToScala(final DoubleStream stream) {
      return StreamExtensions.DoubleStreamHasToScala$(this, stream);
   }

   private StreamConverters$() {
   }
}
