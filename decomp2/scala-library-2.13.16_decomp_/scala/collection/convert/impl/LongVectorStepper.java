package scala.collection.convert.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import scala.collection.LongStepper;
import scala.collection.Stepper;
import scala.collection.Stepper$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005u2Q\u0001C\u0005\u0001\u001bEA\u0001b\u0007\u0001\u0003\u0002\u0003\u0006I!\b\u0005\tC\u0001\u0011\t\u0011)A\u0005;!I!\u0005\u0001B\u0001B\u0003%Qd\t\u0005\nK\u0001\u0011\t\u0011)A\u0005M1BQA\f\u0001\u0005\u0002=BQ\u0001\u000e\u0001\u0005\u0002UBQ!\u000f\u0001\u0005\u0002i\u0012\u0011\u0003T8oOZ+7\r^8s'R,\u0007\u000f]3s\u0015\tQ1\"\u0001\u0003j[Bd'B\u0001\u0007\u000e\u0003\u001d\u0019wN\u001c<feRT!AD\b\u0002\u0015\r|G\u000e\\3di&|gNC\u0001\u0011\u0003\u0015\u00198-\u00197b'\r\u0001!C\u0006\t\u0005'Q1\"$D\u0001\n\u0013\t)\u0012BA\tWK\u000e$xN]*uKB\u0004XM\u001d\"bg\u0016\u0004\"a\u0006\r\u000e\u00035I!!G\u0007\u0003\u00171{gnZ*uKB\u0004XM\u001d\t\u0003'\u0001\t1aX51\u0007\u0001\u0001\"AH\u0010\u000e\u0003=I!\u0001I\b\u0003\u0007%sG/A\u0002`S:\u000b\u0011b\u00183jgBd\u0017-\u001f(\n\u0005\u0011\"\u0012\u0001\u00033jgBd\u0017-\u001f(\u0002\r}#(/\u001e8l!\rqr%K\u0005\u0003Q=\u0011Q!\u0011:sCf\u0004\"A\b\u0016\n\u0005-z!AB!osJ+g-\u0003\u0002.)\u0005)AO];oW\u00061A(\u001b8jiz\"RA\u0007\u00192eMBQaG\u0003A\u0002uAQ!I\u0003A\u0002uAQAI\u0003A\u0002uAQ!J\u0003A\u0002\u0019\n\u0001B\\3yiN#X\r\u001d\u000b\u0002mA\u0011adN\u0005\u0003q=\u0011A\u0001T8oO\u0006I1/Z7jG2|g.\u001a\u000b\u00035mBQ\u0001P\u0004A\u0002u\tA\u0001[1mM\u0002"
)
public class LongVectorStepper extends VectorStepperBase implements LongStepper {
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

   public LongVectorStepper semiclone(final int half) {
      LongVectorStepper ans = new LongVectorStepper(this.i0(), half, this.displayN(), this.trunk());
      this.index_$eq(32);
      this.index1_$eq(32);
      this.i0_$eq(half);
      return ans;
   }

   public long nextStep$mcJ$sp() {
      if (this.hasStep()) {
         this.index_$eq(this.index() + 1);
         if (this.index() >= 32) {
            this.advanceData(this.i0());
         }

         this.i0_$eq(this.i0() + 1);
         return BoxesRunTime.unboxToLong(this.leaves()[this.index()]);
      } else {
         Stepper$ var10000 = Stepper$.MODULE$;
         throw new NoSuchElementException("Empty Stepper");
      }
   }

   public LongVectorStepper(final int _i0, final int _iN, final int _displayN, final Object[] _trunk) {
      super(_i0, _iN, _displayN, _trunk);
   }
}
