package org.apache.commons.text.lookup;

import org.apache.commons.text.StringEscapeUtils;

final class XmlEncoderStringLookup extends AbstractStringLookup {
   static final XmlEncoderStringLookup INSTANCE = new XmlEncoderStringLookup();

   public String lookup(String key) {
      return StringEscapeUtils.escapeXml10(key);
   }
}
