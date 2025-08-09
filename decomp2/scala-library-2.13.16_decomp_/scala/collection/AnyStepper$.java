package scala.collection;

public final class AnyStepper$ {
   public static final AnyStepper$ MODULE$ = new AnyStepper$();

   public AnyStepper ofSeqDoubleStepper(final DoubleStepper st) {
      return new AnyStepper.BoxedDoubleStepper(st);
   }

   public AnyStepper ofParDoubleStepper(final DoubleStepper st) {
      return new Stepper.EfficientSplit(st) {
      };
   }

   public AnyStepper ofSeqIntStepper(final IntStepper st) {
      return new AnyStepper.BoxedIntStepper(st);
   }

   public AnyStepper ofParIntStepper(final IntStepper st) {
      return new Stepper.EfficientSplit(st) {
      };
   }

   public AnyStepper ofSeqLongStepper(final LongStepper st) {
      return new AnyStepper.BoxedLongStepper(st);
   }

   public AnyStepper ofParLongStepper(final LongStepper st) {
      return new Stepper.EfficientSplit(st) {
      };
   }

   private AnyStepper$() {
   }
}
