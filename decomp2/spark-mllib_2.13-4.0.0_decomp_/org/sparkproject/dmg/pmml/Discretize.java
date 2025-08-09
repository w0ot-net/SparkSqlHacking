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
import org.sparkproject.dmg.pmml.adapters.FieldNameAdapter;
import org.sparkproject.dmg.pmml.adapters.ObjectAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.AlternateValueConstructor;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Optional;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Discretize",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "discretizeBins"}
)
@JsonRootName("Discretize")
@JsonPropertyOrder({"field", "mapMissingTo", "defaultValue", "dataType", "extensions", "discretizeBins"})
public class Discretize extends Expression implements HasDataType, HasDefaultValue, HasExtensions, HasFieldReference, HasMapMissingTo {
   @XmlAttribute(
      name = "field",
      required = true
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("field")
   private String field;
   @XmlAttribute(
      name = "mapMissingTo"
   )
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @JsonProperty("mapMissingTo")
   private Object mapMissingTo;
   @XmlAttribute(
      name = "defaultValue"
   )
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @JsonProperty("defaultValue")
   private Object defaultValue;
   @XmlAttribute(
      name = "dataType"
   )
   @JsonProperty("dataType")
   @Added(Version.PMML_3_1)
   private DataType dataType;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "DiscretizeBin",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("DiscretizeBin")
   @Optional(Version.PMML_3_2)
   @CollectionElementType(DiscretizeBin.class)
   private List discretizeBins;
   private static final long serialVersionUID = 67371272L;

   public Discretize() {
   }

   @ValueConstructor
   public Discretize(@Property("field") String field) {
      this.field = field;
   }

   @AlternateValueConstructor
   public Discretize(Field field) {
      this(field != null ? field.requireName() : null);
   }

   public String requireField() {
      if (this.field == null) {
         throw new MissingAttributeException(this, PMMLAttributes.DISCRETIZE_FIELD);
      } else {
         return this.field;
      }
   }

   public String getField() {
      return this.field;
   }

   public Discretize setField(@Property("field") String field) {
      this.field = field;
      return this;
   }

   public Object getMapMissingTo() {
      return this.mapMissingTo;
   }

   public Discretize setMapMissingTo(@Property("mapMissingTo") Object mapMissingTo) {
      this.mapMissingTo = mapMissingTo;
      return this;
   }

   public Object getDefaultValue() {
      return this.defaultValue;
   }

   public Discretize setDefaultValue(@Property("defaultValue") Object defaultValue) {
      this.defaultValue = defaultValue;
      return this;
   }

   public DataType getDataType() {
      return this.dataType;
   }

   public Discretize setDataType(@Property("dataType") DataType dataType) {
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

   public Discretize addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public boolean hasDiscretizeBins() {
      return this.discretizeBins != null && !this.discretizeBins.isEmpty();
   }

   public List getDiscretizeBins() {
      if (this.discretizeBins == null) {
         this.discretizeBins = new ArrayList();
      }

      return this.discretizeBins;
   }

   public Discretize addDiscretizeBins(DiscretizeBin... discretizeBins) {
      this.getDiscretizeBins().addAll(Arrays.asList(discretizeBins));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasDiscretizeBins()) {
            status = PMMLObject.traverse(visitor, this.getDiscretizeBins());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
