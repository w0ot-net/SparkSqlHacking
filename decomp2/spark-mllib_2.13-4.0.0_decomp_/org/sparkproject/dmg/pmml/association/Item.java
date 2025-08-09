package org.sparkproject.dmg.pmml.association;

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
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.HasFieldReference;
import org.sparkproject.dmg.pmml.HasRequiredId;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.FieldNameAdapter;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Item",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("Item")
@JsonPropertyOrder({"id", "value", "field", "category", "mappedValue", "weight", "extensions"})
public class Item extends PMMLObject implements HasExtensions, HasFieldReference, HasRequiredId {
   @XmlAttribute(
      name = "id",
      required = true
   )
   @JsonProperty("id")
   private String id;
   @XmlAttribute(
      name = "value",
      required = true
   )
   @JsonProperty("value")
   private String value;
   @XmlAttribute(
      name = "field"
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("field")
   @Added(Version.PMML_4_3)
   private String field;
   @XmlAttribute(
      name = "category"
   )
   @JsonProperty("category")
   @Added(Version.PMML_4_3)
   private String category;
   @XmlAttribute(
      name = "mappedValue"
   )
   @JsonProperty("mappedValue")
   private String mappedValue;
   @XmlAttribute(
      name = "weight"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("weight")
   private Number weight;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final long serialVersionUID = 67371272L;

   public Item() {
   }

   @ValueConstructor
   public Item(@Property("id") String id, @Property("value") String value) {
      this.id = id;
      this.value = value;
   }

   public String requireId() {
      if (this.id == null) {
         throw new MissingAttributeException(this, PMMLAttributes.ITEM_ID);
      } else {
         return this.id;
      }
   }

   public String getId() {
      return this.id;
   }

   public Item setId(@Property("id") String id) {
      this.id = id;
      return this;
   }

   public String requireValue() {
      if (this.value == null) {
         throw new MissingAttributeException(this, PMMLAttributes.ITEM_VALUE);
      } else {
         return this.value;
      }
   }

   public String getValue() {
      return this.value;
   }

   public Item setValue(@Property("value") String value) {
      this.value = value;
      return this;
   }

   public String getField() {
      return this.field;
   }

   public Item setField(@Property("field") String field) {
      this.field = field;
      return this;
   }

   public String getCategory() {
      return this.category;
   }

   public Item setCategory(@Property("category") String category) {
      this.category = category;
      return this;
   }

   public String getMappedValue() {
      return this.mappedValue;
   }

   public Item setMappedValue(@Property("mappedValue") String mappedValue) {
      this.mappedValue = mappedValue;
      return this;
   }

   public Number getWeight() {
      return this.weight;
   }

   public Item setWeight(@Property("weight") Number weight) {
      this.weight = weight;
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

   public Item addExtensions(Extension... extensions) {
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
