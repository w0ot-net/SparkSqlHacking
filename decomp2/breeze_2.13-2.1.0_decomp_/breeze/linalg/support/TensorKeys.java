package breeze.linalg.support;

import breeze.linalg.QuasiTensor;
import breeze.linalg.TensorLike;
import java.lang.invoke.SerializedLambda;
import scala.;
import scala.Function1;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ub\u0001B\n\u0015\u0001mA\u0001b\t\u0001\u0003\u0006\u0004%I\u0001\n\u0005\ta\u0001\u0011\t\u0011)A\u0005K!A\u0011\u0007\u0001B\u0001B\u0003%!\u0007\u0003\u00056\u0001\t\u0005\t\u0015!\u00037\u0011!a\u0004A!A!\u0002\u0017i\u0004\"B$\u0001\t\u0003A\u0005\"\u0002)\u0001\t\u0003\t\u0006\"B+\u0001\t\u00031\u0006\"B/\u0001\t\u0003q\u0006\"B5\u0001\t\u0003Q\u0007\"B7\u0001\t\u0003q\u0007\"\u00029\u0001\t\u0003\n\b\"B?\u0001\t\u0003rx!CA\u0002)\u0005\u0005\t\u0012AA\u0003\r!\u0019B#!A\t\u0002\u0005\u001d\u0001BB$\u0010\t\u0003\tI\u0001C\u0005\u0002\f=\t\n\u0011\"\u0001\u0002\u000e!I\u00111F\b\u0012\u0002\u0013\u0005\u0011Q\u0006\u0002\u000b)\u0016t7o\u001c:LKf\u001c(BA\u000b\u0017\u0003\u001d\u0019X\u000f\u001d9peRT!a\u0006\r\u0002\r1Lg.\u00197h\u0015\u0005I\u0012A\u00022sK\u0016TXm\u0001\u0001\u0016\tqQTiJ\n\u0003\u0001u\u0001\"AH\u0011\u000e\u0003}Q\u0011\u0001I\u0001\u0006g\u000e\fG.Y\u0005\u0003E}\u0011a!\u00118z%\u00164\u0017A\u0002;f]N|'/F\u0001&!\t1s\u0005\u0004\u0001\u0005\r!\u0002AQ1\u0001*\u0005\u0011!\u0006.[:\u0012\u0005)j\u0003C\u0001\u0010,\u0013\tasDA\u0004O_RD\u0017N\\4\u0011\u0005yq\u0013BA\u0018 \u0005\r\te._\u0001\bi\u0016t7o\u001c:!\u0003\u0019\t7\r^5wKB\u0011adM\u0005\u0003i}\u0011qAQ8pY\u0016\fg.A\u0001g!\u0011qr'\u000f\u001a\n\u0005az\"!\u0003$v]\u000e$\u0018n\u001c82!\t1#\bB\u0003<\u0001\t\u0007\u0011FA\u0001L\u0003\t)g\u000f\u0005\u0003\u001f}\u0015\u0002\u0015BA  \u0005A!C.Z:tI\r|Gn\u001c8%Y\u0016\u001c8\u000f\u0005\u0003B\u0005f\"U\"\u0001\f\n\u0005\r3\"A\u0002+f]N|'\u000f\u0005\u0002'\u000b\u0012)a\t\u0001b\u0001S\t\ta+\u0001\u0004=S:LGO\u0010\u000b\u0005\u00136su\n\u0006\u0002K\u0019B)1\nA\u001dEK5\tA\u0003C\u0003=\r\u0001\u000fQ\bC\u0003$\r\u0001\u0007Q\u0005C\u00042\rA\u0005\t\u0019\u0001\u001a\t\u000fU2\u0001\u0013!a\u0001m\u0005!1/\u001b>f+\u0005\u0011\u0006C\u0001\u0010T\u0013\t!vDA\u0002J]R\f\u0001\"\u001b;fe\u0006$xN]\u000b\u0002/B\u0019\u0001lW\u001d\u000e\u0003eS!AW\u0010\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002]3\nA\u0011\n^3sCR|'/A\u0004g_J,\u0017m\u00195\u0016\u0005};GC\u00011d!\tq\u0012-\u0003\u0002c?\t!QK\\5u\u0011\u0015!\u0017\u00021\u0001f\u0003\t1g\u000e\u0005\u0003\u001foe2\u0007C\u0001\u0014h\t\u0015A\u0017B1\u0001*\u0005\u0005)\u0016A\u00024jYR,'\u000f\u0006\u0002KW\")AN\u0003a\u0001m\u0005\t\u0001/\u0001\u0006xSRDg)\u001b7uKJ$\"AS8\t\u000b1\\\u0001\u0019\u0001\u001c\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012A\u001d\t\u0003gjt!\u0001\u001e=\u0011\u0005U|R\"\u0001<\u000b\u0005]T\u0012A\u0002\u001fs_>$h(\u0003\u0002z?\u00051\u0001K]3eK\u001aL!a\u001f?\u0003\rM#(/\u001b8h\u0015\tIx$\u0001\u0004fcV\fGn\u001d\u000b\u0003e}Da!!\u0001\u000e\u0001\u0004i\u0013A\u000192\u0003)!VM\\:pe.+\u0017p\u001d\t\u0003\u0017>\u0019\"aD\u000f\u0015\u0005\u0005\u0015\u0011a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$#'\u0006\u0005\u0002\u0010\u0005\u0015\u0012qEA\u0015+\t\t\tBK\u00023\u0003'Y#!!\u0006\u0011\t\u0005]\u0011\u0011E\u0007\u0003\u00033QA!a\u0007\u0002\u001e\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003?y\u0012AC1o]>$\u0018\r^5p]&!\u00111EA\r\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0003\u0006wE\u0011\r!\u000b\u0003\u0006\rF\u0011\r!\u000b\u0003\u0006QE\u0011\r!K\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001a\u0016\u0011\u0005=\u0012qGA\u001d\u0003w)\"!!\r+\t\u0005M\u00121\u0003\t\u0006=]\n)D\r\t\u0004M\u0005]B!B\u001e\u0013\u0005\u0004IC!\u0002$\u0013\u0005\u0004IC!\u0002\u0015\u0013\u0005\u0004I\u0003"
)
public class TensorKeys {
   private final Object tensor;
   private final boolean active;
   private final Function1 f;
   private final .less.colon.less ev;

