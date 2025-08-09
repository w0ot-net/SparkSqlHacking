package org.sparkproject.guava.io;

import java.nio.file.FileSystemException;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public final class InsecureRecursiveDeleteException extends FileSystemException {
   public InsecureRecursiveDeleteException(@CheckForNull String file) {
      super(file, (String)null, "unable to guarantee security of recursive delete");
   }
}
