package org.apache.logging.log4j.core.async;

import java.util.concurrent.BlockingQueue;

public interface BlockingQueueFactory {
   String ELEMENT_TYPE = "BlockingQueueFactory";

   BlockingQueue create(int capacity);
}
