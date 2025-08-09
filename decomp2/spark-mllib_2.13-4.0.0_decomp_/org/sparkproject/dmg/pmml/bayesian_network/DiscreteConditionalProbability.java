package org.sparkproject.dmg.pmml.bayesian_network;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.PMMLObject;
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
   name = "DiscreteConditionalProbability",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "parentValues", "valueProbabilities"}
)
@JsonRootName("DiscreteConditionalProbability")
@JsonPropertyOrder({"count", "extensions", "parentValues", "valueProbabilities"})
@Added(Version.PMML_4_3)
public class DiscreteConditionalProbability extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "count"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("count")
   private Number count;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "ParentValue",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("ParentValue")
   @CollectionElementType(ParentValue.class)
   private List parentValues;
   @XmlElement(
      name = "ValueProbability",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("ValueProbability")
   @CollectionElementType(ValueProbability.class)
   private List valueProbabilities;
   private static final long serialVersionUID = 67371272L;

   public DiscreteConditionalProbability() {
   }

   @ValueConstructor
   public DiscreteConditionalProbability(@Property("parentValues") List parentValues, @Property("valueProbabilities") List valueProbabilities) {
      this.parentValues = parentValues;
      this.valueProbabilities = valueProbabilities;
   }

   public Number getCount() {
      return this.count;
   }

   public DiscreteConditionalProbability setCount(@Property("count") Number count) {
      this.count = count;
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

   public DiscreteConditionalProbability addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public boolean hasParentValues() {
      return this.parentValues != null && !this.parentValues.isEmpty();
   }

   public List requireParentValues() {
      if (this.parentValues != null && !this.parentValues.isEmpty()) {
         return this.parentValues;
      } else {
         throw new MissingElementException(this, PMMLElements.DISCRETECONDITIONALPROBABILITY_PARENTVALUES);
      }
   }

   public List getParentValues() {
      if (this.parentValues == null) {
         this.parentValues = new ArrayList();
      }

      return this.parentValues;
   }

   public DiscreteConditionalProbability addParentValues(ParentValue... parentValues) {
      this.getParentValues().addAll(Arrays.asList(parentValues));
      return this;
   }

   public boolean hasValueProbabilities() {
      return this.valueProbabilities != null && !this.valueProbabilities.isEmpty();
   }

   public List requireValueProbabilities() {
      if (this.valueProbabilities != null && !this.valueProbabilities.isEmpty()) {
         return this.valueProbabilities;
      } else {
         throw new MissingElementException(this, PMMLElements.DISCRETECONDITIONALPROBABILITY_VALUEPROBABILITIES);
      }
   }

   public List getValueProbabilities() {
      if (this.valueProbabilities == null) {
         this.valueProbabilities = new ArrayList();
      }

      return this.valueProbabilities;
   }

   public DiscreteConditionalProbability addValueProbabilities(ValueProbability... valueProbabilities) {
      this.getValueProbabilities().addAll(Arrays.asList(valueProbabilities));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasParentValues()) {
            status = PMMLObject.traverse(visitor, this.getParentValues());
         }

         if (status == VisitorAction.CONTINUE && this.hasValueProbabilities()) {
            status = PMMLObject.traverse(visitor, this.getValueProbabilities());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
