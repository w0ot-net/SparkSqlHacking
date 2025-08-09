package org.sparkproject.dmg.pmml.naive_bayes;

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
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "BayesInputs",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "bayesInputs"}
)
@JsonRootName("BayesInputs")
@JsonPropertyOrder({"extensions", "bayesInputs"})
public class BayesInputs extends PMMLObject implements Iterable, HasExtensions {
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "BayesInput",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("BayesInput")
   @CollectionElementType(BayesInput.class)
   private List bayesInputs;
   private static final long serialVersionUID = 67371272L;

   public BayesInputs() {
   }

   @ValueConstructor
   public BayesInputs(@Property("bayesInputs") List bayesInputs) {
      this.bayesInputs = bayesInputs;
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

   public BayesInputs addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Iterator iterator() {
      return this.requireBayesInputs().iterator();
   }

   public boolean hasBayesInputs() {
      return this.bayesInputs != null && !this.bayesInputs.isEmpty();
   }

   public List requireBayesInputs() {
      if (this.bayesInputs != null && !this.bayesInputs.isEmpty()) {
         return this.bayesInputs;
      } else {
         throw new MissingElementException(this, PMMLElements.BAYESINPUTS_BAYESINPUTS);
      }
   }

   public List getBayesInputs() {
      if (this.bayesInputs == null) {
         this.bayesInputs = new ArrayList();
      }

      return this.bayesInputs;
   }

   public BayesInputs addBayesInputs(BayesInput... bayesInputs) {
      this.getBayesInputs().addAll(Arrays.asList(bayesInputs));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasBayesInputs()) {
            status = PMMLObject.traverse(visitor, this.getBayesInputs());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
