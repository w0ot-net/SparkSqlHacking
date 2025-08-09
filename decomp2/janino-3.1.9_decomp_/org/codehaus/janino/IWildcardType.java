package org.codehaus.janino;

import org.codehaus.commons.nullanalysis.Nullable;

public interface IWildcardType extends IType {
   IType getUpperBound();

   @Nullable
   IType getLowerBound();
}
