package org.apache.spark.scheduler;

import org.apache.spark.annotation.DeveloperApi;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005!<Qa\u0003\u0007\t\u0002V1Qa\u0006\u0007\t\u0002bAQ!M\u0001\u0005\u0002IBqaM\u0001\u0002\u0002\u0013\u0005C\u0007C\u0004>\u0003\u0005\u0005I\u0011\u0001 \t\u000f\t\u000b\u0011\u0011!C\u0001\u0007\"9\u0011*AA\u0001\n\u0003R\u0005bB)\u0002\u0003\u0003%\tA\u0015\u0005\b/\u0006\t\t\u0011\"\u0011Y\u0011\u001dI\u0016!!A\u0005BiCqaW\u0001\u0002\u0002\u0013%A,\u0001\u0007K_\n\u001cVoY2fK\u0012,GM\u0003\u0002\u000e\u001d\u0005I1o\u00195fIVdWM\u001d\u0006\u0003\u001fA\tQa\u001d9be.T!!\u0005\n\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0019\u0012aA8sO\u000e\u0001\u0001C\u0001\f\u0002\u001b\u0005a!\u0001\u0004&pEN+8mY3fI\u0016$7#B\u0001\u001a?\t*\u0003C\u0001\u000e\u001e\u001b\u0005Y\"\"\u0001\u000f\u0002\u000bM\u001c\u0017\r\\1\n\u0005yY\"AB!osJ+g\r\u0005\u0002\u0017A%\u0011\u0011\u0005\u0004\u0002\n\u0015>\u0014'+Z:vYR\u0004\"AG\u0012\n\u0005\u0011Z\"a\u0002)s_\u0012,8\r\u001e\t\u0003M9r!a\n\u0017\u000f\u0005!ZS\"A\u0015\u000b\u0005)\"\u0012A\u0002\u001fs_>$h(C\u0001\u001d\u0013\ti3$A\u0004qC\u000e\\\u0017mZ3\n\u0005=\u0002$\u0001D*fe&\fG.\u001b>bE2,'BA\u0017\u001c\u0003\u0019a\u0014N\\5u}Q\tQ#A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002kA\u0011agO\u0007\u0002o)\u0011\u0001(O\u0001\u0005Y\u0006twMC\u0001;\u0003\u0011Q\u0017M^1\n\u0005q:$AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001@!\tQ\u0002)\u0003\u0002B7\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011Ai\u0012\t\u00035\u0015K!AR\u000e\u0003\u0007\u0005s\u0017\u0010C\u0004I\u000b\u0005\u0005\t\u0019A \u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\u0005Y\u0005c\u0001'P\t6\tQJ\u0003\u0002O7\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005Ak%\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$\"a\u0015,\u0011\u0005i!\u0016BA+\u001c\u0005\u001d\u0011un\u001c7fC:Dq\u0001S\u0004\u0002\u0002\u0003\u0007A)\u0001\u0005iCND7i\u001c3f)\u0005y\u0014\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003U\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\u0012!\u0018\t\u0003myK!aX\u001c\u0003\r=\u0013'.Z2uQ\t\t\u0011\r\u0005\u0002cK6\t1M\u0003\u0002e\u001d\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005\u0019\u001c'\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0007F\u0001\u0001b\u0001"
)
public final class JobSucceeded {
   public static String toString() {
      return JobSucceeded$.MODULE$.toString();
   }

   public static int hashCode() {
      return JobSucceeded$.MODULE$.hashCode();
   }

   public static boolean canEqual(final Object x$1) {
      return JobSucceeded$.MODULE$.canEqual(x$1);
   }

   public static Iterator productIterator() {
      return JobSucceeded$.MODULE$.productIterator();
   }

   public static Object productElement(final int x$1) {
      return JobSucceeded$.MODULE$.productElement(x$1);
   }

   public static int productArity() {
      return JobSucceeded$.MODULE$.productArity();
   }

   public static String productPrefix() {
      return JobSucceeded$.MODULE$.productPrefix();
   }

   public static Iterator productElementNames() {
      return JobSucceeded$.MODULE$.productElementNames();
   }

   public static String productElementName(final int n) {
      return JobSucceeded$.MODULE$.productElementName(n);
   }
}
