package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.InputChunked;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.io.OutputChunked;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

public class DeflateSerializer extends Serializer {
   private final Serializer serializer;
   private boolean noHeaders = true;
   private int compressionLevel = 4;

   public DeflateSerializer(Serializer serializer) {
      this.serializer = serializer;
   }

   public void write(Kryo kryo, Output output, Object object) {
      OutputChunked outputChunked = new OutputChunked(output, 256);
      Deflater deflater = new Deflater(this.compressionLevel, this.noHeaders);

      try {
         DeflaterOutputStream deflaterStream = new DeflaterOutputStream(outputChunked, deflater);
         Output deflaterOutput = new Output(deflaterStream, 256);
         this.serializer.write(kryo, deflaterOutput, object);
         deflaterOutput.flush();
         deflaterStream.finish();
      } catch (IOException ex) {
         throw new KryoException(ex);
      } finally {
         deflater.end();
      }

      outputChunked.endChunks();
   }

   public Object read(Kryo kryo, Input input, Class type) {
      Inflater inflater = new Inflater(this.noHeaders);

      Object var6;
      try {
         InflaterInputStream inflaterStream = new InflaterInputStream(new InputChunked(input, 256), inflater);
         var6 = this.serializer.read(kryo, new Input(inflaterStream, 256), type);
      } finally {
         inflater.end();
      }

      return var6;
   }

   public void setNoHeaders(boolean noHeaders) {
      this.noHeaders = noHeaders;
   }

   public void setCompressionLevel(int compressionLevel) {
      this.compressionLevel = compressionLevel;
   }

   public Object copy(Kryo kryo, Object original) {
      return this.serializer.copy(kryo, original);
   }
}
