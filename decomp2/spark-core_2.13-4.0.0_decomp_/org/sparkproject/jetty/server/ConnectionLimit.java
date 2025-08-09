package org.sparkproject.jetty.server;

import java.nio.channels.SelectableChannel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.io.Connection;
import org.sparkproject.jetty.io.EndPoint;
import org.sparkproject.jetty.io.SelectorManager;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.annotation.Name;
import org.sparkproject.jetty.util.component.AbstractLifeCycle;
import org.sparkproject.jetty.util.thread.AutoLock;

@ManagedObject
public class ConnectionLimit extends AbstractLifeCycle implements Connection.Listener, SelectorManager.AcceptListener {
   private static final Logger LOG = LoggerFactory.getLogger(ConnectionLimit.class);
   private final AutoLock _lock;
   private final Server _server;
   private final List _connectors;
   private final Set _accepting;
   private int _connections;
   private int _maxConnections;
   private long _idleTimeout;
   private boolean _limiting;

   public ConnectionLimit(@Name("maxConnections") int maxConnections, @Name("server") Server server) {
      this._lock = new AutoLock();
      this._connectors = new ArrayList();
      this._accepting = new HashSet();
      this._limiting = false;
      this._maxConnections = maxConnections;
      this._server = server;
   }

   public ConnectionLimit(@Name("maxConnections") int maxConnections, @Name("connectors") Connector... connectors) {
      this(maxConnections, (Server)null);

      for(Connector c : connectors) {
         if (c instanceof AbstractConnector) {
            this._connectors.add((AbstractConnector)c);
         } else {
            LOG.warn("Connector {} is not an AbstractConnection. Connections not limited", c);
         }
      }

   }

   @ManagedAttribute("The endpoint idle timeout in ms to apply when the connection limit is reached")
   public long getIdleTimeout() {
      return this._idleTimeout;
   }

   public void setIdleTimeout(long idleTimeout) {
      this._idleTimeout = idleTimeout;
   }

   @ManagedAttribute("The maximum number of connections allowed")
   public int getMaxConnections() {
      try (AutoLock l = this._lock.lock()) {
         return this._maxConnections;
      }
   }

   public void setMaxConnections(int max) {
      try (AutoLock l = this._lock.lock()) {
         this._maxConnections = max;
      }

   }

   @ManagedAttribute("The current number of connections ")
   public int getConnections() {
      try (AutoLock l = this._lock.lock()) {
         return this._connections;
      }
   }

   protected void doStart() throws Exception {
      try (AutoLock l = this._lock.lock()) {
         if (this._server != null) {
            for(Connector c : this._server.getConnectors()) {
               if (c instanceof AbstractConnector) {
                  this._connectors.add((AbstractConnector)c);
               } else {
                  LOG.warn("Connector {} is not an AbstractConnector. Connections not limited", c);
               }
            }
         }

         if (LOG.isDebugEnabled()) {
            LOG.debug("ConnectionLimit {} for {}", this._maxConnections, this._connectors);
         }

         this._connections = 0;
         this._limiting = false;

         for(AbstractConnector c : this._connectors) {
            c.addBean(this);
         }
      }

   }

   protected void doStop() throws Exception {
      try (AutoLock l = this._lock.lock()) {
         for(AbstractConnector c : this._connectors) {
            c.removeBean(this);
         }

         this._connections = 0;
         if (this._server != null) {
            this._connectors.clear();
         }
      }

   }

   protected void check() {
      if (this._accepting.size() + this._connections >= this._maxConnections) {
         if (!this._limiting) {
            this._limiting = true;
            LOG.info("Connection Limit({}) reached for {}", this._maxConnections, this._connectors);
            this.limit();
         }
      } else if (this._limiting) {
         this._limiting = false;
         LOG.info("Connection Limit({}) cleared for {}", this._maxConnections, this._connectors);
         this.unlimit();
      }

   }

   protected void limit() {
      for(AbstractConnector c : this._connectors) {
         c.setAccepting(false);
         if (this._idleTimeout > 0L) {
            for(EndPoint endPoint : c.getConnectedEndPoints()) {
               endPoint.setIdleTimeout(this._idleTimeout);
            }
         }
      }

   }

   protected void unlimit() {
      for(AbstractConnector c : this._connectors) {
         c.setAccepting(true);
         if (this._idleTimeout > 0L) {
            for(EndPoint endPoint : c.getConnectedEndPoints()) {
               endPoint.setIdleTimeout(c.getIdleTimeout());
            }
         }
      }

   }

   public void onAccepting(SelectableChannel channel) {
      try (AutoLock l = this._lock.lock()) {
         this._accepting.add(channel);
         if (LOG.isDebugEnabled()) {
            LOG.debug("onAccepting ({}+{}) < {} {}", new Object[]{this._accepting.size(), this._connections, this._maxConnections, channel});
         }

         this.check();
      }

   }

   public void onAcceptFailed(SelectableChannel channel, Throwable cause) {
      try (AutoLock l = this._lock.lock()) {
         this._accepting.remove(channel);
         if (LOG.isDebugEnabled()) {
            LOG.debug("onAcceptFailed ({}+{}) < {} {} {}", new Object[]{this._accepting.size(), this._connections, this._maxConnections, channel, cause});
         }

         this.check();
      }

   }

   public void onAccepted(SelectableChannel channel) {
   }

   public void onOpened(Connection connection) {
      try (AutoLock l = this._lock.lock()) {
         this._accepting.remove(connection.getEndPoint().getTransport());
         ++this._connections;
         if (LOG.isDebugEnabled()) {
            LOG.debug("onOpened ({}+{}) < {} {}", new Object[]{this._accepting.size(), this._connections, this._maxConnections, connection});
         }

         this.check();
      }

   }

   public void onClosed(Connection connection) {
      try (AutoLock l = this._lock.lock()) {
         --this._connections;
         if (LOG.isDebugEnabled()) {
            LOG.debug("onClosed ({}+{}) < {} {}", new Object[]{this._accepting.size(), this._connections, this._maxConnections, connection});
         }

         this.check();
      }

   }
}
