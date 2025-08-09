package org.glassfish.jaxb.runtime.v2.runtime.property;

import org.glassfish.jaxb.runtime.v2.runtime.JaxBeanInfo;
import org.glassfish.jaxb.runtime.v2.runtime.Name;

class TagAndType {
   final Name tagName;
   final JaxBeanInfo beanInfo;

   TagAndType(Name tagName, JaxBeanInfo beanInfo) {
      this.tagName = tagName;
      this.beanInfo = beanInfo;
   }
}
