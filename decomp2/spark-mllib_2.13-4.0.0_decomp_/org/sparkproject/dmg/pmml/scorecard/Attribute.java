package org.sparkproject.dmg.pmml.scorecard;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.CompoundPredicate;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.False;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.HasPredicate;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Predicate;
import org.sparkproject.dmg.pmml.SimplePredicate;
import org.sparkproject.dmg.pmml.SimpleSetPredicate;
import org.sparkproject.dmg.pmml.True;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.NumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Optional;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Attribute",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "predicate", "complexPartialScore"}
)
@JsonRootName("Attribute")
@JsonPropertyOrder({"reasonCode", "partialScore", "extensions", "predicate", "complexPartialScore"})
@Added(Version.PMML_4_1)
public class Attribute extends PMMLObject implements HasExtensions, HasPredicate, HasReasonCode {
   @XmlAttribute(
      name = "reasonCode"
   )
   @JsonProperty("reasonCode")
   private String reasonCode;
   @XmlAttribute(
      name = "partialScore"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("partialScore")
   @Optional(Version.PMML_4_2)
   private Number partialScore;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElements({@XmlElement(
   name = "SimplePredicate",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = SimplePredicate.class
), @XmlElement(
   name = "CompoundPredicate",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = CompoundPredicate.class
), @XmlElement(
   name = "SimpleSetPredicate",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = SimpleSetPredicate.class
), @XmlElement(
   name = "True",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = True.class
), @XmlElement(
   name = "False",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = False.class
)})
   @JsonProperty("Predicate")
   @JsonTypeInfo(
      include = As.WRAPPER_OBJECT,
      use = Id.NAME
   )
   @JsonSubTypes({@Type(
   name = "SimplePredicate",
   value = SimplePredicate.class
), @Type(
   name = "CompoundPredicate",
   value = CompoundPredicate.class
), @Type(
   name = "SimpleSetPredicate",
   value = SimpleSetPredicate.class
), @Type(
   name = "True",
   value = True.class
), @Type(
   name = "False",
   value = False.class
)})
   private Predicate predicate;
   @XmlElement(
      name = "ComplexPartialScore",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("ComplexPartialScore")
   @Added(Version.PMML_4_2)
   private ComplexPartialScore complexPartialScore;
   private static final long serialVersionUID = 67371272L;

   public Attribute() {
   }

   @ValueConstructor
   public Attribute(@Property("predicate") Predicate predicate) {
      this.predicate = predicate;
   }

   public String getReasonCode() {
      return this.reasonCode;
   }

   public Attribute setReasonCode(@Property("reasonCode") String reasonCode) {
      this.reasonCode = reasonCode;
      return this;
   }

   public Number requirePartialScore() {
      if (this.partialScore == null) {
         throw new MissingAttributeException(this, PMMLAttributes.ATTRIBUTE_PARTIALSCORE);
      } else {
         return this.partialScore;
      }
   }

   public Number getPartialScore() {
      return this.partialScore;
   }

   public Attribute setPartialScore(@Property("partialScore") Number partialScore) {
      this.partialScore = partialScore;
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

   public Attribute addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Predicate requirePredicate() {
      if (this.predicate == null) {
         throw new MissingElementException(this, PMMLElements.ATTRIBUTE_PREDICATE);
      } else {
         return this.predicate;
      }
   }

   public Predicate getPredicate() {
      return this.predicate;
   }

   public Attribute setPredicate(@Property("predicate") Predicate predicate) {
      this.predicate = predicate;
      return this;
   }

   public ComplexPartialScore getComplexPartialScore() {
      return this.complexPartialScore;
   }

   public Attribute setComplexPartialScore(@Property("complexPartialScore") ComplexPartialScore complexPartialScore) {
      this.complexPartialScore = complexPartialScore;
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
            status = PMMLObject.traverse(visitor, this.getPredicate(), this.getComplexPartialScore());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
