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
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.adapters.FieldNameAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.Removed;
import org.sparkproject.jpmml.model.annotations.Required;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "DerivedField",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "expression", "intervals", "values"}
)
@JsonRootName("DerivedField")
@JsonPropertyOrder({"name", "displayName", "opType", "dataType", "extensions", "expression", "intervals", "values"})
public class DerivedField extends Field implements HasContinuousDomain, HasDiscreteDomain, HasExpression, HasExtensions {
   @XmlAttribute(
      name = "name"
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
      name = "optype",
      required = true
   )
   @JsonProperty("optype")
   @Required(Version.PMML_3_1)
   private OpType opType;
   @XmlAttribute(
      name = "dataType",
      required = true
   )
   @JsonProperty("dataType")
   private DataType dataType;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
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
   private Expression expression;
   @XmlElement(
      name = "Interval",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Interval")
   @Removed(Version.PMML_3_1)
   @CollectionElementType(Interval.class)
   private List intervals;
   @XmlElement(
      name = "Value",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Value")
   @CollectionElementType(Value.class)
   private List values;
   private static final long serialVersionUID = 67371272L;

   public DerivedField() {
   }

   @ValueConstructor
   public DerivedField(@Property("name") String name, @Property("opType") OpType opType, @Property("dataType") DataType dataType, @Property("expression") Expression expression) {
      this.name = name;
      this.opType = opType;
      this.dataType = dataType;
      this.expression = expression;
   }

   public String requireName() {
      if (this.name == null) {
         throw new MissingAttributeException(this, PMMLAttributes.DERIVEDFIELD_NAME);
      } else {
         return this.name;
      }
   }

   public String getName() {
      return this.name;
   }

   public DerivedField setName(@Property("name") String name) {
      this.name = name;
      return this;
   }

   public String getDisplayName() {
      return this.displayName;
   }

   public DerivedField setDisplayName(@Property("displayName") String displayName) {
      this.displayName = displayName;
      return this;
   }

   public OpType requireOpType() {
      if (this.opType == null) {
         throw new MissingAttributeException(this, PMMLAttributes.DERIVEDFIELD_OPTYPE);
      } else {
         return this.opType;
      }
   }

   public OpType getOpType() {
      return this.opType;
   }

   public DerivedField setOpType(@Property("opType") OpType opType) {
      this.opType = opType;
      return this;
   }

   public DataType requireDataType() {
      if (this.dataType == null) {
         throw new MissingAttributeException(this, PMMLAttributes.DERIVEDFIELD_DATATYPE);
      } else {
         return this.dataType;
      }
   }

   public DataType getDataType() {
      return this.dataType;
   }

   public DerivedField setDataType(@Property("dataType") DataType dataType) {
      this.dataType = dataType;
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

   public DerivedField addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Expression requireExpression() {
      if (this.expression == null) {
         throw new MissingElementException(this, PMMLElements.DERIVEDFIELD_EXPRESSION);
      } else {
         return this.expression;
      }
   }

   public Expression getExpression() {
      return this.expression;
   }

   public DerivedField setExpression(@Property("expression") Expression expression) {
      this.expression = expression;
      return this;
   }

   public boolean hasIntervals() {
      return this.intervals != null && !this.intervals.isEmpty();
   }

   public List getIntervals() {
      if (this.intervals == null) {
         this.intervals = new ArrayList();
      }

      return this.intervals;
   }

   public DerivedField addIntervals(Interval... intervals) {
      this.getIntervals().addAll(Arrays.asList(intervals));
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

   public DerivedField addValues(Value... values) {
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
            status = PMMLObject.traverse(visitor, (Visitable)this.getExpression());
         }

         if (status == VisitorAction.CONTINUE && this.hasIntervals()) {
            status = PMMLObject.traverse(visitor, this.getIntervals());
         }

         if (status == VisitorAction.CONTINUE && this.hasValues()) {
            status = PMMLObject.traverse(visitor, this.getValues());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
