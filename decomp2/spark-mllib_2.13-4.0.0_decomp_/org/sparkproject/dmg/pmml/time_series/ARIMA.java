package org.sparkproject.dmg.pmml.time_series;

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
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.StringValue;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitable;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;

@XmlRootElement(
   name = "ARIMA",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "nonseasonalComponent", "seasonalComponent", "dynamicRegressors", "maximumLikelihoodStat", "outlierEffects"}
)
@JsonRootName("ARIMA")
@JsonPropertyOrder({"rmse", "transformation", "constantTerm", "predictionMethod", "extensions", "nonseasonalComponent", "seasonalComponent", "dynamicRegressors", "maximumLikelihoodStat", "outlierEffects"})
@Added(Version.PMML_4_0)
public class ARIMA extends Algorithm implements HasExtensions, HasDynamicRegressors {
   @XmlAttribute(
      name = "RMSE"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("RMSE")
   @Added(Version.PMML_4_4)
   private Number rmse;
   @XmlAttribute(
      name = "transformation"
   )
   @JsonProperty("transformation")
   @Added(Version.PMML_4_4)
   private Transformation transformation;
   @XmlAttribute(
      name = "constantTerm"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("constantTerm")
   @Added(Version.PMML_4_4)
   private Number constantTerm;
   @XmlAttribute(
      name = "predictionMethod"
   )
   @JsonProperty("predictionMethod")
   @Added(Version.PMML_4_4)
   private PredictionMethod predictionMethod;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @Added(
      value = Version.PMML_4_4,
      removable = true
   )
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "NonseasonalComponent",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("NonseasonalComponent")
   @Added(Version.PMML_4_4)
   private NonseasonalComponent nonseasonalComponent;
   @XmlElement(
      name = "SeasonalComponent",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("SeasonalComponent")
   @Added(Version.PMML_4_4)
   private SeasonalComponent seasonalComponent;
   @XmlElement(
      name = "DynamicRegressor",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("DynamicRegressor")
   @Added(Version.PMML_4_4)
   @CollectionElementType(DynamicRegressor.class)
   private List dynamicRegressors;
   @XmlElement(
      name = "MaximumLikelihoodStat",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("MaximumLikelihoodStat")
   @Added(Version.PMML_4_4)
   private MaximumLikelihoodStat maximumLikelihoodStat;
   @XmlElement(
      name = "OutlierEffect",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("OutlierEffect")
   @Added(Version.PMML_4_4)
   @CollectionElementType(OutlierEffect.class)
   private List outlierEffects;
   private static final Number DEFAULT_CONSTANT_TERM = (new RealNumberAdapter()).unmarshal("0");
   private static final long serialVersionUID = 67371272L;

   public Number getRMSE() {
      return this.rmse;
   }

   public ARIMA setRMSE(@Property("rmse") Number rmse) {
      this.rmse = rmse;
      return this;
   }

   public Transformation getTransformation() {
      return this.transformation == null ? ARIMA.Transformation.NONE : this.transformation;
   }

   public ARIMA setTransformation(@Property("transformation") Transformation transformation) {
      this.transformation = transformation;
      return this;
   }

   public Number getConstantTerm() {
      return this.constantTerm == null ? DEFAULT_CONSTANT_TERM : this.constantTerm;
   }

   public ARIMA setConstantTerm(@Property("constantTerm") Number constantTerm) {
      this.constantTerm = constantTerm;
      return this;
   }

   public PredictionMethod getPredictionMethod() {
      return this.predictionMethod == null ? ARIMA.PredictionMethod.CONDITIONAL_LEAST_SQUARES : this.predictionMethod;
   }

   public ARIMA setPredictionMethod(@Property("predictionMethod") PredictionMethod predictionMethod) {
      this.predictionMethod = predictionMethod;
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

   public ARIMA addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public NonseasonalComponent getNonseasonalComponent() {
      return this.nonseasonalComponent;
   }

   public ARIMA setNonseasonalComponent(@Property("nonseasonalComponent") NonseasonalComponent nonseasonalComponent) {
      this.nonseasonalComponent = nonseasonalComponent;
      return this;
   }

   public SeasonalComponent getSeasonalComponent() {
      return this.seasonalComponent;
   }

   public ARIMA setSeasonalComponent(@Property("seasonalComponent") SeasonalComponent seasonalComponent) {
      this.seasonalComponent = seasonalComponent;
      return this;
   }

   public boolean hasDynamicRegressors() {
      return this.dynamicRegressors != null && !this.dynamicRegressors.isEmpty();
   }

   public List getDynamicRegressors() {
      if (this.dynamicRegressors == null) {
         this.dynamicRegressors = new ArrayList();
      }

      return this.dynamicRegressors;
   }

   public ARIMA addDynamicRegressors(DynamicRegressor... dynamicRegressors) {
      this.getDynamicRegressors().addAll(Arrays.asList(dynamicRegressors));
      return this;
   }

   public MaximumLikelihoodStat getMaximumLikelihoodStat() {
      return this.maximumLikelihoodStat;
   }

   public ARIMA setMaximumLikelihoodStat(@Property("maximumLikelihoodStat") MaximumLikelihoodStat maximumLikelihoodStat) {
      this.maximumLikelihoodStat = maximumLikelihoodStat;
      return this;
   }

   public boolean hasOutlierEffects() {
      return this.outlierEffects != null && !this.outlierEffects.isEmpty();
   }

   public List getOutlierEffects() {
      if (this.outlierEffects == null) {
         this.outlierEffects = new ArrayList();
      }

      return this.outlierEffects;
   }

   public ARIMA addOutlierEffects(OutlierEffect... outlierEffects) {
      this.getOutlierEffects().addAll(Arrays.asList(outlierEffects));
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
            status = PMMLObject.traverse(visitor, this.getNonseasonalComponent(), this.getSeasonalComponent());
         }

         if (status == VisitorAction.CONTINUE && this.hasDynamicRegressors()) {
            status = PMMLObject.traverse(visitor, this.getDynamicRegressors());
         }

         if (status == VisitorAction.CONTINUE) {
            status = PMMLObject.traverse(visitor, (Visitable)this.getMaximumLikelihoodStat());
         }

         if (status == VisitorAction.CONTINUE && this.hasOutlierEffects()) {
            status = PMMLObject.traverse(visitor, this.getOutlierEffects());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum PredictionMethod implements StringValue {
      @XmlEnumValue("conditionalLeastSquares")
      @JsonProperty("conditionalLeastSquares")
      CONDITIONAL_LEAST_SQUARES("conditionalLeastSquares"),
      @XmlEnumValue("exactLeastSquares")
      @JsonProperty("exactLeastSquares")
      EXACT_LEAST_SQUARES("exactLeastSquares");

      private final String value;

      private PredictionMethod(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static PredictionMethod fromValue(String v) {
         for(PredictionMethod c : values()) {
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

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum Transformation implements StringValue {
      @XmlEnumValue("none")
      @JsonProperty("none")
      NONE("none"),
      @XmlEnumValue("logarithmic")
      @JsonProperty("logarithmic")
      LOGARITHMIC("logarithmic"),
      @XmlEnumValue("squareroot")
      @JsonProperty("squareroot")
      SQUAREROOT("squareroot");

      private final String value;

      private Transformation(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static Transformation fromValue(String v) {
         for(Transformation c : values()) {
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
