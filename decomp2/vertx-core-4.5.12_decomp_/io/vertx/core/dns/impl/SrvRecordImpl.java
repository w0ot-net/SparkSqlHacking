package io.vertx.core.dns.impl;

import io.vertx.core.dns.SrvRecord;

public final class SrvRecordImpl implements SrvRecord, Comparable {
   private final int priority;
   private final int weight;
   private final int port;
   private final String name;
   private final String protocol;
   private final String service;
   private final String target;

   public SrvRecordImpl(int priority, int weight, int port, String name, String protocol, String service, String target) {
      this.priority = priority;
      this.weight = weight;
      this.port = port;
      this.name = name;
      this.protocol = protocol;
      this.service = service;
      this.target = target;
   }

   public int priority() {
      return this.priority;
   }

   public int weight() {
      return this.weight;
   }

   public int port() {
      return this.port;
   }

   public String name() {
      return this.name;
   }

   public String protocol() {
      return this.protocol;
   }

   public String service() {
      return this.service;
   }

   public String target() {
      return this.target;
   }

   public int compareTo(SrvRecord o) {
      return Integer.valueOf(this.priority()).compareTo(o.priority());
   }
}
