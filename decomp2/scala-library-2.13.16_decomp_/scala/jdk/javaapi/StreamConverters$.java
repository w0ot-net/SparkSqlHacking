package scala.jdk.javaapi;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import scala.collection.AnyStepper;
import scala.collection.DoubleStepper;
import scala.collection.IntStepper;
import scala.collection.IterableOnce;
import scala.collection.LongStepper;
import scala.collection.Map;
import scala.collection.StepperShape$;

public final class StreamConverters$ {
   public static final StreamConverters$ MODULE$ = new StreamConverters$();

   public Stream asJavaSeqStream(final IterableOnce cc) {
      return StreamSupport.stream(((AnyStepper)cc.stepper(StepperShape$.MODULE$.anyStepperShapePrototype())).spliterator(), false);
   }

   public IntStream asJavaSeqIntStream(final IterableOnce cc) {
      return StreamSupport.intStream(((IntStepper)cc.stepper(StepperShape$.MODULE$.jIntegerStepperShape())).spliterator$mcI$sp(), false);
   }

   public IntStream asJavaSeqIntStreamFromByte(final IterableOnce cc) {
      return StreamSupport.intStream(((IntStepper)cc.stepper(StepperShape$.MODULE$.jByteStepperShape())).spliterator$mcI$sp(), false);
   }

   public IntStream asJavaSeqIntStreamFromShort(final IterableOnce cc) {
      return StreamSupport.intStream(((IntStepper)cc.stepper(StepperShape$.MODULE$.jShortStepperShape())).spliterator$mcI$sp(), false);
   }

   public IntStream asJavaSeqIntStreamFromChar(final IterableOnce cc) {
      return StreamSupport.intStream(((IntStepper)cc.stepper(StepperShape$.MODULE$.jCharacterStepperShape())).spliterator$mcI$sp(), false);
   }

   public DoubleStream asJavaSeqDoubleStream(final IterableOnce cc) {
      return StreamSupport.doubleStream(((DoubleStepper)cc.stepper(StepperShape$.MODULE$.jDoubleStepperShape())).spliterator$mcD$sp(), false);
   }

   public DoubleStream asJavaSeqDoubleStreamFromFloat(final IterableOnce cc) {
      return StreamSupport.doubleStream(((DoubleStepper)cc.stepper(StepperShape$.MODULE$.jFloatStepperShape())).spliterator$mcD$sp(), false);
   }

   public LongStream asJavaSeqLongStream(final IterableOnce cc) {
      return StreamSupport.longStream(((LongStepper)cc.stepper(StepperShape$.MODULE$.jLongStepperShape())).spliterator$mcJ$sp(), false);
   }

   public Stream asJavaSeqKeyStream(final Map m) {
      return StreamSupport.stream(((AnyStepper)m.keyStepper(StepperShape$.MODULE$.anyStepperShapePrototype())).spliterator(), false);
   }

   public IntStream asJavaSeqKeyIntStream(final Map m) {
      return StreamSupport.intStream(((IntStepper)m.keyStepper(StepperShape$.MODULE$.jIntegerStepperShape())).spliterator$mcI$sp(), false);
   }

   public IntStream asJavaSeqKeyIntStreamFromByte(final Map m) {
      return StreamSupport.intStream(((IntStepper)m.keyStepper(StepperShape$.MODULE$.jByteStepperShape())).spliterator$mcI$sp(), false);
   }

   public IntStream asJavaSeqKeyIntStreamFromShort(final Map m) {
      return StreamSupport.intStream(((IntStepper)m.keyStepper(StepperShape$.MODULE$.jShortStepperShape())).spliterator$mcI$sp(), false);
   }

   public IntStream asJavaSeqKeyIntStreamFromChar(final Map m) {
      return StreamSupport.intStream(((IntStepper)m.keyStepper(StepperShape$.MODULE$.jCharacterStepperShape())).spliterator$mcI$sp(), false);
   }

   public DoubleStream asJavaSeqKeyDoubleStream(final Map m) {
      return StreamSupport.doubleStream(((DoubleStepper)m.keyStepper(StepperShape$.MODULE$.jDoubleStepperShape())).spliterator$mcD$sp(), false);
   }

