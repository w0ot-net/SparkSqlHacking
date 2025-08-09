package org.apache.curator.framework.recipes.cache;

interface Operation {
   void invoke() throws Exception;
}
