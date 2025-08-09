package org.sparkproject.dmg.pmml.time_series;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Arrays;
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
   name = "GARCH",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "armaPart", "garchPart"}
)
@JsonRootName("GARCH")
@JsonPropertyOrder({"extensions", "armaPart", "garchPart"})
@Added(Version.PMML_4_4)
public class GARCH extends Algorithm implements HasExtensions {
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "ARMAPart",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("ARMAPart")
   private ARMAPart armaPart;
   @XmlElement(
      name = "GARCHPart",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("GARCHPart")
   private GARCHPart garchPart;
   private static final long serialVersionUID = 67371272L;

   public GARCH() {
   }

   @ValueConstructor
   public GARCH(@Property("armaPart") ARMAPart armaPart, @Property("garchPart") GARCHPart garchPart) {
      this.armaPart = armaPart;
      this.garchPart = garchPart;
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

   public GARCH addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public ARMAPart requireARMAPart() {
      if (this.armaPart == null) {
         throw new MissingElementException(this, PMMLElements.GARCH_ARMAPART);
      } else {
         return this.armaPart;
      }
   }

   public ARMAPart getARMAPart() {
      return this.armaPart;
   }

   public GARCH setARMAPart(@Property("armaPart") ARMAPart armaPart) {
      this.armaPart = armaPart;
      return this;
   }

   public GARCHPart requireGARCHPart() {
      if (this.garchPart == null) {
         throw new MissingElementException(this, PMMLElements.GARCH_GARCHPART);
      } else {
         return this.garchPart;
      }
   }

   public GARCHPart getGARCHPart() {
      return this.garchPart;
   }

   public GARCH setGARCHPart(@Property("garchPart") GARCHPart garchPart) {
      this.garchPart = garchPart;
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
            status = PMMLObject.traverse(visitor, this.getARMAPart(), this.getGARCHPart());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
