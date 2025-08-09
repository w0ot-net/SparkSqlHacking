package org.sparkproject.dmg.pmml.association;

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
import org.sparkproject.dmg.pmml.HasRequiredId;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.NonNegativeIntegerAdapter;
import org.sparkproject.dmg.pmml.adapters.ProbabilityNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.CollectionSize;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Itemset",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "itemRefs"}
)
@JsonRootName("Itemset")
@JsonPropertyOrder({"id", "support", "numberOfItems", "extensions", "itemRefs"})
public class Itemset extends PMMLObject implements HasExtensions, HasRequiredId {
   @XmlAttribute(
      name = "id",
      required = true
   )
   @JsonProperty("id")
   private String id;
   @XmlAttribute(
      name = "support"
   )
   @XmlJavaTypeAdapter(ProbabilityNumberAdapter.class)
   @JsonProperty("support")
   private Number support;
   @XmlAttribute(
      name = "numberOfItems"
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("numberOfItems")
   @CollectionSize("itemRefs")
   private Integer numberOfItems;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "ItemRef",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("ItemRef")
   @CollectionElementType(ItemRef.class)
   private List itemRefs;
   private static final long serialVersionUID = 67371272L;

   public Itemset() {
   }

   @ValueConstructor
   public Itemset(@Property("id") String id) {
      this.id = id;
   }

   public String requireId() {
      if (this.id == null) {
         throw new MissingAttributeException(this, PMMLAttributes.ITEMSET_ID);
      } else {
         return this.id;
      }
   }

   public String getId() {
      return this.id;
   }

   public Itemset setId(@Property("id") String id) {
      this.id = id;
      return this;
   }

   public Number getSupport() {
      return this.support;
   }

   public Itemset setSupport(@Property("support") Number support) {
      this.support = support;
      return this;
   }

   public Integer getNumberOfItems() {
      return this.numberOfItems;
   }

   public Itemset setNumberOfItems(@Property("numberOfItems") Integer numberOfItems) {
      this.numberOfItems = numberOfItems;
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

   public Itemset addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public boolean hasItemRefs() {
      return this.itemRefs != null && !this.itemRefs.isEmpty();
   }

   public List getItemRefs() {
      if (this.itemRefs == null) {
         this.itemRefs = new ArrayList();
      }

      return this.itemRefs;
   }

   public Itemset addItemRefs(ItemRef... itemRefs) {
      this.getItemRefs().addAll(Arrays.asList(itemRefs));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasItemRefs()) {
            status = PMMLObject.traverse(visitor, this.getItemRefs());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
