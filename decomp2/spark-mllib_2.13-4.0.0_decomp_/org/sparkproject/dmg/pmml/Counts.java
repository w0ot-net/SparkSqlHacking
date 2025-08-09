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
import org.sparkproject.dmg.pmml.adapters.NumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Counts",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("Counts")
@JsonPropertyOrder({"totalFreq", "missingFreq", "invalidFreq", "cardinality", "extensions"})
public class Counts extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "totalFreq",
      required = true
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("totalFreq")
   private Number totalFreq;
   @XmlAttribute(
      name = "missingFreq"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("missingFreq")
   private Number missingFreq;
   @XmlAttribute(
      name = "invalidFreq"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("invalidFreq")
   private Number invalidFreq;
   @XmlAttribute(
      name = "cardinality"
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("cardinality")
   @Added(Version.PMML_4_0)
   private Integer cardinality;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final long serialVersionUID = 67371272L;

   public Counts() {
   }

   @ValueConstructor
   public Counts(@Property("totalFreq") Number totalFreq) {
      this.totalFreq = totalFreq;
   }

   public Number requireTotalFreq() {
      if (this.totalFreq == null) {
         throw new MissingAttributeException(this, PMMLAttributes.COUNTS_TOTALFREQ);
      } else {
         return this.totalFreq;
      }
   }

   public Number getTotalFreq() {
      return this.totalFreq;
   }

   public Counts setTotalFreq(@Property("totalFreq") Number totalFreq) {
      this.totalFreq = totalFreq;
      return this;
   }

   public Number getMissingFreq() {
      return this.missingFreq;
   }

   public Counts setMissingFreq(@Property("missingFreq") Number missingFreq) {
      this.missingFreq = missingFreq;
      return this;
   }

   public Number getInvalidFreq() {
      return this.invalidFreq;
   }

   public Counts setInvalidFreq(@Property("invalidFreq") Number invalidFreq) {
      this.invalidFreq = invalidFreq;
      return this;
   }

   public Integer getCardinality() {
      return this.cardinality;
   }

   public Counts setCardinality(@Property("cardinality") Integer cardinality) {
      this.cardinality = cardinality;
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

   public Counts addExtensions(Extension... extensions) {
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
