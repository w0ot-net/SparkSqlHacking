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
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.HasTable;
import org.sparkproject.dmg.pmml.InlineTable;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.TableLocator;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.dmg.pmml.adapters.IntegerAdapter;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "TrainingInstances",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "instanceFields", "tableLocator", "inlineTable"}
)
@JsonRootName("TrainingInstances")
@JsonPropertyOrder({"transformed", "recordCount", "fieldCount", "extensions", "instanceFields", "tableLocator", "inlineTable"})
@Added(Version.PMML_4_1)
public class TrainingInstances extends PMMLObject implements HasExtensions, HasTable {
   @XmlAttribute(
      name = "isTransformed"
   )
   @JsonProperty("isTransformed")
   private Boolean transformed;
   @XmlAttribute(
      name = "recordCount"
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("recordCount")
   private Integer recordCount;
   @XmlAttribute(
      name = "fieldCount"
   )
   @XmlJavaTypeAdapter(IntegerAdapter.class)
   @JsonProperty("fieldCount")
   private Integer fieldCount;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "InstanceFields",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("InstanceFields")
   private InstanceFields instanceFields;
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
   private static final Boolean DEFAULT_TRANSFORMED = false;
   private static final long serialVersionUID = 67371272L;

   public TrainingInstances() {
   }

   @ValueConstructor
   public TrainingInstances(@Property("instanceFields") InstanceFields instanceFields, @Property("inlineTable") InlineTable inlineTable) {
      this.instanceFields = instanceFields;
      this.inlineTable = inlineTable;
   }

   public boolean isTransformed() {
      return this.transformed == null ? DEFAULT_TRANSFORMED : this.transformed;
   }

   public TrainingInstances setTransformed(@Property("transformed") Boolean transformed) {
      this.transformed = transformed;
      return this;
   }

   public Integer getRecordCount() {
      return this.recordCount;
   }

   public TrainingInstances setRecordCount(@Property("recordCount") Integer recordCount) {
      this.recordCount = recordCount;
      return this;
   }

   public Integer getFieldCount() {
      return this.fieldCount;
   }

   public TrainingInstances setFieldCount(@Property("fieldCount") Integer fieldCount) {
      this.fieldCount = fieldCount;
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

   public TrainingInstances addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public InstanceFields requireInstanceFields() {
      if (this.instanceFields == null) {
         throw new MissingElementException(this, PMMLElements.TRAININGINSTANCES_INSTANCEFIELDS);
      } else {
         return this.instanceFields;
      }
   }

   public InstanceFields getInstanceFields() {
      return this.instanceFields;
   }

   public TrainingInstances setInstanceFields(@Property("instanceFields") InstanceFields instanceFields) {
      this.instanceFields = instanceFields;
      return this;
   }

   public TableLocator getTableLocator() {
      return this.tableLocator;
   }

   public TrainingInstances setTableLocator(@Property("tableLocator") TableLocator tableLocator) {
      this.tableLocator = tableLocator;
      return this;
   }

   public InlineTable requireInlineTable() {
      if (this.inlineTable == null) {
         throw new MissingElementException(this, PMMLElements.TRAININGINSTANCES_INLINETABLE);
      } else {
         return this.inlineTable;
      }
   }

   public InlineTable getInlineTable() {
      return this.inlineTable;
   }

   public TrainingInstances setInlineTable(@Property("inlineTable") InlineTable inlineTable) {
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

         if (status == VisitorAction.CONTINUE) {
            status = PMMLObject.traverse(visitor, this.getInstanceFields(), this.getTableLocator(), this.getInlineTable());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
