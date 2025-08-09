package org.codehaus.commons.compiler.util.resource;

import java.io.IOException;
import org.codehaus.commons.nullanalysis.Nullable;

public abstract class ListableResourceFinder extends ResourceFinder {
   @Nullable
   public abstract Iterable list(String var1, boolean var2) throws IOException;
}
