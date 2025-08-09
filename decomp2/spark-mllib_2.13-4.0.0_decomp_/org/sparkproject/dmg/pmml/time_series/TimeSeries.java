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
import org.sparkproject.dmg.pmml.HasFieldReference;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.StringValue;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitable;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.FieldNameAdapter;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.Removed;
import org.sparkproject.jpmml.model.annotations.Required;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "TimeSeries",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"timeAnchor", "timeExceptions", "timeValues"}
)
@JsonRootName("TimeSeries")
@JsonPropertyOrder({"usage", "startTime", "endTime", "interpolationMethod", "field", "timeAnchor", "timeExceptions", "timeValues"})
@Added(Version.PMML_4_0)
public class TimeSeries extends PMMLObject implements HasFieldReference {
   @XmlAttribute(
      name = "usage"
   )
   @JsonProperty("usage")
   private Usage usage;
   @XmlAttribute(
      name = "startTime"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("startTime")
   private Number startTime;
   @XmlAttribute(
      name = "endTime"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("endTime")
   private Number endTime;
   @XmlAttribute(
      name = "interpolationMethod"
   )
   @JsonProperty("interpolationMethod")
   private InterpolationMethod interpolationMethod;
   @XmlAttribute(
      name = "field"
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("field")
   @Added(Version.PMML_4_4)
   private String field;
   @XmlElement(
      name = "TimeAnchor",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("TimeAnchor")
   private TimeAnchor timeAnchor;
   @XmlElement(
      name = "TimeException",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("TimeException")
   @Removed(Version.PMML_4_1)
   @CollectionElementType(TimeException.class)
   private List timeExceptions;
   @XmlElement(
      name = "TimeValue",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("TimeValue")
   @Required(Version.PMML_4_4)
   @CollectionElementType(TimeValue.class)
   private List timeValues;
   private static final long serialVersionUID = 67371272L;

   public TimeSeries() {
   }

   @ValueConstructor
   public TimeSeries(@Property("timeValues") List timeValues) {
      this.timeValues = timeValues;
   }

   public Usage getUsage() {
      return this.usage == null ? TimeSeries.Usage.ORIGINAL : this.usage;
   }

   public TimeSeries setUsage(@Property("usage") Usage usage) {
      this.usage = usage;
      return this;
   }

   public Number getStartTime() {
      return this.startTime;
   }

   public TimeSeries setStartTime(@Property("startTime") Number startTime) {
      this.startTime = startTime;
      return this;
   }

   public Number getEndTime() {
      return this.endTime;
   }

   public TimeSeries setEndTime(@Property("endTime") Number endTime) {
      this.endTime = endTime;
      return this;
   }

   public InterpolationMethod getInterpolationMethod() {
      return this.interpolationMethod == null ? TimeSeries.InterpolationMethod.NONE : this.interpolationMethod;
   }

   public TimeSeries setInterpolationMethod(@Property("interpolationMethod") InterpolationMethod interpolationMethod) {
      this.interpolationMethod = interpolationMethod;
      return this;
   }

   public String getField() {
      return this.field;
   }

   public TimeSeries setField(@Property("field") String field) {
      this.field = field;
      return this;
   }

   public TimeAnchor getTimeAnchor() {
      return this.timeAnchor;
   }

   public TimeSeries setTimeAnchor(@Property("timeAnchor") TimeAnchor timeAnchor) {
      this.timeAnchor = timeAnchor;
      return this;
   }

   public boolean hasTimeExceptions() {
      return this.timeExceptions != null && !this.timeExceptions.isEmpty();
   }

   public List getTimeExceptions() {
      if (this.timeExceptions == null) {
         this.timeExceptions = new ArrayList();
      }

      return this.timeExceptions;
   }

   public TimeSeries addTimeExceptions(TimeException... timeExceptions) {
      this.getTimeExceptions().addAll(Arrays.asList(timeExceptions));
      return this;
   }

   public boolean hasTimeValues() {
      return this.timeValues != null && !this.timeValues.isEmpty();
   }

   public List requireTimeValues() {
      if (this.timeValues != null && !this.timeValues.isEmpty()) {
         return this.timeValues;
      } else {
         throw new MissingElementException(this, PMMLElements.TIMESERIES_TIMEVALUES);
      }
   }

   public List getTimeValues() {
      if (this.timeValues == null) {
         this.timeValues = new ArrayList();
      }

      return this.timeValues;
   }

   public TimeSeries addTimeValues(TimeValue... timeValues) {
      this.getTimeValues().addAll(Arrays.asList(timeValues));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE) {
            status = PMMLObject.traverse(visitor, (Visitable)this.getTimeAnchor());
         }

         if (status == VisitorAction.CONTINUE && this.hasTimeExceptions()) {
            status = PMMLObject.traverse(visitor, this.getTimeExceptions());
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
   public static enum InterpolationMethod implements StringValue {
      @XmlEnumValue("none")
      @JsonProperty("none")
      NONE("none"),
      @XmlEnumValue("linear")
      @JsonProperty("linear")
      LINEAR("linear"),
      @XmlEnumValue("exponentialSpline")
      @JsonProperty("exponentialSpline")
      EXPONENTIAL_SPLINE("exponentialSpline"),
      @XmlEnumValue("cubicSpline")
      @JsonProperty("cubicSpline")
      CUBIC_SPLINE("cubicSpline");

      private final String value;

      private InterpolationMethod(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static InterpolationMethod fromValue(String v) {
         for(InterpolationMethod c : values()) {
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
   public static enum Usage implements StringValue {
      @XmlEnumValue("original")
      @JsonProperty("original")
      ORIGINAL("original"),
      @XmlEnumValue("logical")
      @JsonProperty("logical")
      LOGICAL("logical"),
      @XmlEnumValue("prediction")
      @JsonProperty("prediction")
      PREDICTION("prediction");

      private final String value;

      private Usage(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static Usage fromValue(String v) {
         for(Usage c : values()) {
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
