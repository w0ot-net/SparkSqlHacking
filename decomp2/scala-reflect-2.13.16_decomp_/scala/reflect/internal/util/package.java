package scala.reflect.internal.util;

import scala.Option;
import scala.StringContext;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005Y<Q\u0001E\t\t\u0002i1Q\u0001H\t\t\u0002uAQAI\u0001\u0005\u0002\rBq\u0001J\u0001C\u0002\u0013\u0005Q\u0005\u0003\u00041\u0003\u0001\u0006IA\n\u0005\bc\u0005\u0011\r\u0011\"\u00013\u0011\u00191\u0014\u0001)A\u0005g!)q'\u0001C\u0001q!)\u0011)\u0001C\u0005\u0005\")\u0001+\u0001C\u0001#\")A+\u0001C\u0001+\u001a!A-A\u0001f\u0011!I7B!b\u0001\n\u0003Q\u0007\u0002\u00038\f\u0005\u0003\u0005\u000b\u0011B6\t\u000b\tZA\u0011A8\t\u000fM\f\u0011\u0011!C\u0002i\u00069\u0001/Y2lC\u001e,'B\u0001\n\u0014\u0003\u0011)H/\u001b7\u000b\u0005Q)\u0012\u0001C5oi\u0016\u0014h.\u00197\u000b\u0005Y9\u0012a\u0002:fM2,7\r\u001e\u0006\u00021\u0005)1oY1mC\u000e\u0001\u0001CA\u000e\u0002\u001b\u0005\t\"a\u00029bG.\fw-Z\n\u0003\u0003y\u0001\"a\b\u0011\u000e\u0003]I!!I\f\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}Q\t!$A\u0005MSN$xJ\u001a(jYV\ta\u0005E\u0002(S1r!a\b\u0015\n\u0005A9\u0012B\u0001\u0016,\u0005\u0011a\u0015n\u001d;\u000b\u0005A9\u0002cA\u0014*[A\u0011qDL\u0005\u0003_]\u0011qAT8uQ&tw-\u0001\u0006MSN$xJ\u001a(jY\u0002\n\u0011bU8nK>3g*\u001b7\u0016\u0003M\u00022a\b\u001b-\u0013\t)tC\u0001\u0004PaRLwN\\\u0001\u000b'>lWm\u00144OS2\u0004\u0013\u0001C1oI\u001a\u000bGn]3\u0015\u0005eb\u0004CA\u0010;\u0013\tYtCA\u0004C_>dW-\u00198\t\u000bu:\u0001\u0019\u0001 \u0002\t\t|G-\u001f\t\u0003?}J!\u0001Q\f\u0003\tUs\u0017\u000e^\u0001\fg\"|'\u000f^3o\u001d\u0006lW\r\u0006\u0002D\u001dB\u0011Ai\u0013\b\u0003\u000b&\u0003\"AR\f\u000e\u0003\u001dS!\u0001S\r\u0002\rq\u0012xn\u001c;?\u0013\tQu#\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u00196\u0013aa\u0015;sS:<'B\u0001&\u0018\u0011\u0015y\u0005\u00021\u0001D\u0003\u0011q\u0017-\\3\u0002)MDwN\u001d;DY\u0006\u001c8o\u00144J]N$\u0018M\\2f)\t\u0019%\u000bC\u0003T\u0013\u0001\u0007a$A\u0001y\u0003)\u0019\bn\u001c:u\u00072\f7o\u001d\u000b\u0003\u0007ZCQa\u0016\u0006A\u0002a\u000bQa\u00197buj\u0004$!\u00170\u0011\u0007\u0011SF,\u0003\u0002\\\u001b\n)1\t\\1tgB\u0011QL\u0018\u0007\u0001\t%yf+!A\u0001\u0002\u000b\u0005\u0001MA\u0002`IE\n\"!L1\u0011\u0005}\u0011\u0017BA2\u0018\u0005\r\te.\u001f\u0002\u001c'R\u0014\u0018N\\4D_:$X\r\u001f;TiJL\u0007/T1sO&tw\n]:\u0014\u0007-qb\r\u0005\u0002\u001cO&\u0011\u0001.\u0005\u0002\u0018'R\u0014\u0018\u000e]'be\u001eLg.\u00138uKJ\u0004x\u000e\\1u_J\fQb\u001d;sS:<7i\u001c8uKb$X#A6\u0011\u0005}a\u0017BA7\u0018\u00055\u0019FO]5oO\u000e{g\u000e^3yi\u0006q1\u000f\u001e:j]\u001e\u001cuN\u001c;fqR\u0004CC\u00019s!\t\t8\"D\u0001\u0002\u0011\u0015Ig\u00021\u0001l\u0003m\u0019FO]5oO\u000e{g\u000e^3yiN#(/\u001b9NCJ<\u0017N\\(qgR\u0011\u0001/\u001e\u0005\u0006S>\u0001\ra\u001b"
)
public final class package {
   public static StringContextStripMarginOps StringContextStripMarginOps(final StringContext stringContext) {
      return package$.MODULE$.StringContextStripMarginOps(stringContext);
   }

   public static String shortClass(final Class clazz) {
      return package$.MODULE$.shortClass(clazz);
   }

   public static String shortClassOfInstance(final Object x) {
      return package$.MODULE$.shortClassOfInstance(x);
   }

   public static boolean andFalse(final BoxedUnit body) {
      return package$.MODULE$.andFalse(body);
   }

   public static Option SomeOfNil() {
      return package$.MODULE$.SomeOfNil();
   }

   public static List ListOfNil() {
      return package$.MODULE$.ListOfNil();
   }

   public static class StringContextStripMarginOps implements StripMarginInterpolator {
      private final StringContext stringContext;

      public final String sm(final Seq args) {
         return StripMarginInterpolator.sm$(this, args);
      }

      public final String sq(final Seq args) {
         return StripMarginInterpolator.sq$(this, args);
      }

      public StringContext stringContext() {
         return this.stringContext;
      }

      public StringContextStripMarginOps(final StringContext stringContext) {
         this.stringContext = stringContext;
      }
   }
}
