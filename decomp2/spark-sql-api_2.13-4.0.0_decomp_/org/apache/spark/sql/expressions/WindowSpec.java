package org.apache.spark.sql.expressions;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.Stable;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Column$;
import org.apache.spark.sql.errors.CompilationErrors$;
import org.apache.spark.sql.internal.WindowFrame;
import org.apache.spark.sql.internal.WindowFrame$;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.collection.IterableOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime.;

@Stable
@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eb\u0001B\u0007\u000f\u0001eA\u0001\u0002\t\u0001\u0003\u0002\u0003\u0006I!\t\u0005\tg\u0001\u0011\t\u0011)A\u0005i!A\u0001\b\u0001B\u0001B\u0003%\u0011\b\u0003\u0004@\u0001\u0011\u0005\u0001\u0003\u0011\u0005\u0006\r\u0002!\ta\u0012\u0005\u0006\r\u0002!\tA\u0018\u0005\u0006O\u0002!\t\u0001\u001b\u0005\u0006O\u0002!\t\u0001\u001c\u0005\u0006_\u0002!\t\u0001\u001d\u0005\u0006q\u0002!\t!\u001f\u0005\u0007y\u0002!\t\u0001E?\t\u0011\u0005u\u0001\u0001\"\u0001\u0011\u0003?\u0011!bV5oI><8\u000b]3d\u0015\ty\u0001#A\u0006fqB\u0014Xm]:j_:\u001c(BA\t\u0013\u0003\r\u0019\u0018\u000f\u001c\u0006\u0003'Q\tQa\u001d9be.T!!\u0006\f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u00059\u0012aA8sO\u000e\u00011C\u0001\u0001\u001b!\tYb$D\u0001\u001d\u0015\u0005i\u0012!B:dC2\f\u0017BA\u0010\u001d\u0005\u0019\te.\u001f*fM\u0006i\u0001/\u0019:uSRLwN\\*qK\u000e\u00042A\t\u0016.\u001d\t\u0019\u0003F\u0004\u0002%O5\tQE\u0003\u0002'1\u00051AH]8pizJ\u0011!H\u0005\u0003Sq\tq\u0001]1dW\u0006<W-\u0003\u0002,Y\t\u00191+Z9\u000b\u0005%b\u0002C\u0001\u00182\u001b\u0005y#B\u0001\u0019\u0011\u0003!Ig\u000e^3s]\u0006d\u0017B\u0001\u001a0\u0005)\u0019u\u000e\\;n]:{G-Z\u0001\n_J$WM]*qK\u000e\u00042A\t\u00166!\tqc'\u0003\u00028_\tI1k\u001c:u\u001fJ$WM]\u0001\u0006MJ\fW.\u001a\t\u00047ib\u0014BA\u001e\u001d\u0005\u0019y\u0005\u000f^5p]B\u0011a&P\u0005\u0003}=\u00121bV5oI><hI]1nK\u00061A(\u001b8jiz\"B!Q\"E\u000bB\u0011!\tA\u0007\u0002\u001d!)\u0001\u0005\u0002a\u0001C!)1\u0007\u0002a\u0001i!)\u0001\b\u0002a\u0001s\u0005Y\u0001/\u0019:uSRLwN\u001c\"z)\r\t\u0005J\u0015\u0005\u0006\u0013\u0016\u0001\rAS\u0001\bG>dg*Y7f!\tYuJ\u0004\u0002M\u001bB\u0011A\u0005H\u0005\u0003\u001dr\ta\u0001\u0015:fI\u00164\u0017B\u0001)R\u0005\u0019\u0019FO]5oO*\u0011a\n\b\u0005\u0006'\u0016\u0001\r\u0001V\u0001\tG>dg*Y7fgB\u00191$\u0016&\n\u0005Yc\"A\u0003\u001fsKB,\u0017\r^3e}!\u0012Q\u0001\u0017\t\u00033rk\u0011A\u0017\u0006\u00037r\t!\"\u00198o_R\fG/[8o\u0013\ti&LA\u0004wCJ\f'oZ:\u0015\u0005\u0005{\u0006\"\u00021\u0007\u0001\u0004\t\u0017\u0001B2pYN\u00042aG+c!\t\u0019G-D\u0001\u0011\u0013\t)\u0007C\u0001\u0004D_2,XN\u001c\u0015\u0003\ra\u000bqa\u001c:eKJ\u0014\u0015\u0010F\u0002BS*DQ!S\u0004A\u0002)CQaU\u0004A\u0002QC#a\u0002-\u0015\u0005\u0005k\u0007\"\u00021\t\u0001\u0004\t\u0007F\u0001\u0005Y\u0003-\u0011xn^:CKR<X-\u001a8\u0015\u0007\u0005\u000bh\u000fC\u0003s\u0013\u0001\u00071/A\u0003ti\u0006\u0014H\u000f\u0005\u0002\u001ci&\u0011Q\u000f\b\u0002\u0005\u0019>tw\rC\u0003x\u0013\u0001\u00071/A\u0002f]\u0012\fAB]1oO\u0016\u0014U\r^<fK:$2!\u0011>|\u0011\u0015\u0011(\u00021\u0001t\u0011\u00159(\u00021\u0001t\u0003%9\u0018\u000e\u001e5Ge\u0006lW\r\u0006\u0004B}\u0006=\u0011\u0011\u0004\u0005\u0007\u007f.\u0001\r!!\u0001\u0002\u0013\u0019\u0014\u0018-\\3UsB,\u0007\u0003BA\u0002\u0003\u0013q1ALA\u0003\u0013\r\t9aL\u0001\f/&tGm\\<Ge\u0006lW-\u0003\u0003\u0002\f\u00055!!\u0003$sC6,G+\u001f9f\u0015\r\t9a\f\u0005\b\u0003#Y\u0001\u0019AA\n\u0003\u0015awn^3s!\u0011\t\u0019!!\u0006\n\t\u0005]\u0011Q\u0002\u0002\u000e\rJ\fW.\u001a\"pk:$\u0017M]=\t\u000f\u0005m1\u00021\u0001\u0002\u0014\u00051Q\u000f\u001d9qKJ\fQb^5uQ\u0006;wM]3hCR,Gc\u00012\u0002\"!1\u00111\u0005\u0007A\u0002\t\f\u0011\"Y4he\u0016<\u0017\r^3)\u0007\u0001\t9\u0003\u0005\u0003\u0002*\u00055RBAA\u0016\u0015\tY&#\u0003\u0003\u00020\u0005-\"AB*uC\ndW\r"
)
public class WindowSpec {
   private final Seq partitionSpec;
   private final Seq orderSpec;
   private final Option frame;

