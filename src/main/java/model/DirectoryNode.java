package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DirectoryNode implements Element {

    private final String name;
    private final List<Element> children = new ArrayList<>();
    private DirectoryNode parent;

    public DirectoryNode(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Directory name cannot be empty");
        }

        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public DirectoryNode getParent() {
        return parent;
    }

    public List<Element> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public void addChild(Element child) {
        if (child == null) {
            throw new IllegalArgumentException(
                    "Child cannot be null");
        }

        if (child.getParent() != null) {
            throw new IllegalArgumentException(
                    "Element already has a parent");
        }

        for (Element existing : children) {
            if (existing.getName().equals(child.getName())) {
                throw new IllegalArgumentException(
                        "Duplicate child name");
            }
        }

        checkForCycle(child);

        child.accept(new Visitor() {
            @Override
            public void visit(FileNode file) {
                file.attachTo(DirectoryNode.this);
            }

            @Override
            public void visit(DirectoryNode directory) {
                directory.attachTo(DirectoryNode.this);
            }
        });

        children.add(child);
    }

    private void checkForCycle(Element child) {
        DirectoryNode current = this;

        while (current != null) {
            if (current == child) {
                throw new IllegalArgumentException(
                        "Cycle detected");
            }

            current = current.getParent();
        }
    }

    void attachTo(DirectoryNode parent) {
        if (parent == null) {
            throw new IllegalArgumentException(
                    "Parent cannot be null");
        }

        if (this.parent != null) {
            throw new IllegalArgumentException(
                    "Directory already has a parent");
        }

        this.parent = parent;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

