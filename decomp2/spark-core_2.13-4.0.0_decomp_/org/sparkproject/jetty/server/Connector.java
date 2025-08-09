package org.sparkproject.jetty.server;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executor;
import org.sparkproject.jetty.io.ByteBufferPool;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.component.Container;
import org.sparkproject.jetty.util.component.Graceful;
import org.sparkproject.jetty.util.component.LifeCycle;
import org.sparkproject.jetty.util.thread.Scheduler;

@ManagedObject("Connector Interface")
public interface Connector extends LifeCycle, Container, Graceful {
   Server getServer();

   Executor getExecutor();

   Scheduler getScheduler();

   ByteBufferPool getByteBufferPool();

   ConnectionFactory getConnectionFactory(String var1);

   Object getConnectionFactory(Class var1);

   ConnectionFactory getDefaultConnectionFactory();

   Collection getConnectionFactories();

   List getProtocols();

   @ManagedAttribute("maximum time a connection can be idle before being closed (in ms)")
   long getIdleTimeout();

   Object getTransport();

   Collection getConnectedEndPoints();

   String getName();
}
