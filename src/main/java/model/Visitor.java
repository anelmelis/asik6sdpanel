package model;

public interface Visitor {
    void visit(FileNode file);
    void visit(DirectoryNode directory);
}