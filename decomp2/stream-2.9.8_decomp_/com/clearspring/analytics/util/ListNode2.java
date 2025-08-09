package com.clearspring.analytics.util;

public class ListNode2 {
   protected Object value;
   protected ListNode2 prev;
   protected ListNode2 next;

   public ListNode2(Object value) {
      this.value = value;
   }

   public ListNode2 getPrev() {
      return this.prev;
   }

   public ListNode2 getNext() {
      return this.next;
   }

   public Object getValue() {
      return this.value;
   }

   public void setValue(Object value) {
      this.value = value;
   }
}
