package scala.collection.convert.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import scala.Function1;
import scala.collection.AnyStepper;
import scala.collection.Stepper;
import scala.collection.Stepper$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]3QAC\u0006\u0003\u001fMA\u0001\"\u000e\u0001\u0003\u0002\u0003\u0006IA\u000e\u0005\ns\u0001\u0011\t\u0011)A\u0005uuB\u0001b\u0010\u0001\u0003\u0002\u0003\u0006I\u0001\u0011\u0005\t\u0007\u0002\u0011\t\u0011)A\u0005\t\"AQ\t\u0001B\u0001B\u0003%a\u0007C\u0005G\u0001\t\u0005\t\u0015!\u00037\u000f\")\u0011\n\u0001C\u0001\u0015\")\u0011\u000b\u0001C\u0001%\")1\u000b\u0001C\u0001)\ny\u0011I\\=UC\ndWm\u0015;faB,'O\u0003\u0002\r\u001b\u0005!\u0011.\u001c9m\u0015\tqq\"A\u0004d_:4XM\u001d;\u000b\u0005A\t\u0012AC2pY2,7\r^5p]*\t!#A\u0003tG\u0006d\u0017-F\u0002\u00157\u001d\u001a2\u0001A\u000b1!\u00191r#\u0007\u00141i5\t1\"\u0003\u0002\u0019\u0017\t\u0001B+\u00192mKN#X\r\u001d9fe\n\u000b7/\u001a\t\u00035ma\u0001\u0001B\u0003\u001d\u0001\t\u0007aDA\u0001B\u0007\u0001\t\"aH\u0012\u0011\u0005\u0001\nS\"A\t\n\u0005\t\n\"a\u0002(pi\"Lgn\u001a\t\u0003A\u0011J!!J\t\u0003\u0007\u0005s\u0017\u0010\u0005\u0002\u001bO\u0011)\u0001\u0006\u0001b\u0001S\t\t\u0011*\u0005\u0002+[A\u0011\u0001eK\u0005\u0003YE\u0011AAT;mYB\u0011\u0001EL\u0005\u0003_E\u0011a!\u00118z%\u00164\u0007cA\u0019335\tq\"\u0003\u00024\u001f\tQ\u0011I\\=Ti\u0016\u0004\b/\u001a:\u0011\tY\u0001\u0011DJ\u0001\u000b?6\f\u0007\u0010T3oORD\u0007C\u0001\u00118\u0013\tA\u0014CA\u0002J]R\faa\u0018;bE2,\u0007c\u0001\u0011<M%\u0011A(\u0005\u0002\u0006\u0003J\u0014\u0018-_\u0005\u0003}]\tQ\u0001^1cY\u0016\fq!\u001b;fe\u0006$X\r\u0005\u0003!\u0003\u001a2\u0013B\u0001\"\u0012\u0005%1UO\\2uS>t\u0017'A\u0004fqR\u0014\u0018m\u0019;\u0011\t\u0001\ne%G\u0001\u0004?&\u0004\u0014aA0j\u001d&\u0011\u0001jF\u0001\u0003S:\u000ba\u0001P5oSRtDc\u0002\u001bL\u00196su\n\u0015\u0005\u0006k\u001d\u0001\rA\u000e\u0005\u0006s\u001d\u0001\rA\u000f\u0005\u0006\u007f\u001d\u0001\r\u0001\u0011\u0005\u0006\u0007\u001e\u0001\r\u0001\u0012\u0005\u0006\u000b\u001e\u0001\rA\u000e\u0005\u0006\r\u001e\u0001\rAN\u0001\t]\u0016DHo\u0015;faR\t\u0011$A\u0005tK6L7\r\\8oKR\u0011A'\u0016\u0005\u0006-&\u0001\rAN\u0001\u0005Q\u0006dg\r"
)
public final class AnyTableStepper extends TableStepperBase implements AnyStepper {
   private final Function1 iterate;
   private final Function1 extract;

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
         Object ans = this.extract.apply(this.myCurrent());
         this.myCurrent_$eq(this.iterate.apply(this.myCurrent()));
         return ans;
      } else {
         Stepper$ var10000 = Stepper$.MODULE$;
         throw new NoSuchElementException("Empty Stepper");
      }
   }

   public AnyTableStepper semiclone(final int half) {
      return new AnyTableStepper(this.maxLength(), this.table(), this.iterate, this.extract, this.i0(), half);
   }

   public AnyTableStepper(final int _maxLength, final Object[] _table, final Function1 iterate, final Function1 extract, final int _i0, final int _iN) {
      super(_maxLength, _table, _i0, _iN);
      this.iterate = iterate;
      this.extract = extract;
   }
}
