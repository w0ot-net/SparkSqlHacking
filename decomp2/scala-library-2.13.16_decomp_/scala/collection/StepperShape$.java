package scala.collection;

public final class StepperShape$ implements StepperShapeLowPriority1 {
   public static final StepperShape$ MODULE$ = new StepperShape$();
   private static final int ReferenceShape;
   private static final int IntShape;
   private static final int LongShape;
   private static final int DoubleShape;
   private static final int ByteShape;
   private static final int ShortShape;
   private static final int CharShape;
   private static final int FloatShape;
   private static final StepperShape intStepperShape;
   private static final StepperShape jIntegerStepperShape;
   private static final StepperShape longStepperShape;
   private static final StepperShape jLongStepperShape;
   private static final StepperShape doubleStepperShape;
   private static final StepperShape jDoubleStepperShape;
   private static final StepperShape byteStepperShape;
   private static final StepperShape jByteStepperShape;
   private static final StepperShape shortStepperShape;
   private static final StepperShape jShortStepperShape;
   private static final StepperShape charStepperShape;
   private static final StepperShape jCharacterStepperShape;
   private static final StepperShape floatStepperShape;
   private static final StepperShape jFloatStepperShape;
   private static StepperShape anyStepperShapePrototype;

   static {
      StepperShapeLowPriority2.$init$(MODULE$);
      StepperShape$ var10000 = MODULE$;
      ReferenceShape = 0;
      IntShape = 1;
      LongShape = 2;
      DoubleShape = 3;
      ByteShape = 4;
      ShortShape = 5;
      CharShape = 6;
      FloatShape = 7;
      intStepperShape = new StepperShape() {
         public int shape() {
            return StepperShape$.MODULE$.IntShape();
         }

         public IntStepper seqUnbox(final AnyStepper st) {
            return new Stepper.UnboxingIntStepper(st);
         }

         public IntStepper parUnbox(final AnyStepper st) {
            return new Stepper.EfficientSplit(st) {
            };
         }
      };
      jIntegerStepperShape = MODULE$.intStepperShape();
      longStepperShape = new StepperShape() {
         public int shape() {
            return StepperShape$.MODULE$.LongShape();
         }

         public LongStepper seqUnbox(final AnyStepper st) {
            return new Stepper.UnboxingLongStepper(st);
         }

         public LongStepper parUnbox(final AnyStepper st) {
            return new Stepper.EfficientSplit(st) {
            };
         }
      };
      jLongStepperShape = MODULE$.longStepperShape();
      doubleStepperShape = new StepperShape() {
         public int shape() {
            return StepperShape$.MODULE$.DoubleShape();
         }

         public DoubleStepper seqUnbox(final AnyStepper st) {
            return new Stepper.UnboxingDoubleStepper(st);
         }

         public DoubleStepper parUnbox(final AnyStepper st) {
            return new Stepper.EfficientSplit(st) {
            };
         }
      };
      jDoubleStepperShape = MODULE$.doubleStepperShape();
      byteStepperShape = new StepperShape() {
         public int shape() {
            return StepperShape$.MODULE$.ByteShape();
         }

         public IntStepper seqUnbox(final AnyStepper st) {
            return new Stepper.UnboxingByteStepper(st);
         }

         public IntStepper parUnbox(final AnyStepper st) {
            return new Stepper.EfficientSplit(st) {
            };
         }
      };
      jByteStepperShape = MODULE$.byteStepperShape();
      shortStepperShape = new StepperShape() {
         public int shape() {
            return StepperShape$.MODULE$.ShortShape();
         }

         public IntStepper seqUnbox(final AnyStepper st) {
            return new Stepper.UnboxingShortStepper(st);
         }

         public IntStepper parUnbox(final AnyStepper st) {
            return new Stepper.EfficientSplit(st) {
            };
         }
      };
      jShortStepperShape = MODULE$.shortStepperShape();
      charStepperShape = new StepperShape() {
         public int shape() {
            return StepperShape$.MODULE$.CharShape();
         }

         public IntStepper seqUnbox(final AnyStepper st) {
            return new Stepper.UnboxingCharStepper(st);
         }

         public IntStepper parUnbox(final AnyStepper st) {
            return new Stepper.EfficientSplit(st) {
            };
         }
      };
      jCharacterStepperShape = MODULE$.charStepperShape();
      floatStepperShape = new StepperShape() {
         public int shape() {
            return StepperShape$.MODULE$.FloatShape();
         }

         public DoubleStepper seqUnbox(final AnyStepper st) {
            return new Stepper.UnboxingFloatStepper(st);
         }

         public DoubleStepper parUnbox(final AnyStepper st) {
            return new Stepper.EfficientSplit(st) {
            };
         }
      };
      jFloatStepperShape = MODULE$.floatStepperShape();
   }

   public StepperShape anyStepperShape() {
      return StepperShapeLowPriority1.anyStepperShape$(this);
   }

   public StepperShape baseStepperShape() {
      return StepperShapeLowPriority2.baseStepperShape$(this);
   }

   public StepperShape anyStepperShapePrototype() {
      return anyStepperShapePrototype;
   }

   public void scala$collection$StepperShapeLowPriority2$_setter_$anyStepperShapePrototype_$eq(final StepperShape x$1) {
      anyStepperShapePrototype = x$1;
   }

   public int ReferenceShape() {
      return ReferenceShape;
   }

   public int IntShape() {
      return IntShape;
   }

   public int LongShape() {
      return LongShape;
   }

   public int DoubleShape() {
      return DoubleShape;
   }

   public int ByteShape() {
      return ByteShape;
   }

   public int ShortShape() {
      return ShortShape;
   }

   public int CharShape() {
      return CharShape;
   }

   public int FloatShape() {
      return FloatShape;
   }

   public StepperShape intStepperShape() {
      return intStepperShape;
   }

   public StepperShape jIntegerStepperShape() {
      return jIntegerStepperShape;
   }

   public StepperShape longStepperShape() {
      return longStepperShape;
   }

   public StepperShape jLongStepperShape() {
      return jLongStepperShape;
   }

   public StepperShape doubleStepperShape() {
      return doubleStepperShape;
   }

   public StepperShape jDoubleStepperShape() {
      return jDoubleStepperShape;
   }

   public StepperShape byteStepperShape() {
      return byteStepperShape;
   }

   public StepperShape jByteStepperShape() {
      return jByteStepperShape;
   }

   public StepperShape shortStepperShape() {
      return shortStepperShape;
   }

   public StepperShape jShortStepperShape() {
      return jShortStepperShape;
   }

   public StepperShape charStepperShape() {
      return charStepperShape;
   }

   public StepperShape jCharacterStepperShape() {
      return jCharacterStepperShape;
   }

   public StepperShape floatStepperShape() {
      return floatStepperShape;
   }

   public StepperShape jFloatStepperShape() {
      return jFloatStepperShape;
   }

   private StepperShape$() {
   }
}
