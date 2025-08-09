package io.fabric8.kubernetes.client.dsl.internal;

import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.dsl.ExecListener;
import io.fabric8.kubernetes.client.utils.URLUtils;
import java.io.InputStream;
import java.io.OutputStream;
import lombok.Generated;

public class PodOperationContext {
   private String containerId;
   private StreamContext output;
   private StreamContext error;
   private StreamContext errorChannel;
   private boolean redirectingIn;
   private InputStream in;
   private boolean tty;
   private boolean terminatedStatus;
   private boolean timestamps;
   private String sinceTimestamp;
   private Integer sinceSeconds;
   private Integer tailingLines;
   private Integer readyWaitTimeout;
   private boolean prettyOutput;
   private ExecListener execListener;
   private Integer limitBytes;
   private Integer bufferSize;
   private String file;
   private String dir;
   private boolean terminateOnError;
   private boolean rolling;

   public PodOperationContext withContainerId(String containerId) {
      return this.toBuilder().containerId(containerId).build();
   }

   public PodOperationContext withIn(InputStream in) {
      return this.toBuilder().in(in).build();
   }

   public PodOperationContext withTty(boolean tty) {
      return this.toBuilder().tty(tty).build();
   }

   public PodOperationContext withTerminatedStatus(boolean terminatedStatus) {
      return this.toBuilder().terminatedStatus(terminatedStatus).build();
   }

   public PodOperationContext withTimestamps(boolean timestamps) {
      return this.toBuilder().timestamps(timestamps).build();
   }

   public PodOperationContext withSinceTimestamp(String sinceTimestamp) {
      return this.toBuilder().sinceTimestamp(sinceTimestamp).build();
   }

   public PodOperationContext withSinceSeconds(Integer sinceSeconds) {
      return this.toBuilder().sinceSeconds(sinceSeconds).build();
   }

   public PodOperationContext withTailingLines(Integer tailingLines) {
      return this.toBuilder().tailingLines(tailingLines).build();
   }

   public PodOperationContext withPrettyOutput(boolean prettyOutput) {
      return this.toBuilder().prettyOutput(prettyOutput).build();
   }

   public PodOperationContext withExecListener(ExecListener execListener) {
      return this.toBuilder().execListener(execListener).build();
   }

   public PodOperationContext withLimitBytes(Integer limitBytes) {
      return this.toBuilder().limitBytes(limitBytes).build();
   }

   public PodOperationContext withBufferSize(Integer bufferSize) {
      return this.toBuilder().bufferSize(bufferSize).build();
   }

   public PodOperationContext withFile(String file) {
      return this.toBuilder().file(file).build();
   }

   public PodOperationContext withDir(String dir) {
      return this.toBuilder().dir(dir).build();
   }

   public PodOperationContext withReadyWaitTimeout(Integer readyWaitTimeout) {
      return this.toBuilder().readyWaitTimeout(readyWaitTimeout).build();
   }

   public String getLogParameters() {
      StringBuilder sb = new StringBuilder();
      sb.append("log?pretty=").append(this.prettyOutput);
      if (this.containerId != null && !this.containerId.isEmpty()) {
         sb.append("&container=").append(this.containerId);
      }

      if (this.terminatedStatus) {
         sb.append("&previous=true");
      }

      if (this.sinceSeconds != null) {
         sb.append("&sinceSeconds=").append(this.sinceSeconds);
      } else if (this.sinceTimestamp != null) {
         sb.append("&sinceTime=").append(URLUtils.encodeToUTF(this.sinceTimestamp).replace("%3A", ":"));
      }

      if (this.tailingLines != null) {
         sb.append("&tailLines=").append(this.tailingLines);
      }

      if (this.limitBytes != null) {
         sb.append("&limitBytes=").append(this.limitBytes);
      }

      if (this.timestamps) {
         sb.append("&timestamps=true");
      }

      return sb.toString();
   }

   public void addQueryParameters(URLUtils.URLBuilder httpUrlBuilder) {
      if (this.containerId != null && !this.containerId.isEmpty()) {
         httpUrlBuilder.addQueryParameter("container", this.containerId);
      }

      if (this.tty) {
         httpUrlBuilder.addQueryParameter("tty", "true");
      }

      boolean usingStream = false;
      if (this.in != null || this.redirectingIn) {
         httpUrlBuilder.addQueryParameter("stdin", "true");
         usingStream = true;
      }

      boolean debug = ExecWebSocketListener.LOGGER.isDebugEnabled();
      if (this.output != null || debug) {
         httpUrlBuilder.addQueryParameter("stdout", "true");
         usingStream = true;
      }

      if (this.error != null || this.terminateOnError || debug) {
         httpUrlBuilder.addQueryParameter("stderr", "true");
         usingStream = true;
      }

      if (!usingStream) {
         throw new KubernetesClientException("Pod operation is not valid unless an in, out, or error stream is used.");
      }
   }

