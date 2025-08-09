package org.apache.spark.ml.feature;

import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.types.StructType;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\t3\u0001\"\u0002\u0004\u0011\u0002\u0007\u0005a\u0001\u0005\u0005\u0006M\u0001!\t\u0001\u000b\u0005\bY\u0001\u0011\r\u0011\"\u0002.\u0011\u0015\t\u0004\u0001\"\u00013\u0011\u00151\u0004\u0001\"\u00058\u0005\u001dIEI\u0012\"bg\u0016T!a\u0002\u0005\u0002\u000f\u0019,\u0017\r^;sK*\u0011\u0011BC\u0001\u0003[2T!a\u0003\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00055q\u0011AB1qC\u000eDWMC\u0001\u0010\u0003\ry'oZ\n\u0006\u0001E9Rd\t\t\u0003%Ui\u0011a\u0005\u0006\u0002)\u0005)1oY1mC&\u0011ac\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0005aYR\"A\r\u000b\u0005iA\u0011!\u00029be\u0006l\u0017B\u0001\u000f\u001a\u0005\u0019\u0001\u0016M]1ngB\u0011a$I\u0007\u0002?)\u0011\u0001%G\u0001\u0007g\"\f'/\u001a3\n\u0005\tz\"a\u0003%bg&s\u0007/\u001e;D_2\u0004\"A\b\u0013\n\u0005\u0015z\"\u0001\u0004%bg>+H\u000f];u\u0007>d\u0017A\u0002\u0013j]&$He\u0001\u0001\u0015\u0003%\u0002\"A\u0005\u0016\n\u0005-\u001a\"\u0001B+oSR\f!\"\\5o\t>\u001cgI]3r+\u0005q\u0003C\u0001\r0\u0013\t\u0001\u0014D\u0001\u0005J]R\u0004\u0016M]1n\u000359W\r^'j]\u0012{7M\u0012:fcV\t1\u0007\u0005\u0002\u0013i%\u0011Qg\u0005\u0002\u0004\u0013:$\u0018A\u0007<bY&$\u0017\r^3B]\u0012$&/\u00198tM>\u0014XnU2iK6\fGC\u0001\u001dA!\tId(D\u0001;\u0015\tYD(A\u0003usB,7O\u0003\u0002>\u0015\u0005\u00191/\u001d7\n\u0005}R$AC*ueV\u001cG\u000fV=qK\")\u0011\t\u0002a\u0001q\u000511o\u00195f[\u0006\u0004"
)
public interface IDFBase extends HasInputCol, HasOutputCol {
   void org$apache$spark$ml$feature$IDFBase$_setter_$minDocFreq_$eq(final IntParam x$1);

   IntParam minDocFreq();

   // $FF: synthetic method
   static int getMinDocFreq$(final IDFBase $this) {
      return $this.getMinDocFreq();
   }

   default int getMinDocFreq() {
      return BoxesRunTime.unboxToInt(this.$(this.minDocFreq()));
   }

   // $FF: synthetic method
   static StructType validateAndTransformSchema$(final IDFBase $this, final StructType schema) {
      return $this.validateAndTransformSchema(schema);
   }

   default StructType validateAndTransformSchema(final StructType schema) {
      SchemaUtils$.MODULE$.checkColumnType(schema, (String)this.$(this.inputCol()), new VectorUDT(), SchemaUtils$.MODULE$.checkColumnType$default$4());
      return SchemaUtils$.MODULE$.appendColumn(schema, (String)this.$(this.outputCol()), new VectorUDT(), SchemaUtils$.MODULE$.appendColumn$default$4());
   }

   static void $init$(final IDFBase $this) {
      $this.org$apache$spark$ml$feature$IDFBase$_setter_$minDocFreq_$eq(new IntParam($this, "minDocFreq", "minimum number of documents in which a term should appear for filtering (>= 0)", ParamValidators$.MODULE$.gtEq((double)0.0F)));
      $this.setDefault(.MODULE$.wrapRefArray(new ParamPair[]{$this.minDocFreq().$minus$greater(BoxesRunTime.boxToInteger(0))}));
   }
}
