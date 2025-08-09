package org.apache.orc.protobuf;

public interface RpcChannel {
   void callMethod(Descriptors.MethodDescriptor method, RpcController controller, Message request, Message responsePrototype, RpcCallback done);
}
