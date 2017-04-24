package com.example.root.gitsearch;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.gitsearch.utilities.NetworkUtils;

import java.net.URL;

public class GithubQuery extends AppCompatActivity {
    private EditText mEditTextSearchBox;
    private TextView mTextViewUrlDisplay;
    private TextView mTextViewResultJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_query);
        mEditTextSearchBox = (EditText)findViewById(R.id.et_search_box);
        mTextViewUrlDisplay = (TextView)findViewById(R.id.tv_url_display);
        mTextViewResultJson = (TextView)findViewById(R.id.tv_github_search_result_json);
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

    }

    /**
     *
     * method menu dengan menampilakan toast ketika action search di click
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
