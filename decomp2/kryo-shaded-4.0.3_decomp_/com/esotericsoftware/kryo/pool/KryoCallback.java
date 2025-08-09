package com.esotericsoftware.kryo.pool;

import com.esotericsoftware.kryo.Kryo;

public interface KryoCallback {
   Object execute(Kryo var1);
}
