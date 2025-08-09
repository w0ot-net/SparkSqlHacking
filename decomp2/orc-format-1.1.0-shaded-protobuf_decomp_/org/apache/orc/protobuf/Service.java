package org.apache.orc.protobuf;

public interface Service {
   Descriptors.ServiceDescriptor getDescriptorForType();

   void callMethod(Descriptors.MethodDescriptor method, RpcController controller, Message request, RpcCallback done);

   Message getRequestPrototype(Descriptors.MethodDescriptor method);

   Message getResponsePrototype(Descriptors.MethodDescriptor method);
}
