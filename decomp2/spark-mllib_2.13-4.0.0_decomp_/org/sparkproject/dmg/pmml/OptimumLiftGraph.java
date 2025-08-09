package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "OptimumLiftGraph",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "liftGraph"}
)
@JsonRootName("OptimumLiftGraph")
@JsonPropertyOrder({"extensions", "liftGraph"})
@Added(Version.PMML_4_0)
public class OptimumLiftGraph extends PMMLObject implements HasExtensions {
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "LiftGraph",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("LiftGraph")
   private LiftGraph liftGraph;
   private static final long serialVersionUID = 67371272L;

   public OptimumLiftGraph() {
   }

   @ValueConstructor
   public OptimumLiftGraph(@Property("liftGraph") LiftGraph liftGraph) {
      this.liftGraph = liftGraph;
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

   public OptimumLiftGraph addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public LiftGraph requireLiftGraph() {
      if (this.liftGraph == null) {
         throw new MissingElementException(this, PMMLElements.OPTIMUMLIFTGRAPH_LIFTGRAPH);
      } else {
         return this.liftGraph;
      }
   }

   public LiftGraph getLiftGraph() {
      return this.liftGraph;
   }

   public OptimumLiftGraph setLiftGraph(@Property("liftGraph") LiftGraph liftGraph) {
      this.liftGraph = liftGraph;
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
            status = PMMLObject.traverse(visitor, (Visitable)this.getLiftGraph());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
