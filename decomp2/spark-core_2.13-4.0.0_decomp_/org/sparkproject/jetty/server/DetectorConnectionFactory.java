package org.sparkproject.jetty.server;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.io.AbstractConnection;
import org.sparkproject.jetty.io.Connection;
import org.sparkproject.jetty.io.EndPoint;
import org.sparkproject.jetty.util.BufferUtil;

public class DetectorConnectionFactory extends AbstractConnectionFactory implements ConnectionFactory.Detecting {
   private static final Logger LOG = LoggerFactory.getLogger(DetectorConnectionFactory.class);
   private final List _detectingConnectionFactories;

   public DetectorConnectionFactory(ConnectionFactory.Detecting... detectingConnectionFactories) {
      super(toProtocolString(detectingConnectionFactories));
      this._detectingConnectionFactories = Arrays.asList(detectingConnectionFactories);

      for(ConnectionFactory.Detecting detectingConnectionFactory : detectingConnectionFactories) {
         this.addBean(detectingConnectionFactory);
      }

   }

   private static String toProtocolString(ConnectionFactory.Detecting... detectingConnectionFactories) {
      if (detectingConnectionFactories.length == 0) {
         throw new IllegalArgumentException("At least one detecting instance is required");
      } else {
         LinkedHashSet<String> protocols = (LinkedHashSet)Arrays.stream(detectingConnectionFactories).map(ConnectionFactory::getProtocol).collect(Collectors.toCollection(LinkedHashSet::new));
         String protocol = (String)protocols.stream().collect(Collectors.joining("|", "[", "]"));
         if (LOG.isDebugEnabled()) {
            LOG.debug("Detector generated protocol name : {}", protocol);
         }

         return protocol;
      }
   }

