package spire.math.prime;

import java.lang.invoke.SerializedLambda;
import java.util.NoSuchElementException;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import spire.math.SafeLong;

@ScalaSignature(
   bytes = "\u0006\u000553Aa\u0003\u0007\u0001'!)!\u0004\u0001C\u00017!1a\u0004\u0001Q!\n}Aa!\u000b\u0001!B\u0013Q\u0003\"B\u0017\u0001\t\u0003r\u0003\"\u0002\u001e\u0001\t\u0003Y\u0004\"B \u0001\t\u0003Y\u0004\"\u0002!\u0001\t\u0003\t\u0005\"\u0002\"\u0001\t\u0003\u0019\u0005\"B$\u0001\t\u0003A\u0005\"B&\u0001\t\u0003a%A\u0003$bGR|'\u000fS3ba*\u0011QBD\u0001\u0006aJLW.\u001a\u0006\u0003\u001fA\tA!\\1uQ*\t\u0011#A\u0003ta&\u0014Xm\u0001\u0001\u0014\u0005\u0001!\u0002CA\u000b\u0019\u001b\u00051\"\"A\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005e1\"AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u00029A\u0011Q\u0004A\u0007\u0002\u0019\u0005\u0019\u0011M\u001d:\u0011\u0007U\u0001#%\u0003\u0002\"-\t)\u0011I\u001d:bsB\u00111E\n\b\u0003;\u0011J!!\n\u0007\u0002\u0013MKWM^3Vi&d\u0017BA\u0014)\u0005\u00191\u0015m\u0019;pe*\u0011Q\u0005D\u0001\u0004Y\u0016t\u0007CA\u000b,\u0013\tacCA\u0002J]R\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002_A\u0011\u0001g\u000e\b\u0003cU\u0002\"A\r\f\u000e\u0003MR!\u0001\u000e\n\u0002\rq\u0012xn\u001c;?\u0013\t1d#\u0001\u0004Qe\u0016$WMZ\u0005\u0003qe\u0012aa\u0015;sS:<'B\u0001\u001c\u0017\u0003\u001dI7/R7qif,\u0012\u0001\u0010\t\u0003+uJ!A\u0010\f\u0003\u000f\t{w\u000e\\3b]\u0006Aan\u001c8F[B$\u00180\u0001\u0003tSj,W#\u0001\u0016\u0002#I,7/\u001b>f\u0013\u001atUmY3tg\u0006\u0014\u0018\u0010F\u0001E!\t)R)\u0003\u0002G-\t!QK\\5u\u0003!!\u0003\u000f\\;tI\u0015\fHC\u0001#J\u0011\u0015Q\u0015\u00021\u0001#\u0003\u00191\u0017m\u0019;pe\u00069A-Z9vKV,G#\u0001\u0012"
)
public class FactorHeap {
   private SieveUtil.Factor[] arr = new SieveUtil.Factor[8];
   private int len = 0;

   public String toString() {
      return .MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(.MODULE$.refArrayOps(this.arr), (x$1) -> BoxesRunTime.boxToBoolean($anonfun$toString$1(x$1)))), (x$2) -> x$2.next(), scala.reflect.ClassTag..MODULE$.apply(SafeLong.class))).mkString("FactorHeap(", ", ", ")");
   }

   public boolean isEmpty() {
      return this.len == 0;
   }

   public boolean nonEmpty() {
      return this.len > 0;
   }

   public int size() {
      return this.len;
   }

   public void resizeIfNecessary() {
      if (this.len >= this.arr.length) {
         SieveUtil.Factor[] arr2 = new SieveUtil.Factor[this.arr.length * 2];
         System.arraycopy(this.arr, 0, arr2, 0, this.arr.length);
         this.arr = arr2;
      }

   }

   public void $plus$eq(final SieveUtil.Factor factor) {
      ++this.len;
      this.resizeIfNecessary();

      int i;
      int j;
      for(i = this.len; i > 1; i = j) {
         j = i >>> 1;
         SieveUtil.Factor fj = this.arr[j];
         if (factor.next().$greater$eq(fj.next())) {
            this.arr[i] = factor;
            return;
         }

         this.arr[i] = fj;
      }

      this.arr[i] = factor;
   }

   public SieveUtil.Factor dequeue() {
      if (this.len == 0) {
         throw new NoSuchElementException("empty heap");
      } else {
         SieveUtil.Factor result = this.arr[1];
         SieveUtil.Factor last = this.arr[this.len];
         --this.len;
         int i = 1;

         for(int j = 2; this.len >= j; j *= 2) {
            if (j < this.len && this.arr[j].next().$greater(this.arr[j + 1].next())) {
               ++j;
            }

            SieveUtil.Factor cv = this.arr[j];
            if (last.next().$less$eq(cv.next())) {
               this.arr[i] = last;
               return result;
            }

            this.arr[i] = cv;
            i = j;
         }

         this.arr[i] = last;
         return result;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$toString$1(final SieveUtil.Factor x$1) {
      return x$1 != null;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
