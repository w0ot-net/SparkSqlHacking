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
   bytes = "\u0006\u0005A3Qa\u0002\u0005\u0001\u0019QA\u0001b\b\u0001\u0003\u0002\u0003\u0006I!\t\u0005\tO\u0001\u0011\t\u0011)A\u0005Q!A1\u0007\u0001B\u0001B\u0003%A\u0007\u0003\u0005A\u0001\t\u0005\t\u0015!\u0003B\u0011\u0015!\u0005\u0001\"\u0001F\u0011\u0015Y\u0005\u0001\"\u0003M\u0005\r\u0012\u0015N\\1ss\u000ec\u0017m]:jM&\u001c\u0017\r^5p]BkU\nT'pI\u0016dW\t\u001f9peRT!!\u0003\u0006\u0002\r\u0015D\bo\u001c:u\u0015\tYA\"\u0001\u0003q[6d'BA\u0007\u000f\u0003\u0015iG\u000e\\5c\u0015\ty\u0001#A\u0003ta\u0006\u00148N\u0003\u0002\u0012%\u00051\u0011\r]1dQ\u0016T\u0011aE\u0001\u0004_J<7c\u0001\u0001\u00167A\u0011a#G\u0007\u0002/)\t\u0001$A\u0003tG\u0006d\u0017-\u0003\u0002\u001b/\t1\u0011I\\=SK\u001a\u0004\"\u0001H\u000f\u000e\u0003!I!A\b\u0005\u0003\u001fAkU\nT'pI\u0016dW\t\u001f9peR\fQ!\\8eK2\u001c\u0001\u0001\u0005\u0002#K5\t1E\u0003\u0002%\u0019\u0005Q!/Z4sKN\u001c\u0018n\u001c8\n\u0005\u0019\u001a#AF$f]\u0016\u0014\u0018\r\\5{K\u0012d\u0015N\\3be6{G-\u001a7\u0002\u0017\u0011,7o\u0019:jaRLwN\u001c\t\u0003SAr!A\u000b\u0018\u0011\u0005-:R\"\u0001\u0017\u000b\u00055\u0002\u0013A\u0002\u001fs_>$h(\u0003\u00020/\u00051\u0001K]3eK\u001aL!!\r\u001a\u0003\rM#(/\u001b8h\u0015\tys#A\no_Jl\u0017\r\\5{CRLwN\\'fi\"|G\r\u0005\u00026{9\u0011agO\u0007\u0002o)\u0011A\u0005\u000f\u0006\u0003\u0017eR!A\u000f\n\u0002\u0007\u0011lw-\u0003\u0002=o\u0005y!+Z4sKN\u001c\u0018n\u001c8N_\u0012,G.\u0003\u0002?\u007f\t\u0019bj\u001c:nC2L'0\u0019;j_:lU\r\u001e5pI*\u0011AhN\u0001\ni\"\u0014Xm\u001d5pY\u0012\u0004\"A\u0006\"\n\u0005\r;\"A\u0002#pk\ndW-\u0001\u0004=S:LGO\u0010\u000b\u0006\r\u001eC\u0015J\u0013\t\u00039\u0001AQaH\u0003A\u0002\u0005BQaJ\u0003A\u0002!BQaM\u0003A\u0002QBQ\u0001Q\u0003A\u0002\u0005\u000b\u0001\u0005]8qk2\fG/\u001a\"j]\u0006\u0014\u0018p\u00117bgNLg-[2bi&|g\u000eU'N\u0019R\tQ\n\u0005\u0002\u0017\u001d&\u0011qj\u0006\u0002\u0005+:LG\u000f"
)
public class BinaryClassificationPMMLModelExport implements PMMLModelExport {
   private final GeneralizedLinearModel model;
   private final String description;
   private final RegressionModel.NormalizationMethod normalizationMethod;
   private final double threshold;
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

   private void populateBinaryClassificationPMML() {
      this.pmml().getHeader().setDescription(this.description);
      if (this.model.weights().size() > 0) {
         String[] fields = new String[this.model.weights().size()];
         DataDictionary dataDictionary = new DataDictionary();
         MiningSchema miningSchema = new MiningSchema();
         RegressionTable regressionTableYES = (new RegressionTable(.MODULE$.double2Double(this.model.intercept()))).setTargetCategory("1");
         double interceptNO = this.threshold;
         if (RegressionModel.NormalizationMethod.LOGIT.equals(this.normalizationMethod)) {
            if (this.threshold <= (double)0) {
               interceptNO = -Double.MAX_VALUE;
            } else if (this.threshold >= (double)1) {
               interceptNO = Double.MAX_VALUE;
            } else {
               interceptNO = -scala.math.package..MODULE$.log((double)1 / this.threshold - (double)1);
            }
         }

         RegressionTable regressionTableNO = (new RegressionTable(.MODULE$.double2Double(interceptNO))).setTargetCategory("0");
         RegressionModel regressionModel = (new RegressionModel()).setMiningFunction(MiningFunction.CLASSIFICATION).setMiningSchema(miningSchema).setModelName(this.description).setNormalizationMethod(this.normalizationMethod).addRegressionTables(regressionTableYES, regressionTableNO);
         scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), this.model.weights().size()).foreach((i) -> $anonfun$populateBinaryClassificationPMML$1(this, fields, dataDictionary, miningSchema, regressionTableYES, BoxesRunTime.unboxToInt(i)));
         String targetField = "target";
         dataDictionary.addDataFields(new DataField(targetField, OpType.CATEGORICAL, DataType.STRING));
         miningSchema.addMiningFields((new MiningField(targetField)).setUsageType(MiningField.UsageType.TARGET));
         dataDictionary.setNumberOfFields(.MODULE$.int2Integer(dataDictionary.getDataFields().size()));
         this.pmml().setDataDictionary(dataDictionary);
         this.pmml().addModels(regressionModel);
      }
   }

   // $FF: synthetic method
   public static final RegressionTable $anonfun$populateBinaryClassificationPMML$1(final BinaryClassificationPMMLModelExport $this, final String[] fields$1, final DataDictionary dataDictionary$1, final MiningSchema miningSchema$1, final RegressionTable regressionTableYES$1, final int i) {
      fields$1[i] = "field_" + i;
      dataDictionary$1.addDataFields(new DataField(fields$1[i], OpType.CONTINUOUS, DataType.DOUBLE));
      miningSchema$1.addMiningFields((new MiningField(fields$1[i])).setUsageType(MiningField.UsageType.ACTIVE));
      return regressionTableYES$1.addNumericPredictors(new NumericPredictor(fields$1[i], .MODULE$.double2Double($this.model.weights().apply(i))));
   }

   public BinaryClassificationPMMLModelExport(final GeneralizedLinearModel model, final String description, final RegressionModel.NormalizationMethod normalizationMethod, final double threshold) {
      this.model = model;
      this.description = description;
      this.normalizationMethod = normalizationMethod;
      this.threshold = threshold;
      PMMLModelExport.$init$(this);
      this.populateBinaryClassificationPMML();
      Statics.releaseFence();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
