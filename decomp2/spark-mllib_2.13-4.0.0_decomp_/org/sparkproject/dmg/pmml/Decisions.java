package org.sparkproject.dmg.pmml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.sparkproject.jpmml.model.MissingElementException;
import org.sparkproject.jpmml.model.annotations.Added;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;
import org.sparkproject.jpmml.model.annotations.ValueConstructor;

@XmlRootElement(
   name = "Decisions",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions", "decisions"}
)
@JsonRootName("Decisions")
@JsonPropertyOrder({"businessProblem", "description", "extensions", "decisions"})
@Added(Version.PMML_4_1)
public class Decisions extends PMMLObject implements Iterable, HasExtensions {
   @XmlAttribute(
      name = "businessProblem"
   )
   @JsonProperty("businessProblem")
   private String businessProblem;
   @XmlAttribute(
      name = "description"
   )
   @JsonProperty("description")
   private String description;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   @XmlElement(
      name = "Decision",
      namespace = "http://www.dmg.org/PMML-4_4",
      required = true
   )
   @JsonProperty("Decision")
   @CollectionElementType(Decision.class)
   private List decisions;
   private static final long serialVersionUID = 67371272L;

   public Decisions() {
   }

   @ValueConstructor
   public Decisions(@Property("decisions") List decisions) {
      this.decisions = decisions;
   }

   public String getBusinessProblem() {
      return this.businessProblem;
   }

   public Decisions setBusinessProblem(@Property("businessProblem") String businessProblem) {
      this.businessProblem = businessProblem;
      return this;
   }

   public String getDescription() {
      return this.description;
   }

   public Decisions setDescription(@Property("description") String description) {
      this.description = description;
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

   public Decisions addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public Iterator iterator() {
      return this.requireDecisions().iterator();
   }

   public boolean hasDecisions() {
      return this.decisions != null && !this.decisions.isEmpty();
   }

   public List requireDecisions() {
      if (this.decisions != null && !this.decisions.isEmpty()) {
         return this.decisions;
      } else {
         throw new MissingElementException(this, PMMLElements.DECISIONS_DECISIONS);
      }
   }

   public List getDecisions() {
      if (this.decisions == null) {
         this.decisions = new ArrayList();
      }

      return this.decisions;
   }

   public Decisions addDecisions(Decision... decisions) {
      this.getDecisions().addAll(Arrays.asList(decisions));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         if (status == VisitorAction.CONTINUE && this.hasDecisions()) {
            status = PMMLObject.traverse(visitor, this.getDecisions());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }
}