   public WindowSpec partitionBy(final String colName, final String... colNames) {
      return this.partitionBy(colName, (Seq).MODULE$.wrapRefArray((Object[])colNames));
   }

   public WindowSpec partitionBy(final Column... cols) {
      return this.partitionBy((Seq).MODULE$.wrapRefArray(cols));
   }

   public WindowSpec orderBy(final String colName, final String... colNames) {
      return this.orderBy(colName, (Seq).MODULE$.wrapRefArray((Object[])colNames));
   }

   public WindowSpec orderBy(final Column... cols) {
      return this.orderBy((Seq).MODULE$.wrapRefArray(cols));
   }

   public WindowSpec partitionBy(final String colName, final Seq colNames) {
      return this.partitionBy((Seq)((IterableOps)colNames.$plus$colon(colName)).map((x$1) -> Column$.MODULE$.apply(x$1)));
   }

   public WindowSpec partitionBy(final Seq cols) {
      return new WindowSpec((Seq)cols.map((x$2) -> x$2.node()), this.orderSpec, this.frame);
   }

   public WindowSpec orderBy(final String colName, final Seq colNames) {
      return this.orderBy((Seq)((IterableOps)colNames.$plus$colon(colName)).map((x$3) -> Column$.MODULE$.apply(x$3)));
   }

   public WindowSpec orderBy(final Seq cols) {
      return new WindowSpec(this.partitionSpec, (Seq)cols.map((x$4) -> x$4.sortOrder()), this.frame);
   }

   public WindowSpec rowsBetween(final long start, final long end) {
      Object var10000;
      if (0L == start) {
         var10000 = WindowFrame.CurrentRow$.MODULE$;
      } else if (Long.MIN_VALUE == start) {
         var10000 = WindowFrame.UnboundedPreceding$.MODULE$;
      } else {
         if (-2147483648L > start || start > 2147483647L) {
            throw CompilationErrors$.MODULE$.invalidBoundaryStartError(start);
         }

         var10000 = WindowFrame$.MODULE$.value((int)start);
      }

      WindowFrame.FrameBoundary boundaryStart = (WindowFrame.FrameBoundary)var10000;
      if (0L == end) {
         var10000 = WindowFrame.CurrentRow$.MODULE$;
      } else if (Long.MAX_VALUE == end) {
         var10000 = WindowFrame.UnboundedFollowing$.MODULE$;
      } else {
         if (-2147483648L > end || end > 2147483647L) {
            throw CompilationErrors$.MODULE$.invalidBoundaryEndError(end);
         }

         var10000 = WindowFrame$.MODULE$.value((int)end);
      }

      WindowFrame.FrameBoundary boundaryEnd = (WindowFrame.FrameBoundary)var10000;
      return this.withFrame(WindowFrame.Row$.MODULE$, boundaryStart, boundaryEnd);
   }

   public WindowSpec rangeBetween(final long start, final long end) {
      WindowFrame.FrameBoundary boundaryStart = (WindowFrame.FrameBoundary)(0L == start ? WindowFrame.CurrentRow$.MODULE$ : (Long.MIN_VALUE == start ? WindowFrame.UnboundedPreceding$.MODULE$ : WindowFrame$.MODULE$.value(start)));
      WindowFrame.FrameBoundary boundaryEnd = (WindowFrame.FrameBoundary)(0L == end ? WindowFrame.CurrentRow$.MODULE$ : (Long.MAX_VALUE == end ? WindowFrame.UnboundedFollowing$.MODULE$ : WindowFrame$.MODULE$.value(end)));
      return this.withFrame(WindowFrame.Range$.MODULE$, boundaryStart, boundaryEnd);
   }

   public WindowSpec withFrame(final WindowFrame.FrameType frameType, final WindowFrame.FrameBoundary lower, final WindowFrame.FrameBoundary uppper) {
      WindowFrame frame = new WindowFrame(frameType, lower, uppper);
      return new WindowSpec(this.partitionSpec, this.orderSpec, new Some(frame));
   }

   public Column withAggregate(final Column aggregate) {
      org.apache.spark.sql.internal.WindowSpec spec = new org.apache.spark.sql.internal.WindowSpec(this.partitionSpec, this.orderSpec, this.frame);
      return Column$.MODULE$.apply((Function0)(() -> new org.apache.spark.sql.internal.Window(aggregate.node(), spec, org.apache.spark.sql.internal.Window$.MODULE$.apply$default$3())));
   }

   public WindowSpec(final Seq partitionSpec, final Seq orderSpec, final Option frame) {
      this.partitionSpec = partitionSpec;
      this.orderSpec = orderSpec;
      this.frame = frame;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
