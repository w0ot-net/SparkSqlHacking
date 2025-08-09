package org.apache.hadoop.hive.ql.io.sarg;

import org.apache.hadoop.conf.Configurable;

public interface LiteralDelegate extends Configurable {
   Object getLiteral();

   String getId();
}
