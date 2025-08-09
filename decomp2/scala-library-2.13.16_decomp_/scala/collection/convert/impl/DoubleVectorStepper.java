package scala.collection.convert.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import scala.collection.DoubleStepper;
import scala.collection.Stepper;
import scala.collection.Stepper$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005u2Q\u0001C\u0005\u0001\u001bEA\u0001b\u0007\u0001\u0003\u0002\u0003\u0006I!\b\u0005\tC\u0001\u0011\t\u0011)A\u0005;!I!\u0005\u0001B\u0001B\u0003%Qd\t\u0005\nK\u0001\u0011\t\u0011)A\u0005M1BQA\f\u0001\u0005\u0002=BQ\u0001\u000e\u0001\u0005\u0002UBQ!\u000f\u0001\u0005\u0002i\u00121\u0003R8vE2,g+Z2u_J\u001cF/\u001a9qKJT!AC\u0006\u0002\t%l\u0007\u000f\u001c\u0006\u0003\u00195\tqaY8om\u0016\u0014HO\u0003\u0002\u000f\u001f\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003A\tQa]2bY\u0006\u001c2\u0001\u0001\n\u0017!\u0011\u0019BC\u0006\u000e\u000e\u0003%I!!F\u0005\u0003#Y+7\r^8s'R,\u0007\u000f]3s\u0005\u0006\u001cX\r\u0005\u0002\u001815\tQ\"\u0003\u0002\u001a\u001b\tiAi\\;cY\u0016\u001cF/\u001a9qKJ\u0004\"a\u0005\u0001\u0002\u0007}K\u0007g\u0001\u0001\u0011\u0005yyR\"A\b\n\u0005\u0001z!aA%oi\u0006\u0019q,\u001b(\u0002\u0013}#\u0017n\u001d9mCft\u0015B\u0001\u0013\u0015\u0003!!\u0017n\u001d9mCft\u0015AB0ueVt7\u000eE\u0002\u001fO%J!\u0001K\b\u0003\u000b\u0005\u0013(/Y=\u0011\u0005yQ\u0013BA\u0016\u0010\u0005\u0019\te.\u001f*fM&\u0011Q\u0006F\u0001\u0006iJ,hn[\u0001\u0007y%t\u0017\u000e\u001e \u0015\u000bi\u0001\u0014GM\u001a\t\u000bm)\u0001\u0019A\u000f\t\u000b\u0005*\u0001\u0019A\u000f\t\u000b\t*\u0001\u0019A\u000f\t\u000b\u0015*\u0001\u0019\u0001\u0014\u0002\u00119,\u0007\u0010^*uKB$\u0012A\u000e\t\u0003=]J!\u0001O\b\u0003\r\u0011{WO\u00197f\u0003%\u0019X-\\5dY>tW\r\u0006\u0002\u001bw!)Ah\u0002a\u0001;\u0005!\u0001.\u00197g\u0001"
)
public class DoubleVectorStepper extends VectorStepperBase implements DoubleStepper {
   public Spliterator.OfDouble spliterator() {
      return DoubleStepper.spliterator$(this);
   }

   public PrimitiveIterator.OfDouble javaIterator() {
      return DoubleStepper.javaIterator$(this);
   }

   public Spliterator.OfDouble spliterator$mcD$sp() {
      return DoubleStepper.spliterator$mcD$sp$(this);
   }

   public PrimitiveIterator.OfDouble javaIterator$mcD$sp() {
      return DoubleStepper.javaIterator$mcD$sp$(this);
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

   public Spliterator spliterator$mcI$sp() {
      return Stepper.spliterator$mcI$sp$(this);
   }

   public Spliterator spliterator$mcJ$sp() {
      return Stepper.spliterator$mcJ$sp$(this);
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

   public double nextStep() {
      return this.nextStep$mcD$sp();
   }

   public DoubleVectorStepper semiclone(final int half) {
      DoubleVectorStepper ans = new DoubleVectorStepper(this.i0(), half, this.displayN(), this.trunk());
      this.index_$eq(32);
      this.index1_$eq(32);
      this.i0_$eq(half);
      return ans;
   }

   public double nextStep$mcD$sp() {
      if (this.hasStep()) {
         this.index_$eq(this.index() + 1);
         if (this.index() >= 32) {
            this.advanceData(this.i0());
         }

         this.i0_$eq(this.i0() + 1);
         return BoxesRunTime.unboxToDouble(this.leaves()[this.index()]);
      } else {
         Stepper$ var10000 = Stepper$.MODULE$;
         throw new NoSuchElementException("Empty Stepper");
      }
   }

   public DoubleVectorStepper(final int _i0, final int _iN, final int _displayN, final Object[] _trunk) {
      super(_i0, _iN, _displayN, _trunk);
   }
}
