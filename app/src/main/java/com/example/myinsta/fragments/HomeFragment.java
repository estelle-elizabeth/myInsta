package com.example.myinsta.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myinsta.HomeAdapter;
import com.example.myinsta.Post;
import com.example.myinsta.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView rvHome;
    protected HomeAdapter adapter;
    protected List<Post> userPosts;
    private SwipeRefreshLayout swipeContainer;
    private final static String TAG = "HomeFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        userPosts = new ArrayList<Post>();
        rvHome = view.findViewById(R.id.rvHome);
        adapter = new HomeAdapter(getContext(), userPosts);
        swipeContainer = view.findViewById(R.id.swipeContainer);
        rvHome.setAdapter(adapter);
        rvHome.setLayoutManager(new LinearLayoutManager(getContext()));
        queryPost();
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                queryPost();
                swipeContainer.setRefreshing(false);
            }
        });

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    protected void queryPost() {
        // Specify which class to query

        ParseQuery<Post> query = new ParseQuery<Post>(Post.class);
        query.include(Post.KEY_USER);
        query.setLimit(20);
        query.addDescendingOrder(Post.KEY_CREATE_AT);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null){
                    Log.e(TAG, "Issue with post query");
                    e.printStackTrace();
                    return;
                }

                userPosts.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
