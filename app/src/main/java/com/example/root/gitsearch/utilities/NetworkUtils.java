package com.example.root.gitsearch.utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by glmvn on 24/04/17.
 */

public class NetworkUtils {

    final static String GITHUB_BASE_URL = "https://api.github.com/search/repositories";

    final static String PARAM_QUERY = "q";
    /**
     * the sort field. One of Stars, fork , or updated.
     * Default: result are sorted by best match if no field is specified.
     */
    final static String PARAM_SORT = "sort";
    final static String sortby = "stars";

    /**
     * Builds the URL used to query github
     * @param githubSearchQuery the keyword that will be queried for
     * @return the URL to use to query the weather server
     */
    public static URL buildUrl (String githubSearchQuery){
        Uri builtUri = Uri.parse(GITHUB_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_QUERY, githubSearchQuery)
                .appendQueryParameter(PARAM_SORT, sortby)
                .build();

        URL url = null;
        try{
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * this method return the entire result from HTTP response
     * @param url ther URL to fetch the HTTP response from
     * @return ther contents of HTTP response
     * @throws IOException related to network and stream reading
     */

    public static String getResponseFromHttpUrl(URL url)throws IOException{
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput){
                return scanner.next();
            }else {
                return null;
            }
        }finally {
            urlConnection.disconnect();
        }
    }
}
