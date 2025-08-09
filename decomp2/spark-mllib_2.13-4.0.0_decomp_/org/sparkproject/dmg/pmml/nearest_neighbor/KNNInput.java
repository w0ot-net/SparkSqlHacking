package org.sparkproject.dmg.pmml.nearest_neighbor;

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
import org.sparkproject.dmg.pmml.CompareFunction;
import org.sparkproject.dmg.pmml.ComparisonField;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.Field;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.FieldNameAdapter;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.AlternateValueConstructor;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "KNNInput",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("KNNInput")
@JsonPropertyOrder({"field", "fieldWeight", "compareFunction", "extensions"})
@Added(Version.PMML_4_1)
public class KNNInput extends ComparisonField implements HasExtensions {
   @XmlAttribute(
      name = "field",
      required = true
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("field")
   private String field;
   @XmlAttribute(
      name = "fieldWeight"
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("fieldWeight")
   private Number fieldWeight;
   @XmlAttribute(
      name = "compareFunction"
   )
   @JsonProperty("compareFunction")
   private CompareFunction compareFunction;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final Number DEFAULT_FIELD_WEIGHT = (new RealNumberAdapter()).unmarshal("1");
   private static final long serialVersionUID = 67371272L;

   public KNNInput() {
   }

   @ValueConstructor
   public KNNInput(@Property("field") String field) {
      this.field = field;
   }

   @AlternateValueConstructor
   public KNNInput(Field field) {
      this(field != null ? field.requireName() : null);
   }

   public String requireField() {
      if (this.field == null) {
         throw new MissingAttributeException(this, PMMLAttributes.KNNINPUT_FIELD);
      } else {
         return this.field;
      }
   }

   public String getField() {
      return this.field;
   }

   public KNNInput setField(@Property("field") String field) {
      this.field = field;
      return this;
   }

   public Number getFieldWeight() {
      return this.fieldWeight == null ? DEFAULT_FIELD_WEIGHT : this.fieldWeight;
   }

   public KNNInput setFieldWeight(@Property("fieldWeight") Number fieldWeight) {
      this.fieldWeight = fieldWeight;
      return this;
   }

   public CompareFunction getCompareFunction() {
      return this.compareFunction;
   }

   public KNNInput setCompareFunction(@Property("compareFunction") CompareFunction compareFunction) {
      this.compareFunction = compareFunction;
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

   public KNNInput addExtensions(Extension... extensions) {
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
