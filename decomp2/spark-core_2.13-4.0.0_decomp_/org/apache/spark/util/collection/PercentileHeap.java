package org.apache.spark.util.collection;

import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005U3Q!\u0004\b\u0001%aA\u0001b\b\u0001\u0003\u0002\u0003\u0006I!\t\u0005\u0006I\u0001!\t!\n\u0005\u0007S\u0001\u0001\u000b\u0011\u0002\u0016\t\rE\u0002\u0001\u0015!\u0003+\u0011\u0015\u0011\u0004\u0001\"\u00014\u0011\u00159\u0004\u0001\"\u00019\u0011\u0015a\u0004\u0001\"\u0001>\u0011\u0015q\u0004\u0001\"\u0001@\u000f!)e\"!A\t\u0002I1e\u0001C\u0007\u000f\u0003\u0003E\tAE$\t\u000b\u0011RA\u0011\u0001%\t\u000f%S\u0011\u0013!C\u0001\u0015\nq\u0001+\u001a:dK:$\u0018\u000e\\3IK\u0006\u0004(BA\b\u0011\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0003#I\tA!\u001e;jY*\u00111\u0003F\u0001\u0006gB\f'o\u001b\u0006\u0003+Y\ta!\u00199bG\",'\"A\f\u0002\u0007=\u0014xm\u0005\u0002\u00013A\u0011!$H\u0007\u00027)\tA$A\u0003tG\u0006d\u0017-\u0003\u0002\u001f7\t1\u0011I\\=SK\u001a\f!\u0002]3sG\u0016tG/Y4f\u0007\u0001\u0001\"A\u0007\u0012\n\u0005\rZ\"A\u0002#pk\ndW-\u0001\u0004=S:LGO\u0010\u000b\u0003M!\u0002\"a\n\u0001\u000e\u00039Aqa\b\u0002\u0011\u0002\u0003\u0007\u0011%A\u0005mCJ<W\rS3baB\u00191fL\u0011\u000e\u00031R!!E\u0017\u000b\u00039\nAA[1wC&\u0011\u0001\u0007\f\u0002\u000e!JLwN]5usF+X-^3\u0002\u0013Ml\u0017\r\u001c7IK\u0006\u0004\u0018aB5t\u000b6\u0004H/\u001f\u000b\u0002iA\u0011!$N\u0005\u0003mm\u0011qAQ8pY\u0016\fg.\u0001\u0003tSj,G#A\u001d\u0011\u0005iQ\u0014BA\u001e\u001c\u0005\rIe\u000e^\u0001\u000ba\u0016\u00148-\u001a8uS2,G#A\u0011\u0002\r%t7/\u001a:u)\t\u00015\t\u0005\u0002\u001b\u0003&\u0011!i\u0007\u0002\u0005+:LG\u000fC\u0003E\u0011\u0001\u0007\u0011%A\u0001y\u00039\u0001VM]2f]RLG.\u001a%fCB\u0004\"a\n\u0006\u0014\u0005)IB#\u0001$\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00132+\u0005Y%FA\u0011MW\u0005i\u0005C\u0001(T\u001b\u0005y%B\u0001)R\u0003%)hn\u00195fG.,GM\u0003\u0002S7\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005Q{%!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0002"
)
public class PercentileHeap {
   private final double percentage;
   private final PriorityQueue largeHeap;
   private final PriorityQueue smallHeap;

   public static double $lessinit$greater$default$1() {
      return PercentileHeap$.MODULE$.$lessinit$greater$default$1();
   }

   public boolean isEmpty() {
      return this.smallHeap.isEmpty() && this.largeHeap.isEmpty();
   }

   public int size() {
      return this.smallHeap.size() + this.largeHeap.size();
   }

   public double percentile() {
      if (this.isEmpty()) {
         throw new NoSuchElementException("empty");
      } else {
         return BoxesRunTime.unboxToDouble(this.largeHeap.peek());
      }
   }

   public void insert(final double x) {
      if (this.isEmpty()) {
         this.largeHeap.offer(BoxesRunTime.boxToDouble(x));
      } else {
         double p = BoxesRunTime.unboxToDouble(this.largeHeap.peek());
         boolean growBot = (int)((double)(this.size() + 1) * this.percentage) > this.smallHeap.size();
         if (growBot) {
            if (x < p) {
               this.smallHeap.offer(BoxesRunTime.boxToDouble(-x));
            } else {
               this.largeHeap.offer(BoxesRunTime.boxToDouble(x));
               this.smallHeap.offer(BoxesRunTime.boxToDouble(-BoxesRunTime.unboxToDouble(this.largeHeap.poll())));
            }
         } else if (x < p) {
            this.smallHeap.offer(BoxesRunTime.boxToDouble(-x));
            this.largeHeap.offer(BoxesRunTime.boxToDouble(-BoxesRunTime.unboxToDouble(this.smallHeap.poll())));
         } else {
            this.largeHeap.offer(BoxesRunTime.boxToDouble(x));
         }
      }
   }

   public PercentileHeap(final double percentage) {
      this.percentage = percentage;
      .MODULE$.assert(percentage > (double)0 && percentage < (double)1);
      this.largeHeap = new PriorityQueue();
      this.smallHeap = new PriorityQueue();
   }
}
