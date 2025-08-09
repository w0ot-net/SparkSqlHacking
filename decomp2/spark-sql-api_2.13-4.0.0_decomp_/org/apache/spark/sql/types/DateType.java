package org.apache.spark.sql.types;

import org.apache.spark.annotation.Stable;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@Stable
@ScalaSignature(
   bytes = "\u0006\u0005I4Aa\u0004\t\u00017!)\u0001\u0005\u0001C\u0005C!)1\u0005\u0001C!I!11\u0006\u0001C!)1:Q\u0001\u000e\t\t\u0002V2Qa\u0004\t\t\u0002ZBQ\u0001I\u0003\u0005\u0002\u0019CqaR\u0003\u0002\u0002\u0013\u0005\u0003\nC\u0004R\u000b\u0005\u0005I\u0011\u0001\u0013\t\u000fI+\u0011\u0011!C\u0001'\"9\u0011,BA\u0001\n\u0003R\u0006bB1\u0006\u0003\u0003%\tA\u0019\u0005\bO\u0016\t\t\u0011\"\u0011i\u0011\u001dIW!!A\u0005B)Dqa[\u0003\u0002\u0002\u0013%AN\u0001\u0005ECR,G+\u001f9f\u0015\t\t\"#A\u0003usB,7O\u0003\u0002\u0014)\u0005\u00191/\u001d7\u000b\u0005U1\u0012!B:qCJ\\'BA\f\u0019\u0003\u0019\t\u0007/Y2iK*\t\u0011$A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u00019A\u0011QDH\u0007\u0002!%\u0011q\u0004\u0005\u0002\r\t\u0006$X\r^5nKRK\b/Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\t\u0002\"!\b\u0001\u0002\u0017\u0011,g-Y;miNK'0Z\u000b\u0002KA\u0011a%K\u0007\u0002O)\t\u0001&A\u0003tG\u0006d\u0017-\u0003\u0002+O\t\u0019\u0011J\u001c;\u0002\u0015\u0005\u001ch*\u001e7mC\ndW-F\u0001#Q\t\u0001a\u0006\u0005\u00020e5\t\u0001G\u0003\u00022)\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005M\u0002$AB*uC\ndW-\u0001\u0005ECR,G+\u001f9f!\tiRa\u0005\u0003\u0006E]R\u0004C\u0001\u00149\u0013\tItEA\u0004Qe>$Wo\u0019;\u0011\u0005m\u001aeB\u0001\u001fB\u001d\ti\u0004)D\u0001?\u0015\ty$$\u0001\u0004=e>|GOP\u0005\u0002Q%\u0011!iJ\u0001\ba\u0006\u001c7.Y4f\u0013\t!UI\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002COQ\tQ'A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002\u0013B\u0011!jT\u0007\u0002\u0017*\u0011A*T\u0001\u0005Y\u0006twMC\u0001O\u0003\u0011Q\u0017M^1\n\u0005A[%AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005Q;\u0006C\u0001\u0014V\u0013\t1vEA\u0002B]fDq\u0001W\u0005\u0002\u0002\u0003\u0007Q%A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u00027B\u0019Al\u0018+\u000e\u0003uS!AX\u0014\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002a;\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\t\u0019g\r\u0005\u0002'I&\u0011Qm\n\u0002\b\u0005>|G.Z1o\u0011\u001dA6\"!AA\u0002Q\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002K\u0005AAo\\*ue&tw\rF\u0001J\u000319(/\u001b;f%\u0016\u0004H.Y2f)\u0005i\u0007C\u0001&o\u0013\ty7J\u0001\u0004PE*,7\r\u001e\u0015\u0003\u000b9B#\u0001\u0002\u0018"
)
public class DateType extends DatetimeType {
   public static boolean canEqual(final Object x$1) {
      return DateType$.MODULE$.canEqual(x$1);
   }

   public static Iterator productIterator() {
      return DateType$.MODULE$.productIterator();
   }

   public static Object productElement(final int x$1) {
      return DateType$.MODULE$.productElement(x$1);
   }

   public static int productArity() {
      return DateType$.MODULE$.productArity();
   }

   public static String productPrefix() {
      return DateType$.MODULE$.productPrefix();
   }

   public static Iterator productElementNames() {
      return DateType$.MODULE$.productElementNames();
   }

   public static String productElementName(final int n) {
      return DateType$.MODULE$.productElementName(n);
   }

   public int defaultSize() {
      return 4;
   }

   public DateType asNullable() {
      return this;
   }
}
