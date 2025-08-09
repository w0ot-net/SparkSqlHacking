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
   name = "ROCGraph",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "xCoordinates", "yCoordinates", "boundaryValues"}
)
@JsonRootName("ROCGraph")
@JsonPropertyOrder({"extensions", "xCoordinates", "yCoordinates", "boundaryValues"})
@Added(Version.PMML_4_0)
public class ROCGraph extends PMMLObject implements HasExtensions {
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "XCoordinates",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("XCoordinates")
   private XCoordinates xCoordinates;
   @XmlElement(
      name = "YCoordinates",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("YCoordinates")
   private YCoordinates yCoordinates;
   @XmlElement(
      name = "BoundaryValues",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("BoundaryValues")
   private BoundaryValues boundaryValues;
   private static final long serialVersionUID = 67371272L;

   public ROCGraph() {
   }

   @ValueConstructor
   public ROCGraph(@Property("xCoordinates") XCoordinates xCoordinates, @Property("yCoordinates") YCoordinates yCoordinates) {
      this.xCoordinates = xCoordinates;
      this.yCoordinates = yCoordinates;
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

   public ROCGraph addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public XCoordinates requireXCoordinates() {
      if (this.xCoordinates == null) {
         throw new MissingElementException(this, PMMLElements.ROCGRAPH_XCOORDINATES);
      } else {
         return this.xCoordinates;
      }
   }

   public XCoordinates getXCoordinates() {
      return this.xCoordinates;
   }

   public ROCGraph setXCoordinates(@Property("xCoordinates") XCoordinates xCoordinates) {
      this.xCoordinates = xCoordinates;
      return this;
   }

   public YCoordinates requireYCoordinates() {
      if (this.yCoordinates == null) {
         throw new MissingElementException(this, PMMLElements.ROCGRAPH_YCOORDINATES);
      } else {
         return this.yCoordinates;
      }
   }

   public YCoordinates getYCoordinates() {
      return this.yCoordinates;
   }

   public ROCGraph setYCoordinates(@Property("yCoordinates") YCoordinates yCoordinates) {
      this.yCoordinates = yCoordinates;
      return this;
   }

   public BoundaryValues getBoundaryValues() {
      return this.boundaryValues;
   }

   public ROCGraph setBoundaryValues(@Property("boundaryValues") BoundaryValues boundaryValues) {
      this.boundaryValues = boundaryValues;
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
            status = PMMLObject.traverse(visitor, this.getXCoordinates(), this.getYCoordinates(), this.getBoundaryValues());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
