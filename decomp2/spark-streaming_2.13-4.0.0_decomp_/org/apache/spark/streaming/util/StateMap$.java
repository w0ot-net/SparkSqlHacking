package org.apache.spark.streaming.util;

import java.io.Serializable;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.StreamingConf$;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class StateMap$ implements Serializable {
   public static final StateMap$ MODULE$ = new StateMap$();

   public StateMap empty() {
      return new EmptyStateMap();
   }

   public StateMap create(final SparkConf conf, final ClassTag evidence$1, final ClassTag evidence$2) {
      int deltaChainThreshold = BoxesRunTime.unboxToInt(conf.get(StreamingConf$.MODULE$.SESSION_BY_KEY_DELTA_CHAIN_THRESHOLD()));
      return new OpenHashMapBasedStateMap(deltaChainThreshold, evidence$1, evidence$2);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StateMap$.class);
   }

   private StateMap$() {
   }
}
