package io.fabric8.kubernetes.client.dsl;

import java.io.InputStream;
import java.nio.file.Path;

public interface CopyOrReadable {
   boolean upload(Path var1);

   boolean upload(InputStream var1);

   InputStream read();

   boolean copy(Path var1);

   CopyOrReadable withReadyWaitTimeout(Integer var1);
}
