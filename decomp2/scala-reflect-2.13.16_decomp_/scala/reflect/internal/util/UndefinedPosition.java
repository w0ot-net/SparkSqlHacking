package scala.reflect.internal.util;

import scala.reflect.ScalaSignature;
import scala.runtime.Nothing;

@ScalaSignature(
   bytes = "\u0006\u0005]2Q!\u0003\u0006\u0002\"MAQ\u0001\u0007\u0001\u0005\u0002eAQa\u0007\u0001\u0005FqAQ!\t\u0001\u0005BqAQA\t\u0001\u0005B\rBQa\n\u0001\u0005B!BQ\u0001\f\u0001\u0005B!BQ!\f\u0001\u0005B!BQA\f\u0001\u0005B=\u0012\u0011#\u00168eK\u001aLg.\u001a3Q_NLG/[8o\u0015\tYA\"\u0001\u0003vi&d'BA\u0007\u000f\u0003!Ig\u000e^3s]\u0006d'BA\b\u0011\u0003\u001d\u0011XM\u001a7fGRT\u0011!E\u0001\u0006g\u000e\fG.Y\u0002\u0001'\t\u0001A\u0003\u0005\u0002\u0016-5\t!\"\u0003\u0002\u0018\u0015\tA\u0001k\\:ji&|g.\u0001\u0004=S:LGO\u0010\u000b\u00025A\u0011Q\u0003A\u0001\nSN$UMZ5oK\u0012,\u0012!\b\t\u0003=}i\u0011\u0001E\u0005\u0003AA\u0011qAQ8pY\u0016\fg.A\u0004jgJ\u000bgnZ3\u0002\rM|WO]2f+\u0005!\u0003CA\u000b&\u0015\t1#\"\u0001\u0007O_N{WO]2f\r&dW-A\u0003ti\u0006\u0014H/F\u0001*!\tq\"&\u0003\u0002,!\t9aj\u001c;iS:<\u0017!\u00029pS:$\u0018aA3oI\u0006Y1/Y7f!>Lg\u000e^!t)\ti\u0002\u0007C\u00032\u0011\u0001\u0007A#\u0001\u0003uQ\u0006$\u0018f\u0001\u00014k%\u0011AG\u0003\u0002\b\r\u0006\\W\rU8t\u0015\t1$\"\u0001\u0006O_B{7/\u001b;j_:\u0004"
)
public abstract class UndefinedPosition extends Position {
   public final boolean isDefined() {
      return false;
   }

   public boolean isRange() {
      return false;
   }

   public NoSourceFile$ source() {
      return NoSourceFile$.MODULE$;
   }

   public Nothing start() {
      return this.fail("start");
   }

   public Nothing point() {
      return this.fail("point");
   }

   public Nothing end() {
      return this.fail("end");
   }

   public boolean samePointAs(final Position that) {
      return false;
   }
}
