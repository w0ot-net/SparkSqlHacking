package com.twitter.chill;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d:Qa\u0002\u0005\t\u0002=1Q!\u0005\u0005\t\u0002IAQ!G\u0001\u0005\u0002iAQaG\u0001\u0005\u0002qAQ\u0001I\u0001\u0005\u0002qAQ!I\u0001\u0005\u0002\tBQAJ\u0001\u0005\u0002\t\nab\u0013:z_N+'/[1mSj,'O\u0003\u0002\n\u0015\u0005)1\r[5mY*\u00111\u0002D\u0001\bi^LG\u000f^3s\u0015\u0005i\u0011aA2p[\u000e\u0001\u0001C\u0001\t\u0002\u001b\u0005A!AD&ss>\u001cVM]5bY&TXM]\n\u0003\u0003M\u0001\"\u0001F\f\u000e\u0003UQ\u0011AF\u0001\u0006g\u000e\fG.Y\u0005\u00031U\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\bF\u0001\u0010\u0003\u0015)W\u000e\u001d;z+\u0005i\u0002C\u0001\t\u001f\u0013\ty\u0002B\u0001\tLef|\u0017J\\:uC:$\u0018.\u0019;pe\u0006Q!/Z4jgR,'/\u001a3\u0002;I,w-[:uKJ\u001cu\u000e\u001c7fGRLwN\\*fe&\fG.\u001b>feN,\u0012a\t\t\u0003!\u0011J!!\n\u0005\u0003\u001d%[%/_8SK\u001eL7\u000f\u001e:be\u0006Y!/Z4jgR,'/\u00117m\u0001"
)
public final class KryoSerializer {
   public static IKryoRegistrar registerAll() {
      return KryoSerializer$.MODULE$.registerAll();
   }

   public static IKryoRegistrar registerCollectionSerializers() {
      return KryoSerializer$.MODULE$.registerCollectionSerializers();
   }

   public static KryoInstantiator registered() {
      return KryoSerializer$.MODULE$.registered();
   }

   public static KryoInstantiator empty() {
      return KryoSerializer$.MODULE$.empty();
   }
}
