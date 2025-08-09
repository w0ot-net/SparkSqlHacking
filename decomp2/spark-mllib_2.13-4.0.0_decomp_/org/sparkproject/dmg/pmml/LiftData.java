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
import org.sparkproject.dmg.pmml.adapters.NumberAdapter;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "LiftData",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "modelLiftGraph", "optimumLiftGraph", "randomLiftGraph"}
)
@JsonRootName("LiftData")
@JsonPropertyOrder({"targetFieldValue", "targetFieldDisplayValue", "rankingQuality", "extensions", "modelLiftGraph", "optimumLiftGraph", "randomLiftGraph"})
@Added(Version.PMML_4_0)
public class LiftData extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "targetFieldValue"
   )
   @JsonProperty("targetFieldValue")
   private String targetFieldValue;
   @XmlAttribute(
      name = "targetFieldDisplayValue"
   )
   @JsonProperty("targetFieldDisplayValue")
   private String targetFieldDisplayValue;
   @XmlAttribute(
      name = "rankingQuality"
   )
   @XmlJavaTypeAdapter(NumberAdapter.class)
   @JsonProperty("rankingQuality")
   private Number rankingQuality;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "ModelLiftGraph",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("ModelLiftGraph")
   private ModelLiftGraph modelLiftGraph;
   @XmlElement(
      name = "OptimumLiftGraph",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("OptimumLiftGraph")
   private OptimumLiftGraph optimumLiftGraph;
   @XmlElement(
      name = "RandomLiftGraph",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("RandomLiftGraph")
   private RandomLiftGraph randomLiftGraph;
   private static final long serialVersionUID = 67371272L;

   public LiftData() {
   }

   @ValueConstructor
   public LiftData(@Property("modelLiftGraph") ModelLiftGraph modelLiftGraph) {
      this.modelLiftGraph = modelLiftGraph;
   }

   public String getTargetFieldValue() {
      return this.targetFieldValue;
   }

   public LiftData setTargetFieldValue(@Property("targetFieldValue") String targetFieldValue) {
      this.targetFieldValue = targetFieldValue;
      return this;
   }

   public String getTargetFieldDisplayValue() {
      return this.targetFieldDisplayValue;
   }

   public LiftData setTargetFieldDisplayValue(@Property("targetFieldDisplayValue") String targetFieldDisplayValue) {
      this.targetFieldDisplayValue = targetFieldDisplayValue;
      return this;
   }

   public Number getRankingQuality() {
      return this.rankingQuality;
   }

   public LiftData setRankingQuality(@Property("rankingQuality") Number rankingQuality) {
      this.rankingQuality = rankingQuality;
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

   public LiftData addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public ModelLiftGraph requireModelLiftGraph() {
      if (this.modelLiftGraph == null) {
         throw new MissingElementException(this, PMMLElements.LIFTDATA_MODELLIFTGRAPH);
      } else {
         return this.modelLiftGraph;
      }
   }

   public ModelLiftGraph getModelLiftGraph() {
      return this.modelLiftGraph;
   }

   public LiftData setModelLiftGraph(@Property("modelLiftGraph") ModelLiftGraph modelLiftGraph) {
      this.modelLiftGraph = modelLiftGraph;
      return this;
   }

   public OptimumLiftGraph getOptimumLiftGraph() {
      return this.optimumLiftGraph;
   }

   public LiftData setOptimumLiftGraph(@Property("optimumLiftGraph") OptimumLiftGraph optimumLiftGraph) {
      this.optimumLiftGraph = optimumLiftGraph;
      return this;
   }

   public RandomLiftGraph getRandomLiftGraph() {
      return this.randomLiftGraph;
   }

   public LiftData setRandomLiftGraph(@Property("randomLiftGraph") RandomLiftGraph randomLiftGraph) {
      this.randomLiftGraph = randomLiftGraph;
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
            status = PMMLObject.traverse(visitor, this.getModelLiftGraph(), this.getOptimumLiftGraph(), this.getRandomLiftGraph());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
