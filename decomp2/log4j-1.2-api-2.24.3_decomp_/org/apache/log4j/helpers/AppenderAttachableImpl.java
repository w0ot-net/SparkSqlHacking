package org.apache.log4j.helpers;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Objects;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.log4j.Appender;
import org.apache.log4j.spi.AppenderAttachable;
import org.apache.log4j.spi.LoggingEvent;

public class AppenderAttachableImpl implements AppenderAttachable {
   private final ConcurrentMap appenders = new ConcurrentHashMap();
   protected Vector appenderList;

   public void addAppender(final Appender appender) {
      if (appender != null) {
         this.appenders.put(Objects.toString(appender.getName()), appender);
      }

   }

   public int appendLoopOnAppenders(final LoggingEvent event) {
      for(Appender appender : this.appenders.values()) {
         appender.doAppend(event);
      }

      return this.appenders.size();
   }

   public void close() {
      for(Appender appender : this.appenders.values()) {
         appender.close();
      }

   }

   public Enumeration getAllAppenders() {
      return Collections.enumeration(this.appenders.values());
   }

   public Appender getAppender(final String name) {
      return name == null ? null : (Appender)this.appenders.get(name);
   }

   public boolean isAttached(final Appender appender) {
      return appender != null ? this.appenders.containsValue(appender) : false;
   }

   public void removeAllAppenders() {
      this.appenders.clear();
   }

   public void removeAppender(final Appender appender) {
      if (appender != null) {
         String name = appender.getName();
         if (name != null) {
            this.appenders.remove(name, appender);
         }
      }

   }

   public void removeAppender(final String name) {
      if (name != null) {
         this.appenders.remove(name);
      }

   }
}
