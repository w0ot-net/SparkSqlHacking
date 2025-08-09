package io.fabric8.kubernetes.client.dsl.internal.core.v1;

import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.DeleteOptions;
import io.fabric8.kubernetes.api.model.EphemeralContainer;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodCondition;
import io.fabric8.kubernetes.api.model.PodConditionBuilder;
import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.api.model.PodSpec;
import io.fabric8.kubernetes.api.model.PodStatus;
import io.fabric8.kubernetes.api.model.policy.v1beta1.Eviction;
import io.fabric8.kubernetes.api.model.policy.v1beta1.EvictionBuilder;
import io.fabric8.kubernetes.api.model.policy.v1beta1.EvictionFluent;
import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.LocalPortForward;
import io.fabric8.kubernetes.client.PortForward;
import io.fabric8.kubernetes.client.dsl.BytesLimitTerminateTimeTailPrettyLoggable;
import io.fabric8.kubernetes.client.dsl.CopyOrReadable;
import io.fabric8.kubernetes.client.dsl.EphemeralContainersResource;
import io.fabric8.kubernetes.client.dsl.ExecListenable;
import io.fabric8.kubernetes.client.dsl.ExecListener;
import io.fabric8.kubernetes.client.dsl.ExecWatch;
import io.fabric8.kubernetes.client.dsl.Execable;
import io.fabric8.kubernetes.client.dsl.LogWatch;
import io.fabric8.kubernetes.client.dsl.Loggable;
import io.fabric8.kubernetes.client.dsl.PodResource;
import io.fabric8.kubernetes.client.dsl.PrettyLoggable;
import io.fabric8.kubernetes.client.dsl.TailPrettyLoggable;
import io.fabric8.kubernetes.client.dsl.TimeTailPrettyLoggable;
import io.fabric8.kubernetes.client.dsl.TtyExecErrorChannelable;
import io.fabric8.kubernetes.client.dsl.TtyExecErrorable;
import io.fabric8.kubernetes.client.dsl.TtyExecOutputErrorable;
import io.fabric8.kubernetes.client.dsl.TtyExecable;
import io.fabric8.kubernetes.client.dsl.internal.ExecWebSocketListener;
import io.fabric8.kubernetes.client.dsl.internal.HasMetadataOperation;
import io.fabric8.kubernetes.client.dsl.internal.HasMetadataOperationsImpl;
import io.fabric8.kubernetes.client.dsl.internal.LogWatchCallback;
import io.fabric8.kubernetes.client.dsl.internal.OperationContext;
import io.fabric8.kubernetes.client.dsl.internal.PodOperationContext;
import io.fabric8.kubernetes.client.dsl.internal.PortForwarderWebsocket;
import io.fabric8.kubernetes.client.dsl.internal.uploadable.PodUpload;
import io.fabric8.kubernetes.client.http.HttpRequest;
import io.fabric8.kubernetes.client.http.WebSocket;
import io.fabric8.kubernetes.client.lib.FilenameUtils;
import io.fabric8.kubernetes.client.utils.URLUtils;
import io.fabric8.kubernetes.client.utils.Utils;
import io.fabric8.kubernetes.client.utils.internal.OptionalDependencyWrapper;
import io.fabric8.kubernetes.client.utils.internal.PodOperationUtil;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.Reader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PodOperationsImpl extends HasMetadataOperation implements PodResource, EphemeralContainersResource, CopyOrReadable {
   public static final int HTTP_TOO_MANY_REQUESTS = 429;
   public static final int DEFAULT_POD_READY_WAIT_TIMEOUT_MS = 0;
   private static final String[] EMPTY_COMMAND = new String[]{"/bin/sh", "-i"};
   public static final String DEFAULT_CONTAINER_ANNOTATION_NAME = "kubectl.kubernetes.io/default-container";
   static final Logger LOG = LoggerFactory.getLogger(PodOperationsImpl.class);
   private final PodOperationContext podOperationContext;

   public PodOperationsImpl(Client client) {
      this(new PodOperationContext(), HasMetadataOperationsImpl.defaultContext(client));
   }

   public PodOperationsImpl(PodOperationContext context, OperationContext superContext) {
      super(superContext.withPlural("pods"), Pod.class, PodList.class);
      this.podOperationContext = context;
   }

   public PodOperationsImpl newInstance(OperationContext context) {
      return new PodOperationsImpl(this.podOperationContext, context);
   }

   public PodOperationContext getContext() {
      return this.podOperationContext;
   }

   protected Object doGetLog(Class type) {
      try {
         URL url = new URL(URLUtils.join(new String[]{this.getResourceUrl().toString(), this.podOperationContext.getLogParameters()}));
         PodOperationUtil.waitUntilReadyOrTerminal(this, this.getContext().getReadyWaitTimeout() != null ? this.getContext().getReadyWaitTimeout() : 0);
         return this.handleRawGet(url, type);
      } catch (IOException ioException) {
         throw KubernetesClientException.launderThrowable(this.forOperationType("doGetLog"), ioException);
      }
   }

   public String getLog() {
      return (String)this.doGetLog(String.class);
   }

   public Reader getLogReader() {
      return (Reader)this.doGetLog(Reader.class);
   }

   public InputStream getLogInputStream() {
      return (InputStream)this.doGetLog(InputStream.class);
   }

   public String getLog(boolean isPretty) {
      return (new PodOperationsImpl(this.getContext().withPrettyOutput(isPretty), this.context)).getLog();
   }

   public LogWatch watchLog() {
      return this.watchLog((OutputStream)null);
   }

   private void checkForPiped(Object object) {
      if (object instanceof PipedOutputStream || object instanceof PipedInputStream) {
         throw new KubernetesClientException("Piped streams should not be used");
      }
   }

   public LogWatch watchLog(OutputStream out) {
      this.checkForPiped(out);

      try {
         PodOperationUtil.waitUntilReadyOrTerminal(this, this.getContext().getReadyWaitTimeout() != null ? this.getContext().getReadyWaitTimeout() : 0);
         URL url = new URL(URLUtils.join(new String[]{this.getResourceUrl().toString(), this.getContext().getLogParameters() + "&follow=true"}));
         LogWatchCallback callback = new LogWatchCallback(out, this.context);
         return callback.callAndWait(this.httpClient, url);
      } catch (IOException ioException) {
         throw KubernetesClientException.launderThrowable(this.forOperationType("watchLog"), ioException);
      }
   }

   public PodOperationsImpl withReadyWaitTimeout(Integer readyWaitTimeout) {
      return new PodOperationsImpl(this.getContext().withReadyWaitTimeout(readyWaitTimeout), this.context);
   }

   public Loggable withLogWaitTimeout(Integer logWaitTimeout) {
      return this.withReadyWaitTimeout(logWaitTimeout);
   }

   public PortForward portForward(int port, ReadableByteChannel in, WritableByteChannel out) {
      try {
         return (new PortForwarderWebsocket(this.httpClient, this.context.getExecutor(), (long)this.getRequestConfig().getRequestTimeout())).forward(this.getResourceUrl(), port, in, out);
      } catch (Exception e) {
         throw KubernetesClientException.launderThrowable(e);
      }
   }

   public LocalPortForward portForward(int port) {
      return this.portForward(port, 0);
   }

   public LocalPortForward portForward(int port, int localPort) {
      return this.portForward(port, (InetAddress)null, localPort);
   }

   public LocalPortForward portForward(int port, InetAddress localInetAddress, int localPort) {
      try {
         return (new PortForwarderWebsocket(this.httpClient, this.context.getExecutor(), (long)this.getRequestConfig().getRequestTimeout())).forward(this.getResourceUrl(), port, localInetAddress, localPort);
      } catch (MalformedURLException ex) {
         throw KubernetesClientException.launderThrowable(ex);
      }
   }

   public boolean evict() {
      Eviction eviction = ((EvictionBuilder)((EvictionBuilder)((EvictionFluent.MetadataNested)((EvictionFluent.MetadataNested)(new EvictionBuilder()).withNewMetadata().withName(this.getName())).withNamespace(this.getNamespace())).endMetadata()).withDeleteOptions(new DeleteOptions())).build();
      return this.handleEvict(eviction);
   }

   public boolean evict(io.fabric8.kubernetes.api.model.policy.v1.Eviction eviction) {
      return this.handleEvict(eviction);
   }

   private boolean handleEvict(HasMetadata eviction) {
      try {
         if (Utils.isNullOrEmpty(eviction.getMetadata().getNamespace())) {
            throw new KubernetesClientException("Namespace not specified, but operation requires it.");
         } else if (Utils.isNullOrEmpty(eviction.getMetadata().getName())) {
            throw new KubernetesClientException("Name not specified, but operation requires it.");
         } else {
            URL requestUrl = new URL(URLUtils.join(new String[]{this.getResourceUrl().toString(), "eviction"}));
            HttpRequest.Builder requestBuilder = this.httpClient.newHttpRequestBuilder().post("application/json", this.getKubernetesSerialization().asJson(eviction)).url(requestUrl);
            this.handleResponse(requestBuilder, (Class)null);
            return true;
         }
      } catch (KubernetesClientException e) {
         if (e.getCode() != 429) {
            throw e;
         } else {
            return false;
         }
      } catch (IOException exception) {
         throw KubernetesClientException.launderThrowable(this.forOperationType("evict"), exception);
      }
   }

   public EphemeralContainersResource ephemeralContainers() {
      return new PodOperationsImpl(this.getContext(), this.context.withSubresource("ephemeralcontainers"));
   }

   public PodOperationsImpl inContainer(String containerId) {
      return new PodOperationsImpl(this.getContext().withContainerId(containerId), this.context);
   }

   public ExecWatch exec(String... command) {
      String[] actualCommands = command.length >= 1 ? command : EMPTY_COMMAND;

      try {
         URL url = this.getURL("exec", actualCommands);
         return this.setupConnectionToPod(url.toURI());
      } catch (Exception e) {
         throw KubernetesClientException.launderThrowable(this.forOperationType("exec"), e);
      }
   }

   public ExecWatch attach() {
      try {
         URL url = this.getURL("attach", (String[])null);
         return this.setupConnectionToPod(url.toURI());
      } catch (Exception e) {
         throw KubernetesClientException.launderThrowable(this.forOperationType("attach"), e);
      }
   }

   private URL getURL(String operation, String[] commands) throws MalformedURLException {
      Pod fromServer = PodOperationUtil.waitUntilReadyOrTerminal(this, this.getContext().getReadyWaitTimeout() != null ? this.getContext().getReadyWaitTimeout() : 0);
      String url = URLUtils.join(new String[]{this.getResourceUrl().toString(), operation});
      URLUtils.URLBuilder httpUrlBuilder = new URLUtils.URLBuilder(url);
      if (commands != null) {
         for(String cmd : commands) {
            httpUrlBuilder.addQueryParameter("command", cmd);
         }
      }

      PodOperationContext contextToUse = this.getContext();
      contextToUse = contextToUse.withContainerId(this.validateOrDefaultContainerId(contextToUse.getContainerId(), fromServer));
      contextToUse.addQueryParameters(httpUrlBuilder);
      return httpUrlBuilder.build();
   }

   String validateOrDefaultContainerId(String name, Pod pod) {
      if (pod == null) {
         pod = (Pod)this.getItemOrRequireFromServer();
      }

      PodSpec spec = pod.getSpec();
      if (spec == null) {
         spec = new PodSpec();
      }

      if (name == null) {
         List<Container> containers = spec.getContainers();
         if (containers == null || containers.isEmpty()) {
            throw new KubernetesClientException("Pod has no containers!");
         }

         name = (String)pod.getMetadata().getAnnotations().get("kubectl.kubernetes.io/default-container");
         if (name != null && !this.hasContainer(containers, name)) {
            LOG.warn("Default container {} from annotation not found in pod {}", name, pod.getMetadata().getName());
            name = null;
         }

         if (name == null) {
            name = ((Container)containers.get(0)).getName();
            LOG.debug("using first container {} in pod {}", name, pod.getMetadata().getName());
         }
      } else if (!this.hasContainer(spec.getContainers(), name) && !this.hasContainer(spec.getInitContainers(), name) && !this.hasEphemeralContainer(spec.getEphemeralContainers(), name)) {
         throw new KubernetesClientException(String.format("container %s not found in pod %s", name, pod.getMetadata().getName()));
      }

      return name;
   }

   private boolean hasContainer(List containers, String toFind) {
      return containers != null && containers.stream().map(Container::getName).anyMatch((s) -> s.equals(toFind));
   }

   private boolean hasEphemeralContainer(List containers, String toFind) {
      return containers != null && containers.stream().map(EphemeralContainer::getName).anyMatch((s) -> s.equals(toFind));
   }

   private ExecWebSocketListener setupConnectionToPod(URI uri) {
      ExecWebSocketListener execWebSocketListener = new ExecWebSocketListener(this.getContext(), this.context.getExecutor(), this.getKubernetesSerialization());
      CompletableFuture<WebSocket> startedFuture = this.httpClient.newWebSocketBuilder().subprotocol("v4.channel.k8s.io").uri(uri).connectTimeout((long)this.getRequestConfig().getRequestTimeout(), TimeUnit.MILLISECONDS).buildAsync(execWebSocketListener);
      startedFuture.whenComplete((w, t) -> {
         if (t != null) {
            execWebSocketListener.onError(w, t);
         }

      });
      Utils.waitUntilReadyOrFail(startedFuture, (long)this.getRequestConfig().getRequestTimeout(), TimeUnit.MILLISECONDS);
      return execWebSocketListener;
   }

   public PodOperationsImpl file(String file) {
      return new PodOperationsImpl(this.getContext().withFile(file), this.context);
   }

   public PodOperationsImpl dir(String dir) {
      return new PodOperationsImpl(this.getContext().withDir(dir), this.context);
   }

   public boolean copy(Path destination) {
      try {
         if (Utils.isNotNullOrEmpty(this.getContext().getFile())) {
            this.copyFile(this.getContext().getFile(), destination.toFile());
            return true;
         } else if (Utils.isNotNullOrEmpty(this.getContext().getDir())) {
            this.copyDir(this.getContext().getDir(), destination.toFile());
            return true;
         } else {
            throw new IllegalStateException("No file or dir has been specified");
         }
      } catch (Exception e) {
         throw KubernetesClientException.launderThrowable(e);
      }
   }

   public boolean upload(InputStream inputStream) {
      return (Boolean)OptionalDependencyWrapper.wrapRunWithOptionalDependency(() -> {
         try {
            return PodUpload.uploadFileData(this, inputStream);
         } catch (Exception ex) {
            throw KubernetesClientException.launderThrowable(ex);
         }
      }, "TarArchiveOutputStream is provided by commons-compress, an optional dependency. To use the read/copy functionality you must explicitly add commons-compress and commons-io dependency to the classpath.");
   }

   public boolean upload(Path path) {
      return (Boolean)OptionalDependencyWrapper.wrapRunWithOptionalDependency(() -> {
         try {
            return PodUpload.upload(this, path);
         } catch (Exception ex) {
            throw KubernetesClientException.launderThrowable(ex);
         }
      }, "TarArchiveOutputStream is provided by commons-compress, an optional dependency. To use the read/copy functionality you must explicitly add commons-compress and commons-io dependency to the classpath.");
   }

   public InputStream read() {
      try {
         if (Utils.isNotNullOrEmpty(this.getContext().getFile())) {
            return this.readFile(this.getContext().getFile());
         } else if (Utils.isNotNullOrEmpty(this.getContext().getDir())) {
            return this.readTar(this.getContext().getDir());
         } else {
            throw new IllegalStateException("No file or dir has been specified");
         }
      } catch (Exception e) {
         throw KubernetesClientException.launderThrowable(e);
      }
   }

   private String[] readFileCommand(String source) {
      return new String[]{"sh", "-c", String.format("cat %s", shellQuote(source))};
   }

   private InputStream readFile(String source) {
      return this.read(this.readFileCommand(source));
   }

   private void copyFile(String source, File target) {
      File destination = target;
      if (!target.exists() && !target.getParentFile().exists() && !target.getParentFile().mkdirs()) {
         throw KubernetesClientException.launderThrowable(new IOException("Failed to create directory: " + target.getParentFile()));
      } else {
         if (target.isDirectory()) {
            String[] parts = source.split("\\/|\\\\");
            String filename = parts[parts.length - 1];
            destination = target.toPath().resolve(filename).toFile();
         }

         try {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(destination.toPath()));

            try {
               ExecWatch w = this.writingOutput(out).exec(this.readFileCommand(source));
               w.exitCode().get();
            } catch (Throwable var8) {
               try {
                  out.close();
               } catch (Throwable var7) {
                  var8.addSuppressed(var7);
               }

               throw var8;
            }

            out.close();
         } catch (Exception e) {
            throw KubernetesClientException.launderThrowable(e);
         }
      }
   }

   public InputStream readTar(String source) {
      return this.read("sh", "-c", "tar -cf - " + shellQuote(source));
   }

   private InputStream read(String... command) {
      ExecWatch watch = this.redirectingOutput().exec(command);
      return watch.getOutput();
   }

   private void copyDir(final String source, final File target) throws Exception {
      try {
         (new Runnable() {
            public void run() {
               File destination = target;
               if (!destination.isDirectory() && !destination.mkdirs()) {
                  throw KubernetesClientException.launderThrowable(new IOException("Failed to create directory: " + destination));
               } else {
                  try {
                     InputStream is = PodOperationsImpl.this.readTar(source);

                     try {
                        TarArchiveInputStream tis = new TarArchiveInputStream(is);

                        try {
                           for(ArchiveEntry entry = tis.getNextTarEntry(); entry != null; entry = tis.getNextEntry()) {
                              if (tis.canReadEntryData(entry)) {
                                 String normalizedEntryName = FilenameUtils.normalize(entry.getName());
                                 if (normalizedEntryName == null) {
                                    throw new IOException("Tar entry '" + entry.getName() + "' has an invalid name");
                                 }

                                 File f = new File(destination, normalizedEntryName);
                                 if (entry.isDirectory()) {
                                    if (!f.isDirectory() && !f.mkdirs()) {
                                       throw new IOException("Failed to create directory: " + f);
                                    }
                                 } else {
                                    File parent = f.getParentFile();
                                    if (!parent.isDirectory() && !parent.mkdirs()) {
                                       throw new IOException("Failed to create directory: " + f);
                                    }

                                    Files.copy(tis, f.toPath(), new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});
                                 }
                              }
                           }
                        } catch (Throwable var10) {
                           try {
                              tis.close();
                           } catch (Throwable var9) {
                              var10.addSuppressed(var9);
                           }

                           throw var10;
                        }

                        tis.close();
                     } catch (Throwable var11) {
                        if (is != null) {
                           try {
                              is.close();
                           } catch (Throwable var8) {
                              var11.addSuppressed(var8);
                           }
                        }

                        throw var11;
                     }

                     if (is != null) {
                        is.close();
                     }

                  } catch (Exception e) {
                     throw KubernetesClientException.launderThrowable(e);
                  }
               }
            }
         }).run();
      } catch (NoClassDefFoundError var4) {
         throw new KubernetesClientException("TarArchiveInputStream class is provided by commons-compress, an optional dependency. To use the read/copy functionality you must explicitly add commons-compress and commons-io dependency to the classpath.");
      }
   }

   public TtyExecOutputErrorable readingInput(InputStream in) {
      this.checkForPiped(in);
      return new PodOperationsImpl(this.getContext().withIn(in), this.context);
   }

   public PodOperationsImpl redirectingInput() {
      return this.redirectingInput((Integer)null);
   }

   public PodOperationsImpl redirectingInput(Integer bufferSize) {
      return new PodOperationsImpl(this.getContext().toBuilder().redirectingIn(true).bufferSize(bufferSize).build(), this.context);
   }

   public TtyExecErrorable writingOutput(OutputStream out) {
      this.checkForPiped(out);
      return new PodOperationsImpl(this.getContext().toBuilder().output(new PodOperationContext.StreamContext(out)).build(), this.context);
   }

   public TtyExecErrorable redirectingOutput() {
      return new PodOperationsImpl(this.getContext().toBuilder().output(new PodOperationContext.StreamContext()).build(), this.context);
   }

   public TtyExecErrorChannelable writingError(OutputStream err) {
      this.checkForPiped(err);
      return new PodOperationsImpl(this.getContext().toBuilder().error(new PodOperationContext.StreamContext(err)).build(), this.context);
   }

   public TtyExecErrorChannelable redirectingError() {
      return new PodOperationsImpl(this.getContext().toBuilder().error(new PodOperationContext.StreamContext()).build(), this.context);
   }

   public TtyExecable writingErrorChannel(OutputStream errChannel) {
      this.checkForPiped(errChannel);
      return new PodOperationsImpl(this.getContext().toBuilder().errorChannel(new PodOperationContext.StreamContext(errChannel)).build(), this.context);
   }

   public TtyExecable redirectingErrorChannel() {
      return new PodOperationsImpl(this.getContext().toBuilder().errorChannel(new PodOperationContext.StreamContext()).build(), this.context);
   }

   public ExecListenable withTTY() {
      return new PodOperationsImpl(this.getContext().withTty(true), this.context);
   }

   public Loggable withPrettyOutput() {
      return new PodOperationsImpl(this.getContext().withPrettyOutput(true), this.context);
   }

   public PrettyLoggable tailingLines(int withTailingLines) {
      return new PodOperationsImpl(this.getContext().withTailingLines(withTailingLines), this.context);
   }

   public TailPrettyLoggable sinceTime(String sinceTimestamp) {
      return new PodOperationsImpl(this.getContext().withSinceTimestamp(sinceTimestamp), this.context);
   }

   public TailPrettyLoggable sinceSeconds(int sinceSeconds) {
      return new PodOperationsImpl(this.getContext().withSinceSeconds(sinceSeconds), this.context);
   }

   public TimeTailPrettyLoggable terminated() {
      return new PodOperationsImpl(this.getContext().withTerminatedStatus(true), this.context);
   }

   public Execable usingListener(ExecListener execListener) {
      return new PodOperationsImpl(this.getContext().withExecListener(execListener), this.context);
   }

   public BytesLimitTerminateTimeTailPrettyLoggable limitBytes(int limitBytes) {
      return new PodOperationsImpl(this.getContext().withLimitBytes(limitBytes), this.context);
   }

   public BytesLimitTerminateTimeTailPrettyLoggable usingTimestamps() {
      return new PodOperationsImpl(this.getContext().withTimestamps(true), this.context);
   }

   public static String shellQuote(String value) {
      return "'" + value.replace("'", "'\\''") + "'";
   }

   public PodOperationsImpl terminateOnError() {
      return new PodOperationsImpl(this.getContext().toBuilder().terminateOnError(true).build(), this.context);
   }

   public Pod patchReadinessGateStatus(Map readiness) {
      Map<String, PodCondition> conditions = new LinkedHashMap();
      Pod pod = (Pod)this.getItemOrRequireFromServer();
      if (pod.getStatus() == null) {
         pod.setStatus(new PodStatus());
      }

      pod.getStatus().getConditions().forEach((pc) -> conditions.put(pc.getType(), pc));

      for(Map.Entry entry : readiness.entrySet()) {
         if (entry.getValue() == null) {
            conditions.remove(entry.getKey());
         } else {
            PodCondition condition = (PodCondition)conditions.get(entry.getKey());
            String valueString = (Boolean)entry.getValue() ? "True" : "False";
            if (condition == null) {
               conditions.put((String)entry.getKey(), ((PodConditionBuilder)((PodConditionBuilder)(new PodConditionBuilder()).withStatus(valueString)).withType((String)entry.getKey())).build());
            } else {
               condition.setStatus(valueString);
            }
         }
      }

      pod.getStatus().setConditions(new ArrayList(conditions.values()));
      return (Pod)((PodResource)this.resource(pod)).subresource("status").patch();
   }
}
