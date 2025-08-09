package scala.collection.convert.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import scala.Function2;
import scala.collection.LongStepper;
import scala.collection.Stepper;
import scala.collection.Stepper$;
import scala.collection.immutable.Node;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005e3Qa\u0003\u0007\u0003!QA\u0001B\r\u0001\u0003\u0002\u0003\u0006Ia\r\u0005\tm\u0001\u0011)\u0019!C\to!A1\b\u0001B\u0001B\u0003%\u0001\bC\u0003=\u0001\u0011\u0005Q\bC\u0003A\u0001\u0011\u0005\u0011\tC\u0003C\u0001\u0011\u00051i\u0002\u0004E\u0019!\u0005\u0001#\u0012\u0004\u0007\u00171A\t\u0001\u0005$\t\u000bqBA\u0011\u0001&\t\u000b-CA\u0011\u0001'\u0003!1{gnZ\"iC6\u00048\u000b^3qa\u0016\u0014(BA\u0007\u000f\u0003\u0011IW\u000e\u001d7\u000b\u0005=\u0001\u0012aB2p]Z,'\u000f\u001e\u0006\u0003#I\t!bY8mY\u0016\u001cG/[8o\u0015\u0005\u0019\u0012!B:dC2\fWCA\u000b!'\r\u0001a#\f\t\u0007/aQb$L\u0019\u000e\u00031I!!\u0007\u0007\u0003!\rC\u0017-\u001c9Ti\u0016\u0004\b/\u001a:CCN,\u0007CA\u000e\u001d\u001b\u0005\u0011\u0012BA\u000f\u0013\u0005\u0011auN\\4\u0011\u0005}\u0001C\u0002\u0001\u0003\u0006C\u0001\u0011\ra\t\u0002\u0002)\u000e\u0001\u0011C\u0001\u0013(!\tYR%\u0003\u0002'%\t!a*\u001e7m!\rA3FH\u0007\u0002S)\u0011!\u0006E\u0001\nS6lW\u000f^1cY\u0016L!\u0001L\u0015\u0003\t9{G-\u001a\t\u0003]=j\u0011\u0001E\u0005\u0003aA\u00111\u0002T8oON#X\r\u001d9feB\u0019q\u0003\u0001\u0010\u0002\u0011}k\u0017\r_*ju\u0016\u0004\"a\u0007\u001b\n\u0005U\u0012\"aA%oi\u00069Q\r\u001f;sC\u000e$X#\u0001\u001d\u0011\u000bmIdd\r\u000e\n\u0005i\u0012\"!\u0003$v]\u000e$\u0018n\u001c83\u0003!)\u0007\u0010\u001e:bGR\u0004\u0013A\u0002\u001fj]&$h\bF\u00022}}BQA\r\u0003A\u0002MBQA\u000e\u0003A\u0002a\n\u0001B\\3yiN#X\r\u001d\u000b\u00025\u0005I1/Z7jG2|g.\u001a\u000b\u0002c\u0005\u0001Bj\u001c8h\u0007\"\fW\u000e]*uKB\u0004XM\u001d\t\u0003/!\u0019\"\u0001C$\u0011\u0005mA\u0015BA%\u0013\u0005\u0019\te.\u001f*fMR\tQ)\u0001\u0003ge>lWCA'Q)\u0011q5+V,\u0011\u0007]\u0001q\n\u0005\u0002 !\u0012)\u0011E\u0003b\u0001#F\u0011AE\u0015\t\u0004Q-z\u0005\"\u0002+\u000b\u0001\u0004\u0019\u0014aB7bqNK'0\u001a\u0005\u0006-*\u0001\raT\u0001\u0005e>|G\u000fC\u00037\u0015\u0001\u0007\u0001\fE\u0003\u001cs=\u001b$\u0004"
)
public final class LongChampStepper extends ChampStepperBase implements LongStepper {
   private final Function2 extract;

   public static LongChampStepper from(final int maxSize, final Node root, final Function2 extract) {
      LongChampStepper$ var10000 = LongChampStepper$.MODULE$;
      LongChampStepper from_ans = new LongChampStepper(maxSize, extract);
      from_ans.initRoot(root);
      return from_ans;
   }

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

   public Function2 extract() {
      return this.extract;
   }

   public long nextStep() {
      return this.nextStep$mcJ$sp();
   }

   public LongChampStepper semiclone() {
      return new LongChampStepper(0, this.extract());
   }

   public long nextStep$mcJ$sp() {
      if (this.hasStep()) {
         long ans = BoxesRunTime.unboxToLong(this.extract().apply(this.currentValueNode(), this.currentValueCursor()));
         this.currentValueCursor_$eq(this.currentValueCursor() + 1);
         this.maxSize_$eq(this.maxSize() - 1);
         return ans;
      } else {
         Stepper$ var10000 = Stepper$.MODULE$;
         throw new NoSuchElementException("Empty Stepper");
      }
   }

   public LongChampStepper(final int _maxSize, final Function2 extract) {
      super(_maxSize);
      this.extract = extract;
   }
}
