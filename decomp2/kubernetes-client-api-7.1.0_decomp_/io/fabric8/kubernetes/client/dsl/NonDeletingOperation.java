package io.fabric8.kubernetes.client.dsl;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public interface NonDeletingOperation extends CreateOrReplaceable, EditReplacePatchable, Replaceable, ItemReplacable, ItemWritableOperation, ServerSideApplicable {
   Object createOr(Function var1);

   NonDeletingOperation unlock();

   Object editStatus(UnaryOperator var1);

   Object patchStatus();

   EditReplacePatchable subresource(String var1);
}
