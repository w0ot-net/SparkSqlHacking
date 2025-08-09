package breeze.optimize.linear;

import scala.Tuple2;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005;Q!\u0002\u0004\t\u000251Qa\u0004\u0004\t\u0002AAQAG\u0001\u0005\u0002mAQ\u0001H\u0001\u0005\u0002uAQAO\u0001\u0005\nm\n1bS;i]6+hn\u001b:fg*\u0011q\u0001C\u0001\u0007Y&tW-\u0019:\u000b\u0005%Q\u0011\u0001C8qi&l\u0017N_3\u000b\u0003-\taA\u0019:fKj,7\u0001\u0001\t\u0003\u001d\u0005i\u0011A\u0002\u0002\f\u0017VDg.T;oWJ,7oE\u0002\u0002#]\u0001\"AE\u000b\u000e\u0003MQ\u0011\u0001F\u0001\u0006g\u000e\fG.Y\u0005\u0003-M\u0011a!\u00118z%\u00164\u0007C\u0001\b\u0019\u0013\tIbAA\tCSB\f'\u000f^5uK6\u000bGo\u00195j]\u001e\fa\u0001P5oSRtD#A\u0007\u0002\u001f\u0015DHO]1di6\u000bGo\u00195j]\u001e$\"A\b\u0018\u0011\tIy\u0012eK\u0005\u0003AM\u0011a\u0001V;qY\u0016\u0014\u0004c\u0001\u0012&Q9\u0011!cI\u0005\u0003IM\tq\u0001]1dW\u0006<W-\u0003\u0002'O\tQ\u0011J\u001c3fq\u0016$7+Z9\u000b\u0005\u0011\u001a\u0002C\u0001\n*\u0013\tQ3CA\u0002J]R\u0004\"A\u0005\u0017\n\u00055\u001a\"A\u0002#pk\ndW\rC\u00030\u0007\u0001\u0007\u0001'A\u0003d_N$8\u000fE\u00022oer!AM\u0012\u000f\u0005M2T\"\u0001\u001b\u000b\u0005Ub\u0011A\u0002\u001fs_>$h(C\u0001\u0015\u0013\tAtEA\u0002TKF\u00042!M\u001c,\u0003%\u0001\u0018\rZ'biJL\u0007\u0010\u0006\u0002=\u0001B\u0019!#P \n\u0005y\u001a\"!B!se\u0006L\bc\u0001\n>W!)q\u0006\u0002a\u0001a\u0001"
)
public final class KuhnMunkres {
   public static Tuple2 extractMatching(final Seq costs) {
      return KuhnMunkres$.MODULE$.extractMatching(costs);
   }
}
