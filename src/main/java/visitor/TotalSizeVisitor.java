package visitor;
import model.DirectoryNode;
import model.FileNode;
import model.Visitor;

public class TotalSizeVisitor implements Visitor {

    private long totalSize = 0;

    @Override
    public void visit(FileNode file) {
        totalSize += file.getSize();
    }

    @Override
    public void visit(DirectoryNode directory) {
    }

    public long getTotalSize() {
        return totalSize;
    }

    @Override
    public void reset() {
        totalSize = 0;
    }
}
