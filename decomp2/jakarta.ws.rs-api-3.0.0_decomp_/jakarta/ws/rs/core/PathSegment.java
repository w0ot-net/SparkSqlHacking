package jakarta.ws.rs.core;

public interface PathSegment {
   String getPath();

   MultivaluedMap getMatrixParameters();
}
