package com.esotericsoftware.kryo;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.InputStream;
import java.io.OutputStream;

public interface StreamFactory {
   Input getInput();

   Input getInput(int var1);

   Input getInput(byte[] var1);

   Input getInput(byte[] var1, int var2, int var3);

   Input getInput(InputStream var1);

   Input getInput(InputStream var1, int var2);

   Output getOutput();

   Output getOutput(int var1);

   Output getOutput(int var1, int var2);

   Output getOutput(byte[] var1);

   Output getOutput(byte[] var1, int var2);

   Output getOutput(OutputStream var1);

   Output getOutput(OutputStream var1, int var2);

   void setKryo(Kryo var1);
}
