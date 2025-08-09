package org.apache.spark.sql.catalog;

import java.lang.invoke.SerializedLambda;
import javax.annotation.Nullable;
import org.apache.spark.annotation.Stable;
import org.apache.spark.sql.catalyst.DefinedByConstructorParams;
import scala.Option.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@Stable
@ScalaSignature(
   bytes = "\u0006\u000554A!\u0005\n\u0001;!A!\u0006\u0001BC\u0002\u0013\u00051\u0006\u0003\u00058\u0001\t\u0005\t\u0015!\u0003-\u0011!\u0019\u0002A!b\u0001\n\u0003Y\u0003\u0002\u0003\u001d\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0017\t\u0011e\u0002!Q1A\u0005\u0002iB\u0001B\u0010\u0001\u0003\u0002\u0003\u0006Ia\u000f\u0005\t\u007f\u0001\u0011)\u0019!C\u0001W!A\u0001\t\u0001B\u0001B\u0003%A\u0006\u0003\u0005B\u0001\t\u0015\r\u0011\"\u0001,\u0011!\u0011\u0005A!A!\u0002\u0013a\u0003\u0002C\"\u0001\u0005\u000b\u0007I\u0011\u0001#\t\u0011!\u0003!\u0011!Q\u0001\n\u0015CQ!\u0013\u0001\u0005\u0002)CQ!\u0013\u0001\u0005\u0002yCQ!\u0019\u0001\u0005\u0002-BQ!\u001a\u0001\u0005B\u0019\u0014\u0001BR;oGRLwN\u001c\u0006\u0003'Q\tqaY1uC2|wM\u0003\u0002\u0016-\u0005\u00191/\u001d7\u000b\u0005]A\u0012!B:qCJ\\'BA\r\u001b\u0003\u0019\t\u0007/Y2iK*\t1$A\u0002pe\u001e\u001c\u0001aE\u0002\u0001=\u0011\u0002\"a\b\u0012\u000e\u0003\u0001R\u0011!I\u0001\u0006g\u000e\fG.Y\u0005\u0003G\u0001\u0012a!\u00118z%\u00164\u0007CA\u0013)\u001b\u00051#BA\u0014\u0015\u0003!\u0019\u0017\r^1msN$\u0018BA\u0015'\u0005i!UMZ5oK\u0012\u0014\u0015pQ8ogR\u0014Xo\u0019;peB\u000b'/Y7t\u0003\u0011q\u0017-\\3\u0016\u00031\u0002\"!\f\u001b\u000f\u00059\u0012\u0004CA\u0018!\u001b\u0005\u0001$BA\u0019\u001d\u0003\u0019a$o\\8u}%\u00111\u0007I\u0001\u0007!J,G-\u001a4\n\u0005U2$AB*ue&twM\u0003\u00024A\u0005)a.Y7fA\u0005A1-\u0019;bY><\u0007%A\u0005oC6,7\u000f]1dKV\t1\bE\u0002 y1J!!\u0010\u0011\u0003\u000b\u0005\u0013(/Y=\u0002\u00159\fW.Z:qC\u000e,\u0007%A\u0006eKN\u001c'/\u001b9uS>t\u0017\u0001\u00043fg\u000e\u0014\u0018\u000e\u001d;j_:\u0004\u0013!C2mCN\u001ch*Y7f\u0003)\u0019G.Y:t\u001d\u0006lW\rI\u0001\fSN$V-\u001c9pe\u0006\u0014\u00180F\u0001F!\tyb)\u0003\u0002HA\t9!i\\8mK\u0006t\u0017\u0001D5t)\u0016l\u0007o\u001c:bef\u0004\u0013A\u0002\u001fj]&$h\bF\u0004L\u001b:C&\fX/\u0011\u00051\u0003Q\"\u0001\n\t\u000b)j\u0001\u0019\u0001\u0017\t\u000bMi\u0001\u0019\u0001\u0017)\u00059\u0003\u0006CA)W\u001b\u0005\u0011&BA*U\u0003)\tgN\\8uCRLwN\u001c\u0006\u0002+\u0006)!.\u0019<bq&\u0011qK\u0015\u0002\t\u001dVdG.\u00192mK\")\u0011(\u0004a\u0001w!\u0012\u0001\f\u0015\u0005\u0006\u007f5\u0001\r\u0001\f\u0015\u00035BCQ!Q\u0007A\u00021BQaQ\u0007A\u0002\u0015#baS0aE\u000e$\u0007\"\u0002\u0016\u000f\u0001\u0004a\u0003\"B1\u000f\u0001\u0004a\u0013\u0001\u00033bi\u0006\u0014\u0017m]3\t\u000b}r\u0001\u0019\u0001\u0017\t\u000b\u0005s\u0001\u0019\u0001\u0017\t\u000b\rs\u0001\u0019A#\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012\u0001\f\u0015\u0003\u0001!\u0004\"![6\u000e\u0003)T!a\u0015\f\n\u00051T'AB*uC\ndW\r"
)
public class Function implements DefinedByConstructorParams {
   private final String name;
   private final String catalog;
   private final String[] namespace;
   private final String description;
   private final String className;
   private final boolean isTemporary;

   public String name() {
      return this.name;
   }

   public String catalog() {
      return this.catalog;
   }

   public String[] namespace() {
      return this.namespace;
   }

   public String description() {
      return this.description;
   }

   public String className() {
      return this.className;
   }

   public boolean isTemporary() {
      return this.isTemporary;
   }

   public String database() {
      return this.namespace() != null && this.namespace().length == 1 ? this.namespace()[0] : null;
   }

   public String toString() {
      String var10000 = this.name();
      return "Function[name='" + var10000 + "', " + .MODULE$.apply(this.database()).map((d) -> "database='" + d + "', ").getOrElse(() -> "") + .MODULE$.apply(this.description()).map((d) -> "description='" + d + "', ").getOrElse(() -> "") + "className='" + this.className() + "', isTemporary='" + this.isTemporary() + "']";
   }

   // $FF: synthetic method
   public static final boolean $anonfun$new$2(final String x$2) {
      return x$2 != null;
   }

   public Function(final String name, @Nullable final String catalog, @Nullable final String[] namespace, @Nullable final String description, final String className, final boolean isTemporary) {
      this.name = name;
      this.catalog = catalog;
      this.namespace = namespace;
      this.description = description;
      this.className = className;
      this.isTemporary = isTemporary;
      if (namespace != null) {
         scala.Predef..MODULE$.assert(scala.collection.ArrayOps..MODULE$.forall$extension(scala.Predef..MODULE$.refArrayOps((Object[])namespace), (x$2) -> BoxesRunTime.boxToBoolean($anonfun$new$2(x$2))));
      }

   }

   public Function(final String name, final String database, final String description, final String className, final boolean isTemporary) {
      this(name, (String)null, database != null ? (String[])((Object[])(new String[]{database})) : null, description, className, isTemporary);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
