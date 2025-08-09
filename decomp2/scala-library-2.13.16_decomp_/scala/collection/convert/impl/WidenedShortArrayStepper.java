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
   bytes = "\u0006\u0005M2Qa\u0002\u0005\u0001\u0019AA\u0001B\u0007\u0001\u0003\u0002\u0003\u0006I\u0001\b\u0005\tG\u0001\u0011\t\u0011)A\u0005I!Aq\u0005\u0001B\u0001B\u0003%A\u0005C\u0003)\u0001\u0011\u0005\u0011\u0006C\u0003.\u0001\u0011\u0005a\u0006C\u00030\u0001\u0011E\u0001G\u0001\rXS\u0012,g.\u001a3TQ>\u0014H/\u0011:sCf\u001cF/\u001a9qKJT!!\u0003\u0006\u0002\t%l\u0007\u000f\u001c\u0006\u0003\u00171\tqaY8om\u0016\u0014HO\u0003\u0002\u000e\u001d\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003=\tQa]2bY\u0006\u001c2\u0001A\t\u0016!\u0011\u00112#F\r\u000e\u0003!I!\u0001\u0006\u0005\u0003%%sG-\u001a=fIN#X\r\u001d9fe\n\u000b7/\u001a\t\u0003-]i\u0011\u0001D\u0005\u000311\u0011!\"\u00138u'R,\u0007\u000f]3s!\t\u0011\u0002!\u0001\u0006v]\u0012,'\u000f\\=j]\u001e\u001c\u0001\u0001E\u0002\u001e=\u0001j\u0011AD\u0005\u0003?9\u0011Q!\u0011:sCf\u0004\"!H\u0011\n\u0005\tr!!B*i_J$\u0018aA0jaA\u0011Q$J\u0005\u0003M9\u00111!\u00138u\u0003\ry\u0016NT\u0001\u0007y%t\u0017\u000e\u001e \u0015\teQ3\u0006\f\u0005\u00065\u0011\u0001\r\u0001\b\u0005\u0006G\u0011\u0001\r\u0001\n\u0005\u0006O\u0011\u0001\r\u0001J\u0001\t]\u0016DHo\u0015;faR\tA%A\u0005tK6L7\r\\8oKR\u0011\u0011$\r\u0005\u0006e\u0019\u0001\r\u0001J\u0001\u0005Q\u0006dg\r"
)
public class WidenedShortArrayStepper extends IndexedStepperBase implements IntStepper {
   public final short[] scala$collection$convert$impl$WidenedShortArrayStepper$$underlying;

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

   public WidenedShortArrayStepper semiclone(final int half) {
      return new WidenedShortArrayStepper(this.scala$collection$convert$impl$WidenedShortArrayStepper$$underlying, this.i0(), half);
   }

   public int nextStep$mcI$sp() {
      if (this.hasStep()) {
         int j = this.i0();
         this.i0_$eq(this.i0() + 1);
         return this.scala$collection$convert$impl$WidenedShortArrayStepper$$underlying[j];
      } else {
         Stepper$ var10000 = Stepper$.MODULE$;
         throw new NoSuchElementException("Empty Stepper");
      }
   }

   public WidenedShortArrayStepper(final short[] underlying, final int _i0, final int _iN) {
      super(_i0, _iN);
      this.scala$collection$convert$impl$WidenedShortArrayStepper$$underlying = underlying;
   }
}
