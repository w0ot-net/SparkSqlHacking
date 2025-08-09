package org.apache.spark.api.java.function;

import java.io.Serializable;
import java.util.Iterator;
import org.apache.spark.annotation.Evolving;
import org.apache.spark.sql.streaming.GroupState;

@Evolving
public interface FlatMapGroupsWithStateFunction extends Serializable {
   Iterator call(Object var1, Iterator var2, GroupState var3) throws Exception;
}
