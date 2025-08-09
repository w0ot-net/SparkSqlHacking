package scala.collection.parallel.mutable;

import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.collection.immutable.List;
import scala.collection.mutable.ArraySeq;
import scala.collection.mutable.DoublingUnrolledBuffer;
import scala.collection.mutable.UnrolledBuffer;
import scala.collection.mutable.ArraySeq.;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.Task;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-da\u0002\u000e\u001c!\u0003\r\t\u0001\n\u0005\u0006{\u0001!\tA\u0010\u0005\b\u0005\u0002\u0011\r\u0011\"\u0001D\u0011\u0015I\u0005\u0001\"\u0001K\u0011\u0015q\u0005\u0001\"\u0001P\u0011\u0015\u0001\u0006\u0001\"\u0001?\u0011\u0015\t\u0006\u0001\"\u0011S\u0011\u0015A\u0006\u0001\"\u0001Z\u0011\u00151\u0007\u0001\"\u0001h\r\u0011A\u0007\u0001A5\t\u00119L!\u0011!Q\u0001\n=D\u0001B]\u0005\u0003\u0002\u0003\u0006I!\u0016\u0005\tg&\u0011\t\u0011)A\u0005+\")A/\u0003C\u0001k\"9a*\u0003a\u0001\n\u0003I\bb\u0002>\n\u0001\u0004%\ta\u001f\u0005\u0007}&\u0001\u000b\u0015B \t\r}LA\u0011AA\u0001\u0011\u001d\ti!\u0003C\u0005\u0003\u001fAq!!\u000b\n\t\u0003\tY\u0003C\u0004\u0002:%!\t!a\u000f\t\u000f\u0005\r\u0013\u0002\"\u0011\u0002F\u001d9\u0011qK\u000e\t\u0002\u0005ecA\u0002\u000e\u001c\u0011\u0003\tY\u0006\u0003\u0004u/\u0011\u0005\u0011Q\f\u0005\b\u0003?:B\u0011AA1\u0005a)fN]8mY\u0016$\u0007+\u0019:BeJ\f\u0017pQ8nE&tWM\u001d\u0006\u00039u\tq!\\;uC\ndWM\u0003\u0002\u001f?\u0005A\u0001/\u0019:bY2,GN\u0003\u0002!C\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003\t\nQa]2bY\u0006\u001c\u0001!\u0006\u0002&aM\u0019\u0001A\n\u0016\u0011\u0005\u001dBS\"A\u0011\n\u0005%\n#AB!osJ+g\r\u0005\u0003,Y9JT\"A\u000f\n\u00055j\"\u0001C\"p[\nLg.\u001a:\u0011\u0005=\u0002D\u0002\u0001\u0003\u0006c\u0001\u0011\rA\r\u0002\u0002)F\u00111G\u000e\t\u0003OQJ!!N\u0011\u0003\u000f9{G\u000f[5oOB\u0011qeN\u0005\u0003q\u0005\u00121!\u00118z!\rQ4HL\u0007\u00027%\u0011Ah\u0007\u0002\t!\u0006\u0014\u0018I\u001d:bs\u00061A%\u001b8ji\u0012\"\u0012a\u0010\t\u0003O\u0001K!!Q\u0011\u0003\tUs\u0017\u000e^\u0001\u0005EV4g-F\u0001E!\r)uIN\u0007\u0002\r*\u0011AdH\u0005\u0003\u0011\u001a\u0013a\u0003R8vE2LgnZ+oe>dG.\u001a3Ck\u001a4WM]\u0001\u0007C\u0012$wJ\\3\u0015\u0005-cU\"\u0001\u0001\t\u000b5\u001b\u0001\u0019\u0001\u0018\u0002\t\u0015dW-\\\u0001\u0007e\u0016\u001cX\u000f\u001c;\u0015\u0003e\nQa\u00197fCJ\f\u0001b]5{K\"Kg\u000e\u001e\u000b\u0003\u007fMCQ\u0001\u0016\u0004A\u0002U\u000b!a\u001d>\u0011\u0005\u001d2\u0016BA,\"\u0005\rIe\u000e^\u0001\bG>l'-\u001b8f+\rQV,\u0019\u000b\u00037\u0012\u0004Ba\u000b\u0017]AB\u0011q&\u0018\u0003\u0006=\u001e\u0011\ra\u0018\u0002\u0002\u001dF\u00111G\f\t\u0003_\u0005$QAY\u0004C\u0002\r\u0014QAT3x)>\f\"!\u000f\u001c\t\u000b\u0015<\u0001\u0019A.\u0002\u000b=$\b.\u001a:\u0002\tML'0Z\u000b\u0002+\n\u00192i\u001c9z+:\u0014x\u000e\u001c7fIR{\u0017I\u001d:bsN\u0019\u0011B\n6\u0011\t-Zw(\\\u0005\u0003Yv\u0011A\u0001V1tWB\u00111*C\u0001\u0006CJ\u0014\u0018-\u001f\t\u0004OA4\u0014BA9\"\u0005\u0015\t%O]1z\u0003\u0019ygMZ:fi\u00069\u0001n\\<nC:L\u0018A\u0002\u001fj]&$h\b\u0006\u0003nm^D\b\"\u00028\u000e\u0001\u0004y\u0007\"\u0002:\u000e\u0001\u0004)\u0006\"B:\u000e\u0001\u0004)V#A \u0002\u0015I,7/\u001e7u?\u0012*\u0017\u000f\u0006\u0002@y\"9QpDA\u0001\u0002\u0004y\u0014a\u0001=%c\u00059!/Z:vYR\u0004\u0013\u0001\u00027fC\u001a$2aPA\u0002\u0011\u001d\t)!\u0005a\u0001\u0003\u000f\tA\u0001\u001d:fmB!q%!\u0003@\u0013\r\tY!\t\u0002\u0007\u001fB$\u0018n\u001c8\u0002\u0013\u0019Lg\u000eZ*uCJ$H\u0003BA\t\u0003K\u0001baJA\n\u0003/)\u0016bAA\u000bC\t1A+\u001e9mKJ\u0002R!!\u0007\u0002 Yr1!RA\u000e\u0013\r\tiBR\u0001\u000f+:\u0014x\u000e\u001c7fI\n+hMZ3s\u0013\u0011\t\t#a\t\u0003\u0011Us'o\u001c7mK\u0012T1!!\bG\u0011\u0019\t9C\u0005a\u0001+\u0006\u0019\u0001o\\:\u0002\u000bM\u0004H.\u001b;\u0016\u0005\u00055\u0002#BA\u0018\u0003kiWBAA\u0019\u0015\r\t\u0019dH\u0001\nS6lW\u000f^1cY\u0016LA!a\u000e\u00022\t!A*[:u\u0003I\u0019\bn\\;mIN\u0003H.\u001b;GkJ$\b.\u001a:\u0016\u0005\u0005u\u0002cA\u0014\u0002@%\u0019\u0011\u0011I\u0011\u0003\u000f\t{w\u000e\\3b]\u0006AAo\\*ue&tw\r\u0006\u0002\u0002HA!\u0011\u0011JA*\u001b\t\tYE\u0003\u0003\u0002N\u0005=\u0013\u0001\u00027b]\u001eT!!!\u0015\u0002\t)\fg/Y\u0005\u0005\u0003+\nYE\u0001\u0004TiJLgnZ\u0001\u0019+:\u0014x\u000e\u001c7fIB\u000b'/\u0011:sCf\u001cu.\u001c2j]\u0016\u0014\bC\u0001\u001e\u0018'\t9b\u0005\u0006\u0002\u0002Z\u0005)\u0011\r\u001d9msV!\u00111MA5)\t\t)\u0007\u0005\u0003;\u0001\u0005\u001d\u0004cA\u0018\u0002j\u0011)\u0011'\u0007b\u0001e\u0001"
)
public interface UnrolledParArrayCombiner extends Combiner {
   static UnrolledParArrayCombiner apply() {
      return UnrolledParArrayCombiner$.MODULE$.apply();
   }

