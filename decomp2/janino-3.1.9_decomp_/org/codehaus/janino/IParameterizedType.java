package org.codehaus.janino;

public interface IParameterizedType extends IType {
   IType[] getActualTypeArguments();

   IType getRawType();
}
