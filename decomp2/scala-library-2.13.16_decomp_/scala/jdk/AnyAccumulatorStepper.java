package scala.jdk;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;
import scala.collection.AnyStepper;
import scala.collection.Stepper;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=b!\u0002\u000e\u001c\u0001my\u0002\u0002C#\u0001\u0005\u0003\u0005\u000b\u0011\u0002$\t\u000b)\u0003A\u0011A&\t\u000f9\u0003\u0001\u0019!C\u0005\u001f\"91\u000b\u0001a\u0001\n\u0013!\u0006B\u0002.\u0001A\u0003&\u0001\u000bC\u0004\\\u0001\u0001\u0007I\u0011B(\t\u000fq\u0003\u0001\u0019!C\u0005;\"1q\f\u0001Q!\nACq\u0001\u0019\u0001A\u0002\u0013%\u0011\rC\u0004f\u0001\u0001\u0007I\u0011\u00024\t\r!\u0004\u0001\u0015)\u0003c\u0011\u001dI\u0007\u00011A\u0005\n)DqA\u001c\u0001A\u0002\u0013%q\u000e\u0003\u0004r\u0001\u0001\u0006Ka\u001b\u0005\be\u0002\u0001\r\u0011\"\u0003k\u0011\u001d\u0019\b\u00011A\u0005\nQDaA\u001e\u0001!B\u0013Y\u0007\"B<\u0001\t\u0013A\b\"B>\u0001\t\u0013a\b\"B?\u0001\t\u0003y\u0005\"\u0002@\u0001\t\u0003Q\u0007BB@\u0001\t\u0003\t\t\u0001C\u0004\u0002\n\u0001!\t!a\u0003\t\u000f\u00055\u0001\u0001\"\u0001\u0002\u0010!9\u0011\u0011\u0003\u0001\u0005B\u0005M!!F!os\u0006\u001b7-^7vY\u0006$xN]*uKB\u0004XM\u001d\u0006\u00039u\t1A\u001b3l\u0015\u0005q\u0012!B:dC2\fWC\u0001\u0011.'\u0011\u0001\u0011%J\u001c\u0011\u0005\t\u001aS\"A\u000f\n\u0005\u0011j\"AB!osJ+g\rE\u0002'S-j\u0011a\n\u0006\u0003Qu\t!bY8mY\u0016\u001cG/[8o\u0013\tQsE\u0001\u0006B]f\u001cF/\u001a9qKJ\u0004\"\u0001L\u0017\r\u0001\u0011)a\u0006\u0001b\u0001a\t\t\u0011i\u0001\u0001\u0012\u0005E\"\u0004C\u0001\u00123\u0013\t\u0019TDA\u0004O_RD\u0017N\\4\u0011\u0005\t*\u0014B\u0001\u001c\u001e\u0005\r\te.\u001f\t\u0003q\ts!!\u000f!\u000f\u0005izdBA\u001e?\u001b\u0005a$BA\u001f0\u0003\u0019a$o\\8u}%\ta$\u0003\u0002);%\u0011\u0011iJ\u0001\b'R,\u0007\u000f]3s\u0013\t\u0019EI\u0001\bFM\u001aL7-[3oiN\u0003H.\u001b;\u000b\u0005\u0005;\u0013aA1dGB\u0019q\tS\u0016\u000e\u0003mI!!S\u000e\u0003\u001d\u0005s\u00170Q2dk6,H.\u0019;pe\u00061A(\u001b8jiz\"\"\u0001T'\u0011\u0007\u001d\u00031\u0006C\u0003F\u0005\u0001\u0007a)A\u0001i+\u0005\u0001\u0006C\u0001\u0012R\u0013\t\u0011VDA\u0002J]R\fQ\u0001[0%KF$\"!\u0016-\u0011\u0005\t2\u0016BA,\u001e\u0005\u0011)f.\u001b;\t\u000fe#\u0011\u0011!a\u0001!\u0006\u0019\u0001\u0010J\u0019\u0002\u0005!\u0004\u0013!A5\u0002\u000b%|F%Z9\u0015\u0005Us\u0006bB-\b\u0003\u0003\u0005\r\u0001U\u0001\u0003S\u0002\n\u0011!Y\u000b\u0002EB\u0019!eY\u0011\n\u0005\u0011l\"!B!se\u0006L\u0018!B1`I\u0015\fHCA+h\u0011\u001dI&\"!AA\u0002\t\f!!\u0019\u0011\u0002\u00039,\u0012a\u001b\t\u0003E1L!!\\\u000f\u0003\t1{gnZ\u0001\u0006]~#S-\u001d\u000b\u0003+BDq!W\u0007\u0002\u0002\u0003\u00071.\u0001\u0002oA\u0005\ta*A\u0003O?\u0012*\u0017\u000f\u0006\u0002Vk\"9\u0011\fEA\u0001\u0002\u0004Y\u0017A\u0001(!\u00035!W\u000f\u001d7jG\u0006$XmU3mMR\u0011A*\u001f\u0005\u0006uJ\u0001\ra[\u0001\u0006Y&l\u0017\u000e^\u0001\tY>\fG-T8sKR\tQ+A\bdQ\u0006\u0014\u0018m\u0019;fe&\u001cH/[2t\u00031)7\u000f^5nCR,7+\u001b>f\u0003\u001dA\u0017m]*uKB,\"!a\u0001\u0011\u0007\t\n)!C\u0002\u0002\bu\u0011qAQ8pY\u0016\fg.\u0001\u0005oKb$8\u000b^3q)\u0005Y\u0013\u0001\u0003;ssN\u0003H.\u001b;\u0015\u0003\u0015\n1b\u001d9mSR,'/\u0019;peV!\u0011QCA\u0015+\t\t9\u0002\u0005\u0004\u0002\u001a\u0005\r\u0012qE\u0007\u0003\u00037QA!!\b\u0002 \u0005!Q\u000f^5m\u0015\t\t\t#\u0001\u0003kCZ\f\u0017\u0002BA\u0013\u00037\u00111b\u00159mSR,'/\u0019;peB\u0019A&!\u000b\u0005\u000f\u0005-\u0012D1\u0001\u0002.\t\t!)\u0005\u0002,i\u0001"
)
public class AnyAccumulatorStepper implements AnyStepper, Stepper.EfficientSplit {
   private final AnyAccumulator acc;
   private int h;
   private int scala$jdk$AnyAccumulatorStepper$$i;
   private Object[] scala$jdk$AnyAccumulatorStepper$$a;
   private long scala$jdk$AnyAccumulatorStepper$$n;
   private long scala$jdk$AnyAccumulatorStepper$$N;

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

