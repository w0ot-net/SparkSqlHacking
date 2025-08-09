package org.apache.spark.sql.catalyst.trees;

import scala.Function0;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m<QAD\b\t\u0002q1QAH\b\t\u0002}AQAJ\u0001\u0005\u0002\u001dBq\u0001K\u0001C\u0002\u0013%\u0011\u0006\u0003\u00046\u0003\u0001\u0006IA\u000b\u0005\u0006m\u0005!\ta\u000e\u0005\u0006q\u0005!\t!\u000f\u0005\u0006\u007f\u0005!\t\u0001\u0011\u0005\u0006\u0003\u0006!\tA\u0011\u0005\u0006\u0015\u0006!\ta\u0013\u0005\u0007\u0015\u0006!\taE0\t\u000f\u0019\f!\u0019!C\u0005O\"1\u0001/\u0001Q\u0001\n!DQ!]\u0001\u0005\nI\fQbQ;se\u0016tGo\u0014:jO&t'B\u0001\t\u0012\u0003\u0015!(/Z3t\u0015\t\u00112#\u0001\u0005dCR\fG._:u\u0015\t!R#A\u0002tc2T!AF\f\u0002\u000bM\u0004\u0018M]6\u000b\u0005aI\u0012AB1qC\u000eDWMC\u0001\u001b\u0003\ry'oZ\u0002\u0001!\ti\u0012!D\u0001\u0010\u00055\u0019UO\u001d:f]R|%/[4j]N\u0011\u0011\u0001\t\t\u0003C\u0011j\u0011A\t\u0006\u0002G\u0005)1oY1mC&\u0011QE\t\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?)\u0005a\u0012!\u0002<bYV,W#\u0001\u0016\u0011\u0007-\u0002$'D\u0001-\u0015\tic&\u0001\u0003mC:<'\"A\u0018\u0002\t)\fg/Y\u0005\u0003c1\u00121\u0002\u00165sK\u0006$Gj\\2bYB\u0011QdM\u0005\u0003i=\u0011aa\u0014:jO&t\u0017A\u0002<bYV,\u0007%A\u0002hKR,\u0012AM\u0001\u0004g\u0016$HC\u0001\u001e>!\t\t3(\u0003\u0002=E\t!QK\\5u\u0011\u0015qd\u00011\u00013\u0003\u0005y\u0017!\u0002:fg\u0016$H#\u0001\u001e\u0002\u0017M,G\u000fU8tSRLwN\u001c\u000b\u0004u\rC\u0005\"\u0002#\t\u0001\u0004)\u0015\u0001\u00027j]\u0016\u0004\"!\t$\n\u0005\u001d\u0013#aA%oi\")\u0011\n\u0003a\u0001\u000b\u0006)1\u000f^1si\u0006Qq/\u001b;i\u001fJLw-\u001b8\u0016\u00051\u0003FCA'_)\tq\u0015\f\u0005\u0002P!2\u0001A!B)\n\u0005\u0004\u0011&!A!\u0012\u0005M3\u0006CA\u0011U\u0013\t)&EA\u0004O_RD\u0017N\\4\u0011\u0005\u0005:\u0016B\u0001-#\u0005\r\te.\u001f\u0005\u00075&!\t\u0019A.\u0002\u0003\u0019\u00042!\t/O\u0013\ti&E\u0001\u0005=Eft\u0017-\\3?\u0011\u0015q\u0014\u00021\u00013+\t\u0001'\r\u0006\u0002bIB\u0011qJ\u0019\u0003\u0006G*\u0011\rA\u0015\u0002\u0002)\"1!L\u0003CA\u0002\u0015\u00042!\t/b\u0003A\u0019\b/\u0019:l\u0007>$W\rU1ui\u0016\u0014h.F\u0001i!\tIg.D\u0001k\u0015\tYG.A\u0003sK\u001e,\u0007P\u0003\u0002n]\u0005!Q\u000f^5m\u0013\ty'NA\u0004QCR$XM\u001d8\u0002#M\u0004\u0018M]6D_\u0012,\u0007+\u0019;uKJt\u0007%A\u0005ta\u0006\u00148nQ8eKR\u00111O\u001e\t\u0003CQL!!\u001e\u0012\u0003\u000f\t{w\u000e\\3b]\")q/\u0004a\u0001q\u0006\u00191\u000f^3\u0011\u0005-J\u0018B\u0001>-\u0005E\u0019F/Y2l)J\f7-Z#mK6,g\u000e\u001e"
)
public final class CurrentOrigin {
   public static Object withOrigin(final Origin o, final Function0 f) {
      return CurrentOrigin$.MODULE$.withOrigin(o, f);
   }

   public static void setPosition(final int line, final int start) {
      CurrentOrigin$.MODULE$.setPosition(line, start);
   }

   public static void reset() {
      CurrentOrigin$.MODULE$.reset();
   }

   public static void set(final Origin o) {
      CurrentOrigin$.MODULE$.set(o);
   }

   public static Origin get() {
      return CurrentOrigin$.MODULE$.get();
   }
}
