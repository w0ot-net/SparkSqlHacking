package org.sparkproject.spark_core.protobuf;

public interface RpcChannel {
   void callMethod(Descriptors.MethodDescriptor method, RpcController controller, Message request, Message responsePrototype, RpcCallback done);
}
