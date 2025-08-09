package org.glassfish.jersey.message.internal;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Variant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class VariantListBuilder extends Variant.VariantListBuilder {
   private List variants;
   private final List mediaTypes = new ArrayList();
   private final List languages = new ArrayList();
   private final List encodings = new ArrayList();

   public List build() {
      if (!this.mediaTypes.isEmpty() || !this.languages.isEmpty() || !this.encodings.isEmpty()) {
         this.add();
      }

      if (this.variants == null) {
         this.variants = new ArrayList();
      }

      return this.variants;
   }

   public VariantListBuilder add() {
      if (this.variants == null) {
         this.variants = new ArrayList();
      }

      this.addMediaTypes();
      this.languages.clear();
      this.encodings.clear();
      this.mediaTypes.clear();
      return this;
   }

   private void addMediaTypes() {
      if (this.mediaTypes.isEmpty()) {
         this.addLanguages((MediaType)null);
      } else {
         for(MediaType mediaType : this.mediaTypes) {
            this.addLanguages(mediaType);
         }
      }

   }

   private void addLanguages(MediaType mediaType) {
      if (this.languages.isEmpty()) {
         this.addEncodings(mediaType, (Locale)null);
      } else {
         for(Locale language : this.languages) {
            this.addEncodings(mediaType, language);
         }
      }

   }

   private void addEncodings(MediaType mediaType, Locale language) {
      if (this.encodings.isEmpty()) {
         this.addVariant(mediaType, language, (String)null);
      } else {
         for(String encoding : this.encodings) {
            this.addVariant(mediaType, language, encoding);
         }
      }

   }

   private void addVariant(MediaType mediaType, Locale language, String encoding) {
      this.variants.add(new Variant(mediaType, language, encoding));
   }

   public VariantListBuilder languages(Locale... languages) {
      this.languages.addAll(Arrays.asList(languages));
      return this;
   }

   public VariantListBuilder encodings(String... encodings) {
      this.encodings.addAll(Arrays.asList(encodings));
      return this;
   }

   public VariantListBuilder mediaTypes(MediaType... mediaTypes) {
      this.mediaTypes.addAll(Arrays.asList(mediaTypes));
      return this;
   }
}
