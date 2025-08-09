package org.glassfish.jersey.message.internal;

import java.text.ParseException;

public class AcceptableLanguageTag extends LanguageTag implements Qualified {
   private final int quality;

   public AcceptableLanguageTag(String primaryTag, String subTags) {
      super(primaryTag, subTags);
      this.quality = 1000;
   }

   public AcceptableLanguageTag(String header) throws ParseException {
      this(HttpHeaderReader.newInstance(header));
   }

   public AcceptableLanguageTag(HttpHeaderReader reader) throws ParseException {
      reader.hasNext();
      this.tag = reader.nextToken().toString();
      if (!this.tag.equals("*")) {
         this.parse(this.tag);
      } else {
         this.primaryTag = this.tag;
      }

      if (reader.hasNext()) {
         this.quality = HttpHeaderReader.readQualityFactorParameter(reader);
      } else {
         this.quality = 1000;
      }

   }

   public int getQuality() {
      return this.quality;
   }

   public boolean equals(Object obj) {
      if (!super.equals(obj)) {
         return false;
      } else {
         AcceptableLanguageTag other = (AcceptableLanguageTag)obj;
         return this.quality == other.quality;
      }
   }

   public int hashCode() {
      int hash = super.hashCode();
      hash = 47 * hash + this.quality;
      return hash;
   }
}
