import model.DirectoryNode;
import model.FileNode;
import model.FileSystemWalker;
import visitor.TotalSizeVisitor;
import visitor.SearchVisitor;
import visitor.StatisticsVisitor;

public class Main {

    public static void main(String[] args) {

        DirectoryNode root = new DirectoryNode("MyProject");

        FileNode photo = new FileNode("photo.jpg", 500);
        FileNode notes = new FileNode("notes.txt", 200);

        DirectoryNode documents = new DirectoryNode("Documents");
        FileNode report = new FileNode("report.pdf", 300);

        root.addChild(photo);
        root.addChild(notes);
        root.addChild(documents);
        documents.addChild(report);

        TotalSizeVisitor sizeVisitor = new TotalSizeVisitor();

        FileSystemWalker walker = new FileSystemWalker();
        walker.walk(root, sizeVisitor);

        System.out.println("Total size: "
                + sizeVisitor.getTotalSize() + " bytes");


        SearchVisitor searchVisitor = new SearchVisitor(".txt", 100);

        walker.walk(root, searchVisitor);

        System.out.println("Search results:");

        for (String path : searchVisitor.getMatches()) {
            System.out.println(path);
        }


        StatisticsVisitor statisticsVisitor = new StatisticsVisitor();

        walker.walk(root, statisticsVisitor);

        System.out.println("Statistics:");
        System.out.println("Files: " + statisticsVisitor.getFileCount());
        System.out.println("Directories: " + statisticsVisitor.getDirectoryCount());
        System.out.println("Maximum depth: " + statisticsVisitor.getMaxDepth());


    }
}
