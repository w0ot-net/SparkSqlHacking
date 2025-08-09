package org.sparkproject.dmg.pmml.gaussian_process;

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
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;

@XmlRootElement(
   name = "RadialBasisKernel",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("RadialBasisKernel")
@JsonPropertyOrder({"description", "gamma", "noiseVariance", "lambda", "extensions"})
@Added(Version.PMML_4_3)
public class RadialBasisKernel extends PMMLObject implements HasExtensions {
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
      name = "noiseVariance"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("noiseVariance")
   private Number noiseVariance;
   @XmlAttribute(
      name = "lambda"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("lambda")
   private Number lambda;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final Number DEFAULT_GAMMA = (new RealNumberAdapter()).unmarshal("1");
   private static final Number DEFAULT_NOISE_VARIANCE = (new RealNumberAdapter()).unmarshal("1");
   private static final Number DEFAULT_LAMBDA = (new RealNumberAdapter()).unmarshal("1");
   private static final long serialVersionUID = 67371272L;

   public String getDescription() {
      return this.description;
   }

   public RadialBasisKernel setDescription(@Property("description") String description) {
      this.description = description;
      return this;
   }

   public Number getGamma() {
      return this.gamma == null ? DEFAULT_GAMMA : this.gamma;
   }

   public RadialBasisKernel setGamma(@Property("gamma") Number gamma) {
      this.gamma = gamma;
      return this;
   }

   public Number getNoiseVariance() {
      return this.noiseVariance == null ? DEFAULT_NOISE_VARIANCE : this.noiseVariance;
   }

   public RadialBasisKernel setNoiseVariance(@Property("noiseVariance") Number noiseVariance) {
      this.noiseVariance = noiseVariance;
      return this;
   }

   public Number getLambda() {
      return this.lambda == null ? DEFAULT_LAMBDA : this.lambda;
   }

   public RadialBasisKernel setLambda(@Property("lambda") Number lambda) {
      this.lambda = lambda;
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

   public RadialBasisKernel addExtensions(Extension... extensions) {
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
