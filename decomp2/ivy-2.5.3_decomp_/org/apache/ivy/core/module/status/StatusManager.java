package org.apache.ivy.core.module.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ivy.core.IvyContext;
import org.apache.ivy.util.Message;

public class StatusManager {
   private final List statuses = new ArrayList();
   private String defaultStatus;
   private Map statusPriorityMap;
   private Map statusIntegrationMap;
   private String deliveryStatusListString;

   public static StatusManager newDefaultInstance() {
      return new StatusManager(new Status[]{new Status("release", false), new Status("milestone", false), new Status("integration", true)}, "integration");
   }

   public static StatusManager getCurrent() {
      return IvyContext.getContext().getSettings().getStatusManager();
   }

   public StatusManager(Status[] status, String defaultStatus) {
      this.statuses.addAll(Arrays.asList(status));
      this.defaultStatus = defaultStatus;
      this.computeMaps();
   }

   public StatusManager() {
   }

   public void addStatus(Status status) {
      this.statuses.add(status);
   }

   public void setDefaultStatus(String defaultStatus) {
      this.defaultStatus = defaultStatus;
   }

   public List getStatuses() {
      return this.statuses;
   }

   private void computeMaps() {
      if (this.statuses.isEmpty()) {
         throw new IllegalStateException("badly configured statuses: none found");
      } else {
         this.statusPriorityMap = new HashMap();

         for(Status status : this.statuses) {
            this.statusPriorityMap.put(status.getName(), this.statuses.indexOf(status));
         }

         this.statusIntegrationMap = new HashMap();

         for(Status status : this.statuses) {
            this.statusIntegrationMap.put(status.getName(), status.isIntegration());
         }

      }
   }

   public boolean isStatus(String status) {
      if (this.statusPriorityMap == null) {
         this.computeMaps();
      }

      return this.statusPriorityMap.containsKey(status);
   }

   public int getPriority(String status) {
      if (this.statusPriorityMap == null) {
         this.computeMaps();
      }

      Integer priority = (Integer)this.statusPriorityMap.get(status);
      if (priority == null) {
         Message.debug("unknown status " + status + ": assuming lowest priority");
         return Integer.MAX_VALUE;
      } else {
         return priority;
      }
   }

   public boolean isIntegration(String status) {
      if (this.statusIntegrationMap == null) {
         this.computeMaps();
      }

      Boolean isIntegration = (Boolean)this.statusIntegrationMap.get(status);
      if (isIntegration == null) {
         Message.debug("unknown status " + status + ": assuming integration");
         return true;
      } else {
         return isIntegration;
      }
   }

   public String getDeliveryStatusListString() {
      if (this.deliveryStatusListString == null) {
         StringBuilder ret = new StringBuilder();

         for(Status status : this.statuses) {
            if (!status.isIntegration()) {
               ret.append(status.getName()).append(",");
            }
         }

         if (ret.length() > 0) {
            ret.deleteCharAt(ret.length() - 1);
         }

         this.deliveryStatusListString = ret.toString();
      }

      return this.deliveryStatusListString;
   }

   public String getDefaultStatus() {
      if (this.defaultStatus == null) {
         if (this.statuses.isEmpty()) {
            throw new IllegalStateException("badly configured statuses: none found");
         }

         this.defaultStatus = ((Status)this.statuses.get(this.statuses.size() - 1)).getName();
      }

      return this.defaultStatus;
   }
}
