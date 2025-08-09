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
import org.sparkproject.dmg.pmml.Array;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.HasRequiredArray;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitable;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.IntegerAdapter;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "SeasonalFactor",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "array"}
)
@JsonRootName("SeasonalFactor")
@JsonPropertyOrder({"difference", "maximumOrder", "extensions", "array"})
@Added(Version.PMML_4_4)
public class SeasonalFactor extends PMMLObject implements HasExtensions, HasRequiredArray {
   @XmlAttribute(
      name = "difference"
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("difference")
   private Integer difference;
   @XmlAttribute(
      name = "maximumOrder"
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("maximumOrder")
   private Integer maximumOrder;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "Array",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Array")
   private Array array;
   private static final Integer DEFAULT_DIFFERENCE = (new IntegerAdapter()).unmarshal("0");
   private static final long serialVersionUID = 67371272L;

   public SeasonalFactor() {
   }

   @ValueConstructor
   public SeasonalFactor(@Property("array") Array array) {
      this.array = array;
   }

   public Integer getDifference() {
      return this.difference == null ? DEFAULT_DIFFERENCE : this.difference;
   }

   public SeasonalFactor setDifference(@Property("difference") Integer difference) {
      this.difference = difference;
      return this;
   }

   public Integer getMaximumOrder() {
      return this.maximumOrder;
   }

   public SeasonalFactor setMaximumOrder(@Property("maximumOrder") Integer maximumOrder) {
      this.maximumOrder = maximumOrder;
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

   public SeasonalFactor addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Array requireArray() {
      if (this.array == null) {
         throw new MissingElementException(this, PMMLElements.SEASONALFACTOR_ARRAY);
      } else {
         return this.array;
      }
   }

   public Array getArray() {
      return this.array;
   }

   public SeasonalFactor setArray(@Property("array") Array array) {
      this.array = array;
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
            status = PMMLObject.traverse(visitor, (Visitable)this.getArray());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
