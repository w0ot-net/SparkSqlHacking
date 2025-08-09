package scala.collection.convert.impl;

import java.util.Iterator;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import scala.collection.IntStepper;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.StepperShape$;
import scala.jdk.Accumulator;
import scala.jdk.IntAccumulator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005Y2QAB\u0004\u0001\u0017=A\u0011\"\b\u0001\u0003\u0002\u0003\u0006IaH\u0016\t\u000b5\u0002A\u0011\u0001\u0018\t\u000bA\u0002A\u0011C\u0019\t\u000bI\u0002A\u0011A\u001a\t\u000bQ\u0002A\u0011A\u001b\u0003%%sG/\u0013;fe\u0006$xN]*uKB\u0004XM\u001d\u0006\u0003\u0011%\tA![7qY*\u0011!bC\u0001\bG>tg/\u001a:u\u0015\taQ\"\u0001\u0006d_2dWm\u0019;j_:T\u0011AD\u0001\u0006g\u000e\fG.Y\n\u0004\u0001AA\u0002#B\t\u0013)aaR\"A\u0004\n\u0005M9!aE%uKJ\fGo\u001c:Ti\u0016\u0004\b/\u001a:CCN,\u0007CA\u000b\u0017\u001b\u0005i\u0011BA\f\u000e\u0005\rIe\u000e\u001e\t\u00033ii\u0011aC\u0005\u00037-\u0011!\"\u00138u'R,\u0007\u000f]3s!\t\t\u0002!A\u0006`k:$WM\u001d7zS:<7\u0001\u0001\t\u0004A!\"bBA\u0011'\u001d\t\u0011S%D\u0001$\u0015\t!c$\u0001\u0004=e>|GOP\u0005\u0002\u001d%\u0011q%D\u0001\ba\u0006\u001c7.Y4f\u0013\tI#F\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0015\t9S\"\u0003\u0002-%\u0005QQO\u001c3fe2L\u0018N\\4\u0002\rqJg.\u001b;?)\tar\u0006C\u0003\u001e\u0005\u0001\u0007q$A\u0005tK6L7\r\\8oKR\tA$\u0001\u0005oKb$8\u000b^3q)\u0005!\u0012\u0001\u0003;ssN\u0003H.\u001b;\u0015\u0003a\u0001"
)
public class IntIteratorStepper extends IteratorStepperBase implements IntStepper {
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

   public IntIteratorStepper semiclone() {
      return new IntIteratorStepper((scala.collection.Iterator)null);
   }

   public int nextStep() {
      return this.nextStep$mcI$sp();
   }

   public IntStepper trySplit() {
      if (this.proxied() != null) {
         return ((IntStepper)this.proxied()).trySplit();
      } else {
         IntAccumulator acc = new IntAccumulator();
         int i = 0;

         int n;
         for(n = this.nextChunkSize() & -4; i < n && this.underlying().hasNext(); ++i) {
            Object $plus$eq_elem = this.underlying().next();
            acc.addOne(BoxesRunTime.unboxToInt($plus$eq_elem));
            $plus$eq_elem = null;
         }

         if (i >= n && this.underlying().hasNext()) {
            IntIteratorStepper ans = this.semiclone();
            StepperShape stepper_shape = StepperShape$.MODULE$.intStepperShape();
            Stepper var11 = ((Accumulator)acc).efficientStepper(stepper_shape);
            stepper_shape = null;
            ans.proxied_$eq(var11);
            this.nextChunkSize_$eq((this.nextChunkSize() & 3) == 3 ? (n < 1073741824 ? n * 2 : n) : this.nextChunkSize() + 1);
            return ans;
         } else {
            StepperShape stepper_shape = StepperShape$.MODULE$.intStepperShape();
            Stepper var10001 = ((Accumulator)acc).efficientStepper(stepper_shape);
            stepper_shape = null;
            this.proxied_$eq(var10001);
            return ((IntStepper)this.proxied()).trySplit();
         }
      }
   }

   public int nextStep$mcI$sp() {
      return this.proxied() != null ? this.proxied().nextStep$mcI$sp() : BoxesRunTime.unboxToInt(this.underlying().next());
   }

   public IntIteratorStepper(final scala.collection.Iterator _underlying) {
      super(_underlying);
   }
}
