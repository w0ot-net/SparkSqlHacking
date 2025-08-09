package org.sparkproject.dmg.pmml;

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
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.adapters.FieldNameAdapter;
import org.sparkproject.dmg.pmml.adapters.IntegerAdapter;
import org.sparkproject.dmg.pmml.adapters.ObjectAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Deprecated;
import org.sparkproject.jpmml.model.annotations.Optional;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.Required;
import org.sparkproject.jpmml.model.annotations.Since;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "OutputField",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "decisions", "expression", "values"}
)
@JsonRootName("OutputField")
@JsonPropertyOrder({"name", "displayName", "opType", "dataType", "targetField", "reportField", "resultFeature", "value", "ruleFeature", "algorithm", "rank", "rankBasis", "rankOrder", "multiValued", "segmentId", "finalResult", "extensions", "decisions", "expression", "values"})
public class OutputField extends Field implements HasDiscreteDomain, HasExpression, HasExtensions, HasTargetFieldReference {
   @XmlAttribute(
      name = "name",
      required = true
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("name")
   private String name;
   @XmlAttribute(
      name = "displayName"
   )
   @JsonProperty("displayName")
   private String displayName;
   @XmlAttribute(
      name = "optype"
   )
   @JsonProperty("optype")
   private OpType opType;
   @XmlAttribute(
      name = "dataType",
      required = true
   )
   @JsonProperty("dataType")
   @Required(Version.PMML_4_3)
   private DataType dataType;
   @XmlAttribute(
      name = "targetField"
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("targetField")
   @Optional(Version.PMML_3_1)
   private String targetField;
   @XmlAttribute(
      name = "x-reportField"
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("x-reportField")
   @Added(Version.XPMML)
   @Since("1.3.8")
   private String reportField;
   @XmlAttribute(
      name = "feature"
   )
   @JsonProperty("feature")
   private ResultFeature resultFeature;
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
      name = "ruleFeature"
   )
   @JsonProperty("ruleFeature")
   @Added(Version.PMML_4_0)
   @Deprecated(Version.PMML_4_2)
   private RuleFeature ruleFeature;
   @XmlAttribute(
      name = "algorithm"
   )
   @JsonProperty("algorithm")
   @Added(Version.PMML_4_0)
   private Algorithm algorithm;
   @XmlAttribute(
      name = "rank"
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("rank")
   private Integer rank;
   @XmlAttribute(
      name = "rankBasis"
   )
   @JsonProperty("rankBasis")
   @Added(Version.PMML_4_0)
   private RankBasis rankBasis;
   @XmlAttribute(
      name = "rankOrder"
   )
   @JsonProperty("rankOrder")
   @Added(Version.PMML_4_0)
   private RankOrder rankOrder;
   @XmlAttribute(
      name = "isMultiValued"
   )
   @JsonProperty("isMultiValued")
   @Added(Version.PMML_4_0)
   @Deprecated(Version.PMML_4_2)
   private MultiValued multiValued;
   @XmlAttribute(
      name = "segmentId"
   )
   @JsonProperty("segmentId")
   @Added(Version.PMML_4_1)
   private String segmentId;
   @XmlAttribute(
      name = "isFinalResult"
   )
   @JsonProperty("isFinalResult")
   @Added(
      value = Version.PMML_4_3,
      removable = true
   )
   private Boolean finalResult;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "Decisions",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Decisions")
   @Added(Version.PMML_4_1)
   private Decisions decisions;
   @XmlElements({@XmlElement(
   name = "Constant",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = Constant.class
), @XmlElement(
   name = "FieldRef",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = FieldRef.class
), @XmlElement(
   name = "NormContinuous",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = NormContinuous.class
), @XmlElement(
   name = "NormDiscrete",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = NormDiscrete.class
), @XmlElement(
   name = "Discretize",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = Discretize.class
), @XmlElement(
   name = "MapValues",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = MapValues.class
), @XmlElement(
   name = "TextIndex",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = TextIndex.class
), @XmlElement(
   name = "Apply",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = Apply.class
), @XmlElement(
   name = "Aggregate",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = Aggregate.class
), @XmlElement(
   name = "Lag",
   namespace = "http://www.dmg.org/PMML-4_4",
   type = Lag.class
)})
   @JsonProperty("Expression")
   @JsonTypeInfo(
      include = As.WRAPPER_OBJECT,
      use = Id.NAME
   )
   @JsonSubTypes({@Type(
   name = "Constant",
   value = Constant.class
), @Type(
   name = "FieldRef",
   value = FieldRef.class
), @Type(
   name = "NormContinuous",
   value = NormContinuous.class
), @Type(
   name = "NormDiscrete",
   value = NormDiscrete.class
), @Type(
   name = "Discretize",
   value = Discretize.class
), @Type(
   name = "MapValues",
   value = MapValues.class
), @Type(
   name = "TextIndex",
   value = TextIndex.class
), @Type(
   name = "Apply",
   value = Apply.class
), @Type(
   name = "Aggregate",
   value = Aggregate.class
), @Type(
   name = "Lag",
   value = Lag.class
)})
   @Added(Version.PMML_4_1)
   private Expression expression;
   @XmlElement(
      name = "Value",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Value")
   @Added(
      value = Version.PMML_4_4,
      removable = true
   )
   @CollectionElementType(Value.class)
   private List values;
   private static final Integer DEFAULT_RANK = (new IntegerAdapter()).unmarshal("1");
   private static final Boolean DEFAULT_FINAL_RESULT = true;
   private static final long serialVersionUID = 67371272L;

   public OutputField() {
   }

   @ValueConstructor
   public OutputField(@Property("name") String name, @Property("opType") OpType opType, @Property("dataType") DataType dataType) {
      this.name = name;
      this.opType = opType;
      this.dataType = dataType;
   }

   public String requireName() {
      if (this.name == null) {
         throw new MissingAttributeException(this, PMMLAttributes.OUTPUTFIELD_NAME);
      } else {
         return this.name;
      }
   }

   public String getName() {
      return this.name;
   }

   public OutputField setName(@Property("name") String name) {
      this.name = name;
      return this;
   }

   public String getDisplayName() {
      return this.displayName;
   }

   public OutputField setDisplayName(@Property("displayName") String displayName) {
      this.displayName = displayName;
      return this;
   }

   public OpType requireOpType() {
      if (this.opType == null) {
         throw new MissingAttributeException(this, PMMLAttributes.OUTPUTFIELD_OPTYPE);
      } else {
         return this.opType;
      }
   }

   public OpType getOpType() {
      return this.opType;
   }

   public OutputField setOpType(@Property("opType") OpType opType) {
      this.opType = opType;
      return this;
   }

   public DataType requireDataType() {
      if (this.dataType == null) {
         throw new MissingAttributeException(this, PMMLAttributes.OUTPUTFIELD_DATATYPE);
      } else {
         return this.dataType;
      }
   }

   public DataType getDataType() {
      return this.dataType;
   }

   public OutputField setDataType(@Property("dataType") DataType dataType) {
      this.dataType = dataType;
      return this;
   }

   public String getTargetField() {
      return this.targetField;
   }

   public OutputField setTargetField(@Property("targetField") String targetField) {
      this.targetField = targetField;
      return this;
   }

   public String getReportField() {
      return this.reportField;
   }

   public OutputField setReportField(@Property("reportField") String reportField) {
      this.reportField = reportField;
      return this;
   }

   public ResultFeature getResultFeature() {
      return this.resultFeature == null ? ResultFeature.PREDICTED_VALUE : this.resultFeature;
   }

   public OutputField setResultFeature(@Property("resultFeature") ResultFeature resultFeature) {
      this.resultFeature = resultFeature;
      return this;
   }

   public Object requireValue() {
      if (this.value == null) {
         throw new MissingAttributeException(this, PMMLAttributes.OUTPUTFIELD_VALUE);
      } else {
         return this.value;
      }
   }

   public Object getValue() {
      return this.value;
   }

   public OutputField setValue(@Property("value") Object value) {
      this.value = value;
      return this;
   }

   public RuleFeature getRuleFeature() {
      return this.ruleFeature == null ? OutputField.RuleFeature.CONSEQUENT : this.ruleFeature;
   }

   public OutputField setRuleFeature(@Property("ruleFeature") RuleFeature ruleFeature) {
      this.ruleFeature = ruleFeature;
      return this;
   }

   public Algorithm getAlgorithm() {
      return this.algorithm == null ? OutputField.Algorithm.EXCLUSIVE_RECOMMENDATION : this.algorithm;
   }

   public OutputField setAlgorithm(@Property("algorithm") Algorithm algorithm) {
      this.algorithm = algorithm;
      return this;
   }

   public Integer getRank() {
      return this.rank == null ? DEFAULT_RANK : this.rank;
   }

   public OutputField setRank(@Property("rank") Integer rank) {
      this.rank = rank;
      return this;
   }

   public RankBasis getRankBasis() {
      return this.rankBasis == null ? OutputField.RankBasis.CONFIDENCE : this.rankBasis;
   }

   public OutputField setRankBasis(@Property("rankBasis") RankBasis rankBasis) {
      this.rankBasis = rankBasis;
      return this;
   }

   public RankOrder getRankOrder() {
      return this.rankOrder == null ? OutputField.RankOrder.DESCENDING : this.rankOrder;
   }

   public OutputField setRankOrder(@Property("rankOrder") RankOrder rankOrder) {
      this.rankOrder = rankOrder;
      return this;
   }

   public MultiValued getMultiValued() {
      return this.multiValued == null ? OutputField.MultiValued.ZERO : this.multiValued;
   }

   public OutputField setMultiValued(@Property("multiValued") MultiValued multiValued) {
      this.multiValued = multiValued;
      return this;
   }

   public String getSegmentId() {
      return this.segmentId;
   }

   public OutputField setSegmentId(@Property("segmentId") String segmentId) {
      this.segmentId = segmentId;
      return this;
   }

   public boolean isFinalResult() {
      return this.finalResult == null ? DEFAULT_FINAL_RESULT : this.finalResult;
   }

   public OutputField setFinalResult(@Property("finalResult") Boolean finalResult) {
      this.finalResult = finalResult;
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

   public OutputField addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Decisions getDecisions() {
      return this.decisions;
   }

   public OutputField setDecisions(@Property("decisions") Decisions decisions) {
      this.decisions = decisions;
      return this;
   }

   public Expression requireExpression() {
      if (this.expression == null) {
         throw new MissingElementException(this, PMMLElements.OUTPUTFIELD_EXPRESSION);
      } else {
         return this.expression;
      }
   }

   public Expression getExpression() {
      return this.expression;
   }

   public OutputField setExpression(@Property("expression") Expression expression) {
      this.expression = expression;
      return this;
   }

   public boolean hasValues() {
      return this.values != null && !this.values.isEmpty();
   }

   public List getValues() {
      if (this.values == null) {
         this.values = new ArrayList();
      }

      return this.values;
   }

   public OutputField addValues(Value... values) {
      this.getValues().addAll(Arrays.asList(values));
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
            status = PMMLObject.traverse(visitor, this.getDecisions(), this.getExpression());
         }

         if (status == VisitorAction.CONTINUE && this.hasValues()) {
            status = PMMLObject.traverse(visitor, this.getValues());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum Algorithm implements StringValue {
      @XmlEnumValue("recommendation")
      @JsonProperty("recommendation")
      RECOMMENDATION("recommendation"),
      @XmlEnumValue("exclusiveRecommendation")
      @JsonProperty("exclusiveRecommendation")
      EXCLUSIVE_RECOMMENDATION("exclusiveRecommendation"),
      @XmlEnumValue("ruleAssociation")
      @JsonProperty("ruleAssociation")
      RULE_ASSOCIATION("ruleAssociation");

      private final String value;

      private Algorithm(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static Algorithm fromValue(String v) {
         for(Algorithm c : values()) {
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

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum MultiValued implements StringValue {
      @XmlEnumValue("0")
      @JsonProperty("0")
      ZERO("0"),
      @XmlEnumValue("1")
      @JsonProperty("1")
      ONE("1");

      private final String value;

      private MultiValued(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static MultiValued fromValue(String v) {
         for(MultiValued c : values()) {
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

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum RankBasis implements StringValue {
      @XmlEnumValue("confidence")
      @JsonProperty("confidence")
      CONFIDENCE("confidence"),
      @XmlEnumValue("support")
      @JsonProperty("support")
      SUPPORT("support"),
      @XmlEnumValue("lift")
      @JsonProperty("lift")
      LIFT("lift"),
      @XmlEnumValue("leverage")
      @JsonProperty("leverage")
      @Added(Version.PMML_4_1)
      LEVERAGE("leverage"),
      @XmlEnumValue("affinity")
      @JsonProperty("affinity")
      @Added(Version.PMML_4_1)
      AFFINITY("affinity");

      private final String value;

      private RankBasis(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static RankBasis fromValue(String v) {
         for(RankBasis c : values()) {
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

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum RankOrder implements StringValue {
      @XmlEnumValue("descending")
      @JsonProperty("descending")
      DESCENDING("descending"),
      @XmlEnumValue("ascending")
      @JsonProperty("ascending")
      ASCENDING("ascending");

      private final String value;

      private RankOrder(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static RankOrder fromValue(String v) {
         for(RankOrder c : values()) {
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

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum RuleFeature implements StringValue {
      @XmlEnumValue("antecedent")
      @JsonProperty("antecedent")
      ANTECEDENT("antecedent"),
      @XmlEnumValue("consequent")
      @JsonProperty("consequent")
      CONSEQUENT("consequent"),
      @XmlEnumValue("rule")
      @JsonProperty("rule")
      RULE("rule"),
      @XmlEnumValue("ruleId")
      @JsonProperty("ruleId")
      RULE_ID("ruleId"),
      @XmlEnumValue("confidence")
      @JsonProperty("confidence")
      CONFIDENCE("confidence"),
      @XmlEnumValue("support")
      @JsonProperty("support")
      SUPPORT("support"),
      @XmlEnumValue("lift")
      @JsonProperty("lift")
      LIFT("lift"),
      @XmlEnumValue("leverage")
      @JsonProperty("leverage")
      @Added(Version.PMML_4_1)
      LEVERAGE("leverage"),
      @XmlEnumValue("affinity")
      @JsonProperty("affinity")
      @Added(Version.PMML_4_1)
      AFFINITY("affinity");

      private final String value;

      private RuleFeature(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static RuleFeature fromValue(String v) {
         for(RuleFeature c : values()) {
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
