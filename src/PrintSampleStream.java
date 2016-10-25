package twitter4j.examples.stream;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.Arrays;

/**
 * <p>This is a code example of Twitter4J Streaming API - sample method support.<br>
 * Usage: java twitter4j.examples.PrintSampleStream<br>
 * </p>
 *
 * @author Yusuke Yamamoto - yusuke at mac.com
 */
public class PrintSampleStream {
    public static String tweetStorage = "";
    public static int tweetCounter = 0;
    /*
     * Main entry of this application.
     *
     * @param args arguments doesn't take effect with this example
     * @throws TwitterException when Twitter service or network is unavailable
     *
     */


    public static void twitterInitializer() throws Exception{
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("KRY9InTAgKLrhdpU1P7Fgmn5C")
                .setOAuthConsumerSecret("wggbxdhoaDkBfA3ysAkgxcw1BwNJyb9eWTkkKwsKwP7hSPrQAf")
                .setOAuthAccessToken("71225425-qBAtHwsQ8UHHijJhOUWXhLcMhs2RzYwuqqMST4jbE")
                .setOAuthAccessTokenSecret("6gK1oeBD6VyZCHMJyKwpPzRRVGrjtoJElJYQ7fYv6p6Hs");
        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {

                if (status.getText().contains("#Connect4")){
                    String tweets = "@" + status.getUser().getScreenName() + " - " + status.getText();
                    System.out.println(tweets);
                        tweetStorage = "@" + status.getUser().getScreenName() + " - " + status.getText();
                }
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                //System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                //System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                //System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                //System.out.println("Got stall warning:" + warning);
            }

            @Override
            public void onException(Exception ex) {
                //ex.printStackTrace();
            }

        };
        FilterQuery fq = new FilterQuery();

        String keywords[] = {"connect4", "Connect4", "#Connect2048"};

        fq.track(keywords);

        twitterStream.addListener(listener);
        twitterStream.filter(fq);

    }
    public static String getTweetStorage(){
        return tweetStorage;
    }

}
