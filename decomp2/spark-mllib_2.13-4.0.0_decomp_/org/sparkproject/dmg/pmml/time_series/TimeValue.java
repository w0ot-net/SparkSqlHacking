package org.sparkproject.dmg.pmml.time_series;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Timestamp;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitable;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.IntegerAdapter;
import org.sparkproject.dmg.pmml.adapters.NumberAdapter;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "TimeValue",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"timestamp"}
)
@JsonRootName("TimeValue")
@JsonPropertyOrder({"index", "time", "value", "standardError", "timestamp"})
@Added(Version.PMML_4_0)
public class TimeValue extends PMMLObject {
   @XmlAttribute(
      name = "index"
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("index")
   private Integer index;
   @XmlAttribute(
      name = "time"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("time")
   private Number time;
   @XmlAttribute(
      name = "value",
      required = true
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("value")
   private Number value;
   @XmlAttribute(
      name = "standardError"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("standardError")
   private Number standardError;
   @XmlElement(
      name = "Timestamp",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Timestamp")
   private Timestamp timestamp;
   private static final long serialVersionUID = 67371272L;

   public TimeValue() {
   }

   @ValueConstructor
   public TimeValue(@Property("value") Number value) {
      this.value = value;
   }

   public Integer getIndex() {
      return this.index;
   }

   public TimeValue setIndex(@Property("index") Integer index) {
      this.index = index;
      return this;
   }

   public Number getTime() {
      return this.time;
   }

   public TimeValue setTime(@Property("time") Number time) {
      this.time = time;
      return this;
   }

   public Number requireValue() {
      if (this.value == null) {
         throw new MissingAttributeException(this, PMMLAttributes.TIMEVALUE_VALUE);
      } else {
         return this.value;
      }
   }

   public Number getValue() {
      return this.value;
   }

   public TimeValue setValue(@Property("value") Number value) {
      this.value = value;
      return this;
   }

   public Number getStandardError() {
      return this.standardError;
   }

   public TimeValue setStandardError(@Property("standardError") Number standardError) {
      this.standardError = standardError;
      return this;
   }

   public Timestamp getTimestamp() {
      return this.timestamp;
   }

   public TimeValue setTimestamp(@Property("timestamp") Timestamp timestamp) {
      this.timestamp = timestamp;
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE) {
            status = PMMLObject.traverse(visitor, (Visitable)this.getTimestamp());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
