package com.example.gadsleaderboard;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gadsleaderboard.dummy.DummyContent.DummyItem;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ItemFragment2 extends Fragment {
    private List<LearnerResponse2> learnerResponseList;
    RecyclerView list;
    MyItemRecyclerViewAdapter2 learnersAdapter;
    PagerAdapter pagerAdapter;
    ViewPager viewPager;
    TabLayout tabLayout;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment2() {
    }
    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance(int columnCount) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

//        learnersAdapter = new MyItemRecyclerViewAdapter();
//        getAllLearners();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item2_list, container, false);
        list = view.findViewById(R.id.list);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();

            RecyclerView recyclerView = (RecyclerView) view;

            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            learnersAdapter = new MyItemRecyclerViewAdapter2();
            getAllSkillers();
        }
//        learnersAdapter = new MyItemRecyclerViewAdapter(learnerResponseList, mListener);

        return view;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        pagerAdapter = new PagerAdapter(getChildFragmentManager());
//        viewPager = view.findViewById(R.id.pager);
//        //viewPager.setAdapter(pagerAdapter);
//        tabLayout = view.findViewById(R.id.tab_layout);
//        //tabLayout.setupWithViewPager(viewPager);

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }

    public void getAllSkillers(){
        Call<List<LearnerResponse2>>  learnerResponseList =  ApiUtil2.getSkillService().getAllSkillers();
        learnerResponseList.enqueue((new Callback<List<LearnerResponse2>>() {
            @Override
            public void onResponse(Call<List<LearnerResponse2>> call, Response<List<LearnerResponse2>> response) {
                if(response.isSuccessful()){
                    List<LearnerResponse2> learnerResponses = response.body();

                    learnersAdapter.setData(learnerResponses);
                    list.setAdapter(learnersAdapter);
                    // Log.e("success", response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<List<LearnerResponse2>> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
            }
        }));
    }
//    public void getAllLearners(){
//        Call<List<LearnerResponse>> learnerList = ApiUtil.getLearnerService().getAllLearners();
//        learnerList.enqueue((new Callback<List<LearnerResponse>>() {
//            @Override
//            public void onResponse(Call<List<LearnerResponse>> call, Response<List<LearnerResponse>> response) {
//                if(response.isSuccessful()){
//                    List<LearnerResponse> learnerResponses = response.body();
//                    learnersAdapter.setData(learnerResponses);
//                    list.setAdapter(learnersAdapter);
//                    // Log.e("success", response.body().toString());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<LearnerResponse>> call, Throwable t) {
//                Log.e("failure", t.getLocalizedMessage());
//            }
//        }));
//    }
}
