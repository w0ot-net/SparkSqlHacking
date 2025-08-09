package org.sparkproject.dmg.pmml.naive_bayes;

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
import org.sparkproject.dmg.pmml.DerivedField;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.Field;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.HasFieldReference;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.FieldNameAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.AlternateValueConstructor;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "BayesInput",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "targetValueStats", "derivedField", "pairCounts"}
)
@JsonRootName("BayesInput")
@JsonPropertyOrder({"field", "extensions", "targetValueStats", "derivedField", "pairCounts"})
public class BayesInput extends PMMLObject implements HasExtensions, HasFieldReference {
   @XmlAttribute(
      name = "fieldName",
      required = true
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("fieldName")
   private String field;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "TargetValueStats",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("TargetValueStats")
   @Added(Version.PMML_4_2)
   private TargetValueStats targetValueStats;
   @XmlElement(
      name = "DerivedField",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("DerivedField")
   private DerivedField derivedField;
   @XmlElement(
      name = "PairCounts",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("PairCounts")
   @CollectionElementType(PairCounts.class)
   private List pairCounts;
   private static final long serialVersionUID = 67371272L;

   public BayesInput() {
   }

   @ValueConstructor
   public BayesInput(@Property("field") String field, @Property("targetValueStats") TargetValueStats targetValueStats, @Property("pairCounts") List pairCounts) {
      this.field = field;
      this.targetValueStats = targetValueStats;
      this.pairCounts = pairCounts;
   }

   @AlternateValueConstructor
   public BayesInput(Field field, TargetValueStats targetValueStats, List pairCounts) {
      this(field != null ? field.requireName() : null, targetValueStats, pairCounts);
   }

   public String requireField() {
      if (this.field == null) {
         throw new MissingAttributeException(this, PMMLAttributes.BAYESINPUT_FIELD);
      } else {
         return this.field;
      }
   }

   public String getField() {
      return this.field;
   }

   public BayesInput setField(@Property("field") String field) {
      this.field = field;
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

   public BayesInput addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public TargetValueStats requireTargetValueStats() {
      if (this.targetValueStats == null) {
         throw new MissingElementException(this, PMMLElements.BAYESINPUT_TARGETVALUESTATS);
      } else {
         return this.targetValueStats;
      }
   }

   public TargetValueStats getTargetValueStats() {
      return this.targetValueStats;
   }

   public BayesInput setTargetValueStats(@Property("targetValueStats") TargetValueStats targetValueStats) {
      this.targetValueStats = targetValueStats;
      return this;
   }

   public DerivedField getDerivedField() {
      return this.derivedField;
   }

   public BayesInput setDerivedField(@Property("derivedField") DerivedField derivedField) {
      this.derivedField = derivedField;
      return this;
   }

   public boolean hasPairCounts() {
      return this.pairCounts != null && !this.pairCounts.isEmpty();
   }

   public List requirePairCounts() {
      if (this.pairCounts != null && !this.pairCounts.isEmpty()) {
         return this.pairCounts;
      } else {
         throw new MissingElementException(this, PMMLElements.BAYESINPUT_PAIRCOUNTS);
      }
   }

   public List getPairCounts() {
      if (this.pairCounts == null) {
         this.pairCounts = new ArrayList();
      }

      return this.pairCounts;
   }

   public BayesInput addPairCounts(PairCounts... pairCounts) {
      this.getPairCounts().addAll(Arrays.asList(pairCounts));
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
            status = PMMLObject.traverse(visitor, this.getTargetValueStats(), this.getDerivedField());
         }

         if (status == VisitorAction.CONTINUE && this.hasPairCounts()) {
            status = PMMLObject.traverse(visitor, this.getPairCounts());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
