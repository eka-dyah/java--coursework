package PerimeterAssignment;

import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int numPoints = 0;
        for (Point point : s.getPoints()) {
            numPoints = numPoints + 1;
        }
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        int count = 0;
        double length = 0;
        double averageLength = 0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            count = count + 1;
            double currDist = prevPt.distance(currPt);
            length = length + currDist;
            averageLength = length / count;
            prevPt = currPt;
        }
        return averageLength;
    }

    public double getLargestSide(Shape s) {
        double largestSide = 0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if (largestSide > currDist) {
                largestSide = largestSide;
            } else {
                largestSide = currDist;
            }
            prevPt = currPt;
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        double largestX = 0;
        for (Point currPt : s.getPoints()) {
            if (largestX > currPt.getX()) {
                largestX = largestX;
            } else {
                largestX = currPt.getX();
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        double largestPerim = 0;
        DirectoryResource dr = new DirectoryResource();

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if (largestPerim > length) {
                largestPerim = largestPerim;
            } else {
                largestPerim = length;
            }
        }

        return largestPerim;
    }

    public String getFileWithLargestPerimeter() {
        double largestPerim = 0;
        DirectoryResource dr = new DirectoryResource();
        File file = null;

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if (largestPerim > length) {
                largestPerim = largestPerim;
                file = file;
            } else {
                largestPerim = length;
                file = f;
            }
        }

        return file.getName();
    }

    public void testPerimeter () {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            System.out.println("perimeter = " + length);
            System.out.println("Total points = " + getNumPoints(s));
            System.out.println("Average length = " + getAverageLength(s));
            System.out.println("Largest side = " + getLargestSide(s));
            System.out.println("Largest X = " + getLargestX(s) + "\n");
        }
    }
    
    public void testPerimeterMultipleFiles() {
        System.out.println("Largest Perimeter = " + getLargestPerimeterMultipleFiles());
    }

    public void testFileWithLargestPerimeter() {
        System.out.println("Largest Perimeter = " + getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
//        pr.testPerimeterMultipleFiles();
//        pr.testFileWithLargestPerimeter();
    }
}
