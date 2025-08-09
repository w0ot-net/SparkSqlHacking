package org.datanucleus.state;

public class ActivityState {
   public static final ActivityState NONE = new ActivityState(0);
   public static final ActivityState INSERTING = new ActivityState(1);
   public static final ActivityState INSERTING_CALLBACKS = new ActivityState(2);
   public static final ActivityState DELETING = new ActivityState(3);
   private final int typeId;

   private ActivityState(int i) {
      this.typeId = i;
   }

   public int hashCode() {
      return this.typeId;
   }

   public boolean equals(Object o) {
      if (o instanceof ActivityState) {
         return ((ActivityState)o).typeId == this.typeId;
      } else {
         return false;
      }
   }

   public String toString() {
      switch (this.typeId) {
         case 0:
            return "none";
         case 1:
            return "inserting";
         case 2:
            return "inserting-callback";
         case 3:
            return "deleting";
         default:
            return "";
      }
   }

   public int getType() {
      return this.typeId;
   }

   public static ActivityState getActivityState(String value) {
      if (value == null) {
         return NONE;
      } else if (NONE.toString().equals(value)) {
         return NONE;
      } else if (INSERTING.toString().equals(value)) {
         return INSERTING;
      } else if (INSERTING_CALLBACKS.toString().equals(value)) {
         return INSERTING_CALLBACKS;
      } else {
         return DELETING.toString().equals(value) ? DELETING : NONE;
      }
   }
}
