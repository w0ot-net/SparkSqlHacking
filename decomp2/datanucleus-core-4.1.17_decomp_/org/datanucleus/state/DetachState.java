package org.datanucleus.state;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.util.StringUtils;

public class DetachState extends FetchPlanState {
   private Map detachedObjectById = new HashMap();
   private ApiAdapter api;

   public DetachState(ApiAdapter api) {
      this.api = api;
   }

   public void setDetachedCopyEntry(Object pc, Object detachedPC) {
      this.detachedObjectById.put(this.getKey(pc), new Entry(detachedPC));
   }

   public Entry getDetachedCopyEntry(Object pc) {
      return (Entry)this.detachedObjectById.get(this.getKey(pc));
   }

   private Object getKey(Object pc) {
      Object id = this.api.getIdForObject(pc);
      return id == null ? StringUtils.toJVMIDString(pc) : id;
   }

   public class Entry {
      private Object detachedPC;
      private List detachStates = new LinkedList();

      Entry(Object detachedPC) {
         this.detachedPC = detachedPC;
         this.detachStates.add(this.getCurrentState());
      }

      public Object getDetachedCopyObject() {
         return this.detachedPC;
      }

      public boolean checkCurrentState() {
         List<String> currentState = this.getCurrentState();
         Iterator<List<String>> iter = this.detachStates.iterator();

         while(iter.hasNext()) {
            List<String> detachState = (List)iter.next();
            if (this.dominates(detachState, currentState)) {
               return true;
            }

            if (this.dominates(currentState, detachState)) {
               iter.remove();
            }
         }

         this.detachStates.add(currentState);
         return false;
      }

      private List getCurrentState() {
         return new ArrayList(DetachState.this.memberNames);
      }

      private boolean dominates(List candidate, List target) {
         if (candidate.size() == 0) {
            return true;
         } else if (candidate.size() > target.size()) {
            return false;
         } else {
            String fieldName = (String)target.get(target.size() - 1);
            return FetchPlanState.calculateObjectDepthForMember(candidate, fieldName) <= FetchPlanState.calculateObjectDepthForMember(target, fieldName);
         }
      }
   }
}
