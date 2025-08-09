package org.datanucleus.state;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class FetchPlanState {
   protected List memberNames = new ArrayList();

   public void addMemberName(String memberName) {
      this.memberNames.add(memberName);
   }

   public void removeLatestMemberName() {
      this.memberNames.remove(this.memberNames.size() - 1);
   }

   public int getCurrentFetchDepth() {
      return this.memberNames.size();
   }

   public int getObjectDepthForType(String memberName) {
      return calculateObjectDepthForMember(this.memberNames, memberName);
   }

   protected static int calculateObjectDepthForMember(List memberNames, String memberName) {
      ListIterator iter = memberNames.listIterator(memberNames.size());

      int number;
      for(number = 0; iter.hasPrevious(); ++number) {
         String field = (String)iter.previous();
         if (!field.equals(memberName)) {
            break;
         }
      }

      return number;
   }
}
