package com.maker.helpers.sql;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeScopes;
import com.google.api.services.youtubeAnalytics.YouTubeAnalyticsScopes;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.security.GeneralSecurityException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Shared class used by every sample. Contains methods for authorizing a user and caching credentials.
 */
public class Auth {

    
    private static final String CREDENTIALS_DIRECTORY = ".oauth-credentials";
    
    private static final String APPLICATION_NAME = "APP_NAME";

   
    
    /** Path to the Service Account's Private Key file */
    

    public static GoogleCredential authorize(String ne) throws GeneralSecurityException, IOException  {

    String SERVICE_ACCOUNT_PKCS12_FILE_PATH = "conf/API Project-"+ne+".p12";
    String SERVICE_ACCOUNT_EMAIL = "";
    
    if (ne.equalsIgnoreCase("RPMNetworks"))
    {
    SERVICE_ACCOUNT_EMAIL = "456099706041-jl47bjp9jp3vroaidb4qsatouc8cnuqu@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("thegamestation"))
    {
    SERVICE_ACCOUNT_EMAIL = "341888358417-qnfe9bgrpho9gpmohl72s1suomk5f6p9@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("thestation+user"))
    {
    SERVICE_ACCOUNT_EMAIL = "854897134689-855p9cib6dh3pj0k2aqj6jg35okkmsv1@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("Maker_Claiming"))
    {
    SERVICE_ACCOUNT_EMAIL = "417563555762-pmupuvrubs9tjorufusrldnuesp19img@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("AudioMicro1+vid"))
    {
    SERVICE_ACCOUNT_EMAIL = "507913499240-coq0khj0tuim2r6mp274f8i2iukmgo2e@developer.gserviceaccount.com";
    } 
    if (ne.equalsIgnoreCase("adrevuppm"))
    {
    SERVICE_ACCOUNT_EMAIL = "350585347788-g1v94gmfp8s5mp5al5uiick89rirn79e@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("adrev615"))
    {
    SERVICE_ACCOUNT_EMAIL = "666845310159-ld02tiprp04qblfa491c7opdk4ejbqhs@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("adrevpub"))
    {
    SERVICE_ACCOUNT_EMAIL = "248523947039-oi10dsgost4d1ncgd6hlmjt5ssubuj6n@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("AdRevEnt_managed"))
    {
    SERVICE_ACCOUNT_EMAIL = "894259606201-15of4lhfun71b2tru3p35arp986mdb8c@developer.gserviceaccount.com";
    }
  
    if (ne.equalsIgnoreCase("RumblefishInc+vid"))
    {
    SERVICE_ACCOUNT_EMAIL = "606270922761-02dpn86br86a11tlt4e4cev4pfafsh2k@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("rumblefish_cdbaby"))
    {
    SERVICE_ACCOUNT_EMAIL = "959101832699-6lb20d73fso9niulotka4a5h5rj21tip@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("JustForLaughsTV"))
    {
    SERVICE_ACCOUNT_EMAIL = "662998965428-r7hetou866e49kpsbbc5rnk57f3fai3c@developer.gserviceaccount.com";
    }   
    if (ne.equalsIgnoreCase("Justforlaughsfestival"))
    {
    SERVICE_ACCOUNT_EMAIL = "879230264136-nv46uv9jucs61ql9judmn4nmgj4kg45p@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("Maker_Music"))
    {
    SERVICE_ACCOUNT_EMAIL = "505736072521-k3bseb9gvjb6d8dtv93h9vnjvovdvf9k@developer.gserviceaccount.com";
    }
    
    if (ne.equalsIgnoreCase("Maker_Music_Publishing"))
    {
    SERVICE_ACCOUNT_EMAIL = "551623814927-e3hnd6eauoqp8ngdp4jgoeldu44vt8i0@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("nashentertainment+vid"))
    {
    SERVICE_ACCOUNT_EMAIL = "177371345987-tlhjiit2oqtlf677tfeltnjd88rv81u7@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("rumblefish"))
    {
    SERVICE_ACCOUNT_EMAIL = "147570250271-ektic2s600jeld3qjhsnmurebbbbl6ns@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("StorytellerMedia"))
    {
    SERVICE_ACCOUNT_EMAIL = "1009764640368-k9v75h7rp5aokp1imln4mes4kac056jl@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("FunnyFuse"))
    {
    SERVICE_ACCOUNT_EMAIL = "506654018825-11sjqacu1j0eta97gm0a85aii409att7@developer.gserviceaccount.com";
    }
    
    if (ne.equalsIgnoreCase("disney_abc"))
    {
    SERVICE_ACCOUNT_EMAIL = "392169724214-ib6ffrf9jog90afubjtf44pkvbhsjgni@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("disney_abcnews"))
    {
    SERVICE_ACCOUNT_EMAIL = "36682646237-6tlhlm8sbckqsm9n2bvpueijv0c4p8h2@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("disney_abcsoapnet"))
    {
    SERVICE_ACCOUNT_EMAIL = "398840075644-anv9imfbcl075u1m16g5jjv6fnekftcj@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("datawarehousedisney35@makerstudios.com"))
    {
    SERVICE_ACCOUNT_EMAIL = "936181372687-3veq03f5e0fu9kgs9ba9a9r17giv3sco@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("disney_digisynd"))
    {
    SERVICE_ACCOUNT_EMAIL = "1072979477364-ktq27ehd1h76rigbfhrh500ltea4h8v4@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("disney"))
    {
    SERVICE_ACCOUNT_EMAIL = "427075719453-9jkfc09kkho1mjisr5ij2vnm1n38ong2@developer.gserviceaccount.com";
    }
    
    if (ne.equalsIgnoreCase("disney_latam"))
    {
    SERVICE_ACCOUNT_EMAIL = "830074289968-a4p0rsnghkrftig2ojei9as3os2ps62a@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("Disney_Animation"))
    {
    SERVICE_ACCOUNT_EMAIL = "748785666735-ih3dj00av175bqpjl1gjjg3l4rm90m5l@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("disney_disneychannel"))
    {
    SERVICE_ACCOUNT_EMAIL = "405911859943-fapauge77fmg11smnnonuoe104g3h0jq@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("Disney_Content_Management"))
    {
    SERVICE_ACCOUNT_EMAIL = "126249466529-5h47uss6qq1rbmf811i0lj7v10nl0lbg@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("Maker_Studios_5"))
    {
    SERVICE_ACCOUNT_EMAIL = "833106971155-6sbmhgvv9tah91746m8e6sgbkq6651u1@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("disney_homeentertainment"))
    {
    SERVICE_ACCOUNT_EMAIL = "982260618136-fpe16o25meo97bj3ace3pgvnj3ov6u1t@developer.gserviceaccount.com";
    }
    
    if (ne.equalsIgnoreCase("DisneyXMedi"))
    {
    SERVICE_ACCOUNT_EMAIL = "383488126905-algatfpteqa3sktrc641pemupb6h78g7@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("DisneyESPN_EST"))
    {
    SERVICE_ACCOUNT_EMAIL = "880371961239-gsbgo798qf57p5q940s3qovpir2bgokh@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("disney_brazil"))
    {
    SERVICE_ACCOUNT_EMAIL = "426844826738-3do58jvupusk6c9mtdlcn7qlhn3jku8r@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("Disney_DADT"))
    {
    SERVICE_ACCOUNT_EMAIL = "998043838377-ph99irj6rdgqv7reofdag1sjs7s2496a@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("disney_emea"))
    {
    SERVICE_ACCOUNT_EMAIL = "429679833442-srt7c5vv5hajiaerjs5ccmt5gakrn6ef@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("Disney_Japan"))
    {
    SERVICE_ACCOUNT_EMAIL = "1008183980634-q0n02m335ftukjt9l6psukhs2jfq1j8j@developer.gserviceaccount.com";
    }
    
    if (ne.equalsIgnoreCase("disney_mexico"))
    {
    SERVICE_ACCOUNT_EMAIL = "981884863766-0rl3nmmrf6ir8hd21nl44tb3gn9qsna3@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("disney_russia"))
    {
    SERVICE_ACCOUNT_EMAIL = "165035547512-cv9tpeb1vd7o7oc1508aadjr5j7jmv7g@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("disney_espn"))
    {
    SERVICE_ACCOUNT_EMAIL = "375655585702-6psqrbmb9ud9ef3vtct2lq656vmf5mc6@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("Grantland"))
    {
    SERVICE_ACCOUNT_EMAIL = "976512521419-dqgeqsf040d7l98ekggb115eeaor9fvi@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("hollywoodrecords"))
    {
    SERVICE_ACCOUNT_EMAIL = "149230149586-e583qdcjonh9hlnpfvigi505dhstescn@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("disney_jimmykimmel"))
    {
    SERVICE_ACCOUNT_EMAIL = "583384549880-r7j9vu8isumg5lja8p7p5u3ld5ic0rc5@developer.gserviceaccount.com";
    }
    
    if (ne.equalsIgnoreCase("Lucasfilm"))
    {
    SERVICE_ACCOUNT_EMAIL = "32914157608-s270g704ev60fbc6f7htr0aq1b7ak6mg@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("MarvelEntertainment"))
    {
    SERVICE_ACCOUNT_EMAIL = "677093121196-rv746ummnjt8orlt6d7qmm2j7mnvtbho@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("disney_pixar"))
    {
    SERVICE_ACCOUNT_EMAIL = "629590603894-epqc5mq2f24lrdtuf85k9h4djesoi19j@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("take180com"))
    {
    SERVICE_ACCOUNT_EMAIL = "656360238167-jeaelkibla0pob5t4ej0i58k4m3dksku@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("utvmotionpictures"))
    {
    SERVICE_ACCOUNT_EMAIL = "168605916497-r6fo8erqhhvvc897256jlolerid6gm0i@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("WaltDisneyMusicCo_Pub"))
    {
    SERVICE_ACCOUNT_EMAIL = "337293930077-pdu7pme8joarfip9ivb18799g7p46ne1@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("Maker_Studios_6"))
    {
    SERVICE_ACCOUNT_EMAIL = "817536234680-posj4od4e21ou7iu3bm82u2pgdhfq3q2@developer.gserviceaccount.com";
    }
    if (ne.equalsIgnoreCase("H7XeNNPkVV3JZxXm-O-MCA"))
    {
    SERVICE_ACCOUNT_EMAIL = "905487231369-p7ebjrtaoq6guvouhq10malch8auuamf@developer.gserviceaccount.com";
    }
    Set<String> scopes = new HashSet<String>();
        scopes.add(YouTubeAnalyticsScopes.YT_ANALYTICS_READONLY);
        scopes.add(YouTubeAnalyticsScopes.YT_ANALYTICS_MONETARY_READONLY);
        scopes.add(YouTubeScopes.YOUTUBE_READONLY);
        scopes.add(YouTubeScopes.YOUTUBEPARTNER);
        scopes.add(YouTubeScopes.YOUTUBEPARTNER_CHANNEL_AUDIT);
        
        HttpTransport httpTransport = new NetHttpTransport();
        JacksonFactory jsonFactory = new JacksonFactory();
        
        GoogleCredential credential = new GoogleCredential.Builder()
            .setTransport(httpTransport)
            .setJsonFactory(jsonFactory)
            .setServiceAccountId(SERVICE_ACCOUNT_EMAIL)
            .setServiceAccountScopes(scopes)
            .setServiceAccountPrivateKeyFromP12File(
                    new java.io.File(SERVICE_ACCOUNT_PKCS12_FILE_PATH))
            .build();
        
      return credential;
        //return service;


    }
}
