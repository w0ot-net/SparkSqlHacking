package org.sparkproject.dmg.pmml.association;

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
import org.sparkproject.dmg.pmml.Entity;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.ProbabilityNumberAdapter;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "AssociationRule",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("AssociationRule")
@JsonPropertyOrder({"antecedent", "consequent", "support", "confidence", "lift", "leverage", "affinity", "id", "extensions"})
public class AssociationRule extends Entity implements HasExtensions {
   @XmlAttribute(
      name = "antecedent",
      required = true
   )
   @JsonProperty("antecedent")
   private String antecedent;
   @XmlAttribute(
      name = "consequent",
      required = true
   )
   @JsonProperty("consequent")
   private String consequent;
   @XmlAttribute(
      name = "support",
      required = true
   )
   @XmlJavaTypeAdapter(ProbabilityNumberAdapter.class)
   @JsonProperty("support")
   private Number support;
   @XmlAttribute(
      name = "confidence",
      required = true
   )
   @XmlJavaTypeAdapter(ProbabilityNumberAdapter.class)
   @JsonProperty("confidence")
   private Number confidence;
   @XmlAttribute(
      name = "lift"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("lift")
   private Number lift;
   @XmlAttribute(
      name = "leverage"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("leverage")
   @Added(Version.PMML_4_1)
   private Number leverage;
   @XmlAttribute(
      name = "affinity"
   )
   @XmlJavaTypeAdapter(ProbabilityNumberAdapter.class)
   @JsonProperty("affinity")
   @Added(Version.PMML_4_1)
   private Number affinity;
   @XmlAttribute(
      name = "id"
   )
   @JsonProperty("id")
   private String id;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final long serialVersionUID = 67371272L;

   public AssociationRule() {
   }

   @ValueConstructor
   public AssociationRule(@Property("antecedent") String antecedent, @Property("consequent") String consequent, @Property("support") Number support, @Property("confidence") Number confidence) {
      this.antecedent = antecedent;
      this.consequent = consequent;
      this.support = support;
      this.confidence = confidence;
   }

   public String requireAntecedent() {
      if (this.antecedent == null) {
         throw new MissingAttributeException(this, PMMLAttributes.ASSOCIATIONRULE_ANTECEDENT);
      } else {
         return this.antecedent;
      }
   }

   public String getAntecedent() {
      return this.antecedent;
   }

   public AssociationRule setAntecedent(@Property("antecedent") String antecedent) {
      this.antecedent = antecedent;
      return this;
   }

   public String requireConsequent() {
      if (this.consequent == null) {
         throw new MissingAttributeException(this, PMMLAttributes.ASSOCIATIONRULE_CONSEQUENT);
      } else {
         return this.consequent;
      }
   }

   public String getConsequent() {
      return this.consequent;
   }

   public AssociationRule setConsequent(@Property("consequent") String consequent) {
      this.consequent = consequent;
      return this;
   }

   public Number requireSupport() {
      if (this.support == null) {
         throw new MissingAttributeException(this, PMMLAttributes.ASSOCIATIONRULE_SUPPORT);
      } else {
         return this.support;
      }
   }

   public Number getSupport() {
      return this.support;
   }

   public AssociationRule setSupport(@Property("support") Number support) {
      this.support = support;
      return this;
   }

   public Number requireConfidence() {
      if (this.confidence == null) {
         throw new MissingAttributeException(this, PMMLAttributes.ASSOCIATIONRULE_CONFIDENCE);
      } else {
         return this.confidence;
      }
   }

   public Number getConfidence() {
      return this.confidence;
   }

   public AssociationRule setConfidence(@Property("confidence") Number confidence) {
      this.confidence = confidence;
      return this;
   }

   public Number requireLift() {
      if (this.lift == null) {
         throw new MissingAttributeException(this, PMMLAttributes.ASSOCIATIONRULE_LIFT);
      } else {
         return this.lift;
      }
   }

   public Number getLift() {
      return this.lift;
   }

   public AssociationRule setLift(@Property("lift") Number lift) {
      this.lift = lift;
      return this;
   }

   public Number requireLeverage() {
      if (this.leverage == null) {
         throw new MissingAttributeException(this, PMMLAttributes.ASSOCIATIONRULE_LEVERAGE);
      } else {
         return this.leverage;
      }
   }

   public Number getLeverage() {
      return this.leverage;
   }

   public AssociationRule setLeverage(@Property("leverage") Number leverage) {
      this.leverage = leverage;
      return this;
   }

   public Number requireAffinity() {
      if (this.affinity == null) {
         throw new MissingAttributeException(this, PMMLAttributes.ASSOCIATIONRULE_AFFINITY);
      } else {
         return this.affinity;
      }
   }

   public Number getAffinity() {
      return this.affinity;
   }

   public AssociationRule setAffinity(@Property("affinity") Number affinity) {
      this.affinity = affinity;
      return this;
   }

   public String getId() {
      return this.id;
   }

   public AssociationRule setId(@Property("id") String id) {
      this.id = id;
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

   public AssociationRule addExtensions(Extension... extensions) {
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
}
