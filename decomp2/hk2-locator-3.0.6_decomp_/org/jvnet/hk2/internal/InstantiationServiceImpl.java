package org.jvnet.hk2.internal;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import org.glassfish.hk2.api.DescriptorVisibility;
import org.glassfish.hk2.api.Injectee;
import org.glassfish.hk2.api.InstantiationData;
import org.glassfish.hk2.api.InstantiationService;
import org.glassfish.hk2.api.Visibility;

@Visibility(DescriptorVisibility.LOCAL)
public class InstantiationServiceImpl implements InstantiationService {
   private final HashMap injecteeStack = new HashMap();

   public synchronized InstantiationData getInstantiationData() {
      long tid = Thread.currentThread().getId();
      LinkedList<Injectee> threadStack = (LinkedList)this.injecteeStack.get(tid);
      if (threadStack == null) {
         return null;
      } else if (threadStack.isEmpty()) {
         return null;
      } else {
         final Injectee head = (Injectee)threadStack.getLast();
         return new InstantiationData() {
            public Injectee getParentInjectee() {
               return head;
            }

            public String toString() {
               Injectee var10000 = head;
               return "InstantiationData(" + var10000 + "," + System.identityHashCode(this) + ")";
            }
         };
      }
   }

   public synchronized void pushInjecteeParent(Injectee injectee) {
      long tid = Thread.currentThread().getId();
      LinkedList<Injectee> threadStack = (LinkedList)this.injecteeStack.get(tid);
      if (threadStack == null) {
         threadStack = new LinkedList();
         this.injecteeStack.put(tid, threadStack);
      }

      threadStack.addLast(injectee);
   }

   public synchronized void popInjecteeParent() {
      long tid = Thread.currentThread().getId();
      LinkedList<Injectee> threadStack = (LinkedList)this.injecteeStack.get(tid);
      if (threadStack != null) {
         threadStack.removeLast();
         if (threadStack.isEmpty()) {
            this.injecteeStack.remove(tid);
         }

      }
   }

   public String toString() {
      Set var10000 = this.injecteeStack.keySet();
      return "InstantiationServiceImpl(" + var10000 + "," + System.identityHashCode(this) + ")";
   }
}
