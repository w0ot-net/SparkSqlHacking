package io.fabric8.kubernetes.client.dsl;

public interface ServerSideApplicable {
   Object serverSideApply();

   ServerSideApplicable fieldManager(String var1);

   ServerSideApplicable forceConflicts();
}
