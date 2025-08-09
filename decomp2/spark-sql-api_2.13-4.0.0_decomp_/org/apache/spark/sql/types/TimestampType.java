package org.apache.spark.sql.types;

import org.apache.spark.annotation.Stable;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@Stable
@ScalaSignature(
   bytes = "\u0006\u0005I4Aa\u0004\t\u00017!)\u0001\u0005\u0001C\u0005C!)1\u0005\u0001C!I!11\u0006\u0001C!)1:Q\u0001\u000e\t\t\u0002V2Qa\u0004\t\t\u0002ZBQ\u0001I\u0003\u0005\u0002\u0019CqaR\u0003\u0002\u0002\u0013\u0005\u0003\nC\u0004R\u000b\u0005\u0005I\u0011\u0001\u0013\t\u000fI+\u0011\u0011!C\u0001'\"9\u0011,BA\u0001\n\u0003R\u0006bB1\u0006\u0003\u0003%\tA\u0019\u0005\bO\u0016\t\t\u0011\"\u0011i\u0011\u001dIW!!A\u0005B)Dqa[\u0003\u0002\u0002\u0013%ANA\u0007US6,7\u000f^1naRK\b/\u001a\u0006\u0003#I\tQ\u0001^=qKNT!a\u0005\u000b\u0002\u0007M\fHN\u0003\u0002\u0016-\u0005)1\u000f]1sW*\u0011q\u0003G\u0001\u0007CB\f7\r[3\u000b\u0003e\t1a\u001c:h\u0007\u0001\u0019\"\u0001\u0001\u000f\u0011\u0005uqR\"\u0001\t\n\u0005}\u0001\"\u0001\u0004#bi\u0016$\u0018.\\3UsB,\u0017A\u0002\u001fj]&$h\bF\u0001#!\ti\u0002!A\u0006eK\u001a\fW\u000f\u001c;TSj,W#A\u0013\u0011\u0005\u0019JS\"A\u0014\u000b\u0003!\nQa]2bY\u0006L!AK\u0014\u0003\u0007%sG/\u0001\u0006bg:+H\u000e\\1cY\u0016,\u0012A\t\u0015\u0003\u00019\u0002\"a\f\u001a\u000e\u0003AR!!\r\u000b\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u00024a\t11\u000b^1cY\u0016\fQ\u0002V5nKN$\u0018-\u001c9UsB,\u0007CA\u000f\u0006'\u0011)!e\u000e\u001e\u0011\u0005\u0019B\u0014BA\u001d(\u0005\u001d\u0001&o\u001c3vGR\u0004\"aO\"\u000f\u0005q\neBA\u001fA\u001b\u0005q$BA \u001b\u0003\u0019a$o\\8u}%\t\u0001&\u0003\u0002CO\u00059\u0001/Y2lC\u001e,\u0017B\u0001#F\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\u0011u\u0005F\u00016\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\t\u0011\n\u0005\u0002K\u001f6\t1J\u0003\u0002M\u001b\u0006!A.\u00198h\u0015\u0005q\u0015\u0001\u00026bm\u0006L!\u0001U&\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"\u0001V,\u0011\u0005\u0019*\u0016B\u0001,(\u0005\r\te.\u001f\u0005\b1&\t\t\u00111\u0001&\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\t1\fE\u0002]?Rk\u0011!\u0018\u0006\u0003=\u001e\n!bY8mY\u0016\u001cG/[8o\u0013\t\u0001WL\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGCA2g!\t1C-\u0003\u0002fO\t9!i\\8mK\u0006t\u0007b\u0002-\f\u0003\u0003\u0005\r\u0001V\u0001\tQ\u0006\u001c\bnQ8eKR\tQ%\u0001\u0005u_N#(/\u001b8h)\u0005I\u0015\u0001D<sSR,'+\u001a9mC\u000e,G#A7\u0011\u0005)s\u0017BA8L\u0005\u0019y%M[3di\"\u0012QA\f\u0015\u0003\t9\u0002"
)
public class TimestampType extends DatetimeType {
   public static boolean canEqual(final Object x$1) {
      return TimestampType$.MODULE$.canEqual(x$1);
   }

   public static Iterator productIterator() {
      return TimestampType$.MODULE$.productIterator();
   }

   public static Object productElement(final int x$1) {
      return TimestampType$.MODULE$.productElement(x$1);
   }

   public static int productArity() {
      return TimestampType$.MODULE$.productArity();
   }

   public static String productPrefix() {
      return TimestampType$.MODULE$.productPrefix();
   }

   public static Iterator productElementNames() {
      return TimestampType$.MODULE$.productElementNames();
   }

   public static String productElementName(final int n) {
      return TimestampType$.MODULE$.productElementName(n);
   }

   public int defaultSize() {
      return 8;
   }

   public TimestampType asNullable() {
      return this;
   }
}
