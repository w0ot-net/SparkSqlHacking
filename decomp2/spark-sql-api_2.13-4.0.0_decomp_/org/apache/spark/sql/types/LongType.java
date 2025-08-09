package org.apache.spark.sql.types;

import org.apache.spark.annotation.Stable;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@Stable
@ScalaSignature(
   bytes = "\u0006\u0005q4A\u0001E\t\u00019!)\u0011\u0005\u0001C\u0005E!)A\u0005\u0001C!K!)A\u0006\u0001C![!1\u0011\b\u0001C!+i:QAQ\t\t\u0002\u000e3Q\u0001E\t\t\u0002\u0012CQ!\t\u0004\u0005\u0002ECqA\u0015\u0004\u0002\u0002\u0013\u00053\u000bC\u0004\\\r\u0005\u0005I\u0011A\u0013\t\u000fq3\u0011\u0011!C\u0001;\"91MBA\u0001\n\u0003\"\u0007bB6\u0007\u0003\u0003%\t\u0001\u001c\u0005\bc\u001a\t\t\u0011\"\u0011s\u0011\u001d\u0019h!!A\u0005BQDq!\u001e\u0004\u0002\u0002\u0013%aO\u0001\u0005M_:<G+\u001f9f\u0015\t\u00112#A\u0003usB,7O\u0003\u0002\u0015+\u0005\u00191/\u001d7\u000b\u0005Y9\u0012!B:qCJ\\'B\u0001\r\u001a\u0003\u0019\t\u0007/Y2iK*\t!$A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001;A\u0011adH\u0007\u0002#%\u0011\u0001%\u0005\u0002\r\u0013:$Xm\u001a:bYRK\b/Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\r\u0002\"A\b\u0001\u0002\u0017\u0011,g-Y;miNK'0Z\u000b\u0002MA\u0011qEK\u0007\u0002Q)\t\u0011&A\u0003tG\u0006d\u0017-\u0003\u0002,Q\t\u0019\u0011J\u001c;\u0002\u0019MLW\u000e\u001d7f'R\u0014\u0018N\\4\u0016\u00039\u0002\"a\f\u001c\u000f\u0005A\"\u0004CA\u0019)\u001b\u0005\u0011$BA\u001a\u001c\u0003\u0019a$o\\8u}%\u0011Q\u0007K\u0001\u0007!J,G-\u001a4\n\u0005]B$AB*ue&twM\u0003\u00026Q\u0005Q\u0011m\u001d(vY2\f'\r\\3\u0016\u0003\rB#\u0001\u0001\u001f\u0011\u0005u\u0002U\"\u0001 \u000b\u0005}*\u0012AC1o]>$\u0018\r^5p]&\u0011\u0011I\u0010\u0002\u0007'R\f'\r\\3\u0002\u00111{gn\u001a+za\u0016\u0004\"A\b\u0004\u0014\t\u0019\u0019S\t\u0013\t\u0003O\u0019K!a\u0012\u0015\u0003\u000fA\u0013x\u000eZ;diB\u0011\u0011J\u0014\b\u0003\u00152s!!M&\n\u0003%J!!\u0014\u0015\u0002\u000fA\f7m[1hK&\u0011q\n\u0015\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003\u001b\"\"\u0012aQ\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003Q\u0003\"!\u0016.\u000e\u0003YS!a\u0016-\u0002\t1\fgn\u001a\u0006\u00023\u0006!!.\u0019<b\u0013\t9d+\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005y\u000b\u0007CA\u0014`\u0013\t\u0001\u0007FA\u0002B]fDqA\u0019\u0006\u0002\u0002\u0003\u0007a%A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002KB\u0019a-\u001b0\u000e\u0003\u001dT!\u0001\u001b\u0015\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002kO\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\ti\u0007\u000f\u0005\u0002(]&\u0011q\u000e\u000b\u0002\b\u0005>|G.Z1o\u0011\u001d\u0011G\"!AA\u0002y\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002M\u0005AAo\\*ue&tw\rF\u0001U\u000319(/\u001b;f%\u0016\u0004H.Y2f)\u00059\bCA+y\u0013\tIhK\u0001\u0004PE*,7\r\u001e\u0015\u0003\rqB#!\u0002\u001f"
)
public class LongType extends IntegralType {
   public static boolean canEqual(final Object x$1) {
      return LongType$.MODULE$.canEqual(x$1);
   }

   public static Iterator productIterator() {
      return LongType$.MODULE$.productIterator();
   }

   public static Object productElement(final int x$1) {
      return LongType$.MODULE$.productElement(x$1);
   }

   public static int productArity() {
      return LongType$.MODULE$.productArity();
   }

   public static String productPrefix() {
      return LongType$.MODULE$.productPrefix();
   }

   public static Iterator productElementNames() {
      return LongType$.MODULE$.productElementNames();
   }

   public static String productElementName(final int n) {
      return LongType$.MODULE$.productElementName(n);
   }

   public int defaultSize() {
      return 8;
   }

   public String simpleString() {
      return "bigint";
   }

   public LongType asNullable() {
      return this;
   }
}
