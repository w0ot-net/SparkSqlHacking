package scala.collection.convert.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import scala.collection.AnyStepper;
import scala.collection.Stepper;
import scala.collection.Stepper$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00193Q\u0001C\u0005\u0001\u001bEA\u0001\"\u000b\u0001\u0003\u0002\u0003\u0006IA\u000b\u0005\t[\u0001\u0011\t\u0011)A\u0005U!Ia\u0006\u0001B\u0001B\u0003%!f\f\u0005\nc\u0001\u0011\t\u0011)A\u0005eaBQA\u000f\u0001\u0005\u0002mBQ\u0001\u0011\u0001\u0005\u0002\u0005CQA\u0011\u0001\u0005\u0002\r\u0013\u0001#\u00118z-\u0016\u001cGo\u001c:Ti\u0016\u0004\b/\u001a:\u000b\u0005)Y\u0011\u0001B5na2T!\u0001D\u0007\u0002\u000f\r|gN^3si*\u0011abD\u0001\u000bG>dG.Z2uS>t'\"\u0001\t\u0002\u000bM\u001c\u0017\r\\1\u0016\u0005Ii2c\u0001\u0001\u0014/A!A#F\f)\u001b\u0005I\u0011B\u0001\f\n\u0005E1Vm\u0019;peN#X\r\u001d9fe\n\u000b7/\u001a\t\u00041eYR\"A\u0007\n\u0005ii!AC!osN#X\r\u001d9feB\u0011A$\b\u0007\u0001\t\u0015q\u0002A1\u0001!\u0005\u0005\t5\u0001A\t\u0003C\u0015\u0002\"AI\u0012\u000e\u0003=I!\u0001J\b\u0003\u000f9{G\u000f[5oOB\u0011!EJ\u0005\u0003O=\u00111!\u00118z!\r!\u0002aG\u0001\u0004?&\u0004\u0004C\u0001\u0012,\u0013\tasBA\u0002J]R\f1aX5O\u0003%yF-[:qY\u0006Lh*\u0003\u00021+\u0005AA-[:qY\u0006Lh*\u0001\u0004`iJ,hn\u001b\t\u0004EM*\u0014B\u0001\u001b\u0010\u0005\u0015\t%O]1z!\t\u0011c'\u0003\u00028\u001f\t1\u0011I\\=SK\u001aL!!O\u000b\u0002\u000bQ\u0014XO\\6\u0002\rqJg.\u001b;?)\u0015AC(\u0010 @\u0011\u0015IS\u00011\u0001+\u0011\u0015iS\u00011\u0001+\u0011\u0015qS\u00011\u0001+\u0011\u0015\tT\u00011\u00013\u0003!qW\r\u001f;Ti\u0016\u0004H#A\u000e\u0002\u0013M,W.[2m_:,GC\u0001\u0015E\u0011\u0015)u\u00011\u0001+\u0003\u0011A\u0017\r\u001c4"
)
public class AnyVectorStepper extends VectorStepperBase implements AnyStepper {
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
         this.index_$eq(this.index() + 1);
         if (this.index() >= 32) {
            this.advanceData(this.i0());
         }

         this.i0_$eq(this.i0() + 1);
         return this.leaves()[this.index()];
      } else {
         Stepper$ var10000 = Stepper$.MODULE$;
         throw new NoSuchElementException("Empty Stepper");
      }
   }

   public AnyVectorStepper semiclone(final int half) {
      AnyVectorStepper ans = new AnyVectorStepper(this.i0(), half, this.displayN(), this.trunk());
      this.index_$eq(32);
      this.index1_$eq(32);
      this.i0_$eq(half);
      return ans;
   }

   public AnyVectorStepper(final int _i0, final int _iN, final int _displayN, final Object[] _trunk) {
      super(_i0, _iN, _displayN, _trunk);
   }
}
