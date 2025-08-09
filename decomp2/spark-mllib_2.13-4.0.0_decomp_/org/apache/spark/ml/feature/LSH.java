package org.apache.spark.ml.feature;

import java.io.IOException;
import org.apache.spark.ml.Estimator;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.util.DatasetUtils$;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.StructType;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u00194qa\u0002\u0005\u0002\u0002)\u00112\rC\u00032\u0001\u0011\u0005!\u0007C\u00035\u0001\u0011\u0005Q\u0007C\u0003E\u0001\u0011\u0005Q\tC\u0003H\u0001\u0011\u0005\u0001\n\u0003\u0004N\u0001\u00016\tB\u0014\u0005\u0006#\u0002!\tE\u0015\u0002\u0004\u0019NC%BA\u0005\u000b\u0003\u001d1W-\u0019;ve\u0016T!a\u0003\u0007\u0002\u00055d'BA\u0007\u000f\u0003\u0015\u0019\b/\u0019:l\u0015\ty\u0001#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002#\u0005\u0019qN]4\u0016\u0005MQ2\u0003\u0002\u0001\u0015Q-\u00022!\u0006\f\u0019\u001b\u0005Q\u0011BA\f\u000b\u0005%)5\u000f^5nCR|'\u000f\u0005\u0002\u001a51\u0001A!B\u000e\u0001\u0005\u0004i\"!\u0001+\u0004\u0001E\u0011a\u0004\n\t\u0003?\tj\u0011\u0001\t\u0006\u0002C\u0005)1oY1mC&\u00111\u0005\t\u0002\b\u001d>$\b.\u001b8h!\r)c\u0005G\u0007\u0002\u0011%\u0011q\u0005\u0003\u0002\t\u0019NCUj\u001c3fYB\u0011Q%K\u0005\u0003U!\u0011\u0011\u0002T*I!\u0006\u0014\u0018-\\:\u0011\u00051zS\"A\u0017\u000b\u00059R\u0011\u0001B;uS2L!\u0001M\u0017\u0003+\u0011+g-Y;miB\u000b'/Y7t/JLG/\u00192mK\u00061A(\u001b8jiz\"\u0012a\r\t\u0004K\u0001A\u0012aC:fi&s\u0007/\u001e;D_2$\"AN\u001c\u000e\u0003\u0001AQ\u0001\u000f\u0002A\u0002e\nQA^1mk\u0016\u0004\"AO!\u000f\u0005mz\u0004C\u0001\u001f!\u001b\u0005i$B\u0001 \u001d\u0003\u0019a$o\\8u}%\u0011\u0001\tI\u0001\u0007!J,G-\u001a4\n\u0005\t\u001b%AB*ue&twM\u0003\u0002AA\u0005a1/\u001a;PkR\u0004X\u000f^\"pYR\u0011aG\u0012\u0005\u0006q\r\u0001\r!O\u0001\u0011g\u0016$h*^7ICNDG+\u00192mKN$\"AN%\t\u000ba\"\u0001\u0019\u0001&\u0011\u0005}Y\u0015B\u0001'!\u0005\rIe\u000e^\u0001\u0012GJ,\u0017\r^3SC^d5\u000bS'pI\u0016dGC\u0001\rP\u0011\u0015\u0001V\u00011\u0001K\u0003!Ig\u000e];u\t&l\u0017a\u00014jiR\u0011\u0001d\u0015\u0005\u0006)\u001a\u0001\r!V\u0001\bI\u0006$\u0018m]3ua\t1V\fE\u0002X5rk\u0011\u0001\u0017\u0006\u000332\t1a]9m\u0013\tY\u0006LA\u0004ECR\f7/\u001a;\u0011\u0005eiF!\u00030T\u0003\u0003\u0005\tQ!\u0001`\u0005\u0011yF%\r\u001d\u0012\u0005y\u0001\u0007CA\u0010b\u0013\t\u0011\u0007EA\u0002B]f\u00142\u0001Z\u001a\u0015\r\u0011)\u0007\u0001A2\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e "
)
public abstract class LSH extends Estimator implements LSHParams, DefaultParamsWritable {
   private IntParam numHashTables;
   private Param outputCol;
   private Param inputCol;

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public final int getNumHashTables() {
      return LSHParams.getNumHashTables$(this);
   }

   public final StructType validateAndTransformSchema(final StructType schema) {
      return LSHParams.validateAndTransformSchema$(this, schema);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String getInputCol() {
      return HasInputCol.getInputCol$(this);
   }

   public final IntParam numHashTables() {
      return this.numHashTables;
   }

   public final void org$apache$spark$ml$feature$LSHParams$_setter_$numHashTables_$eq(final IntParam x$1) {
      this.numHashTables = x$1;
   }

   public final Param outputCol() {
      return this.outputCol;
   }

   public final void org$apache$spark$ml$param$shared$HasOutputCol$_setter_$outputCol_$eq(final Param x$1) {
      this.outputCol = x$1;
   }

   public final Param inputCol() {
      return this.inputCol;
   }

   public final void org$apache$spark$ml$param$shared$HasInputCol$_setter_$inputCol_$eq(final Param x$1) {
      this.inputCol = x$1;
   }

   public LSH setInputCol(final String value) {
      return (LSH)this.set(this.inputCol(), value);
   }

   public LSH setOutputCol(final String value) {
      return (LSH)this.set(this.outputCol(), value);
   }

   public LSH setNumHashTables(final int value) {
      return (LSH)this.set(this.numHashTables(), BoxesRunTime.boxToInteger(value));
   }

   public abstract LSHModel createRawLSHModel(final int inputDim);

   public LSHModel fit(final Dataset dataset) {
      this.transformSchema(dataset.schema(), true);
      int inputDim = DatasetUtils$.MODULE$.getNumFeatures(dataset, (String)this.$(this.inputCol()));
      LSHModel model = (LSHModel)this.createRawLSHModel(inputDim).setParent(this);
      return (LSHModel)this.copyValues(model, this.copyValues$default$2());
   }

   public LSH() {
      HasInputCol.$init$(this);
      HasOutputCol.$init$(this);
      LSHParams.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }
}