   @Generated
   public static PodOperationContextBuilder builder() {
      return new PodOperationContextBuilder();
   }

   @Generated
   public PodOperationContextBuilder toBuilder() {
      return (new PodOperationContextBuilder()).containerId(this.containerId).output(this.output).error(this.error).errorChannel(this.errorChannel).redirectingIn(this.redirectingIn).in(this.in).tty(this.tty).terminatedStatus(this.terminatedStatus).timestamps(this.timestamps).sinceTimestamp(this.sinceTimestamp).sinceSeconds(this.sinceSeconds).tailingLines(this.tailingLines).readyWaitTimeout(this.readyWaitTimeout).prettyOutput(this.prettyOutput).execListener(this.execListener).limitBytes(this.limitBytes).bufferSize(this.bufferSize).file(this.file).dir(this.dir).terminateOnError(this.terminateOnError).rolling(this.rolling);
   }

   @Generated
   public PodOperationContext() {
   }

   @Generated
   public PodOperationContext(String containerId, StreamContext output, StreamContext error, StreamContext errorChannel, boolean redirectingIn, InputStream in, boolean tty, boolean terminatedStatus, boolean timestamps, String sinceTimestamp, Integer sinceSeconds, Integer tailingLines, Integer readyWaitTimeout, boolean prettyOutput, ExecListener execListener, Integer limitBytes, Integer bufferSize, String file, String dir, boolean terminateOnError, boolean rolling) {
      this.containerId = containerId;
      this.output = output;
      this.error = error;
      this.errorChannel = errorChannel;
      this.redirectingIn = redirectingIn;
      this.in = in;
      this.tty = tty;
      this.terminatedStatus = terminatedStatus;
      this.timestamps = timestamps;
      this.sinceTimestamp = sinceTimestamp;
      this.sinceSeconds = sinceSeconds;
      this.tailingLines = tailingLines;
      this.readyWaitTimeout = readyWaitTimeout;
      this.prettyOutput = prettyOutput;
      this.execListener = execListener;
      this.limitBytes = limitBytes;
      this.bufferSize = bufferSize;
      this.file = file;
      this.dir = dir;
      this.terminateOnError = terminateOnError;
      this.rolling = rolling;
   }

   @Generated
   public String getContainerId() {
      return this.containerId;
   }

   @Generated
   public StreamContext getOutput() {
      return this.output;
   }

   @Generated
   public StreamContext getError() {
      return this.error;
   }

   @Generated
   public StreamContext getErrorChannel() {
      return this.errorChannel;
   }

   @Generated
   public boolean isRedirectingIn() {
      return this.redirectingIn;
   }

   @Generated
   public InputStream getIn() {
      return this.in;
   }

   @Generated
   public boolean isTty() {
      return this.tty;
   }

   @Generated
   public boolean isTerminatedStatus() {
      return this.terminatedStatus;
   }

   @Generated
   public boolean isTimestamps() {
      return this.timestamps;
   }

   @Generated
   public String getSinceTimestamp() {
      return this.sinceTimestamp;
   }

   @Generated
   public Integer getSinceSeconds() {
      return this.sinceSeconds;
   }

   @Generated
   public Integer getTailingLines() {
      return this.tailingLines;
   }

   @Generated
   public Integer getReadyWaitTimeout() {
      return this.readyWaitTimeout;
   }

   @Generated
   public boolean isPrettyOutput() {
      return this.prettyOutput;
   }

   @Generated
   public ExecListener getExecListener() {
      return this.execListener;
   }

   @Generated
   public Integer getLimitBytes() {
      return this.limitBytes;
   }

   @Generated
   public Integer getBufferSize() {
      return this.bufferSize;
   }

   @Generated
   public String getFile() {
      return this.file;
   }

   @Generated
   public String getDir() {
      return this.dir;
   }

   @Generated
   public boolean isTerminateOnError() {
      return this.terminateOnError;
   }

   @Generated
   public boolean isRolling() {
      return this.rolling;
   }

   @Generated
   public static class PodOperationContextBuilder {
      @Generated
      private String containerId;
      @Generated
      private StreamContext output;
      @Generated
      private StreamContext error;
      @Generated
      private StreamContext errorChannel;
      @Generated
      private boolean redirectingIn;
      @Generated
      private InputStream in;
      @Generated
      private boolean tty;
      @Generated
      private boolean terminatedStatus;
      @Generated
      private boolean timestamps;
      @Generated
      private String sinceTimestamp;
      @Generated
      private Integer sinceSeconds;
      @Generated
      private Integer tailingLines;
      @Generated
      private Integer readyWaitTimeout;
      @Generated
      private boolean prettyOutput;
      @Generated
      private ExecListener execListener;
      @Generated
      private Integer limitBytes;
      @Generated
      private Integer bufferSize;
      @Generated
      private String file;
      @Generated
      private String dir;
      @Generated
      private boolean terminateOnError;
      @Generated
      private boolean rolling;

