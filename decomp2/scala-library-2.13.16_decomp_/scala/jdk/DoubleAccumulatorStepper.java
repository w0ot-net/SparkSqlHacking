package scala.jdk;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import scala.collection.DoubleStepper;
import scala.collection.Stepper;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=b!B\u000e\u001d\u0001q\u0001\u0003\u0002\u0003\u001e\u0001\u0005\u000b\u0007I\u0011B\u001e\t\u0011\u0001\u0003!\u0011!Q\u0001\nqBQ!\u0011\u0001\u0005\u0002\tCq!\u0012\u0001A\u0002\u0013%a\tC\u0004K\u0001\u0001\u0007I\u0011B&\t\rE\u0003\u0001\u0015)\u0003H\u0011\u001d\u0011\u0006\u00011A\u0005\n\u0019Cqa\u0015\u0001A\u0002\u0013%A\u000b\u0003\u0004W\u0001\u0001\u0006Ka\u0012\u0005\b/\u0002\u0001\r\u0011\"\u0003Y\u0011\u001dy\u0006\u00011A\u0005\n\u0001DaA\u0019\u0001!B\u0013I\u0006bB2\u0001\u0001\u0004%I\u0001\u001a\u0005\bQ\u0002\u0001\r\u0011\"\u0003j\u0011\u0019Y\u0007\u0001)Q\u0005K\"9A\u000e\u0001a\u0001\n\u0013!\u0007bB7\u0001\u0001\u0004%IA\u001c\u0005\u0007a\u0002\u0001\u000b\u0015B3\t\u000bE\u0004A\u0011\u0002:\t\u000bU\u0004A\u0011\u0002<\t\u000b]\u0004A\u0011\u0001$\t\u000ba\u0004A\u0011\u00013\t\u000be\u0004A\u0011\u0001>\t\u000by\u0004A\u0011A@\t\u000f\u0005\u0005\u0001\u0001\"\u0001\u0002\u0004!9\u0011Q\u0001\u0001\u0005B\u0005\u001d!\u0001\u0007#pk\ndW-Q2dk6,H.\u0019;peN#X\r\u001d9fe*\u0011QDH\u0001\u0004U\u0012\\'\"A\u0010\u0002\u000bM\u001c\u0017\r\\1\u0014\t\u0001\tSe\u000b\t\u0003E\rj\u0011AH\u0005\u0003Iy\u0011a!\u00118z%\u00164\u0007C\u0001\u0014*\u001b\u00059#B\u0001\u0015\u001f\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003U\u001d\u0012Q\u0002R8vE2,7\u000b^3qa\u0016\u0014\bC\u0001\u00178\u001d\tiSG\u0004\u0002/i9\u0011qfM\u0007\u0002a)\u0011\u0011GM\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\tq$\u0003\u0002)=%\u0011agJ\u0001\b'R,\u0007\u000f]3s\u0013\tA\u0014H\u0001\bFM\u001aL7-[3oiN\u0003H.\u001b;\u000b\u0005Y:\u0013aA1dGV\tA\b\u0005\u0002>}5\tA$\u0003\u0002@9\t\tBi\\;cY\u0016\f5mY;nk2\fGo\u001c:\u0002\t\u0005\u001c7\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\r#\u0005CA\u001f\u0001\u0011\u0015Q4\u00011\u0001=\u0003\u0005AW#A$\u0011\u0005\tB\u0015BA%\u001f\u0005\rIe\u000e^\u0001\u0006Q~#S-\u001d\u000b\u0003\u0019>\u0003\"AI'\n\u00059s\"\u0001B+oSRDq\u0001U\u0003\u0002\u0002\u0003\u0007q)A\u0002yIE\n!\u0001\u001b\u0011\u0002\u0003%\fQ![0%KF$\"\u0001T+\t\u000fAC\u0011\u0011!a\u0001\u000f\u0006\u0011\u0011\u000eI\u0001\u0002CV\t\u0011\fE\u0002#5rK!a\u0017\u0010\u0003\u000b\u0005\u0013(/Y=\u0011\u0005\tj\u0016B\u00010\u001f\u0005\u0019!u.\u001e2mK\u0006)\u0011m\u0018\u0013fcR\u0011A*\u0019\u0005\b!.\t\t\u00111\u0001Z\u0003\t\t\u0007%A\u0001o+\u0005)\u0007C\u0001\u0012g\u0013\t9gD\u0001\u0003M_:<\u0017!\u00028`I\u0015\fHC\u0001'k\u0011\u001d\u0001f\"!AA\u0002\u0015\f!A\u001c\u0011\u0002\u00039\u000bQAT0%KF$\"\u0001T8\t\u000fA\u000b\u0012\u0011!a\u0001K\u0006\u0011a\nI\u0001\u000eIV\u0004H.[2bi\u0016\u001cV\r\u001c4\u0015\u0005\r\u001b\b\"\u0002;\u0014\u0001\u0004)\u0017!\u00027j[&$\u0018\u0001\u00037pC\u0012luN]3\u0015\u00031\u000bqb\u00195be\u0006\u001cG/\u001a:jgRL7m]\u0001\rKN$\u0018.\\1uKNK'0Z\u0001\bQ\u0006\u001c8\u000b^3q+\u0005Y\bC\u0001\u0012}\u0013\tihDA\u0004C_>dW-\u00198\u0002\u00119,\u0007\u0010^*uKB$\u0012\u0001X\u0001\tiJL8\u000b\u001d7jiR\tQ%A\u0006ta2LG/\u001a:bi>\u0014X\u0003BA\u0005\u0003G)\"!a\u0003\u0011\t\u00055\u0011Q\u0004\b\u0005\u0003\u001f\tI\"\u0004\u0002\u0002\u0012)!\u00111CA\u000b\u0003\u0011)H/\u001b7\u000b\u0005\u0005]\u0011\u0001\u00026bm\u0006LA!a\u0007\u0002\u0012\u0005Y1\u000b\u001d7ji\u0016\u0014\u0018\r^8s\u0013\u0011\ty\"!\t\u0003\u0011=3Gi\\;cY\u0016TA!a\u0007\u0002\u0012\u00119\u0011Q\u0005\u000eC\u0002\u0005\u001d\"!\u0001\"\u0012\u0007q\u000bI\u0003E\u0002#\u0003WI1!!\f\u001f\u0005\r\te.\u001f"
)
public class DoubleAccumulatorStepper implements DoubleStepper, Stepper.EfficientSplit {
   private final DoubleAccumulator acc;
   private int h;
   private int scala$jdk$DoubleAccumulatorStepper$$i;
   private double[] scala$jdk$DoubleAccumulatorStepper$$a;
   private long scala$jdk$DoubleAccumulatorStepper$$n;
   private long scala$jdk$DoubleAccumulatorStepper$$N;

