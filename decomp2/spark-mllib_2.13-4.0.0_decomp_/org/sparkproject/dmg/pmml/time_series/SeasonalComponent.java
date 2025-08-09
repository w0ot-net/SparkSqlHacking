package org.sparkproject.dmg.pmml.time_series;

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
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.NonNegativeIntegerAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "SeasonalComponent",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "ar", "ma"}
)
@JsonRootName("SeasonalComponent")
@JsonPropertyOrder({"p", "d", "q", "period", "extensions", "ar", "ma"})
@Added(Version.PMML_4_4)
public class SeasonalComponent extends PMMLObject implements HasExtensions, HasARIMA {
   @XmlAttribute(
      name = "P"
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("P")
   private Integer p;
   @XmlAttribute(
      name = "D"
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("D")
   private Integer d;
   @XmlAttribute(
      name = "Q"
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("Q")
   private Integer q;
   @XmlAttribute(
      name = "period",
      required = true
   )
   @XmlJavaTypeAdapter(NonNegativeIntegerAdapter.class)
   @XmlSchemaType(
      name = "nonNegativeInteger"
   )
   @JsonProperty("period")
   private Integer period;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "AR",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("AR")
   private AR ar;
   @XmlElement(
      name = "MA",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("MA")
   private MA ma;
   private static final Integer DEFAULT_P = (new NonNegativeIntegerAdapter()).unmarshal("0");
   private static final Integer DEFAULT_D = (new NonNegativeIntegerAdapter()).unmarshal("0");
   private static final Integer DEFAULT_Q = (new NonNegativeIntegerAdapter()).unmarshal("0");
   private static final long serialVersionUID = 67371272L;

   public SeasonalComponent() {
   }

   @ValueConstructor
   public SeasonalComponent(@Property("period") Integer period) {
      this.period = period;
   }

   public Integer getP() {
      return this.p == null ? DEFAULT_P : this.p;
   }

   public SeasonalComponent setP(@Property("p") Integer p) {
      this.p = p;
      return this;
   }

   public Integer getD() {
      return this.d == null ? DEFAULT_D : this.d;
   }

   public SeasonalComponent setD(@Property("d") Integer d) {
      this.d = d;
      return this;
   }

   public Integer getQ() {
      return this.q == null ? DEFAULT_Q : this.q;
   }

   public SeasonalComponent setQ(@Property("q") Integer q) {
      this.q = q;
      return this;
   }

   public Integer requirePeriod() {
      if (this.period == null) {
         throw new MissingAttributeException(this, PMMLAttributes.SEASONALCOMPONENT_PERIOD);
      } else {
         return this.period;
      }
   }

   public Integer getPeriod() {
      return this.period;
   }

   public SeasonalComponent setPeriod(@Property("period") Integer period) {
      this.period = period;
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

   public SeasonalComponent addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public AR getAR() {
      return this.ar;
   }

   public SeasonalComponent setAR(@Property("ar") AR ar) {
      this.ar = ar;
      return this;
   }

   public MA getMA() {
      return this.ma;
   }

   public SeasonalComponent setMA(@Property("ma") MA ma) {
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
