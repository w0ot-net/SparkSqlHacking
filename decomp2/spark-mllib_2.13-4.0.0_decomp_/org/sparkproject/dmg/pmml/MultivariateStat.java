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
import org.sparkproject.dmg.pmml.adapters.IntegerAdapter;
import org.sparkproject.dmg.pmml.adapters.NumberAdapter;
import org.sparkproject.dmg.pmml.adapters.ObjectAdapter;
import org.sparkproject.dmg.pmml.adapters.ProbabilityNumberAdapter;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;

@XmlRootElement(
   name = "MultivariateStat",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("MultivariateStat")
@JsonPropertyOrder({"name", "category", "exponent", "intercept", "importance", "stdError", "tValue", "chiSquareValue", "fStatistic", "df", "pValueAlpha", "pValueInitial", "pValueFinal", "confidenceLevel", "confidenceLowerBound", "confidenceUpperBound", "extensions"})
@Added(Version.PMML_4_1)
public class MultivariateStat extends PMMLObject implements HasExtensions, HasName {
   @XmlAttribute(
      name = "name"
   )
   @JsonProperty("name")
   private String name;
   @XmlAttribute(
      name = "category"
   )
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @JsonProperty("category")
   private Object category;
   @XmlAttribute(
      name = "exponent"
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("exponent")
   private Integer exponent;
   @XmlAttribute(
      name = "isIntercept"
   )
   @JsonProperty("isIntercept")
   private Boolean intercept;
   @XmlAttribute(
      name = "importance"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("importance")
   private Number importance;
   @XmlAttribute(
      name = "stdError"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("stdError")
   private Number stdError;
   @XmlAttribute(
      name = "tValue"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("tValue")
   private Number tValue;
   @XmlAttribute(
      name = "chiSquareValue"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("chiSquareValue")
   private Number chiSquareValue;
   @XmlAttribute(
      name = "fStatistic"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("fStatistic")
   private Number fStatistic;
   @XmlAttribute(
      name = "dF"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("dF")
   private Number df;
   @XmlAttribute(
      name = "pValueAlpha"
   )
   @XmlJavaTypeAdapter(ProbabilityNumberAdapter.class)
   @JsonProperty("pValueAlpha")
   private Number pValueAlpha;
   @XmlAttribute(
      name = "pValueInitial"
   )
   @XmlJavaTypeAdapter(ProbabilityNumberAdapter.class)
   @JsonProperty("pValueInitial")
   private Number pValueInitial;
   @XmlAttribute(
      name = "pValueFinal"
   )
   @XmlJavaTypeAdapter(ProbabilityNumberAdapter.class)
   @JsonProperty("pValueFinal")
   private Number pValueFinal;
   @XmlAttribute(
      name = "confidenceLevel"
   )
   @XmlJavaTypeAdapter(ProbabilityNumberAdapter.class)
   @JsonProperty("confidenceLevel")
   private Number confidenceLevel;
   @XmlAttribute(
      name = "confidenceLowerBound"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("confidenceLowerBound")
   private Number confidenceLowerBound;
   @XmlAttribute(
      name = "confidenceUpperBound"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("confidenceUpperBound")
   private Number confidenceUpperBound;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final Integer DEFAULT_EXPONENT = (new IntegerAdapter()).unmarshal("1");
   private static final Boolean DEFAULT_INTERCEPT = false;
   private static final Number DEFAULT_CONFIDENCE_LEVEL = (new ProbabilityNumberAdapter()).unmarshal("0.95");
   private static final long serialVersionUID = 67371272L;

   public String getName() {
      return this.name;
   }

   public MultivariateStat setName(@Property("name") String name) {
      this.name = name;
      return this;
   }

   public Object getCategory() {
      return this.category;
   }

   public MultivariateStat setCategory(@Property("category") Object category) {
      this.category = category;
      return this;
   }

   public Integer getExponent() {
      return this.exponent == null ? DEFAULT_EXPONENT : this.exponent;
   }

   public MultivariateStat setExponent(@Property("exponent") Integer exponent) {
      this.exponent = exponent;
      return this;
   }

   public boolean isIntercept() {
      return this.intercept == null ? DEFAULT_INTERCEPT : this.intercept;
   }

   public MultivariateStat setIntercept(@Property("intercept") Boolean intercept) {
      this.intercept = intercept;
      return this;
   }

   public Number getImportance() {
      return this.importance;
   }

   public MultivariateStat setImportance(@Property("importance") Number importance) {
      this.importance = importance;
      return this;
   }

   public Number getStdError() {
      return this.stdError;
   }

   public MultivariateStat setStdError(@Property("stdError") Number stdError) {
      this.stdError = stdError;
      return this;
   }

   public Number getTValue() {
      return this.tValue;
   }

   public MultivariateStat setTValue(@Property("tValue") Number tValue) {
      this.tValue = tValue;
      return this;
   }

   public Number getChiSquareValue() {
      return this.chiSquareValue;
   }

   public MultivariateStat setChiSquareValue(@Property("chiSquareValue") Number chiSquareValue) {
      this.chiSquareValue = chiSquareValue;
      return this;
   }

   public Number getFStatistic() {
      return this.fStatistic;
   }

   public MultivariateStat setFStatistic(@Property("fStatistic") Number fStatistic) {
      this.fStatistic = fStatistic;
      return this;
   }

   public Number getDF() {
      return this.df;
   }

   public MultivariateStat setDF(@Property("df") Number df) {
      this.df = df;
      return this;
   }

   public Number getPValueAlpha() {
      return this.pValueAlpha;
   }

   public MultivariateStat setPValueAlpha(@Property("pValueAlpha") Number pValueAlpha) {
      this.pValueAlpha = pValueAlpha;
      return this;
   }

   public Number getPValueInitial() {
      return this.pValueInitial;
   }

   public MultivariateStat setPValueInitial(@Property("pValueInitial") Number pValueInitial) {
      this.pValueInitial = pValueInitial;
      return this;
   }

   public Number getPValueFinal() {
      return this.pValueFinal;
   }

   public MultivariateStat setPValueFinal(@Property("pValueFinal") Number pValueFinal) {
      this.pValueFinal = pValueFinal;
      return this;
   }

   public Number getConfidenceLevel() {
      return this.confidenceLevel == null ? DEFAULT_CONFIDENCE_LEVEL : this.confidenceLevel;
   }

   public MultivariateStat setConfidenceLevel(@Property("confidenceLevel") Number confidenceLevel) {
      this.confidenceLevel = confidenceLevel;
      return this;
   }

   public Number getConfidenceLowerBound() {
      return this.confidenceLowerBound;
   }

   public MultivariateStat setConfidenceLowerBound(@Property("confidenceLowerBound") Number confidenceLowerBound) {
      this.confidenceLowerBound = confidenceLowerBound;
      return this;
   }

   public Number getConfidenceUpperBound() {
      return this.confidenceUpperBound;
   }

   public MultivariateStat setConfidenceUpperBound(@Property("confidenceUpperBound") Number confidenceUpperBound) {
      this.confidenceUpperBound = confidenceUpperBound;
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

   public MultivariateStat addExtensions(Extension... extensions) {
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
