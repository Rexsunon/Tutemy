package com.atornel.tutemy.Fragment.GDrive;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atornel.tutemy.Adapter.CatAdapter;
import com.atornel.tutemy.Common.HTTPDataHandler;
import com.atornel.tutemy.Model.CatModel.CatObject;
import com.atornel.tutemy.R;
import com.google.gson.Gson;

import static com.atornel.tutemy.Util.APILinks.GDRIVE_BASE_URL;
import static com.atornel.tutemy.Util.APILinks.TORRENT_BASE_URL;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CatFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CatFragment extends Fragment {


    SwipeRefreshLayout SwipeLayout;
    RecyclerView recyclerView;
    CatObject catObject;

    private final String apiLink = GDRIVE_BASE_URL + "wp-json/wp/v2/categories";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LatestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CatFragment newInstance(String param1, String param2) {
        CatFragment fragment = new CatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_latest, container, false);
        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        Log.i("API_URL", apiLink);
        loadFeed();


        return view;
    }

    private void loadFeed() {
        AsyncTask<String, String, String> loadFeedAsync = new AsyncTask<String, String, String>() {

            ProgressDialog mDialog = new ProgressDialog(getActivity());

            @Override
            protected void onPreExecute() {
                mDialog.setMessage("Loading, Please Wait...");
                mDialog.show();
                mDialog.setCancelable(false);
            }

            @Override
            protected String doInBackground(String... params) {

                String result;
                String result1;
                HTTPDataHandler http = new HTTPDataHandler();

                result1 = http.GetHTTPData(params[0]);
                result = "{\"items\": " +result1+ "}";
                Log.i("JSONResult", result);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {

                mDialog.dismiss();
                catObject = new Gson().fromJson(s,CatObject.class);
                CatAdapter adapter = new CatAdapter(catObject, getContext());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        };

        String url_get_data = apiLink;
        loadFeedAsync.execute(url_get_data);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