   public DoubleStream asJavaSeqKeyDoubleStreamFromFloat(final Map m) {
      return StreamSupport.doubleStream(((DoubleStepper)m.keyStepper(StepperShape$.MODULE$.jFloatStepperShape())).spliterator$mcD$sp(), false);
   }

   public LongStream asJavaSeqKeyLongStream(final Map m) {
      return StreamSupport.longStream(((LongStepper)m.keyStepper(StepperShape$.MODULE$.jLongStepperShape())).spliterator$mcJ$sp(), false);
   }

   public Stream asJavaSeqValueStream(final Map m) {
      return StreamSupport.stream(((AnyStepper)m.valueStepper(StepperShape$.MODULE$.anyStepperShapePrototype())).spliterator(), false);
   }

   public IntStream asJavaSeqValueIntStream(final Map m) {
      return StreamSupport.intStream(((IntStepper)m.valueStepper(StepperShape$.MODULE$.jIntegerStepperShape())).spliterator$mcI$sp(), false);
   }

   public IntStream asJavaSeqValueIntStreamFromByte(final Map m) {
      return StreamSupport.intStream(((IntStepper)m.valueStepper(StepperShape$.MODULE$.jByteStepperShape())).spliterator$mcI$sp(), false);
   }

   public IntStream asJavaSeqValueIntStreamFromShort(final Map m) {
      return StreamSupport.intStream(((IntStepper)m.valueStepper(StepperShape$.MODULE$.jShortStepperShape())).spliterator$mcI$sp(), false);
   }

   public IntStream asJavaSeqValueIntStreamFromChar(final Map m) {
      return StreamSupport.intStream(((IntStepper)m.valueStepper(StepperShape$.MODULE$.jCharacterStepperShape())).spliterator$mcI$sp(), false);
   }

   public DoubleStream asJavaSeqValueDoubleStream(final Map m) {
      return StreamSupport.doubleStream(((DoubleStepper)m.valueStepper(StepperShape$.MODULE$.jDoubleStepperShape())).spliterator$mcD$sp(), false);
   }

   public DoubleStream asJavaSeqValueDoubleStreamFromFloat(final Map m) {
      return StreamSupport.doubleStream(((DoubleStepper)m.valueStepper(StepperShape$.MODULE$.jFloatStepperShape())).spliterator$mcD$sp(), false);
   }

   public LongStream asJavaSeqValueLongStream(final Map m) {
      return StreamSupport.longStream(((LongStepper)m.valueStepper(StepperShape$.MODULE$.jLongStepperShape())).spliterator$mcJ$sp(), false);
   }

   public Stream asJavaParStream(final IterableOnce cc) {
      return StreamSupport.stream(((AnyStepper)cc.stepper(StepperShape$.MODULE$.anyStepperShapePrototype())).spliterator(), true);
   }

   public IntStream asJavaParIntStream(final IterableOnce cc) {
      return StreamSupport.intStream(((IntStepper)cc.stepper(StepperShape$.MODULE$.jIntegerStepperShape())).spliterator$mcI$sp(), true);
   }

   public IntStream asJavaParIntStreamFromByte(final IterableOnce cc) {
      return StreamSupport.intStream(((IntStepper)cc.stepper(StepperShape$.MODULE$.jByteStepperShape())).spliterator$mcI$sp(), true);
   }

   public IntStream asJavaParIntStreamFromShort(final IterableOnce cc) {
      return StreamSupport.intStream(((IntStepper)cc.stepper(StepperShape$.MODULE$.jShortStepperShape())).spliterator$mcI$sp(), true);
   }

   public IntStream asJavaParIntStreamFromChar(final IterableOnce cc) {
      return StreamSupport.intStream(((IntStepper)cc.stepper(StepperShape$.MODULE$.jCharacterStepperShape())).spliterator$mcI$sp(), true);
   }

   public DoubleStream asJavaParDoubleStream(final IterableOnce cc) {
      return StreamSupport.doubleStream(((DoubleStepper)cc.stepper(StepperShape$.MODULE$.jDoubleStepperShape())).spliterator$mcD$sp(), true);
   }

   public DoubleStream asJavaParDoubleStreamFromFloat(final IterableOnce cc) {
      return StreamSupport.doubleStream(((DoubleStepper)cc.stepper(StepperShape$.MODULE$.jFloatStepperShape())).spliterator$mcD$sp(), true);
   }

