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
import org.sparkproject.dmg.pmml.adapters.FieldNameAdapter;
import org.sparkproject.dmg.pmml.adapters.NumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.AlternateValueConstructor;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "NormContinuous",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "linearNorms"}
)
@JsonRootName("NormContinuous")
@JsonPropertyOrder({"mapMissingTo", "field", "outliers", "extensions", "linearNorms"})
public class NormContinuous extends Expression implements HasExtensions, HasFieldReference, HasMapMissingTo {
   @XmlAttribute(
      name = "mapMissingTo"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("mapMissingTo")
   @Added(Version.PMML_3_2)
   private Number mapMissingTo;
   @XmlAttribute(
      name = "field",
      required = true
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("field")
   private String field;
   @XmlAttribute(
      name = "outliers"
   )
   @JsonProperty("outliers")
   @Added(Version.PMML_3_2)
   private OutlierTreatmentMethod outliers;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "LinearNorm",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("LinearNorm")
   @CollectionElementType(LinearNorm.class)
   private List linearNorms;
   private static final long serialVersionUID = 67371272L;

   public NormContinuous() {
   }

   @ValueConstructor
   public NormContinuous(@Property("field") String field, @Property("linearNorms") List linearNorms) {
      this.field = field;
      this.linearNorms = linearNorms;
   }

   @AlternateValueConstructor
   public NormContinuous(Field field, List linearNorms) {
      this(field != null ? field.requireName() : null, linearNorms);
   }

   public Number getMapMissingTo() {
      return this.mapMissingTo;
   }

   public NormContinuous setMapMissingTo(@Property("mapMissingTo") Number mapMissingTo) {
      this.mapMissingTo = mapMissingTo;
      return this;
   }

   public String requireField() {
      if (this.field == null) {
         throw new MissingAttributeException(this, PMMLAttributes.NORMCONTINUOUS_FIELD);
      } else {
         return this.field;
      }
   }

   public String getField() {
      return this.field;
   }

   public NormContinuous setField(@Property("field") String field) {
      this.field = field;
      return this;
   }

   public OutlierTreatmentMethod getOutliers() {
      return this.outliers == null ? OutlierTreatmentMethod.AS_IS : this.outliers;
   }

   public NormContinuous setOutliers(@Property("outliers") OutlierTreatmentMethod outliers) {
      this.outliers = outliers;
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

   public NormContinuous addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public boolean hasLinearNorms() {
      return this.linearNorms != null && !this.linearNorms.isEmpty();
   }

   public List requireLinearNorms() {
      if (this.linearNorms != null && !this.linearNorms.isEmpty()) {
         return this.linearNorms;
      } else {
         throw new MissingElementException(this, PMMLElements.NORMCONTINUOUS_LINEARNORMS);
      }
   }

   public List getLinearNorms() {
      if (this.linearNorms == null) {
         this.linearNorms = new ArrayList();
      }

      return this.linearNorms;
   }

   public NormContinuous addLinearNorms(LinearNorm... linearNorms) {
      this.getLinearNorms().addAll(Arrays.asList(linearNorms));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasLinearNorms()) {
            status = PMMLObject.traverse(visitor, this.getLinearNorms());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
