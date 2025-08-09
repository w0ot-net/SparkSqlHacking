package org.sparkproject.dmg.pmml.text;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public TextModelNormalization createTextModelNormalization() {
      return new TextModelNormalization();
   }

   public TextModelSimiliarity createTextModelSimiliarity() {
      return new TextModelSimiliarity();
   }

   public TextModel createTextModel() {
      return new TextModel();
   }

   public TextDictionary createTextDictionary() {
      return new TextDictionary();
   }

   public TextCorpus createTextCorpus() {
      return new TextCorpus();
   }

   public TextDocument createTextDocument() {
      return new TextDocument();
   }

   public DocumentTermMatrix createDocumentTermMatrix() {
      return new DocumentTermMatrix();
   }
}
