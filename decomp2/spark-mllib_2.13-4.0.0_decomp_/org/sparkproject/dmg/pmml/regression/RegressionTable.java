package org.sparkproject.dmg.pmml.regression;

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
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.ObjectAdapter;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "RegressionTable",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "numericPredictors", "categoricalPredictors", "predictorTerms"}
)
@JsonRootName("RegressionTable")
@JsonPropertyOrder({"intercept", "targetCategory", "extensions", "numericPredictors", "categoricalPredictors", "predictorTerms"})
public class RegressionTable extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "intercept",
      required = true
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("intercept")
   private Number intercept;
   @XmlAttribute(
      name = "targetCategory"
   )
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @JsonProperty("targetCategory")
   private Object targetCategory;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "NumericPredictor",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("NumericPredictor")
   @CollectionElementType(NumericPredictor.class)
   private List numericPredictors;
   @XmlElement(
      name = "CategoricalPredictor",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("CategoricalPredictor")
   @CollectionElementType(CategoricalPredictor.class)
   private List categoricalPredictors;
   @XmlElement(
      name = "PredictorTerm",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("PredictorTerm")
   @CollectionElementType(PredictorTerm.class)
   private List predictorTerms;
   private static final long serialVersionUID = 67371272L;

   public RegressionTable() {
   }

   @ValueConstructor
   public RegressionTable(@Property("intercept") Number intercept) {
      this.intercept = intercept;
   }

   public Number requireIntercept() {
      if (this.intercept == null) {
         throw new MissingAttributeException(this, PMMLAttributes.REGRESSIONTABLE_INTERCEPT);
      } else {
         return this.intercept;
      }
   }

   public Number getIntercept() {
      return this.intercept;
   }

   public RegressionTable setIntercept(@Property("intercept") Number intercept) {
      this.intercept = intercept;
      return this;
   }

   public Object requireTargetCategory() {
      if (this.targetCategory == null) {
         throw new MissingAttributeException(this, PMMLAttributes.REGRESSIONTABLE_TARGETCATEGORY);
      } else {
         return this.targetCategory;
      }
   }

   public Object getTargetCategory() {
      return this.targetCategory;
   }

   public RegressionTable setTargetCategory(@Property("targetCategory") Object targetCategory) {
      this.targetCategory = targetCategory;
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

   public RegressionTable addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public boolean hasNumericPredictors() {
      return this.numericPredictors != null && !this.numericPredictors.isEmpty();
   }

   public List getNumericPredictors() {
      if (this.numericPredictors == null) {
         this.numericPredictors = new ArrayList();
      }

      return this.numericPredictors;
   }

   public RegressionTable addNumericPredictors(NumericPredictor... numericPredictors) {
      this.getNumericPredictors().addAll(Arrays.asList(numericPredictors));
      return this;
   }

   public boolean hasCategoricalPredictors() {
      return this.categoricalPredictors != null && !this.categoricalPredictors.isEmpty();
   }

   public List getCategoricalPredictors() {
      if (this.categoricalPredictors == null) {
         this.categoricalPredictors = new ArrayList();
      }

      return this.categoricalPredictors;
   }

   public RegressionTable addCategoricalPredictors(CategoricalPredictor... categoricalPredictors) {
      this.getCategoricalPredictors().addAll(Arrays.asList(categoricalPredictors));
      return this;
   }

   public boolean hasPredictorTerms() {
      return this.predictorTerms != null && !this.predictorTerms.isEmpty();
   }

   public List getPredictorTerms() {
      if (this.predictorTerms == null) {
         this.predictorTerms = new ArrayList();
      }

      return this.predictorTerms;
   }

   public RegressionTable addPredictorTerms(PredictorTerm... predictorTerms) {
      this.getPredictorTerms().addAll(Arrays.asList(predictorTerms));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasNumericPredictors()) {
            status = PMMLObject.traverse(visitor, this.getNumericPredictors());
         }

         if (status == VisitorAction.CONTINUE && this.hasCategoricalPredictors()) {
            status = PMMLObject.traverse(visitor, this.getCategoricalPredictors());
         }

         if (status == VisitorAction.CONTINUE && this.hasPredictorTerms()) {
            status = PMMLObject.traverse(visitor, this.getPredictorTerms());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
