package org.sparkproject.dmg.pmml.time_series;

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
import org.sparkproject.dmg.pmml.adapters.IntegerAdapter;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "ARMAPart",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "ar", "ma"}
)
@JsonRootName("ARMAPart")
@JsonPropertyOrder({"constant", "p", "q", "extensions", "ar", "ma"})
@Added(Version.PMML_4_4)
public class ARMAPart extends PMMLObject implements HasExtensions, HasRequiredARMA {
   @XmlAttribute(
      name = "constant"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("constant")
   private Number constant;
   @XmlAttribute(
      name = "p",
      required = true
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("p")
   private Integer p;
   @XmlAttribute(
      name = "q",
      required = true
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("q")
   private Integer q;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "AR",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("AR")
   private AR ar;
   @XmlElement(
      name = "MA",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("MA")
   private MA ma;
   private static final Number DEFAULT_CONSTANT = (new RealNumberAdapter()).unmarshal("0");
   private static final long serialVersionUID = 67371272L;

   public ARMAPart() {
   }

   @ValueConstructor
   public ARMAPart(@Property("p") Integer p, @Property("q") Integer q, @Property("ar") AR ar, @Property("ma") MA ma) {
      this.p = p;
      this.q = q;
      this.ar = ar;
      this.ma = ma;
   }

   public Number getConstant() {
      return this.constant == null ? DEFAULT_CONSTANT : this.constant;
   }

   public ARMAPart setConstant(@Property("constant") Number constant) {
      this.constant = constant;
      return this;
   }

   public Integer requireP() {
      if (this.p == null) {
         throw new MissingAttributeException(this, PMMLAttributes.ARMAPART_P);
      } else {
         return this.p;
      }
   }

   public Integer getP() {
      return this.p;
   }

   public ARMAPart setP(@Property("p") Integer p) {
      this.p = p;
      return this;
   }

   public Integer requireQ() {
      if (this.q == null) {
         throw new MissingAttributeException(this, PMMLAttributes.ARMAPART_Q);
      } else {
         return this.q;
      }
   }

   public Integer getQ() {
      return this.q;
   }

   public ARMAPart setQ(@Property("q") Integer q) {
      this.q = q;
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

   public ARMAPart addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public AR requireAR() {
      if (this.ar == null) {
         throw new MissingElementException(this, PMMLElements.ARMAPART_AR);
      } else {
         return this.ar;
      }
   }

   public AR getAR() {
      return this.ar;
   }

   public ARMAPart setAR(@Property("ar") AR ar) {
      this.ar = ar;
      return this;
   }

   public MA requireMA() {
      if (this.ma == null) {
         throw new MissingElementException(this, PMMLElements.ARMAPART_MA);
      } else {
         return this.ma;
      }
   }

   public MA getMA() {
      return this.ma;
   }

   public ARMAPart setMA(@Property("ma") MA ma) {
      this.ma = ma;
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
            status = PMMLObject.traverse(visitor, this.getAR(), this.getMA());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
