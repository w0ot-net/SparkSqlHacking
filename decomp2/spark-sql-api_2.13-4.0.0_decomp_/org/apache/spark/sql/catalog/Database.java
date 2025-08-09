package org.apache.spark.sql.catalog;

import java.lang.invoke.SerializedLambda;
import javax.annotation.Nullable;
import org.apache.spark.annotation.Stable;
import org.apache.spark.sql.catalyst.DefinedByConstructorParams;
import scala.Option.;
import scala.reflect.ScalaSignature;

@Stable
@ScalaSignature(
   bytes = "\u0006\u0005Y3A\u0001D\u0007\u00011!AQ\u0005\u0001BC\u0002\u0013\u0005a\u0005\u0003\u00053\u0001\t\u0005\t\u0015!\u0003(\u0011!q\u0001A!b\u0001\n\u00031\u0003\u0002C\u001a\u0001\u0005\u0003\u0005\u000b\u0011B\u0014\t\u0011Q\u0002!Q1A\u0005\u0002\u0019B\u0001\"\u000e\u0001\u0003\u0002\u0003\u0006Ia\n\u0005\tm\u0001\u0011)\u0019!C\u0001M!Aq\u0007\u0001B\u0001B\u0003%q\u0005C\u00039\u0001\u0011\u0005\u0011\bC\u00039\u0001\u0011\u0005!\nC\u0003O\u0001\u0011\u0005sJ\u0001\u0005ECR\f'-Y:f\u0015\tqq\"A\u0004dCR\fGn\\4\u000b\u0005A\t\u0012aA:rY*\u0011!cE\u0001\u0006gB\f'o\u001b\u0006\u0003)U\ta!\u00199bG\",'\"\u0001\f\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001Ir\u0004\u0005\u0002\u001b;5\t1DC\u0001\u001d\u0003\u0015\u00198-\u00197b\u0013\tq2D\u0001\u0004B]f\u0014VM\u001a\t\u0003A\rj\u0011!\t\u0006\u0003E=\t\u0001bY1uC2L8\u000f^\u0005\u0003I\u0005\u0012!\u0004R3gS:,GMQ=D_:\u001cHO];di>\u0014\b+\u0019:b[N\fAA\\1nKV\tq\u0005\u0005\u0002)_9\u0011\u0011&\f\t\u0003Umi\u0011a\u000b\u0006\u0003Y]\ta\u0001\u0010:p_Rt\u0014B\u0001\u0018\u001c\u0003\u0019\u0001&/\u001a3fM&\u0011\u0001'\r\u0002\u0007'R\u0014\u0018N\\4\u000b\u00059Z\u0012!\u00028b[\u0016\u0004\u0013\u0001C2bi\u0006dwn\u001a\u0011\u0002\u0017\u0011,7o\u0019:jaRLwN\\\u0001\rI\u0016\u001c8M]5qi&|g\u000eI\u0001\fY>\u001c\u0017\r^5p]V\u0013\u0018.\u0001\u0007m_\u000e\fG/[8o+JL\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0006uqjt)\u0013\t\u0003w\u0001i\u0011!\u0004\u0005\u0006K%\u0001\ra\n\u0005\u0006\u001d%\u0001\ra\n\u0015\u0003{}\u0002\"\u0001Q#\u000e\u0003\u0005S!AQ\"\u0002\u0015\u0005tgn\u001c;bi&|gNC\u0001E\u0003\u0015Q\u0017M^1y\u0013\t1\u0015I\u0001\u0005Ok2d\u0017M\u00197f\u0011\u0015!\u0014\u00021\u0001(Q\t9u\bC\u00037\u0013\u0001\u0007q\u0005\u0006\u0003;\u00172k\u0005\"B\u0013\u000b\u0001\u00049\u0003\"\u0002\u001b\u000b\u0001\u00049\u0003\"\u0002\u001c\u000b\u0001\u00049\u0013\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003\u001dB#\u0001A)\u0011\u0005I#V\"A*\u000b\u0005\t\u000b\u0012BA+T\u0005\u0019\u0019F/\u00192mK\u0002"
)
public class Database implements DefinedByConstructorParams {
   private final String name;
   private final String catalog;
   private final String description;
   private final String locationUri;

   public String name() {
      return this.name;
   }

   public String catalog() {
      return this.catalog;
   }

   public String description() {
      return this.description;
   }

   public String locationUri() {
      return this.locationUri;
   }

   public String toString() {
      String var10000 = this.name();
      return "Database[name='" + var10000 + "', " + .MODULE$.apply(this.catalog()).map((c) -> "catalog='" + c + "', ").getOrElse(() -> "") + .MODULE$.apply(this.description()).map((d) -> "description='" + d + "', ").getOrElse(() -> "") + "path='" + this.locationUri() + "']";
   }

   public Database(final String name, @Nullable final String catalog, @Nullable final String description, final String locationUri) {
      this.name = name;
      this.catalog = catalog;
      this.description = description;
      this.locationUri = locationUri;
   }

   public Database(final String name, final String description, final String locationUri) {
      this(name, (String)null, description, locationUri);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
