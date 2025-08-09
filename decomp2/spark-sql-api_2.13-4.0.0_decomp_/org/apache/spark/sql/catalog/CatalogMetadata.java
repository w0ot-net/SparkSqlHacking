package org.apache.spark.sql.catalog;

import java.lang.invoke.SerializedLambda;
import javax.annotation.Nullable;
import org.apache.spark.sql.catalyst.DefinedByConstructorParams;
import scala.Option.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00053Aa\u0002\u0005\u0001'!A\u0001\u0005\u0001BC\u0002\u0013\u0005\u0011\u0005\u0003\u0005.\u0001\t\u0005\t\u0015!\u0003#\u0011!q\u0003A!b\u0001\n\u0003\t\u0003\u0002C\u0018\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0012\t\u000bA\u0002A\u0011A\u0019\t\u000b}\u0002A\u0011\t!\u0003\u001f\r\u000bG/\u00197pO6+G/\u00193bi\u0006T!!\u0003\u0006\u0002\u000f\r\fG/\u00197pO*\u00111\u0002D\u0001\u0004gFd'BA\u0007\u000f\u0003\u0015\u0019\b/\u0019:l\u0015\ty\u0001#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002#\u0005\u0019qN]4\u0004\u0001M\u0019\u0001\u0001\u0006\u000e\u0011\u0005UAR\"\u0001\f\u000b\u0003]\tQa]2bY\u0006L!!\u0007\f\u0003\r\u0005s\u0017PU3g!\tYb$D\u0001\u001d\u0015\ti\"\"\u0001\u0005dCR\fG._:u\u0013\tyBD\u0001\u000eEK\u001aLg.\u001a3Cs\u000e{gn\u001d;sk\u000e$xN\u001d)be\u0006l7/\u0001\u0003oC6,W#\u0001\u0012\u0011\u0005\rRcB\u0001\u0013)!\t)c#D\u0001'\u0015\t9##\u0001\u0004=e>|GOP\u0005\u0003SY\ta\u0001\u0015:fI\u00164\u0017BA\u0016-\u0005\u0019\u0019FO]5oO*\u0011\u0011FF\u0001\u0006]\u0006lW\rI\u0001\fI\u0016\u001c8M]5qi&|g.\u0001\u0007eKN\u001c'/\u001b9uS>t\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0004eQ*\u0004CA\u001a\u0001\u001b\u0005A\u0001\"\u0002\u0011\u0006\u0001\u0004\u0011\u0003\"\u0002\u0018\u0006\u0001\u0004\u0011\u0003FA\u001b8!\tAT(D\u0001:\u0015\tQ4(\u0001\u0006b]:|G/\u0019;j_:T\u0011\u0001P\u0001\u0006U\u00064\u0018\r_\u0005\u0003}e\u0012\u0001BT;mY\u0006\u0014G.Z\u0001\ti>\u001cFO]5oOR\t!\u0005"
)
public class CatalogMetadata implements DefinedByConstructorParams {
   private final String name;
   private final String description;

   public String name() {
      return this.name;
   }

   public String description() {
      return this.description;
   }

   public String toString() {
      String var10000 = this.name();
      return "Catalog[name='" + var10000 + "', " + .MODULE$.apply(this.description()).map((d) -> "description='" + d + "'").getOrElse(() -> "") + "]";
   }

   public CatalogMetadata(final String name, @Nullable final String description) {
      this.name = name;
      this.description = description;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
