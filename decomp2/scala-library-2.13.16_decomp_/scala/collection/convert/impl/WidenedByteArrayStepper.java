package scala.collection.convert.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import scala.collection.IntStepper;
import scala.collection.Stepper;
import scala.collection.Stepper$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M2Qa\u0002\u0005\u0001\u0019AA\u0001B\u0007\u0001\u0003\u0002\u0003\u0006I\u0001\b\u0005\tG\u0001\u0011\t\u0011)A\u0005I!Aq\u0005\u0001B\u0001B\u0003%A\u0005C\u0003)\u0001\u0011\u0005\u0011\u0006C\u0003.\u0001\u0011\u0005a\u0006C\u00030\u0001\u0011E\u0001GA\fXS\u0012,g.\u001a3CsR,\u0017I\u001d:bsN#X\r\u001d9fe*\u0011\u0011BC\u0001\u0005S6\u0004HN\u0003\u0002\f\u0019\u000591m\u001c8wKJ$(BA\u0007\u000f\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002\u001f\u0005)1oY1mCN\u0019\u0001!E\u000b\u0011\tI\u0019R#G\u0007\u0002\u0011%\u0011A\u0003\u0003\u0002\u0013\u0013:$W\r_3e'R,\u0007\u000f]3s\u0005\u0006\u001cX\r\u0005\u0002\u0017/5\tA\"\u0003\u0002\u0019\u0019\tQ\u0011J\u001c;Ti\u0016\u0004\b/\u001a:\u0011\u0005I\u0001\u0011AC;oI\u0016\u0014H._5oO\u000e\u0001\u0001cA\u000f\u001fA5\ta\"\u0003\u0002 \u001d\t)\u0011I\u001d:bsB\u0011Q$I\u0005\u0003E9\u0011AAQ=uK\u0006\u0019q,\u001b\u0019\u0011\u0005u)\u0013B\u0001\u0014\u000f\u0005\rIe\u000e^\u0001\u0004?&t\u0015A\u0002\u001fj]&$h\b\u0006\u0003\u001aU-b\u0003\"\u0002\u000e\u0005\u0001\u0004a\u0002\"B\u0012\u0005\u0001\u0004!\u0003\"B\u0014\u0005\u0001\u0004!\u0013\u0001\u00038fqR\u001cF/\u001a9\u0015\u0003\u0011\n\u0011b]3nS\u000edwN\\3\u0015\u0005e\t\u0004\"\u0002\u001a\u0007\u0001\u0004!\u0013\u0001\u00025bY\u001a\u0004"
)
public class WidenedByteArrayStepper extends IndexedStepperBase implements IntStepper {
   public final byte[] scala$collection$convert$impl$WidenedByteArrayStepper$$underlying;

   public Spliterator.OfInt spliterator() {
      return IntStepper.spliterator$(this);
   }

   public PrimitiveIterator.OfInt javaIterator() {
      return IntStepper.javaIterator$(this);
   }

   public Spliterator.OfInt spliterator$mcI$sp() {
      return IntStepper.spliterator$mcI$sp$(this);
   }

   public PrimitiveIterator.OfInt javaIterator$mcI$sp() {
      return IntStepper.javaIterator$mcI$sp$(this);
   }

   public double nextStep$mcD$sp() {
      return Stepper.nextStep$mcD$sp$(this);
   }

   public long nextStep$mcJ$sp() {
      return Stepper.nextStep$mcJ$sp$(this);
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

   public Spliterator spliterator$mcJ$sp() {
      return Stepper.spliterator$mcJ$sp$(this);
   }

   public Iterator javaIterator$mcD$sp() {
      return Stepper.javaIterator$mcD$sp$(this);
   }

   public Iterator javaIterator$mcJ$sp() {
      return Stepper.javaIterator$mcJ$sp$(this);
   }

   public scala.collection.Iterator iterator() {
      return Stepper.iterator$(this);
   }

   public int nextStep() {
      return this.nextStep$mcI$sp();
   }

   public WidenedByteArrayStepper semiclone(final int half) {
      return new WidenedByteArrayStepper(this.scala$collection$convert$impl$WidenedByteArrayStepper$$underlying, this.i0(), half);
   }

   public int nextStep$mcI$sp() {
      if (this.hasStep()) {
         int j = this.i0();
         this.i0_$eq(this.i0() + 1);
         return this.scala$collection$convert$impl$WidenedByteArrayStepper$$underlying[j];
      } else {
         Stepper$ var10000 = Stepper$.MODULE$;
         throw new NoSuchElementException("Empty Stepper");
      }
   }

   public WidenedByteArrayStepper(final byte[] underlying, final int _i0, final int _iN) {
      super(_i0, _iN);
      this.scala$collection$convert$impl$WidenedByteArrayStepper$$underlying = underlying;
   }
}
