package org.datanucleus.cache;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import org.datanucleus.NucleusContext;
import org.datanucleus.util.SoftValueMap;

public class SoftLevel2Cache extends WeakLevel2Cache {
   private static final long serialVersionUID = 1204825081286087936L;

   public SoftLevel2Cache(NucleusContext nucleusCtx) {
      this.apiAdapter = nucleusCtx.getApiAdapter();
      this.pinnedCache = new HashMap();
      this.unpinnedCache = new SoftValueMap();
   }

   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
      in.defaultReadObject();
      this.unpinnedCache = new SoftValueMap();
   }
}
