package breeze.linalg.support;

import breeze.linalg.QuasiTensor;
import breeze.linalg.TensorLike;
import java.lang.invoke.SerializedLambda;
import scala.;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ud\u0001\u0002\u000b\u0016\u0001qA\u0001\u0002\n\u0001\u0003\u0006\u0004%I!\n\u0005\tc\u0001\u0011\t\u0011)A\u0005M!A!\u0007\u0001B\u0001B\u0003%1\u0007\u0003\u00057\u0001\t\u0005\t\u0015!\u00038\u0011!\u0019\u0005A!A!\u0002\u0017!\u0005\"B&\u0001\t\u0003a\u0005\"\u0002+\u0001\t\u0003)\u0006\"B-\u0001\t\u0003Q\u0006\"B1\u0001\t\u0003\u0011\u0007\"B1\u0001\t\u0003i\u0007\"B;\u0001\t\u00031\b\"B=\u0001\t\u0003Q\b\"\u0002?\u0001\t\u0003j\bbBA\n\u0001\u0011\u0005\u0013Q\u0003\u0005\b\u00037\u0001A\u0011AA\u000f\u000f%\t)%FA\u0001\u0012\u0003\t9E\u0002\u0005\u0015+\u0005\u0005\t\u0012AA%\u0011\u0019Y\u0015\u0003\"\u0001\u0002L!I\u0011QJ\t\u0012\u0002\u0013\u0005\u0011q\n\u0002\f)\u0016t7o\u001c:QC&\u00148O\u0003\u0002\u0017/\u000591/\u001e9q_J$(B\u0001\r\u001a\u0003\u0019a\u0017N\\1mO*\t!$\u0001\u0004ce\u0016,'0Z\u0002\u0001+\u0011ib(\u0011\u0015\u0014\u0005\u0001q\u0002CA\u0010#\u001b\u0005\u0001#\"A\u0011\u0002\u000bM\u001c\u0017\r\\1\n\u0005\r\u0002#AB!osJ+g-\u0001\u0004uK:\u001cxN]\u000b\u0002MA\u0011q\u0005\u000b\u0007\u0001\t\u0019I\u0003\u0001\"b\u0001U\t!A\u000b[5t#\tYc\u0006\u0005\u0002 Y%\u0011Q\u0006\t\u0002\b\u001d>$\b.\u001b8h!\tyr&\u0003\u00021A\t\u0019\u0011I\\=\u0002\u000fQ,gn]8sA\u00051\u0011m\u0019;jm\u0016\u0004\"a\b\u001b\n\u0005U\u0002#a\u0002\"p_2,\u0017M\\\u0001\u0002MB!q\u0004\u000f\u001e4\u0013\tI\u0004EA\u0005Gk:\u001cG/[8ocA!qdO\u001fA\u0013\ta\u0004E\u0001\u0004UkBdWM\r\t\u0003Oy\"Qa\u0010\u0001C\u0002)\u0012\u0011a\u0013\t\u0003O\u0005#QA\u0011\u0001C\u0002)\u0012\u0011AV\u0001\u0003KZ\u0004BaH#'\u000f&\u0011a\t\t\u0002\u0011I1,7o\u001d\u0013d_2|g\u000e\n7fgN\u0004B\u0001S%>\u00016\tq#\u0003\u0002K/\t1A+\u001a8t_J\fa\u0001P5oSRtD\u0003B'R%N#\"A\u0014)\u0011\u000b=\u0003Q\b\u0011\u0014\u000e\u0003UAQa\u0011\u0004A\u0004\u0011CQ\u0001\n\u0004A\u0002\u0019BQA\r\u0004A\u0002MBqA\u000e\u0004\u0011\u0002\u0003\u0007q'\u0001\u0003tSj,W#\u0001,\u0011\u0005}9\u0016B\u0001-!\u0005\rIe\u000e^\u0001\tSR,'/\u0019;peV\t1\fE\u0002]?jj\u0011!\u0018\u0006\u0003=\u0002\n!bY8mY\u0016\u001cG/[8o\u0013\t\u0001WL\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003\u001d1wN]3bG\",\"aY6\u0015\u0005\u0011<\u0007CA\u0010f\u0013\t1\u0007E\u0001\u0003V]&$\b\"\u00025\n\u0001\u0004I\u0017A\u00014o!\u0011y\u0002H\u000f6\u0011\u0005\u001dZG!\u00027\n\u0005\u0004Q#!A+\u0016\u00059$HC\u00013p\u0011\u0015A'\u00021\u0001q!\u0015y\u0012/\u0010!t\u0013\t\u0011\bEA\u0005Gk:\u001cG/[8oeA\u0011q\u0005\u001e\u0003\u0006Y*\u0011\rAK\u0001\u0007M&dG/\u001a:\u0015\u00059;\b\"\u0002=\f\u0001\u00049\u0014!\u00019\u0002\u0015]LG\u000f\u001b$jYR,'\u000f\u0006\u0002Ow\")\u0001\u0010\u0004a\u0001o\u0005AAo\\*ue&tw\rF\u0001\u007f!\ry\u0018Q\u0002\b\u0005\u0003\u0003\tI\u0001E\u0002\u0002\u0004\u0001j!!!\u0002\u000b\u0007\u0005\u001d1$\u0001\u0004=e>|GOP\u0005\u0004\u0003\u0017\u0001\u0013A\u0002)sK\u0012,g-\u0003\u0003\u0002\u0010\u0005E!AB*ue&twMC\u0002\u0002\f\u0001\na!Z9vC2\u001cHcA\u001a\u0002\u0018!1\u0011\u0011\u0004\bA\u00029\n!\u0001]\u0019\u0002\u00075\f\u0007/\u0006\u0005\u0002 \u0005U\u0012QHA\u0013)\u0011\t\t#!\u0011\u0015\t\u0005\r\u0012\u0011\u0006\t\u0004O\u0005\u0015BABA\u0014\u001f\t\u0007!F\u0001\u0003UQ\u0006$\bbBA\u0016\u001f\u0001\u000f\u0011QF\u0001\u0003E\u001a\u00042bTA\u0018\u0003gi\u0004)a\u000f\u0002$%\u0019\u0011\u0011G\u000b\u0003'\r\u000bg.T1q\u0017\u0016Lh+\u00197vKB\u000b\u0017N]:\u0011\u0007\u001d\n)\u0004B\u0004\u00028=\u0011\r!!\u000f\u0003\u0005Q#\u0016C\u0001\u0014/!\r9\u0013Q\b\u0003\u0007\u0003\u007fy!\u0019\u0001\u0016\u0003\u0003=Ca\u0001[\bA\u0002\u0005\r\u0003#B\u00109u\u0005m\u0012a\u0003+f]N|'\u000fU1jeN\u0004\"aT\t\u0014\u0005EqBCAA$\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%gUA\u0011\u0011KA.\u0003?\n\u0019(\u0006\u0002\u0002T)\"\u0011QKA1!\u0015y\u0002(a\u00164!\u0019y2(!\u0017\u0002^A\u0019q%a\u0017\u0005\u000b}\u001a\"\u0019\u0001\u0016\u0011\u0007\u001d\ny\u0006B\u0003C'\t\u0007!f\u000b\u0002\u0002dA!\u0011QMA8\u001b\t\t9G\u0003\u0003\u0002j\u0005-\u0014!C;oG\",7m[3e\u0015\r\ti\u0007I\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA9\u0003O\u0012\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\t\u0015I3C1\u0001+\u0001"
)
public class TensorPairs {
   private final Object tensor;
   private final boolean active;
   private final Function1 f;
   private final .less.colon.less ev;

