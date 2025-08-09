package org.apache.log4j.spi;

import java.util.Enumeration;
import java.util.Vector;
import org.apache.log4j.Appender;
import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public final class NOPLoggerRepository implements LoggerRepository {
   public void addHierarchyEventListener(final HierarchyEventListener listener) {
   }

   public void emitNoAppenderWarning(final Category cat) {
   }

   public Logger exists(final String name) {
      return null;
   }

   public void fireAddAppenderEvent(final Category logger, final Appender appender) {
   }

   public Enumeration getCurrentCategories() {
      return this.getCurrentLoggers();
   }

   public Enumeration getCurrentLoggers() {
      return (new Vector()).elements();
   }

   public Logger getLogger(final String name) {
      return new NOPLogger(this, name);
   }

   public Logger getLogger(final String name, final LoggerFactory factory) {
      return new NOPLogger(this, name);
   }

   public Logger getRootLogger() {
      return new NOPLogger(this, "root");
   }

   public Level getThreshold() {
      return Level.OFF;
   }

   public boolean isDisabled(final int level) {
      return true;
   }

   public void resetConfiguration() {
   }

   public void setThreshold(final Level level) {
   }

   public void setThreshold(final String val) {
   }

   public void shutdown() {
   }
}
