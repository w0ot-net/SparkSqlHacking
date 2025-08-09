package scala.collection.convert.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import scala.Function1;
import scala.collection.IntStepper;
import scala.collection.Stepper;
import scala.collection.Stepper$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005A4Q\u0001E\t\u0003+eA\u0001\u0002\u000e\u0001\u0003\u0002\u0003\u0006Ia\b\u0005\tk\u0001\u0011\t\u0011)A\u0005G!Aa\u0007\u0001B\u0001B\u0003%q\u0007\u0003\u0005;\u0001\t\u0005\t\u0015!\u0003 \u0011%Y\u0004A!A!\u0002\u0013at\bC\u0005B\u0001\t\u0005\t\u0015!\u0003=\u0005\"AA\t\u0001BC\u0002\u0013EQ\t\u0003\u0005H\u0001\t\u0005\t\u0015!\u0003G\u0011\u0015A\u0005\u0001\"\u0001J\u0011\u0015\t\u0006\u0001\"\u0001S\u0011\u0015\u0019\u0006\u0001\"\u0001U\u000f\u0019i\u0016\u0003#\u0001\u0016=\u001a1\u0001#\u0005E\u0001+}CQ\u0001S\u0007\u0005\u0002\u0001DQ!Y\u0007\u0005\u0002\t\u0014A#\u00138u\u0005&t\u0017M]=Ue\u0016,7\u000b^3qa\u0016\u0014(B\u0001\n\u0014\u0003\u0011IW\u000e\u001d7\u000b\u0005Q)\u0012aB2p]Z,'\u000f\u001e\u0006\u0003-]\t!bY8mY\u0016\u001cG/[8o\u0015\u0005A\u0012!B:dC2\fWC\u0001\u000e&'\r\u00011d\f\t\u00079uy2eL\u001a\u000e\u0003EI!AH\t\u0003+\tKg.\u0019:z)J,Wm\u0015;faB,'OQ1tKB\u0011\u0001%I\u0007\u0002/%\u0011!e\u0006\u0002\u0004\u0013:$\bC\u0001\u0013&\u0019\u0001!QA\n\u0001C\u0002!\u0012\u0011\u0001V\u0002\u0001#\tIC\u0006\u0005\u0002!U%\u00111f\u0006\u0002\u0005\u001dVdG\u000e\u0005\u0002![%\u0011af\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0005A\nT\"A\u000b\n\u0005I*\"AC%oiN#X\r\u001d9feB\u0019A\u0004A\u0012\u0002\u0015}k\u0017\r\u001f'f]\u001e$\b.\u0001\u0006`[f\u001cUO\u001d:f]R\faaX:uC\u000e\\\u0007c\u0001\u00119Y%\u0011\u0011h\u0006\u0002\u0006\u0003J\u0014\u0018-_\u0001\u0007?&tG-\u001a=\u0002\u000b}cWM\u001a;\u0011\t\u0001j4eI\u0005\u0003}]\u0011\u0011BR;oGRLwN\\\u0019\n\u0005\u0001k\u0012\u0001\u00027fMR\faa\u0018:jO\"$\u0018BA\"\u001e\u0003\u0015\u0011\u0018n\u001a5u\u0003\u001d)\u0007\u0010\u001e:bGR,\u0012A\u0012\t\u0005Au\u001as$\u0001\u0005fqR\u0014\u0018m\u0019;!\u0003\u0019a\u0014N\\5u}QA1GS&M\u001b:{\u0005\u000bC\u00035\u0013\u0001\u0007q\u0004C\u00036\u0013\u0001\u00071\u0005C\u00037\u0013\u0001\u0007q\u0007C\u0003;\u0013\u0001\u0007q\u0004C\u0003<\u0013\u0001\u0007A\bC\u0003B\u0013\u0001\u0007A\bC\u0003E\u0013\u0001\u0007a)\u0001\u0005oKb$8\u000b^3q)\u0005y\u0012!C:f[&\u001cGn\u001c8f)\u0015\u0019TkV-\\\u0011\u001516\u00021\u0001 \u0003\u0011i\u0017\r\u001f'\t\u000ba[\u0001\u0019A\u0012\u0002\u00075L8\tC\u0003[\u0017\u0001\u0007q'A\u0002ti.DQ\u0001X\u0006A\u0002}\t!!\u001b=\u0002)%sGOQ5oCJLHK]3f'R,\u0007\u000f]3s!\taRb\u0005\u0002\u000eYQ\ta,\u0001\u0003ge>lWCA2g)\u0019!w-[6n]B\u0019A\u0004A3\u0011\u0005\u00112G!\u0002\u0014\u0010\u0005\u0004A\u0003\"\u00025\u0010\u0001\u0004y\u0012!C7bq2+gn\u001a;i\u0011\u0015Qw\u00021\u0001f\u0003\u0011\u0011xn\u001c;\t\u000b\u0001{\u0001\u0019\u00017\u0011\t\u0001jT-\u001a\u0005\u0006\u0007>\u0001\r\u0001\u001c\u0005\u0006\t>\u0001\ra\u001c\t\u0005Au*w\u0004"
)
public final class IntBinaryTreeStepper extends BinaryTreeStepperBase implements IntStepper {
   private final Function1 extract;

   public static IntBinaryTreeStepper from(final int maxLength, final Object root, final Function1 left, final Function1 right, final Function1 extract) {
      IntBinaryTreeStepper$ var10000 = IntBinaryTreeStepper$.MODULE$;
      IntBinaryTreeStepper from_ans = new IntBinaryTreeStepper(0, (Object)null, BinaryTreeStepper$.MODULE$.emptyStack(), -1, left, right, extract);
      from_ans.initialize(root, maxLength);
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

   public Function1 extract() {
      return this.extract;
   }

   public int nextStep() {
      return this.nextStep$mcI$sp();
   }

   public IntBinaryTreeStepper semiclone(final int maxL, final Object myC, final Object[] stk, final int ix) {
      return new IntBinaryTreeStepper(maxL, myC, stk, ix, this.left(), this.right(), this.extract());
   }

   public int nextStep$mcI$sp() {
      if (this.hasStep()) {
         int ans = BoxesRunTime.unboxToInt(this.extract().apply(this.myCurrent()));
         this.myCurrent_$eq((Object)null);
         this.maxLength_$eq(this.maxLength() - 1);
         return ans;
      } else {
         Stepper$ var10000 = Stepper$.MODULE$;
         throw new NoSuchElementException("Empty Stepper");
      }
   }

   public IntBinaryTreeStepper(final int _maxLength, final Object _myCurrent, final Object[] _stack, final int _index, final Function1 _left, final Function1 _right, final Function1 extract) {
      super(_maxLength, _myCurrent, _stack, _index, _left, _right);
      this.extract = extract;
   }
}
