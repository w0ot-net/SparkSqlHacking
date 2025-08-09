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
import org.sparkproject.dmg.pmml.adapters.ObjectAdapter;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Optional;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "MapValues",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "fieldColumnPairs", "tableLocator", "inlineTable"}
)
@JsonRootName("MapValues")
@JsonPropertyOrder({"mapMissingTo", "defaultValue", "outputColumn", "dataType", "extensions", "fieldColumnPairs", "tableLocator", "inlineTable"})
public class MapValues extends Expression implements HasDataType, HasDefaultValue, HasExtensions, HasMapMissingTo, HasTable {
   @XmlAttribute(
      name = "mapMissingTo"
   )
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @JsonProperty("mapMissingTo")
   private Object mapMissingTo;
   @XmlAttribute(
      name = "defaultValue"
   )
   @XmlJavaTypeAdapter(ObjectAdapter.class)
   @XmlSchemaType(
      name = "anySimpleType"
   )
   @JsonProperty("defaultValue")
   private Object defaultValue;
   @XmlAttribute(
      name = "outputColumn",
      required = true
   )
   @JsonProperty("outputColumn")
   private String outputColumn;
   @XmlAttribute(
      name = "dataType"
   )
   @JsonProperty("dataType")
   @Added(Version.PMML_3_1)
   private DataType dataType;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "FieldColumnPair",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("FieldColumnPair")
   @Optional(Version.PMML_4_2)
   @CollectionElementType(FieldColumnPair.class)
   private List fieldColumnPairs;
   @XmlElement(
      name = "TableLocator",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("TableLocator")
   private TableLocator tableLocator;
   @XmlElement(
      name = "InlineTable",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("InlineTable")
   private InlineTable inlineTable;
   private static final long serialVersionUID = 67371272L;

   public MapValues() {
   }

   @ValueConstructor
   public MapValues(@Property("outputColumn") String outputColumn, @Property("inlineTable") InlineTable inlineTable) {
      this.outputColumn = outputColumn;
      this.inlineTable = inlineTable;
   }

   public Object getMapMissingTo() {
      return this.mapMissingTo;
   }

   public MapValues setMapMissingTo(@Property("mapMissingTo") Object mapMissingTo) {
      this.mapMissingTo = mapMissingTo;
      return this;
   }

   public Object getDefaultValue() {
      return this.defaultValue;
   }

   public MapValues setDefaultValue(@Property("defaultValue") Object defaultValue) {
      this.defaultValue = defaultValue;
      return this;
   }

   public String requireOutputColumn() {
      if (this.outputColumn == null) {
         throw new MissingAttributeException(this, PMMLAttributes.MAPVALUES_OUTPUTCOLUMN);
      } else {
         return this.outputColumn;
      }
   }

   public String getOutputColumn() {
      return this.outputColumn;
   }

   public MapValues setOutputColumn(@Property("outputColumn") String outputColumn) {
      this.outputColumn = outputColumn;
      return this;
   }

   public DataType getDataType() {
      return this.dataType;
   }

   public MapValues setDataType(@Property("dataType") DataType dataType) {
      this.dataType = dataType;
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

   public MapValues addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public boolean hasFieldColumnPairs() {
      return this.fieldColumnPairs != null && !this.fieldColumnPairs.isEmpty();
   }

   public List getFieldColumnPairs() {
      if (this.fieldColumnPairs == null) {
         this.fieldColumnPairs = new ArrayList();
      }

      return this.fieldColumnPairs;
   }

   public MapValues addFieldColumnPairs(FieldColumnPair... fieldColumnPairs) {
      this.getFieldColumnPairs().addAll(Arrays.asList(fieldColumnPairs));
      return this;
   }

   public TableLocator getTableLocator() {
      return this.tableLocator;
   }

   public MapValues setTableLocator(@Property("tableLocator") TableLocator tableLocator) {
      this.tableLocator = tableLocator;
      return this;
   }

   public InlineTable requireInlineTable() {
      if (this.inlineTable == null) {
         throw new MissingElementException(this, PMMLElements.MAPVALUES_INLINETABLE);
      } else {
         return this.inlineTable;
      }
   }

   public InlineTable getInlineTable() {
      return this.inlineTable;
   }

   public MapValues setInlineTable(@Property("inlineTable") InlineTable inlineTable) {
      this.inlineTable = inlineTable;
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasFieldColumnPairs()) {
            status = PMMLObject.traverse(visitor, this.getFieldColumnPairs());
         }

         if (status == VisitorAction.CONTINUE) {
            status = PMMLObject.traverse(visitor, this.getTableLocator(), this.getInlineTable());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
