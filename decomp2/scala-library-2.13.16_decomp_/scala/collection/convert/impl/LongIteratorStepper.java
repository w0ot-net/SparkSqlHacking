package scala.collection.convert.impl;

import java.util.Iterator;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import scala.collection.LongStepper;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.StepperShape$;
import scala.jdk.Accumulator;
import scala.jdk.LongAccumulator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005Y2QAB\u0004\u0001\u0017=A\u0011\"\b\u0001\u0003\u0002\u0003\u0006IaH\u0016\t\u000b5\u0002A\u0011\u0001\u0018\t\u000bA\u0002A\u0011C\u0019\t\u000bI\u0002A\u0011A\u001a\t\u000bQ\u0002A\u0011A\u001b\u0003'1{gnZ%uKJ\fGo\u001c:Ti\u0016\u0004\b/\u001a:\u000b\u0005!I\u0011\u0001B5na2T!AC\u0006\u0002\u000f\r|gN^3si*\u0011A\"D\u0001\u000bG>dG.Z2uS>t'\"\u0001\b\u0002\u000bM\u001c\u0017\r\\1\u0014\u0007\u0001\u0001\u0002\u0004E\u0003\u0012%QAB$D\u0001\b\u0013\t\u0019rAA\nJi\u0016\u0014\u0018\r^8s'R,\u0007\u000f]3s\u0005\u0006\u001cX\r\u0005\u0002\u0016-5\tQ\"\u0003\u0002\u0018\u001b\t!Aj\u001c8h!\tI\"$D\u0001\f\u0013\tY2BA\u0006M_:<7\u000b^3qa\u0016\u0014\bCA\t\u0001\u0003-yVO\u001c3fe2L\u0018N\\4\u0004\u0001A\u0019\u0001\u0005\u000b\u000b\u000f\u0005\u00052cB\u0001\u0012&\u001b\u0005\u0019#B\u0001\u0013\u001f\u0003\u0019a$o\\8u}%\ta\"\u0003\u0002(\u001b\u00059\u0001/Y2lC\u001e,\u0017BA\u0015+\u0005!IE/\u001a:bi>\u0014(BA\u0014\u000e\u0013\ta##\u0001\u0006v]\u0012,'\u000f\\=j]\u001e\fa\u0001P5oSRtDC\u0001\u000f0\u0011\u0015i\"\u00011\u0001 \u0003%\u0019X-\\5dY>tW\rF\u0001\u001d\u0003!qW\r\u001f;Ti\u0016\u0004H#\u0001\u000b\u0002\u0011Q\u0014\u0018p\u00159mSR$\u0012\u0001\u0007"
)
public class LongIteratorStepper extends IteratorStepperBase implements LongStepper {
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

   public LongIteratorStepper semiclone() {
      return new LongIteratorStepper((scala.collection.Iterator)null);
   }

   public long nextStep() {
      return this.nextStep$mcJ$sp();
   }

   public LongStepper trySplit() {
      if (this.proxied() != null) {
         return ((LongStepper)this.proxied()).trySplit();
      } else {
         LongAccumulator acc = new LongAccumulator();
         int i = 0;

         int n;
         for(n = this.nextChunkSize() & -4; i < n && this.underlying().hasNext(); ++i) {
            Object $plus$eq_elem = this.underlying().next();
            acc.addOne(BoxesRunTime.unboxToLong($plus$eq_elem));
            $plus$eq_elem = null;
         }

         if (i >= n && this.underlying().hasNext()) {
            LongIteratorStepper ans = this.semiclone();
            StepperShape stepper_shape = StepperShape$.MODULE$.longStepperShape();
            Stepper var11 = ((Accumulator)acc).efficientStepper(stepper_shape);
            stepper_shape = null;
            ans.proxied_$eq(var11);
            this.nextChunkSize_$eq((this.nextChunkSize() & 3) == 3 ? (n < 1073741824 ? n * 2 : n) : this.nextChunkSize() + 1);
            return ans;
         } else {
            StepperShape stepper_shape = StepperShape$.MODULE$.longStepperShape();
            Stepper var10001 = ((Accumulator)acc).efficientStepper(stepper_shape);
            stepper_shape = null;
            this.proxied_$eq(var10001);
            return ((LongStepper)this.proxied()).trySplit();
         }
      }
   }

   public long nextStep$mcJ$sp() {
      return this.proxied() != null ? this.proxied().nextStep$mcJ$sp() : BoxesRunTime.unboxToLong(this.underlying().next());
   }

   public LongIteratorStepper(final scala.collection.Iterator _underlying) {
      super(_underlying);
   }
}
