package scala.collection.parallel.mutable;

import java.lang.invoke.SerializedLambda;
import scala.collection.generic.Sizing;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Growable;
import scala.collection.parallel.Combiner;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u000594qa\u0003\u0007\u0011\u0002\u0007\u0005Q\u0003C\u0003.\u0001\u0011\u0005a\u0006C\u00043\u0001\t\u0007i\u0011A\u001a\t\u000f%\u0003!\u0019!C\u0001\u0015\")1\n\u0001C\u0001\u0019\")\u0001\u000b\u0001C\u0001#\")!\u000b\u0001C\u0001]!)1\u000b\u0001C\u0001)\")\u0011\r\u0001C\u0001E\")a\r\u0001D\u0001O\")\u0001\u000e\u0001D\u0001S\naA*\u0019>z\u0007>l'-\u001b8fe*\u0011QBD\u0001\b[V$\u0018M\u00197f\u0015\ty\u0001#\u0001\u0005qCJ\fG\u000e\\3m\u0015\t\t\"#\u0001\u0006d_2dWm\u0019;j_:T\u0011aE\u0001\u0006g\u000e\fG.Y\u0002\u0001+\u00111\u0012e\u000b\u001e\u0014\u0007\u000192\u0004\u0005\u0002\u001935\t!#\u0003\u0002\u001b%\t1\u0011I\\=SK\u001a\u0004B\u0001H\u000f U5\ta\"\u0003\u0002\u001f\u001d\tA1i\\7cS:,'\u000f\u0005\u0002!C1\u0001A!\u0002\u0012\u0001\u0005\u0004\u0019#\u0001B#mK6\f\"\u0001J\u0014\u0011\u0005a)\u0013B\u0001\u0014\u0013\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\u0007\u0015\n\u0005%\u0012\"aA!osB\u0011\u0001e\u000b\u0003\u0007Y\u0001!)\u0019A\u0012\u0003\u0005Q{\u0017A\u0002\u0013j]&$H\u0005F\u00010!\tA\u0002'\u0003\u00022%\t!QK\\5u\u0003\u0015\u0019\u0007.Y5o+\u0005!\u0004cA\u001b8s5\taG\u0003\u0002\u000e!%\u0011\u0001H\u000e\u0002\f\u0003J\u0014\u0018-\u001f\"vM\u001a,'\u000f\u0005\u0002!u\u0011)1\b\u0001b\u0001y\t!!)\u001e4g#\t!SHE\u0002?\u0001\u000e3Aa\u0010\u0001\u0001{\taAH]3gS:,W.\u001a8u}A\u0019Q'Q\u0010\n\u0005\t3$\u0001C$s_^\f'\r\\3\u0011\u0005\u0011;U\"A#\u000b\u0005\u0019\u0003\u0012aB4f]\u0016\u0014\u0018nY\u0005\u0003\u0011\u0016\u0013aaU5{S:<\u0017\u0001\u00037bgR\u0014WO\u001a4\u0016\u0003e\na!\u00193e\u001f:,GCA'O\u001b\u0005\u0001\u0001\"B(\u0005\u0001\u0004y\u0012\u0001B3mK6\faA]3tk2$H#\u0001\u0016\u0002\u000b\rdW-\u0019:\u0002\u000f\r|WNY5oKV\u0019Q\u000b\u0017/\u0015\u0005Y{\u0006\u0003\u0002\u000f\u001e/n\u0003\"\u0001\t-\u0005\u000be;!\u0019\u0001.\u0003\u00039\u000b\"\u0001J\u0010\u0011\u0005\u0001bF!B/\b\u0005\u0004q&!\u0002(foR{\u0017C\u0001\u0016(\u0011\u0015\u0001w\u00011\u0001W\u0003\u0015yG\u000f[3s\u0003\u0011\u0019\u0018N_3\u0016\u0003\r\u0004\"\u0001\u00073\n\u0005\u0015\u0014\"aA%oi\u0006y\u0011\r\u001c7pG\u0006$X-\u00118e\u0007>\u0004\u00180F\u0001+\u0003=qWm\u001e'buf\u001cu.\u001c2j]\u0016\u0014HC\u00016m!\u0015Y\u0007a\b\u0016:\u001b\u0005a\u0001\"B7\u000b\u0001\u0004!\u0014!\u00032vM\u001a\u001c\u0007.Y5o\u0001"
)
public interface LazyCombiner extends Combiner {
   void scala$collection$parallel$mutable$LazyCombiner$_setter_$lastbuff_$eq(final Growable x$1);

   ArrayBuffer chain();

   Growable lastbuff();

   // $FF: synthetic method
   static LazyCombiner addOne$(final LazyCombiner $this, final Object elem) {
      return $this.addOne(elem);
   }

   default LazyCombiner addOne(final Object elem) {
      this.lastbuff().$plus$eq(elem);
      return this;
   }

   // $FF: synthetic method
   static Object result$(final LazyCombiner $this) {
      return $this.result();
   }

   default Object result() {
      return this.allocateAndCopy();
   }

   // $FF: synthetic method
   static void clear$(final LazyCombiner $this) {
      $this.clear();
   }

   default void clear() {
      this.chain().clear();
   }

   // $FF: synthetic method
   static Combiner combine$(final LazyCombiner $this, final Combiner other) {
      return $this.combine(other);
   }

   default Combiner combine(final Combiner other) {
      if (this != other) {
         if (other instanceof LazyCombiner) {
            LazyCombiner that = (LazyCombiner)other;
            return this.newLazyCombiner((ArrayBuffer)this.chain().$plus$plus$eq(that.chain()));
         } else {
            throw new UnsupportedOperationException("Cannot combine with combiner of different type.");
         }
      } else {
         return this;
      }
   }

   // $FF: synthetic method
   static int size$(final LazyCombiner $this) {
      return $this.size();
   }

   default int size() {
      return BoxesRunTime.unboxToInt(this.chain().foldLeft(BoxesRunTime.boxToInteger(0), (x$1, x$2) -> BoxesRunTime.boxToInteger($anonfun$size$1(BoxesRunTime.unboxToInt(x$1), x$2))));
   }

   Object allocateAndCopy();

   LazyCombiner newLazyCombiner(final ArrayBuffer buffchain);

   // $FF: synthetic method
   static int $anonfun$size$1(final int x$1, final Growable x$2) {
      return x$1 + ((Sizing)x$2).size();
   }

   static void $init$(final LazyCombiner $this) {
      $this.scala$collection$parallel$mutable$LazyCombiner$_setter_$lastbuff_$eq((Growable)$this.chain().last());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