   public PrimitiveIterator.OfDouble javaIterator() {
      return DoubleStepper.javaIterator$(this);
   }

   public PrimitiveIterator.OfDouble javaIterator$mcD$sp() {
      return DoubleStepper.javaIterator$mcD$sp$(this);
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

   public Spliterator spliterator$mcI$sp() {
      return Stepper.spliterator$mcI$sp$(this);
   }

   public Spliterator spliterator$mcJ$sp() {
      return Stepper.spliterator$mcJ$sp$(this);
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

   private DoubleAccumulator acc() {
      return this.acc;
   }

   private int h() {
      return this.h;
   }

   private void h_$eq(final int x$1) {
      this.h = x$1;
   }

   public int scala$jdk$DoubleAccumulatorStepper$$i() {
      return this.scala$jdk$DoubleAccumulatorStepper$$i;
   }

   public void scala$jdk$DoubleAccumulatorStepper$$i_$eq(final int x$1) {
      this.scala$jdk$DoubleAccumulatorStepper$$i = x$1;
   }

   public double[] scala$jdk$DoubleAccumulatorStepper$$a() {
      return this.scala$jdk$DoubleAccumulatorStepper$$a;
   }

   private void a_$eq(final double[] x$1) {
      this.scala$jdk$DoubleAccumulatorStepper$$a = x$1;
   }

   public long scala$jdk$DoubleAccumulatorStepper$$n() {
      return this.scala$jdk$DoubleAccumulatorStepper$$n;
   }

   public void scala$jdk$DoubleAccumulatorStepper$$n_$eq(final long x$1) {
      this.scala$jdk$DoubleAccumulatorStepper$$n = x$1;
   }

   public long scala$jdk$DoubleAccumulatorStepper$$N() {
      return this.scala$jdk$DoubleAccumulatorStepper$$N;
   }

   public void scala$jdk$DoubleAccumulatorStepper$$N_$eq(final long x$1) {
      this.scala$jdk$DoubleAccumulatorStepper$$N = x$1;
   }

   private DoubleAccumulatorStepper duplicateSelf(final long limit) {
      DoubleAccumulatorStepper ans = new DoubleAccumulatorStepper(this.acc());
      ans.h_$eq(this.h());
      ans.scala$jdk$DoubleAccumulatorStepper$$i_$eq(this.scala$jdk$DoubleAccumulatorStepper$$i());
      ans.a_$eq(this.scala$jdk$DoubleAccumulatorStepper$$a());
      ans.scala$jdk$DoubleAccumulatorStepper$$n_$eq(this.scala$jdk$DoubleAccumulatorStepper$$n());
      ans.scala$jdk$DoubleAccumulatorStepper$$N_$eq(limit);
      return ans;
   }

   public void scala$jdk$DoubleAccumulatorStepper$$loadMore() {
      this.h_$eq(this.h() + 1);
      if (this.h() < this.acc().hIndex()) {
         this.a_$eq(this.acc().history()[this.h()]);
         this.scala$jdk$DoubleAccumulatorStepper$$n_$eq(this.acc().cumulative(this.h()) - this.acc().cumulative(this.h() - 1));
      } else {
         this.a_$eq(this.acc().current());
         this.scala$jdk$DoubleAccumulatorStepper$$n_$eq((long)this.acc().index());
      }

      this.scala$jdk$DoubleAccumulatorStepper$$i_$eq(0);
   }

   public int characteristics() {
      return 16720;
   }

   public long estimateSize() {
      return this.scala$jdk$DoubleAccumulatorStepper$$N();
   }

   public boolean hasStep() {
      return this.scala$jdk$DoubleAccumulatorStepper$$N() > 0L;
   }

   public double nextStep() {
      return this.nextStep$mcD$sp();
   }

   public DoubleStepper trySplit() {
      if (this.scala$jdk$DoubleAccumulatorStepper$$N() <= 1L) {
         return null;
      } else {
         long half = this.scala$jdk$DoubleAccumulatorStepper$$N() >> 1;
         long R = (this.h() <= 0 ? 0L : this.acc().cumulative(this.h() - 1)) + (long)this.scala$jdk$DoubleAccumulatorStepper$$i() + half;
         DoubleAccumulatorStepper ans = this.duplicateSelf(half);
         if (this.h() < this.acc().hIndex()) {
            long w = this.acc().seekSlot(R);
            this.h_$eq((int)(w >>> 32));
            if (this.h() < this.acc().hIndex()) {
               this.a_$eq(this.acc().history()[this.h()]);
               this.scala$jdk$DoubleAccumulatorStepper$$n_$eq(this.acc().cumulative(this.h()) - (this.h() > 0 ? this.acc().cumulative(this.h() - 1) : 0L));
            } else {
               this.a_$eq(this.acc().current());
               this.scala$jdk$DoubleAccumulatorStepper$$n_$eq((long)this.acc().index());
            }

            this.scala$jdk$DoubleAccumulatorStepper$$i_$eq((int)(w & 4294967295L));
         } else {
            this.scala$jdk$DoubleAccumulatorStepper$$i_$eq(this.scala$jdk$DoubleAccumulatorStepper$$i() + (int)half);
         }

         this.scala$jdk$DoubleAccumulatorStepper$$N_$eq(this.scala$jdk$DoubleAccumulatorStepper$$N() - half);
         return ans;
      }
   }

   public Spliterator.OfDouble spliterator() {
      return this.spliterator$mcD$sp();
   }

   public double nextStep$mcD$sp() {
      if (this.scala$jdk$DoubleAccumulatorStepper$$n() <= 0L) {
         throw new NoSuchElementException("next on empty Stepper");
      } else {
         if ((long)this.scala$jdk$DoubleAccumulatorStepper$$i() >= this.scala$jdk$DoubleAccumulatorStepper$$n()) {
            this.scala$jdk$DoubleAccumulatorStepper$$loadMore();
         }

         double ans = this.scala$jdk$DoubleAccumulatorStepper$$a()[this.scala$jdk$DoubleAccumulatorStepper$$i()];
         this.scala$jdk$DoubleAccumulatorStepper$$i_$eq(this.scala$jdk$DoubleAccumulatorStepper$$i() + 1);
         this.scala$jdk$DoubleAccumulatorStepper$$N_$eq(this.scala$jdk$DoubleAccumulatorStepper$$N() - 1L);
         return ans;
      }
   }

   public Spliterator.OfDouble spliterator$mcD$sp() {
      return new DoubleStepper.DoubleStepperSpliterator() {
         // $FF: synthetic field
         private final DoubleAccumulatorStepper $outer;

         public boolean tryAdvance(final DoubleConsumer c) {
            if (this.$outer.scala$jdk$DoubleAccumulatorStepper$$N() <= 0L) {
               return false;
            } else {
               if ((long)this.$outer.scala$jdk$DoubleAccumulatorStepper$$i() >= this.$outer.scala$jdk$DoubleAccumulatorStepper$$n()) {
                  this.$outer.scala$jdk$DoubleAccumulatorStepper$$loadMore();
               }

               c.accept(this.$outer.scala$jdk$DoubleAccumulatorStepper$$a()[this.$outer.scala$jdk$DoubleAccumulatorStepper$$i()]);
               this.$outer.scala$jdk$DoubleAccumulatorStepper$$i_$eq(this.$outer.scala$jdk$DoubleAccumulatorStepper$$i() + 1);
               this.$outer.scala$jdk$DoubleAccumulatorStepper$$N_$eq(this.$outer.scala$jdk$DoubleAccumulatorStepper$$N() - 1L);
               return true;
            }
         }

         public boolean tryAdvance(final Consumer c) {
            if (c instanceof DoubleConsumer) {
               DoubleConsumer var2 = (DoubleConsumer)c;
               return this.tryAdvance(var2);
            } else if (this.$outer.scala$jdk$DoubleAccumulatorStepper$$N() <= 0L) {
               return false;
            } else {
               if ((long)this.$outer.scala$jdk$DoubleAccumulatorStepper$$i() >= this.$outer.scala$jdk$DoubleAccumulatorStepper$$n()) {
                  this.$outer.scala$jdk$DoubleAccumulatorStepper$$loadMore();
               }

               c.accept(this.$outer.scala$jdk$DoubleAccumulatorStepper$$a()[this.$outer.scala$jdk$DoubleAccumulatorStepper$$i()]);
               this.$outer.scala$jdk$DoubleAccumulatorStepper$$i_$eq(this.$outer.scala$jdk$DoubleAccumulatorStepper$$i() + 1);
               this.$outer.scala$jdk$DoubleAccumulatorStepper$$N_$eq(this.$outer.scala$jdk$DoubleAccumulatorStepper$$N() - 1L);
               return true;
            }
         }

         public void forEachRemaining(final DoubleConsumer c) {
            while(this.$outer.scala$jdk$DoubleAccumulatorStepper$$N() > 0L) {
               if ((long)this.$outer.scala$jdk$DoubleAccumulatorStepper$$i() >= this.$outer.scala$jdk$DoubleAccumulatorStepper$$n()) {
                  this.$outer.scala$jdk$DoubleAccumulatorStepper$$loadMore();
               }

               int i0 = this.$outer.scala$jdk$DoubleAccumulatorStepper$$i();
               if (this.$outer.scala$jdk$DoubleAccumulatorStepper$$n() - (long)this.$outer.scala$jdk$DoubleAccumulatorStepper$$i() > this.$outer.scala$jdk$DoubleAccumulatorStepper$$N()) {
                  this.$outer.scala$jdk$DoubleAccumulatorStepper$$n_$eq((long)(this.$outer.scala$jdk$DoubleAccumulatorStepper$$i() + (int)this.$outer.scala$jdk$DoubleAccumulatorStepper$$N()));
               }

               while((long)this.$outer.scala$jdk$DoubleAccumulatorStepper$$i() < this.$outer.scala$jdk$DoubleAccumulatorStepper$$n()) {
                  c.accept(this.$outer.scala$jdk$DoubleAccumulatorStepper$$a()[this.$outer.scala$jdk$DoubleAccumulatorStepper$$i()]);
                  this.$outer.scala$jdk$DoubleAccumulatorStepper$$i_$eq(this.$outer.scala$jdk$DoubleAccumulatorStepper$$i() + 1);
               }

               this.$outer.scala$jdk$DoubleAccumulatorStepper$$N_$eq(this.$outer.scala$jdk$DoubleAccumulatorStepper$$N() - (this.$outer.scala$jdk$DoubleAccumulatorStepper$$n() - (long)i0));
            }

         }

         public void forEachRemaining(final Consumer c) {
            if (c instanceof DoubleConsumer) {
               DoubleConsumer var2 = (DoubleConsumer)c;
               this.forEachRemaining(var2);
            } else {
               while(this.$outer.scala$jdk$DoubleAccumulatorStepper$$N() > 0L) {
                  if ((long)this.$outer.scala$jdk$DoubleAccumulatorStepper$$i() >= this.$outer.scala$jdk$DoubleAccumulatorStepper$$n()) {
                     this.$outer.scala$jdk$DoubleAccumulatorStepper$$loadMore();
                  }

                  int i0 = this.$outer.scala$jdk$DoubleAccumulatorStepper$$i();
                  if (this.$outer.scala$jdk$DoubleAccumulatorStepper$$n() - (long)this.$outer.scala$jdk$DoubleAccumulatorStepper$$i() > this.$outer.scala$jdk$DoubleAccumulatorStepper$$N()) {
                     this.$outer.scala$jdk$DoubleAccumulatorStepper$$n_$eq((long)(this.$outer.scala$jdk$DoubleAccumulatorStepper$$i() + (int)this.$outer.scala$jdk$DoubleAccumulatorStepper$$N()));
                  }

                  while((long)this.$outer.scala$jdk$DoubleAccumulatorStepper$$i() < this.$outer.scala$jdk$DoubleAccumulatorStepper$$n()) {
                     c.accept(this.$outer.scala$jdk$DoubleAccumulatorStepper$$a()[this.$outer.scala$jdk$DoubleAccumulatorStepper$$i()]);
                     this.$outer.scala$jdk$DoubleAccumulatorStepper$$i_$eq(this.$outer.scala$jdk$DoubleAccumulatorStepper$$i() + 1);
                  }

                  this.$outer.scala$jdk$DoubleAccumulatorStepper$$N_$eq(this.$outer.scala$jdk$DoubleAccumulatorStepper$$N() - (this.$outer.scala$jdk$DoubleAccumulatorStepper$$n() - (long)i0));
               }

            }
         }

         public {
            if (DoubleAccumulatorStepper.this == null) {
               throw null;
            } else {
               this.$outer = DoubleAccumulatorStepper.this;
            }
         }
      };
   }

   public DoubleAccumulatorStepper(final DoubleAccumulator acc) {
      this.acc = acc;
      this.h = 0;
      this.scala$jdk$DoubleAccumulatorStepper$$i = 0;
      this.scala$jdk$DoubleAccumulatorStepper$$a = acc.hIndex() > 0 ? acc.history()[0] : acc.current();
      this.scala$jdk$DoubleAccumulatorStepper$$n = acc.hIndex() > 0 ? acc.cumulative(0) : (long)acc.index();
      this.scala$jdk$DoubleAccumulatorStepper$$N = acc.totalSize();
   }
}
