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
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;

@XmlRootElement(
   name = "ResidualSquareCoefficients",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "residuals", "maCoefficients"}
)
@JsonRootName("ResidualSquareCoefficients")
@JsonPropertyOrder({"extensions", "residuals", "maCoefficients"})
@Added(Version.PMML_4_4)
public class ResidualSquareCoefficients extends PMMLObject implements HasExtensions {
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "Residuals",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Residuals")
   private Residuals residuals;
   @XmlElement(
      name = "MACoefficients",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("MACoefficients")
   private MACoefficients maCoefficients;
   private static final long serialVersionUID = 67371272L;

   public boolean hasExtensions() {
      return this.extensions != null && !this.extensions.isEmpty();
   }

   public List getExtensions() {
      if (this.extensions == null) {
         this.extensions = new ArrayList();
      }

      return this.extensions;
   }

   public ResidualSquareCoefficients addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Residuals getResiduals() {
      return this.residuals;
   }

   public ResidualSquareCoefficients setResiduals(@Property("residuals") Residuals residuals) {
      this.residuals = residuals;
      return this;
   }

   public MACoefficients getMACoefficients() {
      return this.maCoefficients;
   }

   public ResidualSquareCoefficients setMACoefficients(@Property("maCoefficients") MACoefficients maCoefficients) {
      this.maCoefficients = maCoefficients;
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
            status = PMMLObject.traverse(visitor, this.getResiduals(), this.getMACoefficients());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