   public static Function1 $lessinit$greater$default$3() {
      return TensorKeys$.MODULE$.$lessinit$greater$default$3();
   }

   public static boolean $lessinit$greater$default$2() {
      return TensorKeys$.MODULE$.$lessinit$greater$default$2();
   }

   private Object tensor() {
      return this.tensor;
   }

   public int size() {
      return ((TensorLike)this.ev.apply(this.tensor())).size();
   }

   public Iterator iterator() {
      return (this.active ? ((QuasiTensor)this.ev.apply(this.tensor())).activeKeysIterator() : ((QuasiTensor)this.ev.apply(this.tensor())).keysIterator()).filter(this.f);
   }

   public void foreach(final Function1 fn) {
      this.iterator().foreach(fn);
   }

   public TensorKeys filter(final Function1 p) {
      return this.withFilter(p);
   }

   public TensorKeys withFilter(final Function1 p) {
      return new TensorKeys(this.tensor(), this.active, (a) -> BoxesRunTime.boxToBoolean($anonfun$withFilter$1(this, p, a)), this.ev);
   }

   public String toString() {
      return this.iterator().mkString("TensorKeys(", ",", ")");
   }

   public boolean equals(final Object p1) {
      boolean var2;
      if (p1 instanceof TensorKeys) {
         TensorKeys var4 = (TensorKeys)p1;
         var2 = var4 == this || this.iterator().sameElements(var4.iterator());
      } else {
         var2 = false;
      }

      return var2;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$withFilter$1(final TensorKeys $this, final Function1 p$1, final Object a) {
      return BoxesRunTime.unboxToBoolean($this.f.apply(a)) && BoxesRunTime.unboxToBoolean(p$1.apply(a));
   }

   public TensorKeys(final Object tensor, final boolean active, final Function1 f, final .less.colon.less ev) {
      this.tensor = tensor;
      this.active = active;
      this.f = f;
      this.ev = ev;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
