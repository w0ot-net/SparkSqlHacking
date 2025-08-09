package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.adapters.NumberAdapter;
import org.sparkproject.dmg.pmml.adapters.ObjectAdapter;
import org.sparkproject.dmg.pmml.adapters.ProbabilityNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;

@XmlRootElement(
   name = "TargetValue",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "partition"}
)
@JsonRootName("TargetValue")
@JsonPropertyOrder({"value", "displayValue", "priorProbability", "defaultValue", "extensions", "partition"})
public class TargetValue extends PMMLObject implements HasDisplayValue, HasExtensions, HasValue {
   @XmlAttribute(
      name = "value"
   )
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @JsonProperty("value")
   private Object value;
   @XmlAttribute(
      name = "displayValue"
   )
   @JsonProperty("displayValue")
   @Added(Version.PMML_3_2)
   private String displayValue;
   @XmlAttribute(
      name = "priorProbability"
   )
   @XmlJavaTypeAdapter(ProbabilityNumberAdapter.class)
   @JsonProperty("priorProbability")
   private Number priorProbability;
   @XmlAttribute(
      name = "defaultValue"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("defaultValue")
   private Number defaultValue;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "Partition",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Partition")
   @Added(Version.PMML_3_2)
   private Partition partition;
   private static final long serialVersionUID = 67371272L;

   public Object requireValue() {
      if (this.value == null) {
         throw new MissingAttributeException(this, PMMLAttributes.TARGETVALUE_VALUE);
      } else {
         return this.value;
      }
   }

   public Object getValue() {
      return this.value;
   }

   public TargetValue setValue(@Property("value") Object value) {
      this.value = value;
      return this;
   }

   public String getDisplayValue() {
      return this.displayValue;
   }

   public TargetValue setDisplayValue(@Property("displayValue") String displayValue) {
      this.displayValue = displayValue;
      return this;
   }

   public Number requirePriorProbability() {
      if (this.priorProbability == null) {
         throw new MissingAttributeException(this, PMMLAttributes.TARGETVALUE_PRIORPROBABILITY);
      } else {
         return this.priorProbability;
      }
   }

   public Number getPriorProbability() {
      return this.priorProbability;
   }

   public TargetValue setPriorProbability(@Property("priorProbability") Number priorProbability) {
      this.priorProbability = priorProbability;
      return this;
   }

   public Number requireDefaultValue() {
      if (this.defaultValue == null) {
         throw new MissingAttributeException(this, PMMLAttributes.TARGETVALUE_DEFAULTVALUE);
      } else {
         return this.defaultValue;
      }
   }

   public Number getDefaultValue() {
      return this.defaultValue;
   }

   public TargetValue setDefaultValue(@Property("defaultValue") Number defaultValue) {
      this.defaultValue = defaultValue;
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

   public TargetValue addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Partition getPartition() {
      return this.partition;
   }

   public TargetValue setPartition(@Property("partition") Partition partition) {
      this.partition = partition;
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
            status = PMMLObject.traverse(visitor, (Visitable)this.getPartition());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
