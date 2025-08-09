package org.apache.spark.sql.types;

import org.apache.spark.annotation.Unstable;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@Unstable
@ScalaSignature(
   bytes = "\u0006\u0005q4A\u0001E\t\u00019!)\u0011\u0005\u0001C\u0005E!)A\u0005\u0001C!K!)A\u0006\u0001C![!1\u0011\b\u0001C!+i:QAQ\t\t\u0002\u000e3Q\u0001E\t\t\u0002\u0012CQ!\t\u0004\u0005\u0002ECqA\u0015\u0004\u0002\u0002\u0013\u00053\u000bC\u0004\\\r\u0005\u0005I\u0011A\u0013\t\u000fq3\u0011\u0011!C\u0001;\"91MBA\u0001\n\u0003\"\u0007bB6\u0007\u0003\u0003%\t\u0001\u001c\u0005\bc\u001a\t\t\u0011\"\u0011s\u0011\u001d\u0019h!!A\u0005BQDq!\u001e\u0004\u0002\u0002\u0013%aO\u0001\tUS6,7\u000f^1na:#&\fV=qK*\u0011!cE\u0001\u0006if\u0004Xm\u001d\u0006\u0003)U\t1a]9m\u0015\t1r#A\u0003ta\u0006\u00148N\u0003\u0002\u00193\u00051\u0011\r]1dQ\u0016T\u0011AG\u0001\u0004_J<7\u0001A\n\u0003\u0001u\u0001\"AH\u0010\u000e\u0003EI!\u0001I\t\u0003\u0019\u0011\u000bG/\u001a;j[\u0016$\u0016\u0010]3\u0002\rqJg.\u001b;?)\u0005\u0019\u0003C\u0001\u0010\u0001\u0003-!WMZ1vYR\u001c\u0016N_3\u0016\u0003\u0019\u0002\"a\n\u0016\u000e\u0003!R\u0011!K\u0001\u0006g\u000e\fG.Y\u0005\u0003W!\u00121!\u00138u\u0003!!\u0018\u0010]3OC6,W#\u0001\u0018\u0011\u0005=2dB\u0001\u00195!\t\t\u0004&D\u00013\u0015\t\u00194$\u0001\u0004=e>|GOP\u0005\u0003k!\na\u0001\u0015:fI\u00164\u0017BA\u001c9\u0005\u0019\u0019FO]5oO*\u0011Q\u0007K\u0001\u000bCNtU\u000f\u001c7bE2,W#A\u0012)\u0005\u0001a\u0004CA\u001fA\u001b\u0005q$BA \u0016\u0003)\tgN\\8uCRLwN\\\u0005\u0003\u0003z\u0012\u0001\"\u00168ti\u0006\u0014G.Z\u0001\u0011)&lWm\u001d;b[BtEK\u0017+za\u0016\u0004\"A\b\u0004\u0014\t\u0019\u0019S\t\u0013\t\u0003O\u0019K!a\u0012\u0015\u0003\u000fA\u0013x\u000eZ;diB\u0011\u0011J\u0014\b\u0003\u00152s!!M&\n\u0003%J!!\u0014\u0015\u0002\u000fA\f7m[1hK&\u0011q\n\u0015\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003\u001b\"\"\u0012aQ\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003Q\u0003\"!\u0016.\u000e\u0003YS!a\u0016-\u0002\t1\fgn\u001a\u0006\u00023\u0006!!.\u0019<b\u0013\t9d+\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005y\u000b\u0007CA\u0014`\u0013\t\u0001\u0007FA\u0002B]fDqA\u0019\u0006\u0002\u0002\u0003\u0007a%A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002KB\u0019a-\u001b0\u000e\u0003\u001dT!\u0001\u001b\u0015\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002kO\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\ti\u0007\u000f\u0005\u0002(]&\u0011q\u000e\u000b\u0002\b\u0005>|G.Z1o\u0011\u001d\u0011G\"!AA\u0002y\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002M\u0005AAo\\*ue&tw\rF\u0001U\u000319(/\u001b;f%\u0016\u0004H.Y2f)\u00059\bCA+y\u0013\tIhK\u0001\u0004PE*,7\r\u001e\u0015\u0003\rqB#!\u0002\u001f"
)
public class TimestampNTZType extends DatetimeType {
   public static boolean canEqual(final Object x$1) {
      return TimestampNTZType$.MODULE$.canEqual(x$1);
   }

   public static Iterator productIterator() {
      return TimestampNTZType$.MODULE$.productIterator();
   }

   public static Object productElement(final int x$1) {
      return TimestampNTZType$.MODULE$.productElement(x$1);
   }

   public static int productArity() {
      return TimestampNTZType$.MODULE$.productArity();
   }

   public static String productPrefix() {
      return TimestampNTZType$.MODULE$.productPrefix();
   }

   public static Iterator productElementNames() {
      return TimestampNTZType$.MODULE$.productElementNames();
   }

   public static String productElementName(final int n) {
      return TimestampNTZType$.MODULE$.productElementName(n);
   }

   public int defaultSize() {
      return 8;
   }

   public String typeName() {
      return "timestamp_ntz";
   }

   public TimestampNTZType asNullable() {
      return this;
   }
}
