package org.apache.hive.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.SERVICE_NAME.;

public abstract class AbstractService implements Service {
   private static final SparkLogger LOG = SparkLoggerFactory.getLogger(AbstractService.class);
   private Service.STATE state;
   private final String name;
   private long startTime;
   private HiveConf hiveConf;
   private final List listeners;

   public AbstractService(String name) {
      this.state = Service.STATE.NOTINITED;
      this.listeners = new ArrayList();
      this.name = name;
   }

   public synchronized Service.STATE getServiceState() {
      return this.state;
   }

   public synchronized void init(HiveConf hiveConf) {
      this.ensureCurrentState(Service.STATE.NOTINITED);
      this.hiveConf = hiveConf;
      this.changeState(Service.STATE.INITED);
      LOG.info("Service:{} is inited.", new MDC[]{MDC.of(.MODULE$, this.getName())});
   }

   public synchronized void start() {
      this.startTime = System.currentTimeMillis();
      this.ensureCurrentState(Service.STATE.INITED);
      this.changeState(Service.STATE.STARTED);
      LOG.info("Service:{} is started.", new MDC[]{MDC.of(.MODULE$, this.getName())});
   }

   public synchronized void stop() {
      if (this.state != Service.STATE.STOPPED && this.state != Service.STATE.INITED && this.state != Service.STATE.NOTINITED) {
         this.ensureCurrentState(Service.STATE.STARTED);
         this.changeState(Service.STATE.STOPPED);
         LOG.info("Service:{} is stopped.", new MDC[]{MDC.of(.MODULE$, this.getName())});
      }
   }

   public synchronized void register(ServiceStateChangeListener l) {
      this.listeners.add(l);
   }

   public synchronized void unregister(ServiceStateChangeListener l) {
      this.listeners.remove(l);
   }

   public String getName() {
      return this.name;
   }

   public synchronized HiveConf getHiveConf() {
      return this.hiveConf;
   }

   public long getStartTime() {
      return this.startTime;
   }

   private void ensureCurrentState(Service.STATE currentState) {
      ServiceOperations.ensureCurrentState(this.state, currentState);
   }

   private void changeState(Service.STATE newState) {
      this.state = newState;

      for(ServiceStateChangeListener l : this.listeners) {
         l.stateChanged(this);
      }

   }
}
