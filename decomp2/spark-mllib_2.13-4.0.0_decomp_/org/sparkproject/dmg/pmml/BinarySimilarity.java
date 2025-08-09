package org.sparkproject.dmg.pmml;

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
import org.sparkproject.dmg.pmml.adapters.NumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "binarySimilarity",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("binarySimilarity")
@JsonPropertyOrder({"c00Parameter", "c01Parameter", "c10Parameter", "c11Parameter", "d00Parameter", "d01Parameter", "d10Parameter", "d11Parameter", "extensions"})
public class BinarySimilarity extends Similarity implements HasExtensions {
   @XmlAttribute(
      name = "c00-parameter",
      required = true
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("c00-parameter")
   private Number c00Parameter;
   @XmlAttribute(
      name = "c01-parameter",
      required = true
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("c01-parameter")
   private Number c01Parameter;
   @XmlAttribute(
      name = "c10-parameter",
      required = true
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("c10-parameter")
   private Number c10Parameter;
   @XmlAttribute(
      name = "c11-parameter",
      required = true
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("c11-parameter")
   private Number c11Parameter;
   @XmlAttribute(
      name = "d00-parameter",
      required = true
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("d00-parameter")
   private Number d00Parameter;
   @XmlAttribute(
      name = "d01-parameter",
      required = true
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("d01-parameter")
   private Number d01Parameter;
   @XmlAttribute(
      name = "d10-parameter",
      required = true
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("d10-parameter")
   private Number d10Parameter;
   @XmlAttribute(
      name = "d11-parameter",
      required = true
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("d11-parameter")
   private Number d11Parameter;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final long serialVersionUID = 67371272L;

   public BinarySimilarity() {
   }

   @ValueConstructor
   public BinarySimilarity(@Property("c00Parameter") Number c00Parameter, @Property("c01Parameter") Number c01Parameter, @Property("c10Parameter") Number c10Parameter, @Property("c11Parameter") Number c11Parameter, @Property("d00Parameter") Number d00Parameter, @Property("d01Parameter") Number d01Parameter, @Property("d10Parameter") Number d10Parameter, @Property("d11Parameter") Number d11Parameter) {
      this.c00Parameter = c00Parameter;
      this.c01Parameter = c01Parameter;
      this.c10Parameter = c10Parameter;
      this.c11Parameter = c11Parameter;
      this.d00Parameter = d00Parameter;
      this.d01Parameter = d01Parameter;
      this.d10Parameter = d10Parameter;
      this.d11Parameter = d11Parameter;
   }

   public Number requireC00Parameter() {
      if (this.c00Parameter == null) {
         throw new MissingAttributeException(this, PMMLAttributes.BINARYSIMILARITY_C00PARAMETER);
      } else {
         return this.c00Parameter;
      }
   }

   public Number getC00Parameter() {
      return this.c00Parameter;
   }

   public BinarySimilarity setC00Parameter(@Property("c00Parameter") Number c00Parameter) {
      this.c00Parameter = c00Parameter;
      return this;
   }

   public Number requireC01Parameter() {
      if (this.c01Parameter == null) {
         throw new MissingAttributeException(this, PMMLAttributes.BINARYSIMILARITY_C01PARAMETER);
      } else {
         return this.c01Parameter;
      }
   }

   public Number getC01Parameter() {
      return this.c01Parameter;
   }

   public BinarySimilarity setC01Parameter(@Property("c01Parameter") Number c01Parameter) {
      this.c01Parameter = c01Parameter;
      return this;
   }

   public Number requireC10Parameter() {
      if (this.c10Parameter == null) {
         throw new MissingAttributeException(this, PMMLAttributes.BINARYSIMILARITY_C10PARAMETER);
      } else {
         return this.c10Parameter;
      }
   }

   public Number getC10Parameter() {
      return this.c10Parameter;
   }

   public BinarySimilarity setC10Parameter(@Property("c10Parameter") Number c10Parameter) {
      this.c10Parameter = c10Parameter;
      return this;
   }

   public Number requireC11Parameter() {
      if (this.c11Parameter == null) {
         throw new MissingAttributeException(this, PMMLAttributes.BINARYSIMILARITY_C11PARAMETER);
      } else {
         return this.c11Parameter;
      }
   }

   public Number getC11Parameter() {
      return this.c11Parameter;
   }

   public BinarySimilarity setC11Parameter(@Property("c11Parameter") Number c11Parameter) {
      this.c11Parameter = c11Parameter;
      return this;
   }

   public Number requireD00Parameter() {
      if (this.d00Parameter == null) {
         throw new MissingAttributeException(this, PMMLAttributes.BINARYSIMILARITY_D00PARAMETER);
      } else {
         return this.d00Parameter;
      }
   }

   public Number getD00Parameter() {
      return this.d00Parameter;
   }

   public BinarySimilarity setD00Parameter(@Property("d00Parameter") Number d00Parameter) {
      this.d00Parameter = d00Parameter;
      return this;
   }

   public Number requireD01Parameter() {
      if (this.d01Parameter == null) {
         throw new MissingAttributeException(this, PMMLAttributes.BINARYSIMILARITY_D01PARAMETER);
      } else {
         return this.d01Parameter;
      }
   }

   public Number getD01Parameter() {
      return this.d01Parameter;
   }

   public BinarySimilarity setD01Parameter(@Property("d01Parameter") Number d01Parameter) {
      this.d01Parameter = d01Parameter;
      return this;
   }

   public Number requireD10Parameter() {
      if (this.d10Parameter == null) {
         throw new MissingAttributeException(this, PMMLAttributes.BINARYSIMILARITY_D10PARAMETER);
      } else {
         return this.d10Parameter;
      }
   }

   public Number getD10Parameter() {
      return this.d10Parameter;
   }

   public BinarySimilarity setD10Parameter(@Property("d10Parameter") Number d10Parameter) {
      this.d10Parameter = d10Parameter;
      return this;
   }

   public Number requireD11Parameter() {
      if (this.d11Parameter == null) {
         throw new MissingAttributeException(this, PMMLAttributes.BINARYSIMILARITY_D11PARAMETER);
      } else {
         return this.d11Parameter;
      }
   }

   public Number getD11Parameter() {
      return this.d11Parameter;
   }

   public BinarySimilarity setD11Parameter(@Property("d11Parameter") Number d11Parameter) {
      this.d11Parameter = d11Parameter;
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

   public BinarySimilarity addExtensions(Extension... extensions) {
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
