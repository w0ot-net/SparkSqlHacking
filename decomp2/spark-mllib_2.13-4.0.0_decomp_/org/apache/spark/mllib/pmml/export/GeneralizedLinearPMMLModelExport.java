package org.apache.spark.mllib.pmml.export;

import java.lang.invoke.SerializedLambda;
import java.time.format.DateTimeFormatter;
import org.apache.spark.mllib.regression.GeneralizedLinearModel;
import org.sparkproject.dmg.pmml.DataDictionary;
import org.sparkproject.dmg.pmml.DataField;
import org.sparkproject.dmg.pmml.DataType;
import org.sparkproject.dmg.pmml.MiningField;
import org.sparkproject.dmg.pmml.MiningFunction;
import org.sparkproject.dmg.pmml.MiningSchema;
import org.sparkproject.dmg.pmml.OpType;
import org.sparkproject.dmg.pmml.PMML;
import org.sparkproject.dmg.pmml.regression.NumericPredictor;
import org.sparkproject.dmg.pmml.regression.RegressionModel;
import org.sparkproject.dmg.pmml.regression.RegressionTable;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005q2Q!\u0002\u0004\u0001\u0015IA\u0001\"\b\u0001\u0003\u0002\u0003\u0006Ia\b\u0005\tK\u0001\u0011\t\u0011)A\u0005M!)\u0011\u0007\u0001C\u0001e!)a\u0007\u0001C\u0005o\t\u0001s)\u001a8fe\u0006d\u0017N_3e\u0019&tW-\u0019:Q\u001b6cUj\u001c3fY\u0016C\bo\u001c:u\u0015\t9\u0001\"\u0001\u0004fqB|'\u000f\u001e\u0006\u0003\u0013)\tA\u0001]7nY*\u00111\u0002D\u0001\u0006[2d\u0017N\u0019\u0006\u0003\u001b9\tQa\u001d9be.T!a\u0004\t\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\t\u0012aA8sON\u0019\u0001aE\r\u0011\u0005Q9R\"A\u000b\u000b\u0003Y\tQa]2bY\u0006L!\u0001G\u000b\u0003\r\u0005s\u0017PU3g!\tQ2$D\u0001\u0007\u0013\tabAA\bQ\u001b6cUj\u001c3fY\u0016C\bo\u001c:u\u0003\u0015iw\u000eZ3m\u0007\u0001\u0001\"\u0001I\u0012\u000e\u0003\u0005R!A\t\u0006\u0002\u0015I,wM]3tg&|g.\u0003\u0002%C\t1r)\u001a8fe\u0006d\u0017N_3e\u0019&tW-\u0019:N_\u0012,G.A\u0006eKN\u001c'/\u001b9uS>t\u0007CA\u0014/\u001d\tAC\u0006\u0005\u0002*+5\t!F\u0003\u0002,=\u00051AH]8pizJ!!L\u000b\u0002\rA\u0013X\rZ3g\u0013\ty\u0003G\u0001\u0004TiJLgn\u001a\u0006\u0003[U\ta\u0001P5oSRtDcA\u001a5kA\u0011!\u0004\u0001\u0005\u0006;\r\u0001\ra\b\u0005\u0006K\r\u0001\rAJ\u0001\u001ea>\u0004X\u000f\\1uK\u001e+g.\u001a:bY&TX\r\u001a'j]\u0016\f'\u000fU'N\u0019R\u0011\u0001h\u000f\t\u0003)eJ!AO\u000b\u0003\tUs\u0017\u000e\u001e\u0005\u0006;\u0011\u0001\ra\b"
)
public class GeneralizedLinearPMMLModelExport implements PMMLModelExport {
   private final String description;
   private DateTimeFormatter org$apache$spark$mllib$pmml$export$PMMLModelExport$$DATE_TIME_FORMATTER;
   private PMML pmml;

   public PMML getPmml() {
      return PMMLModelExport.getPmml$(this);
   }

   public DateTimeFormatter org$apache$spark$mllib$pmml$export$PMMLModelExport$$DATE_TIME_FORMATTER() {
      return this.org$apache$spark$mllib$pmml$export$PMMLModelExport$$DATE_TIME_FORMATTER;
   }

   public PMML pmml() {
      return this.pmml;
   }

   public final void org$apache$spark$mllib$pmml$export$PMMLModelExport$_setter_$org$apache$spark$mllib$pmml$export$PMMLModelExport$$DATE_TIME_FORMATTER_$eq(final DateTimeFormatter x$1) {
      this.org$apache$spark$mllib$pmml$export$PMMLModelExport$$DATE_TIME_FORMATTER = x$1;
   }

   public void org$apache$spark$mllib$pmml$export$PMMLModelExport$_setter_$pmml_$eq(final PMML x$1) {
      this.pmml = x$1;
   }

   private void populateGeneralizedLinearPMML(final GeneralizedLinearModel model) {
      this.pmml().getHeader().setDescription(this.description);
      if (model.weights().size() > 0) {
         String[] fields = new String[model.weights().size()];
         DataDictionary dataDictionary = new DataDictionary();
         MiningSchema miningSchema = new MiningSchema();
         RegressionTable regressionTable = new RegressionTable(.MODULE$.double2Double(model.intercept()));
         RegressionModel regressionModel = (new RegressionModel()).setMiningFunction(MiningFunction.REGRESSION).setMiningSchema(miningSchema).setModelName(this.description).addRegressionTables(regressionTable);
         scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), model.weights().size()).foreach((i) -> $anonfun$populateGeneralizedLinearPMML$1(fields, dataDictionary, miningSchema, regressionTable, model, BoxesRunTime.unboxToInt(i)));
         String targetField = "target";
         dataDictionary.addDataFields(new DataField(targetField, OpType.CONTINUOUS, DataType.DOUBLE));
         miningSchema.addMiningFields((new MiningField(targetField)).setUsageType(MiningField.UsageType.TARGET));
         dataDictionary.setNumberOfFields(.MODULE$.int2Integer(dataDictionary.getDataFields().size()));
         this.pmml().setDataDictionary(dataDictionary);
         this.pmml().addModels(regressionModel);
      }
   }

   // $FF: synthetic method
   public static final RegressionTable $anonfun$populateGeneralizedLinearPMML$1(final String[] fields$1, final DataDictionary dataDictionary$1, final MiningSchema miningSchema$1, final RegressionTable regressionTable$1, final GeneralizedLinearModel model$1, final int i) {
      fields$1[i] = "field_" + i;
      dataDictionary$1.addDataFields(new DataField(fields$1[i], OpType.CONTINUOUS, DataType.DOUBLE));
      miningSchema$1.addMiningFields((new MiningField(fields$1[i])).setUsageType(MiningField.UsageType.ACTIVE));
      return regressionTable$1.addNumericPredictors(new NumericPredictor(fields$1[i], .MODULE$.double2Double(model$1.weights().apply(i))));
   }

   public GeneralizedLinearPMMLModelExport(final GeneralizedLinearModel model, final String description) {
      this.description = description;
      PMMLModelExport.$init$(this);
      this.populateGeneralizedLinearPMML(model);
      Statics.releaseFence();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
