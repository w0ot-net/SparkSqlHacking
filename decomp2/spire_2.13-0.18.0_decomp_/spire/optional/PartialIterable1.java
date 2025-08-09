package spire.optional;

import cats.kernel.Group;
import scala.collection.Factory;
import scala.reflect.ScalaSignature;
import spire.algebra.partial.Groupoid;

@ScalaSignature(
   bytes = "\u0006\u0005U3qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0015\u0001\u0011\u0005Q\u0003C\u0003\u001a\u0001\u0011\r!D\u0001\tQCJ$\u0018.\u00197Ji\u0016\u0014\u0018M\u00197fc)\u0011QAB\u0001\t_B$\u0018n\u001c8bY*\tq!A\u0003ta&\u0014Xm\u0001\u0001\u0014\u0007\u0001Q\u0001\u0003\u0005\u0002\f\u001d5\tABC\u0001\u000e\u0003\u0015\u00198-\u00197b\u0013\tyAB\u0001\u0004B]f\u0014VM\u001a\t\u0003#Ii\u0011\u0001B\u0005\u0003'\u0011\u0011\u0001\u0003U1si&\fG.\u0013;fe\u0006\u0014G.\u001a\u0019\u0002\r\u0011Jg.\u001b;%)\u00051\u0002CA\u0006\u0018\u0013\tABB\u0001\u0003V]&$\u0018\u0001E%uKJ\f'\r\\3He>,\bo\\5e+\rYrH\n\u000b\u00049\u0001\u0003\u0006cA\u000f#I5\taD\u0003\u0002 A\u00059\u0001/\u0019:uS\u0006d'BA\u0011\u0007\u0003\u001d\tGnZ3ce\u0006L!a\t\u0010\u0003\u0011\u001d\u0013x.\u001e9pS\u0012\u00042!\n\u0014?\u0019\u0001!Qa\n\u0002C\u0002!\u0012!aQ\"\u0016\u0005%\"\u0014C\u0001\u0016.!\tY1&\u0003\u0002-\u0019\t9aj\u001c;iS:<\u0007#\u0002\u00182gijT\"A\u0018\u000b\u0005Ab\u0011AC2pY2,7\r^5p]&\u0011!g\f\u0002\f\u0013R,'/\u00192mK>\u00038\u000f\u0005\u0002&i\u0011)QG\nb\u0001m\t\t\u0011)\u0005\u0002+oA\u00111\u0002O\u0005\u0003s1\u00111!\u00118z!\tq3(\u0003\u0002=_\tA\u0011\n^3sC\ndW\rE\u0002&MM\u0002\"!J \u0005\u000bU\u0012!\u0019\u0001\u001c\t\u000f\u0005\u0013\u0011\u0011!a\u0002\u0005\u0006QQM^5eK:\u001cW\r\n\u001a\u0011\u0007\rkeH\u0004\u0002E\u0017:\u0011QI\u0013\b\u0003\r&k\u0011a\u0012\u0006\u0003\u0011\"\ta\u0001\u0010:p_Rt\u0014\"A\u0004\n\u0005\u00052\u0011B\u0001'!\u0003\u001d\u0001\u0018mY6bO\u0016L!AT(\u0003\u000b\u001d\u0013x.\u001e9\u000b\u00051\u0003\u0003\"B)\u0003\u0001\b\u0011\u0016aA2cMB!af\u0015 %\u0013\t!vFA\u0004GC\u000e$xN]="
)
public interface PartialIterable1 extends PartialIterable0 {
   // $FF: synthetic method
   static Groupoid IterableGroupoid$(final PartialIterable1 $this, final Group evidence$2, final Factory cbf) {
      return $this.IterableGroupoid(evidence$2, cbf);
   }

   default Groupoid IterableGroupoid(final Group evidence$2, final Factory cbf) {
      return new IterableGroupoid(cbf, evidence$2);
   }

   static void $init$(final PartialIterable1 $this) {
   }
}
