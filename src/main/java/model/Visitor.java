package model;

public interface Visitor extends TraversalContext {

    void visit(FileNode file);

    void visit(DirectoryNode directory);

    @Override
    default void setContext(String path, int depth) {
    }

    default void reset() {
    }
}
