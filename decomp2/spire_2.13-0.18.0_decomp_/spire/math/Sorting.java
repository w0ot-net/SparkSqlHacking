package spire.math;

import cats.kernel.Order;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q<Qa\u0002\u0005\t\u000251Qa\u0004\u0005\t\u0002AAQaF\u0001\u0005\u0002aAQ!G\u0001\u0005\u0006iAQaT\u0001\u0005\u0006ACQAX\u0001\u0005\u0006}CQ!\\\u0001\u0005\u00069\fqaU8si&twM\u0003\u0002\n\u0015\u0005!Q.\u0019;i\u0015\u0005Y\u0011!B:qSJ,7\u0001\u0001\t\u0003\u001d\u0005i\u0011\u0001\u0003\u0002\b'>\u0014H/\u001b8h'\t\t\u0011\u0003\u0005\u0002\u0013+5\t1CC\u0001\u0015\u0003\u0015\u00198-\u00197b\u0013\t12C\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00035\tAa]8siV\u00111\u0004\u000e\u000b\u00039)#2!\b\u0011B!\t\u0011b$\u0003\u0002 '\t!QK\\5u\u0011\u001d\t3!!AA\u0004\t\n1\"\u001a<jI\u0016t7-\u001a\u00132gA\u00191e\f\u001a\u000f\u0005\u0011bcBA\u0013+\u001d\t1\u0013&D\u0001(\u0015\tAC\"\u0001\u0004=e>|GOP\u0005\u0002\u0017%\u00111FC\u0001\bC2<WM\u0019:b\u0013\tic&A\u0004qC\u000e\\\u0017mZ3\u000b\u0005-R\u0011B\u0001\u00192\u0005\u0015y%\u000fZ3s\u0015\tic\u0006\u0005\u00024i1\u0001A!C\u001b\u0004A\u0003\u0005\tQ1\u00017\u0005\u0005\t\u0015CA\u001c;!\t\u0011\u0002(\u0003\u0002:'\t9aj\u001c;iS:<\u0007C\u0001\n<\u0013\ta4CA\u0002B]fD#\u0001\u000e \u0011\u0005Iy\u0014B\u0001!\u0014\u0005-\u0019\b/Z2jC2L'0\u001a3\t\u000f\t\u001b\u0011\u0011!a\u0002\u0007\u0006YQM^5eK:\u001cW\rJ\u00195!\r!uI\r\b\u0003\u000b\u001ak\u0011AC\u0005\u0003[)I!\u0001S%\u0003\u0011\rc\u0017m]:UC\u001eT!!\f\u0006\t\u000b-\u001b\u0001\u0019\u0001'\u0002\t\u0011\fG/\u0019\t\u0004%5\u0013\u0014B\u0001(\u0014\u0005\u0015\t%O]1z\u00035Ign]3si&|gnU8siV\u0011\u0011k\u0016\u000b\u0003%r#2!H*Z\u0011\u001d!F!!AA\u0004U\u000b1\"\u001a<jI\u0016t7-\u001a\u00132kA\u00191e\f,\u0011\u0005M:F!C\u001b\u0005A\u0003\u0005\tQ1\u00017Q\t9f\bC\u0004[\t\u0005\u0005\t9A.\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013G\u000e\t\u0004\t\u001e3\u0006\"B&\u0005\u0001\u0004i\u0006c\u0001\nN-\u0006IQ.\u001a:hKN{'\u000f^\u000b\u0003A\u001a$\"!Y6\u0015\u0007u\u0011\u0007\u000eC\u0004d\u000b\u0005\u0005\t9\u00013\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013g\u000e\t\u0004G=*\u0007CA\u001ag\t%)T\u0001)A\u0001\u0002\u000b\u0007a\u0007\u000b\u0002g}!9\u0011.BA\u0001\u0002\bQ\u0017aC3wS\u0012,gnY3%ca\u00022\u0001R$f\u0011\u0015YU\u00011\u0001m!\r\u0011R*Z\u0001\ncVL7m[*peR,\"a\\;\u0015\u0005ATHcA\u000fro\"9!OBA\u0001\u0002\b\u0019\u0018aC3wS\u0012,gnY3%ce\u00022aI\u0018u!\t\u0019T\u000fB\u00056\r\u0001\u0006\t\u0011!b\u0001m!\u0012QO\u0010\u0005\bq\u001a\t\t\u0011q\u0001z\u0003-)g/\u001b3f]\u000e,GE\r\u0019\u0011\u0007\u0011;E\u000fC\u0003L\r\u0001\u00071\u0010E\u0002\u0013\u001bR\u0004"
)
public final class Sorting {
   public static void quickSort(final Object data, final Order evidence$19, final ClassTag evidence$20) {
      Sorting$.MODULE$.quickSort(data, evidence$19, evidence$20);
   }

   public static void mergeSort(final Object data, final Order evidence$17, final ClassTag evidence$18) {
      Sorting$.MODULE$.mergeSort(data, evidence$17, evidence$18);
   }

   public static void insertionSort(final Object data, final Order evidence$15, final ClassTag evidence$16) {
      Sorting$.MODULE$.insertionSort(data, evidence$15, evidence$16);
   }

   public static void sort(final Object data, final Order evidence$13, final ClassTag evidence$14) {
      Sorting$.MODULE$.sort(data, evidence$13, evidence$14);
   }
}
