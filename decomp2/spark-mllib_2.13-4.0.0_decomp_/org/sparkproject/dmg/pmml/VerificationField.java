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
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.AlternateValueConstructor;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "VerificationField",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("VerificationField")
@JsonPropertyOrder({"field", "column", "precision", "zeroThreshold", "extensions"})
public class VerificationField extends PMMLObject implements HasExtensions, HasFieldReference, Indexable {
   @XmlAttribute(
      name = "field",
      required = true
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("field")
   private String field;
   @XmlAttribute(
      name = "column"
   )
   @JsonProperty("column")
   private String column;
   @XmlAttribute(
      name = "precision"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("precision")
   private Number precision;
   @XmlAttribute(
      name = "zeroThreshold"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("zeroThreshold")
   private Number zeroThreshold;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final Number DEFAULT_PRECISION = (new RealNumberAdapter()).unmarshal("1E-6");
   private static final Number DEFAULT_ZERO_THRESHOLD = (new RealNumberAdapter()).unmarshal("1E-16");
   private static final long serialVersionUID = 67371272L;

   public VerificationField() {
   }

   @ValueConstructor
   public VerificationField(@Property("field") String field) {
      this.field = field;
   }

   @AlternateValueConstructor
   public VerificationField(Field field) {
      this(field != null ? field.requireName() : null);
   }

   public String getKey() {
      return this.requireField();
   }

   public String requireField() {
      if (this.field == null) {
         throw new MissingAttributeException(this, PMMLAttributes.VERIFICATIONFIELD_FIELD);
      } else {
         return this.field;
      }
   }

   public String getField() {
      return this.field;
   }

   public VerificationField setField(@Property("field") String field) {
      this.field = field;
      return this;
   }

   public String getColumn() {
      return this.column;
   }

   public VerificationField setColumn(@Property("column") String column) {
      this.column = column;
      return this;
   }

   public Number getPrecision() {
      return this.precision == null ? DEFAULT_PRECISION : this.precision;
   }

   public VerificationField setPrecision(@Property("precision") Number precision) {
      this.precision = precision;
      return this;
   }

   public Number getZeroThreshold() {
      return this.zeroThreshold == null ? DEFAULT_ZERO_THRESHOLD : this.zeroThreshold;
   }

   public VerificationField setZeroThreshold(@Property("zeroThreshold") Number zeroThreshold) {
      this.zeroThreshold = zeroThreshold;
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

   public VerificationField addExtensions(Extension... extensions) {
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
