package model;
public class FileSystemWalker {

    public void walk(Element element, Visitor visitor) {
        element.accept(visitor);
        walkChildren(element, visitor);
    }

    private void walkChildren(Element element, Visitor visitor) {
        element.accept(new Visitor() {
            @Override
            public void visit(FileNode file) {
            }

            @Override
            public void visit(DirectoryNode directory) {
                for (Element child : directory.getChildren()) {
                    walk(child, visitor);
                }
            }
        });
    }
}
