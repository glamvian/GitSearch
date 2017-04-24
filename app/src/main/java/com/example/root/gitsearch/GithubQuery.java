package com.example.root.gitsearch;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.gitsearch.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class GithubQuery extends AppCompatActivity {
    private EditText mEditTextSearchBox;
    private TextView mTextViewUrlDisplay;
    private TextView mTextViewResultJson;
    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_query);
        mEditTextSearchBox = (EditText)findViewById(R.id.et_search_box);
        mTextViewUrlDisplay = (TextView)findViewById(R.id.tv_url_display);
        mTextViewResultJson = (TextView)findViewById(R.id.tv_github_search_result_json);
        mErrorMessageDisplay = (TextView)findViewById(R.id.tv_error_message_display);
        mLoadingIndicator = (ProgressBar)findViewById(R.id.pb_loading_indicator);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    /**
     *this method retrieves the search text from the EditText, construct
     * The URL using @link networkUtils for the github repository you'd like
     * to find , displays that url in a textview , and finally fires off an asyntask to
     * perform the get request using our (not yet creating) @link githubQueryTask
     */
    private void makeGithubSearchQuery(){
        String githubQuer = mEditTextSearchBox.getText().toString();
        URL githubSearchUrl = NetworkUtils.buildUrl(githubQuer);
        mTextViewUrlDisplay.setText(githubSearchUrl.toString());

        new GithubQueryTask().execute(githubSearchUrl);

    }

    /**
     * This method will make the view for the json data visible and
     *  hide error message.
     *  since it is okay to redundantly set the visibility of view, we don't need
     *  check wheter each view is currently visible or invisible
     */
    private void showJsonDataView(){
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mTextViewResultJson.setVisibility(View.VISIBLE);
    }

    /**
     * this method will ake error message visible and hide the json view.
     * since it is okay to redundantly set the visibily of view, we don't need
     * to check wheter each view is currently visibl or invisible
     */
    private void showErrorMessage(){
        mTextViewResultJson.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    //class AsyncTask untuk menjalankan second thread di background
    public class GithubQueryTask extends AsyncTask<URL, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params [0];
            String githubSearchResults = null;
            try {
                githubSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            }catch (IOException e){
                e.printStackTrace();
            }
            return githubSearchResults;
        }

        @Override
        protected void onPostExecute(String githubSearchResults) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (githubSearchResults != null && !githubSearchResults.equals("")){
                showJsonDataView();
                mTextViewResultJson.setText(githubSearchResults);
            }else {
                showErrorMessage();
            }
        }
    }

    /**
     *ketika menu search di klik maka akan eksekusi method makeGithubSearchQuery()
     *
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemWasclikcedId = item.getItemId();
        if (itemWasclikcedId == R.id.action_search){
            makeGithubSearchQuery();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
