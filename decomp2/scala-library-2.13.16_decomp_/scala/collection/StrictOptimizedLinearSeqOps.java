package scala.collection;

import scala.Function1;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005=3q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u00035\u0001\u0011\u0005Q\u0007C\u0003:\u0001\u0011\u0005#\bC\u0003?\u0001\u0011\u0005s\bC\u0003F\u0001\u0011\u0005cIA\u000eTiJL7\r^(qi&l\u0017N_3e\u0019&tW-\u0019:TKF|\u0005o\u001d\u0006\u0003\u000f!\t!bY8mY\u0016\u001cG/[8o\u0015\u0005I\u0011!B:dC2\f7\u0001A\u000b\u0005\u0019]q\u0012f\u0005\u0003\u0001\u001bE\t\u0004C\u0001\b\u0010\u001b\u0005A\u0011B\u0001\t\t\u0005\r\te.\u001f\t\u0006%M)R\u0004K\u0007\u0002\r%\u0011AC\u0002\u0002\r\u0019&tW-\u0019:TKF|\u0005o\u001d\t\u0003-]a\u0001\u0001\u0002\u0004\u0019\u0001\u0011\u0015\r!\u0007\u0002\u0002\u0003F\u0011!$\u0004\t\u0003\u001dmI!\u0001\b\u0005\u0003\u000f9{G\u000f[5oOB\u0011aC\b\u0003\u0007?\u0001!)\u0019\u0001\u0011\u0003\u0005\r\u001bUCA\u0011'#\tQ\"\u0005E\u0002\u0013G\u0015J!\u0001\n\u0004\u0003\u00131Kg.Z1s'\u0016\f\bC\u0001\f'\t\u00159cD1\u0001\u001a\u0005\u0005A\u0006C\u0001\f*\t\u0019Q\u0003\u0001\"b\u0001W\t\t1)\u0005\u0002\u001bYI\u0019Qf\f\u0019\u0007\t9\u0002\u0001\u0001\f\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\t\u0004%\r*\u0002#\u0002\n\u0001+uA\u0003#\u0002\n3+uA\u0013BA\u001a\u0007\u0005U\u0019FO]5di>\u0003H/[7ju\u0016$7+Z9PaN\fa\u0001J5oSR$C#\u0001\u001c\u0011\u000599\u0014B\u0001\u001d\t\u0005\u0011)f.\u001b;\u0002\u0011%$XM]1u_J,\u0012a\u000f\t\u0004%q*\u0012BA\u001f\u0007\u0005!IE/\u001a:bi>\u0014\u0018\u0001\u00023s_B$\"\u0001\u000b!\t\u000b\u0005\u001b\u0001\u0019\u0001\"\u0002\u00039\u0004\"AD\"\n\u0005\u0011C!aA%oi\u0006IAM]8q/\"LG.\u001a\u000b\u0003Q\u001dCQ\u0001\u0013\u0003A\u0002%\u000b\u0011\u0001\u001d\t\u0005\u001d)+B*\u0003\u0002L\u0011\tIa)\u001e8di&|g.\r\t\u0003\u001d5K!A\u0014\u0005\u0003\u000f\t{w\u000e\\3b]\u0002"
)
public interface StrictOptimizedLinearSeqOps extends LinearSeqOps, StrictOptimizedSeqOps {
   // $FF: synthetic method
   static Iterator iterator$(final StrictOptimizedLinearSeqOps $this) {
      return $this.iterator();
   }

   default Iterator iterator() {
      return new AbstractIterator() {
         private StrictOptimizedLinearSeqOps current = StrictOptimizedLinearSeqOps.this;

         public boolean hasNext() {
            return !this.current.isEmpty();
         }

         public Object next() {
            Object r = this.current.head();
            this.current = (StrictOptimizedLinearSeqOps)this.current.tail();
            return r;
         }
      };
   }

   // $FF: synthetic method
   static LinearSeq drop$(final StrictOptimizedLinearSeqOps $this, final int n) {
      return $this.drop(n);
   }

   default LinearSeq drop(final int n) {
      return this.loop$2(n, (LinearSeq)this.coll());
   }

   // $FF: synthetic method
   static LinearSeq dropWhile$(final StrictOptimizedLinearSeqOps $this, final Function1 p) {
      return $this.dropWhile(p);
   }

   default LinearSeq dropWhile(final Function1 p) {
      LinearSeq loop$3_s;
      for(loop$3_s = (LinearSeq)this.coll(); loop$3_s.nonEmpty() && BoxesRunTime.unboxToBoolean(p.apply(loop$3_s.head())); loop$3_s = (LinearSeq)loop$3_s.tail()) {
      }

      return loop$3_s;
   }

   private LinearSeq loop$2(final int n, final LinearSeq s) {
      while(n > 0 && !s.isEmpty()) {
         int var10000 = n - 1;
         s = (LinearSeq)s.tail();
         n = var10000;
      }

      return s;
   }

   private LinearSeq loop$3(final LinearSeq s, final Function1 p$1) {
      while(s.nonEmpty() && BoxesRunTime.unboxToBoolean(p$1.apply(s.head()))) {
         s = (LinearSeq)s.tail();
      }

      return s;
   }

   static void $init$(final StrictOptimizedLinearSeqOps $this) {
   }
}
