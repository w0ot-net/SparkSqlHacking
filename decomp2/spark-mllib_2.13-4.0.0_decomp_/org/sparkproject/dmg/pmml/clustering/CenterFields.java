package org.sparkproject.dmg.pmml.clustering;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.dmg.pmml.DerivedField;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.Removed;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "CenterFields",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"derivedFields"}
)
@JsonRootName("CenterFields")
@JsonPropertyOrder({"derivedFields"})
@Removed(Version.PMML_3_2)
public class CenterFields extends PMMLObject {
   @XmlElement(
      name = "DerivedField",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("DerivedField")
   @CollectionElementType(DerivedField.class)
   private List derivedFields;
   private static final long serialVersionUID = 67371272L;

   public CenterFields() {
   }

   @ValueConstructor
   public CenterFields(@Property("derivedFields") List derivedFields) {
      this.derivedFields = derivedFields;
   }

   public boolean hasDerivedFields() {
      return this.derivedFields != null && !this.derivedFields.isEmpty();
   }

   public List requireDerivedFields() {
      if (this.derivedFields != null && !this.derivedFields.isEmpty()) {
         return this.derivedFields;
      } else {
         throw new MissingElementException(this, PMMLElements.CENTERFIELDS_DERIVEDFIELDS);
      }
   }

   public List getDerivedFields() {
      if (this.derivedFields == null) {
         this.derivedFields = new ArrayList();
      }

      return this.derivedFields;
   }

   public CenterFields addDerivedFields(DerivedField... derivedFields) {
      this.getDerivedFields().addAll(Arrays.asList(derivedFields));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasDerivedFields()) {
            status = PMMLObject.traverse(visitor, this.getDerivedFields());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
