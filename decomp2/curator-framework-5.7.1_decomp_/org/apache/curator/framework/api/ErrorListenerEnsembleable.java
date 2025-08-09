package org.apache.curator.framework.api;

public interface ErrorListenerEnsembleable extends Ensembleable {
   Ensembleable withUnhandledErrorListener(UnhandledErrorListener var1);
}
