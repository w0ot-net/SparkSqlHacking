package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.sparkproject.dmg.pmml.adapters.FieldNameAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "ParameterField",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = ""
)
@JsonRootName("ParameterField")
@JsonPropertyOrder({"name", "opType", "dataType", "displayName"})
public class ParameterField extends Field {
   @XmlAttribute(
      name = "name",
      required = true
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("name")
   private String name;
   @XmlAttribute(
      name = "optype"
   )
   @JsonProperty("optype")
   private OpType opType;
   @XmlAttribute(
      name = "dataType"
   )
   @JsonProperty("dataType")
   @Added(Version.PMML_3_1)
   private DataType dataType;
   @XmlAttribute(
      name = "displayName"
   )
   @JsonProperty("displayName")
   @Added(
      value = Version.PMML_4_4,
      removable = true
   )
   private String displayName;
   private static final long serialVersionUID = 67371272L;

   public ParameterField() {
   }

   @ValueConstructor
   public ParameterField(@Property("name") String name) {
      this.name = name;
   }

   public String requireName() {
      if (this.name == null) {
         throw new MissingAttributeException(this, PMMLAttributes.PARAMETERFIELD_NAME);
      } else {
         return this.name;
      }
   }

   public String getName() {
      return this.name;
   }

   public ParameterField setName(@Property("name") String name) {
      this.name = name;
      return this;
   }

   public OpType requireOpType() {
      if (this.opType == null) {
         throw new MissingAttributeException(this, PMMLAttributes.PARAMETERFIELD_OPTYPE);
      } else {
         return this.opType;
      }
   }

   public OpType getOpType() {
      return this.opType;
   }

   public ParameterField setOpType(@Property("opType") OpType opType) {
      this.opType = opType;
      return this;
   }

   public DataType requireDataType() {
      if (this.dataType == null) {
         throw new MissingAttributeException(this, PMMLAttributes.PARAMETERFIELD_DATATYPE);
      } else {
         return this.dataType;
      }
   }

   public DataType getDataType() {
      return this.dataType;
   }

   public ParameterField setDataType(@Property("dataType") DataType dataType) {
      this.dataType = dataType;
      return this;
   }

   public String getDisplayName() {
      return this.displayName;
   }

   public ParameterField setDisplayName(@Property("displayName") String displayName) {
      this.displayName = displayName;
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
