package org.datanucleus.api.jdo;

import javax.jdo.listener.InstanceLifecycleEvent;

public class FieldInstanceLifecycleEvent extends InstanceLifecycleEvent {
   private static final long serialVersionUID = 4518746566556032678L;
   private String[] fieldNames;

   public FieldInstanceLifecycleEvent(Object obj, int eventType, Object otherObj, String[] fieldNames) {
      super(obj, eventType, otherObj);
      this.fieldNames = fieldNames;
   }

   public String[] getFieldNames() {
      return this.fieldNames;
   }
}
