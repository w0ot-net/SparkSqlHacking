package org.sparkproject.jetty.http;

import java.util.Objects;
import org.sparkproject.jetty.util.QuotedStringTokenizer;
import org.sparkproject.jetty.util.StringUtil;

public class CompressedContentFormat {
   public static final String ETAG_SEPARATOR = System.getProperty(CompressedContentFormat.class.getName() + ".ETAG_SEPARATOR", "--");
   public static final CompressedContentFormat GZIP = new CompressedContentFormat("gzip", ".gz");
   public static final CompressedContentFormat BR = new CompressedContentFormat("br", ".br");
   public static final CompressedContentFormat[] NONE = new CompressedContentFormat[0];
   private final String _encoding;
   private final String _extension;
   private final String _etagSuffix;
   private final String _etagSuffixQuote;
   private final PreEncodedHttpField _contentEncoding;

   public CompressedContentFormat(String encoding, String extension) {
      this._encoding = StringUtil.asciiToLowerCase(encoding);
      this._extension = StringUtil.asciiToLowerCase(extension);
      this._etagSuffix = StringUtil.isEmpty(ETAG_SEPARATOR) ? "" : ETAG_SEPARATOR + this._encoding;
      this._etagSuffixQuote = this._etagSuffix + "\"";
      this._contentEncoding = new PreEncodedHttpField(HttpHeader.CONTENT_ENCODING, this._encoding);
   }

   public boolean equals(Object o) {
      if (!(o instanceof CompressedContentFormat)) {
         return false;
      } else {
         CompressedContentFormat ccf = (CompressedContentFormat)o;
         return Objects.equals(this._encoding, ccf._encoding) && Objects.equals(this._extension, ccf._extension);
      }
   }

   public String getEncoding() {
      return this._encoding;
   }

   public String getExtension() {
      return this._extension;
   }

   public String getEtagSuffix() {
      return this._etagSuffix;
   }

   public HttpField getContentEncoding() {
      return this._contentEncoding;
   }

   public String etag(String etag) {
      if (StringUtil.isEmpty(ETAG_SEPARATOR)) {
         return etag;
      } else {
         int end = etag.length() - 1;
         if (etag.charAt(end) == '"') {
            String var10000 = etag.substring(0, end);
            return var10000 + this._etagSuffixQuote;
         } else {
            return etag + this._etagSuffix;
         }
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this._encoding, this._extension});
   }

   public static boolean tagEquals(String etag, String etagWithSuffix) {
      if (etag.equals(etagWithSuffix)) {
         return true;
      } else if (StringUtil.isEmpty(ETAG_SEPARATOR)) {
         return false;
      } else {
         boolean etagQuoted = etag.endsWith("\"");
         boolean etagSuffixQuoted = etagWithSuffix.endsWith("\"");
         int separator = etagWithSuffix.lastIndexOf(ETAG_SEPARATOR);
         if (etagQuoted != etagSuffixQuoted) {
            if (!etagWithSuffix.startsWith("W/") && !etag.startsWith("W/")) {
               etag = etagQuoted ? QuotedStringTokenizer.unquote(etag) : etag;
               etagWithSuffix = etagSuffixQuoted ? QuotedStringTokenizer.unquote(etagWithSuffix) : etagWithSuffix;
               separator = etagWithSuffix.lastIndexOf(ETAG_SEPARATOR);
               return separator > 0 ? etag.regionMatches(0, etagWithSuffix, 0, separator) : Objects.equals(etag, etagWithSuffix);
            } else {
               return false;
            }
         } else {
            return separator > 0 && etag.regionMatches(0, etagWithSuffix, 0, separator);
         }
      }
   }

   public String stripSuffixes(String etagsList) {
      if (StringUtil.isEmpty(ETAG_SEPARATOR)) {
         return etagsList;
      } else {
         while(true) {
            int i = etagsList.lastIndexOf(this._etagSuffix);
            if (i < 0) {
               return etagsList;
            }

            String var10000 = etagsList.substring(0, i);
            etagsList = var10000 + etagsList.substring(i + this._etagSuffix.length());
         }
      }
   }

   public String toString() {
      return this._encoding;
   }
}
