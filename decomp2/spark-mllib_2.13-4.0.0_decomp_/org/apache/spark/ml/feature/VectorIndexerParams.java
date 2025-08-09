package org.apache.spark.ml.feature;

import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasHandleInvalid;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasOutputCol;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005I3\u0001\"\u0002\u0004\u0011\u0002\u0007\u0005\u0001\u0002\u0005\u0005\u0006S\u0001!\ta\u000b\u0005\b_\u0001\u0011\r\u0011\"\u00111\u0011\u001dA\u0005A1A\u0005\u0002%CQ!\u0014\u0001\u0005\u00029\u00131CV3di>\u0014\u0018J\u001c3fq\u0016\u0014\b+\u0019:b[NT!a\u0002\u0005\u0002\u000f\u0019,\u0017\r^;sK*\u0011\u0011BC\u0001\u0003[2T!a\u0003\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00055q\u0011AB1qC\u000eDWMC\u0001\u0010\u0003\ry'oZ\n\u0007\u0001E9Rd\t\u0014\u0011\u0005I)R\"A\n\u000b\u0003Q\tQa]2bY\u0006L!AF\n\u0003\r\u0005s\u0017PU3g!\tA2$D\u0001\u001a\u0015\tQ\u0002\"A\u0003qCJ\fW.\u0003\u0002\u001d3\t1\u0001+\u0019:b[N\u0004\"AH\u0011\u000e\u0003}Q!\u0001I\r\u0002\rMD\u0017M]3e\u0013\t\u0011sDA\u0006ICNLe\u000e];u\u0007>d\u0007C\u0001\u0010%\u0013\t)sD\u0001\u0007ICN|U\u000f\u001e9vi\u000e{G\u000e\u0005\u0002\u001fO%\u0011\u0001f\b\u0002\u0011\u0011\u0006\u001c\b*\u00198eY\u0016LeN^1mS\u0012\fa\u0001J5oSR$3\u0001\u0001\u000b\u0002YA\u0011!#L\u0005\u0003]M\u0011A!\u00168ji\u0006i\u0001.\u00198eY\u0016LeN^1mS\u0012,\u0012!\r\t\u00041I\"\u0014BA\u001a\u001a\u0005\u0015\u0001\u0016M]1n!\t)DH\u0004\u00027uA\u0011qgE\u0007\u0002q)\u0011\u0011HK\u0001\u0007yI|w\u000e\u001e \n\u0005m\u001a\u0012A\u0002)sK\u0012,g-\u0003\u0002>}\t11\u000b\u001e:j]\u001eT!aO\n)\u0007\t\u0001e\t\u0005\u0002B\t6\t!I\u0003\u0002D\u0015\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005\u0015\u0013%!B*j]\u000e,\u0017%A$\u0002\u000bIr3G\f\u0019\u0002\u001b5\f\u0007pQ1uK\u001e|'/[3t+\u0005Q\u0005C\u0001\rL\u0013\ta\u0015D\u0001\u0005J]R\u0004\u0016M]1n\u0003A9W\r^'bq\u000e\u000bG/Z4pe&,7/F\u0001P!\t\u0011\u0002+\u0003\u0002R'\t\u0019\u0011J\u001c;"
)
public interface VectorIndexerParams extends HasInputCol, HasOutputCol, HasHandleInvalid {
   void org$apache$spark$ml$feature$VectorIndexerParams$_setter_$handleInvalid_$eq(final Param x$1);

   void org$apache$spark$ml$feature$VectorIndexerParams$_setter_$maxCategories_$eq(final IntParam x$1);

   Param handleInvalid();

   IntParam maxCategories();

   // $FF: synthetic method
   static int getMaxCategories$(final VectorIndexerParams $this) {
      return $this.getMaxCategories();
   }

   default int getMaxCategories() {
      return BoxesRunTime.unboxToInt(this.$(this.maxCategories()));
   }

   static void $init$(final VectorIndexerParams $this) {
      $this.org$apache$spark$ml$feature$VectorIndexerParams$_setter_$handleInvalid_$eq(new Param($this, "handleInvalid", "How to handle invalid data (unseen labels or NULL values). Options are 'skip' (filter out rows with invalid data), 'error' (throw an error), or 'keep' (put invalid data in a special additional bucket, at index of the number of categories of the feature).", ParamValidators$.MODULE$.inArray((Object)VectorIndexer$.MODULE$.supportedHandleInvalids()), .MODULE$.apply(String.class)));
      $this.org$apache$spark$ml$feature$VectorIndexerParams$_setter_$maxCategories_$eq(new IntParam($this, "maxCategories", "Threshold for the number of values a categorical feature can take (>= 2). If a feature is found to have > maxCategories values, then it is declared continuous.", ParamValidators$.MODULE$.gtEq((double)2.0F)));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.maxCategories().$minus$greater(BoxesRunTime.boxToInteger(20)), $this.handleInvalid().$minus$greater(VectorIndexer$.MODULE$.ERROR_INVALID())}));
   }
}
