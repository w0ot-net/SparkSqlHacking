package org.apache.curator.framework.recipes.queue;

public interface MultiItem {
   Object nextItem() throws Exception;
}
