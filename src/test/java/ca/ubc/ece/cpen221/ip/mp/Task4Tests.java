package ca.ubc.ece.cpen221.ip.mp;

import ca.ubc.ece.cpen221.graphs.core.Graph;
import ca.ubc.ece.cpen221.graphs.core.Vertex;
import ca.ubc.ece.cpen221.graphs.one.TwitterAnalysis;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task4Tests {

    //Test in small text file
    @Test
    public void task4Test1() throws IOException {
        TwitterAnalysis ta = new TwitterAnalysis("datasets/task4test1.txt");
        Set<String> actual = ta.commonInfluencers("245", "300");
        String[] expected = {"157", "100"};
        boolean equal = true;

        Assert.assertEquals(expected.length, actual.size());

         for (int i = 0; i < expected.length; i++) {
             if(!actual.contains(expected[i])) {
                 equal = false;
                 break;
             }
         }

         Assert.assertTrue(equal);
    }

    //Both users in file and follow a common user
    @Test
    public void task4Test2() throws IOException {
        TwitterAnalysis ta = new TwitterAnalysis("datasets/twitter.txt");
        Set<String> actual = ta.commonInfluencers("37722200", "33232300");
        String[] expected = {"25407657"};
        boolean equal = true;

        Assert.assertEquals(expected.length, actual.size());

        for (int i = 0; i < expected.length; i++) {
            if(!actual.contains(expected[i])) {
                equal = false;
                break;
            }
        }
        Assert.assertTrue(equal);
    }

    //Both users not in file
    @Test
    public void task4Test3() throws IOException {
        TwitterAnalysis ta = new TwitterAnalysis("datasets/twitter.txt");
        Set<String> actual = ta.commonInfluencers("1", "2");
        String[] expected = {};
        boolean equal = true;

        Assert.assertEquals(expected.length, actual.size());
    }

    //Both the same user
    @Test
    public void task4Test4() throws IOException {
        TwitterAnalysis ta = new TwitterAnalysis("datasets/twitter.txt");
        Set<String> actual = ta.commonInfluencers("37722200", "37722200");
        String[] expected = {};
        boolean equal = true;

        Assert.assertEquals(expected.length, actual.size());
    }

    //Test in small text file
    @Test
    public void task4Test5() throws IOException {
        TwitterAnalysis ta = new TwitterAnalysis("datasets/task4test5.txt");
        int actual = ta.numRetweets("A","C");
        int expected = 1;
        boolean equal = true;

        Assert.assertEquals(expected,actual);
    }

    //UserB follows userA
    @Test
    public void task4Test6() throws IOException {
        TwitterAnalysis ta = new TwitterAnalysis("datasets/twitter.txt");
        int actual = ta.numRetweets("9103242","84505452");
        int expected = 0;
        System.out.println("Actual: " + actual);
        System.out.println("Expected: " + expected);
        Assert.assertEquals(expected,actual);
    }

    //Both users are not in file
    @Test
    public void task4Test7() throws IOException {
        TwitterAnalysis ta = new TwitterAnalysis("datasets/twitter.txt");
        int actual = ta.numRetweets("1","2");
        int expected = -1;
        Assert.assertEquals(expected,actual);
    }

    //Both users are the same
    @Test
    public void task4Test8() throws IOException {
        TwitterAnalysis ta = new TwitterAnalysis("datasets/twitter.txt");
        int actual = ta.numRetweets("9103242","9103242");
        int expected = -1;
        Assert.assertEquals(expected,actual);
    }

    //Expected exception
    @Test
    public void throwException() throws IOException {
            TwitterAnalysis ta = new TwitterAnalysis("twitter.txt");
            int actual = ta.numRetweets("9103242","9103242");
            int expected = -1;
            Assert.assertEquals(expected,actual);
    }

    //null file expected "File is empty" IOException
    @Test
    public void nullFile() throws IOException{
        TwitterAnalysis ta = new TwitterAnalysis("datasets/nullFile.txt");
        int actual = ta.numRetweets("9103242","9103242");
        int expected = -1;
        Assert.assertEquals(expected,actual);
    }

    //Prints commonInfluencers on commandLine
    @Test
    public void mainTest() throws IOException {
        String[] args = {"datasets/twitter.txt", "commonInfluencers", "37722200", "33232300"};
        TwitterAnalysis ta = new TwitterAnalysis("datasets/twitter.txt");
        ta.main(args);
        String[] expected = {"25407657"};
    }

    //Print numRetweets on command line
    @Test
    public void mainTest2() throws IOException {
        String[] args = {"datasets/twitter.txt", "numRetweets", "37722200", "37722200"};
        TwitterAnalysis ta = new TwitterAnalysis("datasets/twitter.txt");
        ta.main(args);
        int expected = -1;
    }

}
