package org.apache.curator.framework.imps;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import org.apache.curator.RetryLoop;
import org.apache.curator.TimeTrace;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.ConfigureEnsembleable;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.framework.api.Ensembleable;
import org.apache.curator.framework.api.ErrorListenerReconfigBuilderMain;
import org.apache.curator.framework.api.JoinStatConfigEnsembleable;
import org.apache.curator.framework.api.LeaveStatConfigEnsembleable;
import org.apache.curator.framework.api.ReconfigBuilder;
import org.apache.curator.framework.api.ReconfigBuilderMain;
import org.apache.curator.framework.api.StatConfigureEnsembleable;
import org.apache.curator.framework.api.UnhandledErrorListener;
import org.apache.curator.shaded.com.google.common.collect.ImmutableList;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.admin.ZooKeeperAdmin;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.DataTree;

public class ReconfigBuilderImpl implements ReconfigBuilder, BackgroundOperation, ErrorListenerReconfigBuilderMain {
   private final CuratorFrameworkImpl client;
   private Backgrounding backgrounding = new Backgrounding();
   private Stat responseStat;
   private long fromConfig = -1L;
   private List newMembers;
   private List joining;
   private List leaving;

   public ReconfigBuilderImpl(CuratorFrameworkImpl client) {
      this.client = client;
   }

   public ReconfigBuilderImpl(CuratorFrameworkImpl client, Backgrounding backgrounding, Stat responseStat, long fromConfig, List newMembers, List joining, List leaving) {
      this.client = client;
      this.backgrounding = backgrounding;
      this.responseStat = responseStat;
      this.fromConfig = fromConfig;
      this.newMembers = newMembers;
      this.joining = joining;
      this.leaving = leaving;
   }

   public byte[] forEnsemble() throws Exception {
      if (this.backgrounding.inBackground()) {
         this.client.processBackgroundOperation(new OperationAndData(this, (Object)null, this.backgrounding.getCallback(), (OperationAndData.ErrorCallback)null, this.backgrounding.getContext(), (Watching)null), (CuratorEvent)null);
         return new byte[0];
      } else {
         return this.ensembleInForeground();
      }
   }

   public ErrorListenerReconfigBuilderMain inBackground() {
      this.backgrounding = new Backgrounding(true);
      return this;
   }

   public ErrorListenerReconfigBuilderMain inBackground(Object context) {
      this.backgrounding = new Backgrounding(context);
      return this;
   }

   public ErrorListenerReconfigBuilderMain inBackground(BackgroundCallback callback) {
      this.backgrounding = new Backgrounding(callback);
      return this;
   }

   public ErrorListenerReconfigBuilderMain inBackground(BackgroundCallback callback, Object context) {
      this.backgrounding = new Backgrounding(callback, context);
      return this;
   }

   public ErrorListenerReconfigBuilderMain inBackground(BackgroundCallback callback, Executor executor) {
      this.backgrounding = new Backgrounding(callback, executor);
      return this;
   }

   public ErrorListenerReconfigBuilderMain inBackground(BackgroundCallback callback, Object context, Executor executor) {
      this.backgrounding = new Backgrounding(this.client, callback, context, executor);
      return this;
   }

   public ReconfigBuilderMain withUnhandledErrorListener(UnhandledErrorListener listener) {
      this.backgrounding = new Backgrounding(this.backgrounding, listener);
      return this;
   }

   public StatConfigureEnsembleable withNewMembers(String... server) {
      return this.withNewMembers(server != null ? Arrays.asList(server) : null);
   }

   public StatConfigureEnsembleable withNewMembers(List servers) {
      this.newMembers = servers != null && !servers.isEmpty() ? ImmutableList.copyOf(servers) : null;
      return new StatConfigureEnsembleable() {
         public Ensembleable fromConfig(long config) throws Exception {
            ReconfigBuilderImpl.this.fromConfig = config;
            return this;
         }

         public byte[] forEnsemble() throws Exception {
            return ReconfigBuilderImpl.this.forEnsemble();
         }

         public ConfigureEnsembleable storingStatIn(Stat stat) {
            ReconfigBuilderImpl.this.responseStat = stat;
            return this;
         }
      };
   }

