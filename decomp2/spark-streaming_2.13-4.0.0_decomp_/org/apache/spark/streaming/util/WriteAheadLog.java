package org.apache.spark.streaming.util;

import java.nio.ByteBuffer;
import java.util.Iterator;
import org.apache.spark.annotation.DeveloperApi;

@DeveloperApi
public abstract class WriteAheadLog {
   public abstract WriteAheadLogRecordHandle write(ByteBuffer var1, long var2);

   public abstract ByteBuffer read(WriteAheadLogRecordHandle var1);

   public abstract Iterator readAll();

   public abstract void clean(long var1, boolean var3);

   public abstract void close();
}
