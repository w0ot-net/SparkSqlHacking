package scala.collection.convert.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import scala.collection.LongStepper;
import scala.collection.Stepper;
import scala.collection.Stepper$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M2Qa\u0002\u0005\u0001\u0019AA\u0001B\u0007\u0001\u0003\u0002\u0003\u0006I\u0001\b\u0005\tG\u0001\u0011\t\u0011)A\u0005I!Aq\u0005\u0001B\u0001B\u0003%A\u0005C\u0003)\u0001\u0011\u0005\u0011\u0006C\u0003.\u0001\u0011\u0005a\u0006C\u00030\u0001\u0011E\u0001G\u0001\tM_:<\u0017I\u001d:bsN#X\r\u001d9fe*\u0011\u0011BC\u0001\u0005S6\u0004HN\u0003\u0002\f\u0019\u000591m\u001c8wKJ$(BA\u0007\u000f\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002\u001f\u0005)1oY1mCN\u0019\u0001!E\u000b\u0011\tI\u0019R#G\u0007\u0002\u0011%\u0011A\u0003\u0003\u0002\u0013\u0013:$W\r_3e'R,\u0007\u000f]3s\u0005\u0006\u001cX\r\u0005\u0002\u0017/5\tA\"\u0003\u0002\u0019\u0019\tYAj\u001c8h'R,\u0007\u000f]3s!\t\u0011\u0002!\u0001\u0006v]\u0012,'\u000f\\=j]\u001e\u001c\u0001\u0001E\u0002\u001e=\u0001j\u0011AD\u0005\u0003?9\u0011Q!\u0011:sCf\u0004\"!H\u0011\n\u0005\tr!\u0001\u0002'p]\u001e\f1aX51!\tiR%\u0003\u0002'\u001d\t\u0019\u0011J\u001c;\u0002\u0007}Kg*\u0001\u0004=S:LGO\u0010\u000b\u00053)ZC\u0006C\u0003\u001b\t\u0001\u0007A\u0004C\u0003$\t\u0001\u0007A\u0005C\u0003(\t\u0001\u0007A%\u0001\u0005oKb$8\u000b^3q)\u0005\u0001\u0013!C:f[&\u001cGn\u001c8f)\tI\u0012\u0007C\u00033\r\u0001\u0007A%\u0001\u0003iC24\u0007"
)
public class LongArrayStepper extends IndexedStepperBase implements LongStepper {
   public final long[] scala$collection$convert$impl$LongArrayStepper$$underlying;

   public Spliterator.OfLong spliterator() {
      return LongStepper.spliterator$(this);
   }

   public PrimitiveIterator.OfLong javaIterator() {
      return LongStepper.javaIterator$(this);
   }

   public Spliterator.OfLong spliterator$mcJ$sp() {
      return LongStepper.spliterator$mcJ$sp$(this);
   }

   public PrimitiveIterator.OfLong javaIterator$mcJ$sp() {
      return LongStepper.javaIterator$mcJ$sp$(this);
   }

   public double nextStep$mcD$sp() {
      return Stepper.nextStep$mcD$sp$(this);
   }

   public int nextStep$mcI$sp() {
      return Stepper.nextStep$mcI$sp$(this);
   }

   public Stepper trySplit$mcD$sp() {
      return Stepper.trySplit$mcD$sp$(this);
   }

   public Stepper trySplit$mcI$sp() {
      return Stepper.trySplit$mcI$sp$(this);
   }

   public Stepper trySplit$mcJ$sp() {
      return Stepper.trySplit$mcJ$sp$(this);
   }

   public Spliterator spliterator$mcD$sp() {
      return Stepper.spliterator$mcD$sp$(this);
   }

   public Spliterator spliterator$mcI$sp() {
      return Stepper.spliterator$mcI$sp$(this);
   }

   public Iterator javaIterator$mcD$sp() {
      return Stepper.javaIterator$mcD$sp$(this);
   }

   public Iterator javaIterator$mcI$sp() {
      return Stepper.javaIterator$mcI$sp$(this);
   }

   public scala.collection.Iterator iterator() {
      return Stepper.iterator$(this);
   }

   public long nextStep() {
      return this.nextStep$mcJ$sp();
   }

   public LongArrayStepper semiclone(final int half) {
      return new LongArrayStepper(this.scala$collection$convert$impl$LongArrayStepper$$underlying, this.i0(), half);
   }

   public long nextStep$mcJ$sp() {
      if (this.hasStep()) {
         int j = this.i0();
         this.i0_$eq(this.i0() + 1);
         return this.scala$collection$convert$impl$LongArrayStepper$$underlying[j];
      } else {
         Stepper$ var10000 = Stepper$.MODULE$;
         throw new NoSuchElementException("Empty Stepper");
      }
   }

   public LongArrayStepper(final long[] underlying, final int _i0, final int _iN) {
      super(_i0, _iN);
      this.scala$collection$convert$impl$LongArrayStepper$$underlying = underlying;
   }
}
