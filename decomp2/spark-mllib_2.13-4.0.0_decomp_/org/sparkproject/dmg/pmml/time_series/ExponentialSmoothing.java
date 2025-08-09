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
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.StringValue;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "ExponentialSmoothing",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"level", "trendExpoSmooth", "seasonalityExpoSmooth", "timeValues"}
)
@JsonRootName("ExponentialSmoothing")
@JsonPropertyOrder({"rmse", "transformation", "level", "trendExpoSmooth", "seasonalityExpoSmooth", "timeValues"})
@Added(Version.PMML_4_0)
public class ExponentialSmoothing extends Algorithm {
   @XmlAttribute(
      name = "RMSE"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("RMSE")
   private Number rmse;
   @XmlAttribute(
      name = "transformation"
   )
   @JsonProperty("transformation")
   private Transformation transformation;
   @XmlElement(
      name = "Level",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("Level")
   private Level level;
   @XmlElement(
      name = "Trend_ExpoSmooth",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Trend_ExpoSmooth")
   private TrendExpoSmooth trendExpoSmooth;
   @XmlElement(
      name = "Seasonality_ExpoSmooth",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Seasonality_ExpoSmooth")
   private SeasonalityExpoSmooth seasonalityExpoSmooth;
   @XmlElement(
      name = "TimeValue",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("TimeValue")
   @CollectionElementType(TimeValue.class)
   private List timeValues;
   private static final long serialVersionUID = 67371272L;

   public ExponentialSmoothing() {
   }

   @ValueConstructor
   public ExponentialSmoothing(@Property("level") Level level) {
      this.level = level;
   }

   public Number getRMSE() {
      return this.rmse;
   }

   public ExponentialSmoothing setRMSE(@Property("rmse") Number rmse) {
      this.rmse = rmse;
      return this;
   }

   public Transformation getTransformation() {
      return this.transformation == null ? ExponentialSmoothing.Transformation.NONE : this.transformation;
   }

   public ExponentialSmoothing setTransformation(@Property("transformation") Transformation transformation) {
      this.transformation = transformation;
      return this;
   }

   public Level requireLevel() {
      if (this.level == null) {
         throw new MissingElementException(this, PMMLElements.EXPONENTIALSMOOTHING_LEVEL);
      } else {
         return this.level;
      }
   }

   public Level getLevel() {
      return this.level;
   }

   public ExponentialSmoothing setLevel(@Property("level") Level level) {
      this.level = level;
      return this;
   }

   public TrendExpoSmooth getTrendExpoSmooth() {
      return this.trendExpoSmooth;
   }

   public ExponentialSmoothing setTrendExpoSmooth(@Property("trendExpoSmooth") TrendExpoSmooth trendExpoSmooth) {
      this.trendExpoSmooth = trendExpoSmooth;
      return this;
   }

   public SeasonalityExpoSmooth getSeasonalityExpoSmooth() {
      return this.seasonalityExpoSmooth;
   }

   public ExponentialSmoothing setSeasonalityExpoSmooth(@Property("seasonalityExpoSmooth") SeasonalityExpoSmooth seasonalityExpoSmooth) {
      this.seasonalityExpoSmooth = seasonalityExpoSmooth;
      return this;
   }

   public boolean hasTimeValues() {
      return this.timeValues != null && !this.timeValues.isEmpty();
   }

   public List getTimeValues() {
      if (this.timeValues == null) {
         this.timeValues = new ArrayList();
      }

      return this.timeValues;
   }

   public ExponentialSmoothing addTimeValues(TimeValue... timeValues) {
      this.getTimeValues().addAll(Arrays.asList(timeValues));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE) {
            status = PMMLObject.traverse(visitor, this.getLevel(), this.getTrendExpoSmooth(), this.getSeasonalityExpoSmooth());
         }

         if (status == VisitorAction.CONTINUE && this.hasTimeValues()) {
            status = PMMLObject.traverse(visitor, this.getTimeValues());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
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
