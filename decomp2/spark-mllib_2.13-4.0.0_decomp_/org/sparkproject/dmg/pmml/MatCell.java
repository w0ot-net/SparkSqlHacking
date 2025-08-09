package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.eclipse.persistence.oxm.annotations.XmlValueExtension;
import org.sparkproject.dmg.pmml.adapters.IntegerAdapter;
import org.sparkproject.dmg.pmml.adapters.ObjectAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "MatCell",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"value"}
)
@JsonRootName("MatCell")
@JsonPropertyOrder({"row", "col", "value"})
public class MatCell extends PMMLObject {
   @XmlAttribute(
      name = "row",
      required = true
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("row")
   private Integer row;
   @XmlAttribute(
      name = "col",
      required = true
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("col")
   private Integer col;
   @XmlValue
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @XmlValueExtension
   @JsonProperty("value")
   private Object value;
   private static final long serialVersionUID = 67371272L;

   public MatCell() {
   }

   @ValueConstructor
   public MatCell(@Property("row") Integer row, @Property("col") Integer col, @Property("value") Object value) {
      this.row = row;
      this.col = col;
      this.value = value;
   }

   public Integer requireRow() {
      if (this.row == null) {
         throw new MissingAttributeException(this, PMMLAttributes.MATCELL_ROW);
      } else {
         return this.row;
      }
   }

   public Integer getRow() {
      return this.row;
   }

   public MatCell setRow(@Property("row") Integer row) {
      this.row = row;
      return this;
   }

   public Integer requireCol() {
      if (this.col == null) {
         throw new MissingAttributeException(this, PMMLAttributes.MATCELL_COL);
      } else {
         return this.col;
      }
   }

   public Integer getCol() {
      return this.col;
   }

   public MatCell setCol(@Property("col") Integer col) {
      this.col = col;
      return this;
   }

   public Object getValue() {
      return this.value;
   }

   public MatCell setValue(@Property("value") Object value) {
      this.value = value;
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
