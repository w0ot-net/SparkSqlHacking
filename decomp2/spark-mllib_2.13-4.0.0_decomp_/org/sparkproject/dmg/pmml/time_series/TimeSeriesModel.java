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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.LocalTransformations;
import org.sparkproject.dmg.pmml.MathContext;
import org.sparkproject.dmg.pmml.MiningFunction;
import org.sparkproject.dmg.pmml.MiningSchema;
import org.sparkproject.dmg.pmml.Model;
import org.sparkproject.dmg.pmml.ModelExplanation;
import org.sparkproject.dmg.pmml.ModelStats;
import org.sparkproject.dmg.pmml.ModelVerification;
import org.sparkproject.dmg.pmml.Output;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.StringValue;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.Since;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "TimeSeriesModel",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "miningSchema", "output", "modelStats", "modelExplanation", "localTransformations", "timeSeries", "spectralAnalysis", "arima", "exponentialSmoothing", "seasonalTrendDecomposition", "stateSpaceModel", "garch", "modelVerification"}
)
@JsonRootName("TimeSeriesModel")
@JsonPropertyOrder({"modelName", "miningFunction", "algorithmName", "bestFit", "scorable", "mathContext", "extensions", "miningSchema", "output", "modelStats", "modelExplanation", "localTransformations", "timeSeries", "spectralAnalysis", "arima", "exponentialSmoothing", "seasonalTrendDecomposition", "stateSpaceModel", "garch", "modelVerification"})
@Added(Version.PMML_4_0)
public class TimeSeriesModel extends Model implements HasExtensions {
   @XmlAttribute(
      name = "modelName"
   )
   @JsonProperty("modelName")
   private String modelName;
   @XmlAttribute(
      name = "functionName",
      required = true
   )
   @JsonProperty("functionName")
   private MiningFunction miningFunction;
   @XmlAttribute(
      name = "algorithmName"
   )
   @JsonProperty("algorithmName")
   private String algorithmName;
   @XmlAttribute(
      name = "bestFit",
      required = true
   )
   @JsonProperty("bestFit")
   private Algorithm bestFit;
   @XmlAttribute(
      name = "isScorable"
   )
   @JsonProperty("isScorable")
   @Added(
      value = Version.PMML_4_1,
      removable = true
   )
   private Boolean scorable;
   @XmlAttribute(
      name = "x-mathContext"
   )
   @JsonProperty("x-mathContext")
   @Added(
      value = Version.XPMML,
      removable = true
   )
   @Since("1.3.7")
   private MathContext mathContext;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "MiningSchema",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("MiningSchema")
   private MiningSchema miningSchema;
   @XmlElement(
      name = "Output",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Output")
   private Output output;
   @XmlElement(
      name = "ModelStats",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("ModelStats")
   private ModelStats modelStats;
   @XmlElement(
      name = "ModelExplanation",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("ModelExplanation")
   @Added(
      value = Version.PMML_4_1,
      removable = true
   )
   private ModelExplanation modelExplanation;
   @XmlElement(
      name = "LocalTransformations",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("LocalTransformations")
   private LocalTransformations localTransformations;
   @XmlElement(
      name = "TimeSeries",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("TimeSeries")
   @CollectionElementType(TimeSeries.class)
   private List timeSeries;
   @XmlElement(
      name = "SpectralAnalysis",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("SpectralAnalysis")
   private SpectralAnalysis spectralAnalysis;
   @XmlElement(
      name = "ARIMA",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("ARIMA")
   private ARIMA arima;
   @XmlElement(
      name = "ExponentialSmoothing",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("ExponentialSmoothing")
   private ExponentialSmoothing exponentialSmoothing;
   @XmlElement(
      name = "SeasonalTrendDecomposition",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("SeasonalTrendDecomposition")
   private SeasonalTrendDecomposition seasonalTrendDecomposition;
   @XmlElement(
      name = "StateSpaceModel",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("StateSpaceModel")
   @Added(Version.PMML_4_4)
   private StateSpaceModel stateSpaceModel;
   @XmlElement(
      name = "GARCH",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("GARCH")
   @Added(Version.PMML_4_4)
   private GARCH garch;
   @XmlElement(
      name = "ModelVerification",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("ModelVerification")
   private ModelVerification modelVerification;
   private static final Boolean DEFAULT_SCORABLE = true;
   private static final long serialVersionUID = 67371272L;

   public TimeSeriesModel() {
   }

   @ValueConstructor
   public TimeSeriesModel(@Property("miningFunction") MiningFunction miningFunction, @Property("bestFit") Algorithm bestFit, @Property("miningSchema") MiningSchema miningSchema) {
      this.miningFunction = miningFunction;
      this.bestFit = bestFit;
      this.miningSchema = miningSchema;
   }

   public String getModelName() {
      return this.modelName;
   }

   public TimeSeriesModel setModelName(@Property("modelName") String modelName) {
      this.modelName = modelName;
      return this;
   }

   public MiningFunction requireMiningFunction() {
      if (this.miningFunction == null) {
         throw new MissingAttributeException(this, PMMLAttributes.TIMESERIESMODEL_MININGFUNCTION);
      } else {
         return this.miningFunction;
      }
   }

   public MiningFunction getMiningFunction() {
      return this.miningFunction;
   }

   public TimeSeriesModel setMiningFunction(@Property("miningFunction") MiningFunction miningFunction) {
      this.miningFunction = miningFunction;
      return this;
   }

   public String getAlgorithmName() {
      return this.algorithmName;
   }

   public TimeSeriesModel setAlgorithmName(@Property("algorithmName") String algorithmName) {
      this.algorithmName = algorithmName;
      return this;
   }

   public Algorithm requireBestFit() {
      if (this.bestFit == null) {
         throw new MissingAttributeException(this, PMMLAttributes.TIMESERIESMODEL_BESTFIT);
      } else {
         return this.bestFit;
      }
   }

   public Algorithm getBestFit() {
      return this.bestFit;
   }

   public TimeSeriesModel setBestFit(@Property("bestFit") Algorithm bestFit) {
      this.bestFit = bestFit;
      return this;
   }

   public boolean isScorable() {
      return this.scorable == null ? DEFAULT_SCORABLE : this.scorable;
   }

   public TimeSeriesModel setScorable(@Property("scorable") Boolean scorable) {
      this.scorable = scorable;
      return this;
   }

   public MathContext getMathContext() {
      return this.mathContext == null ? MathContext.DOUBLE : this.mathContext;
   }

   public TimeSeriesModel setMathContext(@Property("mathContext") MathContext mathContext) {
      this.mathContext = mathContext;
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

   public TimeSeriesModel addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public MiningSchema requireMiningSchema() {
      if (this.miningSchema == null) {
         throw new MissingElementException(this, PMMLElements.TIMESERIESMODEL_MININGSCHEMA);
      } else {
         return this.miningSchema;
      }
   }

   public MiningSchema getMiningSchema() {
      return this.miningSchema;
   }

   public TimeSeriesModel setMiningSchema(@Property("miningSchema") MiningSchema miningSchema) {
      this.miningSchema = miningSchema;
      return this;
   }

   public Output getOutput() {
      return this.output;
   }

   public TimeSeriesModel setOutput(@Property("output") Output output) {
      this.output = output;
      return this;
   }

   public ModelStats getModelStats() {
      return this.modelStats;
   }

   public TimeSeriesModel setModelStats(@Property("modelStats") ModelStats modelStats) {
      this.modelStats = modelStats;
      return this;
   }

   public ModelExplanation getModelExplanation() {
      return this.modelExplanation;
   }

   public TimeSeriesModel setModelExplanation(@Property("modelExplanation") ModelExplanation modelExplanation) {
      this.modelExplanation = modelExplanation;
      return this;
   }

   public LocalTransformations getLocalTransformations() {
      return this.localTransformations;
   }

   public TimeSeriesModel setLocalTransformations(@Property("localTransformations") LocalTransformations localTransformations) {
      this.localTransformations = localTransformations;
      return this;
   }

   public boolean hasTimeSeries() {
      return this.timeSeries != null && !this.timeSeries.isEmpty();
   }

   public List getTimeSeries() {
      if (this.timeSeries == null) {
         this.timeSeries = new ArrayList();
      }

      return this.timeSeries;
   }

   public TimeSeriesModel addTimeSeries(TimeSeries... timeSeries) {
      this.getTimeSeries().addAll(Arrays.asList(timeSeries));
      return this;
   }

   public SpectralAnalysis requireSpectralAnalysis() {
      if (this.spectralAnalysis == null) {
         throw new MissingElementException(this, PMMLElements.TIMESERIESMODEL_SPECTRALANALYSIS);
      } else {
         return this.spectralAnalysis;
      }
   }

   public SpectralAnalysis getSpectralAnalysis() {
      return this.spectralAnalysis;
   }

   public TimeSeriesModel setSpectralAnalysis(@Property("spectralAnalysis") SpectralAnalysis spectralAnalysis) {
      this.spectralAnalysis = spectralAnalysis;
      return this;
   }

   public ARIMA requireARIMA() {
      if (this.arima == null) {
         throw new MissingElementException(this, PMMLElements.TIMESERIESMODEL_ARIMA);
      } else {
         return this.arima;
      }
   }

   public ARIMA getARIMA() {
      return this.arima;
   }

   public TimeSeriesModel setARIMA(@Property("arima") ARIMA arima) {
      this.arima = arima;
      return this;
   }

   public ExponentialSmoothing requireExponentialSmoothing() {
      if (this.exponentialSmoothing == null) {
         throw new MissingElementException(this, PMMLElements.TIMESERIESMODEL_EXPONENTIALSMOOTHING);
      } else {
         return this.exponentialSmoothing;
      }
   }

   public ExponentialSmoothing getExponentialSmoothing() {
      return this.exponentialSmoothing;
   }

   public TimeSeriesModel setExponentialSmoothing(@Property("exponentialSmoothing") ExponentialSmoothing exponentialSmoothing) {
      this.exponentialSmoothing = exponentialSmoothing;
      return this;
   }

   public SeasonalTrendDecomposition requireSeasonalTrendDecomposition() {
      if (this.seasonalTrendDecomposition == null) {
         throw new MissingElementException(this, PMMLElements.TIMESERIESMODEL_SEASONALTRENDDECOMPOSITION);
      } else {
         return this.seasonalTrendDecomposition;
      }
   }

   public SeasonalTrendDecomposition getSeasonalTrendDecomposition() {
      return this.seasonalTrendDecomposition;
   }

   public TimeSeriesModel setSeasonalTrendDecomposition(@Property("seasonalTrendDecomposition") SeasonalTrendDecomposition seasonalTrendDecomposition) {
      this.seasonalTrendDecomposition = seasonalTrendDecomposition;
      return this;
   }

   public StateSpaceModel requireStateSpaceModel() {
      if (this.stateSpaceModel == null) {
         throw new MissingElementException(this, PMMLElements.TIMESERIESMODEL_STATESPACEMODEL);
      } else {
         return this.stateSpaceModel;
      }
   }

   public StateSpaceModel getStateSpaceModel() {
      return this.stateSpaceModel;
   }

   public TimeSeriesModel setStateSpaceModel(@Property("stateSpaceModel") StateSpaceModel stateSpaceModel) {
      this.stateSpaceModel = stateSpaceModel;
      return this;
   }

   public GARCH requireGARCH() {
      if (this.garch == null) {
         throw new MissingElementException(this, PMMLElements.TIMESERIESMODEL_GARCH);
      } else {
         return this.garch;
      }
   }

   public GARCH getGARCH() {
      return this.garch;
   }

   public TimeSeriesModel setGARCH(@Property("garch") GARCH garch) {
      this.garch = garch;
      return this;
   }

   public ModelVerification getModelVerification() {
      return this.modelVerification;
   }

   public TimeSeriesModel setModelVerification(@Property("modelVerification") ModelVerification modelVerification) {
      this.modelVerification = modelVerification;
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
            status = PMMLObject.traverse(visitor, this.getMiningSchema(), this.getOutput(), this.getModelStats(), this.getModelExplanation(), this.getLocalTransformations());
         }

         if (status == VisitorAction.CONTINUE && this.hasTimeSeries()) {
            status = PMMLObject.traverse(visitor, this.getTimeSeries());
         }

         if (status == VisitorAction.CONTINUE) {
            status = PMMLObject.traverse(visitor, this.getSpectralAnalysis(), this.getARIMA(), this.getExponentialSmoothing(), this.getSeasonalTrendDecomposition(), this.getStateSpaceModel(), this.getGARCH(), this.getModelVerification());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum Algorithm implements StringValue {
      @JsonProperty("ARIMA")
      ARIMA("ARIMA"),
      @XmlEnumValue("ExponentialSmoothing")
      @JsonProperty("ExponentialSmoothing")
      EXPONENTIAL_SMOOTHING("ExponentialSmoothing"),
      @XmlEnumValue("SeasonalTrendDecomposition")
      @JsonProperty("SeasonalTrendDecomposition")
      SEASONAL_TREND_DECOMPOSITION("SeasonalTrendDecomposition"),
      @XmlEnumValue("SpectralAnalysis")
      @JsonProperty("SpectralAnalysis")
      SPECTRAL_ANALYSIS("SpectralAnalysis"),
      @XmlEnumValue("StateSpaceModel")
      @JsonProperty("StateSpaceModel")
      @Added(Version.PMML_4_4)
      STATE_SPACE_MODEL("StateSpaceModel"),
      @JsonProperty("GARCH")
      @Added(Version.PMML_4_4)
      GARCH("GARCH");

      private final String value;

      private Algorithm(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static Algorithm fromValue(String v) {
         for(Algorithm c : values()) {
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