   private int h() {
      return this.h;
   }

   private void h_$eq(final int x$1) {
      this.h = x$1;
   }

   public int scala$jdk$AnyAccumulatorStepper$$i() {
      return this.scala$jdk$AnyAccumulatorStepper$$i;
   }

   public void scala$jdk$AnyAccumulatorStepper$$i_$eq(final int x$1) {
      this.scala$jdk$AnyAccumulatorStepper$$i = x$1;
   }

   public Object[] scala$jdk$AnyAccumulatorStepper$$a() {
      return this.scala$jdk$AnyAccumulatorStepper$$a;
   }

   private void a_$eq(final Object[] x$1) {
      this.scala$jdk$AnyAccumulatorStepper$$a = x$1;
   }

   public long scala$jdk$AnyAccumulatorStepper$$n() {
      return this.scala$jdk$AnyAccumulatorStepper$$n;
   }

   public void scala$jdk$AnyAccumulatorStepper$$n_$eq(final long x$1) {
      this.scala$jdk$AnyAccumulatorStepper$$n = x$1;
   }

   public long scala$jdk$AnyAccumulatorStepper$$N() {
      return this.scala$jdk$AnyAccumulatorStepper$$N;
   }

   public void scala$jdk$AnyAccumulatorStepper$$N_$eq(final long x$1) {
      this.scala$jdk$AnyAccumulatorStepper$$N = x$1;
   }

   private AnyAccumulatorStepper duplicateSelf(final long limit) {
      AnyAccumulatorStepper ans = new AnyAccumulatorStepper(this.acc);
      ans.h_$eq(this.h());
      ans.scala$jdk$AnyAccumulatorStepper$$i_$eq(this.scala$jdk$AnyAccumulatorStepper$$i());
      ans.a_$eq(this.scala$jdk$AnyAccumulatorStepper$$a());
      ans.scala$jdk$AnyAccumulatorStepper$$n_$eq(this.scala$jdk$AnyAccumulatorStepper$$n());
      ans.scala$jdk$AnyAccumulatorStepper$$N_$eq(limit);
      return ans;
   }

