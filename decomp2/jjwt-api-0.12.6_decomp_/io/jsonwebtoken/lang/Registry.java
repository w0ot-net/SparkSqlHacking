package io.jsonwebtoken.lang;

import java.util.Map;

public interface Registry extends Map {
   Object forKey(Object var1) throws IllegalArgumentException;
}
