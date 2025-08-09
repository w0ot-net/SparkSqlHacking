package org.apache.spark.sql.types;

import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.internal.Logging;
import scala.Option;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015q!\u0002\u0005\n\u0011\u0003!b!\u0002\f\n\u0011\u00039\u0002\"\u0002\u0019\u0002\t\u0003\t\u0004\u0002\u0003\u001a\u0002\u0011\u000b\u0007I\u0011B\u001a\t\u000b\u0011\u000bA\u0011A#\t\u000b-\u000bA\u0011\u0001'\t\u000bQ\u000bA\u0011A+\t\u000f%\f\u0011\u0011!C\u0005U\u0006yQ\u000b\u0012+SK\u001eL7\u000f\u001e:bi&|gN\u0003\u0002\u000b\u0017\u0005)A/\u001f9fg*\u0011A\"D\u0001\u0004gFd'B\u0001\b\u0010\u0003\u0015\u0019\b/\u0019:l\u0015\t\u0001\u0012#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002%\u0005\u0019qN]4\u0004\u0001A\u0011Q#A\u0007\u0002\u0013\tyQ\u000b\u0012+SK\u001eL7\u000f\u001e:bi&|gn\u0005\u0003\u00021yQ\u0003CA\r\u001d\u001b\u0005Q\"\"A\u000e\u0002\u000bM\u001c\u0017\r\\1\n\u0005uQ\"AB!osJ+g\r\u0005\u0002 O9\u0011\u0001%\n\b\u0003C\u0011j\u0011A\t\u0006\u0003GM\ta\u0001\u0010:p_Rt\u0014\"A\u000e\n\u0005\u0019R\u0012a\u00029bG.\fw-Z\u0005\u0003Q%\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!A\n\u000e\u0011\u0005-rS\"\u0001\u0017\u000b\u00055j\u0011\u0001C5oi\u0016\u0014h.\u00197\n\u0005=b#a\u0002'pO\u001eLgnZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003Q\ta!\u001e3u\u001b\u0006\u0004X#\u0001\u001b\u0011\tURD\bP\u0007\u0002m)\u0011q\u0007O\u0001\b[V$\u0018M\u00197f\u0015\tI$$\u0001\u0006d_2dWm\u0019;j_:L!a\u000f\u001c\u0003\u00075\u000b\u0007\u000f\u0005\u0002>\u0003:\u0011ah\u0010\t\u0003CiI!\u0001\u0011\u000e\u0002\rA\u0013X\rZ3g\u0013\t\u00115I\u0001\u0004TiJLgn\u001a\u0006\u0003\u0001j\ta!\u001a=jgR\u001cHC\u0001$J!\tIr)\u0003\u0002I5\t9!i\\8mK\u0006t\u0007\"\u0002&\u0005\u0001\u0004a\u0014!D;tKJ\u001cE.Y:t\u001d\u0006lW-\u0001\u0005sK\u001eL7\u000f^3s)\ri\u0005K\u0015\t\u000339K!a\u0014\u000e\u0003\tUs\u0017\u000e\u001e\u0005\u0006#\u0016\u0001\r\u0001P\u0001\nkN,'o\u00117bgNDQaU\u0003A\u0002q\n\u0001\"\u001e3u\u00072\f7o]\u0001\nO\u0016$X\u000b\u0012+G_J$\"A\u00165\u0011\u0007e9\u0016,\u0003\u0002Y5\t1q\n\u001d;j_:\u0004$AW0\u0011\u0007uZV,\u0003\u0002]\u0007\n)1\t\\1tgB\u0011al\u0018\u0007\u0001\t%\u0001g!!A\u0001\u0002\u000b\u0005\u0011MA\u0002`IE\n\"AY3\u0011\u0005e\u0019\u0017B\u00013\u001b\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!\u00074\n\u0005\u001dT\"aA!os\")\u0011K\u0002a\u0001y\u0005aqO]5uKJ+\u0007\u000f\\1dKR\t1\u000e\u0005\u0002mc6\tQN\u0003\u0002o_\u0006!A.\u00198h\u0015\u0005\u0001\u0018\u0001\u00026bm\u0006L!A]7\u0003\r=\u0013'.Z2uQ\t\tA\u000f\u0005\u0002vq6\taO\u0003\u0002x\u001b\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005e4(\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0007fA\u0001|}B\u0011Q\u000f`\u0005\u0003{Z\u0014QaU5oG\u0016\f\u0013a`\u0001\u0006g9\u0012d\u0006\r\u0015\u0003\u0001QD3\u0001A>\u007f\u0001"
)
public final class UDTRegistration {
   public static Option getUDTFor(final String userClass) {
      return UDTRegistration$.MODULE$.getUDTFor(userClass);
   }

   public static void register(final String userClass, final String udtClass) {
      UDTRegistration$.MODULE$.register(userClass, udtClass);
   }

   public static boolean exists(final String userClassName) {
      return UDTRegistration$.MODULE$.exists(userClassName);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return UDTRegistration$.MODULE$.LogStringContext(sc);
   }
}
