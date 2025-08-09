package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.jpmml.model.MissingAttributeException;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "ChildParent",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "fieldColumnPairs", "tableLocator", "inlineTable"}
)
@JsonRootName("ChildParent")
@JsonPropertyOrder({"childField", "parentField", "parentLevelField", "recursive", "extensions", "fieldColumnPairs", "tableLocator", "inlineTable"})
public class ChildParent extends PMMLObject implements HasExtensions, HasTable {
   @XmlAttribute(
      name = "childField",
      required = true
   )
   @JsonProperty("childField")
   private String childField;
   @XmlAttribute(
      name = "parentField",
      required = true
   )
   @JsonProperty("parentField")
   private String parentField;
   @XmlAttribute(
      name = "parentLevelField"
   )
   @JsonProperty("parentLevelField")
   private String parentLevelField;
   @XmlAttribute(
      name = "isRecursive"
   )
   @JsonProperty("isRecursive")
   private Recursive recursive;
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
   @Added(Version.PMML_4_2)
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

   public ChildParent() {
   }

   @ValueConstructor
   public ChildParent(@Property("childField") String childField, @Property("parentField") String parentField, @Property("inlineTable") InlineTable inlineTable) {
      this.childField = childField;
      this.parentField = parentField;
      this.inlineTable = inlineTable;
   }

   public String requireChildField() {
      if (this.childField == null) {
         throw new MissingAttributeException(this, PMMLAttributes.CHILDPARENT_CHILDFIELD);
      } else {
         return this.childField;
      }
   }

   public String getChildField() {
      return this.childField;
   }

   public ChildParent setChildField(@Property("childField") String childField) {
      this.childField = childField;
      return this;
   }

   public String requireParentField() {
      if (this.parentField == null) {
         throw new MissingAttributeException(this, PMMLAttributes.CHILDPARENT_PARENTFIELD);
      } else {
         return this.parentField;
      }
   }

   public String getParentField() {
      return this.parentField;
   }

   public ChildParent setParentField(@Property("parentField") String parentField) {
      this.parentField = parentField;
      return this;
   }

   public String getParentLevelField() {
      return this.parentLevelField;
   }

   public ChildParent setParentLevelField(@Property("parentLevelField") String parentLevelField) {
      this.parentLevelField = parentLevelField;
      return this;
   }

   public Recursive getRecursive() {
      return this.recursive == null ? ChildParent.Recursive.NO : this.recursive;
   }

   public ChildParent setRecursive(@Property("recursive") Recursive recursive) {
      this.recursive = recursive;
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

   public ChildParent addExtensions(Extension... extensions) {
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

   public ChildParent addFieldColumnPairs(FieldColumnPair... fieldColumnPairs) {
      this.getFieldColumnPairs().addAll(Arrays.asList(fieldColumnPairs));
      return this;
   }

   public TableLocator getTableLocator() {
      return this.tableLocator;
   }

   public ChildParent setTableLocator(@Property("tableLocator") TableLocator tableLocator) {
      this.tableLocator = tableLocator;
      return this;
   }

   public InlineTable requireInlineTable() {
      if (this.inlineTable == null) {
         throw new MissingElementException(this, PMMLElements.CHILDPARENT_INLINETABLE);
      } else {
         return this.inlineTable;
      }
   }

   public InlineTable getInlineTable() {
      return this.inlineTable;
   }

   public ChildParent setInlineTable(@Property("inlineTable") InlineTable inlineTable) {
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

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum Recursive implements StringValue {
      @XmlEnumValue("no")
      @JsonProperty("no")
      NO("no"),
      @XmlEnumValue("yes")
      @JsonProperty("yes")
      YES("yes");

      private final String value;

      private Recursive(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static Recursive fromValue(String v) {
         for(Recursive c : values()) {
            if (c.value.equals(v)) {
               return c;
            }
         }

         throw new IllegalArgumentException(v);
      }

      public String toString() {
         return this.value();
      }
   }
}
