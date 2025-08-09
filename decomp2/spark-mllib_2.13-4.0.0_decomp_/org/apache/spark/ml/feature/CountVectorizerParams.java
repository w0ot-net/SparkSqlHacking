package org.apache.spark.ml.feature;

import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.StructType;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\t4\u0001\"\u0004\b\u0011\u0002\u0007\u0005a\u0002\u0007\u0005\u0006]\u0001!\t\u0001\r\u0005\bi\u0001\u0011\r\u0011\"\u00016\u0011\u0015I\u0004\u0001\"\u0001;\u0011\u001dq\u0004A1A\u0005\u0002}BQa\u0011\u0001\u0005\u0002\u0011Cq\u0001\u0013\u0001C\u0002\u0013\u0005q\bC\u0003J\u0001\u0011\u0005A\tC\u0003K\u0001\u0011E1\nC\u0004W\u0001\t\u0007I\u0011A \t\u000b]\u0003A\u0011\u0001#\t\u000fa\u0003!\u0019!C\u00013\")Q\f\u0001C\u0001=\n)2i\\;oiZ+7\r^8sSj,'\u000fU1sC6\u001c(BA\b\u0011\u0003\u001d1W-\u0019;ve\u0016T!!\u0005\n\u0002\u00055d'BA\n\u0015\u0003\u0015\u0019\b/\u0019:l\u0015\t)b#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002/\u0005\u0019qN]4\u0014\u000b\u0001Ir$J\u0016\u0011\u0005iiR\"A\u000e\u000b\u0003q\tQa]2bY\u0006L!AH\u000e\u0003\r\u0005s\u0017PU3g!\t\u00013%D\u0001\"\u0015\t\u0011\u0003#A\u0003qCJ\fW.\u0003\u0002%C\t1\u0001+\u0019:b[N\u0004\"AJ\u0015\u000e\u0003\u001dR!\u0001K\u0011\u0002\rMD\u0017M]3e\u0013\tQsEA\u0006ICNLe\u000e];u\u0007>d\u0007C\u0001\u0014-\u0013\tisE\u0001\u0007ICN|U\u000f\u001e9vi\u000e{G.\u0001\u0004%S:LG\u000fJ\u0002\u0001)\u0005\t\u0004C\u0001\u000e3\u0013\t\u00194D\u0001\u0003V]&$\u0018!\u0003<pG\u0006\u00147+\u001b>f+\u00051\u0004C\u0001\u00118\u0013\tA\u0014E\u0001\u0005J]R\u0004\u0016M]1n\u000319W\r\u001e,pG\u0006\u00147+\u001b>f+\u0005Y\u0004C\u0001\u000e=\u0013\ti4DA\u0002J]R\fQ!\\5o\t\u001a+\u0012\u0001\u0011\t\u0003A\u0005K!AQ\u0011\u0003\u0017\u0011{WO\u00197f!\u0006\u0014\u0018-\\\u0001\tO\u0016$X*\u001b8E\rV\tQ\t\u0005\u0002\u001b\r&\u0011qi\u0007\u0002\u0007\t>,(\r\\3\u0002\u000b5\f\u0007\u0010\u0012$\u0002\u0011\u001d,G/T1y\t\u001a\u000b!D^1mS\u0012\fG/Z!oIR\u0013\u0018M\\:g_Jl7k\u00195f[\u0006$\"\u0001\u0014+\u0011\u00055\u0013V\"\u0001(\u000b\u0005=\u0003\u0016!\u0002;za\u0016\u001c(BA)\u0013\u0003\r\u0019\u0018\u000f\\\u0005\u0003':\u0013!b\u0015;sk\u000e$H+\u001f9f\u0011\u0015)\u0006\u00021\u0001M\u0003\u0019\u00198\r[3nC\u0006)Q.\u001b8U\r\u0006Aq-\u001a;NS:$f)\u0001\u0004cS:\f'/_\u000b\u00025B\u0011\u0001eW\u0005\u00039\u0006\u0012ABQ8pY\u0016\fg\u000eU1sC6\f\u0011bZ3u\u0005&t\u0017M]=\u0016\u0003}\u0003\"A\u00071\n\u0005\u0005\\\"a\u0002\"p_2,\u0017M\u001c"
)
public interface CountVectorizerParams extends HasInputCol, HasOutputCol {
   void org$apache$spark$ml$feature$CountVectorizerParams$_setter_$vocabSize_$eq(final IntParam x$1);

   void org$apache$spark$ml$feature$CountVectorizerParams$_setter_$minDF_$eq(final DoubleParam x$1);

   void org$apache$spark$ml$feature$CountVectorizerParams$_setter_$maxDF_$eq(final DoubleParam x$1);

   void org$apache$spark$ml$feature$CountVectorizerParams$_setter_$minTF_$eq(final DoubleParam x$1);

