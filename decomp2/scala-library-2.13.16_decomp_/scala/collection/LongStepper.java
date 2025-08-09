package scala.collection;

import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.LongConsumer;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Maa\u0002\n\u0014!\u0003\r\t\u0001\u0007\u0005\u0006I\u0001!\t!\n\u0005\u0006S\u00011\tA\u000b\u0005\u0006Y\u0001!\t!\f\u0005\u0006\u0003\u0002!\tAQ\u0004\u0006\u0017NA\t\u0001\u0014\u0004\u0006%MA\t!\u0014\u0005\u0006\u001d\u001a!\ta\u0014\u0004\u0005!\u001a\u0001\u0011\u000b\u0003\u0005Y\u0011\t\u0005\t\u0015!\u0003,\u0011\u0015q\u0005\u0002\"\u0001Z\u0011\u0015i\u0006\u0002\"\u0001_\u0011\u0015i\u0006\u0002\"\u0011k\u0011\u0015I\u0003\u0002\"\u0011x\u0011\u0015A\b\u0002\"\u0001z\u0011\u0015Q\b\u0002\"\u0001|\u0011\u0019y\b\u0002\"\u0011\u0002\u0002!1q\u0010\u0003C!\u0003\u000b\u00111\u0002T8oON#X\r\u001d9fe*\u0011A#F\u0001\u000bG>dG.Z2uS>t'\"\u0001\f\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M\u0019\u0001!G\u000f\u0011\u0005iYR\"A\u000b\n\u0005q)\"AB!osJ+g\rE\u0002\u001f?\u0005j\u0011aE\u0005\u0003AM\u0011qa\u0015;faB,'\u000f\u0005\u0002\u001bE%\u00111%\u0006\u0002\u0005\u0019>tw-\u0001\u0004%S:LG\u000f\n\u000b\u0002MA\u0011!dJ\u0005\u0003QU\u0011A!\u00168ji\u0006AAO]=Ta2LG\u000fF\u0001,!\tq\u0002!A\u0006ta2LG/\u001a:bi>\u0014XC\u0001\u0018<+\u0005y\u0003C\u0001\u00199\u001d\t\td'D\u00013\u0015\t\u0019D'\u0001\u0003vi&d'\"A\u001b\u0002\t)\fg/Y\u0005\u0003oI\n1b\u00159mSR,'/\u0019;pe&\u0011\u0011H\u000f\u0002\u0007\u001f\u001aduN\\4\u000b\u0005]\u0012D!\u0002\u001f\u0004\u0005\u0004i$!\u0001\"\u0012\u0005\u0005r\u0004C\u0001\u000e@\u0013\t\u0001UCA\u0002B]f\fAB[1wC&#XM]1u_J,\"a\u0011&\u0016\u0003\u0011\u0003\"!\u0012%\u000f\u0005E2\u0015BA$3\u0003E\u0001&/[7ji&4X-\u0013;fe\u0006$xN]\u0005\u0003s%S!a\u0012\u001a\u0005\u000bq\"!\u0019A\u001f\u0002\u00171{gnZ*uKB\u0004XM\u001d\t\u0003=\u0019\u0019\"AB\r\u0002\rqJg.\u001b;?)\u0005a%A\u0006'p]\u001e\u001cF/\u001a9qKJ\u001c\u0006\u000f\\5uKJ\fGo\u001c:\u0014\u0007!\u0011v\u0006\u0005\u0002T-6\tAK\u0003\u0002Vi\u0005!A.\u00198h\u0013\t9FK\u0001\u0004PE*,7\r^\u0001\u0002gR\u0011!\f\u0018\t\u00037\"i\u0011A\u0002\u0005\u00061*\u0001\raK\u0001\u000biJL\u0018\t\u001a<b]\u000e,GCA0c!\tQ\u0002-\u0003\u0002b+\t9!i\\8mK\u0006t\u0007\"B2\f\u0001\u0004!\u0017!A2\u0011\u0005\u0015DW\"\u00014\u000b\u0005\u001d\u0014\u0014\u0001\u00034v]\u000e$\u0018n\u001c8\n\u0005%4'\u0001\u0004'p]\u001e\u001cuN\\:v[\u0016\u0014HCA0l\u0011\u0015\u0019G\u00021\u0001ma\ti'\u000fE\u0002f]BL!a\u001c4\u0003\u0011\r{gn];nKJ\u0004\"!\u001d:\r\u0001\u0011I1o[A\u0001\u0002\u0003\u0015\t\u0001\u001e\u0002\u0004?\u0012J\u0014CA;?!\t\u0019f/\u0003\u0002$)R\tq&\u0001\u0007fgRLW.\u0019;f'&TX\rF\u0001\"\u0003=\u0019\u0007.\u0019:bGR,'/[:uS\u000e\u001cH#\u0001?\u0011\u0005ii\u0018B\u0001@\u0016\u0005\rIe\u000e^\u0001\u0011M>\u0014X)Y2i%\u0016l\u0017-\u001b8j]\u001e$2AJA\u0002\u0011\u0015\u0019\u0007\u00031\u0001e)\r1\u0013q\u0001\u0005\u0007GF\u0001\r!!\u00031\t\u0005-\u0011q\u0002\t\u0005K:\fi\u0001E\u0002r\u0003\u001f!1\"!\u0005\u0002\b\u0005\u0005\t\u0011!B\u0001i\n!q\fJ\u00191\u0001"
)
public interface LongStepper extends Stepper$mcJ$sp {
   LongStepper trySplit();

