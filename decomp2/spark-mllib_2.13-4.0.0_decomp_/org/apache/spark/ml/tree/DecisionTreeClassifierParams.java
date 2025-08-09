package org.apache.spark.ml.tree;

import org.apache.spark.ml.classification.ProbabilisticClassifierParams;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00153\u0001\u0002B\u0003\u0011\u0002\u0007\u0005qa\u0004\u0005\u0006G\u0001!\t!\n\u0005\u0006S\u0001!\tF\u000b\u0005\f\u007f\u0001\u0001\n1!A\u0001\n\u0013\u0001EI\u0001\u000fEK\u000eL7/[8o)J,Wm\u00117bgNLg-[3s!\u0006\u0014\u0018-\\:\u000b\u0005\u00199\u0011\u0001\u0002;sK\u0016T!\u0001C\u0005\u0002\u00055d'B\u0001\u0006\f\u0003\u0015\u0019\b/\u0019:l\u0015\taQ\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001d\u0005\u0019qN]4\u0014\u000b\u0001\u0001bCG\u000f\u0011\u0005E!R\"\u0001\n\u000b\u0003M\tQa]2bY\u0006L!!\u0006\n\u0003\r\u0005s\u0017PU3g!\t9\u0002$D\u0001\u0006\u0013\tIRA\u0001\nEK\u000eL7/[8o)J,W\rU1sC6\u001c\bCA\f\u001c\u0013\taRA\u0001\u000bUe\u0016,7\t\\1tg&4\u0017.\u001a:QCJ\fWn\u001d\t\u0003=\u0005j\u0011a\b\u0006\u0003A\u001d\tab\u00197bgNLg-[2bi&|g.\u0003\u0002#?\ti\u0002K]8cC\nLG.[:uS\u000e\u001cE.Y:tS\u001aLWM\u001d)be\u0006l7/\u0001\u0004%S:LG\u000fJ\u0002\u0001)\u00051\u0003CA\t(\u0013\tA#C\u0001\u0003V]&$\u0018A\u0007<bY&$\u0017\r^3B]\u0012$&/\u00198tM>\u0014XnU2iK6\fG\u0003B\u00164ki\u0002\"\u0001L\u0019\u000e\u00035R!AL\u0018\u0002\u000bQL\b/Z:\u000b\u0005AJ\u0011aA:rY&\u0011!'\f\u0002\u000b'R\u0014Xo\u0019;UsB,\u0007\"\u0002\u001b\u0003\u0001\u0004Y\u0013AB:dQ\u0016l\u0017\rC\u00037\u0005\u0001\u0007q'A\u0004gSR$\u0018N\\4\u0011\u0005EA\u0014BA\u001d\u0013\u0005\u001d\u0011un\u001c7fC:DQa\u000f\u0002A\u0002q\n\u0001CZ3biV\u0014Xm\u001d#bi\u0006$\u0016\u0010]3\u0011\u00051j\u0014B\u0001 .\u0005!!\u0015\r^1UsB,\u0017\u0001I:va\u0016\u0014HE^1mS\u0012\fG/Z!oIR\u0013\u0018M\\:g_Jl7k\u00195f[\u0006$BaK!C\u0007\")Ag\u0001a\u0001W!)ag\u0001a\u0001o!)1h\u0001a\u0001y%\u0011\u0011&\t"
)
public interface DecisionTreeClassifierParams extends DecisionTreeParams, TreeClassifierParams, ProbabilisticClassifierParams {
   // $FF: synthetic method
   StructType org$apache$spark$ml$tree$DecisionTreeClassifierParams$$super$validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType);

   // $FF: synthetic method
   static StructType validateAndTransformSchema$(final DecisionTreeClassifierParams $this, final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return $this.validateAndTransformSchema(schema, fitting, featuresDataType);
   }

   default StructType validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      StructType outputSchema = this.org$apache$spark$ml$tree$DecisionTreeClassifierParams$$super$validateAndTransformSchema(schema, fitting, featuresDataType);
      if (.MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.leafCol())))) {
         outputSchema = SchemaUtils$.MODULE$.appendColumn(outputSchema, (String)this.$(this.leafCol()), org.apache.spark.sql.types.DoubleType..MODULE$, SchemaUtils$.MODULE$.appendColumn$default$4());
      }

      return outputSchema;
   }

   static void $init$(final DecisionTreeClassifierParams $this) {
   }
}
