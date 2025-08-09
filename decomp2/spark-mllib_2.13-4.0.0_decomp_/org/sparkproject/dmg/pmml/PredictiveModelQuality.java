package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.adapters.FieldNameAdapter;
import org.sparkproject.dmg.pmml.adapters.NumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.AlternateValueConstructor;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "PredictiveModelQuality",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "confusionMatrix", "liftDatas", "roc"}
)
@JsonRootName("PredictiveModelQuality")
@JsonPropertyOrder({"targetField", "dataName", "dataUsage", "meanError", "meanAbsoluteError", "meanSquaredError", "rootMeanSquaredError", "rSquared", "adjRSquared", "sumSquaredError", "sumSquaredRegression", "numOfRecords", "numOfRecordsWeighted", "numOfPredictors", "degreesOfFreedom", "fStatistic", "aic", "bic", "aiCc", "accuracy", "auc", "precision", "recall", "specificity", "f1", "f2", "fhalf", "extensions", "confusionMatrix", "liftDatas", "roc"})
@Added(Version.PMML_4_0)
public class PredictiveModelQuality extends ModelQuality implements HasExtensions, HasTargetFieldReference {
   @XmlAttribute(
      name = "targetField",
      required = true
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("targetField")
   private String targetField;
   @XmlAttribute(
      name = "dataName"
   )
   @JsonProperty("dataName")
   private String dataName;
   @XmlAttribute(
      name = "dataUsage"
   )
   @JsonProperty("dataUsage")
   private DataUsage dataUsage;
   @XmlAttribute(
      name = "meanError"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("meanError")
   private Number meanError;
   @XmlAttribute(
      name = "meanAbsoluteError"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("meanAbsoluteError")
   private Number meanAbsoluteError;
   @XmlAttribute(
      name = "meanSquaredError"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("meanSquaredError")
   private Number meanSquaredError;
   @XmlAttribute(
      name = "rootMeanSquaredError"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("rootMeanSquaredError")
   @Added(
      value = Version.PMML_4_1,
      removable = true
   )
   private Number rootMeanSquaredError;
   @XmlAttribute(
      name = "r-squared"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("r-squared")
   private Number rSquared;
   @XmlAttribute(
      name = "adj-r-squared"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("adj-r-squared")
   @Added(
      value = Version.PMML_4_1,
      removable = true
   )
   private Number adjRSquared;
   @XmlAttribute(
      name = "sumSquaredError"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("sumSquaredError")
   @Added(
      value = Version.PMML_4_1,
      removable = true
   )
   private Number sumSquaredError;
   @XmlAttribute(
      name = "sumSquaredRegression"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("sumSquaredRegression")
   @Added(
      value = Version.PMML_4_1,
      removable = true
   )
   private Number sumSquaredRegression;
   @XmlAttribute(
      name = "numOfRecords"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("numOfRecords")
   @Added(
      value = Version.PMML_4_1,
      removable = true
   )
   private Number numOfRecords;
   @XmlAttribute(
      name = "numOfRecordsWeighted"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("numOfRecordsWeighted")
   @Added(
      value = Version.PMML_4_1,
      removable = true
   )
   private Number numOfRecordsWeighted;
   @XmlAttribute(
      name = "numOfPredictors"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("numOfPredictors")
   @Added(
      value = Version.PMML_4_1,
      removable = true
   )
   private Number numOfPredictors;
   @XmlAttribute(
      name = "degreesOfFreedom"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("degreesOfFreedom")
   @Added(
      value = Version.PMML_4_1,
      removable = true
   )
   private Number degreesOfFreedom;
   @XmlAttribute(
      name = "fStatistic"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("fStatistic")
   @Added(
      value = Version.PMML_4_1,
      removable = true
   )
   private Number fStatistic;
   @XmlAttribute(
      name = "AIC"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("AIC")
   @Added(
      value = Version.PMML_4_1,
      removable = true
   )
   private Number aic;
   @XmlAttribute(
      name = "BIC"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("BIC")
   @Added(
      value = Version.PMML_4_1,
      removable = true
   )
   private Number bic;
   @XmlAttribute(
      name = "AICc"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("AICc")
   @Added(
      value = Version.PMML_4_1,
      removable = true
   )
   private Number aiCc;
   @XmlAttribute(
      name = "accuracy"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("accuracy")
   @Added(
      value = Version.PMML_4_4,
      removable = true
   )
   private Number accuracy;
   @XmlAttribute(
      name = "AUC"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("AUC")
   @Added(
      value = Version.PMML_4_4,
      removable = true
   )
   private Number auc;
   @XmlAttribute(
      name = "precision"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("precision")
   @Added(
      value = Version.PMML_4_4,
      removable = true
   )
   private Number precision;
   @XmlAttribute(
      name = "recall"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("recall")
   @Added(
      value = Version.PMML_4_4,
      removable = true
   )
   private Number recall;
   @XmlAttribute(
      name = "specificity"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("specificity")
   @Added(
      value = Version.PMML_4_4,
      removable = true
   )
   private Number specificity;
   @XmlAttribute(
      name = "F1"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("F1")
   @Added(
      value = Version.PMML_4_4,
      removable = true
   )
   private Number f1;
   @XmlAttribute(
      name = "F2"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("F2")
   @Added(
      value = Version.PMML_4_4,
      removable = true
   )
   private Number f2;
   @XmlAttribute(
      name = "Fhalf"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("Fhalf")
   @Added(
      value = Version.PMML_4_4,
      removable = true
   )
   private Number fhalf;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "ConfusionMatrix",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("ConfusionMatrix")
   private ConfusionMatrix confusionMatrix;
   @XmlElement(
      name = "LiftData",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("LiftData")
   @CollectionElementType(LiftData.class)
   private List liftDatas;
   @XmlElement(
      name = "ROC",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("ROC")
   private ROC roc;
   private static final long serialVersionUID = 67371272L;

   public PredictiveModelQuality() {
   }

   @ValueConstructor
   public PredictiveModelQuality(@Property("targetField") String targetField) {
      this.targetField = targetField;
   }

   @AlternateValueConstructor
   public PredictiveModelQuality(Field targetField) {
      this(targetField != null ? targetField.requireName() : null);
   }

   public String requireTargetField() {
      if (this.targetField == null) {
         throw new MissingAttributeException(this, PMMLAttributes.PREDICTIVEMODELQUALITY_TARGETFIELD);
      } else {
         return this.targetField;
      }
   }

   public String getTargetField() {
      return this.targetField;
   }

   public PredictiveModelQuality setTargetField(@Property("targetField") String targetField) {
      this.targetField = targetField;
      return this;
   }

   public String getDataName() {
      return this.dataName;
   }

   public PredictiveModelQuality setDataName(@Property("dataName") String dataName) {
      this.dataName = dataName;
      return this;
   }

   public DataUsage getDataUsage() {
      return this.dataUsage == null ? PredictiveModelQuality.DataUsage.TRAINING : this.dataUsage;
   }

   public PredictiveModelQuality setDataUsage(@Property("dataUsage") DataUsage dataUsage) {
      this.dataUsage = dataUsage;
      return this;
   }

   public Number getMeanError() {
      return this.meanError;
   }

   public PredictiveModelQuality setMeanError(@Property("meanError") Number meanError) {
      this.meanError = meanError;
      return this;
   }

   public Number getMeanAbsoluteError() {
      return this.meanAbsoluteError;
   }

   public PredictiveModelQuality setMeanAbsoluteError(@Property("meanAbsoluteError") Number meanAbsoluteError) {
      this.meanAbsoluteError = meanAbsoluteError;
      return this;
   }

   public Number getMeanSquaredError() {
      return this.meanSquaredError;
   }

   public PredictiveModelQuality setMeanSquaredError(@Property("meanSquaredError") Number meanSquaredError) {
      this.meanSquaredError = meanSquaredError;
      return this;
   }

   public Number getRootMeanSquaredError() {
      return this.rootMeanSquaredError;
   }

   public PredictiveModelQuality setRootMeanSquaredError(@Property("rootMeanSquaredError") Number rootMeanSquaredError) {
      this.rootMeanSquaredError = rootMeanSquaredError;
      return this;
   }

   public Number getRSquared() {
      return this.rSquared;
   }

   public PredictiveModelQuality setRSquared(@Property("rSquared") Number rSquared) {
      this.rSquared = rSquared;
      return this;
   }

   public Number getAdjRSquared() {
      return this.adjRSquared;
   }

   public PredictiveModelQuality setAdjRSquared(@Property("adjRSquared") Number adjRSquared) {
      this.adjRSquared = adjRSquared;
      return this;
   }

   public Number getSumSquaredError() {
      return this.sumSquaredError;
   }

   public PredictiveModelQuality setSumSquaredError(@Property("sumSquaredError") Number sumSquaredError) {
      this.sumSquaredError = sumSquaredError;
      return this;
   }

   public Number getSumSquaredRegression() {
      return this.sumSquaredRegression;
   }

   public PredictiveModelQuality setSumSquaredRegression(@Property("sumSquaredRegression") Number sumSquaredRegression) {
      this.sumSquaredRegression = sumSquaredRegression;
      return this;
   }

   public Number getNumOfRecords() {
      return this.numOfRecords;
   }

   public PredictiveModelQuality setNumOfRecords(@Property("numOfRecords") Number numOfRecords) {
      this.numOfRecords = numOfRecords;
      return this;
   }

   public Number getNumOfRecordsWeighted() {
      return this.numOfRecordsWeighted;
   }

   public PredictiveModelQuality setNumOfRecordsWeighted(@Property("numOfRecordsWeighted") Number numOfRecordsWeighted) {
      this.numOfRecordsWeighted = numOfRecordsWeighted;
      return this;
   }

   public Number getNumOfPredictors() {
      return this.numOfPredictors;
   }

   public PredictiveModelQuality setNumOfPredictors(@Property("numOfPredictors") Number numOfPredictors) {
      this.numOfPredictors = numOfPredictors;
      return this;
   }

   public Number getDegreesOfFreedom() {
      return this.degreesOfFreedom;
   }

   public PredictiveModelQuality setDegreesOfFreedom(@Property("degreesOfFreedom") Number degreesOfFreedom) {
      this.degreesOfFreedom = degreesOfFreedom;
      return this;
   }

   public Number getFStatistic() {
      return this.fStatistic;
   }

   public PredictiveModelQuality setFStatistic(@Property("fStatistic") Number fStatistic) {
      this.fStatistic = fStatistic;
      return this;
   }

   public Number getAIC() {
      return this.aic;
   }

   public PredictiveModelQuality setAIC(@Property("aic") Number aic) {
      this.aic = aic;
      return this;
   }

   public Number getBIC() {
      return this.bic;
   }

   public PredictiveModelQuality setBIC(@Property("bic") Number bic) {
      this.bic = bic;
      return this;
   }

   public Number getAICc() {
      return this.aiCc;
   }

   public PredictiveModelQuality setAICc(@Property("aiCc") Number aiCc) {
      this.aiCc = aiCc;
      return this;
   }

   public Number getAccuracy() {
      return this.accuracy;
   }

   public PredictiveModelQuality setAccuracy(@Property("accuracy") Number accuracy) {
      this.accuracy = accuracy;
      return this;
   }

   public Number getAUC() {
      return this.auc;
   }

   public PredictiveModelQuality setAUC(@Property("auc") Number auc) {
      this.auc = auc;
      return this;
   }

   public Number getPrecision() {
      return this.precision;
   }

   public PredictiveModelQuality setPrecision(@Property("precision") Number precision) {
      this.precision = precision;
      return this;
   }

   public Number getRecall() {
      return this.recall;
   }

   public PredictiveModelQuality setRecall(@Property("recall") Number recall) {
      this.recall = recall;
      return this;
   }

   public Number getSpecificity() {
      return this.specificity;
   }

   public PredictiveModelQuality setSpecificity(@Property("specificity") Number specificity) {
      this.specificity = specificity;
      return this;
   }

   public Number getF1() {
      return this.f1;
   }

   public PredictiveModelQuality setF1(@Property("f1") Number f1) {
      this.f1 = f1;
      return this;
   }

   public Number getF2() {
      return this.f2;
   }

   public PredictiveModelQuality setF2(@Property("f2") Number f2) {
      this.f2 = f2;
      return this;
   }

   public Number getFhalf() {
      return this.fhalf;
   }

   public PredictiveModelQuality setFhalf(@Property("fhalf") Number fhalf) {
      this.fhalf = fhalf;
      return this;
   }

   public boolean hasExtensions() {
      return this.extensions != null && !this.extensions.isEmpty();
   }

   public List getExtensions() {
      if (this.extensions == null) {
         this.extensions = new ArrayList();
      }

      return this.extensions;
   }

   public PredictiveModelQuality addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public ConfusionMatrix getConfusionMatrix() {
      return this.confusionMatrix;
   }

   public PredictiveModelQuality setConfusionMatrix(@Property("confusionMatrix") ConfusionMatrix confusionMatrix) {
      this.confusionMatrix = confusionMatrix;
      return this;
   }

   public boolean hasLiftDatas() {
      return this.liftDatas != null && !this.liftDatas.isEmpty();
   }

   public List getLiftDatas() {
      if (this.liftDatas == null) {
         this.liftDatas = new ArrayList();
      }

      return this.liftDatas;
   }

   public PredictiveModelQuality addLiftDatas(LiftData... liftDatas) {
      this.getLiftDatas().addAll(Arrays.asList(liftDatas));
      return this;
   }

   public ROC getROC() {
      return this.roc;
   }

   public PredictiveModelQuality setROC(@Property("roc") ROC roc) {
      this.roc = roc;
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE) {
            status = PMMLObject.traverse(visitor, (Visitable)this.getConfusionMatrix());
         }

         if (status == VisitorAction.CONTINUE && this.hasLiftDatas()) {
            status = PMMLObject.traverse(visitor, this.getLiftDatas());
         }

         if (status == VisitorAction.CONTINUE) {
            status = PMMLObject.traverse(visitor, (Visitable)this.getROC());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum DataUsage implements StringValue {
      @XmlEnumValue("training")
      @JsonProperty("training")
      TRAINING("training"),
      @XmlEnumValue("test")
      @JsonProperty("test")
      TEST("test"),
      @XmlEnumValue("validation")
      @JsonProperty("validation")
      VALIDATION("validation");

      private final String value;

      private DataUsage(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static DataUsage fromValue(String v) {
         for(DataUsage c : values()) {
            if (c.value.equals(v)) {
               return c;
            }
         }

         throw new IllegalArgumentException(v);
      }

      public String toString() {
         return this.value();
      }
   }
}