   // $FF: synthetic method
   static Spliterator.OfLong spliterator$(final LongStepper $this) {
      return $this.spliterator();
   }

   default Spliterator.OfLong spliterator() {
      return this.spliterator$mcJ$sp();
   }

   // $FF: synthetic method
   static PrimitiveIterator.OfLong javaIterator$(final LongStepper $this) {
      return $this.javaIterator();
   }

   default PrimitiveIterator.OfLong javaIterator() {
      return this.javaIterator$mcJ$sp();
   }

   // $FF: synthetic method
   static Spliterator.OfLong spliterator$mcJ$sp$(final LongStepper $this) {
      return $this.spliterator$mcJ$sp();
   }

   default Spliterator.OfLong spliterator$mcJ$sp() {
      return new LongStepperSpliterator(this);
   }

   // $FF: synthetic method
   static PrimitiveIterator.OfLong javaIterator$mcJ$sp$(final LongStepper $this) {
      return $this.javaIterator$mcJ$sp();
   }

   default PrimitiveIterator.OfLong javaIterator$mcJ$sp() {
      return new PrimitiveIterator.OfLong() {
         // $FF: synthetic field
         private final LongStepper $outer;

         public void forEachRemaining(final LongConsumer x$1) {
            super.forEachRemaining(x$1);
         }

         public Long next() {
            return super.next();
         }

         public void forEachRemaining(final Consumer x$1) {
            super.forEachRemaining(x$1);
         }

         public boolean hasNext() {
            return this.$outer.hasStep();
         }

         public long nextLong() {
            return this.$outer.nextStep$mcJ$sp();
         }

         public {
            if (LongStepper.this == null) {
               throw null;
            } else {
               this.$outer = LongStepper.this;
            }
         }
      };
   }

   static void $init$(final LongStepper $this) {
   }

   public static class LongStepperSpliterator implements Spliterator.OfLong {
      private final LongStepper s;

      public boolean tryAdvance(final LongConsumer c) {
         if (this.s.hasStep()) {
            c.accept(this.s.nextStep$mcJ$sp());
            return true;
         } else {
            return false;
         }
      }

      public boolean tryAdvance(final Consumer c) {
         if (c instanceof LongConsumer) {
            LongConsumer var2 = (LongConsumer)c;
            return this.tryAdvance(var2);
         } else if (this.s.hasStep()) {
            c.accept(this.s.nextStep$mcJ$sp());
            return true;
         } else {
            return false;
         }
      }

      public Spliterator.OfLong trySplit() {
         LongStepper sp = this.s.trySplit();
         return sp == null ? null : sp.spliterator$mcJ$sp();
      }

      public long estimateSize() {
         return this.s.estimateSize();
      }

      public int characteristics() {
         return this.s.characteristics();
      }

      public void forEachRemaining(final LongConsumer c) {
         while(this.s.hasStep()) {
            c.accept(this.s.nextStep$mcJ$sp());
         }

      }

      public void forEachRemaining(final Consumer c) {
         if (c instanceof LongConsumer) {
            LongConsumer var2 = (LongConsumer)c;
            this.forEachRemaining(var2);
         } else {
            while(this.s.hasStep()) {
               c.accept(this.s.nextStep$mcJ$sp());
            }

         }
      }

      public LongStepperSpliterator(final LongStepper s) {
         this.s = s;
      }
   }
}