   public ConnectionFactory.Detecting.Detection detect(ByteBuffer buffer) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("Detector {} detecting from buffer {} using {}", new Object[]{this.getProtocol(), BufferUtil.toHexString(buffer), this._detectingConnectionFactories});
      }

      boolean needMoreBytes = true;

      for(ConnectionFactory.Detecting detectingConnectionFactory : this._detectingConnectionFactories) {
         ConnectionFactory.Detecting.Detection detection = detectingConnectionFactory.detect(buffer);
         if (detection == ConnectionFactory.Detecting.Detection.RECOGNIZED) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("Detector {} recognized bytes using {}", this.getProtocol(), detection);
            }

            return ConnectionFactory.Detecting.Detection.RECOGNIZED;
         }

         needMoreBytes &= detection == ConnectionFactory.Detecting.Detection.NEED_MORE_BYTES;
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("Detector {} {}", this.getProtocol(), needMoreBytes ? "requires more bytes" : "failed to recognize bytes");
      }

      return needMoreBytes ? ConnectionFactory.Detecting.Detection.NEED_MORE_BYTES : ConnectionFactory.Detecting.Detection.NOT_RECOGNIZED;
   }

   protected static void upgradeToConnectionFactory(ConnectionFactory connectionFactory, Connector connector, EndPoint endPoint) throws IllegalStateException {
      if (LOG.isDebugEnabled()) {
         LOG.debug("Upgrading to connection factory {}", connectionFactory);
      }

      if (connectionFactory == null) {
         throw new IllegalStateException("Cannot upgrade: connection factory must not be null for " + String.valueOf(endPoint));
      } else {
         Connection nextConnection = connectionFactory.newConnection(connector, endPoint);
         if (!(nextConnection instanceof Connection.UpgradeTo)) {
            String var10002 = String.valueOf(nextConnection);
            throw new IllegalStateException("Cannot upgrade: " + var10002 + " does not implement " + Connection.UpgradeTo.class.getName() + " for " + String.valueOf(endPoint));
         } else {
            endPoint.upgrade(nextConnection);
            if (LOG.isDebugEnabled()) {
               LOG.debug("Upgraded to connection factory {} and released buffer", connectionFactory);
            }

         }
      }
   }

   protected void nextProtocol(Connector connector, EndPoint endPoint, ByteBuffer buffer) throws IllegalStateException {
      String nextProtocol = this.findNextProtocol(connector);
      if (LOG.isDebugEnabled()) {
         LOG.debug("Detector {} detection unsuccessful, found '{}' as the next protocol to upgrade to", this.getProtocol(), nextProtocol);
      }

      if (nextProtocol == null) {
         String var10002 = this.getProtocol();
         throw new IllegalStateException("Cannot find protocol following '" + var10002 + "' in connector's protocol list " + String.valueOf(connector.getProtocols()) + " for " + String.valueOf(endPoint));
      } else {
         upgradeToConnectionFactory(connector.getConnectionFactory(nextProtocol), connector, endPoint);
      }
   }

   public Connection newConnection(Connector connector, EndPoint endPoint) {
      return this.configure(new DetectorConnection(endPoint, connector), connector, endPoint);
   }

   private class DetectorConnection extends AbstractConnection implements Connection.UpgradeFrom, Connection.UpgradeTo {
      private final Connector _connector;
      private final ByteBuffer _buffer;

      private DetectorConnection(EndPoint endp, Connector connector) {
         super(endp, connector.getExecutor());
         this._connector = connector;
         this._buffer = connector.getByteBufferPool().acquire(this.getInputBufferSize(), true);
      }

      public void onUpgradeTo(ByteBuffer buffer) {
         if (DetectorConnectionFactory.LOG.isDebugEnabled()) {
            DetectorConnectionFactory.LOG.debug("Detector {} copying unconsumed buffer {}", DetectorConnectionFactory.this.getProtocol(), BufferUtil.toDetailString(buffer));
         }

         BufferUtil.append(this._buffer, buffer);
      }

      public ByteBuffer onUpgradeFrom() {
         if (this._buffer.hasRemaining()) {
            ByteBuffer unconsumed = ByteBuffer.allocateDirect(this._buffer.remaining());
            unconsumed.put(this._buffer);
            unconsumed.flip();
            this._connector.getByteBufferPool().release(this._buffer);
            return unconsumed;
         } else {
            return null;
         }
      }

      public void onOpen() {
         super.onOpen();
         if (!this.detectAndUpgrade()) {
            this.fillInterested();
         }

      }

      public void onFillable() {
         try {
            while(true) {
               if (BufferUtil.space(this._buffer) > 0) {
                  int fill = this.getEndPoint().fill(this._buffer);
                  if (DetectorConnectionFactory.LOG.isDebugEnabled()) {
                     DetectorConnectionFactory.LOG.debug("Detector {} filled buffer with {} bytes", DetectorConnectionFactory.this.getProtocol(), fill);
                  }

                  if (fill < 0) {
                     this._connector.getByteBufferPool().release(this._buffer);
                     this.getEndPoint().shutdownOutput();
                     return;
                  }

                  if (fill == 0) {
                     this.fillInterested();
                     return;
                  }

                  if (this.detectAndUpgrade()) {
                     return;
                  }
               } else {
                  DetectorConnectionFactory.LOG.warn("Detector {} failed to detect upgrade target on {} for {}", new Object[]{DetectorConnectionFactory.this.getProtocol(), DetectorConnectionFactory.this._detectingConnectionFactories, this.getEndPoint()});
                  this.releaseAndClose();
                  break;
               }
            }
         } catch (Throwable x) {
            DetectorConnectionFactory.LOG.warn("Detector {} error for {}", new Object[]{DetectorConnectionFactory.this.getProtocol(), this.getEndPoint(), x});
            this.releaseAndClose();
         }

      }

      private boolean detectAndUpgrade() {
         if (BufferUtil.isEmpty(this._buffer)) {
            if (DetectorConnectionFactory.LOG.isDebugEnabled()) {
               DetectorConnectionFactory.LOG.debug("Detector {} skipping detection on an empty buffer", DetectorConnectionFactory.this.getProtocol());
            }

            return false;
         } else {
            if (DetectorConnectionFactory.LOG.isDebugEnabled()) {
               DetectorConnectionFactory.LOG.debug("Detector {} performing detection with {} bytes", DetectorConnectionFactory.this.getProtocol(), this._buffer.remaining());
            }

            boolean notRecognized = true;

            for(ConnectionFactory.Detecting detectingConnectionFactory : DetectorConnectionFactory.this._detectingConnectionFactories) {
               ConnectionFactory.Detecting.Detection detection = detectingConnectionFactory.detect(this._buffer);
               if (DetectorConnectionFactory.LOG.isDebugEnabled()) {
                  DetectorConnectionFactory.LOG.debug("Detector {} performed detection from {} with {} which returned {}", new Object[]{DetectorConnectionFactory.this.getProtocol(), BufferUtil.toDetailString(this._buffer), detectingConnectionFactory, detection});
               }

               if (detection == ConnectionFactory.Detecting.Detection.RECOGNIZED) {
                  try {
                     Connection nextConnection = detectingConnectionFactory.newConnection(this._connector, this.getEndPoint());
                     if (!(nextConnection instanceof Connection.UpgradeTo)) {
                        String var10002 = String.valueOf(nextConnection);
                        throw new IllegalStateException("Cannot upgrade: " + var10002 + " does not implement " + Connection.UpgradeTo.class.getName());
                     }

                     this.getEndPoint().upgrade(nextConnection);
                     if (DetectorConnectionFactory.LOG.isDebugEnabled()) {
                        DetectorConnectionFactory.LOG.debug("Detector {} upgraded to {}", DetectorConnectionFactory.this.getProtocol(), nextConnection);
                     }

                     return true;
                  } catch (DetectionFailureException var6) {
                     if (DetectorConnectionFactory.LOG.isDebugEnabled()) {
                        DetectorConnectionFactory.LOG.debug("Detector {} failed to upgrade, rethrowing", DetectorConnectionFactory.this.getProtocol(), var6);
                     }

                     throw var6;
                  } catch (Exception e) {
                     if (DetectorConnectionFactory.LOG.isDebugEnabled()) {
                        DetectorConnectionFactory.LOG.debug("Detector {} failed to upgrade", DetectorConnectionFactory.this.getProtocol());
                     }

                     this.releaseAndClose();
                     throw new DetectionFailureException(e);
                  }
               }

               notRecognized &= detection == ConnectionFactory.Detecting.Detection.NOT_RECOGNIZED;
            }

            if (notRecognized) {
               if (DetectorConnectionFactory.LOG.isDebugEnabled()) {
                  DetectorConnectionFactory.LOG.debug("Detector {} failed to detect a known protocol, falling back to nextProtocol()", DetectorConnectionFactory.this.getProtocol());
               }

               DetectorConnectionFactory.this.nextProtocol(this._connector, this.getEndPoint(), this._buffer);
               if (DetectorConnectionFactory.LOG.isDebugEnabled()) {
                  DetectorConnectionFactory.LOG.debug("Detector {} call to nextProtocol() succeeded, assuming upgrade performed", DetectorConnectionFactory.this.getProtocol());
               }

               return true;
            } else {
               return false;
            }
         }
      }

      private void releaseAndClose() {
         if (DetectorConnectionFactory.LOG.isDebugEnabled()) {
            DetectorConnectionFactory.LOG.debug("Detector {} releasing buffer and closing", DetectorConnectionFactory.this.getProtocol());
         }

         this._connector.getByteBufferPool().release(this._buffer);
         this.close();
      }
   }

   private static class DetectionFailureException extends RuntimeException {
      public DetectionFailureException(Throwable cause) {
         super(cause);
      }
   }
}
