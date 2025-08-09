package org.sparkproject.jetty.util;

import java.util.HashSet;

public class AsciiLowerCaseSet extends HashSet {
   public boolean add(String s) {
      return super.add(s == null ? null : StringUtil.asciiToLowerCase(s));
   }

   public boolean contains(Object o) {
      return o instanceof String ? super.contains(StringUtil.asciiToLowerCase((String)o)) : super.contains(o);
   }
}
