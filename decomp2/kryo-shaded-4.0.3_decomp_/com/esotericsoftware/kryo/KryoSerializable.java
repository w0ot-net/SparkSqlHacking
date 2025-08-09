package com.esotericsoftware.kryo;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public interface KryoSerializable {
   void write(Kryo var1, Output var2);

   void read(Kryo var1, Input var2);
}
