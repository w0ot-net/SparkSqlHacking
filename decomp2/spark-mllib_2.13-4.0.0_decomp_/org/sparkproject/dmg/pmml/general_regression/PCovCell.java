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
import org.sparkproject.dmg.pmml.adapters.ObjectAdapter;
import org.sparkproject.dmg.pmml.adapters.RealNumberAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "PCovCell",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("PCovCell")
@JsonPropertyOrder({"pRow", "pCol", "tRow", "tCol", "value", "targetCategory", "extensions"})
public class PCovCell extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "pRow",
      required = true
   )
   @JsonProperty("pRow")
   private String pRow;
   @XmlAttribute(
      name = "pCol",
      required = true
   )
   @JsonProperty("pCol")
   private String pCol;
   @XmlAttribute(
      name = "tRow"
   )
   @JsonProperty("tRow")
   private String tRow;
   @XmlAttribute(
      name = "tCol"
   )
   @JsonProperty("tCol")
   private String tCol;
   @XmlAttribute(
      name = "value",
      required = true
   )
   @XmlJavaTypeAdapter(RealNumberAdapter.class)
   @JsonProperty("value")
   private Number value;
   @XmlAttribute(
      name = "targetCategory"
   )
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @JsonProperty("targetCategory")
   private Object targetCategory;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final long serialVersionUID = 67371272L;

   public PCovCell() {
   }

   @ValueConstructor
   public PCovCell(@Property("pRow") String pRow, @Property("pCol") String pCol, @Property("value") Number value) {
      this.pRow = pRow;
      this.pCol = pCol;
      this.value = value;
   }

   public String requirePRow() {
      if (this.pRow == null) {
         throw new MissingAttributeException(this, PMMLAttributes.PCOVCELL_PROW);
      } else {
         return this.pRow;
      }
   }

   public String getPRow() {
      return this.pRow;
   }

   public PCovCell setPRow(@Property("pRow") String pRow) {
      this.pRow = pRow;
      return this;
   }

   public String requirePCol() {
      if (this.pCol == null) {
         throw new MissingAttributeException(this, PMMLAttributes.PCOVCELL_PCOL);
      } else {
         return this.pCol;
      }
   }

   public String getPCol() {
      return this.pCol;
   }

   public PCovCell setPCol(@Property("pCol") String pCol) {
      this.pCol = pCol;
      return this;
   }

   public String getTRow() {
      return this.tRow;
   }

   public PCovCell setTRow(@Property("tRow") String tRow) {
      this.tRow = tRow;
      return this;
   }

   public String getTCol() {
      return this.tCol;
   }

   public PCovCell setTCol(@Property("tCol") String tCol) {
      this.tCol = tCol;
      return this;
   }

   public Number requireValue() {
      if (this.value == null) {
         throw new MissingAttributeException(this, PMMLAttributes.PCOVCELL_VALUE);
      } else {
         return this.value;
      }
   }

   public Number getValue() {
      return this.value;
   }

   public PCovCell setValue(@Property("value") Number value) {
      this.value = value;
      return this;
   }

   public Object getTargetCategory() {
      return this.targetCategory;
   }

   public PCovCell setTargetCategory(@Property("targetCategory") Object targetCategory) {
      this.targetCategory = targetCategory;
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

   public PCovCell addExtensions(Extension... extensions) {
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