   void scala$collection$parallel$mutable$UnrolledParArrayCombiner$_setter_$buff_$eq(final DoublingUnrolledBuffer x$1);

   DoublingUnrolledBuffer buff();

   // $FF: synthetic method
   static UnrolledParArrayCombiner addOne$(final UnrolledParArrayCombiner $this, final Object elem) {
      return $this.addOne(elem);
   }

   default UnrolledParArrayCombiner addOne(final Object elem) {
      this.buff().$plus$eq(elem);
      return this;
   }

   // $FF: synthetic method
   static ParArray result$(final UnrolledParArrayCombiner $this) {
      return $this.result();
   }

   default ParArray result() {
      Object[] array = new Object[this.size()];
      ArraySeq arrayseq = .MODULE$.make(array);
      this.combinerTaskSupport().executeAndWaitResult(new CopyUnrolledToArray(array, 0, this.size()));
      return new ParArray(arrayseq);
   }

   // $FF: synthetic method
   static void clear$(final UnrolledParArrayCombiner $this) {
      $this.clear();
   }

   default void clear() {
      this.buff().clear();
   }

   // $FF: synthetic method
   static void sizeHint$(final UnrolledParArrayCombiner $this, final int sz) {
      $this.sizeHint(sz);
   }

   default void sizeHint(final int sz) {
      this.buff().lastPtr().next_$eq(new UnrolledBuffer.Unrolled(0, new Object[sz], (UnrolledBuffer.Unrolled)null, this.buff(), scala.reflect.ClassTag..MODULE$.Any()));
      this.buff().lastPtr_$eq(this.buff().lastPtr().next());
   }

   // $FF: synthetic method
   static Combiner combine$(final UnrolledParArrayCombiner $this, final Combiner other) {
      return $this.combine(other);
   }

   default Combiner combine(final Combiner other) {
      if (other == this) {
         return this;
      } else if (other instanceof UnrolledParArrayCombiner) {
         UnrolledParArrayCombiner var4 = (UnrolledParArrayCombiner)other;
         this.buff().concat(var4.buff());
         return this;
      } else {
         throw new UnsupportedOperationException("Cannot combine with combiner of different type.");
      }
   }

