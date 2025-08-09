package scala.collection.convert.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import scala.collection.AnyStepper;
import scala.collection.Stepper;
import scala.collection.Stepper$;
import scala.collection.immutable.NumericRange;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}2Qa\u0002\u0005\u0001\u0019AA\u0001\u0002\u000b\u0001\u0003\u0002\u0003\u0006I!\u000b\u0005\t_\u0001\u0011\t\u0011)A\u0005a!A1\u0007\u0001B\u0001B\u0003%\u0001\u0007C\u00035\u0001\u0011\u0005Q\u0007C\u0003:\u0001\u0011\u0005!\bC\u0003<\u0001\u0011\u0005AH\u0001\fB]ftU/\\3sS\u000e\u0014\u0016M\\4f'R,\u0007\u000f]3s\u0015\tI!\"\u0001\u0003j[Bd'BA\u0006\r\u0003\u001d\u0019wN\u001c<feRT!!\u0004\b\u0002\u0015\r|G\u000e\\3di&|gNC\u0001\u0010\u0003\u0015\u00198-\u00197b+\t\tBdE\u0002\u0001%Y\u0001Ba\u0005\u000b\u0017O5\t\u0001\"\u0003\u0002\u0016\u0011\t\u0011\u0012J\u001c3fq\u0016$7\u000b^3qa\u0016\u0014()Y:f!\r9\u0002DG\u0007\u0002\u0019%\u0011\u0011\u0004\u0004\u0002\u000b\u0003:L8\u000b^3qa\u0016\u0014\bCA\u000e\u001d\u0019\u0001!Q!\b\u0001C\u0002}\u0011\u0011!Q\u0002\u0001#\t\u0001C\u0005\u0005\u0002\"E5\ta\"\u0003\u0002$\u001d\t9aj\u001c;iS:<\u0007CA\u0011&\u0013\t1cBA\u0002B]f\u00042a\u0005\u0001\u001b\u0003))h\u000eZ3sYfLgn\u001a\t\u0004U5RR\"A\u0016\u000b\u00051b\u0011!C5n[V$\u0018M\u00197f\u0013\tq3F\u0001\u0007Ok6,'/[2SC:<W-A\u0002`SB\u0002\"!I\u0019\n\u0005Ir!aA%oi\u0006\u0019q,\u001b(\u0002\rqJg.\u001b;?)\u00119cg\u000e\u001d\t\u000b!\"\u0001\u0019A\u0015\t\u000b=\"\u0001\u0019\u0001\u0019\t\u000bM\"\u0001\u0019\u0001\u0019\u0002\u00119,\u0007\u0010^*uKB$\u0012AG\u0001\ng\u0016l\u0017n\u00197p]\u0016$\"aJ\u001f\t\u000by2\u0001\u0019\u0001\u0019\u0002\t!\fGN\u001a"
)
public class AnyNumericRangeStepper extends IndexedStepperBase implements AnyStepper {
   private final NumericRange underlying;

   public Spliterator spliterator() {
      return AnyStepper.spliterator$(this);
   }

   public Iterator javaIterator() {
      return AnyStepper.javaIterator$(this);
   }

   public double nextStep$mcD$sp() {
      return Stepper.nextStep$mcD$sp$(this);
   }

   public int nextStep$mcI$sp() {
      return Stepper.nextStep$mcI$sp$(this);
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

   public Spliterator spliterator$mcI$sp() {
      return Stepper.spliterator$mcI$sp$(this);
   }

   public Spliterator spliterator$mcJ$sp() {
      return Stepper.spliterator$mcJ$sp$(this);
   }

   public Iterator javaIterator$mcD$sp() {
      return Stepper.javaIterator$mcD$sp$(this);
   }

   public Iterator javaIterator$mcI$sp() {
      return Stepper.javaIterator$mcI$sp$(this);
   }

   public Iterator javaIterator$mcJ$sp() {
      return Stepper.javaIterator$mcJ$sp$(this);
   }

   public scala.collection.Iterator iterator() {
      return Stepper.iterator$(this);
   }

   public Object nextStep() {
      if (this.hasStep()) {
         int j = this.i0();
         this.i0_$eq(this.i0() + 1);
         return this.underlying.apply(j);
      } else {
         Stepper$ var10000 = Stepper$.MODULE$;
         throw new NoSuchElementException("Empty Stepper");
      }
   }

   public AnyNumericRangeStepper semiclone(final int half) {
      return new AnyNumericRangeStepper(this.underlying, this.i0(), half);
   }

   public AnyNumericRangeStepper(final NumericRange underlying, final int _i0, final int _iN) {
      super(_i0, _iN);
      this.underlying = underlying;
   }
}
