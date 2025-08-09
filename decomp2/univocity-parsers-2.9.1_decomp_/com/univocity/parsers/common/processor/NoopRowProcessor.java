package com.univocity.parsers.common.processor;

public final class NoopRowProcessor extends AbstractRowProcessor {
   public static final RowProcessor instance = new NoopRowProcessor();

   private NoopRowProcessor() {
   }
}
