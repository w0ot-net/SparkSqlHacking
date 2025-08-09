package org.sparkproject.dmg.pmml.sequence;

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
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.StringValue;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Delimiter",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("Delimiter")
@JsonPropertyOrder({"delimiter", "gap", "extensions"})
public class Delimiter extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "delimiter",
      required = true
   )
   @JsonProperty("delimiter")
   private TimeWindow delimiter;
   @XmlAttribute(
      name = "gap",
      required = true
   )
   @JsonProperty("gap")
   private Gap gap;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final long serialVersionUID = 67371272L;

   public Delimiter() {
   }

   @ValueConstructor
   public Delimiter(@Property("delimiter") TimeWindow delimiter, @Property("gap") Gap gap) {
      this.delimiter = delimiter;
      this.gap = gap;
   }

   public TimeWindow requireDelimiter() {
      if (this.delimiter == null) {
         throw new MissingAttributeException(this, PMMLAttributes.DELIMITER_DELIMITER);
      } else {
         return this.delimiter;
      }
   }

   public TimeWindow getDelimiter() {
      return this.delimiter;
   }

   public Delimiter setDelimiter(@Property("delimiter") TimeWindow delimiter) {
      this.delimiter = delimiter;
      return this;
   }

   public Gap requireGap() {
      if (this.gap == null) {
         throw new MissingAttributeException(this, PMMLAttributes.DELIMITER_GAP);
      } else {
         return this.gap;
      }
   }

   public Gap getGap() {
      return this.gap;
   }

   public Delimiter setGap(@Property("gap") Gap gap) {
      this.gap = gap;
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

   public Delimiter addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum Gap implements StringValue {
      @XmlEnumValue("true")
      @JsonProperty("true")
      TRUE("true"),
      @XmlEnumValue("false")
      @JsonProperty("false")
      FALSE("false"),
      @XmlEnumValue("unknown")
      @JsonProperty("unknown")
      UNKNOWN("unknown");

      private final String value;

      private Gap(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static Gap fromValue(String v) {
         for(Gap c : values()) {
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
   public static enum TimeWindow implements StringValue {
      @XmlEnumValue("sameTimeWindow")
      @JsonProperty("sameTimeWindow")
      SAME_TIME_WINDOW("sameTimeWindow"),
      @XmlEnumValue("acrossTimeWindows")
      @JsonProperty("acrossTimeWindows")
      ACROSS_TIME_WINDOWS("acrossTimeWindows");

      private final String value;

      private TimeWindow(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static TimeWindow fromValue(String v) {
         for(TimeWindow c : values()) {
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
