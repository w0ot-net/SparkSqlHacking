package org.sparkproject.dmg.pmml.rule_set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.CompoundPredicate;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.False;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Predicate;
import org.sparkproject.dmg.pmml.SimplePredicate;
import org.sparkproject.dmg.pmml.SimpleSetPredicate;
import org.sparkproject.dmg.pmml.True;
import org.sparkproject.dmg.pmml.Visitable;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "CompoundRule",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "predicate", "rules"}
)
@JsonRootName("CompoundRule")
@JsonPropertyOrder({"extensions", "predicate", "rules"})
public class CompoundRule extends Rule implements HasExtensions {
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
   @XmlElements({@XmlElement(
   name = "SimpleRule",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = SimpleRule.class
), @XmlElement(
   name = "CompoundRule",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = CompoundRule.class
)})
   @JsonProperty("Rule")
   @JsonTypeInfo(
      include = As.WRAPPER_OBJECT,
      use = Id.NAME
   )
   @JsonSubTypes({@Type(
   name = "SimpleRule",
   value = SimpleRule.class
), @Type(
   name = "CompoundRule",
   value = CompoundRule.class
)})
   @CollectionElementType(Rule.class)
   private List rules;
   private static final long serialVersionUID = 67371272L;

   public CompoundRule() {
   }

   @ValueConstructor
   public CompoundRule(@Property("predicate") Predicate predicate, @Property("rules") List rules) {
      this.predicate = predicate;
      this.rules = rules;
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

   public CompoundRule addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Predicate requirePredicate() {
      if (this.predicate == null) {
         throw new MissingElementException(this, PMMLElements.COMPOUNDRULE_PREDICATE);
      } else {
         return this.predicate;
      }
   }

   public Predicate getPredicate() {
      return this.predicate;
   }

   public CompoundRule setPredicate(@Property("predicate") Predicate predicate) {
      this.predicate = predicate;
      return this;
   }

   public boolean hasRules() {
      return this.rules != null && !this.rules.isEmpty();
   }

   public List requireRules() {
      if (this.rules != null && !this.rules.isEmpty()) {
         return this.rules;
      } else {
         throw new MissingElementException(this, PMMLElements.COMPOUNDRULE_RULES);
      }
   }

   public List getRules() {
      if (this.rules == null) {
         this.rules = new ArrayList();
      }

      return this.rules;
   }

   public CompoundRule addRules(Rule... rules) {
      this.getRules().addAll(Arrays.asList(rules));
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
            status = PMMLObject.traverse(visitor, (Visitable)this.getPredicate());
         }

         if (status == VisitorAction.CONTINUE && this.hasRules()) {
            status = PMMLObject.traverse(visitor, this.getRules());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
