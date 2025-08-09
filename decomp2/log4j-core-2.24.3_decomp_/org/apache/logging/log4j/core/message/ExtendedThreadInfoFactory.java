package org.apache.logging.log4j.core.message;

import aQute.bnd.annotation.spi.ServiceProvider;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.message.ThreadDumpMessage;
import org.apache.logging.log4j.message.ThreadInformation;

@ServiceProvider(
   value = ThreadDumpMessage.ThreadInfoFactory.class,
   resolution = "optional"
)
public class ExtendedThreadInfoFactory implements ThreadDumpMessage.ThreadInfoFactory {
   public ExtendedThreadInfoFactory() {
      Method[] methods = ThreadInfo.class.getMethods();
      boolean basic = true;

      for(Method method : methods) {
         if (method.getName().equals("getLockInfo")) {
            basic = false;
            break;
         }
      }

      if (basic) {
         throw new IllegalStateException();
      }
   }

   public Map createThreadInfo() {
      ThreadMXBean bean = ManagementFactory.getThreadMXBean();
      ThreadInfo[] array = bean.dumpAllThreads(true, true);
      Map<ThreadInformation, StackTraceElement[]> threads = new HashMap(array.length);

      for(ThreadInfo info : array) {
         threads.put(new ExtendedThreadInformation(info), info.getStackTrace());
      }

      return threads;
   }
}
