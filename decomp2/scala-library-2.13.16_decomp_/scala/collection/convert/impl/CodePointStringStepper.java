package scala.collection.convert.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import scala.collection.IntStepper;
import scala.collection.Stepper;
import scala.collection.Stepper$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00014QAD\b\u0003']A\u0001b\f\u0001\u0003\u0002\u0003\u0006I\u0001\r\u0005\tq\u0001\u0011\t\u0019!C\u0005s!AQ\b\u0001BA\u0002\u0013%a\b\u0003\u0005E\u0001\t\u0005\t\u0015)\u0003;\u0011!)\u0005A!a\u0001\n\u0013I\u0004\u0002\u0003$\u0001\u0005\u0003\u0007I\u0011B$\t\u0011%\u0003!\u0011!Q!\niBQA\u0013\u0001\u0005\u0002-CQ!\u0015\u0001\u0005\u0002eBQA\u0015\u0001\u0005\u0002MCQa\u0016\u0001\u0005\u0002aCQ\u0001\u0018\u0001\u0005\u0002uCQA\u0018\u0001\u0005\u0002}\u0013acQ8eKB{\u0017N\u001c;TiJLgnZ*uKB\u0004XM\u001d\u0006\u0003!E\tA![7qY*\u0011!cE\u0001\bG>tg/\u001a:u\u0015\t!R#\u0001\u0006d_2dWm\u0019;j_:T\u0011AF\u0001\u0006g\u000e\fG.Y\n\u0005\u0001aa\u0002\u0005\u0005\u0002\u001a55\tQ#\u0003\u0002\u001c+\t1\u0011I\\=SK\u001a\u0004\"!\b\u0010\u000e\u0003MI!aH\n\u0003\u0015%sGo\u0015;faB,'\u000f\u0005\u0002\"Y9\u0011!E\u000b\b\u0003G%r!\u0001\n\u0015\u000e\u0003\u0015R!AJ\u0014\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011AF\u0005\u0003)UI!aK\n\u0002\u000fM#X\r\u001d9fe&\u0011QF\f\u0002\u000f\u000b\u001a4\u0017nY5f]R\u001c\u0006\u000f\\5u\u0015\tY3#\u0001\u0006v]\u0012,'\u000f\\=j]\u001e\u0004\"!M\u001b\u000f\u0005I\u001a\u0004C\u0001\u0013\u0016\u0013\t!T#\u0001\u0004Qe\u0016$WMZ\u0005\u0003m]\u0012aa\u0015;sS:<'B\u0001\u001b\u0016\u0003\tI\u0007'F\u0001;!\tI2(\u0003\u0002=+\t\u0019\u0011J\u001c;\u0002\r%\u0004t\fJ3r)\ty$\t\u0005\u0002\u001a\u0001&\u0011\u0011)\u0006\u0002\u0005+:LG\u000fC\u0004D\u0007\u0005\u0005\t\u0019\u0001\u001e\u0002\u0007a$\u0013'A\u0002ja\u0001\n!!\u001b(\u0002\r%tu\fJ3r)\ty\u0004\nC\u0004D\r\u0005\u0005\t\u0019\u0001\u001e\u0002\u0007%t\u0005%\u0001\u0004=S:LGO\u0010\u000b\u0005\u0019:{\u0005\u000b\u0005\u0002N\u00015\tq\u0002C\u00030\u0011\u0001\u0007\u0001\u0007C\u00039\u0011\u0001\u0007!\bC\u0003F\u0011\u0001\u0007!(A\bdQ\u0006\u0014\u0018m\u0019;fe&\u001cH/[2t\u00031)7\u000f^5nCR,7+\u001b>f+\u0005!\u0006CA\rV\u0013\t1VC\u0001\u0003M_:<\u0017a\u00025bgN#X\r]\u000b\u00023B\u0011\u0011DW\u0005\u00037V\u0011qAQ8pY\u0016\fg.\u0001\u0005oKb$8\u000b^3q)\u0005Q\u0014\u0001\u0003;ssN\u0003H.\u001b;\u0015\u00031\u0003"
)
public final class CodePointStringStepper implements IntStepper, Stepper.EfficientSplit {
   public final String scala$collection$convert$impl$CodePointStringStepper$$underlying;
   private int scala$collection$convert$impl$CodePointStringStepper$$i0;
   private int iN;

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

   public int scala$collection$convert$impl$CodePointStringStepper$$i0() {
      return this.scala$collection$convert$impl$CodePointStringStepper$$i0;
   }

   public void scala$collection$convert$impl$CodePointStringStepper$$i0_$eq(final int x$1) {
      this.scala$collection$convert$impl$CodePointStringStepper$$i0 = x$1;
   }

   private int iN() {
      return this.iN;
   }

   private void iN_$eq(final int x$1) {
      this.iN = x$1;
   }

   public int characteristics() {
      return 1296;
   }

   public long estimateSize() {
      return (long)(this.iN() - this.scala$collection$convert$impl$CodePointStringStepper$$i0());
   }

   public boolean hasStep() {
      return this.scala$collection$convert$impl$CodePointStringStepper$$i0() < this.iN();
   }

   public int nextStep() {
      return this.nextStep$mcI$sp();
   }

   public CodePointStringStepper trySplit() {
      if (this.iN() - 3 > this.scala$collection$convert$impl$CodePointStringStepper$$i0()) {
         int half = this.scala$collection$convert$impl$CodePointStringStepper$$i0() + this.iN() >>> 1;
         if (Character.isLowSurrogate(this.scala$collection$convert$impl$CodePointStringStepper$$underlying.charAt(half))) {
            --half;
         }

         CodePointStringStepper ans = new CodePointStringStepper(this.scala$collection$convert$impl$CodePointStringStepper$$underlying, this.scala$collection$convert$impl$CodePointStringStepper$$i0(), half);
         this.scala$collection$convert$impl$CodePointStringStepper$$i0_$eq(half);
         return ans;
      } else {
         return null;
      }
   }

   public int nextStep$mcI$sp() {
      if (this.hasStep()) {
         int cp = this.scala$collection$convert$impl$CodePointStringStepper$$underlying.codePointAt(this.scala$collection$convert$impl$CodePointStringStepper$$i0());
         this.scala$collection$convert$impl$CodePointStringStepper$$i0_$eq(this.scala$collection$convert$impl$CodePointStringStepper$$i0() + Character.charCount(cp));
         return cp;
      } else {
         Stepper$ var10000 = Stepper$.MODULE$;
         throw new NoSuchElementException("Empty Stepper");
      }
   }

   public CodePointStringStepper(final String underlying, final int i0, final int iN) {
      this.scala$collection$convert$impl$CodePointStringStepper$$underlying = underlying;
      this.scala$collection$convert$impl$CodePointStringStepper$$i0 = i0;
      this.iN = iN;
      super();
   }
}
