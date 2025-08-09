package org.sparkproject.dmg.pmml.nearest_neighbor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.Version;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "KNNInputs",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "knnInputs"}
)
@JsonRootName("KNNInputs")
@JsonPropertyOrder({"extensions", "knnInputs"})
@Added(Version.PMML_4_1)
public class KNNInputs extends PMMLObject implements Iterable, HasExtensions {
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "KNNInput",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("KNNInput")
   @CollectionElementType(KNNInput.class)
   private List knnInputs;
   private static final long serialVersionUID = 67371272L;

   public KNNInputs() {
   }

   @ValueConstructor
   public KNNInputs(@Property("knnInputs") List knnInputs) {
      this.knnInputs = knnInputs;
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

   public KNNInputs addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Iterator iterator() {
      return this.requireKNNInputs().iterator();
   }

   public boolean hasKNNInputs() {
      return this.knnInputs != null && !this.knnInputs.isEmpty();
   }

   public List requireKNNInputs() {
      if (this.knnInputs != null && !this.knnInputs.isEmpty()) {
         return this.knnInputs;
      } else {
         throw new MissingElementException(this, PMMLElements.KNNINPUTS_KNNINPUTS);
      }
   }

   public List getKNNInputs() {
      if (this.knnInputs == null) {
         this.knnInputs = new ArrayList();
      }

      return this.knnInputs;
   }

   public KNNInputs addKNNInputs(KNNInput... knnInputs) {
      this.getKNNInputs().addAll(Arrays.asList(knnInputs));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasKNNInputs()) {
            status = PMMLObject.traverse(visitor, this.getKNNInputs());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
