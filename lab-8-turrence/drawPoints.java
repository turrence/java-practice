import processing.core.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class drawPoints extends PApplet {

	public void settings() {
	size(500, 500);
	}
  
	public void setup() {
		background(180);
		noLoop();
	}

	public void draw() {

	double x, y;
   
	String[] lines = loadStrings("drawMe.txt");
	//println("there are " + lines.length);
		for (int i=0; i < lines.length; i++){
			if (lines[i].length() > 0 ) {
					String[] words= lines[i].split(",");
					x = Double.parseDouble(words[0]);
					y = Double.parseDouble(words[1]);
					//println("xy: " + x + " " + y);
					ellipse((int)x, (int)y, 1, 1);
			}
		}
	}

	private static void populatePointsList(
			final String filename,
            final List<Point> points
	)
			throws FileNotFoundException
	{
		try (Scanner input = new Scanner(new File(filename)))
		{
			processFile(input, points);
		}
	}

	private static void processFile(Scanner input, final List<Point> points){
		while(input.hasNextLine()){
			String[] words = input.nextLine().split(", ");
/*			String str = "";
			for (String w : words)
			    str += w +" ";
			System.out.println(str);*/
			if(words.length == 0)
				return;
			else {
				points.add(new Point(
						Double.parseDouble(words[0]),
						Double.parseDouble(words[1]),
						Integer.parseInt(words[2]))
				);
			}
		}
	}

	private static void writePoints(final List<Point> points){
		try {
			PrintStream ps = new PrintStream("drawMe.txt");

			for(Point p : points){
				ps.println(p.x + ", " + p.y + ", " + p.z);
			}

		} catch(Exception e){
			System.out.println("nope");
		}

	}

	private static List<Point> filterPoints(final List<Point> points){
        return points.stream()
                .filter((Point p ) -> !(p.z > 2.0))
                .map(p -> new Point(p.x * 0.5, p.y * 0.5, p.z / 2))
                .map(p -> new Point(p.x - 150, p.y - 37, p.z ))
                .collect(Collectors.toList());
    }

	public static void main(String args[]) {

	    final List<Point> points = new ArrayList<>();
        try {
            populatePointsList("positions.txt", points);
            List<Point> newPts = filterPoints(points);
            writePoints(newPts);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PApplet.main("drawPoints");
   }
}
