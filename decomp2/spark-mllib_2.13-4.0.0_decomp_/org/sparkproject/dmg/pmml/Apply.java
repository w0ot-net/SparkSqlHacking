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
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.adapters.ObjectAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.Required;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Apply",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "expressions"}
)
@JsonRootName("Apply")
@JsonPropertyOrder({"function", "mapMissingTo", "defaultValue", "invalidValueTreatment", "extensions", "expressions"})
public class Apply extends Expression implements HasDefaultValue, HasExtensions, HasMapMissingTo {
   @XmlAttribute(
      name = "function",
      required = true
   )
   @JsonProperty("function")
   @Required(Version.PMML_3_1)
   private String function;
   @XmlAttribute(
      name = "mapMissingTo"
   )
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @JsonProperty("mapMissingTo")
   @Added(Version.PMML_4_1)
   private Object mapMissingTo;
   @XmlAttribute(
      name = "defaultValue"
   )
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @JsonProperty("defaultValue")
   @Added(Version.PMML_4_2)
   private Object defaultValue;
   @XmlAttribute(
      name = "invalidValueTreatment"
   )
   @JsonProperty("invalidValueTreatment")
   @Added(Version.PMML_4_1)
   private InvalidValueTreatmentMethod invalidValueTreatment;
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
   @CollectionElementType(Expression.class)
   private List expressions;
   private static final long serialVersionUID = 67371272L;

   public Apply() {
   }

   @ValueConstructor
   public Apply(@Property("function") String function) {
      this.function = function;
   }

   public String requireFunction() {
      if (this.function == null) {
         throw new MissingAttributeException(this, PMMLAttributes.APPLY_FUNCTION);
      } else {
         return this.function;
      }
   }

   public String getFunction() {
      return this.function;
   }

   public Apply setFunction(@Property("function") String function) {
      this.function = function;
      return this;
   }

   public Object getMapMissingTo() {
      return this.mapMissingTo;
   }

   public Apply setMapMissingTo(@Property("mapMissingTo") Object mapMissingTo) {
      this.mapMissingTo = mapMissingTo;
      return this;
   }

   public Object getDefaultValue() {
      return this.defaultValue;
   }

   public Apply setDefaultValue(@Property("defaultValue") Object defaultValue) {
      this.defaultValue = defaultValue;
      return this;
   }

   public InvalidValueTreatmentMethod getInvalidValueTreatment() {
      return this.invalidValueTreatment == null ? InvalidValueTreatmentMethod.RETURN_INVALID : this.invalidValueTreatment;
   }

   public Apply setInvalidValueTreatment(@Property("invalidValueTreatment") InvalidValueTreatmentMethod invalidValueTreatment) {
      this.invalidValueTreatment = invalidValueTreatment;
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

   public Apply addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public boolean hasExpressions() {
      return this.expressions != null && !this.expressions.isEmpty();
   }

   public List getExpressions() {
      if (this.expressions == null) {
         this.expressions = new ArrayList();
      }

      return this.expressions;
   }

   public Apply addExpressions(Expression... expressions) {
      this.getExpressions().addAll(Arrays.asList(expressions));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasExpressions()) {
            status = PMMLObject.traverse(visitor, this.getExpressions());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
