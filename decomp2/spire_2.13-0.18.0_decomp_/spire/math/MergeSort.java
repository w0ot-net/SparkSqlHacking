package spire.math;

import cats.kernel.Order;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M<Qa\u0002\u0005\t\u000251Qa\u0004\u0005\t\u0002AAQAG\u0001\u0005\u0002mAQ\u0001H\u0001\u0005\u0006uAQ!J\u0001\u0005\u0006uAQaJ\u0001\u0005\u0006!BQ!X\u0001\u0005\u0006y\u000b\u0011\"T3sO\u0016\u001cvN\u001d;\u000b\u0005%Q\u0011\u0001B7bi\"T\u0011aC\u0001\u0006gBL'/Z\u0002\u0001!\tq\u0011!D\u0001\t\u0005%iUM]4f'>\u0014HoE\u0002\u0002#]\u0001\"AE\u000b\u000e\u0003MQ\u0011\u0001F\u0001\u0006g\u000e\fG.Y\u0005\u0003-M\u0011a!\u00118z%\u00164\u0007C\u0001\b\u0019\u0013\tI\u0002B\u0001\u0003T_J$\u0018A\u0002\u001fj]&$h\bF\u0001\u000e\u0003)\u0019H/\u0019:u/&$G\u000f[\u000b\u0002=A\u0011!cH\u0005\u0003AM\u00111!\u00138uQ\t\u0019!\u0005\u0005\u0002\u0013G%\u0011Ae\u0005\u0002\u0007S:d\u0017N\\3\u0002\u0013M$\u0018M\u001d;Ti\u0016\u0004\bF\u0001\u0003#\u0003\u0011\u0019xN\u001d;\u0016\u0005%\u0012EC\u0001\u0016Y)\rYcf\u0014\t\u0003%1J!!L\n\u0003\tUs\u0017\u000e\u001e\u0005\b_\u0015\t\t\u0011q\u00011\u0003))g/\u001b3f]\u000e,Ge\u000e\t\u0004cu\u0002eB\u0001\u001a;\u001d\t\u0019\u0004H\u0004\u00025o5\tQG\u0003\u00027\u0019\u00051AH]8pizJ\u0011aC\u0005\u0003s)\tq!\u00197hK\n\u0014\u0018-\u0003\u0002<y\u00059\u0001/Y2lC\u001e,'BA\u001d\u000b\u0013\tqtHA\u0003Pe\u0012,'O\u0003\u0002<yA\u0011\u0011I\u0011\u0007\u0001\t%\u0019U\u0001)A\u0001\u0002\u000b\u0007AIA\u0001B#\t)\u0005\n\u0005\u0002\u0013\r&\u0011qi\u0005\u0002\b\u001d>$\b.\u001b8h!\t\u0011\u0012*\u0003\u0002K'\t\u0019\u0011I\\=)\u0005\tc\u0005C\u0001\nN\u0013\tq5CA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0007b\u0002)\u0006\u0003\u0003\u0005\u001d!U\u0001\u000bKZLG-\u001a8dK\u0012B\u0004c\u0001*V\u0001:\u00111\u000bV\u0007\u0002\u0015%\u00111HC\u0005\u0003-^\u0013\u0001b\u00117bgN$\u0016m\u001a\u0006\u0003w)AQ!W\u0003A\u0002i\u000bA\u0001Z1uCB\u0019!c\u0017!\n\u0005q\u001b\"!B!se\u0006L\u0018!B7fe\u001e,WCA0f)\u0019\u0001wM\u001b7oaR\u00111&\u0019\u0005\u0006E\u001a\u0001\u001daY\u0001\u0002_B\u0019\u0011'\u00103\u0011\u0005\u0005+G!C\"\u0007A\u0003\u0005\tQ1\u0001EQ\t)G\nC\u0003i\r\u0001\u0007\u0011.\u0001\u0002j]B\u0019!c\u00173\t\u000b-4\u0001\u0019A5\u0002\u0007=,H\u000fC\u0003n\r\u0001\u0007a$A\u0003ti\u0006\u0014H\u000fC\u0003p\r\u0001\u0007a$A\u0002nS\u0012DQ!\u001d\u0004A\u0002y\t1!\u001a8eQ\t1!\u0005"
)
public final class MergeSort {
   public static void merge(final Object in, final Object out, final int start, final int mid, final int end, final Order o) {
      MergeSort$.MODULE$.merge(in, out, start, mid, end, o);
   }

   public static void sort(final Object data, final Order evidence$7, final ClassTag evidence$8) {
      MergeSort$.MODULE$.sort(data, evidence$7, evidence$8);
   }

   public static int startStep() {
      return MergeSort$.MODULE$.startStep();
   }

   public static int startWidth() {
      return MergeSort$.MODULE$.startWidth();
   }
}
