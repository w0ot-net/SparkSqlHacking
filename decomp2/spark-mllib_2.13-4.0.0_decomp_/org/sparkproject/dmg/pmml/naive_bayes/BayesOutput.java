package org.sparkproject.dmg.pmml.naive_bayes;

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
import org.sparkproject.dmg.pmml.HasTargetFieldReference;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitable;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.FieldNameAdapter;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Optional;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "BayesOutput",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "targetValueCounts"}
)
@JsonRootName("BayesOutput")
@JsonPropertyOrder({"targetField", "extensions", "targetValueCounts"})
public class BayesOutput extends PMMLObject implements HasExtensions, HasTargetFieldReference {
   @XmlAttribute(
      name = "fieldName"
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("fieldName")
   @Optional(Version.XPMML)
   private String targetField;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "TargetValueCounts",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("TargetValueCounts")
   private TargetValueCounts targetValueCounts;
   private static final long serialVersionUID = 67371272L;

   public BayesOutput() {
   }

   @ValueConstructor
   public BayesOutput(@Property("targetValueCounts") TargetValueCounts targetValueCounts) {
      this.targetValueCounts = targetValueCounts;
   }

   public String getTargetField() {
      return this.targetField;
   }

   public BayesOutput setTargetField(@Property("targetField") String targetField) {
      this.targetField = targetField;
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

   public BayesOutput addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public TargetValueCounts requireTargetValueCounts() {
      if (this.targetValueCounts == null) {
         throw new MissingElementException(this, PMMLElements.BAYESOUTPUT_TARGETVALUECOUNTS);
      } else {
         return this.targetValueCounts;
      }
   }

   public TargetValueCounts getTargetValueCounts() {
      return this.targetValueCounts;
   }

   public BayesOutput setTargetValueCounts(@Property("targetValueCounts") TargetValueCounts targetValueCounts) {
      this.targetValueCounts = targetValueCounts;
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
            status = PMMLObject.traverse(visitor, (Visitable)this.getTargetValueCounts());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
