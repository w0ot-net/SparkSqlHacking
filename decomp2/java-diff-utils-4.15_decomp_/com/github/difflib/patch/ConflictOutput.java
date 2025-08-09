package com.github.difflib.patch;

import java.io.Serializable;
import java.util.List;

@FunctionalInterface
public interface ConflictOutput extends Serializable {
   void processConflict(VerifyChunk var1, AbstractDelta var2, List var3) throws PatchFailedException;
}
