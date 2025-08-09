package org.apache.spark.sql.catalyst.plans.logical;

import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019<Qa\u0003\u0007\t\u0002n1Q!\b\u0007\t\u0002zAQaN\u0001\u0005\u0002aBq!O\u0001\u0002\u0002\u0013\u0005#\bC\u0004D\u0003\u0005\u0005I\u0011\u0001#\t\u000f!\u000b\u0011\u0011!C\u0001\u0013\"9q*AA\u0001\n\u0003\u0002\u0006bB,\u0002\u0003\u0003%\t\u0001\u0017\u0005\b;\u0006\t\t\u0011\"\u0011_\u0011\u001dy\u0016!!A\u0005B\u0001Dq!Y\u0001\u0002\u0002\u0013%!-\u0001\bQe>\u001cWm]:j]\u001e$\u0016.\\3\u000b\u00055q\u0011a\u00027pO&\u001c\u0017\r\u001c\u0006\u0003\u001fA\tQ\u0001\u001d7b]NT!!\u0005\n\u0002\u0011\r\fG/\u00197zgRT!a\u0005\u000b\u0002\u0007M\fHN\u0003\u0002\u0016-\u0005)1\u000f]1sW*\u0011q\u0003G\u0001\u0007CB\f7\r[3\u000b\u0003e\t1a\u001c:h\u0007\u0001\u0001\"\u0001H\u0001\u000e\u00031\u0011a\u0002\u0015:pG\u0016\u001c8/\u001b8h)&lWm\u0005\u0003\u0002?\u0015Z\u0003C\u0001\u0011$\u001b\u0005\t#B\u0001\u0012\u0013\u0003%\u0019HO]3b[&tw-\u0003\u0002%C\tAA+[7f\u001b>$W\r\u0005\u0002'S5\tqEC\u0001)\u0003\u0015\u00198-\u00197b\u0013\tQsEA\u0004Qe>$Wo\u0019;\u0011\u00051\"dBA\u00173\u001d\tq\u0013'D\u00010\u0015\t\u0001$$\u0001\u0004=e>|GOP\u0005\u0002Q%\u00111gJ\u0001\ba\u0006\u001c7.Y4f\u0013\t)dG\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u00024O\u00051A(\u001b8jiz\"\u0012aG\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003m\u0002\"\u0001P!\u000e\u0003uR!AP \u0002\t1\fgn\u001a\u0006\u0002\u0001\u0006!!.\u0019<b\u0013\t\u0011UH\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002\u000bB\u0011aER\u0005\u0003\u000f\u001e\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"AS'\u0011\u0005\u0019Z\u0015B\u0001'(\u0005\r\te.\u001f\u0005\b\u001d\u0016\t\t\u00111\u0001F\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\t\u0011\u000bE\u0002S+*k\u0011a\u0015\u0006\u0003)\u001e\n!bY8mY\u0016\u001cG/[8o\u0013\t16K\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGCA-]!\t1#,\u0003\u0002\\O\t9!i\\8mK\u0006t\u0007b\u0002(\b\u0003\u0003\u0005\rAS\u0001\tQ\u0006\u001c\bnQ8eKR\tQ)\u0001\u0005u_N#(/\u001b8h)\u0005Y\u0014\u0001D<sSR,'+\u001a9mC\u000e,G#A2\u0011\u0005q\"\u0017BA3>\u0005\u0019y%M[3di\u0002"
)
public final class ProcessingTime {
   public static String toString() {
      return ProcessingTime$.MODULE$.toString();
   }

   public static int hashCode() {
      return ProcessingTime$.MODULE$.hashCode();
   }

   public static boolean canEqual(final Object x$1) {
      return ProcessingTime$.MODULE$.canEqual(x$1);
   }

   public static Iterator productIterator() {
      return ProcessingTime$.MODULE$.productIterator();
   }

   public static Object productElement(final int x$1) {
      return ProcessingTime$.MODULE$.productElement(x$1);
   }

   public static int productArity() {
      return ProcessingTime$.MODULE$.productArity();
   }

   public static String productPrefix() {
      return ProcessingTime$.MODULE$.productPrefix();
   }

   public static Iterator productElementNames() {
      return ProcessingTime$.MODULE$.productElementNames();
   }

   public static String productElementName(final int n) {
      return ProcessingTime$.MODULE$.productElementName(n);
   }
}
