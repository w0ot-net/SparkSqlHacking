package org.apache.commons.codec.language.bm;

import org.apache.commons.codec.CharEncoding;

final class ResourceConstants {
   static final String CMT = "//";
   static final String ENCODING;
   static final String EXT_CMT_END = "*/";
   static final String EXT_CMT_START = "/*";

   static {
      ENCODING = CharEncoding.UTF_8;
   }
}
