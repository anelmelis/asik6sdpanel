import model.DirectoryNode;
import model.FileNode;
import model.FileSystemWalker;
import org.junit.jupiter.api.Test;
import visitor.TotalSizeVisitor;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TotalSizeVisitorTest {

    @Test
    void shouldCalculateTotalSizeOfNestedFiles() {

        DirectoryNode root = new DirectoryNode("Root");
        DirectoryNode documents = new DirectoryNode("Documents");

        root.addChild(new FileNode("image.png", 450));
        root.addChild(documents);

        documents.addChild(new FileNode("notes.txt", 150));
        documents.addChild(new FileNode("report.pdf", 400));

        TotalSizeVisitor visitor = new TotalSizeVisitor();
        FileSystemWalker walker = new FileSystemWalker();

        walker.walk(root, visitor);

        assertEquals(1000, visitor.getTotalSize());
    }






    @Test
    void shouldNotAccumulateSizeBetweenRuns() {

        DirectoryNode root = new DirectoryNode("Root");

        root.addChild(new FileNode("first.txt", 250));
        root.addChild(new FileNode("second.txt", 150));

        TotalSizeVisitor visitor = new TotalSizeVisitor();
        FileSystemWalker walker = new FileSystemWalker();

        walker.walk(root, visitor);
        assertEquals(400, visitor.getTotalSize());

        walker.walk(root, visitor);
        assertEquals(400, visitor.getTotalSize());
    }

}
