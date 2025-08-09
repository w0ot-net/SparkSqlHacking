package org.apache.curator.framework.api;

public interface ChildrenDeletable extends BackgroundVersionable {
   BackgroundVersionable deletingChildrenIfNeeded();
}
