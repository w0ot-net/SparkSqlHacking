package org.apache.logging.log4j.core.appender.mom.jeromq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.AbstractManager;
import org.apache.logging.log4j.core.appender.ManagerFactory;
import org.apache.logging.log4j.core.util.Cancellable;
import org.apache.logging.log4j.core.util.ShutdownCallbackRegistry;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMonitor;
import org.zeromq.ZMonitor.Event;

public class JeroMqManager extends AbstractManager {
   public static final String SYS_PROPERTY_ENABLE_SHUTDOWN_HOOK = "log4j.jeromq.enableShutdownHook";
   public static final String SYS_PROPERTY_IO_THREADS = "log4j.jeromq.ioThreads";
   private static final JeroMqManagerFactory FACTORY = new JeroMqManagerFactory();
   private static final ZContext CONTEXT;
   private static final Cancellable SHUTDOWN_HOOK;
   private final ZMQ.Socket publisher;
   private final List endpoints;

   private JeroMqManager(final String name, final JeroMqConfiguration config) {
      super((LoggerContext)null, name);
      this.publisher = CONTEXT.createSocket(SocketType.PUB);
      ZMonitor monitor = new ZMonitor(CONTEXT, this.publisher);
      monitor.add(new ZMonitor.Event[]{Event.LISTENING});
      monitor.start();
      this.publisher.setAffinity(config.affinity);
      this.publisher.setBacklog(config.backlog);
      this.publisher.setDelayAttachOnConnect(config.delayAttachOnConnect);
      if (config.identity != null) {
         this.publisher.setIdentity(config.identity);
      }

      this.publisher.setIPv4Only(config.ipv4Only);
      this.publisher.setLinger(config.linger);
      this.publisher.setMaxMsgSize(config.maxMsgSize);
      this.publisher.setRcvHWM(config.rcvHwm);
      this.publisher.setReceiveBufferSize(config.receiveBufferSize);
      this.publisher.setReceiveTimeOut(config.receiveTimeOut);
      this.publisher.setReconnectIVL(config.reconnectIVL);
      this.publisher.setReconnectIVLMax(config.reconnectIVLMax);
      this.publisher.setSendBufferSize(config.sendBufferSize);
      this.publisher.setSendTimeOut(config.sendTimeOut);
      this.publisher.setSndHWM(config.sndHwm);
      this.publisher.setTCPKeepAlive(config.tcpKeepAlive);
      this.publisher.setTCPKeepAliveCount(config.tcpKeepAliveCount);
      this.publisher.setTCPKeepAliveIdle(config.tcpKeepAliveIdle);
      this.publisher.setTCPKeepAliveInterval(config.tcpKeepAliveInterval);
      this.publisher.setXpubVerbose(config.xpubVerbose);
      List<String> endpoints = new ArrayList(config.endpoints.size());

      for(String endpoint : config.endpoints) {
         this.publisher.bind(endpoint);
         ZMonitor.ZEvent event = monitor.nextEvent();
         endpoints.add(event.address);
      }

      this.endpoints = Collections.unmodifiableList(endpoints);
      monitor.destroy();
      LOGGER.debug("Created JeroMqManager with {}", config);
   }

   public boolean send(final byte[] data) {
      return this.publisher.send(data);
   }

   protected boolean releaseSub(final long timeout, final TimeUnit timeUnit) {
      this.publisher.close();
      return true;
   }

   ZMQ.Socket getSocket() {
      return this.publisher;
   }

   public List getEndpoints() {
      return this.endpoints;
   }

   public static JeroMqManager getJeroMqManager(final String name, final long affinity, final long backlog, final boolean delayAttachOnConnect, final byte[] identity, final boolean ipv4Only, final long linger, final long maxMsgSize, final long rcvHwm, final long receiveBufferSize, final int receiveTimeOut, final long reconnectIVL, final long reconnectIVLMax, final long sendBufferSize, final int sendTimeOut, final long sndHwm, final int tcpKeepAlive, final long tcpKeepAliveCount, final long tcpKeepAliveIdle, final long tcpKeepAliveInterval, final boolean xpubVerbose, final List endpoints) {
      return (JeroMqManager)getManager(name, FACTORY, new JeroMqConfiguration(affinity, backlog, delayAttachOnConnect, identity, ipv4Only, linger, maxMsgSize, rcvHwm, receiveBufferSize, receiveTimeOut, reconnectIVL, reconnectIVLMax, sendBufferSize, sendTimeOut, sndHwm, tcpKeepAlive, tcpKeepAliveCount, tcpKeepAliveIdle, tcpKeepAliveInterval, xpubVerbose, endpoints));
   }

   public static ZMQ.Context getContext() {
      return CONTEXT.getContext();
   }

   public static ZContext getZContext() {
      return CONTEXT;
   }

