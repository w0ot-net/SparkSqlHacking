package org.apache.spark.sql.catalog;

import java.lang.invoke.SerializedLambda;
import javax.annotation.Nullable;
import org.apache.spark.annotation.Stable;
import org.apache.spark.sql.catalyst.DefinedByConstructorParams;
import scala.Option.;
import scala.reflect.ScalaSignature;

@Stable
@ScalaSignature(
   bytes = "\u0006\u000514AAE\n\u0001=!A1\u0006\u0001BC\u0002\u0013\u0005A\u0006\u0003\u00059\u0001\t\u0005\t\u0015!\u0003.\u0011!I\u0004A!b\u0001\n\u0003a\u0003\u0002\u0003\u001e\u0001\u0005\u0003\u0005\u000b\u0011B\u0017\t\u0011m\u0002!Q1A\u0005\u00021B\u0001\u0002\u0010\u0001\u0003\u0002\u0003\u0006I!\f\u0005\t{\u0001\u0011)\u0019!C\u0001}!A!\t\u0001B\u0001B\u0003%q\b\u0003\u0005D\u0001\t\u0015\r\u0011\"\u0001?\u0011!!\u0005A!A!\u0002\u0013y\u0004\u0002C#\u0001\u0005\u000b\u0007I\u0011\u0001 \t\u0011\u0019\u0003!\u0011!Q\u0001\n}B\u0001b\u0012\u0001\u0003\u0006\u0004%\tA\u0010\u0005\t\u0011\u0002\u0011\t\u0011)A\u0005\u007f!)\u0011\n\u0001C\u0001\u0015\")\u0011\n\u0001C\u0001;\")A\r\u0001C!K\n11i\u001c7v[:T!\u0001F\u000b\u0002\u000f\r\fG/\u00197pO*\u0011acF\u0001\u0004gFd'B\u0001\r\u001a\u0003\u0015\u0019\b/\u0019:l\u0015\tQ2$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00029\u0005\u0019qN]4\u0004\u0001M\u0019\u0001aH\u0013\u0011\u0005\u0001\u001aS\"A\u0011\u000b\u0003\t\nQa]2bY\u0006L!\u0001J\u0011\u0003\r\u0005s\u0017PU3g!\t1\u0013&D\u0001(\u0015\tAS#\u0001\u0005dCR\fG._:u\u0013\tQsE\u0001\u000eEK\u001aLg.\u001a3Cs\u000e{gn\u001d;sk\u000e$xN\u001d)be\u0006l7/\u0001\u0003oC6,W#A\u0017\u0011\u00059*dBA\u00184!\t\u0001\u0014%D\u00012\u0015\t\u0011T$\u0001\u0004=e>|GOP\u0005\u0003i\u0005\na\u0001\u0015:fI\u00164\u0017B\u0001\u001c8\u0005\u0019\u0019FO]5oO*\u0011A'I\u0001\u0006]\u0006lW\rI\u0001\fI\u0016\u001c8M]5qi&|g.\u0001\u0007eKN\u001c'/\u001b9uS>t\u0007%\u0001\u0005eCR\fG+\u001f9f\u0003%!\u0017\r^1UsB,\u0007%\u0001\u0005ok2d\u0017M\u00197f+\u0005y\u0004C\u0001\u0011A\u0013\t\t\u0015EA\u0004C_>dW-\u00198\u0002\u00139,H\u000e\\1cY\u0016\u0004\u0013aC5t!\u0006\u0014H/\u001b;j_:\fA\"[:QCJ$\u0018\u000e^5p]\u0002\n\u0001\"[:Ck\u000e\\W\r^\u0001\nSN\u0014UoY6fi\u0002\n\u0011\"[:DYV\u001cH/\u001a:\u0002\u0015%\u001c8\t\\;ti\u0016\u0014\b%\u0001\u0004=S:LGO\u0010\u000b\t\u00176s\u0005,\u0017.\\9B\u0011A\nA\u0007\u0002'!)1f\u0004a\u0001[!)\u0011h\u0004a\u0001[!\u0012a\n\u0015\t\u0003#Zk\u0011A\u0015\u0006\u0003'R\u000b!\"\u00198o_R\fG/[8o\u0015\u0005)\u0016!\u00026bm\u0006D\u0018BA,S\u0005!qU\u000f\u001c7bE2,\u0007\"B\u001e\u0010\u0001\u0004i\u0003\"B\u001f\u0010\u0001\u0004y\u0004\"B\"\u0010\u0001\u0004y\u0004\"B#\u0010\u0001\u0004y\u0004\"B$\u0010\u0001\u0004yDcB&_?\u0002\f'm\u0019\u0005\u0006WA\u0001\r!\f\u0005\u0006sA\u0001\r!\f\u0005\u0006wA\u0001\r!\f\u0005\u0006{A\u0001\ra\u0010\u0005\u0006\u0007B\u0001\ra\u0010\u0005\u0006\u000bB\u0001\raP\u0001\ti>\u001cFO]5oOR\tQ\u0006\u000b\u0002\u0001OB\u0011\u0001N[\u0007\u0002S*\u00111kF\u0005\u0003W&\u0014aa\u0015;bE2,\u0007"
)
public class Column implements DefinedByConstructorParams {
   private final String name;
   private final String description;
   private final String dataType;
   private final boolean nullable;
   private final boolean isPartition;
   private final boolean isBucket;
   private final boolean isCluster;

   public String name() {
      return this.name;
   }

   public String description() {
      return this.description;
   }

   public String dataType() {
      return this.dataType;
   }

   public boolean nullable() {
      return this.nullable;
   }

   public boolean isPartition() {
      return this.isPartition;
   }

   public boolean isBucket() {
      return this.isBucket;
   }

   public boolean isCluster() {
      return this.isCluster;
   }

   public String toString() {
      String var10000 = this.name();
      return "Column[name='" + var10000 + "', " + .MODULE$.apply(this.description()).map((d) -> "description='" + d + "', ").getOrElse(() -> "") + "dataType='" + this.dataType() + "', nullable='" + this.nullable() + "', isPartition='" + this.isPartition() + "', isBucket='" + this.isBucket() + "', isCluster='" + this.isCluster() + "']";
   }

   public Column(final String name, @Nullable final String description, final String dataType, final boolean nullable, final boolean isPartition, final boolean isBucket, final boolean isCluster) {
      this.name = name;
      this.description = description;
      this.dataType = dataType;
      this.nullable = nullable;
      this.isPartition = isPartition;
      this.isBucket = isBucket;
      this.isCluster = isCluster;
   }

   public Column(final String name, final String description, final String dataType, final boolean nullable, final boolean isPartition, final boolean isBucket) {
      this(name, description, dataType, nullable, isPartition, isBucket, false);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