   public LongStream asJavaParLongStream(final IterableOnce cc) {
      return StreamSupport.longStream(((LongStepper)cc.stepper(StepperShape$.MODULE$.jLongStepperShape())).spliterator$mcJ$sp(), true);
   }

   public Stream asJavaParKeyStream(final Map m) {
      return StreamSupport.stream(((AnyStepper)m.keyStepper(StepperShape$.MODULE$.anyStepperShapePrototype())).spliterator(), true);
   }

   public IntStream asJavaParKeyIntStream(final Map m) {
      return StreamSupport.intStream(((IntStepper)m.keyStepper(StepperShape$.MODULE$.jIntegerStepperShape())).spliterator$mcI$sp(), true);
   }

   public IntStream asJavaParKeyIntStreamFromByte(final Map m) {
      return StreamSupport.intStream(((IntStepper)m.keyStepper(StepperShape$.MODULE$.jByteStepperShape())).spliterator$mcI$sp(), true);
   }

   public IntStream asJavaParKeyIntStreamFromShort(final Map m) {
      return StreamSupport.intStream(((IntStepper)m.keyStepper(StepperShape$.MODULE$.jShortStepperShape())).spliterator$mcI$sp(), true);
   }

   public IntStream asJavaParKeyIntStreamFromChar(final Map m) {
      return StreamSupport.intStream(((IntStepper)m.keyStepper(StepperShape$.MODULE$.jCharacterStepperShape())).spliterator$mcI$sp(), true);
   }

   public DoubleStream asJavaParKeyDoubleStream(final Map m) {
      return StreamSupport.doubleStream(((DoubleStepper)m.keyStepper(StepperShape$.MODULE$.jDoubleStepperShape())).spliterator$mcD$sp(), true);
   }

   public DoubleStream asJavaParKeyDoubleStreamFromFloat(final Map m) {
      return StreamSupport.doubleStream(((DoubleStepper)m.keyStepper(StepperShape$.MODULE$.jFloatStepperShape())).spliterator$mcD$sp(), true);
   }

   public LongStream asJavaParKeyLongStream(final Map m) {
      return StreamSupport.longStream(((LongStepper)m.keyStepper(StepperShape$.MODULE$.jLongStepperShape())).spliterator$mcJ$sp(), true);
   }

   public Stream asJavaParValueStream(final Map m) {
      return StreamSupport.stream(((AnyStepper)m.valueStepper(StepperShape$.MODULE$.anyStepperShapePrototype())).spliterator(), true);
   }

   public IntStream asJavaParValueIntStream(final Map m) {
      return StreamSupport.intStream(((IntStepper)m.valueStepper(StepperShape$.MODULE$.jIntegerStepperShape())).spliterator$mcI$sp(), true);
   }

   public IntStream asJavaParValueIntStreamFromByte(final Map m) {
      return StreamSupport.intStream(((IntStepper)m.valueStepper(StepperShape$.MODULE$.jByteStepperShape())).spliterator$mcI$sp(), true);
   }

   public IntStream asJavaParValueIntStreamFromShort(final Map m) {
      return StreamSupport.intStream(((IntStepper)m.valueStepper(StepperShape$.MODULE$.jShortStepperShape())).spliterator$mcI$sp(), true);
   }

   public IntStream asJavaParValueIntStreamFromChar(final Map m) {
      return StreamSupport.intStream(((IntStepper)m.valueStepper(StepperShape$.MODULE$.jCharacterStepperShape())).spliterator$mcI$sp(), true);
   }

   public DoubleStream asJavaParValueDoubleStream(final Map m) {
      return StreamSupport.doubleStream(((DoubleStepper)m.valueStepper(StepperShape$.MODULE$.jDoubleStepperShape())).spliterator$mcD$sp(), true);
   }

   public DoubleStream asJavaParValueDoubleStreamFromFloat(final Map m) {
      return StreamSupport.doubleStream(((DoubleStepper)m.valueStepper(StepperShape$.MODULE$.jFloatStepperShape())).spliterator$mcD$sp(), true);
   }

   public LongStream asJavaParValueLongStream(final Map m) {
      return StreamSupport.longStream(((LongStepper)m.valueStepper(StepperShape$.MODULE$.jLongStepperShape())).spliterator$mcJ$sp(), true);
   }

   private StreamConverters$() {
   }
}
