package org.apache.log4j.or;

class DefaultRenderer implements ObjectRenderer {
   public String doRender(final Object o) {
      try {
         return o.toString();
      } catch (Exception ex) {
         return ex.toString();
      }
   }
}
