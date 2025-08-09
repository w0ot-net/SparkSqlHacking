package org.sparkproject.dmg.pmml;

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
import org.sparkproject.dmg.pmml.adapters.FieldNameAdapter;
import org.sparkproject.dmg.pmml.adapters.ObjectAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "ResultField",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("ResultField")
@JsonPropertyOrder({"name", "displayName", "opType", "dataType", "resultFeature", "value", "extensions"})
public class ResultField extends Field implements HasExtensions {
   @XmlAttribute(
      name = "name",
      required = true
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("name")
   private String name;
   @XmlAttribute(
      name = "displayName"
   )
   @JsonProperty("displayName")
   private String displayName;
   @XmlAttribute(
      name = "optype"
   )
   @JsonProperty("optype")
   private OpType opType;
   @XmlAttribute(
      name = "dataType"
   )
   @JsonProperty("dataType")
   private DataType dataType;
   @XmlAttribute(
      name = "feature"
   )
   @JsonProperty("feature")
   private ResultFeature resultFeature;
   @XmlAttribute(
      name = "value"
   )
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @JsonProperty("value")
   private Object value;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final long serialVersionUID = 67371272L;

   public ResultField() {
   }

   @ValueConstructor
   public ResultField(@Property("name") String name) {
      this.name = name;
   }

   public String requireName() {
      if (this.name == null) {
         throw new MissingAttributeException(this, PMMLAttributes.RESULTFIELD_NAME);
      } else {
         return this.name;
      }
   }

   public String getName() {
      return this.name;
   }

   public ResultField setName(@Property("name") String name) {
      this.name = name;
      return this;
   }

   public String getDisplayName() {
      return this.displayName;
   }

   public ResultField setDisplayName(@Property("displayName") String displayName) {
      this.displayName = displayName;
      return this;
   }

   public OpType requireOpType() {
      if (this.opType == null) {
         throw new MissingAttributeException(this, PMMLAttributes.RESULTFIELD_OPTYPE);
      } else {
         return this.opType;
      }
   }

   public OpType getOpType() {
      return this.opType;
   }

   public ResultField setOpType(@Property("opType") OpType opType) {
      this.opType = opType;
      return this;
   }

   public DataType requireDataType() {
      if (this.dataType == null) {
         throw new MissingAttributeException(this, PMMLAttributes.RESULTFIELD_DATATYPE);
      } else {
         return this.dataType;
      }
   }

   public DataType getDataType() {
      return this.dataType;
   }

   public ResultField setDataType(@Property("dataType") DataType dataType) {
      this.dataType = dataType;
      return this;
   }

   public ResultFeature getResultFeature() {
      return this.resultFeature;
   }

   public ResultField setResultFeature(@Property("resultFeature") ResultFeature resultFeature) {
      this.resultFeature = resultFeature;
      return this;
   }

   public Object getValue() {
      return this.value;
   }

   public ResultField setValue(@Property("value") Object value) {
      this.value = value;
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

   public ResultField addExtensions(Extension... extensions) {
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
