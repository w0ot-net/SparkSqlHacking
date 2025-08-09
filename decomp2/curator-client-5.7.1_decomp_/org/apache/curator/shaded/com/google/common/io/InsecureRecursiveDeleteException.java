package org.apache.curator.shaded.com.google.common.io;

import java.nio.file.FileSystemException;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public final class InsecureRecursiveDeleteException extends FileSystemException {
   public InsecureRecursiveDeleteException(@CheckForNull String file) {
      super(file, (String)null, "unable to guarantee security of recursive delete");
   }
}
