package org.glassfish.jersey.server.model;

public abstract class AbstractResourceModelVisitor implements ResourceModelVisitor {
   public void visitResource(Resource resource) {
   }

   public void visitChildResource(Resource resource) {
   }

   public void visitResourceMethod(ResourceMethod method) {
   }

   public void visitInvocable(Invocable invocable) {
   }

   public void visitMethodHandler(MethodHandler methodHandler) {
   }

   public void visitResourceHandlerConstructor(HandlerConstructor constructor) {
   }

   public void visitResourceModel(ResourceModel resourceModel) {
   }

   public void visitRuntimeResource(RuntimeResource runtimeResource) {
   }
}
