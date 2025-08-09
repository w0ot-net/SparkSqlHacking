package org.apache.spark;

import org.apache.spark.annotation.DeveloperApi;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0019<Qa\u0003\u0007\t\u0002N1Q!\u0006\u0007\t\u0002ZAQaL\u0001\u0005\u0002ABq!M\u0001\u0002\u0002\u0013\u0005#\u0007C\u0004<\u0003\u0005\u0005I\u0011\u0001\u001f\t\u000f\u0001\u000b\u0011\u0011!C\u0001\u0003\"9q)AA\u0001\n\u0003B\u0005bB(\u0002\u0003\u0003%\t\u0001\u0015\u0005\b+\u0006\t\t\u0011\"\u0011W\u0011\u001d9\u0016!!A\u0005BaCq!W\u0001\u0002\u0002\u0013%!,A\u0004Tk\u000e\u001cWm]:\u000b\u00055q\u0011!B:qCJ\\'BA\b\u0011\u0003\u0019\t\u0007/Y2iK*\t\u0011#A\u0002pe\u001e\u001c\u0001\u0001\u0005\u0002\u0015\u00035\tABA\u0004Tk\u000e\u001cWm]:\u0014\u000b\u00059R\u0004I\u0012\u0011\u0005aYR\"A\r\u000b\u0003i\tQa]2bY\u0006L!\u0001H\r\u0003\r\u0005s\u0017PU3g!\t!b$\u0003\u0002 \u0019\tiA+Y:l\u000b:$'+Z1t_:\u0004\"\u0001G\u0011\n\u0005\tJ\"a\u0002)s_\u0012,8\r\u001e\t\u0003I1r!!\n\u0016\u000f\u0005\u0019JS\"A\u0014\u000b\u0005!\u0012\u0012A\u0002\u001fs_>$h(C\u0001\u001b\u0013\tY\u0013$A\u0004qC\u000e\\\u0017mZ3\n\u00055r#\u0001D*fe&\fG.\u001b>bE2,'BA\u0016\u001a\u0003\u0019a\u0014N\\5u}Q\t1#A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002gA\u0011A'O\u0007\u0002k)\u0011agN\u0001\u0005Y\u0006twMC\u00019\u0003\u0011Q\u0017M^1\n\u0005i*$AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001>!\tAb(\u0003\u0002@3\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011!)\u0012\t\u00031\rK!\u0001R\r\u0003\u0007\u0005s\u0017\u0010C\u0004G\u000b\u0005\u0005\t\u0019A\u001f\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\u0005I\u0005c\u0001&N\u00056\t1J\u0003\u0002M3\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u00059[%\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$\"!\u0015+\u0011\u0005a\u0011\u0016BA*\u001a\u0005\u001d\u0011un\u001c7fC:DqAR\u0004\u0002\u0002\u0003\u0007!)\u0001\u0005iCND7i\u001c3f)\u0005i\u0014\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003M\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\u0012a\u0017\t\u0003iqK!!X\u001b\u0003\r=\u0013'.Z2uQ\t\tq\f\u0005\u0002aG6\t\u0011M\u0003\u0002c\u0019\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005\u0011\f'\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0007F\u0001\u0001`\u0001"
)
public final class Success {
   public static String toString() {
      return Success$.MODULE$.toString();
   }

   public static int hashCode() {
      return Success$.MODULE$.hashCode();
   }

   public static boolean canEqual(final Object x$1) {
      return Success$.MODULE$.canEqual(x$1);
   }

   public static Iterator productIterator() {
      return Success$.MODULE$.productIterator();
   }

   public static Object productElement(final int x$1) {
      return Success$.MODULE$.productElement(x$1);
   }

   public static int productArity() {
      return Success$.MODULE$.productArity();
   }

   public static String productPrefix() {
      return Success$.MODULE$.productPrefix();
   }

   public static Iterator productElementNames() {
      return Success$.MODULE$.productElementNames();
   }

   public static String productElementName(final int n) {
      return Success$.MODULE$.productElementName(n);
   }
}
