package com.example.root.gitsearch;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
     *
     * method menu dengan menampilakan toast ketika action search di click
     *
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemWasclikcedId = item.getItemId();
        if (itemWasclikcedId == R.id.action_search){
            Context context = GithubQuery.this;
            String textToShow = "search clicked";
            Toast.makeText(context,textToShow,Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
