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
   bytes = "\u0006\u000554A!\u0005\n\u0001;!A!\u0006\u0001BC\u0002\u0013\u00051\u0006\u0003\u00058\u0001\t\u0005\t\u0015!\u0003-\u0011!\u0019\u0002A!b\u0001\n\u0003Y\u0003\u0002\u0003\u001d\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0017\t\u0011e\u0002!Q1A\u0005\u0002iB\u0001B\u0010\u0001\u0003\u0002\u0003\u0006Ia\u000f\u0005\t\u007f\u0001\u0011)\u0019!C\u0001W!A\u0001\t\u0001B\u0001B\u0003%A\u0006\u0003\u0005B\u0001\t\u0015\r\u0011\"\u0001,\u0011!\u0011\u0005A!A!\u0002\u0013a\u0003\u0002C\"\u0001\u0005\u000b\u0007I\u0011\u0001#\t\u0011!\u0003!\u0011!Q\u0001\n\u0015CQ!\u0013\u0001\u0005\u0002)CQ!\u0013\u0001\u0005\u0002yCQ!\u0019\u0001\u0005\u0002-BQ!\u001a\u0001\u0005B\u0019\u0014Q\u0001V1cY\u0016T!a\u0005\u000b\u0002\u000f\r\fG/\u00197pO*\u0011QCF\u0001\u0004gFd'BA\f\u0019\u0003\u0015\u0019\b/\u0019:l\u0015\tI\"$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00027\u0005\u0019qN]4\u0004\u0001M\u0019\u0001A\b\u0013\u0011\u0005}\u0011S\"\u0001\u0011\u000b\u0003\u0005\nQa]2bY\u0006L!a\t\u0011\u0003\r\u0005s\u0017PU3g!\t)\u0003&D\u0001'\u0015\t9C#\u0001\u0005dCR\fG._:u\u0013\tIcE\u0001\u000eEK\u001aLg.\u001a3Cs\u000e{gn\u001d;sk\u000e$xN\u001d)be\u0006l7/\u0001\u0003oC6,W#\u0001\u0017\u0011\u00055\"dB\u0001\u00183!\ty\u0003%D\u00011\u0015\t\tD$\u0001\u0004=e>|GOP\u0005\u0003g\u0001\na\u0001\u0015:fI\u00164\u0017BA\u001b7\u0005\u0019\u0019FO]5oO*\u00111\u0007I\u0001\u0006]\u0006lW\rI\u0001\tG\u0006$\u0018\r\\8hA\u0005Ia.Y7fgB\f7-Z\u000b\u0002wA\u0019q\u0004\u0010\u0017\n\u0005u\u0002#!B!se\u0006L\u0018A\u00038b[\u0016\u001c\b/Y2fA\u0005YA-Z:de&\u0004H/[8o\u00031!Wm]2sSB$\u0018n\u001c8!\u0003%!\u0018M\u00197f)f\u0004X-\u0001\u0006uC\ndW\rV=qK\u0002\n1\"[:UK6\u0004xN]1ssV\tQ\t\u0005\u0002 \r&\u0011q\t\t\u0002\b\u0005>|G.Z1o\u00031I7\u000fV3na>\u0014\u0018M]=!\u0003\u0019a\u0014N\\5u}Q91*\u0014(Y5rk\u0006C\u0001'\u0001\u001b\u0005\u0011\u0002\"\u0002\u0016\u000e\u0001\u0004a\u0003\"B\n\u000e\u0001\u0004a\u0003F\u0001(Q!\t\tf+D\u0001S\u0015\t\u0019F+\u0001\u0006b]:|G/\u0019;j_:T\u0011!V\u0001\u0006U\u00064\u0018\r_\u0005\u0003/J\u0013\u0001BT;mY\u0006\u0014G.\u001a\u0005\u0006s5\u0001\ra\u000f\u0015\u00031BCQaP\u0007A\u00021B#A\u0017)\t\u000b\u0005k\u0001\u0019\u0001\u0017\t\u000b\rk\u0001\u0019A#\u0015\r-{\u0006MY2e\u0011\u0015Qc\u00021\u0001-\u0011\u0015\tg\u00021\u0001-\u0003!!\u0017\r^1cCN,\u0007\"B \u000f\u0001\u0004a\u0003\"B!\u000f\u0001\u0004a\u0003\"B\"\u000f\u0001\u0004)\u0015\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u00031B#\u0001\u00015\u0011\u0005%\\W\"\u00016\u000b\u0005M3\u0012B\u00017k\u0005\u0019\u0019F/\u00192mK\u0002"
)
public class Table implements DefinedByConstructorParams {
   private final String name;
   private final String catalog;
   private final String[] namespace;
   private final String description;
   private final String tableType;
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

   public String tableType() {
      return this.tableType;
   }

   public boolean isTemporary() {
      return this.isTemporary;
   }

   public String database() {
      return this.namespace() != null && this.namespace().length == 1 ? this.namespace()[0] : null;
   }

   public String toString() {
      String var10000 = this.name();
      return "Table[name='" + var10000 + "', " + .MODULE$.apply(this.catalog()).map((d) -> "catalog='" + d + "', ").getOrElse(() -> "") + .MODULE$.apply(this.database()).map((d) -> "database='" + d + "', ").getOrElse(() -> "") + .MODULE$.apply(this.description()).map((d) -> "description='" + d + "', ").getOrElse(() -> "") + "tableType='" + this.tableType() + "', isTemporary='" + this.isTemporary() + "']";
   }

   // $FF: synthetic method
   public static final boolean $anonfun$new$1(final String x$1) {
      return x$1 != null;
   }

   public Table(final String name, @Nullable final String catalog, @Nullable final String[] namespace, @Nullable final String description, final String tableType, final boolean isTemporary) {
      this.name = name;
      this.catalog = catalog;
      this.namespace = namespace;
      this.description = description;
      this.tableType = tableType;
      this.isTemporary = isTemporary;
      if (namespace != null) {
         scala.Predef..MODULE$.assert(scala.collection.ArrayOps..MODULE$.forall$extension(scala.Predef..MODULE$.refArrayOps((Object[])namespace), (x$1) -> BoxesRunTime.boxToBoolean($anonfun$new$1(x$1))));
      }

   }

   public Table(final String name, final String database, final String description, final String tableType, final boolean isTemporary) {
      this(name, (String)null, database != null ? (String[])((Object[])(new String[]{database})) : null, description, tableType, isTemporary);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