   public void scala$jdk$AnyAccumulatorStepper$$loadMore() {
      this.h_$eq(this.h() + 1);
      if (this.h() < this.acc.hIndex()) {
         this.a_$eq(this.acc.history()[this.h()]);
         AnyAccumulator var10001 = this.acc;
         int cumulative_i = this.h();
         if (var10001 == null) {
            throw null;
         }

         long var3 = var10001.cumul()[cumulative_i];
         AnyAccumulator var10002 = this.acc;
         int cumulative_i = this.h() - 1;
         if (var10002 == null) {
            throw null;
         }

         this.scala$jdk$AnyAccumulatorStepper$$n_$eq(var3 - var10002.cumul()[cumulative_i]);
      } else {
         this.a_$eq(this.acc.current());
         this.scala$jdk$AnyAccumulatorStepper$$n_$eq((long)this.acc.index());
      }

      this.scala$jdk$AnyAccumulatorStepper$$i_$eq(0);
   }

   public int characteristics() {
      return 16464;
   }

   public long estimateSize() {
      return this.scala$jdk$AnyAccumulatorStepper$$N();
   }

   public boolean hasStep() {
      return this.scala$jdk$AnyAccumulatorStepper$$N() > 0L;
   }

   public Object nextStep() {
      if (this.scala$jdk$AnyAccumulatorStepper$$N() <= 0L) {
         throw new NoSuchElementException("Next in empty Stepper");
      } else {
         if ((long)this.scala$jdk$AnyAccumulatorStepper$$i() >= this.scala$jdk$AnyAccumulatorStepper$$n()) {
            this.scala$jdk$AnyAccumulatorStepper$$loadMore();
         }

         Object ans = this.scala$jdk$AnyAccumulatorStepper$$a()[this.scala$jdk$AnyAccumulatorStepper$$i()];
         this.scala$jdk$AnyAccumulatorStepper$$i_$eq(this.scala$jdk$AnyAccumulatorStepper$$i() + 1);
         this.scala$jdk$AnyAccumulatorStepper$$N_$eq(this.scala$jdk$AnyAccumulatorStepper$$N() - 1L);
         return ans;
      }
   }

   public AnyStepper trySplit() {
      if (this.scala$jdk$AnyAccumulatorStepper$$N() <= 1L) {
         return null;
      } else {
         long half = this.scala$jdk$AnyAccumulatorStepper$$N() >> 1;
         long var10000;
         if (this.h() <= 0) {
            var10000 = 0L;
         } else {
            AnyAccumulator var11 = this.acc;
            int cumulative_i = this.h() - 1;
            if (var11 == null) {
               throw null;
            }

            var10000 = var11.cumul()[cumulative_i];
         }

         long R = var10000 + (long)this.scala$jdk$AnyAccumulatorStepper$$i() + half;
         AnyAccumulatorStepper ans = this.duplicateSelf(half);
         if (this.h() < this.acc.hIndex()) {
            long w = this.acc.seekSlot(R);
            this.h_$eq((int)(w >>> 32));
            if (this.h() < this.acc.hIndex()) {
               this.a_$eq(this.acc.history()[this.h()]);
               AnyAccumulator var10001 = this.acc;
               int cumulative_i = this.h();
               if (var10001 == null) {
                  throw null;
               }

               long var12 = var10001.cumul()[cumulative_i];
               long var13;
               if (this.h() > 0) {
                  AnyAccumulator var10002 = this.acc;
                  int cumulative_i = this.h() - 1;
                  if (var10002 == null) {
                     throw null;
                  }

                  var13 = var10002.cumul()[cumulative_i];
               } else {
                  var13 = 0L;
               }

               this.scala$jdk$AnyAccumulatorStepper$$n_$eq(var12 - var13);
            } else {
               this.a_$eq(this.acc.current());
               this.scala$jdk$AnyAccumulatorStepper$$n_$eq((long)this.acc.index());
            }

            this.scala$jdk$AnyAccumulatorStepper$$i_$eq((int)(w & 4294967295L));
         } else {
            this.scala$jdk$AnyAccumulatorStepper$$i_$eq(this.scala$jdk$AnyAccumulatorStepper$$i() + (int)half);
         }

         this.scala$jdk$AnyAccumulatorStepper$$N_$eq(this.scala$jdk$AnyAccumulatorStepper$$N() - half);
         return ans;
      }
   }