   void org$apache$spark$ml$feature$CountVectorizerParams$_setter_$binary_$eq(final BooleanParam x$1);

   IntParam vocabSize();

   // $FF: synthetic method
   static int getVocabSize$(final CountVectorizerParams $this) {
      return $this.getVocabSize();
   }

   default int getVocabSize() {
      return BoxesRunTime.unboxToInt(this.$(this.vocabSize()));
   }

   DoubleParam minDF();

   // $FF: synthetic method
   static double getMinDF$(final CountVectorizerParams $this) {
      return $this.getMinDF();
   }

   default double getMinDF() {
      return BoxesRunTime.unboxToDouble(this.$(this.minDF()));
   }

   DoubleParam maxDF();

   // $FF: synthetic method
   static double getMaxDF$(final CountVectorizerParams $this) {
      return $this.getMaxDF();
   }

   default double getMaxDF() {
      return BoxesRunTime.unboxToDouble(this.$(this.maxDF()));
   }

   // $FF: synthetic method
   static StructType validateAndTransformSchema$(final CountVectorizerParams $this, final StructType schema) {
      return $this.validateAndTransformSchema(schema);
   }

   default StructType validateAndTransformSchema(final StructType schema) {
      List typeCandidates = new .colon.colon(new ArrayType(org.apache.spark.sql.types.StringType..MODULE$, true), new .colon.colon(new ArrayType(org.apache.spark.sql.types.StringType..MODULE$, false), scala.collection.immutable.Nil..MODULE$));
      SchemaUtils$.MODULE$.checkColumnTypes(schema, (String)this.$(this.inputCol()), typeCandidates, SchemaUtils$.MODULE$.checkColumnTypes$default$4());
      return SchemaUtils$.MODULE$.appendColumn(schema, (String)this.$(this.outputCol()), new VectorUDT(), SchemaUtils$.MODULE$.appendColumn$default$4());
   }

   DoubleParam minTF();

   // $FF: synthetic method
   static double getMinTF$(final CountVectorizerParams $this) {
      return $this.getMinTF();
   }

   default double getMinTF() {
      return BoxesRunTime.unboxToDouble(this.$(this.minTF()));
   }

   BooleanParam binary();

   // $FF: synthetic method
   static boolean getBinary$(final CountVectorizerParams $this) {
      return $this.getBinary();
   }

   default boolean getBinary() {
      return BoxesRunTime.unboxToBoolean(this.$(this.binary()));
   }

   static void $init$(final CountVectorizerParams $this) {
      $this.org$apache$spark$ml$feature$CountVectorizerParams$_setter_$vocabSize_$eq(new IntParam($this, "vocabSize", "max size of the vocabulary", ParamValidators$.MODULE$.gt((double)0.0F)));
      $this.org$apache$spark$ml$feature$CountVectorizerParams$_setter_$minDF_$eq(new DoubleParam($this, "minDF", "Specifies the minimum number of different documents a term must appear in to be included in the vocabulary. If this is an integer >= 1, this specifies the number of documents the term must appear in; if this is a double in [0,1), then this specifies the fraction of documents.", ParamValidators$.MODULE$.gtEq((double)0.0F)));
      $this.org$apache$spark$ml$feature$CountVectorizerParams$_setter_$maxDF_$eq(new DoubleParam($this, "maxDF", "Specifies the maximum number of different documents a term could appear in to be included in the vocabulary. A term that appears more than the threshold will be ignored. If this is an integer >= 1, this specifies the maximum number of documents the term could appear in; if this is a double in [0,1), then this specifies the maximum fraction of documents the term could appear in.", ParamValidators$.MODULE$.gtEq((double)0.0F)));
      $this.org$apache$spark$ml$feature$CountVectorizerParams$_setter_$minTF_$eq(new DoubleParam($this, "minTF", "Filter to ignore rare words in a document. For each document, terms with frequency/count less than the given threshold are ignored. If this is an integer >= 1, then this specifies a count (of times the term must appear in the document); if this is a double in [0,1), then this specifies a fraction (out of the document's token count). Note that the parameter is only used in transform of CountVectorizerModel and does not affect fitting.", ParamValidators$.MODULE$.gtEq((double)0.0F)));
      $this.org$apache$spark$ml$feature$CountVectorizerParams$_setter_$binary_$eq(new BooleanParam($this, "binary", "If True, all non zero counts are set to 1."));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.vocabSize().$minus$greater(BoxesRunTime.boxToInteger(262144)), $this.minDF().$minus$greater(BoxesRunTime.boxToDouble((double)1.0F)), $this.maxDF().$minus$greater(BoxesRunTime.boxToDouble((double)Long.MAX_VALUE)), $this.minTF().$minus$greater(BoxesRunTime.boxToDouble((double)1.0F)), $this.binary().$minus$greater(BoxesRunTime.boxToBoolean(false))}));
   }
}
