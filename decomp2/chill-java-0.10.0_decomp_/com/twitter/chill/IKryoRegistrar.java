package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import java.io.Serializable;

public interface IKryoRegistrar extends Serializable {
   void apply(Kryo var1);
}
