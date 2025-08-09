package org.datanucleus.metadata;

import java.util.HashMap;
import java.util.Map;

public class EventListenerMetaData extends MetaData {
   private static final long serialVersionUID = 6816110137508487523L;
   String className;
   Map methodNamesByCallbackName = new HashMap();

   public EventListenerMetaData(String className) {
      this.className = className;
   }

   public String getClassName() {
      return this.className;
   }

   public void addCallback(String callbackClassName, String methodName) {
      this.addCallback(callbackClassName, this.className, methodName);
   }

   public void addCallback(String callbackClassName, String className, String methodName) {
      if (this.methodNamesByCallbackName == null) {
         this.methodNamesByCallbackName = new HashMap();
      }

      if (this.methodNamesByCallbackName.get(callbackClassName) == null) {
         this.methodNamesByCallbackName.put(callbackClassName, className + '.' + methodName);
      }
   }

   public String getMethodNameForCallbackClass(String callbackClassName) {
      return (String)this.methodNamesByCallbackName.get(callbackClassName);
   }
}
