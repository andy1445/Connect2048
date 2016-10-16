package twitter4j.examples.stream;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;


final class PrintSampleStream {
    /**
     * Main entry of this application.
     *
     * @param args arguments doesn't take effect with this example
     * @throws TwitterException when Twitter service or network is unavailable
     */
    public static void main(String[] args) throws TwitterException {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("KRY9InTAgKLrhdpU1P7Fgmn5C")
                .setOAuthConsumerSecret("wggbxdhoaDkBfA3ysAkgxcw1BwNJyb9eWTkkKwsKwP7hSPrQAf")
                .setOAuthAccessToken("**************************************************")
                .setOAuthAccessTokenSecret("******************************************");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        Twitter t = TwitterFactory.getSingleton();
        Query query = new Query("source:twitter4j yusukey");
        QueryResult result = twitter.search(query);
        for (Status status : result.getTweets()) {
            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
        }
    }
}