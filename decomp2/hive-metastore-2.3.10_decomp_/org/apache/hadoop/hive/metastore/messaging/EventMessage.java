package org.apache.hadoop.hive.metastore.messaging;

public abstract class EventMessage {
   protected EventType eventType;

   protected EventMessage(EventType eventType) {
      this.eventType = eventType;
   }

   public EventType getEventType() {
      return this.eventType;
   }

   public abstract String getServer();

   public abstract String getServicePrincipal();

   public abstract String getDB();

   public abstract Long getTimestamp();

   public EventMessage checkValid() {
      if (this.getServer() != null && this.getServicePrincipal() != null) {
         if (this.getEventType() == null) {
            throw new IllegalStateException("Event-type unset.");
         } else if (this.getDB() == null) {
            throw new IllegalArgumentException("DB-name unset.");
         } else {
            return this;
         }
      } else {
         throw new IllegalStateException("Server-URL/Service-Principal shouldn't be null.");
      }
   }

   public static enum EventType {
      CREATE_DATABASE("CREATE_DATABASE"),
      DROP_DATABASE("DROP_DATABASE"),
      CREATE_TABLE("CREATE_TABLE"),
      DROP_TABLE("DROP_TABLE"),
      ADD_PARTITION("ADD_PARTITION"),
      DROP_PARTITION("DROP_PARTITION"),
      ALTER_TABLE("ALTER_TABLE"),
      ALTER_PARTITION("ALTER_PARTITION"),
      INSERT("INSERT"),
      CREATE_FUNCTION("CREATE_FUNCTION"),
      DROP_FUNCTION("DROP_FUNCTION"),
      CREATE_INDEX("CREATE_INDEX"),
      DROP_INDEX("DROP_INDEX"),
      ALTER_INDEX("ALTER_INDEX");

      private String typeString;

      private EventType(String typeString) {
         this.typeString = typeString;
      }

      public String toString() {
         return this.typeString;
      }
   }
}
