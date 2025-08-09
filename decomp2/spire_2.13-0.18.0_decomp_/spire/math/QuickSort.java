package spire.math;

import cats.kernel.Order;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y<Qa\u0002\u0005\t\u000251Qa\u0004\u0005\t\u0002AAQaF\u0001\u0005\u0002aAQ!G\u0001\u0005\u0006iAQAI\u0001\u0005\u0006\rBQ\u0001W\u0001\u0005\u0006eCQa[\u0001\u0005\u00061\f\u0011\"U;jG.\u001cvN\u001d;\u000b\u0005%Q\u0011\u0001B7bi\"T\u0011aC\u0001\u0006gBL'/Z\u0002\u0001!\tq\u0011!D\u0001\t\u0005%\tV/[2l'>\u0014Ho\u0005\u0002\u0002#A\u0011!#F\u0007\u0002')\tA#A\u0003tG\u0006d\u0017-\u0003\u0002\u0017'\t1\u0011I\\=SK\u001a\fa\u0001P5oSRtD#A\u0007\u0002\u000b1LW.\u001b;\u0016\u0003m\u0001\"A\u0005\u000f\n\u0005u\u0019\"aA%oi\"\u00121a\b\t\u0003%\u0001J!!I\n\u0003\r%tG.\u001b8f\u0003\u0011\u0019xN\u001d;\u0016\u0005\u0011jDCA\u0013T)\r1\u0013F\u0013\t\u0003%\u001dJ!\u0001K\n\u0003\tUs\u0017\u000e\u001e\u0005\bU\u0011\t\t\u0011q\u0001,\u0003))g/\u001b3f]\u000e,G%\u000f\t\u0004YaZdBA\u00176\u001d\tq3G\u0004\u00020e5\t\u0001G\u0003\u00022\u0019\u00051AH]8pizJ\u0011aC\u0005\u0003i)\tq!\u00197hK\n\u0014\u0018-\u0003\u00027o\u00059\u0001/Y2lC\u001e,'B\u0001\u001b\u000b\u0013\tI$HA\u0003Pe\u0012,'O\u0003\u00027oA\u0011A(\u0010\u0007\u0001\t%qD\u0001)A\u0001\u0002\u000b\u0007qHA\u0001B#\t\u00015\t\u0005\u0002\u0013\u0003&\u0011!i\u0005\u0002\b\u001d>$\b.\u001b8h!\t\u0011B)\u0003\u0002F'\t\u0019\u0011I\\=)\u0005u:\u0005C\u0001\nI\u0013\tI5CA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0007bB&\u0005\u0003\u0003\u0005\u001d\u0001T\u0001\fKZLG-\u001a8dK\u0012\n\u0004\u0007E\u0002N!nr!AT(\u000e\u0003)I!A\u000e\u0006\n\u0005E\u0013&\u0001C\"mCN\u001cH+Y4\u000b\u0005YR\u0001\"\u0002+\u0005\u0001\u0004)\u0016\u0001\u00023bi\u0006\u00042A\u0005,<\u0013\t96CA\u0003BeJ\f\u00170A\u0003rg>\u0014H/\u0006\u0002[AR!1,Z4j)\r1CL\u0019\u0005\u0006;\u0016\u0001\u001dAX\u0001\u0002_B\u0019A\u0006O0\u0011\u0005q\u0002G!\u0003 \u0006A\u0003\u0005\tQ1\u0001@Q\t\u0001w\tC\u0003d\u000b\u0001\u000fA-\u0001\u0002diB\u0019Q\nU0\t\u000bQ+\u0001\u0019\u00014\u0011\u0007I1v\fC\u0003i\u000b\u0001\u00071$A\u0003ti\u0006\u0014H\u000fC\u0003k\u000b\u0001\u00071$A\u0002f]\u0012\f\u0011\u0002]1si&$\u0018n\u001c8\u0016\u00055\u001cH#\u00028yundHcA\u000epk\"9\u0001OBA\u0001\u0002\b\t\u0018aC3wS\u0012,gnY3%cE\u00022!\u0014)s!\ta4\u000fB\u0005?\r\u0001\u0006\t\u0011!b\u0001\u007f!\u00121o\u0012\u0005\bm\u001a\t\t\u0011q\u0001x\u0003-)g/\u001b3f]\u000e,G%\r\u001a\u0011\u00071B$\u000fC\u0003U\r\u0001\u0007\u0011\u0010E\u0002\u0013-JDQ\u0001\u001b\u0004A\u0002mAQA\u001b\u0004A\u0002mAQ! \u0004A\u0002m\t!\u0002]5w_RLe\u000eZ3y\u0001"
)
public final class QuickSort {
   public static int partition(final Object data, final int start, final int end, final int pivotIndex, final ClassTag evidence$11, final Order evidence$12) {
      return QuickSort$.MODULE$.partition(data, start, end, pivotIndex, evidence$11, evidence$12);
   }

   public static void qsort(final Object data, final int start, final int end, final Order o, final ClassTag ct) {
      QuickSort$.MODULE$.qsort(data, start, end, o, ct);
   }

   public static void sort(final Object data, final Order evidence$9, final ClassTag evidence$10) {
      QuickSort$.MODULE$.sort(data, evidence$9, evidence$10);
   }

   public static int limit() {
      return QuickSort$.MODULE$.limit();
   }
}
