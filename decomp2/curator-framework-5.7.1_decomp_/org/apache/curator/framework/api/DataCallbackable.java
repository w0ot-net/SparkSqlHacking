package org.apache.curator.framework.api;

import org.apache.zookeeper.AsyncCallback;

public interface DataCallbackable {
   Object usingDataCallback(AsyncCallback.DataCallback var1, Object var2);
}
