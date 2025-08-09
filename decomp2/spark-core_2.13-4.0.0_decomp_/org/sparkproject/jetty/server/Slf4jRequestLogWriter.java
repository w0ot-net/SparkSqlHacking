package org.sparkproject.jetty.server;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.component.AbstractLifeCycle;

@ManagedObject("Slf4j RequestLog Writer")
public class Slf4jRequestLogWriter extends AbstractLifeCycle implements RequestLog.Writer {
   private Logger logger;
   private String loggerName = "org.sparkproject.jetty.server.RequestLog";

   public void setLoggerName(String loggerName) {
      this.loggerName = loggerName;
   }

   @ManagedAttribute("logger name")
   public String getLoggerName() {
      return this.loggerName;
   }

   protected boolean isEnabled() {
      return this.logger != null;
   }

   public void write(String requestEntry) throws IOException {
      this.logger.info(requestEntry);
   }

   protected void doStart() throws Exception {
      this.logger = LoggerFactory.getLogger(this.loggerName);
      super.doStart();
   }
}
