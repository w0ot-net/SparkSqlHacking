package org.sparkproject.jetty.util.preventers;

import javax.imageio.ImageIO;

public class AppContextLeakPreventer extends AbstractLeakPreventer {
   public void prevent(ClassLoader loader) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("Pinning classloader for AppContext.getContext() with{} ", loader);
      }

      ImageIO.getUseCache();
   }
}
