package io.netty.handler.ssl;

import io.netty.util.internal.ObjectUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class OpenSslCertificateCompressionConfig implements Iterable {
   private final List pairList;

   private OpenSslCertificateCompressionConfig(AlgorithmConfig... pairs) {
      this.pairList = Collections.unmodifiableList(Arrays.asList(pairs));
   }

   public Iterator iterator() {
      return this.pairList.iterator();
   }

   public static Builder newBuilder() {
      return new Builder();
   }

   public static final class Builder {
      private final List algorithmList;

      private Builder() {
         this.algorithmList = new ArrayList();
      }

      public Builder addAlgorithm(OpenSslCertificateCompressionAlgorithm algorithm, AlgorithmMode mode) {
         this.algorithmList.add(new AlgorithmConfig(algorithm, mode));
         return this;
      }

      public OpenSslCertificateCompressionConfig build() {
         return new OpenSslCertificateCompressionConfig((AlgorithmConfig[])this.algorithmList.toArray(new AlgorithmConfig[0]));
      }
   }

   public static final class AlgorithmConfig {
      private final OpenSslCertificateCompressionAlgorithm algorithm;
      private final AlgorithmMode mode;

      private AlgorithmConfig(OpenSslCertificateCompressionAlgorithm algorithm, AlgorithmMode mode) {
         this.algorithm = (OpenSslCertificateCompressionAlgorithm)ObjectUtil.checkNotNull(algorithm, "algorithm");
         this.mode = (AlgorithmMode)ObjectUtil.checkNotNull(mode, "mode");
      }

      public AlgorithmMode mode() {
         return this.mode;
      }

      public OpenSslCertificateCompressionAlgorithm algorithm() {
         return this.algorithm;
      }
   }

   public static enum AlgorithmMode {
      Compress,
      Decompress,
      Both;
   }
}
