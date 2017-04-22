package com.example.root.gitsearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

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
}
