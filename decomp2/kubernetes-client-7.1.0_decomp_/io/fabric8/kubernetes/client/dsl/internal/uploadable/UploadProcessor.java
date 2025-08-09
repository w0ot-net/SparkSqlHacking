package io.fabric8.kubernetes.client.dsl.internal.uploadable;

import java.io.IOException;
import java.io.OutputStream;

@FunctionalInterface
interface UploadProcessor {
   void process(OutputStream var1) throws IOException;
}
