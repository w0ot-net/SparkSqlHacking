package org.apache.spark.ml.source.libsvm;

import java.io.OutputStreamWriter;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.sql.catalyst.InternalRow;
import org.apache.spark.sql.execution.datasources.OutputWriter;
import org.apache.spark.sql.execution.datasources.CodecStreams.;
import org.apache.spark.sql.types.StructType;
import scala.MatchError;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005A4Q\u0001D\u0007\u0001\u001beA\u0001\u0002\n\u0001\u0003\u0006\u0004%\tA\n\u0005\ti\u0001\u0011\t\u0011)A\u0005O!AQ\u0007\u0001B\u0001B\u0003%a\u0007\u0003\u0005=\u0001\t\u0005\t\u0015!\u0003>\u0011\u0015)\u0005\u0001\"\u0001G\u0011\u001da\u0005A1A\u0005\n5CaA\u0016\u0001!\u0002\u0013q\u0005bB,\u0001\u0005\u0004%I\u0001\u0017\u0005\u0007?\u0002\u0001\u000b\u0011B-\t\u000b\u0001\u0004A\u0011I1\t\u000b9\u0004A\u0011I8\u0003%1K'm\u0015,N\u001fV$\b/\u001e;Xe&$XM\u001d\u0006\u0003\u001d=\ta\u0001\\5cgZl'B\u0001\t\u0012\u0003\u0019\u0019x.\u001e:dK*\u0011!cE\u0001\u0003[2T!\u0001F\u000b\u0002\u000bM\u0004\u0018M]6\u000b\u0005Y9\u0012AB1qC\u000eDWMC\u0001\u0019\u0003\ry'oZ\n\u0003\u0001i\u0001\"a\u0007\u0012\u000e\u0003qQ!!\b\u0010\u0002\u0017\u0011\fG/Y:pkJ\u001cWm\u001d\u0006\u0003?\u0001\n\u0011\"\u001a=fGV$\u0018n\u001c8\u000b\u0005\u0005\u001a\u0012aA:rY&\u00111\u0005\b\u0002\r\u001fV$\b/\u001e;Xe&$XM]\u0001\u0005a\u0006$\bn\u0001\u0001\u0016\u0003\u001d\u0002\"\u0001K\u0019\u000f\u0005%z\u0003C\u0001\u0016.\u001b\u0005Y#B\u0001\u0017&\u0003\u0019a$o\\8u})\ta&A\u0003tG\u0006d\u0017-\u0003\u00021[\u00051\u0001K]3eK\u001aL!AM\u001a\u0003\rM#(/\u001b8h\u0015\t\u0001T&A\u0003qCRD\u0007%\u0001\u0006eCR\f7k\u00195f[\u0006\u0004\"a\u000e\u001e\u000e\u0003aR!!\u000f\u0011\u0002\u000bQL\b/Z:\n\u0005mB$AC*ueV\u001cG\u000fV=qK\u000691m\u001c8uKb$\bC\u0001 D\u001b\u0005y$B\u0001!B\u0003%i\u0017\r\u001d:fIV\u001cWM\u0003\u0002C+\u00051\u0001.\u00193p_BL!\u0001R \u0003%Q\u000b7o[!ui\u0016l\u0007\u000f^\"p]R,\u0007\u0010^\u0001\u0007y%t\u0017\u000e\u001e \u0015\t\u001dK%j\u0013\t\u0003\u0011\u0002i\u0011!\u0004\u0005\u0006I\u0015\u0001\ra\n\u0005\u0006k\u0015\u0001\rA\u000e\u0005\u0006y\u0015\u0001\r!P\u0001\u0007oJLG/\u001a:\u0016\u00039\u0003\"a\u0014+\u000e\u0003AS!!\u0015*\u0002\u0005%|'\"A*\u0002\t)\fg/Y\u0005\u0003+B\u0013!cT;uaV$8\u000b\u001e:fC6<&/\u001b;fe\u00069qO]5uKJ\u0004\u0013aA;eiV\t\u0011\f\u0005\u0002[;6\t1L\u0003\u0002]#\u00051A.\u001b8bY\u001eL!AX.\u0003\u0013Y+7\r^8s+\u0012#\u0016\u0001B;ei\u0002\nQa\u001e:ji\u0016$\"A\u00194\u0011\u0005\r$W\"A\u0017\n\u0005\u0015l#\u0001B+oSRDQa\u001a\u0006A\u0002!\f1A]8x!\tIG.D\u0001k\u0015\tY\u0007%\u0001\u0005dCR\fG._:u\u0013\ti'NA\u0006J]R,'O\\1m%><\u0018!B2m_N,G#\u00012"
)
public class LibSVMOutputWriter extends OutputWriter {
   private final String path;
   private final OutputStreamWriter writer;
   private final VectorUDT udt;

   public String path() {
      return this.path;
   }

   private OutputStreamWriter writer() {
      return this.writer;
   }

   private VectorUDT udt() {
      return this.udt;
   }

   public void write(final InternalRow row) {
      double label = row.getDouble(0);
      Vector vector = this.udt().deserialize(row.getStruct(1, this.udt().sqlType().length()));
      this.writer().write(Double.toString(label));
      vector.foreachActive((JFunction2.mcVID.sp)(x0$1, x1$1) -> {
         Tuple2.mcID.sp var5 = new Tuple2.mcID.sp(x0$1, x1$1);
         if (var5 != null) {
            int i = ((Tuple2)var5)._1$mcI$sp();
            double v = ((Tuple2)var5)._2$mcD$sp();
            this.writer().write(" " + (i + 1) + ":" + v);
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            throw new MatchError(var5);
         }
      });
      this.writer().write(10);
   }

   public void close() {
      this.writer().close();
   }

   public LibSVMOutputWriter(final String path, final StructType dataSchema, final TaskAttemptContext context) {
      this.path = path;
      this.writer = .MODULE$.createOutputStreamWriter(context, new Path(path), .MODULE$.createOutputStreamWriter$default$3());
      this.udt = (VectorUDT)dataSchema.apply(1).dataType();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
