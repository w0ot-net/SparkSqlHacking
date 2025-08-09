package io.netty.internal.tcnative;

public interface SSLSessionCache {
   boolean sessionCreated(long var1, long var3);

   long getSession(long var1, byte[] var3);
}
