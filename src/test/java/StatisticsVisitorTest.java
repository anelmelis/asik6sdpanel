import model.DirectoryNode;
import model.FileNode;
import model.FileSystemWalker;
import org.junit.jupiter.api.Test;
import visitor.StatisticsVisitor;
import visitor.TotalSizeVisitor;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatisticsVisitorTest {

    @Test
    void shouldCountFilesDirectoriesAndDepth() {

        DirectoryNode root = new DirectoryNode("Root");
        DirectoryNode documents = new DirectoryNode("Documents");
        DirectoryNode archive = new DirectoryNode("Archive");

        root.addChild(new FileNode("photo.jpg", 500));
        root.addChild(documents);

        documents.addChild(new FileNode("notes.txt", 200));
        documents.addChild(archive);

        archive.addChild(new FileNode("old.txt", 100));

        StatisticsVisitor visitor = new StatisticsVisitor();
        FileSystemWalker walker = new FileSystemWalker();

        walker.walk(root, visitor);

        assertEquals(3, visitor.getFileCount());
        assertEquals(3, visitor.getDirectoryCount());
        assertEquals(3, visitor.getMaxDepth());
    }







    @Test
    void shouldHandleEmptyDirectory() {

        DirectoryNode root = new DirectoryNode("EmptyRoot");

        FileSystemWalker walker = new FileSystemWalker();

        StatisticsVisitor statistics = new StatisticsVisitor();
        walker.walk(root, statistics);

        assertEquals(0, statistics.getFileCount());
        assertEquals(1, statistics.getDirectoryCount());
        assertEquals(0, statistics.getMaxDepth());

        TotalSizeVisitor sizeVisitor = new TotalSizeVisitor();
        walker.walk(root, sizeVisitor);

        assertEquals(0, sizeVisitor.getTotalSize());
    }

}
