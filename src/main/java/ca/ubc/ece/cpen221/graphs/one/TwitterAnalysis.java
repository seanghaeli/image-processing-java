package ca.ubc.ece.cpen221.graphs.one;

import ca.ubc.ece.cpen221.graphs.core.Graph;
import ca.ubc.ece.cpen221.graphs.core.Vertex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


import static ca.ubc.ece.cpen221.graphs.one.Algorithms.distance;

public class TwitterAnalysis {
    // TODO: Implement this class
    static private File objReader;
    static private ArrayList<String> twitterData = new ArrayList<>();

    /*RI:
        filename should send full path
        file should not be null
        twitterData transfers objreader data if not null
    AF: Uses twitterData to find commonFollowing and numRetweets*/
    /**
     * Constructor TwitterAnalysis
     * @param fileName input file name, including path and path is not null
     * @throws IOException if file does not exist
     * Creates file object
     */
    public TwitterAnalysis(String fileName) throws IOException {
        objReader = new File(fileName);
        String line;
        if (!objReader.exists()) {
            throw new IOException("File doesn't exist");
        }

        BufferedReader br = new BufferedReader(new FileReader(objReader));
        while((line = br.readLine()) != null) {
            twitterData.add(line);
        }
        br.close();
    }

    /**
     * Assume in text file a -> b, b follows a
     * @param userA string representation of a user a in twitter file
     * @param userB string representation of a user b in twitter file
     * @return Set of common users (commonInfluencers) that both user a and b follow
     *         or empty Set if there are no commonInfluencers
     */
    public Set<String> commonInfluencers(String userA, String userB) {
        // TODO: Implement this method
        HashSet<String> usersA = new HashSet<>();
        HashSet<String> usersB = new HashSet<>();
        Set<String> commonUsers = new HashSet<>();
        String line = null;

        for(String s: twitterData) {
            String[] data = s.split(" -> ");
            if (data[1].equals(userA)) {
                usersA.add(data[0]);
            } else if (data[1].equals(userB)) {
                usersB.add(data[0]);
            }
        }

        for (String user: usersA) {
            if (usersB.contains(user)) {
                commonUsers.add(user);
            }
        }
        return commonUsers;
    }

    /**
     * Assume that a tweet will appear in b's feed if the tweet
     * originated from one of the users that b follows
     * @param userA - string representation of user that posted tweet, String is not null
     * @param userB - string representation of user that views tweet, String is not null
     * @return - int number of retweets it would take userB to see userA's tweet,
     *           -1 if a user is not in file or if it never appears in userB's feed or
     *           user's are the same
     */
    public int numRetweets(String userA, String userB) {
        AdjacencyListGraph<String> whoIsFollowingWho = new AdjacencyListGraph<>();
        Vertex<String> b = new Vertex<>(userB,userB);
        Vertex<String> a = new Vertex<>(userA,userA);

        if (userA.equals(userB)) {
            return -1;
        }

        for(String s: twitterData) {
            String[] currentUsers = s.split(" -> ");

            Vertex<String> leftUser1 = new Vertex<>(currentUsers[0],currentUsers[0]);
            Vertex<String> rightUser1 = new Vertex<>(currentUsers[1],currentUsers[1]);

            whoIsFollowingWho.addVertex(rightUser1);
            whoIsFollowingWho.addVertex(leftUser1);
            whoIsFollowingWho.addEdge(rightUser1,leftUser1);
        }

        if (!whoIsFollowingWho.getVertices().contains(b) || !whoIsFollowingWho.getVertices().contains(a)) {
            return -1;
        }

        return distance(whoIsFollowingWho,b,a) - 1;
    }


    public static void main(String[] args) {
        /*
            main() should take four arguments:
            - The first argument should be a filename for a file holding
              a Twitter dataset.
            - The second should be one of "commonInfluencers" or "numRetweets".
            - The next two arguments should be identifiers for userA and userB.
            Then main() should invoke the appropriate method and write the result to
            standard output (often, the terminal). For "numRetweets", the output to
            standard output should be just the int. For "commonInfluencers", each
            of the influencers should be written to standard output, one per line,
            with no other text.
         */

        String fileName = args[0];
        String function = args[1];
        String userA = args[2];
        String userB = args[3];
        TwitterAnalysis twitterObj;
        try {
            twitterObj = new TwitterAnalysis(fileName);
            if (function.equals("commonInfluencers")) {
                Set<String> common = twitterObj.commonInfluencers(userA, userB);
                for (String user: common) {
                    System.out.println(user);
                }
            } else if (function.equals("numRetweets")) {
                int num = twitterObj.numRetweets(userA, userB);
                System.out.println(num);
            } else {
                System.out.println("Error, please try again");
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }

    }

}
