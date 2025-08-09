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
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "OutlierEffect",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("OutlierEffect")
@JsonPropertyOrder({"type", "startTime", "magnitude", "dampingCoefficient", "extensions"})
@Added(Version.PMML_4_4)
public class OutlierEffect extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "type",
      required = true
   )
   @JsonProperty("type")
   private Type type;
   @XmlAttribute(
      name = "startTime",
      required = true
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("startTime")
   private Number startTime;
   @XmlAttribute(
      name = "magnitude",
      required = true
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("magnitude")
   private Number magnitude;
   @XmlAttribute(
      name = "dampingCoefficient"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("dampingCoefficient")
   private Number dampingCoefficient;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final long serialVersionUID = 67371272L;

   public OutlierEffect() {
   }

   @ValueConstructor
   public OutlierEffect(@Property("type") Type type, @Property("startTime") Number startTime, @Property("magnitude") Number magnitude) {
      this.type = type;
      this.startTime = startTime;
      this.magnitude = magnitude;
   }

   public Type requireType() {
      if (this.type == null) {
         throw new MissingAttributeException(this, PMMLAttributes.OUTLIEREFFECT_TYPE);
      } else {
         return this.type;
      }
   }

   public Type getType() {
      return this.type;
   }

   public OutlierEffect setType(@Property("type") Type type) {
      this.type = type;
      return this;
   }

   public Number requireStartTime() {
      if (this.startTime == null) {
         throw new MissingAttributeException(this, PMMLAttributes.OUTLIEREFFECT_STARTTIME);
      } else {
         return this.startTime;
      }
   }

   public Number getStartTime() {
      return this.startTime;
   }

   public OutlierEffect setStartTime(@Property("startTime") Number startTime) {
      this.startTime = startTime;
      return this;
   }

   public Number requireMagnitude() {
      if (this.magnitude == null) {
         throw new MissingAttributeException(this, PMMLAttributes.OUTLIEREFFECT_MAGNITUDE);
      } else {
         return this.magnitude;
      }
   }

   public Number getMagnitude() {
      return this.magnitude;
   }

   public OutlierEffect setMagnitude(@Property("magnitude") Number magnitude) {
      this.magnitude = magnitude;
      return this;
   }

   public Number getDampingCoefficient() {
      return this.dampingCoefficient;
   }

   public OutlierEffect setDampingCoefficient(@Property("dampingCoefficient") Number dampingCoefficient) {
      this.dampingCoefficient = dampingCoefficient;
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

   public OutlierEffect addExtensions(Extension... extensions) {
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
   public static enum Type implements StringValue {
      @XmlEnumValue("additive")
      @JsonProperty("additive")
      ADDITIVE("additive"),
      @XmlEnumValue("level")
      @JsonProperty("level")
      LEVEL("level"),
      @XmlEnumValue("transient")
      @JsonProperty("transient")
      TRANSIENT("transient"),
      @XmlEnumValue("seasonalAdditive")
      @JsonProperty("seasonalAdditive")
      SEASONAL_ADDITIVE("seasonalAdditive"),
      @XmlEnumValue("trend")
      @JsonProperty("trend")
      TREND("trend"),
      @XmlEnumValue("innovational")
      @JsonProperty("innovational")
      INNOVATIONAL("innovational");

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
