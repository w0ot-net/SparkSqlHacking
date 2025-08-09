package org.apache.hadoop.hive.shims;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;

public interface SchedulerShim {
   void refreshDefaultQueue(Configuration var1, String var2) throws IOException;
}
