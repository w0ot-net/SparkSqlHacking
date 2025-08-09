package org.sparkproject.dmg.pmml.scorecard;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Characteristics",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "characteristics"}
)
@JsonRootName("Characteristics")
@JsonPropertyOrder({"extensions", "characteristics"})
@Added(Version.PMML_4_1)
public class Characteristics extends PMMLObject implements Iterable, HasExtensions {
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "Characteristic",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("Characteristic")
   @CollectionElementType(Characteristic.class)
   private List characteristics;
   private static final long serialVersionUID = 67371272L;

   public Characteristics() {
   }

   @ValueConstructor
   public Characteristics(@Property("characteristics") List characteristics) {
      this.characteristics = characteristics;
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

   public Characteristics addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Iterator iterator() {
      return this.requireCharacteristics().iterator();
   }

   public boolean hasCharacteristics() {
      return this.characteristics != null && !this.characteristics.isEmpty();
   }

   public List requireCharacteristics() {
      if (this.characteristics != null && !this.characteristics.isEmpty()) {
         return this.characteristics;
      } else {
         throw new MissingElementException(this, PMMLElements.CHARACTERISTICS_CHARACTERISTICS);
      }
   }

   public List getCharacteristics() {
      if (this.characteristics == null) {
         this.characteristics = new ArrayList();
      }

      return this.characteristics;
   }

   public Characteristics addCharacteristics(Characteristic... characteristics) {
      this.getCharacteristics().addAll(Arrays.asList(characteristics));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasCharacteristics()) {
            status = PMMLObject.traverse(visitor, this.getCharacteristics());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
