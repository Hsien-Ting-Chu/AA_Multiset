import java.io.*;
import java.util.*;


/**
 * Framework to test the multiset implementations.
 * 
 * @author jkcchan
 */
public class Tester
{
	/** Name of class, used in error messages. */
	protected static final String progName = "MultisetTester";
	
	/** Standard outstream. */
	protected static final PrintStream outStream = System.out;

	/**
	 * Print help/usage message.
	 */
	public static void usage(String progName) {
		System.err.println(progName + ": <implementation> [fileName to output search results to]");
		System.err.println("<implementation> = <linkedlist | sortedlinkedlist | bst| hash | baltree>");
		System.exit(1);
	} // end of usage


	/**
	 * Process the operation commands coming from inReader, and updates the multiset according to the operations.
	 * 
	 * @param inReader Input reader where the operation commands are coming from.
	 * @param searchOutWriter Where to output the results of search.
	 * @param multiset The multiset which the operations are executed on.
	 * 
	 * @throws IOException If there is an exception to do with I/O.
	 */
	public static void processOperations(BufferedReader inReader, PrintWriter searchOutWriter, Multiset<String> multiset) 
		throws IOException
	{
		String line;
		int lineNum = 1;
		boolean bQuit = false;
		
		// continue reading in commands until we either receive the quit signal or there are no more input commands
		while (!bQuit && (line = inReader.readLine()) != null) {
			String[] tokens = line.split(" ");

			// check if there is at least an operation command
			if (tokens.length < 1) {
				System.err.println(lineNum + ": not enough tokens.");
				lineNum++;
				continue;
			}

			String command = tokens[0];
			// determine which operation to execute
			switch (command.toUpperCase()) {
				// add
				case "A":
					if (tokens.length == 2) {
						multiset.add(tokens[1]);
					}
					else {
						System.err.println(lineNum + ": not enough tokens.");
					}
					break;
				// search
				case "S":
					if (tokens.length == 2) {
						int foundNumber = multiset.search(tokens[1]);
						searchOutWriter.println(tokens[1] + " " + foundNumber);
					}
					else {
						// we print -1 to indicate error for automated testing
						searchOutWriter.println(-1);
						System.err.println(lineNum + ": not enough tokens.");
					}
					break;
				// remove one instance
				case "RO":
					if (tokens.length == 2) {
						multiset.removeOne(tokens[1]);
					}
					else {
						System.err.println(lineNum + ": not enough tokens.");
					}
					break;
				// remove all instances
				case "RA":
					if (tokens.length == 2) {
						multiset.removeAll(tokens[1]);
					}
					else {
						System.err.println(lineNum + ": not enough tokens.");
					}
					break;		
				// print
				case "P":
					multiset.print(outStream);
					break;
				// quit
				case "Q":
					bQuit = true;
					break;
				default:
					System.err.println(lineNum + ": Unknown command.");
			}

			lineNum++;
		}

	} // end of processOperations() 


	/**
	 * Main method.  Determines which implementation to test.
	 */
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		System.out.println("Existing data file: ");
		String existFilename = sc.next();
				
		System.out.println("Input file name: ");
		String inputFilename = sc.next();
		
		System.out.println("Output file name: ");
		String searchOutFilename = sc.next();
		
		Multiset<String> multiset = null;
		String[] implementationType = {"linkedlist", "sortedlinkedlist", "bst", "hash", "baltree"};
//		String[] implementationType = {"bst", "hash", "baltree"};
		
		for(int i=0;i<implementationType.length;i++){
			switch(implementationType[i]) {
				case "linkedlist":
					multiset = new LinkedListMultiset<String>();
					break;
				case "sortedlinkedlist":
					multiset = new SortedLinkedListMultiset<String>();
					break;
				case "bst":
					multiset = new BstMultiset<String>();
					break;
				case "hash":
					multiset = new HashMultiset<String>();
					break;
				case "baltree":
					multiset = new BalTreeMultiset<String>();
					break;
				default:
					System.err.println("Unknown implmementation type.");
					usage(progName);
			}
	
	
			// construct in and output streams/writers/readers, then process each operation.
			try {
				FileReader fr = new FileReader(inputFilename);
				BufferedReader inReader = new BufferedReader(fr);
				PrintWriter searchOutWriter = new PrintWriter(System.out, true);
				
				if (searchOutFilename != null) {
					searchOutWriter = new PrintWriter(new FileWriter(searchOutFilename), true);
				}
				// put data into MulitSet first
				processOperations(new BufferedReader(new FileReader(existFilename)), searchOutWriter, multiset);
				// process the operations
				long startTime = System.nanoTime();
				processOperations(inReader, searchOutWriter, multiset);
				long endTime = System.nanoTime();
				System.out.println("Done.\n"
						+ implementationType[i] +"'s time taken = " + ((double)(endTime - startTime)) / Math.pow(10, 9) + " sec");
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
		
		sc.close();

	} // end of main()

} // end of class MultisetTester
