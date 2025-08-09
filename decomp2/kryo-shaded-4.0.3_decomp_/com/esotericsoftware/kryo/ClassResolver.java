package com.esotericsoftware.kryo;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public interface ClassResolver {
   void setKryo(Kryo var1);

   Registration register(Registration var1);

   Registration registerImplicit(Class var1);

   Registration getRegistration(Class var1);

   Registration getRegistration(int var1);

   Registration writeClass(Output var1, Class var2);

   Registration readClass(Input var1);

   void reset();
}