   public Spliterator spliterator() {
      return new AnyStepper.AnyStepperSpliterator() {
         // $FF: synthetic field
         private final AnyAccumulatorStepper $outer;

         public boolean tryAdvance(final Consumer c) {
            if (this.$outer.scala$jdk$AnyAccumulatorStepper$$N() <= 0L) {
               return false;
            } else {
               if ((long)this.$outer.scala$jdk$AnyAccumulatorStepper$$i() >= this.$outer.scala$jdk$AnyAccumulatorStepper$$n()) {
                  this.$outer.scala$jdk$AnyAccumulatorStepper$$loadMore();
               }

               c.accept(this.$outer.scala$jdk$AnyAccumulatorStepper$$a()[this.$outer.scala$jdk$AnyAccumulatorStepper$$i()]);
               this.$outer.scala$jdk$AnyAccumulatorStepper$$i_$eq(this.$outer.scala$jdk$AnyAccumulatorStepper$$i() + 1);
               this.$outer.scala$jdk$AnyAccumulatorStepper$$N_$eq(this.$outer.scala$jdk$AnyAccumulatorStepper$$N() - 1L);
               return true;
            }
         }

         public void forEachRemaining(final Consumer f) {
            while(this.$outer.scala$jdk$AnyAccumulatorStepper$$N() > 0L) {
               if ((long)this.$outer.scala$jdk$AnyAccumulatorStepper$$i() >= this.$outer.scala$jdk$AnyAccumulatorStepper$$n()) {
                  this.$outer.scala$jdk$AnyAccumulatorStepper$$loadMore();
               }

               int i0 = this.$outer.scala$jdk$AnyAccumulatorStepper$$i();
               if (this.$outer.scala$jdk$AnyAccumulatorStepper$$n() - (long)this.$outer.scala$jdk$AnyAccumulatorStepper$$i() > this.$outer.scala$jdk$AnyAccumulatorStepper$$N()) {
                  this.$outer.scala$jdk$AnyAccumulatorStepper$$n_$eq((long)(this.$outer.scala$jdk$AnyAccumulatorStepper$$i() + (int)this.$outer.scala$jdk$AnyAccumulatorStepper$$N()));
               }

               while((long)this.$outer.scala$jdk$AnyAccumulatorStepper$$i() < this.$outer.scala$jdk$AnyAccumulatorStepper$$n()) {
                  f.accept(this.$outer.scala$jdk$AnyAccumulatorStepper$$a()[this.$outer.scala$jdk$AnyAccumulatorStepper$$i()]);
                  this.$outer.scala$jdk$AnyAccumulatorStepper$$i_$eq(this.$outer.scala$jdk$AnyAccumulatorStepper$$i() + 1);
               }

               this.$outer.scala$jdk$AnyAccumulatorStepper$$N_$eq(this.$outer.scala$jdk$AnyAccumulatorStepper$$N() - (this.$outer.scala$jdk$AnyAccumulatorStepper$$n() - (long)i0));
            }

         }

         public {
            if (AnyAccumulatorStepper.this == null) {
               throw null;
            } else {
               this.$outer = AnyAccumulatorStepper.this;
            }
         }
      };
   }

   public AnyAccumulatorStepper(final AnyAccumulator acc) {
      this.acc = acc;
      this.h = 0;
      this.scala$jdk$AnyAccumulatorStepper$$i = 0;
      this.scala$jdk$AnyAccumulatorStepper$$a = acc.hIndex() > 0 ? acc.history()[0] : acc.current();
      long var10001;
      if (acc.hIndex() > 0) {
         int cumulative_i = 0;
         var10001 = acc.cumul()[cumulative_i];
      } else {
         var10001 = (long)acc.index();
      }

      this.scala$jdk$AnyAccumulatorStepper$$n = var10001;
      this.scala$jdk$AnyAccumulatorStepper$$N = acc.totalSize();
   }
}
