package org.apache.derby.impl.sql.execute;

public class TriggerEvents {
   public static final TriggerEvent BEFORE_INSERT = new TriggerEvent(0);
   public static final TriggerEvent BEFORE_DELETE = new TriggerEvent(1);
   public static final TriggerEvent BEFORE_UPDATE = new TriggerEvent(2);
   public static final TriggerEvent AFTER_INSERT = new TriggerEvent(3);
   public static final TriggerEvent AFTER_DELETE = new TriggerEvent(4);
   public static final TriggerEvent AFTER_UPDATE = new TriggerEvent(5);
}
