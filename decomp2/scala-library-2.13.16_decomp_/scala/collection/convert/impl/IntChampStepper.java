package scala.collection.convert.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import scala.Function2;
import scala.collection.IntStepper;
import scala.collection.Stepper;
import scala.collection.Stepper$;
import scala.collection.immutable.Node;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005Y3Qa\u0003\u0007\u0003!QA\u0001B\r\u0001\u0003\u0002\u0003\u0006IA\u0007\u0005\tg\u0001\u0011)\u0019!C\ti!A\u0001\b\u0001B\u0001B\u0003%Q\u0007C\u0003:\u0001\u0011\u0005!\bC\u0003>\u0001\u0011\u0005a\bC\u0003@\u0001\u0011\u0005\u0001i\u0002\u0004B\u0019!\u0005\u0001C\u0011\u0004\u0007\u00171A\t\u0001E\"\t\u000beBA\u0011A$\t\u000b!CA\u0011A%\u0003\u001f%sGo\u00115b[B\u001cF/\u001a9qKJT!!\u0004\b\u0002\t%l\u0007\u000f\u001c\u0006\u0003\u001fA\tqaY8om\u0016\u0014HO\u0003\u0002\u0012%\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003M\tQa]2bY\u0006,\"!\u0006\u0011\u0014\u0007\u00011R\u0006\u0005\u0004\u00181iqR&M\u0007\u0002\u0019%\u0011\u0011\u0004\u0004\u0002\u0011\u0007\"\fW\u000e]*uKB\u0004XM\u001d\"bg\u0016\u0004\"a\u0007\u000f\u000e\u0003II!!\b\n\u0003\u0007%sG\u000f\u0005\u0002 A1\u0001A!B\u0011\u0001\u0005\u0004\u0019#!\u0001+\u0004\u0001E\u0011Ae\n\t\u00037\u0015J!A\n\n\u0003\t9+H\u000e\u001c\t\u0004Q-rR\"A\u0015\u000b\u0005)\u0002\u0012!C5n[V$\u0018M\u00197f\u0013\ta\u0013F\u0001\u0003O_\u0012,\u0007C\u0001\u00180\u001b\u0005\u0001\u0012B\u0001\u0019\u0011\u0005)Ie\u000e^*uKB\u0004XM\u001d\t\u0004/\u0001q\u0012\u0001C0nCb\u001c\u0016N_3\u0002\u000f\u0015DHO]1diV\tQ\u0007E\u0003\u001cmyQ\"$\u0003\u00028%\tIa)\u001e8di&|gNM\u0001\tKb$(/Y2uA\u00051A(\u001b8jiz\"2!M\u001e=\u0011\u0015\u0011D\u00011\u0001\u001b\u0011\u0015\u0019D\u00011\u00016\u0003!qW\r\u001f;Ti\u0016\u0004H#\u0001\u000e\u0002\u0013M,W.[2m_:,G#A\u0019\u0002\u001f%sGo\u00115b[B\u001cF/\u001a9qKJ\u0004\"a\u0006\u0005\u0014\u0005!!\u0005CA\u000eF\u0013\t1%C\u0001\u0004B]f\u0014VM\u001a\u000b\u0002\u0005\u0006!aM]8n+\tQU\n\u0006\u0003L!J#\u0006cA\f\u0001\u0019B\u0011q$\u0014\u0003\u0006C)\u0011\rAT\t\u0003I=\u00032\u0001K\u0016M\u0011\u0015\t&\u00021\u0001\u001b\u0003\u001di\u0017\r_*ju\u0016DQa\u0015\u0006A\u00021\u000bAA]8pi\")1G\u0003a\u0001+B)1D\u000e'\u001b5\u0001"
)
public final class IntChampStepper extends ChampStepperBase implements IntStepper {
   private final Function2 extract;

   public static IntChampStepper from(final int maxSize, final Node root, final Function2 extract) {
      IntChampStepper$ var10000 = IntChampStepper$.MODULE$;
      IntChampStepper from_ans = new IntChampStepper(maxSize, extract);
      from_ans.initRoot(root);
      return from_ans;
   }

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

   public Function2 extract() {
      return this.extract;
   }

   public int nextStep() {
      return this.nextStep$mcI$sp();
   }

   public IntChampStepper semiclone() {
      return new IntChampStepper(0, this.extract());
   }

   public int nextStep$mcI$sp() {
      if (this.hasStep()) {
         int ans = BoxesRunTime.unboxToInt(this.extract().apply(this.currentValueNode(), this.currentValueCursor()));
         this.currentValueCursor_$eq(this.currentValueCursor() + 1);
         this.maxSize_$eq(this.maxSize() - 1);
         return ans;
      } else {
         Stepper$ var10000 = Stepper$.MODULE$;
         throw new NoSuchElementException("Empty Stepper");
      }
   }

   public IntChampStepper(final int _maxSize, final Function2 extract) {
      super(_maxSize);
      this.extract = extract;
   }
}
