package org.sparkproject.dmg.pmml.support_vector_machine;

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
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;

@XmlRootElement(
   name = "PolynomialKernelType",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("PolynomialKernelType")
@JsonPropertyOrder({"description", "gamma", "coef0", "degree", "extensions"})
public class PolynomialKernel extends Kernel implements HasExtensions {
   @XmlAttribute(
      name = "description"
   )
   @JsonProperty("description")
   private String description;
   @XmlAttribute(
      name = "gamma"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("gamma")
   private Number gamma;
   @XmlAttribute(
      name = "coef0"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("coef0")
   private Number coef0;
   @XmlAttribute(
      name = "degree"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("degree")
   private Number degree;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final Number DEFAULT_GAMMA = (new RealNumberAdapter()).unmarshal("1");
   private static final Number DEFAULT_COEF0 = (new RealNumberAdapter()).unmarshal("1");
   private static final Number DEFAULT_DEGREE = (new RealNumberAdapter()).unmarshal("1");
   private static final long serialVersionUID = 67371272L;

   public String getDescription() {
      return this.description;
   }

   public PolynomialKernel setDescription(@Property("description") String description) {
      this.description = description;
      return this;
   }

   public Number getGamma() {
      return this.gamma == null ? DEFAULT_GAMMA : this.gamma;
   }

   public PolynomialKernel setGamma(@Property("gamma") Number gamma) {
      this.gamma = gamma;
      return this;
   }

   public Number getCoef0() {
      return this.coef0 == null ? DEFAULT_COEF0 : this.coef0;
   }

   public PolynomialKernel setCoef0(@Property("coef0") Number coef0) {
      this.coef0 = coef0;
      return this;
   }

   public Number getDegree() {
      return this.degree == null ? DEFAULT_DEGREE : this.degree;
   }

   public PolynomialKernel setDegree(@Property("degree") Number degree) {
      this.degree = degree;
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

   public PolynomialKernel addExtensions(Extension... extensions) {
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
