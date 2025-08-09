package com.univocity.parsers.common.processor.core;

public final class NoopProcessor extends AbstractProcessor {
   public static final Processor instance = new NoopProcessor();

   private NoopProcessor() {
   }
}
