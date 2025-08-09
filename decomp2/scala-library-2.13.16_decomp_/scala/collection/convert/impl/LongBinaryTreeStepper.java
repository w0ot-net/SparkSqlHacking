package scala.collection.convert.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import scala.Function1;
import scala.collection.LongStepper;
import scala.collection.Stepper;
import scala.collection.Stepper$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005M4Q\u0001E\t\u0003+eA\u0001\u0002\u000e\u0001\u0003\u0002\u0003\u0006I!\u000e\u0005\tq\u0001\u0011\t\u0011)A\u0005G!A\u0011\b\u0001B\u0001B\u0003%!\b\u0003\u0005>\u0001\t\u0005\t\u0015!\u00036\u0011%q\u0004A!A!\u0002\u0013y$\tC\u0005E\u0001\t\u0005\t\u0015!\u0003@\u000b\"Aq\t\u0001BC\u0002\u0013E\u0001\n\u0003\u0005K\u0001\t\u0005\t\u0015!\u0003J\u0011\u0015Y\u0005\u0001\"\u0001M\u0011\u0015!\u0006\u0001\"\u0001V\u0011\u00151\u0006\u0001\"\u0001X\u000f\u0019\u0001\u0017\u0003#\u0001\u0016C\u001a1\u0001#\u0005E\u0001+\tDQaS\u0007\u0005\u0002\rDQ\u0001Z\u0007\u0005\u0002\u0015\u0014Q\u0003T8oO\nKg.\u0019:z)J,Wm\u0015;faB,'O\u0003\u0002\u0013'\u0005!\u0011.\u001c9m\u0015\t!R#A\u0004d_:4XM\u001d;\u000b\u0005Y9\u0012AC2pY2,7\r^5p]*\t\u0001$A\u0003tG\u0006d\u0017-\u0006\u0002\u001bKM\u0019\u0001aG\u0018\u0011\rqirdI\u00184\u001b\u0005\t\u0012B\u0001\u0010\u0012\u0005U\u0011\u0015N\\1ssR\u0013X-Z*uKB\u0004XM\u001d\"bg\u0016\u0004\"\u0001I\u0011\u000e\u0003]I!AI\f\u0003\t1{gn\u001a\t\u0003I\u0015b\u0001\u0001B\u0003'\u0001\t\u0007\u0001FA\u0001U\u0007\u0001\t\"!\u000b\u0017\u0011\u0005\u0001R\u0013BA\u0016\u0018\u0005\u0011qU\u000f\u001c7\u0011\u0005\u0001j\u0013B\u0001\u0018\u0018\u0005\u0019\te.\u001f*fMB\u0011\u0001'M\u0007\u0002+%\u0011!'\u0006\u0002\f\u0019>twm\u0015;faB,'\u000fE\u0002\u001d\u0001\r\n!bX7bq2+gn\u001a;i!\t\u0001c'\u0003\u00028/\t\u0019\u0011J\u001c;\u0002\u0015}k\u0017pQ;se\u0016tG/\u0001\u0004`gR\f7m\u001b\t\u0004Amb\u0013B\u0001\u001f\u0018\u0005\u0015\t%O]1z\u0003\u0019y\u0016N\u001c3fq\u0006)q\f\\3giB!\u0001\u0005Q\u0012$\u0013\t\tuCA\u0005Gk:\u001cG/[8oc%\u00111)H\u0001\u0005Y\u00164G/\u0001\u0004`e&<\u0007\u000e^\u0005\u0003\rv\tQA]5hQR\fq!\u001a=ue\u0006\u001cG/F\u0001J!\u0011\u0001\u0003iI\u0010\u0002\u0011\u0015DHO]1di\u0002\na\u0001P5oSRtD\u0003C\u001aN\u001d>\u0003\u0016KU*\t\u000bQJ\u0001\u0019A\u001b\t\u000baJ\u0001\u0019A\u0012\t\u000beJ\u0001\u0019\u0001\u001e\t\u000buJ\u0001\u0019A\u001b\t\u000byJ\u0001\u0019A \t\u000b\u0011K\u0001\u0019A \t\u000b\u001dK\u0001\u0019A%\u0002\u00119,\u0007\u0010^*uKB$\u0012aH\u0001\ng\u0016l\u0017n\u00197p]\u0016$Ra\r-[9zCQ!W\u0006A\u0002U\nA!\\1y\u0019\")1l\u0003a\u0001G\u0005\u0019Q._\"\t\u000bu[\u0001\u0019\u0001\u001e\u0002\u0007M$8\u000eC\u0003`\u0017\u0001\u0007Q'\u0001\u0002jq\u0006)Bj\u001c8h\u0005&t\u0017M]=Ue\u0016,7\u000b^3qa\u0016\u0014\bC\u0001\u000f\u000e'\tiA\u0006F\u0001b\u0003\u00111'o\\7\u0016\u0005\u0019LGCB4kY:\u0004\u0018\u000fE\u0002\u001d\u0001!\u0004\"\u0001J5\u0005\u000b\u0019z!\u0019\u0001\u0015\t\u000b-|\u0001\u0019A\u001b\u0002\u00135\f\u0007\u0010T3oORD\u0007\"B7\u0010\u0001\u0004A\u0017\u0001\u0002:p_RDQaQ\bA\u0002=\u0004B\u0001\t!iQ\")ai\u0004a\u0001_\")qi\u0004a\u0001eB!\u0001\u0005\u00115 \u0001"
)
public final class LongBinaryTreeStepper extends BinaryTreeStepperBase implements LongStepper {
   private final Function1 extract;

   public static LongBinaryTreeStepper from(final int maxLength, final Object root, final Function1 left, final Function1 right, final Function1 extract) {
      LongBinaryTreeStepper$ var10000 = LongBinaryTreeStepper$.MODULE$;
      LongBinaryTreeStepper from_ans = new LongBinaryTreeStepper(0, (Object)null, BinaryTreeStepper$.MODULE$.emptyStack(), -1, left, right, extract);
      from_ans.initialize(root, maxLength);
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

   public Function1 extract() {
      return this.extract;
   }

   public long nextStep() {
      return this.nextStep$mcJ$sp();
   }

   public LongBinaryTreeStepper semiclone(final int maxL, final Object myC, final Object[] stk, final int ix) {
      return new LongBinaryTreeStepper(maxL, myC, stk, ix, this.left(), this.right(), this.extract());
   }

   public long nextStep$mcJ$sp() {
      if (this.hasStep()) {
         long ans = BoxesRunTime.unboxToLong(this.extract().apply(this.myCurrent()));
         this.myCurrent_$eq((Object)null);
         this.maxLength_$eq(this.maxLength() - 1);
         return ans;
      } else {
         Stepper$ var10000 = Stepper$.MODULE$;
         throw new NoSuchElementException("Empty Stepper");
      }
   }

   public LongBinaryTreeStepper(final int _maxLength, final Object _myCurrent, final Object[] _stack, final int _index, final Function1 _left, final Function1 _right, final Function1 extract) {
      super(_maxLength, _myCurrent, _stack, _index, _left, _right);
      this.extract = extract;
   }
}
