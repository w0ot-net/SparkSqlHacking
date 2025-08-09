package io.airlift.compress.zstd;

class CompressionContext {
   public final CompressionParameters parameters;
   public final RepeatedOffsets offsets = new RepeatedOffsets();
   public final BlockCompressionState blockCompressionState;
   public final SequenceStore sequenceStore;
   public final SequenceEncodingContext sequenceEncodingContext = new SequenceEncodingContext();
   public final HuffmanCompressionContext huffmanContext = new HuffmanCompressionContext();

   public CompressionContext(CompressionParameters parameters, long baseAddress, int inputSize) {
      this.parameters = parameters;
      int windowSize = Math.max(1, Math.min(parameters.getWindowSize(), inputSize));
      int blockSize = Math.min(131072, windowSize);
      int divider = parameters.getSearchLength() == 3 ? 3 : 4;
      int maxSequences = blockSize / divider;
      this.sequenceStore = new SequenceStore(blockSize, maxSequences);
      this.blockCompressionState = new BlockCompressionState(parameters, baseAddress);
   }

   public void slideWindow(int slideWindowSize) {
      Util.checkArgument(slideWindowSize > 0, "slideWindowSize must be positive");
      this.blockCompressionState.slideWindow(slideWindowSize);
   }

   public void commit() {
      this.offsets.commit();
      this.huffmanContext.saveChanges();
   }
}