   static {
      LOGGER.trace("JeroMqManager using ZMQ version {}", ZMQ.getVersionString());
      int ioThreads = PropertiesUtil.getProperties().getIntegerProperty("log4j.jeromq.ioThreads", 1);
      LOGGER.trace("JeroMqManager creating ZMQ context with ioThreads = {}", ioThreads);
      CONTEXT = new ZContext(ioThreads);
      boolean enableShutdownHook = PropertiesUtil.getProperties().getBooleanProperty("log4j.jeromq.enableShutdownHook", true);
      if (enableShutdownHook && LogManager.getFactory() instanceof ShutdownCallbackRegistry) {
         ShutdownCallbackRegistry var10000 = (ShutdownCallbackRegistry)LogManager.getFactory();
         ZContext var10001 = CONTEXT;
         Objects.requireNonNull(var10001);
         SHUTDOWN_HOOK = var10000.addShutdownCallback(var10001::close);
      } else {
         SHUTDOWN_HOOK = null;
      }

   }

   private static final class JeroMqConfiguration {
      private final long affinity;
      private final long backlog;
      private final boolean delayAttachOnConnect;
      private final byte[] identity;
      private final boolean ipv4Only;
      private final long linger;
      private final long maxMsgSize;
      private final long rcvHwm;
      private final long receiveBufferSize;
      private final int receiveTimeOut;
      private final long reconnectIVL;
      private final long reconnectIVLMax;
      private final long sendBufferSize;
      private final int sendTimeOut;
      private final long sndHwm;
      private final int tcpKeepAlive;
      private final long tcpKeepAliveCount;
      private final long tcpKeepAliveIdle;
      private final long tcpKeepAliveInterval;
      private final boolean xpubVerbose;
      private final List endpoints;

      private JeroMqConfiguration(final long affinity, final long backlog, final boolean delayAttachOnConnect, final byte[] identity, final boolean ipv4Only, final long linger, final long maxMsgSize, final long rcvHwm, final long receiveBufferSize, final int receiveTimeOut, final long reconnectIVL, final long reconnectIVLMax, final long sendBufferSize, final int sendTimeOut, final long sndHwm, final int tcpKeepAlive, final long tcpKeepAliveCount, final long tcpKeepAliveIdle, final long tcpKeepAliveInterval, final boolean xpubVerbose, final List endpoints) {
         this.affinity = affinity;
         this.backlog = backlog;
         this.delayAttachOnConnect = delayAttachOnConnect;
         this.identity = identity;
         this.ipv4Only = ipv4Only;
         this.linger = linger;
         this.maxMsgSize = maxMsgSize;
         this.rcvHwm = rcvHwm;
         this.receiveBufferSize = receiveBufferSize;
         this.receiveTimeOut = receiveTimeOut;
         this.reconnectIVL = reconnectIVL;
         this.reconnectIVLMax = reconnectIVLMax;
         this.sendBufferSize = sendBufferSize;
         this.sendTimeOut = sendTimeOut;
         this.sndHwm = sndHwm;
         this.tcpKeepAlive = tcpKeepAlive;
         this.tcpKeepAliveCount = tcpKeepAliveCount;
         this.tcpKeepAliveIdle = tcpKeepAliveIdle;
         this.tcpKeepAliveInterval = tcpKeepAliveInterval;
         this.xpubVerbose = xpubVerbose;
         this.endpoints = endpoints;
      }

      public String toString() {
         return "JeroMqConfiguration{affinity=" + this.affinity + ", backlog=" + this.backlog + ", delayAttachOnConnect=" + this.delayAttachOnConnect + ", identity=" + Arrays.toString(this.identity) + ", ipv4Only=" + this.ipv4Only + ", linger=" + this.linger + ", maxMsgSize=" + this.maxMsgSize + ", rcvHwm=" + this.rcvHwm + ", receiveBufferSize=" + this.receiveBufferSize + ", receiveTimeOut=" + this.receiveTimeOut + ", reconnectIVL=" + this.reconnectIVL + ", reconnectIVLMax=" + this.reconnectIVLMax + ", sendBufferSize=" + this.sendBufferSize + ", sendTimeOut=" + this.sendTimeOut + ", sndHwm=" + this.sndHwm + ", tcpKeepAlive=" + this.tcpKeepAlive + ", tcpKeepAliveCount=" + this.tcpKeepAliveCount + ", tcpKeepAliveIdle=" + this.tcpKeepAliveIdle + ", tcpKeepAliveInterval=" + this.tcpKeepAliveInterval + ", xpubVerbose=" + this.xpubVerbose + ", endpoints=" + this.endpoints + '}';
      }
   }

   private static class JeroMqManagerFactory implements ManagerFactory {
      private JeroMqManagerFactory() {
      }

      public JeroMqManager createManager(final String name, final JeroMqConfiguration data) {
         return new JeroMqManager(name, data);
      }
   }
}
