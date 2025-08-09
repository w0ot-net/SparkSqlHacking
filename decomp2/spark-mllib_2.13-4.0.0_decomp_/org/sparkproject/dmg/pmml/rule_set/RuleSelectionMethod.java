package org.sparkproject.dmg.pmml.rule_set;

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
   name = "RuleSelectionMethod",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("RuleSelectionMethod")
@JsonPropertyOrder({"criterion", "extensions"})
public class RuleSelectionMethod extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "criterion",
      required = true
   )
   @JsonProperty("criterion")
   private Criterion criterion;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final long serialVersionUID = 67371272L;

   public RuleSelectionMethod() {
   }

   @ValueConstructor
   public RuleSelectionMethod(@Property("criterion") Criterion criterion) {
      this.criterion = criterion;
   }

   public Criterion requireCriterion() {
      if (this.criterion == null) {
         throw new MissingAttributeException(this, PMMLAttributes.RULESELECTIONMETHOD_CRITERION);
      } else {
         return this.criterion;
      }
   }

   public Criterion getCriterion() {
      return this.criterion;
   }

   public RuleSelectionMethod setCriterion(@Property("criterion") Criterion criterion) {
      this.criterion = criterion;
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

   public RuleSelectionMethod addExtensions(Extension... extensions) {
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
   public static enum Criterion implements StringValue {
      @XmlEnumValue("weightedSum")
      @JsonProperty("weightedSum")
      WEIGHTED_SUM("weightedSum"),
      @XmlEnumValue("weightedMax")
      @JsonProperty("weightedMax")
      WEIGHTED_MAX("weightedMax"),
      @XmlEnumValue("firstHit")
      @JsonProperty("firstHit")
      FIRST_HIT("firstHit");

      private final String value;

      private Criterion(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static Criterion fromValue(String v) {
         for(Criterion c : values()) {
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
