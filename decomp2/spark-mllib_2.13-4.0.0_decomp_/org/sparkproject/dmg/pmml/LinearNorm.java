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
   name = "LinearNorm",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("LinearNorm")
@JsonPropertyOrder({"orig", "norm", "extensions"})
public class LinearNorm extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "orig",
      required = true
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("orig")
   private Number orig;
   @XmlAttribute(
      name = "norm",
      required = true
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("norm")
   private Number norm;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final long serialVersionUID = 67371272L;

   public LinearNorm() {
   }

   @ValueConstructor
   public LinearNorm(@Property("orig") Number orig, @Property("norm") Number norm) {
      this.orig = orig;
      this.norm = norm;
   }

   public Number requireOrig() {
      if (this.orig == null) {
         throw new MissingAttributeException(this, PMMLAttributes.LINEARNORM_ORIG);
      } else {
         return this.orig;
      }
   }

   public Number getOrig() {
      return this.orig;
   }

   public LinearNorm setOrig(@Property("orig") Number orig) {
      this.orig = orig;
      return this;
   }

   public Number requireNorm() {
      if (this.norm == null) {
         throw new MissingAttributeException(this, PMMLAttributes.LINEARNORM_NORM);
      } else {
         return this.norm;
      }
   }

   public Number getNorm() {
      return this.norm;
   }

   public LinearNorm setNorm(@Property("norm") Number norm) {
      this.norm = norm;
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

   public LinearNorm addExtensions(Extension... extensions) {
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