   public LeaveStatConfigEnsembleable joining(String... server) {
      return this.joining(server != null ? Arrays.asList(server) : null);
   }

   public LeaveStatConfigEnsembleable joining(List servers) {
      this.joining = servers != null && !servers.isEmpty() ? ImmutableList.copyOf(servers) : null;
      return new LeaveStatConfigEnsembleable() {
         public byte[] forEnsemble() throws Exception {
            return ReconfigBuilderImpl.this.forEnsemble();
         }

         public ConfigureEnsembleable storingStatIn(Stat stat) {
            ReconfigBuilderImpl.this.responseStat = stat;
            return this;
         }

         public Ensembleable fromConfig(long config) throws Exception {
            ReconfigBuilderImpl.this.fromConfig = config;
            return this;
         }

         public JoinStatConfigEnsembleable leaving(String... server) {
            return ReconfigBuilderImpl.this.leaving(server);
         }

         public JoinStatConfigEnsembleable leaving(List servers) {
            return ReconfigBuilderImpl.this.leaving(servers);
         }
      };
   }

   public JoinStatConfigEnsembleable leaving(String... server) {
      return this.leaving(server != null ? Arrays.asList(server) : null);
   }

   public JoinStatConfigEnsembleable leaving(List servers) {
      this.leaving = servers != null && !servers.isEmpty() ? ImmutableList.copyOf(servers) : null;
      return new JoinStatConfigEnsembleable() {
         public byte[] forEnsemble() throws Exception {
            return ReconfigBuilderImpl.this.forEnsemble();
         }

         public ConfigureEnsembleable storingStatIn(Stat stat) {
            ReconfigBuilderImpl.this.responseStat = stat;
            return this;
         }

         public Ensembleable fromConfig(long config) throws Exception {
            ReconfigBuilderImpl.this.fromConfig = config;
            return this;
         }

         public LeaveStatConfigEnsembleable joining(String... server) {
            return this.joining(server != null ? Arrays.asList(server) : null);
         }

         public LeaveStatConfigEnsembleable joining(List servers) {
            return ReconfigBuilderImpl.this.joining(servers);
         }
      };
   }

   public CuratorEventType getBackgroundEventType() {
      return CuratorEventType.RECONFIG;
   }

   public void performBackgroundOperation(final OperationAndData data) throws Exception {
      try {
         final TimeTrace trace = this.client.getZookeeperClient().startTracer("ReconfigBuilderImpl-Background");
         AsyncCallback.DataCallback callback = new AsyncCallback.DataCallback() {
            public void processResult(int rc, String path, Object ctx, byte[] bytes, Stat stat) {
               trace.commit();
               if (ReconfigBuilderImpl.this.responseStat != null && stat != null) {
                  DataTree.copyStat(stat, ReconfigBuilderImpl.this.responseStat);
               }

               CuratorEvent event = new CuratorEventImpl(ReconfigBuilderImpl.this.client, CuratorEventType.RECONFIG, rc, path, (String)null, ctx, stat, bytes, (List)null, (WatchedEvent)null, (List)null, (List)null);
               ReconfigBuilderImpl.this.client.processBackgroundOperation(data, event);
            }
         };
         ((ZooKeeperAdmin)this.client.getZooKeeper()).reconfigure(this.joining, this.leaving, this.newMembers, this.fromConfig, callback, this.backgrounding.getContext());
      } catch (Throwable e) {
         this.backgrounding.checkError(e, (Watching)null);
      }

   }

   private byte[] ensembleInForeground() throws Exception {
      TimeTrace trace = this.client.getZookeeperClient().startTracer("ReconfigBuilderImpl-Foreground");
      byte[] responseData = (byte[])RetryLoop.callWithRetry(this.client.getZookeeperClient(), new Callable() {
         public byte[] call() throws Exception {
            return ((ZooKeeperAdmin)ReconfigBuilderImpl.this.client.getZooKeeper()).reconfigure(ReconfigBuilderImpl.this.joining, ReconfigBuilderImpl.this.leaving, ReconfigBuilderImpl.this.newMembers, ReconfigBuilderImpl.this.fromConfig, ReconfigBuilderImpl.this.responseStat);
         }
      });
      trace.commit();
      return responseData;
   }
}