   // $FF: synthetic method
   static int size$(final UnrolledParArrayCombiner $this) {
      return $this.size();
   }

   default int size() {
      return this.buff().size();
   }

   static void $init$(final UnrolledParArrayCombiner $this) {
      $this.scala$collection$parallel$mutable$UnrolledParArrayCombiner$_setter_$buff_$eq(new DoublingUnrolledBuffer(scala.reflect.ClassTag..MODULE$.Any()));
   }

   public class CopyUnrolledToArray implements Task {
      private final Object[] array;
      private final int offset;
      private final int howmany;
      private BoxedUnit result;
      private volatile Throwable throwable;
      // $FF: synthetic field
      public final UnrolledParArrayCombiner $outer;

      public Object repr() {
         return Task.repr$(this);
      }

      public void merge(final Object that) {
         Task.merge$(this, that);
      }

      public void forwardThrowable() {
         Task.forwardThrowable$(this);
      }

      public void tryLeaf(final Option lastres) {
         Task.tryLeaf$(this, lastres);
      }

      public void tryMerge(final Object t) {
         Task.tryMerge$(this, t);
      }

      public void mergeThrowables(final Task that) {
         Task.mergeThrowables$(this, that);
      }

      public void signalAbort() {
         Task.signalAbort$(this);
      }

      public Throwable throwable() {
         return this.throwable;
      }

      public void throwable_$eq(final Throwable x$1) {
         this.throwable = x$1;
      }

      public void result() {
         BoxedUnit var10000 = this.result;
      }

      public void result_$eq(final BoxedUnit x$1) {
         this.result = x$1;
      }

      public void leaf(final Option prev) {
         if (this.howmany > 0) {
            int totalleft = this.howmany;
            Tuple2 var5 = this.findStart(this.offset);
            if (var5 == null) {
               throw new MatchError(var5);
            } else {
               UnrolledBuffer.Unrolled startnode = (UnrolledBuffer.Unrolled)var5._1();
               int startpos = var5._2$mcI$sp();
               Tuple2 var4 = new Tuple2(startnode, BoxesRunTime.boxToInteger(startpos));
               UnrolledBuffer.Unrolled startnodex = (UnrolledBuffer.Unrolled)var4._1();
               int startposx = var4._2$mcI$sp();
               UnrolledBuffer.Unrolled curr = startnodex;
               int pos = startposx;

               for(int arroffset = this.offset; totalleft > 0; curr = curr.next()) {
                  int lefthere = scala.math.package..MODULE$.min(totalleft, curr.size() - pos);
                  scala.Array..MODULE$.copy(curr.array(), pos, this.array, arroffset, lefthere);
                  totalleft -= lefthere;
                  arroffset += lefthere;
                  pos = 0;
               }

            }
         }
      }

      private Tuple2 findStart(final int pos) {
         int left = pos;

         UnrolledBuffer.Unrolled node;
         for(node = this.scala$collection$parallel$mutable$UnrolledParArrayCombiner$CopyUnrolledToArray$$$outer().buff().headPtr(); left - node.size() >= 0; node = node.next()) {
            left -= node.size();
         }

         return new Tuple2(node, BoxesRunTime.boxToInteger(left));
      }

      public List split() {
         int fp = this.howmany / 2;
         return new scala.collection.immutable..colon.colon(this.scala$collection$parallel$mutable$UnrolledParArrayCombiner$CopyUnrolledToArray$$$outer().new CopyUnrolledToArray(this.array, this.offset, fp), new scala.collection.immutable..colon.colon(this.scala$collection$parallel$mutable$UnrolledParArrayCombiner$CopyUnrolledToArray$$$outer().new CopyUnrolledToArray(this.array, this.offset + fp, this.howmany - fp), scala.collection.immutable.Nil..MODULE$));
      }

      public boolean shouldSplitFurther() {
         return this.howmany > scala.collection.parallel.package$.MODULE$.thresholdFromSize(this.scala$collection$parallel$mutable$UnrolledParArrayCombiner$CopyUnrolledToArray$$$outer().size(), this.scala$collection$parallel$mutable$UnrolledParArrayCombiner$CopyUnrolledToArray$$$outer().combinerTaskSupport().parallelismLevel());
      }

      public String toString() {
         return (new StringBuilder(23)).append("CopyUnrolledToArray(").append(this.offset).append(", ").append(this.howmany).append(")").toString();
      }

      // $FF: synthetic method
      public UnrolledParArrayCombiner scala$collection$parallel$mutable$UnrolledParArrayCombiner$CopyUnrolledToArray$$$outer() {
         return this.$outer;
      }

      public CopyUnrolledToArray(final Object[] array, final int offset, final int howmany) {
         this.array = array;
         this.offset = offset;
         this.howmany = howmany;
         if (UnrolledParArrayCombiner.this == null) {
            throw null;
         } else {
            this.$outer = UnrolledParArrayCombiner.this;
            super();
            Task.$init$(this);
            this.result = BoxedUnit.UNIT;
         }
      }
   }
}
