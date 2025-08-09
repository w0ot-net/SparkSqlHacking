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
import org.sparkproject.dmg.pmml.adapters.ObjectAdapter;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Constant",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"value"}
)
@JsonRootName("Constant")
@JsonPropertyOrder({"dataType", "missing", "value"})
public class Constant extends Expression implements HasDataType {
   @XmlAttribute(
      name = "dataType"
   )
   @JsonProperty("dataType")
   @Added(Version.PMML_3_1)
   private DataType dataType;
   @XmlAttribute(
      name = "missing"
   )
   @JsonProperty("missing")
   @Added(Version.PMML_4_4)
   private Boolean missing;
   @XmlValue
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @XmlValueExtension
   @JsonProperty("value")
   private Object value;
   private static final Boolean DEFAULT_MISSING = false;
   private static final long serialVersionUID = 67371272L;

   public Constant() {
   }

   @ValueConstructor
   public Constant(@Property("value") Object value) {
      this.value = value;
   }

   public DataType getDataType() {
      return this.dataType;
   }

   public Constant setDataType(@Property("dataType") DataType dataType) {
      this.dataType = dataType;
      return this;
   }

   public boolean isMissing() {
      return this.missing == null ? DEFAULT_MISSING : this.missing;
   }

   public Constant setMissing(@Property("missing") Boolean missing) {
      this.missing = missing;
      return this;
   }

   public Object getValue() {
      return this.value;
   }

   public Constant setValue(@Property("value") Object value) {
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
