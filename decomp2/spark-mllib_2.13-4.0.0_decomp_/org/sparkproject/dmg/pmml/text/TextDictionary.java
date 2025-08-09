package org.sparkproject.dmg.pmml.text;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.Array;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Taxonomy;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "TextDictionary",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "taxonomy", "array"}
)
@JsonRootName("TextDictionary")
@JsonPropertyOrder({"extensions", "taxonomy", "array"})
public class TextDictionary extends PMMLObject implements HasExtensions {
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "Taxonomy",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Taxonomy")
   private Taxonomy taxonomy;
   @XmlElement(
      name = "Array",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Array")
   private Array array;
   private static final long serialVersionUID = 67371272L;

   public TextDictionary() {
   }

   @ValueConstructor
   public TextDictionary(@Property("array") Array array) {
      this.array = array;
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

   public TextDictionary addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Taxonomy getTaxonomy() {
      return this.taxonomy;
   }

   public TextDictionary setTaxonomy(@Property("taxonomy") Taxonomy taxonomy) {
      this.taxonomy = taxonomy;
      return this;
   }

   public Array requireArray() {
      if (this.array == null) {
         throw new MissingElementException(this, PMMLElements.TEXTDICTIONARY_ARRAY);
      } else {
         return this.array;
      }
   }

   public Array getArray() {
      return this.array;
   }

   public TextDictionary setArray(@Property("array") Array array) {
      this.array = array;
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
            status = PMMLObject.traverse(visitor, this.getTaxonomy(), this.getArray());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
