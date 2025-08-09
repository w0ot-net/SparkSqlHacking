package org.sparkproject.dmg.pmml.text;

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
import org.sparkproject.dmg.pmml.Extension;
import org.sparkproject.dmg.pmml.HasExtensions;
import org.sparkproject.dmg.pmml.PMMLObject;
import org.sparkproject.dmg.pmml.StringValue;
import org.sparkproject.dmg.pmml.Visitor;
import org.sparkproject.dmg.pmml.VisitorAction;
import org.sparkproject.jpmml.model.annotations.CollectionElementType;
import org.sparkproject.jpmml.model.annotations.Property;

@XmlRootElement(
   name = "TextModelNormalization",
   namespace = "http://www.dmg.org/PMML-4_4"
)
@XmlType(
   name = "",
   propOrder = {"extensions"}
)
@JsonRootName("TextModelNormalization")
@JsonPropertyOrder({"localTermWeights", "globalTermWeights", "documentNormalization", "extensions"})
public class TextModelNormalization extends PMMLObject implements HasExtensions {
   @XmlAttribute(
      name = "localTermWeights"
   )
   @JsonProperty("localTermWeights")
   private LocalTermWeights localTermWeights;
   @XmlAttribute(
      name = "globalTermWeights"
   )
   @JsonProperty("globalTermWeights")
   private GlobalTermWeights globalTermWeights;
   @XmlAttribute(
      name = "documentNormalization"
   )
   @JsonProperty("documentNormalization")
   private DocumentNormalization documentNormalization;
   @XmlElement(
      name = "Extension",
      namespace = "http://www.dmg.org/PMML-4_4"
   )
   @JsonProperty("Extension")
   @CollectionElementType(Extension.class)
   private List extensions;
   private static final long serialVersionUID = 67371272L;

   public LocalTermWeights getLocalTermWeights() {
      return this.localTermWeights == null ? TextModelNormalization.LocalTermWeights.TERM_FREQUENCY : this.localTermWeights;
   }

   public TextModelNormalization setLocalTermWeights(@Property("localTermWeights") LocalTermWeights localTermWeights) {
      this.localTermWeights = localTermWeights;
      return this;
   }

   public GlobalTermWeights getGlobalTermWeights() {
      return this.globalTermWeights == null ? TextModelNormalization.GlobalTermWeights.INVERSE_DOCUMENT_FREQUENCY : this.globalTermWeights;
   }

   public TextModelNormalization setGlobalTermWeights(@Property("globalTermWeights") GlobalTermWeights globalTermWeights) {
      this.globalTermWeights = globalTermWeights;
      return this;
   }

   public DocumentNormalization getDocumentNormalization() {
      return this.documentNormalization == null ? TextModelNormalization.DocumentNormalization.NONE : this.documentNormalization;
   }

   public TextModelNormalization setDocumentNormalization(@Property("documentNormalization") DocumentNormalization documentNormalization) {
      this.documentNormalization = documentNormalization;
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

   public TextModelNormalization addExtensions(Extension... extensions) {
      this.getExtensions().addAll(Arrays.asList(extensions));
      return this;
   }

   public VisitorAction accept(Visitor visitor) {
      VisitorAction status = visitor.visit(this);
      if (status == VisitorAction.CONTINUE) {
         visitor.pushParent(this);
         if (status == VisitorAction.CONTINUE && this.hasExtensions()) {
            status = PMMLObject.traverse(visitor, this.getExtensions());
         }

         visitor.popParent();
      }

      return status == VisitorAction.TERMINATE ? VisitorAction.TERMINATE : VisitorAction.CONTINUE;
   }

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum DocumentNormalization implements StringValue {
      @XmlEnumValue("none")
      @JsonProperty("none")
      NONE("none"),
      @XmlEnumValue("cosine")
      @JsonProperty("cosine")
      COSINE("cosine");

      private final String value;

      private DocumentNormalization(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static DocumentNormalization fromValue(String v) {
         for(DocumentNormalization c : values()) {
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

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum GlobalTermWeights implements StringValue {
      @XmlEnumValue("inverseDocumentFrequency")
      @JsonProperty("inverseDocumentFrequency")
      INVERSE_DOCUMENT_FREQUENCY("inverseDocumentFrequency"),
      @XmlEnumValue("none")
      @JsonProperty("none")
      NONE("none"),
      @JsonProperty("GFIDF")
      GFIDF("GFIDF"),
      @XmlEnumValue("normal")
      @JsonProperty("normal")
      NORMAL("normal"),
      @XmlEnumValue("probabilisticInverse")
      @JsonProperty("probabilisticInverse")
      PROBABILISTIC_INVERSE("probabilisticInverse");

      private final String value;

      private GlobalTermWeights(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static GlobalTermWeights fromValue(String v) {
         for(GlobalTermWeights c : values()) {
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

   @XmlType(
      name = ""
   )
   @XmlEnum
   public static enum LocalTermWeights implements StringValue {
      @XmlEnumValue("termFrequency")
      @JsonProperty("termFrequency")
      TERM_FREQUENCY("termFrequency"),
      @XmlEnumValue("binary")
      @JsonProperty("binary")
      BINARY("binary"),
      @XmlEnumValue("logarithmic")
      @JsonProperty("logarithmic")
      LOGARITHMIC("logarithmic"),
      @XmlEnumValue("augmentedNormalizedTermFrequency")
      @JsonProperty("augmentedNormalizedTermFrequency")
      AUGMENTED_NORMALIZED_TERM_FREQUENCY("augmentedNormalizedTermFrequency");

      private final String value;

      private LocalTermWeights(String v) {
         this.value = v;
      }

      public String value() {
         return this.value;
      }

      public static LocalTermWeights fromValue(String v) {
         for(LocalTermWeights c : values()) {
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
