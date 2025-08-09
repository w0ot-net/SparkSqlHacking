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
import org.sparkproject.dmg.pmml.HasDisplayName;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.StringValue;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.IntegerAdapter;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;

@XmlRootElement(
   name = "TimeAnchor",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"timeCycles", "timeExceptions"}
)
@JsonRootName("TimeAnchor")
@JsonPropertyOrder({"type", "offset", "stepsize", "displayName", "timeCycles", "timeExceptions"})
@Added(Version.PMML_4_0)
public class TimeAnchor extends PMMLObject implements HasDisplayName {
   @XmlAttribute(
      name = "type"
   )
   @JsonProperty("type")
   private Type type;
   @XmlAttribute(
      name = "offset"
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("offset")
   private Integer offset;
   @XmlAttribute(
      name = "stepsize"
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("stepsize")
   private Integer stepsize;
   @XmlAttribute(
      name = "displayName"
   )
   @JsonProperty("displayName")
   private String displayName;
   @XmlElement(
      name = "TimeCycle",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("TimeCycle")
   @CollectionElementType(TimeCycle.class)
   private List timeCycles;
   @XmlElement(
      name = "TimeException",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("TimeException")
   @CollectionElementType(TimeException.class)
   private List timeExceptions;
   private static final long serialVersionUID = 67371272L;

   public Type getType() {
      return this.type;
   }

   public TimeAnchor setType(@Property("type") Type type) {
      this.type = type;
      return this;
   }

   public Integer getOffset() {
      return this.offset;
   }

   public TimeAnchor setOffset(@Property("offset") Integer offset) {
      this.offset = offset;
      return this;
   }

   public Integer getStepsize() {
      return this.stepsize;
   }

   public TimeAnchor setStepsize(@Property("stepsize") Integer stepsize) {
      this.stepsize = stepsize;
      return this;
   }

   public String getDisplayName() {
      return this.displayName;
   }

   public TimeAnchor setDisplayName(@Property("displayName") String displayName) {
      this.displayName = displayName;
      return this;
   }

   public boolean hasTimeCycles() {
      return this.timeCycles != null && !this.timeCycles.isEmpty();
   }

   public List getTimeCycles() {
      if (this.timeCycles == null) {
         this.timeCycles = new ArrayList();
      }

      return this.timeCycles;
   }

   public TimeAnchor addTimeCycles(TimeCycle... timeCycles) {
      this.getTimeCycles().addAll(Arrays.asList(timeCycles));
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

   public TimeAnchor addTimeExceptions(TimeException... timeExceptions) {
      this.getTimeExceptions().addAll(Arrays.asList(timeExceptions));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasTimeCycles()) {
            status = PMMLObject.traverse(visitor, this.getTimeCycles());
         }

         if (status == VisitorAction.CONTINUE && this.hasTimeExceptions()) {
            status = PMMLObject.traverse(visitor, this.getTimeExceptions());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum Type implements StringValue {
      @XmlEnumValue("dateTimeMillisecondsSince[0]")
      @JsonProperty("dateTimeMillisecondsSince[0]")
      DATE_TIME_MILLISECONDS_SINCE_0("dateTimeMillisecondsSince[0]"),
      @XmlEnumValue("dateTimeMillisecondsSince[1960]")
      @JsonProperty("dateTimeMillisecondsSince[1960]")
      DATE_TIME_MILLISECONDS_SINCE_1960("dateTimeMillisecondsSince[1960]"),
      @XmlEnumValue("dateTimeMillisecondsSince[1970]")
      @JsonProperty("dateTimeMillisecondsSince[1970]")
      DATE_TIME_MILLISECONDS_SINCE_1970("dateTimeMillisecondsSince[1970]"),
      @XmlEnumValue("dateTimeMillisecondsSince[1980]")
      @JsonProperty("dateTimeMillisecondsSince[1980]")
      DATE_TIME_MILLISECONDS_SINCE_1980("dateTimeMillisecondsSince[1980]"),
      @XmlEnumValue("dateTimeSecondsSince[0]")
      @JsonProperty("dateTimeSecondsSince[0]")
      DATE_TIME_SECONDS_SINCE_0("dateTimeSecondsSince[0]"),
      @XmlEnumValue("dateTimeSecondsSince[1960]")
      @JsonProperty("dateTimeSecondsSince[1960]")
      DATE_TIME_SECONDS_SINCE_1960("dateTimeSecondsSince[1960]"),
      @XmlEnumValue("dateTimeSecondsSince[1970]")
      @JsonProperty("dateTimeSecondsSince[1970]")
      DATE_TIME_SECONDS_SINCE_1970("dateTimeSecondsSince[1970]"),
      @XmlEnumValue("dateTimeSecondsSince[1980]")
      @JsonProperty("dateTimeSecondsSince[1980]")
      DATE_TIME_SECONDS_SINCE_1980("dateTimeSecondsSince[1980]"),
      @XmlEnumValue("dateDaysSince[0]")
      @JsonProperty("dateDaysSince[0]")
      DATE_DAYS_SINCE_0("dateDaysSince[0]"),
      @XmlEnumValue("dateDaysSince[1960]")
      @JsonProperty("dateDaysSince[1960]")
      DATE_DAYS_SINCE_1960("dateDaysSince[1960]"),
      @XmlEnumValue("dateDaysSince[1970]")
      @JsonProperty("dateDaysSince[1970]")
      DATE_DAYS_SINCE_1970("dateDaysSince[1970]"),
      @XmlEnumValue("dateDaysSince[1980]")
      @JsonProperty("dateDaysSince[1980]")
      DATE_DAYS_SINCE_1980("dateDaysSince[1980]"),
      @XmlEnumValue("dateMonthsSince[0]")
      @JsonProperty("dateMonthsSince[0]")
      DATE_MONTHS_SINCE_0("dateMonthsSince[0]"),
      @XmlEnumValue("dateMonthsSince[1960]")
      @JsonProperty("dateMonthsSince[1960]")
      DATE_MONTHS_SINCE_1960("dateMonthsSince[1960]"),
      @XmlEnumValue("dateMonthsSince[1970]")
      @JsonProperty("dateMonthsSince[1970]")
      DATE_MONTHS_SINCE_1970("dateMonthsSince[1970]"),
      @XmlEnumValue("dateMonthsSince[1980]")
      @JsonProperty("dateMonthsSince[1980]")
      DATE_MONTHS_SINCE_1980("dateMonthsSince[1980]"),
      @XmlEnumValue("dateYearsSince[0]")
      @JsonProperty("dateYearsSince[0]")
      DATE_YEARS_SINCE_0("dateYearsSince[0]");

      private final String value;

      private Type(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static Type fromValue(String v) {
         for(Type c : values()) {
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
