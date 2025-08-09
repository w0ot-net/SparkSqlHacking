package io.vertx.core.dns.impl;

import io.vertx.core.dns.MxRecord;

public final class MxRecordImpl implements MxRecord, Comparable {
   private final int priority;
   private final String name;

   public MxRecordImpl(int priority, String name) {
      this.priority = priority;
      this.name = name;
   }

   public int priority() {
      return this.priority;
   }

   public String name() {
      return this.name;
   }

   public String toString() {
      return this.priority() + " " + this.name();
   }

   public int compareTo(MxRecord o) {
      return Integer.valueOf(this.priority()).compareTo(o.priority());
   }
}
