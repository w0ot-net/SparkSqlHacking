package com.esotericsoftware.kryo.util;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.StreamFactory;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.io.UnsafeInput;
import com.esotericsoftware.kryo.io.UnsafeOutput;
import java.io.InputStream;
import java.io.OutputStream;

public class FastestStreamFactory implements StreamFactory {
   private static boolean isUnsafe = UnsafeUtil.unsafe() != null;

   public Input getInput() {
      return (Input)(isUnsafe ? new UnsafeInput() : new Input());
   }

   public Input getInput(int bufferSize) {
      return (Input)(isUnsafe ? new UnsafeInput(bufferSize) : new Input(bufferSize));
   }

   public Input getInput(byte[] buffer) {
      return (Input)(isUnsafe ? new UnsafeInput(buffer) : new Input(buffer));
   }

   public Input getInput(byte[] buffer, int offset, int count) {
      return (Input)(isUnsafe ? new UnsafeInput(buffer, offset, count) : new Input(buffer, offset, count));
   }

   public Input getInput(InputStream inputStream) {
      return (Input)(isUnsafe ? new UnsafeInput(inputStream) : new Input(inputStream));
   }

   public Input getInput(InputStream inputStream, int bufferSize) {
      return (Input)(isUnsafe ? new UnsafeInput(inputStream, bufferSize) : new Input(inputStream, bufferSize));
   }

   public Output getOutput() {
      return (Output)(isUnsafe ? new UnsafeOutput() : new Output());
   }

   public Output getOutput(int bufferSize) {
      return (Output)(isUnsafe ? new UnsafeOutput(bufferSize) : new Output(bufferSize));
   }

   public Output getOutput(int bufferSize, int maxBufferSize) {
      return (Output)(isUnsafe ? new UnsafeOutput(bufferSize, maxBufferSize) : new Output(bufferSize, maxBufferSize));
   }

   public Output getOutput(byte[] buffer) {
      return (Output)(isUnsafe ? new UnsafeOutput(buffer) : new Output(buffer));
   }

   public Output getOutput(byte[] buffer, int maxBufferSize) {
      return (Output)(isUnsafe ? new UnsafeOutput(buffer, maxBufferSize) : new Output(buffer, maxBufferSize));
   }

   public Output getOutput(OutputStream outputStream) {
      return (Output)(isUnsafe ? new UnsafeOutput(outputStream) : new Output(outputStream));
   }

   public Output getOutput(OutputStream outputStream, int bufferSize) {
      return (Output)(isUnsafe ? new UnsafeOutput(outputStream, bufferSize) : new Output(outputStream, bufferSize));
   }

   public void setKryo(Kryo kryo) {
   }
}
