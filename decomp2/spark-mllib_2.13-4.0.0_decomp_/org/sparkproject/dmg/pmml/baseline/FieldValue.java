package org.sparkproject.dmg.pmml.baseline;

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
import org.sparkproject.dmg.pmml.Field;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.HasFieldReference;
import org.sparkproject.dmg.pmml.HasValue;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.FieldNameAdapter;
import org.sparkproject.dmg.pmml.adapters.ObjectAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.AlternateValueConstructor;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "FieldValue",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "fieldValues", "fieldValueCounts"}
)
@JsonRootName("FieldValue")
@JsonPropertyOrder({"field", "value", "extensions", "fieldValues", "fieldValueCounts"})
@Added(Version.PMML_4_1)
public class FieldValue extends PMMLObject implements HasExtensions, HasFieldReference, HasValue {
   @XmlAttribute(
      name = "field",
      required = true
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("field")
   private String field;
   @XmlAttribute(
      name = "value",
      required = true
   )
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @JsonProperty("value")
   private Object value;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "FieldValue",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("FieldValue")
   @CollectionElementType(FieldValue.class)
   private List fieldValues;
   @XmlElement(
      name = "FieldValueCount",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("FieldValueCount")
   @CollectionElementType(FieldValueCount.class)
   private List fieldValueCounts;
   private static final long serialVersionUID = 67371272L;

   public FieldValue() {
   }

   @ValueConstructor
   public FieldValue(@Property("field") String field, @Property("value") Object value, @Property("fieldValues") List fieldValues, @Property("fieldValueCounts") List fieldValueCounts) {
      this.field = field;
      this.value = value;
      this.fieldValues = fieldValues;
      this.fieldValueCounts = fieldValueCounts;
   }

   @AlternateValueConstructor
   public FieldValue(Field field, Object value, List fieldValues, List fieldValueCounts) {
      this(field != null ? field.requireName() : null, value, fieldValues, fieldValueCounts);
   }

   public String requireField() {
      if (this.field == null) {
         throw new MissingAttributeException(this, PMMLAttributes.FIELDVALUE_FIELD);
      } else {
         return this.field;
      }
   }

   public String getField() {
      return this.field;
   }

   public FieldValue setField(@Property("field") String field) {
      this.field = field;
      return this;
   }

   public Object requireValue() {
      if (this.value == null) {
         throw new MissingAttributeException(this, PMMLAttributes.FIELDVALUE_VALUE);
      } else {
         return this.value;
      }
   }

   public Object getValue() {
      return this.value;
   }

   public FieldValue setValue(@Property("value") Object value) {
      this.value = value;
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

   public FieldValue addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public boolean hasFieldValues() {
      return this.fieldValues != null && !this.fieldValues.isEmpty();
   }

   public List requireFieldValues() {
      if (this.fieldValues != null && !this.fieldValues.isEmpty()) {
         return this.fieldValues;
      } else {
         throw new MissingElementException(this, PMMLElements.FIELDVALUE_FIELDVALUES);
      }
   }

   public List getFieldValues() {
      if (this.fieldValues == null) {
         this.fieldValues = new ArrayList();
      }

      return this.fieldValues;
   }

   public FieldValue addFieldValues(FieldValue... fieldValues) {
      this.getFieldValues().addAll(Arrays.asList(fieldValues));
      return this;
   }

   public boolean hasFieldValueCounts() {
      return this.fieldValueCounts != null && !this.fieldValueCounts.isEmpty();
   }

   public List requireFieldValueCounts() {
      if (this.fieldValueCounts != null && !this.fieldValueCounts.isEmpty()) {
         return this.fieldValueCounts;
      } else {
         throw new MissingElementException(this, PMMLElements.FIELDVALUE_FIELDVALUECOUNTS);
      }
   }

   public List getFieldValueCounts() {
      if (this.fieldValueCounts == null) {
         this.fieldValueCounts = new ArrayList();
      }

      return this.fieldValueCounts;
   }

   public FieldValue addFieldValueCounts(FieldValueCount... fieldValueCounts) {
      this.getFieldValueCounts().addAll(Arrays.asList(fieldValueCounts));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasFieldValues()) {
            status = PMMLObject.traverse(visitor, this.getFieldValues());
         }

         if (status == VisitorAction.CONTINUE && this.hasFieldValueCounts()) {
            status = PMMLObject.traverse(visitor, this.getFieldValueCounts());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
