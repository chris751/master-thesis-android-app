package com.thegoodthebadtheasian.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.thegoodthebadtheasian.myapplication.models.Action;

import java.util.ArrayList;
import java.util.List;

public class AddActionActivity extends AppCompatActivity implements ActionListAdapter.OnItemClickListener{

    public static final int PICK_ACTION_REQUEST = 1;

    private ArrayList<Action> actions;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_action);

        deleteThisMethodAtSomePoint();

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.actionListView);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ActionListAdapter(this, actions, this);
        recyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onItemClick(int position) {
        Toast.makeText(this, "SUCK BANANA", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ActionDetailsActivity.class);
        startActivityForResult(intent, PICK_ACTION_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PICK_ACTION_REQUEST){
            if(resultCode == Activity.RESULT_OK){
                Toast.makeText(this,"result ok", Toast.LENGTH_SHORT).show();
                setResult(Activity.RESULT_OK, data);
                finish();
            }
        }
    }

    private void deleteThisMethodAtSomePoint(){
        Action newAction = new Action("1234", "GA");
        ArrayList<Action> tempList = new ArrayList<Action>();
        tempList.add(newAction);

        actions = tempList;
    }
}
