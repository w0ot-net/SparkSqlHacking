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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "DefineFunction",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "parameterFields", "expression"}
)
@JsonRootName("DefineFunction")
@JsonPropertyOrder({"name", "opType", "dataType", "extensions", "parameterFields", "expression"})
public class DefineFunction extends PMMLObject implements HasExpression, HasExtensions, HasRequiredName, HasRequiredType {
   @XmlAttribute(
      name = "name",
      required = true
   )
   @JsonProperty("name")
   private String name;
   @XmlAttribute(
      name = "optype",
      required = true
   )
   @JsonProperty("optype")
   private OpType opType;
   @XmlAttribute(
      name = "dataType"
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
   @XmlElement(
      name = "ParameterField",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("ParameterField")
   @CollectionElementType(ParameterField.class)
   private List parameterFields;
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
   private static final long serialVersionUID = 67371272L;

   public DefineFunction() {
   }

   @ValueConstructor
   public DefineFunction(@Property("name") String name, @Property("opType") OpType opType, @Property("dataType") DataType dataType, @Property("parameterFields") List parameterFields, @Property("expression") Expression expression) {
      this.name = name;
      this.opType = opType;
      this.dataType = dataType;
      this.parameterFields = parameterFields;
      this.expression = expression;
   }

   public String requireName() {
      if (this.name == null) {
         throw new MissingAttributeException(this, PMMLAttributes.DEFINEFUNCTION_NAME);
      } else {
         return this.name;
      }
   }

   public String getName() {
      return this.name;
   }

   public DefineFunction setName(@Property("name") String name) {
      this.name = name;
      return this;
   }

   public OpType requireOpType() {
      if (this.opType == null) {
         throw new MissingAttributeException(this, PMMLAttributes.DEFINEFUNCTION_OPTYPE);
      } else {
         return this.opType;
      }
   }

   public OpType getOpType() {
      return this.opType;
   }

   public DefineFunction setOpType(@Property("opType") OpType opType) {
      this.opType = opType;
      return this;
   }

   public DataType requireDataType() {
      if (this.dataType == null) {
         throw new MissingAttributeException(this, PMMLAttributes.DEFINEFUNCTION_DATATYPE);
      } else {
         return this.dataType;
      }
   }

   public DataType getDataType() {
      return this.dataType;
   }

   public DefineFunction setDataType(@Property("dataType") DataType dataType) {
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

   public DefineFunction addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public boolean hasParameterFields() {
      return this.parameterFields != null && !this.parameterFields.isEmpty();
   }

   public List requireParameterFields() {
      if (this.parameterFields != null && !this.parameterFields.isEmpty()) {
         return this.parameterFields;
      } else {
         throw new MissingElementException(this, PMMLElements.DEFINEFUNCTION_PARAMETERFIELDS);
      }
   }

   public List getParameterFields() {
      if (this.parameterFields == null) {
         this.parameterFields = new ArrayList();
      }

      return this.parameterFields;
   }

   public DefineFunction addParameterFields(ParameterField... parameterFields) {
      this.getParameterFields().addAll(Arrays.asList(parameterFields));
      return this;
   }

   public Expression requireExpression() {
      if (this.expression == null) {
         throw new MissingElementException(this, PMMLElements.DEFINEFUNCTION_EXPRESSION);
      } else {
         return this.expression;
      }
   }

   public Expression getExpression() {
      return this.expression;
   }

   public DefineFunction setExpression(@Property("expression") Expression expression) {
      this.expression = expression;
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasParameterFields()) {
            status = PMMLObject.traverse(visitor, this.getParameterFields());
         }

         if (status == VisitorAction.CONTINUE) {
            status = PMMLObject.traverse(visitor, (Visitable)this.getExpression());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
