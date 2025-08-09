package org.apache.spark.sql.types;

import org.apache.spark.annotation.Stable;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@Stable
@ScalaSignature(
   bytes = "\u0006\u0005q4A\u0001E\t\u00019!)\u0011\u0005\u0001C\u0005E!)A\u0005\u0001C!K!)A\u0006\u0001C![!1\u0011\b\u0001C!+i:QAQ\t\t\u0002\u000e3Q\u0001E\t\t\u0002\u0012CQ!\t\u0004\u0005\u0002ECqA\u0015\u0004\u0002\u0002\u0013\u00053\u000bC\u0004\\\r\u0005\u0005I\u0011A\u0013\t\u000fq3\u0011\u0011!C\u0001;\"91MBA\u0001\n\u0003\"\u0007bB6\u0007\u0003\u0003%\t\u0001\u001c\u0005\bc\u001a\t\t\u0011\"\u0011s\u0011\u001d\u0019h!!A\u0005BQDq!\u001e\u0004\u0002\u0002\u0013%aO\u0001\u000bDC2,g\u000eZ1s\u0013:$XM\u001d<bYRK\b/\u001a\u0006\u0003%M\tQ\u0001^=qKNT!\u0001F\u000b\u0002\u0007M\fHN\u0003\u0002\u0017/\u0005)1\u000f]1sW*\u0011\u0001$G\u0001\u0007CB\f7\r[3\u000b\u0003i\t1a\u001c:h\u0007\u0001\u0019\"\u0001A\u000f\u0011\u0005yyR\"A\t\n\u0005\u0001\n\"\u0001\u0003#bi\u0006$\u0016\u0010]3\u0002\rqJg.\u001b;?)\u0005\u0019\u0003C\u0001\u0010\u0001\u0003-!WMZ1vYR\u001c\u0016N_3\u0016\u0003\u0019\u0002\"a\n\u0016\u000e\u0003!R\u0011!K\u0001\u0006g\u000e\fG.Y\u0005\u0003W!\u00121!\u00138u\u0003!!\u0018\u0010]3OC6,W#\u0001\u0018\u0011\u0005=2dB\u0001\u00195!\t\t\u0004&D\u00013\u0015\t\u00194$\u0001\u0004=e>|GOP\u0005\u0003k!\na\u0001\u0015:fI\u00164\u0017BA\u001c9\u0005\u0019\u0019FO]5oO*\u0011Q\u0007K\u0001\u000bCNtU\u000f\u001c7bE2,W#A\u0012)\u0005\u0001a\u0004CA\u001fA\u001b\u0005q$BA \u0016\u0003)\tgN\\8uCRLwN\\\u0005\u0003\u0003z\u0012aa\u0015;bE2,\u0017\u0001F\"bY\u0016tG-\u0019:J]R,'O^1m)f\u0004X\r\u0005\u0002\u001f\rM!aaI#I!\t9c)\u0003\u0002HQ\t9\u0001K]8ek\u000e$\bCA%O\u001d\tQEJ\u0004\u00022\u0017&\t\u0011&\u0003\u0002NQ\u00059\u0001/Y2lC\u001e,\u0017BA(Q\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\ti\u0005\u0006F\u0001D\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\tA\u000b\u0005\u0002V56\taK\u0003\u0002X1\u0006!A.\u00198h\u0015\u0005I\u0016\u0001\u00026bm\u0006L!a\u000e,\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011a,\u0019\t\u0003O}K!\u0001\u0019\u0015\u0003\u0007\u0005s\u0017\u0010C\u0004c\u0015\u0005\u0005\t\u0019\u0001\u0014\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\u0005)\u0007c\u00014j=6\tqM\u0003\u0002iQ\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005)<'\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$\"!\u001c9\u0011\u0005\u001dr\u0017BA8)\u0005\u001d\u0011un\u001c7fC:DqA\u0019\u0007\u0002\u0002\u0003\u0007a,\u0001\u0005iCND7i\u001c3f)\u00051\u0013\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003Q\u000bAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\u0012a\u001e\t\u0003+bL!!\u001f,\u0003\r=\u0013'.Z2uQ\t1A\b\u000b\u0002\u0006y\u0001"
)
public class CalendarIntervalType extends DataType {
   public static boolean canEqual(final Object x$1) {
      return CalendarIntervalType$.MODULE$.canEqual(x$1);
   }

   public static Iterator productIterator() {
      return CalendarIntervalType$.MODULE$.productIterator();
   }

   public static Object productElement(final int x$1) {
      return CalendarIntervalType$.MODULE$.productElement(x$1);
   }

   public static int productArity() {
      return CalendarIntervalType$.MODULE$.productArity();
   }

   public static String productPrefix() {
      return CalendarIntervalType$.MODULE$.productPrefix();
   }

   public static Iterator productElementNames() {
      return CalendarIntervalType$.MODULE$.productElementNames();
   }

   public static String productElementName(final int n) {
      return CalendarIntervalType$.MODULE$.productElementName(n);
   }

   public int defaultSize() {
      return 16;
   }

   public String typeName() {
      return "interval";
   }

   public CalendarIntervalType asNullable() {
      return this;
   }
}
