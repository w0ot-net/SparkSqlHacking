package org.sparkproject.dmg.pmml.general_regression;

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
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.IntegerAdapter;
import org.sparkproject.dmg.pmml.adapters.ObjectAdapter;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "PCell",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("PCell")
@JsonPropertyOrder({"targetCategory", "parameterName", "beta", "df", "extensions"})
public class PCell extends ParameterCell implements HasExtensions {
   @XmlAttribute(
      name = "targetCategory"
   )
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @JsonProperty("targetCategory")
   private Object targetCategory;
   @XmlAttribute(
      name = "parameterName",
      required = true
   )
   @JsonProperty("parameterName")
   private String parameterName;
   @XmlAttribute(
      name = "beta",
      required = true
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("beta")
   private Number beta;
   @XmlAttribute(
      name = "df"
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("df")
   private Integer df;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final long serialVersionUID = 67371272L;

   public PCell() {
   }

   @ValueConstructor
   public PCell(@Property("parameterName") String parameterName, @Property("beta") Number beta) {
      this.parameterName = parameterName;
      this.beta = beta;
   }

   public Object getTargetCategory() {
      return this.targetCategory;
   }

   public PCell setTargetCategory(@Property("targetCategory") Object targetCategory) {
      this.targetCategory = targetCategory;
      return this;
   }

   public String requireParameterName() {
      if (this.parameterName == null) {
         throw new MissingAttributeException(this, PMMLAttributes.PCELL_PARAMETERNAME);
      } else {
         return this.parameterName;
      }
   }

   public String getParameterName() {
      return this.parameterName;
   }

   public PCell setParameterName(@Property("parameterName") String parameterName) {
      this.parameterName = parameterName;
      return this;
   }

   public Number requireBeta() {
      if (this.beta == null) {
         throw new MissingAttributeException(this, PMMLAttributes.PCELL_BETA);
      } else {
         return this.beta;
      }
   }

   public Number getBeta() {
      return this.beta;
   }

   public PCell setBeta(@Property("beta") Number beta) {
      this.beta = beta;
      return this;
   }

   public Integer getDf() {
      return this.df;
   }

   public PCell setDf(@Property("df") Integer df) {
      this.df = df;
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

   public PCell addExtensions(Extension... extensions) {
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
