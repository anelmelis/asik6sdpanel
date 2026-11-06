package model;

public class FileNode implements Element {

    private final String name;
    private final long size;
    private DirectoryNode parent;

    public FileNode(String name, long size) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "File name cannot be empty");
        }

        if (size < 0) {
            throw new IllegalArgumentException(
                    "File size cannot be negative");
        }

        this.name = name;
        this.size = size;
    }

    @Override
    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    @Override
    public DirectoryNode getParent() {
        return parent;
    }

    void attachTo(DirectoryNode parent) {
        if (parent == null) {
            throw new IllegalArgumentException(
                    "Parent cannot be null");
        }

        if (this.parent != null) {
            throw new IllegalArgumentException(
                    "File already has a parent");
        }

        this.parent = parent;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
