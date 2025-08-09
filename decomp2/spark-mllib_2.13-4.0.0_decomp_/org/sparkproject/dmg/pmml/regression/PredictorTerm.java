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
import org.sparkproject.dmg.pmml.FieldRef;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.HasName;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "PredictorTerm",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "fieldRefs"}
)
@JsonRootName("PredictorTerm")
@JsonPropertyOrder({"name", "coefficient", "extensions", "fieldRefs"})
public class PredictorTerm extends Term implements HasExtensions, HasName {
   @XmlAttribute(
      name = "name"
   )
   @JsonProperty("name")
   @Added(Version.PMML_4_1)
   private String name;
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
   @XmlElement(
      name = "FieldRef",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("FieldRef")
   @CollectionElementType(FieldRef.class)
   private List fieldRefs;
   private static final long serialVersionUID = 67371272L;

   public PredictorTerm() {
   }

   @ValueConstructor
   public PredictorTerm(@Property("coefficient") Number coefficient, @Property("fieldRefs") List fieldRefs) {
      this.coefficient = coefficient;
      this.fieldRefs = fieldRefs;
   }

   public String getName() {
      return this.name;
   }

   public PredictorTerm setName(@Property("name") String name) {
      this.name = name;
      return this;
   }

   public Number requireCoefficient() {
      if (this.coefficient == null) {
         throw new MissingAttributeException(this, PMMLAttributes.PREDICTORTERM_COEFFICIENT);
      } else {
         return this.coefficient;
      }
   }

   public Number getCoefficient() {
      return this.coefficient;
   }

   public PredictorTerm setCoefficient(@Property("coefficient") Number coefficient) {
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

   public PredictorTerm addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public boolean hasFieldRefs() {
      return this.fieldRefs != null && !this.fieldRefs.isEmpty();
   }

   public List requireFieldRefs() {
      if (this.fieldRefs != null && !this.fieldRefs.isEmpty()) {
         return this.fieldRefs;
      } else {
         throw new MissingElementException(this, PMMLElements.PREDICTORTERM_FIELDREFS);
      }
   }

   public List getFieldRefs() {
      if (this.fieldRefs == null) {
         this.fieldRefs = new ArrayList();
      }

      return this.fieldRefs;
   }

   public PredictorTerm addFieldRefs(FieldRef... fieldRefs) {
      this.getFieldRefs().addAll(Arrays.asList(fieldRefs));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasFieldRefs()) {
            status = PMMLObject.traverse(visitor, this.getFieldRefs());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
