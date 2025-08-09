package org.apache.orc.filter;

import org.apache.hadoop.conf.Configuration;

public interface PluginFilterService {
   BatchFilter getFilter(String var1, Configuration var2);
}
