package org.apache.commons.text.lookup;

import org.apache.commons.text.StringEscapeUtils;

final class XmlDecoderStringLookup extends AbstractStringLookup {
   static final XmlDecoderStringLookup INSTANCE = new XmlDecoderStringLookup();

   private XmlDecoderStringLookup() {
   }

   public String lookup(String key) {
      return StringEscapeUtils.unescapeXml(key);
   }
}