   public static Function1 $lessinit$greater$default$3() {
      return TensorPairs$.MODULE$.$lessinit$greater$default$3();
   }

   private Object tensor() {
      return this.tensor;
   }

   public int size() {
      return ((TensorLike)this.ev.apply(this.tensor())).size();
   }

   public Iterator iterator() {
      return (this.active ? ((QuasiTensor)this.ev.apply(this.tensor())).activeIterator() : ((QuasiTensor)this.ev.apply(this.tensor())).iterator()).filter(this.f);
   }

   public void foreach(final Function1 fn) {
      this.iterator().foreach(fn);
   }

   public void foreach(final Function2 fn) {
      this.iterator().foreach((x0$1) -> {
         if (x0$1 != null) {
            Object a = x0$1._1();
            Object b = x0$1._2();
            Object var2 = fn.apply(a, b);
            return var2;
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   public TensorPairs filter(final Function1 p) {
      return this.withFilter(p);
   }

   public TensorPairs withFilter(final Function1 p) {
      return new TensorPairs(this.tensor(), this.active, (a) -> BoxesRunTime.boxToBoolean($anonfun$withFilter$1(this, p, a)), this.ev);
   }

   public String toString() {
      return this.iterator().mkString("TensorKeys(", ",", ")");
   }

   public boolean equals(final Object p1) {
      boolean var2;
      if (p1 instanceof TensorPairs) {
         TensorPairs var4 = (TensorPairs)p1;
         var2 = var4 == this || this.iterator().sameElements(var4.iterator());
      } else {
         var2 = false;
      }

      return var2;
   }

   public Object map(final Function1 fn, final CanMapKeyValuePairs bf) {
      return ((TensorLike)this.ev.apply(this.tensor())).mapPairs((k, v) -> fn.apply(new Tuple2(k, v)), bf);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$withFilter$1(final TensorPairs $this, final Function1 p$1, final Tuple2 a) {
      return BoxesRunTime.unboxToBoolean($this.f.apply(a)) && BoxesRunTime.unboxToBoolean(p$1.apply(a));
   }

   public TensorPairs(final Object tensor, final boolean active, final Function1 f, final .less.colon.less ev) {
      this.tensor = tensor;
      this.active = active;
      this.f = f;
      this.ev = ev;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
