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
import org.sparkproject.dmg.pmml.adapters.NonNegativeIntegerAdapter;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.CollectionSize;
import org.sparkproject.jpmml.model.annotations.Optional;
import org.sparkproject.jpmml.model.annotations.Property;

@XmlRootElement(
   name = "DataDictionary",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "dataFields", "taxonomies"}
)
@JsonRootName("DataDictionary")
@JsonPropertyOrder({"numberOfFields", "extensions", "dataFields", "taxonomies"})
public class DataDictionary extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "numberOfFields"
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("numberOfFields")
   @CollectionSize("dataFields")
   private Integer numberOfFields;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "DataField",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("DataField")
   @Optional(Version.XPMML)
   @CollectionElementType(DataField.class)
   private List dataFields;
   @XmlElement(
      name = "Taxonomy",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Taxonomy")
   @CollectionElementType(Taxonomy.class)
   private List taxonomies;
   private static final long serialVersionUID = 67371272L;

   public Integer getNumberOfFields() {
      return this.numberOfFields;
   }

   public DataDictionary setNumberOfFields(@Property("numberOfFields") Integer numberOfFields) {
      this.numberOfFields = numberOfFields;
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

   public DataDictionary addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public boolean hasDataFields() {
      return this.dataFields != null && !this.dataFields.isEmpty();
   }

   public List getDataFields() {
      if (this.dataFields == null) {
         this.dataFields = new ArrayList();
      }

      return this.dataFields;
   }

   public DataDictionary addDataFields(DataField... dataFields) {
      this.getDataFields().addAll(Arrays.asList(dataFields));
      return this;
   }

   public boolean hasTaxonomies() {
      return this.taxonomies != null && !this.taxonomies.isEmpty();
   }

   public List getTaxonomies() {
      if (this.taxonomies == null) {
         this.taxonomies = new ArrayList();
      }

      return this.taxonomies;
   }

   public DataDictionary addTaxonomies(Taxonomy... taxonomies) {
      this.getTaxonomies().addAll(Arrays.asList(taxonomies));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasDataFields()) {
            status = PMMLObject.traverse(visitor, this.getDataFields());
         }

         if (status == VisitorAction.CONTINUE && this.hasTaxonomies()) {
            status = PMMLObject.traverse(visitor, this.getTaxonomies());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
