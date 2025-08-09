package com.facebook.fb303;

import java.util.AbstractMap;
import java.util.concurrent.ConcurrentHashMap;

public abstract class FacebookBase implements FacebookService.Iface {
   private String name_;
   private long alive_;
   private final ConcurrentHashMap counters_ = new ConcurrentHashMap();
   private final ConcurrentHashMap options_ = new ConcurrentHashMap();

   protected FacebookBase(String name) {
      this.name_ = name;
      this.alive_ = System.currentTimeMillis() / 1000L;
   }

   public String getName() {
      return this.name_;
   }

   public abstract fb_status getStatus();

   public String getStatusDetails() {
      return "";
   }

   public void deleteCounter(String key) {
      this.counters_.remove(key);
   }

   public void resetCounter(String key) {
      this.counters_.put(key, 0L);
   }

   public long incrementCounter(String key) {
      long val = this.getCounter(key) + 1L;
      this.counters_.put(key, val);
      return val;
   }

   public long incrementCounter(String key, long increment) {
      long val = this.getCounter(key) + increment;
      this.counters_.put(key, val);
      return val;
   }

   public long setCounter(String key, long value) {
      this.counters_.put(key, value);
      return value;
   }

   public AbstractMap getCounters() {
      return this.counters_;
   }

   public long getCounter(String key) {
      Long val = (Long)this.counters_.get(key);
      return val == null ? 0L : val;
   }

   public void setOption(String key, String value) {
      this.options_.put(key, value);
   }

   public String getOption(String key) {
      return (String)this.options_.get(key);
   }

   public AbstractMap getOptions() {
      return this.options_;
   }

   public long aliveSince() {
      return this.alive_;
   }

   public String getCpuProfile() {
      return "";
   }

   public void reinitialize() {
   }

   public void shutdown() {
   }
}
