package org.apache.spark.ml.feature;

import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005}2\u0001\"\u0002\u0004\u0011\u0002\u0007\u0005\u0001\u0002\u0005\u0005\u0006E\u0001!\t\u0001\n\u0005\bQ\u0001\u0011\r\u0011\"\u0002*\u0011\u0015q\u0003\u0001\"\u00020\u0011\u0019\u0019\u0004\u0001)C\u000bi\tIAj\u0015%QCJ\fWn\u001d\u0006\u0003\u000f!\tqAZ3biV\u0014XM\u0003\u0002\n\u0015\u0005\u0011Q\u000e\u001c\u0006\u0003\u00171\tQa\u001d9be.T!!\u0004\b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005y\u0011aA8sON!\u0001!E\f !\t\u0011R#D\u0001\u0014\u0015\u0005!\u0012!B:dC2\f\u0017B\u0001\f\u0014\u0005\u0019\te.\u001f*fMB\u0011\u0001$H\u0007\u00023)\u0011!dG\u0001\u0007g\"\f'/\u001a3\u000b\u0005qA\u0011!\u00029be\u0006l\u0017B\u0001\u0010\u001a\u0005-A\u0015m]%oaV$8i\u001c7\u0011\u0005a\u0001\u0013BA\u0011\u001a\u00051A\u0015m](viB,HoQ8m\u0003\u0019!\u0013N\\5uI\r\u0001A#A\u0013\u0011\u0005I1\u0013BA\u0014\u0014\u0005\u0011)f.\u001b;\u0002\u001b9,X\u000eS1tQR\u000b'\r\\3t+\u0005Q\u0003CA\u0016-\u001b\u0005Y\u0012BA\u0017\u001c\u0005!Ie\u000e\u001e)be\u0006l\u0017\u0001E4fi:+X\u000eS1tQR\u000b'\r\\3t+\u0005\u0001\u0004C\u0001\n2\u0013\t\u00114CA\u0002J]R\f!D^1mS\u0012\fG/Z!oIR\u0013\u0018M\\:g_Jl7k\u00195f[\u0006$\"!N\u001f\u0011\u0005YZT\"A\u001c\u000b\u0005aJ\u0014!\u0002;za\u0016\u001c(B\u0001\u001e\u000b\u0003\r\u0019\u0018\u000f\\\u0005\u0003y]\u0012!b\u0015;sk\u000e$H+\u001f9f\u0011\u0015qD\u00011\u00016\u0003\u0019\u00198\r[3nC\u0002"
)
public interface LSHParams extends HasInputCol, HasOutputCol {
   void org$apache$spark$ml$feature$LSHParams$_setter_$numHashTables_$eq(final IntParam x$1);

   IntParam numHashTables();

   // $FF: synthetic method
   static int getNumHashTables$(final LSHParams $this) {
      return $this.getNumHashTables();
   }

   default int getNumHashTables() {
      return BoxesRunTime.unboxToInt(this.$(this.numHashTables()));
   }

   // $FF: synthetic method
   static StructType validateAndTransformSchema$(final LSHParams $this, final StructType schema) {
      return $this.validateAndTransformSchema(schema);
   }

   default StructType validateAndTransformSchema(final StructType schema) {
      return SchemaUtils$.MODULE$.appendColumn(schema, (String)this.$(this.outputCol()), DataTypes.createArrayType(new VectorUDT()), SchemaUtils$.MODULE$.appendColumn$default$4());
   }

   static void $init$(final LSHParams $this) {
      $this.org$apache$spark$ml$feature$LSHParams$_setter_$numHashTables_$eq(new IntParam($this, "numHashTables", "number of hash tables, where increasing number of hash tables lowers the false negative rate, and decreasing it improves the running performance", ParamValidators$.MODULE$.gt((double)0.0F)));
      $this.setDefault(.MODULE$.wrapRefArray(new ParamPair[]{$this.numHashTables().$minus$greater(BoxesRunTime.boxToInteger(1))}));
   }
}