      @Generated
      PodOperationContextBuilder() {
      }

      @Generated
      public PodOperationContextBuilder containerId(String containerId) {
         this.containerId = containerId;
         return this;
      }

      @Generated
      public PodOperationContextBuilder output(StreamContext output) {
         this.output = output;
         return this;
      }

      @Generated
      public PodOperationContextBuilder error(StreamContext error) {
         this.error = error;
         return this;
      }

      @Generated
      public PodOperationContextBuilder errorChannel(StreamContext errorChannel) {
         this.errorChannel = errorChannel;
         return this;
      }

      @Generated
      public PodOperationContextBuilder redirectingIn(boolean redirectingIn) {
         this.redirectingIn = redirectingIn;
         return this;
      }

      @Generated
      public PodOperationContextBuilder in(InputStream in) {
         this.in = in;
         return this;
      }

      @Generated
      public PodOperationContextBuilder tty(boolean tty) {
         this.tty = tty;
         return this;
      }

      @Generated
      public PodOperationContextBuilder terminatedStatus(boolean terminatedStatus) {
         this.terminatedStatus = terminatedStatus;
         return this;
      }

      @Generated
      public PodOperationContextBuilder timestamps(boolean timestamps) {
         this.timestamps = timestamps;
         return this;
      }

      @Generated
      public PodOperationContextBuilder sinceTimestamp(String sinceTimestamp) {
         this.sinceTimestamp = sinceTimestamp;
         return this;
      }

      @Generated
      public PodOperationContextBuilder sinceSeconds(Integer sinceSeconds) {
         this.sinceSeconds = sinceSeconds;
         return this;
      }

      @Generated
      public PodOperationContextBuilder tailingLines(Integer tailingLines) {
         this.tailingLines = tailingLines;
         return this;
      }

      @Generated
      public PodOperationContextBuilder readyWaitTimeout(Integer readyWaitTimeout) {
         this.readyWaitTimeout = readyWaitTimeout;
         return this;
      }

      @Generated
      public PodOperationContextBuilder prettyOutput(boolean prettyOutput) {
         this.prettyOutput = prettyOutput;
         return this;
      }

      @Generated
      public PodOperationContextBuilder execListener(ExecListener execListener) {
         this.execListener = execListener;
         return this;
      }

      @Generated
      public PodOperationContextBuilder limitBytes(Integer limitBytes) {
         this.limitBytes = limitBytes;
         return this;
      }

      @Generated
      public PodOperationContextBuilder bufferSize(Integer bufferSize) {
         this.bufferSize = bufferSize;
         return this;
      }

      @Generated
      public PodOperationContextBuilder file(String file) {
         this.file = file;
         return this;
      }

      @Generated
      public PodOperationContextBuilder dir(String dir) {
         this.dir = dir;
         return this;
      }

      @Generated
      public PodOperationContextBuilder terminateOnError(boolean terminateOnError) {
         this.terminateOnError = terminateOnError;
         return this;
      }

      @Generated
      public PodOperationContextBuilder rolling(boolean rolling) {
         this.rolling = rolling;
         return this;
      }

      @Generated
      public PodOperationContext build() {
         return new PodOperationContext(this.containerId, this.output, this.error, this.errorChannel, this.redirectingIn, this.in, this.tty, this.terminatedStatus, this.timestamps, this.sinceTimestamp, this.sinceSeconds, this.tailingLines, this.readyWaitTimeout, this.prettyOutput, this.execListener, this.limitBytes, this.bufferSize, this.file, this.dir, this.terminateOnError, this.rolling);
      }

      @Generated
      public String toString() {
         return "PodOperationContext.PodOperationContextBuilder(containerId=" + this.containerId + ", output=" + this.output + ", error=" + this.error + ", errorChannel=" + this.errorChannel + ", redirectingIn=" + this.redirectingIn + ", in=" + this.in + ", tty=" + this.tty + ", terminatedStatus=" + this.terminatedStatus + ", timestamps=" + this.timestamps + ", sinceTimestamp=" + this.sinceTimestamp + ", sinceSeconds=" + this.sinceSeconds + ", tailingLines=" + this.tailingLines + ", readyWaitTimeout=" + this.readyWaitTimeout + ", prettyOutput=" + this.prettyOutput + ", execListener=" + this.execListener + ", limitBytes=" + this.limitBytes + ", bufferSize=" + this.bufferSize + ", file=" + this.file + ", dir=" + this.dir + ", terminateOnError=" + this.terminateOnError + ", rolling=" + this.rolling + ")";
      }
   }

   public static final class StreamContext {
      private OutputStream outputStream;

      public StreamContext(OutputStream outputStream) {
         this.outputStream = outputStream;
      }

      public StreamContext() {
      }

      @Generated
      public OutputStream getOutputStream() {
         return this.outputStream;
      }
   }
}
