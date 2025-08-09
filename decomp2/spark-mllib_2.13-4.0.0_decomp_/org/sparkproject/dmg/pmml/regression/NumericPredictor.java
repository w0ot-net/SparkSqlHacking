package org.sparkproject.dmg.pmml.regression;

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
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.Field;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.HasFieldReference;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.FieldNameAdapter;
import org.sparkproject.dmg.pmml.adapters.IntegerAdapter;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.AlternateValueConstructor;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "NumericPredictor",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("NumericPredictor")
@JsonPropertyOrder({"field", "exponent", "coefficient", "extensions"})
public class NumericPredictor extends Term implements HasExtensions, HasFieldReference {
   @XmlAttribute(
      name = "name",
      required = true
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("name")
   private String field;
   @XmlAttribute(
      name = "exponent"
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("exponent")
   private Integer exponent;
   @XmlAttribute(
      name = "coefficient",
      required = true
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("coefficient")
   private Number coefficient;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final Integer DEFAULT_EXPONENT = (new IntegerAdapter()).unmarshal("1");
   private static final long serialVersionUID = 67371272L;

   public NumericPredictor() {
   }

   @ValueConstructor
   public NumericPredictor(@Property("field") String field, @Property("coefficient") Number coefficient) {
      this.field = field;
      this.coefficient = coefficient;
   }

   @AlternateValueConstructor
   public NumericPredictor(Field field, Number coefficient) {
      this(field != null ? field.requireName() : null, coefficient);
   }

   public String requireField() {
      if (this.field == null) {
         throw new MissingAttributeException(this, PMMLAttributes.NUMERICPREDICTOR_FIELD);
      } else {
         return this.field;
      }
   }

   public String getField() {
      return this.field;
   }

   public NumericPredictor setField(@Property("field") String field) {
      this.field = field;
      return this;
   }

   public Integer getExponent() {
      return this.exponent == null ? DEFAULT_EXPONENT : this.exponent;
   }

   public NumericPredictor setExponent(@Property("exponent") Integer exponent) {
      this.exponent = exponent;
      return this;
   }

   public Number requireCoefficient() {
      if (this.coefficient == null) {
         throw new MissingAttributeException(this, PMMLAttributes.NUMERICPREDICTOR_COEFFICIENT);
      } else {
         return this.coefficient;
      }
   }

   public Number getCoefficient() {
      return this.coefficient;
   }

   public NumericPredictor setCoefficient(@Property("coefficient") Number coefficient) {
      this.coefficient = coefficient;
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

   public NumericPredictor addExtensions(Extension... extensions) {
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
