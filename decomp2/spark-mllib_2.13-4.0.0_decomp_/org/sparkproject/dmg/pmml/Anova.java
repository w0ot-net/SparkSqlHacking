package org.sparkproject.dmg.pmml;

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
import org.sparkproject.dmg.pmml.adapters.FieldNameAdapter;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Anova",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "anovaRows"}
)
@JsonRootName("Anova")
@JsonPropertyOrder({"targetField", "extensions", "anovaRows"})
@Added(Version.PMML_4_0)
public class Anova extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "target"
   )
   @XmlJavaTypeAdapter(FieldNameAdapter.class)
   @JsonProperty("target")
   private String targetField;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "AnovaRow",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("AnovaRow")
   @CollectionElementType(AnovaRow.class)
   private List anovaRows;
   private static final long serialVersionUID = 67371272L;

   public Anova() {
   }

   @ValueConstructor
   public Anova(@Property("anovaRows") List anovaRows) {
      this.anovaRows = anovaRows;
   }

   public String getTargetField() {
      return this.targetField;
   }

   public Anova setTargetField(@Property("targetField") String targetField) {
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

   public Anova addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public boolean hasAnovaRows() {
      return this.anovaRows != null && !this.anovaRows.isEmpty();
   }

   public List requireAnovaRows() {
      if (this.anovaRows != null && !this.anovaRows.isEmpty()) {
         return this.anovaRows;
      } else {
         throw new MissingElementException(this, PMMLElements.ANOVA_ANOVAROWS);
      }
   }

   public List getAnovaRows() {
      if (this.anovaRows == null) {
         this.anovaRows = new ArrayList();
      }

      return this.anovaRows;
   }

   public Anova addAnovaRows(AnovaRow... anovaRows) {
      this.getAnovaRows().addAll(Arrays.asList(anovaRows));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasAnovaRows()) {
            status = PMMLObject.traverse(visitor, this.getAnovaRows());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
